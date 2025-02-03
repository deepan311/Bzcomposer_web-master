<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page errorPage="/include/sessionExpired.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/WEB-INF/jsp/include/headlogo.jsp" />
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<jsp:include page="/WEB-INF/jsp/include/menu.jsp" />
<title><spring:message code="BzComposer.vendorpurchaseordertitle" /></title>

<script type="text/javascript">
function toggleFunction() {
	debugger;
  var x = document.getElementById("divtoggle");
  var lftmenu = document.getElementById("leftMenu");
  if (x.style.display === "none") {
    x.style.display = "block";
    lftmenu.style.width = "180px";
    lftmenu.style.position = "relative";
  } else {
    x.style.display = "none";
    lftmenu.style.width = "0";
    lftmenu.style.position = "absolute";
  }
} 
$(function() {
    $( "#tabs" ).tabs();
    $( "#tabs1" ).tabs();
  });
$(document).ready(function()
{
	debugger
	 //request.setAttribute("sortId", sortId);
	var countryId = '<%= request.getAttribute("countryID")%>';
	var stateId = '<%=request.getAttribute("stateID")%>';
	var viaId = '<%= request.getAttribute("viaID")%>';
	var CategoryID = '<%= request.getAttribute("CategoryID")%>';
	
	var termId = '<%= request.getAttribute("termID")%>';
	var repId = '<%= request.getAttribute("repID")%>';
	var paymentMethodId = '<%= request.getAttribute("payMethodID")%>';
	var inchargeEmpId = '<%= request.getAttribute("inchargeEmpID")%>';
	
	debugger
	
	$('select[id="selectedCategoryId"]').find('option[value="'+CategoryID+'"]').attr("selected",true);
	$('select[id="shipCarrierId"]').find('option[value="'+viaId+'"]').attr("selected",true);
	$('select[id="selectedTermId"]').find('option[value="'+termId+'"]').attr("selected",true);
	
	$('select[id="selectedSalesRepId"]').find('option[value="'+repId+'"]').attr("selected",true);
	$('select[id="selectedPaymentId"]').find('option[value="'+paymentMethodId+'"]').attr("selected",true);

	$('#poShowCountry').change(function()
	{
		var isChecked = "<%= request.getAttribute("showCountry")%>";
		//var isChecked = "on";
		debugger
		if($(this).prop("checked") == true)
		{

	        $("#poShowCountry").attr('checked', true);
	        debugger
	        isChecked = "on"; 
		}
	    else if($(this).prop("checked") == false)
	    {

	        $("#poShowCountry").attr('checked', false);
	        debugger
	        isChecked = "off";
		}	
	    else
	    {

	        $("#poShowCountry").attr('checked', isChecked);
	        debugger
	    	document.configurationForm.poShowCountry.value = isChecked;
	    	debugger
	    }	
		$("#poShowCountry").val(isChecked);
	});
		
	$('#poShowTelephone').change(function()
	{
		var isChecked = "<%= request.getAttribute("showTelephone")%>";
		//var isChecked = "on";
		debugger
		if($(this).prop("checked") == true)
		{

	        $("#poShowTelephone").attr('checked', true);
	        debugger
	        isChecked = "on"; 
		}
	    else if($(this).prop("checked") == false)
	    {

	        $("#poShowTelephone").attr('checked', false);
	        debugger
	        isChecked = "off";
		}	
	    else
	    {

	        $("#poShowTelephone").attr('checked', isChecked);
	        debugger
	    	document.configurationForm.poShowTelephone.value = isChecked;
	    	debugger
	    }	
		$("#poShowTelephone").val(isChecked);
	});
	
	$('#isPurchasePrefix').change(function()
	{
		var isChecked = "<%= request.getAttribute("purchasePrefix")%>";
		//var isChecked = "on";
		debugger
		if($(this).prop("checked") == true)
		{

	        $("#isPurchasePrefix").attr('checked', true);
	        debugger
	        isChecked = "on"; 
		}
	    else if($(this).prop("checked") == false)
	    {

	        $("#isPurchasePrefix").attr('checked', false);
	        debugger
	        isChecked = "off";
		}	
	    else
	    {

	        $("#isPurchasePrefix").attr('checked', isChecked);
	        debugger
	    	document.configurationForm.isPurchasePrefix.value = isChecked;
	    	debugger
	    }	
		$("#isPurchasePrefix").val(isChecked);
	});
});

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


