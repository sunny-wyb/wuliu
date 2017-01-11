/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.util;

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
