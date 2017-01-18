/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.orderbusiness.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.wuliu.api.common.model.PageResultModel;
import com.wuliu.api.member.model.WuliuMemberModel;
import com.wuliu.api.order.model.WuliuOrderModel;
import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.api.orderbusiness.model.WuliuWholeOrderModel;
import com.wuliu.api.orderbusiness.service.WuliuWholeOrderService;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam;
import com.wuliu.biz.member.AO.WuliuMemberAO;
import com.wuliu.biz.order.AO.WuliuOrderAO;
import com.wuliu.biz.orderdetail.AO.WuliuOrderDetailAO;
import com.wuliu.biz.util.WuliuWholeOrderUtil;

/**
 * 类WuliuWholeOrderServiceImpl.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月30日 上午11:12:47
 */
public class WuliuWholeOrderServiceImpl implements WuliuWholeOrderService {

    private WuliuOrderAO       wuliuOrderAO;

    private WuliuOrderDetailAO wuliuOrderDetailAO;

    private WuliuMemberAO      wuliuMemberAO;

    /*
     * (non-Javadoc)
     * @see com.wuliu.api.orderbusiness.service.WuliuWholeOrderService#queryWholeOrders(com.wuliu.api.order.model.
     * WuliuOrderQueryParam)
     */
    @Override
    public PageResultModel<WuliuWholeOrderModel> queryWholeOrders(WuliuOrderQueryParam wuliuOrderQueryParam) {
        PageResultModel<WuliuWholeOrderModel> ret = new PageResultModel<WuliuWholeOrderModel>();

        List<WuliuWholeOrderModel> resultList = new ArrayList<WuliuWholeOrderModel>();

        if (wuliuOrderQueryParam == null) {
            return ret;
        }

        List<WuliuOrderModel> wuliuOrderModels = wuliuOrderAO.queryOrders(wuliuOrderQueryParam);
        if (CollectionUtils.isEmpty(wuliuOrderModels)) {
            return ret;
        }

        for (WuliuOrderModel item : wuliuOrderModels) {
            WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam = new WuliuOrderDetailQueryParam();
            wuliuOrderDetailQueryParam.setMainOrderId(item.getId());
            List<WuliuOrderDetailModel> wuliuOrderDetailModels = wuliuOrderDetailAO.queryOrderDetails(wuliuOrderDetailQueryParam);
            WuliuWholeOrderModel wuliuWholeOrderModel = WuliuWholeOrderUtil.buildWholeModel(item,
                                                                                            wuliuOrderDetailModels);
            WuliuMemberModel wuliuMemberModel = wuliuMemberAO.queryMemberWithId(item.getMemberId());
            if (wuliuMemberModel != null) {
                wuliuWholeOrderModel.setName(wuliuMemberModel.getName());
            }

            if (wuliuWholeOrderModel != null) {
                resultList.add(wuliuWholeOrderModel);
            }
        }

        ret.setPageNum(wuliuOrderQueryParam.getPageNum());
        ret.setPageSize(wuliuOrderQueryParam.getPageSize());
        ret.setTotalCount(wuliuOrderAO.countOrders(wuliuOrderQueryParam));
        ret.setResultList(resultList);

        return ret;
    }

    public WuliuOrderAO getWuliuOrderAO() {
        return wuliuOrderAO;
    }

    public void setWuliuOrderAO(WuliuOrderAO wuliuOrderAO) {
        this.wuliuOrderAO = wuliuOrderAO;
    }

    public WuliuOrderDetailAO getWuliuOrderDetailAO() {
        return wuliuOrderDetailAO;
    }

    public void setWuliuOrderDetailAO(WuliuOrderDetailAO wuliuOrderDetailAO) {
        this.wuliuOrderDetailAO = wuliuOrderDetailAO;
    }

    public WuliuMemberAO getWuliuMemberAO() {
        return wuliuMemberAO;
    }

    public void setWuliuMemberAO(WuliuMemberAO wuliuMemberAO) {
        this.wuliuMemberAO = wuliuMemberAO;
    }
}
