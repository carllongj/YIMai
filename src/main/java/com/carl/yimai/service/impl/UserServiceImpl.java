package com.carl.yimai.service.impl;

import cn.carl.cache.redis.RedisCache;
import cn.carl.string.StringTools;
import com.alibaba.fastjson.JSON;
import com.carl.yimai.mapper.YmUserMapper;
import com.carl.yimai.po.YmUser;
import com.carl.yimai.po.YmUserExample;
import com.carl.yimai.service.UserService;
import com.carl.yimai.web.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
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

    @Override
    public Result checkUsername(String username) {
        //创建查询条件
        YmUserExample example = new YmUserExample();
        YmUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<YmUser> ymUsers = userMapper.selectByExample(example);

        if(null != ymUsers && ymUsers.size() > 0){
            return Result.error("用户名已经被注册");
        }

        return Result.ok();
    }

    @Override
    public Result checkEmail(String email) {
        //创建查询条件
        YmUserExample example = new YmUserExample();
        YmUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(email);
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

            //补全信息
            String id = StringTools.uuid();
            user.setId(id);
            user.setCreated(new Date());
            user.setState(0);
            user.setUpdate(new Date());
            userMapper.insert(user);

            //注册成功后将邮箱验证码保存到redis中
            String hash = REDIS_EMAIL_ACTIVE_CODE;
            String key = "userId:" + id;
            String value = StringTools.uuid() + "_" + StringTools.uuid();
            redisCache.hset(hash, key, value);
            return Result.ok();
        }

        return Result.error("系统错误,请稍候...");
    }

    @Override
    public Result login(String username, String password) {
        //创建查询条件
        YmUserExample example = new YmUserExample();
        YmUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(username);
        List<YmUser> ymUsers = userMapper.selectByExample(example);

        if(null != ymUsers || ymUsers.size() == 0 ){
            return Result.error("用户名或者密码错误");
        }

        if(null != ymUsers && ymUsers.size() > 0){
            YmUser user = ymUsers.get(0);
            //对用户的密码进行验证,如果通过,将其保存在redis缓存中
            if(user.getPassword().equals(password)){
                String key = REDIS_USER_SESSION + ":user:" + user.getId();
                redisCache.set(key, JSON.toJSONString(user));
                redisCache.expire(key,REDIS_USER_SESSION_EXPIRE);
                return Result.ok();
            }
        }
        return Result.error("用户名或者密码错误");
    }

    @Override
    public Result activated(String userId,String activeCode) {
        String key = "userId:" + userId ;
        String value = redisCache.hget(REDIS_EMAIL_ACTIVE_CODE, key);

        if (StringUtils.hasText(value) && value.equals(activeCode)){
            YmUser ymUser = userMapper.selectByPrimaryKey(userId);
            if(null == ymUser ){
                return Result.error("该验证码无效,请核对后在激活");
            }

            if(ymUser.getState() == 1){
                return Result.error("该账户已经激活");
            }

            ymUser.setState(1);

            userMapper.insert(ymUser);

            return Result.ok();
        }
        return Result.error("无效的验证码");
    }
}
