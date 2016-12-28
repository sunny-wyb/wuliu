/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.dao;

import java.util.List;

import com.wuliu.api.member.model.WuliuMemberQueryParam;
import com.wuliu.dao.dataobject.WuliuMemberDO;

/**
 * 类WuliuMemberDAO.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午1:20:40
 */
public interface WuliuMemberDAO {

    public boolean addMember(WuliuMemberDO wuliuMemberDO);

    public boolean updateMember(WuliuMemberDO wuliuMemberDO);

    public List<WuliuMemberDO> queryMembers(WuliuMemberQueryParam wuliuMemberQueryParam);

    public int countMembers(WuliuMemberQueryParam wuliuMemberQueryParam);
}
