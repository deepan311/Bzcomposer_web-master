<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.checkpoordertitle"/></title>
<style type="text/css">
.height250 {
height: 47%;
}
.fht-tbody{
height: 85% !important; /*  change table height*/
border-bottom: 1px solid rgb(207, 207, 207);
}
table.tabla-listados {
width: 100%;
border: 1px solid rgb(207, 207, 207);
margin: 0px 0px 0px 0px;
margin: 0px 0px 0px 0px;
}
table.tabla-listados tbody tr.odd td {
background: #e1e5e9;
}
</style>
</head>
<script type="text/javascript">
function showUpdateDialog()
{
	debugger;
	event.preventDefault();
	$("#showUpdateDialog").dialog({
	    	resizable: false,
	        height: 200,
	        width: 500,
	        modal: true,
	        buttons: {
	            "<spring:message code='BzComposer.global.ok'/>": function () {
					$(this).dialog("close");
					document.getElementById("modi").disabled=false;
					document.forms['checkPO'].action ="CheckPO?tabid=UpdateCheckPO";
					document.forms['checkPO'].submit();
				   	//$('checkPO').submit();


	            },
	            <spring:message code='BzComposer.global.cancel'/>: function () {
	                $(this).dialog("close");
	                return false;
	            }
	        }
	    });
	    return false;
}
function checkduplicatenamedialog()
{
	debugger;
	event.preventDefault();
	$("#checkduplicatenamedialog").dialog({
	    	resizable: false,
	        height: 200,
	        width: 500,
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
<body>
<!-- begin shared/header -->
<form Id= "checkPO" action="CheckPO?tabid=ShowListCheckPO" method="post">
<div id="">
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div>
	<div style="float: left;">
		<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.checkpurchaseorder.checkpoorders" />
		</span>
	</div>
	<div style="float: right;">
		<table>
			<tr>
				<td colspan="2" align="right" style="font-size:14px;">
					<input type="submit" class="formbutton" name="findBtn" value='<spring:message code="BzComposer.checkpurchaseorder.refresh"/>'>
				</td>
			</tr>
		</table>
	</div>
</div>
<div>
	<div id="table-negotiations">
		<table cellspacing="0" align="center" style="width: 100%;">
			<tr align="left">
				<td style="font-size:14px;">
					<c:if test="${not empty IsUpdated}">
						<strong><span class="msgstyle">${IsUpdated}</span></strong>
					</c:if>
				</td>
			</tr>
		</table>
		<div>
			<table align="center">
				<tr>
					<td align="center">
						<c:if test="${not empty msg}">
							<font color="red"><b>${msg}</b></font>
						</c:if>
					</td>
				</tr>
			</table>
<!-- 	<span -->
<%-- 		style="font-size: 1.14px; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;"><bean:message --%>
<!-- 		key="BzComposer.purchase.PurchaseBoard.PurchaseList" /></span> -->

			<div>
				<div class="grid_8 height250 tabla-listados" id="table-negotiations" >
      				<table id="checkPOList" class="tabla-listados" cellpadding="0" cellspacing="0">
						<thead style="font-weight: bold;">
							<tr>
								<th style="font-size:14px;">
									<spring:message code="BzComposer.checkpurchaseorder.ponumber"/>
								</th>

								<th style="font-size:14px;">
									<spring:message code="BzComposer.checkpurchaseorder.vendor"/>
								</th>
								<th style="font-size:14px;">
									<spring:message code="BzComposer.checkpurchaseorder.itemcode"/>
								</th>
								<th style="font-size:14px;">
									<spring:message code="BzComposer.checkpurchaseorder.itemname"/>
								</th>
								<th style="font-size:14px;">
									<spring:message code="BzComposer.accountreceivable.dateadded" />
								</th>
								<th style="font-size:14px;">
									<spring:message code="BzComposer.checkpurchaseorder.orderquantity" />
								</th>
								<th style="font-size:14px;">
									<spring:message code="BzComposer.checkpurchaseorder.receivedquantity"/>
								</th>
								<th style="font-size:14px;">
									<spring:message code="BzComposer.checkpurchaseorder.amountdifference"/>
								</th>
								<th style="font-size:14px;">
									<spring:message code="BzComposer.checkpurchaseorder.itemreceived"/>
								</th>
								<th style="font-size:14px;">
									<spring:message code="BzComposer.global.memo"/>
								</th>
							</tr>
						</thead>
						<tbody>
						    <c:if test="${not empty PurchaseBoardDetails}">
								<%
									int ndx = 0;
								%>
									<%--<bean:size id="SalesListSize" name="PurchaseBoardDetails" />--%>
										<c:forEach items="${PurchaseBoardDetails}" var="objList">
											<tr id='<%=ndx%>$$' >
												<%
													ndx++;
												%>
												<td align="left" style="font-size:14px;">
													<spring:message code="BzComposer.purchase.PONumPrefix" />
													${objList.po_no}
												</td>

												<td align="left" style="font-size:14px;">
													${objList.lastName},${objList.firstName}
												</td>
	                       						<td align="left" style="font-size:14px;">
														${objList.inventoryCode}
                       							</td>
												<td align="left" style="font-size:14px;">
														${objList.itemName}
												</td>
												<td align="left" style="font-size:14px;">
														${objList.dateAdded}
												</td>
												<td align="center" style="font-size:14px;">
														${objList.orderQty}
												</td>
												<td align="center" style="font-size:14px;">
														${objList.orderQty}
												</td>
												<td align="center" style="font-size:14px;">
														${objList.total}
												</td>
												<td align="left" style="font-size:14px;">
													<c:if test="${objList.isReceived == '1'}">
														<input type="checkbox" name="isReceived<%=ndx%>"
															   id="isReceivedId" title="isReceived" checked="true" value="ON"
															   onclick="makeUpdate(${objList.po_no},this);">
													</c:if>
													<c:if test="${objList.isReceived == '0'}">
														<input type="checkbox" name="isReceived<%=ndx%>"
															   id="isReceivedId" title="isReceived" value="ON"
															   onclick="makeUpdate(${objList.po_no},this);">
													</c:if>
												</td>
												<td align="left" style="font-size:14px;">
														${objList.memo}
												</td>
											</tr>
										</c:forEach>
								        <input type="hidden" name="sListSize" id="lSize" value='<%=ndx%>'>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<table align="left">
			<tr align="center">
				<td style="font-size:14px;">
					<input type="button" class="formbutton" disabled="disabled" id="modi" onclick="makeUpdateInList();" title="Apply" name="modifyBtn"
					value='<spring:message code="BzComposer.checkpurchaseorder.apply" />'>
				 	<input type="hidden" name="ONum" id="ONumId">
				 	<input type="hidden" name="sEmail" id="sEmailID">
				 	<input type="hidden" name="rNum" id="rowONum">
				 	<input type="hidden" name="senderEmail" id="EID">
				</td>
			</tr>
		</table>
		<input type="hidden" id="ordId" name="OrderValue" value="">
		<input type="hidden" id="statusId" name="StatusValue" value="">
		<input type="hidden" id="ordSize" name="Size" value="">
	<!-- end Contents -->
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	<input type="hidden" id="ord_value" />
		<input type="hidden" id="po_value" />
</form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>

</body>
</html>

<script type="text/javascript">
function setIsEmail(){

	document.getElementById("isEmailChk").value=document.getElementById("EmailId").value;
	document.forms[0].action = "Email?tabid=EOSOLO";
	document.forms[0].submit();
}
function checkName(){
	if(document.RMAForm.fnameTxt.value=="" && document.RMAForm.lnameTxt.value=="")	{


		return checkduplicatenamedialog();
	}
	else{
		document.forms[0].action = "RMA?tabid=R0S0C0" ;
		document.forms[0].submit();
	}
}

function setRowId(rowId,email,rid){
	document.getElementById("po_value").value=rowId;

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
	debugger;
	for(var j=0;j<cnt;j++){
		ord+=ordarr[j]+";";
		status+=statusarr[j]+";";
	}
	document.getElementById('ordSize').value=cnt;
	document.getElementById('ordId').value=ord;
	document.getElementById("statusId").value=status;

	debugger;
	debugger;
	return showUpdateDialog();
	/* response = window.confirm("Do you want to update record?");
	if(response){
		document.getElementById("modi").disabled=false;
		document.forms[0].action = "CheckPO?tabid=UpdateCheckPO";
		document.forms[0].submit();
	}  */
}

function SaleSearch(){
	Myid="";
	count=0;
	size=document.getElementById("lSize").value;
	type=document.getElementById("sType").value;
	typeVal=document.getElementById("SearchVal").value;

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
<!-- Dialog box used in checkPO page -->
<div id="showUpdateDialog" style="display:none;">
	<p><spring:message code="BzComposer.checkpurchaseorder.updaterecordmessage"/></p>
</div>
<div id="checkduplicatenamedialog" style="display:none;">
	<p><spring:message code='BzComposer.checkpurchaseorder.enterfirstorlastname'/></p>
</div>