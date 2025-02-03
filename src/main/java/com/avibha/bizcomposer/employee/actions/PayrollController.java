/*
 * Author :
 */

package com.avibha.bizcomposer.employee.actions;

import com.avibha.bizcomposer.employee.dao.*;
import com.avibha.bizcomposer.employee.forms.AddEmployeeForm;
import com.avibha.bizcomposer.employee.forms.PayrollDto;
import com.avibha.bizcomposer.employee.forms.TimeSheetForm;
import com.avibha.common.utility.Path;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Controller
public class PayrollController {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = {"/Payroll"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView Payroll(ModelMap model, PayrollDto payrollDto, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "redirect:/employee";
		String action = request.getParameter("tabid");
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null) {
			forward = "Expired";
		} else if(action.equalsIgnoreCase("Payroll")) {
			// periodList
			PayrollPeriod pp = new PayrollPeriod();
			ArrayList pList = pp.getPayrollPeriodList("1");
			request.setAttribute("periodList", pList);
			Employee e = new Employee();
			ArrayList<?> arr = e.getEmployeeList(compId, "1");
			if (arr.size() > 0)
				request.setAttribute("empList", arr);
			forward = "/employee/createpayroll";
		} else if(action.equalsIgnoreCase("createList")) {
			String month[]={"month","January" ,"February" ,"March" ,"April" ,"May" ,"June" ,"July" ,"August" ,"September" ,"October" ,"November" ,"December"};
			Employee e = new Employee();
			ArrayList<?> arr = e.getEmployeeList(compId, "1");
			if (arr.size() > 0)
				request.setAttribute("empList", arr);

			boolean CheckPayroll = e.CheckPayroll(payrollDto, compId);
			if (CheckPayroll){
				model.addAttribute("status", false);
				model.addAttribute("message", "You already Created for "+month[Integer.parseInt(payrollDto.getMonth())]+" "+payrollDto.getYear()+" Payroll");
			}else{
				boolean check = e.createPayroll(payrollDto, compId);
				model.addAttribute("status", true);
				model.addAttribute("message",month[Integer.parseInt(payrollDto.getMonth())]+" "+payrollDto.getYear()+" Payroll Created Successfully");
			}
			forward = "/employee/createpayroll";
		}else if(action.equalsIgnoreCase("payrollList")) {
			Employee e = new Employee();
			ArrayList<PayrollDto> arr = e.getPayrollList(compId);
			if (arr.size() > 0)
				request.setAttribute("payrollList", arr);
			forward = "/employee/payrolllist";
		}else if(action.equalsIgnoreCase("deletePayroll")) {
			String PayrollID = request.getParameter("payrollid");
			Employee e = new Employee();
			boolean c = e.deletePayroll(compId, PayrollID);
			model.addAttribute("message","Payroll Deleted Successfully");
			forward = "/employee/payrolllist";
		}

		ModelAndView modelAndView =new ModelAndView(forward);
		return modelAndView;
	}
	@RequestMapping(value = {"/GetPayroll"}, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ArrayList<PayrollDto> execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ParseException {
		Employee employee =new Employee();
		String empid = request.getParameter("empid");
		String payperiodid = request.getParameter("payperiodid");
		String week = request.getParameter("week");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String startDate = null;
		String endDate = null;
		if(week != null){

		}else {
			startDate = year+"-"+month+"-01";
			endDate = year+"-"+month+"-31";
		}

		ArrayList<PayrollDto> payroll = employee.findPayroll(empid, payperiodid, startDate, endDate);
		System.out.println(payroll);
		return payroll;
	}

	@RequestMapping(value = {"/DownloadPayroll"}, method = {RequestMethod.GET, RequestMethod.POST})
	public void exportToPDF(HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException, ServletException, ParseException  {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Payroll_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		HttpSession sess = request.getSession();
		String ComapnyID = (String) sess.getAttribute("CID");
		String PayrollID = request.getParameter("payrollID");

		Employee e = new Employee();
		ArrayList<PayrollDto> payrollDetails = e.downloadPayroll(PayrollID,ComapnyID);
		ArrayList<TimeSheet> timeSheetList = e.getPayrollTimeSheet(ComapnyID,payrollDetails.get(0).getEmployeeID(),payrollDetails.get(0).getYear(), payrollDetails.get(0).getMonth());
		ArrayList<AddEmployeeForm> employeeDetails = e.getPayrollEmployeeDetails(ComapnyID, payrollDetails.get(0).getEmployeeID());
		PdfGeneration exporter = new PdfGeneration(payrollDetails,employeeDetails,timeSheetList);
		exporter.generatePdfForPayRoll(response);

	}
}
