/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam;
import com.wuliu.dao.WuliuOrderDetailDAO;
import com.wuliu.dao.dataobject.WuliuOrderDetailDO;

/**
 * 类WuliuOrderDetailDAOImpl.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月29日 下午1:36:00
 */
public class WuliuOrderDetailDAOImpl implements WuliuOrderDetailDAO {

    private SqlSessionTemplate sqlSessionTemplate;

    /*
     * (non-Javadoc)
     * @see com.wuliu.dao.WuliuOrderDetailDAO#addOrderDetail(com.wuliu.dao.dataobject.WuliuOrderDetailDO)
     */
    @Override
    public WuliuOrderDetailDO addOrderDetail(WuliuOrderDetailDO wuliuOrderDetailDO) {
        if (wuliuOrderDetailDO == null) {
            return null;
        }

        sqlSessionTemplate.insert("WuliuOrderDetail.insert", wuliuOrderDetailDO);
        return wuliuOrderDetailDO;
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.dao.WuliuOrderDetailDAO#updateOrderDetail(com.wuliu.dao.dataobject.WuliuOrderDetailDO)
     */
    @Override
    public boolean updateOrderDetail(WuliuOrderDetailDO wuliuOrderDetailDO) {
        if (wuliuOrderDetailDO == null) {
            return false;
        }

        int cnt = sqlSessionTemplate.update("WuliuOrderDetail.update", wuliuOrderDetailDO);
        return (cnt > 1);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.wuliu.dao.WuliuOrderDetailDAO#queryOrderDetails(com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam)
     */
    @Override
    public List<WuliuOrderDetailDO> queryOrderDetails(WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam) {
        List<WuliuOrderDetailDO> ret = new ArrayList<WuliuOrderDetailDO>();
        if (wuliuOrderDetailQueryParam == null) {
            return ret;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", wuliuOrderDetailQueryParam.getId());
        params.put("mainOrderId", wuliuOrderDetailQueryParam.getMainOrderId());
        params.put("status", wuliuOrderDetailQueryParam.getStatus());
        params.put("start", (wuliuOrderDetailQueryParam.getPageNum() - 1) * wuliuOrderDetailQueryParam.getPageSize());
        params.put("size", wuliuOrderDetailQueryParam.getPageSize());

        return sqlSessionTemplate.selectList("WuliuOrderDetail.query", params);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.wuliu.dao.WuliuOrderDetailDAO#countOrderDetails(com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam)
     */
    @Override
    public int countOrderDetails(WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam) {
        if (wuliuOrderDetailQueryParam == null) {
            return 0;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", wuliuOrderDetailQueryParam.getId());
        params.put("mainOrderId", wuliuOrderDetailQueryParam.getMainOrderId());
        params.put("status", wuliuOrderDetailQueryParam.getStatus());

        return sqlSessionTemplate.selectOne("WuliuOrderDetail.count", params);
    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
}
