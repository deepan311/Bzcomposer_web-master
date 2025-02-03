<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.nxsol.bizcomposer.common.TblVendorDetail"%>
<%@ page isELIgnored="false"%>
<%@page import="java.util.Currency"%>
<%-- <%@page import="jdk.nashorn.internal.runtime.linker.JavaAdapterFactory"%> --%>
<%@page import="javax.script.ScriptEngineManager"%>
<%@page import="com.pritesh.bizcomposer.accounting.bean.TblPayment"%>
<%@page import="com.pritesh.bizcomposer.accounting.bean.TblAccount"%>
<%@page import="com.pritesh.bizcomposer.accounting.bean.TblPaymentType"%>
<%@page import="com.nxsol.bizcomposer.global.clientvendor.ClientVendor"%>
<%@page import="com.nxsol.bizcomposer.common.JProjectUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.nxsol.bizcompser.global.table.TblCategory"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.pritesh.bizcomposer.accounting.bean.ReceivableListBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
	<%@include file="/WEB-INF/jsp/include/header.jsp"%>
	<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.paidbilllisttitle"/></title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!--  Here Is the context path -->
<script>var ctx = "${pageContext.request.contextPath}";</script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style type="text/css">
div#pie { /* 	color:#05A9C5;; */
	padding: 10px 0px 20px 0px;
}

table.tabla-listados {
	width: 100%;
	border: 1px solid rgb(207, 207, 207);
	margin: 20px 0px 20px 0px;
}

table.tabla-listados thead tr th {
	font-size: .7em;
	text-align: left;
	padding: 5px 10px;
	/* 	background: rgba(5, 169, 197, 0.11); */
	border-bottom: 1px solid rgba(5, 169, 197, 0.2);
	/* 	color: #333; */
	text-shadow: #999 0px 1px 1px;
	white-space: nowrap;
}

table.tabla-listados tbody tr td {
	font-size: .8em;
	/* 	color: #666; */
	padding: 5px 0px 5px 14px;
	/* 	border-bottom: 1px solid rgb(207, 207, 207); */
	background: #fff;
	vertical-align: top;
}

#highlight {
	background-color: blue;
}

