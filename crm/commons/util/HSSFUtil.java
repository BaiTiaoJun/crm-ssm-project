package com.zhangshun.crm.commons.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class HSSFUtil {
    public static <T> void generateWorkbook(String[] titles, String[] methods, HSSFWorkbook workbook, List<T> t) {
        //创建表页
        HSSFSheet sheet = workbook.createSheet();
        //第一行字段
        HSSFRow row = sheet.createRow(0);
        //活动表中第一行的所有字段
        HSSFCell cell;
        for (int i = 0; i < titles.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }
        //活动表中查询的字段信息
        if (t != null) {
            //遍历集合每一个对象
            for (int i = 0; i < t.size(); i++) {
                //根据对象个数创建i行字段
                row = sheet.createRow(i + 1);
                //每行获取每个集合中的对象
                Object object = t.get(i);
                //遍历当前实例对象的所有get方法
                for (int j = 0; j < methods.length; j++) {
                    try {
                        //根据遍历的get方法个数创建j列字段
                        cell = row.createCell(j);
                        //通过反射获取当前对象的get方法并通过反射赋值给当前属性列
                        Method method = object.getClass().getDeclaredMethod(methods[j]);
                        cell.setCellValue((String) method.invoke(object));
                    } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void exportWorkbook(Workbook workbook, OutputStream out) {
        try {
            workbook.write(out);
            workbook.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据excel表每列的数据类型获取对应类型的数据
    public static String getValueForColum(HSSFCell cell) {
        String value = null;
        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            value = cell.getStringCellValue();
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            value = String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
            value = cell.getCellFormula();
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            value = String.valueOf(cell.getBooleanCellValue());
        }
        return value;
    }
}
