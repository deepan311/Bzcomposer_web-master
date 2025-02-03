
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/include/headlogo.jsp"%>
<%@include file="/include/header.jsp"%>
<title><bean:message key="BzComposer.Title.SalesBoard"/></title>

</head>
<body>

<!-- begin shared/header -->
<html:form action="SalesBord.do?tabid=ShowList" method="post">
	<div id="">
	<div id="header">
	<div id="header-content">
	<ul id="ulnav">
		<li><a
			href="${pageContext.request.contextPath}/Invoice.do?tabid=Invoice"
			title="Sales" class="selected" rel="section"><bean:message
			key="BzComposer.Sales" /></a></li>
		<li><a href="<bean:write name="path" scope="session" property="pathvalue"/>/PurchaseOrder.do?tabid=PurchaseOrder" title="Purchase"
			rel="section"><bean:message key="BzComposer.Purchase" /></a></li>
				
        <li><a href="Item.do?tabid=Item" title="Item"  rel="section"><span><bean:message
			key="BzComposer.sales.Item" /></span></a></li>
	
		<li><a href="Customer.do?tabid=Customer" title="Customer" rel="section"><span><bean:message
			key="BzComposer.sales.Customer" /></span></a></li>
				
		<li><a href="employee.do" title="Employee" rel="section"><bean:message
			key="BzComposer.Employee" /></a></li>
		<li><a	href="javascript: void(0)" title="Accounting" rel="section"><bean:message key="BzComposer.Accounting" /></a></li>
		
