<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="java.util.*, java.io.*"%>
<%@ page isELIgnored="false"%>
<%--<%@ page errorPage="/include/sessionExpired.jsp"%>--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.vendorinformationtitle" />
</title>
<script type="text/javascript">
var radio_val;
function numbersonly(e,val)
{
	var temp=val.indexOf(".");
	var unicode=e.charCode? e.charCode : e.keyCode;
	if (unicode!=8)
	{
 		//if the key isn't the backspace key (which we should allow)
		if(unicode==46 && temp==-1)
		{
 			return true;
		} 
		else 
		if (unicode<48||unicode>57) //if not a number
			return false; //disable key press
	}
}
function showEditVendor()
{
	debugger;
	var custid = $('#clientVendorID').val();
	if(custid==0 || custid == null){
		return showSelectVendorDialog();
	}
	else{
		/*window.location ="Vendor?tabid=editVendor&cvId="+custid;*/
		window.open("Vendor?tabid=editVendor&cvId="+custid,null,"scrollbars=yes,height=620,width=1600,status=yes,toolbar=no,menubar=no,location=no" );
	}
}
function getVendorInfo(vendorid, rowId)
{
	debugger
	$("#clientVendorID").val(vendorid);
	//document.getElementById("tabid").value="VONODO";
	/* document.forms[0].action="Vendor?";
	document.forms[0].submit(); */
	window.location = "Vendor?tabid=VONODO&&vendrId="+vendorid+"&SelectedRID="+rowId+"&SortBy="+$("#sortBy").val();;
}
function enableDisableFinanceCharges()
{
	ch_box=document.getElementById('chbox');
	if (ch_box.checked) 
	{
		document.VendorForm.annualIntrestRate.disabled=false;
		document.VendorForm.minFCharges.disabled=false;
		document.VendorForm.gracePrd.disabled=false;
		document.getElementById('chk1').disabled=false;
	}
	else
	{
		document.VendorForm.annualIntrestRate.disabled=true;
		document.VendorForm.minFCharges.disabled=true;
		document.VendorForm.gracePrd.disabled=true;
		document.getElementById('chk1').disabled=true;
	}
}
function initialize()
{
	debugger;
	$('#country').val($('#country2').val());
	refreshItemsNow($('#country').val());
    <c:if test="${not empty VendorFrm}">
		tableValue();
		document.getElementById('editAction').disabled = true;
		document.getElementById('deleteAction').disabled = true;
    </c:if>
	document.getElementById("dispay_info1").checked = true;

	var custRows = document.getElementById("custTableBody").getElementsByTagName("tr");
	if(custRows.length){
	    document.getElementById("0$$").className = "draft";
	}
}
//function lookUpHistory(form,v)
function lookUpHistory()
{
	//val = document.getElementById('hidn').value;
	let radio_val = $('input[name="dispay_info"]:checked').val();
	let custId = $('#clientVendorID').val();
    dfrom = document.getElementById('periodFrom').value;
    dto = document.getElementById('periodTo').value;
    refreshTransationNow(radio_val, custId, dfrom, dto);
}
function writeSelectTH(){
   if (oT.readyState != 4 || oT.status != 200) {
     return;
   }
   document.getElementById("t_history").innerHTML = o.responseText ;
}
function refreshTransationNow(radio_val, custid, dfrom, dto)
{
       oT = c(writeSelectTH);
       oGET(oT,'Customer?tabid=addTransactionHistory&custId=' + custid+'&cond='+radio_val+'&pfrom='+dfrom+'&pto='+dto)
}

