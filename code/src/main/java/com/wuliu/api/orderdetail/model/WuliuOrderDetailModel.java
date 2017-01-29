/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.orderdetail.model;

import java.io.Serializable;

/**
 * 类WuliuOrderDetailModel.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午10:03:26
 */
public class WuliuOrderDetailModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4249181145616970982L;

    private Long              id;

    private Long              mainOrderId;

    private Integer           count;

    // 单位是毫米
    private Long              length;

    // 单位是毫米
    private Long              width;

    // 单位是毫米
    private Long              height;

    // 单位是克
    private Long              weight;

    private String            status;

    // 单位是厘米，精度1位
    private String            lengthForDisplay;

    // 单位是厘米，精度1位
    private String            widthForDisplay;

    // 单位是厘米，精度1位
    private String            heightForDisplay;

    // 单位是千克 精度1位
    private String            weightForDisplay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMainOrderId() {
        return mainOrderId;
    }

    public void setMainOrderId(Long mainOrderId) {
        this.mainOrderId = mainOrderId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLengthForDisplay() {
        return lengthForDisplay;
    }

    public void setLengthForDisplay(String lengthForDisplay) {
        this.lengthForDisplay = lengthForDisplay;
    }

    public String getWidthForDisplay() {
        return widthForDisplay;
    }

    public void setWidthForDisplay(String widthForDisplay) {
        this.widthForDisplay = widthForDisplay;
    }

    public String getHeightForDisplay() {
        return heightForDisplay;
    }

    public void setHeightForDisplay(String heightForDisplay) {
        this.heightForDisplay = heightForDisplay;
    }

    public String getWeightForDisplay() {
        return weightForDisplay;
    }

    public void setWeightForDisplay(String weightForDisplay) {
        this.weightForDisplay = weightForDisplay;
    }
}
