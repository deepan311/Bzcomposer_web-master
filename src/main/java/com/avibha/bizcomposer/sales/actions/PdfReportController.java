package com.avibha.bizcomposer.sales.actions;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avibha.bizcomposer.purchase.dao.PurchaseBoard;
import com.avibha.bizcomposer.purchase.dao.PurchaseBoardDetails;
import com.avibha.bizcomposer.purchase.forms.PurchaseBoardDto;
import com.avibha.bizcomposer.sales.dao.EstimationBoard;
import com.avibha.bizcomposer.sales.dao.EstimationBoardDetails;
import com.avibha.bizcomposer.sales.dao.SalesBoard;
import com.avibha.bizcomposer.sales.dao.SalesBoardDetails;
import com.avibha.bizcomposer.sales.dao.SalesOrderBoardDetails;
import com.avibha.bizcomposer.sales.forms.EstimationBoardDto;
import com.avibha.bizcomposer.sales.forms.SalesBoardDto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/PdfReport")
public class PdfReportController {
	@Autowired
	private SalesBoardDetails salesBoardDtls;
	@Autowired
	private EstimationBoardDetails estimationBoardDetails;
	@Autowired
	private SalesOrderBoardDetails salesOrderBoardDetails;
	@Autowired
	private  PurchaseBoardDetails purchaseBoardDtls;
	
    @GetMapping("/downloadInvoiceBoardReport")
    public void downloadPdfReport(@RequestParam List<Integer> selectedRowIDs, SalesBoardDto salesBoardDto, HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
       // SalesBoardDetails sd = new SalesBoardDetails();
        ArrayList<SalesBoard> objList = salesBoardDtls.getSalesBoardDetails(request, salesBoardDto);
        DateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        String reportFileName = "InvoiceBoardReport-" + dateFormatter.format(new Date());
        String reportTitle = "Invoice Board Report - " + dateFormatter.format(new Date());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="+reportFileName+".pdf");
        System.out.println("PDF Report Exporting...");
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, response.getOutputStream());
            //document header attributes
            doc.addAuthor("SarfrazMalik");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("namemaxx.com");
            doc.addTitle(reportTitle);
            doc.setPageSize(PageSize.LETTER);
            doc.open();

