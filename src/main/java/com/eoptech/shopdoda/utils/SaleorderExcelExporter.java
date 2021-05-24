package com.eoptech.shopdoda.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eoptech.shopdoda.entities.Saleorder;

public class SaleorderExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Saleorder> listSaleorders;
     
    public SaleorderExcelExporter(List<Saleorder> listSaleorders) {
        this.listSaleorders = listSaleorders;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Saleorders");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        
        createCell(row, 0, "STT", style);
        createCell(row, 1, "Mã đơn", style);      
        createCell(row, 2, "Thành tiền", style);       
        createCell(row, 3, "Ngày tạo", style);    
        createCell(row, 4, "Khách hàng", style);
        createCell(row, 5, "Địa chỉ", style);
        createCell(row, 6, "Số điện thoại", style);
        createCell(row, 7, "email", style);
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (int i=0; i<listSaleorders.size(); i++) {
        	Saleorder saleorder = listSaleorders.get(i);
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, i+1, style);
            createCell(row, columnCount++, saleorder.getCode(), style);
            createCell(row, columnCount++, saleorder.getTotal().intValue(), style);
            DateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            createCell(row, columnCount++, dateFormatter.format(saleorder.getCreatedDate()), style);
            createCell(row, columnCount++, saleorder.getCustomerName(), style);
            createCell(row, columnCount++, saleorder.getCustomerAddress(), style);
            createCell(row, columnCount++, saleorder.getCustomerPhone(), style);
            createCell(row, columnCount++, saleorder.getCustomerEmail(), style);
        }
        //https://openplanning.net/11259/doc-ghi-file-excel-trong-java-su-dung-apache-poi#a5144240
        CellStyle styleNew = workbook.createCellStyle();
        XSSFFont fontNew = workbook.createFont();
        fontNew.setBold(true);
        fontNew.setFontHeight(16);
        styleNew.setFont(fontNew);
        Row row = sheet.createRow(rowCount+1);
        createCell(row, 1, "Doanh thu:", styleNew);
        Cell cell = row.createCell(2, CellType.FORMULA);
        cell.setCellFormula("SUM(C2:C"+(rowCount+1)+")");
        cell.setCellStyle(styleNew);
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}