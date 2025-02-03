package com.avibha.bizcomposer.File.actions;

import com.avibha.bizcomposer.sales.dao.SalesDetailsDao;
import com.avibha.bizcomposer.sales.forms.ItemDto;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ExcelReportController {

	@Autowired
    private SalesDetailsDao saleDetailsDao;
    @GetMapping("/downloadAdjustInventoryReport")
    public void downloadAdjustInventoryReport(ItemDto itemDto, HttpServletRequest request, HttpServletResponse response) {
        //SalesDetailsDao saleDetailsDao = new SalesDetailsDao();
        ArrayList<ItemDto> itemList = saleDetailsDao.ItemsList(request, itemDto);
        boolean isWholeData = Boolean.parseBoolean(request.getParameter("isWholeData"));
        String reportName = request.getParameter("reportName");

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("AdjustInventory");
        sheet.setColumnWidth(0, 15*256);
        sheet.setColumnWidth(1, 20*256);
        sheet.setColumnWidth(2, 50*256);
        sheet.setColumnWidth(3, 20*256);
        sheet.setColumnWidth(4, 15*256);
        sheet.setColumnWidth(5, 15*256);
        sheet.setColumnWidth(6, 50*256);

        final HSSFFont font = sheet.getWorkbook ().createFont ();
        font.setFontName ("Arial");
        font.setBold(true);

        final CellStyle style = sheet.getWorkbook ().createCellStyle ();
        style.setFont(font);
        style.setWrapText(true);

        HSSFRow rowhead = sheet.createRow((short) 0);
        HSSFCell cell1 = rowhead.createCell(0);
        cell1.setCellValue("Item Category");
        cell1.setCellStyle(style);
        HSSFCell cell2 = rowhead.createCell(1);
        cell2.setCellValue("Item Code");
        cell2.setCellStyle(style);
        HSSFCell cell3 = rowhead.createCell(2);
        cell3.setCellValue("Item Name");
        cell3.setCellStyle(style);
        HSSFCell cell4 = rowhead.createCell(3);
        cell4.setCellValue("Location");
        cell4.setCellStyle(style);
        HSSFCell cell5 = rowhead.createCell(4);
        cell5.setCellValue("Reorder Point");
        cell5.setCellStyle(style);
        HSSFCell cell6 = rowhead.createCell(5);
        cell6.setCellValue("Counted Qty");
        cell6.setCellStyle(style);
        HSSFCell cell7 = rowhead.createCell(6);
        cell7.setCellValue("Expected Qty");
        cell7.setCellStyle(style);
        HSSFCell cell8 = rowhead.createCell(7);
        cell8.setCellValue("Memo");
        cell8.setCellStyle(style);
        short index = 1;
        for (ItemDto item: itemList) {
            HSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(item.getCategoryName());
            row.createCell(1).setCellValue(item.getItemCode());
            row.createCell(2).setCellValue(item.getItemName());
            row.createCell(3).setCellValue(item.getLocation());
            row.createCell(4).setCellValue(item.getReorderPoint());
            row.createCell(5).setCellValue(item.getQty());
            row.createCell(6).setCellValue(isWholeData?item.getExpectedQty():"");
            row.createCell(7).setCellValue(isWholeData?item.getMemo():"");
            index++;
        }
        try{
            DateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
            String currentDate = dateFormatter.format(new Date());
            String reportFileName = "AdjustInventoryReport" + currentDate;
            if(reportName.equalsIgnoreCase("SavingInventoryLists")){
                reportFileName = "Saving Inventory Lists " + currentDate;
            }
            else if(reportName.equalsIgnoreCase("SavingCheckLists")){
                reportFileName = "Saving Check Lists " + currentDate;
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename="+reportFileName+".xls");
            ByteArrayInputStream stream = new ByteArrayInputStream(outputStream.toByteArray());
            IOUtils.copy(stream, response.getOutputStream());
            System.out.println("Excel Report created...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
