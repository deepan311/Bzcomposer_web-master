<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false"%>
<%@ page errorPage="/WEB-INF/jsp/include/sessionExpired.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<script src="${pageContext.request.contextPath}/styles/js/purchase1.js"></script>

<title>
	<spring:message code="BzComposer.purchaseorderboardtitle" />
</title>
<style type="text/css">
table.cart tbody tr td {
/*  border-bottom: 1px solid rgb(207, 207, 207); */
font-size: 14px;				/*Added on 20-09-2019  */
}
</style>
<script type="text/javascript">
    var funsequence = 0;
	var _1 = navigator.userAgent.toLowerCase();
	var ___ = (_1.indexOf("msie") != -1);
	var ___5 = (_1.indexOf("msie 5") != -1);
	var _io = (_1.indexOf("opera") != -1);
	var _im = (_1.indexOf("mac") != -1);
	var ____gi = (_1.indexOf("gecko") != -1);
	var i____s = (_1.indexOf("safari") != -1);
	var o = null;

	var r = null;
	var flag1=false;
	var flag2=false;

	isItemExist=0;
	var wghtArr = new Array();
	var itemArr = new Array();
	var qtyArr = new Array();
	var upriceArr = new Array();
	var codeArr = new Array();
	var taxArr = new Array();
	var descArr = new Array();
	var uwghtArr = new Array();
	var serialArr = new Array();
	var itmIDArr = new Array();
	var itmOrdArr = new Array();

	isship=0;
	flag=0;
	deleted = 0;
	index1=0;
	cnt=0;
	count=0;
	yestax=0;
function setLastPO() {
	document.PurchaseOrderForm.previousPoNum.value = document.PurchaseOrderForm.orderNo.value;
	setIds();
}

function onSave(form) {
	shipinfo();
	No = form.orderNo.value;
	custnm = trim(document.getElementById('custNm').value);
	cid = form.custID.value;
	if(cid==0){
		return showValidationDialog();
		document.getElementById('custNm').value="";
		document.getElementById('custNm').focus();
	}
	else if(custnm==""){
		return showValidationDialog();
		document.getElementById('custNm').focus();
	}else{
		if(No.length==0 || No==0){
			return showItemOrderNumberDialog();
			form.orderNo.focus();
		}else{
			event.preventDefault();
            $("#updatePurchaseOrderDialog").dialog({
                resizable: false,
                height: 200,
                width: 500,
                modal: true,
                buttons: {
                    "Ok": function () {
                        $(this).dialog("close");

                        total=form.total.value;
                        form.total.value=total;
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
                        if(csize!=0){
                            val=((csize/1) + (val)/1) ;
                            var i;
                            ordVal = ( ((document.getElementById('hidn').value)/1) + 1 );

                            for(i=0;i<csize;i++)
                            {
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

                                    form.serialNo.value += document.getElementById(i+"serial").value+";";

                                    itid = document.getElementById(i+'itId11').value;
                                    form.itemTypeID.value += itid+";";
                                    form.itemOrder.value += idV+";";
                                    valTax = document.getElementById(i+"tax").value;
                                    if(valTax=="Yes"){
                                        form.isTaxable.value+="1"+";";
                                    }else{
                                        form.isTaxable.value+="0"+";";
                                    }
                                    idV++;
                                    itemVal = document.getElementById(i+'invID11').value;
                                    form.item.value += itemVal+";";
                                }
                            }
                            val=((((val)/1 - (cnt)/1)));
                            form.size.value=val;
                        }
                        document.forms['PurchaseOrderForm'].action="PurchaseOrderPost?tabid=SavePurchaseOrder";
                        document.forms['PurchaseOrderForm'].submit();
                    },
                    Cancel: function () {
                        $(this).dialog("close");
                        return false;
                    }
                }
            });
            return false;
		}
	}
}

