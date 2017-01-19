/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wuliu.api.common.model.PageResultModel;
import com.wuliu.api.member.constant.WuliuMemberConst;
import com.wuliu.api.member.model.WuliuMemberModel;
import com.wuliu.api.member.model.WuliuMemberQueryParam;
import com.wuliu.api.member.service.WuliuMemberService;
import com.wuliu.api.order.service.WuliuOrderService;
import com.wuliu.api.orderbusiness.service.WuliuMergedOrderService;
import com.wuliu.api.orderbusiness.service.WuliuWholeOrderService;
import com.wuliu.api.orderdetail.service.WuliuOrderDetailService;
import com.wuliu.biz.member.AO.WuliuMemberAO;
import com.wuliu.biz.util.ExportUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 类CrunchifyHelloWorld.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月27日 下午4:36:05
 */

@SuppressWarnings("all")
@Controller
public class Member {

    private final static Logger     logger = LoggerFactory.getLogger(Member.class);

    private SqlSessionTemplate      sqlSessionTemplate;

    private WuliuMemberService      wuliuMemberService;

    private WuliuOrderService       wuliuOrderService;

    private WuliuOrderDetailService wuliuOrderDetailService;

    private WuliuWholeOrderService  wuliuWholeOrderService;

    private WuliuMergedOrderService wuliuMergedOrderService;

    @RequestMapping(value = "/save.html", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String save(@RequestParam(value = "id", required = false) Long id,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "nick-name", required = false) String nickName,
                       @RequestParam(value = "tel-number", required = false) String telephoneNumber,
                       @RequestParam(value = "m-number", required = false) String mobileNumber,
                       @RequestParam(value = "w-price", required = false) Long weightPrice,
                       @RequestParam(value = "v-price", required = false) Long volumnPrice,
                       @RequestParam(value = "address", required = false) String address) {

        WuliuMemberModel wuliuMemberModel = new WuliuMemberModel();

        wuliuMemberModel.setId(id);
        wuliuMemberModel.setName(nickName);
        wuliuMemberModel.setNickName(nickName);
        wuliuMemberModel.setMobileNumber(mobileNumber);
        wuliuMemberModel.setTelephoneNumber(telephoneNumber);
        wuliuMemberModel.setVolumnPrice(volumnPrice);
        wuliuMemberModel.setWeightPrice(weightPrice);
        wuliuMemberModel.setAddress(address);

        Boolean result = null;
        if (id != null) {
            result = wuliuMemberService.updateMember(wuliuMemberModel);
        } else {
            wuliuMemberModel.setStatus(WuliuMemberConst.STATUS_ENABLE);
            WuliuMemberModel newWuliuMemberModel = wuliuMemberService.addMember(wuliuMemberModel);
            result = (newWuliuMemberModel != null);
        }

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("result", result);
        return JSON.toJSONString(ret);
    }

    @RequestMapping("/member")
    public ModelAndView load(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "name", required = false) String name) throws EncryptedDocumentException, InvalidFormatException, IOException {
        ExportUtil.getInstance().export("/tmp", null);
        Map<String , Object> paramMap = new HashMap<String , Object>();
        String decodeName = null;
        if (name != null) {
            decodeName = URLDecoder.decode(name, "utf-8");
        }
        paramMap.put("name", decodeName);
        WuliuMemberQueryParam wuliuMemberQueryParam = new WuliuMemberQueryParam();
        if (page != null) {
            wuliuMemberQueryParam.setPageNum(page);
        }
        if (decodeName != null) {
            wuliuMemberQueryParam.setName(decodeName);
        }

        PageResultModel<WuliuMemberModel> memberPageResult = wuliuMemberService.queryMembers(wuliuMemberQueryParam);
        List<WuliuMemberModel> members = memberPageResult.getResultList();

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("members", members);
        returnMap.put("currentPage", memberPageResult.getPageNum());
        returnMap.put("params", paramMap);

        int totalPage = memberPageResult.getTotalCount() / memberPageResult.getPageSize();
        if (memberPageResult.getTotalCount() % memberPageResult.getPageSize() != 0) {
            totalPage += 1;
        }

        returnMap.put("totalPage", totalPage);
        returnMap.put("JSON", JSON.class);
        return new ModelAndView("member", returnMap);
    }

    @RequestMapping(value = "/delete.html", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String delete(@RequestParam(value = "id") Long id) {

        Boolean result = wuliuMemberService.deleteMember(id);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("result", result);
        return JSON.toJSONString(ret);
    }
    
    @RequestMapping(value = "/searchMember.html", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String searchMember(@RequestParam(value = "term") String name) {

        WuliuMemberQueryParam param = new WuliuMemberQueryParam();
        param.setPrefixName(name);
        PageResultModel<WuliuMemberModel>result = wuliuMemberService.queryMembers(param);
        JSONArray ret = new JSONArray();
        if (result != null && result.getResultList() != null) {
            for (WuliuMemberModel item : result.getResultList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", item.getId());
                jsonObject.put("value", item.getName());
                ret.add(jsonObject);
            }
        }
        return ret.toJSONString();
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

class TestModel {
    long id;
    String value;
    
    public TestModel (long id , String value) {
        this.id = id;
        this.value = value;
    }

    
    public long getId() {
        return id;
    }

    
    public void setId(long id) {
        this.id = id;
    }

    
    public String getValue() {
        return value;
    }

    
    public void setValue(String value) {
        this.value = value;
    }
}
