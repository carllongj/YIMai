package com.carl.yimai.service;

import cn.carl.page.PageResult;
import com.carl.yimai.po.YmUser;
import com.carl.yimai.pojo.UserInfo;
import com.carl.yimai.web.utils.Result;

/**
 * 用户登录注册服务
 * <p>Title: com.carl.yimai.service</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/24 12:42
 * @Version 1.0
 */
public interface UserService {

    /**
     * 检测当前用户名是否已经被注册
     * 如果被注册返回Result中的status为false,信息为用户名已被注册
     * @param username
     * @return
     */
    Result checkUsername(String username);

    /**
     * 检测当前的邮箱是否被注册
     * @param email
     * @return
     */
    Result checkEmail(String email);

    /**
     * 用户注册服务
     * 注册
     * @param user
     * @return
     */
    Result register(YmUser user);

    /**
     * 用户登录的服务
     * @param username
     * @param password
     * @return
     */
    Result login(String username,String password,boolean remember);

    /**
     * 激活用户的账户
     * @param userId 用户的id
     * @param activeCode
     * @return
     */
    Result activated(String userId,String activeCode);

    /**
     * 允许用户完善自己的个人信息
     * @param userInfo
     * @return
     */
    Result updateUserInfo(UserInfo userInfo);

    /**
     * 允许用户能够重新获取邮箱激活的验证信息
     * @param username
     * @param passwd
     * @return
     */
    Result resendEmail(String username,String passwd);

    /**
     * 通过用户的id来查询卖家联系信息
     * @param userId
     * @return
     */
    Result getUserContactById(String userId);

    /**
     * 获取指定id的用户的信息
     * @param userId
     * @return
     */
    Result getUserInfoById(String userId);

    /**
     * 发送邮件的服务
     * @param to
     * @param subject
     * @param content
     */
    void sendMail(String to,String subject,String content);

    /**
     * 管理员查询用户的信息
     * @param page
     * @param state
     * @return
     */
    PageResult<YmUser> getUserList(Integer page, Integer state);

    /**
     * 获取最近的注册的人数
     * @return
     */
    Result getLastestRegister();
}
