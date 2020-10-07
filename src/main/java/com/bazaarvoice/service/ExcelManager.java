package com.bazaarvoice.service;

import com.bazaarvoice.models.AdaptorReport;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {

    public static Workbook getWorkbook(){
        return new XSSFWorkbook();
    }

    public static Sheet createSheetInWorkbook(Workbook workbook, String sheetName){
        return workbook.createSheet(sheetName);
    }

    public static void createCellAndRows( ) {
    }
}
