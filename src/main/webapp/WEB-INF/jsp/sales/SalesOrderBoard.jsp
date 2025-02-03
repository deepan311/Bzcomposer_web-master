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
<title><spring:message code="BzComposer.salesorderboardtitle"/></title>
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
<form:form id="SalesBoardForm" name="salesboardForm" action="SalesOrderBoard?tabid=ShowList" method="post" modelAttribute="salesBoardDto">
	<div id="">
	<div id="ddcolortabsline">&nbsp;</div>
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
	<div>
		<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.salesorderboard.salesorderboard" />
			<c:if test="${not empty msg}">
                <div>*${msg}</div>
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
                    <td><spring:message code="BzComposer.salesorderboard.orderdate" /> <spring:message code="BzComposer.sales.FromDate" /> :</td>
                    <td><spring:message code="BzComposer.salesorderboard.orderdate" /> <spring:message code="BzComposer.salesorderboard.to" /> :</td>
                </tr>
                <tr>
                    <td style="width: 50%;">
                        <form:input path="orderDate1" size="25" style="width: 120px;" />
                        <img src="${pageContext.request.contextPath}/images/cal.gif" onclick="displayCalendar(document.salesboardForm.orderDate1,'mm-dd-yyyy',this);" />
                    </td>
                    <td><form:input path="orderDate2" size="25" style="width: 120px;" />
                        <img src="${pageContext.request.contextPath}/images/cal.gif" onclick="displayCalendar(document.salesboardForm.orderDate2,'mm-dd-yyyy',this);" />
                    </td>
                </tr>
	        </table>
	    </td>
	    <td style="width:25%;padding:10px;">
            <table style="width: 100%;font-size:14px;border-right:2px solid #dddddd;" cellpadding="5">
                <tr><th colspan="2">&nbsp;</th></tr>
                <tr>
                    <td colspan="2"><spring:message code="BzComposer.salesorderboard.salesordernumber" /></td>
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
                    <td><spring:message code="BzComposer.salesorderboard.column" /></td>
                    <td>
                        <form:select path="searchType" style="width: 150px;">
                            <option value="1"><spring:message code="BzComposer.salesorderboard.name" /></option>
                            <option value="2"><spring:message code="BzComposer.salesorderboard.order" /></option>
                            <option value="3"><spring:message code="BzComposer.salesorderboard.orderid" /></option>
                            <option value="4"><spring:message code="BzComposer.salesorderboard.address" /></option>
                            <option value="5"><spring:message code="BzComposer.salesorderboard.productname" /></option>
                            <option value="6"><spring:message code="BzComposer.salesorderboard.email" /></option>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td><spring:message code="BzComposer.salesorderboard.text" /></td>
                    <td><form:input type="text" path="searchTxt" style="width: 150px;" /></td>
                </tr>
                <tr><td colspan="2"><spring:message code="BzComposer.salesorderboard.dateformat" /> : (MM-DD-YYYY)</td></tr>
            </table>
	    </td>
	    <td style="width:20%;">
	        <div><button type="button" class="formbutton" onclick="SaleSearch(1);" style="width: 70px;"><spring:message code='BzComposer.salesorderboard.search'/></button></div>
            <div><button type="button" class="formbutton" onclick="SaleSearch(2);" style="width: 70px;margin-top:10px;"><spring:message code='BzComposer.estimationboard.refresh'/></button></div>
            <div>
                <button type="button" class="formbutton" onclick="SaleSearch(3);" style="width: 70px;margin-top:10px;margin-right:20px;"><spring:message code='BzComposer.estimationboard.clear'/></button>
                <button type="button" class="formbutton" onclick="downloadSOBoardReport();" style="width: 110px;margin-top:10px;"><spring:message code='BzComposer.global.MultiPrint'/></button>
            </div>
	    </td>
	    </tr>
		<tr>
			<!-- <td width="27%" valign="top">
				<table class="table-notifications" width="100%">
					<tr>
						<th colspan="2" align="left" style="font-size:14px;">
							<spring:message code="BzComposer.salesorderboard.sortoption" />
						</th>
					</tr>
					<tr>
						<td style="font-size:14px;">1St</td>
						<td style="font-size:14px;">
							<form:select path="sortType1">
								<option value="0"></option>
								<option value="1">
									<spring:message code="BzComposer.salesorderboard.sortbylastname" />
								</option>
								<option value="2">
									<spring:message code="BzComposer.salesorderboard.sortbyzipcode" />
								</option>
								<option value="3">
									<spring:message code="BzComposer.salesorderboard.sortbyproductname" />
								</option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td style="font-size:14px;">2nd</td>
						<td style="font-size:14px;">
							<form:select path="sortType2">
								<option value="0"></option>
								<option value="1">
									<spring:message code="BzComposer.salesorderboard.sortbylastname" />
								</option>
								<option value="2">
									<spring:message code="BzComposer.salesorderboard.sortbyzipcode" />
								</option>
								<option value="3">
									<spring:message code="BzComposer.salesorderboard.sortbyproductname" />
								</option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" class="formbutton" name="findBtn" value="<spring:message code='BzComposer.salesorderboard.showlist'/>">
						</td>
					</tr>
			    </table>
			</td> -->
		</tr>
	</table>
	<div>
		<br/>
		<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.salesorderboard.salesorderlist" />
		</span>
		<div>
   			<div class="grid_8 tabla-listados" id="salesOrderList" >
            <section>
                <c:forEach items="${SalesOrderBoardDetails}" var="currObject" varStatus="loop">
                    <input type="hidden" id="selectedSOID${loop.index}" value="${currObject.so_no}" />
                </c:forEach>
   			    <input type="hidden" name="sListSize" id="lSize" value='${SalesOrderBoardDetails.size()}' />
      			<table id="salesboardList" class="tabla-listados" cellpadding="0" cellspacing="0">
					<thead style="font-weight: bold;">
						<tr>
						    <th style="font-size: 14px;"><input type="checkbox" id="allRecordsChkHead" onchange="getAllRecordsIDs(this)" /> <spring:message code="eSalesInvoiceDetails.All" /></th>
							<th style="font-size:14px;"><spring:message code="BzComposer.salesorderboard.salesordernumber" /></th>
							<th style="font-size:14px;"><spring:message code="BzComposer.salesorderboard.companyname" /></th>
							<th style="font-size:14px;"><spring:message code="BzComposer.salesorderboard.name" /></th>
							<th style="font-size:14px;"><spring:message code="BzComposer.salesorderboard.emailid" /></th>
							<th style="font-size:14px;"><spring:message code="BzComposer.salesorderboard.orderdate" /></th>
							<th style="font-size:14px;"><spring:message code="BzComposer.salesorderboard.saledate" /></th>
							<th style="font-size:14px;"><spring:message code="BzComposer.salesorderboard.printed" /></th>
			    			<th style="font-size:14px;"><spring:message code="BzComposer.salesorderboard.isinvoice" /></th>
			    			<th style="font-size:14px;"><spring:message code="BzComposer.salesorderboard.isemailed" /></th>
						</tr>
					</thead>
					<tbody>
                        <c:forEach items="${SalesOrderBoardDetails}" var="objList" varStatus="loop">
                            <tr id='${loop.index}$$'  ondblclick="sendToOrder()" onclick="setRowId(${objList.so_no}, ${loop.index}, true);">
                                <td>
                                    <input type="hidden" class="invoiceID" value="${objList.invoiceID}" />
                                    <input type="checkbox" class="allRecordsCLS" id="allRecordsChk${loop.index}" onchange="getRecordID(this, ${loop.index});" value="${objList.so_no}" />
                                </td>
                                <td style="font-size:14px;">${objList.soNumStr}</td>
                                <td style="font-size:14px;">${objList.companyName}</td>
                                <td style="font-size:14px;">${objList.lastName}, ${objList.firstName}</td>
                                <td style="font-size:14px;">${objList.email}</td>
                                <td style="font-size:14px;">${objList.saleDate}</td>
                                <td style="font-size:14px;">${objList.dateAdded}</td>
                                <td style="font-size:14px;">
                                    <c:if test="${objList.printed == true}">
                                        <input type="checkbox" name="isPrintedCHK" id="isPrintedId" title="isPrinted" checked="true" disabled="disabled">
                                    </c:if>
                                    <c:if test="${objList.printed == false}">
                                        <input type="checkbox" name="isPrintedCHK" id="isPrintedId" title="isPrinted" disabled="disabled">
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${objList.isInvoice == 1}">
                                        <input type="checkbox"   disabled="disabled" name="isInvoice${loop.index}" id="isInvoiceId" checked="true" value="ON" />
                                    </c:if>
                                    <c:if test="${objList.isInvoice == 0}">
                                        <input type="checkbox" name="isInvoice${loop.index}" id="isInvoiceId" value="ON"  disabled="disabled" />
                                    </c:if>
                                </td>
                                <td style="font-size:14px;">
                                    <c:if test="${objList.emailed == 1}">
                                        <input type="checkbox" value="ON" name="isEmailValCHK" id="isEmailId"
                                        title="isEmailed" checked="true" disabled="disabled"/>
                                    </c:if>
                                    <c:if test="${objList.emailed == 0}">
                                        <input type="checkbox" value="ON" name="isEmailValCHK" id="isEmailId" title="isEmailed" disabled="disabled"/>
                                    </c:if>
                                </td>
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
			<td>
				<input type="button" class="formbutton" name="smailbtn" id="smail" disabled="disabled" style="padding: 10px;" onclick="sendToOrder();"
				    value='<spring:message code="BzComposer.salesorderboard.lookup" />' />&nbsp;&nbsp;
				<input type="button" class="formbutton" id="modi" style="padding: 10px;" onclick="InvoiceSelectedRecord();"
                    value='<spring:message code="BzComposer.global.InvoiceIt" />' />&nbsp;&nbsp;
                <input type="button" class="formbutton" id="modi" style="padding: 10px;"
                    value='<spring:message code="BzComposer.global.sendmail" />' />
				<input type="hidden" name="ONum" id="ONumId">
				<input type="hidden" name="sEmail" id="sEmailID"> 
				<input type="hidden" name="rNum" id="rowONum"> 
				<input type="hidden" name="senderEmail" id="EID">
			</td>
		</tr>
	</table>
	<input type="hidden" id="ordId" name="OrderValue" value=""> 
	<input type="hidden" id="statusId" name="StatusValue" value=""> 
	<input type="hidden" id="ordSize" name="Size" value=""> 
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
    $('#salesboardList').DataTable({
        "iDisplayLength": 20,
        "ordering": false,
        "fnDrawCallback": function( oSettings ) {
            setRowId(0, 0, false);
            getAllRecordsIDs2();
        }
    });
});
function Init(){
    setTimeout(function () {
        document.getElementById("0$$").click();
    }, 1000);
}

