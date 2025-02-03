<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/include/header.jsp"%>
<title><bean:message key="BzComposer.Title.AddNewCustomer" /></title>
</head>
<body>
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
function writeSelect1(){
   if (o1.readyState != 4 || o1.status != 200) { 
      return;
    }
    document.getElementById("t_statedata1").innerHTML = o1.responseText ;
}
function refreshItemsNow1(val){
  o1 = c(writeSelect1);
  oGET(o1,'${pageContext.request.contextPath}/include/GetStates.jsp?st=bsstate&Cid=' + val)
}
function writeSelect2(){
   if (o2.readyState != 4 || o2.status != 200) { 
      return;
    }
   document.getElementById("t_statedata2").innerHTML = o2.responseText ;
}
function refreshItemsNow12(val,sval){
  o2 = c(writeSelect2);
  oGET(o2,'${pageContext.request.contextPath}/include/GetStates.jsp?st=shstate&Cid=' + val+"&sval="+sval)
}
function refreshItemsNow2(val)
{
  o2 = c(writeSelect2);
  oGET(o2,'${pageContext.request.contextPath}/include/GetStates.jsp?st=shstate&Cid=' + val)
}
</script>
<script type="text/javascript">
  function validate() {
  	var email = document.CustomerForm.email.value;
  	var mail =String(email);
  	var pattern=/^[_0-9a-zA-z]+(\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\.[A-Za-z]+)+$/;
  	if(email==""){
  	}
    else if (!pattern.test(email)) {
        alert('<bean:message key="BzComposer.NewCustomer.Email.Validation" />');
       	document.CustomerForm.email.focus();
        return false;
    }
   if(mail.length>=50){
    	alert('<bean:message key="BzComposer.NewCustomer.EmailLength.Validation" />');
    	document.CustomerForm.email.value="";
    	document.CustomerForm.email.focus();
        return false;
    }
  
   	return true;
  }
</script>
<!-- begin shared/header -->

<div id="header">
<div id="header">
<div id="header-content">
<ul id="ulnav">
	<li><a
		href="${pageContext.request.contextPath}/Sales.do?tabid=SalesBoard"
		title="Sales" class="selected" rel="section"><bean:message
		key="BzComposer.Sales" /></a></li>
	<li><a
		href="${pageContext.request.contextPath}/PurchaseOrder.do?tabid=PurchaseOrder"
		title="Purchase" rel="section"><bean:message key="BzComposer.Purchase" /></a></li>
	<li><a href="employee.do" title="Employee" rel="section"><bean:message
		key="BzComposer.Employee" /></a></li>
	<li><a
		href="${pageContext.request.contextPath}/#"
		title="Accounting" rel="section"><bean:message
		key="BzComposer.Accounting" /></a></li>
	<li><a
		href="${pageContext.request.contextPath}/RMA.do?tabid=R0M0A0"
		title="RMA" rel="section"><bean:message key="BzComposer.RMA" /></a></li>
	<li><a
		href="${pageContext.request.contextPath}/#"
		title="Email" rel="section"><bean:message key="BzComposer.Email" /></a></li>
	<li><a
		href="${pageContext.request.contextPath}/Configuration.do?tabid=config"
		title="Confuguration" rel="section"><bean:message
		key="BzComposer.Confuguration" /></a></li>
</ul>

<div class="clear"></div>

</div>

</div>

<div id="ddcolortabs">
<ul>
	<li style="margin-left: 1px"><a
		href="${pageContext.request.contextPath}/Sales.do?tabid=SalesBoard"
		title=""><span><bean:message key="BzComposer.sales.SalesBoard" /></span></a></li>
	<li><a href="Invoice.do?tabid=Invoice" title=""><span><bean:message
		key="BzComposer.sales.Invoice" /></span></a></li>
	<li><a href="Estimation.do?tabid=Estimation" title=""><span><bean:message
		key="BzComposer.sales.Estimation" /></span></a></li>
	<li id="current"><a href="Customer.do?tabid=Customer" title=""><span><bean:message
		key="BzComposer.sales.Customer" /></span></a></li>
	<li><a href="Item.do?tabid=Item" title=""><span><bean:message
		key="BzComposer.sales.Item" /></span></a></li>
	<li><a href="DataManager.do?tabid=datamanager" title=""><span><bean:message
		key="BzComposer.sales.DataManager" /></span></a></li>
</ul>
</div>
<div id="ddcolortabsline">&nbsp;</div>
<div id="pointermenu">
<ul>
	<li><a href="Customer.do?tabid=Customer"><bean:message
		key="BzComposer.customer.Customer" /></a></li>
	<li><a href="Customer.do?tabid=NewCutomer" id="selected"> <bean:message
		key="BzComposer.customer.AddNewCutomer" /></a></li>
	<li><a href="Customer.do?tabid=PrintLabels"><bean:message
		key="BzComposer.customer.PrintLabels" /></a></li>
