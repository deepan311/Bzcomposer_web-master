<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title><spring:message code="BzComposer.logintitle"/></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src='https://www.google.com/recaptcha/api.js'></script>
<%@include file="templateHeader.jsp"%>
<style>
.msg-error { text-align: center; color: red; font-weight: 500; }
td { padding: 10px; }
</style>
<%
    Cookie[] cookies = request.getCookies();
    String userName = "",remember = "",pass="";
    if (cookies != null){
        for (Cookie cookie : cookies){
            if(cookie.getName().equals("cookieLoginUser"))
                userName = cookie.getValue();
            else if(cookie.getName().equals("cookieLoginPassword1"))
                pass = cookie.getValue();
            else if(cookie.getName().equals("cookieRememberMe"))
                remember = cookie.getValue();
        }
    }
%>
</head>
<body onload="loadFormValue()">
<div id="pageContent" style="width:100%;height: 100%;">
	<!-- page load-->
	<div class="page-loader">
		<div class="loader"></div>
	</div>
	<header>
	<!-- header / start-->
	<div class="hidden-tablet-landscape-up">
		<div class="header header-mobile-1">
			<div class="container">
				<div class="row">
					<div class="col-md-3" align="center">
						<div class="logo" style="max-width: 100%;">
							<a href="#" onclick="goToHomePage()">
								<img src="${pageContext.request.contextPath}/dist/template/images/icons/BzComposerLogo.png" alt="Consulting" style="max-width: 100%"/>
							</a>
						</div>
					</div>
				<div class="col-md-3">
					<div class="search-wrap" align="right">
						<div class="input-group" align="center">
							<input type="text" id="searchBox" name="searchBox" placeholder="<spring:message code="BzComposer.searchtext"/>" style="max-width: 100%;" />
							<div class="input-group-btn">
								<button class="btn btn-primary" onclick="searchText()"><spring:message code="BzComposer.searchbuttontext"/></button>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3" align="center">
					<div class="input-group">
						<select name="localeMobile" id="localeMobile" class="form-control" onchange="showLocaleMobile();">
							<!-- <option value=""><spring:message code="BzComposer.selectlanguage"/></option> -->
							<option value="en" ${currentLocale=='en'?'selected':''}><spring:message code="BzComposer.selectlanguage.english"/></option>
                            <option value="zh" ${currentLocale=='zh'?'selected':''}><spring:message code="BzComposer.selectlanguage.chinese"/></option>
                            <option value="es" ${currentLocale=='es'?'selected':''}><spring:message code="BzComposer.selectlanguage.spanish"/></option>
						</select>
					</div>
				</div>
				<div class="col-md-3" align="center">
					<div align="center">
						<div class="contact-widget contact-widget-1">
							<button type="button" class="btn btn-primary" onclick="redirectToLogin()"><spring:message code="BzComposer.login"/></button>
							<button type="button" class="btn btn-primary" onclick="openRegisterPage()"><spring:message code="BzComposer.register"/></button>
						</div>
					</div>
				</div>
			</div>
			</div>
			<div  style="position: relative;background: #e6e6e6;">&nbsp;</div>
		</div>
	</div>
	<!-- </div> -->
	<!-- When opens without responsive -->
	<div class="hidden-tablet-landscape">
		<div class="header header-1">
			<div class="container">
				<div class="row">
				<div class="col-md-3" align="left">
				<div class="block-left">
					<div class="logo">
						<a href="${pageContext.request.contextPath}">
						<img src="${pageContext.request.contextPath}/dist/template/images/icons/BzComposerLogo.png" alt="Consulting" />
						</a>
					</div>
				</div>
				</div>
				<div class="col-md-4">
					<div class="search-wrap" align="right">
						<div class="input-group" align="right">
							<input type="text" id="searchBox" name="searchBox" placeholder="<spring:message code="BzComposer.searchtext"/>" class="form-control" />
					 		<div class="input-group-btn">
								<button class="btn btn-primary" class="form-control" onclick="searchText()"><spring:message code="BzComposer.searchbuttontext"/></button>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-2" align="right">
					<div class="input-group">
						<select name="locale" id="locale" class="form-control" onchange="showLocale();">
							<option value="en" ${currentLocale=='en'?'selected':''}><spring:message code="BzComposer.selectlanguage.english"/></option>
							<option value="zh" ${currentLocale=='zh'?'selected':''}><spring:message code="BzComposer.selectlanguage.chinese"/></option>
							<option value="es" ${currentLocale=='es'?'selected':''}><spring:message code="BzComposer.selectlanguage.spanish"/></option>
						</select>
					</div>
				</div>
				<div class="col-md-3" align="right">
					<div align="center">
						<div class="contact-widget contact-widget-1">
							<button type="button" class="btn btn-primary" onclick="redirectToLogin()"><spring:message code="BzComposer.login"/></button>
							<button type="button" class="btn btn-primary" onclick="openRegisterPage()"><spring:message code="BzComposer.register"/></button>
						</div>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
	<div class="section section-navbar-1 bg-grey hidden-tablet-landscape" id="js-navbar-fixed">
		<nav>
            <div class="au-navbar navbar-1">&nbsp;</div>
        </nav>
    </div>
	<!-- header / end-->
	</header>
	 <!-- START LOGIN FORM HERE -->
	<section>
        <form action="/LoginValidate?tabid=chkLoginDetails" name="LoginForm" method="post" onsubmit="return validateLoginForm();">
        	<div class="container">
        		<div style="width:70%;margin: 0px auto; margin-top:30px;float:left;">
        		    <h2>
                        <spring:message code="BzComposer.login"/>
                    </h2>
        			<table style="width:85%;border: 1px solid lightgray;padding: 20px;" cellspacing="20">
        			    <tr>
                            <td colspan="3" align="center" style="color:red;">
                                <c:if test="${not empty loginError}">
                                    <c:if test="${loginError == false}">
                                        <spring:message code="err.user.username.wrong"/>
                                    </c:if>
                                </c:if>
                            </td>
        				</tr>
        				<tr>
                            <td>&nbsp;&nbsp;&nbsp; <spring:message code="MultiUserForm.emailAddress" /></td>
                            <td>
                                <input type="email" class="form-control" name="userName" style="width:300px;" value="${userName}" required />
                            </td>
                            <td></td>
        				</tr>
        				<tr>
                            <td>&nbsp;&nbsp;&nbsp; <spring:message code="MultiUserForm.password" /></td>
                            <td>
                                <input type="password" class="form-control" name="password" style="width:300px;" value="${pass}" required />
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div class="g-recaptcha" data-sitekey="6LdYG_YbAAAAABCyLjdCmVFrlS6rkKpHosXfMMoH"></div>
                                <span class="msg-error" id="captchaResponse"></span>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input type="checkbox" name="rememberMe" id="rememberMe" value="${remember}" onchange="rememberMeChanged()" />
                                <spring:message code="BzComposer.signin.rememberme"/>
                                <a href="#" style="margin-left:40px;">
                                    <span style="cursor: pointer; font-size:15px;"><spring:message code="BzComposer.signin.forgotpassword"/></span>
                                </a>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="2"></td>
                            <td class="form-group bca_loginbtn" align="right">
                                <button type="submit" id="submitBtn" class="btn btn-primary imgClass"><spring:message code='BzComposer.signin.login'/></button>

                                <input type="hidden" name="actionType" id="actionType" value=""/>
                            </td>
                        </tr>
        			</table>
        		</div>
        		<div style="margin: 0px auto; margin-top:50px;float:right;margin-bottom:50px;">
        		    <div style="width:300px;border: 1px solid lightgray;"><a href="https://www.joyofrelax.com/" target="_blank" rel="noopener noreferrer">
        		        <img src="${pageContext.request.contextPath}/dist/template/images/loginBnA.png" style="width:300px;display:block;"></a>
        		    </div>
        		    <div style="width:300px;border: 1px solid lightgray;"><a href="https://www.ceragemusa.net" target="_blank" rel="noopener noreferrer">
        		        <img src="${pageContext.request.contextPath}/dist/template/images/loginBnB.png" style="width:300px;display: block;"></a>
        		    </div>
        		    <!-- <div style="width:300px;border: 1px solid lightgray;"><a href="https://www.biomatplus.com/" target="_blank" rel="noopener noreferrer">
        		        <img src="${pageContext.request.contextPath}/dist/template/images/loginBnC.png" style="width:300px;display: block;"></a>
        		    </div> -->
        		</div>
        	</div>
        </form>
	</section>
