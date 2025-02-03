<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false"%>
<%-- <html> --%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/include/headlogo.jsp"%>
<%@include file="/include/header.jsp"%>
<%@include file="/include/menu.jsp"%>
<title><bean:message key="BzComposer.companyinfotitle"/></title>
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
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>

<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />

<script type="text/javascript">
function showLocale()
{
	var lang = document.getElementById("locale").value;
	var locale = "<%= request.getAttribute("selectedLocale")%>";
	
	if(lang == "" && locale != "")
	{
		$('select[id="locale"]').find('option[value="'+locale+'"]').attr("selected",true);
		return showLanguageDialog();
	}
	else
	{
		window.location="./changeLocale?lang="+lang;
	}
}
function showLanguageDialog()
{
	event.preventDefault();
	$("#showLanguageDialog").dialog({
    	resizable: false,
        height: 200,
        width: 400,
        modal: true,
        buttons: {
            "<bean:message key='BzComposer.global.ok'/>": function () {
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
		window.location = "File.do?tabid=zipcode&&zipcode="+zip;	
	}
	<%-- debugger
	$.ajax({
		type: "POST",
   		url:"File.do?tabid=zipcode&&zipcode="+zip,
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

$(document).ready(function(){
	$('select[id="country"]').find('option[id="'+2+'"]').attr("selected",true);
	var state = "<%= request.getAttribute("state")%>";
	refreshItemsNow33(2,state);
	$("#sid").find('option[id="'+state+'"]').attr("selected",true);
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
	if($.trim($("#country").find("option:selected").text())!="United States"){
		$("#zip").val("");
		$("#city").val("");
	}
}
function refreshItemsNow()
{
	debugger;
  o = c(writeSelect);
  var val = $("#country").children(":selected").attr("id");
  oGET(o,'<bean:write name="path" property="pathvalue"/>/include/GetStates.jsp?st=state&Cid=' + val)
  
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
  	oGET(o33,'<bean:write name="path" property="pathvalue"/>/include/GetStates.jsp?st=state&Cid=' + val+"&sval="+sval)
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
    var BusinessTypeId = "<%= request.getAttribute("businessTypeId1")%>";
	$('select[id="businessTypeId"]').find('option[value="'+BusinessTypeId+'"]').attr("selected",true);
	
	debugger;
	 var val = $("#country").children(":selected").attr("id");
	//var  value =  document.getElementById("iCountry").value;
	if(val == 3){
		$("#stateRow").hide();
		$("#ProvinceRow").show();
	}else{
		$("#stateRow").show();
		$("#ProvinceRow").hide();
	}
}
</script>
</head>
<body onload="init()">
	<%-- <a href="<bean:write name="path" property="pathvalue"/>/Invoice.do?tabid=Invoice"><h3 style="float: left;">Go to Home</h3></a> --%>
    <!-- CONTENT -->
	<div id="ddcolortabsline">&nbsp;</div>
    	<!-- <div id="content" class="container"> -->
    	<div style="width: 100%; text-align: center;">
    		<div id="content_inner">
        		<div id="errors" style="color: red;">
    			</div>
        		<h1>
        			<bean:message key="BzComposer.companyinfo.title"/>
        			<!-- Company information -->
       			</h1>
        		<!-- <div id="setup"> -->
        		<div id="setup">
					<html:form action="updateEditedCompanyinfo.do?" enctype="multipart/form-data" method="post" styleId="frmUpdateCompany">				
						<!-- <table cellpadding="0" cellspacing="0" border="0" width="72%" align="right" > -->
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td colspan="3">
									<span class="sub_heading">
										<bean:message key="BzComposer.companyinfo.aboutyou"/>
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
															<bean:message key="BzComposer.companyinfo.emailaddress"/>
															<!-- Email Address: -->
															<span class="inputHighlighted">*</span>
														</td>
														<td>
															<%-- <html:text property="email" size="35" maxlength="80" style="font-size:1em;"/> --%>
															<html:text property="email" size="30" 
															maxlength="80" style="font-size:1em;" styleId="email"/>
														</td>
														<td/>
													</tr>
													<tr>
														<td id="t_txtFirstName">
															<!-- &nbsp; -->
															<bean:message key="BzComposer.Companyinformation.FirstName" />
															:<span class="inputHighlighted">*</span>
														</td>
														<td>
															<%-- <html:text property="firstName" maxlength="50" style="font-size:1em;"/> --%>
															<html:text property="firstName" maxlength="50" 
															style="font-size:1em;" size="30" styleId="firstName"/>
														</td>
														<td/>
													</tr>
													<tr>
														<td id="t_txtLastName">
															<!-- &nbsp; -->
															<bean:message key="BzComposer.Companyinformation.LastName" />
															:<span class="inputHighlighted">*</span>
														</td>
														<td>
															<%-- <html:text property="lastName" maxlength="50" style="font-size:1em;"/> --%>
															<html:text property="lastName" maxlength="50" 
															size="30" style="font-size:1em;" styleId="lastName"/>
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
																<option value=""><bean:message key="BzComposer.selectlanguage"/></option>
																<option value="en"><bean:message key="BzComposer.selectlanguage.english"/></option>
																<option value="zh"><bean:message key="BzComposer.selectlanguage.chinese"/></option>
																<option value="es"><bean:message key="BzComposer.selectlanguage.spanish"/></option>
															</select>
														</td>				
														<td/>
													</tr>
													<tr>
														<td colspan="3">
															<span class="sub_heading">
																<bean:message key="BzComposer.companyinfo.aboutcompany"/>
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
															<bean:message key="BzComposer.Companyinformation.CompanyName" /> :
															<span class="inputHighlighted">*</span>
														</td>
														<%-- commented on 05-05-2020 
														<td>
															<html:text property="companyName" disabled="true" size="35" style="width: 80%;"/>
														</td> --%>
														<td>
															<html:text property="companyName" size="30" 
															style="font-size:1em;" styleId="companyName"/>
														</td>
														<td/>
													</tr>
													<tr>
														<td id="t_txtCompanyName">
															<!-- &nbsp; -->
															<bean:message key="BzComposer.Companyinformation.CompanyDBAName" />:
															<span class="inputHighlighted"></span>
														</td>
														<td>
															<%-- <html:text property="nickName" size="35" style="font-size:1em;"/> --%>
															<html:text property="nickName" size="30" 
															style="font-size:1em;" styleId="nickName"/>
														</td>											
														<td/>
													</tr>
													<tr>
														<td>
															<!-- &nbsp; -->
															<bean:message key="BzComposer.Companyinformation.Type" /> 
															<span class="inputHighlighted">*</span>
														</td>
														<td>
															<%-- <html:text property="type" size="35" disabled="true" 
															style="font-weight: bold;font-size:1em;"/> --%>
															<html:select property="businessTypeId" styleId="businessTypeId">
																 <logic:present name="CompanyInfoForm" property="listOfBusinessType"> 
																	<logic:iterate name="CompanyInfoForm" id="objList1" property="listOfBusinessType" scope="session">
																	<option value="<bean:write name='objList1' property='businessTypeId' />"><bean:write name="objList1" property="businessName" /></option>
																	</logic:iterate>
																 </logic:present> 
															</html:select>
														</td>
														</tr>
													<tr>
														<td id="t_txtAddress1">
															<!-- &nbsp; -->
															<bean:message key="BzComposer.Companyinformation.Address1" />
															:<span class="inputHighlighted">*</span>
														</td>
														<td>
															<%-- <html:text property="address1" size="50" 
															style="font-size:1em;"/> --%>
															<html:text property="address1" size="30" 
															style="font-size:1em;" styleId="address1"/>
														</td>
														<td/>
													</tr>
													<tr>
														<td id="t_txtAddress2">
															<!-- &nbsp; -->
															<bean:message key="BzComposer.Companyinformation.Address2"/>:
															<span class="inputHighlighted"></span>
														</td>
														<td>
															<%-- <html:text property="address2" size="50" 
															style="font-size:1em;"/> --%>
															<html:text property="address2" size="30" 
															style="font-size:1em;" styleId="address2"/>
														</td>
														<td/>
													</tr>
													<tr>
														<td id="t_lblZip">
															<!-- &nbsp; -->
															<bean:message key="BzComposer.companyinfo.zipcode"/>
															<!-- Zip Code: -->
															<span class="inputHighlighted">*</span>
														</td>
														<td>
															<%-- <html:text property="zip" 
															onkeydown="return numbersonly(event,this.value)" 
															size="25" style="font-size:1em;"/> --%>
															<html:text property="zip" onchange="setState()" size="30" style="font-size:1em;" styleId="zip"/>
														</td>
														<td/>
													</tr>
													<tr id="stateRow">
														<td id="t_state">
															<bean:message key="BzComposer.Customer.State" />
														</td>
														<td id="t_statedata" style="font-size:1em;">
												
														</td>
														<logic:present name="state_gen">
															<script>
															var contry=document.CompanyInfoForm.country.value;

														    //debugger
														    //refreshItemsNow33(contry,'<bean:write name="state_gen" />');
														    refreshItemsNow33(2,'<bean:write name="state_gen" />');
														    </script>    
														</logic:present>	    
														<logic:notPresent  name="state_gen">
														</logic:notPresent>
													</tr>
													<tr id="ProvinceRow">
														<td id="t_txtProvince">
															<!-- &nbsp; -->
															<bean:message key="BzComposer.Companyinformation.Province" />:
															<span class="inputHighlighted"></span>
														</td>
														<td>
															<%-- <html:text property="province" size="25" style="font-size:1em;"/> --%>
															<html:text property="province" size="30" 
															style="font-size:1em;" styleId="province"/>
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
															<bean:message key="BzComposer.companyinfo.city"/>
															<!-- city: -->
															<span class="inputHighlighted">*</span>
														</td>
														<td id="txtcityData">
															<%-- <html:text property="city" size="35" 
															style="font-size:1em;"/> --%>
															<div id="cityDiv">
																<html:text property="city" size="30" 
																style="font-size:1em;" styleId="city"/>
															</div>
															<%-- <div id="cityDiv">
																<table>
																	<tr><td>
																	<logic:present name="sList">
																	<logic:notEmpty name="sList" scope="request">
																	<html:select styleId="city" property="city" style="width:280px;" onchange="setZipcode(this.value)">
																		<html:option value="0">
																			<bean:message key="BzComposer.register.selectcity"/>
																		</html:option>
																		<logic:iterate name="sList" id="objList1">
																				<option value="<bean:write name="objList1" property="zip" />">
																					<bean:write name="objList1" property="city" />
																				</option>
																			</logic:iterate>
																	</html:select>
																	</logic:notEmpty>
																	<logic:empty name="sList" scope="request">
																		<html:text styleId="city" property="city" size="30"/>
																	</logic:empty>
																	</logic:present>
																</td></tr>
																</table>
															</div> --%>
														</td>
														<td/>
													</tr>
													<tr>
														<td id="t_country">
															<bean:message key="BzComposer.Customer.Country" />
														</td>
														<td>
															<html:select property="country" 
																	onchange="refreshItemsNow();" 
																	styleId="country" style="font-size:1em;width:285px;">	
																		<html:option value="0">
																			<bean:message key="BzComposer.ComboBox.Select" />
																		</html:option>
																		<%-- <html:options collection="cList" 
																		property="value" labelProperty="label" /> --%>
																		<logic:present name="cList">
																			<logic:iterate name="cList" id="cList" scope="request">
																				<option id="<bean:write name="cList" property="countryId" />"
																						value="<bean:write name="cList" property="country" />">
																					<bean:write name="cList" property="country" />
																				</option>
																			</logic:iterate>
																		</logic:present> 
															</html:select>
														</td>
													</tr>
										  			
													<%-- <tr>
														<td id="t_txtProvince">
															<!-- &nbsp; -->
															<bean:message key="BzComposer.Companyinformation.Province" />:
															<span class="inputHighlighted"></span>
														</td>
														<td>
															<html:text property="province" size="25" style="font-size:1em;"/>
															<html:text property="province" size="30" 
															style="font-size:1em;" styleId="province"/>
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
															<bean:message key="BzComposer.Companyinformation.Phone" />
															:<span class="inputHighlighted"></span>
														</td>
														<td>
															<%-- <html:text onkeydown="return numbersonly(event,this.value)"
															property="phone" size="25" style="font-size:1em;"/> --%>
															<html:text onchange="setPhonePattern(this.value)"
															property="phone" styleId="phone" size="30" style="font-size:1em;"/>
														</td>
														<td/>
													</tr>
													<tr>
														<td>
															<bean:message key="BzComposer.Companyinformation.CellPhone"/>:
														</td>
														<td>
															<html:text property="cellPhone" styleId="cellPhone" 
															size="30" style="font-size:1em;" onchange="setCellPhonePattern(this.value)"/>
														</td>
													</tr>
													<tr>
														<td id="t_txtFax">
															<!-- &nbsp; -->
															<bean:message key="BzComposer.companyinfo.fax"/>
															<!-- Fax: -->
															<span class="inputHighlighted"></span>
														</td>
														<td>
															<%-- <html:text property="fax" size="25" 
															style="font-size:1em;"/> --%>
															<html:text property="fax" styleId="fax" size="30" 
															style="font-size:1em;"/>
														</td>
													</tr>
													<%-- <tr>
														<td colspan="3">
															<span class="sub_heading">
																<bean:message key="BzComposer.companyinfo.security"/>
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
															<bean:message key="BzComposer.companyinfo.loginname"/>
															<!-- Login Name: -->
															<span class="inputHighlighted">*</span>
														</td>
														<td width="50%">
															<!-- <input type="text" name="userID" size="10"	> -->
															<html:text property="userName" 
															styleId="userName" disabled="true" size="50" 
															style="font-size:1em;"/>
															<html:text property="userName" 
															styleId="userName" disabled="true" size="30" 
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
															<bean:message key="BzComposer.companyinfo.password"/>
															<!-- Password: -->
															<span class="inputHighlighted">*</span>
														</td>
														<td id="t_password">
															<html:password property="password" 
															disabled="true" styleId="password" 
															size="25" style="font-size:1em;"/>
															<html:password property="password" 
															styleId="t_password11" 
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
															<bean:message key="BzComposer.companyinfo.retypepassword"/>
															<!-- Retype Password: -->
															<span class="inputHighlighted">*</span>&nbsp;
														</td>
														<td>
															<html:password property="confirmPassword" 
															disabled="true" styleId="confirmPassword" 
															size="25" style="font-size:1em;"/>
															<html:password property="confirmPassword" 
															styleId="t_password_copy11" 
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
												<input type="button" class="formbutton" name="Update" 
												value="<bean:message key='BzComposer.global.update'/>" 
												title="Update Customer Information"
												onclick="updateComapany();" style="font-size:1em;"/>
												<!-- <input type="button" class="formbutton" onclick="closeme();" name="Close" value="Close" title="close"/> -->
												<input type="hidden" name="stname" value="" id="stateId" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</html:form>
				</div>
        	</div>        
      	</div>
    <!-- </div>
    FINISH
  </div>
   FOOTER -->
</body>
</html:html>
<%-- </html> --%>
<script>
function updateComapany()
{
    debugger;
	var sel = document.getElementById("businessTypeId");
    var selectbusinessTypeId = sel.options[sel.selectedIndex].value;
    
	var email = document.getElementById("email").value;
	var fName = document.getElementById("firstName").value;
	var lName = document.getElementById("lastName").value;
	var companyName = document.getElementById("companyName").value;
	var nickName = document.getElementById("nickName").value;
	var address1 = document.getElementById("address1").value;
	var address2 = document.getElementById("address2").value;
	var zip = document.getElementById("zip").value;
	 debugger;
	var stateName = document.getElementById("sid").value;
	var cityName = document.getElementById("city").value;
	var countryName = $.trim(document.getElementById("country").value);
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
		errorMessage += "<br/><h2><bean:message key='err.registration.password.mismatch'/></h2>";
		return false;
	} */
	var errorMessage="";
	if(email == "" && fName == "" && lName == "" && companyName == "" && address1 == "" && cityName == ""){
		errorMessage += "<bean:message key='MultiUserForm'/>";
	}
	else
	{
		if(email == "" || email == null){
			errorMessage += "<br/><h2><bean:message key='BzComposer.companyinfo.enteremailaddress'/></h2>";
		}
		if (!ValidateEmail(email)) {
	        errorMessage += "<h2><bean:message key='err.Email'/></h2>";
	    }
		if(fName == "" || fName == null){
			errorMessage += "<br/><h2><bean:message key='err.Firstname'/></h2>";
		}
		if(lName == "" || lName == null){
			errorMessage += "<br/><h2><bean:message key='err.LastName'/></h2>";
		}
		if(companyName == "" || companyName == null){
			errorMessage += "<br/><h2><bean:message key='BzComposer.companyinfo.enterCompanyName'/></h2>";
		}
		if(address1 == "" || address1 == null){
			errorMessage += "<br/><h2><bean:message key='BzComposer.companyinfo.enterAddress'/></h2>";
		}
		if(zip == "" || zip == null){
			errorMessage += "<h2><bean:message key='err.Zip'/></h2>";
		}
		if(city == "" || city == null){
			errorMessage += "<h2><bean:message key='err.City'/></h2>";
		}
	}
	if(errorMessage !=""){
		document.getElementById("errors").innerHTML = errorMessage;
		return false;
		event.preventDefault();
	}
	else
	{
		/* document.forms[0].action = "updateEditedCompanyinfo.do?tabid=edit";
		document.forms[0].submit(); */
		/* document.forms["frmUpdateCompany"].action = "updateEditedCompanyinfo.do?tabid=edit&&cityName="+cityName+"&&stateName="+stateName+"&countryName="+countryName;
		document.forms["frmUpdateCompany"].submit(); */
		/* document.forms["CompanyInfoForm"].action = "updateEditedCompanyinfo.do?tabid=edit&&cityName="+cityName+"&&stateName="+stateName+"&countryName="+countryName;
		document.forms["CompanyInfoForm"].submit(); */
		//debugger
	
		document.CompanyInfoForm.companyName.value = companyName;
		$.ajax({
			type: "POST",
	   		/* url:"updateEditedCompanyinfo.do?tabid=edit&&cityName="+cityName+"&&stateName="+stateName+"&countryName="+countryName+"&&companyName="+companyName+"&&phone="+phone, */
	   		url:"updateEditedCompanyinfo.do?tabid=edit&&companyName="+companyName+"&&nickName="+nickName+
	   				"&&fName="+fName+"&&Typeid="+selectbusinessTypeId+"&&lName="+lName+"&&add1="+address1+"&add2="+address2+
	   				"&&province="+province+"&&phone="+phone+"&&cellphone="+cellphone+"&&fax="+fax+"&&email="+email+"&&stateName="+stateName,
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
	        	"<bean:message key='BzComposer.global.ok'/>": function () {
	        		$(this).dialog("close");
	                return false;
	        	},
	            "<bean:message key='BzComposer.global.cancel'/>": function () {
	                $(this).dialog("close");
	                return false;
	            }
        	}
	});
	}
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
	<h3><bean:message key="BzComposer.common.erroroccurred"/></h3>	
</div>
<div id="enterPhoneNumber" style="display:none;font-size:1em;">
	<h3><bean:message key="BzComposer.companyinfo.enterphonenumber"/></h3>	
</div>
<div id="enterZip" style="display:none;font-size:1em;">
	<h3><bean:message key="err.Zip"/></h3>	
</div>

<!-- Dialog box used in this page -->
<div id="showsuccessdialog" style="display:none;">
	<p>Record updated</p>
</div>