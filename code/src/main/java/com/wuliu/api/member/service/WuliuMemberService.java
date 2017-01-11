/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.api.member.service;

import com.wuliu.api.common.model.PageResultModel;
import com.wuliu.api.member.model.WuliuMemberModel;
import com.wuliu.api.member.model.WuliuMemberQueryParam;

/**
 * 类WuliuMemberService.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午1:26:36
 */
public interface WuliuMemberService {

    public WuliuMemberModel addMember(WuliuMemberModel wuliuMemberModel);

    public boolean updateMember(WuliuMemberModel wuliuMemberModel);

    public PageResultModel<WuliuMemberModel> queryMembers(WuliuMemberQueryParam wuliuMemberQueryParam);

    public int countMembers(WuliuMemberQueryParam wuliuMemberQueryParam);

    public boolean deleteMember(Long id);
    
    public WuliuMemberModel queryMemberWithId(Long id);
}
