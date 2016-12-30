/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.orderdetail.engine;

import java.util.List;

import com.wuliu.api.orderbusiness.model.WuliuMergedOrderDetailModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;

/**
 * 类OrderDetailMergeEngine.java的实现描述：TODO 类实现描述 
 * @author yunbin.wangyb 2016年12月30日 上午10:36:51
 */
public interface OrderDetailMergeEngine {

    public List<WuliuMergedOrderDetailModel> mergeOrderDetail(List<WuliuOrderDetailModel> wuliuOrderDetailModels , Long weightPrice , Long volumnPrice);
}
