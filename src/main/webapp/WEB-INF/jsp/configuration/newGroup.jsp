<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="BizComposer.Configuration.Networking.addGroup"/></title>
<style type="text/css">
.table-notifications tbody tr td{font-size: 12px; padding: 0;}
</style>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	var checkedBoxes = document.querySelectorAll('input[type=checkbox]');

	var gpPermissions = $('#groupPermissions').val();
	if(gpPermissions != null && gpPermissions != ''){
        for(var i = 0 ; i < checkedBoxes.length; i++){
            if(gpPermissions.charAt(i)==1){
                checkedBoxes[i].checked = true;
            }else{
                checkedBoxes[i].checked = false;
            }
        }
	}
	if($('#isViewGroupPermissions').val()){
	    $('#addNewGroupBtn').hide();
	}
});

function addNewGroup()
{
    event.preventDefault();
    var gName = $("#groupName").val();
    var description = $("#description").val();
    var groupExist = false;
    $('#selectedGroup option').each(function() {
        if($(this).val().toLowerCase() == gName.toLowerCase()){
            groupExist = true;
        }
    });
    if(gName == ""){
        alert("<bean:message key='BzComposer.common.groupCantBeBlank'/>");
        return;
    }else if(description == ""){
         alert("<bean:message key='BzComposer.common.descCantBeBlank'/>");
         return;
     }else if(groupExist){
        alert("<bean:message key='BzComposer.common.groupAlreadyExists'/>");
        return;
    }
    var totalCheckBoxes = $('input[type="checkbox"]').length;
    let accessPermissions = "";
    for(var x=0; x<totalCheckBoxes; x++){
        if($('input[type="checkbox"]')[x].checked) accessPermissions = accessPermissions+'1';
        else accessPermissions = accessPermissions+'0';
    }

    document.getElementById('groupPermissions').value = accessPermissions;
    var formData = $('form').serialize();
    $.ajax({
        type : "POST",
        url :  "ConfigurationAjax/SaveConfiguration?tabid=saveGroup",
        data : formData,
        success : function(data) {
            closeWindow();
            window.opener.location.reload();
        },
        error : function(data) {
            alert("<bean:message key='BzComposer.common.erroroccurred'/>");
        }
    });
}

function checkAll(){
    var isChecked = $('input[type="checkbox"]').prop("checked");
    if(isChecked == true){
        $('input[type="checkbox"]').prop('checked', true);
    }else{
        $('input[type="checkbox"]').prop('checked', false);
    }
}

function checkInvocieModule(){
    var isChecked = $("#invoiceModule").prop("checked");
    if(isChecked == true){
        $('#invoiceBoard').prop('checked',true);
        $('#estimationBoard').prop('checked',true);
        $('#invoice').prop('checked',true);
        $('#estimation').prop('checked',true);
        $('#items').prop('checked',true);
        $('#dataManager').prop('checked',true);
        $('#salesOrder').prop('checked',true);
        $('#salesOrderBoard').prop('checked',true);
    }
    else{
        $('#invoiceBoard').prop('checked',false);
        $('#estimationBoard').prop('checked',false);
        $('#invoice').prop('checked',false);
        $('#estimation').prop('checked',false);
        $('#items').prop('checked',false);
        $('#dataManager').prop('checked',false);
        $('#salesOrder').prop('checked',false);
        $('#salesOrderBoard').prop('checked',false);
    }
}

function ckeckPurchaseModule(){
    var isChecked = $("#purchaseModule").prop("checked");
    if(isChecked == true){
        $('#poBoard').prop('checked',true);
        $('#purchaseOrder').prop('checked',true);
        $('#checkPOOrders').prop('checked',true);
        $('#receivedItems').prop('checked',true);
        $('#poBillBoard').prop('checked',true);
    }
    else{
        $('#poBoard').prop('checked',false);
        $('#purchaseOrder').prop('checked',false);
        $('#checkPOOrders').prop('checked',false);
        $('#receivedItems').prop('checked',false);
        $('#poBillBoard').prop('checked',false);
    }
}

function checkEmployeeModule(){
    var isChecked = $("#employeeModule").prop("checked");
    if(isChecked == true){
        $('#employee').prop('checked',true);
        $('#payRoll').prop('checked',true);
    }
    else{
        $('#employee').prop('checked',false);
        $('#payRoll').prop('checked',false);
    }
}

