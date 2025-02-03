package com.avibha.bizcomposer.employee.actions;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.avibha.bizcomposer.employee.dao.Employee;
import com.avibha.bizcomposer.employee.dao.TimeSheet;
import com.avibha.bizcomposer.employee.forms.TimeSheetForm;

import net.minidev.json.JSONObject;

@Controller
public class TimeSheetController {
	@RequestMapping(value = {"/TimeSheet"}, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ModelAndView execute(@RequestBody JSONObject obj1, TimeSheetForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ParseException {
		String forward = "/employee/timesheet";
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null) {
			forward = "Expired";
		} else {
			TimeSheetForm tf = (TimeSheetForm) form;
			Employee e = new Employee();
			String EmployeeID = null;
			String Day = null;
			String StartWork1 = null;
			String EndWork1 =null;
			String StartWork2 = null;
			String EndWork2 =null;
			String Break =null;
			String hoursWorked =null;
			for(String key1: obj1.keySet()){
				if (key1 == "employeeid"){
					LinkedHashMap linkedHashMap = (LinkedHashMap) obj1.get(key1);
					Iterator<Map.Entry> itr1 =linkedHashMap.entrySet().iterator();
					while (itr1.hasNext()) {
						Map.Entry pair = itr1.next();
						System.out.println(pair.getKey() + " : " + pair.getValue());
						EmployeeID = (String) pair.getValue();
						break;
					}
				}
			}
			for(String key11: obj1.keySet()){
				if (key11 != "employeeid"){
					LinkedHashMap linkedHashMap1 = (LinkedHashMap) obj1.get(key11);
					Iterator<Map.Entry> itr11 =linkedHashMap1.entrySet().iterator();
					while (itr11.hasNext()) {
						Map.Entry pair = itr11.next();
						System.out.println(pair.getKey() + " : " + pair.getValue());
						if (pair.getKey() == "date"){
							Day = (String) pair.getValue();
						}else if (pair.getKey() == "Start Work 1"){
							StartWork1= (String) pair.getValue();
						}else if (pair.getKey() == "End Work 1"){
							EndWork1= (String) pair.getValue();
						}else if (pair.getKey() == "Start Work 2"){
							StartWork2= (String) pair.getValue();
						}else if (pair.getKey() == "End Work 2"){
							EndWork2= (String) pair.getValue();
						}else if (pair.getKey() == "Break"){
							Break= (String) pair.getValue();
						}
						else if (pair.getKey() == "hoursWorked"){
							hoursWorked= (String) pair.getValue();
						}
					}
					boolean Exits = e.CheckTimeSheet(EmployeeID,Day);
					if (Exits == true){
						boolean res = e.updateTimeSheet(EmployeeID, Day,StartWork1,EndWork1,StartWork2,EndWork2,Break,hoursWorked);
					}else {
						boolean res = e.insertTimeSheet(EmployeeID, Day,StartWork1,EndWork1,StartWork2,EndWork2,Break,hoursWorked);
					}
				}
			}
			forward = "redirect:/manageemployee?act=timesheet";
		}
		ModelAndView modelAndView =new ModelAndView(forward);
		return modelAndView;
	}

	@RequestMapping(value = {"/GetTimeSheet"}, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ArrayList<TimeSheet> execute(TimeSheetForm form,Map<String, Object> model,
								HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ParseException {
		Employee employee =new Employee();
		String empid = request.getParameter("empid");
		ArrayList<TimeSheet> timesheet = employee.getTimeSheet(empid);
		System.out.println(timesheet);
		return timesheet;
	}
}
