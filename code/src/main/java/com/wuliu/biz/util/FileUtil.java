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
 * 类FileUtil.java的实现描述：TODO 类实现描述 
 * @author yunbin.wangyb 2017年1月23日 下午1:57:09
 */
public class FileUtil {

    public static boolean delete(File file) {
        if (file.isFile()) {
            return file.delete();
        }
        else {
            boolean flag = true;
            for (File item : file.listFiles()) {
                flag = flag & delete(item);
            }
            flag = flag & file.delete();
            return flag;
        }
    }
    
    public static boolean delete(String filePath) {
        File file = new File(filePath);
        return delete(file);
    }
}
