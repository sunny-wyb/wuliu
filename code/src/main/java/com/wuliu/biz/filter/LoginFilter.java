/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 类LoginFilter.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月23日 上午11:11:59
 */
public class LoginFilter implements Filter {

    public static final String[]    WHITE_LIST               = { "/login.html", "/loginpage.html",
            "/images/banner.png", "/css/reset.css", "/css/jquery-ui.min.css", "/js/jquery-3.1.1.min.js",
            "/js/jquery-ui.min.js"                          };

    public static final String[]    STATIC_RESOURCE_TYPE     = { "js", "css", "images" };

    public static final Set<String> WHITE_LIST_SET           = new HashSet<String>();

    public static final Set<String> STATIC_RESOURCE_TYPE_SET = new HashSet<String>();

    {
        for (String item : WHITE_LIST) {
            WHITE_LIST_SET.add(item);
        }

        for (String item : STATIC_RESOURCE_TYPE) {
            STATIC_RESOURCE_TYPE_SET.add(item);
        }
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     * javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest _request, ServletResponse _response, FilterChain chain) throws IOException,
                                                                                               ServletException {
        HttpServletRequest request = (HttpServletRequest) _request;
        HttpServletResponse response = (HttpServletResponse) _response;
        HttpSession session = request.getSession(false);

        if (isInWhiteList(request.getContextPath(), request.getRequestURI())
            || isStaticResource(request.getContextPath(), request.getRequestURI())
            || (session != null && session.getAttribute("__USER__") != null)) {
            chain.doFilter(_request, _response);
        } else {
            response.sendRedirect("loginpage.html");
        }
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    private boolean isInWhiteList(String context, String uri) {
        String page = uri.substring(context.length());
        return WHITE_LIST_SET.contains(page);
    }

    private boolean isStaticResource(String context, String uri) {
        String resource = uri.substring(context.length());
        String path = resource.substring(1);
        int index = path.indexOf("/");
        if (index == -1) {
            return false;
        }
        
        String type = path.substring(0, index);
        return STATIC_RESOURCE_TYPE_SET.contains(type);
    }
}
