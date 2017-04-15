package com.carl.yimai.service;

import com.carl.yimai.po.YmUserAddr;
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

    /**
     * 新增一条用户的地址
     * @param uid 用户的id
     * @param addr 地址信息
     * @return
     */
    Result addAddress(String uid,YmUserAddr addr);

    /**
     * 删除用户的一条地址信息
     * @param uid
     * @param id
     * @return
     */
    Result deleteAddress(String uid,String id);

    /**
     * 将用户的地址设置为默认
     * @param uid
     * @param id
     * @return
     */
    Result setDefault(String uid,String id);
}
