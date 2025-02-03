<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/include/header.jsp"%>
<title><spring:message code="BzComposer.unpaidinvoicelisttitle"/></title>
<style>
table.tabla-listados { width: 100%; border: 1px solid rgb(207, 207, 207); margin:auto; }
table.tabla-listados tbody tr.odd td { background: #e1e5e9; }
table.tabla-listados thead tr th { font-size: 14px; }
table.tabla-listados tbody tr td { font-size: 14px; }
</style>
</head>
<body>
<form:form action="SalesBord?tabid=UnPaidInvoiceList&ilist=3" method="post" modelAttribute="salesBoardDto">
<div id="">
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div>
	<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; 
	border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.report.unpaidinvoicelist.unpaidinvoices" />
	</span>
</div>
<div>
	<div id="table-negotiations">
		<table cellspacing="0" align="center" class="section-border" style="width: 100%;">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr align="center">
				<td align="left">
					<c:if test="${not empty IsUpdated}">
						<strong><span class="msgstyle"> ${IsUpdated}</span></strong>
					</c:if>
				</td>
			</tr>
			<tr>
				<td align="left" width="46%" valign="top">
					<table class="table-notifications" width="100%">
						<tr>
							<th colspan="6" align="left">
								<spring:message code="BzComposer.report.unpaidinvoicelist.filteroption" />
							</th>
						</tr>			
						<tr>
							<td width="30%">
								<spring:message code="BzComposer.reportcenter.allinvoicelist.orderdate" />
							</td>
							<td>
								<c:if test="${not empty BlankValue}">
									<form:input path="orderDate1"  size="15" value="" />
								</c:if>
								<c:if test="${empty BlankValue}">
									<form:input path="orderDate1"  size="15" />
								</c:if>
							</td>
							<td align="left">
								<img src="images/cal.gif"
								onclick="displayCalendar(document.salesboardForm.orderDate1,'mm-dd-yyyy',this);" />
							</td>
							<td align="left">
								<spring:message code="BzComposer.reportcenter.allinvoicelist.to" /> 
								<c:if test="${not empty BlankValue}">
									<form:input path="orderDate2"  size="15" value="" />
								</c:if>
								<c:if test="${empty BlankValue}">
									<form:input path="orderDate2"  size="15" />
								</c:if>
							</td>
							<td align="left">
								<img src="images/cal.gif"
								onclick="displayCalendar(document.salesboardForm.orderDate2,'mm-dd-yyyy',this);">
							</td>
						</tr>
					</table>
				</td>
				<td width="27%" valign="top">
					<table class="table-notifications" width="100%">		
						<tr>
							<td colspan="2" align="center">
								<input type="submit" class="formbutton" name="findBtn" 
								value="<spring:message code="BzComposer.reportcenter.allinvoicelist.searchbtn"/>">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div>
			<table align="center">
				<tr>
					<td align="center">
						<c:if test="${not empty msg}">
							<font color="red"><b>${msg}</b></font>
						</c:if>
					</td>
				</tr>
			</table>	
			<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; 
			border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
				<spring:message code="BzComposer.reportcenter.allinvoicelist.invoicelist" />
			</span>
			<div id="table-negotiations" style="overflow:auto;height:500;width:100%" class="section-border">
				<table class="tabla-listados" cellspacing="0">
					<thead>
						<tr>
							<th><spring:message code="BzComposer.reportcenter.allinvoicelist.invoicenumber" /></th>
							<th><spring:message code="BzComposer.reportcenter.allinvoicelist.orderdate" /></th>
							<th><spring:message code="BzComposer.reportcenter.allinvoicelist.sortby.clientorvendor" /></th>
							<th><spring:message code="BzComposer.reportcenter.allinvoicelist.representative" /></th>
							<th><spring:message code="BzComposer.reportcenter.allinvoicelist.salesamount" /></th>
							<th><spring:message code="BzComposer.reportcenter.allinvoicelist.balance" /></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty SalesBoardDetails}">
                            <input type="hidden" name="sListSize" id="lSize" value='${SalesBoardDetails.size()}'>
                            <c:forEach items="${SalesBoardDetails}" var="sale">
                                <tr>
                                    <td align="left">${sale.orderNum}</td>
                                    <td align="left">${sale.dateAdded}</td>
                                    <td align="left">${sale.lastName},${sale.firstName}</td>
                                    <td align="left">${sale.rep}</td>
                                    <td align="left">${sale.total}</td>
                                    <td align="left">${sale.balance}</td>
                                </tr>
                            </c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>