<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<title><spring:message code="BzComposer.addnewvendortitle" /></title>
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
<body onload="newValues();">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<%-- <form action="Vendor?tabid=AOVODO" method="post"> --%>
<form:form method="post" id="frmAddVendor" name="VendorForm" modelAttribute="vendorDto" >
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
    <!-- begin Contents -->
    <!-- add the code for tab here -->
    <div>
        <span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
            <spring:message code="BzComposer.addnewvendor.addnewvendortitle"/>
        </span>
    </div>
	<table cellpadding="0" cellspacing="0" border="0" align="center" style="width: 100%;">
		<tr>
			<td class="pageWin">
				<table cellpadding="0" cellspacing="0" border="0" style="width: 100%;">
					<c:if test="${not empty Status}">
					<tr>
						<td colspan="3"><span class="msgstyle">${Status}</span></td>
					</tr>
					</c:if>
					<c:if test="${not empty actionMsg}">
					<tr>
						<td colspan="3"><span class="msgstyle">${actionMsg}</span></td>
						<% session.removeAttribute("actionMsg"); %>
					</tr>
					</c:if>
				</table>
			</td>
		</tr>
	</table>
	<div id="tabs" style="min-height:550px;">
		<ul>
		    <li><a href="#General-1"><spring:message code="BzComposer.updatevendor.tabs.general" /></a></li>
		    <!-- <li><a href="#service-2"><spring:message code="BzComposer.updatevendor.tabs.service" /></a></li> -->
		    <li><a href="#payment-2"><spring:message code="BzComposer.addnewcustomer.tabs.paymentInformation" /></a></li>
            <li><a href="#finance-4"><spring:message code="BzComposer.updatevendor.tabs.financecharges" /></a></li>
		</ul>
		<div id="General-1">
   			<div id="content1" class="tabPage">
   				<!-- add here the content of first tab -->
				<div id="table-negotiations">
					<table class="tabla-listados">
						<thead>
							<tr>
								<th colspan="7">
									<spring:message code="BzComposer.updatevendor.vendorinformation"/>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							    <td><spring:message code="BzComposer.global.customerid" /></td>
                                <td><form:input path="clientVendorID" readonly="true" /></td>
                                <td colspan="5">&nbsp;</td>
							</tr>
							<tr>
							    <td><spring:message code="BzComposer.global.titlename" /></td>
                                <td>
                                    <form:select path="title" style="width:100px;">
                                        <form:options items="${titleList}" itemValue="value" itemLabel="label" />
                                    </form:select>
                                </td>
                                <td colspan="5">
                                    <table style="width:100%;">
                                    <tr>
                                        <td>
                                            <spring:message code="BzComposer.global.firstname" />
                                            <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                        </td>
                                        <td><form:input type="text" path="firstName" size="20" /></td>
                                        <td><spring:message code="BzComposer.global.middlename" /></td>
                                        <td><form:input type="text" path="middleName" size="15" /></td>
                                        <td><spring:message code="BzComposer.global.lastname" />
                                            <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                        </td>
                                        <td><form:input type="text" path="lastName" size="20" /></td>
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
									<form:input type="text" path="cname" style="width:90%;" />
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
                                    <form:input type="text" path="address1" style="width:90%;" />
                                </td>
                                <td colspan="3">&nbsp;</td>
                            </tr>
                            <tr>
								<td align="left"><spring:message code="BzComposer.global.address2" /></td>
								<td colspan="3">
									<form:input type="text" path="address2" style="width:90%;" />
								</td>
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr>
							    <td>
                                    <div class="lblZipcodeShow float-left"><spring:message code="BzComposer.global.zipcode" /></div>
                                    <div class="lblPostalcodeShow float-left"><spring:message code="BzComposer.global.postalcodes" /></div>&nbsp;
                                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                </td>
                                <td>
                                    <form:input type="text" path="zipCode" onfocusout="loadAddressDetailsByZipcode(this.value, 1)" onkeypress="return numbersonly(event,this.value)" />
                                </td>
                                <td>
                                    <spring:message code="BzComposer.global.city" />
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
									<form:input type="text" path="phone" maxlength="15" onkeypress="return numbersonly(event,this.value)" onchange="validateUSAPhoneNumber(this, true);" />
								</td>
								<td>
									<spring:message code="BzComposer.global.mobileNumber" />
								</td>
								<td colspan="2">
									<form:input type="text" path="cellPhone" maxlength="16" onkeypress="return numbersonly(event,this.value)" onchange="validateUSAPhoneNumber(this, true);" />
									<form:checkbox path="isMobilePhoneNumber" onchange="copyPhoneNumber(this.form);" />
                                    (<spring:message code="BzComposer.global.isPhoneNumber" />)
								</td>
								<td>
                                    <spring:message code="BzComposer.global.fax" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <form:input type="text" path="fax" onkeypress="return numbersonly(event,this.value)" onchange="validateUSAPhoneNumber(this, true);" />
                                </td>
                                <td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<spring:message code="BzComposer.global.email" />
									<span class="inputHighlighted">
                                        <spring:message code="BzComposer.CompulsoryField.Validation" />
                                    </span>
								</td>
								<td colspan="3">
									<form:input type="text" path="email" maxlength="45" size="40" />
								</td>
								<td>
                                    <spring:message code="BzComposer.global.homepage" />
                                </td>
                                <td>
                                    <form:input type="text" path="homePage" size="40" />
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>
                                    <spring:message code="BzComposer.global.dateadded" />
                                </td>
                                <td>
                                    <form:input type="text" path="dateAdded" readonly="true" />
                                    <img src="${pageContext.request.contextPath}/images/cal.gif"
                                    onclick="displayCalendar(document.VendorForm.dateAdded,'mm-dd-yyyy',this);">
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
                                        onclick="displayCalendar(document.VendorForm.terminatedDate,'mm-dd-yyyy',this);">
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
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
									<form:input type="text" path="texID" />
								</td>
								<td>
									<input type="checkbox" name="isTaxable" id="chktax" />
									<spring:message code="BzComposer.global.istaxable" />
								</td>
								<td>
									<input type="checkbox" name="isAlsoClient" id="chk_alsovendor">
									<spring:message code="BzComposer.global.isalsoclient" />
								</td>
							</tr>
							<tr>
							    <td>
                                    <spring:message code="BzComposer.global.oppeningunpaidbalance" />
                                </td>
                                <td>
                                    <form:input type="text" path="openingUB" onkeypress="return numbersonly(event,this.value)" />
                                </td>
								<td>
									<spring:message code="BzComposer.global.existingcredits" />
								</td>
								<td>
									<form:input type="text" path="extCredit" onkeypress="return numbersonly(event,this.value)" />
								</td>
								<td colspan="2">&nbsp;</td>
						    </tr>
                            <tr>
								<td>
									<spring:message code="BzComposer.global.memo" />
								</td>
								<td colspan="5">
                                    <form:textarea path="memo" style="width:90%;" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- Account info Start here -->
                <div id="table-negotiations" style="width:100%">
                    <table class="tabla-listados" cellspacing="0">
                        <thead>
                            <tr>
                                <th colspan="8">
                                    <spring:message code="BzComposer.updatevendor.prefrenceinformation" />
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <spring:message code="BzComposer.global.term" />
                            </td>
                            <td colspan="3">
                                <form:select path="term" style="width:150px;">
                                    <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                                    <c:forEach items="${TermList}" var="obj">
                                        <option value="${obj.term}" ${obj.term==defaultCongurationData.selectedTermId?'selected':''}>${obj.name}</option>
                                    </c:forEach>
                                </form:select>
                            </td>
                            <td>
                                <spring:message code="BzComposer.global.paymentmethod" />
                            </td>
                            <td>
                                <form:select path="paymentType" style="width:150px;">
                                    <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                                    <c:forEach items="${PaymentList}" var="obj">
                                        <option value="${obj.id}" ${obj.id==defaultCongurationData.selectedPaymentId?'selected':''}>${obj.typeName}</option>
                                    </c:forEach>
                                </form:select>
                            </td>
                            <td colspan="2"></td>
                        </tr>
                        <tr>
                            <td>
                                <spring:message code="BzComposer.global.representative" />
                            </td>
                            <td colspan="3">
                                <form:select path="rep" style="width:150px;">
                                    <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                                    <c:forEach items="${RepList}" var="obj">
                                        <option value="${obj.value}" ${obj.value==defaultCongurationData.selectedSalesRepId?'selected':''}>${obj.label}</option>
                                    </c:forEach>
                                </form:select>
                            </td>
                            <td>
                                <spring:message code="BzComposer.global.shippingvia" />
                            </td>
                            <td>
                                <form:select path="shipping" style="width:150px;">
                                    <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                                    <c:forEach items="${ShipCarrierList}" var="obj">
                                        <option value="${obj.value}" ${obj.value==defaultCongurationData.customerShippingId?'selected':''}>${obj.label}</option>
                                    </c:forEach>
                                </form:select>
                            </td>
                            <td colspan="2"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- Account info END here -->
                <!-- Manage Address Start here -->
                <div id="table-negotiations" >
					<table align="center" style="width: 100%" class="tabla-listados">
						<thead>
							<tr>
								<th colspan="5">
									<spring:message code="BzComposer.updatevendor.manageaddresses"/>
								</th>
							</tr>
							</thead>
							<thead>
								<tr>
									<td colspan="5">
										&nbsp;<input type="checkbox" id="chk_setaddress" name="setdefaultaddress" onclick="hidebsaddress(this.form);"
										    value="${defaultCongurationData.addressSettings}" ${defaultCongurationData.addressSettings=='on'?'checked':''} />
										<input type="hidden" name="setdefaultbs" value="" />
										<font size="2">
											<spring:message code="BzComposer.addnewvendor.sameaddresslabeltext"/>
										</font>
									</td>
								<tr>
							</thead>
							<tr>
								<td>
									<table class="tabla-listados" cellspacing="0" width="450" align="left">
										<thead>
											<tr>
												<th colspan="5">
													<spring:message code="BzComposer.updatevendor.billingaddress"/>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<spring:message code="BzComposer.global.company" />
												</td>
												<td colspan="2">
													<form:input path="bscname" disabled="true" style="width:90%;" />
												</td>
												<td colspan="2"></td>
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
													<form:input path="bsfirstName" disabled="true"/>
												</td>
												<td>
													<spring:message code="BzComposer.global.lastname" />
												</td>
												<td>
													<form:input path="bslastName" disabled="true"/>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.global.address1" />
												</td>
												<td colspan="2">
													<form:input path="bsaddress1" disabled="true" style="width:90%;" />
												</td>
												<td colspan="2"></td>
                                            </tr>
                                            <tr>
												<td>
													<spring:message code="BzComposer.global.address2" />
												</td>
												<td colspan="2">
													<form:input path="bsaddress2" disabled="true" style="width:90%;" />
												</td>
												<td colspan="2"></td>
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
                                                    <form:select path="bscity" id="cityID2" style="width:200px;" disabled="true">
                                                        <form:option value="0"><spring:message code="BzComposer.register.selectcity" /></form:option>
                                                        <c:forEach items="${cityList}" var="currObject">
                                                            <form:option value="${currObject.cityId}">${currObject.cityName}</form:option>
                                                        </c:forEach>
                                                    </form:select>
                                                </td>
												<td></td>
											</tr>
											<tr>
											    <td>
                                                    <div class="lblStateShow"><spring:message code="BzComposer.global.state" /></div>
                                                    <div class="lblProvinceShow"><spring:message code="BzComposer.global.province" /></div>
                                                </td>
                                                <td>
                                                    <form:select path="bsstate" id="stateID2" onchange="loadCitiesByStateID(this.value, 2);" style="width:200px;" disabled="true">
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
													<form:select path="bscountry" id="countryID2" onchange="loadStatesByCountryID(this.value, 2);" style="width:200px;" disabled="true">
                                                        <form:option value="0"><spring:message  code="BzComposer.register.selectcounry"/></form:option>
                                                        <c:forEach items="${countryList}" var="currObject">
                                                            <form:option value="${currObject.countryId}">${currObject.countryName}</form:option>
                                                        </c:forEach>
                                                    </form:select>
												</td>
												<td></td>
											</tr>
											<tr>
												<td colspan="5" align="center">
													<button type="button" class="formbutton" name="ClearBillingBtn" onclick="clearBillingAdd();">
													    <spring:message code="BzComposer.updatevendor.clearbillingaddress" />
													</button>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
								<td style="vertical-align: inherit;">
									<input type="button" name="CopyBtn" class="formbutton" onclick="CopyBilladdToShipAdd();"
									value="<spring:message code='BzComposer.updatevendor.copyto'/>" title="Copy Address">
								</td>
								<td>
									<table class="tabla-listados" cellspacing="0" width="450" align="left">
										<thead>
											<tr>
												<th colspan="5">
													<spring:message code="BzComposer.updatevendor.shippingaddress"/>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<spring:message code="BzComposer.global.company" />
												</td>
												<td colspan="2">
													<form:input path="shcname" disabled="true" style="width:90%;" />
												</td>
												<td colspan="2"></td>
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
													<form:input path="shfirstName" disabled="true"/>
												</td>
												<td>
													<spring:message code="BzComposer.global.lastname" />
												</td>
												<td>
													<form:input path="shlastName" disabled="true"/>
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.global.address1" />
												</td>
												<td colspan="2">
													<form:input path="shaddress1" disabled="true" style="width:90%;" />
												</td>
												<td colspan="2"></td>
                                            </tr>
                                            <tr>
												<td>
													<spring:message code="BzComposer.global.address2" />
												</td>
												<td colspan="2">
													<form:input path="shaddress2" disabled="true" style="width:90%;" />
												</td>
												<td colspan="2"></td>
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
                                                    <form:select path="shcity" id="cityID3" style="width:200px;" disabled="true">
                                                        <form:option value="0"><spring:message code="BzComposer.register.selectcity" /></form:option>
                                                        <c:forEach items="${cityList}" var="currObject">
                                                            <form:option value="${currObject.cityId}">${currObject.cityName}</form:option>
                                                        </c:forEach>
                                                    </form:select>
                                                </td>
												<td></td>
											</tr>
											<tr>
											    <td>
                                                    <div class="lblStateShow"><spring:message code="BzComposer.global.state" /></div>
                                                    <div class="lblProvinceShow"><spring:message code="BzComposer.global.province" /></div>
                                                </td>
                                                <td>
                                                    <form:select path="shstate" id="stateID3" onchange="loadCitiesByStateID(this.value, 3);" style="width:200px;" disabled="true">
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
													<form:select path="shcountry" id="countryID3" onchange="loadStatesByCountryID(this.value, 3);" style="width:200px;" disabled="true">
                                                        <form:option value="0"><spring:message  code="BzComposer.register.selectcounry"/></form:option>
                                                        <c:forEach items="${countryList}" var="currObject">
                                                            <form:option value="${currObject.countryId}">${currObject.countryName}</form:option>
                                                        </c:forEach>
                                                    </form:select>
												</td>
												<td></td>
											</tr>
											<tr>
												<td colspan="5" align="center">
													<button type="button" class="formbutton" name="ClearShippingBtn" onclick="clearShippingAdd();">
													    <spring:message code='BzComposer.updatevendor.clearshippingaddress'/>
													</button>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
								<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="5">
									&nbsp;
								</td>
							</tr>
						</table>
					</div>
                <!-- Manage Address END here -->
			</div>
  		</div>

        <!-- Service information tab -->
  		<div id="service-2" style="display:none;">
  			<div id="content7" class="tabPage">
  				<input type="hidden" name="table_serviceName" value="" />
  				<input type="hidden" name="table_defaultVal" value="0" />
  				<input type="hidden" name="table_size" value="0" />
  				<input type="hidden" name="table_invId" value="" />
  				<input type="hidden" name="table_bal" value="" />
  				<input type="hidden" name="table_serID" value="" />
  				<input type="hidden" name="table_DbDefSer" value="0" />

  					<%
						int index1 = 0;
  					%>
					<c:forEach items="${ServiceList}" var="objList1">
						<%
							index1++;
						%>
						<input type="hidden" value='${objList1.serviceID}' id='<%=index1%>sid' />
						<input type="hidden" value='${objList1.serviceName}' id='<%=index1%>sname' />
						<input type="hidden" value='${objList1.invoiceStyleId}' id='<%=index1%>isid' />
					</c:forEach>
					<input type="hidden" name="ssize" id="sSize" value='<%=index1%>'>
					<%
						int index2=0;
					%>
					<c:forEach items="${InvoiceName}" var="objList2">
						<%
							index2++;
						%>
						<input type="hidden" value='${objList2.invoiceStyleId}' id='<%=index2%>invid' />
						<input type="hidden" value='${objList2.invoiceStyle}' id='<%=index2%>iname' />
					</c:forEach>
					<input type="hidden" name="isize" id="iSize" value='<%=index2%>'>

					<c:if test="${not empty BalenceDetails}">
						<%
							int index = 0;
						%>

							<c:forEach items="${acPayList}" var="curObject">
								<%
									index++;
								%>
								<input type="hidden" value='${objList3.serviceBalance}' id='<%=index%>bal' />
								<input type="hidden" value='${objList3.defaultService}' id='<%=index%>dservice' />
								<input type="hidden" value='${objList3.serviceID}' id='<%=index%>srid' />
								<input type="hidden" value='${objList3.clientVendorID}' id='<%=index%>cvID' />
							</c:forEach>
						<input type="hidden" name="dsize" id="dSize" value='<%=index%>'>
					</c:if>

				<div id="table-negotiations" style="width:100%">
					<input type="hidden" value="1" id="hidn" />
					<table class="tabla-listados" cellspacing="0">
						<thead>
							<tr>
								<th colspan="3">
									<spring:message code="BzComposer.updatevendor.defaultservice" />
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<td>
								<spring:message code="BzComposer.updatevendor.service" />
							</td>
							<td>
								<select name="serviceID">
                                    <c:forEach items="${ServiceList}" var="obj">
                                        <option value="${obj.serviceID}">${obj.serviceName}</option>
                                    </c:forEach>
								</select>
							</td>
							<td>
								<button type="button" class="formbutton" onclick="addToTable(this.form);"><spring:message code="BzComposer.Vendor.Service.Add" /></button>
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="BzComposer.updatevendor.selectedservice" />
							</td>
							<td colspan="2" >&nbsp;</td>
						</tr>
						</tbody>
					</table>
					<table class="tabla-listados" cellspacing="0">
						<thead>
							<tr>
								<th>
									<spring:message code="BzComposer.updatevendor.servicename" />
								</th>
								<th>
									<spring:message code="BzComposer.updatevendor.invoicestyle" />
								</th>
								<th>
									<spring:message code="BzComposer.updatevendor.balance" />
								</th>
								<th>
									<spring:message code="BzComposer.updatevendor.default" />
								</th>
								<th>
									<spring:message code="BzComposer.global.delete" />
								</th>
							</tr>
						</thead>
						<tr id="tr$$">
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<c:if test="${not empty ServiceInfo}">
							<c:if test="${empty ServiceInfo}">
								<input type="hidden" id="sIDSize" value="0" />
							</c:if>
						</c:if>
					</table>
				</div>
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
                                    onclick="displayCalendar(document.VendorForm.expDate,'mm/yyyy',this);">
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
                                    <input type="checkbox" id="chk_useind" name="UseIndividualFinanceCharges" onclick="hideother(this.form);">
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
                                                            <input type="checkbox" id="chk1" name="AssessFinanceChk" disabled="true" value="${vendorDto.fsAssessFinanceCharge}" ${vendorDto.fsAssessFinanceCharge=='on'?'checked':''} />
                                                            <spring:message code="BzComposer.addnewcustomer.assessfinance"/>
                                                        </td>
                                                    </tr>
                                                    <tr><td colspan="4">&nbsp;</td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <input type="checkbox" id="chk2" name="FChargeInvoiceChk" disabled="true" value="${vendorDto.fsMarkFinanceCharge}" ${vendorDto.fsMarkFinanceCharge=='on'?'checked':''} />
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
		</div>
		</div>
		<table cellpadding="0" cellspacing="0" border="0" align="center" style="width: 100%;">
			<script>
				count=0;
				cnt=0;
				var exist= new Array(10);
				for (i=0; i<exist.length; i++){
				exist[i]=-1;
				}
			</script>
			<tr>
				<td align="center">
					<button type="button" class="formbutton" title="New Vendor" onclick="addNewVendorDialog();"><spring:message code="BzComposer.global.new"/></button>
					<button type="button" class="formbutton" title="Save Vendor" onclick="SaveVendorData();"><spring:message code="BzComposer.global.save"/></button>
					<button type="button" class="formbutton" title="Close" onclick="CloseMe();"><spring:message code='BzComposer.global.close'/></button>
					<input type="hidden" name="stname" value="" id="stateId">
				</td>
			</tr>
		</table>
	</div>
