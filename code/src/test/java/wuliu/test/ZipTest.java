/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package wuliu.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.wuliu.api.orderbusiness.model.WuliuMergedOrderModel;

/**
 * 类ZipTest.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月19日 下午11:21:07
 */
public class ZipTest {

    public static void main(String args[]) throws EncryptedDocumentException, InvalidFormatException, IOException {

        POITest2 instance = new POITest2();
        List<WuliuMergedOrderModel> mergedOrders = instance.fetchData();
        String path = instance.export("/tmp" , mergedOrders);
        
        ZipTest instance1 = new ZipTest();
        instance1.doZip("/tmp", path);
    }

    private void doZip(String tempPath, String folderPath) throws IOException {
        File file = new File(folderPath);
        
        File zipFile = new File(tempPath , file.getName() + ".zip");
        
        InputStream input = null ;  // 定义文件输入流
        ZipOutputStream zipOut = null ; // 声明压缩流对象
        zipOut = new ZipOutputStream(new FileOutputStream(zipFile)) ;
        zipOut.setComment("liuxunTEST") ;   // 设置注释
        int temp = 0 ;
        if(file.isDirectory()){ // 判断是否是文件夹
            File lists[] = file.listFiles() ;   // 列出全部文件
            for(int i=0;i<lists.length;i++){
                input = new FileInputStream(lists[i]) ; // 定义文件的输入流
                zipOut.putNextEntry(new ZipEntry(file.getName()
                    +File.separator+lists[i].getName())) ;  // 设置ZipEntry对象
                while((temp=input.read())!=-1){ // 读取内容
                    zipOut.write(temp) ;    // 压缩输出
                }
                input.close() ; // 关闭输入流
            }
        }
        zipOut.close() ;    // 关闭输出流
    }
}
