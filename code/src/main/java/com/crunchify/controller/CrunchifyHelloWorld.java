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
import com.wuliu.api.orderdetail.service.WuliuOrderDetailService;

/**
 * 类CrunchifyHelloWorld.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月27日 下午4:36:05
 */

@Controller
public class CrunchifyHelloWorld {

    private final static Logger     logger = LoggerFactory.getLogger(CrunchifyHelloWorld.class);

    private SqlSessionTemplate      sqlSessionTemplate;

    private WuliuMemberService      wuliuMemberService;

    private WuliuOrderService       wuliuOrderService;

    private WuliuOrderDetailService wuliuOrderDetailService;

    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {

        logger.info("=========");
        String message = "3234=====";
        TestDO testDO = new TestDO();
        testDO.setName("adsadsdf");
        System.out.println("23");
        System.out.println("1233");
        
        WuliuOrderModel wuliuOrderModel = new WuliuOrderModel();
        wuliuOrderModel.setCarIndex(1L);
        wuliuOrderModel.setDaishouFee(2L);
        wuliuOrderModel.setJiashouFee(3L);
        wuliuOrderModel.setMemberId(4L);
        wuliuOrderModel.setOrderDate(new Date());
        wuliuOrderModel.setOrderIndex(5L);
        wuliuOrderModel.setStatus("enable");
        wuliuOrderModel.setZhongzhuanFee(6L);
        WuliuOrderModel newWuliuOrderModel =  wuliuOrderService.addOrder(wuliuOrderModel);
        
        newWuliuOrderModel.setCarIndex(2L);
        newWuliuOrderModel.setDaishouFee(3L);
        newWuliuOrderModel.setJiashouFee(4L);
        newWuliuOrderModel.setMemberId(5L);
        newWuliuOrderModel.setOrderDate(new Date(System.currentTimeMillis() + 3600L * 1000L));
        newWuliuOrderModel.setOrderIndex(6L);
        newWuliuOrderModel.setStatus("enable");
        newWuliuOrderModel.setZhongzhuanFee(7L);
        wuliuOrderService.updateOrder(newWuliuOrderModel);
        
        wuliuOrderService.deleteOrder(newWuliuOrderModel.getId());
        
        WuliuOrderQueryParam wuliuOrderQueryParam = new WuliuOrderQueryParam();
        wuliuOrderQueryParam.setMemberId(5L);
        System.out.println(JSON.toJSONString(wuliuOrderService.queryOrders(wuliuOrderQueryParam)));
        
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
}