<%-- 		<li><a href="javascript: void(0)" title="Email" rel="section"><bean:message --%>
<!-- 			key="BzComposer.Email" /></a></li> -->
		<li><a href="${pageContext.request.contextPath}/Configuration.do?tabid=config"  title="Confuguration" rel="section" ><bean:message
			key="BzComposer.Confuguration" /></a></li>
	</ul>
	<div class="clear"></div>

	</div>

	</div>

	<div id="ddcolortabs">
	<ul>
		<li><a href="Invoice.do?tabid=Invoice" title=""><span><bean:message
			key="BzComposer.sales.Invoice" /></span></a></li>
		<li id="current" style="margin-left: 1px"><a
			href="${pageContext.request.contextPath}/Sales.do?tabid=SalesBoard"
			title=""><span><bean:message
			key="BzComposer.sales.SalesBoard" /></span></a></li>
	
	   
	   <li><a href="Estimation.do?tabid=EstimationBoard" title=""><span><bean:message
			key="BzComposer.sales.EstimationBoard" /></span></a></li>
			
		<li><a href="Estimation.do?tabid=Estimation" title=""><span><bean:message
			key="BzComposer.sales.Estimation" /></span></a></li>
		
		<li><a href="${pageContext.request.contextPath}/RMA.do?tabid=R0M0A0" title="RMA" ><span><bean:message
			key="BzComposer.RMA" /></span></a></li>
	
		<li><a href="DataManager.do?tabid=datamanager" title=""><span><bean:message
			key="BzComposer.sales.DataManager" /></span></a></li>
	</ul>
	</div>

	<div id="ddcolortabsline">&nbsp;</div>
	
	<div id="cos">

	<div class="statusquo ok">

	<div id="hoja">
	<div id="blanquito">
	<div id="padding">

	<div><span
		style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;"><bean:message
		key="BzComposer.sales.SalesBoard" /></span></div>
	<div>
	<div id="table-negotiations">
	<table cellspacing="0" align="center" class="section-border" style="width: 100%;">

		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr align="center">
			<td align="left"><logic:present name="IsUpdated">
				<strong><span class="msgstyle"> <bean:write
					name="IsUpdated" /></span></strong>
			</logic:present></td>
		</tr>
		<tr>
			<td align="left" width="46%" valign="top">

			<table class="table-notifications" width="100%">
				<tr>
					<th colspan="5" align="left"><bean:message
						key="BzComposer.sales.FilterOption" /></th>
				</tr>
				<tr>
					<td width="30%"><bean:message
						key="BzComposer.sales.MarketPlace" /></td>

					<td width="70%" colspan="4"><html:select
						property="filterMarket">
						<logic:present name="Market"  >
						<logic:equal name="Market" value="1">
								<option value="1" selected="selected"><bean:message
									key="BzComposer.sales.ebay" /></option>
							</logic:equal>

							<logic:notEqual name="Market" value="1">
								<option value="1"><bean:message
									key="BzComposer.sales.ebay" /></option>
							</logic:notEqual>
						
							<logic:equal name="Market" value="2">
								<option value="2" selected="selected"><bean:message
									key="BzComposer.sales.AmazonSeller" /></option>
							</logic:equal>

							<logic:notEqual name="Market" value="2">
								<option value="2"><bean:message
									key="BzComposer.sales.AmazonSeller" /></option>
							</logic:notEqual>

							<logic:equal name="Market" value="3">
								<option value="3" selected="selected"><bean:message
									key="BzComposer.sales.AmazonMarket" /></option>
							</logic:equal>

							<logic:notEqual name="Market" value="3">
								<option value="3"><bean:message
									key="BzComposer.sales.AmazonMarket" /></option>
							</logic:notEqual>

							

							<logic:equal name="Market" value="4">
								<option value="4" selected="selected"><bean:message
									key="BzComposer.sales.Halfcom" /></option>
							</logic:equal>

							<logic:notEqual name="Market" value="4">
								<option value="4"><bean:message
									key="BzComposer.sales.Halfcom" /></option>
							</logic:notEqual>


							<logic:equal name="Market" value="5">
								<option value="5" selected="selected"><bean:message
									key="BzComposer.sales.PriceGrabber" /></option>
							</logic:equal>

							<logic:notEqual name="Market" value="5">
								<option value="5"><bean:message
									key="BzComposer.sales.PriceGrabber" /></option>
							</logic:notEqual>

						</logic:present>

						<logic:notPresent name="Market">
							<option value="1"><bean:message
								key="BzComposer.sales.ebay" /></option>
							<option value="2"><bean:message
								key="BzComposer.sales.AmazonSeller" /></option>
							<option value="3"><bean:message
								key="BzComposer.sales.AmazonMarket" /></option>
						
							<option value="4"><bean:message
								key="BzComposer.sales.Halfcom" /></option>
							<option value="5"><bean:message
								key="BzComposer.sales.PriceGrabber" /></option>
						</logic:notPresent>
						
					</html:select></td>
				</tr>
				<tr>
					<td width="30%"><bean:message key="BzComposer.sales.OrderDate" /></td>
					<td><logic:present name="BlankValue">
						<html:text property="orderDate1"  size="25"
							value="" />
					</logic:present> <logic:notPresent name="BlankValue">
						<html:text property="orderDate1"  size="25" />
					</logic:notPresent></td>
					<td align="left"><img
						src="${pageContext.request.contextPath}/images/cal.gif"
						onclick="displayCalendar(document.salesboardForm.orderDate1,'mm-dd-yyyy',this);" /></td>
					<td align="left"><bean:message key="BzComposer.sales.to" /> <logic:present
						name="BlankValue">
						<html:text property="orderDate2"  size="25"
							value="" />
					</logic:present> <logic:notPresent name="BlankValue">
						<html:text property="orderDate2"  size="25" />
					</logic:notPresent></td>
					<td align="left"><img
						src="${pageContext.request.contextPath}/images/cal.gif"
						onclick="displayCalendar(document.salesboardForm.orderDate2,'mm-dd-yyyy',this);">
					</td>
				</tr>

				<tr>
					<td width="30%"><bean:message key="BzComposer.sales.SaleDate" /></td>
					<td><logic:present name="BlankValue">
						<html:text property="saleDate1"  size="25" value="" />
					</logic:present> <logic:notPresent name="BlankValue">
						<html:text property="saleDate1" size="25" />
					</logic:notPresent></td>
					<td align="left"><img
						src='${pageContext.request.contextPath}/images/cal.gif'
						onclick="displayCalendar(document.salesboardForm.saleDate1,'mm-dd-yyyy',this);"></td>
					<td align="left"><bean:message key="BzComposer.sales.to" /> <logic:present
						name="BlankValue">
						<html:text property="saleDate2" size="25" value="" />
					</logic:present> <logic:notPresent name="BlankValue">
						<html:text property="saleDate2" size="25" />
					</logic:notPresent></td>
					<td align="left"><img
						src='${pageContext.request.contextPath}/images/cal.gif'
						onclick="displayCalendar(document.salesboardForm.saleDate2,'mm-dd-yyyy',this);">
					</td>
				</tr>

			</table>

			</td>
			<td width="27%" valign="top">

			<table class="table-notifications" width="100%">
				<tr>
					<th colspan="2" align="left"><bean:message
						key="BzComposer.sales.SortOption" /></th>
				</tr>
				<tr>
					<td>1St</td>
					<td><html:select property="sortType1">
						<option value="0"></option>
						<option value="1"><bean:message
							key="BzComposer.sales.LastName" /></option>
						<option value="2"><bean:message
							key="BzComposer.sales.Zipcode" /></option>
						<option value="3"><bean:message
							key="BzComposer.sales.ProductName" /></option>

					</html:select></td>
				</tr>
				<tr>
					<td>2nd</td>
					<td><html:select property="sortType2">
						<option value="0"></option>
						<option value="1"><bean:message
							key="BzComposer.sales.LastName" /></option>
						<option value="2"><bean:message
							key="BzComposer.sales.Zipcode" /></option>
						<option value="3"><bean:message
							key="BzComposer.sales.ProductName" /></option>

					</html:select></td>

				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						class="formbutton" name="findBtn" value="Show List"></td>
				</tr>
			</table>

			</td>

			<td width="25%" valign="top">

			<table class="table-notifications" width="100%">
				<tr>
					<th colspan="2" align="left"><bean:message
						key="BzComposer.sales.Search" /></th>
				</tr>
				<tr>
					<td><bean:message key="BzComposer.sales.Column" /></td>
					<td><select name="searchType" id="sType">
						<option value="1"><bean:message
							key="BzComposer.sales.Name" /></option>
						<option value="2"><bean:message
							key="BzComposer.sales.Order" /></option>
						<option value="3"><bean:message
							key="BzComposer.sales.OrderID" /></option>
						<option value="4"><bean:message
							key="BzComposer.sales.Address" /></option>
						<option value="5"><bean:message
							key="BzComposer.sales.ProductName" /></option>
						<option value="6"><bean:message
							key="BzComposer.sales.Email" /></option>
					</select></td>
				</tr>
				<tr>
					<td><bean:message key="BzComposer.sales.Text" /></td>
					<td><input type="text" name="searchTxt" id="SearchVal"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button"
						class="formbutton" name="findBtn" value="Search"
						onclick="SaleSearch();"></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>

	<div >
	<table align="center">
		<tr>
			<td align="center"><logic:notEmpty name="msg">
				<font color="red"><b><bean:write name="msg" /></b></font>
			</logic:notEmpty></td>
		</tr>
	</table>
	
		<span
		style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;"><bean:message
		key="BzComposer.sales.SaleBoard.SalesList" /></span>
	<div id="table-negotiations"
		style="overflow:auto;height:400;width:100%" class="section-border">
	<table class="tabla-listados" cellspacing="0">

		<thead>
			<tr>
				<th><bean:message key="BzComposer.sales.Market" /></th>
				<th><bean:message key="BzComposer.sales.Order" /></th>
