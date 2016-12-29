/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.order.model;

import java.io.Serializable;
import java.util.Date;

import com.wuliu.api.common.model.BaseQueryParam;

/**
 * 类WuliuORderQueryParam.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午10:19:38
 */
public class WuliuOrderQueryParam extends BaseQueryParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2120089651530512292L;

    private Long              id;

    private Date              orderDate;

    private Long              memberId;

    private Long              carIndex;

    private String            status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(Long carIndex) {
        this.carIndex = carIndex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
