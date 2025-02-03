<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="java.util.*, java.io.*"%>
<%@ page isELIgnored="false"%>
<%--<%@ page errorPage="/include/sessionExpired.jsp"%>--%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
	<%@include file="/WEB-INF/jsp/include/header.jsp"%>
	<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
	<title><spring:message code="BzComposer.hiredemployeelisttitle"/>
	</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#myInput").on("keyup", function() {
				var value = $(this).val().toLowerCase();
				$("#custTableBody tr").filter(function() {
					$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
				});
			});
		});
	</script>
	<script type="text/javascript">
		var radio_val;
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
		function showEditEmployee(employeeID)
		{
			if (employeeID != null){
				window.location = "manageemployee?act=edit&eid="+employeeID;
			}else {
				return emptyemployeeidDialog();
			}
		}
		function getEmployeeInfo(employeeid,rowId)
		{
			debugger;
			var employee = employeeid;
			//document.getElementById('venrId').value=vendor;
			$("#venrId").val(employee);
			//document.getElementById("tabid").value="VONODO";
			/* document.forms[0].action="Vendor?";
            document.forms[0].submit(); */
			window.location = "employeelist?type=hiredDetails&emid="+employeeid;
		}

		function initialize()
		{
			;
			document.getElementById("country").options[193].selected=true;
			refreshItemsNow(2);
			<%--//	document.VendorForm.sid.disabled=true;--%>
			<%--// 	<logic:present name="CustomerDetails">--%>
			<%--//	<logic:equal name="CustomerDetails"	path="fsUseIndividual" value="1">--%>
			<%--//	enableDisableFinanceCharges();--%>
			<%--//	</logic:equal>--%>
			<%--// 	</logic:present> 	--%>

			var rid= "<%= request.getParameter("SelectedRID")%>$$";
			var cvid = <%= request.getParameter("vendrId")%>;

			var rd=document.getElementById(rid);
			rd.className = "draft";
			<c:if test="${not empty VendorFrm}">
			tableValue();
			</c:if>
			<c:if test="${not empty VendorFrm}">
			document.getElementById('edit').disabled=true;
			document.getElementById('delete').disabled=true;
			</c:if>
			/* document.getElementById('imgfrm').disabled=true;
            document.getElementById('imgto').disabled=true; */
			/*document.VendorForm.periodFrom.disabled=true;
            document.VendorForm.periodTo.disabled=true;*/
		}

		function DeleteEmployee(employeeid)
		{
			if(employeeid !=null){
				$("#deleteRowDialog").dialog({
					resizable: false,
					height: 200,
					width: 400,
					modal: true,
					buttons: {
						"<spring:message code='BzComposer.global.ok'/>": function () {
							$(this).dialog("close");
							/* document.forms[0].action = "manageemployee?act=terminate&eid="+Id;
                            document.forms[0].submit(); */
							window.location = "manageemployee?act=terminate&eid="+employeeid;
						},
						"<spring:message code='BzComposer.global.cancel'/>": function () {
							$(this).dialog("close");
							return false;
						}
					}
				});
				return false;
			}else {
				return emptyemployeeidDialog();
			}

		}
		function setRowId(rowid,rid)
		{
			debugger;
			size=document.getElementById("lSize").value;
			//size1=document.getElementById("seSize").value;
			for(i=0;i<size;i++)
			{
				var row1=document.getElementById(i+"$$");
				row1.className = "";
			}
			rid = rid+'$$';
			var rd=document.getElementById(rid);
			rd.className = "draft";
			var rowID = rowid;

			var rID = rid;
			var rd1=rid.replace("$$", "");




			//document.getElementById("setRID").value=rID;
			$("#setRID").val(rID);

			//document.VendorForm.clientVendorID.value=rowID;
			$("#clientVendorID").val(rowID);

			getEmployeeInfo(rowID,rd1);
		}
		function tableValue()
		{
			//var rid1=document.VendorForm.selectedRowID.value;
			var rid= "<%= request.getParameter("SelectedRID")%>$$";
			var rd=document.getElementById(rid);
			rd.className = "draft";
			document.getElementById("setRID").value=rid;
			var sortId = <%= request.getParameter("sortById")%>;

			$('select[id="sortBy"]').find('option[value="'+sortId+'"]').attr("selected",true);
		}
		function emptyemployeeidDialog()
		{
			;
			event.preventDefault();
			$("#emptyemployeeidDialog").dialog({
				resizable: false,
				height: 200,
				width: 300,
				modal: true,
				buttons: {
					"<spring:message code='BzComposer.global.ok'/>": function () {
						$(this).dialog("close");
						//window.location="Invoice?tabid=DeleteInvoice&&CustomerID="+cid;
					}
				}
			});
			return false;
		}
		function showSelectVendorDialog()
		{
			;
			event.preventDefault();
			$("#showSelectVendorDialog").dialog({
				resizable: false,
				height: 200,
				width: 300,
				modal: true,
				buttons: {
					"<spring:message code='BzComposer.global.ok'/>": function () {
						$(this).dialog("close");
						//window.location="Invoice?tabid=DeleteInvoice&&CustomerID="+cid;
					}
				}
			});
			return false;
		}
		function deleteVendorDialog()
		{
			;
			event.preventDefault();
			$("#deleteVendorDialog").dialog({
				resizable: false,
				height: 200,
				width: 400,
				modal: true,
				buttons: {
					"<spring:message code='BzComposer.global.ok'/>": function () {
						$(this).dialog("close");
					}
				}
			});
			return false;
		}
	</script>
	<script type="text/javascript">
		flag_state = 0;
		var funsequence = 0;
		var _1 = navigator.userAgent.toLowerCase();
		var ___ = (_1.indexOf("msie") != -1);
		var ___5 = (_1.indexOf("msie 5") != -1);
		var _io = (_1.indexOf("opera") != -1);
		var _im = (_1.indexOf("mac") != -1);
		var ____gi = (_1.indexOf("gecko") != -1);
		var i____s = (_1.indexOf("safari") != -1);
		var o = null;
		var o22 = null;
		var o33 = null;
		var oT = null;
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
		function oGET(oo, url)
		{
			try
			{
				oo.open("GET", url, true);
				oo.send(null);
			}
			catch (ex)
			{}
		}
		function writeSelect()
		{
			if (o.readyState != 4 || o.status != 200)
			{
				return;
			}
			document.getElementById("t_statedata").innerHTML = o.responseText ;
		}
		function refreshItemsNow(val)
		{
			o = c(writeSelect);
			oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=state&Cid=' + val)
		}
		function refreshItemsNow33(val,sval)
		{
			o33 = c(writeSelect33);
			oGET(o33,'${pageContext.request.contextPath}/include/GetStates.jsp?st=state&Cid=' + val+"&sval="+sval)
		}
		function writeSelect33()
		{
			if (o33.readyState != 4 || o33.status != 200)
			{
				return;
			}
			document.getElementById("t_statedata").innerHTML = o33.responseText ;
		}
		function writeSelect1()
		{
			if (o.readyState != 4 || o.status != 200)
			{
				return;
			}
			document.getElementById("t_statedata1").innerHTML = o.responseText ;
		}
		function refreshItemsNow1(val)
		{
			o = c(writeSelect1);
			oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=bsstate&Cid=' + val)
		}
		function refreshItemsNow12(val,sval)
		{
			o = c(writeSelect2);
			oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=shstate&Cid=' + val+"&sval="+sval)
		}
		function refreshItemsNow22(val,sval)
		{
			o22 = c(writeSelect22);
			oGET(o22,'${pageContext.request.contextPath}/include/GetStates.jsp?st=bsstate&Cid=' + val+"&sval="+sval)
		}
		function writeSelect22()
		{
			if (o22.readyState != 4 || o22.status != 200)
			{
				return;
			}
			document.getElementById("t_statedata1").innerHTML = o22.responseText ;
		}
		function writeSelect2()
		{
			if (o.readyState != 4 || o.status != 200)
			{
				return;
			}
			document.getElementById("t_statedata2").innerHTML = o.responseText ;
		}
		function refreshItemsNow2(val)
		{
			o = c(writeSelect2);
			oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=shstate&Cid=' + val)
		}
		function writeSelectTH()
		{
			if (oT.readyState != 4 || oT.status != 200)
			{
				return;
			}
			document.getElementById("t_history").innerHTML = o.responseText ;
		}
		function refreshTransationNow(radio_val,custid,dfrom,dto)
		{
			oT = c(writeSelectTH);
			oGET(oT,'${pageContext.request.contextPath}/sales/addTransactionHistory.jsp?custId=' + custid+'&cond='+radio_val+'&pfrom='+dfrom+'&pto='+dto)
		}
		function setState(state_id,name)
		{
			if(name == 'state')
			{
				document.VendorForm.state.value = state_id;
			}
			else if(name == 'bsstate')
			{
				flag_state = 1;
				document.VendorForm.bsstate.value = state_id;
				document.getElementById('bsst').value = state_id;
			}
			else if(name == 'shstate')
			{
				document.VendorForm.shstate.value = state_id;
			}
		}
	</script>
	<style type="text/css">
		div#pie { /* 	color:#05A9C5;; */
			padding: 10px 0px 20px 0px;
		}
		table.tabla-listados {
			width: 100%;
			border: 1px solid rgb(207, 207, 207);
			margin: 20px 0px 20px 0px;
		}

		table.tabla-listados thead tr th {
			font-size: .7em;
			text-align: left;
			padding: 5px 10px;
			/* 	background: rgba(5, 169, 197, 0.11); */
			border-bottom: 1px solid rgba(5, 169, 197, 0.2);
			/* 	color: #333; */
			text-shadow: #999 0px 1px 1px;
			white-space: nowrap;
		}

		table.tabla-listados tbody tr td {
			font-size: .8em;
			/* 	color: #666; */
			padding: 5px 0px 5px 14px;
			/* 	border-bottom: 1px solid rgb(207, 207, 207); */
			background: #fff;
			vertical-align: top;
		}
	</style>
	<script>
		$(function() {
			$('#sortBy').change(function(){
				var sortBy = $(this).val();
				$.ajax({
					type: "POST",
					url:"EmployeeAjax?tabid=sortHired&SortBy="+sortBy,
					data:{sortBy : sortBy},
				}).done(function(data){
					$('#custTableBody').html('');
					var custDetails = "";
					for(var i=0; i<data.length; i++){
						debugger;
						var objList = data[i];
						var td = "";

						custDetails = custDetails +"<input type='hidden' name='listSize' id='lSize' value='"+data.length+"'>" +
								"<tr id='"+i+"$$' onclick='setRowId("+objList.employeeID+","+i+")'>" +
								"<td colspan='2' style='font-size: 14px; width: 300px;'>"+
								objList.fname+" "+objList.lname+
								"</td>"+
								"</tr>";
					}
					$('#custTableBody').html(custDetails);
				});
			});
		});
	</script>
