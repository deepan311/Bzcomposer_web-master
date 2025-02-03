<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.customerinfotitle" /></title>
<style>
.redColor{color: red;}
.dataTables_filter{font-size:14px;}
.dataTables_info{font-size:14px;}
.dataTables_paginate{font-size:14px;}
.height150{ height: 150px; }
.card-text{
  font-size: 12px! important;
  color: #6C7590! important;
  padding: 0px;
  margin: 5px;
}
.defaultCard{
    margin-top:10px;background: #C3C5CA;border-radius: 5px;padding:5px;
}
.mTop10{ margin-top:10px; }
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
	if (___) 
	{
    	var t = (___5) ? "Microsoft.XMLHTTP" : "Msxml2.XMLHTTP";
    	try 
    	{
      		o = new ActiveXObject(t);
      		o.onreadystatechange = r;
    	} 
    	catch (ex) 
    	{
      		alert("<bean:message key='BzComposer.common.needToEnableActiveXObject'/> ts.." + ex);
    	}
  	} 
	else 
	{
    	o = new XMLHttpRequest();
    	o.onload = r;
    	o.onerror = r;
  	}
  	return o;
}
function oGET(oo, url) 
{
	try 
  	{
    	oo.open("GET", url, true);	
    	oo.send(null);
  	} 
	catch (ex) 
	{}
}
function writeSelect()
{
	if (o.readyState != 4 || o.status != 200) 
	{ 
		return;
    }
    document.getElementById("t_statedata").innerHTML = o.responseText ;
}
function refreshItemsNow(val)
{
	o = c(writeSelect);
  	oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=state&Cid=' + val)
}
function refreshItemsNow33(val,sval)
{
	o33 = c(writeSelect33);
  	oGET(o33,'${pageContext.request.contextPath}/include/GetStates.jsp?st=state&Cid=' + val+"&sval="+sval)
}
function writeSelect33()
{
	if (o33.readyState != 4 || o33.status != 200) 
	{ 
		return;
    }
    document.getElementById("t_statedata").innerHTML = o33.responseText ;
}
function writeSelect1()
{
	if (o.readyState != 4 || o.status != 200) 
	{ 
		return;
    }
    document.getElementById("t_statedata1").innerHTML = o.responseText ;
}
function refreshItemsNow1(val)
{
	o = c(writeSelect1);
  	oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=bsstate&Cid=' + val)
}
function refreshItemsNow12(val,sval)
{
	o = c(writeSelect2);
  	oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=shstate&Cid=' + val+"&sval="+sval)
}
function refreshItemsNow22(val,sval)
{
	o22 = c(writeSelect22);
  	oGET(o22,'${pageContext.request.contextPath}/include/GetStates.jsp?st=bsstate&Cid=' + val+"&sval="+sval)
}
function writeSelect22()
{
	if (o22.readyState != 4 || o22.status != 200) 
	{ 
		return;
	}
    document.getElementById("t_statedata1").innerHTML = o22.responseText ;
}
function writeSelect2()
{
	if (o.readyState != 4 || o.status != 200) 
	{ 
      return;
    }
    document.getElementById("t_statedata2").innerHTML = o.responseText ;
}
function refreshItemsNow2(val)
{
	o = c(writeSelect2);
  	oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=shstate&Cid=' + val)
}
function writeSelectTH()
{
	if (oT.readyState != 4 || oT.status != 200) 
	{
		return;
   	}
   	document.getElementById("t_history").innerHTML = o.responseText ;
}
function refreshTransationNow(radio_val,custid,dfrom,dto)
{
	oT = c(writeSelectTH);
	oGET(oT,'${pageContext.request.contextPath}/sales/addTransactionHistory.jsp?custId=' + custid+'&cond='+radio_val+'&pfrom='+dfrom+'&pto='+dto)
}
function setState(state_id,name)
{
	if(name == 'state')
	{
		document.VendorForm.state.value = state_id;	
	}
	else if(name == 'bsstate')
	{
		flag_state = 1;
		document.VendorForm.bsstate.value = state_id;
		document.getElementById('bsst').value = state_id;
	}
	else if(name == 'shstate')
	{
		document.VendorForm.shstate.value = state_id;
	}
}

$(function() {
$('#sortBy').change(function(){
	var sortBy = $(this).val();
	$.ajax({
        type: "POST",
        url:"CustomerAjax?tabid=sortInvoice&SortBy="+sortBy,
        data:{sortBy : sortBy},
    }).done(function(data){
        setCustomerListData(data);
    });
});

$('#searchCustomerID').keyup(function(){
    var venderText = $(this).val();
    $.ajax({
        type: "POST",
        url:"CustomerAjax?tabid=searchCustomers&venderText="+venderText,
        data:{venderText : venderText},
    }).done(function(data){
        setCustomerListData(data);
    });
});

});

function setCustomerListData(data){
    $('#custTableBody').html('');
    var custDetails = "<input type='hidden' name='listSize' id='lSize' value='"+data.length+"'>";
    for(var i=0; i<data.length; i++){
        var objList = data[i];
        custDetails = custDetails + "<tr id='"+i+"$$' onmouseover='setCutomerDataById("+objList.clientVendorID+", "+i+")'>"
            +"<td colspan='2' style='font-size:12px;'>"+objList.clientVendorID+" : "+objList.cname+" ("+objList.firstName+" "+objList.lastName+")</td></tr>";
    }
    $('#custTableBody').html(custDetails);
}

