<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%@ page isELIgnored="false"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Currency"%>
<%@page import="jdk.nashorn.internal.runtime.linker.JavaAdapterFactory"%>
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
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.accountreceivabletitle"/></title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

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
	 
.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    float: none;
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {
    background-color: #ddd;
}

.dropdown:hover .dropdown-content {
    display: block;
}	
.input-group-text:hover {
	text-decoration: none
}
.highlight { background-color: #00CED1 !important;color: #fff }
</style>

</head>
<body>
<% int find = 0; 
%>
<div id="ddcolortabsline">&nbsp;</div>
 <html:form action="AccountReceiveble" method="post" styleId="receivedForm">
	<div class="content1 clearfix">
		<h3 class="title1">
		    <spring:message code="BzComposer.accountreceivable.accountreceivabletitle"/>
		</h3>
		 <% ReceivableListBean receivablelistbean=(ReceivableListBean)request.getAttribute("Selectedrow");
		 	TblPaymentType payment = (TblPaymentType)request.getAttribute("SelectedPayment");
		 	TblAccount Selectedaccount = (TblAccount)request.getAttribute("SelectedAccount");
		 	TblCategory SelectedCategory = (TblCategory)request.getAttribute("SelectedCategory");
		 	TblPayment SelcetedPaymentForCheck = (TblPayment)request.getAttribute("Payment");
		 	if(request.getSession().getAttribute("invoiceId") != null)
		 	{
		 		find = Integer.parseInt(request.getSession().getAttribute("invoiceId").toString());
		 	}
		 	
		 %>
		 
		<div class="border1  clearfix">
			<form>
				<div class="row">
					<div class="col-md-4">
						<label><spring:message code="BzComposer.sales.Order"/>:</label>
						 <label id="ordernumber"></label>
						<div class="form-group row">
							<label class="col-md-4  col-form-label"><spring:message code="BzComposer.accountreceivable.customer"/></label>
							<div class="col-md-8">
								<select class="form-control devCutNameDrp" id="customerName" >
								<%
									ArrayList<ClientVendor> cv = (ArrayList)request.getAttribute("ClineVendorForCombo");
								/* 	Iterator<ClientVendor> itr1 = cv.iterator(); */
									for(int i=0;i<cv.size();i++)
									{
								%>  
                               			<option value=<%=cv.get(i).getCvID() %>><% out.println(cv.get(i).getName()+"("+cv.get(i).getFirstName()+" "+cv.get(i).getLastName()+")"); %></option>			            	 
									
								<% } %>	
								</select>
							</div>
						</div>
						
						<div class="form-group row">
							<label class="col-md-4  col-form-label">
							    <spring:message code="BzComposer.accountreceivable.receivedtype"/>
							</label>
							<div class="col-md-8">
								<select class="form-control devReceivedTypeDrp" id="receivedType" onclick="checkType()">
								<%-- <%if(payment!=null) 
								{ %>
								 <option selected="selected"><% out.println(payment.getTypeName()); } else{ %> --%>
								<%	
								ArrayList<TblPaymentType> payType = (ArrayList)request.getAttribute("PaymentTypeForCombo");
								/* Iterator<TblPaymentType> itr3 = payType.iterator(); */
								for(int i=0;i<payType.size();i++)
								{
								%>
									<option value=<%= payType.get(i).getId() %> id="<%= payType.get(i).getId()%>"><%  out.println(payType.get(i).getTypeName()); %></option>
							<%  } 
								 
								%>	
						
								</select>
							</div>
						</div>
						 <div class="form-group row">
						<label class="col-md-4  col-form-label">
						    <spring:message code="BzComposer.accountreceivable.amount"/>
						</label>
				    
				    	<div class="col-md-8">	
				    	<div class="input-group">
							<div class="input-group-prepend">
							 <span class="input-group-text" id="basic-addon1">$</span>
							 </div>
								<label  style="padding-left: 10px" id="devAmount" class="form-control"></label>
								</div>
						</div>
						</div>
						 <div class="form-group row">
							<label class="col-md-4  col-form-label">
							    <spring:message code="BzComposer.accountreceivable.receivedamount"/>
							</label>
							 
					<div class="col-md-8">
						<div class="input-group">
							<div class="input-group-prepend">
							 <span class="input-group-text" id="basic-addon1">$</span>
							  </div>
							<input type="text" class="form-control devReceiveAmount" value="" width="20px" id="receivedAmount">
							</div>
						
					</div>
						</div> 
				<script>
					  var data = document.getElementById("Check");
					  data.style.display = "none";
				</script>
				<div class="form-group row" id="Check">
							<label class="col-md-4  col-form-label">
							    <spring:message code="BzComposer.accountreceivable.checknumber"/>
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
							    <spring:message code="BzComposer.popayable.paiddate"/>
							</label>
						   <%--  <html:text property="orderDate" readonly="false"></html:text>  --%>
						    <div class="col-md-8 calendar-img"><input type="text" class="form-control devOrderDate" value="" style="width: 275px" name="orderDate" readonly="true" id="orderDate">
							<img
								src="${pageContext.request.contextPath}/images/cal.gif" class="img-fluid" alt="Responsive image"
								onclick="displayCalendar(document.ReceivableListForm.orderDate,'mm-dd-yyyy',this);">
							</div>   
							
						
						</div>
						
						<div class="form-group row">
							<label class="col-md-4  col-form-label">
							    <spring:message code="BzComposer.accountreceivable.depositto"/>
							</label>
							<div class="col-md-8">
								<select class="form-control devDeposittypeDrp" id="depositId">
									
								<% ArrayList<TblAccount> account = (ArrayList)request.getAttribute("AccountForCombo");
									for(int i=0;i<account.size();i++)
									{
								%>	
									<option value="<%= account.get(i).getAccountID()%>" id="<%= account.get(i).getAccountID()%>"><% out.println(account.get(i).getName());  %></option>
								<%  } %>	
								
								</select>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-4  col-form-label">
							    <spring:message code="BzComposer.accountreceivable.category"/>
							</label>
							<div class="col-md-8">
								<select class="form-control devCategoryDrp" size="1" id="categoryId">
								<%
									ArrayList<TblCategory> category = (ArrayList)request.getAttribute("CategoryCombo");
							/* 		Iterator<TblCategory> itr2 = category.iterator(); */
									for(int i=0;i<category.size();i++)
									{
								%>
								<option value="<%= category.get(i).getId() %>" id="<%= category.get(i).getId() %>"><% out.println(category.get(i).getName() + " " +category.get(i).getCategoryNumber()); %></option>	
								 <%} %>
								</select>
							</div>
						</div>
						
						<div class="form-group row">
							<label class="col-md-4  col-form-label">
							    <spring:message code="BzComposer.accountreceivable.paymentstatus"/>
							</label>
							<div class="col-md-8">
								<select class="form-control paidunpaid">
								    <option value="Unpaid"><spring:message code="BzComposer.accountreceivable.unpaid"/></option>
									<option value="Paid"><spring:message code="BzComposer.accountreceivable.paid"/></option></option>
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
					<div class="col-md-4" style="top:35px;">
						 <div class="form-group">
								<button class="btn btn-info" style="width:100px;font-size: 14px;" onclick="return save()"><spring:message code="BzComposer.global.save"/></button>
							</div>  
						<div class="form-group">
							<button class="btn btn-info" style="width:100px;font-size: 14px;" onclick="return clearTransaction()"><spring:message code="BzComposer.global.clear"/></button>
						</div> 
					</div>
				</div>
			</form>
		</div>
		
		<div class="content-tabs">
			<nav>
			   <div class="nav nav-tabs" id="tabId" role="tablist">
			    <a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true" onclick="receivableList()"><spring:message code="BzComposer.accountreceivable.tabs.receivablelist"/></a>
			    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false" onclick="overDueTab()"><spring:message code="BzComposer.accountreceivable.tabs.overdue"/></a>
			    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false" onclick="cancelledTab()"><spring:message code="BzComposer.accountreceivable.tabs.cancelled"/></a>
			    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false"><spring:message code="BzComposer.accountreceivable.tabs.vendorrma"/></a>
			    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false" onclick="layawaysTab()"><spring:message code="BzComposer.accountreceivable.tabs.layaways"/></a>
			    <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false" onclick="selectedTab()"><spring:message code="BzComposer.accountreceivable.tabs.received"/></a>
			    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">eSales</a>
			  </div>
			</nav>
			<div class="tab-content">
				
				<div class="form-inline pt-2 pb-2">
					<div class="form-group mr-2">
						<select class="form-control">
							<option>All</option>
							<option>Today</option>
							<option>this month</option>
						</select>
					</div>
					
					<div class="form-group mr-2">
						<div class="input-group">
							<input type="text" class="form-control" id = "fromDate" readonly="true">
							<div class="input-group-append">
								 <a class="input-group-text" href="#">&#9662;</a>
						  	</div>
						</div>
					</div>
					
					<div class="form-group mr-2">
						<div class="input-group">
							<input type="text" class="form-control" id = "toDate" readonly="true">
							<div class="input-group-append">
								 <a class="input-group-text" href="#">&#9662;</a>
						  	</div>
						</div>
					</div>
				</div>
			  
			<div class="tab-content" id="nav-tabContent">
			  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
				<div class="filterbar" onclick="selectedRadio1()">
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoReceivedInvoiceOrder" value="option1" checked="checked">
					  <label class="form-check-label" for="inlineRadio1">
					    <spring:message code="BzComposer.accountreceivable.receivedInvoice"/>
					  </label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoReceivedOpeningBalance" value="option2">
					  <label class="form-check-label" for="inlineRadio2">
					    <spring:message code="BzComposer.accountreceivable.receivedOpeningBalance"/>
					  </label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoReceivedVendorRMA" value="option2">
					  <label class="form-check-label" for="inlineRadio3">
					    <spring:message code="BzComposer.accountreceivable.receivedVendorRMA"/>
					  </label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoReceivedCredit" value="option2">
					  <label class="form-check-label" for="inlineRadio3">
					    <spring:message code="BzComposer.accountreceivable.receivedCredit"/>
					  </label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoPartiallyReceivedLayaways" value="option2" onclick="partiallyReceivedLayaways()">
					  <label class="form-check-label" for="inlineRadio3">
					    <spring:message code="BzComposer.accountreceivable.partiallyReceivedLayaways"/>
					  </label>
					</div>
				</div>
				
				
				<div class="table1" id="tblForReceived">
				<table class="table table-bordered table-sm devAcRecDataTbl">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col"><spring:message code="BzComposer.accountreceivable.select"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.sales.Order"/></th>
				      <th scope="col"><spring:message code="BzComposer.accountreceivable.customer"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paiddate"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.depositto"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.receivedtype"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paidamount"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.deposited"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.category"/></th>
				      <th scope="col"><spring:message code="BzComposer.global.memo"/></th>
				    </tr>
				  </thead>
				  <tbody> 
				  <%
				  	ArrayList<TblPayment> li = (ArrayList)request.getAttribute("receivedList");		
				  	Iterator<TblPayment> itr =li.iterator();
				    int index = 1;
				  	while(itr.hasNext()){
				  		TblPayment rb = itr.next();
				    %>
				  <tr onclick="selectrow(<%=rb.getInvoiceID()+","+index+","+rb.getId()%>)">
				      <td><input type="checkbox"></td>
				      <td class="text-right"><% out.println(rb.getOrderNum()); %></td>
				      <td class="text-right" value="<%= rb.getCvID()%>"><% out.println(rb.getCvName()+ " (" +rb.getCompanyName() + ")") ;%></td>
				      <td class="text-right"><% out.println(JProjectUtil.dateFormat.format(rb.getDateAdded()));%></td>
				      <td class="text-right" value="<%= rb.getAccountID()%>"><% out.println(rb.getAccountName()); %></td>
				      <% if(rb.getPaymentTypeName().equals("Check")) {%>
				      <td class="text-right" value="<%= rb.getPaymentTypeID()%>"><% out.println(rb.getPaymentTypeName() + "#" + rb.getCheckNumber()); %></td>
				      <% } else {%>
				      <td class="text-right" value="<%= rb.getPaymentTypeID()%>"><% out.println(rb.getPaymentTypeName()); %></td>
				      <% }%>
				      <td class="text-right"><%= String.format("%.2f", rb.getAmount()) %></td>
				      <% if(rb.isNeedToDeposit()) {%>
				      <td class="text-right"><% out.println("No"); %></td>
				      <% } else { %>
				      <td class="text-right"><% out.println("Yes"); %></td>
				      <% } %>
				      <td class="text-right" value="<%= rb.getCategoryId()%>"><% out.println(rb.getTblcategory());%></td>
				      <td class="text-right"></td>
				      <td hidden="PaidOrUnpaid" value="<%= request.getSession().getAttribute("PaidOrUnpaid"+rb.getInvoiceID()) %>"></td>
				      <td hidden="totalAmount" value="<%= request.getSession().getAttribute("totalAmount"+rb.getInvoiceID()) %>"></td>
				    </tr>
			<%
			index++;
				} %>
		</tbody>	
	</table>
</div>

<div class="table1" id="tblForPartLayaways">
				<table class="table table-bordered table-sm devAcpartiallyLayaways">
				  <thead class="thead-light">
				    <tr>
				        <th scope="col"><spring:message code="BzComposer.accountreceivable.select"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.sales.Order"/></th>
                          <th scope="col"><spring:message code="BzComposer.accountreceivable.customer"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paiddate"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.receivedtype"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paidamount"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.common.totalAmount"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.deposited"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.category"/></th>
                          <th scope="col"><spring:message code="BzComposer.global.memo"/></th>
				    </tr>
				  </thead>
				  <tbody> 
				  <%
				  	ArrayList<TblPayment> pli = (ArrayList)request.getAttribute("partiallyReceivedLayaways");		
				  	Iterator<TblPayment> itr1 =pli.iterator();
							  int indexForpli=1;
				  	while(itr1.hasNext())
				  	{
				  		TblPayment rb = itr1.next();
				    %>
				  <tr onclick="selectrow(<%=rb.getInvoiceID()+","+indexForpli+","+rb.getId()%>)">
				      <td><input type="checkbox"></td>
				      <td class="text-right"><% out.println(rb.getOrderNum()); %></td>
				      <td class="text-right" value="<%= rb.getCvID()%>"><% out.println(rb.getCvName()+ " (" +rb.getCompanyName() + ")") ;%></td>
				      <td class="text-right"><% out.println(JProjectUtil.dateFormat.format(rb.getDateAdded()));%></td>
				      <% if(rb.getPaymentTypeName().equals("Check")) {%>
				      <td class="text-right" value="<%= rb.getPaymentTypeID()%>"><% out.println(rb.getPaymentTypeName() + "#" + rb.getCheckNumber()); %></td>
				      <% } else {%>
				      <td class="text-right" value="<%= rb.getPaymentTypeID()%>"><% out.println(rb.getPaymentTypeName()); %></td>
				      <% }%>
				      <td class="text-right" value="<%= rb.getPaymentTypeID()%>"><% out.println(rb.getAmount()); %></td>
				      <td class="text-right"><% out.println(rb.getTotalAmount()); %></td>
				      <% if(rb.isNeedToDeposit()) {%>
				      <td class="text-right"><% out.println("No"); %></td>
				      <% } else { %>
				      <td class="text-right"><% out.println("Yes"); %></td>
				      <% } %>
				      <td class="text-right" value="<%= rb.getCategoryId()%>"><% out.println(rb.getTblcategory());%></td>
				      <td class="text-right">partially Received Layaways</td>
				      <td hidden="PaidOrUnpaid" value="<%= request.getSession().getAttribute("PaidOrUnpaid"+rb.getInvoiceID()) %>"></td>
				      <td hidden="totalAmount" value="<%= request.getSession().getAttribute("totalAmount"+rb.getInvoiceID()) %>"></td>
				      <td hidden="accountName" value="<%= rb.getAccountID() %>"><%= rb.getAccountNameString() %></td>
				    </tr>
			<%
			indexForpli++;
				} %>
		</tbody>	
	</table>
</div>
<div class="table1" id="tblForRecOpeningBal">
				<table class="table table-bordered table-sm devReceivedOpeningBalance">
				  <thead class="thead-light">
				    <tr>
				        <th scope="col"><spring:message code="BzComposer.accountreceivable.select"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.sales.Order"/></th>
                          <th scope="col"><spring:message code="BzComposer.accountreceivable.customer"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paiddate"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.depositto"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.receivedtype"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paidamount"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.deposited"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.category"/></th>
                          <th scope="col"><spring:message code="BzComposer.global.memo"/></th>
				    </tr>
				  </thead>
				  <tbody> 
				  </tbody>
				  </table>
</div>
<div class="table1" id="tblForRecVendor">
				<table class="table table-bordered table-sm devReceivedVendorRMA">
				  <thead class="thead-light">
				    <tr>
				        <th scope="col"><spring:message code="BzComposer.accountreceivable.select"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.sales.Order"/></th>
                          <th scope="col"><spring:message code="BzComposer.accountreceivable.customer"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paiddate"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.depositto"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.receivedtype"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paidamount"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.deposited"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.category"/></th>
                          <th scope="col"><spring:message code="BzComposer.global.memo"/></th>
				    </tr>
				  </thead>
				  <tbody> 
				  </tbody>
				  </table>
</div>
<div class="table1" id="tblForReceCredit">
				<table class="table table-bordered table-sm devReceivedCredit">
				  <thead class="thead-light">
				    <tr>
				        <th scope="col"><spring:message code="BzComposer.accountreceivable.select"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.sales.Order"/></th>
                          <th scope="col"><spring:message code="BzComposer.accountreceivable.customer"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paiddate"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.depositto"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.receivedtype"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paidamount"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.deposited"/></th>
                          <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.category"/></th>
                          <th scope="col"><spring:message code="BzComposer.global.memo"/></th>
				    </tr>
				  </thead>
				  <tbody> 
				  </tbody>
				  </table>
</div>
	
				<div class="footer1">
                    <button class="btn btn-info" style="font-size: 14px;"><spring:message code="BzComposer.accountreceivable.paymentToDeposit"/></button>
                    <button class="btn btn-info" style="font-size: 14px;"><spring:message code="BzComposer.accountreceivable.addToRefundList"/></button>
				  </div>
	
	
				</div>
			  <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">...</div>
			  <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">...</div>
			</div>
		
		
		</div>



	</div>			

 </html:form>                                
<script type="text/javascript">
	var indexNumber = -1;
	var amtToPay = -1;
	var invoiceId = -1;
	var paymentId = -1;
	var oldClientVendorId = -1;
	var oldBankAccountId = -1;
	var oldPaymentTypeId = -1;
	selectChaseBankFromDropDownList("depositId");

   function selectrow(invoice,index,payId) {
	    debugger;
	 if(document.getElementById("rdoReceivedInvoiceOrder").checked)
	 {   	
	    this.indexNumber = index;
	    this.invoiceId = invoice;
	    this.paymentId = payId;
	    this.oldClientVendorId = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(3)').attr('value');
	    this.oldBankAccountId = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').attr('value')
	    this.oldPaymentTypeId = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').attr('value');
	    $("select.paidunpaid").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(11)').attr('value'));
	   	$("#checkNum").val(''); 
	    $("#ordernumber").text($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(2)').text());
	    $("select.devCutNameDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(3)').attr('value'));
	    <%-- if(amount == balance)
	    	{
	    		$(".devReceiveAmount").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').text());
	    	}
	    else
	    	{
	    		var checkReceived = <%= request.getSession().getAttribute("checkReceived"+) %>;
	    		$(".devReceiveAmount").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(9)').text());
	    	} --%>
	    $("#devAmount").text($('table.devAcRecDataTbl tbody tr:nth-child(1)').find('td:nth-child(12)').attr('value'));
	    $(".devReceiveAmount").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(7)').text()); 
	    $("select.devCategoryDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(9)').attr('value'));
	    if($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').attr('value') == '192' || $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(15)').attr('value') == '1')
	    {
	    		$("#Check").show();
	    		var check = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').text();
	    		var checkNum = check.substring(6);
	    		$(".devCheck").val(checkNum);
	    }
	    else
	    {
	    		$("#Check").hide();
	    }
	    $("select.devReceivedTypeDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').attr('value'));
	    $("select.devDeposittypeDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').attr('value'));
	    $(".devOrderDate").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(4)').text());
	    $(".devMemotext").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').text());
	 } 
	if(document.getElementById("rdoPartiallyReceivedLayaways").checked)
	{
		 this.indexNumber = index;
		    this.invoiceId = invoice;
		    this.paymentId = payId;
		    this.oldClientVendorId = $('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(3)').attr('value');
		    this.oldBankAccountId = $('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(13)').attr('value')
		    this.oldPaymentTypeId = $('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').attr('value');
		   /*  $("select.paidunpaid").val($('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(11)').attr('value')); */
		   	$("#checkNum").val(''); 
		    $("#ordernumber").text($('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(2)').text());
		    $("select.devCutNameDrp").val($('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(3)').attr('value'));
		    
		    $("#devAmount").text($('table.devAcpartiallyLayaways tbody tr:nth-child(1)').find('td:nth-child(7)').text()); 
		    $(".devReceiveAmount").val($('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').text()); 
		    $("select.devCategoryDrp").val($('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(9)').attr('value'));
		    if($('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').attr('value') == '192' || $('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(15)').attr('value') == '1')
		    {
		    		$("#Check").show();
		    		var check = $('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').text();
		    		var checkNum = check.substring(6);
		    		$(".devCheck").val(checkNum);
		    }
		    else
		    {
		    		$("#Check").hide();
		    }
		    $("select.devReceivedTypeDrp").val($('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').attr('value'));
		    $("select.devDeposittypeDrp").val($('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(13)').attr('value'));
		    $(".devOrderDate").val($('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(4)').text());
		    $(".devMemotext").val($('table.devAcpartiallyLayaways tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').text());
		    
	}
   }  
								 							 	
   
   function save()
   {
	   debugger;	

	    var customer = document.getElementById("customerName");
	 	var customerId = customer.options[customer.selectedIndex].value;
	 	var payTypeString = document.getElementById("receivedType");
		var paymentTypeId = payTypeString.options[payTypeString.selectedIndex].id;
		var paymentTypeName = payTypeString.options[payTypeString.selectedIndex].label;
		var depositBank = document.getElementById("depositId");
		var accountId = depositBank.options[depositBank.selectedIndex].id;
		var category = document.getElementById("categoryId");
		var categoryId = category.options[category.selectedIndex].id;
		var receivedAmount = document.getElementById("receivedAmount").value;
		var paidDate = document.getElementById("orderDate").value;
		if(paymentTypeName == 'Check')
        {
            if(document.getElementById("checkNum").value == '' || document.getElementById("checkNum").value == '0')
            {
                alert("<bean:message key='BzComposer.common.enterValidCheckNumber'/>");
                return false;
            }
        }
	   var TblPayment={
			   "invoiceID":invoiceId,
			   "cvID":customerId,
			   "paymentTypeID":paymentTypeId,	   
			   "accountID":accountId,
			   "categoryID":categoryId,
			   "oldclientVendorID":oldClientVendorId,
			   "oldAccountID":oldBankAccountId,
			   "paymentTypeName":paymentTypeName,
			   "oldPaymentTypeId":oldPaymentTypeId,
			   "memo":document.getElementById("memo").value,
			   "checkNumber":document.getElementById("checkNum").value,
	   }
	   
    sendMyValue(TblPayment,receivedAmount,paidDate);
 
  	}
   function sendMyValue(TblPayment,receivedAmount,paidDate) {
		debugger;
		var obj=JSON.stringify(TblPayment);
   	    $.ajax({
		
			type : "POST",
			url : "ReceivedTab?tabid=UpdateRecord",
			data : "row=" + obj + "&PaymentId=" + paymentId + "&ReceivedAmount=" +receivedAmount + "&PaidDate=" + paidDate,
		    success : function(data) {
				
		    updateReceivedTab(data);	
			/* window.location = "${pageContext.request.contextPath}/ReceivedTab?tabid=receivedTab"; */ 
			
			},
			 error : function(data) {
				alert("<bean:message key='BzComposer.common.erroroccurred'/>");
			} 
		});
   	
   	$(document.forms[0]).submit(function( event ) {
	    event.preventDefault();
	});
	} 
   function setInvoice(index)
   {
	   $.ajax({
			
			type : "POST",
			url : "AccountReceiveble?tabid=selectrow&ordernum="+index,
			data : "row=" +index, 			
			success : function(data,status) {
				
				window.location= "${pageContext.request.contextPath}/AccountReceiveble?tabid=selectrow&ordernum="+index;
			},
			 error : function(data) {
				alert("<bean:message key='BzComposer.common.erroroccurred'/>");
			} 
		});
   }
   function checkType()
   {
	 	debugger;
	   var type = document.getElementById("receivedType");
	   var ctype = type.options[type.selectedIndex].innerText;
	   
	   if(ctype == 'Cash')
		   {
		   		document.getElementById("Check").style.display = "none";
		   }
	   else if(ctype == 'Check')
		   {
		   		
		   		$("#Check").show();
		   	} 
	    else
		{ 
	    		$("#Check").hide();
	    } 
   }
   function selectedRadio1()
   {
	 debugger;
	   if(document.getElementById("rdoReceivedInvoiceOrder").checked)
		   {
		   		
		   		$("#tblForPartLayaways").hide();
	   			$("#tblForRecOpeningBal").hide();
	   			$("#tblForRecVendor").hide();
	   			$("#tblForReceCredit").hide();
	   			$("#tblForReceived").show();
	   			$(".footer1").show();
		   }
	   else if(document.getElementById("rdoReceivedOpeningBalance").checked)
		   {
				   	$("#tblForReceived").hide();
		  			$("#tblForPartLayaways").hide();
		  			$("#tblForRecVendor").hide();
		  			$("#tblForReceCredit").hide();
		  			$(".footer1").hide();
		  			$("#tblForRecOpeningBal").show();
		  			
		   }
	   else if(document.getElementById("rdoReceivedVendorRMA").checked)
	   {
			   	$("#tblForReceived").hide();
	  			$("#tblForPartLayaways").hide();
	  			$("#tblForRecOpeningBal").hide();
	  			$("#tblForReceCredit").hide();
	  			$(".footer1").hide();
	  			$("#tblForRecVendor").show();
	   }
	   else if(document.getElementById("rdoReceivedCredit").checked)
	   {
			   	$("#tblForReceived").hide();
	  			$("#tblForPartLayaways").hide();
	  			$("#tblForRecOpeningBal").hide();
	  			$("#tblForRecVendor").hide();
	  			$(".footer1").hide();
	  			$("#tblForReceCredit").show();
	   }
	   else
		   {
		   			$("#tblForReceived").hide();
		   			$("#tblForRecOpeningBal").hide();
		   			$("#tblForRecVendor").hide();
		   			$("#tblForReceCredit").hide();
		   			$(".footer1").hide();
		   			$("#tblForPartLayaways").show();
		   }
   }
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
   function received()
   {
		debugger;
	   var receivedAmountString = 0.0;
	   var type = document.getElementById("receivedType");
	   var ctype = type.options[type.selectedIndex].label;
	   var paymentTypeIdString = type.options[type.selectedIndex].id;
	   var paymentTypeId = parseInt(paymentTypeIdString);
	   
	   if(ctype == 'Check')
	   {
	        var checkNo = document.getElementById("checkNum").value;
	   	    if(checkNo == '0' || checkNo == '')
            {
                alert("<bean:message key='BzComposer.common.enterValidCheckNumber'/>");
                return false;
            }
	   	 
	   }
	   var memo = document.getElementById("memo").value;
	   var depositBank = document.getElementById("depositId");
	   var selectedBank = depositBank.options[depositBank.selectedIndex].innerText;
	   var accountId = depositBank.options[depositBank.selectedIndex].id;
	   var category = document.getElementById("categoryId");
	   var customer = document.getElementById("customerName");
	   var selectedCustomer = customer.options[customer.selectedIndex].value;
	   var selectedCategoryString = category.options[category.selectedIndex].value;
	   var categoryId = parseInt(selectedCategoryString);
	   var amountString = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(8)').text();
	   var balaceString = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').text();
	    var amount = parseFloat(amountString);
	   var balance = parseInt(balaceString); 
	   <%-- var receivedAmount = <%= request.getSession().getAttribute("amtToPay")%>; --%>
	     var receivedAmount = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(18)').attr('value');  
	   if(amount == balance)
		   {
		  		 receivedAmountString =  $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').text();
		   }
	   else
		   {
		  		 receivedAmountString = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(9)').text();
		   }
	   var ReceivableListBean={
			   "orderNum":document.getElementById("ordernumber").innerHTML,
			   "cvID":selectedCustomer,
			   "paymentTypeID":paymentTypeId,	   
			   "bankAccountID":accountId,
			   "adjustedTotal":amountString,	
			   "paidAmount":receivedAmount,
			   "balance":balance,
			   "categoryID":categoryId,
			   "memo":memo,
			   "checkNum":checkNo,
	   };
	   var obj=JSON.stringify(ReceivableListBean);
	   
	   $.ajax({
			
			type : "POST",
			url : "AccountReceiveble?tabid=ReceivedInvoice",			
			data :"row=" + obj + "&index="+indexNumber,
		    success : function(data) {
				
			updateReceivedTab(data);	
			/* window.location = "${pageContext.request.contextPath}/AccountReceiveble?tabid=AccountReceiveble"; */ 
			
			},
			 error : function(data) {
				alert("<bean:message key='BzComposer.common.erroroccurred'/>");
			} 
		});
  	
  	$(document.forms[0]).submit(function( event ) {
	    event.preventDefault();
	});
	   return false;
	   
   }
   function clearTransaction()
   {
	   if(paymentId == '' || paymentId == -1){
            alert("<bean:message key='BzComposer.accountreceivable.selecttransaction'/>");
            return false;
       }
	   else{
        var status = window.confirm("Would you like to remove the transaction ?");
        if(status != true){
                return false;
            }
       }
	   $.ajax({
			
			type : "POST",
			url : "ReceivedTab?tabid=clearTransaction",			
			data :"paymentId=" + paymentId,
		    success : function(data) {
		    	
		    	updateReceivedTab(data);
		    	/* window.location = "${pageContext.request.contextPath}/ReceivedTab?tabid=receivedTab"; */
		    },
			 error : function(data) {
					alert("<bean:message key='BzComposer.common.erroroccurred'/>");
				} 
			});
	   $(document.forms[0]).submit(function( event ) {
		    event.preventDefault();
		});
   }

   $(document).ready(function(){
		debugger;
		var day = new Date().getDay();
		var dName = dayName(day);
	   $("#tblForUnpaidOpeningBalance").hide();
	   $("#tblForUnpaidCreditAmount").hide();
	   $("#orderDate").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear());
	   $("#fromDate").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear());
	   $("#toDate").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear());
	   $("#tblForPartLayaways").hide();
	   $("#tblForRecOpeningBal").hide();
	   $("#tblForRecVendor").hide();
	   $("#tblForReceCredit").hide();
	   var type = document.getElementById("receivedType");
	   var ctype = type.options[type.selectedIndex].innerText;
	   if(ctype != 'Check')
		   {
		  		 $("#Check").hide();
		   }
	   
});
   function selectedRadio()
   {
	 debugger;
	   if(document.getElementById("rdoUnpaidOpeningBalance").checked)
		   {
		   		$("#tblForInvoiceOrder").hide();
	   			$("#tblForUnpaidOpeningBalance").show();
	   			$("#tblForUnpaidCreditAmount").hide();
		   }
	   else if(document.getElementById("rdoUnpaidCreditAmount").checked)
		   {
				   	$("#tblForInvoiceOrder").hide();
		  			$("#tblForUnpaidOpeningBalance").hide();
		  			$("#tblForUnpaidCreditAmount").show();
		   }
	   else
		   {
		   			$("#tblForInvoiceOrder").show();
		   			$("#tblForUnpaidOpeningBalance").hide();
		   			$("#tblForUnpaidCreditAmount").hide();
		   }
   }
   function selectedTab()
   {

	   window.location = "${pageContext.request.contextPath}/ReceivedTab?tabid=receivedTab"; 
   }
   function layawaysTab()
  {
    window.location = "${pageContext.request.contextPath}/Layaway?tabid=layawayTab";
  }
   function cancelledTab()
   {
	   debugger;
	   window.location = "${pageContext.request.contextPath}/CancelledTab?tabid=canCelledTab"; 
   }
   function receivedTab()
   {

	   window.location = "${pageContext.request.contextPath}/ReceivedTab?tabid=receivedTab"; 
   }
   function overDueTab()
   {
	   window.location = "${pageContext.request.contextPath}/OverDueTab?tabid=overDueTab";
   }
   function receivableList()
   {
	   window.location = "${pageContext.request.contextPath}/AccountReceiveble?tabid=AccountReceiveble";
   }
   $(function() {
       $( "#fromDate" ).datepicker();
   });
   $(function() {
       $( "#toDate" ).datepicker();
   });
   
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
   function updateReceivedTab(data)
   {
	   debugger;
	   document.getElementById("devAmount").innerHTML = "";
	   $("#receivedForm")[0].reset();
	   $(document).find('div#tblForReceived table').replaceWith($(data).find('div#tblForReceived').html());
	   $(document).find('div#tblForPartLayaways table').replaceWith($(data).find('div#tblForPartLayaways').html());
	   $(document).find('div#tblForRecOpeningBal table').replaceWith($(data).find('div#tblForRecOpeningBal').html());
	   $(document).find('div#tblForRecVendor table').replaceWith($(data).find('div#tblForRecVendor').html());
	   $(document).find('div#tblForReceCredit table').replaceWith($(data).find('div#tblForReceCredit').html());
	   addCss();
   }
</script>

</body>
</html>