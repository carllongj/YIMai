package com.carl.yimai.service.impl;

import com.carl.yimai.mapper.YmUserAddrMapper;
import com.carl.yimai.po.YmUserAddr;
import com.carl.yimai.po.YmUserAddrExample;
import com.carl.yimai.service.AddressService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: com.carl.yimai.service.impl AddressServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/4/12 17:16
 * @Version 1.0
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Resource(name = "ymUserAddrMapper")
    private YmUserAddrMapper addrMapper;

    /**
     * 获取用户的地址信息
     * @param id 用户的id
     * @return
     */
    @Override
    public Result getMyAddresses(String id) {

        YmUserAddrExample example = new YmUserAddrExample();
        example.createCriteria().andUidEqualTo(id);
        List<YmUserAddr> addrs = addrMapper.selectByExample(example);
        return Result.ok(addrs);
    }
}
