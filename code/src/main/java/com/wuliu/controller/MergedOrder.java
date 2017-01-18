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
import com.wuliu.api.orderbusiness.model.WuliuMergedOrderModel;
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
public class MergedOrder {

    private final static Logger     logger = LoggerFactory.getLogger(MergedOrder.class);

    private WuliuMemberService      wuliuMemberService;

    private WuliuOrderService       wuliuOrderService;

    private WuliuOrderDetailService wuliuOrderDetailService;

    private WuliuWholeOrderService  wuliuWholeOrderService;

    private WuliuMergedOrderService wuliuMergedOrderService;

    @RequestMapping("/mergedorder.html")
    public ModelAndView load(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "memberId", required = false) Long memberId,
                             @RequestParam(value = "orderDate", required = false) String orderDate,
                             @RequestParam(value = "carIndex", required = false) Long carIndex)
                                                                                          throws UnsupportedEncodingException {

        WuliuOrderQueryParam wuliuOrderQueryParam = new WuliuOrderQueryParam();
        if (orderDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
        }
        
        wuliuOrderQueryParam.setCarIndex(carIndex);
        wuliuOrderQueryParam.setMemberId(memberId);

        PageResultModel<WuliuMergedOrderModel>  result = wuliuMergedOrderService.queryMergedOrders(wuliuOrderQueryParam);
        
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("mergedOrders", result);
        returnMap.put("JSON", JSON.class);
        int cnt = result.getTotalCount() / result.getPageSize();
        if (result.getTotalCount() % result.getPageSize() != 0) {
            cnt += 1;
        }
        returnMap.put("totalPage", cnt);
        returnMap.put("currentPage", page);
        
        return new ModelAndView("mergedorder", returnMap);
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

    
    public static Logger getLogger() {
        return logger;
    }
}
