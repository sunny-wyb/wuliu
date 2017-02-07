/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.orderbusiness.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.wuliu.api.common.model.PageResultModel;
import com.wuliu.api.member.model.WuliuMemberModel;
import com.wuliu.api.order.model.WuliuOrderModel;
import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.api.orderbusiness.model.WuliuMergedOrderModel;
import com.wuliu.api.orderbusiness.service.WuliuMergedOrderService;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam;
import com.wuliu.biz.member.AO.WuliuMemberAO;
import com.wuliu.biz.order.AO.WuliuOrderAO;
import com.wuliu.biz.orderdetail.AO.WuliuOrderDetailAO;
import com.wuliu.biz.orderdetail.engine.OrderDetailMergeEngine;
import com.wuliu.biz.util.CalendarUtil;

/**
 * 类WuliuMergedOrderServiceImpl.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月30日 下午1:44:05
 */
public class WuliuMergedOrderServiceImpl implements WuliuMergedOrderService {

    public static final int        SEND_DAYS = 4;

    private WuliuOrderAO           wuliuOrderAO;

    private WuliuOrderDetailAO     wuliuOrderDetailAO;

    private WuliuMemberAO          wuliuMemberAO;

    private OrderDetailMergeEngine orderDetailMergeEngine;

    /*
     * (non-Javadoc)
     * @see com.wuliu.api.orderbusiness.service.WuliuMergedOrderService#queryMergedOrders(com.wuliu.api.order.model.
     * WuliuOrderQueryParam)
     */
    @Override
    public PageResultModel<WuliuMergedOrderModel> queryMergedOrders(WuliuOrderQueryParam wuliuOrderQueryParam) {
        PageResultModel<WuliuMergedOrderModel> ret = new PageResultModel<WuliuMergedOrderModel>();

        if (wuliuOrderQueryParam == null) {
            return ret;
        }

        List<WuliuOrderModel> wuliuOrderModels = wuliuOrderAO.queryOrders(wuliuOrderQueryParam);
        if (CollectionUtils.isEmpty(wuliuOrderModels)) {
            return ret;
        }

        Map<Long, WuliuMemberModel> wuliuMemberMap = new HashMap<Long, WuliuMemberModel>();
        for (WuliuOrderModel item : wuliuOrderModels) {
            if (wuliuMemberMap.containsKey(item.getMemberId())) {
                continue;
            }

            WuliuMemberModel wuliuMemberModel = wuliuMemberAO.queryMemberWithId(item.getMemberId());

            wuliuMemberMap.put(item.getMemberId(), wuliuMemberModel);
        }

        List<WuliuMergedOrderModel> wuliuMergedOrderModels = new ArrayList<WuliuMergedOrderModel>();

        for (WuliuOrderModel item : wuliuOrderModels) {
            WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam = new WuliuOrderDetailQueryParam();
            wuliuOrderDetailQueryParam.setMainOrderId(item.getId());

            WuliuMergedOrderModel wuliuMergedOrderModel = new WuliuMergedOrderModel();
            wuliuMergedOrderModel.setCarIndex(item.getCarIndex());
            wuliuMergedOrderModel.setDaishouFee(item.getDaishouFee());
            wuliuMergedOrderModel.setId(item.getId());
            wuliuMergedOrderModel.setJiashouFee(item.getJiashouFee());
            wuliuMergedOrderModel.setMemberId(item.getMemberId());
            wuliuMergedOrderModel.setOrderDate(item.getOrderDate());
            wuliuMergedOrderModel.setOrderNumber(item.getOrderNumber());
            wuliuMergedOrderModel.setZhongzhuanFee(item.getZhongzhuanFee());
            
            DecimalFormat df = new DecimalFormat("0.##");
            if (wuliuMergedOrderModel.getJiashouFee() != null) {
                wuliuMergedOrderModel.setJiashouFeeForDisplay(df.format(wuliuMergedOrderModel.getJiashouFee() / 100.0));
            }
            
            if (wuliuMergedOrderModel.getDaishouFee() != null) {
                wuliuMergedOrderModel.setDaishouFeeForDisplay(df.format(wuliuMergedOrderModel.getDaishouFee() / 100.0));
            }
            
            if (wuliuMergedOrderModel.getZhongzhuanFee() != null) {
                wuliuMergedOrderModel.setZhongzhuanFeeForDisplay(df.format(wuliuMergedOrderModel.getZhongzhuanFee() / 100.0));;
            }

            List<WuliuOrderDetailModel> wuliuOrderDetailModels = wuliuOrderDetailAO.queryOrderDetails(wuliuOrderDetailQueryParam);
            int count = 0;
            for (WuliuOrderDetailModel detailItem : wuliuOrderDetailModels) {
               count += detailItem.getCount();
            }

            WuliuMemberModel wuliuMemberModel = wuliuMemberMap.get(item.getMemberId());
            if (wuliuMemberModel == null) {
                continue;
            }

            wuliuMergedOrderModel.setName(wuliuMemberModel.getName());
            wuliuMergedOrderModel.setAddress(wuliuMemberModel.getAddress());
            wuliuMergedOrderModel.setTelephoneNumber(wuliuMemberModel.getTelephoneNumber());
            wuliuMergedOrderModel.setMobileNumber(wuliuMemberModel.getMobileNumber());
            wuliuMergedOrderModel.setOrderNumber(item.getOrderNumber());
            wuliuMergedOrderModel.setSendDate(CalendarUtil.addDays(item.getOrderDate(), SEND_DAYS));

            wuliuMergedOrderModel.setWuliuMergedOrderDetailModels(orderDetailMergeEngine.mergeOrderDetail(wuliuOrderDetailModels,
                                                                                                          wuliuMemberModel.getWeightPrice(),
                                                                                                          wuliuMemberModel.getVolumnPrice()));
            wuliuMergedOrderModels.add(wuliuMergedOrderModel);
        }

        ret.setPageNum(wuliuOrderQueryParam.getPageNum());
        ret.setPageSize(wuliuOrderQueryParam.getPageSize());
        ret.setResultList(wuliuMergedOrderModels);
        ret.setTotalCount(wuliuOrderAO.countOrders(wuliuOrderQueryParam));

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

    public OrderDetailMergeEngine getOrderDetailMergeEngine() {
        return orderDetailMergeEngine;
    }

    public void setOrderDetailMergeEngine(OrderDetailMergeEngine orderDetailMergeEngine) {
        this.orderDetailMergeEngine = orderDetailMergeEngine;
    }
}
