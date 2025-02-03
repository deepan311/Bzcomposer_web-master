<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page isELIgnored="false"%>
<%@ page errorPage="/include/sessionExpired.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>

<title><bean:message key="BizComposer.Purchase.ReceivedItem.Title" /></title>

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
   var res = trim(o.responseText);
   document.getElementById("list").innerHTML = res;
 
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
	
		document.getElementById('custNm').value=col_array[part_num];
	   part_num++;
	  if(col_array[part_num]=="true")
		  document.InvoiceForm.taxable.checked=true;
	  else
		  document.InvoiceForm.taxable.checked=false;
	  part_num++;
	  document.InvoiceForm.billTo.value=col_array[part_num];
	  part_num++;
	  document.InvoiceForm.shipTo.value=col_array[part_num];
	  part_num++;
	  document.InvoiceForm.via.value=col_array[part_num];
	  part_num++;
	  document.InvoiceForm.payMethod.value=col_array[part_num];
	  part_num++;
	  document.InvoiceForm.term.value=col_array[part_num];
	  part_num++;
	  document.InvoiceForm.rep.value=col_array[part_num];
	  part_num++;
	  document.InvoiceForm.custID.value=col_array[part_num];
	  part_num++;
	  document.InvoiceForm.bsAddressID.value=col_array[part_num];
	}
}

function vendorName(obj)
{
 val=obj.value;
 selrow=null;
 mover=null;
 o = c(writeSelect);
 oGET(o,'<bean:write name="path" property="pathvalue" />/include/vendorList.jsp?val=' + val);

}

