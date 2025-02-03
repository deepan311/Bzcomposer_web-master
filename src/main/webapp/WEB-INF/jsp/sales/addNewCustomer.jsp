<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<title><spring:message code="BzComposer.addnewcustomertitle" /></title>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<style>
    table.tabla-listados thead tr th{ font-size:14px; }
    table.tabla-listados tbody tr td{ font-size:12px; }
    #tabs ul li { font-size:12px !important; }
    input,textarea,select{ font-size:12px !important; }
</style>
<script type="text/javascript">
selectValidCountryMsg = "<spring:message code='BzComposer.register.selectvalidcountry'/>";
selectValidStateMsg = "<spring:message code='BzComposer.register.selectvalidstate'/>";
selectValidZipcodeMsg = "<spring:message code='BzComposer.register.selectvalidzipcode'/>";
noRecordsFoundMsg = "<spring:message code='BzComposer.employee.norecordsfound'/>";

$(function() {
    self.moveTo(100, 10);
    $( "#tabs" ).tabs();
});
</script>
</head>
<body onload="NewCustomer();">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<%-- <form:form action="Customer/AddCustomer" method="post" id="frmNewCustomer"> --%>
<form:form name="CustomerForm" method="post" id="frmNewCustomer" modelAttribute="customerDto">
<input type="hidden" name="tabid" id="tabid" value="" />
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
	<!-- begin Contents --> 
	<!-- add the code for tab here -->
	<div>
		<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.addnewcustomer.addcustomertitle"/>
		</span>
	</div>
	<table cellpadding="0" cellspacing="0" border="0" align=center  style="width: 100%;">
	   <c:if test="${not empty Status}">
			<tr>
				<td colspan="3">
					<span class="msgstyle">${Status}</span>
				</td>
			</tr>
		</c:if>
		<c:if test="${not empty actionMsg}">
			<tr>
				<td colspan="3">
					<span class="msgstyle">${actionMsg}</span>
					<% session.removeAttribute("actionMsg"); %>
				</td>
			</tr>
		</c:if>
	</table>
	<div id="tabs" style="min-height:550px;">
  		<ul>
    		<li><a href="#General-1"><spring:message code="BzComposer.addnewcustomer.tabs.general" /></a></li>
		    <li><a href="#payment-2"><spring:message code="BzComposer.addnewcustomer.tabs.paymentInformation" /></a></li>
		    <!-- <li><a href="#service-3"><spring:message code="BzComposer.addnewcustomer.tabs.service" /></a></li> -->
		    <li><a href="#finance-4"><spring:message code="BzComposer.addnewcustomer.tabs.financecharges" /></a></li>
		</ul>
		<div id="General-1">
   			<div id="content1" class="tabPage">
   				<!-- add here the content of first tab -->
				<div id="table-negotiations">
					<table class="tabla-listados" cellspacing="0">
						<thead>
							<tr>
								<th colspan="7">
									<spring:message code="BzComposer.addnewcustomer.customerinfo" />
								</th>
							</tr>
						</thead>		
						<tbody>
							<tr>
							    <td><spring:message code="BzComposer.global.customerid" /></td>
                                <td><form:input path="clientVendorID" readonly="true" style="width:100px;" /></td>
								<td colspan="5">&nbsp;</td>
							</tr>
							<tr>
							    <td>
                                    <spring:message code="BzComposer.global.titlename" />
                                </td>
							    <td colspan="5">
							        <table style="width:100%;">
							        <tr>
							            <td>
                                            <form:select path="title" style="width:100px;">
                                                <form:options items="${titleList}" itemValue="value" itemLabel="label" />
                                            </form:select>
                                        </td>
                                        <td>
                                            <spring:message code="BzComposer.global.firstname" />
                                            <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                        </td>
                                        <td>
                                            <form:input path="firstName" size="20" />
                                        </td>
                                        <td>
                                            <spring:message code="BzComposer.global.middlename" />
                                        </td>
                                        <td>
                                            <form:input path="middleName" size="15" />
                                        </td>
                                        <td>
                                            <spring:message code="BzComposer.global.lastname" />
                                            <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                        </td>
                                        <td>
                                            <form:input path="lastName" size="20" />
                                        </td>
                                    </tr>
                                    </table>
                                </td>
                                <td>&nbsp;</td>
                            </tr>
							<tr>
								<td>
									<spring:message code="BzComposer.global.company" />
									<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
								</td>
								<td colspan="3">
									<form:input path="cname" style="width:90%;" />
								</td>
								<td><spring:message code="BzComposer.register.dbaName" /></td>
                                <td><form:input path="dbaName" /></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
								 	<spring:message code="BzComposer.global.address1" />
								 	<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
						 		</td>
								<td colspan="3">
									<form:input path="address1" style="width:90%;" />
								</td>
								<td colspan="3">&nbsp;</td>
							<tr>
							</tr>
								<td>
									<spring:message code="BzComposer.global.address2" />
								</td>
								<td colspan="3">
									<form:input path="address2" style="width:90%;" />
								</td>
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr>
							    <td style="color: #black !important;">
							        <div class="lblZipcodeShow float-left"><spring:message code="BzComposer.global.zipcode" /></div>
                                    <div class="lblPostalcodeShow float-left"><spring:message code="BzComposer.global.postalcodes" /></div>&nbsp;
                                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                </td>
                                <td>
                                    <form:input path="zipCode" onfocusout="loadAddressDetailsByZipcode(this.value, 1)" onkeypress="return numbersonly(event,this.value)" />
                                </td>
                                <td>
                                    <spring:message code="BzComposer.global.city"/>
                                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                </td>
                                <td>
                                    <form:select path="city" id="cityID" style="width:200px;">
                                        <form:option value="0"><spring:message code="BzComposer.register.selectcity" /></form:option>
                                        <c:forEach items="${cityList}" var="currObject">
                                            <form:option value="${currObject.cityId}">${currObject.cityName}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </td>
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
                                    <form:hidden path="province" />
								</td>
								<td>&nbsp;</td>
							</tr>
                            <tr>
							    <td id="t_country">
                                    <spring:message code="BzComposer.global.country" />
                                </td>
                                <td>
                                    <form:select path="country" id="countryID" onchange="loadStatesByCountryID(this.value, 1);" style="width:200px">
                                        <form:option value="0"><spring:message  code="BzComposer.register.selectcounry"/></form:option>
                                        <c:forEach items="${countryList}" var="currObject">
                                            <form:option data-code="${currObject.phoneCode}" value="${currObject.countryId}">${currObject.countryName}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </td>
							    <td colspan="5">&nbsp;</td>
                            </tr>
                            <tr>
                                <td>
                                    <spring:message code="BzComposer.global.phone" />
                                </td>
                                <td>
                                    <form:input path="phone" maxlength="16" onkeypress="return numbersonly(event,this.value)" onchange="validateUSAPhoneNumber(this, true);" />
                                </td>
                                <td>
                                    <spring:message code="BzComposer.global.mobile" />
                                </td>
                                <td colspan="2">
                                    <form:input path="cellPhone" maxlength="16" onkeypress="return numbersonly(event,this.value)" onchange="validateUSAPhoneNumber(this, true);" />&nbsp;&nbsp;&nbsp;
                                    <form:checkbox path="isMobilePhoneNumber" onchange="copyPhoneNumber(this.form);" />
                                    (<spring:message code="BzComposer.global.isPhoneNumber" />)
                                </td>
								<td>
                                    <spring:message code="BzComposer.global.fax" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <form:input path="fax" onkeypress="return numbersonly(event,this.value)" onchange="validateUSAPhoneNumber(this, true);" />
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
								<td>
									<spring:message code="BzComposer.global.email" />
								</td>
								<td colspan="3">
									<form:input type="email" path="email" size="40" />
								</td>
								<td>
                                    <spring:message code="BzComposer.global.homepage" />
                                </td>
                                <td>
                                    <form:input path="homePage" size="40" />
                                </td>
                                <td>&nbsp;</td>
                            <tr>
                            <tr>
                                <td>
                                    <spring:message code="BzComposer.global.dateadded" />
                                </td>
                                <td>
                                    <form:input path="dateAdded" readonly="true" />
                                    <img src="${pageContext.request.contextPath}/images/cal.gif"
                                        onclick="displayCalendar(document.CustomerForm.dateAdded,'mm-dd-yyyy',this);">
                                </td>
                                <td>&nbsp;</td>
                                <td>
                                    <form:checkbox path="terminated" />
                                    <spring:message code="BzComposer.global.terminated" />
                                </td>
                                <td>
                                    <spring:message code="BzComposer.global.terminatedDate" />
                                </td>
                                <td>
                                    <form:input path="terminatedDate" readonly="true" />
                                    <img src="${pageContext.request.contextPath}/images/cal.gif"
                                        onclick="displayCalendar(document.CustomerForm.terminatedDate,'mm-dd-yyyy',this);">
                                </td>
                                <td>&nbsp;</td>
                            <tr>
                            </tr>
								<td>
									<spring:message code="BzComposer.Companyinformation.Type" />
								</td>
								<td>
									<form:select path="type">
										<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
										<form:options items="${VendorCategoryList}" itemValue="value" itemLabel="label"/>
									</form:select>
								</td>
								<td>
									<spring:message code="BzComposer.global.taxid" />
								</td>
								<td>
									<form:input path="texID" />
								</td>
								<td>
									<input type="checkbox" name="isTaxable" id="chktax" ${customerDto.taxAble=='on'?'checked':''} />
									<spring:message code="BzComposer.global.istaxable" />
								</td>
								<td>
									<input type="checkbox" name="isAlsoClient" id="chk_alsovendor">
									<spring:message code="BzComposer.addnewcustomer.isalsovendor" />
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
							    <td>
                                    <spring:message code="BzComposer.global.oppeningunpaidbalance" />
                                </td>
                                <td>
                                    <form:input path="openingUB" onkeypress="return numbersonly(event,this.value)" />
                                </td>
								<td>
									<spring:message code="BzComposer.global.existingcredits" />
								</td>
								<td>
									<form:input path="extCredit" onkeypress="return numbersonly(event,this.value)" />
								</td>
								<td colspan="3">&nbsp;</td>
                            </tr>
                            <tr>
								<td>
									<spring:message code="BzComposer.global.memo" />
								</td>
								<td colspan="5">
									<form:textarea path="memo" style="width:90%;" />
								</td>
								<td>&nbsp;</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="table-negotiations" style="width:100%">
                    <table class="tabla-listados" cellspacing="0">
                        <thead>
                            <tr>
                                <th colspan="6">
                                    <spring:message code="BzComposer.addnewcustomer.prefrenceinfo" />
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <spring:message code="BzComposer.global.term" />
                                </td>
                                <td>
                                    <form:select path="term" style="width:150px;">
                                        <option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
                                        <c:forEach items="${TermList}" var="curObject">
                                            <option value="${curObject.term}" ${curObject.term==defaultCongurationData.selectedTermId?'selected':''}>${curObject.name}</option>
                                        </c:forEach>
                                    </form:select>
                                </td>
                                <td>
                                    <spring:message code="BzComposer.global.paymentmethod" />
                                </td>
                                <td>
                                    <form:select path="paymentType" style="width:150px;">
                                        <option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
                                        <c:forEach items="${PaymentList}" var="curObject">
                                            <option value="${curObject.id}" ${curObject.id==defaultCongurationData.selectedPaymentId?'selected':''}>${curObject.typeName}</option>
                                        </c:forEach>
                                    </form:select>
                                </td>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td>
                                    <spring:message code="BzComposer.global.representative" />
                                </td>
                                <td>
                                    <form:select path="rep" style="width:150px;">
                                        <option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
                                        <c:forEach items="${RepList}" var="curObject">
                                            <option value="${curObject.value}" ${curObject.value==defaultCongurationData.selectedSalesRepId?'selected':''}>${curObject.label}</option>
                                        </c:forEach>
                                    </form:select>
                                </td>
                                <td>
                                    <spring:message code="BzComposer.global.shippingvia" />
                                </td>
                                <td>
                                    <form:select path="shipping" style="width:150px;">
                                        <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                                        <c:forEach items="${ShipCarrierList}" var="curObject">
                                            <option value="${curObject.value}" ${curObject.value==defaultCongurationData.customerShippingId?'selected':''}>${curObject.label}</option>
                                        </c:forEach>
                                    </form:select>
                                </td>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <!-- Manage Address Start here -->
                <div id="table-negotiations">
                    <table align="center" style="width: 100%" class="tabla-listados"  cellspacing="0" >
                        <thead>
                            <tr>
                                <th colspan="4">
                                    <spring:message code="BzComposer.addnewcustomer.manageaddresses" />
                                </th>
                            </tr>
                        </thead>
                        <thead>
                            <tr>
                                <td colspan="4">
                                    &nbsp;<input type="checkbox" id="chk_setaddress" name="setdefaultaddress" onclick="hidebsaddress(this.form);"
                                            value="${defaultCongurationData.addressSettings}" ${defaultCongurationData.addressSettings=='on'?'checked':''} />
                                        <input type="hidden" name="setdefaultbs" value="" />
                                    <font size="2">
                                        <spring:message code="BzComposer.addnewcustomer.differentaddress"/>
                                    </font>
                                </td>
                            </tr>
                        </thead>
                        <tr>
                            <td>
                                <table class="tabla-listados" cellspacing="0" width="300" align="left">
                                    <thead>
                                        <tr>
                                            <th colspan="5">
                                                <spring:message code="BzComposer.addnewcustomer.billingaddress"/>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><spring:message code="BzComposer.global.company" /></td>
                                            <td colspan="3">
                                                <form:input path="bscname" disabled="true" style="width:80%;" />
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.register.dbaName" /></td>
                                            <td>
                                                <form:input path="bsdbaName" disabled="true" />
                                            </td>
                                            <td colspan="3">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <spring:message code="BzComposer.global.firstname" />
                                            </td>
                                            <td>
                                                <form:input path="bsfirstName" disabled="true" />
                                            </td>
                                            <td>
                                                <spring:message code="BzComposer.global.lastname" />
                                            </td>
                                            <td>
                                                <form:input path="bslastName" disabled="true" />
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <spring:message code="BzComposer.global.address1" />
                                            </td>
                                            <td colspan="3">
                                                <form:input path="bsaddress1"  disabled="true" style="width:80%;" />
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <spring:message code="BzComposer.global.address2" />
                                            </td>
                                            <td colspan="3">
                                                <form:input path="bsaddress2"  disabled="true" style="width:80%;" />
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="lblZipcodeShow"><spring:message code="BzComposer.global.zipcode" /></div>
                                                <div class="lblPostalcodeShow"><spring:message code="BzComposer.global.postalcodes" /></div>
                                            </td>
                                            <td>
                                                <form:input path="bszipCode" disabled="true" onfocusout="loadAddressDetailsByZipcode(this.value, 2)" onkeypress="return numbersonly(event,this.value)" />
                                            </td>
                                            <td>
                                                <spring:message code="BzComposer.global.city" />
                                            </td>
                                            <td>
                                                <form:select path="bscity" id="cityID2" style="width:200px;">
                                                    <form:option value="0"><spring:message code="BzComposer.register.selectcity" /></form:option>
                                                    <c:forEach items="${cityList}" var="currObject">
                                                        <form:option value="${currObject.cityId}">${currObject.cityName}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="lblStateShow"><spring:message code="BzComposer.global.state" /></div>
                                                <div class="lblProvinceShow"><spring:message code="BzComposer.global.province" /></div>
                                            </td>
                                            <td>
                                                <form:select path="bsstate" id="stateID2" onchange="loadCitiesByStateID(this.value, 2);" style="width:200px;">
                                                    <form:option value="0"><spring:message code="BzComposer.register.selectstate" /></form:option>
                                                    <c:forEach items="${stateList}" var="currObject">
                                                        <form:option value="${currObject.stateId}">${currObject.state}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                                <form:hidden path="bsprovince" />
                                            </td>
                                            <td id="t_country">
                                                <spring:message code="BzComposer.global.country" />
                                            </td>
                                            <td>
                                                <form:select path="bscountry" id="countryID2" onchange="loadStatesByCountryID(this.value, 2);" style="width:200px;">
                                                    <form:option value="0"><spring:message  code="BzComposer.register.selectcounry"/></form:option>
                                                    <c:forEach items="${countryList}" var="currObject">
                                                        <form:option value="${currObject.countryId}">${currObject.countryName}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td colspan="5" align="center">
                                                <button type="button" class="formbutton" name="ClearBillingBtn" onclick="clearBillingAdd();" title="Clear Billing Address">
                                                    <spring:message code='BzComposer.updatevendor.clearbillingaddress'/>
                                                </button>
                                            </td>
                                        </tr>
                                </table>
                            </td>
                            <td style="vertical-align: inherit;">
                                <input type="button" name="CopyBtn" class="formbutton" onclick="CopyBilladdToShipAdd();" value="<spring:message code='BzComposer.addnewcustomer.copyfrombillingtoshipping'/>" title="Copy Address">
                            </td>
                            <td>
                                <table class="tabla-listados" cellspacing="0" width="300" align="left">
                                    <tbody>
                                        <thead>
                                            <tr>
                                                <th colspan="5">
                                                    <spring:message code="BzComposer.addnewcustomer.shippingaddress"/>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tr>
                                            <td><spring:message code="BzComposer.global.company" /></td>
                                            <td colspan="3">
                                                <form:input path="shcname" disabled="true" style="width:80%;" />
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.register.dbaName" /></td>
                                            <td>
                                                <form:input path="shdbaName" disabled="true" />
                                            </td>
                                            <td colspan="3">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <spring:message code="BzComposer.global.firstname" />
                                            </td>
                                            <td>
                                                <form:input path="shfirstName" disabled="true" />
                                            </td>
                                            <td>
                                                <spring:message code="BzComposer.global.lastname" />
                                            </td>
                                            <td>
                                                <form:input path="shlastName" disabled="true" />
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <spring:message code="BzComposer.global.address1" />
                                            </td>
                                            <td colspan="3">
                                                <form:input path="shaddress1"  disabled="true" style="width:80%;" />
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <spring:message code="BzComposer.global.address2" />
                                            </td>
                                            <td colspan="3">
                                                <form:input path="shaddress2"  disabled="true" style="width:80%;" />
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="lblZipcodeShow"><spring:message code="BzComposer.global.zipcode" /></div>
                                                <div class="lblPostalcodeShow"><spring:message code="BzComposer.global.postalcodes" /></div>
                                            </td>
                                            <td>
                                                <form:input path="shzipCode" disabled="true" onfocusout="loadAddressDetailsByZipcode(this.value, 3)" onkeypress="return numbersonly(event,this.value)" />
                                            </td>
                                            <td>
                                                <spring:message code="BzComposer.global.city" />
                                            </td>
                                            <td>
                                                <form:select path="shcity" id="cityID3" style="width:200px;">
                                                    <form:option value="0"><spring:message code="BzComposer.register.selectcity" /></form:option>
                                                    <c:forEach items="${cityList}" var="currObject">
                                                        <form:option value="${currObject.cityId}">${currObject.cityName}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="lblStateShow"><spring:message code="BzComposer.global.state" /></div>
                                                <div class="lblProvinceShow"><spring:message code="BzComposer.global.province" /></div>
                                            </td>
                                            <td>
                                                <form:select path="shstate" id="stateID3" onchange="loadCitiesByStateID(this.value, 3);" style="width:200px;">
                                                    <form:option value="0"><spring:message code="BzComposer.register.selectstate" /></form:option>
                                                    <c:forEach items="${stateList}" var="currObject">
                                                        <form:option value="${currObject.stateId}">${currObject.state}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                                <form:hidden path="shprovince" />
                                            </td>
                                            <td id="t_country">
                                                <spring:message code="BzComposer.global.country" />
                                            </td>
                                            <td>
                                                <form:select path="shcountry" id="countryID3" onchange="loadStatesByCountryID(this.value, 3);" style="width:200px;">
                                                    <form:option value="0"><spring:message  code="BzComposer.register.selectcounry"/></form:option>
                                                    <c:forEach items="${countryList}" var="currObject">
                                                        <form:option value="${currObject.countryId}">${currObject.countryName}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr align="center">
                                            <td colspan="5">
                                                <button type="button" class="formbutton" name="ClearShippingBtn" onclick="clearShippingAdd();" title="Clear Shipping Address">
                                                    <spring:message code='BzComposer.addnewcustomer.clearshippingaddress'/>
                                                </button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                            <td></td>
                        </tr>
                            <tr>
                                <td colspan="4" align="center">&nbsp;</td>
                            </tr>
                        </table>
                    </div>
                    <!-- Manage Address END here -->
			</div>
		</div>

		<!-- PAyment information tab -->
        <div id="payment-2">
            <div id="content2" class="tabPage">
                <div id="table-negotiations" style="width:100%">
                    <table class="tabla-listados" cellspacing="0">
                        <thead>
                            <tr>
                                <th colspan="12">
                                    <spring:message code="BzComposer.addnewcustomer.creditcardinfo" />
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="3">
                                    <spring:message code="BzComposer.global.cardtype" />
                                </td>
                                <td colspan="5">
                                    <form:select path="ccType" style="width:150px;">
                                        <form:option value="">
                                            <spring:message code="BzComposer.ComboBox.Select" />
                                        </form:option>
                                        <form:options items="${CreditCardList}" itemValue="value" itemLabel="label"/>
                                    </form:select>
                                </td>
                                <td colspan="4">
                                    &nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <spring:message code="BzComposer.global.cardnumber" />
                                </td>
                                <td colspan="3">
                                    <form:input path="cardNo" style="width:225px;" onkeypress="return numbersonly(event,this.value);" />
                                </td>
                                <td colspan="3">
                                    <spring:message code="BzComposer.global.expdate" />
                                </td>
                                <td colspan="3">
                                    <form:input path="expDate" readonly="true" />
                                    <img src="${pageContext.request.contextPath}/images/cal.gif"
                                    onclick="displayCalendar(document.CustomerForm.expDate,'mm/yyyy',this);">
                                    &nbsp;&nbsp;&nbsp;
                                    <spring:message code="BzComposer.global.mformate" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <spring:message code="BzComposer.global.CW2" />
                                </td>
                                <td colspan="3">
                                    <form:input path="cw2" size="7" />
                                </td>
                                <td colspan="3">
                                    <spring:message code="BzComposer.global.cardholdername" />
                                </td>
                                <td colspan="3">
                                    <form:input path="cardHolderName" />
                                </td>
                            </tr>
                            <tr>
                                <tdcolspan="3">
                                    <spring:message code="BzComposer.addnewcustomer.billingaddress"/>
                                </td>
                                <td colspan="3">
                                    <form:input path="cardBillAddress" size="50" />
                                </td>
                                <td colspan="3">
                                    <spring:message code="BzComposer.global.zipcode" />
                                </td>
                                <td colspan="3">
                                    <form:input onkeypress="return numbersonly(event,this.value)" path="cardZip" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Service information tab -->
  		<div id="service-3" style="display:none;">
      		<div id="content2" class="tabPage">
      			<form:hidden path="table_serviceName" value="" />
      			<form:hidden path="table_defaultVal" value="0" />
      			<form:hidden path="table_size" value="0" />Configuration
      			<form:hidden path="table_invId" value="" />
      			<form:hidden path="table_bal" value="" />
      			<form:hidden path="table_serID" value="" />
      			<form:hidden path="table_DbDefSer" value="0" />
      			<input type="hidden" name="ssize" id="sSize" value='${ServiceList.size()}'>
      			<c:forEach items="${ServiceList}" var="objList1" varStatus="loop">
					<input type="hidden" value='${objList1.serviceID}' id='${loop.index}sid' />
					<input type="hidden" value='${objList1.serviceName}' id='${loop.index}sname' />
					<input type="hidden" value='${objList1.invoiceStyleId}' id='${loop.index}isid' />
				</c:forEach>
					<input type="hidden" name="isize" id="iSize" value='${InvoiceName.size()}'>
					<c:forEach items="${InvoiceName}" var="objList2" varStatus="loop">
						<input type="hidden" value='${objList2.invoiceStyleId}' id='${loop.index}invid' />
						<input type="hidden" value='${objList2.invoiceStyle}' id='${loop.index}iname' />
					</c:forEach>
					<c:if test="${not empty BalenceDetails}">
						<input type="hidden" name="dsize" id="dSize" value='${BalenceDetails.size()}'>
						<c:forEach items="${BalenceDetails}" var="objList3" varStatus="loop">
							<input type="hidden" value='${objList3.serviceBalance}' id='${loop.index}bal' />
							<input type="hidden" value='${objList3.defaultService}' id='${loop.index}dservice' />
							<input type="hidden" value='${objList3.serviceID}' id='${loop.index}srid' />
							<input type="hidden" value='${objList3.clientVendorID}' id='${loop.index}cvID' />
						</c:forEach>
					</c:if>
					<c:if test="${empty BalenceDetails}">
					    <input type="hidden" name="dsize" id="dSize" value='0'>
					</c:if>
					<div id="table-negotiations" style="width:100%">
						<input type="hidden" value="1" id="hidn" />
						<table class="tabla-listados" cellspacing="0" style="width: 100%">
							<thead>
								<tr>
									<th colspan="12">
										<strong>
											<spring:message code="BzComposer.addnewcustomer.defaultserviceheader" />
										</strong>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="3">
										<spring:message code="BzComposer.addnewcustomer.service"/>
									</td>
									<td colspan="3">
										<form:select path="serviceID">
											<form:options items="${ServiceList}" itemValue="serviceID" itemLabel="serviceName" />
										</form:select>
									</td>
									<td colspan="3">
										<button type="button"class="formbutton" onclick="addToTable(this.form);"><spring:message code='BzComposer.global.add'/></button>
									</td>
									<td colspan="3">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<spring:message code="BzComposer.addnewcustomer.selectedservice" />
									</td>
									<td colspan="9">
										&nbsp;
									</td>
								</tr>
							</tbody>
						</table>
						<div>
							<table class="tabla-listados" cellspacing="0" style="width: 100%">
								<thead>
									<tr>
										<th>
											<spring:message code="BzComposer.addnewcustomer.servicename" />
										</th>
										<th>
											<spring:message code="BzComposer.addnewcustomer.invoicestyle" />
										</th>
										<th>
											<spring:message code="BzComposer.addnewcustomer.balance" />
										</th>
										<th>
											<spring:message code="BzComposer.addnewcustomer.default" />
										</th>
										<th>
											<spring:message code="BzComposer.addnewcustomer.remove" />
										</th>
									</tr>
								</thead>
								<tr id="tr$$">
									<td align="left"></td>
									<td align="left"></td>
									<td align="left"></td>
									<td align="left"></td>
									<td align="left"></td>
								</tr>
								<c:if test="${not empty ServiceInfo}">
                                    <input type="hidden" name="serviceSize" id="sIDSize" value='${ServiceInfo.size()}'/>
                                    <c:forEach items="${ServiceInfo}" var="objList" varStatus="loop">
                                        <input type="hidden" id='${loop.index}_ser' value='${objList.serviceID}' />
                                            <tr id='row${objList.serviceID}'>
                                            <!-- id='tr${loop.index}' -->
                                                <td align="left">${objList.serviceName}</td>
                                                <td align="left">${objList.invoiceStyle}</td>
                                                <td align="left">${objList.serviceBalance}</td>
                                                <c:if test="${objList.defaultService == 1}">
                                                    <td>
                                                        <input type="radio" name="defaultService" value='${objList.defaultService}'
                                                            onclick="setDefault('${objList.serviceID}',this.form);" checked="checked" />
                                                    </td>
                                                </c:if>
                                                <c:if test="${objList.defaultService != 1}">
                                                    <td>
                                                        <input type="radio" name="defaultService" value='${objList.defaultService}'
                                                            onclick="setDefault('${objList.serviceID}',this.form);" />
                                                    </td>
                                                </c:if>
                                                <td>
                                                    <img src="${pageContext.request.contextPath}/images/delete.png"
                                                        alt="Deletable" onclick="removeFromTable('${objList.serviceID}');" />
                                                <!-- onclick="removeFromTable('${loop.index}');" -->
                                                </td>
                                                <td align="center">
                                                    <input type="hidden" value='${objList.serviceID}' id='${loop.index}id' />
                                                </td>
                                            </tr>
                                            <script>
                                                var temp;
                                                var defService="0";
                                                exist[cnt++]='${objList.serviceID}';
                                                invStyleName='${objList.invoiceStyle}';
                                                InvSize=document.getElementById('iSize').value;
                                                for (i=0; i<InvSize; i++){
                                                    temp=document.getElementById(i+'iname').value;
                                                    if (temp==invStyleName){							//invstyle found...
                                                        temp=document.getElementById(i+'invid').value;
                                                        defService=document.getElementById(i+'dservice').value;
                                                        	//invstyleid found...
                                                        break;
                                                    }
                                                }

                                                document.forms[0].table_serID.value+='${objList.serviceID};';
                                                document.forms[0].table_bal.value+='${objList.serviceBalance};';
                                                document.forms[0].table_invId.value+=temp+";";
                                                document.forms[0].table_defaultVal.value=defService;

                                                //table_defaultVal

                                                //		"bal="+	document.forms[0].table_bal.value+"\n\n"+
                                                //	 	"invstyles="+		document.forms[0].table_invId.value);
                                            </script>
                                    </c:forEach>
								</c:if>
								<c:if test="${empty ServiceInfo}">
									<input type="hidden" id="sIDSize" value="0" />
								</c:if>
							</table>
							<div>
								<table></table>
							</div>
					</div>
				</div>
			</div>  
		</div>

		<!-- Finance information tab -->
        <div id="finance-4">
            <div id="content4" class="tabPage">
                <!-- add here the content of fifth tab -->
                <div id="table-negotiations" style="width:100%">
                    <table class="tabla-listados" cellspacing="0">
                        <thead>
                            <tr>
                                <th colspan="4">
                                    <spring:message code="BzComposer.addnewcustomer.financemsg"/>
                                </th>
                            </tr>
                        </thead>
                        <thead>
                            <tr>
                                <td>
                                    <input value="on" type="checkbox" id="chk_useind" name="UseIndividualFinanceCharges" onclick="hideother(this.form);">
                                    <font size="2">
                                        <spring:message code="BzComposer.addnewcustomer.useindividualfinancecharges" />
                                    </font>
                                </td>
                                <td colspan="3"></td>
                            </tr>
                            <!-- <tr> -->
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <div>
                                        <div id="table-negotiations" style="width:48%;float: left;">
                                            <table class="tabla-listados" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th colspan="4">
                                                            <spring:message code="BzComposer.addnewcustomer.chargerate"/>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <spring:message code="BzComposer.addnewcustomer.annualintrestrate"/>
                                                        </td>
                                                        <td colspan="3">
                                                            <form:input onkeypress="return numbersonly(event,this.value)" path="annualIntrestRate" disabled="true" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <spring:message code="BzComposer.addnewcustomer.minimumfinanacecharge" />
                                                        </td>
                                                        <td colspan="3">
                                                            <form:input onkeypress="return numbersonly(event,this.value)" path="minFCharges" disabled="true" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <spring:message code="BzComposer.addnewcustomer.graceperiod" />
                                                        </td>
                                                        <td colspan="3">
                                                            <form:input onkeypress="return numbersonly(event,this.value)" path="gracePrd" disabled="true" />
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div id="table-negotiations" style="width:48%;float: right;">
                                            <table class="tabla-listados" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th colspan="4">
                                                            <spring:message code="BzComposer.addnewcustomer.applyingcharges" />
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td colspan="4">
                                                            <input type="checkbox" id="chk1" name="AssessFinanceChk" disabled="true" value="${customerDto.fsAssessFinanceCharge}" ${customerDto.fsAssessFinanceCharge=='on'?'checked':''} />
                                                            <spring:message code="BzComposer.addnewcustomer.assessfinance"/>
                                                        </td>
                                                    </tr>
                                                    <tr><td colspan="4">&nbsp;</td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <input type="checkbox" id="chk2" name="FChargeInvoiceChk" disabled="true" value="${customerDto.fsMarkFinanceCharge}" ${customerDto.fsMarkFinanceCharge=='on'?'checked':''} />
                                                            <spring:message code="BzComposer.addnewcustomer.markfinanacechargeinvoice"/>
                                                        </td>
                                                    </tr>
                                                    <tr><td colspan="4">&nbsp;</td></tr>
                                                    <tr><td colspan="4">&nbsp;</td></tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </td>
                                <td colspan="3">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

			</div>
			<table cellpadding="0" cellspacing="0" border="0" align=center  style="width: 100%;margin-top:5px;">
				<script>
					count=0;
					cnt=0;
					var exist= new Array(10); 
					for (i=0; i<exist.length; i++){
						exist[i]=-1;
					}
				</script>
				<tr>
					<td valign=top class="tabPage">			
					</td>
				</tr>
				<tr>
					<td align="center">
						<button type="button" class="formbutton" title="New Customer" onclick="NewCustomer();"><spring:message code='BzComposer.global.new'/></button>
						<button type="button" class="formbutton" title="Save Customer" onclick="CheckMambership();"><spring:message code='BzComposer.global.save'/></button>
						<button type="button" class="formbutton" title="Close" onclick="CloseMe();"><spring:message code='BzComposer.global.close'/></button>
						<input type="hidden" name="stname" value="" id="stateId" />
					</td>
				</tr>
			</table>
			<div>
				<input type="hidden" name="bst" id="bsst" value="0" />
			</div>
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
window.onunload = refreshParent;
function refreshParent() {
    window.opener.location.reload();
}
function validate() {
    var email = document.CustomerForm.email.value;
    var mail =String(email);
    var pattern=/^[_0-9a-zA-z]+(\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\.[A-Za-z]+)+$/;
    if(email==""){
        //return enterEmailValidationDialog();
        return true;
    }
    else if (!pattern.test(email)) {
        document.CustomerForm.email.focus();
        return showEmailValidationDialog();
        return false;
    }
    if(mail.length>=50){
        document.CustomerForm.email.value="";
        document.CustomerForm.email.focus();
        return showEmailLengthValidationDialog();
        return false;
    }
    return true;
}

