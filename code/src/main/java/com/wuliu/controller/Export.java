/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wuliu.api.common.model.PageResultModel;
import com.wuliu.api.member.service.WuliuMemberService;
import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.api.order.service.WuliuOrderService;
import com.wuliu.api.orderbusiness.model.WuliuMergedOrderModel;
import com.wuliu.api.orderbusiness.service.WuliuMergedOrderService;
import com.wuliu.api.orderbusiness.service.WuliuWholeOrderService;
import com.wuliu.api.orderdetail.service.WuliuOrderDetailService;
import com.wuliu.biz.constant.ExportStrategyConst;
import com.wuliu.biz.util.ApplicationContext;
import com.wuliu.biz.util.DownloadUtil;
import com.wuliu.biz.util.ExportUtil;
import com.wuliu.biz.util.FileUtil;
import com.wuliu.biz.util.ZipUtil;

/**
 * 类CrunchifyHelloWorld.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月27日 下午4:36:05
 */

@SuppressWarnings("all")
@Controller
public class Export {

    private final static Logger     logger = LoggerFactory.getLogger(Export.class);

    private WuliuMemberService      wuliuMemberService;

    private WuliuOrderService       wuliuOrderService;

    private WuliuOrderDetailService wuliuOrderDetailService;

    private WuliuWholeOrderService  wuliuWholeOrderService;

    private WuliuMergedOrderService wuliuMergedOrderService;

    @RequestMapping("/exporttool.html")
    public ModelAndView load() {
        return new ModelAndView("export");
    }

    @RequestMapping("/exportcarindex.html")
    public void download(@RequestParam(value = "carIndex", required = true) Long carIndex, HttpServletResponse response)
                                                                                                                        throws Exception {
        
        WuliuOrderQueryParam wuliuOrderQueryParam = new WuliuOrderQueryParam();
        wuliuOrderQueryParam.setCarIndex(carIndex);
        
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
        
        String path = ExportUtil.export(ExportStrategyConst.CAR_INDEX, mergedOrderModel);
        DownloadUtil.download(path, response);
        FileUtil.delete(path);
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
