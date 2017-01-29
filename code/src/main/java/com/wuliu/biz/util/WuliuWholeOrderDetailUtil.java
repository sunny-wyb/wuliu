/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
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

import com.wuliu.api.orderbusiness.model.WuliuWholeOrderDetailModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;

/**
 * 类WuliuWholeOrderDetailUtil.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月10日 下午7:26:09
 */
public class WuliuWholeOrderDetailUtil {

    public static WuliuWholeOrderDetailModel builduliuWholeDetailModel(WuliuOrderDetailModel wuliuOrderDetailModel) {
        WuliuWholeOrderDetailModel ret = new WuliuWholeOrderDetailModel();
        ret.setCount(wuliuOrderDetailModel.getCount());
        ret.setHeight(wuliuOrderDetailModel.getHeight());
        ret.setId(wuliuOrderDetailModel.getId());
        ret.setLength(wuliuOrderDetailModel.getLength());
        ret.setMainOrderId(wuliuOrderDetailModel.getMainOrderId());
        ret.setStatus(wuliuOrderDetailModel.getStatus());
        ret.setWeight(wuliuOrderDetailModel.getWeight());
        ret.setWidth(wuliuOrderDetailModel.getWidth());
        ret.setTotalVolumn(wuliuOrderDetailModel.getHeight() * wuliuOrderDetailModel.getLength()
                           * wuliuOrderDetailModel.getWidth() * wuliuOrderDetailModel.getCount());
        ret.setTotalWeight(wuliuOrderDetailModel.getWeight() * wuliuOrderDetailModel.getCount());

        DecimalFormat df = new DecimalFormat("0.#");
        if (ret.getLength() != null) {
            ret.setLengthForDisplay(df.format(ret.getLength() / 10.0));
        }
        
        if (ret.getWidth() != null) {
            ret.setWidthForDisplay(df.format(ret.getWidth() / 10.0));
        }
        
        if (ret.getHeight() != null) {
            ret.setHeightForDisplay(df.format(ret.getHeight() / 10.0));
        }
        
        if (ret.getWeight() != null) {
            ret.setWeightForDisplay(df.format(ret.getWeight() / 1000.0));
        }        
        
        df.applyPattern("0.###");
        if (ret.getTotalVolumn() != null) {
            if (ret.getTotalVolumn() % 1000000 == 0) {
                ret.setTotalVolumnForDisplay(df.format((ret.getTotalVolumn() / 1000000) / 1000.0));
            }
            else {
                ret.setTotalVolumnForDisplay(df.format(Math.ceil(ret.getTotalVolumn() / 1000000.0f) / 1000.0));
            }
        }
        
        df.applyPattern("0.#");
        if (ret.getTotalWeight() != null) {
            if (ret.getTotalWeight() % 1000 == 0){
                ret.setTotalWeightForDisplay(String.valueOf(ret.getTotalWeight() / 1000));
            }
            else {
                ret.setTotalWeightForDisplay(df.format((ret.getTotalWeight() / 1000.0)));
            }
        }
        
        return ret;
    }
    
    public static List<WuliuWholeOrderDetailModel> builduliuWholeDetailModelList(List<WuliuOrderDetailModel> wuliuOrderDetailModels) {
        List<WuliuWholeOrderDetailModel> ret = new ArrayList<WuliuWholeOrderDetailModel>();
        if (CollectionUtils.isEmpty(wuliuOrderDetailModels)) {
            return ret;
        }
        
        for (WuliuOrderDetailModel item : wuliuOrderDetailModels) {
            ret.add(builduliuWholeDetailModel(item));
        }
        return ret;
    }
}
