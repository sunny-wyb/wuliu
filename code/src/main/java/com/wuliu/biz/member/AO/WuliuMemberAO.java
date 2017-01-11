/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.member.AO;

import java.util.List;

import com.wuliu.api.member.model.WuliuMemberModel;
import com.wuliu.api.member.model.WuliuMemberQueryParam;

/**
 * 类WuliuMemberAO.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午1:56:14
 */
public interface WuliuMemberAO {

    public WuliuMemberModel addMember(WuliuMemberModel wuliuMemberModel);

    public boolean updateMember(WuliuMemberModel wuliuMemberModel);

    public List<WuliuMemberModel> queryMembers(WuliuMemberQueryParam wuliuMemberQueryParam);

    public int countMembers(WuliuMemberQueryParam wuliuMemberQueryParam);

    public boolean deleteMember(Long id);
    
    public WuliuMemberModel queryMemberWithId(Long id);
}
