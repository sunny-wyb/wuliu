/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package wuliu.test;

import java.text.DecimalFormat;

/**
 * 类Test.java的实现描述：TODO 类实现描述 
 * @author yunbin.wangyb 2017年1月26日 下午3:42:56
 */
public class Test {

    public static void main(String args[]) {
        DecimalFormat df = new DecimalFormat("0.##");
        System.out.println(df.format(0));
        System.out.println(df.format(1.0));
        System.out.println(df.format(1.10));
        System.out.println(df.format(1.01));
    }
}
