/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.util.export.strategy;

import java.util.List;

import com.wuliu.api.orderbusiness.model.WuliuMergedOrderModel;

/**
 * 类WholeOrderExport.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月24日 下午5:04:25
 */
public class CarIndexExport implements ExportStrategy {

    public static final int ROW_MAX = 50;

    public static final int COL_MAX = 20;

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.util.export.strategy.XlsExport#export(java.lang.String, java.util.List)
     */

    public String export(String folderPath,  String templateName , List<WuliuMergedOrderModel> mergedOrders) throws Exception {
       return ""; 
    }
}