</ul>
</div>
</div>
<html:form action="Customer.do?tabid=AddCustomer" method="post">
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">

	<div id=""><!-- begin Contents --> <!-- add the code for tab here -->
	<div style="float:left; width: 50%;">
	<table cellpadding="0" cellspacing="0" border="0" align=left>
		<tr>
			<td valign=top height=21><script type="text/javascript"
				src="${pageContext.request.contextPath}/scripts/addvendortabs.js"></script></td>
		</tr>
		<tr>
			<td class="pageWin">&nbsp;
			<table cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td valign=top class="tabPage">
					<div id="content1" class="tabPage"><!-- add here the content of first tab -->

					<div id="table-negotiations">
					<table class="tabla-listados" cellspacing="0" style="width: 81%;">
						<tbody>
							<tr>
								<td><bean:message key="BzComposer.Customer.Company" /><span
									class="inputHighlighted"><bean:message
									key="BzComposer.CompulsoryField.Validation" /></span></td>
								<td><html:text property="cname" /></td>
								<td><bean:message key="BzComposer.Customer.StartDate" /></td>
								<td><html:text property="dateAdded" readonly="true" /> <img
									src="${pageContext.request.contextPath}/images/cal.gif"
									onclick="displayCalendar(document.CustomerForm.dateAdded,'mm-dd-yyyy',this);">
								</td>
							</tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.Title" /></td>
								<td><html:select property="title">
									<html:options collection="titleList" property="value"
										labelProperty="label" />
								</html:select></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.FirstName" /><span
									class="inputHighlighted"><bean:message
									key="BzComposer.CompulsoryField.Validation" /></span></td>
								<td><html:text property="firstName" /></td>
								<td><bean:message key="BzComposer.Customer.LastName" /><span
									class="inputHighlighted"><bean:message
									key="BzComposer.CompulsoryField.Validation" /></span></td>
								<td><html:text property="lastName" /></td>
							</tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.Address1" /><span
									class="inputHighlighted"><bean:message
									key="BzComposer.CompulsoryField.Validation" /></span></td>
								<td colspan="3"><html:text property="address1" /></td>
							<tr>
								<td><bean:message key="BzComposer.Customer.Address2" /></td>
								<td colspan="3"><html:text property="address2" /></td>
							</tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.City" /><span
									class="inputHighlighted"><bean:message
									key="BzComposer.CompulsoryField.Validation" /></span></td>
								<td><html:text property="city" /></td>
								<td><bean:message key="BzComposer.Customer.ZipCode" /><span
									class="inputHighlighted"><bean:message
									key="BzComposer.CompulsoryField.Validation" /></span></td>
								<td><html:text onkeypress="return numbersonly(event,this.value)"
									property="zipCode" /></td>
							</tr>
							<tr>
								<td id="t_country"><bean:message
									key="BzComposer.Customer.Country" /></td>
								<td><html:select property="country"
									onchange="refreshItemsNow(this.value);"
									onblur="refreshItemsNow(this.value);">
									<html:option value="0">
										<bean:message key="BzComposer.ComboBox.Select" />
									</html:option>
									<html:options collection="cList" property="value"
										labelProperty="label" />
								</html:select></td>
								<td id="t_state"><bean:message key="BzComposer.Customer.State" /></td>
								<td id="t_statedata"></td>
							</tr>
							<script>
						        document.CustomerForm.country.value="239";
						        refreshItemsNow("239");
							</script>
							<tr>
								<td><bean:message key="BzComposer.Customer.Province" /></td>
								<td><html:text property="province" /></td>
								<td><bean:message key="BzComposer.Customer.Fax" /></td>
								<td><html:text property="fax"
									onkeypress="return numbersonly(event,this.value)" /></td>
							</tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.Phone" /></td>
								<td><html:text onkeypress="return numbersonly(event,this.value)"
									property="phone" /></td>
								<td><bean:message key="BzComposer.Customer.CellPhone" /></td>
								<td><html:text onkeypress="return numbersonly(event,this.value)"
									property="cellPhone" /></td>
							</tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.HomePage" /></td>
								<td><html:text property="homePage" /></td>
								<td><bean:message key="BzComposer.Customer.Email" /></td>
								<td><html:text property="email" /></td>
							</tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.Type" /></td>
								<td><html:select property="type">
									<html:option value="0">
										<bean:message key="BzComposer.ComboBox.Select" />
									</html:option>
									<html:options collection="VendorCategoryList" property="value"
										labelProperty="label" />
								</html:select></td>
								<td><bean:message key="BzComposer.Customer.TaxID" /></td>
								<td><html:text property="texID" /></td>
							</tr>
							<tr>
								<td><bean:message
									key="BzComposer.Customer.OppeningUnpaidBalance" /></td>
								<td><html:text property="openingUB"
									onkeypress="return numbersonly(event,this.value)" /></td>
								<td colspan="2"><input value="on" type="checkbox"
									name="isTaxable" id="chktax"> <bean:message
									key="BzComposer.Customer.isTaxable" /></td>
							</tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.ExistingCredits" /></td>
								<td><html:text property="extCredit"
									onkeypress="return numbersonly(event,this.value)" /></td>
								<td colspan="2"><input value="on" type="checkbox"
									name="isAlsoClient" id="chk_alsovendor"> <bean:message
									key="BzComposer.Customer.isAlsoVendor" /></td>
							</tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.Memo" /></td>
								<td colspan="3"><html:textarea cols="80" property="memo" /></td>
							</tr>
						</tbody>
					</table>
					</div>
					</div>
					</td>
				</tr>
				<tr>
					<td valign=top class="tabPage">
					<div id="content2" class="tabPage"><!-- add here the content of first tab -->


		
				
					</div>
					</td>
				</tr>

				<tr>
					<td valign=top class="tabPage">
					<div id="content3" class="tabPage"><!-- add here the content of first tab -->

					
					</div>
					</td>
				</tr>
				<tr>
					<td valign=top class="tabPage">
					<div id="content4" class="tabPage"><!-- add here the content of first tab -->
					<div id="table-negotiations" style="width:567">

					<table class="tabla-listados" cellspacing="0" >
						<thead>
							<tr>
								<td><bean:message key="BzComposer.Customer.FinanceMsg" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><input value="on" type="checkbox" id="chk_useind"
									name="UseIndividualFinanceCharges"
									onclick="hideother(this.form);"><font size="2"><bean:message
									key="BzComposer.Customer.UseIndividualFinanceCharges" /></font></td>
							<tr>
						</thead>
						<tbody>
							<tr>
								<td>
								<div id="table-negotiations" style="width:567">
								<table class="tabla-listados" cellspacing="0">
									<thead>
										<tr>
											<td colspan="4"><bean:message
												key="BzComposer.Customer.ChargeRate" /></td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><bean:message key="BzComposer.Customer.AnnualIntrestRate" /></td>
											<td colspan="3"><html:text
												onkeypress="return numbersonly(event,this.value)"
												property="annualIntrestRate" disabled="true" /></td>
										</tr>
										<tr>
											<td><bean:message
												key="BzComposer.Customer.MinimumFinanaceCharge" /></td>
											<td colspan="3"><html:text
												onkeypress="return numbersonly(event,this.value)"
												disabled="true" property="minFCharges" /></td>
										</tr>

										<tr>
											<td><bean:message key="BzComposer.Customer.GracePeriod" /></td>
											<td colspan="3"><html:text
												onkeypress="return numbersonly(event,this.value)"
												disabled="true" property="gracePrd" /></td>
										</tr>

									</tbody>
								</table>
								</div>
								<div id="table-negotiations" style="width:567" >
								<table class="tabla-listados" cellspacing="0">
									<thead>
										<tr>
											<td><bean:message key="BzComposer.Customer.ApplyingCharges" /></td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="4">&nbsp;</td>
										</tr>
										<tr>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td colspan="4"><input value="on" type="checkbox" id="chk1"
												name="AssessFinanceChk" disabled="disabled"> <bean:message
												key="BzComposer.Customer.AssessFinance" /></td>
										</tr>
										<tr>
											<td colspan="4"><input value="on" type="checkbox" id="chk2"
												disabled="disabled" name="FChargeInvoiceChk"><bean:message
												key="BzComposer.Customer.MarkFinanaceChargeInvoice" /></td>
										</tr>
									</tbody>
								</table>
								</div>
								</td>
							</tr>
					</table>
					</div>
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<script>
			count=0;
			cnt=0;
			var exist= new Array(10); 
			for (i=0; i<exist.length; i++){
				exist[i]=-1;
			}
		</script>
		<tr>
			<td valign=top class="tabPage">
			<div id="content7" class="tabPage"><html:hidden
				property="table_serviceName" value="" /> <html:hidden
				property="table_defaultVal" value="0" /> <html:hidden
				property="table_size" value="0" /> <html:hidden
				property="table_invId" value="" /> <html:hidden
				property="table_bal" value="" /> <html:hidden
				property="table_serID" value="" /> <html:hidden
				property="table_DbDefSer" value="0" /> <bean:size
				name="ServiceList" id="SList" /> <input type="hidden" name="ssize"
				id="sSize" value='<bean:write name="SList"/>'> <logic:iterate
				name="ServiceList" id="objList1" indexId="index">
				<input type="hidden"
					value='<bean:write name="objList1" property="serviceID"/>'
					id='<bean:write name="index" />sid' />
				<input type="hidden"
					value='<bean:write name="objList1" property="serviceName"/>'
					id='<bean:write name="index" />sname' />
				<input type="hidden"
					value='<bean:write name="objList1" property="invoiceStyleId"/>'
					id='<bean:write name="index" />isid' />
			</logic:iterate> <bean:size name="InvoiceName" id="IName" /> <input
				type="hidden" name="isize" id="iSize"
				value='<bean:write name="IName"/>'> <logic:iterate
				name="InvoiceName" id="objList2" indexId="index">
				<input type="hidden"
					value='<bean:write name="objList2" property="invoiceStyleId"/>'
					id='<bean:write name="index" />invid' />
				<input type="hidden"
					value='<bean:write name="objList2" property="invoiceStyle"/>'
					id='<bean:write name="index" />iname' />
			</logic:iterate> <logic:present name="BalenceDetails" scope="request">
				<bean:size name="BalenceDetails" id="BDetails" />
				<input type="hidden" name="dsize" id="dSize"
					value='<bean:write name="BDetails"/>'>
				<logic:iterate name="BalenceDetails" id="objList3" indexId="index">
					<input type="hidden"
						value='<bean:write name="objList3" property="serviceBalance"/>'
						id='<bean:write name="index" />bal' />
					<input type="hidden"
						value='<bean:write name="objList3" property="defaultService"/>'
						id='<bean:write name="index" />dservice' />
					<input type="hidden"
						value='<bean:write name="objList3" property="serviceID"/>'
						id='<bean:write name="index" />srid' />
					<input type="hidden"
						value='<bean:write name="objList3" property="clientVendorID"/>'
						id='<bean:write name="index" />cvID' />
				</logic:iterate>
			</logic:present>
	
			</div>
			</td>
		</tr>
		<tr>
			<td align="center"></td>
		</tr>
	</table>
	</div>
	<div style="float: left;width:50%;">
	
		<div id="table-negotiations">
		<br/><br/>
		<font id="apy_b0i2font" style="color:#38678B;font:normal 10px Tahoma;text-decoration:">Billing &amp; Shipping Address</font>
					<table>
						<tr>
							<td>

							<table class="tabla-listados" cellspacing="0" width="300"
								align="left">
								<tbody>
									<tr>
										<td><bean:message key="BzComposer.Customer.Company" /></td>
										<td><html:text property="bscname" /></td>

									</tr>

									<tr>
										<td><bean:message key="BzComposer.Customer.FirstName" /></td>
										<td><html:text property="bsfirstName" /></td>
									</tr>
									<tr>
										<td><bean:message key="BzComposer.Customer.LastName" /></td>
										<td><html:text property="bslastName" /></td>
									</tr>
									<tr>
										<td><bean:message key="BzComposer.Customer.Address1" /></td>
										<td colspan="3"><html:text property="bsaddress1" /></td>
									<tr>
										<td><bean:message key="BzComposer.Customer.Address2" /></td>
										<td colspan="3"><html:text property="bsaddress2" /></td>
									</tr>

									<tr>
										<td><bean:message key="BzComposer.Customer.City" /></td>
										<td><html:text property="bscity" /></td>
									</tr>
									<tr>
										<td><bean:message key="BzComposer.Customer.ZipCode" /></td>
										<td><html:text
											onkeypress="return numbersonly(event,this.value)"
											property="bszipCode" /></td>
									</tr>

									<tr>

										<td id="t_country"><bean:message
											key="BzComposer.Customer.Country" /></td>
										<td><html:select property="bscountry"
											onchange="refreshItemsNow1(this.value);"
											onblur="refreshItemsNow1(this.value);">

											<html:option value="0">
												<bean:message key="BzComposer.ComboBox.Select" />
											</html:option>
											<html:options collection="cList" property="value"
												labelProperty="label" />
										</html:select></td>
									</tr>
									<tr>
										<td id="t_state"><bean:message key="BzComposer.Customer.State" /></td>
										<td id="t_statedata1"></td>

									</tr>
									<script>
								        document.CustomerForm.bscountry.value="239";
								        refreshItemsNow1("239");
									</script>

									<tr>
										<td><bean:message key="BzComposer.Customer.Province" /></td>
										<td><html:text property="bsprovince" /></td>

									</tr>
									<tr>
										<td colspan="2"><input type="button" class="formbutton"
											name="ClearBillingBtn" value="Clear Billing Address"
											onclick="clearBillingAdd();" title="Clear Billing Address"></td>
							</table>
							<td><input type="button" name="CopyBtn" class="formbutton"
								onclick="CopyBilladdToShipAdd();" value=">>Copy>>"
								title="Copy Address"></td>

							<td>

							<table class="tabla-listados" cellspacing="0" width="300"
								align="left">
								<tbody>
									<tr>
										<td><bean:message key="BzComposer.Customer.Company" /></td>
										<td><html:text property="shcname" /></td>

									</tr>

									<tr>
										<td><bean:message key="BzComposer.Customer.FirstName" /></td>
										<td><html:text property="shfirstName" /></td>
									</tr>
									<tr>
										<td><bean:message key="BzComposer.Customer.LastName" /></td>
										<td><html:text property="shlastName" /></td>
									</tr>
									<tr>
										<td><bean:message key="BzComposer.Customer.Address1" /></td>
										<td colspan="3"><html:text property="shaddress1" /></td>
									<tr>
										<td><bean:message key="BzComposer.Customer.Address2" /></td>
										<td colspan="3"><html:text property="shaddress2" /></td>
									</tr>

									<tr>
										<td><bean:message key="BzComposer.Customer.City" /></td>
										<td><html:text property="shcity" /></td>
									</tr>
									<tr>
										<td><bean:message key="BzComposer.Customer.ZipCode" /></td>
										<td><html:text
											onkeypress="return numbersonly(event,this.value)"
											property="shzipCode" /></td>
									</tr>

									<tr>

										<td id="t_country"><bean:message
											key="BzComposer.Customer.Country" /></td>
										<td><html:select property="shcountry"
											onchange="refreshItemsNow2(this.value);"
											onblur="refreshItemsNow2(this.value);">

											<html:option value="0">
												<bean:message key="BzComposer.ComboBox.Select" />
											</html:option>
											<html:options collection="cList" property="value"
												labelProperty="label" />
										</html:select></td>
									</tr>
									<tr>
										<td id="t_state"><bean:message key="BzComposer.Customer.State" /></td>
										<td id="t_statedata2"></td>

									</tr>
									<script>
								        document.CustomerForm.shcountry.value="239";
								        refreshItemsNow2("239");
									</script>
									<tr>
										<td><bean:message key="BzComposer.Customer.Province" /></td>
										<td><html:text property="shprovince" /></td>

									</tr>
									<tr>
										<td colspan="2"><input type="button" class="formbutton"
											name="ClearShippingBtn" onclick="clearShippingAdd();"
											value="Clear Shipping Address" title="Clear Shipping Address"></td>
									</tr>
								</tbody>
							</table>
							</td>
						</tr>
					</table>
					</div>
								<div id="table-negotiations" style="width:567">
					<table class="tabla-listados" cellspacing="0">
						<thead>
							<tr>
								<th colspan="4"><bean:message
									key="BzComposer.Customer.PrefrenceInformation" /></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><bean:message key="BzComposer.Customer.term" /></td>
								<td colspan="3"><html:select property="term">
									<html:option value="">
										<bean:message key="BzComposer.ComboBox.Select" />
									</html:option>
									<html:options collection="TermList" property="value"
										labelProperty="label" />
								</html:select></td>
							<tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.Rep" /></td>
								<td colspan="3"><html:select property="rep">
									<html:option value="">
										<bean:message key="BzComposer.ComboBox.Select" />
									</html:option>
									<html:options collection="RepList" property="value"
										labelProperty="label" />
								</html:select></td>
							<tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.PayMethod" /></td>
								<td colspan="3"><html:select property="paymentType">
									<html:option value="">
										<bean:message key="BzComposer.ComboBox.Select" />
									</html:option>
									<html:options collection="PaymentList" property="value"
										labelProperty="label" />
								</html:select></td>
							<tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.ShippingVia" /></td>
								<td colspan="3"><html:select property="shipping">
									<html:option value="">
										<bean:message key="BzComposer.ComboBox.Select" />
									</html:option>
									<html:options collection="ShipCarrierList" property="value"
										labelProperty="label" />
								</html:select></td>
							<tr>
						</tbody>
					</table>
					</div>
						<div id="table-negotiations" style="width:570">
					<table class="tabla-listados" cellspacing="0">
						<thead>
							<tr>
								<th colspan="4"><bean:message
									key="BzComposer.Customer.CreditCardInformation" /></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><bean:message key="BzComposer.Customer.CardType" /></td>
								<td colspan="3"><html:select property="ccType">
									<html:option value="">
										<bean:message key="BzComposer.ComboBox.Select" />
									</html:option>
									<html:options collection="CreditCardList" property="value"
										labelProperty="label" />
								</html:select></td>
							<tr>
								<td><bean:message key="BzComposer.Customer.CardNumber" /></td>
								<td colspan="3"><html:text property="cardNo"
									onkeypress="return numbersonly(event,this.value);" /></td>
							</tr>

							<tr>
								<td><bean:message key="BzComposer.Customer.ExpDate" /></td>
								<td colspan="3"><html:text property="expDate" readonly="true" />
								<img
									src="${pageContext.request.contextPath}/images/cal.gif"
									onclick="displayCalendar(document.CustomerForm.expDate,'mm/yyyy',this);">
								&nbsp;&nbsp;&nbsp; <bean:message
									key="BzComposer.Customer.mformate" /></td>
							</tr>
							<tr>

								<td><bean:message key="BzComposer.Customer.CW2" /></td>
								<td colspan="3"><html:text property="cw2" size="7" /></td>
							</tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.CardHolderName" /></td>
								<td><html:text property="cardHolderName" /></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><bean:message key="BzComposer.Customer.BillingAddress" /></td>
								<td><html:text property="cardBillAddress" size="50" /></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<td><bean:message key="BzComposer.Customer.ZipCode" /></td>
								<td><html:text onkeypress="return numbersonly(event,this.value)"
									property="cardZip" /></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>

						</tbody>
					</table>
					</div>
	</div>
			<div id="table-negotiations" style="width:100%"><input type="hidden"
				value="1" id="hidn" />
			<table class="tabla-listados" cellspacing="0">
				<thead>
					<tr>
						<th colspan="3"><strong><bean:message
							key="BzComposer.UpdateInvoice.Service.Header" /> </strong></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Service</td>
						<td><html:select property="serviceID">
							<html:options collection="ServiceList" property="serviceID"
								labelProperty="serviceName"></html:options>
						</html:select></td>
						<td><input type="button" name="add" value="Add" class="formbutton"
							onclick="addToTable(this.form);" /></td>
					</tr>
					<tr>
						<td><bean:message key="BzComposer.UpdateInvoice.Service.Service" /></td>
						<td colspan="2">&nbsp;</td>
					</tr>
				</tbody>
			</table>
		
			<div id="table-negotiations"
				style="overflow:auto;height:400;width:100%" class="section-border">
			<table class="tabla-listados" cellspacing="0">
				<thead>
					<tr>
						<th><bean:message
							key="BzComposer.UpdateInvoice.Service.ServiceName" /></th>
						<th><bean:message
							key="BzComposer.UpdateInvoice.Service.InvoiceStyle" /></th>
						<th><bean:message key="BzComposer.UpdateInvoice.Service.Balance" /></th>
						<th><bean:message key="BzComposer.UpdateInvoice.Service.Default" /></th>
						<th><bean:message key="BzComposer.UpdateInvoice.Service.Delete" /></th>
					</tr>
				</thead>
				<tr id="tr$$">
					<td align="center"></td>
					<td align="center"></td>
					<td align="center"></td>
					<td align="center"></td>
				</tr>
				<logic:present name="ServiceInfo" scope="request">
					<logic:notEmpty name="ServiceInfo" scope="request">
						<bean:size id="ServiceInfoSize" name="ServiceInfo" />
						<input type="hidden" name="serviceSize" id="sIDSize"
							value='<bean:write name="ServiceInfoSize" />' />
						<logic:iterate name="ServiceInfo" id="objList" indexId="ndx">
							<input type="hidden" id='<bean:write name="ndx" />_ser'
								value='<bean:write name="objList" property="serviceID" />' />
							<tr id='row<bean:write name="objList" property="serviceID" />'>
								<!-- id='tr<bean:write name="ndx" />' -->
								<td align="center"><bean:write name="objList"
									property="serviceName" /></td>
								<td align="center"><bean:write name="objList"
									property="invoiceStyle" /></td>
								<td align="center"><bean:write name="objList"
									property="serviceBalance" /></td>
								<logic:equal name="objList" property="defaultService" value="1">
									<td><input type="radio" name=defaultService
										value='<bean:write name="objList" property="defaultService" />'
										onclick="setDefault('<bean:write name="objList" property="serviceID" />',this.form);"
										checked="checked" /></td>
								</logic:equal>

								<logic:notEqual name="objList" property="defaultService"
									value="1">
									<td><input type="radio" name=defaultService
										value='<bean:write name="objList" property="defaultService" />'
										onclick="setDefault('<bean:write name="objList" property="serviceID" />',this.form);" /></td>
								</logic:notEqual>

								<td><img
									src="${pageContext.request.contextPath}/images/delete.png"
									alt="Deletable"
									onclick="removeFromTable('<bean:write name="objList" property="serviceID" />');" />
								<!-- onclick="removeFromTable('<bean:write name="ndx" />');" -->
								</td>


								<td align="center"><input type="hidden"
									value='<bean:write name="objList" property="serviceID"/>'
									id='<bean:write name="ndx" />id' /></td>
							</tr>
							<script>
			var temp;
			var defService="0";
			exist[cnt++]='<bean:write name="objList" property="serviceID"/>';
			invStyleName='<bean:write name="objList" property="invoiceStyle" />';	
			InvSize=document.getElementById('iSize').value;
			for (i=0; i<InvSize; i++){
				temp=document.getElementById(i+'iname').value;
				if (temp==invStyleName){							//invstyle found...
					temp=document.getElementById(i+'invid').value;
					defService=document.getElementById(i+'dservice').value;
						//invstyleid found...
					break;
				}
			}

			document.forms[0].table_serID.value+='<bean:write name="objList" property="serviceID"/>;';
			document.forms[0].table_bal.value+='<bean:write name="objList" property="serviceBalance"/>;';
			document.forms[0].table_invId.value+=temp+";";
			document.forms[0].table_defaultVal.value=defService;

			//table_defaultVal

			//		"bal="+	document.forms[0].table_bal.value+"\n\n"+
			//	 	"invstyles="+		document.forms[0].table_invId.value);
		</script>
						</logic:iterate>
					</logic:notEmpty>
				</logic:present>
				<logic:present name="ServiceInfo" scope="request">
					<logic:empty name="ServiceInfo" scope="request">
						<input type="hidden" id="sIDSize" value="0" />
					</logic:empty>
				</logic:present>
			</table>
			<div>
			<table>
			</table>
			</div>
			</div>
			</div>
			<div style="width:50%;text-align: right;">
			<input type="button" name="New" value="New"
				class="formbutton" title="New Customer" onclick="NewCustomer();" />
			<input type="button" class="formbutton" onclick="AddCustomer();"
				name="Add" value="Add" title="Add New Customer"> <input
				type="hidden" name="stname" value="" id="stateId"></div>
	<div>
	
	<input type="hidden" name="bst" id="bsst" value="0" /> <html:hidden
		property="state" value="0" /> <html:hidden property="bsstate"
		value="0" /> <html:hidden property="shstate" value="0" /></div>
	</div>
	</div>
	</div>
	</div>
	</div>
