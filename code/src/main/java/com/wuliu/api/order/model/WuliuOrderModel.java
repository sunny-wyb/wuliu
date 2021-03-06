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

/**
 * 类WuliuOrderModel.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午9:32:09
 */
public class WuliuOrderModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1049743217129559470L;

    protected Long            id;

    protected Date            orderDate;

    protected String          orderNumber;

    protected Long            memberId;

    protected Long            carIndex;

    protected Long            zhongzhuanFee;

    protected Long            jiashouFee;

    protected Long            daishouFee;

    protected String          status;

    protected String          comments;

    protected String          zhongzhuanFeeForDisplay;

    protected String          jiashouFeeForDisplay;

    protected String          daishouFeeForDisplay;

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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getZhongzhuanFeeForDisplay() {
        return zhongzhuanFeeForDisplay;
    }

    public void setZhongzhuanFeeForDisplay(String zhongzhuanFeeForDisplay) {
        this.zhongzhuanFeeForDisplay = zhongzhuanFeeForDisplay;
    }

    public String getJiashouFeeForDisplay() {
        return jiashouFeeForDisplay;
    }

    public void setJiashouFeeForDisplay(String jiashouFeeForDisplay) {
        this.jiashouFeeForDisplay = jiashouFeeForDisplay;
    }

    public String getDaishouFeeForDisplay() {
        return daishouFeeForDisplay;
    }

    public void setDaishouFeeForDisplay(String daishouFeeForDisplay) {
        this.daishouFeeForDisplay = daishouFeeForDisplay;
    }
}
