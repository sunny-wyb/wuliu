/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 类Login.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月23日 上午11:17:05
 */

@Controller
public class Login {

    private String username;

    private String password;

    
    @RequestMapping("/loginpage")
    public ModelAndView load() {
        Map<String , Object> ret = new HashMap<String , Object>();
        ret.put("page", "loginpage");
        return new ModelAndView("loginpage", ret);
    }
    
    @RequestMapping("/login")
    public String login(@RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password , HttpServletRequest request) {
        if (username.equals(this.username) && password.equals(this.password)) {
            HttpSession session = request.getSession();
            if (session == null) {
                session = request.getSession(true);
            }
            session.setAttribute("__USER__", username);
            return "redirect:member.html";
        }
        else {
            return "redirect:loginpage.html";
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
