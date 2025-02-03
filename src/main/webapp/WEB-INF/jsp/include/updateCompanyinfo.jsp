<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.companyinfotitle"/></title>
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
<style type="text/css">
body {
	background: #ffffff url(../images/body_top.png) left top repeat-x;
	margin: 0 auto;
	padding: 0;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #333333;
}
div, h1, h2, h3, h4, p, form, label, input, textarea, span {
	margin: 0;
	padding: 0;
}
p{
	padding: 3px;
	line-height: 20px;
}
ul,li {
	margin: 0;
	padding: 0;
	list-style-type: none;
}
a {
	text-decoration: none;
	color: #2670aa;
}
img {
	border: none;
}
hr {
	border: 0;
	width: 90%;
	color: #f00;
	background-color: #8A8888;
	height: 1px;
}
h5 {
	font-size: 11px;
	font-weight: bold;
}
#errormsg h3{
	background:transparent url(../images/help.png) no-repeat scroll left center;
	color:#333333;
	/* float:left; */
	font-size:16px;
	font-weight:bold;
	padding:10px 10px 0 35px;
	width: 100%;
}
#errormsg ul{
	margin:0;
	padding: 2px 30px 1px;
}
#errormsg ul li{
	list-style-type:disc;
	margin:0;
	padding: 2px 1px 1px 1px;
}
input,textarea,select{
	display: inline-block;
	padding: 4px;
	margin-bottom: 9px;
	line-height: 18px;
	color: #555555;
	border: 1px solid #cccccc;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
}
input:focus,textarea:focus,select:focus {
	border-color: rgba(82, 168, 236, 0.8);
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
		rgba(82, 168, 236, 0.6);
	-moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
		rgba(82, 168, 236, 0.6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
		rgba(82, 168, 236, 0.6);
	outline: 0;
	outline: thin dotted \9;
}
div,a,input {
	behavior: url(iepngfix.htc)
}
/* FOOTER */
h3 {
	background: url(images/help1.png) left center no-repeat;
	/* float: right; */
	padding: 10px 10px 0 35px;
	font-size: 16px;
	font-weight: bold;
	color: #333333;
}
.imgClass {
    background-image: url("images/submitbtn.jpg");
    background-position:  0px 0px;
    background-repeat: no-repeat;
    width: 70px;
    height: 30px;
    border: 0px;
    overflow: hidden;
    text-indent:-9999px;
    background-color:  #f6f6f6;
    cursor: pointer;
}
.imgClass:hover{
	background-position:  0px -2px;
    cursor: pointer;
}
/* #content {
	/* float: left; */
	text-align :center;
	width: 100%;
}*/
#logo {
	background: url(images/logo1.png) left top no-repeat;
	width: 238px;
	height: 64px;
	float: left;
	margin: 0 0 0 50px;
}
.ctrl {
	font-family: Tahoma, Verdana, sans-serif;
	font-size: 12px;
	width: 90%;
}
hr {
	border: 0;
	width: 100%;
	color: #f00;
	background-color: #05A9C5;
	height: 1px;
}
.sub_heading {
	font-size: 14px;
	font-weight: bold;
	padding: 0 0 0 0;
}
#setup {
	width: 668px;
}
h1 {
	/* float: left; */
	padding: 10px;
	width: 50%;
	font-size: 20px !important;
	/* font-weight: bold; */
	color: rgb(138, 136, 136);
	font-family: "Philosopher", "Bitstream Vera Sans", "Lucida Grande", "Trebuchet MS",Arial, Helvetica, sans-serif !important;
}
/* start*/
/* CONTENT */
#right_remove{
	padding-right: 10px;
	top: auto;
}
#content {
	/* float: left; */
	background: url(${pageContext.request.contextPath}/images/content_bg.png) left top repeat-y;
	width: 100%;
}
#content_inner {
	/* float: left; */
	/* width: 867px; */
	padding: 25px 70px 5px 70px;
	width: 100%
}
/* SETUP */
h1 {
	/* float: left; */
	padding: 10px;
	/* width: 50%; */
	width: 100%;
	font-size: 18px;
	font-weight: bold;
}
h1 span {
	color: #2670AA;
}
h2 {
	/* float: none; */
	padding: 0px;
	font-size: 16px;
	font-weight: bold;
}
h2 span {
 color: #2670AA;
}
h4 {
	/* float: left; */
	padding: 0px;
	font-size: 12px;
	font-weight: bold;
}
h4 span {
	color: #2670AA;
}
h3 {
	background: url(../images/help.png) left center no-repeat;
	/* float: right; */
	padding: 10px 10px 0 35px;
	font-size: 16px;
	font-weight: bold;
	color: #333333;
}
#setup {
	/* float: left; */
	margin-bottom: 100px;
	width:100%;
}
.box {
	/* float: left; */
	width: 865px;
	padding: 10px;
	background: #1d5e96;
}
.inner_box {
	background: #eeeeee;
	border: #1d5e96 5px solid;
	width: 216px;
	padding: 10px;
}
.inner_box p {
	height: 50px;
}
#setup h2 {
	text-align: center;
	padding: 5px;
	font-size: 18px;
	font-weight: bold;
	text-transform: capitalize;
}
</style>

