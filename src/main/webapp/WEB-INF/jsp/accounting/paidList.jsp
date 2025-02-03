<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
<title><spring:message code="BzComposer.paidlisttitle"/></title>

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
 .form-inline {
    display: -ms-flexbox;
    display: flex;
    float: left;
    width: 50%;
    -ms-flex-flow: row wrap;
    flex-flow: row wrap;
    -ms-flex-align: center;
    align-items: center;
}
.filterbar {
    margin-top: 10px;
    background: #f2f2f2;
    padding: 10px 10px;
    float: right;
    margin-bottom: 10px;
    width: 32%;
}
.table1 {
    position: relative;
    float: left;
    top: 0;
    max-height: 325px;
    overflow-y: auto;
    width: 100%;
} 
.highlight { background-color: #00CED1 !important;color: #fff }	
</style>

</head>
<body>
<% int find = 0; 
%>
<div id="ddcolortabsline">&nbsp;</div>
 <html:form action="AccountReceiveble" method="post" >  
	<div class="content1 clearfix">
		<h3 class="title1"><spring:message code="BzComposer.popayable.payabletitle"/></h3>
		 <% 
		 	if(request.getSession().getAttribute("invoiceId") != null)
		 	{
		 		find = Integer.parseInt(request.getSession().getAttribute("invoiceId").toString());
		 	}
		 	
		 %>
		 
		<div class="border1  clearfix">
			<form>
				<div class="row">
					<div class="col-md-4">
						<!-- <label>P O Number</label>
						
						 <label id="poNumber">
						    
						      </label> -->
						<div class="form-group row">
							<label class="col-md-4  col-form-label">
								<spring:message code="BzComposer.popayable.vendor"/>
							</label>
							<label id="custId" for="vendorName"> </label>
								
							</div>
						
						<div class="form-group row">
							<label class="col-md-4  col-form-label">
								<spring:message code="BzComposer.popayable.paymenttype"/>
							</label>
							<div class="col-md-8">
								<select class="form-control devReceivedTypeDrp" id="paymentType" onclick="checkType()">
								<%	
								ArrayList<TblPaymentType> payType = (ArrayList)request.getAttribute("paymentTypeForPOcombo");
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
								<spring:message code="BzComposer.popayable.payfrom"/>
							</label>
							<div class="col-md-8">
								<select class="form-control devDeposittypeDrp" id="payId">
									
								 <% ArrayList<TblAccount> account = (ArrayList)request.getAttribute("accountForCombo");
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
							<spring:message code="BzComposer.popayable.amount"/>
						</label>
				    
				    	<div class="col-md-8">	
				    	<div class="input-group">
							<div class="input-group-prepend">
							 <span class="input-group-text" id="basic-addon1">
							 	<spring:message code="BzComposer.popayable.dollersign"/>
							 </span>
							 </div>
								<label  style="padding-left: 10px" id="devAmount" class="form-control"></label>
								</div>
						</div>
						</div>
						 <div class="form-group row">
							<label class="col-md-4  col-form-label">
								<spring:message code="BzComposer.popayable.paymentamount"/>
							</label>
							 
					<div class="col-md-8">
						<div class="input-group">
							<div class="input-group-prepend">
							 <span class="input-group-text" id="basic-addon1">
							 	<spring:message code="BzComposer.popayable.dollersign"/>
							 </span>
							  </div>
							<input type="text" class="form-control devPaymentAmount" value="" width="20px" id="receivedAmount">
							</div>
						
					</div>
						</div> 
						
				<script>
					  var data = document.getElementById("Check");
					  data.style.display = "none";
				</script>
				<div class="form-group row" id="Check">
							<label class="col-md-4  col-form-label">
								<spring:message code="BzComposer.popayable.checknumber"/>
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
								<spring:message code="BzComposer.popayable.date"/>
							</label>
						   <%--  <html:text property="orderDate" readonly="false"></html:text>  --%>
						    <div class="col-md-8 calendar-img"><input type="text" class="form-control poDate" value="" style="width: 275px" name="poDate" readonly="true" id="poDate">
							<img src="${pageContext.request.contextPath}/images/cal.gif"
							class="img-fluid" alt="Responsive image"
								onclick="displayCalendar(document.ReceivableListForm.poDate,'mm-dd-yyyy',this);">
							</div>   
									
						</div>
						<div class="form-group row">
							<label class="col-md-4  col-form-label">
								<spring:message code="BzComposer.popayable.category"/>
							</label>
							<div class="col-md-8">
								<select class="form-control devCategoryDrp" size="1" id="categoryId">
								<%
									ArrayList<TblCategory> category = (ArrayList)request.getAttribute("categoryforcombo");
							
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
								<spring:message code="BzComposer.global.memo"/>
							</label>
							<div class="col-md-8">
								<input type="text" class="form-control devMemotext" id="memo">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-4  col-form-label">
								<spring:message code="BzComposer.popayable.paymentstatus"/>
							</label>
							<div class="col-md-8">
								<select class="form-control paymentOP" size="1" id="payStatus">
									<option value="Unpaid"><spring:message code="BzComposer.popayable.unpaid"/></option>
									<option value="Paid"><spring:message code="BzComposer.popayable.paid"/></option>
								</select>
							</div>
						</div>
						
					</div>
					<div class="col-md-4">
					<%-- <% if(receivablelistbean == null) {%>
						<div class="form-group">
								<button class="btn btn-info btn1" onclick="return save(null,null)">Save</button>
							</div>
						<%} else {%>
							<div class="form-group">
								<button class="btn btn-info btn1" onclick="return save(<%= receivablelistbean.getOrderNum()%>,<%= receivablelistbean.getPaidAmount()+receivablelistbean.getBalance()%>)">Save</button>
							</div>
						<% } %>  --%>
						 <div class="form-group">
								<button class="btn btn-info" style="width:170px;font-size: 14px;" onclick="return save()">
									<spring:message code="BzComposer.global.save"/>
								</button>
							</div>  
						<div class="form-group">
							<button class="btn btn-info" style="width:170px;font-size: 14px;" onclick="return clearTransaction()">
								<spring:message code="BzComposer.popayable.cleartransactionbtn"/>
							</button>
						</div> 
					</div>
				</div>
			</form>
		</div>
		
		<div class="content-tabs">
			
			<nav>
			   <div class="nav nav-tabs" id="tabId" role="tablist">
			    <a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" 
			    aria-controls="nav-home" aria-selected="true" onclick="poPayableTab()">
			    	<spring:message code="BzComposer.popayable.tab.popayablelist"/>
		    	</a>
			    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" 
			    aria-controls="nav-profile" aria-selected="false" onclick="consignmentTab()">
			    	<spring:message code="BzComposer.popayable.tab.consignmentsalepayment"/>
		    	</a>
			    <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" href="#nav-profile"
			    role="tab" aria-controls="nav-profile" aria-selected="false" onclick="overDueTab()">
			    	<spring:message code="BzComposer.popayable.tab.paidlist"/>
		    	</a>
		    	<a class="nav-item nav-link" id="nav-vendorRMA-tab" data-toggle="tab" href="#nav-vendorRMA" role="tab"
                aria-controls="nav-contact" aria-selected="false">
                    <spring:message code="BzComposer.accountreceivable.tabs.VendorRMARefund"/>
                </a>
			  </div>
			</nav>
			<div class="tab-content">
				
				<div class="row">
				<div class="col-md-6">
					<div class="form-group float-left w-25 mr-2">
					    <div class="input-group">
						<select class="form-control rangeOP" id="rangeId">
							<option value="1">All</option>
							<option value="2">Custom</option>
							<option value="3">Today</option>
							<option value="4">This Month</option>
							<option value="5">This Quarter</option>
							<option value="6">This Year</option>
							<option value="7">1 Year</option>
							<option value="8">2 year</option>
							<option value="9">3 Year</option>
							<option value="10">This Month-To-Date</option>
							<option value="11">This Quarter-To-Date</option>
							<option value="12">This Year-To-date</option>
							<option value="13">Last 10 Days</option>
							<option value="14">Last 30 Days</option>
							<option value="15">Last 60 Days</option>
							<option value="16">1 Week</option>
						</select>
						</div>
					</div>
					<div class="form-group float-left w-25 mr-2">
						<div class="input-group">
							<input type="text" class="form-control" id = "fromDate" readonly="true">
							<div class="input-group-append">
								 <a class="input-group-text" href="#">&#9662;</a>
						  	</div>
						</div>
					</div>
					<div class="form-group float-left w-25">
						<div class="input-group">
							<input type="text" class="form-control" id = "toDate" readonly="true">
							<div class="input-group-append"><a class="input-group-text" href="#">&#9662;</a></div>
						</div>
					</div>
			    </div>
                <div class="col-md-6">
					<div class="float-right">
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoPurchaseOrder" value="option1" checked="checked" onclick="viewPaidPOTable()">
                        <label class="form-check-label" for="inlineRadio1">
                            <spring:message code="BzComposer.popayable.purchaseorder"/>
                        </label>
                        </div>
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoUnpaidOpeningBalance" value="option2">
                        <label class="form-check-label" for="inlineRadio2">
                            <spring:message code="BzComposer.popayable.rma"/>
                        </label>
                        </div>
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoUnpaidCreditAmount" value="option2">
                        <label class="form-check-label" for="inlineRadio3">
                            <spring:message code="BzComposer.popayable.unpaidopeningbalance"/>
                        </label>
                        </div>
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoConsignmentSalePayment" value="option2" onclick="viewConsignmentSaleTable()">
                        <label class="form-check-label" for="inlineRadio3">
                            <spring:message code="BzComposer.popayable.consignmentpayment"/>
                        </label>
                        </div>

                    </div>
                </div>
                </div>
			  
			<div class="tab-content" id="nav-tabContent">
			  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">	

				<div class="table1" id="tblForPaidPO">
				<table class="table table-bordered table-sm devAcRecDataTbl">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col"><spring:message code="BzComposer.popayable.ponum"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.vendor"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paiddate"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paymenttype"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.checknumber"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.payfrom"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.amount"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paidamount"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.balance"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.category"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.global.memo"/></th>
				    </tr>
				  </thead>
				  <tbody id="tblForPaidPOBody"> 
				  <%
				  	ArrayList<TblPayment> li = (ArrayList)request.getAttribute("paidList");		
				  	Iterator<TblPayment> itr =li.iterator();
					int index = 1;
				  	while(itr.hasNext()){
				  		TblPayment rb = itr.next();
				    %>
				  <tr onclick="selectrow(<%=rb.getInvoiceID()+","+index+","+rb.getId()%>)">
			
				      <td class="text-right"><% out.println(rb.getPoNum()); %></td>
				      <td class="text-right" value="<%= rb.getCvID()%>"><% out.println(rb.getCvName()) ;%></td>
				      <td class="text-right"><% out.println(JProjectUtil.dateFormat.format(rb.getDateAdded()));%></td>
				      <td class="text-right" value="<%= rb.getPaymentTypeID()%>"><% out.println(rb.getPaymentTypeName()); %></td>
				       <td class="text-right"><% out.println(rb.getCheckNumber()); %></td>
				      <td class="text-right" value="<%= rb.getAccountID() %>"><% out.println(rb.getAccountNameString()); %></td>
				      <td class="text-right"><%= String.format("%.2f", rb.getTotalAmount()) %></td>
				      <td class="text-right"><%= String.format("%.2f", rb.getAmount())  %></td>
				      <td class="text-right"><%= String.format("%.2f", rb.getBalance()) %></td>
				      <td class="text-right" value="<%=rb.getCategoryId() %>"><% out.println(rb.getCategoryName()); %></td>
				      <td class="text-right"></td>
				       <td hidden="PaidOrUnpaid" value="<%= request.getSession().getAttribute("PaidOrUnpaid"+rb.getInvoiceID()) %>"></td>
				 
				    </tr>
			<%
			index++;
				} %>
		</tbody>	
	</table>
</div>

 <div class="table1" id="tblForPaidConsignment">
				<table class="table table-bordered table-sm devAcPaidConsignment">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.vendor"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paiddate"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paymenttype"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.checknumber"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.payfrom"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.amount"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paidamount"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.balance"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.category"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.global.memo"/></th>
				    </tr>
				  </thead>
				  <tbody> 
				  <%
				  	ArrayList<TblPayment> pli = (ArrayList)request.getAttribute("paidConsignment");		
				  	Iterator<TblPayment> itr1 =pli.iterator();
							  int indexForpli=1;
				  	while(itr1.hasNext())
				  	{
				  		TblPayment rb = itr1.next();
				    %>
				  <tr onclick="selectrow(<%=rb.getInvoiceID()+","+indexForpli+","+rb.getId()%>)">
				      <td class="text-right" value="<%= rb.getCvID()%>"><% out.println(rb.getCvName()); %></td>
				      <td class="text-right"><% out.println(JProjectUtil.dateFormat.format(rb.getDateAdded()));%></td>
				       <td class="text-right" value="<%= rb.getPaymentTypeID()%>"><% out.println(rb.getPaymentTypeName()); %></td>
				      <% if(rb.getPaymentTypeName().equals("Check")) {%>
				      <td class="text-right"><% out.println(rb.getCheckNumber()); %></td>
				      <% } else {%>
				      <td class="text-right"></td>
				      <% }%>
				      <td class="text-right" value="<%= rb.getAccountID() %>"><% out.println(rb.getAccountNameString()); %></td>
				      <td class="text-right"><% out.println(rb.getTotalAmount());%></td>
				      <td class="text-right"><% out.println(rb.getAmount()); %></td>
				      <td class="text-right"><% out.println(rb.getBalance()); %></td>
				      <td class="text-right" value="<%= rb.getCategoryId()%>"><% out.println(rb.getCategoryName());%></td>
				      <td class="text-right"></td>
				    </tr>
			<%
			indexForpli++;
				} %>
		</tbody>	
	</table>
</div>
 <!-- <div class="table1" id="tblForRecOpeningBal">
				<table class="table table-bordered table-sm devReceivedOpeningBalance">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col">Select</th>
				      <th scope="col" class="text-right">Order#</th>
				      <th scope="col" class="text-right">Customer</th>
				      <th scope="col" class="text-right">Paid Date</th>
				      <th scope="col" class="text-right">Deposit To</th>
				      <th scope="col" class="text-right">Received Type</th>
				      <th scope="col" class="text-right">Paid Amount</th>
				      <th scope="col" class="text-right">Deposited</th>
				      <th scope="col" class="text-right">Category</th>
				      <th scope="col" class="text-right">Memo</th>
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
				      <th scope="col">Select</th>
				      <th scope="col" class="text-right">Vendor</th>
				      <th scope="col" class="text-right">RMA No.</th>
				      <th scope="col" class="text-right">Paid Date</th>
				      <th scope="col" class="text-right">Deposited To</th>
				      <th scope="col" class="text-right">Received Type</th>
				      <th scope="col" class="text-right">Paid Amount</th>
				      <th scope="col" class="text-right">Deposited</th>
				      <th scope="col" class="text-right">Category</th>
				      <th scope="col" class="text-right">Memo</th>
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
				      <th scope="col">Select</th>
				      <th scope="col" class="text-right">Order#</th>
				      <th scope="col" class="text-right">Customer</th>
				      <th scope="col" class="text-right">Paid Date</th>
				      <th scope="col" class="text-right">Deposit To</th>
				      <th scope="col" class="text-right">Received Type</th>
				      <th scope="col" class="text-right">Paid Amount</th>
				      <th scope="col" class="text-right">Deposited</th>
				      <th scope="col" class="text-right">Category</th>
				      <th scope="col" class="text-right">Memo</th>
				    </tr>
				  </thead>
				  <tbody> 
				  </tbody>
				  </table>
</div> -->
				<!-- <div class="footer1">
				  </div> -->
				</div>
			  <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">...</div>
			  <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">...</div>
			  <div class="tab-pane fade" id="nav-vendorRMA" role="tabpanel" aria-labelledby="nav-vendorRMA-tab">...</div>
			</div>
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
   function selectrow(invoice,index,payId) {
	    debugger;
	  if(document.getElementById("rdoPurchaseOrder").checked)
	  {  	
	    this.indexNumber = index;
	    this.invoiceId = invoice;
	    this.indexNumber = index;
	    this.invoiceId = invoice;
	    this.paymentId = payId;
	    this.oldClientVendorId = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(2)').attr('value');
	    this.oldBankAccountId = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').attr('value');
	    this.oldPaymentTypeId = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(4)').attr('value');
		$("#checkNum").val(''); 
		$("select.paymentOP").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(12)').attr('value'));
	    $("#poNumber").text($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(2)').text());
	   	$(".poDate").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(3)').text());
	    $("#custId").text($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(2)').text());
	    $("#devAmount").text($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(7)').text());
	    $(".devPaymentAmount").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(8)').text()); 
	    $("select.devCategoryDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').attr('value'));
	    $("select.devReceivedTypeDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(4)').attr('value'));
	    $("select.devDeposittypeDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').attr('value'));
	   /*  $(".devOrderDate").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(7)').text()); */
	    $(".devMemotext").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(13)').text());
	    if($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').attr('value') != 'null')
		{
				$(".devCheck").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').attr('value'));
		}	
	    if($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(4)').attr('value') == '1')
	    {
	    		$("#Check").show();
	    		var check = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').text();
	    		$(".devCheck").val(check);
	    }
	    else
	    {
	    		$("#Check").hide();
	    }
	  }
	  if(document.getElementById("rdoConsignmentSalePayment").checked)
		  {
		  this.indexNumber = index;
		    this.invoiceId = invoice;
		    this.indexNumber = index;
		    this.invoiceId = invoice;
		    this.paymentId = payId;
		    this.oldClientVendorId = $('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(1)').attr('value');
		    this.oldBankAccountId = $('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').attr('value');
		    this.oldPaymentTypeId = $('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(3)').attr('value');
			$("#checkNum").val(''); 
		//	$("select.paymentOP").val($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(12)').attr('value'));
		   	$(".poDate").val($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(2)').text());
		    $("#custId").text($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(1)').text());
		    $("#devAmount").text($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').text());
		    $(".devPaymentAmount").val($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(8)').text()); 
		    $("select.devCategoryDrp").val($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(9)').attr('value'));
		    $("select.devReceivedTypeDrp").val($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(3)').attr('value'));
		    $("select.devDeposittypeDrp").val($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').attr('value'));
		   /*  $(".devOrderDate").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(7)').text()); */
		    $(".devMemotext").val($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(13)').text());
		   /*  if($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').attr('value') != 'null')
			{
					$(".devCheck").val($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').attr('value'));
			}	 */
		    if($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(3)').attr('value') == '1')
		    {
		    		$("#Check").show();
		    		if($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(4)').text() != null )
		    		{	
		    			$(".devCheck").val($('table.devAcPaidConsignment tbody tr:nth-child('+indexNumber+')').find('td:nth-child(4)').text());
		    		}
		    		else
		    			{
		    			$("#Check").val('');
		    			}
		    }
		    else
		    {
		    		$("#Check").hide();
		    }
		  
		  }
	 } 								 							 	
   
   function save()
   {
	   debugger;	

	  /*   var customer = document.getElementById("customerName"); */
	 	var customerId = oldClientVendorId;
	 	var payTypeString = document.getElementById("paymentType");
		var paymentTypeId = payTypeString.options[payTypeString.selectedIndex].id;
		var paymentTypeName = payTypeString.options[payTypeString.selectedIndex].label;
		var depositBank = document.getElementById("payId");
		var accountId = depositBank.options[depositBank.selectedIndex].id;
		var category = document.getElementById("categoryId");
		var categoryId = category.options[category.selectedIndex].id;
		var receivedAmount = document.getElementById("receivedAmount").value;
		var paidDate = document.getElementById("poDate").value;
		if(paymentTypeName == 'Check')
			{
					if(document.getElementById("checkNum").value == '' || document.getElementById("checkNum").value == '0')
						{
							alert("<spring:message code='BzComposer.popayable.entervalidchecknumber'/>");
							return false;
						}
			}
		
	   /* if(parseInt(receivedAmount) >parseInt(adjustTotal) )
		   {
		   		alert("<bean:message key='BzComposer.common.receivedAmtShuouldNotBeGTAmt'/>");
		   		return false;
		   } */
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
	   
	/* 	}	  
	else{
			alert("<bean:message key='BzComposer.common.selectARecordFirst'/>");
			return false;
	}   */

/* 	 sendMyValue(row,paymentTypeId,checkNo,memo,accountId,categoryId,receivedAmount); */
    sendMyValue(TblPayment,receivedAmount,paidDate);
 
  	}
   function sendMyValue(TblPayment,receivedAmount,paidDate) {
		debugger;
		var obj=JSON.stringify(TblPayment);
   	 $.ajax({
		
			type : "POST",
			url : "PaidListTabPost?tabid=UpdateRecord",
		/* 	data : "row=" + row + "&paymentTypeId=" +paymentTypeId + "&memo=" + memo + "&accountId=" +accountId + "&categoryId=" +categoryId + "&receivedAmount=" +receivedAmount, */			
				/* data :"row=" + obj + "&index="+indexNumber, */
				data : "row=" + obj + "&PaymentId=" + paymentId + "&ReceivedAmount=" +receivedAmount + "&PaidDate=" + paidDate,
		    success : function(data) {
				/* var html = "" + data.msg; */
				
			window.location = "${pageContext.request.contextPath}/PaidListTab?tabid=paidList";
			
			},
			 error : function(data) {
				 alert("<spring:message code='BzComposer.popayable.someerroroccurred'/>");
			} 
		});
   	
   	$(document.forms[0]).submit(function( event ) {
	    event.preventDefault();
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
	   				alert("<spring:message code='BzComposer.popayable.entervalidchecknumber'/>");
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
	   /*   var total = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(19)').attr('value'); */
	    /* var receivedAmountInt = parseInt(receivedAmount);
	    var totalInt = parseInt(total);  */
	    
	   /*  if(receivedAmountInt > totalInt)
	    	{
	    		
	    	  	receivedAmount = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').attr('value');
	    	}
	    else
	    	{
	    		 receivedAmount = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(18)').attr('value');
	    	} */
	   if(amount == balance)
		   {
		  		 receivedAmountString =  $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').text();
		   }
	   else
		   {
		  		 receivedAmountString = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(9)').text();
		   }
	   
	  /*  	var receivedAmount = parseInt(receivedAmountString); */   
	 /*   var orderNum = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(2)').text(); */
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
		/* 	data : "row=" + row + "&paymentTypeId=" +paymentTypeId + "&memo=" + memo + "&accountId=" +accountId + "&categoryId=" +categoryId + "&receivedAmount=" +receivedAmount, */			
				data :"row=" + obj + "&index="+indexNumber,
		    success : function(data) {
				/* var html = "" + data.msg; */
				
				
			window.location = "${pageContext.request.contextPath}/AccountReceiveble?tabid=AccountReceiveble";
			<%-- <% System.out.println(find);%>
			<% request.getSession().removeAttribute("checkNum"+find);%> --%>

			},
			 error : function(data) {
				 alert("<spring:message code='BzComposer.popayable.someerroroccurred'/>");
			} 
		});
  	
  	$(document.forms[0]).submit(function( event ) {
	    event.preventDefault();
	});
	   return false;
	   
   }
   function clearTransaction()
   {
	   if(paymentId == '' || paymentId == -1)
		   {
		   alert("<spring:message code='BzComposer.popayable.selecttransaction'/>");
		   		return false;
		   }
	   else
		   {
		   	var status = window.confirm("<spring:message code='BzComposer.popayable.clearselectedtransaction'/>");
		   	if(status != true)
		   		{
		   			return false;
		   		}
		   }
	   $.ajax({
			
			type : "POST",
			url : "PaidListTabPost?tabid=clearTransaction",
				data :"paymentId=" + paymentId,
		    success : function(data) {
		    	
		    	window.location = "${pageContext.request.contextPath}/PaidListTabPost?tabid=paidList";
		    },
			 error : function(data) {
				 alert("<spring:message code='BzComposer.popayable.someerroroccurred'/>");
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
	   $("#fromDate").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear());
	   $("#toDate").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear());
	   $("#poDate").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear());
	   $("#tblForPaidConsignment").hide();
	   var type = document.getElementById("paymentType");
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
   function poPayableTab()
   {
	   window.location = "${pageContext.request.contextPath}/PoPayable?tabid=popayable";
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
   function viewConsignmentSaleTable()
   {
	   debugger;
	   $("#tblForPaidPO").hide();
	   $("#tblForPaidConsignment").show();
   }
   function viewPaidPOTable()
   {
	   debugger;
	   $("#tblForPaidConsignment").hide();
	   $("#tblForPaidPO").show();
   }
   function consignmentTab()
   {
 	  window.location = "${pageContext.request.contextPath}/PoPayable?tabid=consignmentTab";
   }
	$( ".rangeOP" ).change(function() 
	{
	   debugger;  
	   var rangeType = document.getElementById("rangeId");
		var range = rangeType.options[rangeType.selectedIndex].value;
		$.ajax
	  	({
			type : "GET",
	 		url : "PaidListTabPost?tabid=slectedMenu&SelectedRange=" + range,
	    	success : function(data)
	    	{
	    		debugger;
		 		$(document).find('div#tblForPaidPO table').replaceWith($(data).find('div#tblForPaidPO').html());
			},
			error : function(data) 
			{
				alert("<spring:message code='BzComposer.popayable.someerroroccurred'/>");
			} 
		});
	})
</script>
</body>
</html>