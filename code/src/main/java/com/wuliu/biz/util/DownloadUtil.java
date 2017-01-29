/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类DownloadUtil.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月20日 上午11:03:30
 */
public class DownloadUtil {

    private final static Logger       logger         = LoggerFactory.getLogger(DownloadUtil.class);

    public static Map<String, String> contentTypeMap = new HashMap<String, String>();

    static {
        contentTypeMap.put("zip", "application/x-msdownload");
        contentTypeMap.put("xlsx", "application/xlsx");
    }

    public static HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes() , "ISO-8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());

            String extension = getExtension(filename);
            if (extension == null) {
                logger.error("error extension , filename : " + filename);
                return null;
            }

            String contentType = contentTypeMap.get(extension);
            if (contentType == null) {
                logger.error("error extension , filename : " + filename);
                return null;
            }

            response.setContentType(contentType);
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            return response;
        } catch (IOException ex) {
            logger.error("download error", ex);
            return null;
        }
    }

    private static String getExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return null;
        } else {
            return fileName.substring(index + 1);
        }
    }
}
