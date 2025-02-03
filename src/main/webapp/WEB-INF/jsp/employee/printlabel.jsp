<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="../include/headlogo.jsp"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/menu.jsp"%>

<title><spring:message code="BzComposer.printlabeltitle" /></title>
<!-- <script> -->
<!-- window.onload = initShowHideDivs; -->

<!-- </script> -->
<style type="text/css">
.height250 {
height: 315px;
}
.fht-tbody{
height: 405px !important;
overflow: auto !important;
overflow-x: auto !important;
}
.fht-table-wrapper .fht-tbody { 
		}
table.tabla-listados {
width: 100%;
border: 1px solid rgb(207, 207, 207);
margin: 0px 0px 0px 0px;
margin: 0px 0px 0px 0px;
}
table.tabla-listados tbody tr td {
}
table.tabla-listados tbody tr.odd td {
background: #e1e5e9;
}
</style>
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

var curr=null;
var over=null;
var name=null;
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
    document.getElementById("empdata").innerHTML="";
	document.getElementById("empdata").innerHTML = o.responseText ;
}

function refreshItemsNow(val)
{
  curr=null;
  o = c(writeSelect);
  oGET(o,'${pageContext.request.contextPath}/include/GetEmployeeList1.jsp?type='+ val)
}
function sendTo()
{
	debugger;
	rid=document.getElementById("setRID").value
	if(rid=="")
	{

		debugger;
		return showCustomerValidationDialog();	
	}
	else{
	  	var sel=document.getElementById(rid);

		var cells= sel.getElementsByTagName("td");
		var name=cells[0].innerHTML +"   "+cells[2].innerHTML +"   "+ cells[3].innerHTML;
		var op=document.createElement('OPTION');
		var txt=document.createTextNode(name);
		op.setAttribute('value',name);
		op.appendChild(txt);
		document.getElementById("list").appendChild(op);
	}
}
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


function rowClick_OldUnused(rid){
 	size=document.getElementById("lSize").value;
 	document.getElementById("setRID").value=rid;
 	try{
	 	for(i=0;i<size;i++){
	 		var row1=document.getElementById(i);
	 		row1.className = "";
	 	}
	 	var rd=document.getElementById(rid);
	 	rd.className ="draft";
		for(i=0;i<size;i++){ 
			 document.getElementById(i).classList.remove('draft');		
			 if(i%2==0){	
				 if(size !=(i+1)){
				 document.getElementById((i+1)).classList.add('odd');
				 }
			 }
		}
		
		rowValue= rid;
		if((rowValue-1)%2==0){ 		
			document.getElementById((rowValue)).classList.remove('odd'); 		
		}
		document.getElementById(rowValue).classList.add('draft');
 	}
 	finally{
 		
 	}
	
}


function rowClick(rid) {
	size = document.getElementById("lSize").value;
 	document.getElementById("setRID").value=rid;

	// 	for(i=0;i<size;i++){
	// 		var row1=document.getElementById(i+"$$");
	// 		row1.className ="";
	// 	}
	// 	var rd = document.getElementById(rid);
	// 	rd.className ="draft";
	try{
		rowValue = rid.replace("$$", "");
		for (i = 0; i < size; i++) {
			document.getElementById(i + "$$").classList.remove('draft');
			if (i % 2 == 0) {
				if (size != (i + 1)) {
					document.getElementById((i + 1) + "$$").classList.add('odd');
				}
			}
		}
		if (rowValue % 2 != 0) {
			document.getElementById(rowValue + "$$").classList.remove('odd');
		}
		document.getElementById(rid).classList.add('draft');
	}
	catch(error){

	}
	finally{

 	}

}



function setRidOnDblClk(rid)
{
	
	rowClick(rid);
	
	sendTo();
	
}
</script>
</head>
<body>
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
 <div id="pagebreak">&nbsp;<P style="page-break-before: always">   </div>
<!-- begin Contents --> 
<!-- <span id="waitMessage"><img -->
<%-- 	src="${pageContext.request.contextPath}/images/spinner.gif"> --%>
<!-- Loading Contents...</span> -->

<!-- <div class="dhtmlgoodies_question"> -->
<div>
	<span style="font-size:1.6em;font-weight:normal;color:#838383;margin:30px 0px 15px 0px;border-bottom:1px dotted #333;padding:0 0 .3em 0;">
		<spring:message code="BzComposer.employeeprintlabels.printlabeltitle" />
	</span>
