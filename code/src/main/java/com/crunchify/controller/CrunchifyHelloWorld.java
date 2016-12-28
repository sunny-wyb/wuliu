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

import com.dao.TestDO;

/**
 * 类CrunchifyHelloWorld.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月27日 下午4:36:05
 */

@Controller
public class CrunchifyHelloWorld {

    private final static Logger logger     = LoggerFactory.getLogger(CrunchifyHelloWorld.class);

    private SqlSessionTemplate sqlSessionTemplate;

    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {

        logger.info("=========");
        String message = "3234=====";
        TestDO testDO = new TestDO();
        testDO.setName("adsadsdf");
        System.out.println(sqlSessionTemplate.insert("Test.insert", testDO));
        System.out.println(testDO.getId());
        System.out.println(sqlSessionTemplate.insert("Test.insert", testDO));
        System.out.println(testDO.getId());
        System.out.println("23");
        System.out.println("1233");
        return new ModelAndView("index", "message", message);
    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
}
