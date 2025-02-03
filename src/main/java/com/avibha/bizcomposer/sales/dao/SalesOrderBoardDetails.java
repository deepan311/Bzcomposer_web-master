package com.avibha.bizcomposer.sales.dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avibha.bizcomposer.sales.forms.SalesBoardDto;
import com.avibha.common.log.Loger;

@Service
public class SalesOrderBoardDetails {

	private SalesOrderBoardInfo saleInfo;
	@Autowired
	public SalesOrderBoardDetails(SalesOrderBoardInfo saleInfo) {
		super();
		this.saleInfo = saleInfo;
	}

	
    public ArrayList getSalesOrderBoardDetails(HttpServletRequest request, SalesBoardDto sform) {
        HttpSession sess = request.getSession();
        String compId = (String) sess.getAttribute("CID");
     //   SalesOrderBoardInfo SaleInfo = new SalesOrderBoardInfo();
        ArrayList saleOrderDetails = saleInfo.SalesRecordSearch(compId, sform);
        request.setAttribute("SalesOrderBoardDetails", saleOrderDetails);
        request.setAttribute("Market", sform.getFilterMarket());
        return saleOrderDetails;
    }

    public void updateRecord(HttpServletRequest request) {
       // SalesOrderBoardInfo salesInfo = new SalesOrderBoardInfo();
        boolean result = saleInfo.update(request);
        String msg = "";
        if (result) {
            msg = "**Update is sucessfully completed";
            Loger.log("Updated " + msg);
        } else {
            msg = "**Record is not updated";
        }
        request.setAttribute("IsUpdated", msg);
    }
}
