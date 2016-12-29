/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.orderdetail.model;

import java.io.Serializable;

import com.wuliu.api.common.model.BaseQueryParam;

/**
 * 类WuliuOrderDetailQueryParam.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月29日 下午1:20:31
 */
public class WuliuOrderDetailQueryParam extends BaseQueryParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4751595628400213454L;

    private Long              id;

    private Long              mainOrderId;

    private String            status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
