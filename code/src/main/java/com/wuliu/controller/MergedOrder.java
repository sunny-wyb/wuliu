/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.wuliu.api.common.model.PageResultModel;
import com.wuliu.api.member.model.WuliuMemberModel;
import com.wuliu.api.member.service.WuliuMemberService;
import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.api.order.service.WuliuOrderService;
import com.wuliu.api.orderbusiness.model.WuliuMergedOrderModel;
import com.wuliu.api.orderbusiness.service.WuliuMergedOrderService;
import com.wuliu.api.orderbusiness.service.WuliuWholeOrderService;
import com.wuliu.api.orderdetail.service.WuliuOrderDetailService;
import com.wuliu.biz.util.ApplicationContext;
import com.wuliu.biz.util.CalendarUtil;
import com.wuliu.biz.util.DownloadUtil;
import com.wuliu.biz.util.ExportUtil;
import com.wuliu.biz.util.ZipUtil;

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
                             @RequestParam(value = "orderDate", required = false) String orderDateStr,
                             @RequestParam(value = "carIndex", required = false) Long carIndex,
                             @RequestParam(value = "orderIndex", required = false) Long orderIndex)
                                                                                                   throws UnsupportedEncodingException,
                                                                                                   ParseException {

        Map<String , Object> params = new HashMap<String , Object>();
        params.put("memberId", memberId);
        params.put("orderDate", orderDateStr);
        params.put("carIndex", carIndex);
        params.put("orderIndex", orderIndex);
        if (memberId != null) {
            WuliuMemberModel memberModel = wuliuMemberService.queryMemberWithId(memberId);
            params.put("name", memberModel.getName());
        }
        
        WuliuOrderQueryParam wuliuOrderQueryParam = new WuliuOrderQueryParam();
        if (orderDateStr != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = sdf.parse(orderDateStr);
            wuliuOrderQueryParam.setMinOrderDate(CalendarUtil.getMinDateInSameDay(orderDate));
            wuliuOrderQueryParam.setMaxOrderDate(CalendarUtil.getMaxDateInSameDay(orderDate));
        }

        wuliuOrderQueryParam.setCarIndex(carIndex);
        wuliuOrderQueryParam.setMemberId(memberId);
        wuliuOrderQueryParam.setOrderIndex(orderIndex);

        if (page != null) {
            wuliuOrderQueryParam.setPageNum(page);
        }

        PageResultModel<WuliuMergedOrderModel> result = wuliuMergedOrderService.queryMergedOrders(wuliuOrderQueryParam);

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("mergedOrders", result);
        returnMap.put("JSON", JSON.class);
        returnMap.put("params", params);
        int cnt = result.getTotalCount() / result.getPageSize();
        if (result.getTotalCount() % result.getPageSize() != 0) {
            cnt += 1;
        }
        returnMap.put("totalPage", cnt);
        returnMap.put("currentPage", wuliuOrderQueryParam.getPageNum());
        
        addUtils(returnMap);

        return new ModelAndView("mergedorder", returnMap);
    }

    @RequestMapping("/download.html")
    public void download(@RequestParam(value = "memberId", required = false) Long memberId,
                         @RequestParam(value = "orderDate", required = false) String orderDateStr,
                         @RequestParam(value = "carIndex", required = false) Long carIndex,
                         @RequestParam(value = "orderIndex", required = false) Long orderIndex,
                         HttpServletResponse response) throws IOException, ParseException, EncryptedDocumentException,
                                                      InvalidFormatException {

        WuliuOrderQueryParam wuliuOrderQueryParam = new WuliuOrderQueryParam();
        if (orderDateStr != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = sdf.parse(orderDateStr);
            wuliuOrderQueryParam.setMinOrderDate(CalendarUtil.getMinDateInSameDay(orderDate));
            wuliuOrderQueryParam.setMinOrderDate(CalendarUtil.getMaxDateInSameDay(orderDate));
        }

        wuliuOrderQueryParam.setCarIndex(carIndex);
        wuliuOrderQueryParam.setMemberId(memberId);
        wuliuOrderQueryParam.setOrderIndex(orderIndex);

        List<WuliuMergedOrderModel> mergedOrderModel = new ArrayList<WuliuMergedOrderModel>();

        int cnt = 0;
        while (true) {
            PageResultModel<WuliuMergedOrderModel> partResult = wuliuMergedOrderService.queryMergedOrders(wuliuOrderQueryParam);
            if (partResult == null || CollectionUtils.isEmpty(partResult.getResultList())) {
                break;
            }

            cnt += partResult.getResultList().size();
            mergedOrderModel.addAll(partResult.getResultList());
            if (cnt >= partResult.getTotalCount()) {
                break;
            }

            wuliuOrderQueryParam.setPageNum(wuliuOrderQueryParam.getPageNum() + 1);
        }

        String path = ExportUtil.getInstance().export(ApplicationContext.TMP_FOLDER, mergedOrderModel);
        String zipPath = ZipUtil.doZip(ApplicationContext.TMP_FOLDER, path);
        DownloadUtil.download(zipPath, response);
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
    
    private void addUtils(Map<String , Object> context) {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
        context.put("sdf", sdf);
    }
}
