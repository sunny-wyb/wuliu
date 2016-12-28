/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.member.AO.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.wuliu.api.member.constant.WuliuMemberConst;
import com.wuliu.api.member.model.WuliuMemberModel;
import com.wuliu.api.member.model.WuliuMemberQueryParam;
import com.wuliu.biz.member.AO.WuliuMemberAO;
import com.wuliu.biz.util.WuliuMemberUtil;
import com.wuliu.dao.WuliuMemberDAO;
import com.wuliu.dao.dataobject.WuliuMemberDO;

/**
 * 类WuliuMemberAOImpl.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午1:58:57
 */
public class WuliuMemberAOImpl implements WuliuMemberAO {

    private WuliuMemberDAO wuliuMemberDAO;

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.member.AO.WuliuMemberAO#addMember(com.wuliu.biz.member.model.WuliuMemberModel)
     */
    @Override
    public WuliuMemberModel addMember(WuliuMemberModel wuliuMemberModel) {
        WuliuMemberDO wuliuMemberDO = WuliuMemberUtil.convertToWuliuMemberDO(wuliuMemberModel);
        boolean flag = wuliuMemberDAO.addMember(wuliuMemberDO);
        wuliuMemberModel.setStatus(WuliuMemberConst.STATUS_ENABLE);
        if (flag) {
            WuliuMemberModel newWuliuMemberModel = WuliuMemberUtil.convertToWuliuMemberModel(wuliuMemberDO);
            return newWuliuMemberModel;
        } else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.member.AO.WuliuMemberAO#updateMember(com.wuliu.biz.member.model.WuliuMemberModel)
     */
    @Override
    public boolean updateMember(WuliuMemberModel wuliuMemberModel) {
        WuliuMemberDO wuliuMemberDO = WuliuMemberUtil.convertToWuliuMemberDO(wuliuMemberModel);
        boolean ret = wuliuMemberDAO.updateMember(wuliuMemberDO);
        return ret;
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.member.AO.WuliuMemberAO#queryMembers(com.wuliu.biz.member.model.WuliuMemberQueryParam)
     */
    @Override
    public List<WuliuMemberModel> queryMembers(WuliuMemberQueryParam wuliuMemberQueryParam) {
        List<WuliuMemberDO> wuliuMemberDOs = wuliuMemberDAO.queryMembers(wuliuMemberQueryParam);
        return WuliuMemberUtil.convertToWuliuMemberList(wuliuMemberDOs);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.member.AO.WuliuMemberAO#countMembers(com.wuliu.biz.member.model.WuliuMemberQueryParam)
     */
    @Override
    public int countMembers(WuliuMemberQueryParam wuliuMemberQueryParam) {
        return wuliuMemberDAO.countMembers(wuliuMemberQueryParam);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.member.AO.WuliuMemberAO#deleteMember(java.lang.Long)
     */
    @Override
    public boolean deleteMember(Long id) {
        WuliuMemberQueryParam wuliuMemberQueryParam = new WuliuMemberQueryParam();
        wuliuMemberQueryParam.setId(id);
        List<WuliuMemberModel> wuliuMemberModels = queryMembers(wuliuMemberQueryParam);
        if (CollectionUtils.isEmpty(wuliuMemberModels) || wuliuMemberModels.size() != 1)  {
            return false;
        }
        
        WuliuMemberModel wuliuMemberModel = wuliuMemberModels.get(0);
        wuliuMemberModel.setStatus(WuliuMemberConst.STATUS_DISABLE);
        return updateMember(wuliuMemberModel);
    }

    public WuliuMemberDAO getWuliuMemberDAO() {
        return wuliuMemberDAO;
    }

    public void setWuliuMemberDAO(WuliuMemberDAO wuliuMemberDAO) {
        this.wuliuMemberDAO = wuliuMemberDAO;
    }
}
