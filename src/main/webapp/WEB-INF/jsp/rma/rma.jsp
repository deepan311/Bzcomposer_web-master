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
<script>
window.onload = initShowHideDivs;
</script>
<title><spring:message code="BzComposer.creatermatitle" /></title>
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
var oo = null;
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
  
   if (o.readyState != 4 || o.status != 200)
    { 
       
      return;
    }
    document.getElementById("list").innerHTML = '&nbsp;&nbsp;&nbsp;'+trim(o.responseText) ;
     
    
}
function writeSelect1()
{
  
   if (o.readyState != 4 || o.status != 200)
    { 
       
      return;
    }
document.getElementById("Lnamelist").innerHTML = '&nbsp;&nbsp;&nbsp;'+trim(o.responseText) ;    
 
}
function refreshFnameNow(obj)
{
val=obj.value;
 selrow=null;
 mover=null;
 d = new Date();
  o = c(writeSelect);
  oGET(o,'${pageContext.request.contextPath}/include/FnameList.jsp?val=' + val);
}


function refreshLnameNow(obj)
{
val=obj.value;
 selrow=null;
 mover=null;
 d = new Date();
  o = c(writeSelect1);
  oGET(o,'${pageContext.request.contextPath}/include/LnameList.jsp?val=' + val);
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
<script>
function setTxtval()
{
document.getElementById("nTxt").value=document.getElementById("fNameVal").value;
document.getElementById("list").innerHTML="";
}

function setLTxtval()
{
document.getElementById("nLTxt").value=document.getElementById("lNameVal").value;
document.getElementById("Lnamelist").innerHTML="";
}

function hideListBox(){
	document.getElementById("list").innerHTML="";
	document.getElementById("Lnamelist").innerHTML="";
}

function hideFirstNameList(){
	document.getElementById("list").innerHTML="";
}
function hideLastNameList(){
	document.getElementById("Lnamelist").innerHTML="";
}
</script>
<style type="text/css">
.dhtmlgoodies_answer{
height: 600px !important;
}
.height250 {
height: 250px;

}
.fht-tbody{
height: 180px !important; /*  change table height*/
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
<body>
<!-- begin shared/header -->
<form:form id="RMAForm" name="RMAForm" action="RMA.do?tabid=R0S0C0" method="post" modelAttribute="rmaDto">

<div id="ddcolortabsline">&nbsp;</div>
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding"><!-- begin Contents --> <span id="waitMessage"> </span>
	<div>
		<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.rma.createrma" />
		</span>
	</div>
	<br/>
	<div>
		<table class="table-notifications" width="100%">
			<thead>
				<tr onclick="hideListBox();">
					<th colspan="12" align="left" style="font-size:14px;">
						<spring:message code="BzComposer.rma.findinvoice"/>
					</th>
				</tr>
			</thead>
			<tbody>	
				<tr>
					<td nowrap="nowrap" width="20%" onclick="hideListBox();">
						<spring:message code="BzComposer.rma.firstname" />
					</td>
					<td width="20%">
						<!-- &nbsp;&nbsp;&nbsp; -->
						<c:if test="${not empty FName}">
							<input id="nTxt" name="fnameTxt" type="text" style="width:115px"
							maxlength="40" onkeyup="refreshFnameNow(this);" onkeydown="hideLastNameList();"
							value='${FName}' />
						</c:if>
						<c:if test="${empty FName}">
							<input id="nTxt" name="fnameTxt" type="text" style="width:122px" maxlength="40" 
							onkeyup="refreshFnameNow(this);" onkeydown="hideLastNameList();" />
						</c:if>
						<br>
						<div id="list"> </div>
					</td>
					<td nowrap="nowrap" width="20%" onclick="hideListBox();">
						<!-- &nbsp;&nbsp;&nbsp;&nbsp; -->
						<spring:message code="BzComposer.rma.lastname" />
					</td>
					<td width="20%" nowrap="nowrap">
										<!-- &nbsp;&nbsp;&nbsp; -->
						<c:if test="${not empty LName}">
							<input name="lnameTxt" id="nLTxt" type="text" style="width:115px"
							maxlength="40" value='${LName}'
							onkeyup="refreshLnameNow(this);" onkeydown="hideFirstNameList();" />
						</c:if>
						<c:if test="${empty LName}">
							<input name="lnameTxt" id="nLTxt" type="text" style="width:122px"
							maxlength="40" onkeyup="refreshLnameNow(this);"
							onkeydown="hideFirstNameList();" />
						</c:if>
						<br>
						<div id="Lnamelist"></div>
					</td>
					<td width="10%" nowrap="nowrap">
						<input type="button" class="formButton" onclick="alert(12);" name="findBtn"
						value='<spring:message code="BzComposer.rma.findcustomerinvoice" />' >			
					</td>
				</tr>
				<tr>
					<td width="20%" onclick="hideListBox();">
						<spring:message code="BzComposer.rma.order" />
					</td>
					<td width="20%">
						<!-- &nbsp;&nbsp;&nbsp;&nbsp; -->
						<form:input path="order" maxlength="10" onkeypress="return numbersonly(event,this.value)"
						onkeydown="hideListBox();" />
					</td>
					<td width="20%">
						<!-- &nbsp;&nbsp;&nbsp;&nbsp; -->
						<spring:message code="BzComposer.rma.orderdate" />
					</td>
					<td width="20%">
						<!-- &nbsp;&nbsp;&nbsp;&nbsp; -->
						<form:input path="orderDate" />
					</td>
					<td width="20%">
						<img src="${pageContext.request.contextPath}/images/cal.gif"
						onclick="displayCalendar(document.RMAForm.orderDate,'mm-dd-yyyy',this);">
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div>
	<div class="grid_8 height250 tabla-listados" id="table-negotiations" onclick="hideListBox();">
		<table id="rmaList" class="tabla-listados" cellpadding="0" cellspacing="0">
		<thead style="font-weight: bold;">
			<tr>
				<th class="emblem">&nbsp;</th>
				<th style="font-size:14px;">
					<spring:message code="BzComposer.rma.order" />
				</th>
				<th style="font-size:14px;">
					<spring:message code="BzComposer.rma.customername" />
				</th>
				<th style="font-size:14px;">
					<spring:message code="BzComposer.rma.orderdate" />
				</th>
				<th style="font-size:14px;">
					<spring:message code="BzComposer.rma.saledate" />
				</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty RMADetails}">
                <input type="hidden" name="RMADID" id="lSize" value='${RMADetails.size()}'>
                <c:forEach items="${RMADetails}" var="objList" varStatus="loop">
                <tr id="${loop.index}" onclick="setRMA('${objList.rma}','${objList.fname}','${objList.lname}','${loop.index}');">
                    <td>&nbsp;</td>
                    <td style="font-size:14px;">
                        ${objList.rma}
                    </td>
                    <td nowrap="nowrap" style="font-size:14px;">
                        ${objList.fname}
                        &nbsp;&nbsp;
                        ${objList.lname}
                    </td>
                    <td nowrap="nowrap" style="font-size:14px;">
                        ${objList.orderDate}
                    </td>
                    <td nowrap="nowrap" style="font-size:14px;">
                        ${objList.sentDate}
                    </td>
                </tr>
                </c:forEach>
			</c:if>
		</tbody>
		</table>
	</div>
	<div align="center">
		<table onclick="hideListBox();">
			<tr>
				<td>
					<div align="center">
						<input type="button" name="custDetlBtn" value='<spring:message code="BzComposer.rma.customerdetail" />'
						class="formButton"> 
						&nbsp;&nbsp;
						<input type="button" disabled="disabled" id="RMAI" onclick="getRMA();" name="RMABtn"
						value='<spring:message code="BzComposer.rma.rmabuttontext" />'
						Title='<spring:message code="BzComposer.rma.rmabuttontitle" />' class="formButton"> 
						<input type="hidden" name="RMAval" id="RMAnum">
						<input type="hidden" name="Fnameval" id="RMAfname"> 
						<input type="hidden" name="Lnameval" id="RMAlname">
					</div>
				</td>
			</tr>
		</table>
	</div>
	</div>
	<%--
	<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<script>
window.onload = initShowHideDivs;

</script>
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
var oo = null;
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
  
   if (o.readyState != 4 || o.status != 200)
    { 
       
      return;
    }
    document.getElementById("list").innerHTML = '&nbsp;&nbsp;&nbsp;'+trim(o.responseText) ;
     
    
}
function writeSelect1()
{
  
   if (o.readyState != 4 || o.status != 200)
    { 
       
      return;
    }
document.getElementById("Lnamelist").innerHTML = '&nbsp;&nbsp;&nbsp;'+trim(o.responseText) ;    
 
}
function refreshFnameNow(obj)
{
val=obj.value;
 selrow=null;
 mover=null;
 d = new Date();
  o = c(writeSelect);
  oGET(o,'${pageContext.request.contextPath}/include/FnameList.jsp?val=' + val);
}


