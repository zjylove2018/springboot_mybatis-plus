package cn.zjy.dayong.demo.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/14
 * Time:14:59
 * Excle导出工具类
 */
public class ExcelUtil {

    public static HSSFWorkbook getHSSFWorkboot(String sheetName, String [] title, String [] [] values, HSSFWorkbook wb){
        //创建一个HSSFWorkbook
        if(wb == null){
            wb = new HSSFWorkbook();
        }
        //添加一个Seet
        HSSFSheet sheet = wb.createSheet(sheetName);
        //在sheet表中添加第0行
        HSSFRow row = sheet.createRow(0);
        //创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        // 设置标题背景色
         style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
         style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         style.setFillBackgroundColor(HSSFColor.LIGHT_GREEN.index);

        //设置标题字体
        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 13);

        //合并单元格
        CellRangeAddress region = new CellRangeAddress(0, 0 , 7, 9);
        sheet.addMergedRegion(region);

         //设置标题边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setFont(font);

        //设置数据内容字体颜色
        HSSFFont font2 = wb.createFont();
        font2.setFontName("仿宋_GB2312");
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font2.setFontHeightInPoints((short) 10);

        //声明列对象
        HSSFCell cell = null;
        //创建标题
        for (int i = 0; i <title.length ; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFCellStyle style2 = wb.createCellStyle();

        //设置内容背景颜色
        style1.setFillForegroundColor(HSSFColor.YELLOW.index);
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style1.setFillBackgroundColor(HSSFColor.YELLOW.index);
        style1.setFont(font2);  //设置数据内容字体颜色

        //设置内容背景颜色
        style2.setFillForegroundColor(HSSFColor.RED.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setFillBackgroundColor(HSSFColor.RED.index);

        //设置数据内容边框
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);

        //设置数据内容边框
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);

        for (int i = 0; i <values.length ; i++) {
            row = sheet.createRow(i+1);
            //合并单元格
            CellRangeAddress region2 = new CellRangeAddress(i + 1, i + 1 , 7, 9);
            sheet.addMergedRegion(region2);

            for (int j = 0; j <values[i].length ; j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
                if(values[i][j] != null){
                    row.getCell(j).setCellStyle(style1);
                } else {
                    row.getCell(j).setCellStyle(style2);
                }
            }
        }
        return wb;
    }
}
