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
<title><spring:message code="BzComposer.rmalisttitle" /></title>
<style type="text/css">
.dhtmlgoodies_answer{
height: 600px !important;
}
.height250 {
height:47%;

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
<body>
<!-- begin shared/header -->
<form:form action="RMA?tabid=R0S0C0" method="post" modelAttribute="rmaDto">
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<!-- begin Contents --> 
<!-- 	<div class="dhtmlgoodies_question"> -->
	<div><span
		style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.rmalist.rmalist" /></span>
		</div>
		
<!-- 	<div class="dhtmlgoodies_answer"> -->
<div>
	<div>
	 <div class="grid_8 height250 tabla-listados" id="table-negotiations" >
   
      <table  id="rmaData" class="tabla-listados" cellpadding="0" cellspacing="0">
	
		<thead style="font-weight: bold;">
			<tr>
				<th class="emblem" align="left" nowrap="nowrap" style="font-size: 14px;">
					<spring:message code="BzComposer.rmalist.rmanumber" />
				</th>
				<th class="emblem" align="left" nowrap="nowrap" style="font-size: 14px;">
					<spring:message code="BzComposer.rmalist.lastname" />
				</th>
				<th class="emblem" align="left" nowrap="nowrap" style="font-size: 14px;">
					<spring:message code="BzComposer.rmalist.firstname" />
				</th>
				<th class="emblem" align="left" nowrap="nowrap" style="font-size: 14px;">
					<spring:message code="BzComposer.rmalist.itemcode" />
				</th>
				<th class="emblem" align="left" nowrap="nowrap" style="font-size: 14px;">
					<spring:message code="BzComposer.rmalist.itemdescription" />
				</th>
				<th class="emblem" align="left" nowrap="nowrap" style="font-size: 14px;">
					<spring:message code="BzComposer.rmalist.reason" />
				</th>
				<th class="emblem" align="left" style="font-size: 14px;">
					<spring:message code="BzComposer.rmalist.rmaquantity" />
				</th>
				<th class="emblem" align="left" style="font-size: 14px;">
					<spring:message code="BzComposer.rmalist.unitprice" />
				</th>
				<th class="emblem" align="left" style="font-size: 14px;">
					<spring:message code="BzComposer.rmalist.unitweight" />
				</th>
				<th class="emblem" align="left" style="font-size: 14px;">
					<spring:message code="BzComposer.rmalist.rmarequest" />
				</th>
			</tr>
		</thead>
		<tbody style="font-size: 14px;">
			<c:if test="${not empty RMAList}">
                <input type="hidden" name="RMALID" id="lSize" value='${RMAList.size()}'>
                <c:forEach items="${RMAList}" var="RobjList" varStatus="loop">
                    <tr id="${loop.index}$$" onclick="setRMA('${RobjList.order}','${RobjList.fname}','${RobjList.lname}','${loop.index}$$');">
                        <td nowrap="nowrap" style="font-size: 14px;">${RobjList.rma}</td>
                        <td nowrap="nowrap" style="font-size: 14px;" >${RobjList.lname}</td>
                        <td nowrap="nowrap" style="font-size: 14px;">${RobjList.fname}</td>
                        <td nowrap="nowrap" style="font-size: 14px;">${RobjList.itemCode}</td>
                        <td nowrap="nowrap" width="400" style="font-size: 14px;">${RobjList.itemDesc}</td>
                        <td nowrap="nowrap" style="font-size: 14px;">${RobjList.reason}</td>
                        <td nowrap="nowrap" align="right" style="font-size: 14px;">${RobjList.qty}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td nowrap="nowrap" align="right" style="font-size: 14px;">${RobjList.unitPrice}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td nowrap="nowrap" align="right" style="font-size: 14px;">${RobjList.unitWeight}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td nowrap="nowrap" style="font-size: 14px;">${RobjList.sentDate}</td>
                    </tr>
                </c:forEach>
			</c:if>

			<tr>
				<td><input type="hidden" name="RMAval" id="RMAnum"> <input
					type="hidden" name="Fnameval" id="RMAfname"> <input
					type="hidden" name="Lnameval" id="RMAlname"></td>
			</tr>
		</tbody>
	</table>
	</div>
	<div align="center">
	<table>
		<tr>
			<td>
			    <c:if test="${not empty PageValue}">
				<c:if test="${PageValue == session.getAttribute('StartPage')}">
									&nbsp;&nbsp;&nbsp;
									<a href="#" onclick="getPreviousRecord();"> <strong>
					<spring:message code="BzComposer.global.previous" /> </strong> </a>
				</c:if>
			</c:if>
			<c:forEach items="${Total}" var="tot">
				<c:if test="${tot == PageValue}">
					&nbsp;&nbsp;&nbsp;<font color="red">
					${tot}</font>
				</c:if>
				<c:if test="${tot != PageValue}">
					&nbsp;&nbsp;&nbsp;<a href="#" onclick="getPrintLabelRecord('${tot}');">
					${tot}</a>
				</c:if>
			</c:forEach>
			<c:if test="${not empty PageValue}">
			    <c:if test="${PageValue != TotalPages}">
					&nbsp;&nbsp;&nbsp;
					<a href="#" onclick="getNextRecord();"> <strong> <spring:message code="BzComposer.global.next" /> </strong> </a>
				</c:if>
			</c:if></td>
		</tr>
	</table>
	
	</div>
	<div align="center">
	<table>
		<tr>
			<td><input disabled="disabled" type="button" id="modifyRMA"
				class="formButton" onclick="getRMA();" name="RMABtn"
				title='<spring:message code="BzComposer.rmalist.modifyrmatooltip"/>'
				value='<spring:message code="BzComposer.rmalist.modifyrmabutton"/>'></td>
		</tr>
	</table>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	<div><input type="hidden" name="tabid" id="tab"
		value="R0L0S0List" /> <form:hidden path="startPage" /></div>
	<!-- end Contents -->
</form:form>

<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>
<script>
	function getPrintLabelRecord(no){
		document.getElementById('tab').value="R0L0S0List";
		document.RMAForm.startPage.value=no;
		document.forms[0].action = "RMA";
		document.forms[0].submit();
	}
	function getPreviousRecord(){
		document.getElementById('tab').value="R0L0S0List";
		document.RMAForm.startPage.value=(parseInt(document.RMAForm.startPage.value) - 1);
		document.forms[0].action = "RMA";
		document.forms[0].submit();
	}
	function getNextRecord(){
		document.getElementById('tab').value="R0L0S0List";
		document.RMAForm.startPage.value=(parseInt(document.RMAForm.startPage.value) + 1);
		document.forms[0].action = "RMA";
		document.forms[0].submit();
	}
function setRMA(rno,fnm,lnm,rid)
{
size=document.getElementById("lSize").value;
// for(i=0;i<size;i++){
// var row1=document.getElementById(i+"$$");
// row1.className="";
// }
// var rd=document.getElementById(rid);
// rd.className="draft";
for(i=0;i<size;i++){ 
	 document.getElementById(i+"$$").classList.remove('draft');		
	 if(i%2==0){	
		 if(size !=(i+1)){
		 document.getElementById((i+1)+"$$").classList.add('odd');
		 }
	 }
}
 rowValue= rid.replace("$$","");

if((rowValue-1)%2==0){ 		
	document.getElementById((rowValue)+"$$").classList.remove('odd'); 		
}
var rd = document.getElementById(rowValue+"$$").classList.add('draft');

document.getElementById("RMAnum").value=rno;
document.getElementById("RMAfname").value=fnm;
document.getElementById("RMAlname").value=lnm;
document.getElementById("modifyRMA").disabled=false;
}

function getRMA()
{
	rnum=document.getElementById("RMAnum").value;
	rFnm=document.getElementById("RMAfname").value;
	rLnm=document.getElementById("RMAlname").value;
	rmawindow=window.open("RMA?tabid=RmaInfo&OrderID="+rnum+"&Fname="+rFnm+"&Lname="+rLnm,null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
	rmawindow.moveTo(50,20);

}
</script>
