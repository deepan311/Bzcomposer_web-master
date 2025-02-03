<%@ page contentType="text/html;charset=UTF-8" %>
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
<title><spring:message code="BzComposer.consignmentsaletitle"/></title>

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
#popupWindow
{
	
    width: 900;
    height: 900;
}	 
.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-draggable.ui-resizable {
    min-width: 45%;
    min-height: 600px;
    overflow:"auto";
}
.btn {
margin-right: 10px;
}
.highlight { background-color: #00CED1 !important;color: #fff }	

</style>

</head>
<body>
<% int find = 0;
 ReceivableListBean rb = null;
 ArrayList<ReceivableListBean> li = null;
 String invoiceNumber = "-1";
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
						<label><spring:message code="BzComposer.popayable.ponumber"/></label>
						
						 <label id="poNumber">
						    
						      </label>
						<div class="form-group row">
							<label class="col-md-4  col-form-label"><spring:message code="BzComposer.popayable.vendorname"/></label>
							<label id="custId" for="vendorName"> </label>
								
							</div>
						
						<div class="form-group row">
							<label class="col-md-4  col-form-label"><spring:message code="BzComposer.popayable.paymenttype"/></label>
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
							<label class="col-md-4  col-form-label"><spring:message code="BzComposer.popayable.payfrom"/></label>
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
						<label class="col-md-4  col-form-label"><spring:message code="BzComposer.popayable.amount"/></label>
				    
				    	<div class="col-md-8">	
				    	<div class="input-group">
							<div class="input-group-prepend">
							 <span class="input-group-text" id="basic-addon1"><spring:message code="BzComposer.popayable.dollersign"/></span>
							 </div>
								<label  style="padding-left: 10px" id="devAmount" class="form-control"></label>
								</div>
						</div>
						</div>
						 <div class="form-group row">
							<label class="col-md-4  col-form-label"><spring:message code="BzComposer.popayable.paymentamount"/></label>
							 
					<div class="col-md-8">
						<div class="input-group">
							<div class="input-group-prepend">
							 <span class="input-group-text" id="basic-addon1"><spring:message code="BzComposer.popayable.dollersign"/></span>
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
						<%-- <div class="form-group row">
						   <label class="col-md-4  col-form-label">Order Date</label>
							<div class="col-md-8">
								<input type="text" class="form-control devOrderDate" value="" style="width: 275px">&nbsp;<img
								src="${pageContext.request.contextPath}/images/cal.gif" class="img-fluid" alt="Responsive image"
								onclick="displayCalendar(document.ReceivableListForm.orderDate,'mm-dd-yyyy',this);">
							</div>   
							
						
						</div> --%>
						<div class="form-group row">
							<label class="col-md-4  col-form-label">
								<spring:message code="BzComposer.popayable.date"/>
							</label>
						   <%--  <html:text property="orderDate" readonly="false"></html:text>  --%>
						    <div class="col-md-8 calendar-img"><input type="text" class="form-control poDate" value="" style="width: 275px" name="poDate" readonly="true" id="poDate">
							<img
								src="${pageContext.request.contextPath}/images/cal.gif" class="img-fluid" alt="Responsive image"
								onclick="displayCalendar(document.ReceivableListForm.poDate,'mm-dd-yyyy',this);">
							</div>   
									
						</div>
						<div class="form-group row">
							<label class="col-md-4  col-form-label"> 
								<spring:message code="BzComposer.popayable.paiddate"/>
							</label>
						   <%--  <html:text property="orderDate" readonly="false"></html:text>  --%>
						    <div class="col-md-8 calendar-img"><input type="text" class="form-control poPaidDate" value="" style="width: 275px" name="poPaidDate" readonly="true" id="poPaidDate">
							<img
								src="${pageContext.request.contextPath}/images/cal.gif" class="img-fluid" alt="Responsive image"
								onclick="displayCalendar(document.ReceivableListForm.poPaidDate,'mm-dd-yyyy',this);">
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
							<label class="col-md-4  col-form-label"><spring:message code="BzComposer.popayable.paymentstatus"/></label>
							<div class="col-md-8">
								<select class="form-control paymentOP" size="1" id="payStatus">
									<option value="Unpaid"><spring:message code="BzComposer.popayable.unpaid"/></option>
									<option value="Paid"><spring:message code="BzComposer.popayable.paid"/></option>
								</select>
							</div>
						</div>
						
					</div>
					<div class="col-md-4">
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
			    aria-controls="nav-home" aria-selected="true" onclick="payableLists()">
			    	<spring:message code="BzComposer.popayable.tab.popayablelist"/>
		    	</a>
			    <a class="nav-item nav-link active" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" 
			    role="tab" aria-controls="nav-profile" aria-selected="false" onclick="consignmentTab()">
			    	<spring:message code="BzComposer.popayable.tab.consignmentsalepayment"/>
		    	</a>
			    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" 
			    aria-controls="nav-contact" aria-selected="false" onclick="paidList()">
			    	<spring:message code="BzComposer.popayable.tab.paidlist"/>
		    	</a>
		    	<a class="nav-item nav-link" id="nav-vendorRMA-tab" data-toggle="tab" href="#nav-vendorRMA" role="tab"
                aria-controls="nav-contact" aria-selected="false">
                    <spring:message code="BzComposer.accountreceivable.tabs.VendorRMARefund"/>
                </a>
			  </div>
			</nav>
				<div class="table1" id="tblForInvoiceOrder">
				<table class="table table-bordered table-sm devAcRecDataTbl">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col"><spring:message code="BzComposer.popayable.pay"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.ponum"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.companyname"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.vendorname"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.category"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.payfrom"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.paymenttype"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.checknumber"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.date"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.amounttopay"/></th>
				      <th scope="col" class="text-right"><spring:message code="BzComposer.popayable.amount"/></th>
				    </tr>
				  </thead>
				  <tbody>
				   <%
				  	li = (ArrayList)request.getAttribute("consignList");		
				  	Iterator<ReceivableListBean> itr =li.iterator();
					int index = 1;
				  	while(itr.hasNext()){
				  		 rb = itr.next();
				  %> 
			 	    <tr onclick="selectrow(<%=rb.getInvoiceID()+","+index%>)">
				      <td><input type="checkbox" id="Checkbox[<%= index %>]" ></td>
				      <td class="text-right"><% out.println(rb.getPoNum()); %></td>
				      <td class="text-right"><% out.println(rb.getCompanyName()); %></td>
				      <td value=<%=rb.getCvID() %> class="text-right"><% out.println(rb.getCvName()); %></td>
				      <td class="text-right" value="<%= rb.getCategoryID() %>"><% out.println(rb.getCategoryName()); %></td>
				      <td class="text-right" id="accdrpdwn" value= "<%=rb.getBankAccountID()%>"><% out.println(rb.getAccountName()); %></td>
				      <td class="text-right" value="<%=rb.getPaymentTypeID() %>"><% out.println(rb.getPaymentTypeName()); %></td>
				      <% if(find == rb.getInvoiceID()) {%>
				      <td class="text-right"><%= request.getSession().getAttribute("checkNum"+rb.getInvoiceID())%></td>
				      <% } else {%>
				        <td class="text-right"></td>
				        <% }%>
				      <td class="text-right"><% out.println(JProjectUtil.dateFormat.format(rb.getDateAdded())); %></td>
				      <td class="text-right"><%= String.format("%.2f", rb.getBalance()) %></td>
				      <td class="text-right"><%= String.format("%.2f", rb.getAdjustedTotal()) %></td>
				      <% if(find == rb.getInvoiceID()) {%>
				        <td hidden="amtToPayID" value=<%= request.getSession().getAttribute("amtToPay"+rb.getInvoiceID())%>></td>
				        <% } else {%>
				        <td hidden="amtToPayID" value=<%= request.getSession().getAttribute("amtToPay"+rb.getInvoiceID())%>></td>
				        <% }%> 
				      
				      <%-- <td class="text-right"></td>
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
				        <% }%>  --%>
				      <%--   <% if(find == rb.getInvoiceID()) {%>
				        <td hidden="totalId" value=<%= request.getSession().getAttribute("totalPayable"+rb.getInvoiceID())%>></td>
				        <% } else {%>
				        <td hidden="totalId" value=<%= request.getSession().getAttribute("totalPayable"+rb.getInvoiceID())%>></td>
				        <% }%> 
				      <td><input type="checkbox"></td> --%>
				      
				    </tr>
			<%
			index++;
				  	}
			%>	     
		</tbody>	
	</table>
	</div>
	
			<div class="footer1">
						
						<button class="btn btn-info" style="font-size: 14px;float: right;" id="pay"><spring:message code="BzComposer.popayable.paybtn"/></button>
						<!-- <button class="float-right" onclick="return received()">Pay</button> -->
						
				  </div>
	
	
				</div>
			  <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">...</div>
			  <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">...</div>
			  <div class="tab-pane fade" id="nav-vendorRMA" role="tabpanel" aria-labelledby="nav-vendorRMA-tab">...</div>
			</div>
		
		
		</div>



	</div>	
 </html:form>
 	<div class="container" id="popupWindow">                                                                                      
  <div class="table-responsive" style="padding-top: 20px">          
  <table class="table table-bordered table-sm dlgRecDataTable" id="myTable">
   <tr><spring:message code="BzComposer.popayable.payablelist"/></tr>&nbsp;
    <thead>
      <tr>
        <th><spring:message code="BzComposer.popayable.vendor"/></th>
        <th><spring:message code="BzComposer.popayable.ponum"/></th>
        <th><spring:message code="BzComposer.popayable.category"/></th>
        <th><spring:message code="BzComposer.popayable.payfrom"/></th>
        <th><spring:message code="BzComposer.popayable.amountdue"/></th>
        <th><spring:message code="BzComposer.popayable.paid"/></th>
        <th><spring:message code="BzComposer.popayable.paymenttype"/></th>
        <th><spring:message code="BzComposer.popayable.checknumber"/></th>
      </tr>
    </thead>
    <tbody id="demo"></tbody>
  </table> 
  	<button type="button" class="btn btn-info" style="float: right;font-size: 14px;">
		<spring:message code="BzComposer.global.cancel"/>
	</button>	
  	<button type="button" class="btn btn-info" style="float: right;margin-right: 10px;font-size: 14px;"
  	onclick="payFromDialog()">
  		<spring:message code="BzComposer.popayable.paybtn"/>
	</button>  
  </div>
