package com.avibha.bizcomposer.reports.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avibha.bizcomposer.sales.dao.SalesDetailsDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

	@GetMapping("/Reports")
	public String reports(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = "reports/reportcenter";
		String action = request.getParameter("tabid");
		if (action.equalsIgnoreCase("ReportsCenter")) {
			//SalesDetailsDao salesDetailsDao = new SalesDetailsDao();
			//salesDetailsDao.getAdjustInventoryList(request);
			forward = "reports/reportcenter";
		}
		return forward;
	}
}
