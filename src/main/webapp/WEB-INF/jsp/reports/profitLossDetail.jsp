<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/include/header.jsp"%>
<title><spring:message code="BzComposer.Report.AllInvoice"/></title>
<style>
table.tabla-customListOds { width: 70%; border: 1px solid rgb(207, 207, 207); margin:auto; }
table.tabla-customListOds tbody tr.odd td { background: #e1e5e9; }
table.tabla-customListOds thead tr th { font-size: 14px; }
table.tabla-customListOds tbody tr td { font-size: 14px; }
</style>
</head>
<body>
<form:form action="Customer?tabid=ProfitLossDetail" method="post" modelAttribute="customerDto">
	<div class="report-form-headerpanel" id="headerPanel">
		<table>
		   <tr>
		   <%-- 	<td><input type="button" value='<spring:message code="BzComposer.Report.btn.ModifyReport"/>' class="formbutton mar"></td> --%>
		   	<td><input type="button" value='<spring:message code="BzComposer.Report.btn.Print"/>' class="formbutton mar" onclick="printPage()"></td>
		   	<td><input type="button" value='<spring:message code="BzComposer.Report.btn.Email"/>' class="formbutton mar"></td>
		   	<td><input id="btnHeader1" type="button" value='<spring:message code="BzComposer.Report.btn.HideHeader"/>' class="formbutton mar" onclick="hideShowHeader()"></td>
		   	<td><input type="button" value='<spring:message code="BzComposer.Report.btn.Refresh"/>' class="formbutton mar"></td>
		   	
		   </tr>
		</table>
	</div>
	<div class="report-form-underheader">
	 <table>
	    	<tr>
	    		<td>
	    		  <label style="padding-right: 10px"><spring:message code="BzComposer.Report.labelDates"/></label>
	    		</td>
	    		<td>
	    		  <form:select path="datesCombo">
	    		  	<form:option value="0" ><spring:message code="BizComposer.amazonBulkMailer.DateSelect.All"/></form:option>
	    		  	<form:option value="1"><spring:message code="BizComposer.amazonBulkMailer.DateSelect.Today"/></form:option>
	    		  	<form:option value="2"><spring:message code="BizComposer.amazonBulkMailer.DateSelect.ThisWeek"/></form:option>
	    		  	<form:option value="3"><spring:message code="BizComposer.amazonBulkMailer.DateSelect.ThisWeekToDate"/></form:option>
	    		  	<form:option value="4"><spring:message code="BizComposer.amazonBulkMailer.DateSelect.ThisMonth"/></form:option>
	    		  	<form:option value="5"><spring:message code="BizComposer.amazonBulkMailer.DateSelect.ThisMonthToDate"/></form:option>
	    		  	<form:option value="6"><spring:message code="BizComposer.amazonBulkMailer.DateSelect.FiscalQuarter"/></form:option>
	    		  	<form:option value="7"><spring:message code="BizComposer.amazonBulkMailer.DateSelect.FiscalQuarterToDate"/></form:option>
	    		  	<form:option value="8"><spring:message code="BizComposer.amazonBulkMailer.DateSelect.Custom"/></form:option>
	    		  	<form:option value="9"><spring:message code="BizComposer.amazonBulkMailer.DateSelect.10Days"/></form:option>
	    		  	<form:option value="10"><spring:message code="BizComposer.amazonBulkMailer.DateSelect.30Days"/></form:option>
	    		  	<form:option value="11"><spring:message code="BizComposer.amazonBulkMailer.DateSelect.60Days"/></form:option>
	    		  </form:select>
	    		</td>
	    		<td><label style="padding-left: 15px"><spring:message code="BzComposer.sales.FromDate"/></label></td>
	    		<td><form:input path="fromDate" size="15"/></td>
	    		<td><img src="images/cal.gif" onclick="displayCalendar(document.CustomerForm.fromDate,'mm-dd-yyyy',this);" style="padding-left: 5px"></td>
	    		<td><label style="padding-left: 15px"><spring:message code="BzComposer.sales.to"/></label></td>
	    		<td><form:input path="toDate" size="15"/></td>
	    		<td><img src="images/cal.gif" onclick="displayCalendar(document.CustomerForm.toDate,'mm-dd-yyyy',this);" style="padding-left: 5px"></td>
	    		<td><label style="padding-right: 10px;padding-left: 15px"><spring:message code="BzComposer.Report.labelSortBy"/></label></td>
	    		<td>
	    		 <form:select path="sortBy">
	    		  	<form:option value="0"><spring:message code="BzComposer.Report.labelSortBy.Default"/></form:option>
	    		  	<form:option value="1"><spring:message code="BzComposer.Report.labelSortBy.Type"/></form:option>
	    		  	<form:option value="2"><spring:message code="BzComposer.Report.labelSortBy.#ID"/></form:option>
	    		  	<form:option value="3"><spring:message code="BzComposer.Report.labelSortBy.client/vendor"/></form:option>
	    		  	<form:option value="4"><spring:message code="BzComposer.Report.labelSortBy.memo"/></form:option>
	    		  	<form:option value="4"><spring:message code="BzComposer.Report.labelSortBy.amount"/></form:option>
	    		</form:select>
	    		</td>
	    	    <td><input type="button" value='<spring:message code="BzComposer.sales.Search"/>' class="formbutton mar" style="margin-left: 50px" onclick="search()"></td>
	    	</tr>
	 </table>
	</div>
<div id="printContent">	
	<div id="headerBar">
	      <h5 style="text-align: center;color: blue;padding-top: 20px" id="headerBarValue">${sessionScope.user}</h5>
	      <h6 style="text-align: center;color: blue;" id="headerBarValue"><spring:message code="BzComposer.Report.Profit&Budget.ProfitLossDetail"/></h6>
	</div>
	<div id="table-negotiations" style="overflow:auto;height:400;width:100%" class="section-border">
	<table class="tabla-customListOds" cellspacing="0" id="abc">
		<thead>
			<tr>
					<th style="padding-left: 50px"><spring:message code="BzComposer.Vendor.Type" /></th>
					<th style="padding-left: 50px"><spring:message code="BzComposer.Report.Invoice.Num" /></th>
					<th style="padding-left: 50px"><spring:message code="BzComposer.Valuation.InventoryValuation.Detail.Customer" /></th>
					<th style="padding-left: 50px"><spring:message code="BzComposer.Employee.Memo" /></th>
					<th style="padding-left: 50px"><spring:message code="BzComposer.Employee.Amount" /></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty AccountReceivable}">
				<input type="hidden" name="sListSize" id="lSize" value='${AccountReceivable.size()}" />'>
                <tr>
                    <td style="padding-left: 1px;color: blue;"><b><spring:message code="BzComposer.Report.Customer.Income"/></b></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><b><spring:message code="BzComposer.Invoice.uncategorised"/></b></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach items="${AccountReceivable}" var="curObject">
                    <tr>
                        <td style="padding-left: 50px">${curObject.type}</td>
                    <c:if test="${empty curObject.number}"><td style="padding-left: 50px">${curObject.clientVendorID}</td></c:if>
                    <c:if test="${not empty curObject.number}"><td style="padding-left: 50px">${curObject.number}</td></c:if>
                        <td style="padding-left: 50px">${curObject.firstName}</td>
                        <td style="padding-left: 50px">${curObject.memo}</td>
                        <td style="padding-left: 50px">${curObject.total}</td>
                    </tr>
                </c:forEach>
                <tr>
                 <td><b><spring:message code="BzComposer.Invoice.Total"/></b></td>
                 <td></td>
                 <td></td>
                 <td></td>
                 <td><b><u>${totalUncategorisedIncome}</u></b></td>
                </tr>
                <tr>
                 <td style="color: blue;"><b><spring:message code="BzComposer.Invoice.TotalIncome"/></b></td>
                 <td></td>
                 <td></td>
                 <td></td>
                 <td style="color: blue;"><b><u>${totalUncategorisedIncome}</u></b></td>
                </tr>
                <tr>
                 <td><b><spring:message code="BzComposer.Report.CostOfGoods"/></b></td>
                 <td></td>
                 <td></td>
                 <td></td>
                 <td></td>
                </tr>
                 <c:forEach items="${AccountPayable}" var="curObject">
                    <tr>
                        <td style="padding-left: 50px">${curObject.type}</td>
                        <td style="padding-left: 50px">${curObject.number}</td>
                        <td style="padding-left: 50px">${curObject.firstName}</td>
                        <td style="padding-left: 50px">${curObject.memo}</td>
                        <td style="padding-left: 50px">${curObject.total}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td><b><spring:message code="BzComposer.Report.TotalCOGS"/></b></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><b><u>${Total_COGS}</u></b></td>
                </tr>
                <tr>
                    <td style="color: blue;"><b><spring:message code="BzComposer.Report.GrossProfit"/></b></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td style="color: blue;"><b><u>${GrossProfit}</u></b></td>
                </tr>
			</c:if>
		</tbody>
	</table>
	</div>
</div>	
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>

<!-- Javascript begins here -->
<script type="text/javascript">
function hideShowHeader()
{
	debugger;
	document.getElementById("headerBar").style.display = "none";
	/* $("#btnHeader1").hide(); */
	document.getElementById("headerBar").style.display = "none";
	$("#btnHeader1").replaceWith("<input id='btnHeader2' type='button' value='<spring:message code='BzComposer.Report.btn.ShowHeader'/>' class='formbutton mar' onclick='ShowHeader()'>");
}
function ShowHeader()
{
	document.getElementById("headerBar").style.display = "block";
	$("#btnHeader2").replaceWith("<input id='btnHeader1' type='button' value='<spring:message code='BzComposer.Report.btn.HideHeader'/>' class='formbutton mar' onclick='hideShowHeader()'>");
}
function printPage()
{
	debugger;
	        var doc = new jsPDF("1", "pt","a2");  
	        debugger;
			 var source = $("#printContent")[0]; 
		 	 doc.fromHTML(source); 
			 doc.save($("#headerBarValue").html()+".pdf"); 
}
function search()
{
	document.forms[0].action = "Customer?tabid=ProfitLossDetail";
	document.forms[0].submit();
}
</script>
<!-- Javascript end here -->
</body>
</html>


