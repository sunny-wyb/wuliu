/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuliu.api.common.model.PageResultModel;
import com.wuliu.api.member.service.WuliuMemberService;
import com.wuliu.api.order.model.WuliuOrderModel;
import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.api.order.service.WuliuOrderService;
import com.wuliu.api.orderbusiness.model.WuliuWholeOrderModel;
import com.wuliu.api.orderbusiness.service.WuliuMergedOrderService;
import com.wuliu.api.orderbusiness.service.WuliuWholeOrderService;
import com.wuliu.api.orderdetail.constant.WuliuOrderDetailConst;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam;
import com.wuliu.api.orderdetail.service.WuliuOrderDetailService;

/**
 * 类CrunchifyHelloWorld.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月27日 下午4:36:05
 */

@SuppressWarnings("all")
@Controller
public class Order {

    private final static Logger     logger = LoggerFactory.getLogger(Order.class);

    private WuliuMemberService      wuliuMemberService;

    private WuliuOrderService       wuliuOrderService;

    private WuliuOrderDetailService wuliuOrderDetailService;

    private WuliuWholeOrderService  wuliuWholeOrderService;

    private WuliuMergedOrderService wuliuMergedOrderService;

    @RequestMapping("/order.html")
    public ModelAndView load(@RequestParam(value = "page", required = false) Integer page)
                                                                                          throws UnsupportedEncodingException {

        WuliuOrderQueryParam wuliuOrderQueryParam = new WuliuOrderQueryParam();

        PageResultModel<WuliuWholeOrderModel> result = wuliuWholeOrderService.queryWholeOrders(wuliuOrderQueryParam);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("orders", result);
        returnMap.put("JSON", JSON.class);
        return new ModelAndView("order", returnMap);
    }

