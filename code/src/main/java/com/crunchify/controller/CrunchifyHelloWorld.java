/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.crunchify.controller;

import java.util.Date;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dao.TestDO;
import com.wuliu.api.member.service.WuliuMemberService;
import com.wuliu.api.order.model.WuliuOrderModel;
import com.wuliu.api.order.model.WuliuOrderQueryParam;
import com.wuliu.api.order.service.WuliuOrderService;
import com.wuliu.api.orderbusiness.service.WuliuMergedOrderService;
import com.wuliu.api.orderbusiness.service.WuliuWholeOrderService;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;
import com.wuliu.api.orderdetail.service.WuliuOrderDetailService;
import com.wuliu.biz.util.CalendarUtil;

/**
 * 类CrunchifyHelloWorld.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月27日 下午4:36:05
 */

@SuppressWarnings("all")
@Controller
public class CrunchifyHelloWorld {

    private final static Logger     logger = LoggerFactory.getLogger(CrunchifyHelloWorld.class);

    private SqlSessionTemplate      sqlSessionTemplate;

    private WuliuMemberService      wuliuMemberService;

    private WuliuOrderService       wuliuOrderService;

    private WuliuOrderDetailService wuliuOrderDetailService;

    private WuliuWholeOrderService  wuliuWholeOrderService;

    private WuliuMergedOrderService wuliuMergedOrderService;

    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {

        logger.info("=========");
        String message = "3234=====";
        TestDO testDO = new TestDO();
        testDO.setName("adsadsdf");
        System.out.println("23");
        System.out.println("1233");

        /*
         * WuliuOrderDetailModel wuliuOrderDetailModel = new WuliuOrderDetailModel();
         * wuliuOrderDetailModel.setHeight(1L); wuliuOrderDetailModel.setLength(1L);
         * wuliuOrderDetailModel.setMainOrderId(9L); wuliuOrderDetailModel.setStatus("enable");
         * wuliuOrderDetailModel.setWeight(1L); wuliuOrderDetailModel.setWidth(1L); wuliuOrderDetailModel.setCount(1);
         * wuliuOrderDetailService.addOrderDetail(wuliuOrderDetailModel); wuliuOrderDetailModel.setHeight(2L);
         * wuliuOrderDetailModel.setLength(2L); wuliuOrderDetailModel.setMainOrderId(9L);
         * wuliuOrderDetailModel.setStatus("disable"); wuliuOrderDetailModel.setWeight(2L);
         * wuliuOrderDetailModel.setWidth(2L); wuliuOrderDetailModel.setCount(2);
         * wuliuOrderDetailService.addOrderDetail(wuliuOrderDetailModel); wuliuOrderDetailModel.setHeight(3L);
         * wuliuOrderDetailModel.setLength(3L); wuliuOrderDetailModel.setMainOrderId(10L);
         * wuliuOrderDetailModel.setStatus("enable"); wuliuOrderDetailModel.setWeight(3L);
         * wuliuOrderDetailModel.setWidth(3L); wuliuOrderDetailModel.setCount(3);
         * wuliuOrderDetailService.addOrderDetail(wuliuOrderDetailModel); wuliuOrderDetailModel.setHeight(4L);
         * wuliuOrderDetailModel.setLength(4L); wuliuOrderDetailModel.setMainOrderId(10L);
         * wuliuOrderDetailModel.setStatus("enable"); wuliuOrderDetailModel.setWeight(4L);
         * wuliuOrderDetailModel.setWidth(4L); wuliuOrderDetailModel.setCount(4);
         * wuliuOrderDetailService.addOrderDetail(wuliuOrderDetailModel);
         */

        /*
         * WuliuOrderQueryParam wuliuOrderQueryParam = new WuliuOrderQueryParam();
         * wuliuOrderQueryParam.setCarIndex(123L);
         * System.out.println(JSON.toJSONString(wuliuWholeOrderService.queryWholeOrders(wuliuOrderQueryParam)));
         * wuliuOrderQueryParam.setCarIndex(124L);
         * System.out.println(JSON.toJSONString(wuliuWholeOrderService.queryWholeOrders(wuliuOrderQueryParam)));
         */

        WuliuOrderQueryParam wuliuOrderQueryParam = new WuliuOrderQueryParam();
        Date date = new Date(System.currentTimeMillis());
        wuliuOrderQueryParam.setMinOrderDate(CalendarUtil.getMinDateInSameDay(date));
        wuliuOrderQueryParam.setMaxOrderDate(CalendarUtil.getMaxDateInSameDay(date));
        System.out.println(JSON.toJSONString(wuliuMergedOrderService.queryMergedOrders(wuliuOrderQueryParam)));

        return new ModelAndView("index", "message", message);
    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
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
