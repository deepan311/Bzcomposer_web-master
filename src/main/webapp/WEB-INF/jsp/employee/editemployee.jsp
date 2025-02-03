<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href='http://fonts.googleapis.com/css?family=Philosopher:400,700,400italic,700italic&subset=cyrillic,latin' rel='stylesheet' type='text/css'>

<link
	href="${pageContext.request.contextPath}/styles/admin.css"
	media="screen" rel="Stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/styles/form.css"
	media="screen" rel="Stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/styles/basic.css"
	media="screen" rel="Stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/styles/formitems.css"
	media="screen" rel="Stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/styles/menu.css"
	media="screen" rel="Stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/styles/textareas.css"
	media="screen" rel="Stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/styles/lightbox.css"
	media="screen" rel="Stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/styles/calendar.css"
	rel="stylesheet"></LINK>
<script
	src="${pageContext.request.contextPath}/scripts/prototype.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/effects.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/dragdrop.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/controls.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/application.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/message.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/calendar.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/tabs.js"
	type="text/javascript"></script>
	
	<script
	src="${pageContext.request.contextPath}/dist/js/jquery.js"
	type="text/javascript"></script>
		
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>

<title><spring:message code="BzComposer.editemployeetitle"/></title>
<script type="text/javascript">
$(function() {
    $( "#tabs" ).tabs();
  });
var funsequence = 0;
var _1 = navigator.userAgent.toLowerCase();
var ___ = (_1.indexOf("msie") != -1);
var ___5 = (_1.indexOf("msie 5") != -1);
var _io = (_1.indexOf("opera") != -1);
var _im = (_1.indexOf("mac") != -1);
var ____gi = (_1.indexOf("gecko") != -1);
var i____s = (_1.indexOf("safari") != -1);
var o = null;

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

function writeSelect()
{
  
   if (o.readyState != 4 || o.status != 200) { 
      return;
    }
     
    document.getElementById("t_statedata").innerHTML = o.responseText ;

    $('#sid').attr('name','state')
}
function refreshItemsNow(val)
{   console.log(val)
  o = c(writeSelect);
  // oGET(o,"include/GetStates.jsp?Cid=" + val)
  oGET(o,"include/GetStateselected.jsp?Cid=" + val)
}

function getStates(val1,val2)
{ console.log(val1 , val2)
  o = c(writeSelect);
  oGET(o,"include/GetStateselected.jsp?Cid=" + val1+"&sid="+val2)
}

</script>
<style>
input,textarea,select{
	display: inline-block;
/* 	padding: 4px; */
	margin-bottom: 3px;
	line-height: 13px;
/* 	color: #555555; */
	border: 1px solid #cccccc;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	margin-top: 3px
}
</style>
</head>
<body>

<!-- begin shared/header -->

<!-- end shared/header 
<div id="flashbox-container-id"><script>FlashTimeouts.add()</script><div class="flashBox notice"><h4>You are on project module view of BizComposer</h4></div></div>
-->

<div id="ddcolortabsline">&nbsp;</div>

<form action="/editemployee" method="post" name="AddEmployeeForm" id="editemployeefrn">

<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div><span
	style="font-size: 1.6em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;"><bean:message
	key="BzComposer.editemployee.editemployeelist" /></span></div><br/>	
