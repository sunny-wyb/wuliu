/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.orderdetail.AO;

import java.util.List;

import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam;

/**
 * 类WuliuOrderDetailAO.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月29日 下午1:15:19
 */
public interface WuliuOrderDetailAO {

    public WuliuOrderDetailModel addOrderDetail(WuliuOrderDetailModel wuliuOrderDetailModel);

    public boolean updateOrderDetail(WuliuOrderDetailModel wuliuOrderDetailModel);

    public List<WuliuOrderDetailModel> queryOrderDetails(WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam);

    public int countOrderDetails(WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam);
    
    public boolean deleteOrderDetail(Long id);
}
