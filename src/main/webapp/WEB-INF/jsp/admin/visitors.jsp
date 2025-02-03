<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menuAdmin.jsp"%>
<title><spring:message code="BzComposer.customerinfotitle" /></title>
<style>
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
<div style="float: left;">
	<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.common.visitors" />
		<c:if test="${not empty actionMsg}">
            <br/> ${actionMsg}
            <% session.removeAttribute("actionMsg"); %>
        </c:if>
	</span>
</div>
<div style="float: right;">
	<table>
		<tr align="right">
			<td colspan="6">
				<div>
					<input type="button" class="formbutton" onclick="addNewCustomer();" style="padding:7 15px;" value="<spring:message code='BzComposer.global.new'/>" />
					<input type="button" class="formbutton" onclick="manageCustomer('EDIT');" style="padding:7 15px;" value="<spring:message code='BzComposer.global.edit'/>" />
					<input type="button" class="formbutton" onclick="manageCustomer('DELETE');" style="padding:7 15px;" value="<spring:message code='BzComposer.global.delete'/>" />
				</div>
			</td>
		</tr>
	</table>
</div>
</div>
<input type="hidden" id="lSize" value='${visitorList.size()}' />
<table id="custTable" class="tabla-listados" cellspacing="0" style="width: 100%; margin-top: 10px; border: 0; padding: 0;height: auto;" align="center">
    <thead>
        <tr valign="center">
            <th><spring:message code="BzComposer.Customer.ID" /></th>
            <th><spring:message code="bca.name" /></th>
            <th><spring:message code="BzComposer.global.company" /></th>
            <th><spring:message code="BzComposer.global.email" /></th>
            <th><spring:message code="BzComposer.global.phone" /></th>
            <th><spring:message code="BzComposer.register.membership" /></th>
            <th><spring:message code="BzComposer.register.jobposition" /></th>
            <th><spring:message code="BzComposer.global.dateadded" /></th>
            <th><spring:message code="BzComposer.global.expdate" /></th>
            <th><spring:message code="Bizcomposer.active" /></th>
        </tr>
    </thead>
    <tbody id="custTableBody">
        <c:forEach items="${visitorList}" var="objList" varStatus="loop">
            <tr id='${loop.index}$$' onclick="setRowId(${objList.userID}, ${loop.index}, true);">
                <td>${objList.userID}</td>
                <td>${objList.firstName} ${objList.lastName}</td>
                <td>${objList.companyName}</td>
                <td>${objList.emailAddress}</td>
                <td>${objList.phone}</td>
                <td>${objList.membershipLevel}</td>
                <td>${objList.jobPosition}</td>
                <td>${objList.dateAdded}</td>
                <td>${objList.dateExpiry}</td>
                <td>${objList.active}</td>
            </tr>
        </c:forEach>
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
$(document).ready(function() {
    $('#custTable').DataTable({
        "iDisplayLength": 25,
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

function addNewCustomer(){
	window.open("administer?tabid=AddVisitor", null,"scrollbars=yes,height="+screen.height+",width=1300,status=yes,toolbar=no,menubar=no,location=no");
}
function manageCustomer(cmd){
    debugger;
	if (itemID == 0) {
		return showCustomerValidationDialog();
	} else {
		if (cmd=="EDIT") {
			//window.location = "administer?tabid=EditVisitor&userID="+itemID;
			window.open("administer?tabid=EditVisitor&userID="+itemID, null,"scrollbars=yes,height="+screen.height+",width=1300,status=yes,toolbar=no,menubar=no,location=no");
		}
		else if (cmd=="DELETE") {
			event.preventDefault();
			$("#deleteSelectedUser").dialog({
		    	resizable: false,
		        height: 200,
		        width: 500,
		        modal: true,
		        buttons: {
		            "<spring:message code='BzComposer.global.ok'/>": function () {
		                $(this).dialog("close");
		                window.location = "administer?tabid=DeleteVisitor&userID="+itemID;
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
	<p><spring:message code="BzComposer.common.selectAtleast1Record"/></p>
</div>
<div id="deleteSelectedUser" style="display:none;">
	<p><spring:message code="BzComposer.common.wantToDelete"/></p>
</div>