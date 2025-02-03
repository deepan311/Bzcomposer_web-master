<%@ page isELIgnored="false"%>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
.highlight { background-color: #00CED1 !important;color: #fff }	
 
	
</style>

</head>
<body>
<% int find = 0; %>
<div id="ddcolortabsline">&nbsp;</div>
 <form:form action="AccountReceiveble" method="post" styleId="cancelledTabForm" >
	<div class="content1 clearfix">
		<h3 class="title1">
		    <spring:message code="BzComposer.accountreceivable.tabs.cancelled"/>
		</h3>
		 <% ReceivableListBean receivablelistbean=(ReceivableListBean)request.getAttribute("Selectedrow");
		 	TblPaymentType payment = (TblPaymentType)request.getAttribute("SelectedPayment");
		 	TblAccount Selectedaccount = (TblAccount)request.getAttribute("SelectedAccount");
		 	TblCategory SelectedCategory = (TblCategory)request.getAttribute("SelectedCategory");
		 	TblPayment SelcetedPaymentForCheck = (TblPayment)request.getAttribute("Payment");
		 	/* System.out.println(request.getSession().getAttribute("checkNum")); */
		 	/* int find = 0; */
		 	
		 	/* if(request.getSession().getAttribute("indexNumber") != null)
		 	{
		 	  find = Integer.parseInt(request.getSession().getAttribute("indexNumber").toString()); 
		 	  System.out.println(find);
		 	} */
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
							    <spring:message code="BzComposer.accountreceivable.orderdate"/>
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
								<select class="form-control" size="1">
									<option><spring:message code="BzComposer.accountreceivable.unpaid"/></option>
                                    <option><spring:message code="BzComposer.accountreceivable.paid"/></option>
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
							<button class="btn btn-info" style="width:100px;font-size: 14px;" onclick="return clearTransaction()">
							    <spring:message code="BzComposer.global.clear"/>
							</button>
						</div> 
					</div>
				</div>
			</form>
		</div>
				
		<div class="content-tabs">
			
			<nav>
			   <div class="nav nav-tabs" id="tabId" role="tablist">
			    <a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true" onclick="receivableList()"><spring:message code="BzComposer.accountreceivable.tabs.receivablelist"/></a>
			    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false"><spring:message code="BzComposer.accountreceivable.tabs.overdue"/></a>
			    <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false" onclick="cancelledTab()"><spring:message code="BzComposer.accountreceivable.tabs.cancelled"/></a>
			    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false"><spring:message code="BzComposer.accountreceivable.tabs.vendorrma"/></a>
			    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false" onclick="layawaysTab()"><spring:message code="BzComposer.accountreceivable.tabs.layaways"/></a>
			    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false" onclick="receivedTab()"><spring:message code="BzComposer.accountreceivable.tabs.received"/></a>
			    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">eSales</a>
			  </div>
			</nav>
			<div class="tab-content" id="nav-tabContent">
			  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
				<div class="table1" id="tblForInvoiceOrder">
				<table class="table table-bordered table-sm devAcRecDataTbl">
				  <thead class="thead-light">
				    <tr>
                        <th scope="col"><spring:message code="BzComposer.accountreceivable.select"/></th>
                      <th scope="col" class="text-right"><spring:message code="BzComposer.sales.Order"/></th>
                      <th scope="col"><spring:message code="BzComposer.accountreceivable.companyname"/></th>
                      <th scope="col"><spring:message code="BzComposer.accountreceivable.customername"/></th>
                      <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.orderdate"/></th>
                      <th scope="col" class="text-right"><spring:message code="BzComposer.global.term"/></th>
                      <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.duedate"/></th>
                      <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.amount"/></th>
                      <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.received"/></th>
                      <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.balance"/></th>
                      <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.lineofcredit"/></th>
                      <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.availablecredit"/></th>
                      <th scope="col" class="text-right"><spring:message code="BzComposer.accountreceivable.category"/></th>
                      <th scope="col"><spring:message code="BzComposer.global.memo"/></th>
                      <th scope="col"><spring:message code="BzComposer.accountreceivable.consigned"/></th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  	ArrayList<ReceivableListBean> li = (ArrayList)request.getAttribute("ReceivableList");		
				  	Iterator<ReceivableListBean> itr =li.iterator();
					int index = 1;
				  	while(itr.hasNext()){
				  		ReceivableListBean rb = itr.next();
				  %>
				    <tr onclick="selectrow(<%=rb.getInvoiceID()+","+index%>)">
				      <td><input type="checkbox" id="Checkbox"></td>
				      <td class="text-right"><% out.println(rb.getOrderNum()); %></td>
				      <td><% out.println(rb.getCompanyName()); %></td>
				      <td value=<%=rb.getCvID() %>><% out.println(rb.getCvName()); %></td>
				      <td class="text-right"><% out.println(JProjectUtil.dateFormat.format(rb.getDateAdded())); %></td>
				      <td class="text-right"><% out.println(rb.getTblterm()); %></td>
				      <td class="text-right"><% out.println(JProjectUtil.dateFormat.format(rb.getDateAdded())); %></td>
				      <td class="text-right"><%= String.format("%.2f", rb.getAdjustedTotal()) %></td>
				      <td class="text-right"><%= String.format("%.2f", rb.getPaidAmount()) %></td>
				      <td class="text-right"><%= String.format("%.2f", rb.getBalance()) %></td>
				      <td class="text-right"><%= String.format("%.2f", rb.getCustomercreditline()) %></td>
				      <td class="text-right"><%= String.format("%.2f", rb.getRemainingcreditamount()) %></td>
				      <td class="text-right" value=<%=rb.getCategoryID() %>><% out.println(rb.getTblcategory()); %></td>
				      <td class="text-right"><% out.println(rb.getMemo()); %></td>
				      <td hidden="PaymentTypeID" value="<%=rb.getPaymentTypeID()%>"></td>
				      <td hidden="BankAccountId" value="<%=rb.getBankAccountID()%>"></td>
				         <% if(find == rb.getInvoiceID()) {%>
				      <td hidden="CheckNumberID" value=<%= request.getSession().getAttribute("checkNum"+rb.getInvoiceID())%>></td>
				      <% } else {%>
				        <td hidden="CheckNumberID" value=<%= request.getSession().getAttribute("checkNum"+rb.getInvoiceID())%>></td>
				        <% }%>
				        <% if(find == rb.getInvoiceID()) {%>
				        <td hidden="amtToPayID" value=<%= request.getSession().getAttribute("amtToPay"+rb.getInvoiceID())%>></td>
				        <% } else {%>
				        <td hidden="amtToPayID" value=<%= request.getSession().getAttribute("amtToPay"+rb.getInvoiceID())%>></td>
				        <% }%> 
				        <% if(find == rb.getInvoiceID()) {%>
				        <td hidden="totalId" value=<%= request.getSession().getAttribute("totalPayable"+rb.getInvoiceID())%>></td>
				        <% } else {%>
				        <td hidden="totalId" value=<%= request.getSession().getAttribute("totalPayable"+rb.getInvoiceID())%>></td>
				        <% }%> 
				      <td><input type="checkbox"></td>
				      
				    </tr>
			<%
			index++;
				  	}
			%>	    
		</tbody>	
	</table>
	</div>
</div>
</div>	
</div>



	</div>			

 </form:form>                                
<script type="text/javascript">
	var indexNumber = -1;
	var amtToPay = -1;
	var invoiceId = -1;
    selectChaseBankFromDropDownList("depositId");

   function selectrow(invoice,index) {
	    debugger;
	    this.indexNumber = index;
	    this.invoiceId = invoice;
	    var count = 1;
	    var matchFind = <%= request.getSession().getAttribute("indexNumber")%>;
	   	$("#checkNum").val(''); 
	    $("#ordernumber").text($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(2)').text());
	    $("select.devCutNameDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(4)').attr('value'));
	    var amountString = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(8)').text();
	    var balanceString  = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').text();
	    var amount = parseInt(amountString);
	    var balance = parseInt(balanceString);
	    <%-- if(amount == balance)
	    	{
	    		$(".devReceiveAmount").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').text());
	    	}
	    else
	    	{
	    		var checkReceived = <%= request.getSession().getAttribute("checkReceived"+) %>;
	    		$(".devReceiveAmount").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(9)').text());
	    	} --%>
	    $("#devAmount").text($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(8)').text());
	    $(".devReceiveAmount").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').text()); 
	    $("select.devCategoryDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(13)').attr('value'));
	    if($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(15)').attr('value') == '192' || $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(15)').attr('value') == '1')
	    {
	    		$("#Check").show();
	    }
	    else
	    {
	    		$("#Check").hide();
	    }
	    $("select.devReceivedTypeDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(15)').attr('value'));
	    $("select.devDeposittypeDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(16)').attr('value'));
	    $(".devOrderDate").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(7)').text());
	    $(".devMemotext").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(14)').text());
	 /*    $(".devCheck").val($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(17)').attr('value')); */
	 	/* if(matchFind != inx)
	 		{ */
			 	/* $(".devCheck").val($('table.devRecDataTblForcheck tbody tr:nth-child(2)').find('td:nth-child(1)').attr('value')); */
				if($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(17)').attr('value') != 'null')
					{
	 					$(".devCheck").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(17)').attr('value'));
					}	
			 	
	 	/* 	} */
	 
	 	/* $('table.devRecDataTblForcheck tbody tr:nth-child(2)').find('td:nth-child(1)').attr('value'); */
	    /* var ve = $("select.devReceivedTypeDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+index+')').find('td:nth-child(15)').attr('value')) == 'check'; */ 
   }  
								 							 	
   
   function save()
   {
	   debugger;	

	   var receivedAmount;
	    var adjustTotal = document.getElementById("devAmount").innerHTML;
		/* var receivedAmount=document.getElementById("receivedAmount").value; */
		receivedAmount = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(9)').text();
		if(parseFloat(receivedAmount) == 0.0)
			{
				receivedAmount=document.getElementById("receivedAmount").value;
				if(parseFloat(receivedAmount) > parseFloat(adjustTotal))
					{
						alert("<bean:message key='BzComposer.common.receivedAmtShuouldNotBeGTAmt'/>");
				   		return false;
					}
			}
		else{
				receivedAmount = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(9)').text();
				var balance = document.getElementById("receivedAmount").value;
				if(parseFloat(receivedAmount)+parseFloat(balance) > parseFloat(adjustTotal) )
				   {
				   		alert("<bean:message key='BzComposer.common.receivedAmtShuouldNotBeGTAmt'/>");
				   		return false;
				   }
		}
		
		this.amtToPay = document.getElementById("receivedAmount").value; 
	
	   
	   var ReceivableListBean={
			   "orderNum":document.getElementById("ordernumber").innerHTML,
			   "cvID":document.getElementById("customerName").value,
			   "paymentTypeID":document.getElementById("receivedType").value,	   
			   "bankAccountID":document.getElementById("depositId").value,
			   "adjustedTotal": document.getElementById("devAmount").innerHTML,
			   "balance":document.getElementById("receivedAmount").value,
			   "amtToPay":amtToPay,
			   "categoryID":document.getElementById("categoryId").value,
			   "memo":document.getElementById("memo").value,
			   "checkNum":document.getElementById("checkNum").value,
	   }
	   
    sendMyValue(ReceivableListBean);
 
  	}
   function sendMyValue(ReceivableListBean) {
		debugger;
		var obj=JSON.stringify(ReceivableListBean);
		
   	 $.ajax({
		
			type : "POST",
			url : "AccountReceiveble?tabid=UpdateRecord",
				data : "row=" + obj + "&invoiceId="+invoiceId,
		    success : function(data) {
				
				updateCancelledTab(data);
			/* window.location = "${pageContext.request.contextPath}/AccountReceiveble?tabid=AccountReceiveble"; */
			
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
           if(checkNo == '0' || checkNo == ''){
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
				
			updateCancelledTab(data);
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

   $(document).ready(function(){
		debugger;
		var day = new Date().getDay();
		var dName = dayName(day);
	   $("#tblForUnpaidOpeningBalance").hide();
	   $("#tblForUnpaidCreditAmount").hide();
	   $("#orderDate").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear());
	   var type = document.getElementById("receivedType");
	   var ctype = type.options[type.selectedIndex].innerText;
	   if(ctype != 'Check')
		   {
		  		 $("#Check").hide();
		   }
	   
});
   
   function popUp()
   {
	   window.open();
   }
   
   function clearTransaction()
   {
	   var answer;
	   debugger;
	   if(parseInt(invoiceId) <= 0){
            alert("<bean:message key='BzComposer.accountreceivable.selecttransaction'/>");
            return false;
       }
	   else{
		  answer = window.confirm("Are you sure want to Clear the Transaction ?");
		  if(answer != true)
			  {
			  		return false;
			  }
	   }
	   var invId = invoiceId; 
	   $.ajax({
			
			type : "POST",
			url : "CancelledTabPost?tabid=ClearTransaction",
			data :"invoiceId=" + invId,
		    success : function(data) {
				/* var html = "" + data.msg; */
				
				updateCancelledTab(data);
				/* window.location = "${pageContext.request.contextPath}/CancelledTab?tabid=canCelledTab"; */
				
			},
			 error : function(data) {
				alert("<bean:message key='BzComposer.common.erroroccurred'/>");
			} 
		});
		$(document.forms[0]).submit(function( event ) {
		    event.preventDefault();
		});
   }
   $(document).ready(function () {
	    $('tr').click(function () {
	         var selected = $(this).hasClass("highlight"); 
	         $("tr").removeClass("highlight");
	         if(!selected)
	             $(this).addClass("highlight");
	    });
	}); 
   function receivableList()
   {
	   window.location = "${pageContext.request.contextPath}/AccountReceiveble?tabid=AccountReceiveble";
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
   function layawaysTab()
  {
    window.location = "${pageContext.request.contextPath}/Layaway?tabid=layawayTab";
  }
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
   function updateCancelledTab(data)
   {
	   document.getElementById("devAmount").innerHTML = "";
	   $("#cancelledTabForm")[0].reset();
	   $(document).find('div#tblForInvoiceOrder table').replaceWith($(data).find('div#tblForInvoiceOrder').html());
	   addCss();
   }
    
</script>

</body>
</html>