function shipinfo(){
	/* <c:if test="${Enable}">
		if(document.getElementById('dropship').checked==false){
			isship=2;
		}
	</c:if> */
}
function init() {
	chbox = document.PurchaseOrderForm.company;
	if(chbox.checked){
		document.getElementById('custNm').value = document.PurchaseOrderForm.companyName.value;
		chbox.value="on";
	}
	else{
		document.getElementById('custNm').value = document.PurchaseOrderForm.fullName.value;
		chbox.value="off";
	}
	<c:if test="${Style}">
		StyleChange(${Style});
	</c:if>
	<c:if test="${Style == null}">
		StyleChange(document.PurchaseOrderForm.invoiceStyle.value);
	</c:if>

	<c:if test="${Exists}">
		<c:if test="${Exists == true}">
			event.preventDefault();
			$("#poNumberExist").dialog({
					resizable: false,
					height: 200,
					width: 500,
					modal: true,
					buttons: {
						"Ok": function () {
							$(this).dialog("close");
							PurchaseInfo();
						},
						Cancel: function () {
							$(this).dialog("close");
							document.PurchaseOrderForm.orderNo.value = document.PurchaseOrderForm.previousPoNum.value;
							PurchaseInfo();
						}
					}
				});
				return false;
		</c:if>
		<c:if test="${Exists == false}">
			//document.getElementById('tab').value="NotExist";
			document.forms['PurchaseOrderForm'].action="PurchaseOrder?tabid=NotExist";
			document.forms['PurchaseOrderForm'].submit();
		</c:if>
	</c:if>
	<c:if test="${Flag}">
		setFlag();
	</c:if>
	Assignment(document.PurchaseOrderForm.custID.value, document.PurchaseOrderForm);
}
</script>
</head>
<body onload="init();">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<!-- begin Contents -->
<form:form method="post" id= "PurchaseOrderForm" name= "PurchaseOrderForm" modelAttribute="purchaseOrderDto">
	<input type="hidden" name="isInvoice" value=""/>
	<input type="hidden" name="isSalestype" value=""/>

	<div id="BillShipAddrDetails">
		<input type="hidden" name="BLSize" id="bSize" value='${BillAddr.size()}'/>
		 <c:forEach items="${BillAddr}" var="objList" varStatus="loop">
			<input type="hidden" value='${objList.clientVendorID}' id='${loop.index}clvid' />
			<input type="hidden" value='${objList.billTo}' id='${loop.index}bl' />
			<input type="hidden" value='${CID}' id='${loop.index}cid' />
			<input type="hidden" value='${objList.bsAddressID}' id='${loop.index}bsaddr' />
		</c:forEach>

		<input type="hidden" name="LSize" id="sSize" value='${ShAddr.size()}'/>
		<c:forEach items="${ShAddr}" var="objList" varStatus="loop">
			<input type="hidden" value='${objList.clientVendorID}' id='${loop.index}sh_id' />
			<input type="hidden" value='${objList.shipTo}' id='${loop.index}sh' />
			<input type="hidden" value='${objList.bsAddressID}' id='${loop.index}shaddr' />
        </c:forEach>
	</div>
	<div>
		<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.purchaseorder.purchaseorderheader" />
		</span>
	</div>

	<div id="fullPageDetails" style="margin-left: auto;margin-right: auto;" >
	<section>
	<div id="table-negotiations">
	<div id="ShipAddress">
	    <input type="hidden" name="shipToAddr" id="ship" value="NA" />
		<c:if test="${ShipAddr}">
			<input type="hidden" name="shipToAddr" id="ship" value="${ShipAddr}" />
		</c:if>
	</div>

	<div id="ItemDetails">
		<input type="hidden" name="ItemSize" id="itemSize" value="${ItemList.size()}"/>
		<c:forEach items="${ItemList}" var="objList" varStatus="loop">
            <input type="hidden" value='${objList.invID}' id='${loop.index}inv' name='${loop.index}inv'/>
            <input type="hidden" value='${objList.qty}' id='${loop.index}q' name='${loop.index}q'/>
            <input type="hidden" value='${objList.invCode}' id='${loop.index}code' name='${loop.index}code'/>
            <input type="hidden" value='${objList.invDesc}' id='${loop.index}desc' name='${loop.index}desc' />
            <input type="hidden" value='${objList.inventoryName}' id='${loop.index}pname'/>
            <input type="hidden" value='${objList.salePrice}' id='${loop.index}price' name='${loop.index}price'/>
            <input type="hidden" value='${objList.weight}' id='${loop.index}wt' name='${loop.index}wt'/>
            <input type="hidden" value='${objList.isCategory}' id='${loop.index}cat' name='${loop.index}cat'/>
            <input type="hidden" value='${objList.itemTypeID}' id='${loop.index}itmId' name='${loop.index}itmId'/>
            <input type="hidden" value='${objList.serialNo}' id='${loop.index}serial'/>
        </c:forEach>
	</div>
	<table class="tabla-listados" cellspacing="0" style="margin-top: -1px;">
		<c:if test="${not empty Status}">
			<tr>
				<td colspan="12">
					<span class="msgstyle">*${Status}</span>
				</td>
			</tr>
		</c:if>
		<c:if test="${not empty SaveStatus}">
			<tr>
				<td colspan="12">
					<span class="msgstyle">*${SaveStatus}</span>
					<% session.removeAttribute("SaveStatus"); %>
				</td>
			</tr>
		</c:if>
		<thead>
			<tr>
				<th colspan="12" style="font-size: 14px;">
					<spring:message code="BzComposer.purchaseorder.customerinformation" />
				</th>
			</tr>
		</thead>
			<tr>
				<td colspan="3" align="left">
					<table>
						<tr>
							<td align="left" style="font-size: 14px;" colspan="2">
								<spring:message code="BzComposer.purchaseorder.vendor" />
							</td>
						 	<td style="font-size:14px;" colspan="1">
						 		<spring:message code="BzComposer.purchaseorder.company" />
					 		</td>
						 	<td style="font-size:14px;" colspan="1">
						 		<spring:message code="BzComposer.purchaseorder.taxable" />
					 		</td>
							<td align="center" style="font-size:14px;">
								<!-- <input type="checkbox" id="dropship" name="useDropShip" onclick="DropShipValue(this);">
								    <spring:message code="BzComposer.purchaseorder.usedropshipping" />
								</input> -->
							</td>
						</tr>
						<tr>
							<td style="font-size:14px;" colspan="2">
								<form:select path='custID' onchange="Assignment(this.value, this.form);">
                                    <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                    <form:option value="1111"><spring:message code="BzComposer.invoice.addcustomer"/></form:option>
                                    <c:forEach items="${VendorList}" var="pform">
                                        <form:option value="${pform.getClientVendorID()}">${pform.getCname()} (${pform.getFirstName()} ${pform.getLastName()})</form:option>
                                    </c:forEach>
                                </form:select>
	                   			<input type="hidden" id="custNm" />
							</td>
							<td align="center" style="font-size: 14px;" colspan="1">
								<input type="checkbox" name="company" value="off" onclick="ComapanyValue(this);" ${purchaseOrderDto.company?'checked':''} />
							</td>
							<td align="center" style="font-size: 14px;" colspan="1">
							    <input type="checkbox" name="taxable" onclick="TaxaValue(this.form);" ${purchaseOrderDto.taxable?'checked':''} />
							</td>
							<td align="center">
							<table>
						    <tr>
							    <td align="center" id="droplabel" style="font-size: 14px;" colspan="4">
								    <!-- <spring:message code="BzComposer.purchaseorder.dropshippingto" /> -->
							    </td>
						    </tr>
						    <tr>
							    <td style="font-size: 14px;" align="center" colspan="4">
								    <!-- <select name="venID" id="dropvalue" onchange="Assignment1(this.value,this.form);">
						 			    <option value="0">
										    <spring:message code="BzComposer.ComboBox.Select" />
						 			    </option>
						 			    <c:forEach items="${CDetails}" var="value">
											<option value="${value.value}">${value.label}</option>
                                        </c:forEach>
								    </select>
								    <c:if test="${CustomerName}">
						 			    <script>
										    document.getElementById('dropship').checked=true;
									    </script>
								    </c:if>
					 			    <c:if test="${CustomerName == null}">
									    <script>
					      				    document.getElementById('dropvalue').disabled=true;
										    document.getElementById('dropship').checked=false;
									    </script>
								    </c:if> -->
								    <div id="droplst"></div>
							    </td>
						    </tr>
					        </table>
				            </td>
			            </tr>
					</table>
				</td>
				<td colspan="5" align="center">
					<div>
						<table>
							<tr>
								<td colspan="12" align="center">
									<table style="padding-left: 120px;width: 100%s">
										<tr>
											<td align="right" style="font-size:14px;" colspan="12">
												<button type="button" class="formbutton" onclick="getPODetailsByBtnName(this.form, 'FirstPurchaseOrder');" style="padding: 8px 10px 8px 10px; font-size: 16px;">
													<spring:message code="BzComposer.global.first" />
												</button>
												<button type="button" class="formbutton" onclick="getPODetailsByBtnName(this.form, 'LastPurchaseOrder');" style="padding: 8px 10px 8px 10px; font-size: 16px;">
													<spring:message code="BzComposer.global.last" />
												</button>
												<button type="button" class="formbutton" onclick="getPODetailsByBtnName(this.form, 'PreviousPurchaseOrder');" style="padding: 8px 10px 8px 10px; font-size: 16px;">
													<spring:message code="BzComposer.global.previous" />
												</button>
												<button type="button" class="formbutton" onclick="getPODetailsByBtnName(this.form, 'NextPurchaseOrder');" style="padding: 8px 10px 8px 10px; font-size: 16px;">
													<spring:message code="BzComposer.global.next" />
												</button>
												<button type="button" id="newInvoice" title="New Invoice" class="formbutton" onclick="NewPurchaseOrder1();" style="padding: 8px 10px 8px 10px; font-size: 16px;">
													<spring:message code="BzComposer.global.new" />
												</button>
											</td>
										</tr>
										<tr>
											<td colspan="12" align="center" style="font-size:14px;">
												<c:if test="${Enable}">
													<button type="button" class="formbutton" id="CustomerBalanceBtn" onclick="paymentHistory(this.form);" style="padding: 8px 20px 8px 20px; font-size: 16px;">
														<spring:message code="BzComposer.global.balance" />
													</button>
												</c:if>
												<c:if test="${Enable == null}">
													<button type="button" class="formbutton" id="CustomerBalanceBtn" onclick="paymentHistory(this.form);" style="padding: 8px 20px 8px 20px; font-size: 16px;">
														<spring:message code="BzComposer.global.balance" />
													</button>
												</c:if>

                                                <c:if test="${not empty Enable}">
                                                    <button type="button" class="formbutton" id="sendMailEnabled" onclick="SendMail(this.form);" style="padding: 8px 20px 8px 20px; font-size: 16px;">
                                                        <spring:message code="BzComposer.global.sendmail" />
                                                    </button>
                                                </c:if>
                                                <c:if test="${Enable == null}">
                                                    <button type="button" class="formbutton" id="sendMailDisabled" disabled="true" style="padding: 8px 20px 8px 20px; font-size: 16px;">
                                                        <spring:message code="BzComposer.global.sendmail" />
                                                    </button>
                                                </c:if>
                                                <button type="button" class="formbutton" onclick="printCustomerOrder(this.form);" style="padding: 8px 20px 8px 20px; font-size: 16px;">
                                                    <spring:message code='BzComposer.global.Print' />
                                                </button>
                                            </td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</td>
				<td colspan="3" align="right">
					<table style="width:100%;">
            			<tr>
            				<input type="hidden" name="Ivhidden" id="InvStyle" />
            				<td style="font-size: 14px;">
                                <spring:message code="BzComposer.common.template" />
                            </td>
							<td style="font-size: 14px;">
                                <spring:message code="BzComposer.purchaseorder.orderstyle" />
                            </td>
							<td style="font-size: 14px;">
								<spring:message code="BzComposer.purchaseorder.date" />
							</td>
        	    			<td style="font-size: 14px;">
            					<spring:message code="BzComposer.purchase.PONum" />#
           					</td>
            			</tr>
            			<tr>
            			    <td style="font-size: 14px;">
                                <form:select path="templateType">
                                    <form:option value="7">PO Standard</form:option>
                                    <form:option value="8">PO Charcoal</form:option>
                                </form:select>
                            </td>
							<td style="font-size: 14px;">
								<form:select path="invoiceStyle" onchange="StyleChange(this.value);" onclick="setIds();">
									<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
									<c:forEach items="${InvoiceStyle}" var="obj">
										<form:option value="${obj.value}">${obj.label}</form:option>
									</c:forEach>
                                </form:select>
							</td>
							<td style="font-size: 14px;">
								<form:input type="text" path="orderDate" readonly="true" size="12" />
								<img src="${path.pathvalue}/images/cal.gif" onclick="displayCalendar(document.PurchaseOrderForm.orderDate,'mm-dd-yyyy',this);" onmouseover="setIds();">
							</td>
							<td style="font-size: 14px;">
								<form:input path="orderNo" id="orderNo" onfocus="setLastPO();" ondblclick="SeeInvoice();" readonly="true" style="text-align: right;width:100px;" />
							</td>
            			</tr>
            		</table>
           		</td>
			</tr>
			<%-- <tr>
				<td colspan="12" align="center" style="font-size:14px;">
					<input type="checkbox" id="dropship" name="useDropShip" onclick="DropShipValue(this);">
						<spring:message code="BzComposer.Purchase.PurchaseOrder.UseDropShipping" />
					</input>
				</td>
				<!-- <td>&nbsp;</td> -->
			</tr> --%>
			<tr>
				<%-- <td colspan="5">
					<table>
						<tr>
							<td align="left" style="font-size: 14px;" colspan="2">
								<spring:message code="BzComposer.Purchase.PurchaseOrder.vendor" />
							</td>
						 	<td style="font-size:14px;" colspan="1">
						 		<spring:message code="BzComposer.Purchase.PurchaseOrder.Company" />
					 		</td>
						 	<td style="font-size:14px;" colspan="1">
						 		<spring:message code="BzComposer.Purchase.PurchaseOrder.Taxable" />
					 		</td>
							<td colspan="1"></td>
						</tr>
						<tr>
							<td style="font-size:14px;" colspan="2">
								<form:select path="custID">
									<option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
									<c:forEach items="${VendorList}" var="pform">
                                        <option value="${pform.getLastName()},${pform.getFirstName()}"><c:out value="${pform.getLastName()},${pform.getFirstName()}"/></option>
                                    </c:forEach>
                                </form:select>
	                   			<input type="hidden" id="custNm" />
									<c:if test="${VendorName}">
										<!--<input type="text" size="30" onkeyup="vendorName(this);" -->
										<!--style="width:180px;" id="custNm" -->
										value="${VendorName}"
										<!--ondblclick="vendorName(this);" /> -->
									</c:if>
									<c:if test="${VendorName == null}">
										<!--<input type="text" size="30" onkeyup="vendorName(this);" -->
										<!--style="width:180px;" id="custNm" ondblclick="vendorName(this);" /> -->
									</c:if>
								<input type="hidden" name="custID" />
							<!--<div id="list"></div> -->
							</td>
							<td align="center" style="font-size: 14px;" colspan="1">
								<input type = "checkbox" name="company" value="off" onclick="ComapanyValue(this);">
								</input>
							</td>
							<td align="center" style="font-size: 14px;" colspan="1">
								<input type = "checkbox" name="taxable" onclick="TaxaValue(this.form);">
								</input>
							</td>
							<td colspan="1"></td>
						</tr>
					</table>
				</td> --%>
				<%-- <td colspan="3" align="center">
					<table>
						<tr>
							<td align="center" id="droplabel" style="font-size: 14px;" colspan="4">
								<spring:message code="BzComposer.Purchase.PurchaseOrder.DropShippingTo" />
							</td>
						</tr>
						<tr>
							<td style="font-size: 14px;" align="center" colspan="4">
								<select name="venID" id="dropvalue" onchange="Assignment1(this.value,this.form);">
						 			<option value="0">
										<spring:message code="BzComposer.ComboBox.Select" />
						 			</option>
						 			<c:forEach items="${CDetails}" var="value">
                                             <option value="${value}">${value}</option>
                                    </c:forEach>
								</select>
								<c:if test="${CustomerName}">
							<!--<input type="text" onkeyup="DropList(this);" ondblclick="DropList(this);" size="25" id="dropvalue1"
							value="${CustomerName}"/>-->
						 			<script>
										//document.getElementById('dropship').checked=true;
									</script>
								</c:if>
					 			<c:if test="${CustomerName == null}">
								<!--<input type="text" onkeyup="DropList(this);" ondblclick="DropList(this);" size="20" id="dropvalue1" disabled="disabled" /> -->
									<script>
										//document.PurchaseOrderForm.dropvalue.value
					      				//document.getElementById('dropvalue').disabled=true;
										//document.getElementById('dropship').checked=false;
									</script>
								</c:if>
								<div id="droplst"></div>
							</td>
						</tr>
					</table>
				</td> --%>
            	<%-- <td align="center" colspan="4">
            		<table style="width:100%;">
            			<tr>
            				<input type="hidden" name="Ivhidden" id="InvStyle" />
							<td align="center" style="font-size: 14px;">
								<spring:message code="BzComposer.Orders.OrderStyle" />
							</td>
							<td align="center" style="font-size: 14px;">
								<spring:message code="BzComposer.Purchase.PurchaseOrder.Date" />
							</td>
        	    			<td align="center" style="font-size: 14px;">
            					<spring:message code="BzComposer.Purchase.PurchaseOrder.PurchaseOrder" />
           					</td>
           					<td colspan="1">
           					</td>
            			</tr>
            			<tr>
							<td style="font-size: 14px;" align="center">
								<select name="invoiceStyle" onchange="StyleChange(this.value);" onclick="setIds();">
									<option value="0">
										<spring:message code="BzComposer.ComboBox.Select" />
									</option>
									<c:forEach items="${InvoiceStyle}" var="value">
                                          <option value="${value}">${value}</option>
                                    </c:forEach>
								</select>
							</td>
							<td style="font-size: 14px;" align="center">
								<input type = "text" name="orderDate" readonly="true" size="12" />
								<img src="${path.pathvalue}/images/cal.gif" onclick="displayCalendar(document.PurchaseOrderForm.orderDate,'mm-dd-yyyy',this);"
								onmouseover="setIds();"/>
							</td>
							<td style="font-size: 14px;" align="center">
								<input type ="text" name="orderNo" size="10" onkeypress="return numbersonly(event,this.value);" onfocus="setLastPO();" ondblclick="SeeInvoice();" />
							</td>
							<td colspan="1">
							</td>
            			</tr>
            		</table>
           		</td> --%>
			</tr>
			<tr align="left">
				<td align="center" >
					<table style="width: 100%;">
						<tr>
							<td id="bill_label"></td>
						</tr>
					</table>
				</td>
				<td id="ship_label"></td>
			</tr>
			<tr>
				<td colspan="6">
					<table>
						<tr>
							<td align="left" id="bill_id" style="font-size: 14px;">
								<spring:message code="BzComposer.purchaseorder.vendor" />
								<br>
								<textarea name="billTo" id="billTo" rows="6" cols="35" readonly="true" onclick="BillConfirmAddress(this.form);" onfocus="setIds();"></textarea>
								</input>
							</td>
						</tr>
					</table>
				</td>
				<td colspan="1">
				</td>
				<td align="right"  colspan="5" style="padding-right: 37px;">
					<table>
						<tr>
							<td id="ship_id" style="font-size: 14px;">
								<spring:message code="BzComposer.purchaseorder.shipto" />
								<br/>
								<textarea name="shipTo" id="shipTo" rows="6" cols="35" readonly="true" onclick="ShipConfirmAddress(this.form);" onfocus="setIds();">${shipTo}</textarea>
								</input>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="font-size: 14px;" colspan="10">
					<table align="left" width="400">
						<tr>
							<td align="left" id="term_label" style="font-size: 14px;">
								<spring:message code="BzComposer.purchaseorder.term" />
							</td>
							<td id="pay_label" style="font-size: 14px;">
								<spring:message code="BzComposer.purchaseorder.paymethod" />
							</td>
							<td align="left" id="via_label" style="font-size: 14px;">
								<spring:message code="BzComposer.purchaseorder.via" />
							</td>
						</tr>
						<tr>
							<td id="term_id" style="font-size: 14px;">
								<form:select path="term" onchange="setFlag();" style="width:150px;">
									<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
									<c:forEach items="${Term}" var="objList">
                                        <form:option value="${objList.getTerm()}">${objList.getName()}</form:option>
                                    </c:forEach>
								</form:select>
							</td>
							<td id="pay_id" style="font-size: 14px;">
								<form:select path="payMethod" onchange="setFlag();" style="width:150px;">
                                    <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                    <c:forEach items="${PayMethod}" var="objList">
                                        <form:option value="${objList.getId()}">${objList.getTypeName()}</form:option>
                                    </c:forEach>
								</form:select>
							</td>
							<td id="via_id" style="font-size: 14px;">
								<form:select path="via" onchange="setFlag();" style="width:150px;">
									<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
									<c:forEach items="${Via}" var="objList">
                                           <form:option value="${objList.value}">${objList.label}</form:option>
                                     </c:forEach>
								</form:select>
							</td>
						</tr>
					</table>
				</td>
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
		<%-- <div align="center">
				<font size="3"><strong>
					<spring:message code="BizComposer.Estimaion.Header.ItemInfo" />
				</strong></font>
		</div> --%>
		<div id="product">
			<table class="tabla-listados" cellspacing="0">
				<thead>
					<tr>
						<th colspan="10" style="font-size: 14px;">
							<spring:message code="BzComposer.purchaseorder.iteminformation" />
						</th>
					</tr>
				</thead>
				<tr>
					<td id="td1" style="font-size:14px;">
						<spring:message code="BzComposer.purchaseorder.itemid" />
					</td>
					<td style="font-size: 14px;">
                        <div>
                            <spring:message code="BzComposer.purchaseorder.itemname" />
                        </div>
                    </td>
					<td style="font-size: 14px;">
						<div id="td10" style="display:block;">
							<spring:message code="BzComposer.purchaseorder.unitprice" />
						</div>
						<div id="td11" style="display:none;">
							<spring:message code="BzComposer.purchaseorder.rateprice" />
						</div>
					</td>
					<td style="font-size: 14px;">
                        <div id="td3">
                            <spring:message code="BzComposer.purchaseorder.quantity" />
                        </div>
                        <div id="td4">
                            <spring:message code="BzComposer.purchaseorder.rate" />
                        </div>
                    </td>
					<td style="font-size: 14px;">
						<div id="td6" style="display:none;">
							<spring:message code="BzComposer.purchaseorder.serialnumber" />
						</div>
					</td>
					<td style="font-size: 14px;">
						<div id="td13" style="display:block;">
							<spring:message code="BzComposer.purchaseorder.amount" />
						</div>
					</td>
					<td style="font-size: 14px;">
						<div  id="td15" style="display:block;">
							<spring:message code="BzComposer.purchaseorder.weight" />
						</div>
					</td>
					<td style="font-size: 14px;">
						<div id="td17" style="display:block;">
							<spring:message code="BzComposer.purchaseorder.tax" />
						</div>
					</td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td id="td2" style="font-size:14px;">
						<select name="itemID" onchange="ItemChange(this.value);">
							<option value="0">
								<spring:message code="BzComposer.ComboBox.Select" />
							</option>
							<c:forEach items="${ItemList}" var="itmList">
								<c:if test = "${itmList.isCategory == 0}">
									<option value='${itmList.invID}'>
										&nbsp;&nbsp;&nbsp;&nbsp; ${itmList.invCode}
									</option>
								</c:if>
								<c:if test = "${itmList.isCategory == 1}">
								    <option value='01'>
										${itmList.invCode}
									</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td style="font-size: 14px;width:50%;">
                        <div>
                            <input type="text" readonly="readonly" id="pname_id" style="width:95%;"/>
                        </div>
                    </td>
					<td style="font-size: 14px;">
						<div style="padding-top: 0px;" id="td12">
							<input type="text" size="10"  readonly="readonly" id="unitPrice_id" onkeypress="return numbersOnlyFloat(event,this.value);" />
						</div>
					</td>
					<td style="font-size: 14px;">
                        <div style="padding-top: 0px;" style="display:block;" id="td5">
                            <input type="text" size="10" id="qty_id" onkeydown="return numbersonly(event,this.value)" />
                        </div>
                    </td>
					<td style="font-size: 14px;">
						<div id="td7">
							<input type="text" size="10" id="serialNo_id" readonly="readonly" />
						</div>
					</td>
					<td style="font-size: 14px;">
						<div id="td14" style="display:block;">
							<input type="text" size="10" readonly="true" id="amount_id" onfocus="Multiplication();" onkeypress="return numbersOnlyFloat(event,this.value);" />
						</div>
					</td>
					<td style="font-size: 14px;">
						<div id="td16" style="display:block;">
							<input type="text" name="itemWeight" size="10" readonly="true" id="weight_id" onkeypress="return numbersOnlyFloat(event,this.value);" />
						</div>
					</td>
					<td style="font-size: 14px;">
						<div id="td18" style="display:block;">
							<select id="tax_id">
								<option value="0"><spring:message code="BzComposer.purchaseorder.tax.no"/></option>
								<option value="1"><spring:message code="BzComposer.purchaseorder.tax.yes"/></option>
							</select>
						</div>
					</td>
					<td style="padding-right:5px;font-size:14px;" colspan="3">
						<div>
							<input type="button" class="formbutton" name="addItem" title="To add item click it" onclick="AddItem(this.form);"
							    value='<spring:message code="BzComposer.purchaseorder.additem"/>' style="padding: 8px 20px 8px 20px; font-size: 16px; margin-top: 0;" />
						</div>
					</td>
				</tr>
			</table>
			<table class="tabla-listados cart" cellspacing="0">
				<thead>
					<tr>
						<th style="font-size: 14px;">
							<div id="it1"><spring:message code="BzComposer.purchaseorder.itemid" /></div>
						</th>
						<th style="font-size: 14px;">
                            <div id="it3"><spring:message code="BzComposer.purchaseorder.itemname" /></div>
                        </th>
						<th style="font-size: 14px;">
							<div id="it8"><spring:message code="BzComposer.purchaseorder.serialnumber" /></div>
						</th>
						<th style="font-size: 14px;">
							<div id="it4"><spring:message code="BzComposer.purchaseorder.unitprice" /></div>
							<div id="it42" style="display:none;"><spring:message code="BzComposer.purchaseorder.rateprice" /></div>
						</th>
						<th style="font-size: 14px;">
                            <div id="it2"><spring:message code="BzComposer.purchaseorder.quantity" /></div>
                            <div id="it22" style="display:none;"><spring:message code="BzComposer.purchaseorder.rate" /></div>
                        </th>
						<th style="font-size: 14px;">
							<div id="it5"><spring:message code="BzComposer.purchaseorder.amount" /></div>
						</th>
						<th style="font-size: 14px;">
							<div id="it6"><spring:message code="BzComposer.purchaseorder.weight" /></div>
						</th>
						<th style="font-size: 14px;">
							<div id="it7"><spring:message code="BzComposer.purchaseorder.tax" /></div>
						</th>
						<th style="font-size: 14px;">
							<spring:message code="BzComposer.global.delete" />
						</th>
					</tr>
				</thead>
                <c:if test="${not empty Cart}">
                    <c:forEach items="${Cart}" var="obj" varStatus="indx">
                        <tr id='${indx.index}row'>
                            <td align="left" style="font-size: 14px;">
                                <div id='${indx.index}icode'>${obj.invCode}</div>
                            </td>
                            <td style="font-size: 14px;">
                                <div id='${indx.index}desc'>${obj.invDesc}</div>
                            </td>
                            <td align="left" style="font-size: 14px;">
                                <div id='${indx.index}iserial'>${obj.serialNo}</div>
                            </td>
                            <td align="left" style="font-size: 14px;">
                                <div id='${indx.index}iprice'>${obj.uprice}</div>
                            </td>
                            <td align="left" style="font-size: 14px;">
                                <div id='${indx.index}qt'>${obj.qty}</div>
                            </td>
                            <td align="left" style="font-size: 14px;">
                                <div id='${indx.index}amt'>${obj.amount}</div>
                            </td>
                            <td align="left" style="font-size: 14px;">
                                <div id='${indx.index}wgt'>${obj.weight}</div>
                            </td>
                            <td align="left" style="font-size: 14px;">
                                <div id='${indx.index}itax'>${obj.tax}</div>
                            </td>
                            <td align="left" style="font-size: 14px;" colspan="3">
                                <img src="images/delete.png" onclick="DeleteRow('${indx.index}row',this.form);" title="Delete this Item" size="8" width="12"/>
                            </td>
                        </tr>
                        <input type="hidden" id='${indx.index}delt' value="0" />
                        <input type="hidden" id='${indx.index}rowVal' value='${indx.index}row' />
                        <input type="hidden" id='${indx.index}invCode' value='${obj.invCode}' />
                        <input type="hidden" id='${indx.index}qty' value='${obj.qty}'/>
                        <input type="hidden" id='${indx.index}invDesc' value='${obj.invDesc}' />
                        <input type="hidden" id='${indx.index}uprice' value='${obj.uprice}'/>
                        <input type="hidden" id='${indx.index}weight' value='${obj.weight}'/>
                        <input type="hidden" id='${indx.index}tax' value='${obj.tax}'/>
                        <input type="hidden" id='${indx.index}serial' value='${obj.serialNo}'/>
                        <input type="hidden" id='${indx.index}itId11' value='${obj.itemTypeID}'/>
                        <input type="hidden" id='${indx.index}invID11' value='${obj.inventoryID}'/>
                    </c:forEach>
                    <input type="hidden" name="hdncsize" id="CartSize" value='${Cart.size()}'>
                </c:if>
                <c:if test="${empty Cart}">
					<input type="hidden" name="hdncsize" id="CartSize" value="0"/>
				</c:if>
				<tr id="tr##">
					<td align="center"></td>
					<td align="center"></td>
					<td align="center"></td>
					<td align="center"></td>
					<td align="center"></td>
					<td align="center"></td>
					<td align="center"></td>
					<td align="center"></td>
					<td align="center" colspan="3"></td>
				</tr>
			</table>
		</div>
	</div>
	<table class="tabla-listados" cellspacing="0">
		<thead>
			<tr>
				<th colspan="12" style="font-size: 14px;">
					<spring:message code="BzComposer.purchaseorder.summary" />
				</th>
			</tr>
		</thead>
		<tr>
			<td nowrap="nowrap" style="font-size: 14px;" colspan="6" valign="top">
			    <div>
                    <spring:message code="BzComposer.Invoice.Message" />
                    <form:select path="message" style="width: 60%;">
                        <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                        <c:forEach items="${Message}" var="obj">
                              <form:option value="${obj.value}">${obj.label}</form:option>
                        </c:forEach>
                    </form:select>
				</div>
				<div valign="top">
                    <spring:message code="BzComposer.Invoice.Memo" />
                    &nbsp;&nbsp;&nbsp;
                    <form:textarea path="memo" rows="3" cols="40" style="width: 60%;" />
				</div>
			</td>
			<td style="font-size: 14px;">
				<table align="right">
					<tr>
						<td align="right" style="font-size: 14px;padding-top: 5px;">
							<spring:message code="BzComposer.purchaseorder.totalindollers" />
						</td>
						<td style="font-size: 14px;">
							<form:input type="number" path="total" readonly="true" style="width: 200px;" onkeypress="return numbersOnlyFloat(event,this.value);" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="bsAddressID" />
	<div>
		<input type="hidden" value="0" id="hidn" />
		<input type="hidden" value="" id="code11" />
		<input type="hidden" value="" id="invStyle" />
		<input type="hidden" value="0" id="amt_id" />
		<input type="hidden" value="0" id="tax_val" />
		<input type="hidden" value="0" id="itmId" />
		<input type="hidden" value="0" id="itmVal" />
		<input type="hidden" value="" id="cid" />
		<input type="hidden" value="0" name="wt" />
        <input type="hidden" name="companyID" value="${CID}"/>
		<input type="hidden" name="billAddrValue" />
		<input type="hidden" name="shipAddr"  value="0"/>
	</div>
	<div>
		<input type="hidden" name="size" value="0" />
		<input type="hidden" name="item" value="" />
		<input type="hidden" name="qty" value="" />
		<input type="hidden" name="serialNo" value="" />
		<input type="hidden" name="desc" value="" />
		<input type="hidden" name="pname" value="" />
		<input type="hidden" name="unitWeight" value="" />
		<input type="hidden" name="wgt" value="" />
		<input type="hidden" name="uprice" value="" />
		<input type="hidden" name="code" value="" />
		<input type="hidden" name="isTaxable" value="" />
		<input type="hidden" name="itemTypeID" value="" />
		<input type="hidden" name="itemOrder" value="" />
		<input type="hidden" name="companyName" />
		<input type="hidden" name="fullName" />
		<input type="hidden" name="previousPoNum" />
		<form:hidden path="clientVendorID" />
	</div>
	<!-- end Contents -->
	</section>
	</div>

		<div class="container">
			<div class="row">
				<div class="col-md-12" style="font-size: 16px;" align="center">
				    <input type="button" id="NewPurchaseOrder" class="formbutton" onclick="NewPurchaseOrder();" title="New PurchaseOrder" style="padding: 8px 20px 8px 20px;" value="<spring:message code='BzComposer.global.new' />" />
                    <input type="button" class="formbutton" onclick="onSave(this.form);" title="Save PurchaseOrder" style="padding: 8px 20px 8px 20px;" value="<spring:message code='BzComposer.global.saveUpdate' />" />
                    <input type="button" id="btnUpdateCustomer" class="formbutton" onclick="showEditVendorPage(this.form);" title="Update Customer information" style="padding: 8px 20px 8px 20px;" value="<spring:message code='BzComposer.updatecustomer.updatecustomer' />" />
				</div>
			</div>
		</div>
	</form:form> <!-- end Contents -->
	</div>
	<div>
		<!-- <input type="hidden" name="tabid" id="tab" value="ReceivedItm" /> -->
		<input type="hidden" name="tabid" id="tabid" value="" />
		<input type="hidden" name="DShipValue" id="dsvalue" />
	</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>

