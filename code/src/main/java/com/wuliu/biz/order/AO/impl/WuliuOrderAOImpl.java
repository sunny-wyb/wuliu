/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.order.AO.impl;

import java.util.List;

import com.wuliu.api.order.constant.WuliuOrderConst;
import com.wuliu.api.order.model.WuliuOrderModel;
import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.biz.order.AO.WuliuOrderAO;
import com.wuliu.biz.util.WuliuOrderUtil;
import com.wuliu.dao.WuliuOrderDAO;
import com.wuliu.dao.dataobject.WuliuOrderDO;

/**
 * 类WuliuOrderAOImpl.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月29日 上午12:32:20
 */
public class WuliuOrderAOImpl implements WuliuOrderAO {

    private WuliuOrderDAO wuliuOrderDAO;
    
    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.order.AO.WuliuOrderAO#addOrder(com.wuliu.api.order.model.WuliuOrderModel)
     */
    @Override
    public WuliuOrderModel addOrder(WuliuOrderModel wuliuOrderModel) {
        WuliuOrderDO wuliuOrderDO = WuliuOrderUtil.convertToWuliuOrderDO(wuliuOrderModel);
        boolean flag = wuliuOrderDAO.addOrder(wuliuOrderDO);
        if (!flag) {
            return null;
        } 
        return WuliuOrderUtil.convertToWuliuOrderModel(wuliuOrderDO);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.order.AO.WuliuOrderAO#updateOrder(com.wuliu.api.order.model.WuliuOrderModel)
     */
    @Override
    public boolean updateOrder(WuliuOrderModel wuliuOrderModel) {
        WuliuOrderDO wuliuOrderDO = WuliuOrderUtil.convertToWuliuOrderDO(wuliuOrderModel);
        if (wuliuOrderDO == null) {
            return false;
        }

        return wuliuOrderDAO.updateOrder(wuliuOrderDO);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.order.AO.WuliuOrderAO#queryOrders(com.wuliu.api.order.model.WuliuOrderQueryParam)
     */
    @Override
    public List<WuliuOrderModel> queryOrders(WuliuOrderQueryParam wuliuOrderQueryParam) {
        List<WuliuOrderDO> wuliuOrderDOs = wuliuOrderDAO.queryOrders(wuliuOrderQueryParam);
        List<WuliuOrderModel> wuliuOrderModels = WuliuOrderUtil.convertToWuliuModelOrderList(wuliuOrderDOs);
        return wuliuOrderModels;
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.order.AO.WuliuOrderAO#countOrders(com.wuliu.api.order.model.WuliuOrderQueryParam)
     */
    @Override
    public int countOrders(WuliuOrderQueryParam wuliuOrderQueryParam) {
        return wuliuOrderDAO.countOrders(wuliuOrderQueryParam);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.order.AO.WuliuOrderAO#deleteOrder(java.lang.Long)
     */
    @Override
    public boolean deleteOrder(Long id) {
        WuliuOrderModel wuliuOrderModel = new WuliuOrderModel();
        wuliuOrderModel.setId(id);
        wuliuOrderModel.setStatus(WuliuOrderConst.STATUS_DISABLE);
        return updateOrder(wuliuOrderModel);
    }

    public WuliuOrderDAO getWuliuOrderDAO() {
        return wuliuOrderDAO;
    }

    public void setWuliuOrderDAO(WuliuOrderDAO wuliuOrderDAO) {
        this.wuliuOrderDAO = wuliuOrderDAO;
    }
}