</div>
</div>
	<div>
		<input type="hidden" name="tabid" id="tab" value="VONODO" />
		<input type="hidden" name="bst" id="bsst" value="0" />
		<input type="hidden" name="tabid" id="tabid" value="" />
	</div>
</form:form>
</body>
</html>
<script>

function CopyBilladdToShipAdd()
{
	document.VendorForm.shcname.value= document.VendorForm.bscname.value;
	document.VendorForm.shdbaName.value= document.VendorForm.bsdbaName.value;
	document.VendorForm.shfirstName.value= document.VendorForm.bsfirstName.value;
	document.VendorForm.shlastName.value= document.VendorForm.bslastName.value;
	document.VendorForm.shaddress1.value= document.VendorForm.bsaddress1.value;
	document.VendorForm.shaddress2.value= document.VendorForm.bsaddress2.value;
	document.VendorForm.shzipCode.value = document.VendorForm.bszipCode.value;
	document.VendorForm.shprovince.value=document.VendorForm.bsprovince.value;
	document.VendorForm.shcountry.value= document.VendorForm.bscountry.value;
	document.VendorForm.shstate.value = document.VendorForm.bsstate.value;
	document.VendorForm.shcity.value= document.VendorForm.bscity.value;
}
function clearBillingAdd()
{
	document.VendorForm.bscname.value="";
	document.VendorForm.bsdbaName.value="";
	document.VendorForm.bsfirstName.value="";
	document.VendorForm.bslastName.value="";
	document.VendorForm.bsaddress1.value="";
	document.VendorForm.bsaddress2.value="";
	document.VendorForm.bscity.value="0";
	document.VendorForm.bszipCode.value="";
	document.VendorForm.bsprovince.value="";
	document.VendorForm.bscountry.value="0";
	document.VendorForm.bsstate.value="0";
}
function clearShippingAdd(){
	document.VendorForm.shcname.value="";
	document.VendorForm.shdbaName.value="";
	document.VendorForm.shfirstName.value="";
	document.VendorForm.shlastName.value= "";
	document.VendorForm.shaddress1.value= "";
	document.VendorForm.shaddress2.value= "";
	document.VendorForm.shcity.value= "";
	document.VendorForm.shzipCode.value = "";
	document.VendorForm.shprovince.value="";
	document.VendorForm.shcountry.value= "0";
	document.VendorForm.shstate.value= "0";
}

	function trim(inputString) {
	debugger;
	   // Removes the spaces  return from the passed string. 
	   var retValue = inputString;
	   var ch = retValue.substring(0, 1);
	   while (ch == "\n" || ch == "\r" || ch==" " || ch=="\t" ) { 
    	  retValue = retValue.substring(1, retValue.length);
	      ch = retValue.substring(0, 1);
	   }
	   return retValue; 
	}


