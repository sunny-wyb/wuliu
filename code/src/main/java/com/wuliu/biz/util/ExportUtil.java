/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.wuliu.api.orderbusiness.constat.WuliuMergedOrderDetailConst;
import com.wuliu.api.orderbusiness.model.WuliuMergedOrderDetailModel;
import com.wuliu.api.orderbusiness.model.WuliuMergedOrderModel;
import com.wuliu.biz.util.export.strategy.ExportStrategy;
import com.wuliu.biz.util.export.strategy.ExportStrategyFactory;

/**
 * 类ExportUtil.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月20日 上午12:47:31
 */

@SuppressWarnings("all")
public class ExportUtil {

    private static ExportStrategyFactory exportStrategyFactory;

    public List<WuliuMergedOrderModel> fetchData() {
        List<WuliuMergedOrderModel> ret = new ArrayList<WuliuMergedOrderModel>();

        WuliuMergedOrderModel model = createModel();

        ret.add(model);

        model = createModel();
        model.setMemberId(99L);

        ret.add(model);
        return ret;
    }

    private WuliuMergedOrderModel createModel() {
        WuliuMergedOrderModel model = new WuliuMergedOrderModel();
        model.setName("name");
        model.setMemberId(1L);
        model.setOrderNumber("20160101110");
        model.setJiashouFee(100L);
        model.setDaishouFee(200L);
        model.setZhongzhuanFee(300L);
        WuliuMergedOrderDetailModel detailModel1 = new WuliuMergedOrderDetailModel();
        detailModel1.setCost(300L);
        detailModel1.setCount(1);
        detailModel1.setType(WuliuMergedOrderDetailConst.TYPE_WEIGHT);

        List<WuliuMergedOrderDetailModel> detailModels = new ArrayList<WuliuMergedOrderDetailModel>();
        detailModels.add(detailModel1);

        WuliuMergedOrderDetailModel detailModel2 = new WuliuMergedOrderDetailModel();
        detailModel2.setCost(300L);
        detailModel2.setCount(1);
        detailModel2.setType(WuliuMergedOrderDetailConst.TYPE_VOLUMN);

        detailModels.add(detailModel2);

        model.setWuliuMergedOrderDetailModels(detailModels);
        return model;
    }

    public static String export(String strategy, List<WuliuMergedOrderModel> mergedOrders) throws Exception {

        ExportStrategy exportStrategy = exportStrategyFactory.getStrategy(strategy) ;
        String templateName = exportStrategyFactory.getTemplate(strategy);
        return exportStrategy.export(ApplicationContext.tmp_folder, templateName, mergedOrders);
    }

    public ExportStrategyFactory getExportStrategyFactory() {
        return exportStrategyFactory;
    }

    public void setExportStrategyFactory(ExportStrategyFactory exportStrategyFactory) {
        this.exportStrategyFactory = exportStrategyFactory;
    }
}
