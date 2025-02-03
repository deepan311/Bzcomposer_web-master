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
<title><spring:message code="BzComposer.billpayabletitle"/></title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!--  Here Is the context path -->
<script>
	var ctx = "${pageContext.request.contextPath}";
</script>
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
.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-draggable.ui-resizable {
    min-width: 55%;
    min-height: 500px;
    height: auto;
    width: 300px;
    display: block;
    z-index: 101;
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
	<form:form action="/BillPayable" method="post" styleId="billPayableForm">
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
									<select class="form-control devReceivedTypeDrp"
										id="receivedType" onclick="checkType()">
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
										<label style="padding-left: 10px" id="devAmount" class="form-control"></label>
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
										<input type="text" class="form-control devReceiveAmount"
											value="" width="20px" id="receivedAmount">
									</div>
								</div>
							</div>
							<div class="form-group row" id="Check">
								<label class="col-md-4  col-form-label">
									<spring:message code="BzComposer.billpayable.checknumber"/>
								</label>
								<div class="col-md-8">
									<input type="text" class="form-control devCheck" id="checkNum" name="checkNum" />
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
										style="width: 275px" name="orderDate" readonly="true"
										id="orderDate"> <img
										src="${pageContext.request.contextPath}/images/cal.gif"
										class="img-fluid" alt="Responsive image"
										onclick="displayCalendar(document.ReceivableListForm.orderDate,'mm-dd-yyyy',this);">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4  col-form-label">
									<spring:message code="BzComposer.billpayable.category"/>
								</label>
								<div class="col-md-8">
									<select class="form-control devCategoryDrp" size="1"
										id="categoryId">
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
								<label class="col-md-4  col-form-label"><spring:message code="BzComposer.global.memo"/></label>
								<div class="col-md-8">
									<input type="text" class="form-control devMemotext" id="memo">
								</div>
							</div>
						</div>
						<div class="col-md-4" style="top:35px;">
							<div class="form-group">
								<a class="btn btn-info" onclick="return save()" style="color:white;width:120px;font-size: 14px;">
									<spring:message code="BzComposer.global.save"/>
								</a>
							</div>
							<div class="form-group">
								<a class="btn btn-info" onclick="return clearTransaction()"  style="color:white;width:120px;font-size: 14px;">
									<spring:message code="BzComposer.global.clear"/>
								</a>
							</div>
							<div class="form-group">
								<a class="btn btn-info" onclick="return makePayment()" style="color:white;width:120px;font-size: 14px;">
									<spring:message code="BzComposer.billpayable.makepaymentbtn"/>
								</a>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="content-tabs">
				<nav>
					<div class="nav nav-tabs" id="tabId" role="tablist">
						<a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" 
						role="tab" aria-controls="nav-home" aria-selected="true">
							<spring:message code="BzComposer.billpayable.tabs.unpaidbilllist"/>
						</a> 
						<a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" 
						role="tab" aria-controls="nav-profile" aria-selected="false" onclick="paidBillLists()">
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
						<h5 class="unpaid-header-title">
							<spring:message code="BzComposer.billpayable.findpayees"/>
						</h5>
						<div class="unpaid-right" id="totalAmountLabelDiv">
							<ul>
								<li>
									<label>
										<spring:message code="BzComposer.billpayable.total"/> 
									</label>
								<% ArrayList<TblVendorDetail> unpaidBillList1 = (ArrayList) request.getAttribute("unpaidBillList"); %>
									<label>
										<%-- <% out.println(unpaidBillList1.get(unpaidBillList1.size() - 1).getTotalBillAmount()); %> --%>
									</label>
								</li>
							</ul>
						</div>
					</div>
					<div class="tab-content" id="nav-tabContent">
						<div class="tab-pane fade show active" id="nav-home"
							role="tabpanel" aria-labelledby="nav-home-tab">
							<div class="table1" id="tblForInvoiceOrder">
								<table class="table table-bordered table-sm devAcRecDataTbl">
									<thead class="thead-light">
										<tr>
											<th scope="col"><spring:message code="BzComposer.ComboBox.Select"/></th>
											<th scope="col" class="text-right"><spring:message code="BzComposer.billpayable.billno"/></th>
											<th scope="col" class="text-right"><spring:message code="BzComposer.billpayable.payee"/></th>
											<th scope="col" class="text-right"><spring:message code="BzComposer.billpayable.category"/></th>
											<th scope="col" class="text-right"><spring:message code="BzComposer.billpayable.duedate"/></th>
											<th scope="col" class="text-right"><spring:message code="BzComposer.billpayable.paidamount"/></th>
											<th scope="col" class="text-right"><spring:message code="BzComposer.global.balance"/></th>
											<th scope="col" class="text-right"><spring:message code="BzComposer.billpayable.amount"/></th>
											<th scope="col" class="text-right"><spring:message code="BzComposer.global.memo"/></th>
											<th scope="col" class="text-right"><spring:message code="BzComposer.global.type"/></th>
											<th scope="col" class="text-right"><spring:message code="BzComposer.billpayable.status"/></th>
										</tr>
									</thead>
									<tbody>
										<%
											ArrayList<TblVendorDetail> unpaidBillList = (ArrayList) request.getAttribute("unpaidBillList");
												int index = 1;
												for (int i = 0; i < unpaidBillList.size(); i++) {
										%>
										<tr
											onclick="selectrow(<%=unpaidBillList.get(i).getBillNo() + "," + index%>)">
											<td class="text-right"><input type="checkbox" id="Checkbox"></td>
											<td class="text-right"><% out.println(unpaidBillList.get(i).getBillNo());%></td>
											<td class="text-right" value="<%=unpaidBillList.get(i).getVendorId()%>"><% out.println(unpaidBillList.get(i).getVendorName());%></td>
											<td class="text-right"><%= unpaidBillList.get(i).getCategoryName()!=null?unpaidBillList.get(i).getCategoryName():"" %></td>
											<td class="text-right"><%= unpaidBillList.get(i).getDueDate() %></td>
											<td class="text-right"><%= String.format("%.2f", unpaidBillList.get(i).getAmountPaid()) %></td>
											<td class="text-right"><%= String.format("%.2f", unpaidBillList.get(i).getAmountTopay()) %></td>
											<td class="text-right"><%= String.format("%.2f", unpaidBillList.get(i).getAmount()) %></td>
											<td class="text-right"><% out.println(unpaidBillList.get(i).getMemo());%></td>
											<td class="text-right"><% out.println("Bill");%></td>
											<td class="text-right"><%= unpaidBillList.get(i).getStatus() %></td>
											<td hidden="bankID" value = "<%= unpaidBillList.get(i).getPayerId() %>"></td>
											<td hidden="check" value = "<%= unpaidBillList.get(i).getCheckNo() %>"></td>
											<td hidden="creditUsed" value = "<%= unpaidBillList.get(i).getCreditUsed() %>"></td>
											<input type="hidden" id="categoryID2<%=index%>" value="<%= unpaidBillList.get(i).getCategoryID() %>"/>
										</tr>
										<%
											index++;
											}
										%>
									</tbody>
								</table>
							</div>
							<div class="footerForCreatingRecurrent">
							<a class="btn btn-info" id="CreatingEditingRecurrentPaymentId" style="color:white;font-size: 14px;">
								<spring:message code="BzComposer.billpayable.creatinrecurringpaymentbtn"/>
							</a>
							</div>
							<div class="footer1">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option1">
									<label class="form-check-label" for="inlineCheckbox2">
										<spring:message code="BzComposer.billpayable.selectall"/>
									</label>
								</div>
								<button type="button" class="btn btn-info" style="font-size: 14px;">
                                    <spring:message code="BzComposer.billpayable.payandprintbtn"/>
                                </button>
                                <button type="button" onclick="AddPayee()" class="btn btn-info" style="font-size: 14px;">
                                    <spring:message code="BzComposer.billpayable.addpayeebtn"/>
                                </button>
								<button type="button" class="btn btn-info" onclick="billingInfo()" style="font-size: 14px;">
									<spring:message code="BzComposer.billpayable.createbillbtn"/>
								</button>
								<button type="button" class="btn btn-info" onclick="return deleteBill()" style="font-size: 14px;">
									<spring:message code="BzComposer.billpayable.deletebillbtn"/>
								</button>
								<button type="button" class="btn btn-info" id="PaybillId" style="font-size: 14px;">
									<spring:message code="BzComposer.billpayable.paybillsbtn"/>
								</button>
								<button type="button" class="btn btn-info" id="MemorizeBillId" style="font-size: 14px;">
									<spring:message code="BzComposer.billpayable.memorizebillbtn"/>
								</button>
								<button type="button" class="btn btn-info" id="MemorizeTransactionListId" style="font-size: 14px;">
									<spring:message code="BzComposer.billpayable.memorizetransactionlistbtn"/>
								</button>
							</div>
						</div>
						<div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">...</div>
						<div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">...</div>
					</div>
				</div>
			</div>
		</div>
<div class="container-fluid" id="ScheduleMemorizedTransaction" class="">
<!--    Here is the AddAccount dialog started -->
	<div class="table-responsive memorized-popup" style="padding:0 15px">
		<div class="row form-group">  
		   <div class="col-md-12 form-inline">
			   	<div class="form-group">
				   	<label>
				   		<spring:message code="BzComposer.billpayable.nameoftransaction"/> 
				   		&nbsp;
			   		</label> 
				   	<input class="form-control" type="text" id="nameOfTransaction"/>   
			   </div>
		   </div>
	   	</div>
	   <div class="row form-group">
	   		<div class="col-md-4">
	   			<div class="form-check col-form-label">
				  <input class="form-check-input" type="radio" name="exampleRadios" id="remindMe" value="0" checked="checked" onclick="remindMe()">
				  <label class="form-check-label" for="exampleRadios1">
				    <spring:message code="BzComposer.billpayable.remindme"/>
				  </label>
				</div>
			</div>
			 <div class="col-md-8 ">
			  	<div class="row">
			  		<label class="col-sm-5 col-form-label">
			  			<spring:message code="BzComposer.billpayable.howoften"/>&nbsp;
		  			</label>
			  		<div class="col-sm-6"><select class="form-control howOften" id="howOften">
			  			<option value="Never">Never</option>
			  			<option value="Daily">Daily</option>
			  			<option value="Weekly">Weekly</option>
			  			<option value="Monthly">Monthly</option>
			  			<option value="Quarterly">Quarterly</option>
			  			<option value="Annualy">Annualy</option>
			  		</select></div>
			  	</div>
			</div>		
		</div>
		
		<div class="row form-group">
	   		<div class="col-md-4">
	   			<div class="form-check col-form-label">
				  <input class="form-check-input" type="radio" name="exampleRadios" id="dontRemindMe" value="1"  onclick="dontRemindMe()">
				  <label class="form-check-label" for="exampleRadios2">
				    <spring:message code="BzComposer.billpayable.dontremindme"/>
				  </label>
				</div>
			</div>		
			 <div class="col-md-8 ">
			  	<div class="row">
			  		<label class="col-sm-5 col-form-label">
			  			<spring:message code="BzComposer.billpayable.nextdate"/>&nbsp;
		  			</label>
			  		<div class="col-sm-6 d-flex"><input type="text" class="form-control schrduleMemorizedTransactionDate" id="schrduleMemorizedTransactionDate">
			  		<div class="input-group-append input-group-append1">
						 <a class="input-group-text" href="#">&#9662;</a>
					</div></div>
			  	</div>
			</div>		
		</div>
		
		<div class="row form-group">
	   		<div class="col-md-4">
	   			<div class="form-check col-form-label">
				  <input class="form-check-input" type="radio" name="exampleRadios" id="automaticEnter" value="2" onclick="automaticEnter()">
				  <label class="form-check-label" for="exampleRadios3">
				    <spring:message code="BzComposer.billpayable.automaticallyenter"/>
				  </label>
				</div>
			</div>		
			 <div class="col-md-8" >
			  	<div class=" row" >
			  		<label class="col-sm-5  col-form-label">
			  			<spring:message code="BzComposer.billpayable.numberremaining"/>&nbsp;
		  			</label>
			  		<div class="col-sm-6"><input type="text" class="form-control" id="numberRemaining"></div>
			  	</div>
			</div>		
		</div>
		
		<div class="row form-group">
	   		<div class="col-md-4">
	   			<div class="form-check col-form-label">
				  <input class="form-check-input" type="radio" name="exampleRadios" id="withTransactionGroup" value="3" onclick="withTransactionGroup()">
				  <label class="form-check-label" for="exampleRadios3">
				    <spring:message code="BzComposer.billpayable.withtransactioningroup"/>
				  </label>
				</div>
			</div>		
			 <div class="col-md-8 " >
			  	<div class=" row">
			  		<label class="col-sm-5 col-form-label">
			  			<spring:message code="BzComposer.billpayable.daysinadvancetoenter"/>&nbsp;
		  			</label>
			  		<div class="col-sm-6"><input type="text" class="form-control" id="daysInAdvanceToEnter"></div>
			  	</div>
			</div>		
		</div>
		
		<div class="row">
	   		<div class="col-md-4">
			</div>		
			 <div class="col-md-8 " >
			  	<div class="row">
			  		<label class="col-sm-5 col-form-label">
			  			<spring:message code="BzComposer.billpayable.transactiongroup"/>&nbsp;
		  			</label>
			  		<div class="col-sm-6"><select class="form-control" id="transactionGroup">
			  		</select></div>
			  	</div>
			</div>		
		</div>
	   	<div class="bzbtn text-right mt-4">
			<button type="button" style="font-size: 14px;" class="btn btn-info" onclick="return ScheduleMemorizedTransactionOkay()" id="deleteBank">
				<spring:message code="BzComposer.global.ok"/>
			</button>	
			<button type="button" style="font-size: 14px;" class="btn btn-info" onclick="return addAccount()"
			id="addButtonForDeposit">
				<spring:message code="BzComposer.global.cancel"/>
			</button>
	  	</div> 
	</div>
</div>
<div class="container-fluid" id="PayBills">                                   <!--    Here is the AddAccount dialog started -->
<div class="table-responsive PayBills-popup" style="padding:0 15px">
	<h4 class="popup-title1">
		<spring:message code="BzComposer.billpayable.selectbilltopay"/>
	</h4>
	<div class="row">
		<div class="col-md-8">
			<div class="row ">
				<label class="col-md-2 col-form-label">
					<spring:message code="BzComposer.billpayable.showbills"/>
				</label>
				<div class="col-md-4">
					<div class="form-check col-form-label">
					  <input class="form-check-input" type="radio" name ="exampleRadios2" id="exampleRadios2" value="option1" checked>
					  <label class="form-check-label" for="exampleRadios2">
					    <spring:message code="BzComposer.billpayable.dueonorbefore"/>
					  </label>
					</div>
				</div>
				<div  class="col-md-5">
					<div class="d-flex"><input type="text" class="form-control" id="payBillsDate">
				  		<div class="input-group-append input-group-append1">
							 <a class="input-group-text" href="#">&#9662;</a>
						</div>
					</div>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-md-2 col-form-label"></label>
				<div class="col-md-4">
					<div class="form-check col-form-label">
					  <input class="form-check-input" type="radio" name="exampleRadios2" id="exampleRadios3" value="option1" >
					  <label class="form-check-label" for="exampleRadios3">
					    <spring:message code="BzComposer.billpayable.showallbills"/>
					  </label>
					</div>
				</div>
				<div  class="col-md-5">
				</div>
			</div>
		</div>
		<div class="col-md-4 form-inline align-items-start">
			<label class="col-form-label">
				<spring:message code="BzComposer.billpayable.sortbillsby"/> 
				&nbsp;
			</label>
			<select class="form-control">
				<option value="Due Date"><spring:message code="BzComposer.billpayable.duedate"/></option>
				<option value="Vendor"><spring:message code="BzComposer.billpayable.vendor"/></option>
				<option value="Amount Due"><spring:message code="BzComposer.billpayable.amountdue"/></option>
			</select>
		</div>
	</div>	
	<div class="table1 popup-table1 mb-2 payBillsTableDiv">
	<table class="table table-bordered table-sm">
		<thead class="thead-light">
		<tr>
			<th><spring:message code="BzComposer.ComboBox.Select"/></th>
			<th><spring:message code="BzComposer.billpayable.billnumber"/></th>
			<th><spring:message code="BzComposer.billpayable.duedate"/></th>
			<th><spring:message code="BzComposer.billpayable.vendor"/></th>
			<th><spring:message code="BzComposer.billpayable.amtdue"/></th>
			<th><spring:message code="BzComposer.billpayable.creditused"/></th>
			<th><spring:message code="BzComposer.billpayable.amttopay"/></th>
			<th><spring:message code="BzComposer.billpayable.payfrom"/></th>
		</tr>
		</thead>
		<tbody>
		<% ArrayList<TblVendorDetail> payBillList = (ArrayList)request.getAttribute("payBillList");
		   for(int i=0;i<payBillList.size();i++)
		   {	   
		%>
			<tr>
				<td><input type="checkbox"></td>
				<td><% out.println(payBillList.get(i).getBillNo()); %></td>
				<td><% out.println(payBillList.get(i).getDueDate()); %></td>
				<td><% out.println(payBillList.get(i).getVendorName()); %></td>
				<td><% out.println(payBillList.get(i).getAmount()); %></td>
				<td><% out.println(payBillList.get(i).getCreditUsed()); %></td>
				<td><% out.println(payBillList.get(i).getAmountTopay()); %></td>
				<td><% out.println(payBillList.get(i).getBankAccount()); %></td>
				
			</tr>
			<% } %>
		</tbody>
	</table>
	</div>
	<div class="mb-3">
		<button type="button" style="font-size: 14px;" class="btn btn-info">
			<spring:message code="BzComposer.billpayable.selectallbillsbtn"/>
		</button>	
	</div>
	<h4 class="popup-title1">
		<spring:message code="BzComposer.billpayable.creditinformation"/>
	</h4>
	
	  <div class=" row">
	    <label for="staticEmail" class="col-sm-3 col-form-label">
	    	<spring:message code="BzComposer.billpayable.numberofcredits"/>
    	</label>
	    <div class="col-sm-3">
	      <input type="text" readonly class="form-control-plaintext  text-right" id="staticEmail" value="0">
	    </div>
	  </div>
	  
	  <div class="form-group row">
	    <label for="staticEmail" class="col-sm-3 col-form-label">
	    	<spring:message code="BzComposer.billpayable.totalcreditsavailable"/>
    	</label>
	    <div class="col-sm-3">
	      <input type="text" readonly class="form-control-plaintext text-right" id="staticEmail" value="0.00">
	    </div>
	    <div class="col-sm-3">
	    	<button type="button" style="font-size: 14px;" class="btn btn-info">
	    		<spring:message code="BzComposer.billpayable.setcreditsbtn"/>
    		</button>
	    </div>
	  </div>
	  <div class="row">
	  	<div class="col-md-5">
	  		<label>
	  			<spring:message code="BzComposer.billpayable.paymentaccount"/>
  			</label>
	  		<select class="form-control mb-2">
	  			<option value="Chase Bank">
	  				<spring:message code="BzComposer.billpayable.chasebank"/>
  				</option>
	  		</select>
	  		<div class="form-group row">
	  			<label for="staticEmail" class="col-sm-6 col-form-label">
	  				<spring:message code="BzComposer.billpayable.endingbalance"/>
  				</label>
			    <div class="col-sm-6">
			      <input type="text" readonly class="form-control-plaintext text-right" id="staticEmail" value="1177.23">
			    </div>
	  		</div>
	  	</div>
	  	<div class="col-md-4">
	  		<label>
	  			<spring:message code="BzComposer.billpayable.paymentmethod"/>
  			</label>
	  		<select class="form-control mb-2">
	  			<option value="Check"><spring:message code="BzComposer.billpayable.check"/></option>
	  		</select>
	  	</div>
	  	<div class="col-md-3">
	  		<label>
	  			<spring:message code="BzComposer.billpayable.paymentdate"/>
  			</label>
	  		<div class="d-flex"><input type="text" class="form-control">
		  		<div class="input-group-append input-group-append1">
					 <a class="input-group-text" href="#">&#9662;</a>
				</div>
			</div>
	  	</div>
	  </div>
	<div class="bzbtn text-right">
		<button type="button" style="font-size: 14px;" class="btn btn-info" onclick="return deleteBankAccount()" id="deleteBank">
			<spring:message code="BzComposer.billpayable.payselectedbillsbtn"/>
		</button>
		<button type="button" style="font-size: 14px;" class="btn btn-info" onclick="return editTransaction()" id="addButtonForDeposit">
			<spring:message code="BzComposer.global.cancel"/>
		</button>
	</div> 
</div>
</div> 
<div class="container-fluid" id="MemorizeTransactionList">                                   <!--    Here is the AddAccount dialog started -->
<div class="table-responsive" style="padding:0 15px" id="MemTraList">
 <table class="table" id="MemorizetranId">
    <thead>
      <tr>
       <th></th>
        <th><spring:message code="BzComposer.billpayable.transactionname"/></th>
        <th><spring:message code="BzComposer.billpayable.sourceaccount"/></th>
        <th><spring:message code="BzComposer.billpayable.amount"/></th>
        <th><spring:message code="BzComposer.billpayable.frequency"/></th>
        <th><spring:message code="BzComposer.billpayable.auto"/></th>
        <th><spring:message code="BzComposer.billpayable.nextdate"/></th>
      </tr>
    </thead>
    <tbody>
    <% ArrayList<TblVendorDetail> getMemorizeTransactionList = (ArrayList)request.getAttribute("getMemorizeTransactionList");
    	int memTransIndex = 1;
    	for(int i=0;i<getMemorizeTransactionList.size();i++)
    	{	
    %>
      <tr onclick="selectMemorizedTransactionList(<%=memTransIndex %>)">
        <td><input type="checkbox"></td>
        <td hidden="billNo"><% out.println(getMemorizeTransactionList.get(i).getBillNo()); %></td>
        <td><% out.println(getMemorizeTransactionList.get(i).getTransactionName()); %></td>
        <td><% out.println(getMemorizeTransactionList.get(i).getBankAccount()); %></td>
        <td><% out.println(getMemorizeTransactionList.get(i).getAmount()); %></td>
        <td value="<%=getMemorizeTransactionList.get(i).getRecurringPeriod()%>"><% out.println(getMemorizeTransactionList.get(i).getRecurringPeriod()); %></td>
        <% if(getMemorizeTransactionList.get(i).getRemindOption() == 2) {%>
        <td value="<%= getMemorizeTransactionList.get(i).getRemindOption() %>"><% out.println("Yes");%></td>
        <% } else { %>
        <td value="<%= getMemorizeTransactionList.get(i).getRemindOption() %>"><% out.println("No");%></td>
        <% } %>
         <td><% out.println(JProjectUtil.getdateFormat().format(getMemorizeTransactionList.get(i).getNextDate())); %></td> 
       <td hidden="howOften"><% out.println(getMemorizeTransactionList.get(i).getHowOften()); %></td>
        <td hidden="recurringPeriod"><% out.println(getMemorizeTransactionList.get(i).getRecurringPeriod()); %></td>
        <td hidden="recurringNumber"><% out.println(getMemorizeTransactionList.get(i).getRecurringNumber()); %></td>
        <td hidden="dayInAdvance"><% out.println(getMemorizeTransactionList.get(i).getDaysInAdvanceToEnter()); %></td>
      </tr>
      <%memTransIndex ++; } %>
    </tbody>
  </table>
  
  <!-- <div class="memorizedbutton">
  <ul>
  <li><button style="font-size: 14px;">Add</button></li>
  <li><button style="font-size: 14px;" onclick="EditMemorizedTransactionList()">Edit</button></li>
  <li><button style="font-size: 14px;" onclick="DeleteMemorizeTransaction()">Delete</button></li>
  <li><button style="font-size: 14px;">Close</button></li>
    
  </ul>
    </div> -->
  
  </div>
  <div class="memorizedbutton">
  <ul>
  <li><button style="font-size: 14px;"><spring:message code="BzComposer.global.add"/></button></li>
  <li><button style="font-size: 14px;" onclick="EditMemorizedTransactionList()"><spring:message code="BzComposer.global.edit"/></button></li>
  <li><button style="font-size: 14px;" onclick="DeleteMemorizeTransaction()"><spring:message code="BzComposer.global.delete"/></button></li>
  <li><button style="font-size: 14px;"><spring:message code="BzComposer.global.close"/></button></li>
    
  </ul>
    </div>
 </div> 
 <div class="container-fluid" id="CreatingEditingRecurrentPaymentDlgId">                                   <!--    Here is the AddAccount dialog started -->
<div class="table-responsive" style="padding:0 15px">
<div class="row">  
   <div class="col-md-12">
   	<div class="inline-form">
   	<label>
   		<spring:message code="BzComposer.billpayable.nameoftransaction"/>
	</label> 
   	<div><input type="text"/></div>   
   </div>
   </div>
   </div>
   <div class="form-check form-check-inline">
	  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoInvoiceOrder" value="option1" 
	  checked="checked">
	<label class="form-check-label" for="inlineRadio1">
	  	<spring:message code="BzComposer.billpayable.invoiceorder"/>
	</label>
	</div>		
 
   <div class="bzbtn text-center">
  	<button type="button" style="font-size: 14px;" class="btn btn-info" onclick="return deleteBankAccount()" id="deleteBank">
  		<spring:message code="BzComposer.global.delete"/>
	</button>	
  	<button type="button" style="font-size: 14px;" class="btn btn-info" onclick="return addAccount()" id="addButtonForDeposit">
  		<spring:message code="BzComposer.global.save"/>
	</button>
  <!-- <button type="button" style="font-size: 14px;" class="btn btn-info" onclick="return addAccount()" id="EditButtonForDeposit">Save</button> -->
  	<button type="button" style="font-size: 14px;" class="btn btn-info" onclick="return editTransaction()" id="addButtonForDeposit">
  		<spring:message code="BzComposer.global.cancel"/>
	</button>
  </div> 
  </div>
 </div> 
	</form:form>
	<script type="text/javascript">
	var billNo = -1;
	var index = -1;
	var amountToBepaid = 0.00;
	var vendorName;
	var vendorId = -1;
	var scheduleTransactionRadioButtonValue = 0;
	var indexForMemTransList = -1;
	function selectrow(no, indexNumber)
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
		 $("#orderDate").val($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(5)').text());
		 if($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(12)').attr('value') != 'null' || $('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(12)').attr('value') != '')
		{
			 $(".devCheck").val($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(12)').attr('value'));
		}
		 this.vendorName = $('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(3)').text();
		 this.vendorId = $('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(3)').attr('value');
		 document.getElementById("nameOfTransaction").value = vendorName; 
		 $('#transactionGroup').append('<option value="'+vendorName+'" selected="selected">'+vendorName+'</option>');
		 document.getElementById("categoryId").value = document.getElementById("categoryID2"+indexNumber).value;
	}

	function selectMemorizedTransactionList(memTransListIndex)
	{
		debugger;
		this.indexForMemTransList = memTransListIndex;
		this.billNo = $('table#MemorizetranId tbody tr:nth-child('+indexForMemTransList+')').find('td:nth-child(2)').text();
		var name = $('table#MemorizetranId tbody tr:nth-child('+indexForMemTransList+')').find('td:nth-child(3)').text();
		document.getElementById("nameOfTransaction").value = name;
		if($('table#MemorizetranId tbody tr:nth-child('+indexForMemTransList+')').find('td:nth-child(7)').attr('value') == '2')
		{
			$("#automaticEnter").prop('checked',true);
			automaticEnter();
			$("#numberRemaining").val($('table#MemorizetranId tbody tr:nth-child('+indexForMemTransList+')').find('td:nth-child(11)').text());
			$("#daysInAdvanceToEnter").val($('table#MemorizetranId tbody tr:nth-child('+indexForMemTransList+')').find('td:nth-child(12)').text());
		}
		else if($('table#MemorizetranId tbody tr:nth-child('+indexForMemTransList+')').find('td:nth-child(7)').attr('value') == '0')
		{
			$("#remindMe").prop('checked',true);
			remindMe();
		}
		else if($('table#MemorizetranId tbody tr:nth-child('+indexForMemTransList+')').find('td:nth-child(7)').attr('value') == '1')
		{
			$("#dontRemindMe").prop('checked',true);
			dontRemindMe();
		}
		else if($('table#MemorizetranId tbody tr:nth-child('+indexForMemTransList+')').find('td:nth-child(7)').attr('value') == '3')
		{
			$("#withTransactionGroup").prop('checked',true);
			withTransactionGroup();
		}
		$("select.howOften").val($('table#MemorizetranId tbody tr:nth-child('+indexForMemTransList+')').find('td:nth-child(6)').attr('value'));
		var dateString = $('table#MemorizetranId tbody tr:nth-child('+indexForMemTransList+')').find('td:nth-child(8)').text();
		var day = new Date(dateString).getDay();
		var dName = dayName(day);
		var date = dName + " " + dateString;
		$("#schrduleMemorizedTransactionDate").val(date);
		$('#transactionGroup').append('<option value="'+name+'" selected="selected">'+name+'</option>');
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

			return enterchecknumberdialog();
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

					 return showerrordialog();
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
		 var index = document.getElementById("receivedType").selectedIndex
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

			return enterchecknumberdialog();
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

				 return showerrordialog()
			} 
		});
  	
  	$(document.forms[0]).submit(function( event ) {
	    event.preventDefault();
	});
	}
	function deleteBill()
	{
		var status;
		var amount = $('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(6)').text();
		/* status = window.confirm("<spring:message code='BzComposer.billpayable.deleteselectedbill'/>")
		if(status == true)
		{			
			$.ajax({
			
				type : "POST",
				url : "BillPayable?tabid=DeleteBill",			
		    	data :"BillNum=" + billNo,
		    	success : function(data) {
					debugger;
					updateBillPayableTab(data);	
				},
			 	error : function(data) {

					return showerrordialog();
				} 
			});
			$(document.forms[0]).submit(function( event ) {
	    	event.preventDefault();
			});
		}
		else
		{
			return false;
		}	 */
		
		debugger;
		event.preventDefault();
		$("#deleteselectedbilldialog").dialog({
			resizable: false,
		    height: 200,
		    width: 500,
		    modal: true,
		    buttons: {
				"<spring:message code='BzComposer.global.ok'/>": function () {
					$.ajax({
						
						type : "POST",
						url : "billPayablePost?tabid=DeleteBill",
				    	data :"BillNum=" + billNo,
				    	success : function(data) {
							debugger;
							updateBillPayableTab(data);	
						},
					 	error : function(data) {

							return showerrordialog();
						} 
					});
					$(document.forms[0]).submit(function( event ) {
			    	event.preventDefault();
					});
					$(this).dialog("close");
					/* $('form').submit(); */
				},
		        "<spring:message code='BzComposer.global.cancel'/>": function () {
					$(this).dialog("close");
					return false;
				}
			}
		});
		return false;
	}
	function paidBillLists()
	{
		window.location = "${pageContext.request.contextPath}/BillPayable?tabid=PaidBillLists";
	}
	function billCreationTab()
	{
		window.location = "${pageContext.request.contextPath}/BillCreation?tabid=billCreation";
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
					$("#schrduleMemorizedTransactionDate").val(dName + " " + ((new Date().getMonth()) + 1) + "-" + new Date().getDate() + "-" + new Date().getFullYear());
					$("#payBillsDate").val(dName + " " + ((new Date().getMonth()) + 1) + "-" + new Date().getDate() + "-" + new Date().getFullYear());
					$("#ScheduleMemorizedTransaction").hide();
					$("#PayBills").hide();
					$("#MemorizeTransactionList").hide();
					$("#CreatingEditingRecurrentPaymentDlgId").hide();
					$("#numberRemaining" ).prop("disabled", true );  
					$("#daysInAdvanceToEnter").prop( "disabled", true );
					$("#transactionGroup").prop( "disabled", true );
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
		    $("#categoryId option:contains(Rent & lease 6610)").attr('selected', 'selected');
		    setTimeout(function() {
               $('table.devAcRecDataTbl tbody tr:nth-child(1)').trigger('click');
            }, 1000);
            $('tr').click(function () {
                 var selected = $(this).hasClass("highlight");
                 $("tr").removeClass("highlight");
                 if(!selected)
                     $(this).addClass("highlight");
            });
		});
		 $(function() {
			   $( "#MemorizeTransactionListId").on("click", function(){
				/*   $("#dateForAddAccount").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear()); */
				  debugger;
				   $( "#MemorizeTransactionList").dialog({
			    	   modal: true,
			    	   title: 'Memorized Transaction List'
			        });   
			    });
			    
			   $(document.forms[0]).submit(function(event) {
				    event.preventDefault();
				});
			 });
		 $(function() {
			   $( "#MemorizeBillId").on("click", function(){
				/*   $("#dateForAddAccount").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear()); */
				  debugger;
				   $( "#ScheduleMemorizedTransaction").dialog({
			    	   modal: true,
			    	   title: 'Schedule Memorized Transaction'
			        });   
			    });
			    
			   $(document.forms[0]).submit(function(event) {
				    event.preventDefault();
				});
			 });
		 $(function() {
			   $( "#PaybillId").on("click", function(){
				/*   $("#dateForAddAccount").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear()); */
				  debugger;
				   $( "#PayBills").dialog({
			    	   modal: true,
			    	   title: 'Pay Bill'
			        });   
			    });
			    
			   $(document.forms['billPayableForm']).submit(function(event) {
				    event.preventDefault();
				});
			 });
		 $(function() {
			   $( "#CreatingEditingRecurrentPaymentId").on("click", function(){
				/*   $("#dateForAddAccount").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear()); */
				  debugger;
				   $( "#CreatingEditingRecurrentPaymentDlgId").dialog({
			    	   modal: true,
			    	   title: 'Creating Recurrent Payment Plan'
			        });   
			    });
			    
			   $(document.forms[0]).submit(function(event) {
				    event.preventDefault();
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
			$(document).find('div#MemTraList table').replaceWith($(data).find("div#MemTraList").html());
			addCss();
			
		}
		function remindMe()
		{
			debugger;
			/* $("#numberRemaining").children().attr("disabled", "disabled"); */
			/* document.getElementById("numberRemaining").disabled = true; */
			/* $("#numberRemaining").disable(); */
			this.scheduleTransactionRadioButtonValue = document.getElementById("remindMe").value;
			 $("#numberRemaining" ).prop("disabled", true );  
			$("#daysInAdvanceToEnter").prop( "disabled", true );
			$("#transactionGroup").prop( "disabled", true );
		}
		function dontRemindMe()
		{
			debugger;
			this.scheduleTransactionRadioButtonValue = document.getElementById("dontRemindMe").value;
			$( "#numberRemaining" ).prop( "disabled", true );
			$( "#daysInAdvanceToEnter" ).prop( "disabled", true );
			$( "#transactionGroup" ).prop( "disabled", true );
			$( "#howOften" ).prop( "disabled", true );
			$( "#schrduleMemorizedTransactionDate" ).prop( "disabled", true );
		}
		function automaticEnter()
		{
			debugger;
			this.scheduleTransactionRadioButtonValue = document.getElementById("automaticEnter").value;
			$( "#howOften" ).prop( "disabled", false );
			$( "#schrduleMemorizedTransactionDate" ).prop( "disabled", false );
			$( "#numberRemaining" ).prop( "disabled", false);
			$( "#daysInAdvanceToEnter" ).prop( "disabled", false);
		}
		function withTransactionGroup()
		{
			debugger;
			this.scheduleTransactionRadioButtonValue = document.getElementById("withTransactionGroup").value;
			$( "#howOften" ).prop( "disabled", true );
			$( "#schrduleMemorizedTransactionDate" ).prop( "disabled", true );
			$( "#numberRemaining" ).prop( "disabled", true);
			$( "#daysInAdvanceToEnter" ).prop( "disabled", true);
			$( "#transactionGroup" ).prop( "disabled", false);
		}
		function ScheduleMemorizedTransactionOkay()
		{
			debugger;
			var remindOption = scheduleTransactionRadioButtonValue;
			var transactionName = "";
			var dayInAdvance = "";
			var howOften = "";
			var nextDate = "";
			var NumRemain = 0;
			var RecurringOption = "";
			var RecurringNumber = "";	
			
			if(remindOption == '0')
			{
				transactionName = document.getElementById("nameOfTransaction").value;
				dayInAdvance = 0;
				var howOftenOption = document.getElementById("howOften");
				howOften = howOftenOption.options[howOftenOption.selectedIndex].value; 
				nextDate = document.getElementById("schrduleMemorizedTransactionDate").value;
				NumRemain = 0;
			}
			else if(remindOption == '1')
			{
				transactionName = document.getElementById("nameOfTransaction").value;
				dayInAdvance = 0;
				howOften = "";
				nextDate = document.getElementById("schrduleMemorizedTransactionDate").value;	
			}
			else if(remindOption == '2')
			{
				transactionName = document.getElementById("nameOfTransaction").value;
				var howOftenOption = document.getElementById("howOften");
				howOften = howOftenOption.options[howOftenOption.selectedIndex].value;
				
				if(document.getElementById("numberRemaining").value == "" )
				{

					return entervalidnumberdialog();
					return false;
				}
				else{
					NumRemain = document.getElementById("numberRemaining").value;
				}
				if(document.getElementById("daysInAdvanceToEnter").value == "" )
				{

					return entervaliddaysinadvancefielddialog();
					return false;
				}
				else{
					dayInAdvance =  document.getElementById("daysInAdvanceToEnter").value;
				}
				
				nextDate = document.getElementById("schrduleMemorizedTransactionDate").value;
			}
			else if(remindOption == '3')
			{
				transactionName = "<none>";
				remindOption = 0;
				nextDate = document.getElementById("schrduleMemorizedTransactionDate").value;
				RecurringOption = 2;
				dayInAdvance =1;
				howOften = "Never";
				RecurringNumber = 2;
			}
			
			TblVendorDetail = {
					"billNo": billNo,
					"transactionName": transactionName,
					"memorizedOption": remindOption,
					"howOften": howOften,
					"numRemain": NumRemain,
					"dayInAdv": dayInAdvance,
					"nextDateString": nextDate,
			};
			var obj=JSON.stringify(TblVendorDetail);
			$('#ScheduleMemorizedTransaction').dialog('close');
			if($('#MemorizeTransactionList').parents('.ui-dialog:visible').length)
			{
				$('#MemorizeTransactionList').dialog('close');
			}
			/* var staus = $('#MemorizeTransactionList').dialog('isOpen');
			if(status == true)
			{
				$('#MemorizeTransactionList').dialog('close');
			} */
			$.ajax({
				
			 	type : "POST",
				url : "billPayablePost?tabid=MakeScheduleMemorizedTransaction",
			    data :"data=" + obj,
			    success : function(data) {
				debugger;
				amountToBepaid = 0.00;
				updateBillPayableTab(data);	
				},
				 error : function(data) {

					 return showerrordialog();
				} 
			});
	  	
	  	$(document.forms[0]).submit(function( event ) {
		    event.preventDefault();
		});
}
function EditMemorizedTransactionList()
{
	debugger;
	$( "#ScheduleMemorizedTransaction").dialog({
 	   modal: true,
 	   title: 'Schedule Memorized Transaction'
     }); 
}
function DeleteMemorizeTransaction()
{
	debugger;
	if($('#MemorizeTransactionList').parents('.ui-dialog:visible').length)
	{
		$('#MemorizeTransactionList').dialog('close');
	}
	if($('#ScheduleMemorizedTransaction').parents('.ui-dialog:visible').length)
	{
		$('#ScheduleMemorizedTransaction').dialog('close');
	}
	var bill = parseInt($('table#MemorizetranId tbody tr:nth-child('+indexForMemTransList+')').find('td:nth-child(2)').text());
	$.ajax({
		
	 	type : "POST",
		url : "billPayablePost?tabid=UpdateMemorizedTransaction",
	    data :"BillNumber=" + bill,
	    success : function(data) {
		debugger;
		amountToBepaid = 0.00;
		updateBillPayableTab(data);	
		},
		 error : function(data) {

			return showerrordialog();
		} 
	});
}
function AddPayee()
{
	/* window.location = "${pageContext.request.contextPath}/Vendor?tabid=AODOVO"; */
	window.open("Vendor?tabid=AODOVO",null,"scrollbars=yes,height=700,width=1300,status=yes,toolbar=no,menubar=no,location=no," );
}	
function enterchecknumberdialog()
{
	event.preventDefault();
	$("#enterchecknumberdialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function showerrordialog()
{
	event.preventDefault();
	$("#showerrordialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function entervalidnumberdialog()
{
	event.preventDefault();
	$("#entervalidnumberdialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function entervaliddaysinadvancefielddialog()
{
	event.preventDefault();
	$("#entervaliddaysinadvancefielddialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
</script>
</body>
</html>
<!-- Dialog box used in this page -->
<div id="enterchecknumberdialog" style="display:none;">
	<p><spring:message code='BzComposer.billpayable.entervalidchecknumber'/></p>
</div>
<div id="showerrordialog" style="display:none;">
	<p><spring:message code='BzComposer.billpayable.someerroroccurred'/></p>
</div>
<div id="deleteselectedbilldialog" style="display:none;">
	<p><spring:message code='BzComposer.billpayable.deleteselectedbill'/></p>
</div>
<div id="entervalidnumberdialog" style="display:none;">
	<p><spring:message code='BzComposer.billpayable.entervaliddatainnumberfield'/></p>
</div>
<div id="entervaliddaysinadvancefielddialog" style="display:none;">
	<p><spring:message code='BzComposer.billpayable.entervaliddataindayfield'/></p>
</div>