function showCustomerValidationDialog()
{
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
	padding: 5px 0px 5px 12px;
	/* 	border-bottom: 1px solid rgb(207, 207, 207); */
	background: #fff;
	vertical-align: top;
}
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
		<spring:message code="BzComposer.Customer.Info" />
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
		<!-- <tr>
            <td><spring:message code="BzComposer.global.search" /></td>
            <td><input type="text" id="searchCustomerID" style="font-size: 14px;" /></td>
        </tr> -->
	</table>
</div>
<div style="float: right;">
	<table>
		<tr align="right">
			<td colspan="6">
				<div>
					<input type="button" class="formbutton" value="<spring:message code='BzComposer.global.edit'/>" onclick="manageCustomer('EDIT');" />
					<input type="button" class="formbutton" value="<spring:message code='BzComposer.global.delete'/>" onclick="manageCustomer('DELETE');" />
				</div>
				<div>
					<form:hidden path="clientVendorID" />
					<form:hidden path="selectedRowID" />
					<input type="hidden" name="cvId" id="vendrId" value="0" /> 
					<input type="hidden" name="SelectedRID" id="setRID" value="">
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
                                <c:if test="${not empty CustomerDetails}">
                                    <c:forEach items="${CustomerDetails}" var="objList" varStatus="loop">
                                        <tr id='${loop.index}$$' onmouseover="setCutomerDataById(${objList.clientVendorID}, ${loop.index})">
                                            <td colspan="2" style="font-size:12px;" class="${objList.paymentUnpaid?'redColor':''}">
                                                ${objList.clientVendorID} : ${objList.cname}
                                                <%-- <input type="hidden" id="fName" value="${objList.firstName}"/>
                                                <input type="hidden" id="lName" value="${objList.lastName}"/> --%>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
							</tbody>
						</table>
						<c:if test="${not empty CustomerDetails}">
                            <input type="hidden" name="listSize" id="lSize" value='${CustomerDetails.size()}' />
                        </c:if>
					</td>							<!-- Added on 26-04-2019 -->
					<td colspan="10" style="vertical-align: 0;">
					    <!-- ================== Customer Information =============== -->
						<div id="table-negotiations" style="height:auto;">
							<table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;">
								<thead>
									<tr>
										<th colspan="10" style="font-size:12px;">
											<spring:message code="BzComposer.customerinfo.customerinformation"/>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
									    <td style="width: 15%;">
                                            <spring:message code="BzComposer.global.titlename" />:
                                        </td>
                                        <td style="width: 18%;">
                                            <form:select path="title" disabled="true">
                                                <form:options items="${titleList}" itemValue="value" itemLabel="label"/>
                                            </form:select>
                                        </td>
										<td style="width: 15%;">
											<spring:message code="BzComposer.global.firstname" />
											<span class="inputHighlighted">*</span>
										</td>
										<td style="width: 18%;" id="firstName">
											${CustomerDetails1.firstName}
										</td>
										<td style="width: 15%;">
                                            <spring:message code="BzComposer.global.middlename" />
                                        </td>
                                        <td style="width: 18%;" id="middleName">
                                            ${CustomerDetails1.middleName}
                                        </td>
										<td colspan="4">&nbsp;</td>
									</tr>
									<tr>
                                        <td style="width: 15%;">
                                            <spring:message code="BzComposer.global.lastname" />
                                            <span class="inputHighlighted">*</span>
                                        </td>
                                        <td style="width: 18%;" id="lastName">
                                            ${CustomerDetails1.lastName}
                                        </td>
                                        <td style="width: 15%;">
                                            <spring:message code="BzComposer.global.customerid" />
                                        </td>
                                        <td style="width: 18%;" id="customerID">
                                            ${CustomerDetails1.clientVendorID}
                                        </td>
                                        <td style="width: 15%;">
                                            <spring:message code="BzComposer.global.inputdate" />:
                                        </td>
                                        <td style="width: 18%;" id="dateInput">
                                            ${CustomerDetails1.dateInput}
                                        </td>
										<td colspan="4">&nbsp;</td>
									</tr>
									<tr>
									    <td style="font-size:12px;">
                                            <spring:message code="BzComposer.Customer.CompanyName"/>
                                            <span class="inputHighlighted">*</span>
                                        </td>
                                        <td colspan="5" style="font-size:12px;" id="cname">
                                            ${CustomerDetails1.cname}
                                        </td>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
									<tr>
									    <td style="font-size:12px;">
                                            <spring:message code="BzComposer.global.address1" />
                                            <span class="inputHighlighted">*</span>
                                        </td>
                                        <td colspan="5" style="font-size:12px;" id="address1">
                                            <%-- <form:text size="20" property="address1" name="CustomerDetails1" readonly="true" /> --%>
                                            ${CustomerDetails1.address1}
                                        </td>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
                                    <tr>
										<td style="font-size:12px;">
											<spring:message code="BzComposer.global.address2" />
											<span class="inputHighlighted">:</span>
										</td>
										<td colspan="5" style="font-size:12px;" id="address2">
											<%-- <form:text size="20" property="address2" name="CustomerDetails1" readonly="true" /> --%>
											${CustomerDetails1.address2}
										</td>
										<td colspan="4">&nbsp;</td>
									</tr>
									<script>
                                    <c:if test="${not empty state_gen}">
                                        var contry = document.CustomerForm.country.value;
                                        refreshItemsNow33(contry,'${state_gen}');
									</c:if>
									</script>
									<tr>
									    <td style="font-size:12px;" class="zipcodeShow">
                                            <spring:message code="BzComposer.global.zipcode"/>
                                            <span class="inputHighlighted">*</span>
                                        </td>
                                        <td style="font-size: 12px;color: #black !important;" class="postalcodeShow">
                                            <spring:message code="BzComposer.global.postalcodes" />
                                            <span class="inputHighlighted">*</span>
                                        </td>
                                        <td style="font-size:12px;" id="zipCode">
                                            ${CustomerDetails1.zipCode}
                                        </td>
										<td style="font-size:12px;">
											<spring:message code="BzComposer.global.city" />
											<span class="inputHighlighted">*</span>
										</td>
										<td style="width: 10%;font-size:12px;" id="city">
											${CustomerDetails1.city}
										</td>
										<td id="t_state" style="font-size:12px;" class="stateShow">
                                            <spring:message code="BzComposer.global.state" />
                                        </td>
                                        <td id="t_statedata" style="font-size:12px;" class="stateShow"></td>
                                        <td style="font-size: 12px;" class="provinceShow">
                                            <spring:message code="BzComposer.global.province" />
                                        </td>
                                        <td style="font-size:12px;" id="province" class="provinceShow">
                                            ${CustomerDetails1.province}
                                        </td>
										<td colspan="4">&nbsp;</td>
									</tr>
									<tr>
									    <td id="t_country" style="font-size:12px;">
                                            <spring:message code="BzComposer.global.country" />
                                        </td>
                                        <td style="font-size:12px;">
                                            <form:select path="country" disabled="true">
                                                <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                <form:options items="${cList}" itemValue="value" itemLabel="label"/>
                                            </form:select>
                                        </td>
                                        <td style="font-size:12px;">
                                            <spring:message code="BzComposer.global.phone" />
                                        </td>
                                        <td style="font-size:12px;" id="phone">
                                            ${CustomerDetails1.phone}
                                        </td>
                                        <td style="font-size:12px;">
                                            <spring:message code="BzComposer.global.mobile" />:
                                        </td>
                                        <td style="font-size:12px;">
                                            <span style="color: black!important;" id="cellPhone">${CustomerDetails1.cellPhone}</span>&nbsp;&nbsp;
                                            <input type="checkbox" id="isPhoneNumber" disabled="true" ${CustomerDetails1.isMobilePhoneNumber==true?'checked':''} />
                                            (<spring:message code="BzComposer.global.isPhoneNumber" />)
                                        </td>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
									<tr>
										<td style="font-size: 12px;">
                                            <spring:message code="BzComposer.global.fax" />
                                        </td>
                                        <td style="font-size:12px;width: 10%" id="fax">
                                            ${CustomerDetails1.fax}
                                        </td>
                                        <td style="font-size:12px;">
                                            <spring:message code="BzComposer.global.email" />
                                        </td>
                                        <td style="font-size:12px;" id="email">
                                            ${CustomerDetails1.email}
                                        </td>
                                        <td style="font-size: 12px;">
                                            <spring:message code="BzComposer.global.homepage" />
                                        </td>
                                        <td style="font-size:12px;" id="homePage">
                                            ${CustomerDetails1.homePage}
                                        </td>
										<td colspan="4">&nbsp;</td>
									</tr>
									<tr>
                                        <td style="font-size: 12px;">
                                            <spring:message code="BzComposer.global.dateadded" />
                                        </td>
                                        <td style="font-size:12px;width: 10%" id="dateAdded">
                                            ${CustomerDetails1.dateAdded}
                                        </td>
                                        <td style="font-size:12px;">
                                            <spring:message code="BzComposer.global.terminated" />
                                        </td>
                                        <td style="font-size:12px;" id="terminated">
                                            ${CustomerDetails1.terminated}
                                        </td>
                                        <td style="font-size: 12px;">
                                            <spring:message code="BzComposer.global.terminatedDate" />
                                        </td>
                                        <td style="font-size:12px;" id="terminatedDate">
                                            ${CustomerDetails1.terminatedDate}
                                        </td>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
									<tr>
									    <td style="font-size:12px;">
                                            <spring:message code="BzComposer.Companyinformation.Type" />
                                        </td>
                                        <td style="font-size:12px;">
                                            <form:select path="type" disabled="true">
                                                <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                <form:options items="${VendorCategoryList}" itemValue="value" itemLabel="label"/>
                                            </form:select>
                                        </td>
										<td style="font-size:12px;">
											<spring:message code="BzComposer.global.taxid" />
										</td>
										<td style="font-size:12px;width: 10%" id="texID">
											${CustomerDetails1.texID}
										</td>
										<td style="font-size:12px;">
											<input type="checkbox" id="isTaxable" disabled="disabled" ${CustomerDetails1.taxAble==1?'checked':''} />
											<spring:message code="BzComposer.global.istaxable" />
										</td>
										<td style="font-size:12px;">
										    <input type="checkbox" id="isClient" disabled="disabled" ${CustomerDetails1.isclient=='1'?'checked':''} />
											<spring:message code="BzComposer.global.isalsoclient" />
										</td>
										<td colspan="4">&nbsp;</td>
									</tr>
									<tr>
									    <td style="font-size:12px;">
                                            <spring:message code="BzComposer.global.oppeningunpaidbalance" />
                                        </td>
                                        <td style="font-size:12px;" id="openingUB">
                                            ${CustomerDetails1.openingUB}
                                        </td>
										<td style="font-size:12px;">
											<spring:message code="BzComposer.global.existingcredits" />
										</td>
										<td style="font-size:12px;width: 10%" id="extCredit">
											${CustomerDetails1.extCredit}
										</td>
										<td colspan="6">&nbsp;</td>
									</tr>
									<tr>
                                        <td style="font-size:12px;">
                                            <spring:message code="BzComposer.global.memo" />
                                        </td>
                                        <td colspan="5" style="font-size:12px;" id="memo">
                                            ${CustomerDetails1.memo}
                                        </td>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
								</tbody>
							</table>
						<%-- ================== Payment Method =============== --%>
                            <table class="tabla-listados" cellspacing="0" style="margin-top: 0; margin-left: 20px;">
                                <thead>
                                    <tr>
                                        <th colspan="12" style="font-size: 12px;">
                                            <spring:message code="BzComposer.addnewcustomer.prefrenceinfo" />
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="3" style="font-size: 12px;">
                                            <spring:message code="BzComposer.global.term" />
                                        </td>
                                        <td colspan="3" style="font-size: 12px;width:50px;">
                                            <form:select path="term" disabled="true" style="width:150px;">
                                                <form:option value=""><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                <form:options items="${TermList}" itemValue="term" itemLabel="name"/>
                                            </form:select>
                                        </td>
                                        <td colspan="3" style="font-size: 12px;">
                                            <spring:message code="BzComposer.global.paymentmethod" />
                                        </td>
                                        <td colspan="3" style="font-size: 12px;width: 50px;">
                                            <form:select path="paymentType" disabled="true" style="width:150px;">
                                                <form:option value=""><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                <form:options items="${PaymentList}" itemValue="id" itemLabel="typeName"/>
                                            </form:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" style="font-size: 12px;">
                                            <spring:message code="BzComposer.global.representative" />
                                        </td>
                                        <td colspan="3" style="font-size: 12px;width: 50px;">
                                            <form:select path="rep" disabled="true" style="width:150px;">
                                                <form:option value=""><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                <form:options items="${RepList}" itemValue="value" itemLabel="label"/>
                                            </form:select>
                                        </td>
                                        <td colspan="3" style="font-size: 12px;">
                                            <spring:message code="BzComposer.global.shippingvia" />
                                        </td>
                                        <td colspan="3" style="font-size: 12px;width:50px;">
                                            <form:select path="shipping" disabled="true" style="width:150px;">
                                                <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                <form:options items="${ShipCarrierList}" itemValue="value" itemLabel="label"/>
                                            </form:select>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <!-- ================== Payment Information / Credit-Card Information ================= -->
                            <table class="tabla-listados" cellspacing="0" style="margin-top: 0; margin-left: 20px;">
                                <thead>
                                    <tr>
                                        <th colspan="4" style="font-size: 12px;">
                                            <spring:message code="BzComposer.addnewcustomer.creditcardinfo" />
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- <tr>
                                        <td style="font-size: 12px;"><spring:message code="BzComposer.global.cardtype" /></td>
                                        <td style="font-size: 12px;">
                                            <form:select path="ccType" disabled="true" style="width:150px;">
                                                <form:option value=""><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                <form:options items="${CreditCardList}" itemValue="value" itemLabel="label"/>
                                            </form:select>
                                        </td>
                                        <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 12px;"><spring:message code="BzComposer.global.cardnumber" /></td>
                                        <td style="font-size: 12px;" id="cardNo">${CustomerDetails1.cardNo}</td>
                                        <td style="font-size: 12px;"><spring:message code="BzComposer.global.expdate" /></td>
                                        <td style="font-size: 12px;">
                                            <span style="color: black!important;" id="expDate">${CustomerDetails1.expDate}</span>&nbsp;&nbsp;&nbsp;
                                            <spring:message code="BzComposer.global.mformate" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 12px;"><spring:message code="BzComposer.global.CW2" /></td>
                                        <td style="font-size: 12px;" id="cw2">${CustomerDetails1.cw2}</td>
                                        <td style="font-size: 12px;"><spring:message code="BzComposer.global.cardholdername" /></td>
                                        <td style="font-size: 12px;" id="cardHolderName">${CustomerDetails1.cardHolderName}</td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 12px;"><spring:message code="BzComposer.addnewcustomer.billingaddress"/></td>
                                        <td style="font-size: 12px;" id="cardBillAddress">${CustomerDetails1.cardBillAddress}</td>
                                        <td style="font-size: 12px;"><spring:message code="BzComposer.global.zipcode" /></td>
                                        <td style="font-size: 12px;" id="cardZip">${CustomerDetails1.cardZip}</td>
                                    </tr>-->
                                    <tr>
                                        <td colspan="4">
                                            <div class="container text-center">
                                                <div class="row text-left" id="customerCards">
                                                    <c:forEach items="${CustomerDetails1.customerCards}" var="objList">
                                                    <div class="col-sm-4">
                                                        <div class="card height150">
                                                            <div class="card-body">
                                                            <h6 class="card-subtitle">${objList.ccTypeName}</h6>
                                                            <p class="card-text mTop10"><spring:message code="BzComposer.global.expdate" />: <span style="color: #6C7590! important;" id="expDate1">${objList.expDate}</span></p>
                                                            <p class="card-text"><spring:message code="BzComposer.global.cardnumber"/>: <span style="color: #6C7590! important;" id="cardNo1">${objList.cardNo}</span></p>
                                                            <p class="card-text"><spring:message code="BzComposer.addnewcustomer.billingaddress"/>: <span style="color: #6C7590! important;" id="cardBillAddress1">${objList.cardBillAddress}</span></p>
                                                            <c:choose>
                                                            <c:when test="${objList.cardDefault}">
                                                                <p class="card-text float-right defaultCard"><strong><spring:message code="BzComposer.global.default"/></strong></p>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <p class="card-text float-right">
                                                                    <a href="Customer?tabid=makeCustomerCardDefault&cvId=${objList.clientVendorID}&cardID=${objList.cardID}">
                                                                        <strong><spring:message code="BzComposer.global.default"/></strong></a>
                                                                </p>
                                                            </c:otherwise>
                                                            </c:choose>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </c:forEach>
                                                    <c:if test="${CustomerDetails1.customerCards.size()==0}">
                                                    <div class="col-sm-4">
                                                        <div class="card height150">
                                                            <div class="card-body">
                                                                <h6 class="card-subtitle text-center" style="margin-top:40px;color:red;">No Card</h6>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <!-- ================== Services ================= -->
                            <table class="tabla-listados" cellspacing="0" style="margin-top: 0; margin-left: 20px;display:none;">
                                <thead>
                                    <tr>
                                        <th style="font-size: 12px;">
                                            <spring:message code="BzComposer.addnewcustomer.servicename" />
                                        </th>
                                        <th style="font-size: 12px;">
                                            <spring:message code="BzComposer.addnewcustomer.invoicestyle" />
                                        </th>
                                        <th style="font-size: 12px;">
                                            <spring:message code="BzComposer.addnewcustomer.balance" />
                                        </th>
                                        <th style="font-size: 12px;">
                                            <spring:message code="BzComposer.addnewcustomer.default" />
                                        </th>
                                        <th style="font-size: 12px;">
                                            <spring:message code="BzComposer.addnewcustomer.remove" />
                                        </th>
                                    </tr>
                                </thead>
                                <tr id="tr$$">
                                    <td align="left"></td>
                                    <td align="left"></td>
                                    <td align="left"></td>
                                    <td align="left"></td>
                                    <td align="left"></td>
                                </tr>
                                <c:if test="${not empty ServiceInfo}">
                                    <c:forEach items="${ServiceInfo}" var="objList" varStatus="loop">
                                        <input type="hidden" id='${loop.index}_ser' value='${objList.serviceID}' />
                                            <tr id='row${objList.serviceID}'>
                                                <td align="left" style="font-size: 12px;">${objList.serviceName}</td>
                                                <td align="left" style="font-size: 12px;">${objList.invoiceStyle}</td>
                                                <td align="left" style="font-size: 12px;">${objList.serviceBalance}</td>
                                                <c:if test="${objList.defaultService == 1}">
                                                    <td style="font-size: 12px;">
                                                        <input type="radio" value='${objList.defaultService}' checked="checked" disabled="true" />
                                                    </td>
                                                </c:if>
                                                <c:if test="${objList.defaultService != 1}">
                                                    <td style="font-size: 12px;">
                                                        <input type="radio" value='${objList.defaultService}' disabled="true" />
                                                    </td>
                                                </c:if>
                                                <td>
                                                    <img src="${pageContext.request.contextPath}/images/delete.png" alt="Deletable" />
                                                <!-- onclick="removeFromTable('${loop.index}');" -->
                                                </td>
                                                <td align="center"></td>
                                            </tr>
                                    </c:forEach>
                                </c:if>
                            </table>
                            <!-- =============== Manage Address ================ -->
                            <table class="tabla-listados" cellspacing="0" style="margin-top: 0; margin-left: 20px;">
                                <thead>
                                    <tr>
                                        <th colspan="4" style="font-size: 12px;">
                                            <spring:message code="BzComposer.addnewcustomer.manageaddresses" />
                                        </th>
                                    </tr>
                                </thead>
                                <thead>
                                    <tr>
                                        <td colspan="4" style="font-size: 12px;">
                                            &nbsp;<input value="on"  checked="checked" type="checkbox" id="chk_setaddress" name="setdefaultaddress" disabled="true" />
                                            <font size="3"><spring:message code="BzComposer.addnewcustomer.differentaddress"/></font>
                                        </td>
                                    </tr>
                                </thead>
                                <tr>
                                    <td>
                                        <table class="tabla-listados" cellspacing="0" width="300" align="left">
                                            <thead>
                                                <tr>
                                                    <th colspan="5" style="font-size: 12px;">
                                                        <spring:message code="BzComposer.addnewcustomer.billingaddress"/>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.company" />
                                                    </td>
                                                    <td style="font-size: 12px;" id="bscname">
                                                        ${CustomerDetails1.bscname}
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.province" />
                                                    </td>
                                                    <td style="font-size: 12px;" id="bsprovince">
                                                        ${CustomerDetails1.bsprovince}
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.firstname" />
                                                    </td>
                                                    <td style="font-size: 12px;" id="bsfirstName">
                                                        ${CustomerDetails1.bsfirstName}
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.lastname" />
                                                    </td>
                                                    <td style="font-size: 12px;" id="bslastName">
                                                        ${CustomerDetails1.bslastName}
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.address1" />
                                                    </td>
                                                    <td colspan="3" style="font-size: 12px;" id="bsaddress1">
                                                        ${CustomerDetails1.bsaddress1}
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.address2" />
                                                    </td>
                                                    <td colspan="3" style="font-size: 12px;" id="bsaddress2">
                                                        ${CustomerDetails1.bsaddress2}
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.zipcode" />
                                                    </td>
                                                    <td style="font-size: 12px;" id="bszipCode">
                                                        ${CustomerDetails1.bszipCode}
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.city" />
                                                    </td>
                                                    <td style="font-size: 12px;" id="bscity">
                                                        ${CustomerDetails1.bscity}
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td id="t_state" style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.state" />
                                                    </td>
                                                    <td id="t_statedata1" style="font-size: 12px;"></td>
                                                    <td id="t_country" style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.country" />
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <form:select disabled="true" path="bscountry">
                                                            <form:option value="0">
                                                                <spring:message code="BzComposer.ComboBox.Select" />
                                                            </form:option>
                                                            <form:options items="${cList}" itemValue="value" itemLabel="label"/>
                                                        </form:select>
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                        </table>
                                    </td>
                                    <td>
                                        <table class="tabla-listados" cellspacing="0" width="300" align="left">
                                            <tbody>
                                                <thead>
                                                    <tr>
                                                        <th colspan="5" style="font-size: 12px;">
                                                            <spring:message code="BzComposer.addnewcustomer.shippingaddress"/>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.company" />
                                                    </td>
                                                    <td style="font-size: 12px;" id="shcname">
                                                        ${CustomerDetails1.shcname}
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.province" />
                                                    </td>
                                                    <td style="font-size: 12px;" id="shprovince">
                                                        ${CustomerDetails1.shprovince}
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.firstname" />
                                                    </td>
                                                    <td style="font-size: 12px;" id="shfirstName">
                                                        ${CustomerDetails1.shfirstName}
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.lastname" />
                                                    </td>
                                                    <td style="font-size: 12px;" id="shlastName">
                                                        ${CustomerDetails1.shlastName}
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.address1" />
                                                    </td>
                                                    <td colspan="3" style="font-size: 12px;" id="shaddress1">
                                                        ${CustomerDetails1.shaddress1}
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.address2" />
                                                    </td>
                                                    <td colspan="3" style="font-size: 12px;" id="shaddress2">
                                                        ${CustomerDetails1.shaddress2}
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.zipcode" />
                                                    </td>
                                                    <td style="font-size: 12px;" id="shzipCode">
                                                        ${CustomerDetails1.shzipCode}
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.city" />
                                                    </td>
                                                    <td style="font-size: 12px;" id="shcity">
                                                        ${CustomerDetails1.shcity}
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td id="t_state" style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.state" />
                                                    </td>
                                                    <td id="t_statedata2" style="font-size: 12px;"></td>
                                                    <td id="t_country" style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.country" />
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <form:select disabled="true" path="shcountry" >
                                                            <form:option value="0">
                                                                <spring:message code="BzComposer.ComboBox.Select" />
                                                            </form:option>
                                                            <form:options items="${cList}" itemValue="value" itemLabel="label"/>
                                                        </form:select>
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                    <td colspan="2"></td>
                                </tr>
                            </table>
                            <!-- =============== Finance Charges ================ -->
                            <table class="tabla-listados" cellspacing="0" style="margin-top: 0; margin-left: 20px;">
                                <thead>
                                    <tr>
                                        <th colspan="4" style="font-size: 14px;">
                                            <spring:message code="BzComposer.updatecustomer.tabs.financecharges"/>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <div>
                                                <div id="table-negotiations" style="width:48%;float: left;">
                                                    <table class="tabla-listados" cellspacing="0">
                                                        <thead>
                                                            <tr>
                                                                <th colspan="4" style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.addnewcustomer.chargerate"/>
                                                                </th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.addnewcustomer.annualintrestrate"/>
                                                                </td>
                                                                <td colspan="3" id="annualIntrestRate">
                                                                    ${CustomerDetails1.annualIntrestRate}
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.addnewcustomer.minimumfinanacecharge" />
                                                                </td>
                                                                <td colspan="3" style="font-size: 12px;" id="minFCharges">
                                                                    ${CustomerDetails1.minFCharges}
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.addnewcustomer.graceperiod" />
                                                                </td>
                                                                <td colspan="3" id="gracePrd">
                                                                    ${CustomerDetails1.gracePrd}
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div id="table-negotiations" style="width:48%;float: right;">
                                                    <table class="tabla-listados" cellspacing="0">
                                                        <thead>
                                                            <tr>
                                                                <th colspan="4" style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.addnewcustomer.applyingcharges" />
                                                                </th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td colspan="4" style="font-size: 12px;">
                                                                    <input value="on" type="checkbox" id="chk1" name="AssessFinanceChk" disabled="disabled">
                                                                        <spring:message code="BzComposer.addnewcustomer.assessfinance"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="4" style="font-size: 12px;">
                                                                    <input value="on" type="checkbox" id="chk2" disabled="disabled" name="FChargeInvoiceChk">
                                                                    <spring:message code="BzComposer.addnewcustomer.markfinanacechargeinvoice"/>
                                                                </td>
                                                            </tr>
                                                            <tr><td colspan="4">&nbsp;</td></tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                        <td colspan="3">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">&nbsp;</td>
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

	<!-- ============ Just for setting select options ============ -->
	<input type="hidden" id="title2" value="${CustomerDetails1.title}" />
	<input type="hidden" id="country2" value="${CustomerDetails1.country}" />
	<input type="hidden" id="type2" value="${CustomerDetails1.type}" />
	<input type="hidden" id="term2" value="${CustomerDetails1.term}" />
	<input type="hidden" id="paymentType2" value="${CustomerDetails1.paymentType}" />
	<input type="hidden" id="rep2" value="${CustomerDetails1.rep}" />
	<input type="hidden" id="shipping2" value="${CustomerDetails1.shipping}" />
	<input type="hidden" id="ccType2" value="${CustomerDetails1.ccType}" />
	<input type="hidden" id="bscountry2" value="${CustomerDetails1.bscountry}" />
	<input type="hidden" id="shcountry2" value="${CustomerDetails1.shcountry}" />
	<input type="hidden" id="companyName" value="${CustomerDetails1.cname}" />
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
function initialize()
{
	debugger;
	<c:if test="${not empty VendorFrm}">
		tableValue();
	</c:if>
	$('#title').val($('#title2').val());
	$('#country').val($('#country2').val());
	$('#type').val($('#type2').val());
	$('#term').val($('#term2').val());
	$('#paymentType').val($('#paymentType2').val());
	$('#rep').val($('#rep2').val());
	$('#shipping').val($('#shipping2').val());
	$('#ccType').val($('#ccType2').val());
	$('#bscountry').val($('#bscountry2').val());
	$('#shcountry').val($('#shcountry2').val());
	refreshItemsNow($('#country').val());
	//$('#sid').val(5);
	document.getElementById("dispay_info1").checked = true;

	var custRows = document.getElementById("custTableBody").getElementsByTagName("tr");
    if(custRows.length){
        document.getElementById("0$$").className = "draft";
    }
}
function refreshItemsNow(val){
	debugger;
	if(val == "3"){
		$(".provinceShow").show();
		$(".postalcodeShow").show();
		$(".stateShow").hide();
		$(".zipcodeShow").hide();
	}else {
		$(".provinceShow").hide();
		$(".postalcodeShow").hide();
		$(".stateShow").show();
		$(".zipcodeShow").show();
	}
  o = c(writeSelect);
  oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=state&Cid=' + val)
}