.highlight {
	background-color: #00CED1 !important;
	color: #fff
}
</style>
</head>
<body>
<div id="ddcolortabsline">&nbsp;</div>
	<form:form action="AccountReceiveble" method="post" styleId="billPayableForm">
		<div class="content1 clearfix">
			<h3 class="title1">
				<spring:message code="BzComposer.billpayable.billpayabletitle"/>
			</h3>
			<div class="border1  clearfix">
				<form>
					<div class="row">
						<div class="col-md-4">
							<label>
								<spring:message code="BzComposer.billpayable.billnumber"/>
							</label> 
							<label id="ordernumber"> </label>
							<div class="form-group row">
								<label class="col-md-4  col-form-label">
									<spring:message code="BzComposer.billpayable.payee"/>
								</label>
								<div class="col-md-8">
									<select class="form-control devCutNameDrp" id="customerName">
										<%
											ArrayList<ClientVendor> cvListForBill = (ArrayList) request.getAttribute("cvForCombo");
												for (int i = 0; i < cvListForBill.size(); i++) {
										%>
										<option value="<%=cvListForBill.get(i).getCvID()%>">
											<%
												out.println(cvListForBill.get(i).getLastName() + " " + cvListForBill.get(i).getFirstName() + "("
																+ cvListForBill.get(i).getName() + ")");
											%>
										</option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4  col-form-label">
									<spring:message code="BzComposer.billpayable.payfrom"/>
								</label>
								<div class="col-md-8">
									<select class="form-control devReceivedTypeDrp" id="receivedType" onclick="checkType()">
										<%
											ArrayList<TblAccount> accountForBill = (ArrayList) request.getAttribute("accountListForBill");
												for (int i = 1; i < accountForBill.size(); i++) {
										%>
										<option value="<%=accountForBill.get(i).getAccountID()%>" label="<%=accountForBill.get(i).getCustomerCurrentBalance() %>">
											<%
												out.println(accountForBill.get(i).getName());
											%>
										</option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4  col-form-label">
									<spring:message code="BzComposer.billpayable.amount"/>
								</label>
								<div class="col-md-8">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">
												<spring:message code="BzComposer.billpayable.dollersign"/>
											</span>
										</div>
										<label style="padding-left: 10px" id="devAmount" class="form-control">
										</label>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4  col-form-label">
									<spring:message code="BzComposer.billpayable.paymentamount"/>
								</label>
								<div class="col-md-8">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">
												<spring:message code="BzComposer.billpayable.dollersign"/>
											</span>
										</div>
										<input type="text" class="form-control devReceiveAmount" value="" width="20px" 
										id="receivedAmount">
									</div>
								</div>
							</div>
							<div class="form-group row" id="Check">
								<label class="col-md-4  col-form-label">
									<spring:message code="BzComposer.billpayable.checknumber"/>
								</label>
								<div class="col-md-8">
									<input type="text" class="form-control devCheck" id="checkNum" name="checkNum">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<label>&nbsp;</label>
							<div class="form-group row">
								<label class="col-md-4  col-form-label">
									<spring:message code="BzComposer.billpayable.date"/>
								</label>
								<%--  <html:text property="orderDate" readonly="false"></html:text>  --%>
								<div class="col-md-8 calendar-img">
									<input type="text" class="form-control devOrderDate" value=""
										style="width: 275px" name="orderDate" readonly="true" id="orderDate"> 
										<img src="${pageContext.request.contextPath}/images/cal.gif"
										class="img-fluid" alt="Responsive image"
										onclick="displayCalendar(document.ReceivableListForm.orderDate,'mm-dd-yyyy',this);">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4  col-form-label">
									<spring:message code="BzComposer.billpayable.category"/>
								</label>
								<div class="col-md-8">
									<select class="form-control devCategoryDrp" size="1" id="categoryId">
										<%
											ArrayList<TblCategory> categoryList = (ArrayList) request.getAttribute("categoryListForCombo");
												for (int i = 1; i < categoryList.size(); i++) {
										%>
										<option value="<%=categoryList.get(i).getId()%>">
											<%
												out.println(categoryList.get(i).getName() + " " + categoryList.get(i).getCategoryNumber());
											%>
										</option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4  col-form-label">
									<spring:message code="BzComposer.billpayable.paymentstatus"/>
								</label>
								<div class="col-md-8">
									<select class="form-control paymentOP" size="1" id="payStatus">
										<option value="Unpaid"><spring:message code="BzComposer.billpayable.unpaid"/></option>
										<option value="Paid"><spring:message code="BzComposer.billpayable.paid"/></option>
									</select>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-md-4  col-form-label">
									<spring:message code="BzComposer.global.memo"/>
								</label>
								<div class="col-md-8">
									<input type="text" class="form-control devMemotext" id="memo">
								</div>
							</div>
						</div>
						<!-- <div class="col-md-4">
							<div class="form-group">
								<button class="btn btn-info btn1" onclick="return save()">Save</button>
							</div>
							<div class="form-group">
								<button class="btn btn-info btn1"
									onclick="return clearTransaction()">Clear the
									Transaction</button>
							</div>
							<div class="form-group">
								<button class="btn btn-info btn1"
									onclick="return makePayment()">Make Payment</button>
							</div>
						</div> -->
					</div>
				</form>
			</div>
			<div class="content-tabs">
				<nav>
					<div class="nav nav-tabs" id="tabId" role="tablist">
						<a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
						aria-controls="nav-home" aria-selected="true" onclick="billPayableTab()">
							<spring:message code="BzComposer.billpayable.tabs.unpaidbilllist"/>
						</a> 
						<a class="nav-item nav-link active" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" 
						role="tab" aria-controls="nav-profile" aria-selected="false" onclick="">
							<spring:message code="BzComposer.billpayable.tabs.paibilllist"/>
						</a> 
						<a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" 
						role="tab" aria-controls="nav-contact" aria-selected="false" onclick="billCreationTab()">
							<spring:message code="BzComposer.billpayable.tabs.billcreation"/>
						</a>
					</div>
				</nav>
				<div class="unpaid">
					<div class="unpaid-header">
						<div class="filterbar" onclick="selectedRadio()">
							<div class="form-check form-check-inline">
					  			<input class="form-check-input" type="radio" name="inlineRadioOptions" 
					  			id="rdoBillPayments" value="option1" checked="checked" onclick="showBillPayaments()">
					  			<label class="form-check-label" for="inlineRadio1">
					  				<spring:message code="BzComposer.paidbilllist.showbillpayments"/>
				  				</label>
							</div>					
							<div class="form-check form-check-inline">
					  			<input class="form-check-input" type="radio" name="inlineRadioOptions" 
					  			id="rdoRecurrentPayments" value="option1" onclick="showRecurrent()">
					  			<label class="form-check-label" for="inlineRadio2">
					  				<spring:message code="BzComposer.paidbilllist.showrecurrentpaymentsbtn"/>
				  				</label>
							</div>
							<div class="form-check form-check-inline">
					  			<input class="form-check-input" type="radio" name="inlineRadioOptions" 
					  			id="rdoPurchaseBill" value="option2">
					  			<label class="form-check-label" for="inlineRadio3">
					  				<spring:message code="BzComposer.paidbilllist.showpurchasebillpaymentsbtn"/>
				  				</label>
							</div>
							<div class="form-check form-check-inline">
					  			<input class="form-check-input" type="radio" name="inlineRadioOptions" 
					  			id="rdoUnpaidCreditPayments" value="option2">
					  			<label class="form-check-label" for="inlineRadio4">
					  				<spring:message code="BzComposer.paidbilllist.showpaidcreditpaymentsbtn"/>
				  				</label>
							</div>
							<div class="unpaid-right" id="totalAmountLabelDiv">
								<ul>
									<li>
										<label>
											<spring:message code="BzComposer.billpayable.total"/> 
										</label>
								 		<% ArrayList<TblPayment> paid = (ArrayList) request.getAttribute("paidBillLists");
								 		ArrayList<TblPayment> recurrentTotal = (ArrayList) request.getAttribute("recurrentPaymentList");
								 		if(!paid.isEmpty())
								 		{	
								 		%>
										<label id="paidBillTotal">
											<% out.println(paid.get(paid.size() - 1).getTotalAmount()); }%>
										</label>
								 		<% if(!recurrentTotal.isEmpty()) {%>
										<label id="recurrentTotal">
											<% out.println(recurrentTotal.get(recurrentTotal.size() - 1).getTotalAmount()); }%>
										</label>
									</li>   
									<li>
										<select>
											<option>All Payments</option>
											<option>Past 1 Month</option>
											<option>Past 2 Month</option>
											<option>Past 3 Month</option>
											<option>Past 6 Month</option>
										</select>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="tab-content" id="nav-tabContent">
						<div class="tab-pane fade show active" id="nav-home" role="tabpanel" 
						aria-labelledby="nav-home-tab">
							<div class="table1" id="tblForInvoiceOrder">
								<table class="table table-bordered table-sm devAcRecDataTbl">
									<thead class="thead-light">
										<tr>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.billpayable.billnumber"/>
											</th>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.paidbilllist.payeename"/>
											</th>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.billpayable.payfrom"/> 
											</th>
											<th scope="col" class="text-right"><spring:message code="BzComposer.global.balance"/></th>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.billpayable.amount"/>
											</th>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.billpayable.checknumber"/>
											</th>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.billpayable.paymentdate"/>
											</th>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.billpayable.status"/>
											</th>
										</tr>
									</thead>
									<tbody>
									  <%
											ArrayList<TblPayment> paidBillLists = (ArrayList) request.getAttribute("paidBillLists");
												int index = 1;
												for (int i = 0; i < paidBillLists.size(); i++) {
										%> 
										<tr onclick="selectrow(<%=paidBillLists.get(i).getBillNum() + "," + index%>)">
											<td class="text-right">
												<%
													out.println(paidBillLists.get(i).getBillNum());
												%>
											</td>
											<td class="text-right"
												value="<%=paidBillLists.get(i).getCvName()%>">
												<%
													out.println(paidBillLists.get(i).getCvName());
												%>
											</td>
											<td class="text-right">
												<% out.println(paidBillLists.get(i).getAccountNameString()); %>
											</td>
											<td class="text-right">
												<%= String.format("%.2f", paidBillLists.get(i).getAmountDue()) %>
											</td>
											<td class="text-right">
                                                <%= String.format("%.2f", paidBillLists.get(i).getAmount()) %>
                                            </td>
											<td class="text-right">
												<% out.println("ToBePrinted");%>
											</td>
											<td class="text-right">
												<% out.println(paidBillLists.get(i).getDateAdded());%>
											</td>
											<td class="text-right">
												<% out.println("Processed");%>
											</td>
										</tr>
										<%
											index++;
												}
										%> 
									</tbody>
								</table>
							</div>
							 <div class="table1" id="tblRecurrentPayment">
								<table class="table table-bordered table-sm devAcRecDataTbl">
									<thead class="thead-light">
										<tr>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.billpayable.billnumber"/>
											</th>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.paidbilllist.payeename"/>
											</th>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.billpayable.payfrom"/>
											</th>
											<!-- <th scope="col" class="text-right"><spring:message code="BzComposer.global.balance"/></th> -->
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.billpayable.amount"/>
											</th>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.billpayable.checknumber"/>
											</th>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.billpayable.paymentdate"/>
											</th>
											<th scope="col" class="text-right">
												<spring:message code="BzComposer.billpayable.status"/>
											</th>
										</tr>
									</thead>
									<tbody>
									  <%
										ArrayList<TblPayment> recurrentPaymentLists = (ArrayList) request.getAttribute("recurrentPaymentList");
										int index1 = 1;
										for (int i = 0; i < recurrentPaymentLists.size(); i++) {
									%> 
										<tr onclick="selectrow(<%=recurrentPaymentLists.get(i).getBillNum() + "," + index1%>)">
											<td class="text-right">
												<%
													out.println("Recurrent Payment");
												%>
											</td>
											<td class="text-right" value="<%=recurrentPaymentLists.get(i).getCvName()%>">
												<%
													out.println(recurrentPaymentLists.get(i).getCvName());
												%>
											</td>
											<td class="text-right">
												<% out.println(recurrentPaymentLists.get(i).getAccountNameString()); %>
											</td>
											<!-- <td class="text-right">0.00</td> -->
											<td class="text-right">
												<% out.println(recurrentPaymentLists.get(i).getAmount());%>
											</td>
											<td class="text-right">
												<% out.println(recurrentPaymentLists.get(i).getCheckNumber());%>
											</td>
											<td class="text-right">
												<% out.println(recurrentPaymentLists.get(i).getDateAdded());%>
											</td>
											<td class="text-right">
												<% out.println("Processed");%>
											</td>
										</tr>
										<%
											index1++;
												}
										%> 
									</tbody>
								</table>
							</div>
							<div class="footer2">
								<button class="btn btn-info" onclick="billingInfo()" style="font-size: 14px;">
									<spring:message code="BzComposer.paidbilllist.printablechecksbtn"/>
								</button>
							</div>
						</div>
						<div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
							...
						</div>
						<div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
							...
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<script type="text/javascript">
	var billNo = -1;
	var index = -1;
	var amountToBepaid = 0.00;
	function selectrow(no , indexNumber)
	{
		debugger;
		this.billNo = no;
		this.index = indexNumber;
		
		 $("#ordernumber").text($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(2)').text());
		 $("select.devCutNameDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(3)').attr('value'));
		 $(".devReceiveAmount").val($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(6)').text());
		 $("#devAmount").text($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(7)').text());
		 $("#memo").val($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(8)').text());
		 $("select.devReceivedTypeDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(11)').attr('value'));
		 $(".devOrderDate").val($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(4)').text());
		 if($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(12)').attr('value') != 'null' || $('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(12)').attr('value') != '')
		{
			 $(".devCheck").val($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(12)').attr('value'));
		}
	}
	function save()
	{
		debugger;
		var billNo = document.getElementById("ordernumber").innerHTML;
		var payerIdSelect = document.getElementById("receivedType");
		var payerID = payerIdSelect.options[payerIdSelect.selectedIndex].value;
		var paidAmount = document.getElementById("receivedAmount").value;
		amountToBepaid  = document.getElementById("receivedAmount").value;
		var checkNo = document.getElementById("checkNum").value;
		var customerName = document.getElementById("customerName");
		var vendorId = customerName.options[customerName.selectedIndex].value; 
		
		var dueDate = document.getElementById("orderDate").value;
		var categoryIdString  = document.getElementById("categoryId");
		var categoryId = categoryIdString.options[categoryIdString.selectedIndex].value;
		var memo = document.getElementById("memo").value;
		
		if(checkNo == '' || checkNo == '0')
		{
			alert("<spring:message code='BzComposer.billpayable.entervalidchecknumber'/>");
			return false;
		}
		TblVendorDetail = {
				"billNo": billNo,
				"vendorID": vendorId,
				"payerId": payerID,
				"amount": paidAmount,
				"checkNo" : checkNo,
				"dueDate": dueDate,
				"categoryID": categoryId,
				"memo": memo,
		};
		var obj=JSON.stringify(TblVendorDetail);
		$.ajax({
				
				type : "POST",
				url : "billPayablePost?tabid=save",
			    data :"data=" + obj,
			    success : function(data) {
				debugger;
				updateBillPayableTab(data);	
				},
				 error : function(data) {
					 alert("<spring:message code='BzComposer.billpayable.someerroroccurred'/>");
				} 
			});
	  	
	  	$(document.forms[0]).submit(function( event ) {
		    event.preventDefault();
		});
		
	}
	function makePayment()
	{
		debugger;
		var paidAmount;
		var amountPaid;
		var billNo = document.getElementById("ordernumber").innerHTML;
		var payerIdSelect = document.getElementById("receivedType");
		var payerID = payerIdSelect.options[payerIdSelect.selectedIndex].value;
		var totalAmount = $('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(7)').text();
		/*  if(!$('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(5)').text() == '')
		{	
			paidAmount = $('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(5)').text();
		}
		else
		{
			paidAmount = document.getElementById("receivedAmount").value;
		}
		if(parseFloat(paidAmount) > parseFloat(totalAmount))
		{
			alert("<bean:message key='BzComposer.common.cantPayMoreThanBalance'/>");
			return false;
		}  */
		
		if(amountToBepaid != 0.00)
		{	
			paidAmount = amountToBepaid;
			amountPaid = parseFloat(totalAmount) - parseFloat($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(6)').text());
		}
		else
		{
			paidAmount = document.getElementById("receivedAmount").value;
			amountPaid = parseFloat(paidAmount);
		}
		var checkNo = document.getElementById("checkNum").value;
		var balance = parseFloat(totalAmount) - parseFloat(amountPaid);
		var customerName = document.getElementById("customerName");
		var vendorId = customerName.options[customerName.selectedIndex].value; 
		var amount = document.getElementById("devAmount").innerHTML;
		var dueDate = document.getElementById("orderDate").value;
		var categoryIdString  = document.getElementById("categoryId");
		var categoryId = categoryIdString.options[categoryIdString.selectedIndex].value;
		var memo = document.getElementById("memo").value;
		
		if(checkNo == '' || checkNo == '0')
		{
			alert("<spring:message code='BzComposer.billpayable.entervalidchecknumber'/>");
			return false;
		}
		TblVendorDetail = {
				"billNo": billNo,
				"vendorID": vendorId,
				"payerId": payerID,
				"amountPaid": amountPaid,
				"amountTopay": paidAmount,
				"balance": balance,
				"amount": amount,
				"checkNo" : checkNo,
				"dueDate": dueDate,
				"categoryID": categoryId,
				"memo": memo,
		};
		var obj=JSON.stringify(TblVendorDetail);
		$.ajax({
			
			type : "POST",
			url : "billPayablePost?tabid=MakePayment",
		    data :"data=" + obj,
		    success : function(data) {
			debugger;
			amountToBepaid = 0.00;
			updateBillPayableTab(data);	
			},
			 error : function(data) {
				 alert("<spring:message code='BzComposer.billpayable.someerroroccurred'/>");
			} 
		});
  	
  	$(document.forms[0]).submit(function( event ) {
	    event.preventDefault();
	});
	}
	function showRecurrent()
	{
		$("#tblRecurrentPayment").show();
		$("#tblForInvoiceOrder").hide();
		$("#recurrentTotal").show();
		$("#paidBillTotal").hide();
	}
	function showBillPayaments()
	{
		$("#tblRecurrentPayment").hide();
		$("#tblForInvoiceOrder").show();
		$("#recurrentTotal").hide();
		$("#paidBillTotal").show();
	}
		$(document).ready(
				function() {
					//debugger;
					var day = new Date().getDay();
					var dName = dayName(day);
					$("#tblForUnpaidOpeningBalance").hide();
					$("#tblForUnpaidCreditAmount").hide();
					$("#orderDate").val(
							dName + " " + ((new Date().getMonth()) + 1) + "-"
									+ new Date().getDate() + "-"
									+ new Date().getFullYear());
					$("#tblRecurrentPayment").hide();
					$("#recurrentTotal").hide();
				});
		 function dayName(date) {
			 
		       var days = new Array(31);
		       var j = 0;
		       var d  = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
		       for(var i=0;i<=31;i++)
		    	   {
		    	   		days[i] = d[j];
		    	   		j++;
		    	   		if(j == 7)
		    	   			{
		    	   				j = 0;
		    	   			}
		    	   }
		        var d = days[date];
		     	return d;
		   }
		 $(document).ready(function () {
			    $('tr').click(function () {
			         var selected = $(this).hasClass("highlight"); 
			         $("tr").removeClass("highlight");
			         if(!selected)
			             $(this).addClass("highlight");
			    });
			}); 
		 function addCss()
		   {
			   $(document).ready(function () {
				    $('tr').click(function () {
				         var selected = $(this).hasClass("highlight"); 
				         $("tr").removeClass("highlight");
				         if(!selected)
				             $(this).addClass("highlight");
				    });
				}); 
			   	var day = new Date().getDay();
				var dName = dayName(day);
			   	$("#orderDate").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear());
		   }
		function updateBillPayableTab(data)
		{
			debugger;
			$("#billPayableForm")[0].reset();
			$(document).find('div#tblForInvoiceOrder table').replaceWith($(data).find('div#tblForInvoiceOrder').html());
			$(document).find('div#totalAmountLabelDiv label').eq(1).text(this.value).replaceWith($(data).find('div#totalAmountLabelDiv label').eq(1).text(this.value));
			addCss();
		}
		function billPayableTab()
		{
			window.location = "${pageContext.request.contextPath}/BillPayable?tabid=billpayable";
		}
		function billCreationTab()
		{
			window.location = "${pageContext.request.contextPath}/BillCreation?tabid=billCreation";
		}
	</script>
</body>
</html>