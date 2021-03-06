/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.util;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.wuliu.api.order.model.WuliuOrderModel;
import com.wuliu.api.orderbusiness.model.WuliuWholeOrderModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;

/**
 * 类WuliuWholeOrderUtil.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月30日 上午11:17:01
 */
public class WuliuWholeOrderUtil {

    public static WuliuWholeOrderModel buildWholeModel(WuliuOrderModel wuliuOrderModel,
                                                List<WuliuOrderDetailModel> wuliuOrderDetailModels) {
        WuliuWholeOrderModel ret = new WuliuWholeOrderModel();
        ret.setCarIndex(wuliuOrderModel.getCarIndex());
        ret.setDaishouFee(wuliuOrderModel.getDaishouFee());
        ret.setId(wuliuOrderModel.getId());
        ret.setJiashouFee(wuliuOrderModel.getJiashouFee());
        ret.setMemberId(wuliuOrderModel.getMemberId());
        ret.setOrderDate(wuliuOrderModel.getOrderDate());
        ret.setOrderNumber(wuliuOrderModel.getOrderNumber());
        ret.setStatus(wuliuOrderModel.getStatus());
        ret.setId(wuliuOrderModel.getId());
        ret.setWuliuOrderDetailModels(WuliuWholeOrderDetailUtil.builduliuWholeDetailModelList(wuliuOrderDetailModels));
        ret.setZhongzhuanFee(wuliuOrderModel.getZhongzhuanFee());
        ret.setComments(wuliuOrderModel.getComments());
        
        
        DecimalFormat df = new DecimalFormat("0.##");
        if (ret.getZhongzhuanFee() != null) {
            ret.setZhongzhuanFeeForDisplay(df.format(ret.getZhongzhuanFee() / 100.0));
        }
        
        if (ret.getDaishouFee() != null) {
            ret.setDaishouFeeForDisplay(df.format(ret.getDaishouFee() / 100.0));
        }
        
        if (ret.getJiashouFee() != null) {
            ret.setJiashouFeeForDisplay(df.format(ret.getJiashouFee() / 100.0));
        }
        
        int count = 0;
        if (CollectionUtils.isNotEmpty(wuliuOrderDetailModels)) {
            for (WuliuOrderDetailModel item : wuliuOrderDetailModels) {
                count += item.getCount();
            }
        }

        ret.setOrderNumber(wuliuOrderModel.getOrderNumber());
        return ret;
    }
}
