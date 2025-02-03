<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.invoicetitle" /></title>
<style type="text/css">
/** Semibold */
@font-face {
  font-family: "SF Mono";
  font-weight: 600;
  src: url("https://sf.abarba.me/SFMono-Semibold.otf");
}

/** Semibold Italic */
@font-face {
  font-family: "SF Mono";
  font-weight: 600;
  font-style: italic;
  src: url("https://sf.abarba.me/SFMono-SemiboldItalic.otf");
}

body{
	/* font-family: "SF Mono"; */
	min-width: 1366px;
}
page-title{
	/* font-size: 18px;margin: 30px 0px 15px 0px;padding: 0 0 10px 0; */
}


.msgstyle{ font-size:18px; color: #3D9EAC; }
.showCustomer { font: bold italic 100px; }
table.cart tbody tr td { font-size: 14px; }
</style>
<script type="text/javascript">
$(function() {
	var locale = "<%= request.getAttribute("selectedLocale")%>";
	$('select[id="locale"]').find('option[value="'+locale+'"]').attr("selected",true);
	$("#sortByLastName").change(function(){
	var checked = $("#sortByLastName").prop('checked');
	if(checked == true){
		$.ajax({
			type: "POST",
			url:"Invoice?tabid=SortInvoice&SortBy=LastName",
			data:{sortBy : "LastName"},
			success : function(data) {
			    $(document).find('div#custDiv').replaceWith($(data).find('div#custDiv').html());
			},
			error : function(data) {
                alert("<bean:message key='BzComposer.categorydetail.erroroccurred'/>");
            }
		});
	} else {
		$.ajax({
        	type : "POST",
         	url:"Invoice?tabid=SortInvoice&SortBy=Name",
        	data:{sortBy : "Name"},
            success : function(data) {
        		$(document).find('div#custDiv').replaceWith($(data).find('div#custDiv').html());
        	},
        	 error : function(data) {
        		alert("<bean:message key='BzComposer.categorydetail.erroroccurred'/>");
        	}
        });

	}
});
});
function saveItemName() 
{
	event.preventDefault();
	$("#SaveItemName").dialog({
    	resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
                var itemName = $.trim(document.getElementById('pname_id').value);
            	var item = document.getElementById('itemID');
            	var itemId = item.options[item.selectedIndex].value;
            	window.location.href = "Invoice?tabid=saveItemName&itemName="+itemName+"&itemID="+itemId;
            },
            "<spring:message code='BzComposer.global.cancel'/>": function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}
function saveNewUnitPrice()
{
	event.preventDefault();
	$("#SaveUnitPrice").dialog({
    	resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
                var price = document.getElementById('unitPrice_id').value;
				var item = document.getElementById('itemID');
				var itemId = item.options[item.selectedIndex].value;
				window.location.href = "Invoice?tabid=saveUnitPrice&price="+price+"&itemID="+itemId;
            },
            <spring:message code='BzComposer.global.cancel'/>: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}

function updateQuantityOfSelectedItem()
{
	event.preventDefault();
	$("#saveQuantity").dialog({
    	resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
                var quantity = document.getElementById('qty_id').value;
				var item = document.getElementById('itemID');
				var itemId = item.options[item.selectedIndex].value;
				//window.location.href = "Invoice?tabid=saveQuantity&quantity="+price+"&itemID="+itemId;
            },
            <spring:message code='BzComposer.global.cancel'/>: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}

function showItemOrderNumberDialog(){
	event.preventDefault();
	$("#showItemOrderNumberDialog").dialog({
    	resizable: false,
        height: 200,
        width: 300,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}

function showValidationDialog(){
	event.preventDefault();
	$("#showValidationDialog").dialog({
    	resizable: false,
        height: 200,
        width: 300,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}

function showSelectItemDialog(){
	event.preventDefault();
	$("#showSelectItemDialog").dialog({
    	resizable: false,
        height: 200,
        width: 300,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}

function deleteInvoiceDialog(){
	event.preventDefault();
	$("#deleteInvoiceDialog").dialog({
    	resizable: false,
        height: 200,
        width: 300,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
                window.location="Invoice?tabid=DeleteInvoice&&CustomerID="+cid;
            },
            <spring:message code='BzComposer.global.cancel'/>: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}

function ShowBillingAddressPage(form){
    custID = form.custID.value;
    let addressID = form.bsAddressID.value;
    window.open("Invoice?tabid=getBillingAddress&addressType=bill&cvID="+custID+"&addressID="+addressID, null,"scrollbars=yes,height=600,width=600,status=yes,toolbar=no,menubar=no,location=no");
}

function ShowShippingAddressPage(form){
    custID = form.custID.value;
    let addressID = form.shAddressID.value;
    window.open("Invoice?tabid=getBillingAddress&addressType=ship&cvID="+custID+"&addressID="+addressID, null,"scrollbars=yes,height=600,width=600,status=yes,toolbar=no,menubar=no,location=no");
}
</script>
</head>
<body onload="Init();">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">   
<div class="statusquo ok">		
<div id="hoja">
<div id="blanquito">
<div id="padding">
<form:form name="InvoiceForm" id="frmInvoice" method="post" modelAttribute="invoiceDto">
	<input type="hidden" name="isInvoice" value="1">
	<input type="hidden" name="isSalestype" value="1">
	<form:errors />
	<div style="float: left; width: 100%;">
		<span class="page-title" style="font-size:1.2em;">
			<spring:message code="BzComposer.invoice.invoicetitle" />
		</span>
	</div>
	<!-- <div> <h3 class="page-title" style="color: #05A9C5;">Invoice</h3> </div> -->
	<div id="fullPageDetails" style="margin-left: auto;margin-right: auto;">
	<section>
		<div id="table-negotiations">
			<div id="CustomerDtailsInfo">
                <input type="hidden" name="CustDetails" id="custSize" value='${CustDetails.size()}'>
                <c:forEach items="${CustDetails}" var="objList" varStatus="loop">
                    <input type="hidden" name='${loop.index}clientID' value='${objList.clientVendorID}' id='a${loop.index}clvndid' />
                    <input type="hidden" name='${loop.index}v' value='${objList.via}' id='${loop.index}va' />
                    <input type="hidden" name='${loop.index}pa' value='${objList.payMethod}' id='${loop.index}paym' />
                    <input type="hidden" name='${loop.index}tm' value='${objList.term}' id='${loop.index}trm' />
                    <input type="hidden" name='${loop.index}rp' value='${objList.rep}' id='${loop.index}rp' />
                    <input type="hidden" name='${loop.index}tx' value='${objList.taxable}' id='a${loop.index}txable' />
                    <input type="hidden" name='${loop.index}fn' value='${objList.firstName}' id='${loop.index}firstName' />
                    <input type="hidden" name='${loop.index}ln' value='${objList.lastName}' id='${loop.index}lastName' />
                    <input type="hidden" id='${loop.index}custBalance' value='${objList.customerHasBalance}' />
                </c:forEach>
            </div>
					
				<div id="BillShipAddrDetails">
                    <input type="hidden" name="BLSize" id="bSize" value='${BillAddr.size()}'>
                    <c:forEach items="${BillAddr}" var="objList" varStatus="loop">
                        <input type="hidden" value='${objList.clientVendorID}' id='${loop.index}clvid' />
                        <input type="hidden" value='${objList.billTo}' id='${loop.index}bl' />
                        <input type="hidden" value='${CID}' id='${loop.index}cid' />
                        <input type="hidden" value='${objList.bsAddressID}' id='${loop.index}bsaddr' />
                    </c:forEach>

                    <input type="hidden" name="LSize" id="sSize" value='${ShAddr.size()}'>
                    <c:forEach items="${ShAddr}" var="objList" varStatus="loop">
                        <input type="hidden" value='${objList.clientVendorID}' id='${loop.index}sh_id' />
                        <input type="hidden" value='${objList.shipTo}' id='${loop.index}sh' />
                        <input type="hidden" value='${objList.shAddressID}' id='${loop.index}shaddr' />
                    </c:forEach>
				</div>
					
                <div id="ItemDetails">
                    <input type="hidden" name="ItemSize" id="itemSize" value='${ItemList.size()}'>
                    <c:forEach items="${ItemList}" var="objList" varStatus="loop">
                        <input type="hidden" value='${objList.invID}' id='${loop.index}inv' />
                        <input type="hidden" value='${objList.qty}' id='${loop.index}q' />
                        <input type="hidden" value='${objList.invCode}' id='${loop.index}code' />

                        <input type="hidden" value='${objList.invDesc}' id='${loop.index}desc' />
                        <input type="hidden" value='${objList.inventoryName}' id='${loop.index}pname' />
                        <input type="hidden" value='${objList.salePrice}' id='${loop.index}price' />
                        <input type="hidden" value='${objList.weight}' id='${loop.index}wt' />
                        <input type="hidden" value='${objList.isCategory}' id='${loop.index}cat' />
                        <input type="hidden" value='${objList.itemTypeID}' id='${loop.index}itmId' />
                        <input type="hidden" value='${objList.serialNo}' id='${loop.index}serial' />
                    </c:forEach>
                </div>
					
					<table class="tabla-listados" cellspacing="0" style="margin-top: -1px;">
						<thead>
							<tr>
								<th colspan="3" style="font-size: 14px;">
									<spring:message code="BzComposer.invoice.customerinfo" />
								</th>
							</tr>
						</thead>
						
						<c:if test="${not empty Status}">
							<tr>
								<td colspan="3" align="left">
									<span class="msgstyle">*${Status}</span>
								</td>
							</tr>
						</c:if>
						<c:if test="${not empty SaveStatus}">
							<tr>
								<td colspan="3">
									<span class="msgstyle">*${SaveStatus}</span>
									<% session.removeAttribute("SaveStatus"); %>
								</td>
							</tr>
						</c:if>
						<tr>
							<td style="width:30%;">
								<table>
									<tr>
										<td colspan="2" style="font-size: 14px;">
											<spring:message code="BzComposer.invoice.customertitle" />
										</td>
										<td colspan="4" style="font-size: 14px;" align="left">
											<!-- Sort By -->
											<spring:message code="BzComposer.invoice.sortby"/>
										</td>
										<td>&nbsp;</td>
										<td style="font-size: 14px;">
											<spring:message code="BzComposer.invoice.taxable" />
										</td>
										<td style="font-size: 14px;">
											<spring:message code="BzComposer.invoice.pending" />
										</td>
									</tr>
									<tr>
										<td colspan="2" style="font-size:14px;">
											<div id="custDiv">
												<div id="custDiv">
													<form:select id="custID" path="custID" onchange="Assignment(this.value, this.form);" style="width: 300px;">
														<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
														<form:option value="1111"><spring:message code="BzComposer.invoice.addcustomer"/></form:option>
														<form:options items="${CDetails}" itemValue="value" itemLabel="label" />
													</form:select>
												</div>
											</div>
										</td>
										<td style="font-size: 14px;">
											<input type="checkbox" id="sortByLastName" name="sortByLastName" value="sortByLastName"/>
										</td>
										<td colspan="3" style="font-size:14px;">
											<spring:message code="BzComposer.invoice.sortbylastname"/>
										</td>
										<td>&nbsp;</td>
										<!-- <td align="center" style="font-size: 14px;">
											<input type="checkbox" id="sortByCompanyName" name="sortByCompanyName" value="sortByCompanyName"/>
										</td>
										<td colspan="3" style="font-size:14px;">
											Company Name
										</td> -->
										<td align="center" style="font-size: 14px;">
											<input type="checkbox" id="taxable" name="taxable" onclick="TaxaValue(this.form);" value="${invoiceDto.taxable}" ${invoiceDto.taxable?'checked':''} />
										</td>									
										<td align="center" style="font-size: 14px;">
											<input type="checkbox" id="isPending" name="isPending" onclick="Pending_Value(this.form);" value="${invoiceDto.isPending}" ${invoiceDto.isPending?'checked':''} />
										</td>
									</tr>
								</table>
							</td>
							<td align="center" style="font-size: 14px;width:35%;">
								<input type="button" class="formbutton" onclick="getInvoiceDetailsByBtnName(this.form, 'FirstInvoice');" style="padding: 8px 10px 8px 10px; font-size: 16px;" value="<spring:message code='BzComposer.invoice.first' />" />
								<input type="button" class="formbutton" onclick="getInvoiceDetailsByBtnName(this.form, 'LastInvoice');" style="padding: 8px 10px 8px 10px; font-size: 16px;" value="<spring:message code='BzComposer.invoice.last' />" />
								<input type="button" class="formbutton" onclick="getInvoiceDetailsByBtnName(this.form, 'PreviousInvoice');" style="padding: 8px 10px 8px 10px; font-size: 16px;" value="<spring:message code='BzComposer.Invoice.Previous' />" />
								<input type="button" class="formbutton" onclick="getInvoiceDetailsByBtnName(this.form, 'NextInvoice');" style="padding: 8px 10px 8px 10px; font-size: 16px;" value="<spring:message code='BzComposer.Invoice.Next' />" />
								<input type="button" id="newInvoice" class="formbutton" onclick="NewInvoice();" style="padding: 8px 10px 8px 10px; font-size: 16px;" value="<spring:message code='BzComposer.global.new' />" />
								<br>
								<input type="button" class="formbutton" id="CustomerBalanceBtn" onclick="paymentHistory(this.form);" style="padding: 8px 20px 8px 20px; font-size: 16px;" value="<spring:message code='BzComposer.Invoice.balance' />" />
                                <c:if test="${not empty Enable}">
                                    <input type="button" id="sendMailEnabled" class="formbutton" title="Send Mail to..." onclick="SendMail(this.form);" style="padding: 8px 20px 8px 20px; font-size: 16px;" value="<spring:message code='BzComposer.Invoice.SendMail' />" />
                                </c:if>
                                <c:if test="${empty Enable}">
                                    <input type="button" id="sendMailDisabled" class="formbutton" title="Send Mail to..." onclick="SendMail(this.form);" disabled="true" style="padding: 8px 20px 8px 20px; font-size: 16px;" value="<spring:message code='BzComposer.Invoice.SendMail' />" />
                                </c:if>
                                <input type="button" class="formbutton" onclick="printCustomerOrder(this.form);" style="padding: 8px 20px 8px 20px; font-size: 16px;" value="<spring:message code='BzComposer.global.print' />" />
							</td>
							<td align="right" style="font-size: 14px;width:35%;">
								<table>
									<tr>
										<td style="font-size:14px;">
											<spring:message code="BzComposer.common.template" />
										</td>
										<td style="font-size:14px;">
                                            <spring:message code="BzComposer.Orders.OrderStyle" />
                                            <input type="hidden" name="Ivhidden" id="InvStyle" />
                                        </td>
										<td align="left" style="font-size: 14px;" colspan="3">
											<spring:message code="BzComposer.Invoice.Date" />
										</td>
										<td align="left" style="font-size: 14px;">
											<spring:message code="BzComposer.Invoice.InvoiceNo" />
										</td>
									</tr>
									<tr>
										<td style="font-size: 14px;">
											<%-- <form:select path="templateType" >
												<form:option value="3">Product Standard</form:option>
												<form:option value="4">Product Charcoal</form:option>
												<form:option value="5">Professional Standard</form:option>
												<form:option value="6">Professional Charcoal</form:option>
											</form:select> --%>
										<c:set var="invoiceType" scope="page" value="${fn:toLowerCase(fn:substring(invoiceDto.formTemplateType.templateName,0,3))}"/> 
											<form:select path="templateType">
												 <c:forEach var="invItems" items="${invoiceDto.formTemplateType.mappingData}" varStatus="status">
												 
												 <c:set var="checkboxId" scope="page" value="${invItems.templateNo}"/>
												 
												 	<form:option value="${checkboxId}" selected="${invItems.isSelected==true?'selected':''}">${invItems.templateType}</form:option>
												 </c:forEach>
											 </form:select>   
											
										</td>
										<td style="font-size: 14px;">
                                            <form:select id="inoiceStyle" path="invoiceStyle" onchange="StyleChange(this.value);">
                                                <form:option value="0">
                                                    <spring:message code="BzComposer.ComboBox.Select" />
                                                </form:option>
                                                <form:options items="${InvoiceStyle}" itemValue="value" itemLabel="label" />
                                            </form:select>
                                        </td>
										<td style="font-size: 14px;" colspan="3">
											<form:input path="orderDate" readonly="true" size="10" />
												<!-- &nbsp; -->
												<img src="${pageContext.request.contextPath}/images/cal.gif"
												onclick="displayCalendar(document.InvoiceForm.orderDate,'mm-dd-yyyy',this);">
										</td>	
										<td align="right" style="font-size: 14px;">
											<form:input path="orderNo" style="text-align: right;" size="10" readonly="true" />
										</td>
									</tr>
								</table>
							</td>
							</tr>
							<tr>
								<!-- <script type="text/javascript"></script> -->				
								<td align="center" colspan="3">
									<div id="pending" style="display:none;">
										<font size="10">
											<spring:message code="BizComposer.Invoice.Pending" /> 
										</font>
									</div>
								</td>
							</tr>
							<tr align="left">
								<td align="center" colspan="2">
									<table style="width: 100%;">
										<tr>
											<td id="bill_label"></td>
										</tr>
									</table>
								</td>
								<td id="ship_label"></td>
							</tr>
							<tr>
								<td align="left">
									<table>
										<tr>
											<td id="bill_id" style="font-size: 14px;">
												<spring:message code="BzComposer.Invoice.BillTo" />
												<br/>
												<form:textarea path="billTo" rows="6" cols="25" onclick="ShowBillingAddressPage(this.form);" style="resize: none; width: 300px;" />
											</td>
											<td id="td2" align="center"></td>
										</tr>
									</table>
								</td>
								<td>&nbsp;</td>
								<td align="right">
									<table>
										<tr>
											<td id="ship_id" style="visibility: visible;padding-left:74px;font-size: 14px;">
												<spring:message code="BzComposer.Invoice.ShipTo" />
												<br/>	 
												<form:textarea path="shipTo" rows="6" cols="25" onclick="ShowShippingAddressPage(this.form);" style="resize: none; width: 370px;" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<!-- asasass -->
									<table align="left">
										<tr>
											<td id="sh_date_label" style="font-size: 14px;">
												<spring:message code="BzComposer.Invoice.ShipDateLower" />
											</td>
											<td id="rep_label" style="font-size: 14px;">
												<spring:message code="BzComposer.Invoice.Rep" />
											</td>	
											<td style="font-size: 14px;">
												<spring:message code="BzComposer.Invoice.Term" />
											</td>
											<td style="font-size: 14px;">
												<spring:message code="BzComposer.Invoice.PayMethod" />
											</td>
											<td id="via_label" style="font-size: 14px;">
												<spring:message code="BzComposer.Invoice.Via" />
											</td>
										</tr>
										<tr>
											<td id="sh_date_id" style="font-size: 14px;">
											    <form:hidden path="poNum" style="text-align: right;" />
												<form:input path="shipDate" readonly="true" size="15" />
												<img src="${pageContext.request.contextPath}/images/cal.gif" onclick="displayCalendar(document.InvoiceForm.shipDate,'mm-dd-yyyy',this);">
											</td>
											<td id="rep_id" style="font-size: 14px;">
												<form:select path="rep" style="width:150px;">
													<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
													<form:options items="${Rep}" itemValue="value" itemLabel="label" />
												<td style="font-size: 14px;">
													<form:select path="term" style="width:150px;">
														<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
														<form:options items="${Term}" itemValue="value" itemLabel="label" />
													</form:select>
												</td>
												<td style="font-size: 14px;" style="font-size: 14px;">
													<form:select path="payMethod" style="width:150px;">
														<c:if test="${not empty PayMethod}">
															<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
															<form:options items="${PayMethod}" itemValue="value" itemLabel="label" />
														</c:if>
													</form:select>
												</td>
												</form:select>
											</td>
											<td id="via_id" style="font-size: 14px;">
												<form:select path="via" style="width:220px;">
													<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
													<form:options items="${Via}" itemValue="value" itemLabel="label" />
												</form:select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
					</table>
					
					<div id="product">
						<table class="tabla-listados" cellspacing="0">
							<thead>
								<tr>
									<th colspan="12" style="font-size: 14px;">
										<spring:message code="BizComposer.Estimaion.Header.ItemInfo" />
									</th>
								</tr>
							</thead>				
							<tr>
								<td id="td1" style="font-size: 14px;">
									<spring:message code="BzComposer.Invoice.ItemID" />
								</td>
								<td style="font-size: 14px;">
                                    <div>
                                        <spring:message code="BzComposer.Invoice.ItemName" />
                                    </div>
                                </td>
								<td style="font-size: 14px;">
									<div id="td10" style="display:block;">
										<spring:message code="BzComposer.Invoice.UnitPrice" />
									</div>
									<div id="td11" style="display:none;">
										<spring:message code="BzComposer.Invoice.RatePrice" />
									</div>
								</td>
								<td style="font-size: 14px;">
                                    <div id="td3"><spring:message code="BzComposer.Invoice.Qty" /></div>
                                    <%-- <div id="td4"><spring:message code="BzComposer.Invoice.Rate" /></div> --%>
                                </td>
								<td style="font-size: 14px;">
									<div id="td6" style="display:none;">
										<spring:message code="BzComposer.Invoice.SerialNo" />
									</div>
								</td>
								<td style="font-size: 14px;">	
									<div id="td13" style="display:block;">
										<spring:message code="BzComposer.Invoice.Amt" />
									</div>
								</td>
								<td style="font-size: 14px;">
									<div id="td15" style="display:block;">
										<spring:message code="BzComposer.Invoice.Weight" />
									</div>
								</td>
								<td style="font-size: 14px;">	
									<div id="td17" style="display:block;">
										<spring:message code="BzComposer.Invoice.Tax" />
									</div>
								</td>
								<td colspan="4"></td>
							</tr>
							<tr>
								<td id="td2" style="font-size: 14px;">
									<div id="itemDiv">
									<div id="itemDiv">
									<form:select path="itemID" onchange="ItemChange(this.value);" id="itemID">
										<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
										<c:forEach items="${ItemList}" var="itmList">
											<c:if test="${itmList.isCategory == 0}">
												<form:option value='${itmList.invID}'>&nbsp;&nbsp;&nbsp;&nbsp;${itmList.invCode}</form:option>
											</c:if>
											<c:if test="${itmList.isCategory == 1}">
												<form:option value='01'>${itmList.invCode}</form:option>
											</c:if>
										</c:forEach>
									</form:select>
									</div>
									</div>
								</td>
								<td style="font-size: 14px;width:50%;">
                                    <div>
                                        <input type="text" id="pname_id" onchange="return saveItemName();" readonly="readonly" style="width:95%;"/>
                                    </div>
                                    <div id="SaveItemName" title="Update item name" style="display:none;">
                                        <p><spring:message code="BzComposer.invoice.saveItemName"/></p>
                                    </div>
                                </td>
								<td style="font-size: 14px;">	
									<div style="padding-top: 0px;" id="td12">
										<input type="text" size="10"  style="text-align: right;" readonly="readonly" id="unitPrice_id" onchange="return saveNewUnitPrice();" onkeypress="return numbersOnlyFloat(event,this.value);" />
									</div>
									<div id="SaveUnitPrice" title="Update unit price" style="display:none;">
				        				<p><spring:message code="BzComposer.invoice.saveItemUnitPrice"/></p>
				   					</div>	
								</td>
								<td style="font-size: 14px;">
                                    <div style="padding-top: 0px;" style="display:block;" id="td5">
                                        <input class="minutesInput" style="text-align: right;" min="1" type="text" size="10" id="qty_id" onchange="Multiplication();" onkeypress="return numbersonly(event,this.value);" />
                                    </div>
                                    <div id="saveQuantity" title="Update quantity" style="display:none;">
                                        <p><spring:message code="BzComposer.invoice.saveItemQuantity"/></p>
                                    </div>
                                </td>
								<td style="font-size: 14px;">
									<div id="td7" >
										<input type="hidden" size="10" id="serialNo_id" readonly="readonly" />
									</div>
								</td>
								<td style="font-size: 14px;">				
									<div id="td14" style="display:block;">
										<input type="text" size="10" style="text-align: right;" readonly="true" id="amount_id" onkeypress="return numbersOnlyFloat(event,this.value);" />
									</div>
								</td>
								<td style="font-size: 14px;">
									<div id="td16" style="display:block;">
										<input type="text"  name="itemWeight" style="text-align: right;" size="10" readonly="true" id="weight_id" onkeypress="return numbersOnlyFloat(event,this.value);" />
									</div>
								</td>				
								<td style="font-size: 14px;">
									<div id="td18" style="display:block;">
										<select id="tax_id">
											<option value="0">No</option>
											<option value="1">Yes</option>
										</select>
									</div>
								</td>
								<td style="padding-right:5px;font-size: 14px;">
									<div>
										<input type="button" class="formbutton" name="addItem" title="To add item click it" onclick="AddItem(this.form);"
										    value='<spring:message code="BzComposer.Invoice.AddItem"/>'  style="padding: 8px 20px 8px 20px; font-size: 16px; margin-top: 0;" />
									</div>
								</td>
								<td colspan="3"></td>
							</tr>
						</table>
						<table class="tabla-listados cart" cellspacing="0">
							<thead>
								<tr>
									<th style="font-size: 14px;width:10%;">
										<div id="it1">
											<spring:message code="BzComposer.Invoice.ItemID" />
										</div>
									</th>
									<th style="font-size: 14px;width:40%;">
                                        <div id="it3"><spring:message code="BzComposer.Invoice.ItemName" /></div>
                                    </th>
									<th style="font-size: 14px;">
										<div id="it8">
											<spring:message code="BzComposer.Invoice.SerialNo" />
										</div>
									</th>
									<th style="font-size: 14px;">
										<div id="it4"><spring:message code="BzComposer.Invoice.UnitPrice" /></div>
										<div id="it42" style="display:none;"><spring:message code="BzComposer.Invoice.RatePrice" /></div>
									</th>
									<th style="font-size: 14px;">
                                        <div id="it2"><spring:message code="BzComposer.Invoice.Qty" /></div>
                                        <%-- <div id="it22"><spring:message code="BzComposer.Invoice.Rate" /></div> --%>
                                    </th>
									<th style="font-size: 14px;">
										<div id="it5">
											<spring:message code="BzComposer.Invoice.Amt" />
										</div>
									</th>
									<th style="font-size: 14px;">
										<div id="it6">
											<spring:message code="BzComposer.Invoice.Weight" />
										</div>
									</th>
									<th style="font-size: 14px;">
										<div id="it7">
											<spring:message code="BzComposer.Invoice.Tax" />
										</div>
									</th>
									<th style="font-size: 14px;">
										<spring:message code="BzComposer.Invoice.DeleteOption" />
									</th>
									<th colspan="3">
									</th>
								</tr>
							</thead>
							<c:if test="${not empty Cart}">
                                <input type="hidden" name="hdncsize" id="CartSize" value='${Cart.size()}'>
                                <c:forEach items="${Cart}" var="cart" varStatus="loop">
                                    <tr id='${loop.index}row'>
                                        <td align="left" style="font-size: 14px;">
                                            <div id='${loop.index}icode'>${cart.invCode}</div>
                                        </td>
                                        <td style="font-size: 14px;">
                                            <div id='${loop.index}desc' style="font-size: 14px;">${cart.invDesc}</div>
                                        </td>
                                        <td align="left" style="font-size: 14px;">
                                            <div id='${loop.index}iserial' style="font-size: 14px;">${cart.serialNo}</div>
                                        </td>
                                        <td align="left" style="font-size: 14px;">
                                            <div id='${loop.index}iprice' style="font-size: 14px;">${cart.uprice}</div>
                                        </td>
                                        <td align="left" style="font-size: 14px;">
                                            <div id='${loop.index}qt'>${cart.qty}</div>
                                        </td>
                                        <td align="left" style="font-size: 14px;">
                                            <div id='${loop.index}amt' style="font-size: 14px;">${cart.amount}</div>
                                        </td>
                                        <td align="left" style="font-size: 14px;">
                                            <div id='${loop.index}wgt' style="font-size: 14px;">${cart.weight}</div>
                                        </td>
                                        <td align="left" style="font-size: 14px;">
                                            <div id='${loop.index}itax' style="font-size: 14px;">${cart.tax}</div>
                                        </td>
                                        <td align="left" style="font-size: 14px;" colspan="4">
                                            <img onclick="return DeleteRow('${loop.index}row',this.form);" src="${pageContext.request.contextPath}/images/delete.png"
                                                title="Delete this Item" size="8" width="12"/>
                                        </td>
                                    </tr>
                                        <input type="hidden" id='${loop.index}delt' value="0" style="font-size: 14px;"/>
                                        <input type="hidden" id='${loop.index}rowVal' value='${loop.index}row' />
                                        <input type="hidden" id='${loop.index}invCode' value='${cart.invCode}' />
                                        <input type="hidden" id='${loop.index}qty' value='${cart.qty}' />
                                        <input type="hidden" id='${loop.index}invDesc' value='${cart.invDesc}' />
                                        <input type="hidden" id='${loop.index}uprice' value='${cart.uprice}' />
                                        <input type="hidden" id='${loop.index}weight' value='${cart.weight}' />
                                        <input type="hidden" id='${loop.index}tax' value='${cart.tax}' />
                                        <input type="hidden" id='${loop.index}serial' value='${cart.serialNo}' />
                                        <input type="hidden" id='${loop.index}itId11' value='${cart.itemTypeID}' />
                                        <input type="hidden" id='${loop.index}invID11' value='${cart.inventoryID}' />
                                </c:forEach>
							</c:if>
							<c:if test="${empty Cart}">
								<input type="hidden" name="hdncsize" id="CartSize" value="0">
							</c:if>
							<tr id="tr##">
								<td align="center" style="font-size: 14px;"></td>
								<td align="center" style="font-size: 14px;"></td>
								<td align="center" style="font-size: 14px;"></td>
								<td align="center" style="font-size: 14px;"></td>
								<td align="center" style="font-size: 14px;"></td>
								<td align="center" style="font-size: 14px;"></td>
								<td align="center" style="font-size: 14px;"></td>
								<td align="center" style="font-size: 14px;"></td>
								<td colspan="4"></td>
							</tr>
						</table>
					</div>
				</div>
				<div>
					<input type="hidden" name="LstSize" id="tSize" value='${TaxRates.size()}'>
                    <c:forEach items="${TaxRates}" var="objList" varStatus="loop">
                        <input type="hidden" value='${objList.salesTaxID}' id='${loop.index}tx_id' />
                        <input type="hidden" value='${objList.state}' id='${loop.index}tx' />
                        <input type="hidden" value='${objList.rate}' id='${loop.index}tx_rt' />
                    </c:forEach>
				</div>
					<table class="tabla-listados" cellspacing="0">
						<thead>
							<tr>
								<th colspan="12" style="font-size: 14px;">
									<spring:message code="BzComposer.Invoice.Summary" />
								</th>
							</tr>
						</thead>
						<tr>
							<td>
							<table style="width: 100%;">
								<tr>
									<td style="padding-top: 5px;">
										<spring:message code="BzComposer.Invoice.Message" />
									</td>
									<td>
										<form:select path="message" style="width: 100%;">
											<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
											<form:options items="${Message}" itemValue="value" itemLabel="label" />
										</form:select>
									</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Invoice.Memo" />
									</td>
									<td>
										<form:textarea path="memo" rows="3" cols="40" style="width: 100%;" />
									</td>
								</tr>
							</table>
							</td>
							<td align="right" style="font-size: 14px;" colspan="3">
								<table>
									<tr>
										<td align="right" style="font-size: 14px;">
											<spring:message code="BzComposer.Invoice.TxRate" />
										</td>
										<td style="font-size: 14px;">
											<form:select path="taxID" onchange="TaxValue(this.value,this.form);" id="taxID" style="width: 120px;">
												<!-- <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option> -->
												<form:options items="${TaxRates}" itemValue="salesTaxID" itemLabel="state" />
											</form:select> 
											<c:if test="${not empty Tax}">
												<input type="hidden" value="" />
											</c:if>
										</td>
									</tr>
									<tr>
										<td align="right" style="font-size: 14px;">
											<spring:message code="BzComposer.Invoice.Wght" />
										</td>
										<td align="left" style="font-size: 14px;">
											<form:input path="weight" size="13" style="width: 120px; text-align: right;" readonly="true" onkeypress="return numbersOnlyFloat(event,this.value);" />
										</td>
									</tr>
									<tr>
										<td style="font-size: 14px;">
											<input type="checkbox" name="paid" id="paid" onclick="PaidItem();" value="${invoiceDto.paid}" ${invoiceDto.paid?'checked':''} />
											&nbsp;<spring:message code="BizComposer.Invoice.Paid" />
										</td>
										<td nowrap style="font-size: 14px;">
											<input type="checkbox" name="itemShipped" id="itemShipped" onclick="ShippedItem();" value="${invoiceDto.itemShipped}" ${invoiceDto.itemShipped?'checked':''} />
											&nbsp;<spring:message code="BizComposer.Invoice.ItemShippped" />
										</td>
									</tr>	
								</table>
							</td>
							<td style="font-size: 14px;" colspan="3">
								<table>
									<tr>
										<td align="right" style="font-size: 14px;">
											<div id="tax_field">
												<spring:message code="BizComposer.Invoice.TaxField" />
											</div>
										</td>
										<td style="font-size: 14px;">
											<form:input path="tax" style="text-align: right;" readonly="true" onkeypress="return numbersOnlyFloat(event,this.value);" />
										</td>
									</tr>
									<tr>
										<td align="right" style="font-size: 14px;">
											<spring:message code="BzComposer.Invoice.Shipping" />
										</td>
										<td style="font-size: 14px;"> 
											<form:input path="shipping" onclick="clearShippingCol()" onchange="sumShippingTotal()" style="width: 167px; text-align: right;" onkeypress="return numbersOnlyFloat(event,this.value);" />
										</td>
									</tr>
									<tr>
										<td align="right" style="font-size: 14px;">
											<spring:message code="BzComposer.Invoice.Balanc" />
										</td>
										<td style="font-size: 14px;">
											<form:input path="balance" style="text-align: right;" onclick="clearDiscountCol()" onchange="calDiscountTotal()" onkeypress="return numbersOnlyFloat(event,this.value);" />
										</td>
									</tr>			
								</table>
							</td>						
							<td style="font-size: 14px;" colspan="3">
								<table>			
									<tr>
										<td align="right" style="font-size: 14px;">
											<spring:message code="BzComposer.Invoice.Subtotal" />
										</td>
										<td style="font-size: 14px;">
											<form:input path="subtotal" style="text-align: right;" readonly="true" onkeypress="return numbersOnlyFloat(event,this.value);" />
										</td>
									</tr>
									<tr>
										<td align="right" style="font-size: 14px;">
											<spring:message code="BzComposer.Invoice.Tot" />
										</td>
										<td style="font-size: 14px;">
											<form:input path="total" style="text-align: right;" readonly="true" onkeypress="return numbersOnlyFloat(event,this.value);" />
										</td>
									</tr>
									<tr>
										<td align="right" style="font-size: 14px;">
											<spring:message code="BzComposer.Invoice.AdjustedTotal" />
										</td>
										<td style="font-size: 14px;">
											<form:input path="adjustedtotal" style="text-align: right;" readonly="true" onkeypress="return numbersOnlyFloat(event,this.value);" />
										</td>
										<td>
											<input type="hidden" size="15" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br>
					<div>
						<input type="hidden" value="0" id="hidn" /> 
						<input type="hidden" value="" id="code11" /> 
						<input type="hidden" value="" id="invStyle" />
						<input type="hidden" value="0" id="amt_id" /> 
						<input type="hidden" value="0" id="tax_val" /> 
						<input type="hidden" value="0" id="itmId" />
						<input type="hidden" value="0" id="itmVal" /> 
						<input type="hidden" value="" id="cid" /> 
						<input type="hidden" value="0" id="wt" />
						<form:hidden path="companyID" value="${CID}" />
						<form:hidden path="bsAddressID" />
						<form:hidden path="shAddressID"/>
					</div>
					<div>
						<form:hidden path="size" value="" /> 
						<form:hidden path="item" value="" /> 
						<form:hidden path="qty" value="" />
						<form:hidden path="serialNo" value="" /> 
						<form:hidden path="desc" value="" />  
						<input type="hidden" id="pname" name="pname" value="" />
						<form:hidden path="unitWeight" value="" /> 
						<form:hidden path="wgt" value="" /> 
						<form:hidden path="uprice" value="" /> 
						<form:hidden path="code" value="" />
						<form:hidden path="isTaxable" value="" /> 
						<form:hidden path="itemTypeID" value="" /> 
						<form:hidden path="itemOrder" value="" />
						<form:hidden path="clientVendorID" />
					</div>
					<!-- end Contents -->
			    </section>
				</div>
		<div class="container">
			<div class="row">
				<div class="col-md-12" style="font-size: 16px;" align="center">
					<input type="button" id="btnNewInvoice" class="formbutton" onclick="NewInvoice();" title="New Invoice" style="padding: 8px 20px 8px 20px;" value="<spring:message code='BzComposer.global.new' />" />
					<input type="button" id="btnSaveInvoice" class="formbutton" onclick="onSave(this.form);" title="Save Invoice" style="padding: 8px 20px 8px 20px;" value="<spring:message code='BzComposer.global.saveUpdate' />" />
					<input type="button" id="btnUpdateInvoice" class="formbutton" onclick="ShowUpdate(this.form);" title="Update Customer information" style="padding: 8px 20px 8px 20px;" value="<spring:message code='BzComposer.updatecustomer.updatecustomer' />" />
					<!-- <input type="button" id="btnDeleteInvoice" title="Delete Invoice" class="formbutton" onclick="onDelete(this.form);"  style="padding: 8px 20px 8px 20px;" value="<spring:message code='BzComposer.Invoice.DeleteBtn' />" /> -->
				</div>
			</div>
		</div>
		<div>
			<input type="hidden" id="tabid" name="tabid" value=""/>
			<input type="hidden" id="request_locale" name="request_locale" value=""/>
		</div>
	</form:form>
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>
<script type="text/javascript">
isItemExist=0;
var wghtArr = new Array(100);
var itemArr = new Array(100);
var qtyArr = new Array(100);
var upriceArr = new Array(100);
var codeArr = new Array(100);
var taxArr = new Array(100);
var descArr = new Array(100);
var uwghtArr = new Array(100);
var serialArr = new Array(100);
var itmIDArr = new Array(100);
var itmOrdArr = new Array(100);

deleted = 0;
index1=0;
cnt=0;
count=0;
yestax=0;
tax_rate=0;
rate = 0;

// this function clear input value,
function clearShippingCol(){
	var convertSubData  =  parseFloat(document.InvoiceForm.total.value) -  parseFloat(document.InvoiceForm.shipping.value);
	document.InvoiceForm.total.value = parseFloat(convertSubData).toFixed(2);
	document.InvoiceForm.shipping.value = 0;
}
// this function sum Shipping value in total
function sumShippingTotal() {
	var convertSubData  =  parseFloat(document.InvoiceForm.total.value) +  parseFloat(document.InvoiceForm.shipping.value);
	document.InvoiceForm.total.value = parseFloat(convertSubData).toFixed(2);
}

//this function clear input value, 
function clearDiscountCol(){
	document.InvoiceForm.adjustedtotal.value = 0;
	var convertSubData  =  parseFloat(document.InvoiceForm.total.value) + parseFloat(document.InvoiceForm.balance.value);
	document.InvoiceForm.balance.value = "";
}
//this function for calculat discount amount 
function calDiscountTotal() {
	var convertSubData  =  parseFloat(document.InvoiceForm.total.value) -  parseFloat(document.InvoiceForm.balance.value);
	document.InvoiceForm.adjustedtotal.value = parseFloat(convertSubData).toFixed(2);
}

function Pending_Value(form){
    if(form.isPending.checked==true){
        form.isPending.value="on";
    } else {
        form.isPending.value="off";
    }
}

function TaxaValue(form){
    if(form.taxable.checked==true){
        form.taxable.value="on";
    } else {
        form.taxable.value="off";
    }
}

function PaidItem(){
    if(document.InvoiceForm.paid.checked==true){
        document.InvoiceForm.paid.value="on";
    } else {
        document.InvoiceForm.paid.value="off";
    }
}
function ShippedItem(){
    if(document.InvoiceForm.itemShipped.checked==true){
        document.InvoiceForm.itemShipped.value="on";
    } else {
        document.InvoiceForm.itemShipped.value="off";
    }
}

function Assignment(value, form){
    debugger;
    if(value==0){
        debugger;
        document.InvoiceForm.billTo.value="";
        document.InvoiceForm.shipTo.value="";
        document.InvoiceForm.via.value="0"
        document.InvoiceForm.payMethod.value="0";
        document.InvoiceForm.rep.value="0";
        document.InvoiceForm.term.value="0";
        document.InvoiceForm.taxable.checked=false;
    }
    else if(value==1111){
        window.location.href = "Customer?tabid=NewCustomer";
    } else {
        debugger;
        size = document.getElementById("bSize").value;
        shsize = document.getElementById("sSize").value;
        var i;
        for(i=0;i<size;i++){
            var field1 = document.getElementById(i+"clvid").value;
            if(value==field1){
                document.getElementById('cid').value = value;
                form.custID.value = document.getElementById('cid').value;
                form.companyID.value = document.getElementById(i+"cid").value;
                form.bsAddressID.value = document.getElementById(i+"bsaddr").value;
                form.billTo.value = document.getElementById(i+"bl").value;
                break;
            }
        }
        for(i=0;i<shsize;i++){
            var field2 = document.getElementById(i+"sh_id").value;
            if(value==field2){
                form.shAddressID.value = document.getElementById(i+"shaddr").value;
                form.shipTo.value = document.getElementById(i+"sh").value;
                break;
            }
        }
        sz = document.getElementById('custSize').value;
        for(i=0;i<sz;i++)
        {
            var field11 = document.getElementById("a"+i+"clvndid").value;
            if(value==field11)
            {
                let custBalance = document.getElementById(i+"custBalance").value;
                if(custBalance == "true") document.getElementById("CustomerBalanceBtn").style.backgroundColor = "red";
                else document.getElementById("CustomerBalanceBtn").style.backgroundColor = "#05A9C5";
                let viaItem = document.getElementById(i+"va").value;
                if(viaItem==""){
                    document.InvoiceForm.via.value="0";
                }
                else{
                    document.InvoiceForm.via.value = viaItem;
                    document.InvoiceForm.rep.value = document.getElementById(i+"rp").value;
                    document.InvoiceForm.payMethod.value = document.getElementById(i+"paym").value;
                    document.InvoiceForm.term.value = document.getElementById(i+"trm").value;
                    document.InvoiceForm.taxable.checked = (document.getElementById("a"+i+"txable").value == "1")?true:false;
                    break;
                }
            }
        }
    }
}
	
	function StyleChange(value)
	{
	    document.getElementById('inoiceStyle').value = value;
		document.getElementById('invStyle').value = value;
		size = document.getElementById('CartSize').value;
		hidn_val= document.getElementById('hidn').value;
		if(value==0)
		{
			product();
			/*QTY */
			document.getElementById('td4').style.display='none';
			document.getElementById('td3').style.display='block';		
			document.getElementById('td5').style.display='block';
			/*Serial No */
			document.getElementById('td6').style.display='none';
			document.getElementById('td7').style.display='none';
			/* DESC */	
			//document.getElementById('td8').style.display='block';
			//document.getElementById('td9').style.display='block';
					
			/* Unit Price/RatePrice*/		
			document.getElementById('td11').style.display='none';
			document.getElementById('td10').style.display='block';
			document.getElementById('td12').style.display='block';
			/* Amount*/
			document.getElementById('td13').style.display='block';		
			document.getElementById('td14').style.display='block';
			/* Weight*/	
			document.getElementById('td15').style.display='block';		
			document.getElementById('td16').style.display='block';
			/* Tax */	
			document.getElementById('td17').style.display='block';
			document.getElementById('td18').style.display='block';
				
			productTable(size);
			for(x=0;x<hidn_val;x++)
				productItem(x);					
		}
		if(value!=0)
		{
			document.getElementById('InvStyle').value=value;
			if(value==1)
			{
				service();
				/*QTY */
				//document.getElementById('td4').style.display='block';
				document.getElementById('td3').style.display='block';
				document.getElementById('td5').style.display='block';
				/*Serial No */
				document.getElementById('td6').style.display='none';
				document.getElementById('td7').style.display='none';
				/* DESC */	
				//document.getElementById('td8').style.display='block';
				//document.getElementById('td9').style.display='block';
					
				/* Unit Price/RatePrice*/		
				document.getElementById('td11').style.display='block';
				document.getElementById('td10').style.display='none';
				document.getElementById('td12').style.display='block';
				/* Amount*/
				document.getElementById('td13').style.display='none';		
				document.getElementById('td14').style.display='none';
				/* Weight*/	
				document.getElementById('td15').style.display='none';		
				document.getElementById('td16').style.display='none';
				/* Tax */	
				document.getElementById('td17').style.display='block';
				document.getElementById('td18').style.display='block';
				
				/* item in table */
				var i;
				for(i=0;i<size;i++)
				{
					document.getElementById(i+"icode").style.visibility='visible';
					document.getElementById(i+"qty").style.visibility='visible';
					document.getElementById(i+"desc").style.visibility='visible';
					document.getElementById(i+"iprice").style.visibility='visible';
					document.getElementById(i+"amt").style.visibility='hidden';
					document.getElementById(i+"wgt").style.visibility='hidden';
					document.getElementById(i+"itax").style.visibility='visible';
					document.getElementById(i+"serial").style.visibility='hidden';
				}
				
				document.getElementById("it1").style.visibility='visible';
				document.getElementById("it2").style.display='block';
				document.getElementById("it3").style.visibility='visible';
				document.getElementById("it4").style.display='none';
				document.getElementById("it5").style.visibility='hidden';
				document.getElementById("it6").style.visibility='hidden';
				document.getElementById("it7").style.visibility='visible';
				document.getElementById("it8").style.visibility='hidden';
				//document.getElementById("it22").style.display='block';
				document.getElementById("it42").style.display='block';
					
				for(x=0;x<hidn_val;x++)
					serviceItem(x);
			}
			else if(value==2)
			{
				quick(size);
				for(x=0;x<hidn_val;x++)
					quickItem(x);
			}
			else if(value==3)
			{
				manufacture(size)
				for(x=0;x<hidn_val;x++)
					manufactureItem(x);
			}
			else if(value==4)
			{
				product();
				productTable(size);
				for(x=0;x<hidn_val;x++)
					productItem(x);
				
				/*QTY */
				//document.getElementById('td4').style.display='none';
				document.getElementById('td3').style.display='block';		
				document.getElementById('td5').style.display='block';
				/*Serial No */
				document.getElementById('td6').style.display='none';
				document.getElementById('td7').style.display='none';
				/* DESC */	
				//document.getElementById('td8').style.display='block';
				//document.getElementById('td9').style.display='block';
					
				/* Unit Price/RatePrice*/		
				document.getElementById('td11').style.display='none';
				document.getElementById('td10').style.display='block';
				document.getElementById('td12').style.display='block';
				/* Amount*/
				document.getElementById('td13').style.display='block';		
				document.getElementById('td14').style.display='block';
				/* Weight*/	
				document.getElementById('td15').style.display='block';		
				document.getElementById('td16').style.display='block';
				/* Tax */	
				document.getElementById('td17').style.display='block';
				document.getElementById('td18').style.display='block';
			}
			else if(value==5)
			{
				finance(size);
				for(x=0;x<hidn_val;x++)
					financeItem(x);
			}
			else if(value==6)
			{
				professional(size);
				for(x=0;x<hidn_val;x++)
				{
					quickItem(x);
				}
			}
			else if(value==7)
			{
				ebusiness(size);
				for(x=0;x<hidn_val;x++)
				{
					ebusinessItem(x);
				}
			}
		}
	}
		
	function service()
	{
		/* hidden field for ship to */
		document.getElementById('ship_label').style.visibility="hidden";
		document.getElementById('ship_id').style.visibility="hidden";
				
		/* hidden field for ship date */
		document.getElementById('sh_date_label').style.visibility="hidden";
		document.getElementById('sh_date_id').style.visibility="hidden";
				
		/* hidden field for  rep */
		document.getElementById('rep_label').style.visibility="hidden";
		document.getElementById('rep_id').style.visibility="hidden";
		
		/* hidden field for via */
		document.getElementById('via_label').style.visibility="hidden";
		document.getElementById('via_id').style.visibility="hidden";
		
		/* Visible field for bill to */
		document.getElementById('bill_label').style.visibility="visible";
		document.getElementById('bill_id').style.visibility="visible";
		
		/* Visible field for Term & Payment */
		document.getElementById('td2').style.visibility="visible";		
				
	}
		
	function quick(size)
	{
		/* hidden field for ship to */
		document.getElementById('ship_label').style.visibility="hidden";
		document.getElementById('ship_id').style.visibility="hidden";

		/* hidden field for ship date */
		document.getElementById('sh_date_label').style.visibility="hidden";
		document.getElementById('sh_date_id').style.visibility="hidden";
		
		/* hidden field for  rep */
		document.getElementById('rep_label').style.visibility="hidden";
		document.getElementById('rep_id').style.visibility="hidden";
		
		/* hidden field for via */
		document.getElementById('via_label').style.visibility="hidden";
		document.getElementById('via_id').style.visibility="hidden";
		
		/* hidden field for bill to */
		document.getElementById('bill_label').style.visibility="hidden";
		document.getElementById('bill_id').style.visibility="hidden";
		
		/* hidden field for Term & Payment */
		document.getElementById('td2').style.visibility="hidden";
				
		/*QTY */
		document.getElementById('td4').style.display='none';
		document.getElementById('td3').style.display='block';		
		document.getElementById('td5').style.display='block';
		
		/*Serial No */
		document.getElementById('td6').style.display='none';
		document.getElementById('td7').style.display='none';
		
		/* DESC */	
		//document.getElementById('td8').style.display='block';
		//document.getElementById('td9').style.display='block';
					
		/* Unit Price/RatePrice*/		
		document.getElementById('td11').style.display='none';
		document.getElementById('td10').style.display='block';
		document.getElementById('td12').style.display='block';
				
		/* Amount*/
		document.getElementById('td13').style.display='block';		
		document.getElementById('td14').style.display='block';
		
		/* Weight*/	
		document.getElementById('td15').style.display='none';		
		document.getElementById('td16').style.display='none';
		
		/* Tax */	
		document.getElementById('td17').style.display='block';
		document.getElementById('td18').style.display='block';
				
		/* Item in Table  */
		quickTable(size);
						
	}
		
	function manufacture(size)
	{
		product();
		
		/*QTY */
		document.getElementById('td4').style.display='none';
		document.getElementById('td3').style.display='block';		
		document.getElementById('td5').style.display='block';
		
		/*Serial No */
		document.getElementById('td6').style.display='block';
		document.getElementById('td7').style.display='block';
			
		/* DESC */	
		//document.getElementById('td8').style.display='block';
		//document.getElementById('td9').style.display='block';
				
		/* Unit Price/RatePrice*/		
		document.getElementById('td11').style.display='none';
		document.getElementById('td10').style.display='block';
		document.getElementById('td12').style.display='block';
			
		/* Amount*/
		document.getElementById('td13').style.display='block';		
		document.getElementById('td14').style.display='block';
			
		/* Weight*/	
		document.getElementById('td15').style.display='none';		
		document.getElementById('td16').style.display='none';
			
		/* Tax */	
		document.getElementById('td17').style.display='block';
		document.getElementById('td18').style.display='block';
					
		/* Item in Table  */
		var i;
		for(i=0;i<size;i++)
		{
			document.getElementById(i+"icode").style.visibility='visible';
			document.getElementById(i+"qty").style.visibility='visible';
			document.getElementById(i+"desc").style.visibility='visible';
			document.getElementById(i+"iprice").style.visibility='visible';
			document.getElementById(i+"amt").style.visibility='visible';
			document.getElementById(i+"wgt").style.visibility='hidden';
			document.getElementById(i+"itax").style.visibility='visible';
			document.getElementById(i+"serial").style.visibility='visible';
		}
		document.getElementById("it1").style.visibility='visible';
		document.getElementById("it2").style.display='block';
		document.getElementById("it3").style.visibility='visible';
		document.getElementById("it4").style.display='block';
		document.getElementById("it5").style.visibility='visible';
		document.getElementById("it6").style.visibility='hidden';
		document.getElementById("it7").style.visibility='visible';
		document.getElementById("it8").style.visibility='visible';
			
		//document.getElementById("it22").style.display='none';
		document.getElementById("it42").style.display='none';
				
	}
		
	function product()
	{
		/* visible field for ship to */
		document.getElementById('ship_label').style.visibility="visible";
		document.getElementById('ship_id').style.visibility="visible";

		/* visible field for ship date */
		document.getElementById('sh_date_label').style.visibility="visible";
		document.getElementById('sh_date_id').style.visibility="visible";
				
		/* Visible field for bill to */
		document.getElementById('bill_label').style.visibility="visible";
		document.getElementById('bill_id').style.visibility="visible";
				
		/* Visible field for Term & Payment */
		document.getElementById('td2').style.visibility="visible";	
				
		/* hidden field for  rep */
		document.getElementById('rep_label').style.visibility="visible";
		document.getElementById('rep_id').style.visibility="visible";
				
		/* hidden field for via */
		document.getElementById('via_label').style.visibility="visible";
		document.getElementById('via_id').style.visibility="visible";
					
	}
		
	function productTable(size)
	{
		var i;
		for(i=0;i<size;i++)
		{
			document.getElementById(i+"icode").style.visibility='visible';
			document.getElementById(i+"qty").style.visibility='visible';
			document.getElementById(i+"desc").style.visibility='visible';
			document.getElementById(i+"iprice").style.visibility='visible';
			document.getElementById(i+"amt").style.visibility='visible';
			document.getElementById(i+"wgt").style.visibility='visible';
			document.getElementById(i+"itax").style.visibility='visible';
			document.getElementById(i+"serial").style.visibility='hidden';
		}
		document.getElementById("it1").style.visibility='visible';
		document.getElementById("it2").style.display='block';
		document.getElementById("it3").style.visibility='visible';
		document.getElementById("it4").style.display='block';
		document.getElementById("it5").style.visibility='visible';
		document.getElementById("it6").style.visibility='visible';
		document.getElementById("it7").style.visibility='visible';
		document.getElementById("it8").style.visibility='hidden';
		//document.getElementById("it22").style.display='none';
		document.getElementById("it42").style.display='none';
		
		value=document.getElementById('hidn').value;
		var j;
		for(j=0;j<value;j++)
		{
			document.getElementById(j+'1').style.visibility='visible';
			document.getElementById(j+'2').style.visibility='visible';
			document.getElementById(j+'3').style.visibility='hidden';
			document.getElementById(j+'4').style.visibility='visible';
			document.getElementById(j+'5').style.visibility='visible';
			document.getElementById(j+'6').style.visibility='visible';
			document.getElementById(j+'7').style.visibility='visible';
			document.getElementById(j+'8').style.visibility='visible';
		}
	}
		
	function finance(size)
	{
		service();
		
		/*QTY */
		document.getElementById('td4').style.display='none';
		document.getElementById('td3').style.display='none';		
		document.getElementById('td5').style.display='none';
		
		/*Serial No */
		document.getElementById('td6').style.display='none';
		document.getElementById('td7').style.display='none';
		
		/* DESC */	
		//document.getElementById('td8').style.display='block';
		//document.getElementById('td9').style.display='block';
					
		/* Unit Price/RatePrice*/		
		document.getElementById('td11').style.display='none';
		document.getElementById('td10').style.display='none';
		document.getElementById('td12').style.display='none';
				
		/* Amount*/
		document.getElementById('td13').style.display='block';		
		document.getElementById('td14').style.display='block';
				
		/* Weight*/	
		document.getElementById('td15').style.display='none';		
		document.getElementById('td16').style.display='none';
				
		/* Tax */	
		document.getElementById('td17').style.display='block';
		document.getElementById('td18').style.display='block';
				
		/* Item in Table  */
		var i;
		for(i=0;i<size;i++)
		{
			document.getElementById(i+"icode").style.visibility='visible';
			document.getElementById(i+"qty").style.visibility='hidden';
			document.getElementById(i+"desc").style.visibility='visible';
			document.getElementById(i+"iprice").style.visibility='hidden';
			document.getElementById(i+"amt").style.visibility='visible';
			document.getElementById(i+"wgt").style.visibility='hidden';
			document.getElementById(i+"itax").style.visibility='visible';
			document.getElementById(i+"serial").style.visibility='hidden';
		}
		document.getElementById("it1").style.visibility='visible';
		document.getElementById("it2").style.display='none';
		document.getElementById("it3").style.visibility='visible';
		document.getElementById("it4").style.display='none';
		document.getElementById("it5").style.visibility='visible';
		document.getElementById("it6").style.visibility='hidden';
		document.getElementById("it7").style.visibility='visible';
		document.getElementById("it8").style.visibility='hidden';
		
		//document.getElementById("it22").style.display='none';
		document.getElementById("it42").style.display='none';
						
	}
		
function professional(size){
    service();
    /*QTY */
        document.getElementById('td4').style.display='none';
        document.getElementById('td3').style.display='block';
        document.getElementById('td5').style.display='block';
        /*Serial No */
        document.getElementById('td6').style.display='none';
        document.getElementById('td7').style.display='none';
        /* DESC */
// 				document.getElementById('td8').style.display='block';
// 				document.getElementById('td9').style.display='block';

        /* Unit Price/RatePrice*/
        document.getElementById('td11').style.display='none';
        document.getElementById('td10').style.display='block';
        document.getElementById('td12').style.display='block';
        /* Amount*/
        document.getElementById('td13').style.display='block';
        document.getElementById('td14').style.display='block';
        /* Weight*/
        document.getElementById('td15').style.display='none';
        document.getElementById('td16').style.display='none';
        /* Tax */
        document.getElementById('td17').style.display='block';
        document.getElementById('td18').style.display='block';

        quickTable(size);

}

function quickTable(size){
    /* Item in Table  */
         var i;
        for(i=0;i<size;i++){
            document.getElementById(i+"icode").style.visibility='visible';
            document.getElementById(i+"qty").style.visibility='visible';
            document.getElementById(i+"desc").style.visibility='visible';
            document.getElementById(i+"iprice").style.visibility='visible';
            document.getElementById(i+"amt").style.visibility='visible';
            document.getElementById(i+"wgt").style.visibility='hidden';
            document.getElementById(i+"itax").style.visibility='visible';
            document.getElementById(i+"serial").style.visibility='hidden';
        }
            document.getElementById("it1").style.visibility='visible';
            document.getElementById("it2").style.display='block';
            document.getElementById("it3").style.visibility='visible';
            document.getElementById("it4").style.display='block';
            document.getElementById("it5").style.visibility='visible';
            document.getElementById("it6").style.visibility='hidden';
            document.getElementById("it7").style.visibility='visible';
            document.getElementById("it8").style.visibility='hidden';
            //document.getElementById("it22").style.display='none';
            document.getElementById("it42").style.display='none';

        value=document.getElementById('hidn').value;
        var j;
        for(j=0;j<value;j++){
            document.getElementById(j+'1').style.visibility='visible';//code
            document.getElementById(j+'2').style.visibility='visible';//qty
            document.getElementById(j+'3').style.visibility='hidden';//serial No
            document.getElementById(j+'4').style.visibility='visible';//desc
            document.getElementById(j+'5').style.visibility='visible';//unit price
            document.getElementById(j+'6').style.visibility='visible';//amount
            document.getElementById(j+'7').style.visibility='hidden';//weight
            document.getElementById(j+'8').style.visibility='visible';//tax
        }
}
function ebusiness(size){
    /* visible field for ship to */
        document.getElementById('ship_label').style.visibility="visible";
        document.getElementById('ship_id').style.visibility="visible";

        /* visible field for ship date */
        document.getElementById('sh_date_label').style.visibility="visible";
        document.getElementById('sh_date_id').style.visibility="visible";

        /* hidden field for  rep */
        document.getElementById('rep_label').style.visibility="visible";
        document.getElementById('rep_id').style.visibility="visible";

        /* hidden field for via */
        document.getElementById('via_label').style.visibility="visible";
        document.getElementById('via_id').style.visibility="visible";

        /* hidden field for bill to */
        document.getElementById('bill_label').style.visibility="hidden";
        document.getElementById('bill_id').style.visibility="hidden";

        /*QTY */
        document.getElementById('td4').style.display='none';
        document.getElementById('td3').style.display='block';
        document.getElementById('td5').style.display='block';
        /*Serial No */
        document.getElementById('td6').style.display='none';
        document.getElementById('td7').style.display='none';
        /* DESC */
// 				document.getElementById('td8').style.display='block';
// 				document.getElementById('td9').style.display='block';

        /* Unit Price/RatePrice*/
        document.getElementById('td11').style.display='none';
        document.getElementById('td10').style.display='none';
        document.getElementById('td12').style.display='none';
        /* Amount*/
        document.getElementById('td13').style.display='block';
        document.getElementById('td14').style.display='block';
        /* Weight*/
        document.getElementById('td15').style.display='none';
        document.getElementById('td16').style.display='none';
        /* Tax */
        document.getElementById('td17').style.display='none';
        document.getElementById('td18').style.display='none';

        /* Item in Table  */
         var i;
        for(i=0;i<size;i++){
            document.getElementById(i+"icode").style.visibility='visible';
            document.getElementById(i+"qty").style.visibility='visible';
            document.getElementById(i+"desc").style.visibility='visible';
            document.getElementById(i+"iprice").style.visibility='hidden';
            document.getElementById(i+"amt").style.visibility='visible';
            document.getElementById(i+"wgt").style.visibility='hidden';
            document.getElementById(i+"itax").style.visibility='hidden';
            document.getElementById(i+"serial").style.visibility='hidden';
        }
            document.getElementById("it1").style.visibility='visible';
            document.getElementById("it2").style.display='block';
            document.getElementById("it3").style.visibility='visible';
            document.getElementById("it4").style.display='none';
            document.getElementById("it5").style.visibility='visible';
            document.getElementById("it6").style.visibility='hidden';
            document.getElementById("it7").style.visibility='hidden';
            document.getElementById("it8").style.visibility='hidden';

            //document.getElementById("it22").style.display='none';
            document.getElementById("it42").style.display='none';

}
function TaxValue1(value){
    debugger;
    size=document.getElementById("tSize").value;
    if(value==0){
        document.getElementById('tax_field').innerHTML="0.0 %";
        rate = 0;
        tax_rate=0;
        document.getElementById('tax_val').value=rate;
    }
    else{
        for(i=0;i<size;i++){
            var field = document.getElementById(i+"tx_id").value;
            if(value==field){
                rt = document.getElementById(i+"tx_rt").value;
                document.getElementById('tax_field').innerHTML=rt+" %";
                rate = ( ((yestax/1 ) * (rt/1)) / 100 ).toFixed(2);  
                document.getElementById('tax_val').value=rate;
                tax_rate=rt;
                break;
            }
        }
    }
}

function TaxValue(value,form){
    tot = form.shipping.value;
    subtotal = form.subtotal.value;
    size=document.getElementById("tSize").value;
    if(value==0){
        document.getElementById('tax_field').innerHTML="0.0 %";
        rate = 0;
        tax_rate=0;
        document.getElementById('tax_val').value=rate;
    }
    else{
        for(i=0;i<size;i++){
            var field = document.getElementById(i+"tx_id").value;
            if(value==field){
                rt = document.getElementById(i+"tx_rt").value;
                document.getElementById('tax_field').innerHTML=rt+" %";
                rate = ( ((yestax/1 ) * (rt/1)) / 100 ).toFixed(2);
                document.getElementById('tax_val').value=rate;
                tax_rate=rt;
                break;
            }
        }
    }

    document.InvoiceForm.tax.value=rate;
    total = ((tot/1) + (subtotal/1)+(rate)/1);
    document.InvoiceForm.total.value=total.toFixed(2);
}

function AddItem(form){
    if(form.itemID.value==0 ||form.itemID.value==='01'){
        document.getElementById('serialNo_id').value="";
        document.getElementById('qty_id').value="";

        document.getElementById('unitPrice_id').value="";
        document.getElementById('amount_id').value="";
        document.getElementById('weight_id').value="";
        document.getElementById('pname_id').value="";
        return showSelectItemDialog();
    }
    else{
        isItemExist++;
        style = document.getElementById('invStyle').value;
        /* var wtft = form.weight.value;
        var wtf = wtft.tiFixed(2); */
        wt =  form.weight.value;
        hidn_val= document.getElementById('hidn').value;

        var tr = document.createElement("tr");
        tr.setAttribute("id", "tr"+hidn_val);

        var tr2 = document.getElementById('tr##');
        var parentTr = tr2.parentNode;
        parentTr.insertBefore(tr, tr2);

        serialNo = document.getElementById('serialNo_id').value;
        var desc = document.getElementById('pname_id').value;
        weight = document.getElementById('weight_id').value;
        tax = document.getElementById('tax_id').value;
        ivcode = document.getElementById('code11').value;

        qty=document.getElementById('qty_id').value;
        uprice=document.getElementById('unitPrice_id').value;

        var td1 = document.createElement("td");
        td1.setAttribute("align", "left");
        td1.setAttribute("id",hidn_val+"1");
        tr.appendChild(td1);
        td1.innerHTML=ivcode;

        var td4 = document.createElement("td");
        td4.setAttribute("align", "left");
        td4.setAttribute("id",hidn_val+"4");
        tr.appendChild(td4);
        td4.innerHTML=desc;

        var td3 = document.createElement("td");
        td3.setAttribute("align", "left");
        td3.setAttribute("id",hidn_val+"3");
        tr.appendChild(td3);
        td3.innerHTML=serialNo;

        var td5 = document.createElement("td");
        td5.setAttribute("align", "left");
        td5.setAttribute("id",hidn_val+"5");
        tr.appendChild(td5);
        td5.innerHTML=uprice;

        var td2 = document.createElement("td");
        td2.setAttribute("align", "left");
        td2.setAttribute("id",hidn_val+"2");
        tr.appendChild(td2);
        td2.innerHTML=qty;

        amt=( (qty/1) * (uprice/1) );
        document.getElementById('amount_id').value=amt.toFixed(2);

        var td6 = document.createElement("td");
        td6.setAttribute("align", "left");
        td6.setAttribute("id",hidn_val+"6");
        tr.appendChild(td6);
        td6.innerHTML=amt.toFixed(2);;

        var td7 = document.createElement("td");
        td7.setAttribute("align", "left");
        td7.setAttribute("id",hidn_val+"7");
        tr.appendChild(td7);
        td7.innerHTML=weight;

        var t="";
        if(tax==0){
            t="No";
        }
        else{
            t="Yes";
            yestax = ((yestax/1) + (amt/1)).toFixed(2);
        }

        subtotal= form.subtotal.value;
        subtotal = ((subtotal/1) + (amt/1)).toFixed(2);;
        form.subtotal.value=subtotal;
        tax_val=((yestax * tax_rate) / 100 ).toFixed(2);

        document.InvoiceForm.tax.value=tax_val;

        tot=(form.shipping.value);
        total = ((tot/1) + (subtotal/1) + (tax_val/1)).toFixed(2);
        form.total.value=total;

        form.subtotal.value=subtotal;
        document.getElementById('amt_id').value=subtotal;

        var td8 = document.createElement("td");
        td8.setAttribute("align", "left");
        td8.setAttribute("id",hidn_val+"8");
        tr.appendChild(td8);
        td8.innerHTML=t;

        var button='<img onclick="DeleteRow1('+hidn_val+',this.form);" width="12" src="${pageContext.request.contextPath}/images/delete.png" title="Delete" size="8"/>';

        var td9 = document.createElement("td");
        td9.setAttribute("align", "left");
        td9.setAttribute("colspan","4");
        tr.appendChild(td9);
        td9.innerHTML=button;

        val=(( wt / 1 ) + (weight/1) );

        itemVal=document.getElementById('itmVal').value;
        //wghtArr[indx]=val;
        itemArr[index1]=itemVal;
        qtyArr[index1]=qty;
        upriceArr[index1]=uprice;

        codeArr[index1]=ivcode;
        taxArr[index1]=tax;
        descArr[index1]=desc;
        uwghtArr[index1]=weight;
        serialArr[index1]=serialNo;
        itmIDArr[index1]=document.getElementById('itmId').value;

        index1++;

        form.weight.value=val;
        form.wt.value=val;
        if(style==0 || style==4){//Product/select

            productItem(hidn_val);
        }
        if(style==1){//service
            serviceItem(hidn_val);

        }
        if(style==2 || style==6){//Quick/Professional
            quickItem(hidn_val);
        }

        if(style==3){
            manufactureItem(hidn_val);
        }

        if(style==5){//Finance
            financeItem(hidn_val);
        }

        if(style==7){//Ebusinness
            ebusinessItem(hidn_val);
        }
        //form.amount.value+=amt+";"

        hidn_val=( (hidn_val/1) + 1);

        document.getElementById('hidn').value=hidn_val;
    }
}

function productItem(hidn_val){
    document.getElementById(hidn_val+"3").style.visibility='hidden';
    document.getElementById(hidn_val+"1").style.visibility='visible';
    document.getElementById(hidn_val+"2").style.visibility='visible';
    document.getElementById(hidn_val+"4").style.visibility='visible';
    document.getElementById(hidn_val+"5").style.visibility='visible';
    document.getElementById(hidn_val+"6").style.visibility='visible';
    document.getElementById(hidn_val+"7").style.visibility='visible';
    document.getElementById(hidn_val+"8").style.visibility='visible';

    document.getElementById("it1").style.visibility='visible';
    document.getElementById("it2").style.display='block';
    document.getElementById("it3").style.visibility='visible';
    document.getElementById("it4").style.display='block';
    document.getElementById("it5").style.visibility='visible';
    document.getElementById("it6").style.visibility='visible';
    document.getElementById("it7").style.visibility='visible';
    document.getElementById("it8").style.visibility='hidden';
    //document.getElementById("it22").style.display='none';
    document.getElementById("it42").style.display='none';
}

function serviceItem(hidn_val){
    document.getElementById(hidn_val+"3").style.visibility='hidden';
    document.getElementById(hidn_val+"1").style.visibility='visible';
    document.getElementById(hidn_val+"2").style.visibility='visible';
    document.getElementById(hidn_val+"4").style.visibility='visible';
    document.getElementById(hidn_val+"5").style.visibility='visible';
    document.getElementById(hidn_val+"6").style.visibility='hidden';
    document.getElementById(hidn_val+"7").style.visibility='hidden';
    document.getElementById(hidn_val+"8").style.visibility='visible';

    document.getElementById("it1").style.visibility='visible';
    document.getElementById("it2").style.display='none';
    document.getElementById("it3").style.visibility='visible';
    document.getElementById("it4").style.display='none';
    document.getElementById("it5").style.visibility='hidden';
    document.getElementById("it6").style.visibility='hidden';
    document.getElementById("it7").style.visibility='visible';
    document.getElementById("it8").style.visibility='hidden';
    //document.getElementById("it22").style.display='block';
    document.getElementById("it42").style.display='block';
}

function quickItem(hidn_val){
    document.getElementById(hidn_val+"3").style.visibility='hidden';
    document.getElementById(hidn_val+"1").style.visibility='visible';
    document.getElementById(hidn_val+"2").style.visibility='visible';
    document.getElementById(hidn_val+"4").style.visibility='visible';
    document.getElementById(hidn_val+"5").style.visibility='visible';
    document.getElementById(hidn_val+"6").style.visibility='visible';
    document.getElementById(hidn_val+"7").style.visibility='hidden';
    document.getElementById(hidn_val+"8").style.visibility='visible';

    document.getElementById("it1").style.visibility='visible';
    document.getElementById("it2").style.display='block';
    document.getElementById("it3").style.visibility='visible';
    document.getElementById("it4").style.display='block';
    document.getElementById("it5").style.visibility='visible';
    document.getElementById("it6").style.visibility='hidden';
    document.getElementById("it7").style.visibility='visible';
    document.getElementById("it8").style.visibility='hidden';
    //document.getElementById("it22").style.display='none';
    document.getElementById("it42").style.display='none';
}

function manufactureItem(hidn_val){
    document.getElementById(hidn_val+"3").style.visibility='visible';
    document.getElementById(hidn_val+"1").style.visibility='visible';
    document.getElementById(hidn_val+"2").style.visibility='visible';
    document.getElementById(hidn_val+"4").style.visibility='visible';
    document.getElementById(hidn_val+"5").style.visibility='visible';
    document.getElementById(hidn_val+"6").style.visibility='visible';
    document.getElementById(hidn_val+"7").style.visibility='hidden';
    document.getElementById(hidn_val+"8").style.visibility='visible';

    document.getElementById("it1").style.visibility='visible';
    document.getElementById("it2").style.display='block';
    document.getElementById("it3").style.visibility='visible';
    document.getElementById("it4").style.display='block';
    document.getElementById("it5").style.visibility='visible';
    document.getElementById("it6").style.visibility='hidden';
    document.getElementById("it7").style.visibility='visible';
    document.getElementById("it8").style.visibility='visible';

    //document.getElementById("it22").style.display='none';
    document.getElementById("it42").style.display='none';
}

function financeItem(hidn_val){
    document.getElementById(hidn_val+"3").style.visibility='hidden';
    document.getElementById(hidn_val+"1").style.visibility='visible';
    document.getElementById(hidn_val+"2").style.visibility='hidden';
    document.getElementById(hidn_val+"4").style.visibility='visible';
    document.getElementById(hidn_val+"5").style.visibility='hidden';
    document.getElementById(hidn_val+"6").style.visibility='visible';
    document.getElementById(hidn_val+"7").style.visibility='hidden';
    document.getElementById(hidn_val+"8").style.visibility='visible';

    document.getElementById("it1").style.visibility='visible';
    document.getElementById("it2").style.display='none';
    document.getElementById("it3").style.visibility='visible';
    document.getElementById("it4").style.display='none';
    document.getElementById("it5").style.visibility='visible';
    document.getElementById("it6").style.visibility='hidden';
    document.getElementById("it7").style.visibility='visible';
    document.getElementById("it8").style.visibility='hidden';

    //document.getElementById("it22").style.display='none';
    document.getElementById("it42").style.display='none';
}

function ebusinessItem(hidn_val){
    document.getElementById(hidn_val+"3").style.visibility='hidden';
    document.getElementById(hidn_val+"1").style.visibility='visible';
    document.getElementById(hidn_val+"2").style.visibility='visible';
    document.getElementById(hidn_val+"4").style.visibility='visible';
    document.getElementById(hidn_val+"5").style.visibility='hidden';
    document.getElementById(hidn_val+"6").style.visibility='visible';
    document.getElementById(hidn_val+"7").style.visibility='hidden';
    document.getElementById(hidn_val+"8").style.visibility='hidden';

    document.getElementById("it1").style.visibility='visible';
    document.getElementById("it2").style.display='block';
    document.getElementById("it3").style.visibility='visible';
    document.getElementById("it4").style.display='none';
    document.getElementById("it5").style.visibility='visible';
    document.getElementById("it6").style.visibility='hidden';
    document.getElementById("it7").style.visibility='hidden';
    document.getElementById("it8").style.visibility='hidden';

    //document.getElementById("it22").style.display='none';
    document.getElementById("it42").style.display='none';
}

function ItemChange(value){
    if(value == '01')
    {
        document.getElementById('qty_id').value="";
        document.getElementById('unitPrice_id').value="";
        document.getElementById('amount_id').value="";
        document.getElementById('weight_id').value="";
        document.getElementById('tax_id').value="";
        document.getElementById('pname_id').value="";

        document.getElementById('qty_id').readonly="true";
        document.getElementById('unitPrice_id').readonly="true";
        document.getElementById('weight_id').readonly="true";
        document.getElementById('code11').value=document.getElementById(count+'code').value;
    }
    var size = document.getElementById('itemSize').value;
    var count;
    for(count=0;count<size;count++){
        var invID = document.getElementById(count+'inv').value;
        if(value==invID){
            var category = document.getElementById(count+'cat').value;
            if(category==1){
                //document.getElementById('serialNo_id').value="";
                document.getElementById('qty_id').value="";
// 				document.getElementById('desc_id').value="";
                document.getElementById('unitPrice_id').value="";
                document.getElementById('amount_id').value="";
                document.getElementById('weight_id').value="";
                document.getElementById('tax_id').value="";

                document.getElementById('qty_id').readonly="true";
                document.getElementById('unitPrice_id').readonly="true";
                document.getElementById('weight_id').readonly="true";
                document.getElementById('code11').value=document.getElementById(count+'code').value;
            }
            else{
                var qty = 1;
                //	You can not enter more then your available stock using this function
                var qtyVal = document.getElementById(count+'q').value;
                document.getElementById('qty_id').max = qtyVal;
                var qtyMax = document.getElementById('qty_id').max;
                var qtyMin = 1;
                //you can replace eventListner like keyup keypress blur change

                $(".minutesInput").on('keyup', function(e) {
                    debugger;
                    var num = parseInt(this.value, 10),
                        min = qtyMin,
                        max = qtyMax;

                    if (isNaN(num)) {
                        this.value = "";
                        return;
                    }

                    this.value = Math.max(num, min);
                    this.value = Math.min(num, max);
                });

                var uprice = document.getElementById(count+'price').value;
                var serialNo = document.getElementById(count+'serial').value;
                document.getElementById('serialNo_id').readonly="false";
                document.getElementById('qty_id').readonly="false";
                document.getElementById('unitPrice_id').readonly="false";
                document.getElementById('weight_id').readonly="false";

                document.getElementById('serialNo_id').value=serialNo;
                document.getElementById('qty_id').value=qty;
// 				document.getElementById('desc_id').value=document.getElementById(count+'desc').value;
                document.getElementById('pname_id').value=document.getElementById(count+'pname').value;
                document.getElementById('unitPrice_id').value=uprice;
                amt=((qty/1)*(uprice/1)).toFixed(2);;
                document.getElementById('amount_id').value=amt;
                document.getElementById('weight_id').value=document.getElementById(count+'wt').value;
                document.getElementById('code11').value=document.getElementById(count+'code').value;
                document.getElementById('itmId').value=document.getElementById(count+'itmId').value;
                document.getElementById('itmVal').value=value;
            }
        }
    }
}

function Multiplication(){
    var qty=document.getElementById('qty_id').value;
    var uprice = document.getElementById('unitPrice_id').value;

    var amount=qty*uprice;
    document.getElementById('amount_id').value=amount.toFixed(2);

    return updateQuantityOfSelectedItem();
}

function AddTotal(form){
    value=prompt("Enter Adjusted Amount","");
    form.adjustedtotal.value=value;
}

function Init(){
    debugger;
    var sortId = '<%= request.getAttribute("sortById")%>';
    TaxValue1(1);
    document.getElementById('tax_val').value=rate;
    $('select[id="itemID"]').find('option[value="0"]').attr("selected",true);
    isItemExist = document.getElementById('CartSize').value;
    var initPending='${InvoiceForm.isPending}';
    if(initPending == 'true'){
        //toggle_visibility('pending');
    }
    <c:if test="${not empty Style}">
        StyleChange(${Style});
    </c:if>
    <c:if test="${empty Style}">
        StyleChange(document.InvoiceForm.invoiceStyle.value);
    </c:if>
    var i;
    for(i=0;i<100;i++){
        deleted[i]=0;
    }
    for(j=0;j<100;j++){
        wghtArr[j]=0;
        itemArr[j]=0;
        qtyArr[j]=0;
        upriceArr[j]=0;
        codeArr[j]=0;
        taxArr[j]=0;
        descArr[j]=0;
        uwghtArr[j]=0;
        serialArr[j]=0;
        itmIDArr[j]=0;
    }
    <c:if test="${not empty TaxValue}">
        yestax = ${TaxValue.taxValue};
        val = document.InvoiceForm.taxID.value;
        if(value!=0){
            for(i=0;i<size;i++){
                var field = document.getElementById(i+"tx_id").value;
                if(val==field){
                    rt = document.getElementById(i+"tx_rt").value;
                    document.getElementById('tax_field').innerHTML=rt+" %";
                    rate = ( ((yestax/1 ) * (rt/1)) / 100 ).toFixed(2);
                    document.getElementById('tax_val').value=rate;
                    tax_rate=rt;
                    break;
                }
            }
        }
    </c:if>
    /*To display data either in readonly or not*/
    var readOnly = <%= request.getAttribute("readData") %>

    if(readOnly)
    {
        $('#newInvoice').prop('disabled', true);
        $('#btnNewInvoice').prop('disabled', true);
        $('#btnSaveInvoice').prop('disabled', true);
        $('#btnUpdateInvoice').prop('disabled', true);
        //$('#btnDeleteInvoice').prop('disabled', true);
        $(':input[type="text"]').prop('disabled', false);
        $('input[type=text],textarea').prop('readonly', true);
        $('#custID').prop('readonly', true);
        $('#invoiceStyle').prop('readonly',true);
        $("#sortByLastName").prop('checked',false);
    }
    else{
        $('#newInvoice').prop('disabled', false);
        $('#btnNewInvoice').prop('disabled', false);
        $('#btnSaveInvoice').prop('disabled', false);
        $('#btnUpdateInvoice').prop('disabled', false);
        //$('#btnDeleteInvoice').prop('disabled', false);
        $('input[type=text],textarea').prop('readonly', false);
        $('#custID').prop('readonly', true);
        $('#invoiceStyle').prop('readonly',true);
        $("#sortByLastName").prop('checked',false);
    }
    StyleChange(4);
}

function onSave(form)
{
    debugger;
    No = form.orderNo.value;
    var bill = form.billTo.value;
    if(form.custID.value==0){
        return showValidationDialog();
    }
    else if(isItemExist <=0){
        return showSelectItemDialog();
    } else {
        if(No.length==0 || No==0) {
            return showItemOrderNumberDialog();
        } else {
            event.preventDefault();
            $("#saveInvoice").dialog({
                    resizable: false,
                    height: 200,
                    width: 500,
                    modal: true,
                    buttons: {
                        "<spring:message code='BzComposer.global.ok'/>": function () {

                            $(this).dialog("close");
                            debugger;
                            subtotal=form.subtotal.value;
                            value = form.taxID.value;
                            sze=document.getElementById("tSize").value;
                            var rt=0;
                            for(i=0;i<sze;i++){
                                var field = document.getElementById(i+"tx_id").value;
                                if(value==field){
                                    rt = document.getElementById(i+"tx_rt").value;
                                    document.getElementById('tax_field').innerHTML=rt+" %";
                                    rt = ((((yestax)/1 ) * (rt/1)) / 100 ).toFixed(2);
                                    document.getElementById('tax_val').value=rt;
                                    break;
                                }
                            }
                            subtotal = form.subtotal.value;
                            shipping = form.shipping.value;
                            total = ( (rt/1) + (subtotal/1) + (shipping/1)).toFixed(2);
                            form.total.value=total;
                            form.tax.value=rt;
                            val1 = document.getElementById('hidn').value;
                            val =((val1)/1 - (deleted)/1);
                            form.size.value=val/1;
                            var x;
                            var idV=0;
                            for(x=0;x<val1;x++){
                                value = itemArr[x];
                                if(value!=-1){
                                    form.item.value+=itemArr[x]+";";
                                    form.qty.value+=qtyArr[x]+";";
                                    form.uprice.value+=upriceArr[x]+";";
                                    form.code.value+=codeArr[x]+";";
                                    form.isTaxable.value+=taxArr[x]+";";
                                    form.desc.value+=descArr[x]+";";
                                    form.unitWeight.value+=uwghtArr[x]+";";
                                    form.wgt.value+="0"+";";
                                    form.serialNo.value+=serialArr[x]+";";

                                    form.itemTypeID.value+=itmIDArr[x]+";";
                                    form.itemOrder.value+=idV+";";
                                    idV++;
                                }
                            }
                            csize = document.getElementById('CartSize').value
                            if(csize!=0)
                            {
                                val=((csize/1) + (val)/1) ;
                                var i;
                                ordVal = ( ((document.getElementById('hidn').value)/1) + 1 );
                                for(i=0;i<csize;i++)
                                {
                                    rowid=document.getElementById(i+'delt').value
                                    if(rowid=="del") {
                                        cnt++;
                                    }
                                    else if(rowid=="0") {
                                        form.code.value+=document.getElementById(i+"invCode").value+";";
                                        form.qty.value+= document.getElementById(i+"qty").value+";";
                                        form.desc.value+= document.getElementById(i+"invDesc").value+";";
                                        form.uprice.value+= document.getElementById(i+"uprice").value+";";
                                        form.unitWeight.value+= document.getElementById(i+"weight").value+";";
                                        form.wgt.value+="0"+";";

                                        form.serialNo.value+=document.getElementById(i+"serial").value+";";

                                        itid=document.getElementById(i+'itId11').value;
                                        form.itemTypeID.value+=itid+";";
                                        form.itemOrder.value+=idV+";";
                                        valTax=document.getElementById(i+"tax").value;
                                        if(valTax=="Yes"){
                                            form.isTaxable.value+="1"+";";
                                        } else {
                                            form.isTaxable.value+="0"+";";
                                        }
                                        idV++;
                                        itemVal=document.getElementById(i+'invID11').value;
                                        form.item.value+=itemVal+";";
                                    }
                                }
                                val = ((((val)/1 - (cnt)/1)));
                                form.size.value = val;
                            }
                            debugger;
                            ShippedItem();
                            PaidItem();
                            var amount = document.getElementById("amount_id").value;
                            var custID = form.custID.value;
                            var bsAddressID = form.bsAddressID.value;
                            var shAddressID = form.shAddressID.value;
                            //document.getElementById('tabid').value="SaveInvoice";
                            document.forms["frmInvoice"].action = "Invoice?tabid=SaveInvoice&custID="+custID;
                            document.forms["frmInvoice"].submit();
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
		
function NewInvoice(){
    window.location.href="Invoice?tabid=NewInvoice";
}

function onDelete(form){
    No = form.orderNo.value;
    cid = form.custID.value;
    if(cid==0){
        return showValidationDialog();
    }
    else{
        if(No.length==0 || No==0){
            return showItemOrderNumberDialog();
        }
        else{
            return deleteInvoiceDialog();
        }
    }
}
function ShowUpdate(form){
    cid=form.custID.value;
    if(cid==0){
        return showValidationDialog();
    }
    else{
        window.open("Customer?tabid=editCustomer&cvId="+cid,null,"scrollbars=yes,height=600,width=1225,status=yes,toolbar=no,menubar=no,location=no" );
    }
}

function paymentHistory(form){
    cid=form.custID.value;
    if(cid==0){
        return showValidationDialog();
    }
    else{
        window.open("Invoice?tabid=PaymentHistory&Type=Invoice&CustId="+cid,null,"scrollbars=yes,height=500,width=1300,status=yes,toolbar=no,menubar=no,location=no" );
    }
}

function SendMail(form){
    cid=form.orderNo.value;
    window.open("Invoice?tabid=ShowEmail&OrderType=invoice&OrderNo="+cid,null,"scrollbars=yes,height=500,width=900,status=yes,toolbar=no,menubar=no,location=no" );
}
		
function DeleteRow(d,form)
{
    event.preventDefault();
    $("#deleteRowDialog").dialog({
            resizable: false,
            height: 200,
            width: 300,
            modal: true,
            buttons: {
                "<spring:message code='BzComposer.global.ok'/>": function () {

                    $(this).dialog("close");

                    size=document.getElementById('CartSize').value;
                    isItemExist--;
                    for(jj=0;jj<size;jj++)
                    {
                        rowId=document.getElementById(jj+'rowVal').value;
                        if(d==rowId)
                        {
                            debugger;
                            var rt=0;
                            document.getElementById(d).style.display='none';

                            document.getElementById(jj+'delt').value="del";

                            qty1=document.getElementById(jj+'qty').value;

                            uprice1=document.getElementById(jj+'uprice').value;

                            amt = ((qty1)/1 * (uprice1)/1);

                            wegt=document.getElementById(jj+'weight').value;
                            w=document.InvoiceForm.weight.value;
                            wg=( (w)/1 - (wegt)/1);
                            document.InvoiceForm.weight.value=wg.toFixed(2);

                            subtotal= document.InvoiceForm.subtotal.value;

                            subtotal = ((subtotal/1) - (amt/1));
                            valu=document.InvoiceForm.taxID.value;
                            subtotal=subtotal.toFixed(2);
                            document.InvoiceForm.subtotal.value=subtotal;
                            sze=document.getElementById("tSize").value;
                            var taxid = document.getElementById(jj+"tax").value;

                            for(i=0;i<sze;i++)
                            {
                                var field = document.getElementById(i+"tx_id").value;
                                if(valu==field)
                                {
                                    if(taxid=="Yes")
                                    {
                                        rt = document.getElementById(i+"tx_rt").value;
                                        document.getElementById('tax_field').innerHTML=rt+" %";

                                        yestax = (yestax - (amt/1)).toFixed(2);
                                        tax_rate=rt;
                                        rt = (yestax * (rt/1))/100;
                                        document.getElementById('tax_val').value=rt;
                                        break;
                                    }
                                }
                            }
                            rt = (yestax * (tax_rate/1))/100;
                            shipping = document.InvoiceForm.shipping.value;
                            total = ( (rt/1) + (subtotal/1) + (shipping/1));
                            document.InvoiceForm.total.value=total.toFixed(2);
                            document.InvoiceForm.tax.value=rt;
                            break;
                        }
                    }
                },
                <spring:message code='BzComposer.global.cancel'/>: function () {
                    $(this).dialog("close");
                    return false;
                }
            }
        });
        return false;
    }
		
function DeleteRow1(d,form){
event.preventDefault();
 $("#deleteRowDialog").dialog({
        resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");

                document.getElementById('tr'+d).style.display='none';
                isItemExist--;
                for(jj=0;jj<=index1;jj++)
                {
                    if(d==jj)
                    {
                        itemArr[jj]=-1;
                        qty1=qtyArr[jj];
                        uprice1=upriceArr[jj];
                        t = taxArr[jj];
                        qtyArr[jj]=-1;
                        upriceArr[jj]=-1;
                        codeArr[jj]=-1;
                        taxArr[jj]=-1;
                        descArr[jj]=-1;
                        wegt=uwghtArr[jj];
                        uwghtArr[jj]=-1;
                        serialArr[jj]=-1;
                        itmIDArr[jj]=-1;

                        amt = ((qty1)/1 * (uprice1)/1).toFixed(2);

                        w=document.InvoiceForm.weight.value;
                        wg=( (w)/1 - (wegt)/1);
                        document.InvoiceForm.weight.value=wg.toFixed(2);

                        subtotal= document.InvoiceForm.subtotal.value;

                        subtotal = ((subtotal/1) - (amt/1));
                        document.InvoiceForm.subtotal.value=subtotal.toFixed(2);
                        tot=(document.InvoiceForm.shipping.value);
                        tx = document.InvoiceForm.tax.value;
                        if(t==1)
                        {
                            yestax = (yestax - amt).toFixed(2);
                            tx=(yestax * tax_rate)/100;
                            document.InvoiceForm.tax.value=tx;
                        }

                        total = ((tot/1) + (subtotal/1) + (tx/1));
                        document.InvoiceForm.total.value=total.toFixed(2);
                        deleted++;
                    }
                }
            },
            <spring:message code='BzComposer.global.cancel'/>: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}

function getInvoiceDetailsByBtnName(form, url){
    //window.location.href="Invoice?tabid=FirstInvoice";
    $.ajax({
        type : "GET",
        url : "/Invoice?tabid="+url,
        success : function(data) {
            $(document).find('div#fullPageDetails section').replaceWith($(data).find('div#fullPageDetails').html());
            let cvID2 = form.clientVendorID.value;
            if(cvID2!=null && cvID2!=''){
                form.custID.value = cvID2;
            }
            Init();
        },
        error : function(data) {
            alert("<bean:message key='BzComposer.categorydetail.erroroccurred'/>");
        }
    });
}

function truncate(num) {
    string = "" + num;
    if (string.indexOf('.') == -1)
        return string + '.00';
    seperation = string.length - string.indexOf('.');
    if (seperation > 3)
        return string.substring(0,string.length-seperation+3);
    else if (seperation == 2)
        return string + '0';
    return string;
}
function printCustomerOrder(form){
    let cid = form.custID.value;
    let ttype = form.templateType.value;
    if(cid==0){
        return showValidationDialog();
    } else {
        event.preventDefault();
        $("#printInvoiceDialog").dialog({
            resizable: false,
            height: 250,
            width: 500,
            modal: true,
            buttons: {
                "<spring:message code='BzComposer.global.print'/>": function () {
                    $(this).dialog("close");
                    if(document.getElementById("PrintInvoiceCbx").checked){
                        window.open("Invoice?tabid=PrintInvoice&custID="+cid+"&ttype="+ttype, null, "scrollbars=yes,height=900,width=900,status=yes,toolbar=no,menubar=no,location=no" );
                    }else if(document.getElementById("PrintPackingSlipCbx").checked){
                        window.open("Invoice?tabid=PrintPackingSlip&custID="+cid, null, "scrollbars=yes,height=900,width=900,status=yes,toolbar=no,menubar=no,location=no" );
                    }
                },
                <spring:message code='BzComposer.global.cancel'/>: function () {
                    $(this).dialog("close");
                    return false;
                }
            }
        });
    }
}

function PrintInvoice22(form){
    orderNo=document.InvoiceForm.orderNo.value;
    if(orderNo==""){
        return showItemOrderNumberDialog();
    }
    else{
        window.open("Invoice?tabid=ShowPrint&OrderNo="+orderNo,null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=yes,location=no" );
    }
}
</script>
<!-- dialog box that used in this page -->
<div id="saveInvoice" style="display:none;">
	<p><spring:message code="BizComposer.Invoice.SaveUpdate" /></p>
</div>
<div id="showValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.Estimaion.cName.Validation" /></p>
</div>
<div id="showSelectItemDialog" style="display:none;">
	<p><spring:message code="BzComposer.invoice.selectItemFirst"/></p>
</div>
<div id="showItemOrderNumberDialog" style="display:none;">
	<p><spring:message code="BizComposer.Invoice.OrderNo.Validation" /></p>
</div>
<div id="deleteInvoiceDialog" style="display:none;">
	<p><spring:message code="BzComposer.Invoice.Delete" /></p>
</div>
<div id="updateBillingAddressDialog" style="display:none;">
	<p><spring:message code="BzComposer.invoice.saveBillingAddress"/></p>
</div>
<div id="updateShippingAddressDialog" style="display:none;">
	<p><spring:message code="BzComposer.invoice.saveShippingAddress"/></p>
</div>
<div id="deleteRowDialog" style="display:none;">
	<p><spring:message code="BzComposer.Estimaion.DeleteItem" /></p>
</div>
<div id="printInvoiceDialog" style="display:none;">
    <div style="margin-bottom:10px;">
        <strong>Please select what would you like to print</strong>
    </div>
    <div>
        <input type="checkbox" id="PrintInvoiceCbx" />
        <span><spring:message code="BzComposer.invoice.invoicetitle" /></span>
    </div>
    <div>
        <input type="checkbox" id="PrintPackingSlipCbx" />
        <span><spring:message code="BzComposer.configuration.packingslips" /></span>
    </div>
</div>