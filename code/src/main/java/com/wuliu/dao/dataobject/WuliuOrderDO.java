/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.dao.dataobject;

import java.util.Date;

/**
 * 类WuliuOrderDO.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午11:17:05
 */
public class WuliuOrderDO {

    private Long   id;

    private Date   orderDate;

    private Long   memberId;

    private Long   orderIndex;

    private Long   carIndex;

    private Long   zhongzhuanFee;

    private Long   daishouFee;

    private Long   jiashouFee;

    private String status;

    private String comments;

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

    public Long getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Long orderIndex) {
        this.orderIndex = orderIndex;
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

    public Long getDaishouFee() {
        return daishouFee;
    }

    public void setDaishouFee(Long daishouFee) {
        this.daishouFee = daishouFee;
    }

    public Long getJiashouFee() {
        return jiashouFee;
    }

    public void setJiashouFee(Long jiashouFee) {
        this.jiashouFee = jiashouFee;
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
}