function disable() 
{
	var value = document.configurationForm.selectedCountryId.value;
	if(value == "2")
	{
		document.configurationForm.selectedStateId.disabled=false;
	}
	else
	{
		document.configurationForm.selectedStateId.disabled=true;
	}
}

function disable1() 
{
	var value = document.configurationForm.selectedCountryId1.value;
	if(value == "2")
	{
		document.configurationForm.selectedStateId1.disabled=false;
	}
	else
	{
		document.configurationForm.selectedStateId1.disabled=true;
	}
}

function clearDescription(){
	document.getElementById("description").value = "";
}

let custTypeID = 0;
function setCustomerType(ctID){
    custTypeID = ctID;
    let mySelectBox = document.getElementById('customerType');
    document.getElementById('custTypeName').value = mySelectBox.options[mySelectBox.selectedIndex].text;
}

function SaveCustomerType(type){
    if(type == 'Add')   custTypeID = 0;
    let custTypeName = document.getElementById('custTypeName').value;
    if(type == 'Edit' && custTypeID == 0){
        alert("<spring:message code='BzComposer.common.selectCustomerType'/>");
        return false;
    }
    else if(custTypeName.trim() == ''){
        alert("<spring:message code='BzComposer.common.enterCustomerType'/>");
        return false;
    }else{
        event.preventDefault();
        $("#saveCustomerTypeDialog").dialog({
            resizable: false,
            height: 200,
            width: 500,
            modal: true,
            buttons: {
                "<spring:message code='BzComposer.global.ok'/>": function () {
                    window.location = "/Configuration?tabid=SaveCustomerType&ID="+custTypeID+"&custTypeName="+custTypeName;
                },
                "<spring:message code='BzComposer.global.cancel'/>": function () {
                    $(this).dialog("close");
                    return false;
                }
            }
        });
    }
}

function DeleteCustomerType(){
    if(custTypeID == 0){
        alert("<spring:message code='BzComposer.common.selectCustomerType'/>");
        return false;
    }else{
        event.preventDefault();
        $("#deleteCustomerTypeDialog").dialog({
            resizable: false,
            height: 200,
            width: 500,
            modal: true,
            buttons: {
                "<spring:message code='BzComposer.global.ok'/>": function () {
                    window.location = "/Configuration?tabid=DeleteCustomerType&ID="+custTypeID;
                },
                "<spring:message code='BzComposer.global.cancel'/>": function () {
                    $(this).dialog("close");
                    return false;
                }
            }
        });
    }
}