function lookUpHistory(){
    let radio_val = $('input[name="dispay_info"]:checked').val();
    let custId = document.getElementById('clientVendorID').value;
    var dfrom = document.CustomerForm.periodFrom.value;
    var dto = document.CustomerForm.periodTo.value;
    refreshTransationNow(radio_val, custId, dfrom, dto);
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

function setRowId(rowid, rid)
{
	size=document.getElementById("lSize").value;
	//size1=document.getElementById("seSize").value;
	for(i=0;i<size;i++)
	{
		//var row1=document.getElementById(i+"$$");
		//row1.className = "";
	}
	/*for(j=0;j<size1;j++){
	row1=document.getElementById(j+"*$$");
	row1.className = "";
	}*/
	rid = rid+'$$';
	var rd=document.getElementById(rid);
	rd.className = "draft";
	document.getElementById("setRID").value=rid;
	var rd1=rid.replace("$$", "");
	document.CustomerForm.clientVendorID.value=rowid;
	var custid=document.CustomerForm.clientVendorID.value;
	getVendorInfo(rowid,rd1);
}

function setCutomerDataById(vendorID, rowId){

    $.ajax({
        type: "POST",
        url:"CustomerAjax?tabid=getCustomerDetails&cvId="+vendorID+"&selectedRID="+rowId,
        data:{clientVendorID : vendorID, selectedRowID : rowId},
        success : function(data) {
            $('#firstName').html(data.firstName);
            $('#lastName').html(data.lastName);
            $('#dateAdded').html(data.dateAdded);
            $('#title').val(data.title);
            $('#cname').html(data.cname);
            $('#address1').html(data.address1);
            $('#address2').html(data.address2);
            $('#country').val(data.country);
            $('#t_statedata').html(data.state);
            $('#city').html(data.city);
            $('#zipCode').html(data.zipCode);
            $('#province').html(data.province);
            $('#phone').html(data.phone);
            $('#isPhoneMobileNumber').prop( "checked", data.isPhoneMobileNumber?true:false );
            $('#homePage').html(data.homePage);
            $('#email').html(data.email);
            $('#type').val(data.type);
            $('#texID').html(data.texID);
            $('#openingUB').html(data.openingUB);
            $('#isTaxable').prop( "checked", data.taxAble==1?true:false );
            $('#isClient').prop( "checked", data.isclient==1?true:false );
            $('#extCredit').html(data.extCredit);
            $('#term').val(data.term);
            $('#paymentType').val(data.paymentType);
            $('#rep').val(data.rep);
            $('#shipping').val(data.shipping);
            $('#ccType').val(data.ccType);
            $('#cardNo').html(data.cardNo);
            $('#expDate').html(data.expDate);
            $('#cw2').html(data.cw2);
            $('#cardHolderName').html(data.cardHolderName);
            $('#cardBillAddress').html(data.cardBillAddress);
            $('#cardZip').html(data.cardZip);
            $('#bscname').html(data.bscname);
            $('#bsprovince').html(data.bsprovince);
            $('#bsfirstName').html(data.bsfirstName);
            $('#bslastName').html(data.bslastName);
            $('#bsaddress1').html(data.bsaddress1);
            $('#bsaddress2').html(data.bsaddress2);
            $('#bscity').html(data.bscity);
            $('#bszipCode').html(data.bszipCode);
            $('#bscountry').val(data.bscountry);
            $('#shcname').html(data.shcname);
            $('#shprovince').html(data.shprovince);
            $('#shfirstName').html(data.shfirstName);
            $('#shlastName').html(data.shlastName);
            $('#shaddress1').html(data.shaddress1);
            $('#shaddress2').html(data.shaddress2);
            $('#shcity').html(data.shcity);
            $('#shzipCode').html(data.shzipCode);
            $('#shcountry').val(data.shcountry);
            $('#annualIntrestRate').html(data.annualIntrestRate);
            $('#minFCharges').html(data.minFCharges);
            $('#gracePrd').html(data.gracePrd);
            //$('#aaaa').html(data.aaaa);

            let cardsHtml = '';
            for(let x=0; x<data.customerCards.length; x++){
                let card = data.customerCards[x];
                cardsHtml = cardsHtml+'<div class="col-sm-4"><div class="card height150"><div class="card-body">'
                    +'<h6 class="card-subtitle">'+card.ccTypeName+'</h6>'
                    +'<p class="card-text mTop10"><spring:message code="BzComposer.global.expdate" />: <span style="color: #6C7590! important;" id="expDate1">'+card.expDate+'</span></p>'
                    +'<p class="card-text"><spring:message code="BzComposer.global.cardnumber"/>: <span style="color: #6C7590! important;" id="cardNo1">'+card.cardNo+'</span></p>'
                    +'<p class="card-text"><spring:message code="BzComposer.addnewcustomer.billingaddress"/>: <span style="color: #6C7590! important;" id="cardBillAddress1">'+card.cardBillAddress+'</span></p>';
                if(card.cardDefault){
                    cardsHtml = cardsHtml+'<p class="card-text float-right defaultCard"><strong><spring:message code="BzComposer.global.default"/></strong></p>';
                }else{
                    cardsHtml = cardsHtml+'<p class="card-text float-right"><a href="Customer?tabid=makeCustomerCardDefault&cvId='+card.clientVendorID+'&cardID='+card.cardID+'"><strong><spring:message code="BzComposer.global.default"/></strong></a></p>';
                }
                cardsHtml = cardsHtml+'</div></div></div>';
            }
            if(data.customerCards.length==0){
                cardsHtml = cardsHtml+'<div class="col-sm-4"><div class="card height150"><div class="card-body"><h6 class="card-subtitle text-center" style="margin-top:40px;color:red;">No Card</h6></div></div></div>';
            }
            document.getElementById("customerCards").innerHTML = cardsHtml;
            markSelectedCustomer(vendorID, rowId+'$$');
        },
        error : function(error) {
             alert("<spring:message code='BzComposer.common.erroroccurred'/>");
        }
    });
}

function markSelectedCustomer(vendorID, rowId){
	let size = document.getElementById("lSize").value;
	for(i=0; i<size; i++){
	    if(document.getElementById(i+"$$"))
		    document.getElementById(i+"$$").classList.remove('draft');
	}
 	document.getElementById(rowId).classList.add('draft');
	document.CustomerForm.clientVendorID.value = vendorID;
}

function tableValue()
{
	debugger;
	rid=document.CustomerForm.selectedRowID.value;
	var rd=document.getElementById(rid);
	if(rd != null){
	    rd.className = "draft";
	}
	var sortId = <%= request.getParameter("sortById")%>;

	$('select[id="sortBy"]').find('option[value="'+sortId+'"]').attr("selected",true);
	//document.getElementById("setRID").value=rid;
}

function resetSelectedCustomer() 
{
	document.getElementById("selCustomer").value="0";

}
function getVendorInfo(vendorid,rowId)
{
	document.getElementById('vendrId').value=vendorid;
	document.getElementById('vendrId').value=document.CustomerForm.clientVendorID.value;
	var rowID = rowId;
	window.location = "Customer?tabid=viewCustomerDetails&&cvId="+vendorid+"&selectedRID="+rowId;
}
function manageCustomer(cmd)
{
	var cvid= document.CustomerForm.clientVendorID.value; //document.forms[0].selectedCustomer.value;
	if (cvid==0)
	{	

		return showCustomerValidationDialog();
		//return;
	}
	else 
	{
		if (cmd=="EDIT") 
		{
			showCustomer(cvid);

		}
		else if (cmd=="DELETE") 
		{	
			if(confirm("This will delete all the information related to this Customer (ID="+cvid+")\nAre you sure to delete ?")==true)
			{
				/* document.forms[0].action="Customer?customerAction=DELETE&cvID="+cvid;
				document.forms[0].submit(); */
				window.location = "Customer?tabid=Customer&customerAction=DELETE&cvID="+cvid;
			}
			/* event.preventDefault();
			$("#deleteCustomer").dialog({
		    	resizable: false,
		        height: 200,
		        width: 500,
		        modal: true,
		        buttons: {
		            "<spring:message code='BzComposer.global.ok'/>": function () {
		                $(this).dialog("close");
		                document.forms[0].action="Customer?customerAction=DELETE&cvID="+cvid;
						document.forms[0].submit();
						$('form').submit();
		            },
		            <spring:message code='BzComposer.global.cancel'/>: function () {
		                $(this).dialog("close");
		                return false;
		            }
				}
			});*/
			return false;
		}
	}
}
function showCustomer(cust_id)
{
	window.open("Customer?tabid=editCustomer&cvId="+cust_id,null,"scrollbars=yes,height=620,width=1580,status=yes,toolbar=no,menubar=no,location=no");
	/*window.open("EditCustomer?tabid=editCustomer&cvId="+cust_id,null,"scrollbars=yes,height=580,width=1225,status=yes,toolbar=no,menubar=no,location=no"); */
}
$(document).ready(function() {
    $('#custTable').DataTable({
        "iDisplayLength": 50,
        "ordering": false
    });
    $("#custTable_length").hide();
});

</script>
<!-- Dialog box used in sales order page -->
<div id="showCustomerValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.customerinfo.selectcustomerfirst"/></p>
</div>
<div id="deleteCustomer" style="display:none;">
	<p><spring:message code="BzComposer.customerinfo.deleteselectedcustomer"/></p>
</div>