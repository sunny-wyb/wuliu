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

import com.wuliu.api.member.model.WuliuMemberQueryParam;
import com.wuliu.dao.WuliuMemberDAO;
import com.wuliu.dao.dataobject.WuliuMemberDO;

/**
 * 类WuliuMemberDAOImpl.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午1:42:10
 */
public class WuliuMemberDAOImpl implements WuliuMemberDAO {

    private SqlSessionTemplate sqlSessionTemplate;

    /*
     * (non-Javadoc)
     * @see com.wuliu.dao.WuliuMemberDAO#addMember(com.wuliu.dao.dataobject.WuliuMemberDO)
     */
    @Override
    public boolean addMember(WuliuMemberDO wuliuMemberDO) {
        if (wuliuMemberDO == null) {
            return false;
        }
        
        sqlSessionTemplate.insert("WuliuMember.insert", wuliuMemberDO);
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.dao.WuliuMemberDAO#updateMember(com.wuliu.dao.dataobject.WuliuMemberDO)
     */
    @Override
    public boolean updateMember(WuliuMemberDO wuliuMemberDO) {
        if (wuliuMemberDO == null) {
            return false;
        }
        
        int cnt = sqlSessionTemplate.update("WuliuMember.update", wuliuMemberDO);
        return cnt > 0;
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.dao.WuliuMemberDAO#queryMembers(com.wuliu.biz.member.model.WuliuMemberQueryParam)
     */
    @Override
    public List<WuliuMemberDO> queryMembers(WuliuMemberQueryParam wuliuMemberQueryParam) {
        Map<String , Object> params = new HashMap<String , Object>();
        params.put("id", wuliuMemberQueryParam.getId());
        params.put("name", wuliuMemberQueryParam.getName());
        params.put("status", wuliuMemberQueryParam.getStatus());
        params.put("start", (wuliuMemberQueryParam.getPageNum() - 1) * wuliuMemberQueryParam.getPageSize());
        params.put("size", wuliuMemberQueryParam.getPageSize());
        List<WuliuMemberDO> ret = sqlSessionTemplate.selectList("WuliuMember.query", params);
        return ret;
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.dao.WuliuMemberDAO#countMembers(com.wuliu.biz.member.model.WuliuMemberQueryParam)
     */
    @Override
    public int countMembers(WuliuMemberQueryParam wuliuMemberQueryParam) {
        Map<String , Object> params = new HashMap<String , Object>();
        params.put("id", wuliuMemberQueryParam.getId());
        params.put("name", wuliuMemberQueryParam.getName());
        params.put("status", wuliuMemberQueryParam.getStatus());
        int count = sqlSessionTemplate.selectOne("WuliuMember.count" , params);
        return count;
    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
}
