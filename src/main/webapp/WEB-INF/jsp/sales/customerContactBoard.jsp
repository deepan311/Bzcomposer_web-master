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
.dataTables_length{ display:none; }
.dataTables_filter{ font-size:14px;float:left !important; }
.dataTables_info{ font-size:14px; }
.dataTables_paginate{ font-size:14px; }

table.sortable thead { background-color: #eee; color: #666666; font-weight: bold; cursor: default; }
table.tabla-listados { width: 100%; border: 1px solid rgb(207, 207, 207); margin: 0px 0px 0px 0px; }
table.tabla-listados tbody tr.odd td { background: #e1e5e9; }
table.tabla-listados thead tr th { font-size: 14px; }
table.tabla-listados tbody tr td { font-size: 12px; }
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
<div>
	<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.sales.ContactBoard" />
		<c:if test="${not empty actionMsg}">
            <br/> ${actionMsg}
            <% session.removeAttribute("actionMsg"); %>
        </c:if>
	</span>
</div>
<div style="width:100%;">
	<table style="width:100%;">
		<tr>
			<td align="left">
                <input type="button" class="formbutton" onclick="openMailSender();" style="padding:7 15px;" value="<spring:message code='BzComposer.Email.SendMail'/>" />
                <input type="button" class="formbutton" onclick="openMailTemplates();" style="padding:7 15px;" value="<spring:message code='BzComposer.Email.MailTemplate'/>" />
                <input type="button" class="formbutton" onclick="openSendThroughOutlook();" style="padding:7 15px;" value="<spring:message code='BzComposer.Email.SendThroughOutlook'/>" />
			</td>
		    <td align="right">
                <input type="button" class="formbutton" onclick="addNewCustomer();" style="padding:7 15px;" value="<spring:message code='BzComposer.global.new'/>" />
                <input type="button" class="formbutton" onclick="manageCustomer('EDIT');" style="padding:7 15px;" value="<spring:message code='BzComposer.global.edit'/>" />
                <input type="button" class="formbutton" onclick="manageCustomer('DELETE');" style="padding:7 15px;" value="<spring:message code='BzComposer.global.delete'/>" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" class="formbutton" onclick="customerImport()" style="padding:7 15px;" value="<spring:message code='menu.file.Import'/>" />
                <input type="button" class="formbutton" onclick="exportCustomer()" style="padding:7 15px;" value="<spring:message code='menu.file.ExportTo'/>" />
            </td>
        </tr>
	</table>
</div>
</div>
<input type="hidden" id="lSize" value='${customerList.size()}' />
<table id="custTable" class="tabla-listados sortable" cellspacing="0" style="width: 100%; margin-top: 10px; border: 0; padding: 0;height: auto;" align="center">
    <thead>
        <tr valign="center">
            <th style="padding-right:30px!important;"><spring:message code="BzComposer.Customer.ID" /></th>
            <th><spring:message code="BzComposer.customerinfo.customer" /></th>
            <th><spring:message code="BzComposer.global.company" /></th>
            <th><spring:message code="BzComposer.Companyinformation.Type" /></th>
            <th><spring:message code="BzComposer.global.email" /></th>
            <th><spring:message code="BzComposer.global.phone" /></th>
            <th><spring:message code="BzComposer.global.mobile" /></th>
            <th><spring:message code="BzComposer.global.fax" /></th>
            <th><spring:message code="BzComposer.global.address1" /></th>
            <th><spring:message code="BzComposer.global.address2" /></th>
            <th><spring:message code="BzComposer.global.city" /></th>
            <th><spring:message code="BzComposer.global.zipcode" /></th>
            <th><spring:message code="BzComposer.global.country" /></th>
            <th><spring:message code="BzComposer.common.overdueAmount" /></th>
            <th><spring:message code="BzComposer.orderimport.lastorderdate" /></th>
            <th><spring:message code="BzComposer.global.dateadded" /></th>
        </tr>
    </thead>
    <tbody id="custTableBody">
        <c:if test="${not empty customerList}">
            <c:forEach items="${customerList}" var="objList" varStatus="loop">
                <tr id='${loop.index}$$' onclick="setRowId(${objList.clientVendorID}, ${loop.index}, true);">
                    <td class="${objList.paymentUnpaid?'redColor':''}">
                        <input type="checkbox" id="custID${loop.index}" value="${objList.clientVendorID}" onchange="addRowIndex(${loop.index}, ${objList.clientVendorID})" />
                        ${objList.clientVendorID}
                    </td>
                    <td>${objList.firstName} ${objList.middleName} ${objList.lastName}</td>
                    <td>${objList.companyName}</td>
                    <td>${objList.type}</td>
                    <td>${objList.email}</td>
                    <td>${objList.phone}</td>
                    <td>${objList.cellPhone}</td>
                    <td>${objList.fax}</td>
                    <td>${objList.address1}</td>
                    <td>${objList.address2}</td>
                    <td>${objList.city}</td>
                    <td>${objList.zipCode}</td>
                    <td>${objList.country}</td>
                    <td>${objList.totalOverdueAmt}</td>
                    <td>${objList.lastOrderDate}</td>
                    <td>${objList.dateAdded}</td>
                </tr>
            </c:forEach>
        </c:if>
    </tbody>
</table>
</div>
<div>
	<input type="hidden" name="tabid" id="tabid" value="" />
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" />
<script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
</body>
</html>
<script>
let itemID = 0;
let itemIndex = 0;
let selectedRowIndexs = [];
$(document).ready(function() {
    $('#custTable').DataTable({
        "iDisplayLength": 20,
        "ordering": true,
        "order": [[0, 'desc']],
        "fnDrawCallback": function( oSettings ) {
            setRowId(0, 0, false);
            hightlightROW();
        }
    });
});

function initialize(){
    let lSize = document.getElementById("lSize").value;
    if(lSize > 0){
        document.getElementById('0$$').className = "even";
    }
}

function setRowId(rowid, rowIndex, flag){
    let lSize = document.getElementById("lSize").value;
    for (i=0; i<lSize; i++) {
        let currROW = document.getElementById(i+'$$');
        if(currROW == null) continue;
        if(i%2 == 1){
            currROW.className = "odd";
        }else{
            currROW.className = "even";
        }
    }
    if(flag){
        itemID = rowid;
        itemIndex = rowIndex;
        if(rowIndex%2 == 1){ ;
            document.getElementById(rowIndex+"$$").classList.remove('odd');
        }
        document.getElementById(rowIndex+'$$').classList.add('draft');
    }
}
function hightlightROW(){
    let currROW2 = document.getElementById(itemIndex+'$$');
    if(currROW2 != null){
        currROW2.className = "draft";
    }
}

function manageCustomer(cmd){
    debugger;
	if (itemID == 0) {
		return showCustomerValidationDialog();
	} else {
		if (cmd=="EDIT") {
			//window.location = "Customer?tabid=editCustomer&cvId="+itemID+"&itemIndex="+itemIndex;
			window.open("Customer?tabid=editCustomer&cvId="+itemID, null,"scrollbars=yes,height=620,width=1200,status=yes,toolbar=no,menubar=no,location=no");
		}
		else if (cmd=="DELETE") {
			event.preventDefault();
			$("#deleteCustomer").dialog({
		    	resizable: false,
		        height: 200,
		        width: 500,
		        modal: true,
		        buttons: {
		            "<spring:message code='BzComposer.global.ok'/>": function () {
		                $(this).dialog("close");
                        let CustIDs = "";
                        for(let x=0; x<selectedRowIndexs.length; x++){
                            CustIDs = CustIDs + selectedRowIndexs[x] +":";
                        }
                        CustIDs = CustIDs.substring(0, CustIDs.length-1);
		                window.location = "Customer?tabid=deleteCustomer&CustIDs="+CustIDs;
		            },
		            <spring:message code='BzComposer.global.cancel'/>: function () {
		                $(this).dialog("close");
		                return false;
		            }
				}
			});
			return false;
		}
	}
}
function addNewCustomer(){
	//window.location = "Customer?tabid=NewCustomer";
	window.open("Customer?tabid=NewCustomer", null,"scrollbars=yes,height=620,width=1200,status=yes,toolbar=no,menubar=no,location=no");
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

function addRowIndex(rowId, custID){
    let isFound = false;
    let isChecked = document.getElementById('custID'+rowId).checked;
    for(let x=0; x<selectedRowIndexs.length; x++){
        if(selectedRowIndexs[x] == custID){
            isFound = true;
            break;
        }
    }
    if(!isFound){
        selectedRowIndexs.push(custID);
    }
    if(!isChecked){
        const index = selectedRowIndexs.indexOf(custID);
        if (index > -1) {
          selectedRowIndexs.splice(index, 1);
        }
    }
}
function openMailSender(){
    if (selectedRowIndexs.length == 0){
        alert("<spring:message code='BzComposer.printlabels.selectcustomer'/>");
        return false;
    }else{
        let CustIDs = "";
        for(let x=0; x<selectedRowIndexs.length; x++){
            CustIDs = CustIDs + selectedRowIndexs[x] +":";
        }
        CustIDs = CustIDs.substring(0, CustIDs.length-1);
        window.open("Customer?tabid=ShowEmailOnCustomerBoard&CustIDs="+CustIDs, null,"scrollbars=yes,height=450,width=800,status=yes,toolbar=no,menubar=no,location=no");
    }
}
function openMailTemplates(){
	window.open("MailTemplates?tabid=getMailTemplates", null,"scrollbars=yes,height=500,width=1000,status=yes,toolbar=no,menubar=no,location=no");
}
</script>
<!-- Dialog box used in sales order page -->
<div id="showCustomerValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.customerinfo.selectcustomerfirst"/></p>
</div>
<div id="deleteCustomer" style="display:none;">
	<p><spring:message code="BzComposer.customerinfo.deleteselectedcustomer"/></p>
</div>