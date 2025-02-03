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
<title><spring:message code="BzComposer.estimaiontitle" /></title>
<style type="text/css">
body{ min-width: 1366px; }
.msgstyle{ font-size:18px; color: #3D9EAC; }
.fontstyle { font-size: 14px; }
table.cart tbody tr td {
    font-size: 14px;				/*Added on 23-09-2019  */
}
</style>
</head>
<body onload="Init();">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<form:form name="EstimationForm" id="estimationForm" method="post" modelAttribute="estimationDto">
	<input type="hidden" name="isInvoice" value="">
	<input type="hidden" name="isSalestype" value="">
	<form:errors />
	<div>
		<span style="font-size: 1.2em; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.estimation.estimationtitle" />
		</span>
	</div>

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
			<input type="hidden" id='${loop.index}custBalance' value='${objList.customerHasBalance}' />
		</c:forEach>
	</div>

	<div id="BillShipAddrDetails">
		<input type="hidden" name="BLSize" id="bSize" value='${BillAddr.size()}'>
		<c:forEach items="${BillAddr}" var="objList" varStatus="loop">
			<input type="hidden" value='${objList.clientVendorID}' id='${loop.index}id' />
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
			<input type="hidden" value='${objList.inventoryName}' id='${loop.index}pname' />
			<input type="hidden" value='${objList.invDesc}' id='${loop.index}desc' />
			<input type="hidden" value='${objList.salePrice}' id='${loop.index}price' />
			<input type="hidden" value='${objList.weight}' id='${loop.index}wt' />
			<input type="hidden" value='${objList.isCategory}' id='${loop.index}cat' />
			<input type="hidden" value='${objList.itemTypeID}' id='${loop.index}itmId' />
			<input type="hidden" value='${objList.serialNo}' id='${loop.index}serial' />
		</c:forEach>
	</div>

	<table class="tabla-listados" cellspacing="0" style="margin-top: -1px;">
		<c:if test="${not empty Status}">
			<tr>
				<td colspan="3">
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
		<thead>
			<tr>
				<th colspan="3" style="font-size:14px;">
					<spring:message code="BzComposer.estimation.customerinfo" />
				</th>
			</tr>
		</thead>
		<tr>
			<td style="width:30%;">
			<table>
				<tr>
					<td colspan="2" style="font-size:14px;"><spring:message code="BzComposer.estimation.customertitle" /></td>
					<td colspan="4" style="font-size: 14px;" align="left" width="50%">
						<spring:message code="BzComposer.estimation.sortby"/>
					</td>
					<%-- <td style="font-size: 14px;">
						<spring:message code="BizComposer.Estimaion.TaxableChkBox" />
					</td> --%>
					<td style="font-size:14px;"><spring:message code="BzComposer.estimaion.companycheckbox" /></td>
					<td style="font-size:14px;"><spring:message code="BzComposer.estimaion.taxablecheckbox" /></td>
				</tr>
				<tr>
					<td style="font-size:14px;">
						<div id="custDiv">
						<div id="custDiv">
						<form:select path="custID" onchange="Assignment(this.value,this.form);" style="width:300px;">
							<form:option value="0">
								<spring:message code="BzComposer.ComboBox.Select" />
							</form:option>
							<form:options items="${CDetails}" itemValue="value" itemLabel="label" />
						</form:select>
						</div>
						</div>
					</td>
					<td style="font-size: 14px;">
						<input type="checkbox" id="sortByLastName" name="sortByLastName" value="sortByLastName"/>
					</td>
					<td colspan="3" style="font-size:14px;" width="50%">
						<spring:message code="BzComposer.estimation.lastname"/>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td align="center" style="font-size:14px;">
						<input type="checkbox" id="company" name="company" onclick="TaxaValue(this.form);" value="${estimationDto.company}" ${estimationDto.company?'checked':''} />
					</td>
					<td align="center" style="font-size:14px;">	
						<input type="checkbox" id="taxable" name="taxable" onclick="TaxaValue(this.form);" value="${estimationDto.taxable}" ${estimationDto.taxable?'checked':''} />
					</td>
				</tr>
			</table>
			</td>
			<td align="center" style="font-size: 14px;width:35%;">
				<input type="button" class="formbutton" onclick="getEstimationDetailsByBtnName(this.form, 'FirstEstimation');" style="padding: 8px 10px 8px 10px; font-size: 16px;" value="<spring:message code='BzComposer.global.first' />" />
				<input type="button" class="formbutton" onclick="getEstimationDetailsByBtnName(this.form, 'LastEstimation');" style="padding: 8px 10px 8px 10px; font-size: 16px;" value="<spring:message code='BzComposer.global.last' />" />
				<input type="button" class="formbutton" onclick="getEstimationDetailsByBtnName(this.form, 'PreviousEstimation');" style="padding: 8px 10px 8px 10px; font-size: 16px;" value="<spring:message code='BzComposer.global.previous' />" />
				<input type="button" class="formbutton" onclick="getEstimationDetailsByBtnName(this.form, 'NextEstimation');" style="padding: 8px 10px 8px 10px; font-size: 16px;" value="<spring:message code='BzComposer.global.next' />" />
				<input type="button" id="newEstimation" title="New Invoice" class="formbutton" onclick="NewEstimation();" style="padding: 8px 10px 8px 10px; font-size: 16px;" value="<spring:message code='BzComposer.global.new' /> " />
				<br>
			<!-- <input type="button" class="formbutton" id="CustomerBalanceBtn" onclick="paymentHistory(this.form);" style="padding: 8px 20px 8px 20px; font-size: 16px;" value="<spring:message code='BzComposer.global.balance' />" /> -->
				<c:if test="${not empty Enable}">
					<input type="button" class="formbutton" title="Send Mail to..." onclick="SendMail(this.form);" style="padding: 8px 30px 8px 30px; font-size: 16px;" value="<spring:message code='BzComposer.global.sendmail' />" />
				</c:if>
				<c:if test="${empty Enable}">
					<input type="button" class="formbutton" title="Send Mail to..." onclick="SendMail(this.form);" disabled="true" style="padding: 8px 30px 8px 30px; font-size: 16px;" value="<spring:message code='BzComposer.global.sendmail' />" />
				</c:if>
				<input type="button" class="formbutton" onclick="printCustomerOrder(this.form);" style="padding: 8px 30px 8px 30px; font-size: 16px;" value="<spring:message code='BzComposer.global.Print' />" />
			</td>
			<td align="right" style="width:35%;">
				<table>
					<tr>
					    <td style="font-size:14px;">
                            <spring:message code="BzComposer.common.template" />
                        </td>
						<td style="font-size:14px;">
							<input type="hidden" name="Ivhidden" id="InvStyle" />
							<spring:message code="BzComposer.estimation.orderstyle" />
						</td>
						<td align="left" colspan="2" style="font-size:14px;">
							<spring:message code="BzComposer.estimation.date" />
						</td>
						<td colspan="2" align="left" style="font-size:14px;">
							<spring:message code="BzComposer.estimation.estimationnumber" />
						</td>
					</tr>
					<tr>
					    <td style="font-size: 14px;">
                            <%-- <form:select path="templateType">
                                <form:option value="3">Product Standard</form:option>
                                <form:option value="4">Product Charcoal</form:option>
                            </form:select> --%>
                            
                            <c:set var="invoiceType" scope="page" value="${fn:toLowerCase(fn:substring(estimationDto.formTemplateType.templateName,0,3))}"/> 
											<form:select path="templateType">
												 <c:forEach var="estItems" items="${estimationDto.formTemplateType.mappingData}" varStatus="status">
												 
												 <c:set var="selectID" scope="page" value="${estItems.templateNo}"/>
												 
												 	<form:option value="${selectID}" selected="${estItems.isSelected==true?'selected':''}">${estItems.templateType}</form:option>
												 </c:forEach>
											 </form:select>   
                        </td>
                        <td style="font-size:14px;">
                            <form:select path="invoiceStyle" onchange="StyleChange(this.value);" onkeydown="StyleChange(this.value);" onkeyup="StyleChange(this.value);">
                                <form:option value="0">
                                    <spring:message code="BzComposer.ComboBox.Select" />
                                </form:option>
                                <form:options items="${InvoiceStyle}" itemValue="value" itemLabel="label" />
                            </form:select>
                        </td>
                        <td colspan="2" style="font-size:14px;">
                            <form:input path="orderDate" readonly="true" size="9" />
                            <img src="${pageContext.request.contextPath}/images/cal.gif" onclick="displayCalendar(document.EstimationForm.orderDate,'mm-dd-yyyy',this);">
                        </td>
                        <td colspan="2" align="left" style="font-size:14px;">
                            <form:input path="orderNo" style="text-align: right;" size="10" readonly="true" />
                        </td>
				    </tr>
			    </table>
			</td>
		</tr>
		<tr align="left">
			<td align="center" style="font-size:14px;">
				<table style="width: 100%;">
					<tr>
						<td id="bill_label" style="font-size:14px;"></td>			    	
					</tr>
				</table>			
		   </td>
			<td style="font-size:14px;">
				<div id="pending" style="display:none;"><font size="10"><spring:message code="BzComposer.estimation.pending" /></font></div>
			</td>
			<td id="ship_label" style="font-size:14px;"></td>
		</tr>
		<tr>
			<td width="25%" align="left" style="font-size:14px;">
				<table>
					<tr>
						<td id="bill_id" style="font-size:14px;">
							<spring:message code="BzComposer.estimation.billto" /><br/>
							<form:textarea path="billTo" rows="6" cols="25" onclick="ShowBillingAddressPage(this.form);" style="resize: none; width: 300px;" />
						</td>
			 		</tr>
				</table>
			</td>
			<td>&nbsp;</td>
			<td align="right">
				<table>
					<tr>
						<td id="ship_id" style="font-size:14px;">
			    			&nbsp;&nbsp;&nbsp;&nbsp;
			    			<spring:message code="BzComposer.estimation.shipto" />
			    			<br/>
			    			&nbsp;&nbsp;&nbsp;&nbsp;
			    			<form:textarea path="shipTo" rows="6" cols="25" onclick="ShowShippingAddressPage(this.form);" style="resize: none; width: 300px;" />
			 			</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="font-size:14px;" colspan="3">
				<table align="left">
					<tr>
						<%-- <td id="po_num_label" style="font-size:14px;">
							<spring:message code="BzComposer.Invoice.PONum" />
						</td> --%>
						<td id="sh_date_label" style="font-size:14px;">
							<spring:message code="BzComposer.estimation.shipdate" />
						</td>
						<td id="rep_label" style="font-size:14px;">
							<spring:message code="BzComposer.estimation.rep" />
						</td>	
						<td style="font-size:14px;">
							<spring:message code="BzComposer.estimation.term" />					
						</td>
						<td style="font-size:14px;">
							<spring:message code="BzComposer.estimation.paymethod" />
						</td>
						<td id="via_label" style="font-size:14px;">
							<spring:message code="BzComposer.estimation.via" />
						</td>
					</tr>
					<tr>
						<%-- <td id="po_num_id" style="font-size:14px;">
							<form:input path="poNum" size="5" onkeydown="return numbersonly(event,this.value)"></form:input>
						</td> --%>
						<td id="sh_date_id" style="font-size:14px;">
							<form:input path="shipDate" readonly="true" size="15" />
							<img src="${pageContext.request.contextPath}/images/cal.gif" onclick="displayCalendar(document.EstimationForm.shipDate,'mm-dd-yyyy',this);">
						</td>
						<td id="rep_id" style="font-size:14px;">
							<form:select path="rep" style="width:150px;">
								<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
								<form:options items="${Rep}" itemValue="value" itemLabel="label" />
									<td style="font-size:14px;">
										<form:select path="term" style="width:150px;">
											<form:option value="0">
												<spring:message code="BzComposer.ComboBox.Select" />
											</form:option>
											<form:options items="${Term}" itemValue="value" itemLabel="label" />
										</form:select>
									</td>
									<td style="font-size:14px;">
										<form:select path="payMethod" style="width:150px;">
											<c:if test="${not empty PayMethod}">
												<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
												<form:options items="${PayMethod}" itemValue="value" itemLabel="label" />
											</c:if>
										</form:select>
									</td>
							</form:select>
						</td>
						<td id="via_id" style="font-size:14px;">
							<form:select path="via" style="width:220px;">
								<form:option value="0">
									<spring:message code="BzComposer.ComboBox.Select" />
								</form:option>
								<form:options items="${Via}" itemValue="value" itemLabel="label" />
							</form:select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div id="product">
		<table class="tabla-listados" cellspacing="0">
			<thead>
				<tr>
					<th colspan="12" style="font-size:14px;">
						<spring:message code="BzComposer.estimaion.iteminfo" />
					</th>
				</tr>
			</thead>
			<tr>
				<td id="td1" style="pading-left: 30px;font-size:14px;">
					<spring:message code="BzComposer.estimation.itemid" />
				</td>
				<td style="font-size: 14px;">
                    <div style="font-size: 14px;">
                    <spring:message code="BzComposer.estimation.itemname" /></div>
                </td>
				<td style="font-size:14px;">
					<div id="td10" style="display:block;font-size:14px;">
						<spring:message code="BzComposer.estimation.unitprice" />
					</div>
					<div id="td11" style="display:none;font-size:14px;">
						<spring:message code="BzComposer.estimation.rateprice" />
					</div>
				</td>	
				<td style="font-size:14px;">
                    <div id="td3" style="font-size:14px;">
                        <spring:message code="BzComposer.estimation.quantity" />
                    </div>
                    <div id="td4" style="font-size:14px;">
                        <spring:message code="BzComposer.estimation.rate" />
                    </div>
                </td>
<!-- 			<td> -->
<%-- 			<div id="td8"><spring:message code="BzComposer.Invoice.Desc" /></div></td> --%>
				<td style="font-size: 14px;">
					<div id="td6" style="display:none;">
						<spring:message code="BzComposer.estimation.serialnumber" />
					</div>
				</td>
				<td style="font-size: 14px;">	
					<div id="td13" style="display:block;font-size:14px;">
						<spring:message code="BzComposer.estimation.amount" />
					</div>
				</td>
				<td style="font-size: 14px;">
					<div id="td15" style="display:block;font-size:14px;">
						<spring:message code="BzComposer.estimation.weight" />
					</div>
				</td>
				<td style="font-size: 14px;">	
					<div id="td17" style="display:block;">
						<spring:message code="BzComposer.estimation.tax" />
					</div>
				</td>
				<td colspan="4"></td>
			</tr>
			<tr>
			<td id="td2" style="font-size: 14px;">
				<div id="itemDiv">
				<form:select path="itemID" onchange="ItemChange(this.value);" id="itemID">
					<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
					<c:forEach items="${ItemList}" var="itmList">
						<c:if test="${itmList.isCategory == 0}">
							<form:option value='${itmList.invID}'>
								&nbsp;&nbsp;&nbsp;&nbsp;
								${itmList.invCode}
							</form:option>
						</c:if>
						<c:if test="${itmList.isCategory == 1}">
							<form:option value='01'>
								${itmList.invCode}
							</form:option>
						</c:if>
					</c:forEach>
				</form:select>
				</div>
			</td>
			<td style="font-size: 14px;width:50%;">
                <div style="font-size: 14px;">
                    <input type="text" readonly="readonly" id="pname_id" onchange="return saveNewItemName();" style="width:95%;"/>
                </div>
                <div id="SaveItemName" style="display:none;">
                    <p><spring:message code="BzComposer.estimation.saveitemname"/></p>
                </div>
            </td>
			<td style="font-size: 14px;">	
				<div style="padding-top: 0px;" id="td12"><input type="text" style="text-align: right;" size="10"  readonly="readonly" id="unitPrice_id" onkeypress="return numbersOnlyFloat(event,this.value);" onchange="return saveNewUnitPrice();"/>
				</div>	
				<div id="SaveUnitPrice" style="display:none;">
    				<p><spring:message code="BzComposer.estimation.saveitemunitprice"/></p>
				</div>	
			</td>
			<td style="font-size: 14px;">
                <div style="padding-top: 0px;display:block;font-size:14px;" id="td5">
                    <input class="minutesInput" min="1" style="text-align: right;" type="text" size="10" id="qty_id" onkeypress="return numbersonly(event,this.value);" />
                </div>
            </td>
<!-- 			<td><div id="td9"  > -->
			
<!-- 			 <textarea rows="4" cols="33" readonly="readonly" id="desc_id"></textarea> -->
<!-- 			 </div> -->
<!-- 			</td>		 -->
			
			<td style="font-size: 14px;">
				<div id="td7" style="font-size: 14px;">
					<input type="hidden" size="10" id="serialNo_id" readonly="readonly" />
				</div>
			</td>
			<td style="font-size: 14px;">				
				<div id="td14" style="display:block;font-size: 14px;">
					<input type="text" style="text-align: right;" size="10" readonly="true" id="amount_id" onfocus="Multiplication();" onkeypress="return numbersOnlyFloat(event,this.value);" />
				</div>
			</td>
			<td style="font-size: 14px;">
				<div id="td16" style="display:block;font-size:14px;">
					<input type="text" style="text-align: right;" name="itemWeight" size="10" readonly="true" id="weight_id" onkeypress="return numbersOnlyFloat(event,this.value);" />
				</div>
			</td>				
			<td style="font-size: 14px;">
				<div id="td18" style="display:block;font-size:14px;">
					<select id="tax_id">
						<option value="0"><spring:message code="BzComposer.estimation.tax.no"/></option>
						<option value="1"><spring:message code="BzComposer.estimation.tax.yes"/></option>
					</select>
				</div>
			</td>
			<td style="padding-right:5px;font-size:14px;">
				<div>
					<input type="button" class="formbutton" name="addItem" title="To add item click it" onclick="AddItem(this.form);" value='<spring:message code="BzComposer.estimation.additem"/>' style="padding: 8px 10px 8px 10px; font-size: 16px;" />
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
						<spring:message code="BzComposer.estimation.itemid" />
					</div>
				</th>
				<th style="font-size: 14px;width:40%;">
                    <div id="it3">
                        <spring:message code="BzComposer.estimation.itemname" />
                    </div>
                </th>
				<th style="font-size: 14px;">
					<div id="it8">
						<spring:message code="BzComposer.estimation.serialnumber" />
					</div>
				</th>
				<th style="font-size: 14px;">
					<div id="it4">
						<spring:message code="BzComposer.estimation.unitprice" />
					</div>
					<div id="it42" style="display:none;">
						<spring:message code="BzComposer.estimation.rateprice" />
					</div>
				</th>
				<th style="font-size: 14px;">
                    <div id="it2">
                        <spring:message code="BzComposer.estimation.quantity" />
                    </div>
                    <div id="it22" style="display:none;">
                        <spring:message code="BzComposer.estimation.rate" />
                    </div>
                </th>
				<th style="font-size: 14px;">
					<div id="it5">
						<spring:message code="BzComposer.estimation.amount" />
					</div>
				</th>
				<th style="font-size: 14px;">
					<div id="it6">
						<spring:message code="BzComposer.estimation.weight" /></div>
				</th>
				<th style="font-size: 14px;">
					<div id="it7">
						<spring:message code="BzComposer.estimation.tax" />
					</div>
				</th>
				<th style="font-size: 14px;">
					<spring:message code="BzComposer.estimation.deleteoption" />
				</th>
				<th colspan="3">
					&nbsp;
				</th>
			</tr>
		</thead>
		<c:if test="${not empty Cart}">
			<input type="hidden" name="hdncsize" id="CartSize" value='${Cart.size()}'>
			<c:forEach items="${Cart}" var="cart" varStatus="loop">
				<tr id='${loop.index}row'>
					<td align="left" style="font-size: 14px;">
						<div id='${loop.index}icode' style="font-size:14px;">
							${cart.invCode}
						</div>
					</td>
					<td style="font-size: 14px;">
                        <div id='${loop.index}desc' style="font-size:14px;">
                            ${cart.invDesc}
                        </div>
                    </td>
					<td align="left" style="font-size: 14px;">
						<div id='${loop.index}iserial' style="font-size:14px;">
							${cart.serialNo}
						</div>
					</td>
					<td align="left" style="font-size: 14px;">
						<div id='${loop.index}iprice' style="font-size:14px;">
							${cart.uprice}
						</div>
					</td>
					<td align="left" style="font-size: 14px;">
                        <div id='${loop.index}qt' style="font-size:14px;">
                            ${cart.qty}
                        </div>
                    </td>
					<td align="left" style="font-size: 14px;">
						<div id='${loop.index}amt' style="font-size:14px;">
							${cart.amount}
						</div>
					</td>
					<td align="left">
						<div id='${loop.index}wgt' style="font-size:14px;">
							${cart.weight}
						</div>
					</td>
					<td align="left">
						<div id='${loop.index}itax' style="font-size:14px;">
							${cart.tax}
						</div>
					</td>
					<td align="left" colspan="3">
						<img onclick="DeleteRow('${loop.index}row',this.form);"
						src="${pageContext.request.contextPath}/images/delete.png"
						title="Delete" size="8" width="12"/>
					</td>
				</tr>
					<input type="hidden" id='${loop.index}delt' value="0" />
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
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center" colspan="4"></td>
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
				<th colspan="12" style="font-size:14px;">
					<spring:message code="BzComposer.estimation.summary" />
				</th>
			</tr>
		</thead>
		<tr>
						<%-- <td>
							<table>
								<tr>
									<td style="padding-top: 5px;">
										<spring:message code="BzComposer.estimation.Message" />
									</td>
									<td>
										<form:select path="message" style="width: 400px;">
											<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
											<form:options items="${Message}" itemValue="value" itemLabel="label" />
										</form:select>
									</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.estimation.Memo" />
									</td>
									<td>
										<form:textarea path="memo" rows="3" cols="40" style="width: 400px;">
										</form:textarea>
									</td>
								</tr>
							</table>
						</td> --%>
			<!-- <td align="left" style="font-size:14px;" colspan="3"> -->
			<td>
				<table style="width: 100%;">
					<tr>
						<td style="padding-top: 5px;">
							<spring:message code="BzComposer.estimation.message" /> 
						</td>
						<td>
							<form:select path="message" style="width: 95%;">
								<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
								<form:options items="${Message}" itemValue="value" itemLabel="label" />
							</form:select>
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="BzComposer.estimation.memo" />
						</td>
						<td>
							<form:textarea path="memo" rows="3" cols="40" style="width: 95%;"></form:textarea>
						</td>
					</tr>
				</table>
			</td>
			<td style="font-size:14px;" colspan="3">
				<table>
					<tr>
						<td align="right" style="font-size:14px;">
							<spring:message code="BzComposer.estimation.tax" />
						</td>
						<td style="font-size:14px;">
							<form:select path="taxID" onchange="TaxValue(this.value,this.form);"  id="taxID" style="width: 120px;">
								<!-- <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option> -->
								<form:options items="${TaxRates}" itemValue="salesTaxID" itemLabel="state" />
							</form:select> 
							<c:if test="${not empty Tax}">
								<input type="hidden" value="" />
							</c:if>
						</td>
					</tr>
					<tr>
						<td align="right" style="font-size:14px;">
							<spring:message code="BzComposer.estimation.weight" />
						</td>
						<td align="left" style="font-size:14px;">
							<form:input path="weight" size="13" style="width: 120px; text-align: right;"  readonly="true" onkeypress="return numbersOnlyFloat(event,this.value);" />
						</td>
					</tr>
				</table>
			</td>
			<td style="font-size:14px;" colspan="3">
				<table>
					<tr>
						<td style="font-size:14px;">
							<div id="tax_field">
								<spring:message code="BzComposer.estimation.taxfield" />
							</div>
						</td>
						<td style="font-size:14px;">
							<form:input path="tax" readonly="true" style="width: 170px; text-align: right;" onkeypress="return numbersOnlyFloat(event,this.value);" />
						</td>
					</tr>
					<tr>
						<td align="right" style="font-size: 14px;">
							<spring:message code="BzComposer.estimation.shipping" />
						</td>
						<td style="font-size:14px;">
							<form:input path="shipping" onclick="clearShippingCol()" onchange="sumShippingTotal()" style="width: 170px; text-align: right;" onkeypress="return numbersOnlyFloat(event,this.value);" />
						</td>
					</tr>
				</table>
			</td>
			<td style="font-size:14px;" colspan="3">
				<table>
					<tr>
						<td align="right" style="font-size:14px;">
							<spring:message code="BzComposer.estimation.subtotal" />
						</td>
						<td style="font-size:14px;">
							<form:input path="subtotal" readonly="true" style="text-align: right;" onkeypress="return numbersOnlyFloat(event,this.value);" />
						</td>
					</tr>
					<tr>
						<td align="right" style="font-size:14px;">
							<spring:message code="BzComposer.estimation.totalindollers" />
						</td>
						<td style="font-size:14px;">
							<form:input path="total" style="text-align: right;" readonly="true" onkeypress="return numbersOnlyFloat(event,this.value);" />
						</td>
					</tr>
					<tr>
						<td align="right" style="font-size:14px;">
							<spring:message code="BzComposer.estimation.adjustedtotalindollers" />
						</td>
						<td style="font-size:14px;">
							<form:input path="adjustedtotal" style="text-align: right;" onkeypress="return numbersOnlyFloat(event,this.value);" />
						</td>
					</tr>
				</table>
			</td>
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
		 	<input type="hidden" name="pname" id="pname" value="" />
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
				<input type="button" id="btnNewEstimation" class="formbutton" onclick="NewEstimation();" title="New Estimation" style="padding: 8px 20px 8px 20px;" value="<spring:message code='BzComposer.global.new' />" />
				<input type="button" id="btnSaveEstimation" class="formbutton" onclick="onSave(this.form);" title="Save Estimation" style="padding: 8px 20px 8px 20px;" value="<spring:message code='BzComposer.global.saveUpdate' />" />
				<input type="button" id="btnUpdateCustomer" class="formbutton" onclick="ShowUpdate(this.form);" title="Update Customer information" style="padding: 8px 20px 8px 20px;" value="<spring:message code='BzComposer.updatecustomer.updatecustomer' />" />
			</div>
		</div>
	</div>
	<div>
		<input type="hidden" id="tabid" name="tabid" value=""/>
	</div>
</form:form>	
</div>
</div>
</div>
</div>
</div>
<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>
<script type="text/javascript">
isItemExist = 0;
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

$(function() {
	var locale = "<%= request.getAttribute("selectedLocale")%>";

	$('select[id="locale"]').find('option[value="'+locale+'"]').attr("selected",true);

	$("#sortByLastName").change(function(){
		var checked = $("#sortByLastName").prop('checked');
		if(checked == true){
			$.ajax({
				type: "POST",
				url:"Estimation?tabid=sortEstimation&SortBy=LastName",
				data:{sortBy : "LastName"},
				}).done(function(data){
				$(document).find('div#custDiv').replaceWith($(data).find('div#custDiv').html());
				});
		} else {
			$.ajax({
				type: "POST",
				url:"Estimation?tabid=sortEstimation&SortBy=Name",
				data:{sortBy : "Name"},
				}).done(function(data){
				$(document).find('div#custDiv').replaceWith($(data).find('div#custDiv').html());
			});
		}
	});
});

//This function clear Shipping input value, If Shipping input have value then this value reduce from total
function clearShippingCol(){
	var convertSubData  =  parseFloat(document.EstimationForm.total.value) -  parseFloat(document.EstimationForm.shipping.value);
	document.EstimationForm.total.value = parseFloat(convertSubData).toFixed(2);
	document.EstimationForm.shipping.value = 0;
}
//myFunction() - this function sum Shipping value in total
function sumShippingTotal() {
	var convertSubData  =  parseFloat(document.EstimationForm.total.value) +  parseFloat(document.EstimationForm.shipping.value);
	document.EstimationForm.total.value = parseFloat(convertSubData).toFixed(2);
}

/* //this function clear input value, 
function clearDiscountCol(){
	document.InvoiceForm.adjustedtotal.value = 0;
	var convertSubData  =  parseFloat(document.InvoiceForm.total.value) + parseFloat(document.InvoiceForm.balance.value);
	document.InvoiceForm.balance.value = "";
}
//this function for calculat discount amount 
function calDiscountTotal() {
	var convertSubData  =  parseFloat(document.InvoiceForm.total.value) -  parseFloat(document.InvoiceForm.balance.value);
	document.InvoiceForm.adjustedtotal.value = parseFloat(convertSubData).toFixed(2);
} */

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

function Pending_Value(form){
	if(form.isPending.checked==true){
		form.isPending.value="true";
		document.getElementById('pending').style.display="block";
	}
	else{
		form.isPending.value="false";
		document.getElementById('pending').style.display="none";
	}
}
function TaxaValue(form){
	if(form.taxable.checked==true){
		form.taxable.value="on";
	}
	else{
		form.taxable.value="off";
	}
}
function ShippedItem(form){
	if(form.itemShipped.checked==true){
		form.itemShipped.value="true";
	}
	else{
		form.itemShipped.value="false";
	}
}
function Assignment(value, form){
	if(value==0){
		document.EstimationForm.billTo.value="";
		document.EstimationForm.shipTo.value="";
		document.EstimationForm.via.value="0"
		document.EstimationForm.payMethod.value="0";
		document.EstimationForm.rep.value="0";
		document.EstimationForm.term.value="0";
		document.EstimationForm.taxable.checked=false;		
	}
	else{		
		size=document.getElementById("bSize").value;
		shSize=document.getElementById("sSize").value;
		var i;
		for(i=0;i<size;i++){
			var field1 = document.getElementById(i+"id").value;
			if(value==field1){
				document.getElementById('cid').value = value;
				form.companyID.value = document.getElementById(i+"cid").value;
				form.bsAddressID.value = document.getElementById(i+"bsaddr").value;
				form.billTo.value = document.getElementById(i+"bl").value;
				break;		
			}
		}
		for(i=0;i<size;i++){
			var field2 = document.getElementById(i+"sh_id").value;
			if(value==field2){
			    form.shAddressID.value = document.getElementById(i+"shaddr").value;
				form.shipTo.value=document.getElementById(i+"sh").value;
				break;		
			}
		} 
		sz = document.getElementById('custSize').value; 
		
		for(i=0;i<sz;i++){
			var field11 = document.getElementById("a"+i+"clvndid").value;
			if(value==field11){
			    let custBalance = document.getElementById(i+"custBalance").value;
                if(custBalance == "true") document.getElementById("CustomerBalanceBtn").style.backgroundColor = "red";
                else document.getElementById("CustomerBalanceBtn").style.backgroundColor = "#05A9C5";
				viaitem = document.getElementById(i+"va").value;
				if(viaitem==""){
					document.EstimationForm.via.value=0;
				}
				else
					document.EstimationForm.via.value=viaitem;
				repitem=document.getElementById(i+"rp").value;
				if(repitem=="")
					document.EstimationForm.rep.value=0;
				else
					document.EstimationForm.rep.value=repitem;
					payitem = document.getElementById(i+"paym").value;
					if(payitem=="")
						document.EstimationForm.payMethod.value="0";
					else
						document.EstimationForm.payMethod.value=payitem;
					trmitem = document.getElementById(i+"trm").value;
					if(trmitem=="")
						document.EstimationForm.term.value="0";
					else
						document.EstimationForm.term.value=trmitem;
						
					txid=document.getElementById("a"+i+"txable").value;
					if(txid=="1"){
						document.EstimationForm.taxable.checked=true;	
					}
					else{
						document.EstimationForm.taxable.checked=false;	
					}
					break;		
				}
			} 
	}
}
function StyleChange(value){
    document.getElementById('invoiceStyle').value = value;
	document.getElementById('invStyle').value = value;
	size = document.getElementById('CartSize').value;
	hidn_val = document.getElementById('hidn').value;
	if(value==0){
		product();
			/*QTY */
		document.getElementById('td4').style.display='none';
		document.getElementById('td3').style.display='block';		
		document.getElementById('td5').style.display='block';
		/*Serial No */
		document.getElementById('td6').style.display='none';
		document.getElementById('td7').style.display='none';
		/* DESC */	
//		document.getElementById('td8').style.display='block';
//		document.getElementById('td9').style.display='block';
			
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
	if(value!=0){
		document.getElementById('InvStyle').value=value;
		if(value==1){
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
		for(i=0;i<size;i++){
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
		document.getElementById("it2").style.display='visible';
		document.getElementById("it3").style.visibility='visible';
		document.getElementById("it4").style.display='none';
		document.getElementById("it5").style.visibility='hidden';
		document.getElementById("it6").style.visibility='hidden';
		document.getElementById("it7").style.visibility='visible';
		document.getElementById("it8").style.visibility='hidden';
		document.getElementById("it22").style.display='hidden';
		document.getElementById("it42").style.display='block';
					
		for(x=0;x<hidn_val;x++)
				serviceItem(x);
		}
		else if(value==2){
			quick(size);
			for(x=0;x<hidn_val;x++)
				quickItem(x);
		}
		else if(value==3){
			manufacture(size)
			for(x=0;x<hidn_val;x++)
				manufactureItem(x);
		}
		else if(value==4){
			product();
			productTable(size);
			for(x=0;x<hidn_val;x++)
				productItem(x);
		
				/*QTY */
			document.getElementById('td4').style.display='none';
			document.getElementById('td3').style.display='block';		
			document.getElementById('td5').style.display='block';
			/*Serial No */
			document.getElementById('td6').style.display='none';
			document.getElementById('td7').style.display='none';
			/* DESC */	
//			document.getElementById('td8').style.display='block';
//			document.getElementById('td9').style.display='block';
					
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
			else if(value==5){
				finance(size);
				for(x=0;x<hidn_val;x++)
					financeItem(x);
			}
			else if(value==6){
			professional(size);
				for(x=0;x<hidn_val;x++){
					quickItem(x);
				}
			}
		else if(value==7){
			ebusiness(size);
			for(x=0;x<hidn_val;x++){
				ebusinessItem(x);
			}
		}
	}
}
	function service(){
		/* hidden field for ship to */
				document.getElementById('ship_label').style.visibility="hidden";
				document.getElementById('ship_id').style.visibility="hidden";
				
				
				/* hidden field for P.O. Num */
				/*both fields commented on 23-09-2019*/
				//document.getElementById('po_num_label').style.visibility="hidden";
				//document.getElementById('po_num_id').style.visibility="hidden";
				
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
		
		function quick(size){
			/* hidden field for ship to */
				document.getElementById('ship_label').style.visibility="hidden";
				document.getElementById('ship_id').style.visibility="hidden";
				
				/* hidden field for P.O. Num */
				/*both fields commented on 23-09-2019*/
				//document.getElementById('po_num_label').style.visibility="hidden";
				//document.getElementById('po_num_id').style.visibility="hidden";
				
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
				document.getElementById('td2').style.visibility="block";
				
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
				
				/* Item in Table  */
				quickTable(size);
						
		}
		
		function manufacture(size){
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
				for(i=0;i<size;i++){
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
				
					document.getElementById("it22").style.display='none';
					document.getElementById("it42").style.display='none';
					
		}
		
		function product(){
			/* visible field for ship to */
				document.getElementById('ship_label').style.visibility="visible";
				document.getElementById('ship_id').style.visibility="visible";
				
				/* visible field for P.O. Num */
				/*both fields commented on 23-09-2019*/
				//document.getElementById('po_num_label').style.visibility="visible";
				//document.getElementById('po_num_id').style.visibility="visible";
				
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
		
		function productTable(size){
			var i;
			for(i=0;i<size;i++){
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
			document.getElementById("it22").style.display='none';
			document.getElementById("it42").style.display='none';
			
			value=document.getElementById('hidn').value;
			var j;
			for(j=0;j<value;j++){
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
		
		function finance(size){
			service();
				/*QTY */
				document.getElementById('td4').style.display='none';
				document.getElementById('td3').style.display='none';		
				document.getElementById('td5').style.display='none';
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
				document.getElementById('td17').style.display='block';
				document.getElementById('td18').style.display='block';
				
				/* Item in Table  */
				 var i;
				for(i=0;i<size;i++){
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
					
					document.getElementById("it22").style.display='none';
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
					document.getElementById("it22").style.display='none';
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
		function ebusiness(size)
		{
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
				
				
				/* hidden field for P.O. Num */
				/*both fields commented on 23-09-2019*/
				//document.getElementById('po_num_label').style.visibility="hidden";
				//document.getElementById('po_num_id').style.visibility="hidden";
								
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
				
					document.getElementById("it22").style.display='none';
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
			
			document.EstimationForm.tax.value=rate;
			total = ((tot/1) + (subtotal/1)+(rate)/1);
			document.EstimationForm.total.value=total.toFixed(2);
		}
		
		function AddItem(form){
			if(form.itemID.value==0 || form.itemID.value==='01'){
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
				wt = form.weight.value;
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
				
				document.EstimationForm.tax.value=tax_val;
				
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
				td9.setAttribute("colspan",4);
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
							
				hidn_val=( (hidn_val/1) + 1);
				document.getElementById('hidn').value=hidn_val;
   			}	
		}
		
function productItem(hidn_val)
{
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
	document.getElementById("it22").style.display='none';
	document.getElementById("it42").style.display='none';
}
		
function serviceItem(hidn_val)
{
	document.getElementById(hidn_val+"3").style.visibility='hidden';
	document.getElementById(hidn_val+"1").style.visibility='visible';
	document.getElementById(hidn_val+"2").style.visibility='visible';
	document.getElementById(hidn_val+"4").style.visibility='visible';
	document.getElementById(hidn_val+"5").style.visibility='visible';
	document.getElementById(hidn_val+"6").style.visibility='hidden';
	document.getElementById(hidn_val+"7").style.visibility='hidden';
	document.getElementById(hidn_val+"8").style.visibility='visible';
	
	document.getElementById("it1").style.visibility='visible';
	document.getElementById("it2").style.display='block';
	document.getElementById("it3").style.visibility='visible';
	document.getElementById("it4").style.display='none';
	document.getElementById("it5").style.visibility='hidden';
	document.getElementById("it6").style.visibility='hidden';
	document.getElementById("it7").style.visibility='visible';
	document.getElementById("it8").style.visibility='hidden';
	document.getElementById("it22").style.display='none';
	document.getElementById("it42").style.display='block';
}
		
function quickItem(hidn_val)
{
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
	document.getElementById("it22").style.display='none';
	document.getElementById("it42").style.display='none';
}
		
function manufactureItem(hidn_val)
{
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

	document.getElementById("it22").style.display='none';
	document.getElementById("it42").style.display='none';
}
		
function financeItem(hidn_val)
{
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
	
	document.getElementById("it22").style.display='none';
	document.getElementById("it42").style.display='none';
}		
function ebusinessItem(hidn_val)
{
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

	document.getElementById("it22").style.display='none';
	document.getElementById("it42").style.display='none';
}
function ItemChange(value)
{
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
	for(count=0;count<size;count++)
	{
		var invID = document.getElementById(count+'inv').value;
		if(value==invID)
		{
			var category = document.getElementById(count+'cat').value;
			if(category==1)
			{
				//document.getElementById('serialNo_id').value="";
				document.getElementById('qty_id').value="";
				//document.getElementById('desc_id').value="";
				document.getElementById('unitPrice_id').value="";
				document.getElementById('amount_id').value="";
				document.getElementById('weight_id').value="";
				document.getElementById('tax_id').value="";
				
				document.getElementById('qty_id').readonly="true";
				document.getElementById('unitPrice_id').readonly="true";
				document.getElementById('weight_id').readonly="true";
				document.getElementById('code11').value=document.getElementById(count+'code').value;
			}
			else
			{
				var qty = 1;

				//	You can not enter more then your available stock using this function
				var qtyVal = document.getElementById(count+'q').value;
				document.getElementById('qty_id').max = qtyVal;
				var qtyMax = document.getElementById('qty_id').max;
				
				debugger;
				//you can replace eventListner like keyup keypress blur change

				$(".minutesInput").on('keyup', function(e) {
				    var num = parseInt(this.value, 10),
				        min = 1,
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
				//document.getElementById('desc_id').value=document.getElementById(count+'desc').value;
				document.getElementById('pname_id').value=document.getElementById(count+'pname').value;
				document.getElementById('unitPrice_id').value=uprice;
				document.getElementById('amount_id').value=qty*uprice;
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
}
function saveNewUnitPrice(){
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
            "<spring:message code='BzComposer.global.cancel'/>": function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
	
}
function saveNewItemName()
{
	$("#SaveItemName").dialog({
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
            	window.location = "Estimation?tabid=saveUnitPrice&price="+price+"&itemID="+itemId;
            	/* $.ajax({
           			type: "POST",
           			url:"Estimation?tabid=saveUnitPrice&price="+price+"&itemID="+itemId,
           			data:{price : price,itemID:itemId},
           			}).done(function(data){
           			debugger
           			//$(document).find('div#itemDiv').replaceWith($(data).find('div#itemDiv').html());
           			debugger
           			}); */
            },
            "<spring:message code='BzComposer.global.cancel'/>": function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}
function AddTotal(form){
	value=prompt("Enter Adjusted Amount","");
	form.adjustedtotal.value=value;
}
function Init(){
    debugger
	var sortId = '<%= request.getAttribute("sortById")%>';
	isItemExist = document.getElementById('CartSize').value;
	TaxValue1(1);
	document.getElementById('tax_val').value=rate;
    $('select[id="itemID"]').find('option[value="0"]').attr("selected",true);

	var initPending='${InvoiceForm.isPending}';
	if(initPending == 'true'){	
		toggle_visibility('pending');
	}

	<c:if test="${not empty Style}">
		StyleChange(${Style});
	</c:if>
	<c:if test="${empty Style}">
		StyleChange(document.EstimationForm.invoiceStyle.value);
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
		val = document.EstimationForm.taxID.value;
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
	</c:if>
	var readOnly = <%= request.getAttribute("readData") %>

	if(readOnly)
	{

		$('#newEstimation').prop('disabled', true);
		$('#btnNewEstimation').prop('disabled', true);
		$('#btnSaveEstimation').prop('disabled', true);
		$('#btnUpdateCustomer').prop('disabled', true);
		$(':input[type="text"]').prop('disabled', false);
		$('input[type=text],textarea').prop('readonly', true);
		$('#custID').prop('readonly', true);
		$('#invoiceStyle').prop('readonly',true);
	}
	else {

		$('#newEstimation').prop('disabled', false);
		$('#btnNewEstimation').prop('disabled', false);
		$('#btnSaveEstimation').prop('disabled', false);
		$('#btnUpdateCustomer').prop('disabled', false);
		$('input[type=text],textarea').prop('readonly', false);
		$('#custID').prop('readonly', true);
		$('#invoiceStyle').prop('readonly',true);
	}
}

function onSave(form)
{
    debugger;
	No=form.orderNo.value;
	cid = form.custID.value;
	if(cid==0){
		return showValidationDialog();
	}
	else if(isItemExist <=0){
		return showSelectItemDialog();  
	}
	else{
		if(No.length==0 || No==0){
			return showItemOrderNumberDialog();
		}
		else{
			event.preventDefault();
			$("#saveEstimation").dialog({
			    	resizable: false,
			        height: 200,
			        width: 500,
			        modal: true,
			        buttons: {
			            "<spring:message code='BzComposer.global.ok'/>": function () {
			                
			            	$(this).dialog("close");
			            	debugger;
							var rt=0;
							subtotal=form.subtotal.value;
							value = form.taxID.value;
							sze=document.getElementById("tSize").value;
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
							csize = document.getElementById('CartSize').value;
							if(csize!=0)
							{
								val=((csize/1) + (val)/1) ;
								var i;
								ordVal = ( ((document.getElementById('hidn').value)/1) + 1 );
								for(i=0;i<csize;i++){
									rowid=document.getElementById(i+'delt').value
									if(rowid=="del"){
										cnt++;
									}
									else if(rowid=="0"){
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
										}else{
											form.isTaxable.value+="0"+";";
										}
										idV++;
										itemVal=document.getElementById(i+'invID11').value;
										form.item.value+=itemVal+";";
									}
								}
								val=((((val)/1 - (cnt)/1)));
								form.size.value=val;
							}
							document.getElementById('tabid').value="SaveEstimation";
							document.forms['estimationForm'].action="Estimation?tabid=SaveEstimation";
							document.forms['estimationForm'].submit();
					  	},
					  	"<spring:message code='BzComposer.global.cancel'/>": function () {
			                $(this).dialog("close");
			                return false;
			            }
			        }
			    });
			    return false;
		}
	}
}
function NewEstimation(){
	window.location.href="Estimation?tabid=Estimation";
}

function ShowUpdate(form){
	cid=form.custID.value;
	if(cid==0){
		return showValidationDialog();
	}
	else{
		window.open("Customer?tabid=editCustomer&cvId="+cid,null,"scrollbars=yes,height=600,width=1100,status=yes,toolbar=no,menubar=no,location=no" );
	}
}

function paymentHistory(form)
{
	cid=form.custID.value;
	if(cid==0){

		return showValidationDialog();
	}
	else {
		window.open("Estimation?tabid=PaymentHistory&CustId="+cid,null,"scrollbars=yes,height=600,width=800,status=yes,toolbar=no,menubar=no,location=no" );
	}
}

function printCustomerOrder(form){
    let cid = form.custID.value;
    let ttype = form.templateType.value;
    if(cid==0){
        return showValidationDialog();
    } else {
        window.open("Estimation?tabid=PrintEstimation&custID="+cid+"&ttype="+ttype, null, "scrollbars=yes,height=900,width=900,status=yes,toolbar=no,menubar=no,location=no" );
    }
}

function SendMail(form)
{
	cid=form.orderNo.value;
	window.open("Invoice?tabid=ShowEmail&OrderType=estimation&OrderNo="+cid,null,"scrollbars=yes,height=500,width=900,status=yes,toolbar=no,menubar=no,location=no" );
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
							var rt=0;
							document.getElementById(d).style.display='none';
							document.getElementById(jj+'delt').value="del";
							qty1=document.getElementById(jj+'qty').value;
				
							uprice1=document.getElementById(jj+'uprice').value;
				
							amt = ((qty1)/1 * (uprice1)/1);

							wegt=document.getElementById(jj+'weight').value;
							w=document.EstimationForm.weight.value;
							wg=( (w)/1 - (wegt)/1);
							document.EstimationForm.weight.value=wg.toFixed(2);;
					
							subtotal= document.EstimationForm.subtotal.value;
				
							subtotal = ((subtotal/1) - (amt/1));
							valu=document.EstimationForm.taxID.value;
							subtotal=subtotal.toFixed(2);;
							document.EstimationForm.subtotal.value=subtotal;
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
							shipping = document.EstimationForm.shipping.value;
							total = ( (rt/1) + (subtotal/1) + (shipping/1));
							document.EstimationForm.total.value=total.toFixed(2);
							document.EstimationForm.tax.value=rt;
				
							break;
  							}
					}
					},
					"<spring:message code='BzComposer.global.cancel'/>": function () {
		                $(this).dialog("close");
		                return false;
		            }
		        }
		    });
		    return false;
		}

function DeleteRow1(d,form)
{
	event.preventDefault();
	 $("#deleteRowDialog").dialog({
	    	resizable: false,
	        height: 200,
	        width: 350,
	        modal: true,
	        buttons: {
	            "<spring:message code='BzComposer.global.ok'/>": function () {
	            	$(this).dialog("close");
	                
	            	isItemExist--;
	    			document.getElementById('tr'+d).style.display='none';
	    			for(jj=0;jj<=index1;jj++){
	    				if(d==jj){
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
	    						
	    						w=document.EstimationForm.weight.value;
	    						wg=( (w)/1 - (wegt)/1);
	    						document.EstimationForm.weight.value=wg.toFixed(2);
	    						
	    						subtotal= document.EstimationForm.subtotal.value;
	    					
	    						subtotal = ((subtotal/1) - (amt/1));
	    						document.EstimationForm.subtotal.value=subtotal.toFixed(2);
	    						tot=(document.EstimationForm.shipping.value);
	    						tx = document.EstimationForm.tax.value;
	    						if(t==1){
	    							yestax = (yestax - amt).toFixed(2);
	    							tx=(yestax * tax_rate)/100;
	    							document.EstimationForm.tax.value=tx;
	    						}
	    						
	    						
	    						total = ((tot/1) + (subtotal/1) + (tx/1));
	    						document.EstimationForm.total.value=total.toFixed(2);
	    						deleted++;
	    					}
	    				}
	            },
	            "<spring:message code='BzComposer.global.cancel'/>": function () {
	                $(this).dialog("close");
	                return false;
	            }
	        }
	    });
	    return false;
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

function getEstimationDetailsByBtnName(form, url){
    //window.location.href="Estimation?tabid=FirstEstimation";
    $.ajax({
        type : "GET",
        url : "/Estimation?tabid="+url,
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
</script>
<!-- Dialog box which are used in this page. -->
<div id="showSelectItemDialog" style="display:none;">
	<p><spring:message code="BzComposer.estimaion.itemvalidation"/></p>
</div>
<div id="showValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.estimaion.namevalidation" /></p>
</div>
<div id="showItemOrderNumberDialog" style="display:none;">
	<p><spring:message code="BzComposer.estimaion.estimaionnumbernotblankorzero" /></p>
</div>
<div id="saveEstimation" style="display:none;">
	<p><spring:message code="BzComposer.estimaion.saveorupdateestimation" /></p>
</div>
<div id="deleteRowDialog" style="display:none;">
	<p><spring:message code="BzComposer.estimaion.deleteitem" /></p>
</div>