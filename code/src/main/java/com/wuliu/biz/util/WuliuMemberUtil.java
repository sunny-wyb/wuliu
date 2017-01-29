/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.wuliu.api.member.model.WuliuMemberModel;
import com.wuliu.dao.dataobject.WuliuMemberDO;

/**
 * 类WuliuMemberUtil.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月28日 下午1:44:04
 */
public class WuliuMemberUtil {

    public static WuliuMemberDO convertToWuliuMemberDO(WuliuMemberModel wuliuMemberModel) {
        if (wuliuMemberModel == null) {
            return null;
        }

        WuliuMemberDO ret = new WuliuMemberDO();
        ret.setAddress(wuliuMemberModel.getAddress());
        ret.setId(wuliuMemberModel.getId());
        ret.setMobileNumber(wuliuMemberModel.getMobileNumber());
        ret.setName(wuliuMemberModel.getName());
        ret.setNickName(wuliuMemberModel.getNickName());
        ret.setStatus(wuliuMemberModel.getStatus());
        ret.setTelephoneNumber(wuliuMemberModel.getTelephoneNumber());
        ret.setVolumnPrice(wuliuMemberModel.getVolumnPrice());
        ret.setWeightPrice(wuliuMemberModel.getWeightPrice());
        ret.setShopAddress(wuliuMemberModel.getShopAddress());
        
        return ret;
    }

    public static WuliuMemberModel convertToWuliuMemberModel(WuliuMemberDO wuliuMemberDO) {
        if (wuliuMemberDO == null) {
            return null;
        }

        WuliuMemberModel ret = new WuliuMemberModel();
        ret.setAddress(wuliuMemberDO.getAddress());
        ret.setId(wuliuMemberDO.getId());
        ret.setMobileNumber(wuliuMemberDO.getMobileNumber());
        ret.setName(wuliuMemberDO.getName());
        ret.setNickName(wuliuMemberDO.getNickName());
        ret.setStatus(wuliuMemberDO.getStatus());
        ret.setTelephoneNumber(wuliuMemberDO.getTelephoneNumber());
        ret.setVolumnPrice(wuliuMemberDO.getVolumnPrice());
        ret.setWeightPrice(wuliuMemberDO.getWeightPrice());
        ret.setShopAddress(wuliuMemberDO.getShopAddress());

        DecimalFormat df = new DecimalFormat("0.##");
        if (ret.getWeightPrice() != null) {
            ret.setWeightPriceForDisplay(df.format(ret.getWeightPrice() / 100.0));
        }
        
        if (ret.getVolumnPrice() != null) {
            ret.setVolumnPriceForDisplay(df.format(ret.getVolumnPrice() / 100.0));
        }
        
        return ret;
    }

    public static List<WuliuMemberModel> convertToWuliuMemberModelList(List<WuliuMemberDO> wuliuMemberDOs) {
        List<WuliuMemberModel> ret = new ArrayList<WuliuMemberModel>();
        
        if (CollectionUtils.isEmpty(wuliuMemberDOs)) {
            return ret;
        }
        
        for (WuliuMemberDO item : wuliuMemberDOs) {
            WuliuMemberModel model = convertToWuliuMemberModel(item);
            if (model !=null) {
            ret.add(model);
            }
        }
        
        return ret;
    }
}
