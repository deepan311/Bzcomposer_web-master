<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@	page
	import="com.avibha.bizcomposer.employee.dao.Employee,com.avibha.bizcomposer.employee.dao.TimeSheet,java.util.ArrayList"%>

<%String id = request.getParameter("id");
			String d = request.getParameter("dates");
			String days[] = { "Monday", "Tuesday", "Wednesday", "Thursday",
					"Friday", "Saturday", "Sunday" };
			String data = "";
			int cnt1 = 0;
			int cnt2 = 0;
			int len = 0;
			if (id != null || !"".equals(id.trim())) {
				String dates[] = d.split(",");
				Employee e = new Employee();
				ArrayList arr = e.getTimeSheet(id, dates[0], dates[6]);
				System.out.println(arr.size());
				len = arr.size();

				for (cnt1 = 0; cnt1 < len; cnt1++) {
					TimeSheet t = (TimeSheet) arr.get(cnt1);
					console.log(t)
					data = data + t.getStartWork() + "#" + t.getLunchTimeOut()
							+ "#" + t.getLunchTimeIn() + "#" + t.getEndWork()
							+ "$";

				}

				for (cnt2 = len; cnt2 < 7; cnt2++) {
					data = data + "-" + "#" + "-" + "#" + "-" + "#" + "-" + "$";
				}
			}

			%>
<input type="hidden" name="tsdata" id="tsdata" value="<%=data%>">

<%System.out.println("data" + data);

		%>