</div>
	                                
<script type="text/javascript">
	var indexNumber = -1;
	var amtToPay = -1;
	var invoiceId = -1;
	var vendor = "";
	var PO = "";
	var categoryd = "";
	var PayFrom = "";
	var AmtDue = 0.00;
	var paid = 0.00;
	var PType = "";
	var checkD = "";
	var payFromId = 0;
	var payTypeId = 0;
	var catId = 0;
	var obj = [];
	var vendorId = 0;
   function selectrow(invoice,index) {
	    debugger;
	      $( "#demo" ).empty();  
	    this.indexNumber = index;	
	    this.invoiceId = invoice;
	    var count = 1;
	    PO = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(2)').text();
	    vendor = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(4)').text();
	    categoryd = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').text();
	    PayFrom = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').text();
	   
	    AmtDue = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(11)').text();
	    if(parseInt($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(12)').attr('value')) != 0.0)
	    {
	    	paid = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(12)').attr('value');
	    }
	    else
	    {
	    	paid = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(11)').text();
	    }
	    vendorId = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(4)').attr('value');
	    PType = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(7)').text();
	    payFromId =  $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').attr('value');
	    catId = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').attr('value');
	    AccId = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').attr('value');
	    payTypeId = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(7)').attr('value');
	    if($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(17)').text() != 'null')
	    {
	    	checkD = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(8)').text();
	    }
	    
	    var data = {
	    		 InvoiceId : invoiceId,
	    		 venId : vendorId,
	    		 PoNum : PO,
	    		 vendorName : vendor, 
	    		 categoryName : categoryd, 
	    		 PayName:PayFrom, 
	    		 AmountDueDue : AmtDue,
	    		 PaidAmount : paid,
	    		 PaymentType : PType,
	    		 payFromID :  payFromId,
	    		 categoryId : catId,
	    		 paymentTypeId : payTypeId,
	    		 AmountDue : AmtDue,
	    		 checkNum : checkD
	    	};
	    if($('input[id="Checkbox['+indexNumber+']"]').is(':checked'))
	   { 	
	    	obj.push(data);
	   } 
	    else
	    {
	    	obj = [];
	    	/* obj.splice(obj.findIndex(obj.findIndex(x => x.InvoiceId == invoiceId)),1); */
	    }
	   	$("#checkNum").val(''); 
	    $("#poNumber").text($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(2)').text());
	   	$(".poDate").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(9)').text());
	    $("#custId").text($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(4)').text());
	    var amountString = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(8)').text();
	    var balanceString  = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').text();
	    var amount = parseInt(amountString);
	    var balance = parseInt(balanceString);
	    
	    $("#devAmount").text($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(11)').text());
	    $(".devPaymentAmount").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(10)').text()); 
	    $("select.devCategoryDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(5)').attr('value'));
	    if($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(7)').attr('value') == '1')
	    {
	    		$("#Check").show();
	    }
	    else
	    {
	    		$("#Check").hide();
	    }
	    $("select.devReceivedTypeDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(7)').attr('value'));
	    $("select.devDeposittypeDrp").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(6)').attr('value'));
	   /*  $(".devOrderDate").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(7)').text()); */
	    $(".devMemotext").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(13)').text());
	    if($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(8)').attr('value') != 'null')
		{
				$(".devCheck").val($('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(8)').text());
		}	
	
   }  
   
   $(function() {
	   $( "#pay").on("click", function(){ 
		   debugger;
		   var content;
		   $( "#demo" ).empty();  
		   if(invoiceId >= '0' && $('input[type="checkbox"]').is(':checked'))
			   {
			   $( "#popupWindow").dialog({
		    	   modal: true,
		    	   title: 'Payable'
		        });
				for(var index = 0;index<=obj.length;index++)
			{
			   content = "<tr>",
			   content += "<td value="+obj[index].venId+">"+obj[index].vendorName+"</td>";
			   content += "<td>"+obj[index].PoNum+"</td>";
			   content += "<td value="+obj[index].categoryId+">"+obj[index].categoryName+"</td>";
			   content += "<td value="+obj[index].payFromID+">"+obj[index].PayName+"</td>";
			   content += "<td>"+obj[index].AmountDue+"</td>";
			   content += "<td>"+obj[index].PaidAmount+"</td>";
			   content += "<td value="+obj[index].paymentTypeId+">"+obj[index].PaymentType+"</td>";
			   content += "<td>"+obj[index].checkNum+"</td>";
			   content += "<td hidden='invoiceId'>"+obj[index].InvoiceId+"</td>",
			   content += "</tr>";
			    $( "#demo" ).append(content); 
			   $( "#popupWindow" ).show(); 
			 
			 
		       $(document.forms[0]).submit(function(event) {
				    event.preventDefault();
				});
			   }
			}		
		   else
		   {
				alert("<spring:message code='BzComposer.popayable.selectonepayable'/>");
			   	obj = [];
			   	return false;
			}
	    });
	 });
	
   function save()
   {
	   debugger;	

	   var receivedAmount;
	    var adjustTotal = document.getElementById("devAmount").innerHTML;
		/* var receivedAmount=document.getElementById("receivedAmount").value; */
		receivedAmount = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(12)').text();
		if(parseFloat(receivedAmount) == 0.0)
			{
				receivedAmount=document.getElementById("receivedAmount").value;
				if(parseFloat(receivedAmount) > parseFloat(adjustTotal))
					{
						alert("<spring:message code='BzComposer.popayable.receivedamountismorethanamount'/>");
				   		return false;
					}
			}
		else{
				receivedAmount = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(12)').text();
				var balance = document.getElementById("receivedAmount").value;
				if(parseFloat(receivedAmount)+parseFloat(balance) > parseFloat(adjustTotal) )
				   {
						alert("<spring:message code='BzComposer.popayable.receivedamountismorethanamount'/>");
				   		return false;
				   }
		}
		
		var type = document.getElementById("paymentType");
		var ptype = type.options[type.selectedIndex].label;
		
		if(ptype == 'Check')
			{
				if(document.getElementById("checkNum").value == '0' || document.getElementById("checkNum").value == '')
					{
						alert("<spring:message code='BzComposer.popayable.entervalidchecknumber'/>");
						return false;
					}
			}
		
		this.amtToPay = document.getElementById("receivedAmount").value; 
		var vendor = $('table.devAcRecDataTbl tbody tr:nth-child('+indexNumber+')').find('td:nth-child(4)').attr('value');
	   
	   var ReceivableListBean={
			   "poNum":document.getElementById("poNumber").innerHTML,
			   "cvID":vendor,
			   "paymentTypeID":document.getElementById("paymentType").value,	   
			   "bankAccountID":document.getElementById("payId").value,
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
			url : "PoPayable?tabid=UpdateRecord",
		/* 	data : "row=" + row + "&paymentTypeId=" +paymentTypeId + "&memo=" + memo + "&accountId=" +accountId + "&categoryId=" +categoryId + "&receivedAmount=" +receivedAmount, */			
				/* data :"row=" + obj + "&index="+indexNumber, */
				data : "row=" + obj + "&invoiceId="+invoiceId,
		    success : function(data) {
				/* var html = "" + data.msg; */
				
			window.location = "${pageContext.request.contextPath}/PoPayable?tabid=consignmentTab";
			
			},
			 error : function(data) {
				 alert("<spring:message code='BzComposer.popayable.someerroroccurred'/>");
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
				 alert("<spring:message code='BzComposer.popayable.someerroroccurred'/>");
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
	   var receivedAmount = <%= request.getSession().getAttribute("amtToPay")%>;
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
   $(document).ready(function(){
		debugger;
		var day = new Date().getDay();
		var dName = dayName(day);
	   $("#poDate").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear());
	   $("#poPaidDate").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear());
	    $("#popupWindow").hide(); 
	   var type = document.getElementById("paymentType");
	   var ctype = type.options[type.selectedIndex].innerText;
	   if(ctype != 'Check')
		   {
		  		 $("#Check").hide();
		   }
	   
});
$("td#accdrpdwn").click(function(e){     //function_td
	   debugger;
	  $(this).replaceWith($("#payId"));
	 });
   function popUp()
   {
	   window.open();
   }
   
   function clearTransaction()
   {
	   var answer;
	   debugger;

	   if(parseInt(invoiceId) > 0 &&  $('input[id="Checkbox['+indexNumber+']"]').is(':checked'))
		   {
		   answer = window.confirm("<spring:message code='BzComposer.popayable.clearselectedtransaction'/>");
			  if(answer != true)
				  {
				  		return false;
				  }
		   }
	   else{
		  
		   alert("<spring:message code='BzComposer.popayable.selecttransaction'/>");
	   	   return false;
	   }
	   var invId = invoiceId; 
	   $.ajax({
			
			type : "POST",
			url : "PoPayable?tabid=clearFromConsignTab",
			data :"invoiceId=" + invId,
		    success : function(data) {
				/* var html = "" + data.msg; */
				
				window.location = "${pageContext.request.contextPath}/PoPayable?tabid=consignmentTab";
				
			},
			 error : function(data) {
				 alert("<spring:message code='BzComposer.popayable.someerroroccurred'/>");
			} 
		});
		$(document.forms[0]).submit(function( event ) {
		    event.preventDefault();
		});
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
   function billingInfo()
   {
	   debugger;
	   var h = window.screen.height;
	   var w = window.screen.width;
	   
	   var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : window.screenX;
	   var dualScreenTop = window.screenTop != undefined ? window.screenTop : window.screenY;
	   
	   var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
	   var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;
	    
	   var left = (screen.width/2) - (w / 2);
	   var top = (screen.height/2) - (h / 2);
	    
	   /* window.open("BillingInfo?tabid=showBilling","Billing ","scrollbars=yes,width="+w/2+",height="+h/2+",top=150,left="+(left+300)+",status=yes,toolbar=no,menubar=no,location=no," ); */
	   window.open("BillingInfo?tabid=showBilling","Billing ","scrollbars=yes,width="+w/2+",height="+h/2+",top=150,left="+(left+300)+",status=yes,toolbar=no,menubar=no,location=no," );
	   
	   $(document.forms[0]).submit(function( event ) {
		    event.preventDefault();
		});
   }
   function checkPaymentStatus()
   {
	   debugger;
	   	/* var pay = document.getElementById("payStatus");
	  	vat option = pay.options[pay.selectedIndex].value; */
	   $(document.forms[0]).submit(function( event ) {
		    event.preventDefault();
		});
   }
   $( ".paymentOP" ).change(function() {
	   debugger;
	  var pay = document.getElementById("payStatus");
	  var payment = pay.options[pay.selectedIndex].value; 
	 
	  if(payment == 'Layaway')
		  {
		  var option = window.confirm("<spring:message code='BzComposer.popayable.wantlayawaysinvoice'/>");
		  if(option != true)
			  {
			  		return;
			  }
		  $.ajax({
				
				type : "POST",
				url : "AccountReceiveble?tabid=layaway",
				data :"invoiceId=" + invoiceId,
			    success : function(data) {
					/* var html = "" + data.msg; */
					window.location = "${pageContext.request.contextPath}/AccountReceiveble?tabid=AccountReceiveble";
				},
				 error : function(data) {
					 alert("<spring:message code='BzComposer.popayable.someerroroccurred'/>");
				} 
			}); 		
		  }
	 });
   function layawaysTab()
   {
	  window.location = "${pageContext.request.contextPath}/Layaway?tabid=layawayTab";
   } 
  function payFromDialog()
  {
	  debugger;
	  var vendorName = $('table.dlgRecDataTable tbody tr:nth-child(1)').find('td:nth-child(1)').text();
	  var invoiceId =  $('table.dlgRecDataTable tbody tr:nth-child(1)').find('td:nth-child(9)').text();
	  var vendorId = $('table.dlgRecDataTable tbody tr:nth-child(1)').find('td:nth-child(1)').attr('value');
	  var PoNumber = $('table.dlgRecDataTable tbody tr:nth-child(1)').find('td:nth-child(2)').text();
	  var categoryId = $('table.dlgRecDataTable tbody tr:nth-child(1)').find('td:nth-child(3)').attr('value');
	  var bankAccId = $('table.dlgRecDataTable tbody tr:nth-child(1)').find('td:nth-child(4)').attr('value');
	  var AdjustedTotal = $('table.dlgRecDataTable tbody tr:nth-child(1)').find('td:nth-child(5)').text();
	  var paidAmount = $('table.dlgRecDataTable tbody tr:nth-child(1)').find('td:nth-child(6)').text();
	  var paymentTypeId = $('table.dlgRecDataTable tbody tr:nth-child(1)').find('td:nth-child(7)').attr('value');
	  var checkNum = $('table.dlgRecDataTable tbody tr:nth-child(1)').find('td:nth-child(8)').text();
	  var balance = parseInt(AdjustedTotal) - parseInt(paidAmount);
	  var ReceivableListBean={
			   "poNum":PoNumber,
			   "invoiceID":invoiceId,
			   "cvID":vendorId,
			   "paymentTypeID":paymentTypeId,	   
			   "bankAccountID":bankAccId,
			   "adjustedTotal":AdjustedTotal,	
			   "paidAmount":paidAmount,
			  /*  "balance":balance, */
			   "categoryID":categoryId,
			   "checkNum":checkNum,
	   };
	  var obj=JSON.stringify(ReceivableListBean);
	  $.ajax({
			
			type : "POST",
			url : "PoPayable?tabid=Pay",
			data :"row=" + obj,
		    success : function(data) {
				/* var html = "" + data.msg; */
				
				window.location = "${pageContext.request.contextPath}/PoPayable?tabid=consignmentTab";
				
			},
			 error : function(data) {
				 alert("<spring:message code='BzComposer.popayable.someerroroccurred'/>");
			} 
		}); 		
	  
	  $(document.forms[0]).submit(function( event ) {
		    event.preventDefault();
		});
  }
  function paidList()
  {
	  window.location = "${pageContext.request.contextPath}/PaidListTab?tabid=paidList";
  }
  function consignmentTab()
  {
	  window.location = "${pageContext.request.contextPath}/PoPayable?tabid=consignmentTab";
  }
  function payableLists()
  {
	  window.location = "${pageContext.request.contextPath}/PoPayable?tabid=popayable";
  }
  $(document).ready(function () {
	    $('tr').click(function () {
	         var selected = $(this).hasClass("highlight"); 
	         $("tr").removeClass("highlight");
	         if(!selected)
	             $(this).addClass("highlight");
	    });
	});   
   
   /*  $(document.forms[0]).submit(function( event ) {
	    event.preventDefault();
	});  */
	/*  document.forms[0].action="AccountReceiveble?tabid=saveInvoice&object="+save;
	  document.forms[0].submit(); */
</script>
</body>
</html>