<%-- 				<th><bean:message key="BzComposer.sales.OrderID" /></th> --%>
				<th><bean:message key="BzComposer.sales.Name" /></th>
				<th><bean:message key="BzComposer.sales.Address" /></th>
				<th><bean:message key="BzComposer.sales.Zipcode" /></th>
				<th><bean:message key="BzComposer.sales.ProductName" /></th>
				<th><bean:message key="BzComposer.sales.EmailID" /></th>
				<th><bean:message key="BzComposer.sales.OrderDate" /></th>
				<th><bean:message key="BzComposer.sales.SaleDate" /></th>
				<th><bean:message key="BzComposer.sales.Printed" /></th>
				<th><bean:message key="BzComposer.sales.Shipped" /></th>


			</tr>
		</thead>
		<tbody>
			<logic:present name="SalesBoardDetails" scope="request">
				<logic:notEmpty name="SalesBoardDetails" scope="request">

					<bean:size id="SalesListSize" name="SalesBoardDetails" />
					<input type="hidden" name="sListSize" id="lSize"
						value='<bean:write name="SalesListSize" />'>

					<logic:iterate name="SalesBoardDetails" id="objList" indexId="ndx">
						<tr id='<bean:write name="ndx" />$$'
							onclick="setRowId(<bean:write name="objList" property="orderNum" />,'<bean:write name="objList" property="email" />','<bean:write name="ndx" />$$');">

							<td align="center"><bean:write name="objList"
								property="marketPlaceName" /></td>
							<td align="center"><bean:write name="objList"
								property="orderNum" /></td>
