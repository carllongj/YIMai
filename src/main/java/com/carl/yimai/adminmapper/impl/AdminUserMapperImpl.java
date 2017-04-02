package com.carl.yimai.adminmapper.impl;

import cn.carl.page.PageResult;
import com.carl.yimai.adminmapper.AdminUserMapper;
import com.carl.yimai.web.utils.Page;
import com.carl.yimai.web.utils.Result;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Title: com.carl.yimai.adminmapper.impl AdminUserMapperImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/3/24 11:27
 * @Version 1.0
 */
@Repository("adminUserMapper")
public class AdminUserMapperImpl implements AdminUserMapper {

    @Resource(name = "sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    @Value("${ADMIN_ALL_USER_PAGE}")
    private Integer rows;

    private Calendar cal = Calendar.getInstance();

    @Override
    public Integer[] selectUserCount() {
        Integer[] arr = new Integer[12];
        SqlSession session = sqlSessionFactory.openSession();
        int current = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        for (int i = 11; i >= 0;i--) {
            if (current < 1 ) {
                current = 12;
                year -= 1;
            }
            Integer count = session.selectOne("adminManageUserQuery.selectCountLastest", year + "-" + checkForDate(current--));
            arr[i] = count;
        }
        return arr;
    }

    @Override
    public PageResult<HashMap> selectAllUser(int currentPage, int state) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Page page = Page.getPageInstance(currentPage, rows);
            Long total = this.getTotal(page, state);
            List<HashMap> users = session.selectList("adminManageUserQuery.selectAllUser", page);
            if (null != users) {
                return PageResult.newInstance(total, rows, users);
            }
        }finally {
            session.commit();
            session.close();
        }
        return null;
    }

    /**
     * 获取查询的总的记录数
     * @param page
     * @param state
     * @return
     */
    private Long getTotal(Page page,int state){
        SqlSession session = sqlSessionFactory.openSession();
        Long total = session.selectOne("adminManageUserQuery.selectAllUserCount",page);
        page.setState(state);
        session.commit();
        session.close();
        return total;
    }

    private String checkForDate(int current){
        if (current < 10) {
            return "0" + current;
        }
        return String.valueOf(current);
    }
}
