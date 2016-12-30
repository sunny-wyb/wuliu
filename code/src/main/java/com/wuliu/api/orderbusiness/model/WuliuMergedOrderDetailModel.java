/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.orderbusiness.model;

import java.io.Serializable;

/**
 * 类WuliuMergedOrderDetailModel.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午10:26:42
 */
public class WuliuMergedOrderDetailModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5280396110985339553L;

    private Long              mainOrderId;

    private int               Count;

    private long              weight;

    private long              volumn;

    private long              cost;

    private String            type;

    public Long getMainOrderId() {
        return mainOrderId;
    }

    public void setMainOrderId(Long mainOrderId) {
        this.mainOrderId = mainOrderId;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getVolumn() {
        return volumn;
    }

    public void setVolumn(long volumn) {
        this.volumn = volumn;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
