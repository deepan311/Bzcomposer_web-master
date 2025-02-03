package com.avibha.bizcomposer.sales.dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.avibha.bizcomposer.sales.forms.SalesBoardDto;
import com.avibha.common.log.Loger;

@Controller
public class SalesBoardDetails {
	private SalesBoardInfo SaleInfo;
	
	@Autowired
    public SalesBoardDetails(SalesBoardInfo saleInfo) {
		super();
		SaleInfo = saleInfo;
	}

	public ArrayList getSalesBoardDetails(HttpServletRequest request, SalesBoardDto salesBoardDto) {
        String compId = (String) request.getSession().getAttribute("CID");
        String invoiceReportType = request.getParameter("ilist"); //report Type
        invoiceReportType =(invoiceReportType == null) ?"":invoiceReportType;

       // SalesBoardInfo SaleInfo = new SalesBoardInfo();
        ArrayList saleDetails = SaleInfo.SalesRecordSearch(compId, invoiceReportType, salesBoardDto);
        request.setAttribute("SalesBoardDetails", saleDetails);
        request.setAttribute("Market", salesBoardDto.getFilterMarket());
        return saleDetails;
    }

    public void getSalesReportByCustomer(HttpServletRequest request, SalesBoardDto salesBoardDto) {
        String compId = (String) request.getSession().getAttribute("CID");
        String invoiceReportType = request.getParameter("ilist");
        String invoiceReportType1 = request.getParameter("ilist");
        invoiceReportType1 =(invoiceReportType == null) ?"":invoiceReportType1;

      //  SalesBoardInfo SaleInfo = new SalesBoardInfo();
        ArrayList salesRBC = SaleInfo.getSaleReportCustomerSearch(compId, invoiceReportType1, salesBoardDto);
        request.setAttribute("salesRBC", salesRBC);
    }

    public void getSalesReportByRep(HttpServletRequest request, SalesBoardDto salesBoardDto){

        HttpSession sess = request.getSession();
        String compId = (String) sess.getAttribute("CID");

     //   SalesBoardInfo SaleInfo = new SalesBoardInfo();

        ArrayList SalesReportByRep = new ArrayList();
        String oDate1 = salesBoardDto.getOrderDate1();
        String oDate2 = salesBoardDto.getOrderDate2();

        SalesReportByRep = SaleInfo.SalesReportByRep(compId, oDate1, oDate2);
        request.setAttribute("SalesReportByRep", SalesReportByRep);
        request.setAttribute("Market", salesBoardDto.getFilterMarket());
    }

    public void updateRecord(HttpServletRequest request) {
       // SalesBoardInfo salesInfo = new SalesBoardInfo();
        boolean result = SaleInfo.update(request);
        String msg = "";
        if (result) {
            msg = "**Update is sucessfully completed";
            Loger.log("Updated " + msg);
        } else {
            msg = "**Record is not updated";
        }
        request.setAttribute("IsUpdated", msg);
    }

    public void getRefundInvoiceReport(HttpServletRequest request, SalesBoardDto salesBoardDto) {
        HttpSession sess = request.getSession();
        String compId = (String) sess.getAttribute("CID");
      //  SalesBoardInfo SaleInfo = new SalesBoardInfo();
        ArrayList refundInvoiceReport = new ArrayList();
        String oDate1 = salesBoardDto.getOrderDate1();
        String oDate2 = salesBoardDto.getOrderDate2();

        refundInvoiceReport = SaleInfo.getRefundInvoiceReport(compId,oDate1,oDate2);
        request.setAttribute("refundInvoice", refundInvoiceReport);
    }
}
