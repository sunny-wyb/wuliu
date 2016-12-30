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
import java.util.List;

import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;

/**
 * 类WuliuOrderModel.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午9:32:09
 */
public class WuliuOrderModel implements Serializable {

    /**
     * 
     */
    private static final long           serialVersionUID = -1049743217129559470L;

    private Long                        id;

    private Date                        orderDate;

    private Long                        orderIndex;

    private Long                        memberId;

    private Long                        carIndex;

    private Long                        zhongzhuanFee;

    private Long                        jiashouFee;

    private Long                        daishouFee;

    private String                      status;

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

    public Long getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Long orderIndex) {
        this.orderIndex = orderIndex;
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

    public Long getZhongzhuanFee() {
        return zhongzhuanFee;
    }

    public void setZhongzhuanFee(Long zhongzhuanFee) {
        this.zhongzhuanFee = zhongzhuanFee;
    }

    public Long getJiashouFee() {
        return jiashouFee;
    }

    public void setJiashouFee(Long jiashouFee) {
        this.jiashouFee = jiashouFee;
    }

    public Long getDaishouFee() {
        return daishouFee;
    }

    public void setDaishouFee(Long daishouFee) {
        this.daishouFee = daishouFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
