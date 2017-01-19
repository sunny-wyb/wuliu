/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package wuliu.test;

import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 类POITest.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2017年1月18日 上午11:06:17
 */
public class POITest {

    public static void main(String args[]) {
        Workbook workbook = null;
        workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("testdata");
        System.out.println(sheet.getColumnWidth(0));
        sheet.setColumnWidth(0, 1024);
        mergeCell(sheet);
//        Row[] rows = new Row[18];
//        for (int i = 0; i < 18; i++) {
//            rows[i] = sheet.createRow(i);
//        }
//        Row row0 = rows[0];
//
//        sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
//                                                   17, // last row (0-based)
//                                                   0, // first column (0-based)
//                                                   0 // last column (0-based)
//        ));
//
//        sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
//                                                   17, // last row (0-based)
//                                                   8, // first column (0-based)
//                                                   8 // last column (0-based)
//        ));
//
//        Cell temp = rows[1].getCell(0);
//        if (temp == null) {
//            temp = rows[1].createCell(0);
//        }
//        temp.setCellValue("义乌市收货地址:义乌市青口后张新村42幢");
//        CellStyle style1 = workbook.createCellStyle();
//        style1.setWrapText(true);
//        style1.setAlignment(HorizontalAlignment.CENTER);
//        style1.setVerticalAlignment(VerticalAlignment.CENTER);
//        temp.setCellStyle(style1);
//        temp = rows[1].getCell(9);
//        // temp.setCellValue("乌市卸货地址:乌鲁木齐市三泰路南郊停车场内");
//
//        /*
//         * 创建第一排，合并单元格，设置行高，设置字体，设置行宽
//         */
//        for (int i = 0; i < 10; i++) {
//            Cell cell = row0.createCell(i);
//            if (i == 9) {
//                cell.setCellValue("1");
//            }
//        }
//
//        row0.getCell(1).setCellValue("浙江省义乌市众佳物流货运单");
//        /*
//         * 设置cell style
//         */
//        Font font = workbook.createFont();
//        font.setFontHeightInPoints((short) 24);
//        font.setFontName("Times");
//        CellStyle style = workbook.createCellStyle();
//        style.setFont(font);
//        style.setWrapText(true);
//        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setVerticalAlignment(VerticalAlignment.CENTER);
//        row0.getCell(1).setCellStyle(style);
//
//        row0.setHeightInPoints(36);
//
//        sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
//                                                   0, // last row (0-based)
//                                                   1, // first column (0-based)
//                                                   8 // last column (0-based)
//        ));
//
//        /*
//         * 第二行
//         */
//
//        sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
//                                                   1, // last row (0-based)
//                                                   1, // first column (0-based)
//                                                   8 // last column (0-based)
//        ));
//        temp = rows[1].getCell(1);
//        if (temp == null) {
//            temp = rows[1].createCell(1);
//        }
//        temp.setCellValue("乌市:15276684875/18099224546     义乌:13625892411/13989439213");
//
//        /*
//         * 第三行
//         */
//        sheet.addMergedRegion(new CellRangeAddress(2, // first row (0-based)
//                                                   2, // last row (0-based)
//                                                   1, // first column (0-based)
//                                                   3 // last column (0-based)
//        ));
//        temp = rows[2].getCell(1);
//        if (temp == null) {
//            temp = rows[2].createCell(1);
//        }
//        temp.setCellValue("送货单");
//
//        /*
//         * 第四行
//         */
//        temp = rows[3].getCell(2);
//        if (temp == null) {
//            temp = rows[3].createCell(2);
//        }
//        temp.setCellValue("客户名称：");
//
//        temp = rows[3].getCell(5);
//        if (temp == null) {
//            temp = rows[3].createCell(5);
//        }
//        temp.setCellValue("客户电话：");
//
//        sheet.addMergedRegion(new CellRangeAddress(3, // first row (0-based)
//                                                   3, // last row (0-based)
//                                                   3, // first column (0-based)
//                                                   4 // last column (0-based)
//        ));
//        temp = rows[3].getCell(3);
//        if (temp == null) {
//            temp = rows[3].createCell(3);
//        }
//        temp.setCellValue("客户名字");
//
//        sheet.addMergedRegion(new CellRangeAddress(3, // first row (0-based)
//                                                   3, // last row (0-based)
//                                                   6, // first column (0-based)
//                                                   8 // last column (0-based)
//        ));
//        temp = rows[3].getCell(6);
//        if (temp == null) {
//            temp = rows[3].createCell(6);
//        }
//        temp.setCellValue("15867136779");
//
//        /*
//         * 第五行
//         */
//        temp = rows[4].getCell(2);
//        if (temp == null) {
//            temp = rows[4].createCell(2);
//        }
//        temp.setCellValue("客户地址：");
//
//        temp = rows[4].getCell(5);
//        if (temp == null) {
//            temp = rows[4].createCell(5);
//        }
//        temp.setCellValue("送货日期：");
//
//        sheet.addMergedRegion(new CellRangeAddress(4, // first row (0-based)
//                                                   4, // last row (0-based)
//                                                   3, // first column (0-based)
//                                                   4 // last column (0-based)
//        ));
//        temp = rows[4].getCell(3);
//        if (temp == null) {
//            temp = rows[4].createCell(3);
//        }
//        temp.setCellValue("地址地址地址地址地址");
//
//        sheet.addMergedRegion(new CellRangeAddress(4, // first row (0-based)
//                                                   4, // last row (0-based)
//                                                   6, // first column (0-based)
//                                                   8 // last column (0-based)
//        ));
//        temp = rows[4].getCell(6);
//        if (temp == null) {
//            temp = rows[4].createCell(6);
//        }
//        temp.setCellValue(new Date());
//
//        /*
//         * 第六行
//         */
//        temp = rows[5].getCell(1);
//        if (temp == null) {
//            temp = rows[5].createCell(1);
//        }
//        temp.setCellValue("序号");
//
//        temp = rows[5].getCell(2);
//        if (temp == null) {
//            temp = rows[5].createCell(2);
//        }
//        temp.setCellValue("产品货号");
//
//        temp = rows[5].getCell(3);
//        if (temp == null) {
//            temp = rows[5].createCell(3);
//        }
//        temp.setCellValue("单位");
//
//        temp = rows[5].getCell(4);
//        if (temp == null) {
//            temp = rows[5].createCell(4);
//        }
//        temp.setCellValue("数量");
//
//        temp = rows[5].getCell(5);
//        if (temp == null) {
//            temp = rows[5].createCell(5);
//        }
//        temp.setCellValue("单价");
//
//        temp = rows[5].getCell(6);
//        if (temp == null) {
//            temp = rows[5].createCell(6);
//        }
//        temp.setCellValue("金额");
//
//        temp = rows[5].getCell(7);
//        if (temp == null) {
//            temp = rows[5].createCell(7);
//        }
//        temp.setCellValue("备注");

        try {
            FileOutputStream outputStream = new FileOutputStream("/tmp/test.xlsx");
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workbook.close();
            System.out.println("success");
        } catch (Exception e) {
            System.out.println("It cause Error on WRITTING excel workbook: ");
            e.printStackTrace();
        }
    }

