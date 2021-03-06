/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.orderbusiness.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 类WuliuMergedOrderModel.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午10:25:50
 */
public class WuliuMergedOrderModel implements Serializable {

    /**
     * 
     */
    private static final long                 serialVersionUID = -1361499947142542209L;

    private Long                              id;

    private Date                              orderDate;

    private Long                              memberId;

    private Long                              carIndex;

    private Long                              zhongzhuanFee;

    private Long                              jiashouFee;

    private Long                              daishouFee;

    private List<WuliuMergedOrderDetailModel> wuliuMergedOrderDetailModels;

    private Long                              cost;

    private String                            comments;

    private String                            name;

    private String                            orderNumber;

    private String                            address;

    private String                            telephoneNumber;

    private String                            mobileNumber;

    private Date                              sendDate;

    private String                            daishouFeeForDisplay;

    private String                            zhongzhuanFeeForDisplay;

    private String                            jiashouFeeForDisplay;

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

    public List<WuliuMergedOrderDetailModel> getWuliuMergedOrderDetailModels() {
        return wuliuMergedOrderDetailModels;
    }

    public void setWuliuMergedOrderDetailModels(List<WuliuMergedOrderDetailModel> wuliuMergedOrderDetailModels) {
        this.wuliuMergedOrderDetailModels = wuliuMergedOrderDetailModels;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getDaishouFeeForDisplay() {
        return daishouFeeForDisplay;
    }

    public void setDaishouFeeForDisplay(String daishouFeeForDisplay) {
        this.daishouFeeForDisplay = daishouFeeForDisplay;
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
}
