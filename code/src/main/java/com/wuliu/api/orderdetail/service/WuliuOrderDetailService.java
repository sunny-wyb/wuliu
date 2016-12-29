/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.orderdetail.service;

import com.wuliu.api.common.model.PageResultModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam;

/**
 * 类WuliuOrderDetailService.java的实现描述：TODO 类实现描述 
 * @author yunbin.wangyb 2016年12月29日 下午9:06:39
 */
public interface WuliuOrderDetailService {

    public WuliuOrderDetailModel addOrderDetail(WuliuOrderDetailModel wuliuOrderDetailModel);
    
    public boolean updateOrderDetail(WuliuOrderDetailModel wuliuOrderDetailModel);
    
    public PageResultModel<WuliuOrderDetailModel> queryOrderDetails(WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam);
    
    public int countOrderDetails(WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam);
    
    public boolean deleteOrderDetail(Long id);
}
