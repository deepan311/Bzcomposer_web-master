<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<script>
window.onload = initShowHideDivs;
</script>
<script type="text/javascript">
function cancelRMADialog() {
	debugger;
    $("#cancelRMADialog").dialog({
    	resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "Ok": function () {
                $(this).dialog("close");
                debugger
                RID=document.getElementById("RMAID").value ;
        		document.forms[0].action = "RMA?tabid=R0R0M0&RMAno="+RID;
        		document.forms[0].submit();
            },
            Cancel: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}

function showRMAQuantityDialog() {
	debugger;
    $("#showRMAQuantityDialog").dialog({
    	resizable: false,
        height: 200,
        width: 550,
        modal: true,
        buttons: {
            "Ok": function () {
                $(this).dialog("close");
                debugger
            },
            Cancel: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}

function RMADetailsDialog() {
	debugger;
    $("#RMADetailsDialog").dialog({
    	resizable: false,
        height: 200,
        width: 550,
        modal: true,
        buttons: {
            "Ok": function () {
                $(this).dialog("close");
                debugger
            },
            Cancel: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}

function RMAQuantityDialog() {
	debugger;
    $("#RMAQuantityDialog").dialog({
    	resizable: false,
        height: 200,
        width: 550,
        modal: true,
        buttons: {
            "Ok": function () {
                $(this).dialog("close");
                debugger
            },
            Cancel: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}
</script>
<title><spring:message code="BzComposer.RMA.RMADetails.RMADetailsTitle"/> </title>
</head>
<body onload="init();">
<!-- begin shared/header -->
<form:form action="RMA?tabid=R0S0C0" method="post" modelAttribute="rmaDto">
	<div id="cos">

	<div class="statusquo ok">

	<div id="hoja">
	<div id="blanquito">
	<div id="padding"><!-- begin Contents --> <span id="waitMessage"> </span>

	<div id="table-negotiations">
	<table class="tabla-listados" cellspacing="0" width="90%">
	<tr>
		<td>
		<table class="tabla-listados" cellspacing="0" border="0" width="60%">
			<thead>
			<tr>
				<th class="emblem" colspan="2">
					<spring:message code="BzComposer.rma.InvoiceInfo" />
				</th>
			</tr>
			</thead>
			<tbody>
				<tr>
					<td><spring:message code="BzComposer.RMA.LastName" /></td>
					<td><c:if test="${not empty Lname}">
						<input type="text" disabled="disabled" value="${Lname}">
						</c:if>
						<c:if test="${empty Lname}">
						<input type="text" value="" disabled="disabled">
						</c:if></td>
				</tr>
				<tr>
					<td><spring:message code="BzComposer.RMA.FirstName" /></td>
					<td><c:if test="${not empty Fname}">
						<input type="text" disabled="disabled" value="${Fname}">
					</c:if>
					<c:if test="${empty Fname}">
						<input type="text" disabled="disabled" value="">
					</c:if></td>
				</tr>
				
				<tr>
					<td><spring:message code="BzComposer.RMA.Invoice" /></td>
					<td><c:if test="${not empty OrderID}">
						<input type="text" disabled="disabled" value="${OrderID}">
					</c:if>
					<c:if test="${empty OrderID}">
						<input type="text" disabled="disabled" value="">
					</c:if></td>
				</tr>
			</tbody>
			</table>
			
		</td>
	</tr>
	<tr>
		<td>
		<table class="section-border" style="padding: 4px;">
		<tr>
			<td>
			&nbsp;&nbsp;&nbsp;<spring:message code="BzComposer.rma.InvoiceItem" />

			<div style="overflow:auto;height:200;width:425">
			
			<table cellspacing="0">
			<thead>
				<tr>
					<th><spring:message code="BzComposer.RMA.ItemCode" /></th>
					<th><spring:message code="BzComposer.RMA.ItemDescription" /></th>
					<th><spring:message code="BzComposer.RMA.RmaDetail.Qty" /></th>
					<th><spring:message code="BzComposer.RMA.UnitPrice" /></th>
					<th><spring:message code="BzComposer.RMA.UnitWeight" /></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty ItemDetails}">
                    <input type="hidden" name="RMADID" id="lSize" value='${ItemDetails.size()}'>
                    <c:forEach items="${ItemDetails}" var="objList" varStatus="loop">
                        <tr id='${loop.index}$' onclick="setItemId('${objList.cartID}','${loop.index}$');">
                            <td align="center" nowrap="nowrap">${objList.itemCode}</td>
                            <td>${objList.itemDesc}&nbsp;&nbsp;${objList.lname}</td>
                            <td>${objList.qty}</td>
                            <td align="right">${objList.unitPrice}</td>
                            <td align="right">${objList.unitWeight}</td>
                        </tr>
                    </c:forEach>
				</c:if>
			</tbody>
			</table>
			
			</div>
		
			</td>

			<td >
			
			
			<table  class="tabla-listados" cellspacing="0" border="0">
			<thead>
				<tr>
					<th class="emblem" colspan="2">
						<spring:message code="BzComposer.rma.RMAInfo" />
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<td nowrap="nowrap"><spring:message code="BzComposer.RMA.lastrma" />
				</td>
				<td> <c:if test="${not empty LastRMA}">
					<input type="text" disabled="disabled" value="${LastRMA}">
				</c:if>
				<c:if test="${empty LastRMA}">
					<input type="text" disabled="disabled" value="">
				</c:if>
				
				</td>
				
			</tr>
			<tr>
				<td colspan="2">
					<hr>
				</td>
			</tr>
			<tr>
				<td nowrap="nowrap"><spring:message code="BzComposer.RMA.rma" /></td>
				<td><form:input path="rma"  maxlength="30" /></td>
			</tr>
			<tr>
				<td><spring:message code="BzComposer.RMA.Reason" /></td>
				<td><form:input path="reason" value="" maxlength="45"/></td>
			</tr>
			<tr>
				<td><spring:message code="BzComposer.RMA.RmaQty" /></td>
				<td><form:input path="qty" maxlength="6"
				onkeypress="return numbersonly(event,this.value)" value="" /></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="button"  disabled="disabled" id="approveId" class="formButton"
					onclick="approveRMA();" name="findBtn" value="Approve RMA"
					title="Select an Item from Invoice">
					
				</td>
				
			</tr>
			</tbody>
		</table>
		</td>
	</tr>
	</table>
	<tr align="center">
		<td >
			<spring:message code="BzComposer.rma.RmaItem" />
		</td>
	</tr>
	<tr>
	<td>	



		<div id="table-negotiations" style="overflow:auto;height:350" class="section-border">
			<table class="tabla-listados" cellspacing="0">
				<thead>
					<tr>
						<th><spring:message code="BzComposer.RMA.rma" /></th>
						<th><spring:message code="BzComposer.RMA.ItemCode" /></th>
						<th><spring:message code="BzComposer.RMA.ItemDescription" /></th>
						<th><spring:message code="BzComposer.RMA.Reason" /></th>
						<th align="right"><spring:message code="BzComposer.RMA.RmaQty" /></th>
						<th align="right"><spring:message code="BzComposer.RMA.UnitPrice" /></th>
						<th align="right"><spring:message code="BzComposer.RMA.UnitWeight" /></th>

					</tr>
					<c:if test="${not empty RMADetails}">
                        <input type="hidden" name="RMALID" id="l2Size" value='${RMADetails.size()}'>
                        <c:forEach items="${RMADetails}" var="RobjList" varStatus="loop">
                        <tr id="${loop.index}$$" onclick="setRMAId('${RobjList.rma}','${loop.index}$$');">
                            <td>${RobjList.rma}</td>
                            <td nowrap="nowrap">${RobjList.itemCode}</td>
                            <td nowrap="nowrap" width="250">${RobjList.itemDesc}</td>
                            <td nowrap="nowrap">${RobjList.reason}</td>
                            <td align="right">${RobjList.qty}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td align="right">${RobjList.unitPrice}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td align="right">${RobjList.unitWeight}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        </tr>
                        </c:forEach>
					</c:if>

				</thead>
				<tbody>
					<tr>
						<td></td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td><input type="hidden" id="RMAID" name="RMAVal"> <input
				type="hidden" id="cartID" name="ItemVal"></td>
		</tr>
		<tr>
			<td align="center">
			    <input type="button" disabled="disabled" id="RMACancel" onclick="CancelRMA();" class="formButton"
				title='<spring:message code="BzComposer.RMA.RmaDetails.CancelRMAToolTip" />' name="findBtn" value='<spring:message code="BzComposer.RMA.RmaDetails.CancelRMA" />'>&nbsp;&nbsp;
				<input type="button" onclick="closeme();" name="findBtn" value='<spring:message code="BzComposer.RMA.RmaDetails.Close" />'' class="formButton"
				title="Close">
			</td>
			
		</tr>
	</table>
	</div>
	</div>

	<!-- end Contents --></div>
	</div>
	</div>
	</div>
	<div class="clear"></div>	
</form:form>
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

	function trim(inputString) {
	   // Removes the spaces  return from the passed string. 
	   var retValue = inputString;
	   var ch = retValue.substring(0, 1);
	   while (ch == "\n" || ch == "\r" || ch==" " || ch=="\t" ) { 
    	  retValue = retValue.substring(1, retValue.length);
	      ch = retValue.substring(0, 1);
	   }
	   return retValue; 
	}

function approveRMA()
{
	cartID=document.getElementById("cartID").value ;
	if(trim(document.RMAForm.rma.value)==""){

		return RMADetailsDialog();
		document.RMAForm.rma.focus();
	}
	else if(parseInt(document.RMAForm.qty.value)==0 || document.RMAForm.qty.value==""){

		return showRMAQuantityDialog();
		document.RMAForm.qty.focus();
	}
	else if((!IsNumeric(document.RMAForm.qty.value))){

		return RMAQuantityDialog();
		document.RMAForm.qty.focus();
	}
	else{
		document.RMAForm.qty.value = parseInt(document.RMAForm.qty.value);
		document.forms[0].action = "RMA?tabid=R0A0D0&cartID="+cartID ;
		document.forms[0].submit();
	}
}	

	function IsNumeric(stext){
	   var validchars = "0123456789";
	   var isnumber=true;
	   var ch;
	   for (i = 0; i < stext.length && isnumber == true; i++){ 
	      ch = stext.charAt(i); 
    	  if (validchars.indexOf(ch) == -1){
	         isnumber = false;
    	  }
       }
	   return isnumber;
   }
   
function CancelRMA()
{	
	debugger;
	return cancelRMADialog();
	/* if(confirm('<spring:message code="BzComposer.RMA.CancelRMA.Confirm" />'))
	{
		RID=document.getElementById("RMAID").value ;
		document.forms[0].action = "RMA?tabid=R0R0M0&RMAno="+RID;
		document.forms[0].submit();
	} */
}	

function setItemId(Ino,rid)
{

size=document.getElementById("lSize").value;
for(i=0;i<size;i++){
var row1=document.getElementById("$"+i+"$");
row1.className="";
}
var rd=document.getElementById(rid);
rd.className='draft';

document.getElementById("cartID").value=Ino;
document.getElementById("approveId").disabled=false;
}

function setRMAId(Rno,rid)
{

size=document.getElementById("l2Size").value;
for(i=0;i<size;i++){
var row1=document.getElementById(i+"$$");
row1.className="";
}
var rd=document.getElementById(rid);
rd.className='draft';
document.getElementById("RMAID").value=Rno;
document.getElementById("RMACancel").disabled=false;
}

function closeme()
{
window.close();
}

function init(){
	debugger;
	document.RMAForm.rma.value="";
}

</script>
<!-- Dialog box used in rmadetails page -->
<div id="cancelRMADialog" title = "Cancel RMA" style="display:none;">
	<p><spring:message code="BzComposer.RMA.CancelRMA.Confirm" /></p>
</div>
<div id="RMADetailsDialog" title = "Select First Name and Last Name" style="display:none;">
	<p><spring:message code="BzComposer.RMA.rmano.Validation" /></p>
</div>
<div id="showRMAQuantityDialog" title = "Enter Quantity greater than 0" style="display:none;">
	<p><spring:message code="BzComposer.RMA.qty.Validation" /></p>
</div>
<div id="RMAQuantityDialog" title = "Enter Quantity in only digits" style="display:none;">
	<p><spring:message code="BzComposer.RMA.qty.InNumber.Validation" /></p>
</div>