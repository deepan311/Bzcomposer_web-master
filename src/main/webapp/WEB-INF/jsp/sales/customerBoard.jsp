<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<c:if test="${not empty AdminURL && AdminURL=='administer'}">
    <%@include file="/WEB-INF/jsp/include/menuAdmin.jsp"%>
</c:if>
<c:if test="${empty AdminURL}">
    <%@include file="/WEB-INF/jsp/include/menu.jsp"%>
</c:if>
<title><spring:message code="BzComposer.customerinfotitle" /></title>
<style>
.redColor{color: red;}
.dataTables_filter{font-size: 14px;}
.dataTables_info{font-size: 14px;}
.dataTables_paginate{font-size: 14px;}
</style>
<script type="text/javascript">
flag_state = 0;
var funsequence = 0;
var _1 = navigator.userAgent.toLowerCase();
var ___ = (_1.indexOf("msie") != -1);
var ___5 = (_1.indexOf("msie 5") != -1);
var _io = (_1.indexOf("opera") != -1);
var _im = (_1.indexOf("mac") != -1);
var ____gi = (_1.indexOf("gecko") != -1);
var i____s = (_1.indexOf("safari") != -1);
var o = null;
var o22 = null;
var o33 = null;
var oT = null;
var r = null;
function c(r) 
{
	if (___) {
    	var t = (___5) ? "Microsoft.XMLHTTP" : "Msxml2.XMLHTTP";
    	try {
      		o = new ActiveXObject(t);
      		o.onreadystatechange = r;
    	} 
    	catch (ex) {
      		alert("<spring:message code='BzComposer.common.needToEnableActiveXObject'/> ts.." + ex);
    	}
  	} else {
    	o = new XMLHttpRequest();
    	o.onload = r;
    	o.onerror = r;
  	}
  	return o;
}
function oGET(oo, url) {
	try {
    	oo.open("GET", url, true);	
    	oo.send(null);
  	} 
	catch (ex) 
	{}
}

function writeSelectTH(){
	if (oT.readyState != 4 || oT.status != 200) {
		return;
   	}
   	document.getElementById("t_history").innerHTML = o.responseText ;
}
function refreshTransationNow(radio_val,custid,dfrom,dto){
	oT = c(writeSelectTH);
	oGET(oT,'${pageContext.request.contextPath}/sales/addTransactionHistory.jsp?custId=' + custid+'&cond='+radio_val+'&pfrom='+dfrom+'&pto='+dto)
}

