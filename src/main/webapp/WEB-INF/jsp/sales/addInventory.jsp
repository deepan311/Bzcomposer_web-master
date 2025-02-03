
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<ROOT>
<html:form action="Item.do" enctype="MULTIPART/FORM-DATA" method="post">

	<table class="tabla-listados" cellspacing="0">
		<tr>
			<td><bean:message key="BzComposer.Item.InventryName" /></td>
			<td><bean:message key="BzComposer.Item.Subcategoryof" /></td>
			<td><html:select property="tectcmd" disabled="disabled">
				<html:option value="-1">Nothing Is Selecteded</html:option>
				<html:options collection="fillList" property="value"
					labelProperty="label"></html:options>
			</html:select></td>
		</tr>
		<tr>
			<td><html:text property="itemCode" /></td>
			<td></td>
			<td><html:file property="photoName" size="52" /></td>
		</tr>
		<tr>
			<td><input type="checkbox" name="iscat" id="chk_cat1"
				value="true" onclick="hide_other(this.value);"> <bean:message
				key="BzComposer.Item.isCategory" /></td>
			<td><bean:message key="BzComposer.Item.Barcode" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><html:text property="barcode"
				onkeydown="return numbersonly(event,this.value)" /></td>
		<tr>
			<td colspan="2">
			<table id="hide_all">
				<tr>
					<th colspan="4"><bean:message
						key="BzComposer.Item.InventoryInformation" /></th>
				</tr>
				<tr>
					<td colspan="4"><input type="checkbox" name="isTaxable"><bean:message
						key="BzComposer.Item.Taxable" /></td>
				</tr>
				<tr>
					<td colspan="4"><bean:message
						key="BzComposer.Item.InventoryTitle" /></td>
				</tr>
				<tr>
					<td colspan="4"><html:text property="itemName" /></td>
				</tr>
				<tr>
					<td colspan="4"><bean:message
						key="BzComposer.Item.Description" /></td>
				</tr>
				<tr>
					<td colspan="4"><html:textarea property="invTitle" /></td>
				</tr>
				<tr>
					<td><bean:message key="BzComposer.Item.SalePrice" /></td>
					<td><bean:message key="BzComposer.Item.PurchasePrice" /></td>
					<td><bean:message key="BzComposer.Item.QtyonHand" /></td>
					<td><bean:message key="BzComposer.Item.Weight" /></td>
				</tr>
				<tr>
					<td><html:text property="salePrice"
						onkeydown="return numbersonly(event,this.value)" /></td>
					<td><html:text property="purchasePrice"
						onkeydown="return numbersonly(event,this.value)" /></td>
					<td><html:text property="qty"
						onkeydown="return numbersonly(event,this.value)" /></td>
					<td><html:text property="weight"
						onkeydown="return numbersonly(event,this.value)" /></td>
				</tr>
				<tr>
					<td><bean:message key="BzComposer.Item.Location" /></td>
					<td><bean:message key="BzComposer.Item.Serial" /></td>
				</tr>
				<tr>
					<td><html:text property="location" /></td>
					<td><html:text property="serialNum"
						onkeydown="return numbersonly(event,this.value)" /></td>
				</tr>
			</table>
</html:form>
<script type="text/javascript">

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

function hide_other(val){
	if(chk_cat1.checked==true)	{
		document.getElementById("hide_all").style.visibility="hidden";
	}
	else{
		document.getElementById("hide_all").style.visibility="visible";
	}
}
</script>
</ROOT>