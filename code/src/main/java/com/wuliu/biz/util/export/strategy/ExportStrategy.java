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
 * 类XlsExport.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月24日 下午5:00:41
 */
public interface ExportStrategy {

    public String export(String folderPath, String templateName , List<WuliuMergedOrderModel> mergedOrders) throws Exception;
}