</html:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>
<script>
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
function setRowId(rowId,rid){
	size=document.getElementById("lSize").value;
	for(i=0;i<size;i++){
		var row1=document.getElementById(i+"$$");
		row1.className = "";
	}
	var rd=document.getElementById(rid);
	rd.className = "draft";
}
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
 
function CopyBilladdToShipAdd(){
	document.CustomerForm.shcname.value= document.CustomerForm.bscname.value;
	document.CustomerForm.shfirstName.value= document.CustomerForm.bsfirstName.value;
	document.CustomerForm.shlastName.value= document.CustomerForm.bslastName.value;
	document.CustomerForm.shaddress1.value= document.CustomerForm.bsaddress1.value;
	document.CustomerForm.shaddress2.value= document.CustomerForm.bsaddress2.value;
	document.CustomerForm.shcity.value= document.CustomerForm.bscity.value;
	document.CustomerForm.shzipCode.value = document.CustomerForm.bszipCode.value;
	document.CustomerForm.shprovince.value=document.CustomerForm.bsprovince.value;
	document.CustomerForm.shcountry.value= document.CustomerForm.bscountry.value;
	document.CustomerForm.shstate.value = document.getElementById('bsst').value;
	refreshItemsNow12(document.CustomerForm.bscountry.value,document.getElementById('bsst').value);
}