function SaveVendorData(){
	debugger
	if(trim(document.getElementById('firstName').value)==""){

		document.getElementById('firstName').focus();
		return showblankfirstnamevalidationdialog();
	}
	else if(trim(document.getElementById('lastName').value)==""){

		document.getElementById('lastName').focus();
		return showblanklastnamevalidationdialog();
	}
	else if(trim(document.getElementById('cname').value)==""){

		document.getElementById('cname').focus();
		return showblanknamevalidationdialog();
	}
	else if(trim(document.getElementById('address1').value)==""  ){

		document.getElementById('address1').focus();
		return showblankaddress1validationdialog();

	}
	else if(trim(document.getElementById('cityID').value)==""){

		document.getElementById('cityID').focus();
		return showblanklcityvalidationdialog();
	}
	else if(trim(document.getElementById('zipCode').value)==""){

		document.getElementById('zipCode').focus();
		return showblankzipcodevalidationdialog();
	}
	else {
		if(validate()){
			document.forms["frmAddVendor"].action = "Vendor?tabid=AOVODO";
			document.forms["frmAddVendor"].submit();
		}
	}
}

function hideother(form){
	chbox = document.getElementById('chk_useind');
	if(chbox.checked){
		document.VendorForm.annualIntrestRate.disabled=false;
		document.VendorForm.minFCharges.disabled=false;
		document.VendorForm.gracePrd.disabled=false;
		document.getElementById('chk1').disabled=false;
		document.getElementById('chk2').disabled=false;
	}
	else{
		document.VendorForm.annualIntrestRate.disabled=true;
		document.VendorForm.minFCharges.disabled=true;
		document.VendorForm.gracePrd.disabled=true;
		document.getElementById('chk1').disabled=true;
		document.getElementById('chk2').disabled=true;
	}
}

