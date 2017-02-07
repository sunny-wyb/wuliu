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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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

/**
 * 类WholeOrderExport.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月24日 下午5:04:25
 */
@SuppressWarnings("all")
public class WholeOrderExport implements ExportStrategy {

    public static final int ROW_MAX = 50;

    public static final int COL_MAX = 20;

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.util.export.strategy.XlsExport#export(java.lang.String, java.util.List)
     */

    public String export(String folderPath, String templateName, List<WuliuMergedOrderModel> mergedOrders)
                                                                                                          throws Exception {
        File folder = createFolder(folderPath);
        List<List<WuliuMergedOrderModel>> mergedOrderLists = split(mergedOrders);

        if (CollectionUtils.isEmpty(mergedOrderLists)) {
            return null;
        }

        for (List<WuliuMergedOrderModel> item : mergedOrderLists) {
            File template = new File(this.getClass().getClassLoader().getResource(templateName).getFile());
            InputStream inp = new FileInputStream(template);
            Workbook wb = WorkbookFactory.create(inp);
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = wb.getSheetAt(0);
            fillSheet(sheet, item);
            evaluate(sheet, evaluator);

            File file = new File(folder, getName(item));
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
        }
        return folder.getAbsolutePath();
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
        // file header
        if (sheet.getHeader() != null) {
            String right = sheet.getHeader().getRight();
            if (right != null && right.length() > 5) {
                right = right.substring(0, right.length() - 5) + "第" + mergedOrderModels.get(0).getCarIndex() + "车";
                sheet.getHeader().setRight(right);
            }
        }

        // file name
        Row row3 = sheet.getRow(3);
        Cell nameCell = row3.getCell(3);
        if (nameCell == null) {
            nameCell = row3.createCell(3);
        }
        nameCell.setCellValue(mergedOrderModels.get(0).getName());

        Cell phoneCell = row3.getCell(6);
        if (phoneCell == null) {
            phoneCell = row3.createCell(6);
        }
        phoneCell.setCellValue(getPhoneNumber(mergedOrderModels.get(0)));

        // fill address
        Row row4 = sheet.getRow(4);
        Cell addressCell = row4.getCell(3);
        if (addressCell == null) {
            addressCell = row4.createCell(3);
        }
        addressCell.setCellValue(mergedOrderModels.get(0).getAddress());

        // fill send date
        Cell sendDateCell = row4.getCell(6);
        if (sendDateCell == null) {
            sendDateCell = row4.createCell(6);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sendDateCell.setCellValue("送货日期：" + sdf.format(mergedOrderModels.get(0).getSendDate()));

        int rowNum = 6;
        int index = 1;
        for (WuliuMergedOrderModel item : mergedOrderModels) {
            List<CellInfo> cellInfoList = convertCellInfoList(item);

            for (CellInfo cellInfo : cellInfoList) {

                Row row = sheet.getRow(rowNum);
                rowNum += 1;

                Cell cell = createCellIfNotExit(row, 1);
                cell.setCellValue(index);
                index += 1;

                if (cellInfo.getOrderNumber() != null) {
                    cell = createCellIfNotExit(row, 2);
                    cell.setCellValue(cellInfo.getOrderNumber());
                }
                
                if (cellInfo.getGuige() != null) {
                    cell = createCellIfNotExit(row, 3);
                    cell.setCellValue(cellInfo.getGuige());
                }

                if (cellInfo.getUnit() != null) {
                    cell = createCellIfNotExit(row, 4);
                    cell.setCellValue(cellInfo.getUnit());
                }

                if (cellInfo.getCount() != null) {
                    cell = createCellIfNotExit(row, 5);
                    cell.setCellValue(cellInfo.getCount());
                }

                if (cellInfo.getCost() != null) {
                    cell = createCellIfNotExit(row, 7);
                    cell.setCellValue(Double.valueOf(cellInfo.getCost()));
                }

                if (cellInfo.getComments() != null) {
                    cell = createCellIfNotExit(row, 8);
                    cell.setCellValue(cellInfo.getComments());
                }
            }
        }
    }

    private String getName(List<WuliuMergedOrderModel> mergedOrderModels) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy_MM_dd_hh_mm_ss");
        WuliuMergedOrderModel item = mergedOrderModels.get(0);
        return item.getName() + "_" + item.getOrderNumber() + sdf.format(new Date()) + ".xlsx";
    }

