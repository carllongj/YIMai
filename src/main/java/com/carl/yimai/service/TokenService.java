package com.carl.yimai.service;

import com.carl.yimai.po.YmUser;

/**
 * 用户是否已经登录
 * <p>Title: com.carl.yimai.service</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 14:18
 * @Version 1.0
 */
public interface TokenService {

    /**
     *根据token获取用户的信息
     * @param token
     * @return
     */
    YmUser getUserToken(String token);

    /**
     * 获取当前项目的登录的链接地址
     * @return
     */
    String getLoginURL();

}