</head>
<body onload="initialize();">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
	<div class="statusquo ok">
		<div id="hoja">
			<div id="blanquito">
				<div id="padding">
					<div>
						<div style="float: left;">
                        <span style="font-size: 1.6em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
                            <spring:message code="BzComposer.Employee.HiredEmployeeList" />
                        </span>
							<br>
							<table>
								<tr>
									<td>
										<spring:message code="BzComposer.customer.sortby" />
									</td>
									<td>
										<select id="sortBy">
											<option value="1"><spring:message code="BzComposer.global.firstname"/></option>
											<option value="2"><spring:message code="BzComposer.global.lastname"/></option>
										</select>
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
							</table>
							<table>
								<tr>
									<td>
										<spring:message code="BzComposer.searchbuttontext" />
									</td>
									<td>
										<input type="text" id="myInput">
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
							</table>
							<br>
						</div>
						<div style="float: right;">
							<table>
								<tr align="right">
									<td colspan="6" style="font-size: 14px;">
										<input type="button" name="editAction" value='<spring:message code="BzComposer.global.edit" />'
											   title='<spring:message code="BzComposer.vendorlist.edittooltip" />' class="formButton" onclick="showEditEmployee(${empList1[0].employeeID});">
										<input type="button" name="deleteAction" value='<spring:message code="BzComposer.global.delete" />'
											   title='<spring:message code="BzComposer.vendorlist.deletetooltip" />' class="formButton" onclick="DeleteEmployee(${empList1[0].employeeID});">
									</td>
								</tr>
							</table>
						</div>
					</div>
					<!-- dialog space -->
					<!-- Dialog used in vendor list page -->
					<div id="showSelectVendorDialog" style="display:none;">
						<p><spring:message code="BzComposer.vendorlist.selectvendordialog"/></p>
					</div>
					<div id="emptyemployeeidDialog" style="display:none;">
						<p><spring:message code="BzComposer.Employee.emptyEmployeeDialog"/></p>
					</div>

					<div id="deleteVendorDialog" style="display:none;">
						<p><spring:message code="BzComposer.vendorlist.selectvendortodelete"/></p>
					</div>
					<div id="deleteRowDialog" style="display:none;">
						<p><spring:message code="BzComposer.employee.deleteselectedemployee"/></p>
					</div>
					<div>
						<hidden path="clientVendorID" Id="clientVendorID" />
						<hidden path="selectedRowID" />
						<input type="hidden" name="venrId" id="venrId" value="" />
						<input type="hidden" name="cvId" id="cvId" value=""/>
						<input type="hidden" name="tabid" id="tabid" value="" />
						<input type="hidden" name="SelectedRID" id="setRID" value="">
						<input type="hidden" name="bst" id="bsst" value="0" />
						<hidden path="state" value="0" />
						<hidden path="bsstate" value="0" />
						<hidden path="shstate" value="0" />
					</div>
					<!-- end Contents -->
					<input type="hidden" name="actionValidate" value="vendor.jsp">
					<table style="width: 100%;">
						<tr>
							<td style="font-size: 14px;">
								<table style="width: 100%;">
									<tr>
										<td style="border: 1px solid #ccc; width: 300px; padding: 0; margin: 0;">
											<table class="tabla-listados" cellspacing="0" style="border: 0;padding: 0; margin: 0;">
												<thead>
												<!-- <tr valign="top"> -->
												<tr>
													<th class="emblem" style="font-size: 14px;">
														<div align="center">
															<spring:message code="BzComposer.Employee" />
														</div>
													</th>
												</tr>
												</thead>
												<tbody>
												<tr>
													<!-- <td colspan="4" style="padding: 6px 0px 3px 3px"> -->
													<td colspan="4" style="margin: 0; padding: 0;">
														<!-- <div style="overflow: auto; height:340; width: 250;"> -->
														<div style="overflow: auto;height: 80vh;width: 300px;">
															<table>
																<tbody id="custTableBody">
																<c:if test="${not empty empList}">
																	<%int ndx = 0;%>
																	<c:forEach items="${empList}" var="objList">
																		<tr id='<%=ndx%>$$' onclick="setRowId(${objList.employeeID},<%=ndx%>);">
																			<% ndx++; %>
																			<td colspan="2" style="font-size: 14px; width: 300px;">
																					${objList.fname} ${objList.lname}
																			</td>
																		</tr>
																	</c:forEach>
																	<input type="hidden" name="listSize" id="lSize" value='<%=ndx%>'>
																</c:if>
																</tbody>
															</table>
														</div>
												</tbody>
											</table>
										</td>
										<td style="font-size:14px; margin: 0; padding: 0;vertical-align: 0;">
											<c:if test="${not empty empList1}"></c:if>
											<div id="table-negotiations" >
												<table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;">
													<thead>
													<tr>
														<th colspan="10" style="font-size: 14px;">
															<spring:message code="BzComposer.Employee.GeneralInformation"/>
														</th>
													</tr>
													</thead>
													<tbody>
													<tr>
														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.FirstName" />
															<!-- <span class="inputHighlighted">*</span> --> :
														</td>
														<td style="font-size:14px;">
															${empList1[0].fname}
														</td>
														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.LastName" />
															<!-- <span class="inputHighlighted">*</span> --> :
														</td>
														<td style="font-size:14px;">
															${empList1[0].lname}
														</td>
														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.DOA" />:
														</td>
														<td style="font-size:14px;">
															${empList1[0].doa}
														</td>
														<td colspan="4">&nbsp;</td>
													</tr>
													<tr>
														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.Title" />:
														</td>
														<td style="font-size:14px;">
															<select path="title" name="vendorDetails1" disabled="true" style="width:125px;">
																<c:forEach items="${titleList}" var="obj">
																	<option value="${obj.value}">${obj.label}</option>
																</c:forEach>
															</select>
															<%--</td>--%>

														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.Province" />
														</td>
														<td style="width: 10%;font-size:14px;">
															${vendorDetails1.province}
														</td>
														<td colspan="6">&nbsp;</td>
													</tr>
													<tr>
														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.Address1" />
															<span class="inputHighlighted">*</span>
														</td>
														<td style="font-size:14px;">
															<%-- <text style="width:100%;" path="address1" name="vendorDetails1" readonly="true" /> --%>
															${empList1[0].address1}
														</td>
														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.Address2" />
															<span class="inputHighlighted">:</span>
														</td>
														<td style="font-size:14px;">
															<%-- <text style="width:100%;" path="address2" name="vendorDetails1" readonly="true" /> --%>
															${empList1[0].address2}
														</td>

														<td colspan="6">&nbsp;</td>
													</tr>
													<tr>
														<td id="t_country" style="font-size:14px;">
															<spring:message code="BzComposer.Employee.Country" />
														</td>
														<td style="font-size:14px;">
															<select id="country" name="vendorDetails1" disabled="true">
																<option value="0">
																	<spring:message code="BzComposer.ComboBox.Select" />
																</option>
																<c:forEach items="${cList}" var="obj">
																	<option value="${obj.value}">${obj.label}</option>
																</c:forEach>
															</select>
														</td>
														<td id="t_state" style="font-size:14px;">
															<spring:message code="BzComposer.Employee.State" />
														</td>
														<td id="t_statedata" style="font-size:14px;"></td>
														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.City" />
															<span class="inputHighlighted">*</span>
														</td>
														<td style="width:10%;font-size:14px;">
															${empList1[0].city}
														</td>
														<td colspan="4">&nbsp;</td>
													</tr>
													<script>
														<c:if test="${not empty state_gen}">
														var contry=document.VendorForm.country.value;
														refreshItemsNow33(contry,'${state_gen}');
														</c:if>
													</script>
													<tr>
														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.Zip" />
															<!-- <span class="inputHighlighted">*</span> --> :
														</td>
														<td style="font-size:14px;">
															${empList1[0].zip}
														</td>
														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.Phone" />
														</td>
														<td style="width: 10%;font-size:14px;">
															${empList1[0].phone}
														</td>
														<td colspan="6">&nbsp;</td>
													</tr>
													<tr>

														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.Email" />
														</td>
														<td style="font-size:14px;">
															${empList1[0].email}
														</td>
														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.EmpType" />
														</td>
														<td style="font-size:14px;">
															<select path="type" disabled="true">
																<option value="0">
																	<spring:message code="BzComposer.ComboBox.Select" />
																</option>
																<c:forEach items="${VendorCategoryList}" var="obj">
																	<option value="${obj.value}">${obj.label}</option>
																</c:forEach>
															</select>
														</td>
														<td colspan="6">&nbsp;</td>
													</tr>
													<tr>
														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.SSN" />
														</td>
														<td style="font-size:14px;">
															${empList1[0].ssn}
														</td>

														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.Terminated" />
															<c:if test="${not empty empList1[0].terminated}">
																<input value="on" type="checkbox" disabled="disabled" checked="checked">
															</c:if>
															<c:if test="${empty empList1[0].terminated}">
																<input value="on" type="checkbox" disabled="disabled">
															</c:if>
															<%-- <bean:message key="BzComposer.vendorlist.isalsoclient" /> --%>
														</td>
														<td style="font-size:14px;">
															<spring:message code="BzComposer.Employee.TerminatedDate" />
														</td>
														<td style="font-size: 14px;">
															${empList1[0].terminated}
														</td>
														<td colspan="4">&nbsp;</td>
													</tr>
													<!--<tr> -->
													<%--<td><spring:message code="BzComposer.Customer.Memo" /></td> --%>
													<%--<td colspan="2"><textarea style="width:100%" path="memo" --%>
													<!--name="CustomerDetails" readonly="true"/></td> -->
													<!--</tr> -->
													</tbody>
												</table>
												<table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;">
													<thead>
													<tr>
														<th colspan="10" style="font-size: 14px;">
															<spring:message code="BzComposer.editemployee.payrollinfo"/>
														</th>
													</tr>
													</thead>
													<tbody>
													<tr>
														<td id="t_filing" style="font-size:1em;">
															<spring:message code="BzComposer.editemployee.filingstatus" />
															<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
														</td>
														<td id="t_filling">
															<select name="filingStatus" path="${empList1[0].filingStatus}" id="filingStatus" style="font-size:1em;">
																<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
																<c:forEach items="${filingList}" var="obj">
																	<option value="${obj.value}">${obj.label}</option>
																</c:forEach>
															</select>
														</td>
														<td id="t_allowance" style="font-size:1em;">
															<spring:message code="BzComposer.editemployee.allowance" />
															<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
														</td>
														<td id="t_allowance" style="font-size:1em;">
															${empList1[0].allowance}
														</td>
														<td id="t_stateworked" align="right" style="font-size:1em;">
															<spring:message code="BzComposer.editemployee.stateworked" />
															<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
														</td>
														<td id="t_stateworked" style="font-size:1em;">
															<select name="stateworked" path="stateworked" id="stateworked" style="font-size:1em;">
																<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
																<c:forEach items="${statewList}" var="obj">
																	<option value="${obj.value}">${obj.label}</option>
																</c:forEach>
															</select>
														</td>
														<td colspan="2">&nbsp;</td>
													</tr>
													<tr>
														<td id="t_payrollmethod" style="font-size:1.4em;" colspan="8">
															<b><spring:message code="BzComposer.editemployee.payrollmethod" /></b>
															<span class="inputHighlighted"></span>
														</td>
													</tr>
													<tr>
														<td id="t_period" style="font-size:1em;">
														</td>
														<td id="t_period" style="font-size:1em;">
															<input type="radio" name="payMethod" path="payMethod" value="2"/>
															&nbsp;<spring:message code="BzComposer.editemployee.hourly" />
															&nbsp;&nbsp;&nbsp;&nbsp;
															<input type="radio" name="payMethod" path="payMethod" value="3" />
															&nbsp;<spring:message code="BzComposer.editemployee.daily" />
															&nbsp;&nbsp;&nbsp;&nbsp;
															<input type="radio" name="payMethod" path="payMethod" value="1" />
															&nbsp;<spring:message code="BzComposer.editemployee.salary" />
															&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td id="t_period" style="font-size:1em;"></td>
														<td colspan="5">&nbsp;</td>
													</tr>
													<tr>
														<td id="t_amount" style="font-size:1em;">
															<spring:message code="BzComposer.editemployee.amount" />
															<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
														</td>
														<td>
															${empList1[0].amount}
														</td>
														<td id="t_payperiod" style="font-size:1em;">
															<spring:message code="BzComposer.editemployee.payperiod" />
															<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
														</td>
														<td id="t_lname" width="16%">
															<select name="payperiod" path="payperiod" id="payperiod" Class="ctrl" style="font-size:1em;">
																<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
																<c:forEach items="${periodList}" var="obj">
																	<option value="${obj.value}">${obj.label}</option>
																</c:forEach>
															</select>
														</td>
														<td width="20%" id="t_lname" align="right">
															&nbsp;<span class="inputHighlighted"></span>
														</td>
														<td width="20%" id="t_lname" align="right">
															&nbsp;<span class="inputHighlighted"></span>
														</td>
														<td colspan="2">&nbsp;</td>
													</tr>

													</tbody>
												</table>
											</div>
											<%-- </logic:present> --%>
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
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>
<!-- dialog box that used in this page -->