    private Cell createCellIfNotExist(Row row, int colNum) {
        if (row == null) {
            return null;
        }

        Cell ret = row.getCell(colNum);
        if (ret == null) {
            ret = row.createCell(colNum);
        }

        return ret;
    }
    
    public static void fillStaticInfo(Sheet sheet) {
        
    }

    public static boolean mergeCell(Sheet sheet) {
        /*
         * merge第一行
         */
        sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
                                                   0, // last row (0-based)
                                                   1, // first column (0-based)
                                                   8 // last column (0-based)
        ));
        
        /*
         * merge左边列
         */
        sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
                                                   17, // last row (0-based)
                                                   0, // first column (0-based)
                                                   0 // last column (0-based)
        ));
        
        
        /*
         * merge右边列
         */
        sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
                                                   17, // last row (0-based)
                                                   9, // first column (0-based)
                                                   9 // last column (0-based)
        ));
        
        /*
         * merge第二行
         */
        sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
                                                   1, // last row (0-based)
                                                   1, // first column (0-based)
                                                   8 // last column (0-based)
        ));
        
        /*
         * 第三行
         */
        sheet.addMergedRegion(new CellRangeAddress(2, // first row (0-based)
                                                   2, // last row (0-based)
                                                   1, // first column (0-based)
                                                   8 // last column (0-based)
        ));
        
        /*
         * 第四行
         */
        sheet.addMergedRegion(new CellRangeAddress(3, // first row (0-based)
                                                   3, // last row (0-based)
                                                   3, // first column (0-based)
                                                   4 // last column (0-based)
        ));
        
        sheet.addMergedRegion(new CellRangeAddress(3, // first row (0-based)
                                                   3, // last row (0-based)
                                                   6, // first column (0-based)
                                                   8 // last column (0-based)
        ));
        
        /*
         * 第五行
         */
        sheet.addMergedRegion(new CellRangeAddress(4, // first row (0-based)
                                                   4, // last row (0-based)
                                                   3, // first column (0-based)
                                                   5 // last column (0-based)
        ));
        
        sheet.addMergedRegion(new CellRangeAddress(4, // first row (0-based)
                                                   4, // last row (0-based)
                                                   7, // first column (0-based)
                                                   8 // last column (0-based)
        ));
        
        
        /*
         * 第十五行
         */
        sheet.addMergedRegion(new CellRangeAddress(14, // first row (0-based)
                                                   14, // last row (0-based)
                                                   1, // first column (0-based)
                                                   2 // last column (0-based)
        ));
        
        sheet.addMergedRegion(new CellRangeAddress(14, // first row (0-based)
                                                   14, // last row (0-based)
                                                   3, // first column (0-based)
                                                   4 // last column (0-based)
        ));
        
        sheet.addMergedRegion(new CellRangeAddress(14, // first row (0-based)
                                                   14, // last row (0-based)
                                                   5, // first column (0-based)
                                                   6 // last column (0-based)
        ));
        
        sheet.addMergedRegion(new CellRangeAddress(14, // first row (0-based)
                                                   14, // last row (0-based)
                                                   7, // first column (0-based)
                                                   8 // last column (0-based)
        ));
        
        /*
         * 第十六行
         */
        sheet.addMergedRegion(new CellRangeAddress(15, // first row (0-based)
                                                   15, // last row (0-based)
                                                   1, // first column (0-based)
                                                   8 // last column (0-based)
        ));
        
        /*
         * 第十七行
         */
        sheet.addMergedRegion(new CellRangeAddress(16, // first row (0-based)
                                                   16, // last row (0-based)
                                                   1, // first column (0-based)
                                                   2 // last column (0-based)
        ));
        
        sheet.addMergedRegion(new CellRangeAddress(16, // first row (0-based)
                                                   16, // last row (0-based)
                                                   3, // first column (0-based)
                                                   5 // last column (0-based)
        ));
        
        sheet.addMergedRegion(new CellRangeAddress(16, // first row (0-based)
                                                   16, // last row (0-based)
                                                   7, // first column (0-based)
                                                   8 // last column (0-based)
        ));
        
        /*
         * 第十八行
         */
        sheet.addMergedRegion(new CellRangeAddress(17, // first row (0-based)
                                                   17, // last row (0-based)
                                                   1, // first column (0-based)
                                                   2 // last column (0-based)
        ));
        
        sheet.addMergedRegion(new CellRangeAddress(17, // first row (0-based)
                                                   17, // last row (0-based)
                                                   5, // first column (0-based)
                                                   6 // last column (0-based)
        ));
        
        return true;
    }

    public static CellStyle getStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // 设置单元格字体
        Font headerFont = workbook.createFont(); // 字体
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(HSSFColor.RED.index);
        headerFont.setFontName("宋体");
        style.setFont(headerFont);
        style.setWrapText(true);

        // 设置单元格边框及颜色
        style.setBorderBottom((short) 1);
        style.setBorderLeft((short) 1);
        style.setBorderRight((short) 1);
        style.setBorderTop((short) 1);
        style.setWrapText(true);
        return style;
    }
}