<%-- 							<td align="center"><bean:write name="objList" --%>
<!-- 								property="orderid" /></td> -->
							<td align="center"><bean:write name="objList"
								property="lastName" />,<bean:write name="objList"
								property="firstName" /></td>
							<td align="center"><bean:write name="objList"
								property="address1" />,<bean:write name="objList"
								property="address2" /></td>

							<td align="center"><bean:write name="objList"
								property="zipCode" /></td>
							<td align="center"><bean:write name="objList"
								property="itemName" /></td>
							<td align="center"><bean:write name="objList"
								property="email" /></td>
							<td align="center"><bean:write name="objList"
								property="saleDate" /></td>

							<td align="center" ><bean:write name="objList"
								property="dateAdded" /></td>
							<td align="center"><logic:equal name="objList"
								property="printed" value="true">
								<input type="checkbox" name="isPrintedCHK" id="isPrintedId"
									title="isPrinted" checked="true" disabled="disabled">
							</logic:equal> <logic:equal name="objList" property="printed" value="false">
								<input type="checkbox" name="isPrintedCHK" id="isPrintedId"
									title="isPrinted" disabled="disabled">
							</logic:equal></td>
							<td align="center"><logic:equal name="objList"
								property="shipped" value="1">
								<input type="checkbox" name="shipped<bean:write name="ndx" />"
									id="shippedId" title="shipped" checked="true" value="ON"
									onclick="makeUpdate(<bean:write name="objList" property="orderNum" />,this);">
							</logic:equal> <logic:equal name="objList" property="shipped" value="0">
								<input type="checkbox" name="shipped<bean:write name="ndx" />"
									id="shippedId" title="shipped" value="ON"
									onclick="makeUpdate(<bean:write name="objList" property="orderNum" />,this);">
							</logic:equal></td>

							<td align="center"><logic:equal name="objList"
								property="email" value="true">
								<input type="checkbox" name="isEmailValCHK" id="isEmailId"
									title="isEmailed" checked="true" disabled="disabled">
							</logic:equal> <logic:equal name="objList" property="email" value="false">
								<input type="checkbox" name="isEmailValCHK" id="isEmailId"
									title="isEmailed" disabled="disabled">
							</logic:equal></td>

						</tr>
					</logic:iterate>
				</logic:notEmpty>

			</logic:present>

		</tbody>
	</table>
	</div>
	</div>
	<table align="center">
		<tr align="center">
			<td><input type="button" class="formbutton" name="smailbtn"
				id="smail" value='<bean:message	key="BzComposer.sales.Lookup" /> '
				disabled="disabled" title="Please select a record"
				onclick="sendToInvoice();"> &nbsp;&nbsp;<input type="button"
				class="formbutton" disabled="disabled" id="modi"
				onclick="makeUpdateInList();" title="Modify shipped"
				name="modifyBtn"
				value='<bean:message	key="BzComposer.sales.Update" />'> <input
				type="hidden" name="ONum" id="ONumId"> <input type="hidden"
				name="sEmail" id="sEmailID"> <input type="hidden"
				name="rNum" id="rowONum"> <input type="hidden"
				name="senderEmail" id="EID"></td>
		</tr>
	</table>
	<input type="hidden" id="ordId" name="OrderValue" value=""> <input
		type="hidden" id="statusId" name="StatusValue" value=""> <input
		type="hidden" id="ordSize" name="Size" value=""> <!-- end Contents --></div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	<input type="hidden" id="ord_value" />
</html:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>

</body>
</html>

