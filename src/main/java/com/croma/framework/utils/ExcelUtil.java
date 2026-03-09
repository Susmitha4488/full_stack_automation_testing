package com.croma.framework.utils;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.*;

public class ExcelUtil {
    private String path;
    private XSSFWorkbook workbook;

    public ExcelUtil(String path) {
        this.path = path;
        try (FileInputStream fis = new FileInputStream(path)) {
            workbook = new XSSFWorkbook(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed opening excel: " + e.getMessage(), e);
        }
    }

    public List<Map<String, String>> getSheetData(String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Iterator<Row> rows = sheet.iterator();
        List<Map<String, String>> data = new ArrayList<>();
        if(!rows.hasNext()) return data;
        Row headerRow = rows.next();
        List<String> headers = new ArrayList<>();
        headerRow.forEach(cell -> headers.add(cell.getStringCellValue()));
        while (rows.hasNext()) {
            Row r = rows.next();
            Map<String, String> rowMap = new HashMap<>();
            for (int i = 0; i < headers.size(); i++) {
                Cell c = r.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                c.setCellType(CellType.STRING);
                rowMap.put(headers.get(i), c.getStringCellValue());
            }
            data.add(rowMap);
        }
        return data;
    }
}