    private File createFolder(String parentPath) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy_MM_dd_hh_mm_ss");
        String folderName = "fp_" + sdf.format(new Date());
        File file = new File(parentPath, folderName);
        file.mkdir();
        return file;
    }

    private Cell createCellIfNotExit(Row row, int colNum) {
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }
        return cell;
    }

    private List<CellInfo> convertCellInfoList(WuliuMergedOrderModel mergedModel) {
        List<CellInfo> ret = new ArrayList<CellInfo>();

        List<WuliuMergedOrderDetailModel> list = mergedModel.getWuliuMergedOrderDetailModels();

        for (WuliuMergedOrderDetailModel item : list) {
            CellInfo cellInfo = new CellInfo();
            cellInfo.setOrderNumber(mergedModel.getOrderNumber());
            
            cellInfo.setGuige(String.valueOf(item.getCount()));
            
            if (WuliuMergedOrderDetailConst.TYPE_WEIGHT.equals(item.getType())) {
                cellInfo.setUnit("千克");
                cellInfo.setCount(item.getWeightForDisplay());
            } else if (WuliuMergedOrderDetailConst.TYPE_VOLUMN.equals(item.getType())) {
                cellInfo.setUnit("立方米");
                cellInfo.setCount(item.getVolumnForDisplay());
            }

            cellInfo.setCost(String.valueOf(item.getCost()));
            ret.add(cellInfo);
        }

        DecimalFormat df = new DecimalFormat("0.##");
        if (mergedModel.getDaishouFee() != null && mergedModel.getDaishouFee() > 0) {
            CellInfo cellInfo = new CellInfo();
            cellInfo.setOrderNumber(mergedModel.getOrderNumber());
            cellInfo.setCost(df.format(mergedModel.getDaishouFee() / 100.0));
            cellInfo.setComments("代收费");
            ret.add(cellInfo);
        }

        if (mergedModel.getJiashouFee() != null && mergedModel.getJiashouFee() > 0) {
            CellInfo cellInfo = new CellInfo();
            cellInfo.setOrderNumber(mergedModel.getOrderNumber());
            cellInfo.setCost(df.format(mergedModel.getJiashouFee() / 100.0));
            cellInfo.setComments("加收费");
            ret.add(cellInfo);
        }

        if (mergedModel.getZhongzhuanFee() != null && mergedModel.getZhongzhuanFee() > 0) {
            CellInfo cellInfo = new CellInfo();
            cellInfo.setOrderNumber(mergedModel.getOrderNumber());
            cellInfo.setCost(df.format(mergedModel.getZhongzhuanFee() / 100.0));
            cellInfo.setComments("中转费");
            ret.add(cellInfo);
        }

        return ret;
    }

    private void evaluate(Sheet sheet, FormulaEvaluator evaluator) {
        for (int i = 0; i < ROW_MAX; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            for (int j = 0; j < COL_MAX; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }

                if (cell.getCellTypeEnum() == CellType.FORMULA) {
                    evaluator.evaluateFormulaCell(cell);
                }
            }
        }
    }

    private String getPhoneNumber(WuliuMergedOrderModel mergedOrderModel) {
        StringBuffer sb = new StringBuffer();
        if (mergedOrderModel.getTelephoneNumber() != null) {
            sb.append(mergedOrderModel.getTelephoneNumber());
        }
        if (mergedOrderModel.getMobileNumber() != null) {
            if (sb.length() > 0) {
                sb.append(" / ");
            }
            sb.append(mergedOrderModel.getMobileNumber());
        }
        return sb.toString();
    }
}

class CellInfo {

    private String orderNumber;

    private String guige;

    private String unit;

    private String count;

    private String cost;

    private String comments;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
