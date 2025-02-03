<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<ROOT>
<html:form action="Item.do" method="post">
	<table class="tabla-listados" cellspacing="0">
		<tbody>
			<tr>
				<td><bean:message key="BzComposer.Item.ServiceName" /></td>
				<td><bean:message key="BzComposer.Item.Subcategoryof" /></td>
				<td></td>
			</tr>
			<tr>
				<td><html:text property="itemCode" /></td>
				<td></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="iscat"> <bean:message
					key="BzComposer.Item.isCategory" /></td>
				<td><html:select property="tectcmd" disabled="disabled">
					<html:option value="-1">Nothing Is Selected</html:option>
					<html:options collection="fillList" property="value"
						labelProperty="label"></html:options>
				</html:select></td>
				<td><bean:message key="BzComposer.Item.Barcode" /></td>
			</tr>
			<tr>
				<td colspan="4">
				<table>
					<tr>
						<th><bean:message key="BzComposer.Item.ServiceInformation" /></th>
					</tr>
					<tr>
						<td><input type="checkbox" name="isTaxable"><bean:message
							key="BzComposer.Item.Taxable" /></td>
					</tr>
					<tr>
						<td colspan="4"><bean:message
							key="BzComposer.Item.ServiceTitle" /></td>
					</tr>
					<tr>
						<td colspan="4"><html:text property="itemName" /></td>
					</tr>
					<tr>
						<td colspan="4"><bean:message
							key="BzComposer.Item.ServiceDescription" /></td>
					</tr>
					<tr>
						<td colspan="4"><html:textarea property="invTitle" /></td>
					</tr>
					<tr>
						<td><bean:message key="BzComposer.Item.ServiceRate" /></td>
						<td><html:text property="serviceRate"
							onkeydown="return numbersonly(event,this.value)" /></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
		</tbody>
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


</script>
</ROOT>
