package com.carl.yimai.adminmapper.impl;

import com.carl.yimai.adminmapper.AdminUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Calendar;

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

    private String checkForDate(int current){
        if (current < 10) {
            return "0" + current;
        }
        return String.valueOf(current);
    }
}
