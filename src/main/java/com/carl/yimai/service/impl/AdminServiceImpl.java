package com.carl.yimai.service.impl;

import cn.carl.page.PageResult;
import com.carl.yimai.adminmapper.AdminMapper;
import com.carl.yimai.mapper.YmUserMapper;
import com.carl.yimai.po.YmUser;
import com.carl.yimai.pojo.AdminItemCondition;
import com.carl.yimai.service.AdminService;
import com.carl.yimai.service.UserService;
import com.carl.yimai.web.utils.Result;
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

    @Resource(name = "ymUserMapper")
    private YmUserMapper userMapper;

    @Resource(name = "userService")
    private UserService userService;

    @Value("${ADMIN_ALL_USER_PAGE}")
    private Integer rows;

    @Value("${EMAIL_FORBIDDEN_CONTENT}")
    private String EMAIL_FORBIDDEN_CONTENT;

    @Value("${EMAIL_FORBIDDEN_TITLE}")
    private String EMAIL_FORBIDDEN_TITLE;


    @Override
    public PageResult<HashMap> selectAllUser(int page, int state) {
        PageResult<HashMap> result = adminMapper.selectAllUser(page, state);
        return result;
    }

    @Override
    public PageResult<HashMap> selectItemsList(AdminItemCondition condition, int page) {
        PageResult<HashMap> result = adminMapper.selectItems(condition, page);
        return result;
    }

    @Override
    public Result checkItemCate(String name) {
        Result result = adminMapper.checkItemCate(name);
        return result;
    }

    @Override
    public Result forbiddenUser(String adminId, String id) {
        YmUser user = userMapper.selectByPrimaryKey(id);

        if (null == user){
            return Result.error("没有对应的用户信息");
        }

        if (0 == user.getForbidden()){
            return Result.error("该用户已被禁用,不可再次禁用");
        }

        //发送邮件
        try{
            userService.sendMail(user.getEmail(),EMAIL_FORBIDDEN_TITLE,EMAIL_FORBIDDEN_CONTENT);
        }catch (Exception e){

        }
        user.setForbidden((byte)0);
        user.setEditor(adminId);
        userMapper.updateByPrimaryKeySelective(user);
        return Result.ok();
    }
}
