<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page errorPage="/WEB-INF/jsp/include/sessionExpired.jsp"%>
<html>
<head>
<script>
	self.moveTo(190,150);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="BzComposer.Purchase.PurchaseOrder.ConfirmAddressTitle" /></title>
<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu_add.js"></script>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<style>
table.tabla-listados tr td {
    font-size: 14px !important;padding: 5px 0px 5px 14px;
}
</style>
</head>
<body onload="loadPageData();">
<form:form method="post" action="${URL}" id="AddressForm" name="AddressForm" modelAttribute="invoiceDto">
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
	<div>
	    <span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
            <c:choose>
            <c:when test="${addressType=='bill'}">
                <spring:message code="BzComposer.invoice.updateBillingAddress" />
            </c:when>
            <c:when test="${addressType=='ship'}">
                <spring:message code="BzComposer.invoice.updateShippingAddress" />
            </c:when>
            <c:otherwise>
                <spring:message code="BzComposer.sales.UpdateAddress" />
            </c:otherwise>
            </c:choose>
            <c:if test="${not empty actionMsg}">
                <div align="center"><spring:message code="${actionMsg}"/></div>
                <% session.removeAttribute("actionMsg"); %>
            </c:if>
	    </span>
	</div>
	<div align="center">
	<table>
		<tr>
			<td>
			<fieldset>
			<div align="center">
			<table class="tabla-listados">
				<tr>
					<td style="width:30%;"><spring:message code="BzComposer.Purchase.PurchaseOrder.ConfirmAddress.CompanyName" /></td>
					<td style="width:70%;"><form:input type="text" path="companyName" style="width:90%;" /></td>
				</tr>
				<tr>
					<td><spring:message code="BzComposer.Purchase.PurchaseOrder.ConfirmAddress.FirstName" /></td>
					<td><form:input type="text" path="firstName" /></td>
				</tr>
				<tr>
					<td><spring:message code="BzComposer.Purchase.PurchaseOrder.ConfirmAddress.LastName" /></td>
					<td><form:input type="text" path="lastName" /></td>
				</tr>
				<tr>
					<td><spring:message code="BzComposer.Purchase.PurchaseOrder.ConfirmAddress.Address1" /></td>
					<td><form:input type="text" path="address1" style="width:90%;" /></td>
				</tr>
				<tr>
					<td><spring:message code="BzComposer.Purchase.PurchaseOrder.ConfirmAddress.Address2" /></td>
					<td><form:input type="text" path="address2" style="width:90%;" /></td>
				</tr>
				<tr>
                    <td>
                        <div class="lblZipcodeShow"><spring:message code="BzComposer.global.zipcode" /></div>
                        <div class="lblPostalcodeShow"><spring:message code="BzComposer.global.postalcodes" /></div>
                    </td>
                    <td><form:input type="text" path="zipcode" onfocusout="loadAddressDetailsByZipcode(this.value, 1)" onkeypress="return numbersonly(event, this.value)" /></td>
                </tr>
				<tr>
					<td><spring:message code="BzComposer.Purchase.PurchaseOrder.ConfirmAddress.City" /></td>
					<td>
					    <form:select path="city" id="cityID" style="width:200px;">
                            <form:option value="0"><spring:message code="BzComposer.register.selectcity" /></form:option>
                            <c:forEach items="${cityList}" var="currObject">
                                <form:option value="${currObject.cityId}">${currObject.cityName}</form:option>
                            </c:forEach>
                        </form:select>
					</td>
				</tr>

				<tr>
					<td>
					    <div class="lblStateShow"><spring:message code="BzComposer.global.state" /></div>
                        <div class="lblProvinceShow"><spring:message code="BzComposer.global.province" /></div>
					</td>
					<td>
					    <form:select path="state" id="stateID" onchange="loadCitiesByStateID(this.value, 1);" style="width:200px;">
                            <form:option value="0"><spring:message code="BzComposer.register.selectstate" /></form:option>
                            <c:forEach items="${stateList}" var="currObject">
                                <form:option value="${currObject.stateId}">${currObject.state}</form:option>
                            </c:forEach>
                        </form:select>
					</td>
				</tr>
				<tr>
					<td><spring:message code="BzComposer.Purchase.PurchaseOrder.ConfirmAddress.Country" /></td>
					<td>
					    <form:select path="country" id="countryID" onchange="loadStatesByCountryID(this.value, 1);" style="width:200px">
						    <form:option value="0"><spring:message  code="BzComposer.register.selectcounry"/></form:option>
						    <c:forEach items="${countryList}" var="currObject">
                        		<form:option value="${currObject.countryId}">${currObject.countryName}</form:option>
                            </c:forEach>
					    </form:select>
                    </td>
				</tr>
			</table>
			</div>
			</fieldset>
			</td>

		</tr>
		<tr>
			<td style="font-size: 14px !important;padding: 5px 0px 5px 10px;">
			    <spring:message code="BzComposer.Purchase.PurchaseOrder.ConfirmAddress.SureMessage" />
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr align="center">
			<td>
			    <button type="submit" class="formbutton"><spring:message code="BzComposer.Purchase.PurchaseOrder.ConfirmAddress.OkButton" /></button>
				<button type="button" class="formbutton" onclick="cancel();"><spring:message code="BzComposer.Purchase.PurchaseOrder.ConfirmAddress.CloseButton" /></button>
			</td>
		</tr>
	</table>
	</div>

	</div>
	</div>
	</div>
	</div>
	</div>
	<div>
	    <form:hidden path="clientVendorID" />
	    <form:hidden path="addressID" />
	    <input type="hidden" id="stateID" name="stateID" value="${invoiceDto.state}" />
	</div>
	<!-- end Contents -->
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>

</body>
<script type="text/javascript">
selectValidCountryMsg = "<spring:message code='BzComposer.register.selectvalidcountry'/>";
selectValidStateMsg = "<spring:message code='BzComposer.register.selectvalidstate'/>";
selectValidZipcodeMsg = "<spring:message code='BzComposer.register.selectvalidzipcode'/>";
noRecordsFoundMsg = "<spring:message code='BzComposer.employee.norecordsfound'/>";

function loadPageData(){
    if($("#countryID").find("option:selected").text().trim() == 'Canada'){
        $('.lblProvinceShow').show();
        $('.lblPostalcodeShow').show();
        $('.lblStateShow').hide();
        $('.lblZipcodeShow').hide();
    }else{
        $('.lblProvinceShow').hide();
        $('.lblPostalcodeShow').hide();
        $('.lblStateShow').show();
        $('.lblZipcodeShow').show();
    }
}
function cancel(){
	window.close();
}
</script>
</html>

