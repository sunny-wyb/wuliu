/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.crunchify.controller;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dao.TestDO;
import com.wuliu.api.member.model.WuliuMemberModel;
import com.wuliu.api.member.model.WuliuMemberQueryParam;
import com.wuliu.api.member.service.WuliuMemberService;

/**
 * 类CrunchifyHelloWorld.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月27日 下午4:36:05
 */

@Controller
public class CrunchifyHelloWorld {

    private final static Logger logger = LoggerFactory.getLogger(CrunchifyHelloWorld.class);

    private SqlSessionTemplate  sqlSessionTemplate;

    private WuliuMemberService  wuliuMemberService;

    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {

        logger.info("=========");
        String message = "3234=====";
        TestDO testDO = new TestDO();
        testDO.setName("adsadsdf");
        System.out.println("23");
        System.out.println("1233");

        WuliuMemberModel wuliuMemberModel = new WuliuMemberModel();
        wuliuMemberModel.setAddress("address2");
        wuliuMemberModel.setMobileNumber("m_number2");
        wuliuMemberModel.setName("name2");
        wuliuMemberModel.setNickName("nickName2");
        wuliuMemberModel.setStatus("enable");
        wuliuMemberModel.setTelephoneNumber("tel_number2");
        wuliuMemberModel.setVolumnPrice(1002L);
        wuliuMemberModel.setWeightPrice(2002L);
        wuliuMemberModel = wuliuMemberService.addMember(wuliuMemberModel);
        long id = wuliuMemberModel.getId();
        wuliuMemberModel.setId(id);
        wuliuMemberModel.setAddress("address3");
        wuliuMemberModel.setMobileNumber("m_number3");
        wuliuMemberModel.setName("name3");
        wuliuMemberModel.setNickName("nickName3");
        wuliuMemberModel.setStatus("enable");
        wuliuMemberModel.setTelephoneNumber("tel_number3");
        wuliuMemberModel.setVolumnPrice(1003L);
        wuliuMemberModel.setWeightPrice(2003L);
        wuliuMemberService.updateMember(wuliuMemberModel);
        
        
        wuliuMemberService.deleteMember(id);
        
        WuliuMemberQueryParam wuliuMemberQueryParam = new WuliuMemberQueryParam();
        wuliuMemberQueryParam.setName("name2");
        wuliuMemberQueryParam.setStatus("enable");
        System.out.println(JSON.toJSONString(wuliuMemberService.queryMembers(wuliuMemberQueryParam)));
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
}
