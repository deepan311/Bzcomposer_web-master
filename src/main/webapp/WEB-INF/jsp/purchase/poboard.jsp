<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.purchaseorderboardtitle"/></title>
<style type="text/css">
.fht-tbody{
    height: 180px !important; /*  change table height*/
    border-bottom: 1px solid rgb(207, 207, 207);
}
.dataTables_length{ font-size:14px; }
.dataTables_filter{ font-size:14px; }
.dataTables_info{ font-size:14px; }
.dataTables_paginate{ font-size:14px; }

table.tabla-listados { width: 100%; border: 1px solid rgb(207, 207, 207); margin: 0px 0px 0px 0px; }
table.tabla-listados tbody tr.odd td { background: #e1e5e9; }
table.tabla-listados thead tr th { font-size: 14px; }
table.tabla-listados tbody tr td { font-size: 12px; }
</style>
</head>
<body onload="Init();">
<!-- begin shared/header -->
<form:form action="PurchaseBoard?tabid=ShowList" method="post" id="frmpoboard" name="PurchaseBoardForm" modelAttribute="purchaseBoardDto">
	<div id="">
	<div id="ddcolortabsline">&nbsp;</div>
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
	<div>
		<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.purchaseorderboard.poboard" />
			<c:if test="${not empty msg}">
                <div><span class="msgstyle">*${msg}</span></div>
            </c:if>
            <c:if test="${not empty SaveStatus}">
                <div>
                    <span class="msgstyle">*${SaveStatus}</span>
                    <% session.removeAttribute("SaveStatus"); %>
                </div>
            </c:if>
		</span>
	</div>
	<div>
		<div id="table-negotiations">
		    <table cellspacing="0" align="center" style="width: 100%;">
                <tr align="left">
                    <td style="font-size:14px;">
                        <c:if test="${not empty IsUpdated}">
                            <strong><span class="msgstyle">${IsUpdated}</span></strong>
                        </c:if>
                    </td>
                </tr>
            </table>
			<table cellspacing="0" align="center" class="section-border" style="width: 100%;">
				<tr>
					<td style="width:30%;padding:10px;">
						<table style="width: 100%;font-size:14px;border-right:2px solid #dddddd;" cellpadding="5">
                            <tr>
                                <th colspan="2"><spring:message code="BzComposer.estimationboard.filteroption" /></th>
                            </tr>
                            <tr>
                                <td colspan="2"><spring:message code="BzComposer.salesorderboard.daterange" /></td>
                            </tr>
                            <tr>
                                <td><spring:message code="BzComposer.purchaseorderboard.purchaseorderdate" /> <spring:message code="BzComposer.sales.FromDate" /> :</td>
                                <td><spring:message code="BzComposer.purchaseorderboard.purchaseorderdate" /> <spring:message code="BzComposer.purchaseorderboard.to" /> :</td>
                            </tr>
                            <tr>
                                <td style="width: 50%;">
                                    <form:input path="orderDate1" size="20" style="width: 120px;" />
                                    <img style="margin: 5;"src="${pageContext.request.contextPath}/images/cal.gif" onclick="displayCalendar(document.PurchaseBoardForm.orderDate1,'mm-dd-yyyy',this);" />
                                </td>
                                <td>
                                    <form:input path="orderDate2" size="20" style="width: 120px;" />
                                    <img style="margin: 5;" src="${pageContext.request.contextPath}/images/cal.gif" onclick="displayCalendar(document.PurchaseBoardForm.orderDate2,'mm-dd-yyyy',this);">
                                </td>
                            </tr>
                        </table>
					</td>
					<td style="width:25%;padding:10px;">
                        <table style="width: 100%;font-size:14px;border-right:2px solid #dddddd;" cellpadding="5">
                            <tr><th colspan="2">&nbsp;</th></tr>
                            <tr>
                                <td colspan="2"><spring:message code="BzComposer.purchaseorderboard.PONum" />#</td>
                            </tr>
                            <tr>
                                <td><spring:message code="menu.quickbook.from" /> :</td>
                                <td><spring:message code="menu.quickbook.to" /> :</td>
                            </tr>
                            <tr>
                                <td style="width: 50%;">
                                    <form:input path="orderNoFrom" style="width: 120px;" onkeypress="return numbersonly(event, this.value)" />
                                </td>
                                <td>
                                    <form:input path="orderNoTo" style="width: 120px;" onkeypress="return numbersonly(event, this.value)" />
                                </td>
                            </tr>
                        </table>
                    </td>
					<td style="width:25%;">
						<table style="width: 100%;font-size:14px;" cellpadding="5">
                            <tr><th colspan="2">&nbsp;</th></tr>
                            <tr>
                                <td><spring:message code="BzComposer.estimationboard.column" /></td>
                                <td>
                                    <form:select path="searchType" style="width: 150px;">
                                        <option value="1"><spring:message code="BzComposer.estimationboard.name" /></option>
                                        <option value="2"><spring:message code="BzComposer.estimationboard.order" /></option>
                                        <option value="3"><spring:message code="BzComposer.estimationboard.orderid" /></option>
                                        <option value="4"><spring:message code="BzComposer.estimationboard.address" /></option>
                                        <option value="5"><spring:message code="BzComposer.estimationboard.productname" /></option>
                                        <option value="6"><spring:message code="BzComposer.estimationboard.email" /></option>
                                    </form:select>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="BzComposer.estimationboard.text" /></td>
                                <td><form:input type="text" path="searchTxt" style="width: 150px;" /></td>
                            </tr>
                            <tr><td colspan="2"><spring:message code="BzComposer.salesorderboard.dateformat" /> : (MM-DD-YYYY)</td></tr>
                        </table>
					</td>
					<td style="width:20%;">
                        <div><button type="button" class="formbutton" onclick="SaleSearch(1);" style="width: 70px;"><spring:message code='BzComposer.estimationboard.search'/></button></div>
                        <div><button type="button" class="formbutton" onclick="SaleSearch(2);" style="width: 70px;margin-top:10px;"><spring:message code='BzComposer.estimationboard.refresh'/></button></div>
                        <div>
                            <button type="button" class="formbutton" onclick="SaleSearch(3);" style="width: 70px;margin-top:10px;margin-right:20px;"><spring:message code='BzComposer.estimationboard.clear'/></button>
                            <button type="button" class="formbutton" onclick="downloadPOBoardReport();" style="width: 110px;margin-top:10px;"><spring:message code='BzComposer.global.MultiPrint'/></button>
                        </div>
                    </td>
				</tr>
			</table>
		<div>
			<br/>
			<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
				<spring:message code="BzComposer.purchaseorderboard.purchaseorderlist"/>
			</span>	
			<div>	
     			<div class="grid_8 tabla-listados" id="purchaseOrderList" >
     			<section>
                    <c:forEach items="${PurchaseBoardDetails}" var="currObject" varStatus="loop">
                        <input type="hidden" id="selectedPOID${loop.index}" value="${currObject.po_no}" />
                    </c:forEach>
     			    <input type="hidden" name="sListSize" id="lSize" value='${PurchaseBoardDetails.size()}'>
      				<table id="puboardList" class="tabla-listados" cellpadding="0" cellspacing="0">
						<thead style="font-weight: bold;">
							<tr>
							    <th style="font-size: 14px;"><input type="checkbox" id="allRecordsChkHead" onchange="getAllRecordsIDs(this)" /> <spring:message code="eSalesInvoiceDetails.All" /></th>
								<th><spring:message code="BzComposer.purchaseorderboard.PONum"/></th>
								<th><spring:message code="BzComposer.purchaseorderboard.purchaseorderdate" /></th>
								<th><spring:message code="BzComposer.purchaseorderboard.companyname" /></th>
								<th><spring:message code="BzComposer.purchaseorderboard.name" /></th>
								<th><spring:message code="BzComposer.purchaseorderboard.poamount" /></th>
								<th><spring:message code="BzComposer.purchaseorderboard.emailid" /></th>
								<th><spring:message code="BzComposer.purchaseorderboard.isemailed" /></th>
								<th><spring:message code="BzComposer.purchaseorderboard.poreceived" /></th>
								<th><spring:message code="BzComposer.purchaseorderboard.receiveddate" /></th>
							</tr>
						</thead>
						<tbody>
                            <c:forEach items="${PurchaseBoardDetails}" var="objList" varStatus="loop">
                                <tr id='${loop.index}$$' ondblclick="sendToPurchase()" onclick="setRowId(${objList.po_no}, ${loop.index}, true);">
                                    <td>
                                        <input type="hidden" class="invoiceID" value="${objList.invoiceID}" />
                                        <input type="checkbox" class="allRecordsCLS" id="allRecordsChk${loop.index}" onchange="getRecordID(this, ${loop.index});" value="${objList.po_no}" />
                                    </td>
                                    <td><spring:message code="BzComposer.purchase.PONumPrefix" />${objList.poNumStr}</td>
                                    <td>${objList.dateAdded}</td>
                                    <td>${objList.companyName}</td>
                                    <td>${objList.lastName}, ${objList.firstName}</td>
                                    <td>${objList.total}</td>
                                    <td>${objList.email}</td>
                                    <td align="center">
                                        <input type="checkbox" name="isEmailValCHK" id="isEmailId" title="isEmailed"
                                            disabled="true" ${objList.emailed == '1'?"checked='true'":""} />
                                    </td>
                                    <td align="center">
                                        <input type="checkbox" name="isReceived${loop.index}" id="isReceived${loop.index}" value="${objList.po_no}" title="isReceived"
                                            onclick="makeUpdate(${objList.po_no}, ${objList.cartID}, this);" ${objList.isReceived == '1'?"checked='true'":""} />
                                    </td>
                                    <td>${objList.dateReceived}</td>
                                </tr>
                            </c:forEach>
						</tbody>
					</table>
				<section>
				</div>
			</div>
		</div>
		<table align="center">
			<tr align="center">
				<td style="font-size:14px;">
					<input type="button" class="formbutton" name="smailbtn" id="smail" disabled="disabled" style="padding: 10px;" onclick="sendToPurchase();"
					    value='<spring:message code="BzComposer.purchaseorderboard.lookup" />' />&nbsp;&nbsp;
                    <input type="button" class="formbutton" style="padding: 10px;" onclick="InvoiceSelectedRecord();"
                        value='<spring:message code="BzComposer.global.InvoiceIt" />' />&nbsp;&nbsp;
                    <input type="button" class="formbutton" disabled="disabled" id="modi" style="padding: 10px;" onclick="makeUpdateReceivedInList();"
                    	value='<spring:message code="BzComposer.checkpurchaseorder.checkpoorders" />' />
					<input type="hidden" name="ONum" id="ONumId"> 
					<input type="hidden" name="sEmail" id="sEmailID"> 
					<input type="hidden" name="rNum" id="rowONum"> 
					<input type="hidden" name="senderEmail" id="EID">
				</td>
			</tr>
		</table>
		<input type="hidden" id="ordId" name="OrderValue" value="" />
		<input type="hidden" id="statusId" name="StatusValue" value="" />
		<input type="hidden" id="cartIDs" name="cartIDs" value="" />
		<input type="hidden" id="ordSize" name="Size" value="" />
		<input type="hidden" id="tabid" name="tabid" />
		<!-- end Contents -->
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	<input type="hidden" id="ord_value" />
		<input type="hidden" id="po_value" />
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" />
<script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
</body>
</html>
<script type="text/javascript">
let itemID = 0;
let itemIndex = 0;
$(document).ready(function() {
    $('#puboardList').DataTable({
        "iDisplayLength": 20,
        "ordering": false,
        "fnDrawCallback": function( oSettings ) {
            setRowId(0, 0, false);
            hightlightROW();
        }
    });
});
function Init(){
    setTimeout(function () {
        document.getElementById("0$$").click();
    }, 1000);
}
function setIsEmail()
{

	document.getElementById("isEmailChk").value=document.getElementById("EmailId").value;
	document.forms[0].action = "Email?tabid=EOSOLO";
	document.forms[0].submit();
}
function checkName()
{
	if(document.RMAForm.fnameTxt.value=="" && document.RMAForm.lnameTxt.value=="")	
	{

		return showNameDialog();
	}
	else
	{
		document.forms[0].action = "RMA?tabid=R0S0C0" ;
		document.forms[0].submit();
	}	
}

function setRowId(rowId, rowIndex, flag)
{
	document.getElementById("po_value").value=rowId;
	size=document.getElementById("lSize").value;
	type=document.getElementById("searchType").value;
	typeVal=document.getElementById("searchTxt").value
	for(i=0;i<size;i++){
		 let currROW = document.getElementById(i+'$$');
         if(currROW == null) continue;
         if(i%2 == 1){
             currROW.className = "odd";
         }else{
             currROW.className = "even";
         }
	}
	if(flag){
        itemID = rowId;
        itemIndex = rowIndex;
        if(rowIndex%2 == 1){ ;
            document.getElementById(rowIndex+"$$").classList.remove('odd');
        }
        document.getElementById(rowIndex+'$$').classList.add('draft');
    }
	document.getElementById("smail").disabled=false;
	document.getElementById("rowONum").value=rowId;
}
function hightlightROW(){
    let currROW2 = document.getElementById(itemIndex+'$$');
    if(currROW2 != null){
        currROW2.className = "draft";
    }
    getAllRecordsIDs2();
}

let selectedRowIDs = [];
function getAllRecordsIDs() {
    selectedRowIDs = [];
    let isAllSelected = document.getElementById('allRecordsChkHead').checked;
    let size = document.getElementById("lSize").value;
    if(isAllSelected){
        for(i=0; i<size; i++){
            selectedRowIDs.push(document.getElementById("selectedPOID"+i).value);
        }
    }
    getAllRecordsIDs2();
}
function getAllRecordsIDs2() {
    $('.allRecordsCLS').prop('checked', false);
    let size = document.getElementById("lSize").value;
    for(i=0; i<size; i++){
         let currChkBox = document.getElementById('allRecordsChk'+i);
         if(currChkBox){
            for(j=0; j<selectedRowIDs.length; j++){
                if(selectedRowIDs[j] == currChkBox.value){
                    currChkBox.checked = true;
                    break;
                }
            }
         }
    }
}
function getRecordID(currChkBox, rowIndex) {
    if(currChkBox.checked){
        selectedRowIDs.push(currChkBox.value);
    }else{
        const index = selectedRowIDs.indexOf(currChkBox.value);
        if (index > -1) {
          selectedRowIDs.splice(index, 1);
        }
    }
    if(document.getElementById("lSize").value == selectedRowIDs.length){
        document.getElementById('allRecordsChkHead').checked = true;
    }else{
        document.getElementById('allRecordsChkHead').checked = false;
    }
}

function sendToPurchase(){
	po_no = document.getElementById("po_value").value;
	if(po_no == ""){
		return showpurchaseorderdialog();
	}else{
		window.location.href = "PurchaseOrder?tabid=PBLU&po_no="+po_no;
	}
}

function InvoiceSelectedRecord(){
    let ItemCells = $("#puboardList tr.draft")[0].cells;
    if(ItemCells && ItemCells.length){
        let invoiceID = ItemCells[0].getElementsByClassName("invoiceID")[0].value;
        window.location = "Invoice?tabid=makeSelectedOrderAsInvoice&reqType=PO&invoiceID="+invoiceID;
    }else{
        alert("<spring:message code='BzComposer.categorymanager.selectitemrow'/>");
    }
}

var ordarr = new Array();
var statusarr = new Array();
var cartIDarr = new Array();
var cnt=0;
var ord="";
var status="";
var cartIDs="";

function makeUpdate(orderno, cartID, obj)
{
	var flag=0;
	val = obj.checked;
	for(j=0;j<=cnt;j++){
		if(orderno==ordarr[j]){
			flag=1;
			statusarr[j]=val;
		}
	}
	if(flag==0)
	{
		ordarr[cnt]=orderno;
		statusarr[cnt]=val;
		cartIDarr[cnt]=cartID;
		cnt++;
	}
	document.getElementById("modi").disabled = false;
}

function makeUpdateReceivedInList(){
	debugger;
	for(var j=0;j<cnt;j++){
		ord+=ordarr[j]+";";
		status+=statusarr[j]+";";
		cartIDs+=cartIDarr[j]+";";
	}
	document.getElementById('ordSize').value=cnt;
	document.getElementById('ordId').value=ord;
	document.getElementById("statusId").value=status;
	document.getElementById("cartIDs").value=cartIDs;

	event.preventDefault();
    $("#showUpdateDialog").dialog({
        resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
                debugger;
                document.getElementById("tabid").value="UpdateCheckPO";
                document.forms['frmpoboard'].action ="CheckPO?tabid=UpdateCheckPO";
                document.forms['frmpoboard'].submit();
                //$('checkPO').submit();
            },
            <spring:message code='BzComposer.global.cancel'/>: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}

function makeUpdateInList()
{
	debugger;
	for(j=0;j<cnt;j++)
	{
		ord+=ordarr[j]+";";
		status+=statusarr[j]+";";
	}
	document.getElementById('ordSize').value=cnt;
	document.getElementById('ordId').value=ord;
	document.getElementById("statusId").value=status;
	/* response = window.confirm("Do you want to update record?");
	if(response)
	{
		document.getElementById("modi").disabled=false;
		document.forms[0].action = "PurchaseBoard?tabid=UpdateRecord";
		document.forms[0].submit();
	} */
	event.preventDefault();
	$("#updateRecord").dialog({
	    	resizable: false,
	        height: 200,
	        width: 500,
	        modal: true,
	        buttons: {
	            "<spring:message code='BzComposer.global.ok'/>": function () {
	                $(this).dialog("close");
	            	document.getElementById("modi").disabled=false;
	        		document.forms[0].action = "PurchaseBoard?tabid=UpdateRecord";
	        		document.forms[0].submit();
					$('form').submit();
	            },
	            <spring:message code='BzComposer.global.cancel'/>: function () {
	                $(this).dialog("close");
	                return false;
	            }
	        }
	    });
	    return false;
}

function SaleSearch(filterType)
{
	if(filterType > 1){
        location.reload();
    }
    let searchType = $("#searchType").val();
    let searchTxt = $("#searchTxt").val();
    let orderDate1 = $("#orderDate1").val();
    let orderDate2 = $("#orderDate2").val();
    let orderNoFrom = $("#orderNoFrom").val();
    let orderNoTo = $("#orderNoTo").val();
    $.ajax({
        type : "POST",
        url : "PurchaseBoard?tabid=ShowList",
        data:"searchType=" + searchType + "&searchTxt=" +searchTxt+ "&orderDate1=" +orderDate1+ "&orderDate2=" +orderDate2+ "&orderNoFrom=" +orderNoFrom+ "&orderNoTo=" +orderNoTo,
        success : function(data){
            $(document).find('div#purchaseOrderList section').replaceWith($(data).find('div#purchaseOrderList').html());
            selectedRowIDs = [];
        },
         error : function(data) {
             alert("<spring:message code='BzComposer.billingboard.someerroroccurred'/>");
        }
    });
}
function showList()
{
	debugger;
	var sortType1 = document.getElementById("sortType1").value;
	var sortType2 = document.getElementById("sortType2").value;
	if(sortType1 == 0 || sortType2 == 0){
		return showSortTypeDialog();
		
	}else {
		document.forms['frmpoboard'].action = "PurchaseBoard?tabid=ShowList";
		document.forms['frmpoboard'].submit();
		
	}
}
/* debugger;
var sortType1 = document.getElementById("sortType1").value;
var sortType2 = document.getElementById("sortType2").value;

if(sortType1 == 0 || sortType2 == 0){

}
else{

	event.preventDefault();
} */

function downloadPOBoardReport(){
    if(selectedRowIDs.length == 0){
        alert("<spring:message code='BzComposer.common.selectAtleast1Record'/>");
    }else{
        window.location = "/PdfReport/downloadPOBoardReport?selectedRowIDs="+selectedRowIDs;
    }
}

function showNameDialog(){
	event.preventDefault();
	$("#showNameDialog").dialog({
		resizable: false,
	    height: 200,
	    width: 500,
	    modal: true,
	    buttons: {
	        "<spring:message code='BzComposer.global.ok'/>": function () {
	            $(this).dialog("close");
	        }
	    }
	});
	return false;
}
function showErrorTextDialog(){
	event.preventDefault();
	$("#showErrorTextDialog").dialog({
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
function showSortTypeDialog(){
	event.preventDefault();
	$("#showSortTypeDialog").dialog({
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
function showpurchaseorderdialog(){
	event.preventDefault();
	$("#showpurchaseorderdialog").dialog({
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
<!-- Dialog box used in this page. -->
<div id="showNameDialog" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorderboard.enterfirstorlastname"/></p>
</div>
<div id="updateRecord" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorderboard.updaterecordmessage"/></p>
</div>
<div id="showErrorTextDialog" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorderboard.entertexttosearch"/></p>
</div>
<div id="showSortTypeDialog" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorderboard.selectsorttype"/></p>
</div>
<div id="showpurchaseorderdialog" style="display:none;">
	<p><spring:message code="BzComposer.common.selectpurchaseorderfirst"/></p>
</div>
<div id="showUpdateDialog" style="display:none;">
	<p><spring:message code="BzComposer.checkpurchaseorder.updaterecordmessage"/></p>
</div>