package com.carl.yimai.service.impl;

import cn.carl.page.PageResult;
import com.carl.yimai.adminmapper.AdminMapper;
import com.carl.yimai.service.AdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>Title: com.carl.yimai.service.impl AdminServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/4/2 12:35
 * @Version 1.0
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService{

    @Resource(name = "adminMapper")
    private AdminMapper adminMapper;

    @Value("${ADMIN_ALL_USER_PAGE}")
    private Integer rows;


    @Override
    public PageResult<HashMap> selectAllUser(int page, int state) {
        PageResult<HashMap> result = adminMapper.selectAllUser(page, state);
        return result;
    }
}
