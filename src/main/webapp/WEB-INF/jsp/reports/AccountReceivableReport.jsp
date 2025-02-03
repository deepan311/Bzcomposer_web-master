<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/include/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${sessionScope.user}-<spring:message code="BzComposer.Report.Banking&Accounting.ARInvDetail"/></title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<%-- <script src="tableStyle/js/jquery.min.js"></script> --%>
<style>
input, textarea, select {
	display: inline-block; margin-bottom: 3px; line-height: 13px; border: 1px solid #cccccc;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px; margin-top: 3px
}

table.tabla-listados { width: 90%; border: 1px solid rgb(207, 207, 207); margin:auto; }
table.tabla-listados tbody tr.odd td { background: #e1e5e9; }
table.tabla-listados thead tr th { font-size: 14px; }
table.tabla-listados tbody tr td { font-size: 14px; }
</style>
<script type="text/javascript">
$(function() {
    $("#tabs").tabs();
});
</script>
</head>
<body>
	<form:form action="AccountReceivableAR?tabid=AccontReceivableReport" method="post" modelAttribute="invoiceDto">
		<div id="cos">
			<%-- <label style="margin-right: 5px">Date</label>
			<form:select path="sortingElement">
				<form:option value="">All</form:option>
			</form:select> --%>
			<%-- <div style="float: right;">
				<label>From</label>
				<form:input path="fromDate" />
				<img src="images/cal.gif" onclick="displayCalendar(document.InvoiceForm.fromDate,'mm-dd-yyyy',this);" />
				<label>To</label>
				<form:input path="toDate" />
				<img src="images/cal.gif" onclick="displayCalendar(document.InvoiceForm.toDate,'mm-dd-yyyy',this);" />
				<input type="button" class="formbutton" name="AddItemBtn" value="Refresh" onclick="callRefresh();" title="Refresh Item" id="refresh"></td>
			</div> --%>
			
			
			<div class="statusquo ok">
				<div id="hoja">
					<div id="blanquito">
						<div id="padding">

							<div id="tabs" style="float: left;width: 100%">
								<ul class="uiTabs">
									<li><a href="#AR-InvoiceDetail" data-toggle="tab"><spring:message code="BzComposer.Report.Banking&Accounting.ARInvDetail" /></a></li>
									<li><a href="#AR-CustomerDetail" data-toggle="tab"><spring:message code="BzComposer.Report.Banking&Accounting.ARCustDetail" /></a></li>
									<li><a href="#Graph" data-toggle="tab"><spring:message code="BzComposer.Report.Banking&Accounting.Graph" /></a></li>
								</ul>
								<div id="AR-InvoiceDetail">
									<div id="content1" class="tabPage" style="overflow:auto;height: 500px">
	<%-- <div class="report-form-underheader">
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
	    		<td><img src="images/cal.gif" onclick="displayCalendar(document.InvoiceForm.fromDate,'mm-dd-yyyy',this);" style="padding-left: 5px"></td>
	    		<td><label style="padding-left: 15px"><spring:message code="BzComposer.sales.to"/></label></td>
	    		<td><form:input path="toDate" size="15"/></td>
	    		<td><img src="images/cal.gif" onclick="displayCalendar(document.InvoiceForm.toDate,'mm-dd-yyyy',this);" style="padding-left: 5px"></td>
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
	    	    <td><input type="button" value='<spring:message code="BzComposer.sales.Search"/>' class="formbutton mar" style="margin-left: 195px" onclick="search()"></td>
	    	</tr>
	 </table>
	</div> --%>
            <div id="table-negotiations">
                <table class="tabla-listados" cellspacing="0" style="overflow:-y:auto">
                    <thead>
                        <tr>
                            <th colspan="9"><spring:message code="BzComposer.Vendor.Type" /></th>
                            <th colspan="9"><spring:message code="BzComposer.sales.SaleDate" /></th>
                            <th colspan="9"><spring:message code="BzComposer.Invoice.Number" /></th>
                            <th colspan="9"><spring:message code="BzComposer.Email.Name" /></th>
                            <th colspan="9"><spring:message code="BzComposer.datamanager.terms" /></th>
                            <th colspan="9"><spring:message code="BizComposer.Configuration.Reminders.DueDate" /></th>
                            <th colspan="9"><spring:message code="BizComposer.Configuration.Reminders.OverdueDays" /></th>
                            <th colspan="9"><spring:message code="BzComposer.datamanager.paymentmethod" /></th>
                            <th colspan="9"><spring:message code="BzComposer.Report.Receivable" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${not empty invoiceDetail}">
                            <input type="hidden" name="sListSize" id="lSize" value='${invoiceDetail.size()}'>
                            <c:forEach items="${invoiceDetail}" var="curObject">
                                <tr>
                                    <td colspan="9">${curObject.invoiceType}</td>
                                    <td colspan="9">${curObject.dateAdded}</td>
                                    <td colspan="9">${curObject.invoiceId}</td>
                                    <td colspan="9">${curObject.firstName},${curObject.lastName}</td>
                                    <td colspan="9">${curObject.term}</td>
                                    <td colspan="9">${curObject.dueDate}</td>
                                    <td colspan="9">${curObject.pastDays}</td>
                                    <td colspan="9">${curObject.paymentTypeName}</td>
                                    <td colspan="9">${curObject.balance}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
            <div id="AR-CustomerDetail" style="float: left;width: 100%">
                <div id="content1" class="tabPage" style="overflow:auto;height: 500px">
                    <div id="table-negotiations">
                        <table class="tabla-listados" cellspacing="0">
                            <thead>
                                <tr>
                                    <th colspan="9"><spring:message code="BzComposer.Companyinformation.FirstName" /></th>
                                    <th colspan="9"><spring:message code="BzComposer.Companyinformation.LastName" /></th>
                                    <th colspan="9"><spring:message code="BzComposer.Vendor.CompanyName" /></th>
                                    <th colspan="9"><spring:message code="BzComposer.Invoice.TotalInv" /></th>
                                    <th colspan="9"><spring:message code="BzComposer.Invoice.TotalNoRec" /></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${not empty custDetail}">
                                    <input type="hidden" name="sListSize" id="lSize" value='${custDetail.size()}'>
                                    <c:forEach items="${invoiceDetail}" var="curObject">
                                        <tr>
                                            <td colspan="9">${curObject.firstName}</td>
                                            <td colspan="9">${curObject.lastName}</td>
                                            <td colspan="9">${curObject.companyName}</td>
                                            <td colspan="9">${curObject.totalInvoices}</td>
                                            <td colspan="9">${curObject.balance}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div id="Graph">
                <div id="content1" class="tabPage">
                    <!-- <div id="table-negotiations"> -->
                    <table class="tabla-listados-noborder" cellspacing="0">
                        <tr>
                            <td style="width: 300px">
                                <ul>
                                    <li style="cursor: pointer"><spring:message code="BzComposer.Report.AgingSum" /></li>
                                    <li style="cursor: pointer"><spring:message code="BzComposer.Report.ChartSum" /></li>
                                </ul>
                            </td>
                            <td><label>As of</label> <form:input path="date" size="15" value="" />
                                <img src="images/cal.gif" onclick="displayCalendar(document.InvoiceForm.date,'mm-dd-yyyy',this);" />
                                <table class="tabla-listados">
                                    <thead>
                                        <th colspan="9"><spring:message code="BzComposer.sales.Customer" /></th>
                                        <th colspan="9"><spring:message code="BzComposer.Report.Current" /></th>
                                        <th colspan="9"><spring:message code="BzComposer.Report.1-30" /></th>
                                        <th colspan="9"><spring:message code="BzComposer.Report.31-60" /></th>
                                        <th colspan="9"><spring:message code="BzComposer.Report.61-90" /></th>
                                        <th colspan="9"><spring:message code="BzComposer.Report.G90" /></th>
                                        <th colspan="9"><spring:message code="BzComposer.Invoice.Total" /></th>
                                    </thead>
                                </table></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="btn-center">
            <input type="button" class="formbutton" name="AddItemBtn" value="Look Up" onclick="callRefresh();" title="Refresh Item">
            <input type="button" class="formbutton" name="AddItemBtn" value="Print" onclick="callRefresh();" title="Refresh Item">
        <div style="float: right;">
            <label>Sum of Receivable@</label>
        <input type="text" name="totalBalance" id="lSize" readonly="true" value='${totalBalance}'>
        </div>
        </div>

        </div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
<script type="text/javascript">
$("#tabs").tabs({
    change: function(event, ui) {

    }
});
function callRefresh()
{
	debugger;
	document.forms[0].action = "AccountReceivableAR.do?tabid=AccontReceivableReport";
	document.forms[0].submit();
}
function search()
{
	document.forms[0].action = "AccountReceivableAR.do?tabid=AccontReceivableReport";
	document.forms[0].submit();
}
</script>	
</body>
</html>