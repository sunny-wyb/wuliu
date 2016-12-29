/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.order.AO;

import java.util.List;

import com.wuliu.api.order.model.WuliuOrderModel;
import com.wuliu.api.order.model.WuliuOrderQueryParam;

/**
 * 类WuliuOrderAO.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午10:39:58
 */
public interface WuliuOrderAO {

    public WuliuOrderModel addOrder(WuliuOrderModel wuliuOrderModel);

    public boolean updateOrder(WuliuOrderModel wuliuOrderModel);

    public List<WuliuOrderModel> queryOrders(WuliuOrderQueryParam wuliuOrderQueryParam);

    public int countOrders(WuliuOrderQueryParam wuliuOrderQueryParam);

    public boolean deleteOrder(Long id);
}
