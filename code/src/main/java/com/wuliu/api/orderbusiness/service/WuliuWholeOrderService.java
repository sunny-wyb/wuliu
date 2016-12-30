/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.orderbusiness.service;

import java.util.List;

import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.api.orderbusiness.model.WuliuWholeOrderModel;

/**
 * 类WuliuWholeOrderService.java的实现描述：TODO 类实现描述 
 * @author yunbin.wangyb 2016年12月30日 上午11:06:58
 */
public interface WuliuWholeOrderService {

    public List<WuliuWholeOrderModel> queryWholeOrders(WuliuOrderQueryParam wuliuOrderQueryParam);
}
