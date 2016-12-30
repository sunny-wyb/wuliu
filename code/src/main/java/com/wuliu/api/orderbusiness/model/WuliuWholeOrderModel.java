/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.orderbusiness.model;

import java.io.Serializable;
import java.util.List;

import com.wuliu.api.order.model.WuliuOrderModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;

/**
 * 类WuliuWholeOrderModel.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月30日 上午11:08:04
 */
public class WuliuWholeOrderModel extends WuliuOrderModel implements Serializable {

    /**
     * 
     */
    private static final long           serialVersionUID = -225344244820376541L;

    private List<WuliuOrderDetailModel> wuliuOrderDetailModels;

    public List<WuliuOrderDetailModel> getWuliuOrderDetailModels() {
        return wuliuOrderDetailModels;
    }

    public void setWuliuOrderDetailModels(List<WuliuOrderDetailModel> wuliuOrderDetailModels) {
        this.wuliuOrderDetailModels = wuliuOrderDetailModels;
    }
}