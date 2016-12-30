/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.orderbusiness.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.wuliu.api.order.model.WuliuOrderModel;
import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.api.orderbusiness.model.WuliuWholeOrderModel;
import com.wuliu.api.orderbusiness.service.WuliuWholeOrderService;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam;
import com.wuliu.biz.order.AO.WuliuOrderAO;
import com.wuliu.biz.orderbusiness.util.WuliuWholeOrderUtil;
import com.wuliu.biz.orderdetail.AO.WuliuOrderDetailAO;

/**
 * 类WuliuWholeOrderServiceImpl.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月30日 上午11:12:47
 */
public class WuliuWholeOrderServiceImpl implements WuliuWholeOrderService {

    private WuliuOrderAO       wuliuOrderAO;

    private WuliuOrderDetailAO wuliuOrderDetailAO;

    /*
     * (non-Javadoc)
     * @see com.wuliu.api.orderbusiness.service.WuliuWholeOrderService#queryWholeOrders(com.wuliu.api.order.model.
     * WuliuOrderQueryParam)
     */
    @Override
    public List<WuliuWholeOrderModel> queryWholeOrders(WuliuOrderQueryParam wuliuOrderQueryParam) {
        List<WuliuWholeOrderModel> ret = new ArrayList<WuliuWholeOrderModel>();

        if (wuliuOrderQueryParam == null) {
            return ret;
        }

        List<WuliuOrderModel> wuliuOrderModels = wuliuOrderAO.queryOrders(wuliuOrderQueryParam);
        if (CollectionUtils.isEmpty(wuliuOrderModels)) {
            return ret;
        }

        for (WuliuOrderModel item : wuliuOrderModels) {
            WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam = new WuliuOrderDetailQueryParam();
            wuliuOrderDetailQueryParam.setMainOrderId(item.getId());
            List<WuliuOrderDetailModel> wuliuOrderDetailModels = wuliuOrderDetailAO.queryOrderDetails(wuliuOrderDetailQueryParam);
            WuliuWholeOrderModel wuliuWholeOrderModel = WuliuWholeOrderUtil.buildWholeModel(item,
                                                                                            wuliuOrderDetailModels);
            if (wuliuWholeOrderModel != null) {
                ret.add(wuliuWholeOrderModel);
            }
        }

        return ret;
    }

    public WuliuOrderAO getWuliuOrderAO() {
        return wuliuOrderAO;
    }

    public void setWuliuOrderAO(WuliuOrderAO wuliuOrderAO) {
        this.wuliuOrderAO = wuliuOrderAO;
    }

    public WuliuOrderDetailAO getWuliuOrderDetailAO() {
        return wuliuOrderDetailAO;
    }

    public void setWuliuOrderDetailAO(WuliuOrderDetailAO wuliuOrderDetailAO) {
        this.wuliuOrderDetailAO = wuliuOrderDetailAO;
    }
}