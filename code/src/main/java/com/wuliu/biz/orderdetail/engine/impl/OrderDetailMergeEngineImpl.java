/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.orderdetail.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.wuliu.api.orderbusiness.constat.WuliuMergedOrderDetailConst;
import com.wuliu.api.orderbusiness.model.WuliuMergedOrderDetailModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;
import com.wuliu.biz.orderdetail.engine.OrderDetailMergeEngine;

/**
 * 类OrderDetailMergeEngineImpl.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月30日 上午10:38:24
 */
public class OrderDetailMergeEngineImpl implements OrderDetailMergeEngine {

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.orderdetail.engine.OrderDetailMergeEngine#mergeOrderDetail(java.util.List, java.lang.Long,
     * java.lang.Long)
     */
    @Override
    public List<WuliuMergedOrderDetailModel> mergeOrderDetail(List<WuliuOrderDetailModel> wuliuOrderDetailModels,
                                                              Long weightPrice, Long volumnPrice) {
        List<WuliuMergedOrderDetailModel> ret = new ArrayList<WuliuMergedOrderDetailModel>();

        if (CollectionUtils.isEmpty(wuliuOrderDetailModels)) {
            return ret;
        }

        WuliuMergedOrderDetailModel weightModel = null;
        WuliuMergedOrderDetailModel volumnModel = null;

        for (WuliuOrderDetailModel item : wuliuOrderDetailModels) {
            Long weightCost = getWeightCost(item, weightPrice);
            Long volumnCost = getVolumnCost(item, volumnPrice);
            if (weightCost > volumnCost) {
                if (weightModel == null) {
                    weightModel = new WuliuMergedOrderDetailModel();
                    weightModel.setType(WuliuMergedOrderDetailConst.TYPE_WEIGHT);
                }

                add(weightModel, item.getWeight(), 0L, weightCost, item.getCount());
            } else {
                if (volumnModel == null) {
                    volumnModel = new WuliuMergedOrderDetailModel();
                    volumnModel.setType(WuliuMergedOrderDetailConst.TYPE_VOLUMN);
                }

                add(volumnModel, 0, getVolumn(item), volumnCost, item.getCount());
            }
        }
        
        if (weightModel != null) {
            if (weightModel.getCost() % 100000L == 0) {
                weightModel.setCost(weightModel.getCost() / 100000);
            }
            else {
                weightModel.setCost((long)Math.ceil(weightModel.getCost() / 100000.0));
            }
            ret.add(weightModel);
        }

        if (volumnModel != null) {
            if (volumnModel.getCost()  % 100000000000L == 0) {
                volumnModel.setCost(volumnModel.getCost() / 100000000000L);
            }
            else {
                volumnModel.setCost((long)Math.ceil(volumnModel.getCost() / 100000000000.0f));
            }
            ret.add(volumnModel);
        }

        return ret;
    }

    private Long getVolumn(WuliuOrderDetailModel item) {
        return item.getHeight() * item.getWidth() * item.getLength();
    }

    private Long getWeightCost(WuliuOrderDetailModel item, Long weightPrice) {
        return item.getWeight() * weightPrice;
    }

    private Long getVolumnCost(WuliuOrderDetailModel item, Long volumnPrice) {
        return getVolumn(item) * volumnPrice;
    }

    private void add(WuliuMergedOrderDetailModel mergedModel, long partWeight, long partVolumn, long partCost,
                     int partCount) {
        mergedModel.setCost(mergedModel.getCost() + partCost * partCount);
        mergedModel.setCount(mergedModel.getCount() + partCount);
        mergedModel.setWeight(mergedModel.getWeight() + partWeight * partCount);
        mergedModel.setVolumn(mergedModel.getVolumn() + partVolumn * partCount);
    }
}
