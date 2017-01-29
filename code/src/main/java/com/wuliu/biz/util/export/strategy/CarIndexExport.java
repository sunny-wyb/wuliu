/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.util.export.strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.wuliu.api.orderbusiness.model.WuliuMergedOrderModel;

/**
 * 类WholeOrderExport.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月24日 下午5:04:25
 */
public class CarIndexExport implements ExportStrategy {

    public static final int ROW_MAX = 50;

    public static final int COL_MAX = 20;

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.util.export.strategy.XlsExport#export(java.lang.String, java.util.List)
     */

    public String export(String folderPath, String templateName, List<WuliuMergedOrderModel> mergedOrders)
                                                                                                          throws Exception {

        List<List<WuliuMergedOrderModel>> mergedOrderLists = split(mergedOrders);

        if (CollectionUtils.isEmpty(mergedOrderLists)) {
            return null;
        }

        File template = new File(this.getClass().getClassLoader().getResource(templateName).getFile());
        InputStream inp = new FileInputStream(template);
        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);
        fillSheet(sheet, mergedOrders);

        File file = new File(folderPath, getName(mergedOrders));
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
            wb.close();
            System.out.println("success");
        } catch (Exception e) {
            System.out.println("It cause Error on WRITTING excel workbook: ");
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    private List<List<WuliuMergedOrderModel>> split(List<WuliuMergedOrderModel> mergedOrders) {
        List<List<WuliuMergedOrderModel>> memberSplitList = splitMember(mergedOrders);
        List<List<WuliuMergedOrderModel>> carIndexSplitList = new ArrayList<List<WuliuMergedOrderModel>>();
        for (List<WuliuMergedOrderModel> item : memberSplitList) {
            carIndexSplitList.addAll(splitCarIndex(item));
        }

        List<List<WuliuMergedOrderModel>> ret = new ArrayList<List<WuliuMergedOrderModel>>();
        for (List<WuliuMergedOrderModel> item : carIndexSplitList) {
            ret.addAll(splitSize(item));
        }
        return ret;
    }

    private List<List<WuliuMergedOrderModel>> splitMember(List<WuliuMergedOrderModel> mergedOrders) {
        Map<Long, List<WuliuMergedOrderModel>> map = new HashMap<Long, List<WuliuMergedOrderModel>>();

        for (WuliuMergedOrderModel item : mergedOrders) {
            List<WuliuMergedOrderModel> list = map.get(item.getMemberId());
            if (list == null) {
                list = new ArrayList<WuliuMergedOrderModel>();
                map.put(item.getMemberId(), list);
            }
            list.add(item);
        }

        List<List<WuliuMergedOrderModel>> ret = new ArrayList<List<WuliuMergedOrderModel>>();
        for (List<WuliuMergedOrderModel> item : map.values()) {
            ret.add(item);
        }
        return ret;
    }

    private List<List<WuliuMergedOrderModel>> splitCarIndex(List<WuliuMergedOrderModel> mergedOrders) {
        Map<Long, List<WuliuMergedOrderModel>> map = new HashMap<Long, List<WuliuMergedOrderModel>>();

        for (WuliuMergedOrderModel item : mergedOrders) {
            List<WuliuMergedOrderModel> tmpList = map.get(item.getCarIndex());
            if (tmpList == null) {
                tmpList = new ArrayList<WuliuMergedOrderModel>();
                map.put(item.getCarIndex(), tmpList);
            }
            tmpList.add(item);
        }

        List<List<WuliuMergedOrderModel>> ret = new ArrayList<List<WuliuMergedOrderModel>>();
        for (List<WuliuMergedOrderModel> item : map.values()) {
            ret.add(item);
        }
        return ret;
    }

    private List<List<WuliuMergedOrderModel>> splitSize(List<WuliuMergedOrderModel> mergedOrders) {

        if (CollectionUtils.isEmpty(mergedOrders)) {
            return null;
        }

        List<List<WuliuMergedOrderModel>> mergedOrderLists = new ArrayList<List<WuliuMergedOrderModel>>();
        int cnt = 0;
        List<WuliuMergedOrderModel> tempListItem = new ArrayList<WuliuMergedOrderModel>();
        for (WuliuMergedOrderModel item : mergedOrders) {
            int tempLines = countLines(item);
            if (cnt + tempLines <= 8) {
                cnt += tempLines;
                tempListItem.add(item);
            } else {
                mergedOrderLists.add(tempListItem);

                tempListItem = new ArrayList<WuliuMergedOrderModel>();
                cnt = tempLines;
                tempListItem.add(item);
            }
        }

        if (tempListItem.size() > 0) {
            mergedOrderLists.add(tempListItem);
        }

        return mergedOrderLists;
    }

    private int countLines(WuliuMergedOrderModel wuliuMergedOrderModel) {
        int cnt = 0;
        cnt += wuliuMergedOrderModel.getWuliuMergedOrderDetailModels().size();
        if (wuliuMergedOrderModel.getZhongzhuanFee() != null && wuliuMergedOrderModel.getZhongzhuanFee() > 0) {
            cnt += 1;
        }

        if (wuliuMergedOrderModel.getDaishouFee() != null && wuliuMergedOrderModel.getDaishouFee() > 0) {
            cnt += 1;
        }

        if (wuliuMergedOrderModel.getJiashouFee() != null && wuliuMergedOrderModel.getJiashouFee() > 0) {
            cnt += 1;
        }
        return cnt;
    }

    private void fillSheet(Sheet sheet, List<WuliuMergedOrderModel> mergedOrderModels) {
        int index = 0;
        for (WuliuMergedOrderModel item : mergedOrderModels) {
            index++;
            Row row = sheet.getRow(index);
            if (row == null) {
                row = sheet.createRow(index);
            }
            
            Cell cell = row.getCell(0);
            if (cell == null) {
                cell = row.createCell(0);
            }
            cell.setCellValue(item.getOrderNumber());
        }
    }

    private String getName(List<WuliuMergedOrderModel> mergedOrderModels) {
        return "车次_" + mergedOrderModels.get(0).getCarIndex() + ".xlsx";
    }
}