<div id="tabs" style="height:550px;width:auto;">
  <ul>
    <li style="font-size:1em;"><a href="#General-1"><spring:message code="BzComposer.editemployee.tabs.general" /></a></li>
    <li style="font-size:1em;"><a href="#payroll-2"><spring:message code="BzComposer.editemployee.tabs.payrollandtaxinfo" /></a></li>  
  </ul>
	<div id="General-1">
		<div id="content1" class="tabPage" >
			<!-- add here the content of first tab -->
			<div id="table-negotiations">
				<table class="tabla-listados" cellspacing="0" >
					<thead>
						<tr>
							<th colspan="9" style="font-size:1.6em;">
								<spring:message code="BzComposer.editemployee.editemployeelist" />
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td id="t_title" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.title" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td>
								<select name="title" value="${employee.title}" id="title" style="font-size:1em;">
									<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
									<c:forEach items="${titleList}" var="obj">
										 <c:choose>
                                            <c:when test="${obj.value == employee.title}">
                                               <option value="${obj.value}" selected>${obj.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                              <option value="${obj.value}">${obj.label}</option>
                                            </c:otherwise>
                                        </c:choose>
									</c:forEach>
								</select>
							</td>
                            <td id="t_fname" style="font-size:1em;">
								<spring:message code="BzComposer.global.firstname" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td>
								<input type="text" name="fname" value="${employee.fname}" style="font-size:1em;"/>
							</td>				<td id="t_nname" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.middlename" />
							</td>
							<td>
								<input type="text" name="mname" value="${employee.mname}" style="font-size:1em;"/>
							</td>
							<td colspan="3">&nbsp;</td>
						</tr>

						<tr>

							<td id="t_lname" style="font-size:1em;">
								<spring:message code="BzComposer.global.lastname" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td>
								<input type="text" name="lname" value="${employee.lname}" style="font-size:1em;"/>
							</td>
                            <td id="t_ssn" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.ssn" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td>
								<input type="text" name="ssn" value="${employee.ssn}" size="10" style="font-size:1em;"/>
							</td>
                            <td nowrap id="t_dob" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.dateofbirth" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td nowrap>
								<input type="text" name="dob" value="${employee.dob}" size="10" readonly="true" style="font-size:1em;"/>
								<img src="${pageContext.request.contextPath}/images/cal.gif"
								onclick="displayCalendar(document.AddEmployeeForm.dob,'mm-dd-yyyy',this);">
							</td>
							<td colspan="3">&nbsp;</td>
						</tr>



						<tr>
						    <td id="t_jtitle" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.jobtitle" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td colspan="4">
								<select name="jtitle" path="jtitle" id="jtitle" style="font-size:1em;">
									<html:option value=""><spring:message code="BzComposer.ComboBox.Select" /></html:option>
									<c:forEach items="${jtitleList}" var="obj">
										<option value="${obj.value}">${obj.label}</option>
									</c:forEach>
								</select>
							</td>
							<td colspan="4">&nbsp;</td>
						</tr>

						<tr>
							<td id="t_address1" style="font-size:1em;">
								<spring:message code="BzComposer.global.address1" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td colspan="6">
								<textarea name="address1" cols="40" style="font-size:1em;">${employee.address1}</textarea>
							</td>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>

						<tr>
							<td id="t_address2" style="font-size:1em;">
								<spring:message code="BzComposer.global.address2" />
							</td>
							<td colspan="6">
								<textarea name="address2" cols="40" style="font-size:1em;">${employee.address2}</textarea>
							</td>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>


						<tr>
                            <td id="t_zip" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.zip" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>

                            <td>
								<input type="text" name="zip" value="${employee.zip}" style="font-size:1em;"/>
							</td>

							<td id="t_city" style="font-size:1em;">
								<spring:message code="BzComposer.global.city" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td>
								<input type="text" name="city" value="${employee.city}" style="font-size:1em;"/>
							</td>
                            <td id="t_state" style="font-size:1em;">
								<spring:message code="BzComposer.global.state" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td id="t_statedata" colspan="2"></td>

							<td id="t_province" style="font-size:1em;">
								<spring:message code="BzComposer.global.province" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
                            <td>
								<input type="text" name="province" value="${employee.province}" style="font-size:1em;"/>
							</td>
						</tr>
						 <tr>
							<td id="t_country" style="font-size:1em;">
								<spring:message code="BzComposer.global.country" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td>
								<select name="country" value="${employee.country}" id="country" onchange="refreshItemsNow(this.value);"
								onblur="refreshItemsNow(this.value);" style="font-size:1em;">
									<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
									<c:forEach items="${cList}" var="obj">
                                         <c:choose>
                                            <c:when test="${obj.value == employee.country}">
                                               <option value="${obj.value}" selected>${obj.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                              <option value="${obj.value}">${obj.label}</option>
                                            </c:otherwise>
                                        </c:choose>
									</c:forEach>
								</select>
							</td>
 							<script>
					     	getStates('${employee.country}','${employee.state}');
                           	</script>
						    <td id="t_phone" style="font-size:1em;">
								<spring:message code="BzComposer.global.phone" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td>
								<input type="text" name="phone" value="${employee.phone}" style="font-size:1em;"/>
							</td>
							<td colspan="2" style="font-size:14px;">
                                        <c:choose>
                                            <c:when test="${employee.sameAsMobileNumber == '1'}">
                                                <input type="checkbox" name="sameAsMobileNumber" checked/>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="checkbox" name="sameAsMobileNumber"/>
                                            </c:otherwise>
                                        </c:choose>
                                <spring:message code="BzComposer.global.sameasmobileno"/>
                            </td>
							<td id="t_mobile" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.mobile" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td>

							<c:choose>
                                                                        <c:when test="${employee.sameAsMobileNumber == '1'}">
								                                                <input type="text" name="mobile" value="${employee.mobile}" style="font-size:1em;" />
                                                                        </c:when>
                                                                        <c:otherwise>
								                                                  <input type="text" name="mobile" value="${employee.mobile}" style="font-size:1em;" disabled='disabled'/>
                                                                        </c:otherwise>
                                                                    </c:choose>
							</td>
							<td colspan="1">&nbsp;</td>
						</tr>
            			<tr>
							<td id="t_email" style="font-size:1em;">
								<spring:message code="BzComposer.global.email" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td>
								<input type="text" name="email" value="${employee.email}" size="15" style="font-size:1em;"/>
							</td>
                            <td id="t_emptype" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.employeetype" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td>
								<select name="emptype"  id="emptype" style="font-size:1em;">
									<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
									<c:forEach items="${emptypeList}" var="obj">
                                        <c:choose>
                                            <c:when test="${obj.value == employee.emptype}">
                                               <option value="${obj.value}" selected>${obj.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                              <option value="${obj.value}">${obj.label}</option>
                                            </c:otherwise>
                                        </c:choose>
									</c:forEach>
								</select>
							</td>
							<td nowrap id="t_dos" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.dateofstarted" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td nowrap>
								<input type="text" name="dos" value="${employee.dos}" size="10" readonly="true" style="font-size:1em;"/>
								<img src="${pageContext.request.contextPath}/images/cal.gif"
								onclick="displayCalendar(document.AddEmployeeForm.dos,'mm-dd-yyyy',this);">
							</td>
					         <td colspan="3">&nbsp;</td>
						</tr>
						<tr>
                            <td nowrap id="t_doa" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.dateofadded" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td>
								<input type="text" name="doa" value="${employee.doa}" size="10" readonly="true" style="font-size:1em;"/>
								<img src="${pageContext.request.contextPath}/images/cal.gif"
								onclick="displayCalendar(document.AddEmployeeForm.doa,'mm-dd-yyyy',this);">
							</td>
						    <td id="t_terminated" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.terminated" />
							</td>
							<td>
								<html:checkbox name="terminated" path="terminated" value="y" style="font-size:1em;"/>
							</td>
							<td id="t_dot" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.terminateddate" />
							</td>
							<td>
								<input type="text" name="dot" value="${employee.dot}" size="10" readonly="true" style="font-size:1em;"/>
								<img src="${pageContext.request.contextPath}/images/cal.gif"
								onclick="displayCalendar(document.AddEmployeeForm.dot,'mm-dd-yyyy',this);">
							</td>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td id="t_memo" style="font-size:1em;">
								<spring:message code="BzComposer.global.memo" />
							</td>
							<td colspan="5">
								<textarea name="memo"  cols="40" style="font-size:1em;">${employee.memo}</textarea>
							</td>
							<td colspan="3">&nbsp;</td>
						</tr>
						<div align="center" id="error_registration" style="display: block;"></div>
					</tbody>
				</table>
			</div>
		</div>
  	</div>
	
	<div id="payroll-2">
		<div id="content2" class="tabPage">
			<div id="table-negotiations">
				<table class="tabla-listados" cellspacing="0">
					<thead>
						<tr>
							<th class="emblem" colspan="8" style="font-size:1.6em;">
								<spring:message code="BzComposer.editemployee.payrolltaxinfo" />
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="8" style="font-size:1.4em;">
								<b><spring:message code="BzComposer.editemployee.payrollinfo" /> </b>
								<span class="inputHighlighted"></span>
							</td>
						</tr>
						<tr>
							<td id="t_filing" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.filingstatus" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td id="t_filling">
								<select name="filingStatus" path="filingStatus" id="filingStatus" style="font-size:1em;">
									<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
									<c:forEach items="${filingList}" var="obj">
                                         <c:choose>
                                            <c:when test="${obj.value == employee.filingStatus}">
                                               <option value="${obj.value}" selected>${obj.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                              <option value="${obj.value}">${obj.label}</option>
                                            </c:otherwise>
                                        </c:choose>
									</c:forEach>
								</select>
							</td>
							<td id="t_allowance" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.allowance" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td id="t_allowance" style="font-size:1em;">
								<input type="text" name="allowance" Class="ctrl" value="${employee.allowance}" style="font-size:1em;"/>
							</td>
						<!-- style change -->
						<!--<td width="20%" id="t_lname" align="right">&nbsp;<span -->
						<!--class="inputHighlighted"></span></td> -->
						<!--<td width="20%" id="t_lname" align="right">&nbsp;<span -->
						<!--class="inputHighlighted"></span></td> -->
							<td id="t_stateworked" align="right" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.stateworked" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td id="t_stateworked" style="font-size:1em;">
								<select name="stateworked" path="stateworked" id="stateworked" style="font-size:1em;">
									<html:option value=""><spring:message code="BzComposer.ComboBox.Select" /></html:option>
                                    <c:forEach items="${statewList}" var="obj">
                                         <c:choose>
                                            <c:when test="${obj.value == employee.stateworked}">
                                               <option value="${obj.value}" selected>${obj.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                              <option value="${obj.value}">${obj.label}</option>
                                            </c:otherwise>
                                        </c:choose>
									</c:forEach>
								</select>
							</td>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td id="t_payrollmethod" style="font-size:1.4em;" colspan="8">
								<b><spring:message code="BzComposer.editemployee.payrollmethod" /></b>
								<span class="inputHighlighted"></span>
							</td>
						</tr>
						<tr>
							<td id="t_period" style="font-size:1em;">
							</td>
							<td id="t_period" style="font-size:1em;">

								<input type="radio" name="payMethod" path="payMethod" value="1" ${employee.payMethod == '1' ? 'checked' : '' }/>
								&nbsp;<spring:message code="BzComposer.editemployee.hourly" />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="payMethod" path="payMethod" value="2" ${employee.payMethod == '2' ? 'checked' : '' }/>
								&nbsp;<spring:message code="BzComposer.editemployee.daily" />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="payMethod" path="payMethod" value="3" ${employee.payMethod == '3' ? 'checked' : '' }/>
								&nbsp;<spring:message code="BzComposer.editemployee.salary" />
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td id="t_period" style="font-size:1em;"></td>
							<td colspan="5">&nbsp;</td>	
						</tr>
						<tr>
							<td id="t_amount" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.amount" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td>
								<input type="text" name="amount" value="${employee.amount}" style="font-size:1em;"/>
							</td>
							<td id="t_payperiod" style="font-size:1em;">
								<spring:message code="BzComposer.editemployee.payperiod" />
								<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
							</td>
							<td id="t_lname" width="16%">
								<select name="payperiod" path="payperiod" id="payperiod" Class="ctrl" style="font-size:1em;">
									<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
									<c:forEach items="${periodList}" var="obj">
                                        <c:choose>
                                            <c:when test="${obj.value == employee.payperiod}">
                                               <option value="${obj.value}" selected>${obj.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                              <option value="${obj.value}">${obj.label}</option>
                                            </c:otherwise>
                                        </c:choose>
									</c:forEach>
								</select>
							</td>
							<td width="20%" id="t_lname" align="right">
								&nbsp;<span class="inputHighlighted"></span>
							</td>
							<td width="20%" id="t_lname" align="right">
								&nbsp;<span class="inputHighlighted"></span>
							</td>
							<td colspan="2">&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="status" value="${employee.status}" value="U" />
				<input type="hidden" name="employeeID" value="${employee.employeeID}" />
			</div>
		</div>
	</div>
	<!-- add the code for tab here -->	
</div>
<br/>
<table cellpadding="0" cellspacing="0" border="0" width=100% align=center>
	<tr>
		<td colspan="6" align="center">
			<%-- <html:reset value="<spring:message code='BzComposer.editemployee.cleardata'/>" Class="formbutton" style="font-size:1em;"/> --%>
			<input type="reset" value="Clear Data" Class="formbutton" style="font-size:1em;"/>
			&nbsp;&nbsp;&nbsp; 
			<%-- <html:submit value="<spring:message code='BzComposer.editemployee.updateemployee'/>" Class="formbutton" style="font-size:1em;" /> --%>
			<input type="submit" value="Update Employee" Class="formbutton" style="font-size:1em;" />
		</td>
	</tr>
</table>
</div>
</div>
</div>
</div>
</form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
<script>
$(document).ready(function(){
        $("input[name='sameAsMobileNumber']").change(function() {
               $("input[name='mobile']").prop( "disabled", !this.checked );
        });
          $("input[name='zip']").on("input",function(){
                if($.isNumeric($(this).val() )){
                     $.ajax({
                            								type: "GET",
                            						   		url:"/CountryAndState/"+this.value,
                            					 			success : function(res) {
                            					 			    console.log('res',res)
                                                                if(res && res.length > 0){
                                                                    $("input[name='city']").val(res[0])
                                                                    $("select[name='state'] option").filter(function () { return $(this).html() == res[1]; }).prop('selected', true)
                                                                }

                             						   		},
                            								error : function(data) {
                            									event.preventDefault();

                            								    return false;
                            								}
                            							});
                }
                else{
                    $(this).val('')
                }
          });
        });

</script>
</html>
