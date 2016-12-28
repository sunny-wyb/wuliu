/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.member.service.impl;

import java.util.List;

import com.wuliu.api.common.model.PageResultModel;
import com.wuliu.api.member.model.WuliuMemberModel;
import com.wuliu.api.member.model.WuliuMemberQueryParam;
import com.wuliu.api.member.service.WuliuMemberService;
import com.wuliu.biz.member.AO.WuliuMemberAO;

/**
 * 类WuliuMemberServiceIMpl.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午2:36:05
 */
public class WuliuMemberServiceImpl implements WuliuMemberService {

    private WuliuMemberAO wuliuMemberAO;

    /*
     * (non-Javadoc)
     * @see com.wuliu.api.member.service.WuliuMemberService#addMember(com.wuliu.api.member.model.WuliuMemberModel)
     */
    @Override
    public WuliuMemberModel addMember(WuliuMemberModel wuliuMemberModel) {
        return wuliuMemberAO.addMember(wuliuMemberModel);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.api.member.service.WuliuMemberService#updateMember(com.wuliu.api.member.model.WuliuMemberModel)
     */
    @Override
    public boolean updateMember(WuliuMemberModel wuliuMemberModel) {
        return wuliuMemberAO.updateMember(wuliuMemberModel);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.wuliu.api.member.service.WuliuMemberService#queryMembers(com.wuliu.api.member.model.WuliuMemberQueryParam)
     */
    @Override
    public PageResultModel<WuliuMemberModel> queryMembers(WuliuMemberQueryParam wuliuMemberQueryParam) {
        PageResultModel<WuliuMemberModel> ret = new PageResultModel<WuliuMemberModel>();
        ret.setPageNum(wuliuMemberQueryParam.getPageNum());
        ret.setPageSize(wuliuMemberQueryParam.getPageSize());
        int totalCount = wuliuMemberAO.countMembers(wuliuMemberQueryParam); 
        List<WuliuMemberModel> wuliuMemberModels = wuliuMemberAO.queryMembers(wuliuMemberQueryParam);
        ret.setTotalCount(totalCount);
        ret.setResultList(wuliuMemberModels);
        
        return ret;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.wuliu.api.member.service.WuliuMemberService#countMembers(com.wuliu.api.member.model.WuliuMemberQueryParam)
     */
    @Override
    public int countMembers(WuliuMemberQueryParam wuliuMemberQueryParam) {
        return wuliuMemberAO.countMembers(wuliuMemberQueryParam);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.api.member.service.WuliuMemberService#deleteMember(java.lang.Long)
     */
    @Override
    public boolean deleteMember(Long id) {
        return wuliuMemberAO.deleteMember(id);
    }

    public WuliuMemberAO getWuliuMemberAO() {
        return wuliuMemberAO;
    }

    public void setWuliuMemberAO(WuliuMemberAO wuliuMemberAO) {
        this.wuliuMemberAO = wuliuMemberAO;
    }
}
