package com.carl.yimai.service.impl;

import com.carl.yimai.service.UserService;
import com.carl.yimai.web.utils.Result;

/**
 * <p>Title: com.carl.yimai.service.impl UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/24 13:08
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {

    @Override
    public Result checkUsername(String username) {
        return null;
    }

    @Override
    public Result checkEmail(String email) {
        return null;
    }

    @Override
    public Result register(YmUser user) {
        return null;
    }

    @Override
    public Result login(String username, String password) {
        return null;
    }

    @Override
    public Result activated(String activeCode) {
        return null;
    }
}
