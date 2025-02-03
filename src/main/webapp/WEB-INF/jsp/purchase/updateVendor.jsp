<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--<%@ page errorPage="/include/sessionExpired.jsp"%>--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<title><spring:message code="BzComposer.updatevendortitle" /></title>
<link href="/WEB-INF/jsp/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="/WEB-INF/jsp/tableStyle/tab/jquery-ui.js"></script>
<style>
.errorClass { border: solid 1px #FF0000; }
.redColor{color: red;}
.height150{ height: 150px; }
.card-text{ font-size: 12px! important; color: #6C7590! important; padding: 0px; margin: 5px; }
.defaultCard{ margin-top:10px;background: #C3C5CA;border-radius: 5px;padding:5px; }
.mTop10{ margin-top:10px; }
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
<body onload="init();">
<form:form method="post" id="frmAddVendor" name="VendorForm" modelAttribute="vendorDetails1" >
	<form:hidden path="custId" value='<%= request.getParameter("cvId")%>'/>
	<div id="cos">
		<div class="statusquo ok">
			<div id="hoja">
				<div id="blanquito">
					<div id="padding">
						<div>
			<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
				<spring:message code="BzComposer.updatevendor.editvendorinformation"/>
			</span>
						</div>
						<!-- begin Contents -->
						<!-- add the code for tab here -->
						<div>
							<c:if test="${not empty actionMsg}">
                                <span class="msgstyle">${actionMsg}</span>
                                <% session.removeAttribute("actionMsg"); %>
							</c:if>
						</div>
						<div>
							<div id="tabs" style="min-height:500px;width: 1300px;">
								<ul>
									<li><a href="#General-1"><spring:message code="BzComposer.updatevendor.tabs.general" /></a></li>
									<!-- <li><a href="#service-3"><spring:message code="BzComposer.updatevendor.tabs.service" /></a></li> -->
									<li><a href="#payment-2"><spring:message code="BzComposer.addnewcustomer.tabs.paymentInformation" /></a></li>
                                    <li><a href="#finance-4"><spring:message code="BzComposer.updatevendor.tabs.financecharges" /></a></li>
									<li><a href="#Transactional-5"><spring:message code="BzComposer.updatecustomer.tabs.transactionhistory" /></a></li>
								</ul>
								<div id="General-1">
									<div id="content1" class="tabPage">
										<!-- add here the content of first tab -->
										<div id="table-negotiations">
											<table class="tabla-listados" cellspacing="0">
												<thead>
												<tr>
													<th colspan="7" style="font-size:14px;">
														<spring:message code="BzComposer.updatevendor.vendorinformation"/>
													</th>
												</tr>
												</thead>
												<tbody>
												<tr>
												    <td>
                                                        <spring:message code="BzComposer.global.customerid" />
                                                    </td>
                                                    <td>
                                                        <form:input path="clientVendorID" readonly="true" />
                                                    </td>
                                                    <td colspan="5">&nbsp;</td>
                                                </tr>
                                                <tr>
												    <td>
                                                        <spring:message code="BzComposer.global.titlename" />
                                                    </td>
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
														<span class="inputHighlighted">
                                                            <spring:message code="BzComposer.CompulsoryField.Validation" />
                                                        </span>
													</td>
													<td colspan="3">
														<form:input type="text" path="address1" style="width:90%;" />
													</td>
													<td colspan="3">&nbsp;</td>
                                                </tr>
                                                <tr>
													<td>
														<spring:message code="BzComposer.global.address2" />
													</td>
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
                                                        <form:input path="zipCode" onfocusout="loadAddressDetailsByZipcode(this.value, 1)" onkeypress="return numbersonly(event,this.value)" />
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
														<form:input path="phone" maxlength="16" onkeypress="return numbersonly(event,this.value)" onchange="validateUSAPhoneNumber(this, true);" />
													</td>
													<td>
														<spring:message code="BzComposer.global.mobileNumber" />
													</td>
													<td colspan="2">
														<form:input path="cellPhone" maxlength="16" onkeypress="return numbersonly(event,this.value)" onchange="validateUSAPhoneNumber(this, true);" />
														<form:checkbox path="isMobilePhoneNumber" onchange="copyPhoneNumber(this.form);" />
														(<spring:message code="BzComposer.global.isPhoneNumber" />)
													</td>
												    <td>
                                                        <spring:message code="BzComposer.global.fax" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <form:input type="text" path="fax" onkeypress="return numbersonly(event,this.value);" onchange="validateUSAPhoneNumber(this, true);" />
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
													<td>
														<spring:message code="BzComposer.global.email" />
														<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
													</td>
													<td colspan="3">
														<form:input type="text" path="email" size="40" />
													</td>
													<td>
                                                        <spring:message code="BzComposer.global.homepage" />
                                                    </td>
                                                    <td colspan="2">
                                                        <form:input type="text" path="homePage" size="40" />
                                                    </td>
                                                    <td colspan="3">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size:12px;">
                                                        <spring:message code="BzComposer.global.dateadded" />
                                                    </td>
                                                    <td style="font-size:12px;">
                                                        <form:input type="text" path="dateAdded" readonly="true" />
                                                        <img src="${pageContext.request.contextPath}/images/cal.gif"
                                                        onclick="displayCalendar(document.VendorForm.dateAdded,'mm-dd-yyyy',this);">
                                                    </td>
                                                    <td>
                                                        <spring:message code="BzComposer.global.terminated" />
                                                    </td>
                                                    <td>
                                                        <form:checkbox path="terminated" />
                                                    </td>
                                                    <td>
                                                        <spring:message code="BzComposer.global.terminatedDate" />
                                                    </td>
                                                    <td>
                                                        <form:input path="terminatedDate" readonly="true" />
                                                        <img src="${pageContext.request.contextPath}/images/cal.gif"
                                                            onclick="displayCalendar(document.VendorForm.terminatedDate,'mm-dd-yyyy',this);">
                                                    </td>
                                                </tr>
                                                <tr>
													<td>
														<spring:message code="BzComposer.Companyinformation.Type" />
													</td>
													<td>
														<form:select path="type">
															<option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
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
														<c:if test="${vendorDetails1.taxAble == '1'}">
															<input value="on" type="checkbox" name="isTaxable" checked="checked" />
														</c:if>
														<c:if test="${vendorDetails1.taxAble != '1'}">
															<input value="on" type="checkbox" name="isTaxable" />
														</c:if>
														<spring:message code="BzComposer.global.istaxable" />
													</td>
													<td>
														<c:if test="${vendorDetails1.isclient == '1'}">
															<input value="on" type="checkbox" name="isAlsoClient" checked="checked" />
														</c:if>
														<c:if test="${vendorDetails1.isclient != '1'}">
															<input value="on" type="checkbox" name="isAlsoClient" />
														</c:if>
														<spring:message code="BzComposer.global.isalsoclient" />
													</td>
													<td colspan="4">&nbsp;</td>
												</tr>
												<tr>
												    <td>
                                                        <spring:message code="BzComposer.global.oppeningunpaidbalance" />
                                                    </td>
                                                    <td>
                                                        <form:input type="text" path="openingUB" onkeypress="return numbersonly(event,this.value);" />
                                                    </td>
													<td>
														<spring:message code="BzComposer.global.existingcredits" />
													</td>
													<td>
														<form:input type="text" path="extCredit" onkeypress="return numbersonly(event,this.value);" />
													</td>
													<td colspan="6">&nbsp;</td>
											    </tr>
                                                <tr>
													<td>
														<spring:message code="BzComposer.global.memo" />
													</td>
													<td colspan="5">
														<form:textarea path="memo" style="width:90%;" />
													</td>
													<td colspan="4">&nbsp;</td>
												</tr>
												</tbody>
											</table>
										</div>
										<!-- Account info Start Here -->
                                        <div id="table-negotiations" style="width:100%">
                                            <table class="tabla-listados" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th colspan="8" style="font-size:14px;">
                                                        <spring:message code="BzComposer.updatevendor.prefrenceinformation" />
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
                                                            <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                                                            <form:options items="${TermList}" itemValue="term" itemLabel="name"/>
                                                        </form:select>
                                                    </td>
                                                    <td>
                                                        <spring:message code="BzComposer.global.paymentmethod" />
                                                    </td>
                                                    <td>
                                                        <form:select path="paymentType" style="width:150px;">
                                                            <option value="0">
                                                                <spring:message code="BzComposer.ComboBox.Select" />
                                                            </option>
                                                            <form:options items="${PaymentList}" itemValue="id" itemLabel="typeName" />
                                                        </form:select>
                                                    </td>
                                                    <td colspan="4">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <spring:message code="BzComposer.global.representative" />
                                                    </td>
                                                    <td>
                                                        <form:select path="rep" style="width:150px;">
                                                            <option value="0">
                                                                <spring:message code="BzComposer.ComboBox.Select" />
                                                            </option>
                                                            <form:options items="${RepList}" itemValue="value" itemLabel="label"/>
                                                        </form:select>
                                                    </td>
                                                    <td>
                                                        <spring:message code="BzComposer.global.shippingvia" />
                                                    </td>
                                                    <td>
                                                        <form:select path="shipping" style="width:150px;">
                                                            <option value="0">
                                                                <spring:message code="BzComposer.ComboBox.Select" />
                                                            </option>
                                                            <form:options items="${ShipCarrierList}" itemValue="value" itemLabel="label"/>
                                                        </form:select>
                                                    </td>
                                                    <td colspan="4">&nbsp;</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!-- Manage Address Start here -->
                                        <div id="table-negotiations">
                                            <table align="center" style="width: 100%" class="tabla-listados">
                                                <thead>
                                                <tr>
                                                    <th colspan="5" style="font-size:14px;">
                                                        <spring:message code="BzComposer.updatevendor.manageaddresses"/>
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tr>
                                                    <td>
                                                        <table class="tabla-listados" cellspacing="0" width="300" align="left">
                                                            <thead>
                                                            <tr>
                                                                <th colspan="5" style="font-size:14px;">
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
                                                                    <form:input type="text" path="bscname" style="width:90%;" />
                                                                </td>
                                                                <td colspan="2">&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td><spring:message code="BzComposer.register.dbaName" /></td>
                                                                <td>
                                                                    <form:input path="bsdbaName" />
                                                                </td>
                                                                <td colspan="3">&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <spring:message code="BzComposer.global.firstname" />
                                                                </td>
                                                                <td>
                                                                    <form:input type="text" path="bsfirstName" />
                                                                </td>
                                                                <td>
                                                                    <spring:message code="BzComposer.global.lastname" />
                                                                </td>
                                                                <td>
                                                                    <form:input type="text" path="bslastName" />
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <spring:message code="BzComposer.global.address1" />
                                                                </td>
                                                                <td colspan="2">
                                                                    <form:input type="text" path="bsaddress1" style="width:90%;" />
                                                                </td>
                                                                <td colspan="2">&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <spring:message code="BzComposer.global.address2" />
                                                                </td>
                                                                <td colspan="2">
                                                                    <form:input type="text" path="bsaddress2" style="width:90%;" />
                                                                </td>
                                                                <td colspan="2">&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <div class="lblZipcodeShow"><spring:message code="BzComposer.global.zipcode" /></div>
                                                                    <div class="lblPostalcodeShow"><spring:message code="BzComposer.global.postalcodes" /></div>
                                                                </td>
                                                                <td>
                                                                    <form:input path="bszipCode" onfocusout="loadAddressDetailsByZipcode(this.value, 2)" onkeypress="return numbersonly(event,this.value)" />
                                                                </td>
                                                                <td>
                                                                    <spring:message code="BzComposer.global.city" />
                                                                </td>
                                                                <td>
                                                                    <form:select path="bscity" id="cityID2" style="width:200px;">
                                                                        <form:option value="0"><spring:message code="BzComposer.register.selectcity" /></form:option>
                                                                        <c:forEach items="${cityList2}" var="currObject">
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
                                                                        <c:forEach items="${stateList2}" var="currObject">
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
                                                                    <input type="button" class="formbutton" name="ClearBillingBtn" value="<spring:message code='BzComposer.updatevendor.clearbillingaddress'/>"
                                                                           onclick="clearBillingAdd();" title="Clear Billing Address">
                                                                </td>
                                                            </tr>
                                                            </tbody>	<!-- Added on 25-06-2019 -->
                                                        </table>
                                                    </td>				<!-- Added on 25-06-2019 -->
                                                    <td style="font-size:1em;vertical-align: inherit;">
                                                        <input type="button" name="CopyBtn" class="formbutton" onclick="CopyBilladdToShipAdd();" value="<spring:message code='BzComposer.updatevendor.copyto'/>" title="Copy Address">
                                                    </td>
                                                    <td>
                                                        <table class="tabla-listados" cellspacing="0" width="300" align="left">
                                                            <tbody>
                                                            <thead>
                                                            <tr>
                                                                <th colspan="5" style="font-size:14px;">
                                                                    <spring:message code="BzComposer.updatevendor.shippingaddress"/>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                            <tr>
                                                                <td>
                                                                    <spring:message code="BzComposer.global.company" />
                                                                </td>
                                                                <td colspan="2">
                                                                    <form:input type="text" path="shcname" style="width:90%;" />
                                                                </td>
                                                                <td colspan="2">&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td><spring:message code="BzComposer.register.dbaName" /></td>
                                                                <td>
                                                                    <form:input path="shdbaName" />
                                                                </td>
                                                                <td colspan="3">&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <spring:message code="BzComposer.global.firstname" />
                                                                </td>
                                                                <td>
                                                                    <form:input type="text" path="shfirstName" />
                                                                </td>
                                                                <td>
                                                                    <spring:message code="BzComposer.global.lastname" />
                                                                </td>
                                                                <td>
                                                                    <form:input type="text" path="shlastName" />
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <spring:message code="BzComposer.global.address1" />
                                                                </td>
                                                                <td colspan="2">
                                                                    <form:input type="text" path="shaddress1" style="width:90%;" />
                                                                </td>
                                                                <td colspan="2">&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <spring:message code="BzComposer.global.address2" />
                                                                </td>
                                                                <td colspan="2">
                                                                    <form:input type="text" path="shaddress2" style="width:90%;" />
                                                                </td>
                                                                <td colspan="2">&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <div class="lblZipcodeShow"><spring:message code="BzComposer.global.zipcode" /></div>
                                                                    <div class="lblPostalcodeShow"><spring:message code="BzComposer.global.postalcodes" /></div>
                                                                </td>
                                                                <td>
                                                                    <form:input path="shzipCode" onfocusout="loadAddressDetailsByZipcode(this.value, 3)" onkeypress="return numbersonly(event,this.value)" />
                                                                </td>
                                                                <td>
                                                                    <spring:message code="BzComposer.global.city" />
                                                                </td>
                                                                <td>
                                                                    <form:select path="shcity" id="cityID3" style="width:200px;">
                                                                        <form:option value="0"><spring:message code="BzComposer.register.selectcity" /></form:option>
                                                                        <c:forEach items="${cityList3}" var="currObject">
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
                                                                        <c:forEach items="${stateList3}" var="currObject">
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
                                                                    <button type="button" class="formbutton" name="ClearShippingBtn" onclick="clearShippingAdd();">
                                                                        <spring:message code='BzComposer.updatevendor.clearshippingaddress'/>
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td colspan="2">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="5">&nbsp;</td>
                                                </tr>
                                            </table>
                                        </div>
									</div>
								</div>

								<div id="service-3" style="display:none;">
									<div id="content7" class="tabPage">
										<!------------------------------------------ add here the content of services tab ----------------------------------------------------->
										<form:input type="hidden" path="table_serviceName" value="" />
										<form:input type="hidden" path="table_defaultVal" value="0" />
										<form:input type="hidden" path="table_size" value="0" />
										<form:input type="hidden" path="table_invId" value="" />
										<form:input type="hidden" path="table_bal" value="" />
										<form:input type="hidden" path="table_serID" value="" />
										<form:input type="hidden" path="table_DbDefSer" value="0" />

										<%--<bean:size name="ServiceList" id="SList" /> --%>
										<input type="hidden" name="ssize" id="sSize" value='${SList}'>
										<c:forEach items="${ServiceList}" var="objList1">
											<input type="hidden" value='${objList1.serviceID}' id='${index}sid' />
											<input type="hidden" value='${objList1.serviceName}' id='${index}sname' />
											<input type="hidden" value='${objList1.invoiceStyleId}' id='${index}isid' />
										</c:forEach>

										<%--<bean:size name="InvoiceName" id="IName" /> --%>
										<input type="hidden" name="isize" id="iSize" value='${IName}'>
										<c:forEach items="${InvoiceName}" var="objList2">
											<input type="hidden" value='${objList2.invoiceStyleId}' id='${index}invid' />
											<input type="hidden" value='${objList2.invoiceStyle}' id='${index}iname' />
										</c:forEach>
										<%--<bean:size name="BalenceDetails" id="BDetails" />--%>
										<input type="hidden" name="dsize" id="dSize" value='${BDetails}'>
										<c:forEach items="${BalenceDetails}" var="objList3">
											<input type="hidden" value='${objList3.serviceBalance}' id='${index}bal' />
											<input type="hidden" value='${objList3.defaultService}' id='${index}dservice' />
											<input type="hidden" value='${objList3.serviceID}' id='${index}srid' />
											<input type="hidden" value='${objList3.clientVendorID}' id='${index}cvID' />
										</c:forEach>

										<div id="table-negotiations" style="width:100%">
											<input type="hidden" value="1" id="hidn" />
											<table class="tabla-listados" cellspacing="0">
												<tbody>
												<thead>
												<tr>
													<th colspan="4" style="font-size:14px;">
														<spring:message code="BzComposer.updatevendor.defaultservice" />
													</th>
												</tr>
												<td id="sername"></td>
												</thead>
												<tr>
													<td>
														<spring:message code="BzComposer.updatevendor.service" />
													</td>
													<td>
														<form:select path="serviceID" style="width:215px;">
															<c:forEach items="${ServiceList}" var="obj">
																<option value="${obj.serviceID}">${obj.serviceName}</option>
															</c:forEach>
														</form:select>
													</td>
													<td>
														<input type="button" class="formbutton" name="add" value="<spring:message code='BzComposer.global.add'/>" onclick="addToTable(this.form);" />
													</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>
														<spring:message code="BzComposer.updatevendor.selectedservice" />
													</td>
													<td colspan="3">&nbsp;</td>
												</tr>
												</tbody>
											</table>
											<div id="table-negotiations">
												<table class="tabla-listados" cellspacing="0">
													<thead>
													<tr>
														<th style="font-size:14px;">
															<spring:message code="BzComposer.updatevendor.servicename" />
														</th>
														<th style="font-size:14px;">
															<spring:message code="BzComposer.updatevendor.invoicestyle" />
														</th>
														<th style="font-size:14px;">
															<spring:message code="BzComposer.updatevendor.balance" />
														</th>
														<th style="font-size:14px;">
															<spring:message code="BzComposer.updatevendor.default" />
														</th>
														<th style="font-size:14px;">
															<spring:message code="BzComposer.global.delete" />
														</th>
													</tr>
													</thead>
													<tr id="tr$$">
														<td align="center"></td>
														<td align="center"></td>
														<td align="center"></td>
														<td align="center"></td>
														<td align="center"></td>
													</tr>
													<c:if test="${not empty ServiceInfo}">
														<script type="text/javascript">
															default_sid=0;
															default_sid=${DefaultService};
														</script>
														<c:if test="${not empty DefaultService}">
															<script type="text/javascript">
																default_sid=${DefaultService};
															</script>
														</c:if>
														<c:if test="${not empty DefaultService}">
															<script type="text/javascript">
																default_sid=0;
															</script>
														</c:if>
														<c:if test="${not empty ServiceInfo}">

															<%--<bean:size id="ServiceInfoSize" name="ServiceInfo" />--%>
															<input type="hidden" name="serviceSize" id="sIDSize" value='${ServiceInfoSize}' />
															<c:forEach items="${ServiceInfo}" var="objList">
																<input type="hidden" id='${ndx}_ser' value='${objList.serviceID}/>'/>
																<tr id='row${objList.serviceID}'>
																		<%--<!-- id='tr${ndx}' -->--%>
																	<td>
																			${objList.serviceName}
																	</td>
																	<td>
																			${objList.invoiceStyle}
																	</td>
																	<td align="center">
																			${objList.serviceBalance}
																	</td>
																	<c:if test="${objList.defaultService}">
																		<td align="left">
																			<input type="radio" name=defaultService id="default"
																				   value='${objList.defaultService}'
																				   onclick="setDefault('${objList.serviceID}',this.form);"
																				   checked="checked" />
																			<script type="text/javascript">
																				default_sid=${objList.serviceID}
																						document.getElementById("sername").innerHTML = '${objList.serviceName}';
																			</script>
																		</td>
																	</c:if>
																	<c:if test="${not empty objList.defaultService}" > <%--value='1'--%>
																		<td>
																			<input type="radio" name=defaultService id="default"
																				   value='${objList.defaultService}'
																				   onclick="setDefault('${objList.serviceID}',this.form);" />
																		</td>
																	</c:if>
																	<td align="left">
																		<img src="/WEB-INF/jsp/images/delete.png" alt="Deletable"
																			 onclick="removeFromTable('${objList.serviceID}');" />
																			<%-- <!-- onclick="removeFromTable('${ndx}');" -->--%>
																	</td>

																	<td align="center">
																		<input type="hidden" value='${objList.serviceID}'
																			   id='${ndx}id' />
																	</td>
																</tr>
																<script>
																	var temp;
																	flagy = 0;
																	var defService="0";
																	exist[cnt++]='${objList.serviceID}';
																	invStyleName='${objList.invoiceStyle}';
																	InvSize=document.getElementById('iSize').value;
																	for (i=0; i<InvSize; i++){
																		temp=document.getElementById(i+'iname').value;
																		if (temp==invStyleName){							//invstyle found...
																			temp=document.getElementById(i+'invid').value;
																			//defService=document.getElementById(i+'dservice').value;
																			flagy =1;
																			break;
																		}
																	}

																	if(flagy==0 && invStyleName==""){
																		temp="0";
																	}

																	document.forms[0].table_serID.value+='${objList.serviceID};';
																	document.forms[0].table_bal.value+='${objList.serviceBalance};';
																	document.forms[0].table_invId.value+=temp+";";
																	document.forms[0].table_defaultVal.value=defService;
																</script>
															</c:forEach>
														</c:if>
													</c:if>
													<c:if test="${not empty ServiceInfo}">
														<input type="hidden" id="sIDSize" value="0" />
													</c:if>
												</table>
											</div>

										</div>
									</div>

								</div>

								<script>
									count=0;
									cnt=0;

									var exist= new Array();
									for (i=0; i<100; i++){
										exist[i]=-1;
									}

								</script>

								<!-- PAyment information tab -->
                                <div id="payment-2">
                                    <div id="content2" class="tabPage">
                                        <div id="table-negotiations" style="width:100%">
                                            <table class="tabla-listados" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th colspan="5">
                                                            <spring:message code="BzComposer.addnewcustomer.creditcardinfo" />
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <!-- <tr>
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
                                                    </tr> -->
                                                    <tr>
                                                        <td colspan="5">
                                                            <div class="container text-center">
                                                                <div class="row text-left" id="customerCards">
                                                                    <c:forEach items="${vendorDetails1.customerCards}" var="objList">
                                                                    <div class="col-sm-4">
                                                                        <div class="card height150">
                                                                            <div class="card-body">
                                                                            <h6 class="card-subtitle">${objList.ccTypeName}</h6>
                                                                            <p class="card-text mTop10"><spring:message code="BzComposer.global.expdate" />: <span style="color: #6C7590! important;" id="expDate1">${objList.expDate}</span></p>
                                                                            <p class="card-text"><spring:message code="BzComposer.global.cardnumber"/>: <span style="color: #6C7590! important;" id="cardNo1">${objList.cardNo}</span></p>
                                                                            <p class="card-text"><spring:message code="BzComposer.addnewcustomer.billingaddress"/>: <span style="color: #6C7590! important;" id="cardBillAddress1">${objList.cardBillAddress}</span></p>
                                                                            <c:choose>
                                                                            <c:when test="${objList.cardDefault}">
                                                                                <p class="card-text float-right defaultCard"><strong><spring:message code="BzComposer.global.default"/></strong></p>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <p class="card-text float-right">
                                                                                    <a href="Customer?tabid=makeCustomerCardDefault4&cvId=${objList.clientVendorID}&cardID=${objList.cardID}" style="color: #007bff;">
                                                                                        <strong><spring:message code="BzComposer.global.default"/></strong></a>
                                                                                </p>
                                                                            </c:otherwise>
                                                                            </c:choose>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    </c:forEach>
                                                                    <div class="col-sm-4">
                                                                        <div class="card height150">
                                                                            <div class="card-body">
                                                                                <a href="#" onclick="showAddPaymentMethodDialog();">
                                                                                <h6 class="card-subtitle text-center" style="margin-top:40px;color: #05A9C5 !important;"><spring:message code="BzComposer.banking.addPaymentMethod"/></h6>
                                                                                </a>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
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

								<!--  Transactional History-->
								<div id="Transactional-5">
									<div id="content5" class="tabPage">
										<!-- add here the content of sixth tab -->
										<div id="table-negotiations" >
											<table class="tabla-listados" cellspacing="0">
												<thead>
												<tr>
													<th colspan="4" style="font-size:14px;">
														<spring:message code="BzComposer.updatevendor.displayoption" />
													</th>
												</tr>
												</thead>
												<tr>
													<td colspan="4">
														<form:radiobutton path="dispay_info" value="ShowAll" onclick="hide_details(this.value);" />
														<spring:message code="BzComposer.updatevendor.showall" />
														<script type="text/javascript">
															//radio_val = document.VendorForm.dispay_info.value;
														</script>
													</td>
												</tr>
												<tr>
													<td>
														<form:radiobutton path="dispay_info" value="ByVal" onclick="hide_details(this.value);" />
														<spring:message code="BzComposer.updatevendor.byval" />
													</td>
													<td>
														<spring:message code="BzComposer.updatevendor.from"/>
														<form:input type="text" path="periodFrom" readonly="true" size="15" disabled="true" />
														<img src="${pageContext.request.contextPath}/images/cal.gif"
															 onclick="displayCalendar(document.VendorForm.periodFrom,'mm-dd-yyyy',this);" id="imgfrm">
													</td>
													<td>
														<spring:message code="BzComposer.updatevendor.to"/>
														<form:input type="text" path="periodTo" readonly="true" size="15" disabled="true" />
														<img src="${pageContext.request.contextPath}/images/cal.gif"
															 onclick="displayCalendar(document.VendorForm.periodTo,'mm-dd-yyyy',this);" id="imgto">
													</td>
													<td>
														<input type="button" class="formbutton" name="Look up" value="<spring:message code='BzComposer.updatevendor.lookhistory'/>" id="lookBtn" onclick="lookUpHistory();" />
													</td>
												</tr>
											</table>
											<div id="t_history">
											</div>
										</div>
									</div>
								</div>
							</div>
							<table cellpadding="0" cellspacing="0" border="0" align=center  style="width: 100%;">
								<td align="center">
									<button type="button" class="formbutton" title="Update Vendor" onclick="UpdateVendorData();"><spring:message code='BzComposer.global.update'/></button>
									<button type="button" class="formbutton" title="Close" onclick="closeme();"><spring:message code='BzComposer.global.close'/></button>
									<input type="hidden" name="stname" value="" id="stateId">
								</td>
							</table>

							<div>
							    <input type="hidden" name="tabid" id="tabid" value="" />
							    <input type="hidden" name="AnualRate" id="arate" />
							    <input type="hidden" name="MinFinance" id="mfinance" />
							    <input type="hidden" name="GracePeriod" id="gperiod" />
							    <input type="hidden" name="Flag" id="flagId" value="0" />
                                <input type="hidden" name="bst" id="bsst" value="0" />
                            </div>
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
var funsequence = 0;
var _1 = navigator.userAgent.toLowerCase();
var ___ = (_1.indexOf("msie") != -1);
var ___5 = (_1.indexOf("msie 5") != -1);
var _io = (_1.indexOf("opera") != -1);
var _im = (_1.indexOf("mac") != -1);
var ____gi = (_1.indexOf("gecko") != -1);
var i____s = (_1.indexOf("safari") != -1);
var o = null;
var o22 = null;
var o33 = null;
var oT = null;
var r = null;

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
	var radio_val;
	function validateEntry(str)
	{
		var p = str.charAt("/");
		if (p < 0)
			document.getElementsByName("expDate").value="";
	}
	function closeme(){
		this.window.close();
	}
	function clearBillingAdd()
	{
		document.VendorForm.bscname.value="";
		document.VendorForm.bsfirstName.value="";
		document.VendorForm.bslastName.value="";
		document.VendorForm.bsaddress1.value="";
		document.VendorForm.bsaddress2.value="";
		document.VendorForm.bscity.value="";
		document.VendorForm.bszipCode.value="";
		document.VendorForm.bsprovince.value="";
		document.VendorForm.bscountry.value="0";
		document.VendorForm.bsstate.value="0";
	}
	function clearShippingAdd()
	{
		document.VendorForm.shcname.value="";
		document.VendorForm.shfirstName.value="";
		document.VendorForm.shlastName.value= "";
		document.VendorForm.shaddress1.value= "";
		document.VendorForm.shaddress2.value= "";
		document.VendorForm.shcity.value= "";
		document.VendorForm.shzipCode.value = "";
		document.VendorForm.shprovince.value="";
		document.VendorForm.shcountry.value= "0";
		document.VendorForm.shstate.value="0";
	}
	function CopyBilladdToShipAdd()
	{
		document.VendorForm.shcname.value= document.VendorForm.bscname.value;
		document.VendorForm.shfirstName.value= document.VendorForm.bsfirstName.value;
		document.VendorForm.shlastName.value= document.VendorForm.bslastName.value;
		document.VendorForm.shaddress1.value= document.VendorForm.bsaddress1.value;
		document.VendorForm.shaddress2.value= document.VendorForm.bsaddress2.value;
		document.VendorForm.shcity.value= document.VendorForm.bscity.value;
		document.VendorForm.shzipCode.value = document.VendorForm.bszipCode.value;
		document.VendorForm.shprovince.value=document.VendorForm.bsprovince.value;
		document.VendorForm.shcountry.value= document.VendorForm.bscountry.value;
 		document.VendorForm.shstate.value = document.VendorForm.bsstate.value;
	}
	function trim(inputString)
	{
		// Removes the spaces  return from the passed string.
		var retValue = inputString;
		var ch = retValue.substring(0, 1);
		while (ch == "\n" || ch == "\r" || ch==" " || ch=="\t" )
		{
			retValue = retValue.substring(1, retValue.length);
			ch = retValue.substring(0, 1);
		}
		return retValue;
	}

	function UpdateVendorData()
	{
	    debugger;
		if(document.getElementById("cname").value=="")
		{

			return showNameDialog();
			document.VendorForm.cname.focus();
		}
		else if(trim(document.VendorForm.firstName.value)=="")
		{

			return showFirstNameDialog();
			document.VendorForm.firstName.focus();
		}
		else if(trim(document.VendorForm.lastName.value)=="")
		{

			return showLastNameDialog();
			document.VendorForm.lastName.focus();
		}
		else if(trim(document.VendorForm.address1.value)=="")
		{

			return showAddress1Dialog();
			document.VendorForm.address1.focus();
		}
		else if(trim(document.VendorForm.city.value)=="")
		{

			return showCityDialog();
			document.VendorForm.address1.focus();
		}
		else if(trim(document.VendorForm.zipCode.value)=="")
		{

			return showZipCodeDialog();
			document.VendorForm.zipCode.focus();
		}
		else{
			if(validate()){
				event.preventDefault();
				$("#updateVendorDialog").dialog({
					resizable: false,
					height: 200,
					width: 500,
					modal: true,
					buttons: {
						"<spring:message code='BzComposer.global.ok'/>": function () {
							document.getElementById('tabid').value="updateVendor";
							document.forms[0].action="Vendor?tabid=updateVendor";
							document.forms[0].submit();
						},
				<spring:message code='BzComposer.global.cancel'/>: function () {
				$(this).dialog("close");
				return false;
			}
			}
			});
				return false;
			}
		}
	}

	function addToTable(form)
	{

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

		/*
            for(i=0; i<SIDSize; i++)
            {
                sid=document.getElementById(i+'_ser').value;

                exist[i]=sid;
                cnt++;
            }
        */

		for(t=0; t<exist.length; t++){	//limit=cnt
			if(invID==exist[t])		{
				flag=1;
				break;
			}
		}

		/*	for(z=0;z<SIDSize;z++)
            {
                sid=document.getElementById(z+'id').value;
                if(exist[z]==sid)
                {
                    flag1=1;
                }
            }
    */
		/***********************************************************************************************************
		 for(i=0;i<SLSize;i++)
		 {
		iinvID=document.getElementById(i+'sid').value;
		isID=document.getElementById(i+'isid').value;
		if(invID==iinvID)
		{


				serviceName=document.getElementById(i+'sname').value;



			for(j=0;j<InvSize;j++)
			{
				isID2=document.getElementById(j+'invid').value;
					if(isID==isID2)
					{
							exist[cnt]=invID;


							InvoiceStyle=document.getElementById(j+'iname').value;
							cnt++;
					}
			}
		}
	}
		 *************************************************************************************************************/
		if(flag==1)
		{

			return showServiceValidationDialog();
			return;
		}
		else
		{
			flagx=0;

			for(i=0;i<SLSize;i++){
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
							flagx=1;

							//cnt++;
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
			tr.setAttribute("id", "row"+invID);	//tr.setAttribute("id", "row"+hidn_val);

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

			var button='<img src="/WEB-INF/jsp/images/delete.png" alt="Deletable" onclick=removeFromTable('+invID+'); >';
			var td5 = document.createElement("td");
			td5.setAttribute("align", "left");
			tr.appendChild(td5);
			td5.innerHTML=button;

			/* var td6 = document.createElement("td");
            td6.setAttribute("align", "left");
            tr.appendChild(td6); */										//td6 is added on 02-10-2019

			form.table_size.value++;


			form.table_serID.value+=invID+";";


			form.table_serviceName.value+=serviceName+";";

			//form.table_invId.value+=InvoiceStyle+";";

			form.table_bal.value+=ServiceBalance+";";


			if(InvoiceStyle==""){
				form.table_invId.value+="0;";
			}
			else
			{
				for(p=0;p<InvSize;p++) {
					invoiceName=document.getElementById(p+'iname').value;
					if(invoiceName==InvoiceStyle) {

						form.table_invId.value+=document.getElementById(p+'invid').value+";";
					}
				}
			}
			count = ((count)/1 + 1);
			document.getElementById('hidn').value=((hidn_val)/1 + 1);
		}
		name=form.table_serviceName.value;
	}

	/* function AddItem(form)
    {
        if(form.itemID.value==0)
        {
            document.getElementById('serialNo_id').value="";
            document.getElementById('qty_id').value="";
            document.getElementById('desc_id').value="";
            document.getElementById('unitPrice_id').value="";
            document.getElementById('amount_id').value="";
            document.getElementById('weight_id').value="";
            alert('<spring:message code="BizComposer.Estimaion.Item.Validation" />');
	}
	else
	{
		hidn_Id = document.getElementById('hidnId').value;

		ivcode = document.getElementById('code1').value;
		qty=document.getElementById('qty_id').value;
		serialNo = document.getElementById('serialNo_id').value;
		var desc = document.getElementById('desc_id').value;
		weight = document.getElementById('weight_id').value;
		tax = document.getElementById('tax_id').value;
		uprice=document.getElementById('unitPrice_id').value;

		var tr = document.createElement("tr");
		tr.setAttribute("id", "tr"+hidn_Id);

		var tr2 = document.getElementById('tr##');
		var parentTr = tr2.parentNode;
		parentTr.insertBefore(tr, tr2);

		var td1 = document.createElement("td");
		td1.setAttribute("align", "center");
		tr.appendChild(td1);
		td1.innerHTML=ivcode;

		var td2 = document.createElement("td");
		td2.setAttribute("align", "center");
		tr.appendChild(td2);
		td2.innerHTML=qty;

		var td3 = document.createElement("td");
		td3.setAttribute("align", "center");
		tr.appendChild(td3);
		td3.innerHTML=desc;

		var td4 = document.createElement("td");
		td4.setAttribute("align", "left");
		tr.appendChild(td4);
		td4.innerHTML=uprice;

		amt=( (qty/1) * (uprice/1) );
		document.getElementById('amount_id').value=amt;

		var td5 = document.createElement("td");
		td5.setAttribute("align","center");
		tr.appendChild(td5);
		td5.innerHTML=amt;

		var td6 = document.createElement("td");
		td6.setAttribute("align","center");
		tr.appendChild(td6);
		td6.innerHTML=weight;

		var t="";
		if(tax==0)
			t="Yes";
		else
			t="No";

		var td6 = document.createElement("td");
		td6.setAttribute("align","center");
		tr.appendChild(td6);
		td6.innerHTML=t;

		var td7 = document.createElement("td");
		td7.setAttribute("align","center");
		tr.appendChild(td7);
		td7.innerHTML=serialNo;

		hidn_Id++;
	}
} */
	function setDefault(invID1,form)
	{
		form.table_defaultVal.value=invID1;
		default_sid=invID1;
	}
	function setDefaultToDb(invID1,form)
	{
		form.table_DbDefSer.value=invID1;
		default_sid=invID1;
	}
	function removeFromTable(idV)
	{
		if(idV==default_sid)
		{
			default_sid=0;
		}
		var str;
		var trid;
		trid="row"+idV;
		for(i=0; i<exist.length; i++)
		{
			if(idV==exist[i])
			{
				var ttt=document.getElementById(trid);
				ttt.parentNode.removeChild(ttt);
				exist[i]=-1;
				cnt--;
				removeStringValues(idV);
				break;
			}
		}
	}
	function removeStringValues(key)
	{	//from one string('str'), a substring 'key' will be removed
		var str, str2, str3;
		var temp=new Array(20);
		var temp2=new Array(20);
		var temp3=new Array(20);
		str=str2=str3="";
		str= document.forms[0].table_serID.value;
		str2=document.forms[0].table_bal.value;
		str3=document.forms[0].table_invId.value;
		if (str!="" && str2!="" && str3!="")
		{
			temp=str.split(";");
			temp2=str2.split(";");
			temp3=str3.split(";");
		}
		str=str2=str3="";
		for (i=0; i<temp.length; i++)
		{
			if (temp[i]!=key)
			{
				k=0;
				for (k=0; k<i; k++)
				{
					if (temp[i]==temp[k])
					{
						k=-1;
						break;
					}
				}
				if (k!=-1)
				{
					str=str+temp[i]+";";
					str2=str2+temp2[i]+";"
					str3=str3+temp3[i]+";"
				}
			}
		}
		str=str.substring(0,str.length-1);	//removes last semi-colon
		str2=str2.substring(0,str2.length-1);	//removes last semi-colon
		str3=str3.substring(0,str3.length-1);	//removes last semi-colon
		if (key==document.forms[0].table_defaultVal.value)
		{
			document.forms[0].table_defaultVal.value="0";	//reset if no service is set to default
		}
		document.forms[0].table_serID.value=str;
		document.forms[0].table_bal.value=str2;
		document.forms[0].table_invId.value=str3;
	}
	function lookUpHistory(){
		//val = document.getElementById('hidn').value;
		let radio_val = $('input[name="dispay_info"]:checked').val();
		custid = document.VendorForm.custId.value;
		var dfrom = document.VendorForm.periodFrom.value;
		var dto = document.VendorForm.periodTo.value;
		document.getElementById('flagId').value = '1';
		refreshTransationNow(radio_val, custid, dfrom, dto);
	}
	function writeSelectTH(){
        if (oT.readyState != 4 || oT.status != 200) {
            return;
        }
        document.getElementById("t_history").innerHTML = o.responseText ;
    }
    function refreshTransationNow(radio_val, custid, dfrom, dto){
        oT = c(writeSelectTH);
        oGET(oT,'Customer?tabid=addTransactionHistory&custId=' + custid+'&cond='+radio_val+'&pfrom='+dfrom+'&pto='+dto)
    }

	function hide_details(val)
	{
		document.getElementById('lookBtn').disabled=false;
		if(val=="ShowAll"){
			document.getElementById('imgfrm').style.visibility='hidden';
			document.getElementById('imgto').style.visibility='hidden';
			document.VendorForm.periodFrom.disabled=true;
			document.VendorForm.periodTo.disabled=true;
		}
		if(val=="ByVal"){
			document.getElementById('imgfrm').style.visibility='visible';
			document.getElementById('imgto').style.visibility='visible';
			document.VendorForm.periodFrom.disabled=false;
			document.VendorForm.periodTo.disabled=false;
		}
		radio_val=val;
	}

	function init()
	{
		debugger;
		<c:if test = "${CustomerDetails}">
		<%--<logic:equal name="CustomerDetails"	path="fsUseIndividual" value="1">--%>
		</c:if>
		<c:if test="${not empty RadioVal}">
		<%--<logic:equal name="RadioVal" value="1">--%>
		hide_details("ShowAll");
		radio_val="ShowAll";
		<c:if test="${not empty ClientID}">
		lookUpHistory();
		</c:if>
		<c:if test = "${RadioVal}"> /*value="2"*/
		hide_details("ByVal");
		radio_val="ByVal";
		<c:if test="${not empty FlagValue}">
		lookUpHistory();
		</c:if>
		</c:if>
		</c:if>
		<c:if test="${not empty RadioVal}">
		hide_details("ShowAll");
		</c:if>
		//document.getElementById('tid').value="";

		/*To display data either in readonly or not*/
		var readOnly = <%= request.getAttribute("readData") %>

		if(readOnly)
		{
			$('#newInvoice').prop('disabled', true);
			$('#btnNewInvoice').prop('disabled', true);
			$('#btnSaveInvoice').prop('disabled', true);
			$('#btnUpdateInvoice').prop('disabled', true);
			$('#btnDeleteInvoice').prop('disabled', true);
			$(':input[type="text"]').prop('disabled', false);
			$('input[type=text],textarea').prop('readonly', true);
			$('#custID').prop('readonly', true);
			$('#invoiceStyle').prop('readonly',true);
			$("#sortByLastName").prop('checked',false);
		}
		else
		{
			$('#newInvoice').prop('disabled', false);
			$('#btnNewInvoice').prop('disabled', false);
			$('#btnSaveInvoice').prop('disabled', false);
			$('#btnUpdateInvoice').prop('disabled', false);
			$('#btnDeleteInvoice').prop('disabled', false);
			$('input[type=text],textarea').prop('readonly', false);
			$('#custID').prop('readonly', true);
			$('#invoiceStyle').prop('readonly',true);
			$("#sortByLastName").prop('checked',false);
		}
		document.getElementById("dispay_info1").checked = true;

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

function numbersonly(e,val){
    var temp=val.indexOf(".");
    var unicode=e.charCode? e.charCode : e.keyCode;
    if (unicode!=8){
        //if the key isn't the backspace key (which we should allow)
        if(unicode==46 && temp==-1){
            return true;
        }
        else
        if (unicode<48||unicode>57) //if not a number
            return false; //disable key press
    }
}

//Adding Payment method
function showAddPaymentMethodDialog(){
    event.preventDefault();
    $("#showAddPaymentMethodDialog").dialog({
        resizable: false,
        height: 450,
        width: 450,
        modal: true,
        title: "Add Payment Method",
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $('#ccType').removeClass('errorClass');
                $('#cardNo').removeClass('errorClass');
                $('#expDate').removeClass('errorClass');
                $('#cw2').removeClass('errorClass');
                $('#cardHolderName').removeClass('errorClass');
                $('#cardBillAddress').removeClass('errorClass');
                $('#cardZip').removeClass('errorClass');

                if(document.getElementById('ccType').value.trim() == '0'){
                    $('#ccType').addClass('errorClass');
                    return false;
                }
                else if(document.getElementById('cardNo').value.trim() == ''){
                    $('#cardNo').addClass('errorClass');
                    return false;
                }
                else if(document.getElementById('expDate').value.trim() == ''){
                    $('#expDate').addClass('errorClass');
                    return false;
                }
                else if(document.getElementById('cw2').value.trim() == ''){
                    $('#cw2').addClass('errorClass');
                    return false;
                }
                else if(document.getElementById('cardHolderName').value.trim() == ''){
                    $('#cardHolderName').addClass('errorClass');
                    return false;
                }
                else if(document.getElementById('cardBillAddress').value.trim() == ''){
                    $('#cardBillAddress').addClass('errorClass');
                    return false;
                }
                else if(document.getElementById('cardZip').value.trim() == ''){
                    $('#cardZip').addClass('errorClass');
                    return false;
                }else{
                    $(this).dialog("close");
                    let cardInfo = {
                        custId : document.getElementById('custId').value,
                        ccType : document.getElementById('ccType').value,
                        cardNo : document.getElementById('cardNo').value,
                        expDate : document.getElementById('expDate').value,
                        cw2 : document.getElementById('cw2').value,
                        cardHolderName : document.getElementById('cardHolderName').value,
                        cardBillAddress : document.getElementById('cardBillAddress').value,
                        cardZip : document.getElementById('cardZip').value
                    }
                    $.ajax({
                        type: "POST",
                        url:"CustomerAjax?tabid=addPaymentMethod",
                        data : cardInfo,
                        success : function(data) {
                            location.reload();
                        },
                        error : function(data) {
                            alert("<bean:message key='BzComposer.common.erroroccurred'/>");
                        }
                    });
                }
            },
            <spring:message code='BzComposer.global.cancel'/>: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}

function validate()
{
    var email = document.VendorForm.email.value;
    var mail =String(email);
    var pattern=/^[_0-9a-zA-z]+(\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\.[A-Za-z]+)+$/;
    if(email==""){
        return true;
    }
    else if (!pattern.test(email)){
        document.VendorForm.email.focus();
        return showEmailValidationDialog();
        return false;
    }
    return true;
}
function showNameDialog()
{
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
function showFirstNameDialog()
{
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
function showLastNameDialog()
{
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
function showAddress1Dialog()
{
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
function showCityDialog()
{
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
function showZipCodeDialog()
{
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
function showEmailValidationDialog()
{
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
function enterEmailValidationDialog()
{
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
function showServiceValidationDialog()
{
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

<div id="showAddPaymentMethodDialog" style="display:none;">
	<div id="table-negotiations">
	    <form name="CardForm" method="post">
        <table class="tabla-listados" cellspacing="0">
            <thead>
                <tr>
                    <th colspan="3" style="font-size:14px;">
                        <spring:message code="BzComposer.updatecustomer.creditcardinfo" />
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>&nbsp;<spring:message code="BzComposer.global.cardtype" /></td>
                    <td>
                        <select id="ccType">
                            <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                            <c:forEach items="${CreditCardList}" var="objList">
                            <option value="${objList.value}">${objList.label}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;<spring:message code="BzComposer.global.cardnumber" /></td>
                    <td><input id="cardNo" /></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;<spring:message code="BzComposer.global.expdate" /></td>
                    <td>
                        <input id="expDate" name="expDate" readonly="true" />
                        <img src="images/cal.gif" onclick="displayCalendar(document.CardForm.expDate, 'mm/yyyy', this);">
                        <spring:message code="BzComposer.Customer.mformate" />
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;<spring:message code="BzComposer.global.CW2" /></td>
                    <td><input id="cw2" size="7" /></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;<spring:message code="BzComposer.global.cardholdername" /></td>
                    <td><input id="cardHolderName" /></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;<spring:message code="BzComposer.updatecustomer.billingaddress" /></td>
                    <td><input id="cardBillAddress" size="30" /></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;<spring:message code="BzComposer.global.zipcode" /></td>
                    <td><input onkeydown="return numbersonly(event,this.value)" id="cardZip" /></td>
                    <td>&nbsp;</td>
                </tr>
            </tbody>
        </table>
        </form>
    </div>
</div>
<!-- dialog box used in this page -->
<div id="showNameDialog" style="display:none;">
    <p><spring:message code="BzComposer.updatevendor.entercompanynamevalidation" /></p>
</div>
<div id="showFirstNameDialog" style="display:none;">
    <p><spring:message code="BzComposer.updatevendor.enterfirstnamevalidation" /></p>
</div>
<div id="showLastNameDialog" style="display:none;">
    <p><spring:message code="BzComposer.updatevendor.enterlastnamevalidation"/></p>
</div>
<div id="showAddress1Dialog" style="display:none;">
    <p><spring:message code="BzComposer.updatevendor.enteraddress1validation"/></p>
</div>
<div id="showCityDialog" style="display:none;">
    <p><spring:message code="BzComposer.updatevendor.entercityvalidation"/></p>
</div>
<div id="showZipCodeDialog" style="display:none;">
    <p><spring:message code="BzComposer.updatevendor.enterzipcodevalidation"/></p>
</div>
<div id="showEmailValidationDialog" style="display:none;">
    <p><spring:message code="BzComposer.updatevendor.enteremailvalidation" /></p>
</div>
<div id="enterEmailValidationDialog" style="display:none;">
    <p><spring:message code="Bzcomposer.updatevendor.enteremailaddress"/></p>
</div>
<div id="updateVendorDialog" style="display:none;">
    <p><spring:message code="BzComposer.updatevendor.updatecustomerinformation"/></p>
</div>
<div id="showServiceValidationDialog" style="display:none;">
    <p><spring:message code="BzComposer.updatevendor.serviceexistvalidation"/></p>
</div>