    @RequestMapping(value = "/manageorder.html", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String manageOrder(@RequestParam(value = "orderId", required = false) Long orderId,
                                    @RequestParam(value = "carIndex", required = true) Long carIndex,
                                    @RequestParam(value = "orderDate", required = true) String orderDate,
                                    @RequestParam(value = "orderIndex", required = true) Long orderIndex,
                                    @RequestParam(value = "memberId", required = true) Long memberId,
                                    @RequestParam(value = "zzFee", required = false) Long zzFee,
                                    @RequestParam(value = "jsFee", required = false) Long jsFee,
                                    @RequestParam(value = "dsFee", required = false) Long dsFee,
                                    @RequestParam(value = "comments", required = false) String comments,
                                    @RequestParam(value = "detailList", required = true) String detailListJSON)
                                                                                                           throws UnsupportedEncodingException,
                                                                                                           ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        WuliuOrderModel wuliuOrderModel = new WuliuOrderModel();
        wuliuOrderModel.setId(orderId);
        wuliuOrderModel.setComments(comments);
        wuliuOrderModel.setDaishouFee(dsFee);
        wuliuOrderModel.setJiashouFee(jsFee);
        wuliuOrderModel.setMemberId(memberId);
        wuliuOrderModel.setCarIndex(carIndex);
        if (StringUtils.isNotBlank(orderDate)) {
            wuliuOrderModel.setOrderDate(sdf.parse(orderDate));
        }
        wuliuOrderModel.setOrderIndex(orderIndex);
        wuliuOrderModel.setZhongzhuanFee(zzFee);

        if (orderId == null) {
            WuliuOrderModel newWuliuOrderModel = wuliuOrderService.addOrder(wuliuOrderModel);
            orderId = newWuliuOrderModel.getId();
        }
        else {
            wuliuOrderService.updateOrder(wuliuOrderModel);
        }
        
        List<Map<String , String>> detailMapList = JSON.parseObject(detailListJSON , new TypeReference<List<Map<String , String>>>(){});
        List<WuliuOrderDetailModel> oldDetailList = new ArrayList<WuliuOrderDetailModel>();
        
        if (orderId != null) {
            WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam = new WuliuOrderDetailQueryParam();
            wuliuOrderDetailQueryParam.setMainOrderId(orderId);
            wuliuOrderDetailQueryParam.setStatus(WuliuOrderDetailConst.STATUS_ENABLE);
            int count = 0;
            int pageNum = 1;
            while (true) {
                PageResultModel<WuliuOrderDetailModel> partOrderDetail = wuliuOrderDetailService.queryOrderDetails(wuliuOrderDetailQueryParam);
                if (partOrderDetail != null && CollectionUtils.isNotEmpty(partOrderDetail.getResultList())) {
                    oldDetailList.addAll(partOrderDetail.getResultList());
                    count += partOrderDetail.getResultList().size();
                }
                else {
                    break;
                }
                
                if (count >= partOrderDetail.getTotalCount()) {
                    break;
                }
                
                pageNum += 1;
            }
        }
        
        Map<Long , WuliuOrderDetailModel> orderDetailIdMap = new HashMap<Long , WuliuOrderDetailModel>();
        for (WuliuOrderDetailModel item : oldDetailList) {
            orderDetailIdMap.put(item.getId(),item); 
        }
        
        List<WuliuOrderDetailModel> detailList = new ArrayList<WuliuOrderDetailModel>();
        List<WuliuOrderDetailModel> addList = new ArrayList<WuliuOrderDetailModel>();
        List<WuliuOrderDetailModel> updateList = new ArrayList<WuliuOrderDetailModel>();
        List<WuliuOrderDetailModel> deleteList = new ArrayList<WuliuOrderDetailModel>();
        
        for (Map<String , String> detailMapItem : detailMapList) {
            WuliuOrderDetailModel wuliuOrderDetailModel = new WuliuOrderDetailModel();
            wuliuOrderDetailModel.setCount(detailMapItem.get("count") != null ? Integer.parseInt(detailMapItem.get("count")) : null);
            wuliuOrderDetailModel.setHeight(detailMapItem.get("height") != null ? Long.parseLong(detailMapItem.get("height")) : null);
            wuliuOrderDetailModel.setId(detailMapItem.get("id") != null ? Long.parseLong(detailMapItem.get("id")) : null);
            wuliuOrderDetailModel.setLength(detailMapItem.get("height") != null ? Long.parseLong(detailMapItem.get("height")) : null);
            wuliuOrderDetailModel.setWidth(detailMapItem.get("width") != null ? Long.parseLong(detailMapItem.get("width")) : null);
            wuliuOrderDetailModel.setWeight(detailMapItem.get("totalWeight") != null ? Long.parseLong(detailMapItem.get("totalWeight")) : null);
            wuliuOrderDetailModel.setStatus(WuliuOrderDetailConst.STATUS_ENABLE);
            wuliuOrderDetailModel.setMainOrderId(orderId);
            detailList.add(wuliuOrderDetailModel);
        }
        
        Set<Long> newIdSet = new HashSet<Long>();
        for (WuliuOrderDetailModel item : detailList) {
            if (item.getId() == null) {
                addList.add(item);
            }
            else if (orderDetailIdMap.keySet().contains(item.getId())) {
                updateList.add(item);
                newIdSet.add(item.getId());
            }
        }
        
        for (WuliuOrderDetailModel item : oldDetailList) {
            if (!newIdSet.contains(item.getId())) {
                deleteList.add(item);
            }
        }
        
       for (WuliuOrderDetailModel item : addList) {
           wuliuOrderDetailService.addOrderDetail(item);
       }
       
       for (WuliuOrderDetailModel item : updateList) {
           wuliuOrderDetailService.updateOrderDetail(item);
       }
       for (WuliuOrderDetailModel item : deleteList) {
           wuliuOrderDetailService.deleteOrderDetail(item.getId());
       }
       
       Map<String , Object> ret = new HashMap<String , Object>();
       ret.put("result", true);
       return JSON.toJSONString(ret);
    }

    public WuliuMemberService getWuliuMemberService() {
        return wuliuMemberService;
    }

    public void setWuliuMemberService(WuliuMemberService wuliuMemberService) {
        this.wuliuMemberService = wuliuMemberService;
    }

    public WuliuOrderService getWuliuOrderService() {
        return wuliuOrderService;
    }

    public void setWuliuOrderService(WuliuOrderService wuliuOrderService) {
        this.wuliuOrderService = wuliuOrderService;
    }

    public WuliuOrderDetailService getWuliuOrderDetailService() {
        return wuliuOrderDetailService;
    }

    public void setWuliuOrderDetailService(WuliuOrderDetailService wuliuOrderDetailService) {
        this.wuliuOrderDetailService = wuliuOrderDetailService;
    }

    public WuliuWholeOrderService getWuliuWholeOrderService() {
        return wuliuWholeOrderService;
    }

    public void setWuliuWholeOrderService(WuliuWholeOrderService wuliuWholeOrderService) {
        this.wuliuWholeOrderService = wuliuWholeOrderService;
    }

    public WuliuMergedOrderService getWuliuMergedOrderService() {
        return wuliuMergedOrderService;
    }

    public void setWuliuMergedOrderService(WuliuMergedOrderService wuliuMergedOrderService) {
        this.wuliuMergedOrderService = wuliuMergedOrderService;
    }
}