function hidebsaddress(form){
	chbox=document.getElementById('chk_setaddress');
	if(!chbox.checked)
	{		
		document.VendorForm.bscname.disabled=false;
		document.VendorForm.bsdbaName.disabled=false;
		document.VendorForm.bsfirstName.disabled=false;
		document.VendorForm.bslastName.disabled=false;	
		document.VendorForm.bsaddress1.disabled=false;
		document.VendorForm.bsaddress2.disabled=false;
		document.VendorForm.bscity.disabled=false;
		document.VendorForm.bszipCode.disabled=false;
		document.VendorForm.bsprovince.disabled=false;
		document.VendorForm.bsstate.disabled=false;
		document.VendorForm.bscountry.disabled=false;
				
		document.VendorForm.shcname.disabled=false;
		document.VendorForm.shdbaName.disabled=false;
		document.VendorForm.shfirstName.disabled=false;
		document.VendorForm.shlastName.disabled=false;	
		document.VendorForm.shaddress1.disabled=false;
		document.VendorForm.shaddress2.disabled=false;
		document.VendorForm.shcity.disabled=false;
		document.VendorForm.shzipCode.disabled=false;
		document.VendorForm.shprovince.disabled=false;
		document.VendorForm.shstate.disabled=false;
	    document.VendorForm.setdefaultbs.value="0";
	    document.VendorForm.shcountry.disabled=false;
	}
	else
	{
		document.VendorForm.bscname.disabled=true;
		document.VendorForm.bsdbaName.disabled=false;
		document.VendorForm.bsfirstName.disabled=true;
		document.VendorForm.bslastName.disabled=true;	
		document.VendorForm.bsaddress1.disabled=true;
		document.VendorForm.bsaddress2.disabled=true;
		document.VendorForm.bscity.disabled=true;
		document.VendorForm.bszipCode.disabled=true;
		document.VendorForm.bsprovince.disabled=true;
		document.VendorForm.bsstate.disabled=true;
		document.VendorForm.bscountry.disabled=true;
		
		document.VendorForm.shcname.disabled=true;
		document.VendorForm.shdbaName.disabled=true;
		document.VendorForm.shfirstName.disabled=true;
		document.VendorForm.shlastName.disabled=true;	
		document.VendorForm.shaddress1.disabled=true;
		document.VendorForm.shaddress2.disabled=true;
		document.VendorForm.shcity.disabled=true;
		document.VendorForm.shzipCode.disabled=true;
		document.VendorForm.shprovince.disabled=true;
		document.VendorForm.shstate.disabled=true;
		document.VendorForm.shcountry.disabled=true;
	    document.VendorForm.setdefaultbs.value="";
	    CopyBilladdToShipAdd();
	}
}

