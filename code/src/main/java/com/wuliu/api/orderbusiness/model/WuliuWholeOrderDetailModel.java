/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.orderbusiness.model;

import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;

/**
 * 类WuliuWholeOrderDetailModel.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月10日 下午7:25:12
 */
public class WuliuWholeOrderDetailModel extends WuliuOrderDetailModel {

    /**
     * 
     */
    private static final long serialVersionUID = 6293824839928930272L;

    private Long              totalWeight;

    private Long              totalVolumn;

    public Long getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Long totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Long getTotalVolumn() {
        return totalVolumn;
    }

    public void setTotalVolumn(Long totalVolumn) {
        this.totalVolumn = totalVolumn;
    }
}