function numbersonly(e,val)
{
	var temp=val.indexOf(".");
	var unicode=e.charCode? e.charCode : e.keyCode;
	if (unicode!=8){
 		//if the key isn't the backspace key (which we should allow)
		if(unicode==46 && temp==-1){
 			return true;
		} 
		else 
		if (unicode<48||unicode>57){
			return false; //disable key press
		}
	}
}

function CopyBilladdToShipAdd()
{
	document.CustomerForm.shcname.value= document.CustomerForm.bscname.value;
	document.CustomerForm.shdbaName.value = document.CustomerForm.bsdbaName.value;
	document.CustomerForm.shfirstName.value= document.CustomerForm.bsfirstName.value;
	document.CustomerForm.shlastName.value= document.CustomerForm.bslastName.value;
	document.CustomerForm.shaddress1.value= document.CustomerForm.bsaddress1.value;
	document.CustomerForm.shaddress2.value= document.CustomerForm.bsaddress2.value;
	document.CustomerForm.shzipCode.value = document.CustomerForm.bszipCode.value;
	document.CustomerForm.shprovince.value=document.CustomerForm.bsprovince.value;
	document.CustomerForm.shcountry.value= document.CustomerForm.bscountry.value;
	document.CustomerForm.shstate.value = document.CustomerForm.bsstate.value
	document.CustomerForm.shcity.value= document.CustomerForm.bscity.value;
}

