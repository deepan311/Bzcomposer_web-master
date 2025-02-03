<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="expires" content="-1">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.terminatedemployeelisttitle"/></title>
<!-- <script> -->
<!-- window.onload = initShowHideDivs; -->
<!-- </script> -->
<script>
function deleteEmployee1(Id)
{	
	document.getElementById("eid").value=Id;
	/* var res=confirm("Do you want to Delete Employee?");
	if(res==true)
	{
	document.forms[0].action = "manageemployee?act=delete&eid="+Id;
	document.forms[0].submit();
	} */
	debugger;
	//event.preventDefault();
	$("#deleteEmployeeDialog").dialog({
		resizable: false,
	    height: 200,
	    width: 400,
	    modal: true,
	    buttons: {
			"Ok": function () {
	        	$(this).dialog("close");
	        	/* document.forms[0].action = "manageemployee?act=delete&eid="+Id;
	        	document.forms[0].submit(); */
	        	window.location =  "manageemployee?act=delete&eid="+Id;
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
<style type="text/css">
.height250 {
height: 400px;

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
<div id="padding">
<!-- begin Contents --> 
<div>
	<span style="font-size: 1.6em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.employee.terminatedemployeelist" />
	</span>
</div>
<br>
<div>
	<div>
		<div class="grid_8 height250 tabla-listados" id="table-negotiations" >
    		<table id="terminatedEmployeeList" class="tabla-listados" cellpadding="0" cellspacing="0">
				<thead style="font-weight: bold;">
					<tr>
						<th style="font-size: 1em;">
							<spring:message code="BzComposer.employee.id" />
						</th>
						<th style="font-size: 1em;">
							<spring:message code="BzComposer.global.firstname" />
						</th>
						<th style="font-size: 1em;">
							<spring:message code="BzComposer.global.lastname" />
						</th>
						<th style="font-size: 1em;">
							<spring:message code="BzComposer.global.city" />
						</th>
						<th style="font-size: 1em;">
							<spring:message code="BzComposer.global.state" />
						</th>
						<th style="font-size: 1em;">
							<spring:message code="BzComposer.global.zipcode" />
						</th>
						<th style="font-size: 1em;">
							<spring:message code="BzComposer.global.phone" />
						</th>
						<th style="font-size: 1em;">
							<spring:message code="BzComposer.employee.mobile" />
						</th>
						<th style="font-size: 1em;">
							<spring:message code="BzComposer.global.email" />
						</th>
						<th style="font-size: 1em;">
							<spring:message code="BzComposer.employee.doa" />
						</th>
						<th class="unsortable" colspan="2" style="font-size: 1em;">
							<spring:message code="BzComposer.employee.options"/>
						</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empList != null}">
						<input type="hidden" name="empListSizeSize" id="empListSizeSize" value="${empList.size()}" />
							<input type="hidden" name="listSize" id="lSize" value="${empList.size()}">
								<c:forEach items="${empList}" var="empList" varStatus="ndx">
									<tr id='${ndx.index}$$'
									ondblclick="showCustomer('${empList.employeeID}')"
									onclick="setRowId(${empList.employeeID},'${ndx.index}$$');">
										<td style="font-size: 1em;">
											${empList.employeeID}
										</td>
										<td style="font-size: 1em;">
											${empList.fname}
										</td>
										<td style="font-size: 1em;">
											${empList.lname}
										</td>
										<td style="font-size: 1em;">
											${empList.state}
										</td>
										<td style="font-size: 1em;">
											${empList.state}
										</td>
										<td style="font-size: 1em;">
											${empList.zip}
										</td>
										<td style="font-size: 1em;">
											${empList.phone}
										</td>
										<td style="font-size: 1em;">
											${empList.mobile}
										</td>
										<td style="font-size: 1em;">
											${empList.email}
										</td>
										<td style="font-size: 1em;">
											${empList.doa}
										</td>
										<td>
											<a href='manageemployee?act=edit&eid=${empList.employeeID}'>
												<img src="${pageContext.request.contextPath}/images/edit.png" alt="Edit" title="Edit">
											</a>
										</td>
										<td>
											<a href='javascript:deleteEmployee1(${empList.employeeID})'>
												<img src="${pageContext.request.contextPath}/images/delete.png" alt="Delete" title="Delete">
											</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>	
					</table>
					<c:if test="${empList == null}">
						<div style="minHeight:800;"><spring:message code="BzComposer.employee.norecordsfound"/></div>
					</c:if>
				</div>
			</div>
		</div>
<!-- end Contents -->
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%></div>
<form><input type="hidden" name="eid" id="eid"/> <input type="hidden"
	name="act" id="act" value="delete"/> <input type="hidden" name="type"
	id="type" value="hired"/></form>
</body>
</html>
<script>
function setRowId(rowId,rid)
{
	
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

setRowId(null, "0$$");
</script>
<!-- Dialog box used in sales order page -->
<div id="deleteEmployeeDialog" style="display:none;">
	<p><spring:message code="BzComposer.employee.deleteselectedemployee"/>Do you want to Delete Employee?</p>
</div>