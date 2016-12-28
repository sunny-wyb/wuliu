/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.member.model;

import java.io.Serializable;

import com.wuliu.api.common.model.BaseQueryParam;

/**
 * 类WuliuMemberQueryParam.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午1:17:24
 */
public class WuliuMemberQueryParam extends BaseQueryParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -373097428146552142L;

    private Long              id;

    private String            name;

    private String            status;
    
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
