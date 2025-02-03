<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu.js"></script>
	<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu_add.js"></script>

	<%@include file="/include/header.jsp"%>
	<title><spring:message code="BizComposer.ItemDetails.Title"/></title>
</head>
<body>
<form:form action="UpdateItem" method="post" id="itemdetailsfrm" name="itemdetailsfrm" enctype="MULTIPART/FORM-DATA" modelAttribute="itemDto">
	<div id="cos">
		<div class="statusquo ok">
			<div id="hoja">
				<div id="blanquito">
					<div id="padding"><form:errors /> <!-- begin Contents -->
						<div>
						    <span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
						        <c:if test="${not empty SaveStatus}">${SaveStatus}</c:if>
						    </span>
						</div>
						<div>
							<div id="table-negotiations" style="overflow:auto;width:95%">
								<table class="tabla-listados" cellspacing="0">
									<thead>
									<tr>
										<th colspan="2"><spring:message code="BzComposer.Item.Edittem" /></th>
									</tr>
									</thead>
                                    <input type="hidden" id="inId" value="<%= request.getParameter("InvId")%>" />
                                    <input type="hidden" name="itmType" id="itmType" value='${itemDto.itemType}'/>
                                    <tr>
                                        <td width="100" align="left">
                                            <form:select path="itemType" disabled="true" size="20" >
                                                <option value="1"><spring:message code="BzComposer.additem.product" /></option>
                                                <option value="2"><spring:message code="BzComposer.additem.service" /></option>
                                                <option value="3"><spring:message code="BzComposer.additem.recurringservice"/></option>
                                                <option value="4"><spring:message code="BzComposer.additem.giftcertificate"/></option>
                                                <option value="5"><spring:message code="BzComposer.additem.inventoryassembly"/></option>
                                            </form:select>
                                        </td>
                                        <td>
                                            <table>
                                                <tbody>
                                                <c:if test="${itemDto.itemType == 1}">
                                                    <tr>
                                                        <td><input type="hidden" name="InvId" id="inId" value='${itemDto.inventoryId}'>
                                                            <spring:message code="BzComposer.Item.InventryName" />
                                                        </td>
                                                        <td>
                                                            <form:input type="text" path="itemCode" value="${itemDto.itemCode}" />
                                                        </td>
                                                        <td align="right"><spring:message code="BzComposer.Item.Subcategoryof" /></td>
                                                        <td>
                                                            <form:select path="tectcmd" disabled="disabled">
                                                            <option value="0"><spring:message code="BzComposer.ComboBox.Select"/></option>
                                                            <c:forEach items="${fillList}" var="obj">
                                                                <option value="${obj.value}">${obj.label}</option>
                                                            </c:forEach>
                                                            </form:select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2"></td>
                                                        <td align="right">
                                                            <c:if test="${not empty ISCategory}">
                                                                <div style="display:none">
                                                                    <spring:message code="BzComposer.Item.AddPicture" />
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${empty ISCategory}">
                                                                <div style="display:block">
                                                                    <spring:message code="BzComposer.Item.AddPicture" />
                                                                </div>
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <c:if test="${not empty ISCategory}">
                                                                <div style="display:none">
                                                                    <input type="file" name="attachFile" />
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${empty ISCategory}">
                                                                <div style="display:block">
                                                                    <input type="file" name="attachFile" />
                                                                </div>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <c:if test="${not empty ISCategory}">
                                                                <input type="checkbox" id="chk_cat" checked="true" onclick="hide_other(this.value);" disabled="disabled"/>
                                                                <spring:message code="BzComposer.Item.isCategory" />
                                                                <form:input type="hidden" path="iscategory" value="on" />
                                                            </c:if>
                                                            <c:if test="${empty ISCategory}">
                                                                <input type="checkbox" id="chk_cat" onclick="hide_other(this.value);" disabled="disabled"/>
                                                                <spring:message code="BzComposer.Item.isCategory" />
                                                                <form:input type="hidden" path="iscategory" value="off" />
                                                            </c:if>
                                                        </td>
                                                        <td align="right"><spring:message code="BzComposer.Item.Barcode" /></td>
                                                        <td><form:input type="text" path="barcode" onkeydown="return numbersonly(event,this.value)" /></td>
                                                        <td align="right">
                                                            <spring:message code="BzComposer.Item.Discontinued" />
                                                            <c:if test="${not empty itemDto.discontinued == true}">
                                                                <input type="checkbox" name="discontinued" checked="checked" />
                                                            </c:if>
                                                            <c:if test="${empty itemDto.discontinued == true}">
                                                                <input type="checkbox" name="discontinued" />
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <c:if test="${not empty ISCategory}">
                                                                <table style="visibility:hidden" >
                                                                    <tr>
                                                                        <th><spring:message code="BzComposer.Item.InventoryInformation" /></th>
                                                                    </tr>
                                                                    <tr>
                                                                        <td colspan="4">
                                                                            <c:if test="${itemDto.taxable == true}">
                                                                                <input type="checkbox" name="taxable" checked="checked" />
                                                                            </c:if>
                                                                            <c:if test="${itemDto.taxable == false}">
                                                                                <input type="checkbox" name="taxable" />
                                                                            </c:if>
                                                                            <spring:message code="BzComposer.Item.Taxable" />
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><spring:message code="BzComposer.Item.InventoryTitle" /></td>
                                                                        <td colspan="3"><form:input type="text" path="itemName" style="width:100%;" /></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><spring:message code="BzComposer.Item.Description" /></td>
                                                                        <td colspan="3">
                                                                            <form:textarea path="invTitle" rows="4" cols="35" />
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><spring:message code="BzComposer.Item.SalePrice" /></td>
                                                                        <td><spring:message code="BzComposer.Item.PurchasePrice" /></td>
                                                                        <td><spring:message code="BzComposer.Item.QtyonHand" /></td>
                                                                        <td><spring:message code="BzComposer.Item.Weight" /></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><form:input type="text" path="salePrice" onkeydown="return numbersonly(event,this.value)" /></td>
                                                                        <td><form:input type="text" path="purchasePrice" onkeydown="return numbersonly(event,this.value)" /></td>
                                                                        <td><form:input type="text" path="qty" onkeydown="return numbersonly(event,this.value)" /></td>
                                                                        <td><form:input type="text" path="weight" size="8" onkeydown="return numbersonly(event,this.value)" /></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><spring:message code="BzComposer.Item.Location" /></td>
                                                                        <td><spring:message code="BzComposer.Item.Serial" /></td>
                                                                        <td colspan="2"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><form:input type="text" path="location" /></td>
                                                                        <td><form:input type="text" path="serialNum" onkeydown="return numbersonly(event,this.value)" /></td>
                                                                        <td colspan="2"></td>
                                                                    </tr>
                                                                </table>
                                                            </c:if>
                                                            <c:if test="${empty ISCategory}">
                                                                <table style="visibility:visible">
                                                                    <tr>
                                                                        <th><spring:message code="BzComposer.Item.InventoryInformation" /></th>
                                                                    </tr>
                                                                    <tr>
                                                                        <td colspan="4">
                                                                            <c:if test="${itemDto.taxable == '1'}">
                                                                                <input type="checkbox" name="taxable" checked="checked" />
                                                                            </c:if>
                                                                            <c:if test="${itemDto.taxable == '0'}">
                                                                                <input type="checkbox" name="taxable" />
                                                                            </c:if>
                                                                            <spring:message code="BzComposer.Item.Taxable" /></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><spring:message code="BzComposer.Item.InventoryTitle" /></td>
                                                                        <td colspan="3"><form:input type="text" path="itemName" style="width:100%;" /></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><spring:message code="BzComposer.Item.Description" /></td>
                                                                        <td colspan="4"><form:textarea path="invTitle" rows="4" cols="35" /></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><spring:message code="BzComposer.Item.SalePrice" /></td>
                                                                        <td><spring:message code="BzComposer.Item.PurchasePrice" /></td>
                                                                        <td><spring:message code="BzComposer.Item.QtyonHand" /></td>
                                                                        <td><spring:message code="BzComposer.Item.Weight" /></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><form:input type="text" path="salePrice" onkeydown="return numbersonly(event,this.value)" /></td>
                                                                        <td><form:input type="text" path="purchasePrice" onkeydown="return numbersonly(event,this.value)" /></td>
                                                                        <td><form:input type="text" path="qty" onkeydown="return numbersonly(event,this.value)" /></td>
                                                                        <td><form:input type="text" path="weight" size="8" onkeydown="return numbersonly(event,this.value)" /></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><spring:message code="BzComposer.Item.Location" /></td>
                                                                        <td><spring:message code="BzComposer.Item.Serial" /></td>
                                                                        <td colspan="2"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><form:input type="text" path="location" /></td>
                                                                        <td><form:input type="text" path="serialNum" onkeydown="return numbersonly(event,this.value)" /></td>
                                                                        <td colspan="2"></td>
                                                                    </tr>
                                                                </table>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td colspan="4">
                                                            <table>
                                                                <tr>
                                                                    <td>
                                                                        <input type="button" class="formbutton" onclick="updateItem();" value='<spring:message code="BzComposer.global.update" />' />
                                                                    </td>
                                                                    <td>
                                                                        <input type="button" class="formbutton" value='<spring:message code="BzComposer.Item.Clear" />' />
                                                                    </td>
                                                                    <td>
                                                                        <input type="button" class="formbutton" onclick="CloseMe();" value='<spring:message code="BzComposer.Item.Close"  />' />
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                </tbody>
                                                <c:if test="${itemDto.itemType == 2}">
                                                    <tr>
                                                        <td>
                                                            <spring:message code="BzComposer.Item.DiscountName" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><form:input type="text" path="itemCode" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="BzComposer.Item.AmountofDiscount" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td><form:input type="text" path="salePrice" onkeydown="return numbersonly(event,this.value)" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="BzComposer.Item.DiscountTitle" /></td>
                                                    <tr>
                                                        <td><form:input type="text" path="invTitle" /></td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td colspan="4">
                                                            <input type="button" onclick="updateItem();" value='<spring:message code="BzComposer.global.update" />' />
                                                            <input type="button" onclick="ClearMe();" value='<spring:message code="BzComposer.Item.Clear" />' />
                                                            <input type="button" onclick="CloseMe();" value='<spring:message code="BzComposer.Item.Close" />' />
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${itemDto.itemType == 3}">
                                                    <tr>
                                                        <td><spring:message code="BzComposer.Item.SubtotalName" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td><form:input type="text" path="itemCode" /></td>
                                                        <td><input type="hidden" value='${itemDto.itemCode}' id="$$3" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="BzComposer.Item.Description" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td><form:input type="text" path="itemName" /></td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td colspan="4">
                                                            <input type="button" class="formbutton" onclick="updateItem();" value='<spring:message code="BzComposer.global.update" />' />
                                                            <input type="button" class="formbutton" onclick="ClearMe();" value='<spring:message code="BzComposer.Item.Clear" />' />
                                                            <input type="button" class="formbutton" onclick="CloseMe();" value='<spring:message code="BzComposer.Item.Close" />' />
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${itemDto.itemType == 4}">
                                                    <tr>
                                                        <td><spring:message code="BzComposer.Item.ServiceName" /></td>
                                                        <td><spring:message code="BzComposer.Item.Subcategoryof" /></td>
                                                        <td><form:select path="tectcmd" disabled="disabled">
                                                            <option value="0"><spring:message code="BzComposer.ComboBox.Select"/></option>
                                                            <c:forEach items="${fillList}" var="obj">
                                                                <option value="${obj.value}">${obj.label}</option>
                                                            </c:forEach>
                                                        </form:select></td>
                                                    </tr>
                                                    <tr>
                                                        <td><form:input type="text" path="itemCode" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <c:if test="${not empty ISCategory}">
                                                                <input type="checkbox" id="chk_cat" checked="true" onclick="hide_other(this.value);" disabled="disabled"/>
                                                                <spring:message code="BzComposer.Item.isCategory" />
                                                                <form:input type="hidden" path="iscategory" value="on" />
                                                            </c:if>>
                                                            <c:if test="${empty ISCategory}">
                                                                <input type="checkbox" id="chk_cat" onclick="hide_other(this.value);" disabled="disabled"/>
                                                                <spring:message code="BzComposer.Item.isCategory" />
                                                                <form:input type="hidden" path="iscategory" value="off"/>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <c:if test="${not empty ISCategory}">
                                                                <div style="visibility:hidden">
                                                                    <table>
                                                                        <tr>
                                                                            <th><spring:message code="BzComposer.Item.InventoryInformation" /></th>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <c:if test="${itemDto.taxable == true}">
                                                                                    <input type="checkbox" name="taxable" checked="checked" />
                                                                                </c:if>
                                                                                <c:if test="${itemDto.taxable == false}">
                                                                                    <input type="checkbox" name="taxable" />
                                                                                </c:if>
                                                                                <spring:message code="BzComposer.Item.Taxable" /></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="4">
                                                                                <spring:message code="BzComposer.Item.ServiceTitle" />
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="4">
                                                                                <form:input type="text" path="itemName" />
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="4">
                                                                                <spring:message code="BzComposer.Item.ServiceDescription" />
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="4">
                                                                                <form:input type="text" path="invTitle" />
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <spring:message code="BzComposer.Item.ServiceRate" />
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <form:input type="text" path="salePrice" onkeydown="return numbersonly(event,this.value)"/>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${empty ISCategory}">
                                                                <div style="visibility:visible">
                                                                    <table>
                                                                        <tr>
                                                                            <th><spring:message code="BzComposer.Item.InventoryInformation" /></th>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <c:if test="${itemDto.taxable == true}">
                                                                                    <input type="checkbox" name="taxable" checked="checked" />
                                                                                </c:if>
                                                                                <c:if test="${itemDto.taxable == false}">
                                                                                    <input type="checkbox" name="taxable" />
                                                                                </c:if>
                                                                                <spring:message code="BzComposer.Item.Taxable" /></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="4">
                                                                                <spring:message code="BzComposer.Item.ServiceTitle" />
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="4">
                                                                                <form:input type="text" path="itemName" />
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="4">
                                                                                <spring:message code="BzComposer.Item.ServiceDescription" />
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="4">
                                                                                <form:input type="text" path="invTitle" />
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <spring:message code="BzComposer.Item.ServiceRate" />
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <form:input type="text" path="salePrice" onkeydown="return numbersonly(event,this.value)" />
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </div>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td colspan="4">
                                                            <input type="button" class="formbutton" onclick="updateItem(this.form);" value='<spring:message code="BzComposer.global.update" />'>
                                                            <input type="button" class="formbutton" onclick="ClearMe();" value='<spring:message code="BzComposer.Item.Clear" />' />
                                                            <input type="button" class="formbutton" onclick="CloseMe();" value='<spring:message code="BzComposer.Item.Close" />' />
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </table>
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
	<!-- end Contents -->