</div>
<!-- <div class="dhtmlgoodies_answer"> -->
<div>
	<div id="table-negotiations">
		<table cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td valign="top">
						<table class="tabla-listados" cellspacing="0">
							<tbody>
								<tr>
									<td align="left" style="font-size: 1em;">
									
										<input type="hidden" name="listSize" id="lSize" value='${lSize}" />'>
										
										<input type="radio" name="over" onclick="refreshItemsNow('1');" checked>
										<spring:message code="BzComposer.employeeprintlabels.hiredemployeelist" />
									</td>
									<td align="left" style="font-size: 1em;">
										<input type="radio" name="over" onclick="refreshItemsNow('0');">
										<spring:message code="BzComposer.employeeprintlabels.terminatedemployeelist" />
									</td>
								</tr>
							</tbody>
						</table>
						<div id="empdata" style="font-size: 1em;">
						</div>
					</td>
					<td>
						<table class="tabla-listados" cellspacing="0" >
							<thead>
								<tr>
									<th style="text-align:center;font-size: 1em;">
										<spring:message code="BzComposer.employeeprintlabels.selectaddresslabeltype" />
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td style="font-size:1em;">
										<select id="labelType" name="labelType">
											<option value="0">
												<spring:message code="BzComposer.employeeprintlabels.selectlabel" />
											</option>
											<c:if test="${Labels != null}">
												<c:forEach items="${Labels}" var="Labels">
													<option value="${Labels.id}">
														${Labels.labeltype}
												</c:forEach>
											</c:if>
										</select>
									</td>
								</tr>
								<tr>
									<td align="center" style="font-size:1em;">
										<input type="button" class="formbutton" value="<spring:message code='BzComposer.employeeprintlabels.setuplabel' />">
									</td>
								</tr>
								<tr>
									<td style="font-size:1em;">
										<spring:message code="BzComposer.employeeprintlabels.employeelisttoprint" />
									</td>
								</tr>
								<tr>						
									<td style="font-size:1em;">
										<select id="list" name="lbltype" size="16" style="width:200">
										</select>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		<table class="tabla-listados" cellspacing="0">
			<tbody>
				<tr>
					<td colspan="4" align="center">
						<input type="button" value="<spring:message code='BzComposer.employeeprintlabels.sendto' />"
				class="formbutton" onclick="sendTo();">
				&nbsp;&nbsp;&nbsp;
				<input type="hidden" name="SelectedRID" id="setRID" value=""> 
				<input type="button" value="<spring:message code='BzComposer.employeeprintlabels.printlabelbtn'   />"
				class="formbutton" onclick="printlabel();" >
				&nbsp;&nbsp;&nbsp;
				<input type="button" value="<spring:message code='BzComposer.employeeprintlabels.cleardata' />"
				class="formbutton" onclick="clearData();"></td>
		</tr>
	</tbody>
</table>
</div>
</div>
<!-- end Contents --></div>
</div>
</div>
</div>
<%@ include file="../include/footer.jsp"%></div>
<script>
refreshItemsNow('1');
</script>

</body>
</html>
<script type="text/javascript">

function printlabel()
{
	debugger;
	//lbltype
	 var list=document.getElementById("list");
	 var ops=list.getElementsByTagName('OPTION');
	 var pagebreak= document.getElementById("pagebreak").innerHTML;
	 var customerdetails;
	/*  for (var i = 0; i < ops.length; i++) {
		 var customerdetails = ops[i].textContent;
		
	 } */
	 
	 if(customerdetails == ""){
		alert("<bean:message key='BzComposer.common.selectCustomer'/>");
	 }else{
		 var i;
		 var a = window.open('', '', 'height=500, width=500'); 
			a.document.write('<html>'); 
			a.document.write('<body>'); 
			var lengt = ops.length;
			
			for ( i = 0; i < ops.length; i++) {
				var j =lengt-1;
				 var customerdetails = ops[i].textContent;
				 if(j != i){
					 a.document.write(customerdetails,pagebreak);  
				 }else{
					 a.document.write(customerdetails); 
				 }
				
			 }

			//a.document.write(customerdetails); 
			a.document.write('</body></html>'); 
			a.document.close(); 
			a.print(); 	 
	 }
	 
	
	
	}
	
	
rowClick("0$$");


</script>
<!-- Dialog box used in sales order page -->
<div id="showCustomerValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.employeeprintlabels.selectemployee"/></p>
</div>
<div id="showlabelValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.employeeprintlabels.selectlabeltoupdate"/></p>
</div>