<script type="text/javascript">
function setIsEmail(){

	document.getElementById("isEmailChk").value=document.getElementById("EmailId").value;
	document.forms[0].action = "Email.do?tabid=EOSOLO";
	document.forms[0].submit();
}
function checkName(){
	if(document.RMAForm.fnameTxt.value=="" && document.RMAForm.lnameTxt.value=="")	{
		alert("<bean:message key='BzComposer.common.insertFirstOrLastname'/>");
	}
	else{
		document.forms[0].action = "RMA.do?tabid=R0S0C0" ;
		document.forms[0].submit();
	}	
}

function setRowId(rowId,email,rid){
	document.getElementById("ord_value").value=rowId;
	
	//orderNo=document.getElementById("ord_value").value;
	size=document.getElementById("lSize").value;
	type=document.getElementById("sType").value;
	typeVal=document.getElementById("SearchVal").value
	for(i=0;i<size;i++){
		var row1=document.getElementById(i+"$$");
		row1.className = "";
	}
	var rd=document.getElementById(rid);
	rd.className = "draft";
	document.getElementById("smail").disabled=false;
	document.getElementById("sEmailID").value=email;
	document.getElementById("rowONum").value=rowId;
}

function sendToInvoice(){

	order_no=document.getElementById("ord_value").value;
	document.forms[0].action = "Invoice.do?tabid=SBLU&order_no="+order_no;
	document.forms[0].submit();
}
var ordarr = new Array();
var statusarr = new Array();

var cnt=0;
var ord="";
var status="";
function makeUpdate(orderno,obj)
{
	var flag=0;
	document.getElementById("modi").disabled=false;
	val=obj.checked;
	for(j=0;j<=cnt;j++){
		if(orderno==ordarr[j]){
			flag=1;
			statusarr[j]=val;
		}
	}
	if(flag==0){
		ordarr[cnt]=orderno;
		statusarr[cnt]=val;
		cnt++;
	}
	
}

function makeUpdateInList()
{
	for(j=0;j<cnt;j++){
		ord+=ordarr[j]+";";
		status+=statusarr[j]+";";
	}
	document.getElementById('ordSize').value=cnt;
	document.getElementById('ordId').value=ord;
	document.getElementById("statusId").value=status;
	response = window.confirm("Do you want to update record?");
	if(response){
		document.getElementById("modi").disabled=false;
		document.forms[0].action = "SalesBord.do?tabid=UpdateRecord";
		document.forms[0].submit();
	}
}

function SaleSearch(){
	debugger
	Myid="";
	count=0;
	size=document.getElementById("lSize").value;
	type=document.getElementById("sType").value;
	typeVal=document.getElementById("SearchVal").value;
	debugger

	for(i=0;i<size;i++){
		var row1=document.getElementById(i+"$$");
		row1.className = "";
	}
	for(i=0;i<size;i++){
		var row1=document.getElementById(i+"$$");
		row1.className = "";
		var cells=row1.getElementsByTagName("td");
		var data1=cells[1].innerHTML;//value in first td
		
		var data2=cells[2].innerHTML;//value in second td
		var data3=cells[3].innerHTML;//value in third td
		var data4=cells[4].innerHTML;//value in forth td
		var data5=cells[5].innerHTML;//value in fifth td
		var data6=cells[6].innerHTML;//value in sixth td
		var data7=cells[7].innerHTML;//value in seventh td
	
		data1=data1.toLowerCase();
		data2=data2.toLowerCase();
		data3=data3.toLowerCase();
		data4=data4.toLowerCase();
		data5=data5.toLowerCase();	
		data6=data6.toLowerCase();
		data7=data7.toLowerCase();					
	
		typeVal=typeVal.toLowerCase();	
						
		if(type=="1" && data3.match(typeVal))
			{row1.className = "draft"; break;}
		else if(type=="2" && data1.match(typeVal))
			{row1.className = "draft"; break;}
		else if(type=="3" && data2.match(typeVal))
			{ row1.className = "draft"; break;}
		else if(type=="4" && data4.match(typeVal))
			{ row1.className = "draft"; break;}
		else if(type=="5" && data6.match(typeVal))
			{ row1.className = "draft"; break;}
		else if(type=="6" && data7.match(typeVal))
			{ row1.className = "draft"; break;}
	}
}
</script>
