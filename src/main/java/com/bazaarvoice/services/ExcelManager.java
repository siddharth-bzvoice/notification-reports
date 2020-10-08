package com.bazaarvoice.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelManager.class);

    public static Workbook getWorkbook(){
        return new XSSFWorkbook();
    }

    public static Sheet createSheetInWorkbook(Workbook workbook, String sheetName){
        return workbook.createSheet(sheetName);
    }

    private static List<String> getFieldNamesForClass(Class<?> clazz) throws IOException {
        List<String> fieldNames = new ArrayList<String>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    private static String capitalize(String s) {
        if (s.length() == 0)
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static <T> ByteArrayOutputStream writeToExcel(String sheetName, List<T> data) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Workbook workbook = ExcelManager.getWorkbook();

            Sheet sheet = ExcelManager.createSheetInWorkbook(workbook, sheetName);

            List<String> fieldNames = getFieldNamesForClass(data.get(0).getClass());

            int rowCount = 0;
            int columnCount = 0;

            Row row = sheet.createRow(rowCount++);

            for (String fieldName : fieldNames) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(fieldName);
            }

            Class<?> classz = data.get(0).getClass();

            for (T t : data) {
                row = sheet.createRow(rowCount++);
                columnCount = 0;
                for (String fieldName : fieldNames) {
                    Cell cell = row.createCell(columnCount);
                    Method method = null;
                    try {
                        method = classz.getMethod("get" + capitalize(fieldName));
                    } catch (NoSuchMethodException nme) {
                        method = classz.getMethod("get" + fieldName);
                    }
                    Object value = method.invoke(t, (Object[]) null);
                    if (value != null) {
                        if (value instanceof String) {
                            cell.setCellValue((String) value);
                        } else if (value instanceof Long) {
                            cell.setCellValue((Long) value);
                        } else if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof Double) {
                            cell.setCellValue((Double) value);
                        } else if (value instanceof Date){
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            String strDate = dateFormat.format(value);
                            cell.setCellValue(strDate);
                        }
                    }
                    columnCount++;
                }
            }
            workbook.write(out);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return out;
    }
}
