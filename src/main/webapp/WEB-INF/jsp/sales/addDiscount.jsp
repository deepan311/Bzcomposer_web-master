<%-- test comment --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<ROOT>
<html:form action="Item.do" method="post">

	<table class="tabla-listados" cellspacing="0">

		<tr>
			<td><bean:message key="BzComposer.Item.DiscountName" /></td>
		</tr>
		<tr>
			<td><html:text property="itemCode" /></td>
		</tr>
		<tr>
			<td colspan="4"><bean:message
				key="BzComposer.Item.AmountofDiscount" /></td>
		</tr>
		<tr>
			<td colspan="4"><html:text property="discountAmt"
				onkeydown="return numbersonly(event,this.value)" /></td>
		</tr>
		<tr>
			<td colspan="4"><bean:message
				key="BzComposer.Item.DiscountTitle" /></td>
		</tr>
		<tr>
			<td colspan="4"><html:textarea property="invTitle" /></td>
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
</script>
</ROOT>
