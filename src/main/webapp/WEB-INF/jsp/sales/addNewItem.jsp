<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- <script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu_add.js"></script> -->
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><bean:message key="BzComposer.Title.AddItem"/> </title>
</head>
<body onload="Init();">
<div id="ddcolortabsline">&nbsp;</div>
<html:form action="Item.do" enctype="MULTIPART/FORM-DATA" method="post">
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div>
	<span style="font-size:1.6em;font-weight:normal;color:#838383;margin:30px 0px 15px 0px;border-bottom:1px dotted #333;padding:0 0 .3em 0;">
		Add Item
	</span>
</div>
</div>
<br>
<div>
	<table cellpadding="0" cellspacing="0" border="0" align=center  style="width: 100%;">	
		<logic:present name="Status">
			<tr>
				<td colspan="3">
					<span class="msgstyle">*
						<bean:write name="Status" />
					</span>
				</td>
			</tr>
		</logic:present>
		<logic:present name="SaveStatus">
			<tr>
				<td colspan="3">
					<span class="msgstyle">*
						<bean:write name="SaveStatus"/>
					</span>
				</td>
			</tr>
		</logic:present>
	</table>
</div>
<div id="table-negotiations" style="overflow:auto;width:100%">
	<table class="tabla-listados" cellspacing="0" align="left">
		<tr>
			<td style="padding-right: 14px;" width="100%">
				<div id="table-negotiations" style="width:100%">
					<table class="tabla-listados" cellspacing="0">
						<thead>
							<tr>
								<th colspan="12" style="font-size:1.6em;">
									ItemType
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td align="left" style="font-size:1em;">
									<bean:message key="BzComposer.Vendor.Type" />
		          				</td>                			
								<td colspan="2" style="font-size:1em;">
									<html:select property="itemType" onchange="call11(this.value);" value='<%=request.getParameter("ItemType") %>'>
										<html:option value="1">
											<bean:message key="BzComposer.Item.Inventory" />
										</html:option>
										<html:option value="2">
											<bean:message key="BzComposer.Item.Discount" />
										</html:option>
										<html:option value="3">
											<bean:message key="BzComposer.Item.Subtotal" />
										</html:option>
										<html:option value="4">
											<bean:message key="BzComposer.Item.Service" />
										</html:option>
									</html:select>
								</td>
								<td colspan="9">
									&nbsp;
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</td>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td style="padding-right: 14px;" width="100%">
				<div id="table-negotiations" style="width:100%">
				<table id="inventoryTab" class="tabla-listados" cellspacing="0">
					<thead>
						<tr>
							<th colspan="12" style="font-size:1.6em;">
								<bean:message key="BzComposer.Item.InventoryInformation" />
							</th>
						</tr>
					</thead>
					<tr>	
						<td>
							<table style="padding-left: 17px;padding-top: 13px;">
							<%--<td><bean:message key="BzComposer.Item.InventryName" /></td> --%>
								<tr>
									<td style="font-size:1em;">
										Inventory code
									</td>
									<td style="font-size:1em;">
										<html:text property="itemCode"/>
									</td>
									<td colspan="10">&nbsp;</td>
								</tr>					
							</table>			
						</td>
						<td colspan="11">&nbsp;</td>
					</tr>
					<tr>
						<td>
							<table style="padding-left: 116px;padding-top: 11px;">				
								<tr>
									<td style="font-size:1em;">
										<input type="checkbox" name="iscategory" id="chk_cat" onclick="hide_other(this.value);"> 
									<td style="font-size:1em;">
										<bean:message key="BzComposer.Item.isCategory" />
									</td>
									<td style="font-size:1em;">
										<bean:message key="BzComposer.Item.Subcategoryof" />
									</td>	
									<td style="font-size:1em;">
										<html:select property="tectcmd" disabled="false" value="0" styleId="drop_cat">
											<html:option value="0">
												<bean:message key="BzComposer.ComboBox.Select" />
											</html:option>
											<html:options collection="fillList" property="value" labelProperty="label"></html:options>
										</html:select>
									</td>
									<td style="font-size:1em;">
										<bean:message key="BzComposer.Item.Barcode" />
									</td>
									<td style="font-size:1em;">
										<html:text property="barcode" onkeydown="return numbersonly(event,this.value)" />
									</td>
									<td style="font-size:1em;">
										<input type="checkbox" name="discontinued"> 
									</td>
									<td style="font-size:1em;">	
										<bean:message key="BzComposer.Item.Discontinued" />
									</td>
									<td style="font-size:1em;">
										<div id="labelid">
											<bean:message key="BzComposer.Item.AddPicture" />
										</div>
									</td>
									<td style="font-size:1em;">
										<div id="photoid">
											<html:file property="photoName" />
										</div>
									</td>
								</tr>
							</table>
						</td>
						<td colspan="11">&nbsp;</td>
					</tr>				
					<tr id="hide_all">
						<td style="font-size:1em;">
							<table>						
								<tr>
									<td>
										<table>
											<tr>
												<td style="font-size:1em;">
													<bean:message key="BzComposer.Item.InventoryTitle" />
												</td>
												<td style="font-size:1em;">
													<input type="text" name="itemName" id="itm_name" size="60" />
												</td>
												<td style="font-size:1em;">
													<input type="checkbox" name="taxable" id="chk_tax">
													<bean:message key="BzComposer.Item.Taxable" />
												</td>
											</tr>
										</table>
									</td>							
								</tr>
								<tr>
									<td>
										<table>
											<tr>
												<td style="padding-left: 30px;font-size:1em;">
													<bean:message key="BzComposer.Item.Description" />
												</td>
												<td style="font-size:1em;">
													<textarea name="invTitle" id="txt_invtitle" cols="100"></textarea>
												</td>
											</tr>
										</table>
									</td>							
								</tr>
								<tr>
									<td>
										<table align="center" style="padding-left:25px"> 
											<tr>
												<td style="font-size:1em;">
													<bean:message key="BzComposer.Item.SalePrice" />
												</td>
												<td style="font-size:1em;">
													<input type="text" name="salePrice" id="sale_price"
													onkeydown="return numbersonly(event,this.value)" />
												</td>
												<td style="font-size:1em;">
													<bean:message key="BzComposer.Item.PurchasePrice" />
												</td>
												<td style="font-size:1em;">
													<input type="text" name="purchasePrice" id="Pur_price"
													onkeydown="return numbersonly(event,this.value)" />
												</td>
												<td style="font-size:1em;">
													<bean:message key="BzComposer.Item.QtyonHand" />
												</td>
												<td style="font-size:1em;">
													<input type="text" name="qty" id="qty"
													onkeydown="return numbersonly(event,this.value)" />
												</td>
												<td style="font-size:1em;">
													<bean:message key="BzComposer.Item.Weight" />
												</td>
												<td style="font-size:1em;">
													<input type="text" name="weight" id="weight"
													onkeydown="return numbersonly(event,this.value)" />
												</td>
											</tr>
											<tr>							
												<td style="font-size:1em;">
													<bean:message key="BzComposer.Item.Location" />
												</td>
												<td style="font-size:1em;">
													<input type="text" name="location" id="loc" />
												</td>
												<td style="font-size:1em;">
													<bean:message key="BzComposer.Item.Serial" />
												</td>
												<td style="font-size:1em;">
													<input type="text" name="serialNum" id="serial"
													onkeydown="return numbersonly(event,this.value)"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>						
							</table>
						</td>
						<td colspan="11">&nbsp;</td>
					</tr>
				</table>
				
				<!--</tr>
			</table> -->

			<!-- --Services -->
			<!-- <div id="table-negotiations" style="width:100%"> -->
			<%-- <table id="servicesTab" style="display:none;width: 100%;" class="tabla-listados" cellspacing="0">
				<thead>
					<tr>
						<th colspan="12" style="font-size: 1.6em;width: 100%"> 
							<bean:message key="BzComposer.Item.ServiceInformation"/>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>	
						<td>
							<table style="padding-left: 17px;padding-top: 13px;">
							<td><bean:message key="BzComposer.Item.InventryName" /></td>
								<tr>
									<td style="font-size:1em;">
										<bean:message key="BzComposer.Item.ServiceName" />
									</td>
									<td style="font-size:1em;">
										<html:text property="itemCodeSer" />
									</td>
									<td style="font-size:1em;">
										<bean:message key="BzComposer.Item.Subcategoryof"/>
									</td>
									<td style="font-size:1em;">
										<html:select property="tectcmdSer" disabled="disabled">
											<html:option value="0">
												<bean:message key="BzComposer.ComboBox.Select"/>
											</html:option>
											<html:options collection="fillList" property="value" labelProperty="label"></html:options>
										</html:select>
									</td>
								</tr>	
							</table>			
						</td>
					</tr>
					<tr>
						<td>
							<table style="padding-left: 116px;padding-top: 11px;">
								<tr>
									<td style="font-size:1em;">
										<input type="checkbox" name="iscat" id="sercat" onclick="hide_service(this.value);"> 
										<bean:message key="BzComposer.Item.isCategory"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table id="hide_ser" style="padding-left: 116px;padding-top: 11px;">
								<tr>
									<td style="font-size:1em;">
										<bean:message key="BzComposer.Item.ServiceTitle"/>
									</td>
									<td style="font-size:1em;">
										<html:text property="itemNameSer"/>
									</td>
								</tr>
								<tr>
									<td style="font-size:1em;">
										<bean:message key="BzComposer.Item.ServiceDescription" />
									</td>
									<td style="font-size:1em;">
										<html:textarea property="invTitleSer"/>
									</td>
									<td style="font-size:1em;">
										<input type="checkbox" name="taxableSer" id="taxser">
										<bean:message key="BzComposer.Item.Taxable"/>
									</td>
								</tr>
								<tr>
									<td style="font-size:1em;">
										<bean:message key="BzComposer.Item.ServiceRate" />
									</td>							
									<td style="font-size:1em;">
										<html:text property="serviceRate" onkeydown="return numbersonly(event,this.value)"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
			</table> --%>
			<!-- -Subtotal -->
			<%-- <table id="subtotalTab" style="display:none;width: 100%;" class="tabla-listados" cellspacing="0">
				<thead>
					<tr>
						<th colspan="12" style="font-size: 1.6em;"> 
							<bean:message key="BzComposer.Item.Subtotal" />
						</th>
					</tr>
				</thead>
				<tr>	
					<td>
						<table style="padding-left: 17px;padding-top: 13px;">
						<td><bean:message key="BzComposer.Item.InventryName" /></td>
							<tr>
								<td style="font-size:1em;">
									<bean:message key="BzComposer.Item.SubtotalName" />
								</td>
								<td style="font-size:1em;">
									<html:text property="itemCodeSub" />
								</td>
							</tr>
							<tr>
							</tr>
							<tr>
								<td style="font-size:1em;">
									<bean:message key="BzComposer.Item.Description" />
								</td>
								<td style="font-size:1em;">
									<html:text property="itemNameSub" />
								</td>
							</tr>
							<tr>
							</tr>
						</table>
					</td>
				</tr>
			</table> --%>			
			
			<!-- Discount  -->
			<%-- <table id="discountTab" style="display:none;width:100%;" class="tabla-listados" cellspacing="0">
				<thead>
					<tr>
						<th style="font-size:1.6em;"> 
							<bean:message key="BzComposer.Item.Discount"/>
						</th>
					</tr>
				</thead>
				<tr>
					<td>
						<table style="padding-left: 17px;padding-top: 13px;width: 100%">
							<tr>
								<td style="font-size:1em;">
									<bean:message key="BzComposer.Item.DiscountName" />
								</td>				
								<td style="font-size:1em;">
									<html:text property="itemCodeDis" />
								</td>
								<td colspan="10">&nbsp;</td>
							</tr>
							<tr>
								<td style="font-size:1em;">
									<bean:message key="BzComposer.Item.AmountofDiscount"/>
								</td>				
								<td style="font-size:1em;">
									<html:text property="discountAmt" onkeydown="return numbersonly(event,this.value)" />
								</td>
								<td colspan="10">&nbsp;</td>
							</tr>
							<tr>
								<td style="font-size:1em;">
									<bean:message key="BzComposer.Item.DiscountTitle" />
								</td>				
								<td style="font-size:1em;">
									<html:textarea property="invTitleDis"/>
								</td>
								<td colspan="10">&nbsp;</td>
							</tr>				
						</table>
								
			</table> --%>
			</div>
		</td>
		<td colspan="3">&nbsp;</td>
	</tr>
	<tr align="center">
		<td colspan="4">
			<table>
				<tr>
					<td>
						<input type="button" name="updatBtn" class="formbutton" onclick="ShowAdd(this.form);"
						value='<bean:message key="BzComposer.Item.Add" />'>
					</td>
					<td>
						<input type="button" name="updatBtn" class="formbutton" value='<bean:message key="BzComposer.Item.Clear" />'
						onclick="ClearMe(this.form);">
					</td>
					<td>
						<input type="button" class="formbutton" name="updatBtn" value='<bean:message key="BzComposer.Item.Close" />'
						onclick="CloseMe();">
					</td>			
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>
</div>
</div>
</div>
</div>
<!-- end Contents -->
</html:form>
</body>
</html>
<script>
function hide_other(val){
	cat = document.getElementById('chk_cat');
	
	if(cat.checked==true){
		document.getElementById("hide_all").style.visibility="hidden";
		document.getElementById('labelid').style.visibility="hidden";
		document.getElementById('photoid').style.visibility="hidden";
		document.getElementById('drop_cat').disabled=true;    
	}
	else{
		document.getElementById("hide_all").style.visibility="visible";
		document.getElementById('labelid').style.visibility="visible";
		document.getElementById('photoid').style.visibility="visible";
		document.getElementById('drop_cat').disabled=false;    
	}
}
function showCombo(){
	if(chkbox.checked==true){
		document.getElementById("cmdbox").disabled=false;
	}
	else
		document.getElementById("cmdbox").disabled=true;
}
function ShowAdd(form){
	val = form.itemType.value;
	if(val=="1"){
		cat = document.getElementById('chk_cat');
		if(cat.checked==false){
			if(form.itemCode.value==""){
				alert('<bean:message key="BzComposer.AddItem.InventoryName.Validation" />');
				form.itemCode.focus();
			}
			else if(form.salePrice.value==""){
				alert('<bean:message key="BzComposer.AddItem.SalePrice.Validation" />');
				form.salePrice.focus();
			}
			else if(form.purchasePrice.value==""){
				alert('<bean:message key="BzComposer.AddItem.PurchasePrice.Validation" />');
				form.purchasePrice.focus();
			}
			else if(form.qty.value==""){
				alert('<bean:message key="BzComposer.AddItem.Quantity.Validation" />');
				form.qty.focus();
			}
			else if(form.weight.value==""){
				alert('<bean:message key="BzComposer.AddItem.Weight.Validation" />');
				form.weight.focus();
			}
			else{
				var res=window.confirm('<bean:message key="BzComposer.AddItem.Confirmation.Validation" />')
				if (res){
					document.forms[0].action = "Item.do?tabid=NewItem&ItemType="+val;
					document.forms[0].submit();
				}
			}
		}
		else if(cat.checked==true){
			if(form.itemCode.value==""){
				alert('<bean:message key="BzComposer.AddItem.InventoryName.Validation" />');
				form.itemCode.focus();
			}
			else{
				form.salePrice.value="0";
				form.purchasePrice.value="0";
				form.qty.value="0";
				form.weight.value="0";
				var res=window.confirm('<bean:message key="BzComposer.AddItem.Confirmation.Validation" />')
				if (res){
					document.forms[0].action = "Item.do?tabid=AddItem&ItemType="+val;
					document.forms[0].submit();
				}
			}
		}
	}
	else if(val=="2"){
		if(form.itemCodeDis.value==""){
			alert('<bean:message key="BzComposer.AddItem.Discount.Validation" />');
			form.itemCodeDis.focus();
		}
		else{
			var res=window.confirm('<bean:message key="BzComposer.AddItem.Confirmation.Validation" />')
			 if (res){
				form.itemCode.value=form.itemCodeDis.value;
				document.forms[0].action = "Item.do?tabid=AddItem&ItemType="+val;
				document.forms[0].submit();
			}
		}
	}
	else if(val=="3"){
		if(form.itemCodeSub.value==""){
			alert('<bean:message key="BzComposer.AddItem.Subtotal.Validation" />');
			form.itemCodeSub.focus();
		}
		else{
			var res=window.confirm('<bean:message key="BzComposer.AddItem.Confirmation.Validation" />')
			 if (res){
				form.itemCode.value=form.itemCodeSub.value;
				document.forms[0].action = "Item.do?tabid=AddItem&ItemType="+val;
				document.forms[0].submit();
			}
		}
	}
	else if(val=="4"){
		if(form.itemCodeSer.value==""){
			alert('<bean:message key="BzComposer.AddItem.Service.Validation" />');
			form.itemCodeSer.focus();
		}
		else{
			var res=window.confirm('<bean:message key="BzComposer.AddItem.Confirmation.Validation" />')
				if (res){
					form.itemCode.value=form.itemCodeSer.value;
					document.forms[0].action = "Item.do?tabid=AddItem&ItemType="+val;
					document.forms[0].submit();
				}
		}
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
	form.tectcmd.value="0";
	form.photoName.value="";
	form.itemCode.value="";
	form.tectcmdSer.value="0";
	form.itemCodeSer.value="";
	form.barcode.value="";
	form.itemCodeSub.value="";
	form.itemCodeDis.value="";
	form.itemNameSub.value="";
	form.invTitleDis.value="";
	form.discountAmt.value="";
	form.itemNameSer.value="";
	form.invTitleSer.value="";
	form.serviceRate.value="";
	
	document.getElementById('itm_name').value="";
	document.getElementById('txt_invtitle').value="";
	document.getElementById('sale_price').value="";
	document.getElementById('Pur_price').value="";
	document.getElementById('qty').value="";
	document.getElementById('weight').value="";
	document.getElementById('loc').value="";
	document.getElementById('serial').value="";
	
	document.getElementById('chk_cat').checked=false;
	document.getElementById('chk_tax').checked=false;
	
	document.getElementById('taxser').checked=false;
	document.getElementById('sercat').checked=false;
	
	hide_other(0);
	hide_service(0);
}		
function call11(value){
	if(value==1){
		document.getElementById('inventoryTab').style.display='block';
		document.getElementById('discountTab').style.display='none';
		document.getElementById('subtotalTab').style.display='none';
		document.getElementById('servicesTab').style.display='none';
	}
	else if(value==2){
		document.getElementById('inventoryTab').style.display='none';
		document.getElementById('discountTab').style.display='block';
		document.getElementById('subtotalTab').style.display='none';
		document.getElementById('servicesTab').style.display='none';
	}
	else if(value==3){
		document.getElementById('inventoryTab').style.display='none';
		document.getElementById('discountTab').style.display='none';
		document.getElementById('subtotalTab').style.display='block';
		document.getElementById('servicesTab').style.display='none';
	}
	else if(value==4){
		document.getElementById('inventoryTab').style.display='none';
		document.getElementById('discountTab').style.display='none';
		document.getElementById('subtotalTab').style.display='none';
		document.getElementById('servicesTab').style.display='block';	}
}
function hide_service(val){
	category = document.getElementById('sercat');
	if(category.checked==true){
		document.getElementById("hide_ser").style.visibility="hidden";
	}
	else{
		document.getElementById("hide_ser").style.visibility="visible";
	}
}
function Init(){
	call11(<%= request.getParameter("ItemType")%>);
}
</script>