            //special font sizes
            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10);
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(0, 0, 0));
            DecimalFormat df = new DecimalFormat("0.00");

            float[] columnWidths = {1f, 1.2f, 2f, 2f, 1.5f, 1.5f, 1f, 1f};      //specify column widths
            PdfPTable table = new PdfPTable(columnWidths);  //create PDF table with the given widths
            table.setWidthPercentage(100f);  // set table width a percentage of the page width

            //insert column headings
            insertCell(table, "Order#", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Order Date", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Company", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Customer", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Sales Amount", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Email ID", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "isShipped", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "isEmailed", Element.ALIGN_CENTER, 1, bfBold12);
            table.setHeaderRows(1);
            for (SalesBoard salesBoard :objList) {
                if(selectedRowIDs.contains(Integer.valueOf(salesBoard.getOrderNum()+""))) {
                    insertCell(table, salesBoard.getOrderNum() + "", Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, salesBoard.getDateAdded(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, salesBoard.getCompanyName(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, salesBoard.getFirstName() + " " + salesBoard.getLastName(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, df.format(salesBoard.getTotal()), Element.ALIGN_RIGHT, 1, bf12);
                    insertCell(table, salesBoard.getEmail(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, salesBoard.getShipped() + "", Element.ALIGN_CENTER, 1, bf12);
                    insertCell(table, salesBoard.getEmailed() + "", Element.ALIGN_CENTER, 1, bf12);
                }
            }
            Paragraph paragraph = new Paragraph(reportTitle);
            paragraph.add(table);   //add the PDF table to the paragraph
            doc.add(paragraph);     // add the paragraph to the document
            doc.close();
            System.out.println("PDF Report Exported.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/downloadEstimationBoardReport")
    public void downloadEstimationBoardReport(@RequestParam List<Integer> selectedRowIDs, EstimationBoardDto estBoardDto, HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
       // EstimationBoardDetails estimationBoardDetails = new EstimationBoardDetails();
        ArrayList<EstimationBoard> objList = estimationBoardDetails.getEstimationBoardDetails(request, estBoardDto);
        DateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        String reportFileName = "EstimationBoardReport-" + dateFormatter.format(new Date());
        String reportTitle = "Estimation Board Report - " + dateFormatter.format(new Date());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="+reportFileName+".pdf");
        System.out.println("PDF Report Exporting...");
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, response.getOutputStream());
            doc.addAuthor("SarfrazMalik");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("namemaxx.com");
            doc.addTitle(reportTitle);
            doc.setPageSize(PageSize.LETTER);
            doc.open();

            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10);
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(0, 0, 0));

            float[] columnWidths = {1f, 2f, 2f, 1.2f, 1f, 1f, 1f};      //specify column widths
            PdfPTable table = new PdfPTable(columnWidths);  //create PDF table with the given widths
            table.setWidthPercentage(100f);  // set table width a percentage of the page width

            //insert column headings
            insertCell(table, "Est. No#", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Company", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Customer", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Email ID", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Est. Date", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Sale Date", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "isShipped", Element.ALIGN_CENTER, 1, bfBold12);
            table.setHeaderRows(1);
            for (EstimationBoard estBoard :objList) {
                if(selectedRowIDs.contains(Integer.valueOf(estBoard.getEst_no()+""))) {
                    insertCell(table, estBoard.getEst_no() + "", Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, estBoard.getCompanyName(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, estBoard.getFirstName() + " " + estBoard.getLastName(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, estBoard.getEmail(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, estBoard.getSaleDate() + "", Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, estBoard.getDateAdded(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, estBoard.getShipped() + "", Element.ALIGN_CENTER, 1, bf12);
                }
            }
            Paragraph paragraph = new Paragraph(reportTitle);
            paragraph.add(table);   //add the PDF table to the paragraph
            doc.add(paragraph);     // add the paragraph to the document
            doc.close();
            System.out.println("PDF Report Exported.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/downloadSOBoardReport")
    public void downloadSOBoardReport(@RequestParam List<Integer> selectedRowIDs, SalesBoardDto salesBoardDto, HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
       // SalesOrderBoardDetails salesOrderBoardDetails = new SalesOrderBoardDetails();
        ArrayList<SalesBoard> objList = salesOrderBoardDetails.getSalesOrderBoardDetails(request, salesBoardDto);
        DateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        String reportFileName = "SOBoardReport-" + dateFormatter.format(new Date());
        String reportTitle = "Sales Order Board Report - " + dateFormatter.format(new Date());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="+reportFileName+".pdf");
        System.out.println("PDF Report Exporting...");
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, response.getOutputStream());
            //document header attributes
            doc.addAuthor("SarfrazMalik");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("namemaxx.com");
            doc.addTitle(reportTitle);
            doc.setPageSize(PageSize.LETTER);
            doc.open();

            //special font sizes
            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10);
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(0, 0, 0));

            float[] columnWidths = {1f, 2f, 2f, 1.5f, 1.2f, 1.2f, 1f, 1f};      //specify column widths
            PdfPTable table = new PdfPTable(columnWidths);  //create PDF table with the given widths
            table.setWidthPercentage(100f);  // set table width a percentage of the page width

            //insert column headings
            insertCell(table, "SO#", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Company", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Customer", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Email ID", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Order Date", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Sale Date", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "isInvoice", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "isEmailed", Element.ALIGN_CENTER, 1, bfBold12);
            table.setHeaderRows(1);
            for (SalesBoard salesBoard :objList) {
                if(selectedRowIDs.contains(Integer.valueOf(salesBoard.getSo_no()+""))) {
                    insertCell(table, salesBoard.getSo_no() + "", Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, salesBoard.getCompanyName(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, salesBoard.getFirstName() + " " + salesBoard.getLastName(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, salesBoard.getEmail(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, salesBoard.getSaleDate(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, salesBoard.getDateAdded(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, salesBoard.getIsInvoice() + "", Element.ALIGN_CENTER, 1, bf12);
                    insertCell(table, salesBoard.getEmailed() + "", Element.ALIGN_CENTER, 1, bf12);
                }
            }
            Paragraph paragraph = new Paragraph(reportTitle);
            paragraph.add(table);   //add the PDF table to the paragraph
            doc.add(paragraph);     // add the paragraph to the document
            doc.close();
            System.out.println("PDF Report Exported.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/downloadPOBoardReport")
    public void downloadPOBoardReport(@RequestParam List<Integer> selectedRowIDs, PurchaseBoardDto purchaseBoardDto, HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
        //PurchaseBoardDetails pd = new PurchaseBoardDetails();
        ArrayList<PurchaseBoard> objList = purchaseBoardDtls.getPurchaseBoardDetails(request, purchaseBoardDto);
        DateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        String reportFileName = "POBoardReport-" + dateFormatter.format(new Date());
        String reportTitle = "Purchase Order Board Report - " + dateFormatter.format(new Date());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="+reportFileName+".pdf");
        System.out.println("PDF Report Exporting...");
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, response.getOutputStream());
            //document header attributes
            doc.addAuthor("SarfrazMalik");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("namemaxx.com");
            doc.addTitle(reportTitle);
            doc.setPageSize(PageSize.LETTER);
            doc.open();

            //special font sizes
            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10);
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(0, 0, 0));
            DecimalFormat df = new DecimalFormat("0.00");

            float[] columnWidths = {1f, 2f, 2f, 1.5f, 1.2f, 1f, 1f, 1.2f, 1.5f};      //specify column widths
            PdfPTable table = new PdfPTable(columnWidths);  //create PDF table with the given widths
            table.setWidthPercentage(100f);  // set table width a percentage of the page width

            //insert column headings
            insertCell(table, "PONum", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Company", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Customer", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Email ID", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "PO Amount", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "isEmailed", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "isReceived", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "PO Date", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Received Date", Element.ALIGN_CENTER, 1, bfBold12);
            table.setHeaderRows(1);
            for (PurchaseBoard poBoard :objList) {
                if(selectedRowIDs.contains(Integer.valueOf(poBoard.getPo_no()+""))) {
                    insertCell(table, "PO-" + poBoard.getPo_no() + "", Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, poBoard.getCompanyName(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, poBoard.getFirstName() + " " + poBoard.getLastName(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, poBoard.getEmail(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, df.format(poBoard.getTotal()), Element.ALIGN_RIGHT, 1, bf12);
                    insertCell(table, poBoard.getEmailed() + "", Element.ALIGN_CENTER, 1, bf12);
                    insertCell(table, poBoard.getIsReceived() + "", Element.ALIGN_CENTER, 1, bf12);
                    insertCell(table, poBoard.getDateAdded(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, poBoard.getDateReceived(), Element.ALIGN_LEFT, 1, bf12);
                }
            }
            Paragraph paragraph = new Paragraph(reportTitle);
            paragraph.add(table);   //add the PDF table to the paragraph
            doc.add(paragraph);     // add the paragraph to the document
            doc.close();
            System.out.println("PDF Report Exported.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {
        if(text == null){
            text = "";
        }
        else if(text.contains("null")){
            text = text.replace("null", "");
        }
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);           //set the cell column span in case you want to merge two or more cells
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        table.addCell(cell);
    }

}