function clearBillingAdd()
{
	document.CustomerForm.bscname.value="";
	document.CustomerForm.bsdbaName.value="";
	document.CustomerForm.bsfirstName.value="";
	document.CustomerForm.bslastName.value="";
	document.CustomerForm.bsaddress1.value="";
	document.CustomerForm.bsaddress2.value="";
	document.CustomerForm.bscity.value="0";
	document.CustomerForm.bszipCode.value="";
	document.CustomerForm.bsprovince.value="";
	document.CustomerForm.bscountry.value="0";
	document.CustomerForm.bsstate.value="0";
}
function clearShippingAdd()
{
	document.CustomerForm.shcname.value="";
	document.CustomerForm.shdbaName.value="";
	document.CustomerForm.shfirstName.value="";
	document.CustomerForm.shlastName.value= "";
	document.CustomerForm.shaddress1.value= "";
	document.CustomerForm.shaddress2.value= "";
	document.CustomerForm.shcity.value= "0";
	document.CustomerForm.shzipCode.value = "";
	document.CustomerForm.shprovince.value="";
	document.CustomerForm.shcountry.value= "0";
	document.CustomerForm.shstate.value="0";
}
function CheckMambership() {
	debugger;
	var membershipLevel = "<%= request.getAttribute("membershipLevel")%>";
	var size = "<%= request.getAttribute("CustomerSize")%>";
	if(membershipLevel == "Standard"){
		if(size>=1000){
			debugger;
			return maxnumberofuserdialog();
		}else {
			return AddCustomer();
		}
	}else if(membershipLevel == "Professional"){
		if(size>=10000){
			debugger;
			return maxnumberofuserdialog();
		}else {
			return AddCustomer();
		}
	}
}
function AddCustomer()
{
	debugger;
	if(document.CustomerForm.cname.value==""){
		document.CustomerForm.cname.focus();
		return showNameDialog();
	}
	else if(document.CustomerForm.firstName.value==""){
		document.CustomerForm.firstName.focus();
		return showFirstNameDialog();
	}
	else if(document.CustomerForm.lastName.value==""){
		document.CustomerForm.lastName.focus();
		return showLastNameDialog();
	}
	else if(document.CustomerForm.address1.value==""  ){
		document.CustomerForm.address1.focus();
		return showAddress1Dialog();
	}
	else if(document.CustomerForm.city.value==""){
		document.CustomerForm.city.focus();
		return showCityDialog();
	}
	else if(document.CustomerForm.zipCode.value==""){
		document.CustomerForm.zipCode.focus();
		return showZipCodeDialog();
	}
	else if(String(document.CustomerForm.cellPhone.value).length>16){
		document.CustomerForm.cellPhone.value="";
		document.CustomerForm.cellPhone.focus();
		return showCellPhoneLengthDialog();
	}
	else 
	{
		if(validate())
		{
			debugger;
			event.preventDefault();
			$("#addNewCustomerDialog").dialog({
                resizable: false,
                height: 200,
                width: 500,
                modal: true,
                buttons: {
                    "<spring:message code='BzComposer.global.ok'/>": function () {
                        debugger;
                        $(this).dialog("close");
                        document.getElementById('tabid').value="AddCustomer";
                        document.forms["frmNewCustomer"].action = "Customer?tabid=AddCustomer" ;
                        document.forms["frmNewCustomer"].submit();
                    },
                    "<spring:message code='BzComposer.global.cancel'/>": function () {
                        $(this).dialog("close");
                        return false;
                    }
                }
            });
            return false;
		}
	}
}
function maxnumberofuserdialog()
{
	event.preventDefault();
	$("#maxnumberofuserdialog").dialog({
    	resizable: false,
        height: 250,
        width: 800,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function hideother(form){
	chbox=document.getElementById('chk_useind');
	if(chbox.checked){
		document.CustomerForm.annualIntrestRate.disabled=false;
		document.CustomerForm.minFCharges.disabled=false;
		document.CustomerForm.gracePrd.disabled=false;
		document.getElementById('chk1').disabled=false;
		document.getElementById('chk2').disabled=false;
	}
	else{
		document.CustomerForm.annualIntrestRate.disabled=true;
		document.CustomerForm.minFCharges.disabled=true;
		document.CustomerForm.gracePrd.disabled=true;
		document.getElementById('chk1').disabled=true;
		document.getElementById('chk2').disabled=true;
	}
}
function hidebsaddress(form){
	chbox=document.getElementById('chk_setaddress');
	if(!chbox.checked)
	{				
		document.CustomerForm.bscname.disabled=false;
		document.CustomerForm.bsdbaName.disabled=false;
		document.CustomerForm.bsfirstName.disabled=false;
		document.CustomerForm.bslastName.disabled=false;	
		document.CustomerForm.bsaddress1.disabled=false;
		document.CustomerForm.bsaddress2.disabled=false;
		document.CustomerForm.bscity.disabled=false;
		document.CustomerForm.bszipCode.disabled=false;
		document.CustomerForm.bsprovince.disabled=false;
		document.CustomerForm.bsstate.disabled=false;
		document.CustomerForm.bscountry.disabled=false;
				
		document.CustomerForm.shcname.disabled=false;
		document.CustomerForm.shdbaName.disabled=false;
		document.CustomerForm.shfirstName.disabled=false;
		document.CustomerForm.shlastName.disabled=false;	
		document.CustomerForm.shaddress1.disabled=false;
		document.CustomerForm.shaddress2.disabled=false;
		document.CustomerForm.shcity.disabled=false;
		document.CustomerForm.shzipCode.disabled=false;
		document.CustomerForm.shprovince.disabled=false;
		document.CustomerForm.shstate.disabled=false;
		document.CustomerForm.shcountry.disabled=false;
	    document.CustomerForm.setdefaultbs.value="0";		
	}
	else
	{
		document.CustomerForm.bscname.disabled=true;
		document.CustomerForm.bsdbaName.disabled=false;
		document.CustomerForm.bsfirstName.disabled=true;
		document.CustomerForm.bslastName.disabled=true;	
		document.CustomerForm.bsaddress1.disabled=true;
		document.CustomerForm.bsaddress2.disabled=true;
		document.CustomerForm.bscity.disabled=true;
		document.CustomerForm.bszipCode.disabled=true;
		document.CustomerForm.bsprovince.disabled=true;
		document.CustomerForm.bsstate.disabled=true;
		document.CustomerForm.bscountry.disabled=true;

		document.CustomerForm.shcname.disabled=true;
		document.CustomerForm.shdbaName.disabled=true;
		document.CustomerForm.shfirstName.disabled=true;
		document.CustomerForm.shlastName.disabled=true;	
		document.CustomerForm.shaddress1.disabled=true;
		document.CustomerForm.shaddress2.disabled=true;
		document.CustomerForm.shcity.disabled=true;
		document.CustomerForm.shzipCode.disabled=true;
		document.CustomerForm.shprovince.disabled=true;
		document.CustomerForm.shstate.disabled=true;
		document.CustomerForm.shcountry.disabled=true;
	    document.CustomerForm.setdefaultbs.value="";
	    CopyBilladdToShipAdd();
	}
}
function addToTable(form){
	flag=0;
	flag1=0;
	invID=form.serviceID.value;
	SIDSize=document.getElementById('sIDSize') .value;

	SLSize=document.getElementById('sSize') .value;
	InvSize=document.getElementById('iSize') .value;
	DetailSize=document.getElementById('dSize') .value;
	
	var serviceName;
	var InvoiceStyle;
	var ServiceBalance="0.0";
	var DefaultService;

		for(t=0; t<exist.length; t++){	//limit=cnt
			if(invID==exist[t])		{				
				flag=1;
				break;
			}
		}

		if(flag==1)		{

			debugger;
			return showServiceValidationDialog();
			return;
		}
		else {
				flagx = 0;
				for(i=0;i<SLSize;i++)		{
					iinvID=document.getElementById(i+'sid').value;
					isID=document.getElementById(i+'isid').value;
					if(invID==iinvID) {

						serviceName=document.getElementById(i+'sname').value;

						for(j=0;j<InvSize;j++)	{
							isID2=document.getElementById(j+'invid').value;
							if(isID==isID2) {
								for (k=0; k<exist.length; k++){
									if (exist[k]==-1) {
										exist[k]=invID;
										break;
									}
								}
								InvoiceStyle=document.getElementById(j+'iname').value;		
								flagx = 1;
							}				
						}
					}
				}
		
				if(flagx==0){
					InvoiceStyle = "";
					for (k=0; k<exist.length; k++){
						if (exist[k]==-1) {
							exist[k]=invID;
							break;
						}
					}
				}
				
				hidn_val = document.getElementById('hidn').value;
				hidn_val1=hidn_val;
		
				var tr = document.createElement("tr");
				tr.setAttribute("id", "row"+invID);
				
				var tr2 = document.getElementById('tr$$');
				var parentTr = tr2.parentNode;
				parentTr.insertBefore(tr, tr2);

				var td1 = document.createElement("td");
				td1.setAttribute("align", "left");
				tr.appendChild(td1);
				td1.innerHTML=serviceName;

				var td2 = document.createElement("td");
				td2.setAttribute("align", "left");
				tr.appendChild(td2);
				td2.innerHTML=InvoiceStyle;

				var td3 = document.createElement("td");
				td3.setAttribute("align", "left");
				tr.appendChild(td3);
				td3.innerHTML=ServiceBalance;

				var td4 = document.createElement("td");
				td4.setAttribute("align", "left");
				tr.appendChild(td4);
				var rd="<input type=radio id=setdisable name=defaultService onclick=setDefault("+invID+",this.form); />";
				td4.innerHTML=rd;
				var button='<img src="${pageContext.request.contextPath}/images/delete.png" alt="Deletable" onclick=removeFromTable('+invID+'); >';

				var td5 = document.createElement("td");
				td5.setAttribute("align", "left");
				tr.appendChild(td5);
				td5.innerHTML=button;

				document.CustomerForm.table_size.value++;
				document.CustomerForm.table_serID.value+=invID+";";
				document.CustomerForm.table_serviceName.value+=serviceName+";";
				document.CustomerForm.table_bal.value+=ServiceBalance+";";
				if(InvoiceStyle==""){
					form.table_invId.value+="0;";
				}
				else{
					for(p=0;p<InvSize;p++) {
						invoiceName=document.getElementById(p+'iname').value;
						if(invoiceName==InvoiceStyle) {
							document.CustomerForm.table_invId.value+=document.getElementById(p+'invid').value+";";
						}				
					}
				}
				count = ((count)/1 + 1);
				document.getElementById('hidn').value=((hidn_val)/1 + 1);	
			}
		name=document.CustomerForm.table_serviceName.value;
}
function setDefault(invID1,form){
	//form.table_defaultVal.value+=1+":"+invID1+";";
	document.CustomerForm.table_defaultVal.value=invID1;
	//document.getElementById('setdisable').disabled=true;
}
function removeFromTable(idV){
	var str;
	var trid;
//	removeStringValues(document.forms[0].table_serID.value,idV);
	trid="row"+idV;
	for(i=0; i<exist.length; i++){
		if(idV==exist[i]){
			//document.getElementById(trid).style.display='none';
			var ttt=document.getElementById(trid);
				ttt.parentNode.removeChild(ttt);
			exist[i]=-1;			
			cnt--;

			//str=removeStringValues(document.forms[0].table_serID.value,idV);
			removeStringValues(idV);

			//do same for other two strings
			break;
		}
	}
}
function removeStringValues(key){	//from one string('str'), a substring 'key' will be removed
	var str, str2, str3;
	var temp=new Array(20);
	var temp2=new Array(20);
	var temp3=new Array(20);				

	str= document.forms[0].table_serID.value;		
	str2=document.forms[0].table_bal.value;
	str3=document.forms[0].table_invId.value;
	
	temp=str.split(";");
	temp2=str2.split(";");
	temp3=str3.split(";");		
	
	str=str2=str3="";
	for (i=0; i<temp.length; i++){
		if (temp[i]!=key){
			k=0;
			for (k=0; k<i; k++){
				if (temp[i]==temp[k]){
					k=-1;
					break;
				}
			}
			if (k!=-1){
				str=str+temp[i]+";";
				str2=str2+temp2[i]+";"
				str3=str3+temp3[i]+";"					
			}
		}
	}
	str=str.substring(0,str.length-1);	//removes last semi-colon
	str2=str2.substring(0,str2.length-1);	//removes last semi-colon
	str3=str3.substring(0,str3.length-1);	//removes last semi-colon

	if (key==document.forms[0].table_defaultVal.value){
		document.forms[0].table_defaultVal.value="0";	//reset if no service is set to default
	}		


	//		"\n\ntable_bal="+document.forms[0].table_bal.value+"\nstr2="+str2+
	//	"\n\ntable_invId="+document.forms[0].table_invId.value+"\nstr3="+str3+
	//"\n\ndefault service="+document.forms[0].table_defaultVal.value);

	document.forms[0].table_serID.value=str;
	document.forms[0].table_bal.value=str2;
	document.forms[0].table_invId.value=str3;
}
function NewCustomer(){
//billing addressres/*
	document.CustomerForm.bscname.value="";
	document.CustomerForm.bsfirstName.value="";
	document.CustomerForm.bslastName.value="";
	document.CustomerForm.bsaddress1.value="";
	document.CustomerForm.bsaddress2.value="";
	document.CustomerForm.bscity.value="0";
	document.CustomerForm.bszipCode.value="";
	document.CustomerForm.bsprovince.value="";
	document.CustomerForm.bscountry.value="0";
//shipping addresses
	document.CustomerForm.shcname.value="";
	document.CustomerForm.shfirstName.value="";
	document.CustomerForm.shlastName.value= "";
	document.CustomerForm.shaddress1.value= "";
	document.CustomerForm.shaddress2.value= "";
	document.CustomerForm.shcity.value= "0";
	document.CustomerForm.shzipCode.value = "";
	document.CustomerForm.shprovince.value="";
	document.CustomerForm.shcountry.value= "0";
//genaral Tab
	document.CustomerForm.firstName.value="";
	document.CustomerForm.lastName.value="";
	document.CustomerForm.address1.value="";
	document.CustomerForm.address2.value="";
	document.CustomerForm.city.value="0";
	//document.CustomerForm.stateName.value="";
	document.CustomerForm.zipCode.value="";
	document.CustomerForm.phone.value="";
	document.CustomerForm.cellPhone.value="";
	document.CustomerForm.fax.value="";
	document.CustomerForm.email.value="";
//	document.CustomerForm.title.value="";
	document.CustomerForm.province.value="";
//	document.CustomerForm.country.value="0";
	document.CustomerForm.homePage.value="www.";
//	document.CustomerForm.type.value="";
	document.CustomerForm.texID.value="";
	document.CustomerForm.openingUB.value="";
	document.CustomerForm.extCredit.value="";
	document.CustomerForm.memo.value="";
	document.CustomerForm.ccType.value="";
	document.CustomerForm.cardNo.value="";
	document.CustomerForm.expDate.value="";
	document.CustomerForm.cw2.value="";
	document.CustomerForm.cardHolderName.value="";
	document.CustomerForm.cardBillAddress.value="";
	document.CustomerForm.cardZip.value="";
	//document.CustomerForm.dateAdded.value="";
	document.CustomerForm.cname.value="";
	document.CustomerForm.chk_alsovendor.checked=false;
	document.CustomerForm.chk_useind.checked=false;

	$("#countryID").val(231).change();
    $("#countryID2").val(231).change();
    $("#countryID3").val(231).change();
}

function CloseMe(){
	window.close();
}
function showNameDialog(){
	event.preventDefault();
	$("#showNameDialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function enterEmailValidationDialog(){
	event.preventDefault();
	$("#enterEmailValidationDialog").dialog({
    	resizable: false,
        height: 200,
        width: 450,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function showEmailLengthValidationDialog(){
	event.preventDefault();
	$("#enterEmailValidationDialog").dialog({
    	resizable: false,
        height: 200,
        width: 450,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function showFirstNameDialog(){
	event.preventDefault();
	$("#showFirstNameDialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function showLastNameDialog(){
	event.preventDefault();
	$("#showLastNameDialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function showAddress1Dialog(){
	event.preventDefault();
	$("#showAddress1Dialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function showCityDialog(){
	event.preventDefault();
	$("#showCityDialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function showZipCodeDialog(){
	event.preventDefault();
	$("#showZipCodeDialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function showphoneemptydialog(){
	event.preventDefault();
	$("#showphoneemptydialog").dialog({
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
function showCellPhoneLengthDialog(){
	event.preventDefault();
	$("#showCellPhoneLengthDialog").dialog({
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
function showEmailValidationDialog(){
	event.preventDefault();
	$("#showEmailValidationDialog").dialog({
    	resizable: false,
        height: 200,
        width: 450,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function showServiceValidationDialog(){
	debugger;
	event.preventDefault();
	$("#showServiceValidationDialog").dialog({
    	resizable: false,
        height: 200,
        width: 450,
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
<!-- Dialog box used in this page -->
<div id="enterEmailValidationDialog" style="display:none;">
	<p><spring:message code="Bzcomposer.updatevendor.enteremailaddress"/></p>
</div>
<div id="showEmailLengthValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.entervalidemaillength" /></p>
</div>
<div id="showNameDialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.entercompanyname" /></p>
</div>
<div id="showFirstNameDialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.enterfirstname" /></p>
</div>
<div id="showLastNameDialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.enterlastname" /></p>
</div>
<div id="showAddress1Dialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.enteradderss1" /></p>
</div>
<div id="showCityDialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.entercity" /></p>
</div>
<div id="showZipCodeDialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.enterzipcode" /></p>
</div>
<div id="showEmailValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.entervalidemail" /></p>
</div>
<div id="showCellPhoneLengthDialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.cellphonelength" /></p>
</div>
<div id="showphoneemptydialog" style="display:none;">
	<p>Please Enter Phone First</p>
</div>
<div id="addNewCustomerDialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.insertnewcustomer"/></p>
</div>
<div id="showServiceValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.serviceexist" /></p>
</div>
<div id="errorOccurred" style="display:none;font-size:12px;">
	<p><spring:message code="BzComposer.common.erroroccurred"/></p>
</div>
<!-- Dialog box used in this page -->
<div id="maxnumberofuserdialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.networksecurity.maxnumberofuser"/></p>
</div>