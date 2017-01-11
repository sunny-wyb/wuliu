/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.order.service.impl;

import com.wuliu.api.common.model.PageResultModel;
import com.wuliu.api.order.constant.WuliuOrderConst;
import com.wuliu.api.order.model.WuliuOrderModel;
import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.api.order.service.WuliuOrderService;
import com.wuliu.biz.order.AO.WuliuOrderAO;

/**
 * 类WuliuOrderServiceImpl.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月29日 上午8:33:40
 */
public class WuliuOrderServiceImpl implements WuliuOrderService {

    private WuliuOrderAO wuliuOrderAO;

    /*
     * (non-Javadoc)
     * @see com.wuliu.api.order.service.WuliuOrderService#addOrder(com.wuliu.api.order.model.WuliuOrderModel)
     */
    @Override
    public WuliuOrderModel addOrder(WuliuOrderModel wuliuOrderModel) {
        if (wuliuOrderModel.getStatus() == null) {
            wuliuOrderModel.setStatus(WuliuOrderConst.STATUS_ENABLE);
        }
        return wuliuOrderAO.addOrder(wuliuOrderModel);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.api.order.service.WuliuOrderService#updateOrder(com.wuliu.api.order.model.WuliuOrderModel)
     */
    @Override
    public boolean updateOrder(WuliuOrderModel wuliuOrderModel) {
        return wuliuOrderAO.updateOrder(wuliuOrderModel);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.api.order.service.WuliuOrderService#queryOrders(com.wuliu.api.member.model.WuliuMemberQueryParam)
     */
    @Override
    public PageResultModel<WuliuOrderModel> queryOrders(WuliuOrderQueryParam wuliuOrderQueryParam) {
        PageResultModel<WuliuOrderModel> ret = new PageResultModel<WuliuOrderModel>();
        if (wuliuOrderQueryParam == null) {
            return null;
        }

        ret.setPageNum(wuliuOrderQueryParam.getPageNum());
        ret.setPageSize(wuliuOrderQueryParam.getPageSize());
        ret.setResultList(wuliuOrderAO.queryOrders(wuliuOrderQueryParam));
        ret.setTotalCount(wuliuOrderAO.countOrders(wuliuOrderQueryParam));
        return ret;
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.api.order.service.WuliuOrderService#countOrders(com.wuliu.api.member.model.WuliuMemberQueryParam)
     */
    @Override
    public int countOrders(WuliuOrderQueryParam wuliuOrderQueryParam) {
        return wuliuOrderAO.countOrders(wuliuOrderQueryParam);
    }

    public WuliuOrderAO getWuliuOrderAO() {
        return wuliuOrderAO;
    }

    public void setWuliuOrderAO(WuliuOrderAO wuliuOrderAO) {
        this.wuliuOrderAO = wuliuOrderAO;
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.api.order.service.WuliuOrderService#deleteOrder(java.lang.Long)
     */
    @Override
    public boolean deleteOrder(Long id) {
        return wuliuOrderAO.deleteOrder(id);
    }
}
