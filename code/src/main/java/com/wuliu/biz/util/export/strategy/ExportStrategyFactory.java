/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.util.export.strategy;

import java.util.Map;

/**
 * 类ExportStrategyFactory.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月24日 下午5:06:58
 */
public class ExportStrategyFactory {

    private Map<String, String>    templateMap;

    private Map<String, ExportStrategy> strategyMap;

    public ExportStrategy getStrategy(String name) {
        return strategyMap.get(name);
    }

    public String getTemplate(String name) {
        return templateMap.get(name);
    }

    public Map<String, String> getTemplateMap() {
        return templateMap;
    }

    public void setTemplateMap(Map<String, String> templateMap) {
        this.templateMap = templateMap;
    }

    public Map<String, ExportStrategy> getStrategyMap() {
        return strategyMap;
    }

    public void setStrategyMap(Map<String, ExportStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }
}