<script type="text/javascript">
function showLocale(){
	var lang = document.getElementById("locale").value;
	window.location = "./changeLocale?requestPage=CompanyInfoPage&lang="+lang;
}

function showLanguageDialog(){
	event.preventDefault();
	$("#showLanguageDialog").dialog({
    	resizable: false,
        height: 200,
        width: 400,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function ValidateEmail(email) {
    var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return expr.test(email);
}
function setPhonePattern(phone){
	if(phone.length > 10 || phone.length < 10){
		event.preventDefault();
		$("#enterPhoneNumber").dialog({
	    	resizable: false,
	        height: 200,
	        width: 500,
	        modal: true,
	        buttons: {
	            "Ok": function () {
	                $(this).dialog("close");
	            }
	        }
	    });
	    return false;
	}
	//Filter only numbers from the input
  	let cleaned = ('' + phone).replace(/\D/g, '');
	
	//Check if the input is of correct length
	let match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
	
	if (match) {
		$("#phone").val('1(' + match[1] + ') ' + match[2] + '-' + match[3]);
  	};
}

function setCellPhonePattern(cellPhone){
	if(cellPhone.length > 10 || cellPhone.length < 10){
		event.preventDefault();
		$("#enterPhoneNumber").dialog({
	    	resizable: false,
	        height: 200,
	        width: 500,
	        modal: true,
	        buttons: {
	            "Ok": function () {
	                $(this).dialog("close");
	            }
	        }
	    });
	    return false;
	}
	//Filter only numbers from the input
  	let cleaned = ('' + cellPhone).replace(/\D/g, '');

	//Check if the input is of correct length
	let match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);

	if (match) {
		$("#cellPhone").val('1(' + match[1] + ') ' + match[2] + '-' + match[3]);
  	};
}
function setState(){
	var zip = $("#zip").val();
	if(zip == "" || zip == null){
		event.preventDefault();
		$("#enterZip").dialog({
	    	resizable: false,
	        height: 200,
	        width: 500,
	        modal: true,
	        buttons: {
	            "Ok": function () {
	                $(this).dialog("close");
	            }
	        }
	    });
	    return false;
	}
	else{
		window.location = "File?tabid=zipcode&&zipcode="+zip;
	}
	<%-- debugger
	$.ajax({
		type: "POST",
   		url:"File?tabid=zipcode&&zipcode="+zip,
     	data: { zip : zip }
 		}).done(function(data){
 			debugger
 			$(document).find('div#cityDiv').replaceWith($(data).find('div#cityDiv').html());
 			debugger
 			var stateName = '<%= request.getAttribute("state") %>';
 			debugger
 			$('select[id="sid"]').find('option[value="'+stateName+'"]').attr("selected",true);
 			debugger
	}); --%>
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
function writeSelect()
{ 
   	if (o.readyState != 4 || o.status != 200) { 
		return;
	}    
   	document.getElementById("t_statedata").innerHTML = o.responseText ;
	if($.trim($("#countryId").find("option:selected").text())!="United States"){
		$("#zip").val("");
		$("#city").val("");
	}
}
function refreshItemsNow(){
  o = c(writeSelect);
  var val = $("#countryId").val();
  oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=state&Cid=' + val)
  if(val == 3){
		$("#stateRow").hide();
		$("#ProvinceRow").show();
	}else{
		$("#stateRow").show();
		$("#ProvinceRow").hide();
	} 
}
function refreshItemsNow33(val,sval)
{
  	o33 = c(writeSelect33);
  	oGET(o33,'${pageContext.request.contextPath}/include/GetStates.jsp?st=state&Cid=' + val+"&sval="+sval)
}
function writeSelect33()
{
	if (o33.readyState != 4 || o33.status != 200) 
	{ 
      return;
    }
    document.getElementById("t_statedata").innerHTML = o33.responseText ;
}
function init() {
	debugger;
    refreshItemsNow();
    setTimeout(function () {
        document.getElementById("sid").value = "<%= request.getAttribute("state")%>";
    }, 1000);
}
</script>
</head>
<body onload="init()">
	<%-- <a href="${pageContext.request.contextPath}/Invoice?tabid=Invoice"><h3 style="float: left;">Go to Home</h3></a> --%>
    <!-- CONTENT -->
	<div id="ddcolortabsline">&nbsp;</div>
    	<!-- <div id="content" class="container"> -->
    	<div style="width: 100%; text-align: center;">
    		<div id="content_inner">
        		<div id="errors" style="color: red;">
    			</div>
        		<h1>
        			<spring:message code="BzComposer.companyinfo.title"/>
        			<!-- Company information -->
       			</h1>
        		<!-- <div id="setup"> -->
        		<div id="setup">
					<form:form name="CompanyInfoForm" enctype="multipart/form-data" method="post" id="frmUpdateCompany" modelAttribute="companyInfoDto">
						<!-- <table cellpadding="0" cellspacing="0" border="0" width="72%" align="right" > -->
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td colspan="3">
									<span class="sub_heading">
										<spring:message code="BzComposer.companyinfo.aboutyou"/>
										<!-- About You -->
									</span>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<hr>
								</td>
							</tr>
							<tr>
								<td>
									<img src="images/pixel.gif" border="0"/>
								</td>
								<td>
									<img src="images/pixel.gif" width="1" height="10" border="0"/>
									<br/>
									<table cellpadding="0" cellspacing="0" border="0" width="100%">
										<tr>
											<td>
												<table cellspacing="1" cellpadding="2" border="0" 
												width="100%">
							  						<tr>
														<td>
							  								<div style="color: red">
																<% org.apache.struts.action.ActionErrors ae = (org.apache.struts.action.ActionErrors) request.getAttribute(org.apache.struts.Globals.ERROR_KEY);
																	boolean emailError=false;
																	boolean requiredError=false;
																	boolean MismatchError=false;
																	boolean duplicateEmailError=false;
																	boolean duplicateUserError=false;
																	boolean invalidPassword=false;
																	boolean registrationSuccessful=false;
																	boolean invalidZipError=false;
																	boolean invalidPhoneError=false;
																	boolean invalidUSState=false;
								 	 								if(ae != null)
								 	 								{
								 										for(int x=0; x<ae.size(); x++)
								 										{
								 			 								java.util.Iterator iter=ae.get();
								 			 								while(iter.hasNext())
								 			 								{
								 												org.apache.struts.action.ActionMessage e=(org.apache.struts.action.ActionMessage)iter.next();
								 												//out.println(e.getValues()[0]);
								 												if(e.getKey().indexOf("email")>1){
								 													emailError=true;
								 												}
																	 			/*if(e.getKey().indexOf("invalid")>1 && e.getValues()[0].toString().indexOf("Zip")>1 ){
																 					invalidUSZipCode=true;
																 					out.println(e.getKey());
																 				}
																 				if(e.getKey().indexOf("invalid")>1 && e.getValues()[0].toString().indexOf("Phone")>1){// && e.getKey().indexOf("invalid")>1 ){
																 					invalidUSPhoneNumber=true;
																 					out.println(e.getKey());
																 				}*/
																 				if((e.getKey().indexOf("maxlength")>=0 || e.getKey().indexOf("minlength")>=0 )&& e.getValues()[0].toString().indexOf("Password")>=0){// && e.getKey().indexOf("invalid")>1 ){
																 					invalidPassword=true;
																 					//out.println(e.getKey()+"test");
																 				}
																 				/* if(e.getValues()[0].toString().indexOf("Zip")>1 ){
																 					invalidUSZipCode=true;
																 					out.println(e.getKey());
																 				}
																 				if(e.getValues()[0].toString().indexOf("Phone")>1){// && e.getKey().indexOf("invalid")>1 ){
																 					invalidUSPhoneNumber=true;
																 					out.println(e.getKey());
																 				}
																 				if(e.getValues()[0].toString().indexOf("Password")>1){// && e.getKey().indexOf("invalid")>1 ){
																 					invalidPassword=true;
																 					out.println(e.getKey());
																 				} */
																 				if(e.getKey().indexOf("required")>=0){
																 					requiredError=true;
																 				}
																 				if(e.getKey().indexOf("err.registration.password.mismatch")>=0){
																 					MismatchError=true;
																 				}
																 				if(e.getKey().indexOf("err.registration.emailaddress.duplicate")>=0){
																 					duplicateEmailError=true;
																 				}
																 				if(e.getKey().indexOf("err.registration.username.duplicate")>=0){
																 					duplicateUserError=true;
																 				}
																 				if(e.getKey().indexOf("err.registration.success")>=0){
																 					registrationSuccessful=true;
																 				}
																 				if(e.getKey().indexOf("err.registration.invalidzip")>=0){
																 					invalidZipError=true;
																 				}
																 				if(e.getKey().indexOf("err.registration.invalidphone")>=0){
																 					invalidPhoneError=true;
																 				}
																 				if(e.getKey().indexOf("err.registration.invalidstate")>=0){
																 					invalidUSState=true;
																 				}
																 			}
																 		}
																	 	if(requiredError){
																			 out.println("Please enter fields marked with *<br>");
																		 }
																		 if(emailError){
																			 out.println("Invalid email address <br>");
																		 }
																		 if(MismatchError){
																				 out.println("Passwords does not match<br>");
																		 }
																		 if(duplicateEmailError){
																			 out.println("Email already exists<br>");
																		 }
																		 if(duplicateUserError){
																			 out.println("User Name already exists<br>");
																		 }
																		 if(registrationSuccessful){
																			 out.println("Registration successful.Please click <a href='index.jsp'>here</a> to login<br>");
																		 }
																		 if(invalidZipError){
																			 out.println("Invalid US Zip<br>");
																		 }
																		 if(invalidPhoneError){
																			 out.println("Invalid US Phone number<br>");
																		 }
																		 if(invalidUSState){
																			 out.println("Choose state from the drop down<br>");
																		}
																	}
																%>
								   							</div> 
				  										</td>
			  										</tr>
													<tr>
														<td id="t_email">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.companyinfo.emailaddress"/>
															<!-- Email Address: -->
															<span class="inputHighlighted">*</span>
														</td>
														<td>
															<%-- <form:input path="email" size="35" maxlength="80" style="font-size:1em;"/> --%>
															<form:input path="email" size="30"
															maxlength="80" style="font-size:1em;" id="email"/>
														</td>
														<td/>
													</tr>
													<tr>
														<td id="t_txtFirstName">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.Companyinformation.FirstName" />
															:<span class="inputHighlighted">*</span>
														</td>
														<td>
															<%-- <form:input path="firstName" maxlength="50" style="font-size:1em;"/> --%>
															<form:input path="firstName" maxlength="50"
															style="font-size:1em;" size="30" id="firstName"/>
														</td>
														<td/>
													</tr>
													<tr>
														<td id="t_txtLastName">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.Companyinformation.LastName" />
															:<span class="inputHighlighted">*</span>
														</td>
														<td>
															<%-- <form:input path="lastName" maxlength="50" style="font-size:1em;"/> --%>
															<form:input path="lastName" maxlength="50"
															size="30" style="font-size:1em;" id="lastName"/>
														</td>				
														<td/>
													</tr>
													<tr>
														<td id="t_txtLastName">
															<!-- &nbsp; -->
																Language
															:<!-- <span class="inputHighlighted">*</span> -->
														</td>
														<td>
															<select name="locale" id="locale" onchange="showLocale();">
																<!-- <option value=""><spring:message code="BzComposer.selectlanguage"/></option> -->
																<option value="en" ${currentLocale=='en'?'selected':''}><spring:message code="BzComposer.selectlanguage.english"/></option>
																<option value="zh" ${currentLocale=='zh'?'selected':''}><spring:message code="BzComposer.selectlanguage.chinese"/></option>
																<option value="es" ${currentLocale=='es'?'selected':''}><spring:message code="BzComposer.selectlanguage.spanish"/></option>
															</select>
														</td>				
														<td/>
													</tr>
													<tr>
														<td colspan="3">
															<span class="sub_heading">
																<spring:message code="BzComposer.companyinfo.aboutcompany"/>
																<!-- About Your Company -->
															</span>
														</td>
													</tr>
													<tr>
														<td colspan="3"><hr></td>
													</tr>
													<tr>
														<td id="t_txtCompanyName">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.Companyinformation.CompanyName" /> :
															<span class="inputHighlighted">*</span>
														</td>
														<%-- commented on 05-05-2020 
														<td>
															<form:input path="companyName" disabled="true" size="35" style="width: 80%;"/>
														</td> --%>
														<td>
															<form:input path="companyName" size="30" style="font-size:1em;" id="companyName"/>
														</td>
														<td/>
													</tr>
													<tr>
														<td id="t_txtCompanyName">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.Companyinformation.CompanyDBAName" />:
															<span class="inputHighlighted"></span>
														</td>
														<td>
															<%-- <form:input path="nickName" size="35" style="font-size:1em;"/> --%>
															<form:input path="nickName" size="30"
															style="font-size:1em;" id="nickName"/>
														</td>											
														<td/>
													</tr>
													<tr>
														<td>
															<spring:message code="BzComposer.Companyinformation.Type" />
															<span class="inputHighlighted">*</span>
														</td>
														<td>
															<form:select path="businessTypeId" id="businessTypeId">
                                                                <c:forEach items="${companyInfoDto.listOfBusinessType}" var="objList1">
                                                                    <form:option value="${objList1.businessTypeId}">${objList1.businessName}</form:option>
                                                                </c:forEach>
															</form:select>
														</td>
														</tr>
													<tr>
														<td id="t_txtAddress1">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.Companyinformation.Address1" />
															:<span class="inputHighlighted">*</span>
														</td>
														<td>
															<%-- <form:input path="address1" size="50"
															style="font-size:1em;"/> --%>
															<form:input path="address1" size="30"
															style="font-size:1em;" id="address1"/>
														</td>
														<td/>
													</tr>
													<tr>
														<td id="t_txtAddress2">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.Companyinformation.Address2"/>:
															<span class="inputHighlighted"></span>
														</td>
														<td>
															<%-- <form:input path="address2" size="50"
															style="font-size:1em;"/> --%>
															<form:input path="address2" size="30"
															style="font-size:1em;" id="address2"/>
														</td>
														<td/>
													</tr>
													<tr>
														<td id="t_lblZip">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.companyinfo.zipcode"/>
															<!-- Zip Code: -->
															<span class="inputHighlighted">*</span>
														</td>
														<td>
															<form:input path="zip" onfocusout="loadStateCityByZipcode(this.value);" size="30" style="font-size:1em;" id="zip"/>
														</td>
														<td/>
													</tr>
													<tr id="stateRow">
														<td id="t_state">
															<spring:message code="BzComposer.Customer.State" />
														</td>
														<td id="t_statedata" style="font-size:1em;"></td>
													</tr>
													<tr id="ProvinceRow">
														<td id="t_txtProvince">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.Companyinformation.Province" />:
															<span class="inputHighlighted"></span>
														</td>
														<td>
															<%-- <form:input path="province" size="25" style="font-size:1em;"/> --%>
															<form:input path="province" size="30"
															style="font-size:1em;" id="province"/>
														</td>
														<td>
															<a id="bubble_tooltip_content" href="javascript:" class="tooltip"
															onmousemove="showToolTip(event,'Enter your province,outside US only');return false"
															onmouseout="hideToolTip()"><img src="images/q.gif" border="0"
															width="20" height="20"/></a>
															<!-- <a href="javascript:" class="tooltip_text" title="Enter your province,outside US only"><img src="images/q.gif" border="0"
															width="20" height="20"/></a> -->
														</td>
													</tr>
													<tr>
														<td id="t_txtcity">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.companyinfo.city"/>
															<!-- city: -->
															<span class="inputHighlighted">*</span>
														</td>
														<td id="txtcityData">
															<%-- <form:input path="city" size="35"
															style="font-size:1em;"/> --%>
															<div id="cityDiv">
																<form:input path="city" size="30" style="font-size:1em;" id="city"/>
															</div>
														</td>
														<td/>
													</tr>
													<tr>
														<td id="t_country">
															<spring:message code="BzComposer.Customer.Country" />AS:${companyInfoDto.countryId}
														</td>
														<td>
															<form:select path="countryId" onchange="refreshItemsNow();" style="font-size:1em;width:285px;">
                                                                <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                                <%-- <form:options collection="cList" property="value" labelProperty="label" /> --%>
                                                                <c:forEach items="${cList}" var="objList1">
                                                                    <form:option value="${objList1.countryId}">${objList1.country}</form:option>
                                                                </c:forEach>
															</form:select>
														</td>
													</tr>
										  			
													<%-- <tr>
														<td id="t_txtProvince">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.Companyinformation.Province" />:
															<span class="inputHighlighted"></span>
														</td>
														<td>
															<form:input path="province" size="25" style="font-size:1em;"/>
															<form:input path="province" size="30"
															style="font-size:1em;" id="province"/>
														</td>
														<td>
															<a id="bubble_tooltip_content" href="javascript:" class="tooltip"
															onmousemove="showToolTip(event,'Enter your province,outside US only');return false"
															onmouseout="hideToolTip()"><img src="images/q.gif" border="0"
															width="20" height="20"/></a>
															<!-- <a href="javascript:" class="tooltip_text" title="Enter your province,outside US only"><img src="images/q.gif" border="0"
															width="20" height="20"/></a> -->
														</td>
													</tr> --%>
													<tr>
														<td id="t_txtPhone">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.Companyinformation.Phone" />
															:<span class="inputHighlighted"></span>
														</td>
														<td>
															<%-- <form:input onkeydown="return numbersonly(event,this.value)"
															path="phone" size="25" style="font-size:1em;"/> --%>
															<form:input onchange="setPhonePattern(this.value)"
															path="phone" id="phone" size="30" style="font-size:1em;"/>
														</td>
														<td/>
													</tr>
													<tr>
														<td>
															<spring:message code="BzComposer.Companyinformation.CellPhone"/>:
														</td>
														<td>
															<form:input path="cellPhone" id="cellPhone"
															size="30" style="font-size:1em;" onchange="setCellPhonePattern(this.value)"/>
														</td>
													</tr>
													<tr>
														<td id="t_txtFax">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.companyinfo.fax"/>
															<!-- Fax: -->
															<span class="inputHighlighted"></span>
														</td>
														<td>
															<%-- <form:input path="fax" size="25"
															style="font-size:1em;"/> --%>
															<form:input path="fax" id="fax" size="30"
															style="font-size:1em;"/>
														</td>
													</tr>
													<%-- <tr>
														<td colspan="3">
															<span class="sub_heading">
																<spring:message code="BzComposer.companyinfo.security"/>
																<!-- Security -->
															</span>
														</td>
													</tr> --%>
													<!-- <tr>
														<td colspan="3">
															<hr>
														</td>
													</tr> -->
													<%-- <tr>
														<td id="t_uname" width="40%">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.companyinfo.loginname"/>
															<!-- Login Name: -->
															<span class="inputHighlighted">*</span>
														</td>
														<td width="50%">
															<!-- <input type="text" name="userID" size="10"	> -->
															<form:input path="userName"
															id="userName" disabled="true" size="50"
															style="font-size:1em;"/>
															<form:input path="userName"
															id="userName" disabled="true" size="30"
															style="font-size:1em;"/>
														</td>
														<td>
															<a href="javascript:" class="tooltip_text"
															onmousemove="showToolTip(event,'Your Login Name is not case sensitive and must be between 4 and 16 characters in length.');return false"
															onmouseout="hideToolTip()">
																<img src="images/q.gif" border="0"
																width="20" height="20"/>
															</a>
														</td>
													</tr> --%>
													<%-- <tr>
														<td id="t_password1">
															<!-- &nbsp; -->
															<spring:message code="BzComposer.companyinfo.password"/>
															<!-- Password: -->
															<span class="inputHighlighted">*</span>
														</td>
														<td id="t_password">
															<form:password path="password"
															disabled="true" id="password"
															size="25" style="font-size:1em;"/>
															<form:password path="password"
															id="t_password11"
															size="30" style="font-size:1em;"/>
														</td>
														<td>
															<a href="javascript:" class="tooltip_text"
															onmousemove="showToolTip(event,'Your password is case sensitive and must be between 6 and 16 characters in length.');return false"
															onmouseout="hideToolTip()">
																<img src="images/q.gif" border="0"
																width="20" height="20"/>
															</a>
														</td>
													</tr> --%>
													<%-- <tr>
														<td id="t_password_copy" nowrap>
															<!-- &nbsp; -->
															<spring:message code="BzComposer.companyinfo.retypepassword"/>
															<!-- Retype Password: -->
															<span class="inputHighlighted">*</span>&nbsp;
														</td>
														<td>
															<form:password path="confirmPassword"
															disabled="true" id="confirmPassword"
															size="25" style="font-size:1em;"/>
															<form:password path="confirmPassword"
															id="t_password_copy11"
															size="30" style="font-size:1em;"/>
														</td>
														<td/>
													</tr> --%>
												</table>
											</td>
										</tr>
									</table>
									<img src="images/pixel.gif" width="1" height="10" border="0" />
									<br>
									<div align="center" id="error_registration" style="display: block;">
									</div>
								</td>
								<td>
									<img src="images/pixel.gif" border="0"/>
								</td>
							</tr>
							<tr>
								<td colspan="5" bgcolor="#f1f1f1">
									<img src="images/pixel.gif" width="1" height="1" border="0"/>
								</td>
							</tr>
							<tr>
								<td colspan="5" align="center">
									<table cellpadding="0" cellspacing="0" border="0" style="padding-right: 68px;">
										<tr>
											<!--<td class="" width="1"> -->
											<br/>
											<!--<input type="image" style="border: none;" -->
											<!--src="images/regbtn.jpg" alt="" width="70" height="30" border="0" -->
											<!--name="Submit"/></td> -->
											<td align="center">
												<input type="button" class="formbutton" name="Update" value="<spring:message code='BzComposer.global.update'/>"
												    title="Update Customer Information" onclick="updateComapany();" style="font-size:1em;"/>
												<!-- <input type="button" class="formbutton" onclick="closeme();" name="Close" value="Close" title="close"/> -->
												<input type="hidden" name="stname" value="" id="stateId" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</form:form>
				</div>
        	</div>        
      	</div>
    <!-- </div>
    FINISH
  </div>
   FOOTER -->
</body>
</html>
<%-- </html> --%>
<script>
function updateComapany()
{
    debugger;
	var sel = document.getElementById("businessTypeId");
    var businessTypeId = sel.options[sel.selectedIndex].value;

	var email = document.getElementById("email").value;
	var fName = document.getElementById("firstName").value;
	var lName = document.getElementById("lastName").value;
	var companyName = document.getElementById("companyName").value;
	var nickName = document.getElementById("nickName").value;
	var address1 = document.getElementById("address1").value;
	var address2 = document.getElementById("address2").value;
	var zip = document.getElementById("zip").value;
	var stateName = document.getElementById("sid").value;
	var cityName = document.getElementById("city").value;
	var countryId = document.getElementById("countryId").value;
	var phone = document.getElementById("phone").value;
	var province = document.getElementById("province").value;
	var cellphone = document.getElementById("cellPhone").value;
	var fax = document.getElementById("fax").value;

	/* var t_password = document.getElementById("t_password").value;
	var t_password_copy = document.getElementById("t_password_copy").value;
	 */
	/* var t_password = document.getElementById("t_password11").value;
	var t_password_copy = document.getElementById("t_password_copy11").value;
	var pp = encodeURIComponent(t_password);
	if(t_password!=t_password_copy){
		errorMessage += "<br/><h2><spring:message code='err.registration.password.mismatch'/></h2>";
		return false;
	} */
	var errorMessage="";
	if(email == "" && fName == "" && lName == "" && companyName == "" && address1 == "" && cityName == ""){
		errorMessage += "<spring:message code='MultiUserForm'/>";
	}
	else
	{
		if(email == "" || email == null){
			errorMessage += "<br/><h2><spring:message code='BzComposer.companyinfo.enteremailaddress'/></h2>";
		}
		if (!ValidateEmail(email)) {
	        errorMessage += "<h2><spring:message code='err.Email'/></h2>";
	    }
		if(fName == "" || fName == null){
			errorMessage += "<br/><h2><spring:message code='err.Firstname'/></h2>";
		}
		if(lName == "" || lName == null){
			errorMessage += "<br/><h2><spring:message code='err.LastName'/></h2>";
		}
		if(companyName == "" || companyName == null){
			errorMessage += "<br/><h2><spring:message code='BzComposer.companyinfo.enterCompanyName'/></h2>";
		}
		if(address1 == "" || address1 == null){
			errorMessage += "<br/><h2><spring:message code='BzComposer.companyinfo.enterAddress'/></h2>";
		}
		if(zip == "" || zip == null){
			errorMessage += "<h2><spring:message code='err.Zip'/></h2>";
		}
		if(city == "" || city == null){
			errorMessage += "<h2><spring:message code='err.City'/></h2>";
		}
	}
	if(errorMessage !=""){
		document.getElementById("errors").innerHTML = errorMessage;
		return false;
		event.preventDefault();
	}
	else
	{
		document.CompanyInfoForm.companyName.value = companyName;
		$.ajax({
			type: "POST",
	   		url:"updateEditedCompanyinfo?tabid=edit&companyName="+companyName+"&nickName="+nickName+"&businessTypeId="+businessTypeId
	   				+"&fName="+fName+"&lName="+lName+"&add1="+address1+"&add2="+address2+"&city="+cityName+"&zip="+zip+"&province="+province
	   				+"&countryId="+countryId+"&phone="+phone+"&cellphone="+cellphone+"&fax="+fax+"&email="+email+"&stateName="+stateName,
 			success : function() {
				$("#sid").find('option[value="'+stateName+'"]').attr("selected",true);
				document.getElementById("errors").innerHTML = "<h2>Record updated</h2>";	
	   		},
			error : function(data) {
				event.preventDefault();
				$("#errorOccurred").dialog({
			    	resizable: false,
			        height: 200,
			        width: 500,
			        modal: true,
			        buttons: {
			            "Ok": function () {
			                $(this).dialog("close");
			            }
			        }
			    });
			    return false;
			}
		});
		
		$("#showsuccessdialog").dialog({
			resizable: false,
	        height: 200,
	        width: 500,
	        modal: true,
	        buttons: {
	        	"<spring:message code='BzComposer.global.ok'/>": function () {
	        		$(this).dialog("close");
	                return false;
	        	},
	            "<spring:message code='BzComposer.global.cancel'/>": function () {
	                $(this).dialog("close");
	                return false;
	            }
        	}
	});
	}
}

function loadStateCityByZipcode(zipCode){
    $.ajax({
        type: "POST",
        url:"CustomerAjax?tabid=zipcode&zipcode="+zipCode,
        data :"zipCode=" + zipCode,
        success : function(data) {
            $("#sid option:contains('"+data.stateName+"')").attr('selected', 'selected');
            $("#city").val(data.city);
        },
        error : function(data) {
            alert("<bean:message key='BzComposer.common.erroroccurred'/>");
        }
    });
}

function closeme()
{
	window.close();
}
// form fields description structure
var a_fields = {
	'uname' : {
		'l': 'Login Name',  // label
		'r': true,    // required
		'f': 'alphanum',  // format (see below)
		't': 't_uname',// id of the element to highlight if input not validated
		
		'm': null,     // must match specified form field
		'mn': 4,       // minimum length
		'mx': 16       // maximum length
	},
	'pass' : {'l':'Password','r':true,'f':'alphanum','t':'t_password','m':'pass_con','mn':'6','mx':'16'},
	'pass_con' : {'l':'Password confirm','r':true,'f':'alphanum','t':'t_password_copy','mn':'6','mx':'16'},
	'email' : {'l':'E-mail','r':true,'f':'email','t':'t_email','m':'email_con'},
	'email_con' : {'l':'E-mail confirm','r':true,'f':'email','t':'t_emailcon'},
	'question' : {'l':'Password Reminder Question','r':true,'f':'alphanum','t':'t_question'},
	'answer' : {'l':'Answer','r':true,'f':'alphanum','t':'t_answer'}
},
o_config = {
	'to_disable' : ['Submit'],
	'alert' : 1
}
// validator constructor call
var v = new validator('login', a_fields, o_config);
//'uname' : {
//		'l': 'Name',  // label
//		'r': false,    // required
//		'f': 'alpha',  // format (see below)
//		't': 't_uname',// id of the element to highlight if input not validated
//		
//		'm': null,     // must match specified form field
//		'mn': 2,       // minimum length
//		'mx': null       // maximum length
//	},
</script>

<!-- dialog box that used in this page -->
<div id="errorOccurred" style="display:none;font-size:1em;">
	<h3><spring:message code="BzComposer.common.erroroccurred"/></h3>	
</div>
<div id="enterPhoneNumber" style="display:none;font-size:1em;">
	<h3><spring:message code="BzComposer.companyinfo.enterphonenumber"/></h3>	
</div>
<div id="enterZip" style="display:none;font-size:1em;">
	<h3><spring:message code="err.Zip"/></h3>	
</div>

<!-- Dialog box used in this page -->
<div id="showsuccessdialog" style="display:none;">
	<p>Record updated</p>
</div>