<script>


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
	function oGET(oo, url) {
		try {
			oo.open("GET", url, true);
			oo.send(null);
		} catch (ex) {
		}
	}



	function writeSelect()
	{
		if (o.readyState != 4 || o.status != 200){
			return;
		}
		var res=trim(o.responseText);
		document.getElementById("list").innerHTML = res;
	}
	function writeSelectDrop()
	{
		if (o.readyState != 4 || o.status != 200){
			return;
		}
		var res = trim(o.responseText);
		document.getElementById("droplst").innerHTML = res;
	}
	var text="";
	function writeSelectDropValue()
	{
		if (o.readyState != 4 || o.status != 200){
			return;
		}
		text=trim(o.responseText);

		var records=text.split(";");
		var index=0;
		if(index< records.length){
			//document.getElementById('dropvalue').value=records[index];
			index++;
			document.PurchaseOrderForm.shipTo.value=records[index];
			index++;
			document.PurchaseOrderForm.shipAddr.value=records[index];
			index++;
			document.PurchaseOrderForm.clientVendorID.value=records[index];
		}
	}

	var v="";
	function writeSelect11()
	{
		if (o.readyState != 4 || o.status != 200){
			return;
		}
		v=trim(o.responseText);
		split_response(v);
	}

	function split_response(my_colors)
	{
		var col_array=my_colors.split(";");

		var part_num=0;
		if(part_num < col_array.length){

			document.PurchaseOrderForm.fullName.value=col_array[part_num];
			part_num++;
			if(col_array[part_num]=="true")
				document.PurchaseOrderForm.taxable.checked=true;
			else
				document.PurchaseOrderForm.taxable.checked=false;
			part_num++;
			document.PurchaseOrderForm.billTo.value=col_array[part_num];
			part_num++;
			document.PurchaseOrderForm.via.value=col_array[part_num];
			part_num++;
			document.PurchaseOrderForm.payMethod.value=col_array[part_num];
			part_num++;
			document.PurchaseOrderForm.term.value=col_array[part_num];
			part_num++;
			document.PurchaseOrderForm.custID.value=col_array[part_num];
			part_num++;
			document.PurchaseOrderForm.billAddrValue.value=col_array[part_num];
			part_num++;
			document.PurchaseOrderForm.companyName.value=col_array[part_num];

			if(document.PurchaseOrderForm.company.checked){
				document.getElementById('custNm').value=document.PurchaseOrderForm.companyName.value;
			}
			else{
				document.getElementById('custNm').value=document.PurchaseOrderForm.fullName.value;
			}
			//if(document.getElementById('dropship').checked==false){
			//	document.PurchaseOrderForm.shipTo.value=document.shipToAddr.value;
			//	document.PurchaseOrderForm.shipAddr.value=0;
			//}
		}
	}
	var compvalue="";
	function vendorName(obj)
	{
		compvalue=document.PurchaseOrderForm.company.value;
		val=obj.value;
		d= new Date();
		o = c(writeSelect);
		oGET(o,'${pageContext.request.contextPath}/purchase/vendorsInfo.jsp?Name='+ val+"&Company="+compvalue);
		document.getElementById('btnUpdateCustomer').disabled=true;
		document.getElementById("droplst").innerHTML ="";
	}

	function DropList(obj)
	{
		val=obj.value;
		d= new Date();
		o = c(writeSelectDrop);onSave
		oGET(o,'$${pageContext.request.contextPath}/purchase/dropShipList.jsp?Name='+ val);
		document.getElementById("list").innerHTML ="";
	}
	function setDropList(clientid){
		o = c(writeSelectDropValue);
		oGET(o,'${pageContext.request.contextPath}/purchase/dropShipToRecord.jsp?ClientID='+clientid);
		flag=1;
		document.getElementById("droplst").innerHTML ="";

		document.getElementById('btnUpdateCustomer').disabled=true;

	}

	function setTxtval(){
		vendorid = document.getElementById("vendorlist").value;
		o = c(writeSelect11);
		oGET(o,'${pageContext.request.contextPath}/purchase/purchaseOrderRecord.jsp?ClientID='+vendorid+"&Company="+compvalue);
		flag=1;
		document.getElementById("list").innerHTML ="";
		document.getElementById('btnUpdateCustomer').disabled=true;
	}

	function trim(inputString) {
		// Removes leading carriage return from the passed string. Also removes
		// consecutive carriage return.
		var retValue = inputString;
		var ch = retValue.substring(0, 1);
		while (ch == "\n" || ch == "\r" || ch==" " || ch=="\t" ) { // Check for carriage return at the beginning of the string
			retValue = retValue.substring(1, retValue.length);
			ch = retValue.substring(0, 1);
		}
		return retValue;
	}

	function paymentHistory(form){
		cid=form.custID.value;
		if(cid==0){
			return showValidationDialog();
		}
		else{
			window.open("Invoice?tabid=PaymentHistory&Type=PO&CustId="+cid,null,"scrollbars=yes,height=500,width=1300,status=yes,toolbar=no,menubar=no,location=no" );
		}
	}

	function showSelectItemDialog()
	{
		;
		event.preventDefault();
		;
		$("#showSelectItemDialog").dialog({
			resizable: false,
			height: 200,
			width: 300,
			modal: true,
			buttons: {
				"Ok": function () {
					$(this).dialog("close");
				}
			}
		});
		return false;
	}
	function showValidationDialog()
	{
		event.preventDefault();
		$("#showValidationDialog").dialog({
			resizable: false,
			height: 200,
			width: 300,
			modal: true,
			buttons: {
				"Ok": function () {
					$(this).dialog("close");
				}
			}
		});
		return false;
	}

	function showShipInfoValidationDialog()
	{
		;
		event.preventDefault();
		$("#showShipInfoValidationDialog").dialog({
			resizable: false,
			height: 200,
			width: 500,
			modal: true,
			buttons: {
				"Ok": function () {
					$(this).dialog("close");
				}
			}
		});
	}

	function showItemOrderNumberDialog()
	{
		event.preventDefault();
		$("#showItemOrderNumberDialog").dialog({
			resizable: false,
			height: 200,
			width: 300,
			modal: true,
			buttons: {
				"Ok": function () {
					$(this).dialog("close");
				}
			}
		});
		return false;
	}
	function showSavePurchaseOrderDialog()
	{

		event.preventDefault();
		$("#showItemOrderNumberDialog").dialog({
			resizable: false,
			height: 200,
			width: 500,
			modal: true,
			buttons: {
				"Ok": function () {
					$(this).dialog("close");
				},
				Cancel: function () {
					$(this).dialog("close");
					return false;
				}
			}
		});
		return false;
	}

	function ComapanyValue(chbox)
	{
		if(chbox.checked)
		{
			document.getElementById('custNm').value=document.PurchaseOrderForm.companyName.value;
			chbox.value="on";
		}
		else
		{
			document.getElementById('custNm').value=document.PurchaseOrderForm.fullName.value;
			chbox.value="off";
		}
		setIds();
	}

	function DropShipValue(chbox){
		if(chbox.checked){
			//document.getElementById('dropvalue').disabled=false;
			document.getElementById('droplabel').disabled=false;
			isship=1;
		}
		else{

			document.getElementById('droplabel').disabled=true;
			//document.getElementById('dropvalue').value="";
			//document.getElementById('dropvalue').disabled=true;
			document.PurchaseOrderForm.shipTo.value=document.shipToAddr.value;
			document.PurchaseOrderForm.shipAddr.value=0;
		}
		setIds();
	}

	function SeeInvoice(){
		document.getElementById('tab').value="IsPoNumExist";
		document.forms[0].action="PurchaseOrder";
		document.forms[0].submit();
	}

	function StyleChange(value){
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
				document.getElementById('td4').style.display='block';
				document.getElementById('td3').style.display='none';
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
				document.getElementById("it1").style.visibility='visible';
				document.getElementById("it2").style.display='none';
				document.getElementById("it3").style.visibility='visible';
				document.getElementById("it4").style.display='none';
				document.getElementById("it5").style.visibility='hidden';
				document.getElementById("it6").style.visibility='hidden';
				document.getElementById("it7").style.visibility='visible';
				document.getElementById("it8").style.visibility='hidden';
				document.getElementById("it22").style.display='block';
				document.getElementById("it42").style.display='block';

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


		/* hidden field for via & rep */
		document.getElementById('via_label').style.visibility="hidden";
		document.getElementById('via_id').style.visibility="hidden";
		/* Visible field for bill to */
		document.getElementById('bill_label').style.visibility="visible";
		document.getElementById('bill_id').style.visibility="visible";

		/* Visible field for Term & Payment */
		document.getElementById('term_label').style.visibility="visible";
		document.getElementById('term_id').style.visibility="visible";

		/* Visible field for Payment */
		document.getElementById('pay_label').style.visibility="visible";
		document.getElementById('pay_id').style.visibility="visible";
	}

	function quick(size){
		/* hidden field for ship to */
		document.getElementById('ship_label').style.visibility="hidden";
		document.getElementById('ship_id').style.visibility="hidden";

		/* hidden field for via & rep */
		document.getElementById('via_label').style.visibility="hidden";
		document.getElementById('via_id').style.visibility="hidden";

		/* hidden field for bill to */
		document.getElementById('bill_label').style.visibility="hidden";
		document.getElementById('bill_id').style.visibility="hidden";


		/* Visible field for Term & Payment */
		document.getElementById('term_label').style.visibility="hidden";
		document.getElementById('term_id').style.visibility="hidden";

		/* Visible field for Payment */
		document.getElementById('pay_label').style.visibility="hidden";
		document.getElementById('pay_id').style.visibility="hidden";

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

		/* Visible field for bill to */
		document.getElementById('bill_label').style.visibility="visible";
		document.getElementById('bill_id').style.visibility="visible";

		/* visible field for via & rep */
		document.getElementById('via_label').style.visibility="visible";
		document.getElementById('via_id').style.visibility="visible";

		/* Visible field for Term & Payment */
		document.getElementById('term_label').style.visibility="visible";
		document.getElementById('term_id').style.visibility="visible";

		/* Visible field for Payment */
		document.getElementById('pay_label').style.visibility="visible";
		document.getElementById('pay_id').style.visibility="visible";

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
	function ebusiness(size){
		/* visible field for ship to */
		document.getElementById('ship_label').style.visibility="visible";
		document.getElementById('ship_id').style.visibility="visible";

		/* visible field for via & rep */
		document.getElementById('via_label').style.visibility="visible";
		document.getElementById('via_id').style.visibility="visible";

		/* Visible field for Term & Payment */
		document.getElementById('term_label').style.visibility="visible";
		document.getElementById('term_id').style.visibility="visible";

		/* Visible field for Payment */
		document.getElementById('pay_label').style.visibility="visible";
		document.getElementById('pay_id').style.visibility="visible";

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

	function AddItem(form){
		if(form.itemID.value==0 || form.itemID.value==='01'){
			document.getElementById('serialNo_id').value="";
			document.getElementById('qty_id').value="";
			//	document.getElementById('desc_id').value="";
			document.getElementById('unitPrice_id').value="";
			document.getElementById('amount_id').value="";
			document.getElementById('weight_id').value="";
			document.getElementById('pname_id').value="";

			return showSelectItemDialog();
			form.itemID.focus();
		}
		else{
			isItemExist++;
			flag=1;
			style = document.getElementById('invStyle').value;
			hidn_val= document.getElementById('hidn').value;
			var tr = document.createElement("tr");
			tr.setAttribute("id", "tr"+hidn_val);

			var tr2 = document.getElementById('tr##');
			var parentTr = tr2.parentNode;
			parentTr.insertBefore(tr, tr2);

			serialNo = document.getElementById('serialNo_id').value;
			var desc = document.getElementById('pname_id').value;
			if(document.getElementById('weight_id').value==""){
				document.getElementById('weight_id').value = 0;
			}
			weight = parseInt(document.getElementById('weight_id').value);
			tax = document.getElementById('tax_id').value;
			ivcode = document.getElementById('code11').value;

			if(document.getElementById('qty_id').value==""){
				document.getElementById('qty_id').value = 1;
			}
			qty=parseInt(document.getElementById('qty_id').value);
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

			total= form.total.value;
			total = ((total/1) + (amt/1)).toFixed(2);;
			form.total.value=total;

			document.getElementById('amt_id').value=total;

			var td8 = document.createElement("td");
			td8.setAttribute("align", "left");
			td8.setAttribute("id",hidn_val+"8");
			tr.appendChild(td8);
			td8.innerHTML=t;

			var button='<img onclick="DeleteRow1('+hidn_val+',this.form);" width="12"  src="${pageContext.request.contextPath}/images/delete.png" title="Delete" size="8"/>';

			var td9 = document.createElement("td");
			td9.setAttribute("align", "left");
			td9.setAttribute("colspan","3")
			tr.appendChild(td9);
			td9.innerHTML=button;


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

	function Assignment(custID, form){
	    document.PurchaseOrderForm.clientVendorID.value = custID;
		if(custID==0){
			form.billTo.value="";
			form.shipTo.value="";
		}
		else if(custID==1111){
            window.location.href = "Customer?tabid=NewCustomer";
        } else{
			form.custNm.value = custID;
			size = document.getElementById("bSize").value;
			shsize = document.getElementById("sSize").value;
			for(let i=0;i<size;i++){
				var field1 = document.getElementById(i+"clvid").value;
				if(custID == field1){
					document.getElementById('cid').value = custID;
					//form.companyID.value = document.getElementById(i+"cid").value;
					form.bsAddressID.value = document.getElementById(i+"bsaddr").value;
					form.billAddrValue.value = document.getElementById(i+"bsaddr").value;
					form.billTo.value = document.getElementById(i+"bl").value;
					break;
				}
			}
			for(i=0;i<shsize;i++){
                var field2 = document.getElementById(i+"sh_id").value;
                if(custID==field2){
                    form.bsAddressID.value = document.getElementById(i+"shaddr").value;
                    form.shipTo.value = document.getElementById(i+"sh").value;
                    break;
                }
            }
		}
	}

	function Assignment1(custID, form){
		if(custID==0){
			form.shipTo.value="";
		}
		else{
			shsize=document.getElementById("sSize").value;
			var i;
			for(i=0;i<shsize;i++){
				var field2 = document.getElementById(i+"sh_id").value;
				if(custID==field2){
					form.shipTo.value = document.getElementById(i+"sh").value;
					form.shipAddr.value = document.getElementById(i+"sh_id").value; //set Shipping Address Id
					break;
				}
			}
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
		document.getElementById("it2").style.display='none';
		document.getElementById("it3").style.visibility='visible';
		document.getElementById("it4").style.display='none';
		document.getElementById("it5").style.visibility='hidden';
		document.getElementById("it6").style.visibility='hidden';
		document.getElementById("it7").style.visibility='visible';
		document.getElementById("it8").style.visibility='hidden';
		document.getElementById("it22").style.display='block';
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

		document.getElementById("it22").style.display='none';
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

		document.getElementById("it22").style.display='none';
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
		setFlag();
		for(count=0;count<size;count++){
			var invID = document.getElementById(count+'inv').value;
			if(value==invID){
				var category = document.getElementById(count+'cat').value;
				if(category==1){
					//document.getElementById('serialNo_id').value="";
					document.getElementById('qty_id').value="";

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
					var qty=1;
					var uprice = document.getElementById(count+'price').value;
					var serialNo = document.getElementById(count+'serial').value;
					document.getElementById('serialNo_id').readonly="false";
					document.getElementById('qty_id').readonly="false";
					document.getElementById('unitPrice_id').readonly="false";
					document.getElementById('weight_id').readonly="false";

					document.getElementById('serialNo_id').value=serialNo;
					document.getElementById('qty_id').value=qty;
// 						document.getElementById('desc_id').value=document.getElementById(count+'desc').value;
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

	function SendMail(form){
        cid=form.orderNo.value;
        window.open("Invoice?tabid=ShowEmail&OrderType=PO&OrderNo="+cid,null,"scrollbars=yes,height=500,width=900,status=yes,toolbar=no,menubar=no,location=no" );
    }

	function DeleteRow(d,form)
	{
		event.preventDefault();
		$("#deleteRowDialog").dialog({
			resizable: false,
			height: 200,
			width: 400,
			modal: true,
			buttons: {
				"Ok": function () {
					;
					$(this).dialog("close");
					flag=1;
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
							total= document.PurchaseOrderForm.total.value;
							total = ((total/1) - (amt/1));
							total=total.toFixed(2);;
							document.PurchaseOrderForm.total.value=total;
							break;
						}
					}
				},
				Cancel: function () {
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
			width: 400,
			modal: true,
			buttons: {
				"Ok": function ()
				{
					;
					$(this).dialog("close");

					flag=1;
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
							total= document.PurchaseOrderForm.total.value;
							total = ((total/1) - (amt/1));
							document.PurchaseOrderForm.total.value=total.toFixed(2);
							deleted++;
						}
					}
				},
				Cancel: function ()
				{
					$(this).dialog("close");
					return false;
				}
			}
		});
		return false;
	}
	/*Code for buttons*/

	function NewPurchaseOrder(){
        if(flag==1){
			//if(window.confirm('<spring:message code="BizComposer.PurchaseOrder.SaveConfirm" />'))
			if(showSavePurchaseOrderDialog()){
				SaveConfirm(form);
			}else{
				window.location.href="PurchaseOrder?tabid=PurchaseOrder";
			}
		}else{
			window.location.href="PurchaseOrder?tabid=PurchaseOrder";
		}
	}

	function NewPurchase(){
		window.location.href="PurchaseOrder?tabid=PurchaseOrder";
	}

	function trim(inputString){
		// Removes the spaces  return from the passed string.
		var retValue = inputString;
		var ch = retValue.substring(0, 1);
		while (ch == "\n" || ch == "\r" || ch==" " || ch=="\t" ){
			retValue = retValue.substring(1, retValue.length);
			ch = retValue.substring(0, 1);
		}
		return retValue;
	}

	function PurchaseInfo(){
		document.getElementById('tab').value="InvoiceData";
		document.forms[0].action="PurchaseOrder";
		document.forms[0].submit();
	}

	function DropShipInvoice(){
		return dropShipInvoiceDialog();
		event.preventDefault();
		$("#dropShipInvoiceDialog").dialog({
			resizable: false,
			height: 200,
			width: 500,
			modal: true,
			buttons: {
				"Ok": function () {
					$(this).dialog("close");
					document.getElementById('dsvalue').value=document.PurchaseOrderForm.orderNo.value;
					document.getElementById('tab').value="DropShipInvoice";
					document.forms[0].action="Invoice";
					document.forms[0].submit();
				},
				Cancel: function () {
					$(this).dialog("close");
					NewPurchase();
					return false;
				}
			}
		});
		return false;
	}

	function TaxaValue(form){
		if(form.taxable.checked==true){
			form.taxable.value="on";
		} else {
			form.taxable.value="off";
		}
		setFlag();
	}

	function Multiplication(){
		if(document.getElementById('qty_id').value==""){
			document.getElementById('qty_id').value=1;
		}
		qty=parseInt(document.getElementById('qty_id').value);
		var uprice = document.getElementById('unitPrice_id').value;

		var amount=qty*uprice;
		document.getElementById('amount_id').value=amount.toFixed(2);;
	}

	function SaveConfirm(form)
	{
		shipinfo();
		No=form.orderNo.value;
		custnm=trim(document.getElementById('custNm').value);
		cid=form.custID.value;
		if(isship==2)
		{

			return showShipInfoValidationDialog();
		}
		else if(cid==0)
		{

			return showValidationDialog();
			document.getElementById('custNm').value="";
			document.getElementById('custNm').focus();
		}
		else if(custnm=="")
		{

			return showValidationDialog();
			document.getElementById('custNm').focus();
		}
		else
		{
			if(No.length==0 || No==0)
			{
				return showItemOrderNumberDialog();
				form.orderNo.focus();
			}
			else
			{
				event.preventDefault();
				$("#savePurchaseOrderDialog").dialog({
					resizable: false,
					height: 200,
					width: 500,
					modal: true,
					buttons: {
						"Ok": function () {

							$(this).dialog("close");
							total=form.total.value;
							form.total.value=total;
							val1 = document.getElementById('hidn').value;
							val =((val1)/1 - (deleted)/1);
							form.size.value=val/1;
							var x;
							var idV=0;
							for(x=0;x<val1;x++)
							{
								value = itemArr[x];
								if(value!=-1)
								{
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
									if(rowid=="del")
									{
										cnt++;
									}
									else if(rowid=="0")
									{
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
										if(valTax=="Yes")
										{
											form.isTaxable.value+="1"+";";
										}
										else
										{
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
							document.getElementById('tab').value="SavePurchaseOrder";
							document.forms[0].action="PurchaseOrder";
							document.forms[0].submit();
						},
						Cancel: function () {
							$(this).dialog("close");
							return false;
						}
					}
				});
				return false;
			}
		}
	}

function setFlag(){
    flag=1;
    setIds();
}

function setIds(){
    //document.getElementById("droplst").innerHTML ="";
    //document.getElementById("list").innerHTML ="";
}

function showEditVendorPage(form){
    cid = form.custID.value;
    if(cid==0){
        return showValidationDialog();
    }
    else{
        window.open("Vendor?tabid=editVendor&cvId="+cid,null,"scrollbars=yes,height=800,width=1300,status=yes,toolbar=no,menubar=no,location=no" );
    }
}

function BillConfirmAddress(form){
    let custID = form.custID.value;
    let addressID = form.bsAddressID.value;
    if(custID != 0){
        window.open("PurchaseOrder?tabid=AddressConfirm&CType=bill&addressID="+addressID, null,"scrollbars=yes,height=600,width=600,status=no,toolbar=no,menubar=no,location=no");
    }
}
function ShipConfirmAddress(form){
    let custID = form.custID.value;
    let addressID = form.bsAddressID.value;
    if(custID != 0){
        window.open("PurchaseOrder?tabid=AddressConfirm&CType=ship&addressID="+addressID, null,"scrollbars=yes,height=600,width=600,status=yes,toolbar=no,menubar=no,location=no");
    }
}

function printCustomerOrder(form){
    let cid = form.custID.value;
    let ttype = form.templateType.value;
    if(cid==0){
        return showValidationDialog();
    } else {
        window.open("PurchaseOrder?tabid=PrintPO&custID="+cid+"&ttype="+ttype, null, "scrollbars=yes,height=900,width=900,status=yes,toolbar=no,menubar=no,location=no" );
    }
}

function NewPurchaseOrder1(){
	window.location.href = "PurchaseOrder?tabid=PurchaseOrder";
}
function getPODetailsByBtnName(form, url){
	//window.location.href = "PurchaseOrder?tabid=FirstPurchaseOrder";
	$.ajax({
        type : "GET",
        url : "/PurchaseOrder?tabid="+url,
        success : function(data) {
            $(document).find('div#fullPageDetails section').replaceWith($(data).find('div#fullPageDetails').html());
            let cvID2 = form.clientVendorID.value;
            if(cvID2!=null && cvID2!=''){
                form.custID.value = cvID2;
            }
            init();
        },
        error : function(data) {
            alert("<bean:message key='BzComposer.categorydetail.erroroccurred'/>");
        }
    });
}
</script>
</body>
</html>
<!-- dialog box that used in this page -->
<div id="showSelectItemDialog" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorder.selectitemfirst"/></p>
</div>
<div id="showValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorder.selectcustomervalidation" /></p>
</div>
<div id="showShipInfoValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorder.shippinginformationvalidation" /></p>
</div>
<div id="showItemOrderNumberDialog" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorder.ponumvalidation" /></p>
</div>
<div id="savePurchaseOrderDialog" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorder.updatepo" /></p>
</div>
<div id="deleteRowDialog" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorder.deleteitem" /></p>
</div>
<div id="showSavePurchaseOrderDialog" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorder.savechangedinformation" /></p>
</div>
<div id="updatePurchaseOrderDialog" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorder.savepurchaseorder" /></p>
</div>
<div id="dropShipInvoiceDialog" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorder.createinvoicefordropshippingvalidation" /></p>
</div>
<div id="poNumberExist" style="display:none;">
	<p><spring:message code="BzComposer.purchaseorder.pohavesamenumber" />
	\n <spring:message code="BzComposer.purchaseorder.seetheinvoice" /></p>
</div>