function addToTable(form){
	debugger;
	flag=0;
	flag1=0;
	invID=form.serviceID.value;
	SIDSize=document.getElementById('sIDSize').value;
	SLSize=document.getElementById('sSize').value;
	InvSize=document.getElementById('iSize').value;
	DetailSize=document.getElementById('dSize').value;
	
	var serviceName;
	var InvoiceStyle;
	var ServiceBalance="0.0";
	var DefaultService;
		for(t=0; t<exist.length; t++){	
			if(invID==exist[t]){				
				flag=1;
				break;
			}
		}
		if(flag==1)
		{

			return showservicevalidationdialog();
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
				document.VendorForm.table_size.value++;
				document.VendorForm.table_serID.value+=invID+";";
				document.VendorForm.table_serviceName.value+=serviceName+";";
				document.VendorForm.table_bal.value+=ServiceBalance+";";
				if(InvoiceStyle==""){
					form.table_invId.value+="0;";
				}
				else{
					for(p=0;p<InvSize;p++) {
						invoiceName=document.getElementById(p+'iname').value;
						if(invoiceName==InvoiceStyle) {
							document.VendorForm.table_invId.value+=document.getElementById(p+'invid').value+";";
						}				
					}
				}
				count = ((count)/1 + 1);
				document.getElementById('hidn').value=((hidn_val)/1 + 1);	
			}
		name=document.VendorForm.table_serviceName.value;
}
function setDefault(invID1,form){
	document.VendorForm.table_defaultVal.value=invID1;
}
function setDefaultToDb(invID1,form){
	document.VendorForm.table_DbDefSer.value=invID1;
}
function removeFromTable(idV){

	var str;
	var trid;
	trid="row"+idV;
	for(i=0; i<exist.length; i++){
		if(idV==exist[i]){

			var ttt=document.getElementById(trid);
				ttt.parentNode.removeChild(ttt);
			exist[i]=-1;			
			cnt--;
			removeStringValues(idV);
			break;
		}
	}
}
function removeStringValues(key){
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

	document.forms[0].table_serID.value=str;
	document.forms[0].table_bal.value=str2;
	document.forms[0].table_invId.value=str3;
}

