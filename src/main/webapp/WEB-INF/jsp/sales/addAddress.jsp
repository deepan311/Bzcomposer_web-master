<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Address</title>
<%@include file="/include/headlogo.jsp"%>
<%@include file="/include/header.jsp"%>
</head>
<script>
$(function() {
	var status = "<%= request.getParameter("chkStatus")%>"
	if(status == "Default")
	{
		$("#addressStatusDefault").prop('checked', true);	
		$("#addressStatusActive").prop('checked', true);
		$("#addressStatusActive").attr("disabled",true);
		$("#add").hide();
	}
	else
	{
		$("#addressStatusActive").prop('checked', true);
		$("#addressStatusDefault").prop('checked', false);
		$("#addressStatusActive").attr("disabled",false);
		$("#addressStatusDefault").attr("disabled",false);
		$("#update").hide();
	}
	
	$('#addressStatusDefault').change(function(){
		if($(this).prop('checked') == true)
		{
			$(this).prop('checked',true);
		}
		else
		{
			if(confirm("No Default address available,Do you want to Make it default."))
			{
				$(this).prop('checked', true);	
				$("#addressStatusActive").prop('checked', true);
				$("#addressStatusActive").attr("disabled",true);
			}
			else
			{
				$(this).prop('checked', false);
				$("#addressStatusActive").prop('checked', true);
				$("#addressStatusActive").attr("disabled",false);
			}
		}
	});
});
var funsequence = 0;
var _1 = navigator.userAgent.toLowerCase();
var ___ = (_1.indexOf("msie") != -1);
var ___5 = (_1.indexOf("msie 5") != -1);
var _io = (_1.indexOf("opera") != -1);
var _im = (_1.indexOf("mac") != -1);
var ____gi = (_1.indexOf("gecko") != -1);
var i____s = (_1.indexOf("safari") != -1);
var o = null;
var o1 = null;
var o2 = null;
var o3 = null;
var r = null;

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
function writeSelect(){
   if (o.readyState != 4 || o.status != 200) { 
      return;
   }
    document.getElementById("t_statedata").innerHTML = o.responseText ;
}
function refreshItemsNow(val){
  o = c(writeSelect);
  oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=state&Cid=' + val)
}

function submitFormUpdate(form)
{
	debugger;
	//var addStatus = null;
	debugger;
	if($('#addressStatusActive').prop('checked',true) && $('#addressStatusActive').prop('disabled',false) && $('#addressStatusDefault').prop('checked',false))
	{
		debugger
		status = "Active";
	}
	else
	{
		debugger
		status = "Default";
	}
	var st = status;
	var cName = form.cname.value;
	var fName = form.firstName.value;
	var lName = form.lastName.value;
	var addressName = form.title.value;
	var add1 = form.address1.value;
	var add2 = form.address2.value;
	var city = form.city.value;
	var country = form.country.value;
	var state = form.sid.value;
	var province = form.province.value;
	var zipCode = form.zCode.value;
	var phone = form.phone.value;
	var fax = form.fax.value;
	debugger
	document.getElementById("status1").value=st;
	debugger;

	document.forms[0].action="Customer.do?tabid=addNewAddress&status="+st;
	document.getElementById("tabid").value="addNewAddress";
	document.forms[0].submit();
	//this.window.close();
}

function showSupplierForm()
{
	window.location.href = "Customer.do?tabid=addSupplier";
}
function submitFormAdd(form)
{
	debugger;
	if($('#addressStatusActive').prop('checked',true) && $('#addressStatusActive').prop('disabled',false) && $('#addressStatusDefault').prop('checked',false))
	{
		debugger
		status = "Active";
	}
	else
	{
		debugger
		status = "Default";
	}

	var cName = form.cname.value;
	var fName = form.firstName.value;
	var lName = form.lastName.value;
	var addressName = form.title.value;
	var add1 = form.address1.value;
	var add2 = form.address2.value;
	var city = form.city.value;
	var country = form.country.value;
	var state = form.sid.value;
	var province = form.province.value;
	var zipCode = form.zCode.value;
	var phone = form.phone.value;
	var fax = form.fax.value;
	var st = status;
	debugger
	document.getElementById("status").value=status;
	//$("#addressForm").submit();
	
	//window.location.href = "Customer.do?tabid=addNewAddress&&status="+status;
	document.forms[0].action="Customer.do?tabid=addNewAddress&status="+st;
	debugger
	document.getElementById("tabid").value="addNewAddress"; 
	debugger
	document.forms[0].submit();
	debugger
	//this.window.close();
}
</script>
<script>
function setState(state_id,name){
	if(name == 'state'){
		document.CustomerForm.state.value = state_id;
	}
	else if(name == 'bsstate'){
		document.CustomerForm.bsstate.value = state_id;
		document.getElementById('bsst').value = state_id;
	}
	else if(name == 'shstate'){
		document.CustomerForm.shstate.value = state_id;
	}
}

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
<body>
<div id="ddcolortabsline">
&nbsp;
</div>
<html:form styleId="addressForm" action="Customer.do?" enctype="MULTIPART/FORM-DATA" method="post">
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div>
	<span style="font-size:1.1em;font-weight:normal;color:#838383;margin:30px 0px 15px 0px;border-bottom:1px dotted #333;padding:0 0 .3em 0;">
		Address Dialog
	</span>
