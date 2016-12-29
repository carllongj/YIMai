package com.carl.yimai.web.utils;

import com.sun.jdi.IntegerType;

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
public class IDUtils {

    /**
     * 生成订单的id
     * @return
     */
    public static Integer getOrderId(){
            long millis = System.nanoTime();
            Random random = new Random();
            int end2 = random.nextInt(99);
            //如果不足两位前面补0
            String str = millis + String.format("%02d", end2);
            Integer id = new Integer(str);
            return id;
        }
    }
