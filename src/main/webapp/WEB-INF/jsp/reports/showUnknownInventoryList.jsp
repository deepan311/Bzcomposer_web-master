<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/include/header.jsp"%>
<title>${sessionScope.user} - <spring:message code="BzComposer.Report.Unknown.InventoryList"/></title>
<style>
table.tabla-customListOds { width: 80%; border: 1px solid rgb(207, 207, 207); margin:auto; }
table.tabla-customListOds tbody tr.odd td { background: #e1e5e9; }
table.tabla-customListOds thead tr th { font-size: 14px; }
table.tabla-customListOds tbody tr td { font-size: 14px; }
</style>
</head>
<body>
<form:form action="Item?tabid=showUnknownInventoryList" method="post" modelAttribute="itemDto">
	<div class="report-form-headerpanel" id="headerPanel">
		<table>
		   <tr>
		   <%--  <td><input type="button" value='<spring:message code="BzComposer.Report.btn.ModifyReport"/>' class="formbutton mar"></td>
		   	<td><input type="button" value='<spring:message code="BzComposer.Report.btn.Print"/>' class="formbutton mar" onclick="printPage()"></td>
		   	<td><input type="button" value='<spring:message code="BzComposer.Report.btn.Refresh"/>' class="formbutton mar" onclick="search()"></td> --%>
		   	<td><input type="button" value='<spring:message code="BzComposer.Report.btn.Email"/>' class="formbutton mar" onclick="sendMail()" id="email"></td>
		   	<td><input id="btnHeader1" type="button" value='<spring:message code="BzComposer.Report.btn.HideHeader"/>' class="formbutton mar" onclick="hideShowHeader()"></td>
		   	<td><input type="button" value='<spring:message code="BzComposer.Report.btn.Refresh"/>' class="formbutton mar" onclick="search()"></td>
		   </tr>
		</table>
	</div>
	<div id="ddcolortabsline">&nbsp;</div>

	<!--search  -->
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
	    		<td><img src="images/cal.gif" onclick="displayCalendar(document.ItemForm.fromDate,'mm-dd-yyyy',this);" style="padding-left: 5px"></td>
	    		<td><label style="padding-left: 15px"><spring:message code="BzComposer.sales.to"/></label></td>
	    		<td><form:input path="toDate" size="15"/></td>
	    		<td><img src="images/cal.gif" onclick="displayCalendar(document.ItemForm.toDate,'mm-dd-yyyy',this);" style="padding-left: 5px"></td>
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
	<!-- search -->
	<div id="headerBar">
          <h5 style="text-align: center;color: blue;padding-top: 20px">${sessionScope.user}</h5>
          <h6 style="text-align: center;color: blue;" id="headerBarValue"><spring:message code="BzComposer.Report.Unknown.InventoryList"/></h6>
    </div>
	<table align="center">
		<tr>
			<td align="center"><c:if test="${not empty msg}">
				<font color="red"><b>${msg}</b></font>
			</c:if></td>
		</tr>
	</table>
		<%-- <span
		style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.sales.InvoiceBoard.InvoiceList" /></span> --%>
	<div id="table-negotiations" style="overflow: auto; height: 400; text-align: center;">
    <table class="tabla-customListOds" id="exportPd" border="1">
		<thead>
			<tr>
				<th><spring:message code="BzComposer.Report.InventoryId" /></th>
				<th><spring:message code="BzComposer.Report.ReservedInventoryList.InventoryCode" /></th>
				<th><spring:message code="BzComposer.Report.ReservedInventoryList.OldQty" /></th>
				<th><spring:message code="BzComposer.Report.ReservedInventoryList.NewQty" /></th>
				<th><spring:message code="BzComposer.Report.ItemInventory.Gap" /></th>
				<th><spring:message code="BzComposer.Employee.Memo" /></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty damagesItemList}">
				<input type="hidden" name="sListSize" id="lSize" value='${damagesItemList.size()}'>
                <c:forEach items="${damagesItemList}" var="item">
                    <tr>
                        <td align="left">${item.inventoryId}</td>
                        <td align="left">${item.inventoryCode}</td>
                        <td align="left">${item.oldQty}</td>
                        <td align="left">${item.newQty}</td>
                        <td align="left">${item.gap}</td>
                        <td align="left">${item.memo}</td>
                    </tr>
                </c:forEach>
			</c:if>
		</tbody>
	</table>
	</div>
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
<%@ include file="/include/emailModal.jsp"%>
</body>
</html>

<script>
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
function search()
{
	document.forms[0].action = "Item?tabid=showUnknownInventoryList";
	document.forms[0].submit();
}

function sendMail() {
	modal.style.display = "block";
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
}
function closeModal()
{
	modal.style.display = "none";
}
var modal = document.getElementById('myModal');
</script>