</script>
</head>
<!-- <body onload="init1();"> -->
<body onload="init();">
<!-- begin shared/header -->
<form:form name="configurationForm" id="frmVendorPurchase"  enctype="MULTIPART/FORM-DATA" method="post" modelAttribute="configDto">
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">

	<div>
		<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.configuration.configurationtitle"/>
		</span>
	</div>
	<div>
		<div>
			<c:if test="${not empty Labels}">
                <input type="hidden" name="lsize" id="lblsize" value='${Labels.size()}' />
                <c:forEach items="${Labels}" var="lbl" varStatus="loop">
                    <input type="hidden" id='${loop.index}lid' name='${loop.index}lidname' value='${lbl.value}' />
                    <input type="hidden" id='${loop.index}lname' name='${loop.index}lnm' value='${lbl.label}' />
                </c:forEach>
			</c:if>
		</div>
		<div id="table-negotiations" style="padding: 0; border: 1px solid #ccc;">
		<span style="font-size:30px;cursor:pointer; margin-left: 20px;" onclick="toggleFunction()">&#9776;</span>
			<table cellspacing="0"  style="border: 0;width: 100%;overflow-y:scroll;" class="section-border">
				<tr>
					<td id="leftMenu" style="position: relative; width: 180px;">
						<table>
							<tr>
								<td>
									<jsp:include page="menuPage.jsp" />
								</td>
							</tr>
						</table>
					</td>
					<td valign="top" >
					    <div id="tabs" style="height:auto;">
                            <ul>
                                <li style="font-size: 12px;"><a href="#inventoryTab"><spring:message code="BizComposer.Configuration.Inventory" /></a></li>
                                <li style="font-size: 12px;"><a href="#vendorTab"><spring:message code="BzComposer.configuration.vendor" /></a></li>
                                <li style="font-size: 12px;"><a href="#purchaseOrderTab"><spring:message code="BzComposer.purchase.PurchaseOrder" /></a></li>
                            </ul>
                            <!-- inventoryTab Starts -->
                            <div id="inventoryTab" style="display:none;">
                                <table class="table-notifications" width="100%">
                                    <tr>
                                        <th colspan="4" style="font-size:12px; padding: 5px;"><spring:message code="BizComposer.Configuration.Inventory" /></th>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="font-size:12px;">
                                            <input type="checkbox" id="showReorderPointWarning" name="showReorderPointWarning" ${configDto.showReorderPointWarning=='on'?'checked':''} />
                                            <spring:message code="BzComposer.configuration.showoutofstockwarning"/>
                                        </td>
                                        <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="font-size: 12px;">
                                            <input type="checkbox" id="productTaxable" name="productTaxable" ${configDto.productTaxable=='on'?'checked':''} />
                                            <spring:message code="BzComposer.configuration.allproductsaretaxable"/>
                                        </td>
                                        <td colspan="2">&nbsp;</td>
                                    <tr>
                                        <td colspan="2" style="font-size:12px;">
                                            <input type="checkbox" id="reservedQuantity" name="reservedQuantity" ${configDto.reservedQuantity=='on'?'checked':''} />
                                            <spring:message code="BzComposer.configuration.quantityreservedforpendingbuilds"/>
                                        </td>
                                        <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="font-size:12px;">
                                            <input type="checkbox" id="salesOrderQty" name="salesOrderQty" ${configDto.salesOrderQty=='on'?'checked':''} />
                                            <spring:message code="BzComposer.configuration.quantityonsalesorder"/>
                                        </td>
                                        <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12px;width:25%;">
                                            <spring:message code="BzComposer.additem.productAccountCategory"/>
                                        </td>
                                        <td style="font-size:12px;width:25%;">
                                            <form:select path="productCategoryID" style="width:150px;">
                                                <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                <c:forEach items="${configDto.listOfExistingCategory}" var="objList1">
                                                    <form:option value="${objList1.selectedCategoryId}">${objList1.categoryName}&nbsp;${objList1.categoryNumber}</form:option>
                                                </c:forEach>
                                            </form:select>
                                        </td>
                                        <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12px;">
                                            <spring:message code="BzComposer.additem.reorderpoint"/>
                                        </td>
                                        <td style="font-size:12px;">
                                            <form:input type="text" path="reorderPoint" onkeydown="return numbersonly(event, this.value)" />
                                        </td>
                                        <td colspan="2">&nbsp;</td>
                                    </tr>
                                </table>
                            </div>

			<!-- ==================== vendor-Tab =================== -->
			<div id="vendorTab" style="display:none;">
			<table class="table-notifications" width="100%">
				<tr>
					<th colspan="4" align="left" style="font-size:12px; padding: 5px;"><spring:message code="BzComposer.configuration.vendor" />
					</th>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.vendorlistsortby"/> :
 					</td>
					<td style="font-size:12px;">
						<form:select path="sortBy" id="sortBy">
							<form:option value="1">
								<spring:message code="BzComposer.configuration.companyname"/>
							</form:option>
							<form:option value="2">
								<spring:message code="BzComposer.global.lastname"/>
							</form:option>
						</form:select>
					</td>
                    <td style="font-size:12px;">
                        <form:hidden path="selectedCountryId1" id="selectedCountryId1" />
                        <form:hidden path="selectedStateId1" id="selectedStateId1" />
                        <form:hidden path="vendorProvience" id="vendorProvience" />
                        <form:hidden path="selectedActiveEmployeeId" id="selectedActiveEmployeeId" />
                    </td>
                    <td>&nbsp;</td>
				</tr>
				<tr>
					<th colspan="4" align="left" style="font-size:12px; padding: 5px;">
						<spring:message code="BzComposer.configuration.vendorpreference" />
					</th>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.shipping"/> :
					</td>
					<td style="font-size:12px;">
						<form:select path="shipCarrierId" id="shipCarrierId">
							<option value="0"></option>
							<c:if test="${not empty configDto.listOfExistingShipCarrier}">
                            	<c:forEach items="${configDto.listOfExistingShipCarrier}" var="objList1">
                            		<option value="${objList1.shipCarrierId}">${objList1.shipCarrierName}</option>
                            	</c:forEach>
                            </c:if>
						</form:select>
					</td>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.term"/> :
					</td>
					<td style="font-size:12px;">
						<form:select path="selectedTermId" id="selectedTermId">
							<option value="0"></option>
							<c:if test="${not empty configDto.listOfExistingTerm}">
                                <c:forEach items="${configDto.listOfExistingTerm}" var="objList1">
                                    <option value="${objList1.selectedTermId}">${objList1.termName}</option>
                                </c:forEach>
                            </c:if>
						</form:select>
					</td>
				</tr>
				<tr>
					 <td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.rep"/> :
					</td>
					<td style="font-size:12px;">
						<form:select path="selectedSalesRepId" id="selectedSalesRepId">
							<option value="0"></option>
							<c:if test="${not empty configDto.listOfExistingSalesRep}">
                                <c:forEach items="${configDto.listOfExistingSalesRep}" var="objList1">
                                    <option value="${objList1.selectedSalesRepId}">${objList1.salesRepName}</option>
                                </c:forEach>
                            </c:if>
						</form:select>
					</td>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.paymentmethod"/> :
					</td>
					<td style="font-size:12px;">
						<form:select path="selectedPaymentId" id="selectedPaymentId">
						    <c:if test="${not empty configDto.listOfExistingPayment}">
                                <c:forEach items="${configDto.listOfExistingPayment}" var="objList1">
                                    <option value="${objList1.paymentId}">${objList1.paymentName}</option>
                                </c:forEach>
                            </c:if>
						</form:select>
					</td>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.defaultcategoryforaccpayable"/> :
					</td>
					<td style="font-size:12px;">
						<form:select path="selectedCategoryId" id="selectedCategoryId">
							<c:if test="${not empty configDto.listOfExistingCategory}">
                                <c:forEach items="${configDto.listOfExistingCategory}" var="objList1">
                                    <option value="${objList1.selectedCategoryId}">${objList1.categoryName}&nbsp;${objList1.categoryNumber}</option>
                                </c:forEach>
                            </c:if>
						</form:select>
					</td>
					<td style="font-size:12px;">
                        <spring:message code="BzComposer.Companyinformation.Type"/> :
                    </td>
                    <td style="font-size:12px;">
                        <form:select path="vendorBusinessTypeID">
                            <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                            <form:options items="${businessTypes}" itemValue="value" itemLabel="label"/>
                        </form:select>
                    </td>
				</tr>
				<tr>
                    <td style="font-size:12px;">
                        <spring:message code="BzComposer.orderimport.requisitioner"/> :
                    </td>
                    <td style="font-size:12px;">
                        <select path="selectedCategoryId" id="selectedCategoryId">
                            <option value="1">Value-1</option>
                            <option value="2">Value-2</option>
                            <option value="3">Value-3</option>
                        </select>
                    </td>
                    <td colspan="2">&nbsp;</td>
                </tr>
				<tr>
                    <th colspan="4" align="left" style="font-size:12px; padding: 5px;">
                        <spring:message code="BzComposer.PriceLevel" />
                    </th>
                </tr>
                <tr>
                    <td style="font-size:12px;">
                        <spring:message code="BzComposer.customer.CustomerType"/> :
                    </td>
                    <td style="font-size:12px;">
                        <form:select path="customerType" onchange="setCustomerType(this.value);">
                            <form:options items="${customerTypeList}" itemValue="value" itemLabel="label"/>
                        </form:select>
                    </td>
                    <td colspan="2">
                        <div style="float:left;"><input id="custTypeName" /></div>
                        <div style="float:right;">
                            <button type="button" class="formButton" onclick="SaveCustomerType('Add');"><spring:message code="BzComposer.global.add"/></button>
                            <button type="button" class="formButton" onclick="SaveCustomerType('Edit');"><spring:message code="BzComposer.global.edit"/></button>
                            <button type="button" class="formButton" onclick="DeleteCustomerType();"><spring:message code="BzComposer.global.delete"/></button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td style="font-size:12px;">
                        <spring:message code="BzComposer.common.priority"/> : &nbsp;&nbsp;
                        <form:input path="priceLevelPriority" onkeydown="return numbersonly(event, this.value)" style="width:100px;" />%
                    </td>
                    <td style="font-size:12px;">
                        <spring:message code="BzComposer.common.dealer"/> : &nbsp;&nbsp;
                        <form:input path="priceLevelDealer" onkeydown="return numbersonly(event, this.value)" style="width:100px;" />%
                    </td>
                    <td style="font-size:12px;">
                        <spring:message code="BzComposer.customer.Customer"/> : &nbsp;&nbsp;
                        <form:input path="priceLevelCustomer" onkeydown="return numbersonly(event, this.value)" style="width:100px;" />%
                    </td>
                    <td style="font-size:12px;">
                        <spring:message code="BzComposer.common.general"/> : &nbsp;&nbsp;
                        <form:input path="priceLevelGeneral" onkeydown="return numbersonly(event, this.value)" style="width:100px;" />%
                    </td>
                </tr>
			</table>
			</div>

			<!-- ==================== Purchase-Order =================== -->
            <div id="purchaseOrderTab" style="display:none;">
            <table class="table-notifications" width="100%">
                <tr>
                    <th colspan="4" align="left" style="font-size:12px; padding: 5px;"><spring:message code="BzComposer.configuration.purchaseorderpreference" /></th>
                </tr>
                <tr>
                    <td style="font-size:12px;">
                        <spring:message code="BzComposer.configuration.startponumber" /> :
                    </td>
                    <td style="font-size:12px;">
                        <form:input path="startPONum" id="startPONum" maxlength="15" style="width:150px;" class="startPONum" />
                    </td>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td style="font-size:12px;"><spring:message code="BzComposer.configuration.postyle" /> :</td>
                    <td style="font-size:12px;">
                        <form:select path="vendorInvoiceStyleId">
                            <c:forEach items="${configDto.listOfExistingInvoiceStyle}" var="objList1">
                                <form:option value="${objList1.selectedInvoiceStyleId}">${objList1.invoiceStyle}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td style="font-size:12px;"><spring:message code="BzComposer.configuration.defaultfootnote" /> :</td>
                    <td id="vdfoot" style="font-size:12px;">
                        <form:select path="vendorDefaultFootnoteID" style="width:70%;">
                            <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                            <c:forEach items="${configDto.messages}" var="curObject" varStatus="loop">
                                <form:option value='${curObject.selectedMessageId}'>${curObject.messageName}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td style="font-size:12px;">
                        <input type="checkbox" name="poShowCountry" id="poShowCountry" value="${configDto.poShowCountry}" ${configDto.poShowCountry=='on'?'checked':''} />
                        <spring:message code="BzComposer.configuration.showcountorynameonpurchaseorder"/>
                    </td>
                    <td style="font-size:12px;">
                        <input type="checkbox" name="poShowTelephone" id="poShowTelephone" value="${configDto.poShowTelephone}" ${configDto.poShowTelephone=='on'?'checked':''} />
                        <spring:message code="BzComposer.configuration.showphonefaxnumberonpurchaseorder"/>
                    </td>
                    <td style="font-size:12px;">
                        <input type="checkbox" name="isPurchasePrefix" id="isPurchasePrefix" value="${configDto.isPurchasePrefix}" ${configDto.isPurchasePrefix=='on'?'checked':''} />
                        <spring:message code="BzComposer.configuration.useprefixpo"/>
                    </td>
                </tr>
            </table>
            </div>

			</div>
			</td>
		</tr>
	</table>
	<div>
		<form:hidden path="empStateID" />
		<form:hidden path="labelName" />
		<form:hidden path="fileName" />
	</div>
	<div>
		<input type="hidden" name="tabid" id="tabid" value="" />
		<input type="hidden" name="showCountry" id="showCountry" value="" />
		<input type="hidden" name="showTelephone" id="showTelephone" value="" />
		<input type="hidden" name="isPrefix" id="isPrefix" value="" />
	</div>
	</div>
	<div>
		<div align="center">
			<input type="submit" name="Submit" class="bottomButton formButton" value="<spring:message code='BzComposer.global.save'/>"/>
			<input type="reset" name="Cancel" class="bottomButton formButton" onclick="RevokeValues()" value="<spring:message code='BzComposer.global.cancel'/>"/>
		</div>	
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</form:form>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
</body>
<script type="text/javascript">

