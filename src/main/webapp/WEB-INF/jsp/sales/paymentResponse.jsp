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
#paymentDetails th, td {
  padding: 10px;
}
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
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<!-- begin Contents -->
<div>
<div style="float: left;width:100%;">
	<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		Payment Status
	</span>
	<br>
	<div class="text-center">
        <h2>${paymentDetails.error_message}</h2>
        <c:if test="${paymentDetails.status == 1}">
            <table id="paymentDetails" style="width:20%;margin:auto;">
                <tr>
                    <td><strong>Amount:</strong></td>
                    <td>$${paymentDetails.amount}</td>
                </tr>
            </table>
        </c:if>
	</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<div style="margin-top:400px;">
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</div>
</body>
</html>