</div>
<br/>
<table cellpadding="0" cellspacing="0" border="0" align=center  style="width: 100%;">	
	<logic:present name="Status">
		<tr>
			<td colspan="4">
				<span class="msgstyle">*
					<bean:write name="Status" />
				</span>
			</td>
		</tr>
	</logic:present>
	<logic:present name="SaveStatus">
		<tr>
			<td colspan="4">
				<span class="msgstyle">*
					<bean:write name="SaveStatus"/>
				</span>
			</td>
		</tr>
	</logic:present>
	<tr>
	<td colspan="4">
		<div id="table-negotiations" style="overflow:auto;width:100%">
			<table class="tabla-listados" cellspacing="0" align="left">
				<thead>
				<tr>
					<th colspan="4" style="font-size:1.3em;">
						Insert New Address
					</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td width="20%" style="font-size: 1em;">
						Address Name
						<span class="highlighted">*</span>
					</td>
					<td style="font-size: 1em;">
						<!-- <input type="text" id="addressName" size="60"/> -->
						<html:text property="title" size="60" styleId="address"></html:text>
					</td>
					<td width="10%" align="left" style="font-size: 1em;">
						<!-- <input type="checkbox" id="Default" value="Default">Default -->
						<html:checkbox property="status" value="Default" styleId="addressStatusDefault">Default</html:checkbox>
					</td>
					<td width="60%">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td width="20%">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td align="left" style="font-size: 1em;">
						<!-- <input type="checkbox" id="Active" value="Active" checked>Active -->
						<html:checkbox property="status" value="Active" styleId="addressStatusActive">Active</html:checkbox>
					</td>
					<td  width="60%">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td width="20%" style="font-size: 1em;">
						<bean:message key="BzComposer.Vendor.Company"/>
						<span class="highlighted">*</span>
					</td>
					<td style="font-size: 1em;">
						<html:text property="cname" styleId="companyName" size="90"/>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="font-size: 1em;">
						<bean:message key="MultiUserForm.firstName"/>
						<span class="highlighted">*</span>
					</td>
					<td style="font-size: 1em;">
						<html:text property="firstName" styleId="firstName" size="90"/>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="font-size: 1em;">
						<bean:message key="MultiUserForm.lastName"/>
						<span class="highlighted">*</span>
					</td>
					<td style="font-size: 1em;">
						<html:text property="lastName" styleId="lastName" size="90"/>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="font-size: 1em;">
						<bean:message key="BzComposer.Customer.Address1"/>
						<span class="highlighted">*</span>
					</td>
					<td style="font-size: 1em;">
						<html:text property="address1" size="90" styleId="address1"/>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="font-size: 1em;">
						<bean:message key="BzComposer.Customer.Address2"/>
					</td>
					<td style="font-size: 1em;">
						<html:text property="address2" styleId="address2" size="90"/>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="font-size: 1em;">
						<bean:message key="BzComposer.Customer.City"/>
						<span class="highlighted">*</span>
					</td>
					<td style="font-size: 1em;">
						<html:text property="city" styleId="city" size="90"/>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td id="t_state" style="font-size: 1em;">
						<bean:message key="BzComposer.Customer.State" />
					</td>
					<td id="t_statedata" style="font-size: 1em;">
					</td>
					<script>
	       				document.CustomerForm.shcountry.value="239";
	       				refreshItemsNow("239");
					</script>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="font-size: 1em;">
						<bean:message key="BzComposer.Customer.Province"/>
					</td>
					<td style="font-size: 1em;">
						<!-- <input type="text" id="province" size="90"/> -->
						<html:text property="province" styleId="province" size="90"></html:text>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="font-size: 1em;">
						<bean:message key="BzComposer.Customer.ZipCode"/>
						<span class="highlighted">*</span>
					</td>
					<td style="font-size: 1em;">
						<html:text onkeypress="return numbersonly(event,this.value)"  styleId="zCode" property="zipCode" size="90"/>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="font-size: 1em;">
						<bean:message key="BzComposer.Customer.Country"/>
						<span class="highlighted">*</span>
					</td>
					<td style="font-size: 1em;">
						<html:select property="country" styleId="country" onchange="refreshItemsNow(this.value);" onblur="refreshItemsNow(this.value);">
							<html:option value="0">
								<bean:message key="BzComposer.ComboBox.Select" />
							</html:option>
							<html:options collection="cList" property="value" labelProperty="label" />
						</html:select>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="font-size: 1em;">
						<bean:message key="BzComposer.Customer.Phone"/>
					</td>
					<td style="font-size: 1em;">
						<html:text onkeypress="return numbersonly(event,this.value)" property="phone" size="90" styleId="phone"/>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="font-size: 1em;">
						<bean:message key="BzComposer.Customer.Fax"/>
					</td>
					<td style="font-size: 1em;">
						<html:text property="fax" onkeypress="return numbersonly(event,this.value)" styleId="fax" size="90"/>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<!-- <button type="submit" id="add" class="formbutton" value="Add" onclick="">Add</button> -->
						<html:submit styleId="add" property="Save" title="Save Address" Class="formbutton" onclick="submitFormAdd(this.form);">
							Add
						</html:submit>
						<!-- <button type="button" id="update" class="formbutton" value="Update" onclick="submitFormUpdate(this.form);">Update</button> -->
						<html:submit styleId="update" property="update" title="Save Address" Class="formbutton" onclick="submitFormUpdate(this.form);">
							Update
						</html:submit>
						&nbsp;&nbsp;
						<button type="button" id="close" class="formbutton" value="Cancel" onclick="showSupplierForm();">Cancel</button>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				</tbody>
			</table>
			<input type="hidden" value="0" id="status" name="status"/>
			<input type="hidden" value="0" id="status1" name="status1"/>
			<input type="hidden" value="0" id="tabid" name="tabid"/>  
		</div>
	</td>
	</tr>
	<tr>
		<td colspan="4">
			&nbsp;
		</td>
	</tr>
	</table>
</div>
</div>
</div>
</div>
</div>
</html:form>
</body>
</html>