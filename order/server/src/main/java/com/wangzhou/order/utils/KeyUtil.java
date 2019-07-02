package com.wangzhou.order.utils;

import java.util.Random;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:20:20
 **/
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */
    //synchronized避免多线程的时候生成同样的订单号
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        //随机生成0到n之间的随机int值，并保证6位
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
