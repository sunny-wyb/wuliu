/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.wuliu.api.order.model.WuliuOrderModel;
import com.wuliu.dao.dataobject.WuliuOrderDO;

/**
 * 类WuliuOrderUtil.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月29日 上午12:21:50
 */
public class WuliuOrderUtil {

    public static WuliuOrderModel convertToWuliuOrderModel(WuliuOrderDO wuliuOrderDO) {
        if (wuliuOrderDO == null) {
            return null;
        }
        
        WuliuOrderModel ret = new WuliuOrderModel();
        ret.setCarIndex(wuliuOrderDO.getCarIndex());
        ret.setDaishouFee(wuliuOrderDO.getDaishouFee());
        ret.setId(wuliuOrderDO.getId());
        ret.setJiashouFee(wuliuOrderDO.getJiashouFee());
        ret.setMemberId(wuliuOrderDO.getMemberId());
        ret.setOrderDate(wuliuOrderDO.getOrderDate());
        ret.setOrderIndex(wuliuOrderDO.getOrderIndex());
        ret.setStatus(wuliuOrderDO.getStatus());
        ret.setZhongzhuanFee(wuliuOrderDO.getZhongzhuanFee());
        return ret;
    }

    public static WuliuOrderDO convertToWuliuOrderDO(WuliuOrderModel wuliuOrderModel) {
        if (wuliuOrderModel == null) {
            return null;
        }
        
        WuliuOrderDO ret = new WuliuOrderDO();
        ret.setCarIndex(wuliuOrderModel.getCarIndex());
        ret.setDaishouFee(wuliuOrderModel.getDaishouFee());
        ret.setId(wuliuOrderModel.getId());
        ret.setJiashouFee(wuliuOrderModel.getJiashouFee());
        ret.setMemberId(wuliuOrderModel.getMemberId());
        ret.setOrderDate(wuliuOrderModel.getOrderDate());
        ret.setOrderIndex(wuliuOrderModel.getOrderIndex());
        ret.setStatus(wuliuOrderModel.getStatus());
        ret.setZhongzhuanFee(wuliuOrderModel.getZhongzhuanFee());
        return ret;
    }

    public static List<WuliuOrderModel> convertToWuliuModelOrderList(List<WuliuOrderDO> wuliuOrderDOs) {
        List<WuliuOrderModel> ret = new ArrayList<WuliuOrderModel>();
        if (CollectionUtils.isEmpty(wuliuOrderDOs)) {
            return ret;
        }

        for (WuliuOrderDO item : wuliuOrderDOs) {
            WuliuOrderModel wuliuOrderModel = convertToWuliuOrderModel(item);
            if (wuliuOrderModel == null) {
                continue;
            }
            ret.add(wuliuOrderModel);
        }

        return ret;
    }
}
