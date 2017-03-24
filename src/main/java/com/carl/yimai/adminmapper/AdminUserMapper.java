package com.carl.yimai.adminmapper;

import org.springframework.stereotype.Repository;

/**
 * <p>Title: com.carl.yimai.adminmapper</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/3/24 11:26
 * @Version 1.0
 */
public interface AdminUserMapper {

    Integer[] selectUserCount();
}