<!-- our services start -->
<%@ include file="templateFooter.jsp"%>
<div id="up-to-top">
    <i class="fa fa-angle-up"></i>
</div>
<%@ include file="templateScript.jsp"%>
</div>
<script type="text/javascript">
function goToHomePage(){
    window.location = "/";
}
function openRegisterPage(){
	window.location = "Register?tabid=register";
}
function redirectToLogin(){
	window.location = "Login?tabid=loginPage";
}

function showLocale(){
	var lang = document.getElementById("locale").value;
	window.location = "/changeLocale?requestPage=LoginPage&lang="+lang;
}

function showLocaleMobile(){
	var lang = document.getElementById("localeMobile").value;
	window.location = "/changeLocale?requestPage=LoginPage&lang="+lang;
}

function loadFormValue(){
    var user = document.LoginForm.userName.value;
    var pass = document.LoginForm.password.value;
    var rem = "<%= remember%>";
    if(rem == "on"){
        document.getElementById("rememberMe").checked = true;
        document.forms[0].userName.value = userName;
        document.forms[0].password.value = pass;
        document.forms[0].rememberMe.value = rem;
    }
    else{
        document.getElementById("rememberMe").checked = false;
        document.forms[0].userName.value = "";
        document.forms[0].password.value = "";
        document.forms[0].rememberMe.value = rem;
    }
}

function validateLoginForm(){
    var user = document.LoginForm.userName.value.trim();
    var pass = document.LoginForm.password.value.trim();
    let response = grecaptcha.getResponse();
    jQuery('#captchaResponse').text('');
    if(user=="" || user==" "){
        $("#userName").focus();
        return false;
    }
    else if(pass==""||pass==" "){
        $("#userName").focus();
        return false;
    }
    else if (response.length === 0) {
       $('#captchaResponse').text("reCAPTCHA is mandatory");
       return false;
    }
    else{
        document.LoginForm.rememberMe.value = document.LoginForm.rememberMe.value;
        document.getElementById('actionType').value = document.getElementById('rememberMe').value;
        return true;
    }
}

function rememberMeChanged(){
    let isChecked = document.getElementById('rememberMe').checked;
    if(isChecked){
        document.LoginForm.rememberMe.value = "on";
    }
    else{
        document.LoginForm.rememberMe.value = "off";;
    }
}
</script>
</body>
</html>
