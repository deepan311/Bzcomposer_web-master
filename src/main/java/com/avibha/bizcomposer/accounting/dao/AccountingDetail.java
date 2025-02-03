package com.avibha.bizcomposer.accounting.dao;

import com.avibha.bizcomposer.accounting.forms.AccountDto;
import com.avibha.bizcomposer.sales.forms.InvoiceDto;
import com.nxsol.bizcomposer.reportcenter.eSales.EsalesPOJO;
import net.sf.jasperreports.engine.JRException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class AccountingDetail {

    public void getCheckDetail(HttpServletRequest request, AccountDto accountDto) {
        HttpSession sess = request.getSession();
        String compId = (String) sess.getAttribute("CID");

        ArrayList checkDetail = new ArrayList<>();

        String datesCombo = accountDto.getDatesCombo();
        String fromDate = accountDto.getFromDate();
        String toDate = accountDto.getToDate();
        String sortBy = accountDto.getSortBy();

        AccountingDAO acd = new AccountingDAO();
        checkDetail = acd.getCheckDetailList(compId,request,datesCombo,fromDate,toDate,sortBy,accountDto);
        request.setAttribute("checkDetail", checkDetail);
    }

    public void getDepositDetail(HttpServletRequest request, AccountDto accountDto) {
        HttpSession sess = request.getSession();
        String compId = (String) sess.getAttribute("CID");

        ArrayList depositDetail = new ArrayList<>();
        String datesCombo = accountDto.getDatesCombo();
        String fromDate = accountDto.getFromDate();
        String toDate = accountDto.getToDate();
        String sortBy = accountDto.getSortBy();

        AccountingDAO acd = new AccountingDAO();
        depositDetail = acd.getDepositDetailReport(compId, request,datesCombo,fromDate,toDate,sortBy,accountDto);
        request.setAttribute("depositDetail", depositDetail);
    }

    public void getBillDetail(HttpServletRequest request, AccountDto accountDto) {
        HttpSession sess = request.getSession();
        String compId = (String) sess.getAttribute("CID");

        ArrayList billDetail = new ArrayList<>();

        String datesCombo = accountDto.getDatesCombo();
        String fromDate = accountDto.getFromDate();
        String toDate = accountDto.getToDate();
        String sortBy = accountDto.getSortBy();

        AccountingDAO acd = new AccountingDAO();
        billDetail = acd.getBillDetailReport(compId, request,datesCombo,fromDate,toDate,sortBy,accountDto);
        request.setAttribute("billDetail", billDetail);
    }

    public void getARGraph(HttpServletRequest request, AccountDto accountDto) throws JRException {
        HttpSession sess = request.getSession();
        ArrayList<AccountDto> graphDetail = new ArrayList<>();
        String compId = (String) sess.getAttribute("CID");
        AccountingDAO acd = new AccountingDAO();
        graphDetail = acd.getARGraphReport(compId,request);
    }

    public void getIEGraph(HttpServletRequest request, AccountDto accountDto) throws JRException {
        HttpSession sess = request.getSession();
        ArrayList<AccountDto> graphDetail = new ArrayList<>();
        String compId = (String) sess.getAttribute("CID");
        AccountingDAO acd = new AccountingDAO();
        graphDetail = acd.getIEGraphReport(compId,request);
    }

    public void getNWGraph(HttpServletRequest request, AccountDto accountDto) throws JRException
    {
        HttpSession sess = request.getSession();
        ArrayList<AccountDto> graphDetail = new ArrayList<>();
        String compId = (String) sess.getAttribute("CID");
        AccountingDAO acd = new AccountingDAO();
        graphDetail = acd.getNWGraphReport(compId,request);
    }

    public void getBvAGraph(HttpServletRequest request, AccountDto accountDto) throws JRException {
        HttpSession sess = request.getSession();
        ArrayList<AccountDto> graphDetail = new ArrayList<>();
        String compId = (String) sess.getAttribute("CID");
        AccountingDAO acd = new AccountingDAO();
        graphDetail = acd.getBvAGraphReport(compId,request);
    }

    public void getAccountReceivable(HttpServletRequest request, InvoiceDto invoiceDto) {
        HttpSession sess = request.getSession();
        ArrayList<AccountDto> invoiceDetail = new ArrayList<>();
        ArrayList<AccountDto> custDetail = new ArrayList<>();
        String compId = (String) sess.getAttribute("CID");

        String datesCombo = invoiceDto.getDatesCombo();
        String fromDate = invoiceDto.getFromDate();
        String toDate = invoiceDto.getToDate();
        String sortBy = invoiceDto.getSortBy();

        AccountingDAO dao = new AccountingDAO();
        invoiceDetail = dao.getInvoiceDetail(compId, request);
        custDetail = dao.arCustomerDetail(compId, request);
        request.setAttribute("custDetail", custDetail);
        request.setAttribute("invoiceDetail", invoiceDetail);
    }

    public void eSaleSalesGraph(HttpServletRequest request, AccountDto accountDto) throws JRException {
        HttpSession sess = request.getSession();
        ArrayList<EsalesPOJO> graphDetail = new ArrayList<>();
        String compId = (String) sess.getAttribute("CID");
        AccountingDAO acd = new AccountingDAO();
        graphDetail = acd.eSaleSalesGraph(compId,request);
    }
}
