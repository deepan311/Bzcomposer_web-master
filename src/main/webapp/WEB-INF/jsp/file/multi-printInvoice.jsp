<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><spring:message code="menu.file.MultiPrintInvoice"/></title>
<link href="${pageContext.request.contextPath}/dist/css/custom.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/custom.js"></script>
<%@include file="/include/header.jsp"%>
</head>
<body>
<form:form action="File?tabid=MultiPrintInvoice" method="post" modelAttribute="companyInfoDto">
	<!-- Tab links -->
<div class="bca_multiprintwindow">
  	<ul>
  		<li id="mpw_tab1"><spring:message code="BzComposer.multiprintforms.general"/></li>
  		<li id="mpw_tab2"><spring:message code="BzComposer.multiprintforms.paper"/></li>
  	</ul>
</div>
<div class="mpw_tab1_details">
	<div class="mpw_tab1_details_left">	
		<div class="tab1_nuberpofpages">
			<label><b><spring:message code="BzComposer.multiprintforms.numberofpages"/></b></label>
			<br>
			<label><spring:message code="BzComposer.multiprintforms.invoicenumber"/></label>
			<form:input path="invoiceNo"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label><spring:message code="BzComposer.multiprintforms.packingslipnumber"/></label>
			<form:input path="packingSlipNo"/>
		</div>	
		<div class="tab1_print">
			<input type="radio" name="p" id="p1" value="print"> 
				<label><b><spring:message code="BzComposer.multiprintforms.printlabel"/></b></label>
				<br>
				<label><spring:message code="BzComposer.multiprintforms.printfrom"/></label>
				<form:input path="printInvoiceFrom"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label><spring:message code="BzComposer.multiprintforms.printto"/></label>
				<form:input path="printInvoiceTo"/>
		</div>
		<div class="tab1_print">
			<input type="radio" name="p" id="p2" value="print" checked="checked"> 
				<label><b><spring:message code="BzComposer.multiprintforms.printbydatetime"/></b></label>
				<br>
				<label><spring:message code="BzComposer.multiprintforms.datefrom"/></label>
				<form:input path="fromDate" size="15"/>
				<img src="${pageContext.request.contextPath}/images/cal.gif" onclick="displayCalendar(document.CompanyInfoForm.fromDate,'mm-dd-yyyy',this);">
				<label>
					<spring:message code="BzComposer.multiprintforms.dateto"/>
				</label>
				<form:input path="toDate" size="15"/>
				<img src="${pageContext.request.contextPath}/images/cal.gif" onclick="displayCalendar(document.CompanyInfoForm.toDate,'mm-dd-yyyy',this);">
		</div>
	</div>
	<div class="mpw_tab1_details_right">	
		<div class="tab1_sortoption">
			<label>
				<b><spring:message code="BzComposer.multiprintforms.sortoption"/></b>
			</label>
			<br>
			<table>
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th><spring:message code="BzComposer.multiprintforms.priority"/></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<input type="checkbox" value="Shipping Method" checked="checked"><spring:message code="BzComposer.multiprintforms.shippingmethod"/>
						</td>
						<td><input type="text" size="1"></td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" value="Item Title" checked="checked"><spring:message code="BzComposer.multiprintforms.itemtitle"/>
						</td>
						<td><input type="text" size="1"></td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" value="Destination" checked="checked"><spring:message code="BzComposer.multiprintforms.destination"/>
						</td>
						<td><input type="text" size="1"></td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" value="Special Handing" checked="checked"><spring:message code="BzComposer.multiprintforms.specialhandling"/>
						</td>
						<td><input type="text" size="1"></td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" value="Location" checked="checked"><spring:message code="BzComposer.multiprintforms.location"/>
						</td>
						<td><input type="text" size="1"></td>
					</tr>
				</tbody>
			</table>	
		</div>
		<div class="tab1_options">
			<label>
				<b><spring:message code="BzComposer.multiprintforms.options"/></b>
			</label>
			<br>
			<table>
				<tbody>
					<tr>
						<td>
							<input type="checkbox" value="Skip if already printed" checked="checked"><spring:message code="BzComposer.multiprintforms.skipifprinted"/>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" value="Print test page first." checked="checked"><spring:message code="BzComposer.multiprintforms.printtestpage"/>
						</td>
					</tr>
				</tbody>
			</table>	
		</div>
	</div>
</div>
<div class="mpw_tab2_details">
</div>
<div class="mpw_button_bottom">
	<ul>
		<li><button type="submit" class="formbutton"><spring:message code="BzComposer.multiprintforms.print"/></button></li>
		<li><button type="button" class="formbutton" onclick="CloseMe();"><spring:message code="BzComposer.global.close"/></button></li>
	</ul>
</div>
</form:form>
</body>
<script type="text/javascript">
function CloseMe(){
    window.close();
}
</script>
</html>