function checkAccountModule(){
    var isChecked = $("#accountModule").prop("checked");
    if(isChecked == true){
        $('#banking').prop('checked',true);
        $('#accountReceivable').prop('checked',true);
        $('#reconciliation').prop('checked',true);
        $('#categoryDetails').prop('checked',true);
        $('#categoryManager').prop('checked',true);
        $('#accountPayableModule').prop('checked',true);
        $('#payableList').prop('checked',true);
        $('#paidList').prop('checked',true);
        $('#billPayable').prop('checked',true);
        $('#vendorRMA').prop('checked',true);
        $('#poPayable').prop('checked',true);
    }
    else{
        $('#banking').prop('checked',false);
        $('#accountReceivable').prop('checked',false);
        $('#reconciliation').prop('checked',false);
        $('#categoryDetails').prop('checked',false);
        $('#categoryManager').prop('checked',false);
        $('#accountPayableModule').prop('checked',false);
        $('#payableList').prop('checked',false);
        $('#paidList').prop('checked',false);
        $('#billPayable').prop('checked',false);
        $('#vendorRMA').prop('checked',false);
        $('#poPayable').prop('checked',false);
    }
}

function checkAllAccountPayable(){
    var isChecked = $("#accountPayableModule").prop("checked");
    if(isChecked == true){
        $('#payableList').prop('checked',true);
        $('#paidList').prop('checked',true);
        $('#billPayable').prop('checked',true);
        $('#vendorRMA').prop('checked',true);
        $('#poPayable').prop('checked',true);
    }
    else{
        $('#payableList').prop('checked',false);
        $('#paidList').prop('checked',false);
        $('#billPayable').prop('checked',false);
        $('#vendorRMA').prop('checked',false);
        $('#poPayable').prop('checked',false);
    }
}

function closeWindow(){
    window.close();
}