function newValues(){
	//billing addressres/*
		document.VendorForm.bscname.value="";
		document.VendorForm.bsfirstName.value="";
		document.VendorForm.bslastName.value="";
		document.VendorForm.bsaddress1.value="";
		document.VendorForm.bsaddress2.value="";
		document.VendorForm.bscity.value="0";
		document.VendorForm.bszipCode.value="";
		document.VendorForm.bsprovince.value="";
		document.VendorForm.bscountry.value="0";
	
	//shipping addresses
		document.VendorForm.shcname.value="";
		document.VendorForm.shfirstName.value="";
		document.VendorForm.shlastName.value= "";
		document.VendorForm.shaddress1.value= "";
		document.VendorForm.shaddress2.value= "";
		document.VendorForm.shcity.value= "0";
		document.VendorForm.shzipCode.value = "";
		document.VendorForm.shprovince.value="";
		document.VendorForm.shcountry.value= "0";
	
	//genaral Tab
		document.VendorForm.firstName.value="";
		document.VendorForm.lastName.value="";
		document.VendorForm.address1.value="";
		document.VendorForm.address2.value="";
		document.VendorForm.city.value="0";
		document.VendorForm.state.value="0";
		document.VendorForm.zipCode.value="";
		document.VendorForm.phone.value="";
		document.VendorForm.cellPhone.value="";
		document.VendorForm.fax.value="";
		document.VendorForm.email.value="";
		document.VendorForm.province.value="";
		document.VendorForm.homePage.value="www.";
		document.VendorForm.type.value="0";
		document.VendorForm.texID.value="";
		document.VendorForm.openingUB.value="";
		document.VendorForm.extCredit.value="";
		document.VendorForm.memo.value="";

		document.VendorForm.cname.value="";
		document.VendorForm.chktax.checked=false;
		document.VendorForm.chk_alsovendor.checked=false;
	
	// Services Tab
		document.VendorForm.serviceID.value="1";
		document.VendorForm.table_defaultVal.value="0";
		document.getElementById('hidn').value="1";
		count=0;
		for(index=1;index<=4;index++){
			removeFromTable(index);
		}
		for (i=0; i<exist.length; i++){
				exist[i]=-1;
		}
		document.VendorForm.state.value=0;
		document.VendorForm.bsstate.value=0;
		document.VendorForm.shstate.value=0;

        $("#countryID").val(231).change();
        $("#countryID2").val(231).change();
        $("#countryID3").val(231).change();

}

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

