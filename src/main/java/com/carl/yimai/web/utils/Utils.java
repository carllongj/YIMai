package com.carl.yimai.web.utils;

import com.sun.jdi.IntegerType;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * 产生订单的id
 * <p>Title: com.carl.yimai.web.utils IDUtils</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/29 16:45
 * @Version 1.0
 */
public class Utils {

    /**
     * 生成订单的id
     * @return
     */
    public static Long getOrderId(){
            long millis = System.nanoTime();
            Random random = new Random();
            int end2 = random.nextInt(99);
            //如果不足两位前面补0
            String str = millis + String.format("%02d", end2);
            Long id = new Long(str.substring(2,str.length()));
            return id;
    }

    public static String getAdminId(HttpServletRequest request){
        return (String) request.getAttribute("userId");
    }


    public static boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("x-requested-with") != null;
    }
}