$("#frmVendorPurchase").submit(function(event) {
    debugger;
    //Vendor Section
    var sortBy =document.getElementById('sortBy').value;
    var selectedStateId1 = document.getElementById('selectedStateId1').value;
    var selectedCountryId1 =document.getElementById('selectedCountryId1').value;
    var vendorProvience = document.getElementById('vendorProvience').value;
    //Purchase Order Preference Section
    var startPONum =document.getElementById('startPONum').value;
    var vendorDefaultFootnoteID = document.getElementById('vendorDefaultFootnoteID').value;
   	//Check Box values
    var showTelephone =document.getElementById('poShowTelephone').value;
    var isPrefix =document.getElementById('isPurchasePrefix').value;
	var showCountry = document.getElementById('poShowCountry').value ;
    //Vendor Preference Section
    var shipCarrierId =document.getElementById('shipCarrierId').value;
    var selectedTermId =document.getElementById('selectedTermId').value;
    var selectedSalesRepId =document.getElementById('selectedSalesRepId').value;
    var selectedPaymentId =document.getElementById('selectedPaymentId').value;
    var selectedCategoryId =document.getElementById('selectedCategoryId').value;
    //Employee In Charge Of Item Received Itemse Section
     var selectedActiveEmployeeId =document.getElementById('selectedActiveEmployeeId').value;
     var showReorderPointWarning = $('#showReorderPointWarning').prop("checked")?'on':'off';
     var reservedQuantity = $("#reservedQuantity").prop("checked")?'on':'off';
     var salesOrderQty = $("#salesOrderQty").prop("checked")?'on':'off';
     var productTaxable = $("#productTaxable").prop("checked")?'on':'off';
     var productCategoryID = document.getElementById('productCategoryID').value;
     var reorderPoint = document.getElementById('reorderPoint').value;
     var vendorBusinessTypeID = document.getElementById('vendorBusinessTypeID').value;
     var vendorInvoiceStyleId = document.getElementById('vendorInvoiceStyleId').value;
     var customerType = document.getElementById('customerType').value;
     var priceLevelPriority = document.getElementById('priceLevelPriority').value;
     var priceLevelDealer = document.getElementById('priceLevelDealer').value;
     var priceLevelCustomer = document.getElementById('priceLevelCustomer').value;
     var priceLevelGeneral = document.getElementById('priceLevelGeneral').value;
    debugger;
	event.preventDefault();
	$("#showsaverecorddialog").dialog({
	    	resizable: false,
	        height: 200,
	        width: 500,
	        modal: true,
	        buttons: {
	        	"<spring:message code='BzComposer.global.ok'/>": function () {
	            	debugger;
					//$('form').submit();
					var formData = $('frmVendorPurchase').serialize();
	        		$.ajax({
	        			type:"POST",
	        			url:"ConfigurationAjax/SaveConfiguration?tabid=saveVendorPurchaseValues&sortBy="+sortBy+"&selectedStateId1="+selectedStateId1+
	        					"&selectedCountryId1="+selectedCountryId1+"&vendorProvience="+vendorProvience+"&startPONum="+startPONum+
	        					"&vendorDefaultFootnoteID="+vendorDefaultFootnoteID+"&showTelephone="+showTelephone+"&isPrefix="+isPrefix+
	        					"&showCountry="+showCountry+"&shipCarrierId="+shipCarrierId+"&selectedTermId="+selectedTermId+
	        					"&selectedSalesRepId="+selectedSalesRepId+"&selectedPaymentId="+selectedPaymentId+"&selectedActiveEmployeeId="+selectedActiveEmployeeId+
	        					"&selectedCategoryId="+selectedCategoryId+"&showReorderPointWarning="+showReorderPointWarning+"&reservedQuantity="+reservedQuantity+
	        					"&salesOrderQty="+salesOrderQty+"&productTaxable="+productTaxable+"&productCategoryID="+productCategoryID+"&reorderPoint="+reorderPoint+
	        					"&vendorBusinessTypeID="+vendorBusinessTypeID+"&vendorInvoiceStyleId="+vendorInvoiceStyleId+"&customerType="+customerType+
	        					"&priceLevelPriority="+priceLevelPriority+"&priceLevelDealer="+priceLevelDealer+"&priceLevelCustomer="+priceLevelCustomer+"&priceLevelGeneral="+priceLevelGeneral,
	        			data:formData,
	        			success:function(data) {
	        				debugger
	        				$("#showsaverecorddialog").dialog("close");
	        				$("#showsuccessdialog").dialog({
                                resizable: false,
                                height: 200,
                                width: 500,
                                modal: true,
                                buttons: {
                                    "<spring:message code='BzComposer.global.ok'/>": function () {
                                        $(this).dialog("close");
                                        return false;
                                    },
                                    "<spring:message code='BzComposer.global.cancel'/>": function () {
                                        $(this).dialog("close");
                                        return false;
                                    }
                                }
	        				});
	        			},
	        			error:function(data) {
	        			    $("#showsaverecorddialog").dialog("close");
	        				alert("<spring:message code='BzComposer.common.erroroccurred'/>");
	        			}
	        		});
	            },
	            "<spring:message code='BzComposer.global.cancel'/>": function () {
	                $(this).dialog("close");
	                /* stop form from submitting normally */
	                event.preventDefault();
	                return false;
	            }
	        }
	    });
	    return false;
    
  });
</script>
</html>
<!-- Dialog box used in this page -->
<div id="showsaverecorddialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.saveconfirm"/></p>
</div>
<div id="saveCustomerTypeDialog" style="display:none;">
	<p><spring:message code="BzComposer.common.saveCustomerTypeConfirm"/></p>
</div>
<div id="deleteCustomerTypeDialog" style="display:none;">
	<p><spring:message code="BizComposer.PurchaseOrder.Delete.Validation"/></p>
</div>
<!-- Dialog box used in this page -->
<div id="showsuccessdialog" style="display:none;">
    <p><spring:message code="BzComposer.common.recordUpdated"/></p>
</div>