function clearBillingAdd(){
	document.CustomerForm.bscname.value="";
	document.CustomerForm.bsfirstName.value="";
	document.CustomerForm.bslastName.value="";
	document.CustomerForm.bsaddress1.value="";
	document.CustomerForm.bsaddress2.value="";
	document.CustomerForm.bscity.value="";
	document.CustomerForm.bszipCode.value="";
	document.CustomerForm.bsprovince.value="";
	document.CustomerForm.bscountry.value="";
	document.CustomerForm.bsstate.value="0";
	refreshItemsNow1(0);
}
function clearShippingAdd(){
	document.CustomerForm.shcname.value="";
	document.CustomerForm.shfirstName.value="";
	document.CustomerForm.shlastName.value= "";
	document.CustomerForm.shaddress1.value= "";
	document.CustomerForm.shaddress2.value= "";
	document.CustomerForm.shcity.value= "";
	document.CustomerForm.shzipCode.value = "";
	document.CustomerForm.shprovince.value="";
	document.CustomerForm.shcountry.value= "";
	document.CustomerForm.shstate.value="0";
	refreshItemsNow12(0,0);
}
function AddCustomer(){
	if(document.CustomerForm.cname.value==""){
		alert('<bean:message key="BzComposer.NewCustomer.Name.Validation" />');
		document.CustomerForm.cname.focus();
	}
	else if(document.CustomerForm.firstName.value==""){
		alert('<bean:message key="BzComposer.NewCustomer.FirstName.Validation" />');
		document.CustomerForm.firstName.focus();
	}
	else if(document.CustomerForm.lastName.value==""){
		alert('<bean:message key="BzComposer.NewCustomer.LastName.Validation" />');
		document.CustomerForm.lastName.focus();
	}
	else if(document.CustomerForm.address1.value==""  ){
		alert('<bean:message key="BzComposer.NewCustomer.Adderss1.Validation" />');
		document.CustomerForm.address1.focus();
	}
	else if(document.CustomerForm.city.value==""){
		alert('<bean:message key="BzComposer.NewCustomer.City.Validation" />');
		document.CustomerForm.address1.focus();
	}
	else if(document.CustomerForm.zipCode.value==""){
		alert('<bean:message key="BzComposer.NewCustomer.ZipCode.Validation" />');	
		document.CustomerForm.zipCode.focus();
	}
	else if(String(document.CustomerForm.cellPhone.value).length>16){
		alert('<bean:message key="BzComposer.NewCustomer.EmailLength.Validation" />');	
		document.CustomerForm.cellPhone.value="";
		document.CustomerForm.cellPhone.focus();
	}
	else {
		if(validate()){
			document.forms[0].action = "Customer.do?tabid=AddCustomer" ;
			document.forms[0].submit();
		}
	}
}
function hideother(form){
	chbox=document.getElementById('chk_useind');
	if(chbox.checked){
		document.CustomerForm.annualIntrestRate.disabled=false;
		document.CustomerForm.minFCharges.disabled=false;
		document.CustomerForm.gracePrd.disabled=false;
		document.getElementById('chk1').disabled=false;
		document.getElementById('chk2').disabled=false;
	}
	else{
		document.CustomerForm.annualIntrestRate.disabled=true;
		document.CustomerForm.minFCharges.disabled=true;
		document.CustomerForm.gracePrd.disabled=true;
		document.getElementById('chk1').disabled=true;
		document.getElementById('chk2').disabled=true;
	}
}
function addToTable(form){	
	flag=0;
	flag1=0;
	invID=form.serviceID.value;
	SIDSize=document.getElementById('sIDSize') .value;

	SLSize=document.getElementById('sSize') .value;
	InvSize=document.getElementById('iSize') .value;
	DetailSize=document.getElementById('dSize') .value;
	
	var serviceName;
	var InvoiceStyle;
	var ServiceBalance="0.0";
	var DefaultService;

		for(t=0; t<exist.length; t++){	//limit=cnt
			if(invID==exist[t])		{				
				flag=1;
				break;
			}
		}

		if(flag==1)		{
			alert('<bean:message key="BzComposer.NewCustomer.Service.Validation" />');
			return;
		}
		else {
				flagx = 0;
				for(i=0;i<SLSize;i++)		{
					iinvID=document.getElementById(i+'sid').value;
					isID=document.getElementById(i+'isid').value;
					if(invID==iinvID) {

						serviceName=document.getElementById(i+'sname').value;

						for(j=0;j<InvSize;j++)	{
							isID2=document.getElementById(j+'invid').value;
							if(isID==isID2) {
								for (k=0; k<exist.length; k++){
									if (exist[k]==-1) {
										exist[k]=invID;
										break;
									}
								}
								InvoiceStyle=document.getElementById(j+'iname').value;		
								flagx = 1;
							}				
						}
					}
				}
		
				if(flagx==0){
					InvoiceStyle = "";
					for (k=0; k<exist.length; k++){
						if (exist[k]==-1) {
							exist[k]=invID;
							break;
						}
					}
				}
				
				hidn_val = document.getElementById('hidn').value;
				hidn_val1=hidn_val;
		
				var tr = document.createElement("tr");
				tr.setAttribute("id", "row"+invID);
				
				var tr2 = document.getElementById('tr$$');
				var parentTr = tr2.parentNode;
				parentTr.insertBefore(tr, tr2);

				var td1 = document.createElement("td");
				td1.setAttribute("align", "center");
				tr.appendChild(td1);
				td1.innerHTML=serviceName;

				var td2 = document.createElement("td");
				td2.setAttribute("align", "center");
				tr.appendChild(td2);
				td2.innerHTML=InvoiceStyle;

				var td3 = document.createElement("td");
				td3.setAttribute("align", "center");
				tr.appendChild(td3);
				td3.innerHTML=ServiceBalance;

				var td4 = document.createElement("td");
				td4.setAttribute("align", "left");
				tr.appendChild(td4);
				var rd="<input type=radio id=setdisable name=defaultService onclick=setDefault("+invID+",this.form); />";
				td4.innerHTML=rd;
				var button='<img src="${pageContext.request.contextPath}/images/delete.png" alt="Deletable" onclick=removeFromTable('+invID+'); >';

				var td5 = document.createElement("td");
				td5.setAttribute("align", "left");
				tr.appendChild(td5);
				td5.innerHTML=button;

				document.CustomerForm.table_size.value++;
				document.CustomerForm.table_serID.value+=invID+";";
				document.CustomerForm.table_serviceName.value+=serviceName+";";
				document.CustomerForm.table_bal.value+=ServiceBalance+";";
				if(InvoiceStyle==""){
					form.table_invId.value+="0;";
				}
				else{
					for(p=0;p<InvSize;p++) {
						invoiceName=document.getElementById(p+'iname').value;
						if(invoiceName==InvoiceStyle) {
							document.CustomerForm.table_invId.value+=document.getElementById(p+'invid').value+";";
						}				
					}
				}
				count = ((count)/1 + 1);
				document.getElementById('hidn').value=((hidn_val)/1 + 1);	
			}
		name=document.CustomerForm.table_serviceName.value;
}
function setDefault(invID1,form){

	//form.table_defaultVal.value+=1+":"+invID1+";";
	document.CustomerForm.table_defaultVal.value=invID1;

	//document.getElementById('setdisable').disabled=true;
}
function setDefaultToDb(invID1,form){

	//form.table_DbDefSer.value+=1+":"+invID1+";";
	document.CustomerForm.table_DbDefSer.value=invID1;

	//document.getElementById('setdisable').disabled=true;
}
function removeFromTable(idV){

	var str;
	var trid;

	//		removeStringValues(document.forms[0].table_serID.value,idV);
	trid="row"+idV;
	for(i=0; i<exist.length; i++){
		if(idV==exist[i]){
			//document.getElementById(trid).style.display='none';
			var ttt=document.getElementById(trid);
				ttt.parentNode.removeChild(ttt);
			exist[i]=-1;			
			cnt--;


			//str=removeStringValues(document.forms[0].table_serID.value,idV);
			removeStringValues(idV);

			
			//do same for other two strings
			break;
		}
	}
}
function removeStringValues(key){	//from one string('str'), a substring 'key' will be removed
	var str, str2, str3;
	var temp=new Array(20);
	var temp2=new Array(20);
	var temp3=new Array(20);				

	str= document.forms[0].table_serID.value;		
	str2=document.forms[0].table_bal.value;
	str3=document.forms[0].table_invId.value;
	
	temp=str.split(";");
	temp2=str2.split(";");
	temp3=str3.split(";");		
	
	str=str2=str3="";
	for (i=0; i<temp.length; i++){
		if (temp[i]!=key){
			k=0;
			for (k=0; k<i; k++){
				if (temp[i]==temp[k]){
					k=-1;
					break;
				}
			}
			if (k!=-1){
				str=str+temp[i]+";";
				str2=str2+temp2[i]+";"
				str3=str3+temp3[i]+";"					
			}
		}
	}
	str=str.substring(0,str.length-1);	//removes last semi-colon
	str2=str2.substring(0,str2.length-1);	//removes last semi-colon
	str3=str3.substring(0,str3.length-1);	//removes last semi-colon

	if (key==document.forms[0].table_defaultVal.value){
		document.forms[0].table_defaultVal.value="0";	//reset if no service is set to default
	}		


	//		"\n\ntable_bal="+document.forms[0].table_bal.value+"\nstr2="+str2+
	//	"\n\ntable_invId="+document.forms[0].table_invId.value+"\nstr3="+str3+
	//"\n\ndefault service="+document.forms[0].table_defaultVal.value);

	document.forms[0].table_serID.value=str;
	document.forms[0].table_bal.value=str2;
	document.forms[0].table_invId.value=str3;
}
function NewCustomer(){
//billing addressres/*
	document.CustomerForm.bscname.value="";
	document.CustomerForm.bsfirstName.value="";
	document.CustomerForm.bslastName.value="";
	document.CustomerForm.bsaddress1.value="";
	document.CustomerForm.bsaddress2.value="";
	document.CustomerForm.bscity.value="";
	document.CustomerForm.bszipCode.value="";
	document.CustomerForm.bsprovince.value="";
	document.CustomerForm.bscountry.value="";
//shipping addresses
	document.CustomerForm.shcname.value="";
	document.CustomerForm.shfirstName.value="";
	document.CustomerForm.shlastName.value= "";
	document.CustomerForm.shaddress1.value= "";
	document.CustomerForm.shaddress2.value= "";
	document.CustomerForm.shcity.value= "";
	document.CustomerForm.shzipCode.value = "";
	document.CustomerForm.shprovince.value="";
	document.CustomerForm.shcountry.value= "";
//genaral Tab
	document.CustomerForm.firstName.value="";
	document.CustomerForm.lastName.value="";
	document.CustomerForm.address1.value="";
	document.CustomerForm.address2.value="";
	document.CustomerForm.city.value="";
	document.CustomerForm.state.value="";
	//document.CustomerForm.stateName.value="";
	document.CustomerForm.zipCode.value="";
	document.CustomerForm.phone.value="";
	document.CustomerForm.cellPhone.value="";
	document.CustomerForm.fax.value="";
	document.CustomerForm.email.value="";
	document.CustomerForm.title.value="";
	document.CustomerForm.province.value="";
	document.CustomerForm.country.value="";
	document.CustomerForm.homePage.value="";
	document.CustomerForm.type.value="";
	document.CustomerForm.texID.value="";
	document.CustomerForm.openingUB.value="";
	document.CustomerForm.extCredit.value="";
	document.CustomerForm.memo.value="";
	document.CustomerForm.term.value="";
	document.CustomerForm.rep.value="";
	document.CustomerForm.paymentType.value="";
	document.CustomerForm.shipping.value="";
	document.CustomerForm.ccType.value="";
	document.CustomerForm.cardNo.value="";
	document.CustomerForm.expDate.value="";
	document.CustomerForm.cw2.value="";
	document.CustomerForm.cardHolderName.value="";
	document.CustomerForm.cardBillAddress.value="";
	document.CustomerForm.cardZip.value="";
	document.CustomerForm.annualIntrestRate.value="";
	document.CustomerForm.minFCharges.value="";
	document.CustomerForm.gracePrd.value="";
	document.CustomerForm.dateAdded.value="";
	document.CustomerForm.cname.value="";
	document.CustomerForm.chktax.checked=false;
	document.CustomerForm.chk_alsovendor.checked=false;
	
	document.CustomerForm.chk_useind.checked=false;
	document.CustomerForm.chk1.checked=false;
	document.CustomerForm.chk2.checked=false;
	
	document.CustomerForm.annualIntrestRate.disabled=true;
	document.CustomerForm.minFCharges.disabled=true;
	document.CustomerForm.gracePrd.disabled=true;
	document.getElementById('chk1').disabled=true;
	document.getElementById('chk2').disabled=true;
}
</script>