function setTxtval(){
	value = document.getElementById("NameVal").value;
	var idvalue=value.split("/");
	var index=0;
	var clientid="";
	var servicenm="";
	if(index < idvalue.length){
		clientid=idvalue[index];
		index++;
		servicenm=idvalue[index];
	}
	document.InvoiceForm.serviceName.value=servicenm;
	o = c(writeSelect11);
	oGET(o,'<bean:write name="path" property="pathvalue" />/purchase/recvItemRecord.jsp?ClientID='+clientid+"&ServiceNm="+servicenm);
	document.getElementById("list").innerHTML ="";
	document.getElementById('payhist').disabled=false;
	document.getElementById('updatecust').disabled=false;
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
	
</script>
<style type="text/css">
.msgstyle{

font-size:18px;
color: #3D9EAC;

}
</style>
</head>
<body onload="Init();">


<div id="ddcolortabsline">&nbsp;</div>



<div id="cos">

<div class="statusquo ok">

<div id="hoja">
<div id="blanquito">
<div id="padding"><html:form action="/ReceivedItem" method="post">
	<html:errors />
	<div><span
		style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;"><bean:message
		key="BzComposer.ReceivedItem.ReceivedItemTitle" /></span></div>
		<div style="width: 855;margin-left: auto;margin-right: auto;">
	<table>
		<tr>
			<td width="15%">
			<table>
				<tr>
					<td><html:button property="New" title="New Invoice"
						styleClass="formbutton" onclick="NewInvoice();">
						<bean:message key="BzComposer.global.new" />
					</html:button></td>
					<td><html:button property="Save" title="Save Invoice"
						styleClass="formbutton" onclick="onSave(this.form);">
						<bean:message key="BzComposer.global.saveUpdate" />
					</html:button></td>
					<td><logic:present name="IsDisplay">
						<html:button property="Delete" title="Delete Invoice"
							styleClass="formbutton" disabled="true">
							<bean:message key="BzComposer.Invoice.DeleteBtn" />
						</html:button>
					</logic:present> <logic:notPresent name="IsDisplay">
						<html:button property="Delete" title="Delete Invoice"
							styleClass="formbutton" onclick="onDelete(this.form);"
							disabled="false">
							<bean:message key="BzComposer.Invoice.DeleteBtn" />
						</html:button>
					</logic:notPresent></td>
					<td><logic:present name="IsDisplay">
						<input type="button" name="Update" class="formbutton"
							id="updatecust" title="Update Customer information"
							disabled="disabled" onclick="ShowUpdate(this.form);"
							value='<bean:message key="BzComposer.Invoice.Update" />' />


					</logic:present> <logic:notPresent name="IsDisplay">
						<input type="button" name="Update" class="formbutton"
							title="Update Customer information"
							onclick="ShowUpdate(this.form);"
							value='<bean:message key="BzComposer.Invoice.Update" />' />


					</logic:notPresent></td>
				</tr>
			</table>
			</td>

			<td colspan="2" align="left">
			<table width="15%">
				<tr>
					<td align="right"><logic:present name="IsFirst">
						<html:button property="First" styleClass="formbutton"
							title="First Invoice Record" disabled="true">
							<bean:message key="BzComposer.Invoice.First" />
						</html:button>
					</logic:present> <logic:notPresent name="IsFirst">
						<html:button property="First" styleClass="formbutton"
							title="First Invoice Record" onclick="FirstInvoice();"
							disabled="false">
							<bean:message key="BzComposer.Invoice.First" />
						</html:button>
					</logic:notPresent></td>
					<td align="right"><logic:present name="IsDisplay">
						<html:button property="Previous" styleClass="formbutton"
							title="Previous Invoice Record" disabled="true">
							<bean:message key="BzComposer.Invoice.Previous" />
						</html:button>
					</logic:present> <logic:notPresent name="IsDisplay">
						<logic:present name="IsFirst">
							<html:button property="Previous" styleClass="formbutton"
								title="Previous Invoice Record" disabled="true">
								<bean:message key="BzComposer.Invoice.Previous" />
							</html:button>
						</logic:present>
						<logic:notPresent name="IsFirst">
							<html:button property="Previous" styleClass="formbutton"
								title="Previous Invoice Record" onclick="PreviousInvoice();"
								disabled="false">
								<bean:message key="BzComposer.Invoice.Previous" />
							</html:button>
						</logic:notPresent>
					</logic:notPresent></td>
					<td align="right"><logic:present name="IsDisplay">
						<html:button property="Next" styleClass="formbutton"
							title="Next Invoice Record" disabled="true">
							<bean:message key="BzComposer.Invoice.Next" />
						</html:button>
					</logic:present> <logic:notPresent name="IsDisplay">
						<logic:present name="IsLast">
							<html:button property="Next" styleClass="formbutton"
								title="Next Invoice Record" disabled="true">
								<bean:message key="BzComposer.Invoice.Next" />
							</html:button>
						</logic:present>
						<logic:notPresent name="IsLast">
							<html:button property="Next" styleClass="formbutton"
								title="Next Invoice Record" onclick="NextInvoice();"
								disabled="false">
								<bean:message key="BzComposer.Invoice.Next" />
							</html:button>
						</logic:notPresent>
					</logic:notPresent></td>
					<td align="right"><logic:present name="IsLast">
						<html:button property="Last" styleClass="formbutton"
							title="Last Invoice Record" disabled="true">
							<bean:message key="BzComposer.Invoice.Last" />
						</html:button>
					</logic:present> <logic:notPresent name="IsLast">
						<html:button property="Last" styleClass="formbutton"
							title="Last Invoice Record" onclick="LastInvoice();"
							disabled="false">
							<bean:message key="BzComposer.Invoice.Last" />
						</html:button>
					</logic:notPresent></td>

				</tr>
			</table>
			</td>



			<td align="right"><logic:present name="IsDisplay">
				<input type="button" name="payHistory" id="payhist"
					class="formbutton" title="Payment History"
					onclick="paymentHistory(this.form);" disabled="disabled"
					value='<bean:message key="BzComposer.Invoice.PayHistory" />' />


			</logic:present> <logic:notPresent name="IsDisplay">
				<input type="button" name="payHistory" d="payhist"
					class="formbutton" title="Payment History"
					onclick="paymentHistory(this.form);"
					value='<bean:message key="BzComposer.Invoice.PayHistory" />' />


			</logic:notPresent></td>
			<td align="right"><logic:present name="Enable">
				<html:button property="sendMail" styleClass="formbutton"
					title="Send Mail to..." onclick="SendMail(this.form);"
					disabled="false">
					<bean:message key="BzComposer.Invoice.SendMail" />
				</html:button>
			</logic:present> <logic:notPresent name="Enable">
				<html:button property="sendMail" styleClass="formbutton"
					title="Send Mail to..." onclick="SendMail(this.form);"
					disabled="true">
					<bean:message key="BzComposer.Invoice.SendMail" />
				</html:button>
			</logic:notPresent></td>

		</tr>

	</table></div>
	<div id="table-negotiations" style="margin-left: auto ;
  margin-right: auto ;">
	<div id="table-negotiations">



	<div id="ItemDetails"><bean:size id="iSize" name="ItemList" /> <input
		type="hidden" name="ItemSize" id="itemSize"
		value='<bean:write name="iSize" />'> <logic:iterate
		name="ItemList" id="objList" indexId="index">
		<input type="hidden"
			value='<bean:write name="objList" property="invID" />'
			id='<bean:write name="index" />inv'
			name='ii<bean:write name="index" />inv' />
		<input type="hidden"
			value='<bean:write name="objList" property="qty"/>'
			id='<bean:write name="index" />q'
			name='ii<bean:write name="index" />q' />
		<input type="hidden"
			value='<bean:write name="objList" property="invCode" filter="false" />'
			id='<bean:write name="index" />code'
			name='ii<bean:write name="index" />code' />

		<input type="hidden"
			value='<bean:write name="objList" property="invDesc"  />'
			id='<bean:write name="index" />desc'
			name='ii<bean:write name="index" />desc' />
		<input type="hidden"
			value='<bean:write name="objList" property="salePrice"/>'
			id='<bean:write name="index" />price'
			name='ii<bean:write name="index" />price' />
		<input type="hidden"
			value='<bean:write name="objList" property="weight"/>'
			id='<bean:write name="index" />wt'
			name='ii<bean:write name="index" />wt' />
		<input type="hidden"
			value='<bean:write name="objList" property="isCategory"/>'
			id='<bean:write name="index" />cat'
			name='ii<bean:write name="index" />cat' />
		<input type="hidden"
			value='<bean:write name="objList" property="itemTypeID"/>'
			id='<bean:write name="index" />itmId'
			name='ii<bean:write name="index" />itmId' />
		<input type="hidden"
			value='<bean:write name="objList" property="serialNo"/>'
			id='<bean:write name="index" />serial'
			name='ii<bean:write name="objList" property="serialNo"/>ser' />

	</logic:iterate></div>
	<html:hidden property="serviceName" />
	<table class="tabla-listados" cellspacing="0">
		
		<logic:present name="Status">
			<tr>
				<td colspan="3"><span class="msgstyle">*<bean:write
					name="Status" /></span></td>
			</tr>
		</logic:present>
		<logic:present name="SaveStatus">
			<tr>
				<td colspan="3"><span class="msgstyle">*<bean:write
					name="SaveStatus" /></span></td>
			</tr>
		</logic:present>
		<thead>
							<tr>
								<th colspan="9"><bean:message
			key="BzComposer.ReceivedItem.ReceivedItemTitle"/></th>
							</tr>
						</thead>
		<tr>
			<td>
			<table>
				<tr>
					<td align="center"><bean:message
						key="BzComposer.Purchase.ReceivedItem.vendor" /></td>
                      <td>	<bean:message
						key="BzComposer.Purchase.ReceivedItem.Style" /></td>
                        <td>	<bean:message key="BzComposer.Purchase.ReceivedItem.Taxable" /></td>
                          <td><bean:message key="BizComposer.Invoice.Pending" /></td>
				</tr>
				<tr>
					<td><logic:present name="CustomerName">
						<input type="text" size="30" onkeyup="vendorName(this);"
							onclick="vendorName(this);" style="width:140px;" id="custNm"
							value='<bean:write name="CustomerName" />' />
					</logic:present> <logic:notPresent name="CustomerName">
						<input type="text" size="30" onkeyup="vendorName(this);"
							onclick="vendorName(this);" style="width:140px;" id="custNm" />
					</logic:notPresent>
					<div id="list"></div>
					</td>
					<td>
					<input type="hidden" name="Ivhidden" id="InvStyle" />
						<html:select property="invoiceStyle"
						onchange="StyleChange(this.value);"
						onkeydown="StyleChange(this.value);"
						onkeyup="StyleChange(this.value);">
						<html:option value="0">
							<bean:message key="BzComposer.ComboBox.Select" />
						</html:option>

						<html:options collection="InvoiceStyle" property="value"
							labelProperty="label" />
					</html:select></td>
					<td align="center">	<html:checkbox property="taxable"
						onclick="TaxaValue(this.form);">
					
					</html:checkbox></td>
					<td align="center">
					
						<html:checkbox property="isPending"
				         onclick="Pending_Value(this.form);toggle_visibility('pending');">
				
			           </html:checkbox>
					</td>
					
					
					<html:hidden property="custID" />
				</tr>
			</table>
<script type="text/javascript">
<!--
function toggle_visibility(id) {
	var e = document.getElementById(id);
	e.style.display = ((e.style.display!='none') ? 'none' : 'block');
}
//-->
</script>			
			</td>	
<td align="center">
			<table>
				<tr>
					<td align="center"><bean:message key="BzComposer.Invoice.Date" />
					</td>
					<td align="center"><bean:message
						key="BzComposer.Purchase.ReceivedItem.Rcv" /></td>
				</tr>
				<tr>
					<td><html:text property="orderDate" readonly="true" size="15" />
					<img
						src="${pageContext.request.contextPath}/images/cal.gif"
						onclick="displayCalendar(document.InvoiceForm.orderDate,'mm-dd-yyyy',this);"
						onmouseover="setIds();"></td>

					<td align="right">&nbsp;&nbsp;&nbsp; <html:text
						property="orderNo" size="10"
						onkeypress="return numbersonly(event,this.value);"
						onfocus="setIds();" /></td>
				</tr>
			</table>
			</td>
			<td align="right">
			<table align="left">
				<tr>
				<td align="center" id="sh_date_label"><bean:message
						key="BzComposer.Invoice.ShipDate" /></td>
					<td align="center" id="po_num_label"><bean:message
						key="BzComposer.Invoice.PONum" /></td>
					
				</tr>
				<tr>
				<td id="sh_date_id"><html:text property="shipDate"
						readonly="true" size="12" /> <img
						src="${pageContext.request.contextPath}/images/cal.gif"
						onclick="displayCalendar(document.InvoiceForm.shipDate,'mm-dd-yyyy',this);"
						onmouseover="setIds();"></td>
					<td id="po_num_id"><html:text property="poNum" size="10"
						onkeypress="return numbersonly(event,this.value)"
						onfocus="setIds();"></html:text></td>
					

				</tr>
			</table>
			</td>

			
		</tr>

		<tr align="left">
		<td>
		<table style="width: 100%;">
		<tr>
		<td align="center" id="bill_label"><bean:message
				key="BzComposer.Purchase.ReceivedItem.vendor" />
				
				
				</td>
		<td align="center" id="ship_label"><bean:message
				key="BzComposer.Invoice.ShipTo" /></td>			
		</tr>
		
		</table>
		
		</td>
		
			
			<td align="center">
			
	<div id="pending" style="display:none;"><font size="10"><bean:message
				key="BizComposer.Invoice.Pending" /> </font>
			
			</td>
			<td></td>
		</tr>
		<tr>
		
		
		<td>
		<table>
		<tr>
			<td width="25%" align="left" id="bill_id"><html:textarea
				property="billTo" rows="5" cols="35" readonly="true"
				onfocus="setIds();"></html:textarea></td>
		<td width="40%" align="center" id="ship_id"><html:textarea
				property="shipTo" rows="5" cols="35" readonly="true"
				onfocus="setIds();"></html:textarea></td>
		</tr>
		</table>
		</td>
		
				
				
				
				
		<td id="td2" align="center">
			<table>
				<tr>
					<td align="center"><bean:message key="BzComposer.Invoice.Term" />
					</td>
					<td align="center"><bean:message
						key="BzComposer.Invoice.PayMethod" /></td>
				</tr>
				<tr>
					<td><html:select property="term" onclick="setIds();">
						<html:option value="0">
							<bean:message key="BzComposer.ComboBox.Select" />
						</html:option>
						<html:options collection="Term" property="value"
							labelProperty="label" />
					</html:select></td>
					<td><html:select property="payMethod" onclick="setIds();">
						<logic:present name="PayMethod">
							<html:option value="0">
								<bean:message key="BzComposer.ComboBox.Select" />
							</html:option>
							<html:options collection="PayMethod" property="value"
								labelProperty="label" />
						</logic:present>
					</html:select></td>

				</tr>
			</table>
			</td>
			<td align="right" id="td1">
			<table align="right">
				<tr>
					<td align="center"><bean:message key="BzComposer.Invoice.Via" /></td>
					<td align="center"><bean:message key="BzComposer.Invoice.Rep" /></td>
				</tr>
				<tr>
					<td><html:select property="via" onclick="setIds();">
						<html:option value="0">
							<bean:message key="BzComposer.ComboBox.Select" />
						</html:option>
						<html:options collection="Via" property="value"
							labelProperty="label" />
					</html:select></td>
					<td><html:select property="rep" onclick="setIds();">
						<html:option value="0">
							<bean:message key="BzComposer.ComboBox.Select" />
						</html:option>
						<html:options collection="Rep" property="value"
							labelProperty="label" />

					</html:select></td>

				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="25%">
		
			</td>
			<td></td>
			<td></td>
		</tr>
		<tr></tr>
	</table>

<%-- 	<div align="center"><font size="3"><strong> <bean:message --%>
<!-- 		key="BizComposer.Estimaion.Header.ItemInfo" /> </strong></font></div> -->

	<div id="product">
	<table class="tabla-listados" cellspacing="0">
	<thead>
							<tr>
								<th colspan="9"><bean:message
		key="BizComposer.Estimaion.Header.ItemInfo" /></th>
							</tr>
						</thead>
		<tr>
			<td align="right" id="td1"><bean:message
				key="BzComposer.Invoice.ItemID" /></td>

			<td id="td2"><html:select property="itemID"
				onchange="ItemChange(this.value);" onclick="setIds();">
				<html:option value="0">
					<bean:message key="BzComposer.ComboBox.Select" />
				</html:option>

				<logic:iterate name="ItemList" id="itmList">
					<logic:equal name="itmList" property="isCategory" value="0">
						<html:option value='${itmList.invID}'>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write
								name="itmList" property="invCode" filter="false" />
						</html:option>
					</logic:equal>
					<logic:equal name="itmList" property="isCategory" value="1">
						<html:option value='${itmList.invID}'>
							<bean:write name="itmList" property="invCode" filter="false" />
						</html:option>
					</logic:equal>
				</logic:iterate>
			</html:select></td>

			<td align="right">
			<div id="td3" style="display:block;"><bean:message
				key="BzComposer.Invoice.Qty" /></div>
			<div id="td4" style="display:none"><bean:message
				key="BzComposer.Invoice.Rate" /></div>
			</td>
			<td>
			<div style="display:block;" id="td5"><input type="text"
				size="10" id="qty_id"
				onkeypress="return numbersonly(event,this.value)"
				onfocus="setIds();" /></div>
			</td>
			<td align="right">
			<div id="td6" style="display:none;"><bean:message
				key="BzComposer.Invoice.SerialNo" /></div>
			</td>
			<td>
			<div id="td7" style="display:none;"><input type="text"
				size="10" id="serialNo_id" readonly="readonly" onfocus="setIds();" /></div>
			</td>
			<td align="center">
			<div id="td8"><bean:message key="BzComposer.Invoice.Desc" /></div>
			</td>
			<td>
			<div id="td9"><textarea rows="1" cols="25" readonly="readonly"
				id="desc_id" onfocus="setIds();"></textarea></div>
			</td>
		</tr>
		<tr id="tr$$">

			<td align="right">
			<div id="td10" style="display:block;"><bean:message
				key="BzComposer.Invoice.UnitPrice" /></div>
			<div id="td11" style="display:none;"><bean:message
				key="BzComposer.Invoice.RatePrice" /></div>
			</td>
			<td>
			<div id="td12"><input type="text" size="10" id="unitPrice_id"
				onkeypress="return numbersonly(event,this.value)"
				onfocus="setIds();" /></div>
			</td>
			<td align="right">
			<div id="td13" style="display:block;"><bean:message
				key="BzComposer.Invoice.Amt" /></div>
			</td>

			<td>
			<div id="td14" style="display:block;"><input type="text"
				size="10" readonly="true" id="amount_id" onfocus="Multiplication();"
				onclick="setIds();" /></div>
			</td>

			<td align="right">
			<div id="td15" style="display:block;"><bean:message
				key="BzComposer.Invoice.Weight" /></div>
			</td>
			<td>
			<div id="td16" style="display:block;"><input type="text"
				name="itemWeight" size="10" id="weight_id"
				onkeypress="return numbersonly(event,this.value)"
				onfocus="setIds();" /></div>
			</td>
			<td align="right">
			<div id="td17" style="display:block;"><bean:message
				key="BzComposer.Invoice.Tax" /></div>
			</td>
			<td>
			<div id="td18" style="display:block;"><select id="tax_id"
				onclick="setIds();">
				<option value="0">No</option>
				<option value="1">Yes</option>
			</select></div>
			</td>

		</tr>

		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td><br>
			<input type="button" class="formbutton" name="addItem"
				title="To add item click it" onclick="AddItem(this.form);"
				value='<bean:message key="BzComposer.Invoice.AddItem"/>' /></td>
			<td></td>
			<td></td>


		</tr>
	</table>

	<div id="table-negotiations" style="overflow:auto;height:300;" class="section-border">
	<table class="tabla-listados" cellspacing="0">
		<thead>
			<tr>
				<th>
				<div id="it1"><bean:message key="BzComposer.Invoice.ItemID" /></div>
				</th>
				<th>
				<div id="it2" align="right"><bean:message
					key="BzComposer.Invoice.Qty" /></div>
				<div id="it22" style="display:none;" align="right"><bean:message
					key="BzComposer.Invoice.Rate" /></div>
				</th>
				<th>
				<div id="it8" align="right"><bean:message
					key="BzComposer.Invoice.SerialNo" /></div>
				</th>
				<th>
				<div id="it3"><bean:message key="BzComposer.Invoice.Desc" /></div>
				</th>
				<th>
				<div id="it4" align="right"><bean:message
					key="BzComposer.Invoice.UnitPrice" /></div>
				<div id="it42" style="display:none;" align="right"><bean:message
					key="BzComposer.Invoice.RatePrice" /></div>
				</th>
				<th>
				<div id="it5" align="right"><bean:message
					key="BzComposer.Invoice.Amt" /></div>
				</th>

				<th>
				<div id="it6" align="right"><bean:message
					key="BzComposer.Invoice.Weight" /></div>
				</th>
				<th>
				<div id="it7" align="center"><bean:message
					key="BzComposer.Invoice.Tax" /></div>
				</th>
				<th><bean:message key="BzComposer.Invoice.DeleteOption" /></th>
			</tr>
		</thead>
		<logic:present name="Cart">
			<bean:size id="ctSize" name="Cart" />
			<input type="hidden" name="hdncsize" id="CartSize"
				value='<bean:write name="ctSize" />'>
			<logic:iterate name="Cart" id="cart" indexId="indx">
				<tr id='<bean:write name="indx" />row'>
					<td align="left">
					<div id='<bean:write name="indx" />icode'><bean:write
						name="cart" property="invCode" /></div>
					</td>
					<td align="right">
					<div id='<bean:write name="indx" />qt'><bean:write
						name="cart" property="qty" /></div>
					</td>
					<td align="right">
					<div id='<bean:write name="indx" />iserial'><bean:write
						name="cart" property="serialNo" />
					</td>
					<td>
					<div id='<bean:write name="indx" />desc'><bean:write
						name="cart" property="invDesc" /></div>
					</td>
					<td align="right">
					<div id='<bean:write name="indx" />iprice'><bean:write
						name="cart" property="uprice" /></div>
					</td>
					<td align="right">
					<div id='<bean:write name="indx" />amt'><bean:write
						name="cart" property="amount" /></div>
					</td>
					<td align="right">
					<div id='<bean:write name="indx" />wgt'><bean:write
						name="cart" property="weight" /></div>
					</td>
					<td align="center">
					<div id='<bean:write name="indx" />itax'><bean:write
						name="cart" property="tax" />
					</td>

					<td align="center"><img
						onclick="DeleteRow('<bean:write name="indx" />row',this.form);"
						src="${pageContext.request.contextPath}/images/delete.png"
						title="Delete this Item" size="8" /></td>
				</tr>
				<input type="hidden" id='<bean:write name="indx" />delt' value="0" />
				<input type="hidden" id='<bean:write name="indx" />rowVal'
					value='<bean:write name="indx" />row' />
				<input type="hidden" id='<bean:write name="indx" />invCode'
					value='<bean:write name="cart" property="invCode" />' />
				<input type="hidden" id='<bean:write name="indx" />qty'
					value='<bean:write name="cart" property="qty" />' />
				<input type="hidden" id='<bean:write name="indx" />invDesc'
					value='<bean:write name="cart" property="invDesc" />' />
				<input type="hidden" id='<bean:write name="indx" />uprice'
					value='<bean:write name="cart" property="uprice" />' />
				<input type="hidden" id='<bean:write name="indx" />weight'
					value='<bean:write name="cart" property="weight" />' />
				<input type="hidden" id='<bean:write name="indx" />tax'
					value='<bean:write name="cart" property="tax" />' />
				<input type="hidden" id='<bean:write name="indx" />serial'
					value='<bean:write name="cart" property="serialNo" />' />
				<input type="hidden" id='<bean:write name="indx" />itId11'
					value='<bean:write name="cart" property="itemTypeID" />' />

				<input type="hidden" id='<bean:write name="indx" />invID11'
					value='<bean:write name="cart" property="inventoryID" />' />
			</logic:iterate>
		</logic:present>
		<logic:notPresent name="Cart">
			<input type="hidden" name="hdncsize" id="CartSize" value="0">
		</logic:notPresent>
		<tr id="tr##">
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
		</tr>

	</table>
	</div>

	</div>
	</div>
	<div><bean:size id="txSize" name="Tax" /> <input type="hidden"
		name="LstSize" id="tSize" value='<bean:write name="txSize" />'>
	<logic:iterate name="Tax" id="objList" indexId="index">
		<input type="hidden"
			value='<bean:write name="objList" property="salesTaxID"/>'
			id='<bean:write name="index" />tx_id' />
		<input type="hidden"
			value='<bean:write name="objList" property="state"/>'
			id='<bean:write name="index" />tx' />
		<input type="hidden"
			value='<bean:write name="objList" property="rate"/>'
			id='<bean:write name="index" />tx_rt' />
	</logic:iterate></div>
	<table class="tabla-listados">
		<tr>
			<td nowrap="nowrap"><bean:message
				key="BzComposer.Invoice.Message" /> <html:select property="message"
				onclick="setIds();" style="width:250;">
				<html:option value="0">Select</html:option>
				<html:options collection="Message" property="value"
					labelProperty="label" />
			</html:select></td>
			<td nowrap><html:checkbox property="itemShipped"
				onclick="ShippedItem(this.form);">
				<bean:message key="BizComposer.Invoice.ItemShippped" />
			</html:checkbox></td>
			<td nowrap><html:checkbox property="paid"
				onclick="paidValue(this.form);">
				<bean:message key="BizComposer.Invoice.Paid" />
			</html:checkbox></td>
			<td align="right"><bean:message key="BzComposer.Invoice.Balanc" /></td>
			<td><html:text property="balance" size="10" readonly="true"
				onfocus="setIds();"></html:text></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2" align="right">
			<table>
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr valign="top">
					<td><bean:message key="BzComposer.Invoice.Wght" />&nbsp;&nbsp;&nbsp;</td>
					<td align="right"><html:text property="weight" size="13"
						onkeypress="return numbersonly(event,this.value)"
						onfocus="setIds();" /></td>
				</tr>

				<tr valign="middle">
					<td><bean:message key="BzComposer.Invoice.Tx" /></td>

					<td><html:select property="taxID"
						onchange="TaxValue(this.value,this.form);" onclick="setIds();">
						<html:option value="0">
							<bean:message key="BzComposer.ComboBox.Select" />
						</html:option>
						<html:options collection="Tax" property="salesTaxID"
							labelProperty="state" />
					</html:select> <logic:notEmpty name="Tax">
						<input type="hidden" value="" />
					</logic:notEmpty></td>

				</tr>

			</table>
			</td>
			<td colspan="2">
			<table>
				<tr>
					<td align="right"><bean:message
						key="BzComposer.Invoice.Subtotal" /></td>
					<td><html:text property="subtotal" readonly="true"
						onfocus="setIds();" /></td>
				</tr>
				<tr>
					<td align="right"><bean:message
						key="BzComposer.Invoice.Shipping" /></td>
					<td><html:text property="shipping"
						onkeypress="return numbersonly(event,this.value)"
						onfocus="setIds();" /></td>
				</tr>
				<tr>
					<td>
					<div id="tax_field"><bean:message
						key="BizComposer.Invoice.TaxField" /></div>
					</td>
					<td><html:text property="tax" readonly="true"
						onfocus="setIds();" /></td>
				</tr>
				<tr>
					<td align="right"><bean:message key="BzComposer.Invoice.Tot" /></td>
					<td><html:text property="total" readonly="true"
						onfocus="setIds();" /></td>
				</tr>
				<tr>
					<td align="right"><bean:message
						key="BzComposer.Invoice.AdjustedTotal" /></td>
					<td><html:text property="adjustedtotal"
						onkeypress="return numbersonly(event,this.value)"
						onfocus="setIds();" /></td>
					<td><input type="hidden" size="15" /></td>
				</tr>

			</table>
			</td>
	</table>
	<br>
	<hr>

	<div><input type="hidden" value="0" id="hidn" /> <input
		type="hidden" value="" id="code11" /> <input type="hidden" value=""
		id="invStyle" /> <input type="hidden" value="0" id="amt_id" /> <input
		type="hidden" value="0" id="tax_val" /> <input type="hidden"
		value="0" id="itmId" /> <input type="hidden" value="0" id="itmVal" />
	<input type="hidden" value="" id="cid" /> <html:hidden value="0"
		property="wt" /> <html:hidden property="companyID" /> <html:hidden
		property="bsAddressID" /></div>
	<div><html:hidden property="size" value="" /> <html:hidden
		property="item" value="" /> <html:hidden property="qty" value="" />
	<html:hidden property="serialNo" value="" /> <html:hidden
		property="desc" value="" /> <html:hidden property="unitWeight"
		value="" /> <html:hidden property="wgt" value="" /> <html:hidden
		property="uprice" value="" /> <html:hidden property="code" value="" />
	<html:hidden property="isTaxable" value="" /> <html:hidden
		property="itemTypeID" value="" /> <html:hidden property="itemOrder"
		value="" /></div>

	<!-- end Contents --></div>
	<div style="width: 855;margin-left: auto;margin-right: auto;">
	<table>
		<tr>
			<td width="15%">
			<table>
				<tr>
					<td><html:button property="New" title="New Invoice"
						styleClass="formbutton" onclick="NewInvoice();">
						<bean:message key="BzComposer.global.new" />
					</html:button></td>
					<td><html:button property="Save" title="Save Invoice"
						styleClass="formbutton" onclick="onSave(this.form);">
						<bean:message key="BzComposer.global.saveUpdate" />
					</html:button></td>
					<td><logic:present name="IsDisplay">
						<html:button property="Delete" title="Delete Invoice"
							styleClass="formbutton" disabled="true">
							<bean:message key="BzComposer.Invoice.DeleteBtn" />
						</html:button>
					</logic:present> <logic:notPresent name="IsDisplay">
						<html:button property="Delete" title="Delete Invoice"
							styleClass="formbutton" onclick="onDelete(this.form);"
							disabled="false">
							<bean:message key="BzComposer.Invoice.DeleteBtn" />
						</html:button>
					</logic:notPresent></td>
					<td><logic:present name="IsDisplay">
						<input type="button" name="Update" class="formbutton"
							id="updatecust" title="Update Customer information"
							disabled="disabled" onclick="ShowUpdate(this.form);"
							value='<bean:message key="BzComposer.Invoice.Update" />' />


					</logic:present> <logic:notPresent name="IsDisplay">
						<input type="button" name="Update" class="formbutton"
							title="Update Customer information"
							onclick="ShowUpdate(this.form);"
							value='<bean:message key="BzComposer.Invoice.Update" />' />


					</logic:notPresent></td>
				</tr>
			</table>
			</td>

			<td colspan="2" align="left">
			<table width="15%">
				<tr>
					<td align="right"><logic:present name="IsFirst">
						<html:button property="First" styleClass="formbutton"
							title="First Invoice Record" disabled="true">
							<bean:message key="BzComposer.Invoice.First" />
						</html:button>
					</logic:present> <logic:notPresent name="IsFirst">
						<html:button property="First" styleClass="formbutton"
							title="First Invoice Record" onclick="FirstInvoice();"
							disabled="false">
							<bean:message key="BzComposer.Invoice.First" />
						</html:button>
					</logic:notPresent></td>
					<td align="right"><logic:present name="IsDisplay">
						<html:button property="Previous" styleClass="formbutton"
							title="Previous Invoice Record" disabled="true">
							<bean:message key="BzComposer.Invoice.Previous" />
						</html:button>
					</logic:present> <logic:notPresent name="IsDisplay">
						<logic:present name="IsFirst">
							<html:button property="Previous" styleClass="formbutton"
								title="Previous Invoice Record" disabled="true">
								<bean:message key="BzComposer.Invoice.Previous" />
							</html:button>
						</logic:present>
						<logic:notPresent name="IsFirst">
							<html:button property="Previous" styleClass="formbutton"
								title="Previous Invoice Record" onclick="PreviousInvoice();"
								disabled="false">
								<bean:message key="BzComposer.Invoice.Previous" />
							</html:button>
						</logic:notPresent>
					</logic:notPresent></td>
					<td align="right"><logic:present name="IsDisplay">
						<html:button property="Next" styleClass="formbutton"
							title="Next Invoice Record" disabled="true">
							<bean:message key="BzComposer.Invoice.Next" />
						</html:button>
					</logic:present> <logic:notPresent name="IsDisplay">
						<logic:present name="IsLast">
							<html:button property="Next" styleClass="formbutton"
								title="Next Invoice Record" disabled="true">
								<bean:message key="BzComposer.Invoice.Next" />
							</html:button>
						</logic:present>
						<logic:notPresent name="IsLast">
							<html:button property="Next" styleClass="formbutton"
								title="Next Invoice Record" onclick="NextInvoice();"
								disabled="false">
								<bean:message key="BzComposer.Invoice.Next" />
							</html:button>
						</logic:notPresent>
					</logic:notPresent></td>
					<td align="right"><logic:present name="IsLast">
						<html:button property="Last" styleClass="formbutton"
							title="Last Invoice Record" disabled="true">
							<bean:message key="BzComposer.Invoice.Last" />
						</html:button>
					</logic:present> <logic:notPresent name="IsLast">
						<html:button property="Last" styleClass="formbutton"
							title="Last Invoice Record" onclick="LastInvoice();"
							disabled="false">
							<bean:message key="BzComposer.Invoice.Last" />
						</html:button>
					</logic:notPresent></td>

				</tr>
			</table>
			</td>



			<td align="right"><logic:present name="IsDisplay">
				<input type="button" name="payHistory" id="payhist"
					class="formbutton" title="Payment History"
					onclick="paymentHistory(this.form);" disabled="disabled"
					value='<bean:message key="BzComposer.Invoice.PayHistory" />' />


			</logic:present> <logic:notPresent name="IsDisplay">
				<input type="button" name="payHistory" d="payhist"
					class="formbutton" title="Payment History"
					onclick="paymentHistory(this.form);"
					value='<bean:message key="BzComposer.Invoice.PayHistory" />' />


			</logic:notPresent></td>
			<td align="right"><logic:present name="Enable">
				<html:button property="sendMail" styleClass="formbutton"
					title="Send Mail to..." onclick="SendMail(this.form);"
					disabled="false">
					<bean:message key="BzComposer.Invoice.SendMail" />
				</html:button>
			</logic:present> <logic:notPresent name="Enable">
				<html:button property="sendMail" styleClass="formbutton"
					title="Send Mail to..." onclick="SendMail(this.form);"
					disabled="true">
					<bean:message key="BzComposer.Invoice.SendMail" />
				</html:button>
			</logic:notPresent></td>

		</tr>

	</table></div>
	
	</div>
<div><input type="hidden" name="tabid" id="tab"
	value="ReceivedItm" /></div>
</html:form></div>
</div>
</div>
</div>

<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>

</body>
</html>
<script type="text/javascript">

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
		function setIds(){
			document.getElementById("list").innerHTML ="";
		}
		function paidValue(form){
			if(form.paid.checked==true){
				form.paid.value="on";
			}
			else{
				form.paid.value="off";
				
			}
			setIds();
		}
		function Pending_Value(form){
			if(form.isPending.checked==true){
				form.isPending.value="on";
			}
			else{
				form.isPending.value="off";
				
			}
			setIds();
		}
		function TaxaValue(form){
			if(form.taxable.checked==true){
				form.taxable.value="on";
			}
			else{
				form.taxable.value="off";
			}
			setIds();
		}
		
		function ShippedItem(form){
			if(form.itemShipped.checked==true){
				form.itemShipped.value="on";
			}
			else{
				form.itemShipped.value="off";
			}
			setIds();
		}
		
		function StyleChange(value){
			document.getElementById('invStyle').value=value;
			size=document.getElementById('CartSize').value;
			hidn_val= document.getElementById('hidn').value;
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
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
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
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
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
					document.getElementById("it2").style.display='none';
					document.getElementById("it3").style.visibility='visible';
					document.getElementById("it4").style.display='none';
					document.getElementById("it5").style.visibility='hidden';
					document.getElementById("it6").style.visibility='hidden';
					document.getElementById("it7").style.visibility='visible';
					document.getElementById("it8").style.visibility='hidden';
					document.getElementById("it22").style.display='block';
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
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
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
				document.getElementById('po_num_label').style.visibility="hidden";
				document.getElementById('po_num_id').style.visibility="hidden";
				
				/* hidden field for ship date */
				document.getElementById('sh_date_label').style.visibility="hidden";
				document.getElementById('sh_date_id').style.visibility="hidden";
				
				/* hidden field for via & rep */
				document.getElementById('td1').style.visibility="hidden";
				
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
				document.getElementById('po_num_label').style.visibility="hidden";
				document.getElementById('po_num_id').style.visibility="hidden";
				
				/* hidden field for ship date */
				document.getElementById('sh_date_label').style.visibility="hidden";
				document.getElementById('sh_date_id').style.visibility="hidden";
				
				/* hidden field for via & rep */
				document.getElementById('td1').style.visibility="hidden";
				
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
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
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
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
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
				document.getElementById('po_num_label').style.visibility="visible";
				document.getElementById('po_num_id').style.visibility="visible";
				
				/* visible field for ship date */
				document.getElementById('sh_date_label').style.visibility="visible";
				document.getElementById('sh_date_id').style.visibility="visible";
				
				/* Visible field for bill to */
				document.getElementById('bill_label').style.visibility="visible";
				document.getElementById('bill_id').style.visibility="visible";
				
				/* Visible field for Term & Payment */
				document.getElementById('td2').style.visibility="visible";	
				
				/* visible field for via & rep */
				document.getElementById('td1').style.visibility="visible";
				
					
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
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
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
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
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
				
				/* visible field for ship date */
				document.getElementById('sh_date_label').style.visibility="visible";
				document.getElementById('sh_date_id').style.visibility="visible";
				
				/* visible field for via & rep */
				document.getElementById('td1').style.visibility="visible";
				
				/* visible field for term & payment method */
				document.getElementById('td1').style.visibility="visible";
				
				/* hidden field for P.O. Num */
				document.getElementById('po_num_label').style.visibility="hidden";
				document.getElementById('po_num_id').style.visibility="hidden";
								
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
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
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
			setIds();
			if(form.itemID.value==0){
				document.getElementById('serialNo_id').value="";
				document.getElementById('qty_id').value="";
				document.getElementById('desc_id').value="";
				document.getElementById('unitPrice_id').value="";
				document.getElementById('amount_id').value="";
				document.getElementById('weight_id').value="";
				alert("<bean:message key='BzComposer.item.selectitemvalidation'/>");
			}
			else{		
				style = document.getElementById('invStyle').value;
				wt = form.weight.value;
				hidn_val= document.getElementById('hidn').value;
				
				
				var tr = document.createElement("tr");
				tr.setAttribute("id", "tr"+hidn_val);
			
				var tr2 = document.getElementById('tr##');
				var parentTr = tr2.parentNode;
				parentTr.insertBefore(tr, tr2);
				
				serialNo = document.getElementById('serialNo_id').value;
				var desc = document.getElementById('desc_id').value;

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
   				
   				var td2 = document.createElement("td");
				td2.setAttribute("align", "right");
				td2.setAttribute("id",hidn_val+"2");
				tr.appendChild(td2);
   				td2.innerHTML=qty;
								
				var td3 = document.createElement("td");
				td3.setAttribute("align", "right");
				td3.setAttribute("id",hidn_val+"3");
				tr.appendChild(td3);
				td3.innerHTML=serialNo;
				
				var td4 = document.createElement("td");
				td4.setAttribute("align", "left");
				td4.setAttribute("id",hidn_val+"4");
				tr.appendChild(td4);
   				td4.innerHTML=desc;
   				
   				
   				var td5 = document.createElement("td");
				td5.setAttribute("align", "right");
				td5.setAttribute("id",hidn_val+"5");
				tr.appendChild(td5);
   				td5.innerHTML=uprice;
			
				amt=( (qty/1) * (uprice/1) );
				document.getElementById('amount_id').value=amt.toFixed(2);
				
				var td6 = document.createElement("td");
				td6.setAttribute("align", "right");
				td6.setAttribute("id",hidn_val+"6");
				tr.appendChild(td6);
   				td6.innerHTML=amt.toFixed(2);;
   			
	   			var td7 = document.createElement("td");
				td7.setAttribute("align", "right");
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
				td8.setAttribute("align", "center");
				td8.setAttribute("id",hidn_val+"8");
				tr.appendChild(td8);
				td8.innerHTML=t;
				
				var button='<img onclick="DeleteRow1('+hidn_val+',this.form);"  src="${pageContext.request.contextPath}/images/delete.png" title="Delete" size="8"/>';
				
				var td9 = document.createElement("td");
				td9.setAttribute("align", "center");
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
					document.getElementById("it22").style.display='none';
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
					document.getElementById("it22").style.display='block';
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
					document.getElementById("it22").style.display='none';
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
			var size = document.getElementById('itemSize').value;
			var count;
			for(count=0;count<size;count++){
				var invID = document.getElementById(count+'inv').value;
				if(value==invID){
					var category = document.getElementById(count+'cat').value;
					if(category==1){
						//document.getElementById('serialNo_id').value="";
						document.getElementById('qty_id').value="";
						document.getElementById('desc_id').value="";
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
						document.getElementById('desc_id').value=document.getElementById(count+'desc').value;
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
			if(document.getElementById('qty_id').value==""){
				document.getElementById('qty_id').value = 1;
			}	
			qty=parseInt(document.getElementById('qty_id').value);

			var uprice = document.getElementById('unitPrice_id').value;
			
			var amount=qty*uprice;
			document.getElementById('amount_id').value=amount.toFixed(2);;				
			
		}
		
		function AddTotal(form){
			value=prompt("Enter Adjusted Amount","");
			form.adjustedtotal.value=value;
		}
				
		function Init(){
			<logic:present name="Style">
				StyleChange(<bean:write name="Style"/>);
			</logic:present>
			<logic:notPresent name="Style">
					StyleChange(document.InvoiceForm.invoiceStyle.value);
			</logic:notPresent>
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
			<logic:present name="TaxValue">
				yestax = <bean:write name="TaxValue" property="taxValue"/>;
				
				val = document.InvoiceForm.taxID.value;
				if(val!=0){
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
			</logic:present>
		}
	
		function onSave(form){
			No=form.orderNo.value;
			custnm=trim(document.getElementById('custNm').value);
			cid=form.custID.value;
			if(cid==0){
				alert('<bean:message key="BzComposer.Estimaion.cName.Validation" />');
				document.getElementById('custNm').value="";
				document.getElementById('custNm').focus();
				
			}
			else if(custnm==""){
				alert('<bean:message key="BzComposer.Estimaion.cName.Validation" />');
				document.getElementById('custNm').focus();
			}
			
			else{
				if(No.length==0 || No==0){
					alert('<bean:message key="BizComposer.Invoice.OrderNo.Validation" />');
					form.orderNo.focus();
				}
				else{
				  var x=window.confirm('<bean:message key="BizComposer.Invoice.SaveUpdate" />');
				  if (x){
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
									
					if(csize!=0){
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
							}
							else{
						
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
					document.getElementById('tab').value="SaveInvoice";
					document.getElementById('custID').value = cid;
					document.forms[0].action="ReceivedItem.do";
					document.forms[0].submit();
				  }
				}
			}	
		}
		
		function NewInvoice(){
			document.getElementById('tab').value="ReceivedItm";
			document.forms[0].action="ReceivedItem.do";
			document.forms[0].submit();
		}
		
		function onDelete(form){
			
			No=form.orderNo.value;
			if(No.length==0 || No==0){
				alert('<bean:message key="BizComposer.Invoice.OrderNo.Validation" />');
				form.orderNo.focus();
			}
			else{
				var x=window.confirm('<bean:message key="BzComposer.Invoice.Delete" />')
				if (x){
					document.getElementById('tab').value="DeleteInvoice";
					document.forms[0].action="ReceivedItem.do";
					document.forms[0].submit();
				}
			}
			
		}
		
		
		
		function ShowUpdate(form){
			custnm=trim(document.getElementById('custNm').value);
			cid=form.custID.value;
			if(cid==0){
				alert('<bean:message key="BzComposer.Estimaion.cName.Validation" />');
				document.getElementById('custNm').value="";
				document.getElementById('custNm').focus();
			}
			else if(custnm==""){
				alert('<bean:message key="BzComposer.Estimaion.cName.Validation" />');
				document.getElementById('custNm').focus();
			}
			else{
				window.open("EditCustomer.do?tabid=editCustomer&cvId="+cid,null,"scrollbars=yes,height=600,width=1200,status=yes,toolbar=no,menubar=no,location=no");
			
			}
		}
		function FirstInvoice(){
			document.getElementById('tab').value="FirstInvoice";
			document.forms[0].action="ReceivedItem.do";
			document.forms[0].submit();
			
		}
		function LastInvoice(){
			document.getElementById('tab').value="LastInvoice";
			document.forms[0].action="ReceivedItem.do";
			document.forms[0].submit();
		}
		
		function PreviousInvoice(){
			document.getElementById('tab').value="PreviousInvoice";
			document.forms[0].action="ReceivedItem.do";
			document.forms[0].submit();
		}
		
		function NextInvoice(){
			document.getElementById('tab').value="NextInvoice";
			document.forms[0].action="ReceivedItem.do";
			document.forms[0].submit();
		}
		
		function paymentHistory(form){
			custnm=trim(document.getElementById('custNm').value);
			cid=form.custID.value;
			if(cid==0){
				alert('<bean:message key="BzComposer.Estimaion.cName.Validation" />');
				document.getElementById('custNm').value="";
				document.getElementById('custNm').focus();
			}
			else if(custnm==""){
				alert('<bean:message key="BzComposer.Estimaion.cName.Validation" />');
				document.getElementById('custNm').value="";
				document.getElementById('custNm').focus();
			}
			else{
				window.open("Invoice.do?tabid=PaymentHistory&CustId="+cid,null,"scrollbars=yes,height=500,width=700,status=yes,toolbar=no,menubar=no,location=no" );
				
			}
		}
			
		
					
		function SendMail(form){
			cid=form.orderNo.value;
			window.open("Invoice.do?tabid=ShowEmail&OrderNo="+cid,null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );

		}
		
		function DeleteRow(d,form){
		response = window.confirm('<bean:message key="BzComposer.Estimaion.DeleteItem" />');
		if(response){	
			size=document.getElementById('CartSize').value;
			
			for(jj=0;jj<size;jj++){
			
				rowId=document.getElementById(jj+'rowVal').value;
					if(d==rowId){
						var rt=0;
						document.getElementById(d).style.display='none';
					
						document.getElementById(jj+'delt').value="del";
								
						qty1=document.getElementById(jj+'qty').value;
					
						uprice1=document.getElementById(jj+'uprice').value;
					
						amt = ((qty1)/1 * (uprice1)/1);
					

						wegt=document.getElementById(jj+'weight').value;
						w=document.InvoiceForm.weight.value;
						wg=( (w)/1 - (wegt)/1);
						document.InvoiceForm.weight.value=wg.toFixed(2);;
						
						subtotal= document.InvoiceForm.subtotal.value;
					
						subtotal = ((subtotal/1) - (amt/1));
						valu=document.InvoiceForm.taxID.value;
						subtotal=subtotal.toFixed(2);;
						document.InvoiceForm.subtotal.value=subtotal;
						sze=document.getElementById("tSize").value;	  	
						var taxid = document.getElementById(jj+"tax").value;					
						
						
   						for(i=0;i<sze;i++){
   						
						var field = document.getElementById(i+"tx_id").value;
							if(valu==field){
								if(taxid=="Yes"){
									
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
			}
		}
		
		function DeleteRow1(d,form){
		response = window.confirm('<bean:message key="BzComposer.Estimaion.DeleteItem" />');
		if(response){
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
		

						
						w=document.InvoiceForm.weight.value;
						wg=( (w)/1 - (wegt)/1);
						document.InvoiceForm.weight.value=wg.toFixed(2);
						
						subtotal= document.InvoiceForm.subtotal.value;
					
						subtotal = ((subtotal/1) - (amt/1));
						document.InvoiceForm.subtotal.value=subtotal.toFixed(2);
						tot=(document.InvoiceForm.shipping.value);
						tx = document.InvoiceForm.tax.value;
						if(t==1){
							yestax = (yestax - amt).toFixed(2);
							tx=(yestax * tax_rate)/100;
							document.InvoiceForm.tax.value=tx;
						}
						
						
						total = ((tot/1) + (subtotal/1) + (tx/1));
						document.InvoiceForm.total.value=total.toFixed(2);
						deleted++;
						
					}
				
				}
			}
			
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
		
		function PrintInvoice(form){
			orderNo=document.InvoiceForm.orderNo.value;
			if(orderNo=="" || orderNo==0 ){
				alert('<bean:message key="BizComposer.Invoice.OrderNo.Validation" />');
				document.InvoiceForm.orderNo.focus();
			}
			else{
				window.open("Invoice.do?tabid=ShowPrint&OrderNo="+orderNo,null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=yes,location=no" );
			}
		}
		

</script>
<script type="text/javascript">
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
</script>
