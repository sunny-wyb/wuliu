/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.orderbusiness.service;

import com.wuliu.api.common.model.PageResultModel;
import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.api.orderbusiness.model.WuliuMergedOrderModel;

/**
 * 类WuliuMergedOrderService.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月30日 下午1:39:40
 */
public interface WuliuMergedOrderService {

    public PageResultModel<WuliuMergedOrderModel> queryMergedOrders(WuliuOrderQueryParam wuliuOrderQueryParam);
}
