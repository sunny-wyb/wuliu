/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.dao;

import java.util.List;

import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.dao.dataobject.WuliuOrderDO;

/**
 * 类WuliuOrderDAO.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午11:19:53
 */
public interface WuliuOrderDAO {

    public boolean addOrder(WuliuOrderDO wuliuOrderDO);

    public boolean updateOrder(WuliuOrderDO wuliuOrderDO);

    public List<WuliuOrderDO> queryOrders(WuliuOrderQueryParam wuliuOrderQueryParam);

    public int countOrders(WuliuOrderQueryParam wuliuORderQueryParam);
}
