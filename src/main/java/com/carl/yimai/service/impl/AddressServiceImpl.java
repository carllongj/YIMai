package com.carl.yimai.service.impl;

import cn.carl.string.StringTools;
import com.carl.yimai.mapper.YmUserAddrMapper;
import com.carl.yimai.po.YmUserAddr;
import com.carl.yimai.po.YmUserAddrExample;
import com.carl.yimai.service.AddressService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
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

    @Override
    public Result addAddress(String uid,YmUserAddr addr) {
        addr.setUid(uid);
        addr.setId(StringTools.uuid());
        addr.setCreated(new Date());
        addr.setUpdated(new Date());
        addr.setDefAddr((byte) 0);
        addrMapper.insert(addr);
        return Result.ok();
    }

    @Override
    public Result deleteAddress(String uid, String id) {
        YmUserAddr addr = addrMapper.selectByPrimaryKey(id);

        if (null == addr || !addr.getUid().equals(uid)){
            return Result.error("没有相关的地址信息");
        }

        //执行删除
        addrMapper.deleteByPrimaryKey(id);

        return Result.ok();
    }

    @Override
    public Result setDefault(String uid, String id) {
        YmUserAddrExample example = new YmUserAddrExample();
        example.createCriteria().andDefAddrEqualTo((byte) 1).andUidEqualTo(uid);

        List<YmUserAddr> list = addrMapper.selectByExample(example);

        if (null == list || list.size() == 0){
            YmUserAddr addr = addrMapper.selectByPrimaryKey(id);

            if (null == addr || !addr.getUid().equals(uid)){
                return Result.error("没有相关的地址信息");
            }

            addr.setDefAddr((byte) 1);
            //执行更新
            addrMapper.updateByPrimaryKey(addr);
            return Result.ok();
        }else{
            YmUserAddr addr = list.get(0);
            addr.setDefAddr((byte) 0);
            addrMapper.updateByPrimaryKeySelective(addr);
            YmUserAddr addrDef = addrMapper.selectByPrimaryKey(id);

            if (null == addr || !addr.getUid().equals(uid)){
                return Result.error("没有相关的地址信息");
            }

            addrDef.setDefAddr((byte) 1);
            addrMapper.updateByPrimaryKeySelective(addrDef);
            return Result.ok();
        }
    }
}