function CloseMe(){
	window.close();
}
function validate() {
  	var email = document.getElementById('email').value
  	var mail =String(email);
  	var pattern=/^[_0-9a-zA-z]+(\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\.[A-Za-z]+)+$/;
  	if(email==""){
  	}
    else if (!pattern.test(email)) {
        document.VendorForm.email.focus();
        return showInvalidEmailDialog();
        return false;
    }

   	return true;
  }

  function showInvalidEmailDialog()
  {
	  debugger;
		event.preventDefault();
		$("#showInvalidEmailDialog").dialog({
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

  function showblanknamevalidationdialog()
  {
	  debugger;
		event.preventDefault();
		$("#showblanknamevalidationdialog").dialog({
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

  function showblankfirstnamevalidationdialog()
  {
	  debugger;
		event.preventDefault();
		$("#showblankfirstnamevalidationdialog").dialog({
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

  function showblanklastnamevalidationdialog()
  {
	  debugger;
		event.preventDefault();
		$("#showblanklastnamevalidationdialog").dialog({
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

  function showblankaddress1validationdialog()
  {
	  debugger;
		event.preventDefault();
		$("#showblankaddress1validationdialog").dialog({
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

  function showblanklcityvalidationdialog()
  {
	  debugger;
		event.preventDefault();
		$("#showblanklcityvalidationdialog").dialog({
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

  function showblankzipcodevalidationdialog()
  {
	  debugger;
		event.preventDefault();
		$("#showblankzipcodevalidationdialog").dialog({
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

  function addNewVendorDialog()
  {
	  debugger;
	  event.preventDefault();
		$("#addNewVendorDialog").dialog({
	    	resizable: false,
	        height: 200,
	        width: 550,
	        modal: true,
	        buttons: {
	            "<spring:message code='BzComposer.global.ok'/>": function () {
	            	debugger
	                $(this).dialog("close");
	            	newValues();
	            },
	            "<spring:message code='BzComposer.global.cancel'/>": function () {
	                $(this).dialog("close");
	            }
	        }
	    });
	    return false;
  }

  function showservicevalidationdialog()
  {
	  debugger;
		event.preventDefault();
		$("#showservicevalidationdialog").dialog({
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
</script>
<!-- Dialog used in add new vendor page -->
<div id="showInvalidEmailDialog" style="display:none;">
	<p><spring:message code="BzComposer.NewCustomer.Email.Validation" /></p>
</div>
<div id="showblanknamevalidationdialog" style="display:none;">
	<p><spring:message code="BzComposer.NewCustomer.Name.Validation" /></p>
</div>
<div id="showblankfirstnamevalidationdialog" style="display:none;">
	<p><spring:message code="BzComposer.NewCustomer.FirstName.Validation" /></p>
</div>
<div id="showblanklastnamevalidationdialog" style="display:none;">
	<p><spring:message code="BzComposer.NewCustomer.LastName.Validation" /></p>
</div>
<div id="showblanklcityvalidationdialog" style="display:none;">
	<p><spring:message code="BzComposer.NewCustomer.City.Validation" /></p>
</div>
<div id="showblankzipcodevalidationdialog" style="display:none;">
	<p><spring:message code="BzComposer.NewCustomer.ZipCode.Validation" /></p>
</div>
<div id="showblankaddress1validationdialog" style="display:none;">
	<p><spring:message code="BzComposer.NewCustomer.Adderss1.Validation" /></p>
</div>
<div id="addNewVendorDialog" style="display:none;">
	<p><spring:message code="BzComposer.Vendor.AddNewVendor.ClearValidation"/></p>
</div>
<div id="showservicevalidationdialog" style="display:none;">
	<p><spring:message code="BzComposer.NewCustomer.Service.Validation" /></p>
</div>