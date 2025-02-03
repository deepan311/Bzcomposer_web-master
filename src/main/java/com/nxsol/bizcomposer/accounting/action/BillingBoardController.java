package com.nxsol.bizcomposer.accounting.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.BillingStatement;
import com.nxsol.bizcomposer.jasper.pojo.BillingBoardReport;
import com.nxsol.bizcomposer.jasper.pojo.BillingStatementReport;
import com.pritesh.bizcomposer.accounting.bean.ReceivableListBean;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

/*import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;*/
@Controller
public class BillingBoardController{

	@GetMapping("/BillingBoard")
	public String billingBoard(HttpServletRequest request) throws Exception {
		
		String forward = "/accounting/billingBoard";
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		ReceivableLIst rl = new ReceivableListImpl();
		Date from = null;
		Date to = null;
		String columnName = " OrderNum ";
		int InvoiceType = 113;
		int overdueDays = 0;
		String alldata = "Default";
		String advanceSearchCriteria = "";
		String advanceSearchData = "";
		String dataForBillStatement = "";
		String criteriaForBillStatement = "";
		InputStream fileInputStream;
		 String jasperPath = "";
		 String pdfName = "";
		 String rpt = ""; 
		 String destinationPsth = "";
		 int x=50;
		 int y=100;
		 int width=900;
		 int height=725;
		 
		Gson gson = new Gson();
		ArrayList<ReceivableListBean> billingList = rl.getAllInvoicesForBillingBoardWithSearchOption(from, to, "DESC", columnName, InvoiceType, overdueDays, alldata, advanceSearchCriteria, advanceSearchData);
		ArrayList<BillingStatement> billingStatementList = rl.getBillStatementList(dataForBillStatement,criteriaForBillStatement);
		request.setAttribute("billingStatementList", billingStatementList);
		request.setAttribute("billingList", billingList);
		return forward;
	}

	@PostMapping("/BillingBoardStatement")
	public String billingBoardStatement(HttpServletRequest request) throws Exception {
		String forward = "/accounting/billingBoard";
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		ReceivableLIst rl = new ReceivableListImpl();
		Date from = null;
		Date to = null;
		String columnName = " OrderNum ";
		int InvoiceType = 113;
		int overdueDays = 0;
		String alldata = "Default";
		String advanceSearchCriteria = "";
		String advanceSearchData = "";
		String dataForBillStatement = "";
		String criteriaForBillStatement = "";
		InputStream fileInputStream;
		String jasperPath = "";
		String pdfName = "";
		String rpt = "";
		String destinationPsth = "";
		int x=50;
		int y=100;
		int width=900;
		int height=725;

		Gson gson=new Gson();
		if(action.equals("CreateBillingStatement"))
		{
			rl.insertIntoBillingStatement(Integer.parseInt(request.getParameter("invoiceId")));
			ArrayList<BillingStatementReport> bill = rl.printBillingStatement(Integer.parseInt(request.getParameter("invoiceId")));
			jasperPath = request.getServletContext().getRealPath("/JasperReport/billingStatement.jrxml");
			JasperDesign design = JRXmlLoader.load(jasperPath);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport, rl.getReportParameter(), new JRBeanCollectionDataSource(bill));
			JFrame frame = new JFrame("BillingStatement");
			frame.getContentPane().add(new JRViewer(jasperPrint));
			frame.setBounds(x, y, width,height);
			frame.setVisible(true);

		}
		if(action.equals("searchByColumn"))
		{
			advanceSearchCriteria = request.getParameter("advanceSerchCriteria");
			advanceSearchData = request.getParameter("advanceSearchData");
		}
		if(action.equals("searchForBillingStatement"))
		{
			criteriaForBillStatement = request.getParameter("advanceSerchCriteria");
			dataForBillStatement = request.getParameter("advanceSearchData");
		}
		if(action.equals("PrintBill"))
		{
			String invoiceId = request.getParameter("invoiceId");
			ArrayList<BillingBoardReport> bill = rl.getBillForPrint(Integer.parseInt(invoiceId));
			jasperPath = request.getServletContext().getRealPath("/JasperReport/PrintBilling.jrxml");
			destinationPsth = request.getServletContext().getRealPath("/JasperReport/PrintBilling.pdf");
			System.out.println(request.getServletContext().getContextPath());
			/*JasperDesign design = JRXmlLoader.load(jasperPath);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport, rl.getReportParameter(), new JRBeanCollectionDataSource(bill));
			JFrame frame = new JFrame("Invoice");
	        frame.getContentPane().add(new JRViewer(jasperPrint));
	        frame.setBounds(x, y, width,height);
	        frame.setVisible(true);*/
			System.out.println(invoiceId);
		}

		ArrayList<ReceivableListBean> billingList = rl.getAllInvoicesForBillingBoardWithSearchOption(from, to, "DESC", columnName, InvoiceType, overdueDays, alldata, advanceSearchCriteria, advanceSearchData);
		ArrayList<BillingStatement> billingStatementList = rl.getBillStatementList(dataForBillStatement, criteriaForBillStatement);
		request.setAttribute("billingStatementList", billingStatementList);
		request.setAttribute("billingList", billingList);
		return forward;
	}
}