function hide_details(val,form)
{
	document.getElementById('lookBtn').disabled=false;
	if(val=="ShowAll")
	{
		document.getElementById('imgfrm').disabled=true;
		document.getElementById('imgto').disabled=true;
		document.VendorForm.periodFrom.disabled=true;
		document.VendorForm.periodTo.disabled=true;
	}
	if(val=="ByVal")
	{
		document.getElementById('imgfrm').disabled=false;
		document.getElementById('imgto').disabled=false;
		document.VendorForm.periodFrom.disabled=false;
		document.VendorForm.periodTo.disabled=false;
	}
	radio_val=val;
}
function CopyBilladdToShipAdd()
{
	document.VendorForm.shcname.value= document.VendorForm.bscname.value;
	document.VendorForm.shfirstName.value= document.VendorForm.bsfirstName.value;
	document.VendorForm.shlastName.value= document.VendorForm.bslastName.value;
	document.VendorForm.shaddress1.value= document.VendorForm.bsaddress1.value;
	document.VendorForm.shaddress2.value= document.VendorForm.bsaddress2.value;
	document.VendorForm.shcity.value= document.VendorForm.bscity.value;
	document.VendorForm.shzipCode.value = document.VendorForm.bszipCode.value;
	document.VendorForm.shprovince.value=document.VendorForm.bsprovince.value;
	document.VendorForm.shcountry.value= document.VendorForm.bscountry.value;
	<c:if test="${not empty state_bt}">
		document.VendorForm.bsstate.value = '${state_bt}';
	</c:if>
	if(flag_state == 1)
	{
		document.VendorForm.bsstate.value = document.getElementById('bsst').value;
	}
	document.VendorForm.shstate.value = document.VendorForm.bsstate.value;
	refreshItemsNow12(document.VendorForm.bscountry.value,document.VendorForm.bsstate.value);
}
function clearBillingAdd()
{
	document.VendorForm.bscname.value="";
	document.VendorForm.bsfirstName.value="";
	document.VendorForm.bslastName.value="";
	document.VendorForm.bsaddress1.value="";
	document.VendorForm.bsaddress2.value="";
	document.VendorForm.bscity.value="";
	document.VendorForm.bszipCode.value="";
	document.VendorForm.bsprovince.value="";
	document.VendorForm.bscountry.value="0";
	document.VendorForm.bsstate.value="0";
	refreshItemsNow22(0,0);
}
function clearShippingAdd()
{
	document.VendorForm.shcname.value="";
	document.VendorForm.shfirstName.value="";
	document.VendorForm.shlastName.value= "";
	document.VendorForm.shaddress1.value= "";
	document.VendorForm.shaddress2.value= "";
	document.VendorForm.shcity.value= "";
	document.VendorForm.shzipCode.value = "";
	document.VendorForm.shprovince.value="";
	document.VendorForm.shcountry.value= "0";
	document.VendorForm.shstate.value="0";
	refreshItemsNow12(0,0);
}
function DeleteVendor()
{
	debugger;
	//vendorid=document.VendorForm.clientVendorID.value;
	let vendorid = $('#clientVendorID').val();
	if(vendorid==0 || vendorid == null){

		return deleteVendorDialog();
	}
	else{
		debugger;
		event.preventDefault();
		$("#deleteRowDialog").dialog({
	    	resizable: false,
	        height: 200,
	        width: 550,
	        modal: true,
	        buttons: {
	            "<spring:message code='BzComposer.global.ok'/>": function () {
	            	debugger
	                $(this).dialog("close");
	                document.getElementById('venrId').value=vendorid;
	                document.getElementById('cvId').value = vendorid;
	    			document.getElementById('tabid').value="DeleteVendor";
	    			document.forms[0].action="Vendor";
	    			//document.forms[0].submit();
	    			$('form').submit();
	            },
	            <spring:message code='BzComposer.global.cancel'/> : function () {
	                $(this).dialog("close");
	            }
	        }
	    });
	    return false;
	}
}
function setCutomerDataById(vendorID, rowId){

    $.ajax({
        type: "POST",
        url:"CustomerAjax?tabid=getCustomerDetails&cvId="+vendorID+"&selectedRID="+rowId,
        data:{clientVendorID : vendorID, selectedRowID : rowId},
        success : function(data) {
            $('#customerID').html(data.clientVendorID);
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
             alert("<bean:message key='BzComposer.common.erroroccurred'/>");
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
function setRowId(rowID, rid)
{
	debugger;
	size=document.getElementById("lSize").value;
	for(i=0;i<size;i++){
		var row1=document.getElementById(i+"$$");
		if(row1) row1.className = "";
	}
	let ridStr = rid+"$$";
	var rd = document.getElementById(ridStr);
	rd.className = "draft";
	$("#setRID").val(ridStr);
	$("#clientVendorID").val(rowID);
	getVendorInfo(rowID, rid);
}
function tableValue()
{
	//var rid1=document.VendorForm.selectedRowID.value;
	var rid= "<%= request.getParameter("SelectedRID")%>$$";
	var rd=document.getElementById(rid);
	rd.className = "draft";
	document.getElementById("setRID").value=rid;
}
function showSelectVendorDialog()
{
	debugger;
	event.preventDefault();
	$("#showSelectVendorDialog").dialog({
    	resizable: false,
        height: 200,
        width: 300,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
                //window.location="Invoice?tabid=DeleteInvoice&&CustomerID="+cid;
            }
        }
    });
    return false;
}
function deleteVendorDialog()
{
	debugger;
	event.preventDefault();
	$("#deleteVendorDialog").dialog({
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
function c(r) {
if (___) {
    var t = (___5) ? "Microsoft.XMLHTTP" : "Msxml2.XMLHTTP";
    try {
      o = new ActiveXObject(t);
      o.onreadystatechange = r;
    } catch (ex) {
      alert("<bean:message key='BzComposer.common.needToEnableActiveXObject'/> ts.." + ex);
    }
  } else {
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
	padding: 5px 0px 5px 14px;
	/* 	border-bottom: 1px solid rgb(207, 207, 207); */
	background: #fff;
	vertical-align: top;
}
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
</head>
<body onload="initialize();">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<form:form name="CustomerForm"  method="post" modelAttribute="vendorDetails1">
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
	<div>
		<div style="float: left;">
			<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
				<spring:message code="BzComposer.vendorlist.vendorinformation" />
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
                    <td>
                        <input type="text" id="searchVenderID" style="font-size: 14px;" />
                    </td>
                </tr> -->
                <tr><td colspan="2">&nbsp;</td></tr>
            </table>
		</div>
		<div style="float: right;">
			<table>
				<tr align="right">
					<td colspan="6">
						<input type="button" name="editAction" id="editAction" value='<spring:message code="BzComposer.global.edit" />'
						title='<spring:message code="BzComposer.vendorlist.edittooltip" />' class="formButton" onclick="showEditVendor();">
						<input type="button" name="deleteAction" id="deleteAction" value='<spring:message code="BzComposer.global.delete" />'
						title='<spring:message code="BzComposer.vendorlist.deletetooltip" />' class="formButton" onclick="DeleteVendor();">
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- dialog space -->
	<!-- Dialog used in vendor list page -->
	<div id="showSelectVendorDialog" style="display:none;">
	<p><spring:message code="BzComposer.vendorlist.selectvendordialog"/></p>
	</div>
	<div id="deleteVendorDialog" style="display:none;">
		<p><spring:message code="BzComposer.vendorlist.selectvendortodelete"/></p>
	</div>
	<div id="deleteRowDialog" style="display:none;">
		<p><spring:message code="BzComposer.vendorlist.deleteselectedvendor"/></p>
	</div>
	<div>
		<form:hidden path="clientVendorID" value="${clientVendorID}" />
		<hidden path="selectedRowID" />
		<input type="hidden" name="venrId" id="venrId" value="" /> 
		<input type="hidden" name="cvId" id="cvId" value=""/>
		<input type="hidden" name="tabid" id="tabid" value="" /> 
		<input type="hidden" name="SelectedRID" id="setRID" value=""> 
		<input type="hidden" name="bst" id="bsst" value="0" />
		<hidden path="state" value="0" />
		<hidden path="bsstate" value="0" />
		<hidden path="shstate" value="0" />
		<input type="hidden" id="country2" value="${vendorDetails1.country}" />
	</div>
	<!-- end Contents -->
	<input type="hidden" name="actionValidate" value="vendor.jsp">
	<table style="width: 100%;">
		<tr>
			<td style="font-size: 14px;">
				<table style="width: 100%;">
					<tr>
						<td style="border: 1px solid #ccc; width: 350px; padding: 0; margin: 0;" valign="top">
							<table id="custTable" class="tabla-listados" cellspacing="0" style="border: 0;padding: 0; margin: 0;">
								<thead>
									<!-- <tr valign="top"> -->
									<tr>
										<th class="emblem" style="font-size: 12px;">
											<div align="center">
												<spring:message code="BzComposer.vendorlist.vendor" />
											</div>
										</th>
									</tr>
								</thead>
								<tbody id="custTableBody">
                                    <c:if test="${not empty VendorDetails}">
                                        <c:forEach items="${VendorDetails}" var="objList" varStatus="loop">
                                                <tr id='${loop.index}$$' onmouseover="setCutomerDataById(${objList.clientVendorID}, ${loop.index});">
                                                    <td colspan="2" style="width: 300px;">${objList.clientVendorID} : ${objList.cname} (${objList.firstName} ${objList.lastName})</td>
                                                </tr>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                                <c:if test="${not empty VendorDetails}">
                                    <input type="hidden" name="listSize" id="lSize" value='${VendorDetails.size()}'>
                                </c:if>
								</td>
								<td style=" margin: 0; padding: 0;vertical-align: 0;">
                                    <c:if test="${not empty VendorDetails}"></c:if>
									<div id="table-negotiations" >
										<table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;margin-right: 20px;">
											<thead>
												<tr>
													<th colspan="6" style="font-size: 12px;">
														<spring:message code="BzComposer.vendorlist.vendorinformation"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<tr>
												    <td style="width: 15%;">
                                                        <spring:message code="BzComposer.vendorlist.titlename" />:
                                                    </td>
                                                    <td style="width: 18%;">
                                                        <form:select path="title" disabled="true">
                                                            <form:options items="${titleList}" itemValue="value" itemLabel="label"/>
                                                        </form:select>
                                                    </td>
													<td style="width: 15%;">
														<spring:message code="BzComposer.vendorlist.firstname" /> 
														<!-- <span class="inputHighlighted">*</span> --> :
													</td>
													<td style="width: 18%;" id="firstName">${vendorDetails1.firstName}</td>
													<td style="width: 15%;">
														<spring:message code="BzComposer.global.middlename" /> :
													</td>
													<td style="width: 18%;" id="middleName">${vendorDetails1.middleName}</td>
												</tr>
												<tr>
												    <td>
                                                        <spring:message code="BzComposer.vendorlist.lastname" />
                                                    </td>
                                                    <td id="lastName">${vendorDetails1.lastName}</td>
                                                    <td>
                                                        <spring:message code="BzComposer.global.customerid" />
                                                    </td>
                                                    <td id="customerID">${vendorDetails1.clientVendorID}</td>
													<td>
                                                        <spring:message code="BzComposer.global.inputdate" />:
                                                    </td>
                                                    <td id="dateInput">${vendorDetails1.dateInput}</td>
												</tr>
												</tr>
												    <td>
                                                        <spring:message code="BzComposer.vendorlist.company" />
                                                        <span class="inputHighlighted">*</span>
                                                    </td>
                                                    <td id="cname">${vendorDetails1.cname}</td>
                                                    <td colspan="4"></td>
												</tr>
												<tr>
													<td>
														<spring:message code="BzComposer.vendorlist.address1" /> 
														<span class="inputHighlighted">*</span>
													</td>
													<td id="address1">${vendorDetails1.address1}</td>
													<td colspan="4"></td>
                                                </tr>
                                                <tr>
													<td>
														<spring:message code="BzComposer.vendorlist.address2" />
														<span class="inputHighlighted">:</span>
													</td>
													<td id="address2">${vendorDetails1.address2}</td>
													<td colspan="4"></td>
												</tr>
												<tr>
												    <td class="zipcodeShow">
                                                        <spring:message code="BzComposer.vendorlist.zipcode" />
                                                    </td>
                                                    <td class="postalcodeShow">
                                                        <spring:message code="BzComposer.global.postalcodes" />
                                                    </td>
                                                    <td id="zipCode">${vendorDetails1.zipCode}</td>
												    <td>
                                                        <spring:message code="BzComposer.vendorlist.city" />
                                                        <span class="inputHighlighted">*</span>
                                                    </td>
                                                    <td style="width:10%;" id="city">${vendorDetails1.city}</td>
													<td id="t_state" class="stateShow">
														<spring:message code="BzComposer.vendorlist.state" />
													</td>
													<td id="t_statedata" class="stateShow"></td>
													<td class="provinceShow">
                                                        <spring:message code="BzComposer.vendorlist.province" />
                                                    </td>
                                                    <td style="width: 10%;" class="provinceShow" id="province">
                                                            ${vendorDetails1.province}
                                                    </td>
												</tr>
												<script>
													<c:if test="${not empty state_gen}">
									    				var contry=document.VendorForm.country.value;
														refreshItemsNow33(contry,'${state_gen}');
													</c:if>
												</script>
												<tr>
												    <td id="t_country">
                                                        <spring:message code="BzComposer.vendorlist.country" />
                                                    </td>
                                                    <td>
                                                        <form:select path="country" disabled="true">
                                                            <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                            <form:options items="${cList}" itemValue="value" itemLabel="label"/>
                                                        </form:select>
                                                    </td>
                                                    <td>
														<spring:message code="BzComposer.vendorlist.phone" />
													</td>
													<td style="width: 10%;" id="phone">${vendorDetails1.phone}</td>
													<td>
                                                        <spring:message code="BzComposer.vendorlist.cellphone" />:
                                                    </td>
                                                    <td>
                                                        <span style="color: black!important;" id="cellPhone">${vendorDetails1.cellPhone}</span>&nbsp;&nbsp;
                                                        <input type="checkbox" id="isPhoneNumber" disabled="true" ${vendorDetails1.isMobilePhoneNumber==true?'checked':''} />
                                                        (<spring:message code="BzComposer.global.isPhoneNumber" />)
                                                    </td>
												</tr>
												<tr>
												    <td>
                                                        <spring:message code="BzComposer.vendorlist.fax" />
                                                    </td>
                                                    <td style="width: 10%;" id="fax">${vendorDetails1.fax}</td>
												    <td>
														<spring:message code="BzComposer.vendorlist.email" />
													</td>
													<td id="email">${vendorDetails1.email}</td>
													<td>
                                                        <spring:message code="BzComposer.vendorlist.homepage" />
                                                    </td>
                                                    <td style="width: 10%;" id="homePage">${vendorDetails1.homePage}</td>
												</tr>
												<tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.dateadded" />
                                                    </td>
                                                    <td style="font-size:12px;width: 10%" id="dateAdded">
                                                        ${vendorDetails1.dateAdded}
                                                    </td>
                                                    <td style="font-size:12px;">
                                                        <spring:message code="BzComposer.global.terminated" />
                                                    </td>
                                                    <td style="font-size:12px;" id="terminated">
                                                        ${vendorDetails1.terminated}
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.terminatedDate" />
                                                    </td>
                                                    <td style="font-size:12px;" id="terminatedDate">
                                                        ${vendorDetails1.terminatedDate}
                                                    </td>
                                                </tr>
												<tr>
												    <td>
                                                        <spring:message code="BzComposer.Companyinformation.Type" />
                                                    </td>
                                                    <td>
                                                        <form:select path="type" disabled="true">
                                                            <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                            <form:options items="${VendorCategoryList}" itemValue="value" itemLabel="label"/>
                                                        </form:select>
                                                    </td>
													<td>
														<spring:message code="BzComposer.vendorlist.taxid" />
													</td>
													<td id="texID">${vendorDetails1.texID}</td>
													<td>
                                                        <input type="checkbox" id="isTaxable" disabled="disabled" ${vendorDetails1.taxAble==1?'checked':''} />
														<spring:message code="BzComposer.vendorlist.istaxable" />
													</td>
													<td>
													    <input type="checkbox" id="isClient" disabled="disabled" ${vendorDetails1.isclient=='1'?'checked':''} />
														<spring:message code="BzComposer.vendorlist.isalsoclient" />
													</td>
												</tr>
												<tr>
												    <td>
                                                        <spring:message code="BzComposer.vendorlist.oppeningunpaidbalance" />
                                                    </td>
                                                    <td id="openingUB">${vendorDetails1.openingUB}</td>
													<td>
														<spring:message code="BzComposer.vendorlist.existingcredits" />
													</td>
													<td id="extCredit">${vendorDetails1.extCredit}</td>
													<td colspan="2"></td>
												</tr>
											    <tr>
											        <td><spring:message code="BzComposer.Customer.Memo" /></td>
											        <td colspan="5" id="memo">${vendorDetails1.memo}</td>
											    </tr>
											</tbody>
										</table>
										<%-- ================== Preference-Info =============== --%>
										<table class="tabla-listados" cellspacing="0" style="margin-top: 0; margin-left: 20px;">
                                            <thead>
                                                <tr>
                                                    <th colspan="4" style="font-size: 12px;">
                                                        <spring:message code="BzComposer.addnewcustomer.prefrenceinfo" />
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.term" />
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <form:select path="term" disabled="true" style="width:150px;">
                                                            <form:option value=""><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                            <form:options items="${TermList}" itemValue="term" itemLabel="name"/>
                                                        </form:select>
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.paymentmethod" />
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <form:select path="paymentType" disabled="true" style="width:150px;">
                                                            <form:option value=""><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                            <form:options items="${PaymentList}" itemValue="id" itemLabel="typeName"/>
                                                        </form:select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.representative" />
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <form:select path="rep" disabled="true" style="width:150px;">
                                                            <form:option value=""><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                            <form:options items="${RepList}" itemValue="value" itemLabel="label"/>
                                                        </form:select>
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.global.shippingvia" />
                                                    </td>
                                                    <td style="font-size: 12px;">
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
                                                <tr>
                                                    <td colspan="4">
                                                        <div class="container text-center">
                                                            <div class="row text-left" id="customerCards">
                                                                <c:forEach items="${vendorDetails1.customerCards}" var="objList">
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
                                                                                <a href="Customer?tabid=makeCustomerCardDefault3&cvId=${objList.clientVendorID}&cardID=${objList.cardID}&SelectedRID=${SelectedRID}">
                                                                                    <strong><spring:message code="BzComposer.global.default"/></strong></a>
                                                                            </p>
                                                                        </c:otherwise>
                                                                        </c:choose>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                </c:forEach>
                                                                <c:if test="${vendorDetails1.customerCards.size()==0}">
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
                                                                    ${vendorDetails1.bscname}
                                                                </td>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.province" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="bsprovince">
                                                                    ${vendorDetails1.bsprovince}
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.firstname" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="bsfirstName">
                                                                    ${vendorDetails1.bsfirstName}
                                                                </td>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.lastname" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="bslastName">
                                                                    ${vendorDetails1.bslastName}
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.address1" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="bsaddress1">
                                                                    ${vendorDetails1.bsaddress1}
                                                                </td>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.address2" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="bsaddress2">
                                                                    ${vendorDetails1.bsaddress2}
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            <tr>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.zipcode" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="bszipCode">
                                                                    ${vendorDetails1.bszipCode}
                                                                </td>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.city" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="bscity">
                                                                    ${vendorDetails1.bscity}
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
                                                                    ${vendorDetails1.shcname}
                                                                </td>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.province" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="shprovince">
                                                                    ${vendorDetails1.shprovince}
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.firstname" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="shfirstName">
                                                                    ${vendorDetails1.shfirstName}
                                                                </td>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.lastname" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="shlastName">
                                                                    ${vendorDetails1.shlastName}
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.address1" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="shaddress1">
                                                                    ${vendorDetails1.shaddress1}
                                                                </td>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.address2" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="shaddress2">
                                                                    ${vendorDetails1.shaddress2}
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.zipcode" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="shzipCode">
                                                                    ${vendorDetails1.shzipCode}
                                                                </td>
                                                                <td style="font-size: 12px;">
                                                                    <spring:message code="BzComposer.global.city" />
                                                                </td>
                                                                <td style="font-size: 12px;" id="shcity">
                                                                    ${vendorDetails1.shcity}
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
                                                                                ${vendorDetails1.annualIntrestRate}
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="font-size: 12px;">
                                                                                <spring:message code="BzComposer.addnewcustomer.minimumfinanacecharge" />
                                                                            </td>
                                                                            <td colspan="3" style="font-size: 12px;" id="minFCharges">
                                                                                ${vendorDetails1.minFCharges}
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="font-size: 12px;">
                                                                                <spring:message code="BzComposer.addnewcustomer.graceperiod" />
                                                                            </td>
                                                                            <td colspan="3" id="gracePrd">
                                                                                ${vendorDetails1.gracePrd}
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
									<%-- </logic:present> --%>
								</td>
							</tr>
						</table>
					</td>
				</tr>

							</table>
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
<!-- dialog box that used in this page -->
<script>
$(function() {
    $('#sortBy').change(function(){
        var sortBy = $(this).val();
        $.ajax({
            type: "POST",
            url:"VendorAjax?tabid=sortVendors&SortBy="+sortBy,
            data:{sortBy : sortBy},
        }).done(function(data){
            setVendorListData(data);
        });
    });

    $('#searchVenderID').keyup(function(){
        var venderText = $(this).val();
        $.ajax({
            type: "POST",
            url:"VendorAjax?tabid=searchVendors&venderText="+venderText,
            data:{venderText : venderText},
        }).done(function(data){
            setVendorListData(data);
        });
    });

});
function setVendorListData(data){
    $('#custTableBody').html('');
    var custDetails = "";
    for(var i=0; i<data.length; i++){
        debugger;
        var objList = data[i];
        custDetails = custDetails +"<input type='hidden' name='listSize' id='lSize' value='"+data.length+"'>"
            +"<tr id='"+i+"$$' onmouseover='setCutomerDataById("+objList.clientVendorID+","+i+");'>"
            +"<td colspan='2' style='width: 300px;'>"+objList.cname+" ("+objList.firstName+" "+objList.lastName+")</td></tr>";
    }
    $('#custTableBody').html(custDetails);
}

$(document).ready(function() {
    $('#custTable').DataTable({
        "iDisplayLength": 50,
        "ordering": false
    });
    $("#custTable_length").hide();
});
</script>