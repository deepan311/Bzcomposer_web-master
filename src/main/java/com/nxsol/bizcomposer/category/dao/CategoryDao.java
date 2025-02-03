package com.nxsol.bizcomposer.category.dao;

import com.avibha.bizcomposer.accounting.dao.Account;
import com.avibha.bizcomposer.accounting.forms.CategoryListDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class CategoryDao {

    public void getProfitLoss(HttpServletRequest request, HttpServletResponse response, CategoryListDto cform) {
        String compId = (String) request.getSession().getAttribute("CID");
        Account accountDao = new Account();
        ArrayList<CategoryListDto> profitLoss= new ArrayList<>();
        String fromDate = cform.getFromDate();
        String toDate = cform.getToDate();
        String soryBy = cform.getSortBy();
        String datesCombo = cform.getDatesCombo();
        profitLoss = accountDao.getProfitLoss(fromDate, toDate, soryBy, compId, request);
        request.setAttribute("profitLoss", profitLoss);
    }

    public void getIncomeStatement(HttpServletRequest request,HttpServletResponse response, CategoryListDto cform) {
        String compId = (String) request.getSession().getAttribute("CID");
        Account accountDao = new Account();
        ArrayList<CategoryListDto> profitLoss= new ArrayList<>();
        String datesCombo = cform.getDatesCombo();
        String fromDate = cform.getFromDate();
        String toDate = cform.getToDate();
        String sortBy = cform.getSortBy();
        ArrayList<CategoryListDto> catList = accountDao.getIncomeStatementReport(datesCombo, fromDate, toDate, sortBy, compId, request, cform);
        request.setAttribute("catList", catList);
    }
}
