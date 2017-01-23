/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.member.model;

import java.io.Serializable;

/**
 * 类WuliuMemberModel.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午1:11:22
 */
public class WuliuMemberModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6692198660657789309L;

    private Long              id;

    /**
     * 名字
     */
    private String            name;

    /**
     * 别名
     */
    private String            nickName;

    /**
     * 座机号码
     */
    private String            telephoneNumber;

    /**
     * 手机号码
     */
    private String            mobileNumber;

    /**
     * 地址
     */
    private String            address;

    /**
     * 重量单价 每千克多少分 分/千克 这里的单位是多少?
     */
    private Long              weightPrice;

    /**
     * 体积单价 这里的单位是杜少
     */
    private Long              volumnPrice;

    /**
     * 状态
     */
    private String            status;

    /*
     * 店铺地址
     */
    private String            shopAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getWeightPrice() {
        return weightPrice;
    }

    public void setWeightPrice(Long weightPrice) {
        this.weightPrice = weightPrice;
    }

    public Long getVolumnPrice() {
        return volumnPrice;
    }

    public void setVolumnPrice(Long volumnPrice) {
        this.volumnPrice = volumnPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
}