function setHelp(){
    alert("<bean:message key='BzComposer.common.notSupportedYet'/>");
}
</script>
<body>
<form:form method="post" modelAttribute="configDto">
<div id="ddcolortabsline">&nbsp;</div>
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
	<div>
	<div id="table-negotiations">
	<table class="table-notifications">
		<form:select path="selectedGroupId" id="selectedGroup" multiple="multiple" style="display:none;">
			<c:if test="${not empty configDto.listOfExistingGroup}">
				<c:forEach items="${configDto.listOfExistingGroup}" var="objList1">
					<option value="${objList1.groupName}">${objList1.groupName}</option>
				</c:forEach>
			</c:if>
		</form:select>
		<tr>
			<th colspan="4" style="font-size: 14px;padding: 5px;">
				<b><spring:message code="BizComposer.Configuration.Networking.moduleAccessForGroup"/></b>
			</th>
		</tr>
		<tr>
			<td style="padding-top: 10px;"><spring:message code="Bizcomposer.groupName"/></td>
			<td style="padding-top: 10px;">
                <c:choose>
                  <c:when test="${configDto.selectedGroupId > 0}">
                    <form:input type="text" path="groupName" disabled="true" />
                  </c:when>
                  <c:otherwise>
                    <form:input type="text" path="groupName" />
                  </c:otherwise>
                </c:choose>
			</td>
		</tr>
		<tr>
			<td><spring:message code="BzComposer.Item.Description"/></td>
			<td><form:input type="text" path="description" /></td>
		</tr>
		<tr>
			<th colspan="4" align="left" style="font-size: 14px;padding: 5px;">
				<b><spring:message code="BizComposer.Configuration.Networking.accessPermissions"/></b>
			</th>
		</tr>
		<tr>
			 <td>
				<input type="checkbox" id="allModules" class="allModules" name="allModules" onchange="checkAll()" />
				<b>All Modules</b>
			</td>
		</tr>
		<tr>
		    <td style="padding-top: 10px;">
				&nbsp;&nbsp;<input type="checkbox" class="allModules" id="invoiceModule" name="invoiceModule" onChange="checkInvocieModule()" />
				<b>Invoice Module</b>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" class="allModules" id="invoiceBoard" name="invoiceBoard" />
				Invoice Board
			</td>
			<td>
				<input type="checkbox" class="allModules" id="estimationBoard" name="estimationBoard" />
				Estimation Board
			</td>
			<td>
				<input type="checkbox" class="allModules" id="invoice" name="invoice" />
				Invoice
			</td>
			<td>
				<input type="checkbox" class="allModules" id="estimation" name="estimation" />
				Estimation
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" id="items" class="allModules" name="items" />
				Items
			</td>
			<td>
				<input type="checkbox" class="allModules" id="dataManager" name="dataManager" />
				Data Manager
			</td>
			<td>
				<input type="checkbox" class="allModules" id="salesOrder" name="salesOrder" />
				Sales Order
			</td>
			<td>
				<input type="checkbox" class="allModules" id="salesOrderBoard" name="salesOrderBoard" />
				Sales Order Board
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px;">
				&nbsp;&nbsp;<input type="checkbox" id="purchaseModule" class="allModules" name="purchaseModule" onchange="ckeckPurchaseModule()" />
				<b>Purchase Module</b>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" class="allModules" id="poBoard" name="poBoard" />
				P.O.Board
			</td>
			<td>
				<input type="checkbox" id="purchaseOrder" class="allModules" name="purchaseOrder" />
				Purchase Order
			</td>
			<td>
				<input type="checkbox" id="checkPOOrders" class="allModules" name="checkPOOrders" />
				Check PO Orders
			</td>
			<td>
				<input type="checkbox" id="receivedItems" class="allModules" name="receivedItems" />
				Received Items
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" id="poBillBoard" class="allModules" name="poBillBoard" />
				P.O.Bill Board
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px;">
				&nbsp;&nbsp;<input type="checkbox" id="employeeModule" class="allModules" name="employeeModule" onchange="checkEmployeeModule()" />
				<b>Employee Module</b>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" id="employee" class="allModules" name="employee" />
				Employee
			</td>
			<td>
				<input type="checkbox" id="payRoll" class="allModules" name="payRoll" />
				Payroll
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px;">
				&nbsp;&nbsp;<input type="checkbox" id="accountModule" class="allModules" name="accountModule" onchange="checkAccountModule()" />
				<b>Account Module</b>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" id="banking" class="allModules" name="banking" />
				Banking
			</td>
			<td>
				<input type="checkbox" id="accountReceivable" class="allModules" name="accountReceivable" />
				Account Receivable
			</td>
			<td>
				<input type="checkbox" id="reconciliation" class="allModules" name="reconciliation" />
				Reconciliation
			</td>
			<td>
				<input type="checkbox" id="categoryDetails" class="allModules" name="categoryDetails" />
				Category Details
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" id="categoryManager" class="allModules" name="categoryManager" />
				Category Manager
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" id="accountPayableModule" class="allModules" name="accountPayable" onchange="checkAllAccountPayable()" />
				<b>Account Payable</b>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" id="payableList" class="allModules" name="payableList" />
				Payable List
			</td>
			<td>
				<input type="checkbox" id="paidList" class="allModules" name="paidList" />
				Paid List
			</td>
			<td>
				<input type="checkbox" id="billPayable" class="allModules" name="billPayable" />
				Bill Payable
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" id="vendorRMA" class="allModules" name="vendorRMA" />
				Vendor RMA
			</td>
			<td>
				<input type="checkbox" id="poPayable" class="allModules" name="poPayable" />
				P.O. Payable
			</td>
		</tr>
		<tr><td style="padding-top: 10px;"></td></tr>
		<tr>
			<td>
				&nbsp;<input type="checkbox" id="customerRMA" class="allModules" name="customerRMA" />
				<b>Customer RMA</b>
			</td>
			<td>
				<input type="checkbox" id="billingModule" class="allModules" name="billingModule" />
				<b>Billing Module</b>
			</td>
			<td>
				<input type="checkbox" id="eSalesModule" class="allModules" name="eSalesModule" />
				<b>eSales Module</b>
			</td>
			<td>
				<input type="checkbox" id="listModule" class="allModules" name="listModule" />
				<b>List Module</b>
			</td>
		</tr>
		<tr><td style="padding-top: 10px;"></td></tr>
		<tr>
			<td>
				&nbsp;<input type="checkbox" id="reportsModule" class="allModules" name="reportsModule" />
				<b>Reports Module</b>
			</td>
			<td>
				<input type="checkbox" id="customerModule" class="allModules" name="customerModule" />
				<b>Customer Module</b>
			</td>
			<td>
				<input type="checkbox" id="vendorModule" class="allModules" name="vendorModule" />
				<b>Vendor Module</b>
			</td>
			<td>
				<input type="checkbox" id="configurationModule" class="allModules" name="configurationModule" />
				<b>Configuration Module</b>
			</td>
		</tr>
		<!-- <c:if test="${not empty configDto.listOfExistingModule}">
		<c:forEach items="${configDto.listOfExistingModule}" var="objList1" varStatus="loop">
		    <tr><td><input type="checkbox" path="${objList1.moduleName}" /> ${objList1.moduleName}</td></tr>
		</c:forEach>
		</c:if> -->
		<tr>
			<td colspan="4" align="right" style="padding-top:10px;font-size:14px;">
				<button type="button" id="addNewGroupBtn" class="formButton" onclick="addNewGroup()">
				    <spring:message code="BzComposer.global.save"/>
				</button>&nbsp;&nbsp;
				<button type="button" class="formButton" onclick="closeWindow()">
				    <spring:message code="BzComposer.global.cancel"/>
				</button>&nbsp;&nbsp;
				<button type="button" class="formButton" onclick="setHelp()">
				    <spring:message code="BzComposer.Help"/>
				</button>
			</td>
		</tr>
	</table>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
<form:hidden path="selectedGroupId" />
<form:hidden path="groupPermissions" />
<input type="hidden" id="isViewGroupPermissions" value="${isViewGroupPermissions}" />
<input type="hidden" name="tabid" id="tabid" value="saveGroup" />
</form:form>
</body>
</html>
