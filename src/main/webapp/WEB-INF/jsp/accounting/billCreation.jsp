<%@page import="com.nxsol.bizcomposer.common.TblRecurrentPaymentPlan"%>
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
<title><spring:message code="BzComposer.billcreationtitle"/></title>
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

.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-draggable.ui-resizable
	{
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
	<% ArrayList<TblRecurrentPaymentPlan> recurrentPaymentList = (ArrayList)request.getAttribute("recurentPaymentList"); 
	ArrayList<TblAccount> getAccountForRecurrent = (ArrayList)request.getAttribute("getAccountForRecurrent");
	%>
	<html:form action="AccountReceiveble" method="post" styleId="billPayableForm">
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
											<% out.println(cvListForBill.get(i).getLastName() + " " + cvListForBill.get(i).getFirstName() + "("+ cvListForBill.get(i).getName() + ")");%>
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
									<select class="form-control devReceivedTypeDrp" id="receivedType" 
									onclick="checkType()">
									<%
										ArrayList<TblAccount> accountForBill = (ArrayList) request.getAttribute("accountListForBill");
										for (int i = 1; i < accountForBill.size(); i++) {
									%>
										<option value="<%=accountForBill.get(i).getAccountID()%>" label="<%=accountForBill.get(i).getCustomerCurrentBalance()%>">
											<% out.println(accountForBill.get(i).getName());%>
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
									<input type="text" class="form-control devOrderDate" value="" style="width: 275px" 
									name="orderDate" readonly="true" id="orderDate"> 
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
											<%out.println(categoryList.get(i).getName() + " " + categoryList.get(i).getCategoryNumber());%>
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
						<div class="col-md-4">
							<div class="form-group">
								<button class="btn btn-info" onclick="return save()" style="width:170px;font-size: 14px;">
									<spring:message code="BzComposer.global.save"/>
								</button>
							</div>
							<div class="form-group">
								<button class="btn btn-info" onclick="return clearTransaction()" style="width:170px;font-size: 14px;">
									<spring:message code="BzComposer.billpayable.cleartransactionbtn"/>
								</button>
							</div>
							<div class="form-group">
								<button class="btn btn-info" onclick="return makePayment()" style="width:170px;font-size: 14px;">
									<spring:message code="BzComposer.billpayable.makepaymentbtn"/>
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
						aria-controls="nav-home" aria-selected="true" onclick="billPayableTab()">
							<spring:message code="BzComposer.billpayable.tabs.unpaidbilllist"/>
						</a> 
						<a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" 
						role="tab" aria-controls="nav-profile" aria-selected="false" onclick="paidBillLists()">
							<spring:message code="BzComposer.billpayable.tabs.unpaidbilllist"/>
						</a> 
						<a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" 
						role="tab" aria-controls="nav-contact" aria-selected="false" onclick="">
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
									<% ArrayList<TblVendorDetail> allBill = (ArrayList) request.getAttribute("allBillLists");%> 
									<label> 
										<%= String.format("%.2f", allBill.get(allBill.size() - 1).getTotalBillAmount()) %>
									</label>
								</li>
								<li>
									<button>
										<spring:message code="BzComposer.billpayable.payandprintbtn"/>
									</button>
								</li>
								<li>
									<button onclick="AddPayee()">
										<spring:message code="BzComposer.billpayable.addpayeebtn"/>
									</button>
								</li>
							</ul>
						</div>
					</div>
					<div class="tab-content" id="nav-tabContent">
						<div class="tab-pane fade show active" id="nav-home" role="tabpanel" 
						aria-labelledby="nav-home-tab">
							<div class="row">
								<div class="col-md-3 pl-3 pr-1">
									<div class="table1" id="payeeNamesForNavigation">
										<table class="table table-bordered table-sm decACPayeeTable">
											<thead class="thead-light">
												<tr>
													<th scope="col">
														<spring:message code="BzComposer.paidbilllist.payeename"/>
													</th>
												</tr>
											</thead>
											<tbody>
											<script>
											var list = [<%
							                    for(int i=0;i<recurrentPaymentList.size();i++)
							                    {
							                    	out.println(recurrentPaymentList.get(i).getPayeeID()); 
							                    	if(recurrentPaymentList.size()-1 != i)
							                    	{
							                    		out.println(",");
							                    	}
							                    }
							                    %>];
											</script>
											<%
													ArrayList<ClientVendor> cv = (ArrayList) request.getAttribute("cvForCombo");
														int selectVendor = 2;
														for (int i = 0; i < cv.size(); i++) {
												%>
											<tr onclick="selectVendor(<%=selectVendor%>,list)">
												<td value="<%=cv.get(i).getCvID()%>"><% out.println(cv.get(i).getLastName() + " " + cv.get(i).getFirstName() + "(" + cv.get(i).getName() + ")");%></td>
												<td hidden="vendorAddress1" value="<%=cv.get(i).getAddress1()%>"></td>
											</tr>
											<%
												selectVendor++;
												}
											%>
											</tbody>
										</table>
									</div>
									<div class="recurrentPaymentListForEdit" id="recurrentPaymentListForPayee">
			                    		<table class="tableRe reCurrent">
											<thead>
												<tr>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.payeeid"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.planid"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.paymentaccountid"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billpayable.amount"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.samepaymentamount"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.lastpamentamount"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.firstpaymentdate"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billpayable.frequency"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.days"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.recurrentoption"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.numberofoption"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billpayable.status"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.lastpaymentdate"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.plansetupdate"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.active"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.global.memo"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.customercurrentbalance"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.dateadded"/>
													</th>
													<th class="text-right">
														<spring:message code="BzComposer.billcreation.paymenttypeid"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<% 
													for(int i=0;i<recurrentPaymentList.size();i++)
													{	
												%>
												<tr>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getPayeeID());%>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getPlanID());%>
													</td>
													<td class="text-right" 
													value="<%=recurrentPaymentList.get(i).getPaymentAccountID()%>">
														<% out.println(recurrentPaymentList.get(i).getPaymentAccountID());%>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getAmount()); %>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).isSamePaymentAmount());%>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getLastPaymentAmount());%>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getFirstPaymentDate()); %>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getFrequency());%>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getDays());%>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getRecurrentOption());%>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getNumberOfPayments());%>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getStatus());%>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getLastPaymentDate());%>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getPlanSetupDate());%>
													</td>
													<td class="text-right">
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getMemo());%>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getCustomerCurrentBalance()); %>
													</td>
													<td class="text-right">
														<% out.println(recurrentPaymentList.get(i).getPaymentTypeID()); %>
													</td>
												</tr>
												<% } %>
											</tbody>
										</table>		
									</div>
								</div>
								<Div class="col-md-9 pl-1 pr-3">
									<div class="table1" id="tblForInvoiceOrder">
										<table class="table table-bordered table-sm devAcRecDataTbl">
											<thead class="thead-light">
												<tr>
													<th scope="col"><spring:message code="BzComposer.ComboBox.Select"/></th>
													<th scope="col" class="text-right">
														<spring:message code="BzComposer.billpayable.billno"/>
													</th>
													<th scope="col" class="text-right">
														<spring:message code="BzComposer.billpayable.payee"/>
													</th>
													<th scope="col" class="text-right">
														<spring:message code="BzComposer.billpayable.duedate"/>
													</th>
													<th scope="col" class="text-right">
														<spring:message code="BzComposer.billpayable.paidamount"/>
													</th>
													<th scope="col" class="text-right">
														<spring:message code="BzComposer.global.balance"/>
													</th>
													<th scope="col" class="text-right">
														<spring:message code="BzComposer.billpayable.amount"/>
													</th>
													<th scope="col" class="text-right">
														<spring:message code="BzComposer.global.memo"/>
													</th>
													<th scope="col" class="text-right">
														<spring:message code="BzComposer.global.type"/>
													</th>
													<th scope="col" class="text-right">
														<spring:message code="BzComposer.billpayable.status"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<%
													ArrayList<TblVendorDetail> allBillLists = (ArrayList) request.getAttribute("allBillLists");
														int index = 1;
														for (int i = 0; i < allBillLists.size(); i++) {
												%>
												<tr onclick="selectrow(<%=allBillLists.get(i).getBillNo() + "," + index%>)">
													<td class="text-right">
														<input type="checkbox" id="Checkbox">
													</td>
													<td class="text-right">
														<% out.println(allBillLists.get(i).getBillNo());%>
													</td>
													<td class="text-right" 
													value="<%=allBillLists.get(i).getVendorId()%>">
														<% out.println(allBillLists.get(i).getVendorName());%>
													</td>
													<td class="text-right">
														<% out.println(allBillLists.get(i).getDueDate());%>
													</td>
													<td class="text-right">
														<%= String.format("%.2f", allBillLists.get(i).getAmountPaid()) %>
													</td>
													<td class="text-right">
														<%= String.format("%.2f", allBillLists.get(i).getAmountTopay()) %>
													</td>
													<td class="text-right">
														<%= String.format("%.2f", allBillLists.get(i).getAmount()) %>
													</td>
													<td class="text-right">
														<% out.println(allBillLists.get(i).getMemo());%>
													</td>
													<td class="text-right">
														<% out.println("Bill");%>
													</td>
													<td class="text-right" 
													value="<%=allBillLists.get(i).getStatus()%>">
														<% out.println(allBillLists.get(i).getStatus());%>
													</td>
													<td hidden="bankID" value="<%=allBillLists.get(i).getPayerId()%>">
													</td>
													<td hidden="check" value="<%=allBillLists.get(i).getCheckNo()%>">
													</td>
													<td hidden="creditUsed" 
													value="<%=allBillLists.get(i).getCreditUsed()%>">
													</td>
												</tr>
												<%
													index++;
													}
													String maxBillId = request.getAttribute("maxBillId").toString();
												%>
												<tr>
													<td hidden="maxBill" id="maxBillId">
														<% out.println(maxBillId);%>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="footerForCreatingRecurrent">
								<button class="btn btn-info btn1" id="CreatingEditingRecurrentPaymentId">
									<spring:message code="BzComposer.billpayable.creatinrecurringpaymentbtn"/>
								</button>
							</div>
							<div class="footer1">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option1"> 
									<label class="form-check-label" for="inlineCheckbox2">
										<spring:message code="BzComposer.billpayable.selectall"/>
									</label>
								</div>
								<a class="btn btn-info btn1" id="CreateBillButtonId" style="color:white">
									<spring:message code="BzComposer.billpayable.createbillbtn"/>
								</a>
								<a class="btn btn-info btn1" onclick="return deleteBill()" style="color:white">
									<spring:message code="BzComposer.billpayable.deletebillbtn"/>
								</a>
								<a class="btn btn-info btn1" id="PaybillId" style="color:white">
									<spring:message code="BzComposer.billpayable.paybillsbtn"/>
								</a>
								<a class="btn btn-info btn1" id="MemorizeBillId" style="color:white">
									<spring:message code="BzComposer.billpayable.memorizebillbtn"/>
								</a>
								<a class="btn btn-info btn1" id="MemorizeTransactionListId" style="color:white">
									<spring:message code="BzComposer.billpayable.memorizetransactionlistbtn"/>
								</a>
								<a class="btn btn-info btn1" id="EditRecurrentPaymentButtonId" style="color:white">
									<spring:message code="BzComposer.global.edit"/>
								</a>
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
		<div class="container-fluid" id="ScheduleMemorizedTransaction" class="">
			<!--    Here is the AddAccount dialog started -->
			<div class="table-responsive memorized-popup" style="padding: 0 15px">
				<div class="row form-group">
					<div class="col-md-12 form-inline">
						<div class="form-group">
							<label>
								<spring:message code="BzComposer.billpayable.nameoftransaction"/> 
								&nbsp;
							</label> 
							<input class="form-control" type="text" id="nameOfTransaction" />
						</div>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-md-4">
						<div class="form-check col-form-label">
							<input class="form-check-input" type="radio" name="exampleRadios" id="remindMe" value="0" 
							checked="checked" onclick="remindMe()">
							<label class="form-check-label" for="exampleRadios1">
								<spring:message code="BzComposer.billpayable.remindme"/> 
							</label>
						</div>
					</div>
					<div class="col-md-8 ">
						<div class="row">
							<label class="col-sm-5 col-form-label">
								<spring:message code="BzComposer.billpayable.howoften"/> 
								&nbsp;
							</label>
							<div class="col-sm-6">
								<select class="form-control howOften" id="howOften">
									<option value="Never">Never</option>
									<option value="Daily">Daily</option>
									<option value="Weekly">Weekly</option>
									<option value="Monthly">Monthly</option>
									<option value="Quarterly">Quarterly</option>
									<option value="Annualy">Annualy</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-md-4">
						<div class="form-check col-form-label">
							<input class="form-check-input" type="radio" name="exampleRadios" id="dontRemindMe" 
							value="1" onclick="dontRemindMe()"> 
							<label class="form-check-label" for="exampleRadios2"> 
								<spring:message code="BzComposer.billpayable.dontremindme"/> 
							</label>
						</div>
					</div>
					<div class="col-md-8 ">
						<div class="row">
							<label class="col-sm-5 col-form-label">
								<spring:message code="BzComposer.billpayable.nextdate"/> 
								&nbsp;
							</label>
							<div class="col-sm-6 d-flex">
								<input type="text" class="form-control schrduleMemorizedTransactionDate"
								id="schrduleMemorizedTransactionDate">
								<div class="input-group-append input-group-append1">
									<a class="input-group-text" href="#">&#9662;</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-md-4">
						<div class="form-check col-form-label">
							<input class="form-check-input" type="radio" name="exampleRadios" id="automaticEnter" 
							value="2" onclick="automaticEnter()">
							<label class="form-check-label" for="exampleRadios3">
								<spring:message code="BzComposer.billpayable.automaticallyenter"/> 
							</label>
						</div>
					</div>
					<div class="col-md-8">
						<div class=" row">
							<label class="col-sm-5  col-form-label">
								<spring:message code="BzComposer.billpayable.numberremaining"/>
								&nbsp;
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="numberRemaining">
							</div>
						</div>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-md-4">
						<div class="form-check col-form-label">
							<input class="form-check-input" type="radio" name="exampleRadios" id="withTransactionGroup" 
							value="3" onclick="withTransactionGroup()"> 
							<label class="form-check-label" for="exampleRadios3"> 
								<spring:message code="BzComposer.billpayable.withtransactioningroup"/> 
							</label>
						</div>
					</div>
					<div class="col-md-8 ">
						<div class=" row">
							<label class="col-sm-5 col-form-label">
								<spring:message code="BzComposer.billpayable.daysinadvancetoenter"/> 
								&nbsp;
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="daysInAdvanceToEnter">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-8 ">
						<div class="row">
							<label class="col-sm-5 col-form-label">
								<spring:message code="BzComposer.billpayable.transactiongroup"/>
								&nbsp;
							</label>
							<div class="col-sm-6">
								<select class="form-control" id="transactionGroup">
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="bzbtn text-right mt-4">
					<button type="button" class="btn btn-info" font-size: 14px; onclick="return ScheduleMemorizedTransactionOkay()"
					id="deleteBank">
						<spring:message code="BzComposer.global.ok"/>
					</button>
					<button type="button" class="btn btn-info" font-size: 14px; onclick="return addAccount()"
					id="addButtonForDeposit">
						<spring:message code="BzComposer.global.cancel"/>
					</button>
				</div>
			</div>
		</div>
		<div class="container-fluid" id="PayBills">
			<!--    Here is the AddAccount dialog started -->
			<div class="table-responsive PayBills-popup" style="padding: 0 15px">
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
									<input class="form-check-input" type="radio" name="exampleRadios2" 
									id="exampleRadios2" value="option1" checked> 
									<label class="form-check-label" for="exampleRadios2">
										<spring:message code="BzComposer.billpayable.dueonorbefore"/> 
									</label>
								</div>
							</div>
							<div class="col-md-5">
								<div class="d-flex">
									<input type="text" class="form-control" id="payBillsDate">
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
									<input class="form-check-input" type="radio" name="exampleRadios2" 
									id="exampleRadios3" value="option1">
									<label class="form-check-label" for="exampleRadios3">
										<spring:message code="BzComposer.billpayable.showallbills"/>
									</label>
								</div>
							</div>
							<div class="col-md-5"></div>
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
							<%
								ArrayList<TblVendorDetail> payBillList = (ArrayList) request.getAttribute("payBillList");
									for (int i = 0; i < payBillList.size(); i++) {
							%>
							<tr>
								<td>
									<input type="checkbox">
								</td>
								<td>
									<% out.println(payBillList.get(i).getBillNo());%>
								</td>
								<td>
									<% out.println(payBillList.get(i).getDueDate());%>
								</td>
								<td>
									<% out.println(payBillList.get(i).getVendorName());%>
								</td>
								<td>
									<% out.println(payBillList.get(i).getAmount());%>
								</td>
								<td>
									<% out.println(payBillList.get(i).getCreditUsed());%>
								</td>
								<td>
									<%out.println(payBillList.get(i).getAmountTopay());%>
								</td>
								<td>
									<% out.println(payBillList.get(i).getBankAccount());%>
								</td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
				<div class="mb-3">
					<button type="button" class="btn btn-info" style="font-size: 14px;">
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
						<input type="text" readonly class="form-control-plaintext text-right" id="staticEmail" 
						value="0.00">
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info" style="font-size: 14px;">
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
								<input type="text" readonly class="form-control-plaintext text-right" id="staticEmail"
								value="1177.23">
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
						<div class="d-flex">
							<input type="text" class="form-control">
							<div class="input-group-append input-group-append1">
								<a class="input-group-text" href="#">&#9662;</a>
							</div>
						</div>
					</div>
				</div>
				<div class="bzbtn text-right">
					<button type="button" class="btn btn-info" style="font-size: 14px;" onclick="return deleteBankAccount()" id="deleteBank">
						<spring:message code="BzComposer.billpayable.payselectedbillsbtn"/>
					</button>
					<button type="button" class="btn btn-info" style="font-size: 14px;" onclick="return editTransaction()" id="addButtonForDeposit">
						<spring:message code="BzComposer.global.cancel"/>
					</button>
				</div>
			</div>
		</div>
		<div class="container-fluid" id="MemorizeTransactionList">
			<div class="table-responsive" style="padding: 0 15px" id="MemTraList">
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
						<%
							ArrayList<TblVendorDetail> getMemorizeTransactionList = (ArrayList) request.getAttribute("getMemorizeTransactionList");
								int memTransIndex = 1;
								for (int i = 0; i < getMemorizeTransactionList.size(); i++) {
						%>
						<tr onclick="selectMemorizedTransactionList(<%=memTransIndex%>)">
							<td>
								<input type="checkbox">
							</td>
							<td hidden="billNo">
								<% out.println(getMemorizeTransactionList.get(i).getBillNo());%>
							</td>
							<td>
								<% out.println(getMemorizeTransactionList.get(i).getTransactionName());%>
							</td>
							<td>
								<% out.println(getMemorizeTransactionList.get(i).getBankAccount());%>
							</td>
							<td>
								<% out.println(getMemorizeTransactionList.get(i).getAmount());%>
							</td>
							<td value="<%=getMemorizeTransactionList.get(i).getRecurringPeriod()%>">
								<% out.println(getMemorizeTransactionList.get(i).getRecurringPeriod());%>
							</td>
							<%
								if (getMemorizeTransactionList.get(i).getRemindOption() == 2) {
							%>
							<td value="<%=getMemorizeTransactionList.get(i).getRemindOption()%>">
								<% out.println("Yes");%>
							</td>
							<%
								} else {
							%>
							<td value="<%=getMemorizeTransactionList.get(i).getRemindOption()%>">
								<% out.println("No");%>
							</td>
							<%
								}
							%>
							<td>
								<% out.println(JProjectUtil.getdateFormat().format(getMemorizeTransactionList.get(i).getNextDate()));%>
							</td>
							<td hidden="howOften">
								<%
									out.println(getMemorizeTransactionList.get(i).getHowOften());
								%>
							</td>
							<td hidden="recurringPeriod">
								<% out.println(getMemorizeTransactionList.get(i).getRecurringPeriod());%>
							</td>
							<td hidden="recurringNumber">
								<% out.println(getMemorizeTransactionList.get(i).getRecurringNumber());%>
							</td>
							<td hidden="dayInAdvance">
								<% out.println(getMemorizeTransactionList.get(i).getDaysInAdvanceToEnter());%>
							</td>
						</tr>
						<%
							memTransIndex++;
								}
						%>
					</tbody>
				</table>
				<!-- <div class="memorizedbutton">
						<ul>
							<li><button >Add</button></li>
						  	<li><button onclick="EditMemorizedTransactionList()">Edit</button></li>
						  	<li><button onclick="DeleteMemorizeTransaction()">Delete</button></li>
						  	<li><button>Close</button></li>
						  </ul>
					</div> -->
			</div>
			<div class="memorizedbutton">
				<ul>
					<li><button><spring:message code="BzComposer.global.add"/></button></li>
					<li><button onclick="EditMemorizedTransactionList()"><spring:message code="BzComposer.global.edit"/></button></li>
					<li><button onclick="DeleteMemorizeTransaction()"><spring:message code="BzComposer.global.delete"/></button></li>
					<li><button><spring:message code="BzComposer.global.close"/></button></li>
				</ul>
			</div>
		</div>
		<div class="container-fluid" id="CreatingEditingRecurrentPaymentDlgId">
			<!--    Here is the AddAccount dialog started -->
			<div class="table-responsive" style="padding: 0 15px">
				<p>
					<b>
						<spring:message code="BzComposer.billcreation.payeeinformation"/>
					</b>
					<br> 
					<spring:message code="BzComposer.paidbilllist.payeename"/> *: 
					<label id="payeeInformationForRecurrent"></label>
				</p>
				<hr>
				<div class="row">
					<div class="col-md-12">
						<b>
							<spring:message code="BzComposer.billpayable.paymentaccount"/>
						</b>
						<div class="row">
							<div class="col-sm-3 form-inline">
								<div class="form-group">
									<label>
										<spring:message code="BzComposer.billpayable.payfrom"/> *:
										&nbsp;
									</label> 
									<select id="paymentAccountForRecurrent">
									 		<%  
											for(int i=0;i<getAccountForRecurrent.size();i++)
											{	
										%>
										<option value="<%=getAccountForRecurrent.get(i).getAccountID() %>">
											<% out.println(getAccountForRecurrent.get(i).getName()); %> 
										</option>
										<% } %>
									</select>
								</div>
							</div>
							<div class="col-sm-3 form-inline">
								<div class="form-group">
									<label>
										<spring:message code="BzComposer.billpayable.paymentmethod"/> *:
										&nbsp;
									</label> 
									<select id="paymentTypeForRecurrent">
										<% ArrayList<TblPaymentType> paymentType = (ArrayList)request.getAttribute("getPayMentTypeForRecurrent"); 
										for(int i=0;i<paymentType.size();i++)
										{	
										%>
										<option value="<%=paymentType.get(i).getId() %>">
											<% out.println(paymentType.get(i).getTypeName());%>
										</option>
										<% }%>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-12">
						<b>
							<spring:message code="BzComposer.billpayable.amount"/>
						</b>
						<div class="row">
							<div class="col-sm-3 form-inline">
								<div class="form-group">
									<label>
										<spring:message code="BzComposer.billpayable.paymentamount"/> *:
										&nbsp;
									</label> 
									<input type="text" name="" id="paymentAmountForRecurrent">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4 form-inline">
								<div class="form-group">
									<label for="allAMount">
										<input id="allAmountAreSame" name="amount" type="radio" checked="checked">
										&nbsp;
										<spring:message code="BzComposer.billcreation.allpaymentaresame"/>
									</label>
								</div>
							</div>
							<div class="col-sm-3 form-inline">
								<div class="form-group">
									<label>
										<spring:message code="BzComposer.global.memo"/> :
										&nbsp;
									</label> 
									<input type="text" name="" id="memoForRecurrent">
									<span>
										<spring:message code="BzComposer.billcreation.optional"/>
									</span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4 form-inline">
								<div class="form-group">
									<label for="lastAMount"><input id="amountOfLastPaymentShouldBe" name="amount" 
									type="radio">
										&nbsp;
										<spring:message code="BzComposer.billcreation.amountoflastpayment"/>
									</label>
								</div>
							</div>
							<div class="col-sm-3 form-inline">
								<div class="form-group">
									<input type="text" name="" id="lastPaymentAmount">
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-12">
						<b>
							<spring:message code="BzComposer.billcreation.frequencyandduration"/>
						</b>
						<div class="row">
							<div class="col-sm-5 form-inline">
								<div class="form-group dateWrap">
									<label>
										<spring:message code="BzComposer.billcreation.deliverfirstpaymenton"/> 
										:
										&nbsp;
									</label> 
									<span> 
										<span class="placeholder">
											<spring:message code="BzComposer.billcreation.dateformat"/>
										</span> 
										<span class="d-flex">
											<input type="text" class="form-control" id="DeliveryFirstPaymentDate"> 
											<span class="input-group-append input-group-append1"> 
												<a class="input-group-text" href="#">&#9662;</a>
											</span>
										</span>
									</span>
								</div>
							</div>
						</div>
						<div class="row" style="margin-top: 15px;">
							<div class="col-sm-3 form-inline">
								<div class="form-group">
									<label>
										<spring:message code="BzComposer.billcreation.sendpayments"/> 
										*:
										&nbsp;
									</label> 
									<select id="sendPayments" onchange="sendPaymentsChange()">
										<option value="Weekly">Weekly</option>
										<option selected="selected" value="Monthly">Monthly</option>
										<option value="Quarterly">Quarterly</option>
										<option value="Annualy">Annualy</option>
									</select>
								</div>
							</div>
						</div>
						<div class="clearfix" style="margin: 15px;">
							<span class="form-inline"> 
								<span class="form-group">
									<label for="setdatin">
										<input id="setInDaysCheckBox" name="setdatin" type="checkbox" 
										onclick="setInDaysChange()">
										&nbsp; 
										<spring:message code="BzComposer.billcreation.setindays"/>
									</label>
								</span> 
								<span class="form-group">
									&nbsp;&nbsp; 
									<label for="setdatindays">
										<spring:message code="BzComposer.billcreation.days"/> 
										: &nbsp;
										<input id="daysFieldForRecurrent" name="setdatindays" type="text" value="30">
									</label>
								</span> 
								<span class="form-group">
									&nbsp;&nbsp; 
									<label for="setdatinnextday">
										<spring:message code="BzComposer.billcreation.nextpaymentdate"/> 
										: &nbsp;
									</label>
									<input type="text" class="form-control" id="DeliveryNextPaymentDate"> 
									<span class="input-group-append input-group-append1"> 
										<a class="input-group-text" href="#">&#9662;</a>
									</span>
								</span>
							</span>
						</div>
						<div class="row">
							<div class="col-sm-12 form-inline">
								<div class="form-group">
									<label for="chancle1">
									<input id="UntillIChange" name="untillchange" type="radio">
										&nbsp;
										<spring:message code="BzComposer.billcreation.untilchangeorcancelpayment"/>
									</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4 form-inline">
								<div class="form-group">
									<label for="chancle2">
										<input id="untillATotalNumber" name="untillchange" type="radio">
										&nbsp;
										<spring:message code="BzComposer.billcreation.untiltotalnumberof"/>
										&nbsp;
										<input type="text" class="form-control" id="numberOfPayments">
										&nbsp;
										<spring:message code="BzComposer.billcreation.paymentsent"/>
									</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4 form-inline">
								<div class="form-group">
									<label for="chancle3">
										<input id="untilButNot" name="untillchange" type="radio" checked="checked">
										&nbsp;
										<spring:message code="BzComposer.billcreation.untilbutnotafter"/> 
										&nbsp;
										<span class="d-flex"> 
											<input type="text" class="form-control" id="UntilButNotDate"> 
											<span class="input-group-append input-group-append1"> 
												<a class="input-group-text" href="#">&#9662;</a>
											</span>
										</span>
										&nbsp;
										<spring:message code="BzComposer.billcreation.dateformat"/>
									</label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="bzbtn text-right">
					<button type="button" class="btn btn-info" style="font-size: 14px;" onclick="return setUpPayment()" id="setUpPayment">
						<spring:message code="BzComposer.billcreation.setuppaymentbtn"/>
					</button>
					<button type="button" class="btn btn-info" style="font-size: 14px;" id="dontSetUpPayment">
						<spring:message code="BzComposer.billcreation.dontsetuppaymentbtn"/>
					</button>
				</div>
			</div>
		</div>
		<div class="container-fluid" id="EditingRecurrentPaymentDlgId">
			<!--    Here is the Edit Recurrent payment dialog started -->
			<div class="table-responsive" style="padding: 0 15px">
				<b>
					<spring:message code="BzComposer.billcreation.payeeinformation"/>
				</b>
				<div class="box">
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group row">
								<label class="col-md-4 col-form-label">
									<spring:message code="BzComposer.billpayable.payee"/>
									&nbsp;:
								</label>
								<div class="col-md-8">
									<label class="col-md-4 col-form-label" id="EditRecurrentPayee"></label>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group row">
								<label class="col-md-4 col-form-label">
									<spring:message code="BzComposer.billcreation.payfromaccount"/>
									:
								</label>
								<div class="col-md-8">
									<select class="form-control" id="EditRecurrentBankAccount" 
									onchange="getCustomerCurrentBalance()">
											<% ArrayList<TblAccount> account = (ArrayList)request.getAttribute("getAccountForRecurrent");
								for(int i=0;i<account.size();i++)
								{
								%>
										<option value="<%=account.get(i).getAccountID() %>" label="<%=account.get(i).getCustomerCurrentBalance() %>">
											<% out.println(account.get(i).getName());%>
										</option>
										<% } %>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label">
									<spring:message code="BzComposer.billpayable.endingbalance"/> 
									:
								</label>
								<div class="col-md-8">
									<label class="col-md-4 col-form-label" id="EndingBalanceOfAccount">
										0.00
									</label>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label">
									<spring:message code="BzComposer.billcreation.plansetupdate"/> 
									:
								</label>
								<div class="col-md-8">
									<div class="d-flex">
										<input type="text" class="form-control" id="PlanSetUpDateForEditRecurrent">
										<div class="input-group-append input-group-append1">
											<a class="input-group-text" href="#" id="billDuedateCalendar">&#9662;</a>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label">
									<spring:message code="BzComposer.billpayable.frequency"/> 
									:
								</label>
								<div class="col-md-8">
									<select class="form-control" id="EditFrequencyForRecurrent" 
									onchange="changeFrequencyForEditRecurrent()">
										<option value="Weekly">Weekly</option>
										<option selected="selected" value="Monthly">Monthly</option>
										<option value="Quarterly">Quarterly</option>
										<option value="Annualy">Annualy</option>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label">
								<input type="checkbox"> 
									<spring:message code="BzComposer.billcreation.setindays"/>
								</label>
								<div class="col-md-8">
									<spring:message code="BzComposer.billcreation.days"/> 
									:&nbsp; 
									<input type="text" value="" id="EditDaysForRecurrent">
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group row">
								<label class="col-md-4 col-form-label">
									<spring:message code="BzComposer.billpayable.paymentamount"/>&nbsp; 
									*:
								</label>
								<div class="col-md-8">
									<input class="form-control" type="text" vlaue="" id="EditPaymentAmountForRecurrent">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label">
									<spring:message code="BzComposer.billcreation.lastpaymentamt"/>
									*:
								</label>
								<div class="col-md-8">
									<input class="form-control" type="text" vlaue="" id="EditLastPaymentAmount">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label">
									<spring:message code="BzComposer.billcreation.firstpaymentdate"/> &nbsp; 
									:
								</label>
								<div class="col-md-8">
									<div class="d-flex">
										<input type="text" class="form-control" id="EditFirstPaymentDateForRecurrent">
										<div class="input-group-append input-group-append1">
											<a class="input-group-text" href="#" id="billDuedateCalendar">&#9662;</a>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label">
									<spring:message code="BzComposer.billcreation.finalpaymentdate"/> 
									&nbsp;:
								</label>
								<div class="col-md-8">
									<div class="d-flex">
										<input type="text" class="form-control" disabled="disabled" 
										id="EditFinalPaymentDateForRecurrent">
										<div class="input-group-append input-group-append1">
											<a class="input-group-text" href="#" id="billDuedateCalendar">&#9662;</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix">
					<hr>
						<div class="row justify-content-between">
							<div class="col-sm-5">
								<div class="form-inline ">
									<div class="form-group">
										<label for="exampleInputName2">
											<spring:message code="BzComposer.billcreation.totalschedulepayments"/> 
											&nbsp;:
										</label> 
										<input type="text" class="form-control" 
										id="EditTotalSchedulePaymentsForRecurernt" value="">
									</div>
								</div>
							</div>
							<div class="col-sm-5 pull-right">
								<div class="form-inline justify-content-end">
									<div class="form-group ">
										<label for="exampleInputEmail2">
											<spring:message code="BzComposer.billcreation.totalscheduleamount"/> 
											&nbsp;:
										</label> 
										<input type="text" class="form-control" id="EditTotalScheduleAmountForRecurrent"
										value="">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="bzbtn text-right">
					<button type="button" class="btn btn-info" style="font-size: 14px;" onclick="return UpdateRecurrentPayment()" id="">
						<spring:message code="BzComposer.global.update"/>
					</button>
					<button type="button" class="btn btn-info" style="font-size: 14px;" onclick="return addAccount()"
					id="addButtonForDeposit">
						<spring:message code="BzComposer.global.cancel"/>
					</button>
					<!-- <button type="button" class="btn btn-info" style="font-size: 14px;" onclick="return addAccount()" id="EditButtonForDeposit">Save</button> -->
					<button type="button" class="btn btn-info" style="font-size: 14px;" onclick="return editTransaction()"
					id="addButtonForDeposit">
						<spring:message code="BzComposer.global.close"/>
					</button>
				</div>
			</div>
		</div>
<style>
.CreateBill .form-control-plaintext {
	height: 32px;
}
.CreateBill .ui-widget-content .form-control {
	height: 27.59px
}
.CreateBill .ui-widget-content tr:first-child td:first-child .form-control
{
	width: 130px;
}
.CreateBill h5 {
	background: #f3f3f3
}
</style>
	<div class="container-fluid" id="CreateBillDlgId">
		<!--    Here is the Create Bill dialog dialog started -->
		<div class="table-responsive CreateBill" style="padding: 0 15px">
			<h5 class="text-center pt-1 pb-1">
				<spring:message code="BzComposer.billcreation.bill"/>
			</h5>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="inlineRadioOptions" id="createBillRadio" 
				value="option1" checked="checked"> 
				<label class="form-check-label" for="rdoInvoiceOrder">
					<spring:message code="BzComposer.billcreation.bill"/>
				</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoInvoiceOrder1" 
				value="option2">
				<label class="form-check-label" for="rdoInvoiceOrder1">
					<spring:message code="BzComposer.billcreation.credit"/>
				</label>
			</div>
			<div class="row form-horizontal">
				<div class="col-md-6">
					<div class="form-group row">
						<label class="col-md-4 col-form-label">
							<spring:message code="BzComposer.billpayable.payfrom"/>
						</label>
						<div class="col-md-8">
							<select class="form-control" id="selectBankForCreateBill">
								<option value="56933"><spring:message code="BzComposer.billpayable.chasebank"/></option>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-4 col-form-label">
							<spring:message code="BzComposer.billpayable.vendor"/>:
						</label>
						<div class="col-md-8">
							<input type="text" class="form-control-plaintext" id="createBillVendorName" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-4 col-form-label">
							<spring:message code="BzComposer.billcreation.address"/>&nbsp;
							:
						</label>
						<div class="col-md-8">
							<textarea class="form-control" rows="4" id="addressForVenBill" readonly="true">
							</textarea>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-4 col-form-label">
							<spring:message code="BzComposer.billpayable.category"/>
						</label>
						<div class="col-md-8">
							<select class="form-control" id="categoryForCreateBill">
								<%
									ArrayList<TblCategory> allCategory = (ArrayList) request.getAttribute("allcategoryList");
									for (int i = 0; i < allCategory.size(); i++) {
								%>
								<option value="<%=allCategory.get(i).getId()%>">
									<% out.println(allCategory.get(i).getName() + " " + allCategory.get(i).getCategoryNumber());%>
								</option>
								<%
									}
								%>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group row">
						<label class="col-md-4 col-form-label">
							<spring:message code="BzComposer.billcreation.billno"/>
						</label>
						<div class="col-md-8">
							<input type="text" class="form-control-plaintext" id="BillNumberForCreate" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-4 col-form-label">
							<spring:message code="BzComposer.billpayable.date"/>:
						</label>
						<div class="col-md-8">
							<div class="d-flex">
								<input type="text" class="form-control" id="billDateCalendarText">
								<div class="input-group-append input-group-append1">
									<a class="input-group-text" href="#" id="billDateCalendar">&#9662;</a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-4 col-form-label">
							<spring:message code="BzComposer.billcreation.billdue"/>
						</label>
						<div class="col-md-8">
							<div class="d-flex">
								<input type="text" class="form-control" id="billDuedateCalendarText">
								<div class="input-group-append input-group-append1">
									<a class="input-group-text" href="#" id="billDuedateCalendar">&#9662;</a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-4 col-form-label">
							<spring:message code="BzComposer.global.memo"/>:
						</label>
						<div class="col-md-8">
							<textarea class="form-control" rows="4" readonly="true" id="memoForCreateBill">
								<spring:message code="BzComposer.billcreation.vendorbill"/>
							</textarea>
						</div>
					</div>
				</div>
			</div>
			<div id="tabs" class="mb-3">
				<ul>
					<li>
						<a href="#tabs-1">
							<spring:message code="BzComposer.billcreation.expences"/>
						</a>
					</li>
					<li>
						<a href="#tabs-2">
							<spring:message code="BzComposer.billcreation.items"/>
						</a>
					</li>
				</ul>
				<div id="tabs-1" class="pl-2 pr-2 pt-3 pb-1">
					<div class="table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>
										<spring:message code="BzComposer.billcreation.account"/>
									</th>
									<th>
										<spring:message code="BzComposer.billpayable.amount"/>
									</th>
									<th>
										<spring:message code="BzComposer.global.memo"/>
									</th>
									<th>
										<spring:message code="BzComposer.billcreation.customservice"/>
									</th>
									<th>
										<spring:message code="BzComposer.billcreation.billable"/>
									</th>
								</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<select class="form-control" id="expenseAccountId">
												<%
													for (int i = 0; i < allCategory.size(); i++) {
												%>
												<option value="<%=allCategory.get(i).getId()%>">
													<% out.println(allCategory.get(i).getName() + " " + allCategory.get(i).getCategoryNumber());%>
												</option>
												<%
													}
												%>
											</select>
										</td>
										<td class="text-right">
											<input class="form-control" type="text" id="amountForCreateBill">
										</td>
										<td></td>
										<td>
											<select class="form-control" id="expenseClientVendorId">
												<option></option>
												<%
													ArrayList<ClientVendor> getClientForBill = (ArrayList) request.getAttribute("getClientForBill");
														for (int i = 0; i < getClientForBill.size(); i++) {
												%>
												<option value="<%=getClientForBill.get(i).getCvID() %>">
													<% out.println(getClientForBill.get(i).getLastName() + " " + getClientForBill.get(i).getFirstName()+ "(" + getClientForBill.get(i).getName() + ")");%>
												</option>
												<%
													}
												%>
											</select>
										</td>
										<td>
											<input type="checkbox" id="billableId">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div id="tabs-2" class="pl-2 pr-2 pt-3 pb-1">
						<div class="table-responsive">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th><spring:message code="BzComposer.billcreation.item"/></th>
										<th><spring:message code="BzComposer.billcreation.description"/></th>
										<th><spring:message code="BzComposer.billcreation.quantity"/></th>
										<th><spring:message code="BzComposer.billcreation.cost"/></th>
										<th><spring:message code="BzComposer.billcreation.amount"/></th>
										<th><spring:message code="BzComposer.billcreation.customer"/></th>
										<th><spring:message code="BzComposer.billcreation.billable"/></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<select class="form-control">
												<option value="Inventory">
													<spring:message code="BzComposer.billcreation.inventory"/>
												</option>
												<option value="Chase Bank">
													<spring:message code="BzComposer.billpayable.chasebank"/>
												</option>
											</select>
										</td>
										<td class="text-right">
											<input class="form-control" type="text" value="">
										</td>
										<td class="text-right">
											<input class="form-control" type="text" value="1">
										</td>
										<td class="text-right">
											<input class="form-control" type="text" value="2.14">
										</td>
										<td class="text-right">
											<input class="form-control" type="text" value="2.14">
										</td>
										<td>
											<select class="form-control">
												<option value="Inventory">
													<spring:message code="BzComposer.billcreation.inventory"/>
												</option>
												<option value="Chase Bank">
													<spring:message code="BzComposer.billpayable.chasebank"/>
												</option>
											</select>
										</td>
										<td>
											<input type="checkbox">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="bzbtn text-right">
					<button type="button" class="btn btn-info" style="font-size: 14px;">
						<spring:message code="BzComposer.billcreation.saveandnewbtn"/>
					</button>
					<button type="button" class="btn btn-info" style="font-size: 14px;" onclick="return SaveCloseBill()">
						<spring:message code="BzComposer.billcreation.saveandclosebtn"/>
					</button>
					<button type="button" class="btn btn-info" style="font-size: 14px;" onclick="closedialog();">
						<spring:message code="BzComposer.global.close"/>
					</button>
				</div>
			</div>
		</div>
	</html:form>
<script type="text/javascript">

	var billNo = -1;
	var index = -1;
	var amountToBepaid = 0.00;
	var vendorName;
	var vendorId = -1;
	var scheduleTransactionRadioButtonValue = 0;
	var indexForMemTransList = -1;
	var paymentStatus = "";
	var vendorAddress = "";
	var matchVendorIdWithRecurrentId = 0;
	var recurrentPaymentLength = 0;
	var matchIndex = 0;
	var planID = -1;
	function selectrow(no , indexNumber)
	{
		debugger;
		 this.billNo = no;
		 this.index = indexNumber;
		 this.paymentStatus = $('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(10)').attr('value');
		 $("select.paymentOP").val(paymentStatus);
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
		 this.vendorName = $('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(3)').text();
		 this.vendorId = $('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(3)').attr('value');
		 document.getElementById("nameOfTransaction").value = vendorName; 
		 $('#transactionGroup').append('<option value="'+vendorName+'" selected="selected">'+vendorName+'</option>');
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
				url : "BillCreationPost?tabid=save",
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
			url : "BillCreationPost?tabid=MakePayment",
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
	function deleteBill()
	{
		var status;
		var amount = $('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(6)').text();
		status = window.confirm("<spring:message code='BzComposer.billpayable.deleteselectedbill'/>");
		if(status == true)
{			
	$.ajax({
			
			type : "POST",
			url : "BillCreationPost?tabid=DeleteBill",
		    data :"BillNum=" + billNo,
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
else
{
	return false;
}
			
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
					debugger;
					var day = new Date().getDay();
					var dName = dayName(day);
					var dateForDue = new Date();
					dateForDue.setDate(dateForDue.getDate() - 1);
					dateForDue.setMonth(dateForDue.getMonth() + 1);
					var dayForDue = dateForDue.getDay();
					var dNameForDue = dayName(dayForDue);
					$("#tblForUnpaidOpeningBalance").hide();
					$("#tblForUnpaidCreditAmount").hide();
					$("#orderDate").val(
							dName + " " + ((new Date().getMonth()) + 1) + "-"
									+ new Date().getDate() + "-"
									+ new Date().getFullYear());
					$("#schrduleMemorizedTransactionDate").val(dName + " " + ((new Date().getMonth()) + 1) + "-" + new Date().getDate() + "-" + new Date().getFullYear());
					$("#payBillsDate").val(dName + " " + ((new Date().getMonth()) + 1) + "-" + new Date().getDate() + "-" + new Date().getFullYear());
					$("#billDateCalendarText").val(dName + " " + ((new Date().getMonth()) + 1) + "-" + new Date().getDate() + "-" + new Date().getFullYear());
					 $("#billDuedateCalendarText").val(dNameForDue + " " + (dateForDue.getMonth()+1) + "-" + dateForDue.getDate() + "-" + dateForDue.getFullYear()); 
					$("#ScheduleMemorizedTransaction").hide();
					$("#EditingRecurrentPaymentDlgId").hide();
					$("#PayBills").hide();
					$("#CreateBillDlgId").hide();
					$("#MemorizeTransactionList").hide();
					$("#CreatingEditingRecurrentPaymentDlgId").hide();
					$("#recurrentPaymentListForPayee").hide();
					$("#numberRemaining" ).prop("disabled", true );  
					$("#daysInAdvanceToEnter").prop( "disabled", true );
					$("#transactionGroup").prop( "disabled", true );
					$("#daysFieldForRecurrent").prop('disabled',true);
					$("#DeliveryFirstPaymentDate").val(dName + " " + ((new Date().getMonth()) + 1) + "-" + new Date().getDate() + "-" + new Date().getFullYear());
					var accountCombo = document.getElementById("EditRecurrentBankAccount");
					var accountBalance = accountCombo.options[accountCombo.selectedIndex].label; 
					$("#EndingBalanceOfAccount").html(accountBalance);
					$("#EditDaysForRecurrent").val(30);
					$("#EditDaysForRecurrent").prop("disabled",true);
					$("#EditTotalSchedulePaymentsForRecurernt").prop("disabled",true);
					$("#EditTotalScheduleAmountForRecurrent").prop("disabled",true);
					
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
				 if(paymentStatus == '' || paymentStatus == 'Paid')
				 {
					 alert("<spring:message code='BzComposer.billcreation.selectunpaidbilllist'/>");
					 return false;
				 }
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
			    
			   $(document.forms[0]).submit(function(event) {
				    event.preventDefault();
				});
			 });
		 $(function() { 
			 $( "#CreatingEditingRecurrentPaymentId").on("click", function(){
				/*   $("#dateForAddAccount").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear()); */
				 debugger;
				if(vendorId != matchVendorIdWithRecurrentId)
				{	
				   $( "#CreatingEditingRecurrentPaymentDlgId").dialog({
			    	   modal: true,
			    	   title: 'Creating Recurrent Payment Plan'
			        });
				}
				else
				{
					$( "#EditingRecurrentPaymentDlgId").dialog({
				    	   modal: true,
				    	   title: 'Plan Details'
				        });
					/* matchVendorIdWithRecurrentId = 0; */
				}
			    });
			    
			   $(document.forms[0]).submit(function(event) {
				    event.preventDefault();
				});
			 });
		 $(function() {
			   $( "#EditRecurrentPaymentButtonId").on("click", function(){
				/*   $("#dateForAddAccount").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear()); */
				  debugger;
				   $( "#EditingRecurrentPaymentDlgId").dialog({
			    	   modal: true,
			    	   title: 'Plan Details'
			        });   
			    });
			    	
			   $(document.forms[0]).submit(function(event) {
				    event.preventDefault();
				});
			 });
		 $(function() {
			   $( "#CreateBillButtonId").on("click", function(){
				/*   $("#dateForAddAccount").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear()); */
				  debugger;
				if(vendorId == -1)
				{
					alert("<spring:message code='BzComposer.billcreation.selectvendorfirst'/>");
					return false;
				}
				$("#createBillVendorName").val(vendorName);
				$("#addressForVenBill").val(vendorAddress);
				$("#BillNumberForCreate").val(document.getElementById("maxBillId").innerHTML);
				   $( "#CreateBillDlgId").dialog({
			    	   modal: true,
			    	   title: 'Enter Bill'
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
			$(document).find('div#recurrentPaymentListForPayee table').replaceWith($(data).find('div#recurrentPaymentListForPayee').html());
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
					alert("<spring:message code='BzComposer.billpayable.entervaliddatainnumberfield'/>");
					return false;
				}
				else{
					NumRemain = document.getElementById("numberRemaining").value;
				}
				if(document.getElementById("daysInAdvanceToEnter").value == "" )
				{
					alert("<spring:message code='BzComposer.billpayable.entervaliddataindayfield'/>");
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
				url : "BillCreationPost?tabid=MakeScheduleMemorizedTransaction",
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
		url : "BillCreationPost?tabid=UpdateMemorizedTransaction",
	    data :"BillNumber=" + bill,
	    success : function(data) {
		debugger;
		amountToBepaid = 0.00;
		updateBillPayableTab(data);	
		},
		 error : function(data) {
			 alert("<spring:message code='BzComposer.billpayable.someerroroccurred'/>");
		} 
	});
}
function selectVendor(vendorIndex,recurrentPayment)
{
	debugger;
	this.vendorId = parseInt($('table.decACPayeeTable tbody tr:nth-child('+vendorIndex+')').find('td:nth-child(1)').attr('value'));
	this.vendorAddress = $('table.decACPayeeTable tbody tr:nth-child('+vendorIndex+')').find('td:nth-child(2)').attr('value')
	this.vendorName = $('table.decACPayeeTable tbody tr:nth-child('+vendorIndex+')').find('td:nth-child(1)').text();
	this.recurrentPaymentLength = recurrentPayment.length;
	for(var i=0;i<recurrentPayment.length;i++)
	{
		if(vendorId == recurrentPayment[i])
		{
			this.matchIndex = i;
			this.matchVendorIdWithRecurrentId = vendorId;
			this.planID = $('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(2)').text();
			var Id = $('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(1)').attr('value'); 
			document.getElementById("EditRecurrentPayee").innerText = vendorName;
			$("#EditRecurrentBankAccount").val($('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(3)').attr('value'));
			$("#PlanSetUpDateForEditRecurrent").val($('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(14)').text());
			$("#EditFrequencyForRecurrent").html($('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(8)').attr('value'));
			$("#EditFinalPaymentDateForRecurrent").val($('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(13)').text());
			$("#EditFirstPaymentDateForRecurrent").val($('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(14)').text());
			$("#EditPaymentAmountForRecurrent").val($('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(4)').text());
			$("#EditLastPaymentAmount").val($('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(6)').text());
			var totalSchedulePayments = parseFloat($('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(4)').text()) * parseFloat($('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(11)').text()); 
			$("#EditTotalSchedulePaymentsForRecurernt").val($('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(11)').text());
			$("#EditTotalScheduleAmountForRecurrent").val(totalSchedulePayments);
			break;
		}
	}
	window.location.href = "BillCreation?tabid=billCreation&VendorId=" + vendorId;
    /* $.ajax({
	 	type : "GET",
		url : "BillCreation?tabid=billCreation",
	    data :"VendorId=" + vendorId,
	    success : function(data) {
	        debugger;
		    updateBillPayableTab(data);
		},
		 error : function(data) {
			 alert("<spring:message code='BzComposer.billpayable.someerroroccurred'/>");
		}
	}); */
}
function AddPayee()
{
	window.open("Vendor?tabid=AODOVO",null,"scrollbars=yes,height=750,width=1200,status=yes,toolbar=no,menubar=no,location=no," );
}
function closedialog() {
	$('#CreateBillDlgId').dialog('close');
	//$(this).dialog("close");
}
function SaveCloseBill()
{
	debugger;
	var billRadio = $("#createBillRadio").prop("checked");
	var billType = 0;
	if(billRadio == true)
	{
		billType = 0;
	}
	else
	{
		billType = 1;
	}
	var selectBankCombo = document.getElementById("selectBankForCreateBill");
	var selectedAccount = selectBankCombo.options[selectBankCombo.selectedIndex].value;
	
	var Vendorid = vendorId;
	var vendorAddress = $("#addressForVenBill").val();
	var categoryCombo = document.getElementById("categoryForCreateBill");
	var categoryId = categoryCombo.options[categoryCombo.selectedIndex].value;
	if(categoryId == '-1' || categoryId == '')
	{
		alert("<spring:message code='BzComposer.billcreation.choosevalidcategory'/>");
		return false;
	}
	var billNum = $("#BillNumberForCreate").val();
	var dateAdded = $("#billDateCalendarText").val();
	var dueDate = $("#billDuedateCalendarText").val();
	var memo = $("#memoForCreateBill").val();
	var expenseAccountCombo = document.getElementById("expenseAccountId");
	var expenseAccount = expenseAccountCombo.options[expenseAccountCombo.selectedIndex].value;
	var expenseAccountName = expenseAccountCombo.options[expenseAccountCombo.selectedIndex].text;
	if(expenseAccount == '-1' || expenseAccount == '')
	{
		alert("<spring:message code='BzComposer.billcreation.choosevalidexpenseaccount'/>");
		return false;
	}
	var expenseClicneVendorCombo = document.getElementById("expenseClientVendorId");
	var expenseClientVendorId = expenseClicneVendorCombo.options[expenseClicneVendorCombo.selectedIndex].value;
	if(expenseClientVendorId == '-1' || expenseClientVendorId == '')
	{
		alert("<spring:message code='BzComposer.billcreation.choosevalidexpensevendor'/>");
		return false;
	}
	var billaBillableRadio = $("#billableId").prop("checked");
	var billable;
	if(billaBillableRadio == true)
	{
		billable = 1;	
	}	
	else
	{
		billable = 0;
	}	
	var amount = $("#amountForCreateBill").val();
	if(amount == '0' || amount == '' || amount == '0.00' || amount == '.00' || amount == '0.0')
	{
		alert("<spring:message code='BzComposer.billcreation.entervalidamount'/>");
		return false;
	}
	TblVendorDetail = {
			"billType": billType,
			"vendorID": Vendorid,
			"vendorAddress": vendorAddress,
			"categoryID": categoryId,
			"billNo": billNum,
			"date": dateAdded,
			"dueDate": dueDate,
			"memo":memo,
			"expenseAccountId":expenseAccount,
			"expenseClientVendorId":expenseClientVendorId,
			"billAble":billable,
			"amount":amount,
			"accountId":selectedAccount,
			"expenseMemo":expenseAccountName
	};
	var obj=JSON.stringify(TblVendorDetail);
	$('#CreateBillDlgId').dialog('close');
$.ajax({
		
	 	type : "POST",
	 	url : "BillCreationPost?tabid=CreateBill",
	    data :"data=" + obj + "&VendorId=" +Vendorid ,
	    success : function(data) {
		debugger;
		updateBillPayableTab(data);	
		vendorId = -1;
		},
		 error : function(data) {
			 alert("<spring:message code='BzComposer.billpayable.someerroroccurred'/>");
		} 
	});
}
function setUpPayment()
{
	debugger;
	 var payeeId = vendorId;
	 var recurrentOption = 0;
	 var numberOfPayments = -1;
	 var lastPaymentAmount = 0.0;
	var accountCombo = document.getElementById("paymentAccountForRecurrent");
	var accountId = accountCombo.options[accountCombo.selectedIndex].value;
	var payTypeCombo = document.getElementById("paymentTypeForRecurrent");
	var paymnetTypeId = payTypeCombo.options[payTypeCombo.selectedIndex].value;
	var memo = $("#memoForRecurrent").val();
	var paymentAmount = document.getElementById("paymentAmountForRecurrent").value;
	var samePaymentAmount = $("#allAmountAreSame").prop('checked');
	var amountOfLastPayment = $("#amountOfLastPaymentShouldBe").prop('checked'); 
	if(amountOfLastPayment == true)
	{
		lastPaymentAmount = $("#lastPaymentAmount").val();
	}
	var deliverFirstPaymentdate = $("#DeliveryFirstPaymentDate").val();
	var sendPaymentCombo = document.getElementById("sendPayments");
	var frequency = sendPaymentCombo.options[sendPaymentCombo.selectedIndex].value;
	var planSetUpDate = (new Date().getMonth() + 1) + "-" + new Date().getDate() + "-" + new Date().getFullYear();
	var numOfdays = $("#daysFieldForRecurrent").val();
	var nextPaymentDate = $("#DeliveryNextPaymentDate").val();
	var untillChange = $("#UntillIChange").prop('checked');
	if(untillChange == true)
	{
		recurrentOption = 0;	
	}	
	
	var untillTotalNumber = $("#untillATotalNumber").prop('checked');
	if(untillTotalNumber == true)
	{
		numberOfPayments = $("#numberOfPayments").val();
		if(numberOfPayments == '')
		{
			alert("<spring:message code='BzComposer.billcreation.entervalidnumberofpayments'/>");
			return false;
		}
	}
	if(untillTotalNumber == true)
	{
		recurrentOption = 1;
	}
	else
		{
			recurrentOption = 2;
		}
	var lastPaymentDate = $("#UntilButNotDate").val();
	var untillButNotAfter =  $("#untilButNot").prop('checked');
	
	TblRecurrentPaymentPlan = {
			
			"PayeeID":payeeId,
			"PaymentAccountID":accountId,
			/* "isToBePrinted":, */
			"PaymentTypeID":paymnetTypeId,
			"Amount":paymentAmount,
			"SamePaymentAmount":samePaymentAmount,
			"Frequency":frequency,
			"LastPaymentAmount":lastPaymentAmount,
			"FirstPaymentDate":deliverFirstPaymentdate,
			"days":numOfdays,
			"NextPaymentDate":nextPaymentDate,
			"rdoUntillChange":untillChange,
			"rdUntilTotalOf":untillTotalNumber,
			"NumberOfPayments":numberOfPayments,
			"LastPaymentDate":lastPaymentDate,
			"RecurrentOption":recurrentOption,
			"planSetupDate":planSetUpDate,
			"memo":memo,
			"Status":'Scheduled'
	};
	var obj=JSON.stringify(TblRecurrentPaymentPlan);
	$('#CreatingEditingRecurrentPaymentDlgId').dialog('close');
$.ajax({
		
	 	type : "POST",
	    url : "BillCreationPost?tabid=setUpPayment",
	    data :"data=" + obj + "&VendorId=" +payeeId,
	    success : function(data) {
		debugger;
		updateBillPayableTab(data);	
		$(document).find('div#payeeNamesForNavigation table').replaceWith($(data).find('div#payeeNamesForNavigation').html());
		/* vendorId = -1; */
		},
		 error : function(data) {
			 alert("<spring:message code='BzComposer.billpayable.someerroroccurred'/>");
		} 
	});
}
function UpdateRecurrentPayment()
{
	debugger;
	if(planID == -1)
	{
		alert("<spring:message code='BzComposer.billcreation.selectpropervendor'/>");
		return false;
	}
	var payeeId = $('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(1)').text();  
	var paymentTypeID = $('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(18)').text();
	var samePaymentAmount = $('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(5)').text();
	var recurrentoption = $('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(10)').text();
	var memo = $('table.reCurrent tbody tr:nth-child('+(this.matchIndex + 1)+')').find('td:nth-child(16)').text();
	var accountCombo = document.getElementById("EditRecurrentBankAccount");
	var accountId = accountCombo.options[accountCombo.selectedIndex].value;
	var amount = $("#EditPaymentAmountForRecurrent").val();
	var planSetUpDate = $("#PlanSetUpDateForEditRecurrent").val();
	var firstPaymentDate = $("#EditFirstPaymentDateForRecurrent").val();
	var lastPaymentAmount = $("#EditLastPaymentAmount").val();
	var lastPaymentDate = $("#EditFinalPaymentDateForRecurrent").val();
	var days = $("#EditDaysForRecurrent").val();
	var frequencyCombo = document.getElementById("EditFrequencyForRecurrent");
	var frequency = frequencyCombo.options[frequencyCombo.selectedIndex].value;
	var numberOfPayment = $("#EditTotalSchedulePaymentsForRecurernt").val();
	var idOfPlan = parseInt(planID);
	
	var TblRecurrentPaymentPlan = {
			"PayeeID":payeeId,
			"PaymentTypeID":paymentTypeID,
			"SamePaymentAmount":samePaymentAmount,
			"PaymentAccountID":accountId,	
			"Amount":amount,
			"planSetupDate":planSetUpDate,
			"FirstPaymentDate":firstPaymentDate,
			"LastPaymentDate":lastPaymentDate,
			"LastPaymentAmount":lastPaymentAmount,
			"days":days,
			"Frequency":frequency,
			"memo":memo,
			"RecurrentOption":recurrentoption,
			"NumberOfPayments":numberOfPayment,
			"Status":'Scheduled'
	};
	var obj=JSON.stringify(TblRecurrentPaymentPlan);
	$('#EditingRecurrentPaymentDlgId').dialog('close');
	
$.ajax({
		
	 	type : "POST",
	    url : "BillCreationPost?tabid=UpdateRecurrentPayment",
	    data :"data=" + obj + "&PlanID=" +idOfPlan,
	    success : function(data) {
		debugger;
		updateBillPayableTab(data);	
		$(document).find('div#payeeNamesForNavigation table').replaceWith($(data).find('div#payeeNamesForNavigation').html());
		/* vendorId = -1; */
		},
		 error : function(data) {
			alert("<spring:message code='BzComposer.billpayable.someerroroccurred'/>");
		} 
	});
	
}
function sendPaymentsChange()
{
	debugger;
	var sendPaymentCombo = document.getElementById("sendPayments");
	var sendPayment = sendPaymentCombo.options[sendPaymentCombo.selectedIndex].value;
	
	var newDate = new Date(document.getElementById("DeliveryFirstPaymentDate").value);
	var dayNameForDeliver;
	if(sendPayment == 'Weekly')
	{
		document.getElementById("daysFieldForRecurrent").value = 7;
		newDate.setDate(newDate.getDate() + 7);
		var dayForDeliver = newDate.getDay();
		dayNameForDeliver = dayName(dayForDeliver);
	}
	else if(sendPayment == 'Monthly')
	{
		document.getElementById("daysFieldForRecurrent").value = 30;
		newDate.setDate(newDate.getDate() + 30);
		var dayForDeliver = newDate.getDay();
		dayNameForDeliver = dayName(dayForDeliver);
	}
	else if(sendPayment == 'Quarterly')
	{
		document.getElementById("daysFieldForRecurrent").value = 120;
		newDate.setDate(newDate.getDate() + 120);
		var dayForDeliver = newDate.getDay();
		dayNameForDeliver = dayName(dayForDeliver);
	}
	else if(sendPayment == 'Annualy')
	{
		document.getElementById("daysFieldForRecurrent").value = 365;
		newDate.setDate(newDate.getDate() + 365);
		var dayForDeliver = newDate.getDay();
		dayNameForDeliver = dayName(dayForDeliver);
	}
	$("#DeliveryNextPaymentDate").val(dayNameForDeliver + " " + ((newDate.getMonth()) + 1) + "-" + newDate.getDate() + "-" + newDate.getFullYear());
	$("#UntilButNotDate").val(dayNameForDeliver + " " + ((newDate.getMonth()) + 1) + "-" + newDate.getDate() + "-" + newDate.getFullYear());
	var i;
}
function changeFrequencyForEditRecurrent()
{
	var sendPaymentCombo = document.getElementById("EditFrequencyForRecurrent");
	var sendPayment = sendPaymentCombo.options[sendPaymentCombo.selectedIndex].value;
	
	var newDate = new Date(document.getElementById("PlanSetUpDateForEditRecurrent").value);
	var dayNameForDeliver;
	
	if(sendPayment == 'Weekly')
	{
		document.getElementById("EditDaysForRecurrent").value = 7;
		newDate.setDate(newDate.getDate() + 7);
		var dayForDeliver = newDate.getDay();
		dayNameForDeliver = dayName(dayForDeliver);
	}
	else if(sendPayment == 'Monthly')
	{
		document.getElementById("EditDaysForRecurrent").value = 30;
		newDate.setDate(newDate.getDate() + 30);
		var dayForDeliver = newDate.getDay();
		dayNameForDeliver = dayName(dayForDeliver);
	}
	else if(sendPayment == 'Quarterly')
	{
		document.getElementById("EditDaysForRecurrent").value = 120;
		newDate.setDate(newDate.getDate() + 120);
		var dayForDeliver = newDate.getDay();
		dayNameForDeliver = dayName(dayForDeliver);
	}
	else if(sendPayment == 'Annualy')
	{
		document.getElementById("EditDaysForRecurrent").value = 365;
		newDate.setDate(newDate.getDate() + 365);
		var dayForDeliver = newDate.getDay();
		dayNameForDeliver = dayName(dayForDeliver);
	}
	$("#EditFinalPaymentDateForRecurrent").val(((newDate.getMonth()) + 1) + "-" + newDate.getDate() + "-" + newDate.getFullYear());

}
function setInDaysChange()
{
	debugger;
	if($("#setInDaysCheckBox").prop('checked') == true)
	{	
		$("#daysFieldForRecurrent").prop('disabled',false);
	}
	else
	{
		$("#daysFieldForRecurrent").prop('disabled',true);
	}
}
function billPayableTab()
{
	window.location = "${pageContext.request.contextPath}/BillPayable?tabid=billpayable";
}
function getCustomerCurrentBalance()
{
	debugger;
	var accountCombo = document.getElementById("EditRecurrentBankAccount");
	var accountBalance = accountCombo.options[accountCombo.selectedIndex].label; 
	$("#EndingBalanceOfAccount").html(accountBalance);
}
	</script>
	<script>
  $( function() {
    $( "#tabs" ).tabs();
  } );
</script>
</body>
</html>