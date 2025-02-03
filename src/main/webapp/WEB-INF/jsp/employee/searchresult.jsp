<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<%@include file="../include/headlogo.jsp"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/menu.jsp"%>
<title>BizComposer</title>
<!-- <script> -->
<!-- window.onload = initShowHideDivs; -->
<!-- </script> -->
<script>
function terminateEmployee(Id)
{
	document.getElementById("eid").value=Id;
	document.getElementById("act").value="terminate";
	var res=confirm("Do U want to Terminate Employee?");
	if(res==true)
	{
	document.forms[0].action = "manageemployee?act=terminate&eid="+Id;
	document.forms[0].submit();
	}
}
function deleteEmployee(Id)
{
	document.getElementById("eid").value=Id;
	document.getElementById("act").value="delete";
	var res=confirm("Do U want to delete Employee?");
	if(res==true)
	{
	document.forms[0].action = "manageemployee?act=delete&eid="+Id;
	document.forms[0].submit();
	}
}
</script>
<style type="text/css">
.height250 {
height: 250px;
}
.fht-tbody{
height: 100% !important; /*  change table height*/
/* border-bottom: 1px solid rgb(207, 207, 207); */
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
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding"><!-- begin Contents --> 
<!-- <span id="waitMessage"><img -->
<%-- 	src="${pageContext.request.contextPath}/images/spinner.gif"> --%>
<!-- Loading Contents...</span> -->
<!-- <div class="dhtmlgoodies_question" -->
<div>
	<span style="font-size: 1.6em;font-weight:normal;color:#838383;margin:30px 0px 15px 0px;border-bottom:1px dotted #333;padding:0 0 .3em 0;">
		<spring:message code="BzComposer.Employee.EmployeeList" />
	</span>
</div>
<br>
<!-- <div class="dhtmlgoodies_answer"> -->
<div>
	<div>
 		<div class="grid_8 height250 tabla-listados" id="table-negotiations" >
    		<table id="searchresultEmployee" class="tabla-listados" cellpadding="0" cellspacing="0">
				<thead style="font-weight: bold;">
    				<tr>
						<th style="font-size:1em;">
							<spring:message code="BzComposer.Employee.Id" />
						</th>
						<th style="font-size:1em;">
							<spring:message code="BzComposer.Employee.Name" />
						</th>
						<th style="font-size:1em;">
							<spring:message code="BzComposer.Employee.City"/>
						</th>
						<th style="font-size:1em;">
							<spring:message code="BzComposer.Employee.State"/>
						</th>
						<th style="font-size:1em;">
							<spring:message code="BzComposer.Employee.Zip"/>
						</th>
						<th style="font-size:1em;">
							<spring:message code="BzComposer.Employee.Email"/>
						</th>
						<th style="font-size:1em;">
							<spring:message code="BzComposer.Employee.DOA"/>
						</th>
						<th colspan="2" class="unsortable" style="font-size:1em;">
							<spring:message code="BzComposer.Employee.Options"/>
						</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empList != null}">
						<c:if test="${not empty empList}">
							${empList.ItemDetailsSize}
								<input type="hidden" name="empList" id="lSize" value='${ItemDetailsSize}'>
								<C:forEach items="empList" var="empList" varStatus="ndx">
									<tr id='${ndx}$$'
									onclick="setRowId('${empList.employeeID}','${ndx.index}$$');"
									ondblclick="showEdit('${empList.employeeID}');">
										<td style="font-size:1em;">
											${empList.employeeID}
										</td>
										<td style="font-size:1em;">
											${empList.fname}
										<td style="font-size:1em;">
											${empList.city}
										</td>
										<td style="font-size:1em;">
											${empList.state}
										</td>
										<td style="font-size:1em;">
											${empList.zip}
										</td>
										<td style="font-size:1em;">
											${empList.email}
										</td>
										<td style="font-size:1em;">
											${empList.doa}
										</td>
										<td style="font-size:1em;">
											<a href='manageemployee?act=edit&eid=${empList.employeeID}'>
												<img src="${pageContext.request.contextPath}/images/edit.png" alt="Edit" title="Edit">
											</a>
										</td>
										<c:if test="${empList.type =='1'}">
										<td style="font-size:1em;">
											<a href='javascript:terminateEmployee(${empList.employeeID}'>
												<img src="${pageContext.request.contextPath}/images/delete.png" alt="Terminate" title="Terminate">
											</a>
										</td>
										</c:if>
										<c:if test="${empList.type =='0'}">
										<td style="font-size:1em;">
											<a href='javascript:deleteEmployee(${empList.employeeID})'>
												<img src="${pageContext.request.contextPath}/images/delete.png" alt="Delete" title="Delete">
											</a>
										</td>
										</c:if>
									</tr>
								</C:forEach>
							</c:if>
						</c:if>
					</tbody>
				</table>
			</div>
			<div align="center" style="font-size:1em;">
				<a href="javascript:history.go(-1)">Back</a>
			</div>
</div>
</div>
</div>
</div>
</div>
</div>
<%@ include file="../include/footer.jsp"%></div>
<form><input type="hidden" name="eid" id="eid"> <input type="hidden"
	name="act" id="act"> <input type="hidden" name="type" id="type"></form>
</body>
</html>
<script type="text/javascript">
function setRowId(rowId,rid){
	
	size=document.getElementById("lSize").value;
rowValue= rid.replace('$$','');
rowValue++;
	for(i=0;i<size;i++){
		 document.getElementById(i+"$$").classList.remove('draft');		
		 if(i%2==0){		
			 if(size !=(i+1)){
			 document.getElementById((i+1)+"$$").classList.add('odd');
			 }
		 }
	}
	if(rowValue%2==0){ 	
		document.getElementById((rowValue-1)+"$$").classList.remove('odd'); 		
	}
	var rd = document.getElementById(rid).classList.add('draft');
	
	document.getElementById("rowONum").value=rowId;
}

</script>