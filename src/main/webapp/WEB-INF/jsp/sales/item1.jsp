<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.itemtitle" /></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#myInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#ItemDetails tr").filter(function() {
				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	});
</script>
<script>
	$(function() {
		$('#sortBy').change(function(){
			debugger;
			var sortBy = $(this).val();
			$.ajax({
				type: "POST",
				url:"ItemAjax?tabid=sortItem&SortBy="+sortBy,
				data:{sortBy : sortBy},
			}).done(function(data){
				$('#ItemDetails').html('');
				var custDetails = "";
				for(var i=0; i<data.length; i++){
					debugger;
					var objList = data[i];
                    custDetails = custDetails +
                        "<input type='hidden' name='listSize' id='lSize' value='"+data.length+"'>"
                        +"<tr id='"+i+"$$' onclick='setRowId("+objList.inventoryId+","+i+");'>"
                        +"<td colspan='2' style='font-size:12px;'><b>("+objList.itemCode+")</b>"+objList.itemName+"</td></tr>";

                }
				$('#ItemDetails').html(custDetails);
			});
		});
	});
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
function c(r)
{
	if (___)
	{
    	var t = (___5) ? "Microsoft.XMLHTTP" : "Msxml2.XMLHTTP";
    	try
    	{
      		o = new ActiveXObject(t);
      		o.onreadystatechange = r;
    	}
    	catch (ex)
    	{
      		alert("<bean:message key='BzComposer.common.needToEnableActiveXObject'/> ts.." + ex);
    	}
  	}
	else
	{
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


</script>
<script>

function showCustomerValidationDialog()
{
	event.preventDefault();
	$("#showCustomerValidationDialog").dialog({
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
	padding: 5px 0px 5px 12px;
	/* 	border-bottom: 1px solid rgb(207, 207, 207); */
	background: #fff;
	vertical-align: top;
}
</style>
</head>
<body onload="initialize();">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<form:form name="CustomerForm" enctype="MULTIPART/FORM-DATA"  method="post" modelAttribute="customerDto">
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<!-- begin Contents -->
<div>
<div style="float: left;">
	<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.item.iteminfo" />
	</span>
	<table>
		<tr>
			<td>
				<spring:message code="BzComposer.customer.sortby"/>
			</td>
			<td>
				<select id="sortBy">
					<option value="1"><spring:message code="BzComposer.Item.ItemName"/></option>
					<option value="2"><spring:message code="BzComposer.Item.ItemCode"/></option>
				</select>
			</td>
			<td colspan="4">&nbsp;</td>
		</tr>
	</table>
	<table>
		<tr>
			<td>
				<spring:message code="BzComposer.searchbuttontext" />:
			</td>
			<td>
				<input type="text" id="myInput">
			</td>
			<td colspan="4">&nbsp;</td>
		</tr>
	</table>
</div>
<div style="float: right;">
	<table>
		<tr align="right">
			<td colspan="6">
				<div>
					<input type="button" class="formbutton" value="  <spring:message code='BzComposer.global.edit'/>  " onclick="manageItem('EDIT');" >
					<input type="button" class="formbutton" value="  <spring:message code='BzComposer.global.delete'/>  " onclick="manageItem('DELETE');" />
				</div>
				<div>
					<form:hidden path="clientVendorID" />
					<form:hidden path="selectedRowID" />
					<input type="hidden" name="cvId" id="vendrId" value="0" />
					<input type="hidden" name="SelectedRID" id="setRID" value="">
				</div>
			</td>
		</tr>
	</table>
</div>
</div>
<table style="width: 100%; apdding: 0;">
	<tr>
		<td>
			<table style="padding: 0;width: 100%; margin-top: 10px;" align="center">
				<tr>
					<td valign="top" colspan="1" style="width: 300px; padding: 0; height: 75vh; border: 1px solid #ccc;">
						<table class="tabla-listados" cellspacing="0" style=" border: 0; padding: 0;margin: 0; height: auto;">
							<thead>
								<tr valign="top">
									<th class="emblem" style="font-size:12px;">
										<div align="center">
											<spring:message code="BzComposer.additem.item" />
										</div>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="4">
										<div id="custDiv" style="overflow: auto; height:auto;">
										<div id="custDiv">
											<table id="custTable">
												<tbody id="ItemDetails">
												<c:if test="${not empty ItemDetails}">
                                                    <input type="hidden" name="listSize" id="lSize" value='${ItemDetails.size()}'>
                                                    <c:forEach items="${ItemDetails}" var="objList" varStatus="loop">
                                                        <tr id='${loop.index}$$' onclick="setRowId(${objList.inventoryId}, ${loop.index});">
                                                            <td colspan="2" style="font-size:12px;">
																<b>(${objList.itemCode})</b>  ${objList.itemName}
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
												</c:if>
												</tbody>
											</table>
										</div>
										</div>
									</td>			<!-- Added on 26-04-2019 -->
								</tr>				<!-- Added on 26-04-2019 -->
							</tbody>
						</table>
					</td>
					<!-- Added on 26-04-2019 -->

					<td colspan="10" style="vertical-align: 0;">
						<div id="table-negotiations" style="height:auto;">
							<table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;">
								<thead>
									<tr>
										<th colspan="10" style="font-size:12px;">
											<spring:message code="BzComposer.Item.ItemInfo"/>
										</th>
									</tr>
								</thead>
									<input type="hidden" name="inventoryId" id="inventoryId11" value="${ItemDetails1[0].inventoryId}"/>

									<tbody>
									<tr>

										<td style="font-size:12px;">
											<spring:message code="BzComposer.Item.InventryName"/>:
										</td>
										<td   style="font-size:12px;">
											${ItemDetails1[0].itemCode}
										</td>
										<td>
											<input type="hidden" name="InvId" id="inId"
												   value='${ItemDetails1[0].inventoryId}' />
										</td>
										<td align="right">
											<spring:message code="BzComposer.Item.Subcategoryof" />:
										</td>

										<td>
											<select path="tectcmd" disabled="disabled"
													name="objList">
												<option value="0"><spring:message code="BzComposer.ComboBox.Select"/></option>
												<c:forEach items="${fillList}" var="obj">
													<option value="${obj.value}">${obj.label}</option>
												</c:forEach>
											</select>
										</td>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="right">
											<c:if test="${not empty ISCategory}">
												<div style="display:none">
													<spring:message code="BzComposer.Item.AddPicture" />
												</div>
											</c:if>
											<c:if test="${empty ISCategory}">
												<div style="display:block">
													<spring:message code="BzComposer.Item.AddPicture" />:
												</div>
											</c:if>
										</td>
										<td>
											<c:if test="${not empty ISCategory}">
												<div style="display:none">
													<input type="file" path="photoName" />
												</div>
											</c:if>
											<c:if test="${empty ISCategory}">
												<div style="display:block">
													<input type="file" path="photoName" />
												</div>
											</c:if>
										</td>
										<td>
											<c:if test="${not empty ISCategory}">
												<input type="checkbox" id="chk_cat"
													   checked="true" onclick="hide_other(this.value);" disabled="disabled"/> <spring:message code="BzComposer.Item.isCategory" />
												<input type="hidden" path="iscategory" value="on"/>
											</c:if>
											<c:if test="${empty ISCategory}">
												<input type="checkbox" id="chk_cat"
													   onclick="hide_other(this.value);" disabled="disabled"/> <spring:message code="BzComposer.Item.isCategory" />
												<input type="hidden" path="iscategory" value="off"/>
											</c:if>
										</td>
										<td align="right"><spring:message code="BzComposer.Item.Barcode" /></td>
										<td><input type="text" name="objList"  readonly="readonly" path="barcode" value="${ItemDetails1[0].barcode}"
												   onkeydown="return numbersonly(event,this.value)" /></td>
										<td>
										<td colspan="4">&nbsp;</td>
									</tr>
									<tr>
										<td>
											<spring:message code="BzComposer.Item.Discontinued" />

											<c:if test="${not empty objList.discontinued == true}">
												<input type="checkbox" name="discontinued" checked="checked">
											</c:if>
											<c:if test="${empty objList.discontinued == true}">
												<input type="checkbox" name="discontinued">
											</c:if>


										</td>
										<td colspan="6">&nbsp;</td>
									</tr>
									<tr>
										<th><spring:message code="BzComposer.Item.InventoryInformation" /></th>
									</tr>
									<script>
                                    <c:if test="${not empty state_gen}">
                                        var contry=document.CustomerForm.country.value;
                                        refreshItemsNow33(contry,'${state_gen}');
									</c:if>
									</script>
									<tr>
										<td>
											<c:if test="${ItemDetails1[0].taxable == true}">
												<input type="checkbox" name="taxable" checked="checked" >
											</c:if>
											<c:if test="${ItemDetails1[0].taxable == false}">
												<input type="checkbox" name="taxable">
											</c:if>
											<spring:message code="BzComposer.Item.Taxable" />
										</td>
										<td colspan="6">&nbsp;</td>
									</tr>
									<tr>
										<td style="font-size:12px;">
											<spring:message code="BzComposer.Item.InventoryTitle" />:
										</td>
										<td style="font-size:12px;" colspan="4">
												${ItemDetails1[0].itemName}
										</td>
										<td colspan="4">&nbsp;</td>
									</tr>
									<tr>
										<td style="font-size:12px;" >
											<spring:message code="BzComposer.Item.Description" />:
										</td>
										<td colspan="4">
											<textarea name="objList" path="invTitle" readonly="readonly" rows="4" cols="35">${ItemDetails1[0].invTitle}</textarea>
										</td>
										<td colspan="4">&nbsp;</td>
									</tr>
								</tbody>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>
<div>
	<input type="hidden" name="tabid" id="tabid" value="" />
</div>
</div>
</div>
</div>
</div>
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>
<script>
function initialize()
{
	var rid= "<%= request.getParameter("selectedRID")%>$$";
	var rd=document.getElementById(rid);
	rd.className = "draft";
	<c:if test="${not empty VendorFrm}">
		tableValue();
	</c:if>
}
function setRowId(rowid, rid)
{
	size=document.getElementById("lSize").value;
	//size1=document.getElementById("seSize").value;
	for(i=0;i<size;i++)
	{
		var row1=document.getElementById(i+"$$");
		row1.className = "";
	}
	/*for(j=0;j<size1;j++){
	row1=document.getElementById(j+"*$$");
	row1.className = "";
	}*/
	rid = rid+'$$';
	var rd=document.getElementById(rid);
	rd.className = "draft";
	document.getElementById("setRID").value=rid;
	var rd1=rid.replace("$$", "");
	document.CustomerForm.clientVendorID.value=rowid;
	var custid=document.CustomerForm.clientVendorID.value;
	getVendorInfo(rowid,rd1);
}

function getVendorInfo(inventoryId,rowId)
{
	//document.getElementById('vendrId').value=vendorid;
	//document.getElementById('vendrId').value=document.CustomerForm.clientVendorID.value;
	//var rowID = rowId;
	window.location = "Item?tabid=SearchItemView&InvId="+inventoryId;
	/* document.getElementById('tabid').value="Customer";
	debugger
	document.forms[0].action="Customer?";
	document.forms[0].submit(); */
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
function manageItem(cmd)
{
	debugger;
	var itemid = document.getElementById("inventoryId11").value;
	if (itemid==0)
	{

		return showCustomerValidationDialog();
		//return;
	}
	else
	{
		if (cmd=="EDIT")
		{
			showitem(itemid);

		}
		else if (cmd=="DELETE")
		{

			if(confirm("This will delete all the information related to this Customer (ID="+itemid+")\nAre you sure to delete ?")==true)
			{
			 window.location = "Item?tabid=DeleteItem&InvId="+itemid;
			}
			/* event.preventDefault();
			$("#deleteCustomer").dialog({
		    	resizable: false,
		        height: 200,
		        width: 500,
		        modal: true,
		        buttons: {
		            "<spring:message code='BzComposer.global.ok'/>": function () {
		                $(this).dialog("close");
		                document.forms[0].action="Customer?customerAction=DELETE&cvID="+cvid;
						document.forms[0].submit();
						$('form').submit();
		            },
		            <spring:message code='BzComposer.global.cancel'/>: function () {
		                $(this).dialog("close");
		                return false;
		            }
				}
			});*/
			return false;
		}
	}
}
function showitem(itemid)
{
	window
			.open(
					"Item?tabid=SearchItem&InvId="+itemid,
					null,
					"scrollbars=yes,height=600,width=900,status=yes,toolbar=no,menubar=no,location=no");
}
</script>
<!-- Dialog box used in sales order page -->
<div id="showCustomerValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.customerinfo.selectitemfirst"/></p>
</div>
<div id="deleteCustomer" style="display:none;">
	<p><spring:message code="BzComposer.customerinfo.deleteselecteditem"/></p>
</div>