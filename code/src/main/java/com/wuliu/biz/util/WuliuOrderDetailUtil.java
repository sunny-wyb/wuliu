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

import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;
import com.wuliu.dao.dataobject.WuliuOrderDetailDO;

/**
 * 类WuliuOrderDetailUtil.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月29日 下午1:37:02
 */
public class WuliuOrderDetailUtil {

    public static WuliuOrderDetailModel convertToWuliuOrderDetailModel(WuliuOrderDetailDO wuliuOrderDetailDO) {

        WuliuOrderDetailModel ret = new WuliuOrderDetailModel();
        ret.setCount(wuliuOrderDetailDO.getCount());
        ret.setHeight(wuliuOrderDetailDO.getHeight());
        ret.setId(wuliuOrderDetailDO.getId());
        ret.setLength(wuliuOrderDetailDO.getLength());
        ret.setMainOrderId(wuliuOrderDetailDO.getMainOrderId());
        ret.setStatus(wuliuOrderDetailDO.getStatus());
        ret.setWidth(wuliuOrderDetailDO.getWidth());
        ret.setStatus(wuliuOrderDetailDO.getStatus());

        return ret;
    }

    public static WuliuOrderDetailDO convertToWuliuOrderDetailDO(WuliuOrderDetailModel wuliuOrderDetailModel) {
        WuliuOrderDetailDO ret = new WuliuOrderDetailDO();
        ret.setCount(wuliuOrderDetailModel.getCount());
        ret.setHeight(wuliuOrderDetailModel.getHeight());
        ret.setId(wuliuOrderDetailModel.getId());
        ret.setLength(wuliuOrderDetailModel.getLength());
        ret.setMainOrderId(wuliuOrderDetailModel.getMainOrderId());
        ret.setStatus(wuliuOrderDetailModel.getStatus());
        ret.setWidth(wuliuOrderDetailModel.getWidth());
        ret.setStatus(wuliuOrderDetailModel.getStatus());
        return ret;
    }

    public static List<WuliuOrderDetailModel> convertToWuliuOrderDetailModelList(List<WuliuOrderDetailDO> wuliuOrderDetailDOs) {
        List<WuliuOrderDetailModel> ret = new ArrayList<WuliuOrderDetailModel>();
        if (CollectionUtils.isEmpty(wuliuOrderDetailDOs)) {
            return ret;
        }

        for (WuliuOrderDetailDO item : wuliuOrderDetailDOs) {
            WuliuOrderDetailModel model = convertToWuliuOrderDetailModel(item);
            if (model != null) {
                ret.add(model);
            }
        }
        return ret;
    }
}
