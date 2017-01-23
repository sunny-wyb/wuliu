/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.util;

import java.io.File;

/**
 * 类ApplicationContext.java的实现描述：TODO 类实现描述 
 * @author yunbin.wangyb 2017年1月20日 下午1:45:07
 */
public class ApplicationContext {

    public static String tmp_folder;

    public void init() {
        File file = new File(tmp_folder);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
    
    public String getTmp_folder() {
        return tmp_folder;
    }
    
    public void setTmp_folder(String tmp_folder) {
        ApplicationContext.tmp_folder = tmp_folder;
    }
}
