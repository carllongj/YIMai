package com.carl.yimai.service;

import cn.carl.page.PageResult;

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
}