</form:form>
</body>
</html>
<script>
	function DisConValue(form){
		if(form.discontinued.checked==true){
			form.discontinued.value="on";
		}
		else{
			form.discontinued.value="off";
		}
	}
	function updateItem(form){
		debugger;
		val = document.getElementById('inId').value;
		type = document.getElementById('itmType').value;
		var res = window.confirm('<spring:message code="BizComposer.ItemDetails.Update.Validation" />')
		if (res){
			document.forms['itemdetailsfrm'].action = "Item?tabid=UpdateItem&InvId="+val+"&ItemType="+type;
			document.forms['itemdetailsfrm'].submit();
			//window.close();
		}

	}

	function numbersonly(e,val){
		var temp=val.indexOf(".");
		var key=e.charCode? e.charCode : e.keyCode;
		if(window.event){
			if(window.event.ctrlKey)
				isCtrl = true;
			else
				isCtrl = false;
		}
		else{
			if(e.ctrlKey)
				isCtrl = true;
			else
				isCtrl = false;
		}
		if(isCtrl){
			if("v" == String.fromCharCode(key).toLowerCase()) {
				return false;
			}
			if("x" == String.fromCharCode(key).toLowerCase()) {
				return false;
			}
		}
		else if (key!=8){
			var str =String(val);
			var temp=val.indexOf(".");
			index=0;
			for(i=0;i<str.length;i++){
				if(str.charAt(i)=='.'){
					index=1;
					break;
				}
			}
			if(key==46 && temp==-1){
				return true;
			}
			else if(key==37 || key==39){
				return true;
			}
			else if(key==110 && index==0){
				return true;
			}
			else if(key==190 && index==0){
				return true;
			}
			else if(key>=96 && key<=105){
				return true;
			}
			else if (key<48||key>57) //if not a number
				return false; //disable key press
		}
	}


	function CloseMe(){
		window.close();
	}
	function ClearMe(form){
		type=document.getElementById('itmType').value;
		window.open("Item.do?tabid=ShowAdd&ItemType="+type,null,"scrollbars=yes,height=500,width=950,status=yes,toolbar=no,menubar=no,location=no" );
	}
</script>