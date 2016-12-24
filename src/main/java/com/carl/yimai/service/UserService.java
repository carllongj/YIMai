package com.carl.yimai.service;

import com.carl.yimai.po.YmUser;
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
    Result login(String username,String password);

    /**
     * 激活用户的账户
     * @param userId 用户的id
     * @param activeCode
     * @return
     */
    Result activated(String userId,String activeCode);

}