$(document).ready(function() {
$('#custTable').DataTable({
    "iDisplayLength": 20,
    "ordering": true,
    "order": [[0, 'desc']]
});
$("#custTable_length").hide();

$('#sortBy').change(function(){
	var sortBy = $(this).val();
	$.ajax({
        type: "POST",
        url:"CustomerAjax?tabid=sortInvoice&SortBy="+sortBy,
        data:{sortBy : sortBy},
    }).done(function(data){
        $('#custTableBody').html('');
        var custDetails = "<input type='hidden' name='listSize' id='lSize' value='"+data.length+"'>";
        for(var i=0; i<data.length; i++){
            var objList = data[i];
            custDetails = custDetails + "<tr id='"+i+"$$' onclick='setCutomerDataById("+objList.clientVendorID+", "+i+")'>"
                +"<td colspan='2' style='font-size:12px;'>"+objList.clientVendorID+" : "+objList.cname+" ("+objList.firstName+" "+objList.lastName+")</td></tr>";
        }
        $('#custTableBody').html(custDetails);
    });
});

});
</script>
<style type="text/css">
div#pie { padding: 10px 0px 20px 0px; }
table.tabla-listados { width: 100%; border: 1px solid rgb(207, 207, 207); margin: 20px 0px 20px 0px; }
table.tabla-listados thead tr th {
    font-size: .7em; text-align: left; padding: 5px 10px;
	border-bottom: 1px solid rgba(5, 169, 197, 0.2);
	text-shadow: #999 0px 1px 1px; white-space: nowrap;
}
table.tabla-listados tbody tr td {
	font-size: .8em; padding: 5px 0px 5px 12px; background: #fff; vertical-align: top; }
</style>
</head>
<body onload="initialize();">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<form:form name="CustomerForm"  method="post" modelAttribute="customerDto">
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<!-- begin Contents -->
<div>
<div style="float: left;">
	<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.sales.CustomerBoard" />
	</span>
	<br>
	<table>
		<tr>
			<td><spring:message code="BzComposer.customer.sortby" /></td>
			<td>
				<select id="sortBy">
					<option value="1"><spring:message code="BzComposer.customerinfo.companyname"/></option>
					<option value="2"><spring:message code="BzComposer.global.firstname"/></option>
					<option value="3"><spring:message code="BzComposer.global.lastname"/></option>
				</select>
			</td>
		</tr>
	</table>
</div>
<div style="float: right;">
	<table>
		<tr align="right">
			<td colspan="6">
				<div>
					<input type="button" class="formbutton" onclick="manageCustomer('EDIT');" style="padding:7 15px;" value="<spring:message code='BzComposer.global.edit'/>" />
					<input type="button" class="formbutton" onclick="manageCustomer('DELETE');" style="padding:7 15px;" value="<spring:message code='BzComposer.global.delete'/>" />
				</div>
			</td>
		</tr>
	</table>
</div>
</div>
<table style="width: 100%; apdding: 0;">
	<tr>
		<td>
			<table style="padding: 0;width: 100%; margin-top: 10px;" align="center">
				<tr>
					<td valign="top" colspan="1" style="width: 350px; padding: 0; height: 75vh; border: 1px solid #ccc;">
					    <input type="hidden" name="listSize" id="lSize" value='${CustomerDetails.size()}' />
						<table id="custTable" class="tabla-listados" cellspacing="0" style=" border: 0; padding: 0;margin: 0; height: auto;">
							<thead>
								<tr valign="top">
									<th class="emblem" style="font-size:12px;">
										<div align="center">
											<spring:message code="BzComposer.customerinfo.customer" />
										</div>
									</th>
								</tr>
							</thead>
							<tbody id="custTableBody">
                                <c:forEach items="${CustomerDetails}" var="objList" varStatus="loop">
                                    <tr id='${loop.index}$$' onclick="setCutomerDataById(${objList.clientVendorID}, ${loop.index})">
                                        <td colspan="2" style="font-size:12px;" class="${objList.paymentUnpaid?'redColor':''}">
                                            ${objList.clientVendorID} : ${objList.cname}
                                            <%-- <input type="hidden" id="fName" value="${objList.firstName}"/>
                                            <input type="hidden" id="lName" value="${objList.lastName}"/> --%>
                                        </td>
                                    </tr>
                                </c:forEach>
							</tbody>
						</table>
					</td>							<!-- Added on 26-04-2019 -->
					<td colspan="10" style="vertical-align: 0;">
					    <!-- ================== Customer Information =============== -->
						<div id="table-negotiations" style="height:auto;">
							<table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;">
								<thead>
									<tr>
										<th colspan="3" style="font-size:12px;">
											<spring:message code="BzComposer.customerinfo.customerinformation"/>
										</th>
									</tr>
								</thead>
								<tbody>
								    <tr>
								        <td style="width: 40%;">
								            <table cellspacing="0" class="tabla-listados">
								                <tr>
                                                    <td style="width: 50%;"><strong><spring:message code="BzComposer.global.customerid" />:</strong></td>
                                                    <td style="width: 50%;" id="customerID"></td>
								                </tr>
								                <tr>
                                                    <td><strong><spring:message code="BzComposer.Employee.Name" />:</strong></td>
                                                    <td id="customerName"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.Customer.CompanyName"/>:</strong></td>
                                                    <td id="cname"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.register.dbaName"/>:</strong></td>
                                                    <td id="dbaName"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.Customer.Type" />:</strong></td>
                                                    <td>
                                                        <select id="customerType" disabled="true">
                                                            <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                                                            <c:forEach items="${VendorCategoryList}" var="item">
                                                                <option value="${item.value}">${item.label}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.updatevendor.billingAddress" />:</strong></td>
                                                    <td id="billingAddress"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.updatevendor.shippingAddress" />:</strong></td>
                                                    <td id="shippingAddress"></td>
                                                </tr>
								            </table>
								        </td>
								        <td style="width: 30%;">
								            <table cellspacing="0" class="tabla-listados">
								                <tr>
                                                    <td style="width: 50%;"><strong><spring:message code="BzComposer.global.phone" />:</strong></td>
                                                    <td style="width: 50%;" id="phone"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.global.mobile" />:</strong></td>
                                                    <td id="cellPhone"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.global.fax" />:</strong></td>
                                                    <td id="fax"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.global.email" />:</strong></td>
                                                    <td id="email"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.global.dateadded" />:</strong></td>
                                                    <td id="dateAdded"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.orderimport.lastorderdate" />:</strong></td>
                                                    <td id="lastOrderDate"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.global.country" />:</strong></td>
                                                    <td>
                                                        <select id="country" disabled="true" style="width:170px;">
                                                            <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                                                            <c:forEach items="${countryList}" var="item">
                                                                <option value="${item.countryId}">${item.countryName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="Bizcomposer.active" />:</strong></td>
                                                    <td id="activeCustomer"></td>
                                                </tr>
								            </table>
								        </td>
								        <td style="width: 30%;">
								            <table cellspacing="0" class="tabla-listados">
                                                <tr>
                                                    <td style="width: 60%;"><strong><spring:message code="BzComposer.sales.SalesAmount" /></strong></td>
                                                    <td style="width: 40%;"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.common.last3MonthsAmount" />:</strong></td>
                                                    <td id="last3MonthAmt"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.common.last1YearAmount" />:</strong></td>
                                                    <td id="last1YearAmt"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.common.totalOverdueAmount" />:</strong></td>
                                                    <td id="totalOverdueAmt"></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.global.oppeningunpaidbalance" />:</strong></td>
                                                    <td id="openingUB"></td>
                                                </tr>
                                                <tr>
                                                    <td><strong><spring:message code="BzComposer.global.existingcredits" />:</strong></td>
                                                    <td id="extCredit"></td>
                                                </tr>
                                            </table>
								        </td>
								    </tr>
								</tbody>
							</table>

                            <!-- =============== Transaction History ================ -->
                            <table class="tabla-listados" cellspacing="0" style="margin-top: 0; margin-left: 20px;">
                                <thead>
                                    <tr>
                                        <th style="font-size: 14px;">
                                            <spring:message code="BzComposer.updatecustomer.tabs.transactionhistory"/>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr><td style="padding-right: 12px! important;">
                                    <table class="tabla-listados" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th colspan="4">
                                                    <spring:message code="BzComposer.updatecustomer.displayoption" />
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td colspan="4">
                                                <form:radiobutton path="dispay_info" value="ShowAll" onclick="hide_details(this.value);" />
                                                <spring:message code="BzComposer.updatecustomer.showall" />
                                                <script type="text/javascript">
                                                    radio_val = document.CustomerForm.dispay_info.value;
                                                </script>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <form:radiobutton path="dispay_info" value="ByVal" onclick="hide_details(this.value);" />
                                                <spring:message code="BzComposer.updatecustomer.byval" /></td>
                                            <td>
                                                <spring:message code="BzComposer.updatecustomer.from"/>
                                                <form:input path="periodFrom" readonly="true" size="15" disabled="true" />
                                                <img src="${pageContext.request.contextPath}/images/cal.gif"  id="imgfrm" onclick="displayCalendar(document.CustomerForm.periodFrom,'mm-dd-yyyy',this);" />
                                                </td>
                                            <td>
                                                <spring:message code="BzComposer.updatecustomer.to"/>
                                                <form:input path="periodTo" readonly="true" size="15" disabled="true" />
                                                <img src="${pageContext.request.contextPath}/images/cal.gif" id="imgto" onclick="displayCalendar(document.CustomerForm.periodTo,'mm-dd-yyyy',this);" />
                                            </td>
                                            <td><input type="button" class="formbutton" value="<spring:message code='BzComposer.updatecustomer.lookhistory'/>" id="lookBtn" onclick="lookUpHistory();" /></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <div id="t_history" ></div>
                                    </td></tr>
                                </tbody>
                            </table>
                        </div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>
<div>
	<input type="hidden" name="tabid" id="tabid" value="" />
	<input type="hidden" id="selectedCvID" value="${selectedCvID}" />
	<c:forEach items="${CustomerDetails}" var="objList" varStatus="loop">
        <input type="hidden" id="selectedCvIndex${objList.clientVendorID}" value="${loop.index}" />
    </c:forEach>
</div>
</div>
</div>
</div>
</div>					
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
</body>
</html>
<script>
let clientVendorID = 0;
function initialize()
{
	debugger;
	document.getElementById("dispay_info1").checked = true;
	let selectedCvID = document.getElementById("selectedCvID").value;
	let selectedCvIndex = document.getElementById("selectedCvIndex"+selectedCvID).value;
    setCutomerDataById(selectedCvID, selectedCvIndex)
}

function lookUpHistory(){
    let radio_val = $('input[name="dispay_info"]:checked').val();
    var dfrom = document.CustomerForm.periodFrom.value;
    var dto = document.CustomerForm.periodTo.value;
    refreshTransationNow(radio_val, clientVendorID, dfrom, dto);
}
function writeSelectTH(){
   if (oT.readyState != 4 || oT.status != 200) {
     return;
   }
   document.getElementById("t_history").innerHTML = o.responseText ;
}
function refreshTransationNow(radio_val, custid, dfrom, dto){
       oT = c(writeSelectTH);
       oGET(oT,'Customer?tabid=addTransactionHistory&custId=' + custid+'&cond='+radio_val+'&pfrom='+dfrom+'&pto='+dto)
}

function setCutomerDataById(vendorID, rowId){
    clientVendorID = vendorID;
    $.ajax({
        type: "POST",
        url:"CustomerAjax?tabid=getCustomerDetails&cvId="+vendorID,
        data:{clientVendorID : vendorID},
        success : function(data) {
            $('#customerID').html(data.clientVendorID);
            $('#customerName').html(data.firstName +' '+ data.middleName +' '+ data.lastName);
            $('#cname').html(data.cname);
            $('#dbaName').html(data.dbaName);
            $('#customerType').val(data.type);
            $('#billingAddress').html(data.billTo);
            $('#shippingAddress').html(data.shipTo);
            $('#country').val(data.country);
            $('#activeCustomer').html(data.active);

            $('#phone').html(data.phone);
            $('#cellPhone').html(data.cellPhone);
            $('#fax').html(data.fax);
            $('#email').html(data.email);
            $('#dateAdded').html(data.dateAdded);
            $('#lastOrderDate').html(data.lastOrderDate);

            $('#last3MonthAmt').html(data.last3MonthAmt);
            $('#last1YearAmt').html(data.last1YearAmt);
            $('#totalOverdueAmt').html(data.totalOverdueAmt);
            $('#openingUB').html(data.openingUB);
            $('#extCredit').html(data.extCredit);

            let size = document.getElementById("lSize").value;
            for(i=0; i<size; i++){
                if(document.getElementById(i+"$$"))
                    document.getElementById(i+"$$").classList.remove('draft');
            }
            document.getElementById(rowId+'$$').classList.add('draft');
            lookUpHistory();
        },
        error : function(error) {
             alert("<bean:message key='BzComposer.common.erroroccurred'/>");
        }
    });
}

function manageCustomer(cmd)
{
	if (clientVendorID == 0){
		return showCustomerValidationDialog();
	} else {
		if (cmd=="EDIT") {
			window.open("Customer?tabid=editCustomer&cvId="+clientVendorID, null,"scrollbars=yes,height=620,width=1580,status=yes,toolbar=no,menubar=no,location=no");
		}
		else if (cmd=="DELETE") {
			if(confirm("<spring:message code='BzComposer.customerinfo.deleteselectedcustomer'/>")==true) {
				//window.location = "Customer?tabid=Customer&customerAction=DELETE&cvID="+clientVendorID;
				$.ajax({
                    type : "GET",
                    url : "Customer?tabid=Customer&customerAction=DELETE&cvID="+clientVendorID,
                    success : function(data) {
                        location.reload();
                    },
                    error : function(error) {
                         alert("<bean:message key='BzComposer.common.erroroccurred'/>");
                    }
                });
			}
			return false;
		}
	}
}

function showCustomerValidationDialog(){
	event.preventDefault();
	$("#showCustomerValidationDialog").dialog({
    	resizable: false,
        height: 200,
        width: 400,
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
<!-- Dialog box used in sales order page -->
<div id="showCustomerValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.customerinfo.selectcustomerfirst"/></p>
</div>
<div id="deleteCustomer" style="display:none;">
	<p><spring:message code="BzComposer.customerinfo.deleteselectedcustomer"/></p>
</div>