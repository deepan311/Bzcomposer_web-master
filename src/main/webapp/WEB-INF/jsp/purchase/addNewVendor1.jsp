<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--<%@ page errorPage="/include/sessionExpired.jsp"%>--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.addnewvendortitle" /></title>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<style type="text/css">
.dataTables_length{ font-size:12px; }
.dataTables_filter{ font-size:12px; }
.dataTables_info{ font-size:12px; }
.dataTables_paginate{ font-size:12px; }

.myTable { height: 150px; overflow-y: scroll; }
tr.highlight td { background-color: navy; }
table.sortable thead { background-color: #eee; color: #666666; font-weight: bold; cursor: default; }
table.tabla-listados { width: 100%; border: 1px solid rgb(207, 207, 207); margin: 0px 0px 0px 0px; }
table.tabla-listados tbody tr.odd td { background: #e1e5e9; }
table.tabla-listados thead tr th { font-size: 12px; }
table.tabla-listados tbody tr td { font-size: 12px; }

.errorClass { border: solid 1px #FF0000; }
.redColor {color: red;}
.height150 { height: 180px; width:200px; margin-top:10px; }
.card-text { font-size: 12px! important; color: #6C7590! important; padding: 0px; margin: 5px; }
.defaultCard { margin-top:10px;background: #C3C5CA;border-radius: 5px;padding:5px; }
.mTop10 { margin-top:10px; }
</style>
<script type="text/javascript">
$(document).ready(function(){
    $("#tabs").tabs();
    $('#custTable').DataTable({
        "iDisplayLength": 10,
        "ordering": false,
        "fnDrawCallback": function( oSettings ) {
            setCutomerDataById(0, 0, false);
            hightlightROW();
        }
    });
});
</script>
</head>
<body onload="initialize();">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
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
        <c:choose>
          <c:when test="${empty vendorDto.clientVendorID}">
            <spring:message code="BzComposer.addnewvendor.addnewvendortitle"/>
          </c:when>
          <c:otherwise>
            <spring:message code="BzComposer.updatevendor.editvendorinformation"/>
          </c:otherwise>
        </c:choose>
        <c:if test="${not empty actionMsg}">
            <br/> ${actionMsg}
            <% session.removeAttribute("actionMsg"); %>
        </c:if>
    </span>
</div>
<div id="ItemListArea" class="float-left" style="width:42%;">
    <table id="custTable" class="tabla-listados sortable" cellspacing="0" style="width: 100%; margin-top: 10px; border: 0; padding: 0;height: auto;" align="center">
        <thead>
        <tr valign="top">
            <th align="center"><spring:message code="BzComposer.customerinfo.customer"/></th>
            <th align="center"><spring:message code="BzComposer.global.company"/></th>
            <th align="center"><spring:message code="BzComposer.global.address1"/></th>
            <th align="center"><spring:message code="BzComposer.global.city"/></th>
        </tr>
        </thead>
        <tbody>
            <c:if test="${not empty VendorList}">
                <c:forEach items="${VendorList}" var="objList" varStatus="loop">
                    <tr id='${loop.index}$$' onclick="setCutomerDataById(${objList.clientVendorID}, ${loop.index}, true);">
                        <td>${objList.firstName} ${objList.middleName} ${objList.lastName}</td>
                        <td>${objList.cname}</td>
                        <td>${objList.address1}</td>
                        <td>${objList.city}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
    <input type="hidden" id="lSize" value='${VendorList.size()}' />
</div>
<div id="addNewItemArea" class="float-right" style="width:55%;">
	<div id="tabs" style="min-height:550px;">
		<ul>
		    <li style="font-size:12px;"><a href="#General-1"><spring:message code="BzComposer.updatevendor.tabs.general" /></a></li>
		    <li style="font-size:12px;"><a href="#payment-2"><spring:message code="BzComposer.addnewcustomer.tabs.paymentInformation" /></a></li>
            <li style="font-size:12px;"><a href="#finance-4"><spring:message code="BzComposer.updatevendor.tabs.financecharges" /></a></li>
            <li style="font-size:12px;" id="Transactional-50"><a href="#Transactional-5"><spring:message code="BzComposer.updatecustomer.tabs.transactionhistory" /></a></li>
		</ul>
		<div id="General-1">
   			<div id="content1" class="tabPage">
   				<!-- add here the content of first tab -->
				<div id="table-negotiations">
					<table class="tabla-listados">
						<thead>
							<tr>
								<th colspan="5" >
									<spring:message code="BzComposer.updatevendor.vendorinformation"/>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							    <td style="width:18%;">
                                    <spring:message code="BzComposer.global.titlename" />
                                </td>
                                <td style="width:31%;">
                                    <form:select path="title">
                                        <form:options items="${titleList}" itemValue="value" itemLabel="label" />
                                    </form:select>
                                </td>
                                <td style="width:18%;">
                                    <spring:message code="BzComposer.global.customerid" />
                                </td>
                                <td style="width:31%;">
                                    <form:input path="clientVendorID" readonly="true" />
                                </td>
								<td style="width:2%;">&nbsp;</td>
						    </tr>
						    <tr>
								<td>
                                    <spring:message code="BzComposer.global.firstname" />
                                    <span class="inputHighlighted">
                                        <spring:message code="BzComposer.CompulsoryField.Validation" />
                                    </span>
                                </td>
                                <td>
                                    <form:input type="text" path="firstName" />
                                </td>
								<td>
									<spring:message code="BzComposer.global.middlename" />
								</td>
								<td>
									<form:input type="text" path="middleName" />
								</td>
								<td>&nbsp;</td>
						    </tr>
						    <tr>
							    <td>
                                    <spring:message code="BzComposer.global.lastname" />
                                    <span class="inputHighlighted">
                                        <spring:message code="BzComposer.CompulsoryField.Validation" />
                                    </span>
                                </td>
                                <td>
                                    <form:input type="text" path="lastName" />
                                </td>
                                <td>
                                    <spring:message code="BzComposer.global.inputdate" />
                                </td>
                                <td>
                                    <form:input path="dateInput" readonly="true" />
                                    <img src="${pageContext.request.contextPath}/images/cal.gif"
                                        onclick="displayCalendar(document.VendorForm.dateInput,'mm-dd-yyyy',this);">
                                </td>
                                <td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<spring:message code="BzComposer.global.company" />
									<span class="inputHighlighted">
										<spring:message code="BzComposer.CompulsoryField.Validation" />
									</span>
								</td>
								<td colspan="3">
									<form:input type="text" path="cname" style="width:90%;" />
								</td>
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
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
								<td>
									<spring:message code="BzComposer.global.address2" />
								</td>
								<td colspan="3">
									<form:input type="text" path="address2" style="width:90%;" />
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
							    <td class="zipcodeShow">
                                    <spring:message code="BzComposer.global.zipcode" />
                                    <span class="inputHighlighted">
                                        <spring:message code="BzComposer.CompulsoryField.Validation" />
                                    </span>
                                </td>
                                <td class="postalcodeShow">
                                    <spring:message code="BzComposer.global.postalcodes" />
                                    <span class="inputHighlighted">
                                        <spring:message code="BzComposer.CompulsoryField.Validation" />
                                    </span>
                                </td>
                                <td>
                                    <form:input type="text" onkeypress="return numbersonly(event,this.value)" path="zipCode" />
                                </td>
                                <td>
                                    <spring:message code="BzComposer.global.city" />
                                    <span class="inputHighlighted">
                                        <spring:message code="BzComposer.CompulsoryField.Validation" />
                                    </span>
                                </td>
                                <td>
                                    <form:input type="text" path="city" />
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
							    <td id="t_state" class="stateShow">
                                    <spring:message code="BzComposer.global.state" />
                                </td>
                                <td id="t_statedata" class="stateShow stateClass1"></td>
                                <td class="provinceShow">
                                    <spring:message code="BzComposer.global.province" />
                                </td>
                                <td class="provinceShow">
                                    <form:input type="text" path="province" />
                                </td>
							    <td id="t_country" >
                                    <spring:message code="BzComposer.global.country" />
                                </td>
                                <td>
                                    <form:select path="country" onchange="refreshItemsNow(this.value);">
                                        <form:option value="0">
                                            <spring:message code="BzComposer.ComboBox.Select" />
                                        </form:option>
                                        <form:options items="${cList}" itemValue="value" itemLabel="label" />
                                    </form:select>
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
								<td>
									<spring:message code="BzComposer.global.phone" />
								</td>
								<td>
									<form:input type="text" onkeypress="return numbersonly(event,this.value)" path="phone" maxlength="15" />
								</td>
								<td>
									<spring:message code="BzComposer.global.mobileNumber" />
								</td>
								<td>
									<form:input type="text" onkeypress="return numbersonly(event,this.value)" path="cellPhone" maxlength="16" />
									<form:checkbox path="isMobilePhoneNumber" />
                                    (<spring:message code="BzComposer.global.isPhoneNumber" />)
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
                                    <spring:message code="BzComposer.global.fax" />
                                </td>
                                <td>
                                    <form:input type="text" path="fax" onkeypress="return numbersonly(event,this.value)" />
                                </td>
								<td>
									<spring:message code="BzComposer.global.email" />
								</td>
								<td>
									<form:input type="text" path="email" maxlength="45" />
								</td>
								<td>&nbsp;</td>
						    </tr>
						    <tr>
								<td>
                                    <spring:message code="BzComposer.global.homepage" />
                                </td>
                                <td>
                                    <form:input type="text" path="homePage" />
                                </td>
                                <td>
                                    <spring:message code="BzComposer.global.dateadded" />
                                </td>
                                <td>
                                    <form:input type="text" path="dateAdded" readonly="true" />
                                    <img src="${pageContext.request.contextPath}/images/cal.gif"
                                        onclick="displayCalendar(document.VendorForm.dateAdded,'mm-dd-yyyy',this);">
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
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
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" name="isTaxable" id="chktax" ${vendorDto.taxAble==1?'checked':''} />
									<spring:message code="BzComposer.global.istaxable" />
								</td>
								<td>
									<input type="checkbox" name="isAlsoClient" id="chk_alsovendor" ${vendorDto.isclient==1?'checked':''} />
									<spring:message code="BzComposer.global.isalsoclient" />
								</td>
							    <td>
                                    <spring:message code="BzComposer.global.oppeningunpaidbalance" />
                                </td>
                                <td>
                                    <form:input type="text" path="openingUB" onkeypress="return numbersonly(event,this.value)" />
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
								<td>
									<spring:message code="BzComposer.global.existingcredits" />
								</td>
								<td>
									<form:input type="text" path="extCredit" onkeypress="return numbersonly(event,this.value)" />
								</td>
								<td colspan="3">&nbsp;</td>
						    </tr>
                            <tr>
								<td>
									<spring:message code="BzComposer.global.memo" />
								</td>
								<td  colspan="3">
                                    <form:textarea path="memo" style="width:90%;" />
								</td>
								<td>&nbsp;</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- Account info Start here -->
                <div id="table-negotiations" style="width:100%">
                    <table class="tabla-listados" cellspacing="0">
                        <thead>
                            <tr>
                                <th colspan="4" >
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
                                    <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                    <c:forEach items="${TermList}" var="obj">
                                        <form:option value="${obj.term}">${obj.name}</form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                            <td>
                                <spring:message code="BzComposer.global.paymentmethod" />
                            </td>
                            <td>
                                <form:select path="paymentType" style="width:150px;">
                                    <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                    <c:forEach items="${PaymentList}" var="obj">
                                        <form:option value="${obj.id}">${obj.typeName}</form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <spring:message code="BzComposer.global.representative" />
                            </td>
                            <td>
                                <form:select path="rep" style="width:150px;">
                                    <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                    <c:forEach items="${RepList}" var="obj">
                                        <form:option value="${obj.value}">${obj.label}</form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                            <td>
                                <spring:message code="BzComposer.global.shippingvia" />
                            </td>
                            <td>
                                <form:select path="shipping" style="width:150px;">
                                    <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                    <c:forEach items="${ShipCarrierList}" var="obj">
                                        <form:option value="${obj.value}">${obj.label}</form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
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
								<th colspan="3"><spring:message code="BzComposer.updatevendor.manageaddresses"/></th>
							</tr>
							</thead>
							<thead>
								<tr>
									<td colspan="3">
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
												<th colspan="2" >
													<spring:message code="BzComposer.updatevendor.billingaddress"/>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<spring:message code="BzComposer.global.company" />
												</td>
												<td>
													<form:input path="bscname" disabled="true" style="width:90%;" />
												</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.global.firstname" />
												</td>
												<td>
													<form:input path="bsfirstName" disabled="true"/>
												</td>
											</tr><tr>
												<td>
													<spring:message code="BzComposer.global.lastname" />
												</td>
												<td>
													<form:input path="bslastName" disabled="true"/>
												</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.global.address1" />
												</td>
												<td>
													<form:input path="bsaddress1" disabled="true" style="width:90%;" />
												</td>
                                            </tr>
                                            <tr>
												<td>
													<spring:message code="BzComposer.global.address2" />
												</td>
												<td>
													<form:input path="bsaddress2" disabled="true" style="width:90%;" />
												</td>
											</tr>
											<tr>
												<td class="zipcodeShow2">
													<spring:message code="BzComposer.global.zipcode" />
												</td>
												<td class="postalcodeShow2">
												    <spring:message code="BzComposer.global.postalcodes" />
												</td>
												<td>
													<form:input path="bszipCode" onkeypress="return numbersonly(event,this.value)" disabled="true"/>
												</td>
											</tr><tr>
												<td>
                                                    <spring:message code="BzComposer.global.city" />
                                                </td>
                                                <td>
                                                    <form:input path="bscity"  disabled="true"/>
                                                </td>
											</tr>
											<tr>
											    <td id="t_state"  class="stateShow2">
                                                    <spring:message code="BzComposer.global.state" />
                                                </td>
                                                <td id="t_statedata1"  class="stateShow2 stateClass2"></td>
                                                <td  class="provinceShow2">
                                                    <spring:message code="BzComposer.global.province" />
                                                </td>
                                                <td  class="provinceShow2">
                                                    <form:input path="bsprovince"  disabled="true"/>
                                                </td>
                                            </tr><tr>
												<td id="t_country" >
													<spring:message code="BzComposer.global.country" />
												</td>
												<td>
													<form:select  disabled="true" path="bscountry" onchange="refreshItemsNow1(this.value);">
														<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
														<form:options items="${cList}" itemValue="value" itemLabel="label"/>
													</form:select>
												</td>
											</tr>
											<tr>
												<td colspan="2" align="center" >
													<input type="button" class="formbutton" name="ClearBillingBtn" onclick="clearBillingAdd();"
													    value="<spring:message code="BzComposer.updatevendor.clearbillingaddress" />" />
												</td>
											</tr>
										</tbody>
									</table>
								</td>
								<td style="vertical-align: inherit;">
									<input type="button" name="CopyBtn" class="formbutton" onclick="CopyBilladdToShipAdd();"
									    value="<spring:message code='BzComposer.updatevendor.copyto'/>" title="Copy Address" />
								</td>
								<td>
									<table class="tabla-listados" cellspacing="0" width="450" align="left">
										<thead>
											<tr>
												<th colspan="2">
													<spring:message code="BzComposer.updatevendor.shippingaddress"/>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<spring:message code="BzComposer.global.company" />
												</td>
												<td>
													<form:input path="shcname" disabled="true" style="width:90%;" />
												</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.global.firstname" />
												</td>
												<td>
													<form:input path="shfirstName" disabled="true"/>
												</td>
										    </tr><tr>
												<td>
													<spring:message code="BzComposer.global.lastname" />
												</td>
												<td>
													<form:input path="shlastName" disabled="true"/>
												</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.global.address1" />
												</td>
												<td>
													<form:input path="shaddress1" disabled="true" style="width:90%;" />
												</td>
                                            </tr>
                                            <tr>
												<td>
													<spring:message code="BzComposer.global.address2" />
												</td>
												<td>
													<form:input path="shaddress2" disabled="true" style="width:90%;" />
												</td>
											</tr>
											<tr>
												<td class="zipcodeShow3">
												    <spring:message code="BzComposer.global.zipcode" />
												</td>
												<td class="postalcodeShow3">
													<spring:message code="BzComposer.global.postalcodes" />
												</td>
												<td>
													<form:input path="shzipCode" onkeypress="return numbersonly(event,this.value)" disabled="true"/>
												</td>
											</tr><tr>
												<td>
                                                    <spring:message code="BzComposer.global.city" />
                                                </td>
                                                <td>
                                                    <form:input path="shcity" disabled="true"/>
                                                </td>
											</tr>
											<tr>
											    <td id="t_state" class="stateShow3">
                                                    <spring:message code="BzComposer.global.state" />
                                                </td>
                                                <td id="t_statedata2"  class="stateShow3 stateClass3"></td>
                                                <td  class="provinceShow3">
                                                    <spring:message code="BzComposer.global.province" />
                                                </td>
                                                <td  class="provinceShow3">
                                                    <form:input path="shprovince" disabled="true" />
                                                </td>
                                            </tr><tr>
												<td id="t_country" >
													<spring:message code="BzComposer.global.country" />
												</td>
												<td>
													<form:select path="shcountry" disabled="true" onchange="refreshItemsNow2(this.value);">
														<form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                        <c:forEach items="${cList}" var="obj">
                                                            <form:option value="${obj.value}">${obj.label}</form:option>
                                                        </c:forEach>
													</form:select>
												</td>
											</tr>
											<tr>
												<td colspan="2" align="center" >
													<input type="button" class="formbutton" name="ClearShippingBtn" onclick="clearShippingAdd();"
													value="<spring:message code='BzComposer.updatevendor.clearshippingaddress'/>"
													title="<spring:message code='BzComposer.updatevendor.clearshippingaddress'/>">
												</td>
											</tr>
										</tbody>
									</table>
								</td>
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
                                <th colspan="4">
                                    <spring:message code="BzComposer.addnewcustomer.creditcardinfo" />
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="hideShowAddCardDetails">
                                <td>
                                    <spring:message code="BzComposer.global.cardtype" />
                                </td>
                                <td>
                                    <form:select path="ccType" style="width:99%;">
                                        <form:option value="">
                                            <spring:message code="BzComposer.ComboBox.Select" />
                                        </form:option>
                                        <form:options items="${CreditCardList}" itemValue="value" itemLabel="label"/>
                                    </form:select>
                                </td>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr class="hideShowAddCardDetails">
                                <td>
                                    <spring:message code="BzComposer.global.cardnumber" />
                                </td>
                                <td>
                                    <form:input path="cardNo" style="width:99%;" onkeypress="return numbersonly(event,this.value);" />
                                </td>
                                <td>
                                    <spring:message code="BzComposer.global.expdate" />
                                </td>
                                <td>
                                    <form:input path="expDate" readonly="true" />
                                    <img src="${pageContext.request.contextPath}/images/cal.gif"
                                        onclick="displayCalendar(document.VendorForm.expDate,'mm/yyyy',this);">
                                    &nbsp;&nbsp;&nbsp;
                                    <spring:message code="BzComposer.global.mformate" />
                                </td>
                            </tr>
                            <tr class="hideShowAddCardDetails">
                                <td>
                                    <spring:message code="BzComposer.global.CW2" />
                                </td>
                                <td>
                                    <form:input path="cw2" style="width:100px;" />
                                </td>
                                <td>
                                    <spring:message code="BzComposer.global.cardholdername" />
                                </td>
                                <td>
                                    <form:input path="cardHolderName" />
                                </td>
                            </tr>
                            <tr class="hideShowAddCardDetails">
                                <td>
                                    <spring:message code="BzComposer.global.zipcode" />
                                </td>
                                <td>
                                    <form:input onkeypress="return numbersonly(event,this.value)" path="cardZip" />
                                </td>
                                <td>
                                    <spring:message code="BzComposer.addnewcustomer.billingaddress"/>
                                </td>
                                <td>
                                    <form:input path="cardBillAddress" style="width:99%;" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <div class="container text-center">
                                        <div class="row text-left" id="customerCards">
                                            <c:forEach items="${vendorDto.customerCards}" var="objList">
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
                                                            <a href="#" onclick="makeCustomerCardDefault(${objList.clientVendorID}, ${objList.cardID})" style="color: #007bff;">
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
                                    <input type="checkbox" id="chk_useind" name="UseIndividualFinanceCharges" onclick="enableDisableFinanceCharges();">
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
                                    <input type="button" class="formbutton" onclick="lookUpHistory();" value="<spring:message code='BzComposer.updatevendor.lookhistory'/>" />
                                </td>
                            </tr>
                        </table>
                        <div id="t_history">
                        </div>
                    </div>
                </div>
            </div>
            <!-- Transactional-History END -->

            </div>
        <!--  Save Button-->
        <table cellpadding="0" cellspacing="0" border="0" align="center"  style="width: 100%;margin-top:5px;">
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
					<button type="button" class="formbutton" style="padding:7 15px;" onclick="reloadPage();"><spring:message code='BzComposer.global.cancel' /></button>
					<button type="button" class="formbutton" style="padding:7 15px;" onclick="SaveVendorData();"><spring:message code="BzComposer.global.save"/></button>
					<input type="hidden" name="stname" value="" id="stateId">
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
    <input type="hidden" name="tabid" id="tab" value="VONODO" />
    <input type="hidden" name="bst" id="bsst" value="0" />
    <input type="hidden" name="state" value="0" />
    <input type="hidden" name="bsstate" value="0" />
    <input type="hidden" name="shstate" value="0" />
    <input type="hidden" name="tabid" id="tabid" value="" />
    <input type="hidden" name="Flag" id="flagId" value="0" />
    <input type="hidden" name="itemIndex" id="itemIndex" value="${itemIndex}" />
</div>
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" />
<script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
</body>
</html>

<script>
let itemID = 0;
let itemIndex;
var funsequence = 0;
var _1 = navigator.userAgent.toLowerCase();
var ___ = (_1.indexOf("msie") != -1);
var ___5 = (_1.indexOf("msie 5") != -1);
var _io = (_1.indexOf("opera") != -1);
var _im = (_1.indexOf("mac") != -1);
var ____gi = (_1.indexOf("gecko") != -1);
var i____s = (_1.indexOf("safari") != -1);
var o = null;
var o1 = null;
var o2 = null;
var o3 = null;
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
  } catch (ex) { }
}

function initialize(){
    refreshItemsNow(0);
    refreshItemsNow1(0);
    refreshItemsNow2(0);
    refreshItemsNow1($("#bscountry").val());
    refreshItemsNow2($("#shcountry").val());
    refreshItemsNow($("#country").val());
    <c:if test="${vendorDto.fsUseIndividual == true}">
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
    if(document.VendorForm.clientVendorID.value){
        hideShowExitingUserTabs(true);
    }else{
        hideShowExitingUserTabs(false);
    }
    itemIndex = document.getElementById('itemIndex').value;
    hightlightROW();
}
function hightlightROW(){
    let currROW2 = document.getElementById(itemIndex+'$$');
    if(currROW2 != null){
        currROW2.className = "draft";
    }
}
function hideShowExitingUserTabs(isExit){
    if(isExit){
        $('.hideShowAddCardDetails').hide();
        $('#customerCards').show();
        $('#Transactional-50').show();
    }else{
        $('.hideShowAddCardDetails').show();
        $('#customerCards').hide();
        $('#Transactional-50').hide();
    }
}
function hide_details(val){
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

function lookUpHistory(){
	var dfrom = document.VendorForm.periodFrom.value;
    var dto = document.VendorForm.periodTo.value;
    document.getElementById('flagId').value='1';
    <c:if test="${not empty ClientID}">
    	refreshTransationNow(radio_val,'${ClientID}',dfrom,dto);
    </c:if>
    <c:if test="${empty ClientID}">
	    custid=document.VendorForm.clientVendorID.value;
    	refreshTransationNow(radio_val, custid, dfrom, dto);
    </c:if>
}

function writeSelect(){
   if (o.readyState != 4 || o.status != 200) {
      return;
   }
   document.getElementById("t_statedata").innerHTML = o.responseText ;
}
function refreshItemsNow(val){
    debugger;
    if(val == "3"){
        $(".provinceShow").show();
        $(".postalcodeShow").show();
        $(".stateShow").hide();
        $(".zipcodeShow").hide();
    }else {
        $(".provinceShow").hide();
        $(".postalcodeShow").hide();
        $(".stateShow").show();
        $(".zipcodeShow").show();
    }
    o = c(writeSelect);
    oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=state&Cid=' + val)
}
function writeSelect1(){
    debugger;
   if (o1.readyState != 4 || o1.status != 200) {
      return;
    }
    document.getElementById("t_statedata1").innerHTML = o1.responseText ;
}
function refreshItemsNow1(val){
    if(val == "3"){
        $(".provinceShow2").show();
        $(".postalcodeShow2").show();
        $(".stateShow2").hide();
        $(".zipcodeShow2").hide();
    }else {
        $(".provinceShow2").hide();
        $(".postalcodeShow2").hide();
        $(".stateShow2").show();
        $(".zipcodeShow2").show();
    }
  o1 = c(writeSelect1);
  oGET(o1,'${pageContext.request.contextPath}/include/GetStates.jsp?st=bsstate&Cid=' + val)
}
function writeSelect2(){
   if (o2.readyState != 4 || o2.status != 200) {
      return;
    }
   document.getElementById("t_statedata2").innerHTML = o2.responseText ;
}
function refreshItemsNow12(val,sval){
  o2 = c(writeSelect2);
  oGET(o2,'${pageContext.request.contextPath}/include/GetStates.jsp?st=shstate&Cid=' + val+"&sval="+sval)
}
function refreshItemsNow2(val)
{
    if(val == "3"){
        $(".provinceShow3").show();
        $(".postalcodeShow3").show();
        $(".stateShow3").hide();
        $(".zipcodeShow3").hide();
    }else {
        $(".provinceShow3").hide();
        $(".postalcodeShow3").hide();
        $(".stateShow3").show();
        $(".zipcodeShow3").show();
    }
  o2 = c(writeSelect2);
  oGET(o2,'${pageContext.request.contextPath}/include/GetStates.jsp?st=shstate&Cid=' + val)
}

function validate() {
  	var email = document.getElementById('email').value
  	var mail =String(email);
  	var pattern=/^[_0-9a-zA-z]+(\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\.[A-Za-z]+)+$/;
  	if(email==""){
  	}
    else if (!pattern.test(email))
    {

        return showInvalidEmailDialog();
        document.VendorForm.email.focus();
        return false;
    }

   	return true;
  }
  function setState(state_id,name){
	if(name == 'state'){
		document.VendorForm.state.value = state_id;
	}
	else if(name == 'bsstate'){
		document.VendorForm.bsstate.value = state_id;
		document.getElementById('bsst').value = state_id;
	}
	else if(name == 'shstate'){
		document.VendorForm.shstate.value = state_id;
	}
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
	document.VendorForm.shstate.value = document.getElementById('bsst').value;
	refreshItemsNow12(document.VendorForm.bscountry.value, document.getElementById('bsst').value);
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
function clearShippingAdd(){
	document.VendorForm.shcname.value="";
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

function reloadPage() {
    location.reload();
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
	else if(trim(document.getElementById('city').value)==""){
		document.getElementById('city').focus();
		return showblanklcityvalidationdialog();
	}
	else if(trim(document.getElementById('zipCode').value)==""){
		document.getElementById('zipCode').focus();
		return showblankzipcodevalidationdialog();
	}
	else {
		if(validate()){
			debugger;
            event.preventDefault();
            $("#saveCustomerInfoDialog").dialog({
                resizable: false,
                height: 200,
                width: 500,
                modal: true,
                buttons: {
                    "<spring:message code='BzComposer.global.ok'/>": function () {
                        debugger;
                        $(this).dialog("close");
                        if(document.VendorForm.chk_setaddress.checked){
                            hidebsaddress(document.VendorForm);
                        }else{
                            hidebsaddress(document.VendorForm);
                            hidebsaddress(document.VendorForm);
                        }
                        document.getElementById('tabid').value="AOVODO";
                        let myURL = "Vendor?tabid=AOVODO&itemIndex="+itemIndex;
                        if(document.VendorForm.clientVendorID.value){
                            myURL = "Vendor?tabid=updateVendor&itemIndex="+itemIndex;
                        }
                        document.forms["frmAddVendor"].action = myURL;
                        document.forms["frmAddVendor"].submit();
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

function enableDisableFinanceCharges(){
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
		document.VendorForm.bsfirstName.disabled=false;
		document.VendorForm.bslastName.disabled=false;	
		document.VendorForm.bsaddress1.disabled=false;
		document.VendorForm.bsaddress2.disabled=false;
		document.VendorForm.bscity.disabled=false;
		document.VendorForm.bszipCode.disabled=false;
		document.VendorForm.bscountry.disabled=false;
		document.VendorForm.bsprovince.disabled=false;
				
		document.VendorForm.shcname.disabled=false;
		document.VendorForm.shfirstName.disabled=false;
		document.VendorForm.shlastName.disabled=false;	
		document.VendorForm.shaddress1.disabled=false;
		document.VendorForm.shaddress2.disabled=false;
		document.VendorForm.shcity.disabled=false;
		document.VendorForm.shzipCode.disabled=false;
		document.VendorForm.shcountry.disabled=false;
		document.VendorForm.shprovince.disabled=false;		
	    document.VendorForm.setdefaultbs.value="0";		
	}
	else
	{
		document.VendorForm.bscname.disabled=true;
		document.VendorForm.bsfirstName.disabled=true;
		document.VendorForm.bslastName.disabled=true;	
		document.VendorForm.bsaddress1.disabled=true;
		document.VendorForm.bsaddress2.disabled=true;
		document.VendorForm.bscity.disabled=true;
		document.VendorForm.bszipCode.disabled=true;
		document.VendorForm.bscountry.disabled=true;
		document.VendorForm.bsprovince.disabled=true;
		
		document.VendorForm.shcname.disabled=true;
		document.VendorForm.shfirstName.disabled=true;
		document.VendorForm.shlastName.disabled=true;	
		document.VendorForm.shaddress1.disabled=true;
		document.VendorForm.shaddress2.disabled=true;
		document.VendorForm.shcity.disabled=true;
		document.VendorForm.shzipCode.disabled=true;
		document.VendorForm.shcountry.disabled=true;
		document.VendorForm.shprovince.disabled=true;
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
$(function() {
    $("#zipCode").focusout(function(){
        let zipCode = $("#zipCode").val();
        $.ajax({
            type: "POST",
            url:"CustomerAjax?tabid=zipcode&zipcode="+zipCode,
            data :"zipCode=" + zipCode,
            success : function(data) {

                $("#city").val(data.city);
                $(".stateClass1 option:contains('"+data.stateName+"')").attr('selected', 'selected');
            },
            error : function(data) {
                alert("<bean:message key='BzComposer.common.erroroccurred'/>");
            }
        });
    });
    $("#bszipCode").focusout(function(){
        let zipCode = $("#bszipCode").val();
        $.ajax({
            type: "POST",
            url:"CustomerAjax?tabid=zipcode&zipcode="+zipCode,
            data :"zipCode=" + zipCode,
            success : function(data) {
                $("#bscity").val(data.city);
                $(".stateClass2 option:contains('"+data.stateName+"')").attr('selected', 'selected');
            },
            error : function(data) {
                alert("<bean:message key='BzComposer.common.erroroccurred'/>");
            }
        });
    });
    $("#shzipCode").focusout(function(){
        let zipCode = $("#shzipCode").val();
        $.ajax({
            type: "POST",
            url:"CustomerAjax?tabid=zipcode&zipcode="+zipCode,
            data :"zipCode=" + zipCode,
            success : function(data) {
                $("#shcity").val(data.city);
                $(".stateClass3 option:contains('"+data.stateName+"')").attr('selected', 'selected');
            },
            error : function(data) {
                alert("<bean:message key='BzComposer.common.erroroccurred'/>");
            }
        });
    });
});

function setCutomerDataById(rowID, rowIndex, flag) {
    let lSize = document.getElementById("lSize").value;
    for (i=0; i<lSize; i++) {
        let currROW = document.getElementById(i+'$$');
        if(currROW == null) continue;
        if(i%2 == 1){
            currROW.className = "odd";
        }else{
            currROW.className = "even";
        }
    }
    if(flag){
        itemID = rowID;
        itemIndex = rowIndex;
        if(rowIndex%2 == 1){ ;
            document.getElementById(rowIndex+"$$").classList.remove('odd');
        }
        document.getElementById(rowIndex+'$$').classList.add('draft');
        $.ajax({
            type: "POST",
            url:"CustomerAjax?tabid=getCustomerDetails&cvId="+rowID+"&selectedRID="+rowIndex,
            data:{clientVendorID : rowID, selectedRowID : rowIndex},
            success : function(data) {
                $('#clientVendorID').val(data.clientVendorID);
                $('#title').val(data.title);
                $('#firstName').val(data.firstName);
                $('#middleName').val(data.middleName);
                $('#lastName').val(data.lastName);
                $('#dateInput').val(data.dateInput);
                $('#cname').val(data.cname);
                $('#address1').val(data.address1);
                $('#address2').val(data.address2);
                $('#country').val(data.country);
                $('#t_statedata').val(data.state);
                $('#city').val(data.city);
                $('#zipCode').val(data.zipCode);
                $('#province').val(data.province);
                $('#phone').val(data.phone);
                $('#cellPhone').val(data.cellPhone);
                document.VendorForm.isMobilePhoneNumber.checked = data.isMobilePhoneNumber;
                //$('#isPhoneMobileNumber').prop( "checked", data.isPhoneMobileNumber?true:false );
                $('#homePage').val(data.homePage);
                $('#fax').val(data.fax);
                $('#email').val(data.email);
                $('#dateAdded').val(data.dateAdded);
                $('#terminatedDate').val(data.terminatedDate);
                document.VendorForm.terminated.checked = data.terminated;
                $('#type').val(data.type);
                $('#texID').val(data.texID);
                $('#openingUB').val(data.openingUB);
                document.VendorForm.isTaxable.checked = data.taxAble==1?true:false;
                document.VendorForm.isAlsoClient.checked = data.isclient==1?true:false;
                $('#extCredit').val(data.extCredit);
                $('#term').val(data.term);
                $('#paymentType').val(data.paymentType);
                $('#rep').val(data.rep);
                $('#shipping').val(data.shipping);
                $('#memo').val(data.memo);
                $('#ccType').val(data.ccType);
                $('#cardNo').val(data.cardNo);
                $('#expDate').val(data.expDate);
                $('#cw2').val(data.cw2);
                $('#cardHolderName').val(data.cardHolderName);
                $('#cardBillAddress').val(data.cardBillAddress);
                $('#cardZip').val(data.cardZip);
                $('#bscname').val(data.bscname);
                $('#bsprovince').val(data.bsprovince);
                $('#bsfirstName').val(data.bsfirstName);
                $('#bslastName').val(data.bslastName);
                $('#bsaddress1').val(data.bsaddress1);
                $('#bsaddress2').val(data.bsaddress2);
                $('#bscity').val(data.bscity);
                $('#bszipCode').val(data.bszipCode);
                $('#bscountry').val(data.bscountry);
                $('#shcname').val(data.shcname);
                $('#shprovince').val(data.shprovince);
                $('#shfirstName').val(data.shfirstName);
                $('#shlastName').val(data.shlastName);
                $('#shaddress1').val(data.shaddress1);
                $('#shaddress2').val(data.shaddress2);
                $('#shcity').val(data.shcity);
                $('#shzipCode').val(data.shzipCode);
                $('#shcountry').val(data.shcountry);
                $('#annualIntrestRate').val(data.annualIntrestRate);
                $('#minFCharges').val(data.minFCharges);
                $('#gracePrd').val(data.gracePrd);
                //$('#aaaa').html(data.aaaa);

                let cardsHtml = '';
                for(let x=0; x<data.customerCards.length; x++){
                    let card = data.customerCards[x];
                    cardsHtml = cardsHtml+'<div class="col-sm-4"><div class="card height150"><div class="card-body">'
                        +'<h6 class="card-subtitle">'+card.ccTypeName+'</h6>'
                        +'<p class="card-text mTop10"><spring:message code="BzComposer.global.expdate" />: <span style="color: #6C7590! important;" id="expDate1">'+card.expDate+'</span></p>'
                        +'<p class="card-text"><spring:message code="BzComposer.global.cardnumber"/>: <span style="color: #6C7590! important;" id="cardNo1">'+card.cardNo+'</span></p>'
                        +'<p class="card-text"><spring:message code="BzComposer.addnewcustomer.billingaddress"/>: <span style="color: #6C7590! important;" id="cardBillAddress1">'+card.cardBillAddress+'</span></p>';
                    if(card.cardDefault){
                        cardsHtml = cardsHtml+'<p class="card-text float-right defaultCard"><strong><spring:message code="BzComposer.global.default"/></strong></p>';
                    }else{
                        cardsHtml = cardsHtml+'<p class="card-text float-right"><a href="#" onclick="makeCustomerCardDefault('+card.clientVendorID+', '+card.cardID+')"><strong><spring:message code="BzComposer.global.default"/></strong></a></p>';
                    }
                    cardsHtml = cardsHtml+'</div></div></div>';
                }
                cardsHtml = cardsHtml+'<div class="col-sm-4"><div class="card height150"><div class="card-body"><a href="#" onclick="showAddPaymentMethodDialog();">'
                    + '<h6 class="card-subtitle text-center" style="margin-top:40px;color: #05A9C5 !important;"><spring:message code="BzComposer.banking.addPaymentMethod"/></h6>'
                    + '</a></div></div></div>';
                document.getElementById("customerCards").innerHTML = cardsHtml;
                hideShowExitingUserTabs(true);
            },
            error : function(error) {
                 alert("<bean:message key='BzComposer.common.erroroccurred'/>");
            }
        });
    }
}

function showInvalidEmailDialog()
{
  debugger;
    event.preventDefault();
    $("#showInvalidEmailDialog").dialog({
        resizable: false,
        height: 200,
        width: 300,
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
<div id="showservicevalidationdialog" style="display:none;">
	<p><spring:message code="BzComposer.NewCustomer.Service.Validation" /></p>
</div>
<div id="saveCustomerInfoDialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.savevendorinfo"/></p>
</div>