function setRowId(rowId, rowIndex, flag){
	document.getElementById("ord_value").value=rowId;
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

let selectedRowIDs = [];
function getAllRecordsIDs() {
    selectedRowIDs = [];
    let isAllSelected = document.getElementById('allRecordsChkHead').checked;
    let size = document.getElementById("lSize").value;
    if(isAllSelected){
        for(i=0; i<size; i++){
            selectedRowIDs.push(document.getElementById("selectedSOID"+i).value);
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

function sendToOrder(){
	order_no = document.getElementById("ord_value").value;
	window.location = "SalesOrder?tabid=SOBLU&order_no="+order_no;
}

function InvoiceSelectedRecord(){
    let ItemCells = $("#salesboardList tr.draft")[0].cells;
    if(ItemCells && ItemCells.length){
        let invoiceID = ItemCells[0].getElementsByClassName("invoiceID")[0].value;
        window.location = "Invoice?tabid=makeSelectedOrderAsInvoice&reqType=SO&invoiceID="+invoiceID;
    }else{
        alert("<spring:message code='BzComposer.categorymanager.selectitemrow'/>");
    }
}

function SaleSearch(filterType){
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
        url : "SalesOrderBoard?tabid=ShowList",
        data:"searchType=" + searchType + "&searchTxt=" +searchTxt+ "&orderDate1=" +orderDate1+ "&orderDate2=" +orderDate2+ "&orderNoFrom=" +orderNoFrom+ "&orderNoTo=" +orderNoTo,
        success : function(data){
            $(document).find('div#salesOrderList section').replaceWith($(data).find('div#salesOrderList').html());
            selectedRowIDs = [];
        },
         error : function(data) {
             alert("<spring:message code='BzComposer.billingboard.someerroroccurred'/>");
        }
    });
}
function downloadSOBoardReport(){
    if(selectedRowIDs.length == 0){
        alert("<spring:message code='BzComposer.common.selectAtleast1Record'/>");
    }else{
        window.location = "/PdfReport/downloadSOBoardReport?selectedRowIDs="+selectedRowIDs;
    }
}
</script>
