package com.carl.yimai.service;

import cn.carl.page.PageResult;
import com.carl.yimai.pojo.AdminItemCondition;
import com.carl.yimai.web.utils.Result;

import java.util.HashMap;

/**
 * <p>Title: com.carl.yimai.service</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/4/2 12:35
 * @Version 1.0
 */
public interface AdminService {

    PageResult<HashMap> selectAllUser(int page,int state);

    PageResult<HashMap> selectItemsList(AdminItemCondition condition,int page);

    Result checkItemCate(String name);
}