function refreshLnameNow(obj)
{
val=obj.value;
 selrow=null;
 mover=null;
 d = new Date();
  o = c(writeSelect1);
  oGET(o,'${pageContext.request.contextPath}/include/LnameList.jsp?val=' + val);
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
<script>
function setTxtval()
{
document.getElementById("nTxt").value=document.getElementById("fNameVal").value;
document.getElementById("list").innerHTML="";
}

function setLTxtval()
{
document.getElementById("nLTxt").value=document.getElementById("lNameVal").value;
document.getElementById("Lnamelist").innerHTML="";
}

function hideListBox(){
	document.getElementById("list").innerHTML="";
	document.getElementById("Lnamelist").innerHTML="";
}

function hideFirstNameList(){
	document.getElementById("list").innerHTML="";
}
function hideLastNameList(){
	document.getElementById("Lnamelist").innerHTML="";
}
</script>
<style type="text/css">
.dhtmlgoodies_answer{
height: 600px !important;
}
.height250 {
height: 250px;

}
.fht-tbody{
height: 180px !important; /*  change table height*/
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
<title><spring:message code="BzComposer.RMA.RMATitle" /></title>
</head>
<body>
<!-- begin shared/header -->
<form:form action="RMA?tabid=R0S0C0" method="post" modelAttribute="rmaDto">

<div id="ddcolortabsline">&nbsp;</div>
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding"><!-- begin Contents --> <span id="waitMessage"> </span>
	<div><span
		style="font-size: 1.6em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.RMA.CreateRma" /></span></div>
		<br/>
		<div>
			<div id="table-negotiations" onclick="hideListBox();" >
				<table>		
					<tr>
						<td>
							<table class="table-notifications" border="0" cellspacing="0" width="100%" >
								<tr style="width:90%" onclick="hideListBox();">
									<th width="90%" align="left" colspan="12" style="font-size:14px;">
										<spring:message code="BzComposer.rma.FindInvoice" />
									</th>
								</tr>
								<tr style="width: 90%">
									<td nowrap="nowrap" colspan="3" onclick="hideListBox();">
										<spring:message code="BzComposer.RMA.FirstName" />
									</td>
									<td nowrap="nowrap" colspan="3">
										&nbsp;&nbsp;&nbsp;
										<c:if test="${not empty FName}">
											<input id="nTxt" name="fnameTxt" type="text" style="width:115px"
											maxlength="40" onkeyup="refreshFnameNow(this);" onkeydown="hideLastNameList();"
											value='${FName}' />
										</c:if>
										<c:if test="${empty FName}">
											<input id="nTxt" name="fnameTxt" type="text" style="width:122px" maxlength="40" 
											onkeyup="refreshFnameNow(this);" onkeydown="hideLastNameList();" />
										</c:if>
										<br>
										<div id="list">
										</div>
									</td>
									<td nowrap="nowrap" colspan="3" onclick="hideListBox();">
										&nbsp;&nbsp;&nbsp;&nbsp;
										<spring:message code="BzComposer.RMA.LastName" />
									</td>
									<td colspan="2" nowrap="nowrap">
										&nbsp;&nbsp;&nbsp;
										<c:if test="${not empty LName}">
											<input name="lnameTxt" id="nLTxt" type="text" style="width:115px"
											maxlength="40" value='${LName}'
											onkeyup="refreshLnameNow(this);" onkeydown="hideFirstNameList();" />
										</c:if>
										<c:if test="${empty LName}">
											<input name="lnameTxt" id="nLTxt" type="text" style="width:122px"
											maxlength="40" onkeyup="refreshLnameNow(this);"
											onkeydown="hideFirstNameList();" />
										</c:if>
										<br>
										<div id="Lnamelist">
										</div>
									</td>
								</tr>
								<tr style="width: 90%">
									<td nowrap="nowrap" colspan="3" onclick="hideListBox();">
										<spring:message code="BzComposer.RMA.Order" />
									</td>
									<td nowrap="nowrap"colspan="3">
										&nbsp;&nbsp;&nbsp;&nbsp;
										<form:input path="order" maxlength="10" onkeypress="return numbersonly(event,this.value)"
										onkeydown="hideListBox();" />
									</td>
									<td nowrap="nowrap" colspan="3">
										&nbsp;&nbsp;&nbsp;&nbsp;
										<spring:message code="BzComposer.RMA.OrderDate" />
									</td>
									<td nowrap="nowrap" colspan="3">
										&nbsp;&nbsp;&nbsp;&nbsp;
										<form:input property="orderDate" />
									</td>
									<td>
										<img src="${pageContext.request.contextPath}/images/cal.gif"
										onclick="displayCalendar(document.RMAForm.orderDate,'mm-dd-yyyy',this);">
									</td>
								</tr>
							</table>
						</td>
						<td width="10%" valign="top" onclick="hideListBox();">
							<table class="table-notifications" border="0" cellspacing="0" width="100%" >
								<tr>
									<th align="left" colspan="12" style="font-size:14px;">
										&nbsp;&nbsp;
									</th>
								</tr>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										<input type="button" class="formButton" onclick="checkName();" name="findBtn"
										value='<spring:message code="BzComposer.RMA.RMA.FindCustInvoice" />'>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</table>
					<div>
						<div>
	 						<div class="grid_8 height250 tabla-listados" id="table-negotiations" onclick="hideListBox();">
      							<table id="rmaList" class="tabla-listados" cellpadding="0" cellspacing="0">
									<thead style="font-weight: bold;">
										<tr>
											<th class="emblem">&nbsp;</th>
											<th style="font-size:14px;">
												<spring:message code="BzComposer.RMA.Order" />
											</th>
											<th style="font-size:14px;">
												<spring:message code="BzComposer.RMA.CustomerName" />
											</th>
											<th style="font-size:14px;">
												<spring:message code="BzComposer.RMA.OrderDate" />
											</th>
											<th style="font-size:14px;">
												<spring:message code="BzComposer.RMA.SaleDate" />
											</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${not empty RMADetails}">
                                            <input type="hidden" name="RMADID" id="lSize" value='${RMADetails.size()}'>
                                            <c:forEach items="${RMADetails}" var="objList" varStatus="loop">
                                            <tr id="${loop.index}" onclick="setRMA('${objList.rma}','${objList.fname}','${objList.lname}','${loop.index}');">
                                                <td>&nbsp;</td>
                                                <td style="font-size:14px;">
                                                    ${objList.rma}
                                                </td>
                                                <td nowrap="nowrap" style="font-size:14px;">
                                                    ${objList.fname}
                                                    &nbsp;&nbsp;
                                                    ${objList.lname}
                                                </td>
                                                <td nowrap="nowrap" style="font-size:14px;">
                                                    ${objList.orderDate}
                                                </td>
                                                <td nowrap="nowrap" style="font-size:14px;">
                                                    ${objList.sentDate}
                                                </td>
                                            </tr>
                                            </c:forEach>
										</c:if>
									</tbody>
								</table>
							</div>
						</div>
						<div align="center">
							<table onclick="hideListBox();">
								<tr>
									<td>
										<div align="center">
											<input type="button" name="custDetlBtn" value='<spring:message code="BzComposer.RMA.RMA.CustmerDetails" />'
											class="formButton"> 
											&nbsp;&nbsp;
											<input type="button" disabled="disabled" id="RMAI" onclick="getRMA();" name="RMABtn"
											value='<spring:message code="BzComposer.RMA.RMA.RmaButton" />'
											Title='<spring:message code="BzComposer.RMA.RMA.RmaButtonTitle" />' class="formButton"> 
											<input type="hidden" name="RMAval" id="RMAnum">
											<input type="hidden" name="Fnameval" id="RMAfname"> 
											<input type="hidden" name="Lnameval" id="RMAlname">
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<!-- end Contents -->
				</div>
			</div>
	--%>
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

function checkName()
{
	debugger ;
// if(trim(document.RMAForm.fnameTxt.value)=="" && trim(document.RMAForm.lnameTxt.value)==""){

// 	document.RMAForm.fnameTxt.focus();
	
// }
// else if(document.RMAForm.order.value!= "" && !IsNumeric(document.RMAForm.order.value)){

// 	document.RMAForm.order.focus();
// }
// else
// {
	
	
	//private String order;

	//private String orderDate;

	rFnm = document.getElementById('nTxt').value;
	rLnm = document.getElementById('nLTxt').value;
	document.forms['RMAForm'].action = "RMA?tabid=R0S0C0&fname="rFnm+"&lname="+rLnm ;
	document.forms['RMAForm'].submit();

//}	

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

function setRMA(rno,fnm,lnm,rid)
{
size=document.getElementById("lSize").value;
// for(i=0;i<size;i++){
// var row1=document.getElementById(i);
// row1.className = "";
// }
// var rd=document.getElementById(rid);
// rd.className = "draft";
for(i=0;i<size;i++){
	 document.getElementById(i).classList.remove('draft');		
	 if(i%2==0){	
		 if(size !=(i+1)){
		 document.getElementById(i+1).classList.add('odd');
		 }
	 }
}
if(rid%2 !=0){ 	
	document.getElementById(rid).classList.remove('odd'); 		
}
var rd = document.getElementById(rid).classList.add('draft');

document.getElementById("RMAnum").value=rno;
document.getElementById("RMAfname").value=fnm;
document.getElementById("RMAlname").value=lnm;
document.getElementById("RMAI").disabled=false;
}
function getRMA()
{
	rnum=document.getElementById("RMAnum").value;
	/*rFnm=document.getElementById("RMAfname").value;
	rLnm=document.getElementById("RMAlname").value;*/
	rFnm = document.getElementById('nTxt').value;
	rLnm = document.getElementById('nLTxt').value;
	rmawindow=window.open("RMA?tabid=RmaInfo&OrderID="+rnum+"&fname="+rFnm+"&lname="+rLnm,null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
	rmawindow.moveTo(50,50);
	
}

</script>
