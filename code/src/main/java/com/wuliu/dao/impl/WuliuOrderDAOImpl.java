/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.dao.WuliuOrderDAO;
import com.wuliu.dao.dataobject.WuliuOrderDO;

/**
 * 类WuliuOrderDAOImpl.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午11:22:14
 */
public class WuliuOrderDAOImpl implements WuliuOrderDAO {

    private SqlSessionTemplate sqlSessionTemplate;

    /*
     * (non-Javadoc)
     * @see com.wuliu.dao.WuliuOrderDAO#addOrder(com.wuliu.dao.dataobject.WuliuOrderDO)
     */
    @Override
    public boolean addOrder(WuliuOrderDO wuliuOrderDO) {
        if (wuliuOrderDO == null) {
            return false;
        }
        
        sqlSessionTemplate.insert("WuliuOrder.insert", wuliuOrderDO);
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.dao.WuliuOrderDAO#updateOrder(com.wuliu.dao.dataobject.WuliuOrderDO)
     */
    @Override
    public boolean updateOrder(WuliuOrderDO wuliuOrderDO) {
        if (wuliuOrderDO == null) {
            return false;
        }
        
        int cnt = sqlSessionTemplate.update("WuliuOrder.update", wuliuOrderDO);
        return cnt > 0;
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.dao.WuliuOrderDAO#queryOrders(com.wuliu.api.order.model.WuliuORderQueryParam)
     */
    @Override
    public List<WuliuOrderDO> queryOrders(WuliuOrderQueryParam wuliuORderQueryParam) {
        Map<String , Object> params = new HashMap<String , Object>();
        params.put("id", wuliuORderQueryParam.getId());
        params.put("carIndex", wuliuORderQueryParam.getCarIndex());
        params.put("memberId", wuliuORderQueryParam.getMemberId());
        params.put("orderDate", wuliuORderQueryParam.getOrderDate());
        params.put("status", wuliuORderQueryParam.getStatus());
        params.put("start", (wuliuORderQueryParam.getPageNum() - 1) * wuliuORderQueryParam.getPageSize());
        params.put("size", wuliuORderQueryParam.getPageSize());
        List<WuliuOrderDO> ret = sqlSessionTemplate.selectList("WuliuOrder.query", params);
        return ret;
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.dao.WuliuOrderDAO#countOrders(com.wuliu.api.order.model.WuliuORderQueryParam)
     */
    @Override
    public int countOrders(WuliuOrderQueryParam wuliuORderQueryParam) {
        Map<String , Object> params = new HashMap<String , Object>();
        params.put("id", wuliuORderQueryParam.getId());
        params.put("carIndex", wuliuORderQueryParam.getCarIndex());
        params.put("memberId", wuliuORderQueryParam.getMemberId());
        params.put("orderDate", wuliuORderQueryParam.getOrderDate());
        params.put("status", wuliuORderQueryParam.getStatus());
        int count = sqlSessionTemplate.selectOne("WuliuOrder.count" , params);
        return count;
    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
}
