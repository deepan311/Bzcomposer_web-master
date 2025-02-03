<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script>
	self.moveTo(100, 100);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<title><spring:message code="BzComposer.updatecustomertitle" /></title>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<style>
.errorClass { border: solid 1px #FF0000; }
.redColor {color: red;}
.height150 { height: 150px; }
.card-text { font-size: 12px! important; color: #6C7590! important; padding: 0px; margin: 5px; }
.defaultCard { margin-top:10px;background: #C3C5CA;border-radius: 5px;padding:5px; }
.mTop10 { margin-top:10px; }
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
    $("#tabs").tabs();
});
</script>
</head>
<body onload="initialize();">
<form:form name="CustomerForm" method="post" onsubmit="return validate();" modelAttribute="CustomerDetails">
	<form:hidden path="custId" value='<%= request.getParameter("cvId")%>'/>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">

<div id="padding">
	<!-- begin Contents --> 
	<!-- add the code for tab here -->
	<div>
		<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.updatecustomer.editcustomerinformation"/>
		</span>
	</div>
	<div>
		<c:if test="${not empty SaveStatus}">
			<span class="msgstyle">${SaveStatus}</span>
		</c:if>
	</div>
	<div>
	<div id="tabs" style="min-height:550px;width:100%;">
  		<ul>
		    <li><a href="#General-1"><spring:message code="BzComposer.updatecustomer.tabs.general" /></a></li>
		    <li><a href="#payment-2"><spring:message code="BzComposer.addnewcustomer.tabs.paymentInformation" /></a></li>
		    <!-- <li><a href="#service-3"><spring:message code="BzComposer.updatecustomer.tabs.service" /></a></li> -->
		    <li><a href="#finance-5"><spring:message code="BzComposer.updatecustomer.tabs.financecharges" /></a></li>
		    <li><a href="#Transactional-6"><spring:message code="BzComposer.updatecustomer.tabs.transactionhistory" /></a></li>
  		</ul>
	<div id="General-1">
		<div id="content1" class="tabPage">
		<!-- add here the content of first tab -->
		<div id="table-negotiations">
			<table class="tabla-listados" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th colspan="7" style="font-size:14px;">
							<spring:message code="BzComposer.updatecustomer.customerinformation"/>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					    <td>
                            <spring:message code="BzComposer.global.customerid" />
                            <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                        </td>
                        <td>
                            <form:input path="clientVendorID" readonly="true" style="width:100px;" />
                        </td>
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
						<td>
                            <spring:message code="BzComposer.register.dbaName" />
                        </td>
                        <td>
                            <form:input path="dbaName" />
                        </td>
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
                    </tr>
                    <tr>
						<td>
							<spring:message code="BzComposer.global.address2" />
						</td>
						<td colspan="3">
							<form:input path="address2" style="width:90%;" />
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
                            <spring:message code="BzComposer.global.mobile" />
                        </td>
                        <td colspan="2">
                            <form:input path="cellPhone" maxlength="16" onkeypress="return numbersonly(event,this.value)" onchange="validateUSAPhoneNumber(this, true);" />
                            <form:checkbox path="isMobilePhoneNumber" onchange="copyPhoneNumber(this.form);" />
                            (<spring:message code="BzComposer.global.isPhoneNumber" />)
                        </td>
                        <td>
                            <spring:message code="BzComposer.global.fax" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <form:input path="fax" onkeypress="return numbersonly(event,this.value);" onchange="validateUSAPhoneNumber(this, true);" />
                        </td>
                        <td>&nbsp;</td>
                    </tr>
					<tr>

						<td>
							<spring:message code="BzComposer.global.email" />
						</td>
						<td colspan="3">
							<form:input path="email" size="40" />
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
                    <tr>
						<td>
							<spring:message code="BzComposer.Companyinformation.Type" />
						</td>
						<td>
							<form:select path="type" style="width:215px;">
								<form:option value="0">
									<spring:message code="BzComposer.ComboBox.Select" />
								</form:option>
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
							<c:if test="${CustomerDetails.taxAble == 1}">
								<input value="on" type="checkbox" name="isTaxable" checked="checked" />
							</c:if>
							<c:if test="${CustomerDetails.taxAble != 1}">
								<input value="on" type="checkbox" name="isTaxable" />
							</c:if>
							<spring:message code="BzComposer.global.istaxable" />
						</td>
						<td>
							<c:if test="${CustomerDetails.isclient == 1}">
								<input value="on" type="checkbox" name="isAlsoClient"  checked="checked">
							</c:if>
							<c:if test="${CustomerDetails.isclient != 1}">
								<input value="on" type="checkbox" name="isAlsoClient" >
							</c:if>
							<spring:message code="BzComposer.global.isalsovendor" />
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
					    <td>
                            <spring:message code="BzComposer.global.oppeningunpaidbalance" />
                        </td>
                        <td>
                            <form:input path="openingUB" onkeypress="return numbersonly(event,this.value);" />
                        </td>
						<td>
							<spring:message code="BzComposer.global.existingcredits" />
						<td>
							<form:input path="extCredit" onkeypress="return numbersonly(event,this.value);" />
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
		<!-- Payment method Start here -->
		<div id="table-negotiations" style="width:100%">
            <table class="tabla-listados" cellspacing="0">
                <thead>
                    <tr>
                        <th colspan="6" style="font-size:14px;">
                            <spring:message code="BzComposer.updatecustomer.prefrenceinfo" />
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
                                <form:option value="0">
                                    <spring:message code="BzComposer.ComboBox.Select" />
                                </form:option>
                                <form:options items="${TermList}" itemValue="term" itemLabel="name"/>
                            </form:select>
                        </td>
                        <td>
                            <spring:message code="BzComposer.global.paymentmethod" />
                        </td>
                        <td>
                            <form:select path="paymentType" style="width:150px;">
                                <form:option value="0">
                                    <spring:message code="BzComposer.ComboBox.Select" />
                                </form:option>
                                <form:options items="${PaymentList}" itemValue="id" itemLabel="typeName"/>
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
                                <form:option value="0">
                                    <spring:message code="BzComposer.ComboBox.Select" />
                                </form:option>
                                <form:options items="${RepList}" itemValue="value" itemLabel="label"/>
                            </form:select>
                        </td>
                        <td>
                            <spring:message code="BzComposer.global.shippingvia" />
                        </td>
                        <td>
                            <form:select path="shipping" style="width:150px;">
                                <form:option value="0">
                                    <spring:message code="BzComposer.ComboBox.Select" />
                                </form:option>
                                <form:options items="${ShipCarrierList}" itemValue="value" itemLabel="label"/>
                            </form:select>
                        </td>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Manage Address Start here -->
        <div id="table-negotiations">
            <table align="center" style="width: 100%" class="tabla-listados">
                <thead>
                    <tr>
                        <th colspan="4" style="font-size:14px;">
                            <spring:message code="BzComposer.updatecustomer.manageaddresses"/>
                        </th>
                    </tr>
                </thead>
                <!-- <thead>
                    <tr>
                    <td>
                            <input value="on"  checked="checked" type="checkbox" id="chk_setaddress"
                            name="setdefaultaddress" onclick="hidebsaddress(this.form);">
                            <input type="hidden" name="setdefaultbs" value="">
                            <font size="2">
                                Billing And Shipping Address should be the same as the Customer Information Address
                            </font>
                        </td>
                    <tr>
                </thead> -->
                <tr>
                    <td>
                        <table class="tabla-listados" cellspacing="0" width="200" align="left">
                            <thead>
                                <tr>
                                    <th colspan="5" style="font-size:14px;">
                                        <spring:message code="BzComposer.updatecustomer.billingaddress"/>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><spring:message code="BzComposer.global.company" /></td>
                                    <td colspan="2"><form:input path="bscname" style="width:90%;" /></td>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="BzComposer.register.dbaName" /></td>
                                    <td colspan="2">
                                        <form:input path="bsdbaName" />
                                    </td>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="BzComposer.global.firstname" /></td>
                                    <td><form:input path="bsfirstName" /></td>

                                    <td><spring:message code="BzComposer.global.lastname" /></td>
                                    <td><form:input path="bslastName" /></td>

                                    <td>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="BzComposer.global.address1" /></td>
                                    <td colspan="2"><form:input path="bsaddress1" style="width:90%;" /></td>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="BzComposer.global.address2" /></td>
                                    <td colspan="2"><form:input path="bsaddress2" style="width:90%;" /></td>
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
                                    <td><spring:message code="BzComposer.global.city" /></td>
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
                                        <button type="button" class="formbutton" name="ClearBillingBtn" onclick="clearBillingAdd();" title="Clear Billing Address">
                                            <spring:message code='BzComposer.updatecustomer.clearbillingaddress'/>
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                    <td style="vertical-align: inherit;font-size:14px;">
                        <input type="button" name="CopyBtn" class="formbutton"
                        onclick="CopyBilladdToShipAdd();" value="<spring:message code='BzComposer.updatecustomer.copyto'/>" title="Copy Address">
                    </td>
                    <td>
                        <table class="tabla-listados" cellspacing="0" width="200" align="left">
                            <thead>
                                <tr>
                                    <th colspan="5" style="font-size:14px;">
                                        <spring:message code="BzComposer.updatecustomer.shippingaddress"/>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><spring:message code="BzComposer.global.company" /></td>
                                    <td colspan="2"><form:input path="shcname" style="width:90%;" /></td>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="BzComposer.register.dbaName" /></td>
                                    <td colspan="2">
                                        <form:input path="shdbaName" />
                                    </td>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="BzComposer.global.firstname" /></td>
                                    <td><form:input path="shfirstName" /></td>

                                    <td><spring:message code="BzComposer.global.lastname" /></td>
                                    <td><form:input path="shlastName" /></td>
                                    <td>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="BzComposer.global.address1" /></td>
                                    <td colspan="2"><form:input path="shaddress1" style="width:90%;" /></td>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="BzComposer.global.address2" /></td>
                                    <td colspan="2"><form:input path="shaddress2" style="width:90%;" /></td>
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
                                    <td><spring:message code="BzComposer.global.city" /></td>
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
                                        <button type="button" class="formbutton" name="ClearShippingBtn" onclick="clearShippingAdd();" title="Clear Shipping Address">
                                            <spring:message code='BzComposer.updatecustomer.clearshippingaddress'/>
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
            </table>
        </div>
        <!-- Manage Address END here -->
		</div>  
	</div>

		<div id="finance-5">
			<div id="content4" class="tabPage">
			<!-- add here the content of fifth tab -->
				<div id="table-negotiations" >
					<table class="tabla-listados" cellspacing="0">
						<thead>
							<tr>
								<th colspan="4" style="font-size:14px;"><spring:message code="BzComposer.updatecustomer.financemsg" /></th>
							</tr>
						</thead>
						<thead>
							<tr>
								<td colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="4">
									<c:if test="${CustomerDetails.fsUseIndividual == true}">
										&nbsp;<input value="on" type="checkbox" name="UseIndividualFinanceCharges" checked="checked"
										onclick="enableDisableFinanceCharges();" id="chbox">
									</c:if>
									<c:if test="${CustomerDetails.fsUseIndividual != true}">
										&nbsp;<input value="on" type="checkbox" name="UseIndividualFinanceCharges"
										onclick="enableDisableFinanceCharges();" id="chbox">
									</c:if>
									<font>
										<spring:message code="BzComposer.updatecustomer.useindividualfinancecharges" />
									</font>
								</td>
							<tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<div>
										<div id="table-negotiations" style="width:48%;float: left;">
											<table class="tabla-listados" cellspacing="0">
											<thead>
												<tr>
													<th colspan="4" style="font-size:14px;">
														<spring:message code="BzComposer.updatecustomer.chargerate" />
													</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
														<spring:message code="BzComposer.updatecustomer.annualintrestrate" />
													</td>
													<td colspan="3">
														<form:input onkeypress="return numbersonly(event,this.value)"
														path="annualIntrestRate" disabled="true" />
													</td>
												</tr>
												<tr>
													<td>
														<spring:message code="BzComposer.updatecustomer.minimumfinanacecharge" />
													</td>
													<td colspan="3">
														<form:input onkeypress="return numbersonly(event,this.value)"
														path="minFCharges"  disabled="true" />
													</td>
												</tr>
												<tr>
													<td>
														<spring:message code="BzComposer.updatecustomer.graceperiod" />
													</td>
													<td colspan="3">
														<form:input onkeypress="return numbersonly(event,this.value)"
														path="gracePrd"  disabled="true" />
													</td>
												</tr>					
											</tbody>									
											</table>
										</div>
										<div id="table-negotiations" style="width:48%;float: right;">
											<table class="tabla-listados" cellspacing="0">
												<thead>
													<tr>
														<th colspan="4" style="font-size:14px;">
															<spring:message code="BzComposer.updatecustomer.applyingcharges" />
														</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td colspan="4">&nbsp;</td>
													</tr>											
													<tr>
														<td colspan="4">
															<input type="checkbox" id="chk1" name="AssessFinanceChk" disabled="true" value="${CustomerDetails.fsAssessFinanceCharge}" ${CustomerDetails.fsAssessFinanceCharge=='on'?'checked':''} />
															<spring:message code="BzComposer.addnewcustomer.assessfinance" />
														</td>
													</tr>
													<tr><td colspan="4">&nbsp;</td></tr>
                                                    <tr>
                                                        <td colspan="4" style="font-size: 12px;">
                                                            <input type="checkbox" id="chk2" name="FChargeInvoiceChk" disabled="true" value="${CustomerDetails.fsMarkFinanceCharge}" ${CustomerDetails.fsMarkFinanceCharge=='on'?'checked':''} />
                                                            <spring:message code="BzComposer.addnewcustomer.markfinanacechargeinvoice"/>
                                                        </td>
                                                    </tr>
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
						</tbody>
					</table>
				</div>
			</div>
		</div>	
				<!---------------------------  services   ------------------------------------------------------------------->
				<!-- ------------------------------------------------------------------------------- -->
				<script>
					count=0;
					cnt=0;
																		
					var exist= new Array(10); 
					for (i=0; i<exist.length; i++){
						exist[i]=-1;
					}

				</script>

				<!-- PAyment information tab -->
				<div id="payment-2">
                    <div id="content2" class="tabPage">
                        <div id="table-negotiations">
                            <table class="tabla-listados" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th colspan="5" style="font-size:14px;">
                                            <spring:message code="BzComposer.updatecustomer.creditcardinfo" />
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- <tr>
                                        <td><spring:message code="BzComposer.global.cardtype" /></td>
                                        <td>
                                            <form:select path="ccType" >
                                                <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                <form:options items="${CreditCardList}" itemValue="value" itemLabel="label"/>
                                            </form:select>
                                        </td>
                                        <td colspan="3">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code="BzComposer.global.cardnumber" /></td>
                                        <td><form:input path="cardNo"  /></td>
                                        <td><spring:message code="BzComposer.global.expdate" /></td>
                                        <td>
                                            <form:input path="expDate"  readonly="true" />
                                            <img src="images/cal.gif" onclick="displayCalendar(document.CustomerForm.expDate,'mm/yyyy',this);">
                                            <spring:message code="BzComposer.Customer.mformate" />
                                        </td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code="BzComposer.global.CW2" /></td>
                                        <td><form:input path="cw2"  size="7" /></td>
                                        <td><spring:message code="BzComposer.global.cardholdername" /></td>
                                        <td><form:input path="cardHolderName"  /></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code="BzComposer.updatecustomer.billingaddress" /></td>
                                        <td><form:input path="cardBillAddress"  size="30" /></td>
                                        <td><spring:message code="BzComposer.global.zipcode" /></td>
                                        <td><form:input onkeypress="return numbersonly(event,this.value)" path="cardZip"  /></td>
                                        <td>&nbsp;</td>
                                    </tr> -->
                                    <tr>
                                        <td colspan="5">
                                            <div class="container text-center">
                                                <div class="row text-left" id="customerCards">
                                                    <c:forEach items="${CustomerDetails.customerCards}" var="objList">
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
                                                                    <a href="Customer?tabid=makeCustomerCardDefault2&cvId=${objList.clientVendorID}&cardID=${objList.cardID}" style="color: #007bff;">
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

				<!-- Service information tab -->
				<div id="service-3" style="display:none;">
					<div id="content3" class="tabPage">
						<!------------------------------------------ add here the content of services tab ----------------------------------------------------->
						<form:hidden path="table_serviceName" value="" />
						<form:hidden path="table_defaultVal" value="0" />
						<form:hidden path="table_size" value="0" />
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

					    <input type="hidden" name="dsize" id="dSize" value='${BalenceDetails.size()}'>
					    <c:forEach items="${BalenceDetails}" var="objList3" varStatus="loop">
					        <input type="hidden" value='${objList3.serviceBalance}' id='${loop.index}bal' />
					        <input type="hidden" value='${objList3.defaultService}' id='${loop.index}dservice' />
					        <input type="hidden" value='${objList3.serviceID}' id='${loop.index}srid' />
					        <input type="hidden" value='${objList3.clientVendorID}' id='${loop.index}cvID' />
					    </c:forEach>

					<div id="table-negotiations" style="width:100%">
						<input type="hidden" value="1" id="hidn" />
						<table class="tabla-listados" cellspacing="0">
							<tbody>
								<thead>
									<tr>
										<th colspan="6" style="font-size:14px;">
											<spring:message code="BzComposer.updatecustomer.defaultservice" />
										</th>
										<!-- <td id="sername"></td> -->
									</tr>
								</thead>
								<tr>
									<td>
										<spring:message code="BzComposer.updatecustomer.service"/>
									</td>
									<td>
										<form:select path="serviceID">
											<form:options items="${ServiceList}" itemValue="serviceID" itemLabel="serviceName"/>
										</form:select>
									</td>
									<td><input type="button" class="formbutton" name="add"
									value="<spring:message code='BzComposer.global.add'/>" onclick="addToTable(this.form);" /></td>
									<td colspan="3">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="6">
										<spring:message code="BzComposer.updatecustomer.selectedservice" />
									</td>
								</tr>
							</tbody>
						</table>
					<div >

					<table class="tabla-listados" cellspacing="0">
						<thead>
							<tr>
								<th>
									<spring:message code="BzComposer.updatecustomer.servicename" />
								</th>
								<th>
									<spring:message code="BzComposer.updatecustomer.invoicestyle" />
								</th>
								<th align="center">
									<spring:message code="BzComposer.updatecustomer.balance" />
								</th>
								<th align="center">
									<spring:message code="BzComposer.updatecustomer.default" />
								</th>
								<th align="center">
									<spring:message code="BzComposer.updatecustomer.remove" />
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
							<c:if test="${empty ServiceInfo}">
								<script type="text/javascript">
                                    $('#default_sid').val(${DefaultService});
								</script>
							</c:if>
							<c:if test="${not empty DefaultService}">
								<script type="text/javascript">
										$('#default_sid').val(${DefaultService});
								</script>
							</c:if>
							<c:if test="${empty DefaultService}">
								<script type="text/javascript">
										$('#default_sid').val(0);
								</script>
							</c:if>

							<c:if test="${not empty ServiceInfo}">
								<input type="text" name="serviceSize" id="sIDSize" value='${ServiceInfo.size()}' />
								<c:forEach items="${ServiceInfo}" var="objList" varStatus="loop">
									<input type="hidden" id='${loop.index}_ser' value='${objList.serviceID}' />
									<tr id='row${objList.serviceID}'>
										<!-- id='tr${loop.index}' -->
										<td>${objList.serviceName}</td>
										<td>${objList.invoiceStyle}</td>
										<td align="center">${objList.serviceBalance}</td>

										<c:if test="${objList.defaultService == 1}">
											<td align="left">
											    <input type="radio" name=defaultService id="default" value='${objList.defaultService}'
												    onclick="setDefault('${objList.serviceID}',this.form);" checked="checked" />
												<script type="text/javascript">
													$('#default_sid').val(${objList.serviceID});
													document.getElementById("sername").innerHTML = '${objList.serviceName}';
												</script></td>
										</c:if>

										<c:if test="${objList.defaultService != 1}">
											<td align="left">
											    <input type="radio" name=defaultService id="default" value='${objList.defaultService}'
												onclick="setDefault('${objList.serviceID}',this.form);" />
											</td>
										</c:if>

										<td align="left"><img
											src="${pageContext.request.contextPath}/images/delete.png"
											alt="Deletable"
											onclick="removeFromTable('${objList.serviceID}');" />
										<!-- onclick="removeFromTable('${loop.index}');" -->
										</td>


										<td align="center"><input type="hidden"
											value='${objList.serviceID}'
											id='${loop.index}id' /></td>
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
						<c:if test="${empty ServiceInfo}">
							<input type="hidden" id="sIDSize" value="0" />
						</c:if>
					</table>
					<div>
					<table>
					</table>
					</div>
					</div>
					</div>
					</div>
					</div>

				<!--  Transactional History-->
				<div id="Transactional-6">
					<div id="content5" class="tabPage">
					<!-- add here the content of sixth tab -->
					<div id="table-negotiations">
					<table class="tabla-listados" cellspacing="0">
						<thead>
							<tr>
								<th colspan="4" style="font-size:14px;">
									<spring:message code="BzComposer.updatecustomer.displayoption" />
								</th>
							</tr>
						</thead>
						<tr>
							<td colspan="4">
							    <form:radiobutton path="dispay_info" value="ShowAll" onclick="hide_details(this.value);" />
							    <spring:message code="BzComposer.updatecustomer.showall" />
								<script type="text/javascript">
                                    radio_val = document.CustomerForm.dispay_info.value;
                                </script>
                            </td>
						</tr>
						<tr>
							<td>
							    <form:radiobutton path="dispay_info" value="ByVal" onclick="hide_details(this.value);" />
							    <spring:message code="BzComposer.updatecustomer.byval" /></td>
							<td>
								<spring:message code="BzComposer.updatecustomer.from"/>
								<form:input path="periodFrom" readonly="true" size="15" disabled="true" />
								<img src="${pageContext.request.contextPath}/images/cal.gif"  id="imgfrm" onclick="displayCalendar(document.CustomerForm.periodFrom,'mm-dd-yyyy',this);" />
								</td>
							<td>
								<spring:message code="BzComposer.updatecustomer.to"/>
								<form:input path="periodTo" readonly="true" size="15" disabled="true" />
								<img src="${pageContext.request.contextPath}/images/cal.gif" id="imgto" onclick="displayCalendar(document.CustomerForm.periodTo,'mm-dd-yyyy',this);" />
							</td>
							<td><input type="button" class="formbutton" name="Look up"
								value="<spring:message code='BzComposer.updatecustomer.lookhistory'/>" id="lookBtn" onclick="lookUpHistory();" /></td>
						</tr>
					</table>
					<div id="t_history" ></div>

					</div>
					</div>
					
						

				<!--  Periodic Billing -->
<!-- 							<tr> -->
<!-- 							<td><input type="hidden" value="0" -->
<!-- 						id="hidnId"> <input type="hidden" value="" id="code1"> -->
<!-- 								<div id="table-negotiations" style="width:100%;"> -->
<!-- 								<table class="tabla-listados" cellspacing="0"> -->
<!-- 									<thead> -->
<!-- 										<tr> -->
<%-- 											<th colspan="4"><spring:message --%>
<!-- 												code="BzComposer.UpdateCustomer.PeriodicBilling.MonthlyBillingDay" /></th> -->
<!-- 										</tr> -->
<!-- 									</thead> -->
<!-- 									<tbody> -->
<!-- 										<tr> -->
<%-- 									<td align="left"><form:select path="monthlyBilling"> --%>
<%-- 										<form:option value="1"></form:option> --%>
<%-- 										<form:option value="2"></form:option> --%>
<%-- 										<form:option value="3"></form:option> --%>
<%-- 										<form:option value="4"></form:option> --%>
<%-- 										<form:option value="5"></form:option> --%>
<%-- 										<form:option value="6"></form:option> --%>
<%-- 										<form:option value="7"></form:option> --%>
<%-- 										<form:option value="8"></form:option> --%>
<%-- 										<form:option value="9"></form:option> --%>
<%-- 										<form:option value="10"></form:option> --%>
<%-- 										<form:option value="11"></form:option> --%>
<%-- 										<form:option value="12"></form:option> --%>
<%-- 										<form:option value="13"></form:option> --%>
<%-- 										<form:option value="14"></form:option> --%>
<%-- 										<form:option value="15"></form:option> --%>
<%-- 										<form:option value="16"></form:option> --%>
<%-- 										<form:option value="17"></form:option> --%>
<%-- 										<form:option value="18"></form:option> --%>
<%-- 										<form:option value="19"></form:option> --%>
<%-- 										<form:option value="20"></form:option> --%>
<%-- 										<form:option value="21"></form:option> --%>
<%-- 										<form:option value="22"></form:option> --%>
<%-- 										<form:option value="23"></form:option> --%>
<%-- 										<form:option value="24"></form:option> --%>
<%-- 										<form:option value="25"></form:option> --%>
<%-- 										<form:option value="26"></form:option> --%>
<%-- 										<form:option value="27"></form:option> --%>
<%-- 										<form:option value="28"></form:option> --%>
<%-- 										<form:option value="29"></form:option> --%>
<%-- 										<form:option value="30"></form:option> --%>
<%-- 										<form:option value="31"></form:option> --%>
<%-- 									</form:select></td> --%>
								
<!-- 											</tr> -->
<!-- 											</tbody> -->
<!-- 								</table> -->
<!-- 								</div> -->
<!-- 								</td> -->
<!-- 								</tr>			 -->
<!-- 						<tr> -->
<!-- 								<td> -->
<!-- 								<div id="table-negotiations" style="width:100%;"> -->
<!-- 								<table class="tabla-listados" cellspacing="0"> -->
<!-- 									<thead> -->
<!-- 										<tr> -->
<%-- 											<th colspan="4"><spring:message --%>
<!-- 												code="BizComposer.Estimaion.Header.ItemInfo" /></th> -->
<!-- 										</tr> -->
<!-- 									</thead> -->
<!-- 									<tbody> -->
<!-- 										<tr> -->
<!-- 											<td colspan="4">&nbsp; -->
<!-- 											<table> -->
<!-- 								<tr> -->
<!-- 									<td> -->
									
<!-- 										<tr> -->
<%-- 											<td align="right" id="td1"><spring:message --%>
<!-- 												code="BzComposer.Invoice.ItemID" /></td> -->
<%-- 											<td id="td2"><form:select path="itemID" --%>
<!-- 												onchange="ItemChange(this.value);"> -->
<%-- 												<form:option value="0"> --%>
<%-- 													<spring:message code="BzComposer.ComboBox.Select" /> --%>
<%-- 												</form:option> --%>
<%-- 							<form:options collection="ItemList" property="invID" --%>
<!-- labelProperty="invCode" />				 -->
<%-- 				<logic:iterate name="ItemList" id="itmList"> --%>
<%-- 					<logic:equal name="itmList" property="isCategory" value="0"> --%>
<%-- 						<form:option value='${itmList.invID}'>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write --%>
<!-- 								name="itmList" property="invCode" filter="false" /> -->
<%-- 						</form:option> --%>
<%-- 					</logic:equal> --%>
<%-- 					<logic:equal name="itmList" property="isCategory" value="1"> --%>
<%-- 						<form:option value='${itmList.invID}'> --%>
<%-- 							<bean:write name="itmList" property="invCode" filter="false" /> --%>
<%-- 						</form:option> --%>
<%-- 					</logic:equal> --%>
<%-- 				</logic:iterate> --%>
<%-- 			</form:select></td>		 --%>
										

<!-- 											<td align="right"><div id="td3" style="display:block;"> -->
<%-- 											<spring:message code="BzComposer.Invoice.Qty" /> --%>
<!-- 											</div> -->
<!-- 											</td> -->
<!-- 											<td><input type="text" size="10" id="qty_id" -->
<!-- 												onkeypress="return numbersonly(event,this.value)" /></td> -->
<%-- 											<td align="right"><spring:message --%>
<!-- 												code="BzComposer.Invoice.SerialNo" /></td> -->
<!-- 											<td><input type="text" size="10" id="serialNo_id" -->
<!-- 												readonly="readonly" /></td> -->
<%-- 											<td align="center"><spring:message --%>
<!-- 												code="BzComposer.Invoice.Desc" /></td> -->
<!-- 											<td><textarea rows="1" cols="25" readonly="readonly" -->
<!-- 												id="desc_id"></textarea></td> -->
											
<!-- 										</tr> -->
<!-- 										<tr id="tr2" > -->
<%-- 											<td align="right"><spring:message --%>
<!-- 												code="BzComposer.Invoice.UnitPrice" /></td> -->
<!-- 											<td><input type="text" size="10" id="unitPrice_id" -->
<!-- 												onkeypress="return numbersonly(event,this.value)" /></td> -->
<%-- 											<td align="right"><spring:message --%>
<!-- 												code="BzComposer.Invoice.Amount" /></td> -->

<!-- 											<td><input type="text" size="10" readonly="true" -->
<!-- 												id="amount_id" onfocus="Multiplication();" /></td> -->

<%-- 											<td align="right"><spring:message --%>
<!-- 												code="BzComposer.Invoice.Weight" /></td> -->
<!-- 											<td><input type="text" name="itemWeight" size="10" -->
<!-- 												id="weight_id" -->
<!-- 												onkeypress="return numbersonly(event,this.value)" /></td> -->
<%-- 											<td align="right"><spring:message code="BzComposer.Invoice.Tax" /> --%>
<!-- 											</td> -->
<!-- 											<td><select id="tax_id"> -->
<%-- 												<option value="0"><spring:message --%>
<!-- 													code="BizComposer.Estimaion.Tax.Yes" /></option> -->
<%-- 												<option value="1"><spring:message --%>
<!-- 													code="BizComposer.Estimaion.Tax.No" /></option> -->
<!-- 											</select></td> -->

<!-- 										</tr> -->

<!-- 										<tr> -->
<!-- 											<td></td> -->
<!-- 											<td></td> -->
<!-- 											<td></td> -->
<!-- 											<td></td> -->
<!-- 											<td></td> -->
<!-- 											<td></td> -->
<!-- 											<td><br> -->
<!-- 											<input type="button" class="formbutton" name="addItem" -->
<!-- 												title="To add item click it" onclick="AddItem(this.form);" -->
<%-- 												value='<spring:message code="BzComposer.Invoice.AddItem"/>' /> --%>
<!-- 											</td> -->
<!-- 											<td></td> -->
<!-- 											<td></td> -->
<!-- 										</tr> -->
<!-- 									</table> -->
								
<!-- 									</td> -->
<!-- 								</tr> -->

<!-- 							</tbody> -->
<!-- 							</table> -->
<!-- 							</div> -->
<!-- 							</td> -->
<!-- 							</tr>		 -->
								
<!-- 								<tr> -->
					
<!-- 									<td> -->

<%-- 									<div id="ItemDetails"><bean:size id="iSize" name="ItemList" /> --%>
<!-- 									<input type="hidden" name="ItemSize" id="itmSize" -->
<%-- 										value='<bean:write name="iSize" />'> <logic:iterate --%>
<!-- 										name="ItemList" id="objList" indexId="index"> -->
<!-- 										<input type="hidden" -->
<%-- 											value='<bean:write name="objList" property="invID"/>' --%>
<%-- 											id='<bean:write name="index" />inv' /> --%>
<!-- 										<input type="hidden" -->
<%-- 											value='<bean:write name="objList" property="qty"/>' --%>
<%-- 											id='<bean:write name="index" />q' /> --%>
<!-- 										<input type="hidden" -->
<%-- 											value='<bean:write name="objList" property="invCode"/>' --%>
<%-- 											id='<bean:write name="index" />code' /> --%>

<!-- 										<input type="hidden" -->
<%-- 											value='<bean:write name="objList" property="invDesc"/>' --%>
<%-- 											id='<bean:write name="index" />desc' /> --%>
<!-- 										<input type="hidden" -->
<%-- 											value='<bean:write name="objList" property="salePrice"/>' --%>
<%-- 											id='<bean:write name="index" />price' /> --%>
<!-- 										<input type="hidden" -->
<%-- 											value='<bean:write name="objList" property="weight"/>' --%>
<%-- 											id='<bean:write name="index" />wt' /> --%>
<!-- 										<input type="hidden" -->
<%-- 											value='<bean:write name="objList" property="isCategory"/>' --%>
<%-- 											id='<bean:write name="index" />cat' /> --%>
<!-- 										<input type="hidden" -->
<%-- 											value='<bean:write name="objList" property="itemTypeID"/>' --%>
<%-- 											id='<bean:write name="index" />itmId' /> --%>
<!-- 										<input type="hidden" -->
<%-- 											value='<bean:write name="objList" property="serialNo"/>' --%>
<%-- 											id='<bean:write name="index" />serial' /> --%>

<%-- 									</logic:iterate></div> --%>
									
<!-- 									<div id="table-negotiations" -->
<!-- 										style="overflow:auto;height:400;width:100%";" class="section-border"> -->

<!-- 									<table class="tabla-listados" cellspacing="0"> -->

<!-- 										<thead> -->
<!-- 											<tr> -->
<%-- 												<th><spring:message --%>
<!-- 													code="BzComposer.UpdateCustomer.PeriodicBilling.ItemId" /></th> -->
<%-- 												<th><spring:message --%>
<!-- 													code="BzComposer.UpdateCustomer.PeriodicBilling.Qty" /></th> -->
<%-- 												<th><spring:message --%>
<!-- 													code="BzComposer.UpdateCustomer.PeriodicBilling.Description" /></th> -->
<%-- 												<th><spring:message --%>
<!-- 													code="BzComposer.UpdateCustomer.PeriodicBilling.UnitePrice" /></th> -->
<%-- 												<th><spring:message --%>
<!-- 													code="BzComposer.UpdateCustomer.PeriodicBilling.Amount" /></th> -->
<%-- 												<th><spring:message --%>
<!-- 													code="BzComposer.UpdateCustomer.PeriodicBilling.Weight" /></th> -->
<%-- 												<th><spring:message --%>
<!-- 													code="BzComposer.UpdateCustomer.PeriodicBilling.Tax" /></th> -->
<%-- 												<th><spring:message --%>
<!-- 													code="BzComposer.UpdateCustomer.PeriodicBilling.SerialNo" /></th> -->
<!-- 											</tr> -->
<!-- 										</thead> -->
<!-- 										<tbody> -->
<!-- 											<tr id="tr##"> -->
<!-- 												<td align="center"></td> -->
<!-- 												<td align="center"></td> -->
<!-- 												<td align="center"></td> -->
<!-- 												<td align="center"></td> -->
<!-- 												<td align="center"></td> -->
<!-- 												<td align="center"></td> -->
<!-- 												<td align="center"></td> -->
<!-- 												<td align="center"></td> -->
<!-- 											</tr> -->
<!-- 										</tbody> -->
<!-- 									</table> -->
<!-- 									</div> -->

<!-- 									</td> -->
<!-- 								</tr> -->

					

					</div>
					</div>
	</div></div>
				<!--  finance Charges -->

			<table cellpadding="0" cellspacing="0" border="0" align=center  style="width: 100%;">
		<tr>
			<td align="center">
			    <button type="button" class="formbutton" title="Update Customer" onclick="UpdateCust();"><spring:message code='BzComposer.global.update'/></button>
				<button type="button" class="formbutton" title="Close" onclick="closeme();"><spring:message code='BzComposer.global.close'/></button>
				<input type="hidden" name="stname" value="" id="stateId" />
			</td>
		</tr>
	</table>

	
	<div>
	    <input type="hidden" name="AnualRate" id="arate">
	    <input type="hidden" name="MinFinance" id="mfinance">
		<input type="hidden" name="GracePeriod" id="gperiod" />
		<input type="hidden" name="Flag" id="flagId" value="0" />
		<input type="hidden" name="tabid" id="tabid" value="" />
		</div>
		</div>
		</div>
		</div>
		</div>

</form:form>
<input type="hidden" id="default_sid" />
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>
<script>
var radio_val;
window.onunload = refreshParent;
function refreshParent() {
    window.opener.location.reload();
}

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

function writeSelectTH(){
   if (oT.readyState != 4 || oT.status != 200) {
     return;
   }
   document.getElementById("t_history").innerHTML = o.responseText ;
}
function refreshTransationNow(radio_val,custid,dfrom,dto){
   oT = c(writeSelectTH);
   oGET(oT,'Customer?tabid=addTransactionHistory&custId=' + custid+'&cond='+radio_val+'&pfrom='+dfrom+'&pto='+dto)
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

function UpdateCust(){
	if(document.CustomerForm.cname.value==""){

		return showNameDialog();
		document.CustomerForm.cname.focus();
	}
	else if(document.CustomerForm.firstName.value==""){

		return showFirstNameDialog();
		document.CustomerForm.firstName.focus();
	}
	else if(document.CustomerForm.lastName.value==""){

		return showLastNameDialog();
		document.CustomerForm.lastName.focus();
	}
	else if(document.CustomerForm.address1.value==""  ){

		return showAddress1Dialog();
		document.CustomerForm.address1.focus();
	}
	else if(document.CustomerForm.city.value==""){

		return showCityDialog();
		document.CustomerForm.address1.focus();
	}
	else if(document.CustomerForm.zipCode.value==""){

		return showZipCodeDialog();
		document.CustomerForm.zipCode.focus();
	}
	else if(String(document.CustomerForm.cellPhone.value).length>16){

		return showCellPhoneDialog();
		document.CustomerForm.cellPhone.value="";	
		document.CustomerForm.cellPhone.focus();
	}
	else {
		if(validate())
		{
			event.preventDefault();
			$("#updateCustomerDialog").dialog({
                resizable: false,
                height: 200,
                width: 500,
                modal: true,
                buttons: {
                    "<spring:message code='BzComposer.global.ok'/>": function () {
                        document.getElementById('chk1').disabled=false;
                        if(document.getElementById('chk1').checked==true)
                        {
                            document.getElementById('chk1').value="on";
                        }
                        else
                        {
                            document.getElementById('chk1').value="off";
                        }

                        document.getElementById('arate').value=document.CustomerForm.annualIntrestRate.value;
                        document.getElementById('mfinance').value=document.CustomerForm.minFCharges.value;
                        document.getElementById('gperiod').value=document.CustomerForm.gracePrd.value;
                        document.CustomerForm.table_defaultVal.value=$('#default_sid').val();
                        document.getElementById('tabid').value="edit";
                        document.forms[0].action="updateEditedCustomer";
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
                            alert("<spring:message code='BzComposer.common.erroroccurred'/>");
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
		if(flag==1)		{

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
	
				var rd="<input type='radio' id='setdisable' name='defaultService' onclick='setDefault("+invID+",this.form);' />";
				
				td4.innerHTML=rd;
				
			
				var button='<img src="${pageContext.request.contextPath}/images/delete.png" alt="Deletable" onclick=removeFromTable('+invID+'); >';
				var td5 = document.createElement("td");
				td5.setAttribute("align", "left");
				tr.appendChild(td5);
				td5.innerHTML=button;
				
				form.table_size.value++;

				
				form.table_serID.value+=invID+";";


				form.table_serviceName.value+=serviceName+";";
				
				//form.table_invId.value+=InvoiceStyle+";";
				
				form.table_bal.value+=ServiceBalance+";";

	
				if(InvoiceStyle==""){
					form.table_invId.value+=invID+";";
				}
				else{
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

function AddItem(form)
{

	if(form.itemID.value==0){
				document.getElementById('serialNo_id').value="";
				document.getElementById('qty_id').value="";
				document.getElementById('desc_id').value="";
				document.getElementById('unitPrice_id').value="";
				document.getElementById('amount_id').value="";
				document.getElementById('weight_id').value="";

				return showselectitemdialog();
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
			
}

	
	function setDefault(invID1,form)
	{
		form.table_defaultVal.value=invID1;
		$('#default_sid').val(invID1);
	}

	
	function setDefaultToDb(invID1,form)
	{
		form.table_DbDefSer.value=invID1;
		$('#default_sid').val(invID1);
	}



	function removeFromTable(idV)
	{
		if(idV==$('#default_sid').val()){
			$('#default_sid').val(0);
		}

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


	function removeStringValues(key){	//from one string('str'), a substring 'key' will be removed

		var str, str2, str3;
		var temp=new Array(20);
		var temp2=new Array(20);
		var temp3=new Array(20);				
		
		str=str2=str3="";
		
		str= document.forms[0].table_serID.value;		
		str2=document.forms[0].table_bal.value;
		str3=document.forms[0].table_invId.value;
		
		if (str!="" && str2!="" && str3!="") {
			temp=str.split(";");
			temp2=str2.split(";");
			temp3=str3.split(";");		
		}
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

	
function lookUpHistory(){
	val=document.getElementById('hidn').value;
    var dfrom=document.CustomerForm.periodFrom.value;
    var dto=document.CustomerForm.periodTo.value;
    document.getElementById('flagId').value='1';
    <c:if test="${not empty ClientID}">
    	refreshTransationNow(radio_val,'${ClientID}',dfrom,dto);
    </c:if>
    <c:if test="${empty ClientID}">
	    custid=document.CustomerForm.custId.value;
    	refreshTransationNow(radio_val,custid,dfrom,dto);
    </c:if>
}
function setRowId(rowId,rid){
	size=document.getElementById("lSize").value;
	for(i=0;i<size;i++){
		var row1=document.getElementById(i+"$$");
		row1.className = "";
	}
	var rd=document.getElementById(rid);
	rd.className = "draft";
}

function numbersonly(e,val){
	var temp=val.indexOf(".");
	var key=e.charCode? e.charCode : e.keyCode;
	if(window.event){
              	if(window.event.ctrlKey)
                       isCtrl = true;
               else
                   isCtrl = false;
       }
       else{
               if(e.ctrlKey)
                       isCtrl = true;
               else
                       isCtrl = false;
       }
       if(isCtrl){ 
        if("v" == String.fromCharCode(key).toLowerCase()) {
        	return false;
           }
           if("x" == String.fromCharCode(key).toLowerCase()) {
               return false;
           }
       }
		else if (key!=8){
		var str =String(val);
		var temp=val.indexOf(".");
		index=0;		
		for(i=0;i<str.length;i++){
			if(str.charAt(i)=='.'){
				index=1;
				break;
			}
		}
		if(key==46 && temp==-1){
			 return true;
		} 
		else if(key==37 || key==39){
			return true; 	
		}
		else if(key==110 && index==0){
			return true;
		}
		else if(key==190 && index==0){
			return true;
		}
		else if(key>=96 && key<=105){
			return true; 	
		}
		else if (key<48||key>57) //if not a number
			return false; //disable key press
	}
}

flag_state = 0;
function initialize(){
	<c:if test="${CustomerDetails.fsUseIndividual == true}">
    		enableDisableFinanceCharges();
    	</c:if>
	<c:if test="${not empty RadioVal}">
		<c:if test="${RadioVal == 1}">
			hide_details("ShowAll");
			radio_val="ShowAll";
			<c:if test="${not empty ClientID}">
				lookUpHistory();	
			</c:if>
		</c:if>
		<c:if test="${RadioVal == 2}">
			hide_details("ByVal");
			radio_val="ByVal";
			<c:if test="${not empty FlagValue}">
				lookUpHistory();	
			</c:if>
		</c:if>
	</c:if>
	<c:if test="${empty RadioVal}">
		hide_details("ShowAll");
	</c:if>

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
    if(document.CustomerForm.homePage.value == ''){
        document.CustomerForm.homePage.value = 'www.';
    }
}


function CopyBilladdToShipAdd(){
	document.CustomerForm.shcname.value= document.CustomerForm.bscname.value;
	document.CustomerForm.shdbaName.value = document.CustomerForm.bsdbaName.value;
	document.CustomerForm.shfirstName.value= document.CustomerForm.bsfirstName.value;
	document.CustomerForm.shlastName.value= document.CustomerForm.bslastName.value;
	document.CustomerForm.shaddress1.value= document.CustomerForm.bsaddress1.value;
	document.CustomerForm.shaddress2.value= document.CustomerForm.bsaddress2.value;
	document.CustomerForm.shcity.value= document.CustomerForm.bscity.value;
	document.CustomerForm.shzipCode.value = document.CustomerForm.bszipCode.value;
	document.CustomerForm.shprovince.value = document.CustomerForm.bsprovince.value;
	document.CustomerForm.shstate.value = document.CustomerForm.bsstate.value;
	document.CustomerForm.shcountry.value= document.CustomerForm.bscountry.value;
}
function clearBillingAdd(){
	document.CustomerForm.bscname.value="";
	document.CustomerForm.bsdbaName.value="";
	document.CustomerForm.bsfirstName.value="";
	document.CustomerForm.bslastName.value="";
	document.CustomerForm.bsaddress1.value="";
	document.CustomerForm.bsaddress2.value="";
	document.CustomerForm.bscity.value="";
	document.CustomerForm.bszipCode.value="";
	document.CustomerForm.bsprovince.value="";
	document.CustomerForm.bscountry.value="0";
	document.CustomerForm.bsstate.value="0";
}
	
function clearShippingAdd(){
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
	
	function UpdateCustomer(){
	
		val=document.getElementById('hidn_custID').value;
		document.forms[0].action = "UpdateInvoice";
		document.forms[0].submit();
		window.close();
	
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
			document.CustomerForm.bscountry.disabled=false;
			document.CustomerForm.bsprovince.disabled=false;
					
			document.CustomerForm.shcname.disabled=false;
			document.CustomerForm.shdbaName.disabled=false;
			document.CustomerForm.shfirstName.disabled=false;
			document.CustomerForm.shlastName.disabled=false;	
			document.CustomerForm.shaddress1.disabled=false;
			document.CustomerForm.shaddress2.disabled=false;
			document.CustomerForm.shcity.disabled=false;
			document.CustomerForm.shzipCode.disabled=false;
			document.CustomerForm.shcountry.disabled=false;
			document.CustomerForm.shprovince.disabled=false;		
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
			document.CustomerForm.bscountry.disabled=true;
			document.CustomerForm.bsprovince.disabled=true;

			document.CustomerForm.shcname.disabled=true;
			document.CustomerForm.shdbaName.disabled=true;
			document.CustomerForm.shfirstName.disabled=true;
			document.CustomerForm.shlastName.disabled=true;	
			document.CustomerForm.shaddress1.disabled=true;
			document.CustomerForm.shaddress2.disabled=true;
			document.CustomerForm.shcity.disabled=true;
			document.CustomerForm.shzipCode.disabled=true;
			document.CustomerForm.shcountry.disabled=true;
			document.CustomerForm.shprovince.disabled=true;
		    document.CustomerForm.setdefaultbs.value="";
		}
	}

function hide_details(val)
{
	if(val=="ShowAll"){
		document.getElementById('imgfrm').style.visibility='hidden';
		document.getElementById('imgto').style.visibility='hidden';
		document.CustomerForm.periodFrom.disabled=true;
		document.CustomerForm.periodTo.disabled=true;
	}
	if(val=="ByVal"){
		document.getElementById('imgfrm').style.visibility='visible';
		document.getElementById('imgto').style.visibility='visible';
		document.CustomerForm.periodFrom.disabled=false;
		document.CustomerForm.periodTo.disabled=false;
	}
	radio_val=val;
}

function closeme(){
	this.window.close();
}
function fun(val){

}
function ItemChange(value){
	var size = document.getElementById('itmSize').value;
	var count;
	for(count=0;count<size;count++){
		var invID = document.getElementById(count+'inv').value;
		if(value==invID){
			var category = document.getElementById(count+'cat').value;
			if(category==1){
				document.getElementById('qty_id').value="";
				document.getElementById('desc_id').value="";
				document.getElementById('unitPrice_id').value="";
				document.getElementById('amount_id').value="";
				document.getElementById('weight_id').value="";
				document.getElementById('tax_id').value="";
				document.getElementById('serialNo_id').value="";
				
				document.getElementById('qty_id').readonly="true";
				document.getElementById('unitPrice_id').readonly="true";
				document.getElementById('weight_id').readonly="true";
				document.getElementById('code1').value=document.getElementById(count+'code').value;
			}
			else{
				var qty=1;
				var uprice = document.getElementById(count+'price').value;
				var serialNo = document.getElementById(count+'serial').value;
				document.getElementById('serialNo_id').readonly="false";
				document.getElementById('qty_id').readonly="false";
				document.getElementById('unitPrice_id').readonly="false";
				document.getElementById('weight_id').readonly="false";
				
				document.getElementById('serialNo_id').value=serialNo;
				document.getElementById('qty_id').value=qty;
				document.getElementById('desc_id').value=document.getElementById(count+'desc').value;
				document.getElementById('unitPrice_id').value=uprice;
				document.getElementById('amount_id').value=qty*uprice;
				document.getElementById('weight_id').value=document.getElementById(count+'wt').value;
				document.getElementById('code1').value=document.getElementById(count+'code').value;
				document.getElementById('tax_id').value="0";
				//document.getElementById('itmId').value=document.getElementById(count+'itmId').value;
			//	document.getElementById('itmVal').value=value;
			}
		}				
	}
}
function DeleteItem(rowId){
}

function enableDisableFinanceCharges(){
	ch_box=document.getElementById('chbox');
	
	if (ch_box.checked) {
		document.CustomerForm.annualIntrestRate.disabled=false;
		document.CustomerForm.minFCharges.disabled=false;
		document.CustomerForm.gracePrd.disabled=false;
		document.getElementById('chk1').disabled=false;
	}
	else {
		document.CustomerForm.annualIntrestRate.disabled=true;
		document.CustomerForm.minFCharges.disabled=true;
		document.CustomerForm.gracePrd.disabled=true;
		
		document.getElementById('chk1').disabled=true;
	}
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
function enterEmailValidationDialog()
{
	event.preventDefault();
	$("#enterEmailValidationDialog").dialog({
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

function showEmailLengthValidationDialog()
{
	event.preventDefault();
	$("#showEmailLengthValidationDialog").dialog({
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

function showCellPhoneDialog()
{
	event.preventDefault();
	$("#showCellPhoneDialog").dialog({
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

function showselectitemdialog()
{
	event.preventDefault();
	$("#showselectitemdialog").dialog({
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
<!-- dialog box used in this page -->
<div id="showNameDialog" style="display:none;">
	<p><spring:message code="BzComposer.updatecustomer.entercompanyname" /></p>
</div>
<div id="showFirstNameDialog" style="display:none;">
	<p><spring:message code="BzComposer.updatecustomer.enterfirstname" /></p>
</div>
<div id="showLastNameDialog" style="display:none;">
	<p><spring:message code="BzComposer.updatecustomer.enterlastname"/></p>
</div>
<div id="showAddress1Dialog" style="display:none;">
	<p><spring:message code="BzComposer.updatecustomer.enteradderss1"/></p>
</div>
<div id="showCityDialog" style="display:none;">
	<p><spring:message code="BzComposer.updatecustomer.entercity"/></p>
</div>
<div id="showZipCodeDialog" style="display:none;">
	<p><spring:message code="BzComposer.updatecustomer.enterzipcode"/></p>
</div>
<div id="showEmailValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.updatevendor.enteremailvalidation" /></p>
</div>
<div id="enterEmailValidationDialog" style="display:none;">
	<p><spring:message code="Bzcomposer.updatevendor.enteremailaddress"/></p>
</div>
<div id="showEmailLengthValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.updatecustomer.entervalidemaillength" /></p>
</div>
<div id="updateCustomerDialog" style="display:none;">
	<p><spring:message code="BzComposer.updatecustomer.updatecustomerinformation"/></p>
</div>
<div id="showCellPhoneDialog" style="display:none;">
	<p><spring:message code="BzComposer.updatecustomer.cellphonenumberistoolarge"/></p>
</div>
<div id="showServiceValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.updatecustomer.serviceexist"/></p>
</div>
<div id="showselectitemdialog" style="display:none;">
	<p><spring:message code="BzComposer.updatecustomer.selectitemfirst" /></p>
</div>
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
                    <td><input onkeypress="return numbersonly(event,this.value)" id="cardZip" /></td>
                    <td>&nbsp;</td>
                </tr>
            </tbody>
        </table>
        </form>
    </div>
</div>