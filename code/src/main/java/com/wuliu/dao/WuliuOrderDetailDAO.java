/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.dao;

import java.util.List;

import com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam;
import com.wuliu.dao.dataobject.WuliuOrderDetailDO;

/**
 * 类WuliuOrderDetailDAO.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月29日 下午1:18:38
 */
public interface WuliuOrderDetailDAO {

    public WuliuOrderDetailDO addOrderDetail(WuliuOrderDetailDO wuliuOrderDetailDO);

    public boolean updateOrderDetail(WuliuOrderDetailDO wuliuOrderDetailDO);

    public List<WuliuOrderDetailDO> queryOrderDetails(WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam);

    public int countOrderDetails(WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam);
}
