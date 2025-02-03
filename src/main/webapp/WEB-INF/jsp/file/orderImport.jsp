<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/styles/form.css" media="screen" rel="Stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/admin.css" media="screen" rel="Stylesheet" type="text/css">	
<script src="${pageContext.request.contextPath}/tableStyle/js/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/styles/calendar.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/scripts/calendar.js" type="text/javascript"></script>
<style type="text/css">
.customfieldset{
    margin-left: 20px;
}
.customlegend{
    
}
</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title><spring:message code="BzComposer.importordertitle"/></title>
</head>
<body>
<form:form action="File?tabid=OrderImport" method="post" enctype="MULTIPART/FORM-DATA" id="uploadForm">
	<div>
		<h4><spring:message code="BzComposer.file.option.orderImport"/></h4>
		<fieldset>
			<legend><spring:message code="BzComposer.file.option.load"/></legend>
			<label>
				<spring:message code="BzComposer.file.option.load"/>
				<select>
					<option><spring:message code="BzComposer.orderimport.selecttemplate"/></option>
					<c:if test="${not empty listOfOrderImportFile}">
						<c:forEach items="${listOfOrderImportFile}" var="objList">
							<option value="${objList.value}">${objList.label}</option>
						</c:forEach>
					</c:if>
				</select>
			</label>
		</fieldset>
		
		<fieldset>
			<legend><spring:message code="BzComposer.file.option.ImportFrom"/></legend>
			<div><label>
				<spring:message code="BzComposer.file.option.file"/>
				<input type="text">
				<button type="button" class="btn" onclick="document.getElementById('getFile').click()">
					<!-- Select File -->
					<spring:message code="BzComposer.global.selectfile"/>
				</button>
				<input type='file' id="getFile" style="display:none">
				<!-- <input type="file" value="Select File"/> -->
			</label></div>
			
			<div><label>
				<spring:message code="BzComposer.file.option.excel"/>
				<select>
					<option></option>
				</select>
			</label></div>
			
		</fieldset>
		
		<fieldset>
			<legend><spring:message code="BzComposer.file.option.template"/></legend>
			<label><input type="radio" name="temptype" checked="checked"><spring:message code="BzComposer.file.option.invoice"/></label>
			<label><input type="radio" name="temptype"><spring:message code="BzComposer.file.option.purchase"/></label>
		</fieldset>

		<fieldset>
			<legend><spring:message code="BzComposer.file.option.fields"/></legend>
			<div id="tabs1" class="mb-3 clear custom-fixed-tabs">
				<ul class="tabs">
					<li><a href="#tabs-1"><spring:message code="BzComposer.file.option.order"/></a></li>
					<li><a href="#tabs-2"><spring:message code="BzComposer.file.option.name"/></a></li>
					<li><a href="#tabs-3"><spring:message code="BzComposer.file.option.items"/></a></li>
				</ul>
				<div class="tab-content"> 
					<div id="tabs-1" class="pl-2 pr-2 pt-3 pb-1">
						<div class="table1">
							<table class="table" border="1">
								<thead>
									<tr>
										<th><spring:message code="BzComposer.file.option.database"/></th>
										<th><spring:message code="BzComposer.file.option.mapping"/></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.orderid"/>
											<spring:message code="BzComposer.CompulsoryField.Validation" /></td>
									</tr>
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.orderdate"/>
											<spring:message code="BzComposer.CompulsoryField.Validation" />
										</td>
									</tr>
									<tr>
										<td><spring:message code="BzComposer.orderimport.paymethod"/></td>
									</tr>
									<tr>
										<td><spring:message code="BzComposer.orderimport.shippingmethod"/></td>
									</tr>
									<tr>
										<td><spring:message code="BzComposer.orderimport.producttotal"/></td>
									</tr>
									<tr>
										<td><spring:message code="BzComposer.orderimport.taxtotal"/></td>
									</tr>
									<tr>
										<td><spring:message code="BzComposer.orderimport.taxable"/></td>
									</tr>	
								</tbody>
							</table>
						</div>
					</div>
					<div id="tabs-2" class="pl-2 pr-2 pt-3 pb-1">
						<div class="table1 table2" id="devCategoryTable">
							<table class="table table-bordered table-sm devAcCategoryListTable" border="1">
								<thead class="thead-light">
									<tr>
										<th><spring:message code="BzComposer.file.option.database"/></th>
										<th><spring:message code="BzComposer.file.option.mapping"/></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.firstname"/>
										</td>
									</tr>	
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.lastname"/>
										</td>
									</tr>	
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.companyname"/>
										</td>
									</tr>	
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.email"/>
											<spring:message code="BzComposer.CompulsoryField.Validation" />
										</td>
									</tr>	
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.city"/>
										</td>
									</tr>	
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.state"/>
										</td>
									</tr>	
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.zipcode"/>
										</td>
									</tr>	
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.country"/>
										</td>
									</tr>	
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.phone"/>
										</td>
									</tr>	
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.billingaddress1"/>
										</td>
									</tr>	
								</tbody>
							</table>
						</div>
					</div>
					<div id="tabs-3" class="pl-2 pr-2 pt-3 pb-1">
						<div class="table1 table2" id="devCategoryTable">
							<table class="table table-bordered table-sm devAcCategoryListTable" border="1">
								<thead class="thead-light">
									<tr>
										<th><spring:message code="BzComposer.file.option.database"/></th>
										<th><spring:message code="BzComposer.file.option.mapping"/></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.sku"/>
											<spring:message code="BzComposer.CompulsoryField.Validation" />
										</td>
									</tr>
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.itemname"/>
										</td>
									</tr>
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.quantity"/>
											<spring:message code="BzComposer.CompulsoryField.Validation" />
										</td>
									</tr>
									<tr>
										<td>
											<spring:message code="BzComposer.orderimport.unitprice"/>
											<spring:message code="BzComposer.CompulsoryField.Validation" />
										</td>
									</tr>
									<tr>
										<td><spring:message code="BzComposer.orderimport.unitweight"/></td>
									</tr>
									<tr>
										<td><spring:message code="BzComposer.orderimport.taxable"/></td>
									</tr>	
								</tbody>	
								</tbody>	
								</tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
	</div>
</form:form>
<script type="text/javascript">
var progress;
function downloadCustomerList(type){
	debugger;
	document.forms[0].action = "Item?tabid=ExportItem&type="+type;
	document.forms[0].submit();
}
$(document).ready(function(){	
	
});

$(function() {
    debugger;
	  $( "#tabs1" ).tabs();
  } );
</script>
</body>
</html>