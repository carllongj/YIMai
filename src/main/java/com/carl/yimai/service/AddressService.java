package com.carl.yimai.service;

import com.carl.yimai.web.utils.Result;

/**
 * <p>Title: com.carl.yimai.service</p>
 * <p>Description: 地址服务接口 </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/4/12 17:15
 * @Version 1.0
 */
public interface AddressService {

    /**
     * 获取用户的所有的地址信息
     * @param id 用户的id
     * @return
     */
    Result getMyAddresses(String id);
}
