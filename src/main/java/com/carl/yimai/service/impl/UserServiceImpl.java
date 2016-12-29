package com.carl.yimai.service.impl;

import cn.carl.cache.redis.RedisCache;
import cn.carl.mail.Mail;
import cn.carl.mail.MailTools;
import cn.carl.string.StringTools;
import cn.carl.web.cookie.CookieTools;
import com.alibaba.fastjson.JSON;
import com.carl.yimai.mapper.YmUserMapper;
import com.carl.yimai.po.YmUser;
import com.carl.yimai.po.YmUserExample;
import com.carl.yimai.service.UserService;
import com.carl.yimai.web.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import sun.misc.Unsafe;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户服务service
 * <p>Title: com.carl.yimai.service.impl UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/24 13:08
 * @Version 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name="ymUserMapper")
    private YmUserMapper userMapper;

    @Resource(name = "redisCache")
    private RedisCache redisCache;

    @Value("${REDIS_USER_SESSION}")
    private String REDIS_USER_SESSION;

    @Value("${REDIS_USER_SESSION_EXPIRE}")
    private Integer REDIS_USER_SESSION_EXPIRE;

    @Value("${REDIS_EMAIL_ACTIVE_CODE}")
    private String REDIS_EMAIL_ACTIVE_CODE;

    @Value("${MAIL_FROM_ADDRESS}")
    private String MAIL_FROM_ADDRESS;

    @Value("${MAIL_ADDRESS_HOST}")
    private String MAIL_ADDRESS_HOST;

    @Value("${MAIL_ADDRESS_USERNAME}")
    private String MAIL_ADDRESS_USERNAME;

    @Value("${MAIL_ADDRESS_PASSWORD}")
    private String MAIL_ADDRESS_PASSWORD;

    @Value("${MAIL_SUBJECT_TEXT}")
    private String MAIL_SUBJECT_TEXT;

    @Value("${MAIL_CONTENT_MAIN_TEXT}")
    private String MAIL_CONTENT_MAIN_TEXT;

    @Value("${MAIL_CONTENT_MAIL_BASE_URL}")
    private String MAIL_CONTENT_MAIL_BASE_URL;

    @Override
    public Result checkUsername(String username) {
        //创建查询条件
        YmUserExample example = new YmUserExample();
        YmUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<YmUser> ymUsers = userMapper.selectByExample(example);

        if(null == ymUsers || ymUsers.size() == 0){
            return Result.ok();
        }
        return Result.error("用户名已经被注册");
    }

    @Override
    public Result checkEmail(String email) {
        //创建查询条件
        YmUserExample example = new YmUserExample();
        YmUserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        List<YmUser> ymUsers = userMapper.selectByExample(example);

        if(null == ymUsers || ymUsers.size() == 0){
            return Result.ok();
        }
        return Result.error("邮箱已经被注册");

    }

    /**
     * 用户注册时,将邮箱的验证码保存到redis中
     * @param user
     * @return
     */
    @Override
    public Result register(YmUser user) {

        //再次检验当前的用户名是否唯一
        Result repeat = this.checkUsername(user.getUsername());

        if (!repeat.isStatus()){
            return repeat;
        }

        //再次校验当前用户需要注册的邮箱是否唯一
        Result result = this.checkEmail(user.getEmail());

        if (!result.isStatus()){
            return result;
        }

        if(repeat.isStatus() && result.isStatus()){

            //补全信息,生成用户的唯一主键标识
            String id = StringTools.uuid();
            user.setId(id);
            user.setCreated(new Date());
            user.setState(0);
            user.setUpdated(new Date());
            user.setAdmin(0);
            //对用户的密码使用md5加密处理
            String passwd = user.getPasswd();
            String md5DigestAsHex = DigestUtils.md5DigestAsHex(passwd.getBytes());
            user.setPasswd(md5DigestAsHex);
            userMapper.insert(user);

            //进行发送邮件
            return resendEmail(id,user.getEmail());
        }

        return Result.error("系统错误,请稍候...");
    }

    @Override
    public Result login(String username, String password) {
        //创建查询条件
        YmUserExample example = new YmUserExample();
        YmUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<YmUser> ymUsers = userMapper.selectByExample(example);

        if(null == ymUsers || ymUsers.size() == 0 ){
            return Result.error("用户名或者密码错误");
        }

        if(null != ymUsers && ymUsers.size() > 0){
            YmUser user = ymUsers.get(0);
            String realPass = DigestUtils.md5DigestAsHex(password.getBytes());
            //对用户的密码进行验证,如果通过,将其保存在redis缓存中
            if(user.getPasswd().equals(realPass)){
                //如果用户没有激活该账户,需要激活后才能登录
                if (user.getState() == 0){
                    return Result.error("该账户还没有激活");
                }

                String cookieKey = StringTools.uuid();

                String key = REDIS_USER_SESSION + ":user:" + cookieKey;

                //将用户的密码置为null
                user.setPasswd(null);
                redisCache.set(key, JSON.toJSONString(user));
                redisCache.expire(key,REDIS_USER_SESSION_EXPIRE);
                CookieTools.setCookie("USER_TOKEN",cookieKey);
                return Result.ok();
            }
        }
        return Result.error("用户名或者密码错误");
    }

    @Override
    public Result activated(String key,String activeCode) {
        String redisKey = "code:" + key ;
        String value = redisCache.hget(REDIS_EMAIL_ACTIVE_CODE, redisKey);

        if (StringUtils.hasText(value) && value.equals(activeCode)){
            //获取保存用户id键值对的
            String userKey = "userId:" + key;
            String userId = redisCache.hget(REDIS_EMAIL_ACTIVE_CODE, userKey);
            YmUser ymUser = userMapper.selectByPrimaryKey(userId);

            if(null == ymUser ){
                return Result.error("该验证码无效,请重新获取验证信息");
            }

            if(ymUser.getState() == 1){
                return Result.error("该账户已经激活");
            }

            ymUser.setState(1);

            //删除用户注册的缓存中的相关信息
            redisCache.hdel(REDIS_EMAIL_ACTIVE_CODE,redisKey);
            redisCache.hdel(REDIS_EMAIL_ACTIVE_CODE,userKey);

            userMapper.insert(ymUser);

            return Result.ok();
        }
        return Result.error("无效的验证码");
    }

    @Override
    public Result updateUserInfo(YmUser ymUser) {
        userMapper.updateByPrimaryKeySelective(ymUser);
        return Result.ok();
    }

    /**
     * 将需要发送邮件独立出来,供用户的其他需求
     * @param username
     * @param passwd
     * @return
     */
    @Override
    public Result resendEmail(String username,String passwd) {

        YmUser user = checkPasswd(username, passwd);

        if (null == user){
            return Result.error("用户名或者密码错误");
        }

        return resend(user.getId(),user.getEmail());
    }

    /**
     * 当用户已经被查询出来后,只需要使用id就可发送邮件
     * @param userId
     * @return
     */
    private Result resend(String userId,String email){

        //注册成功后将邮箱验证码保存到redis中
        String hash = REDIS_EMAIL_ACTIVE_CODE;
        String redisId = StringTools.uuid();

        String key = "code:" + redisId;
        String value = StringTools.uuid() + "_" + StringTools.uuid();
        redisCache.hset(hash, key, value);

        //将用户id保存到redis缓存中!
        String userKey = "userId:" + redisId;
        redisCache.hset(hash, userKey, userId);

        String content = this.getEmailContent(key, value);
        this.sendMail(email, MAIL_SUBJECT_TEXT, content);
        return Result.ok();

    }

    /**
     * 校验用户的用户名和密码是否正确
     * @param username
     * @param passwd
     * @return
     */
    private YmUser checkPasswd(String username,String passwd){

        YmUserExample example = new YmUserExample();
        YmUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);

        List<YmUser> ymUsers = userMapper.selectByExample(example);

        if (null != ymUsers && ymUsers.size() == 1){
            return ymUsers.get(0);
        }

        return null;
    }

    /**
     * 设置邮件的主体内容
     * @param key
     * @param code
     * @return
     */
    private String getEmailContent(String key,String code){
        StringBuilder sb = new StringBuilder(MAIL_CONTENT_MAIN_TEXT + MAIL_CONTENT_MAIL_BASE_URL);
        sb.append(key + "/").append(code);
        return sb.toString();
    }

    /**
     * 发送邮件
     * @param to
     * @param subject
     * @param content
     */
    private void sendMail(String to,String subject,String content){
        Mail mail = new Mail(MAIL_FROM_ADDRESS,to,subject,content);
        javax.mail.Session session = MailTools.getSession(
                MAIL_ADDRESS_HOST, MAIL_ADDRESS_USERNAME, MAIL_ADDRESS_PASSWORD);
        MailTools.sendMsg(session,mail);
    }
}
