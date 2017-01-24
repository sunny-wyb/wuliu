/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类OrderNumberUtil.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月22日 上午10:22:11
 */
public class WuliuOrderNumberUtil {

    public static String getOrderNumber(Date date, Long orderIndex, Integer count) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        StringBuffer sb = new StringBuffer();
        sb.append(sdf.format(date)).append("-").append(orderIndex).append("-").append(count);

        return sb.toString();
    }

    /* public static Date getDateFromOrderNumber(String orderNumber) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = orderNumber.substring(0, 8);
        return sdf.parse(dateStr);
    }

    public static Long getOrderIndexFromOrderNumber(String orderNumber) {
        String orderIndexStr = orderNumber.substring(8, 11);
        return Long.parseLong(orderIndexStr);
    }*/
}
