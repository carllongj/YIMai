package com.carl.yimai.service.impl;

import cn.carl.cache.redis.RedisCache;
import com.alibaba.fastjson.JSON;
import com.carl.yimai.po.YmUser;
import com.carl.yimai.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>Title: com.carl.yimai.service.impl LoginTokenImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 14:19
 * @Version 1.0
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    @Resource(name = "redisCache")
    private RedisCache redisCache;

    @Value("${LOGIN_PAGE_URL}")
    private String LOGIN_PAGE_URL;

    @Value("${REDIS_USER_SESSION_EXPIRE}")
    private Integer REDIS_USER_SESSION_EXPIRE;

    @Value("${REDIS_USER_SESSION}")
    private String REDIS_USER_SESSION;

    @Override
    public YmUser getUserToken(String token) {
        String ymUserString = redisCache.get(REDIS_USER_SESSION + ":user:" + token);
        YmUser ymUser = JSON.parseObject(ymUserString, YmUser.class);
        //查询到用户的令牌,重新设置过期时间
        if (null != ymUser){
            redisCache.expire(token,REDIS_USER_SESSION_EXPIRE);
        }
        return ymUser;
    }

    @Override
    public String getLoginURL() {
        return this.LOGIN_PAGE_URL;
    }
}
