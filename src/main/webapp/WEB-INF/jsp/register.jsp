<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title><spring:message code="BzComposer.registertitle"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/myAppUtility.js"></script>
<%@ include file="templateHeader.jsp"%>
<%@ page import="java.util.*"%>
<style type="text/css">
/* Tooltip Starts */
.msg-error {
    text-align: center; color:red !important; font-weight: 500;
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

img,div,a,input {
	behavior: url(iepngfix.htc)
}

#logo {
    background: url(images/logo.png) left top no-repeat; width: 238px; height: 64px; float: left; margin: 0 0 0 50px;
}
hr {
    border: 0; width: 100%; color: #f00; background-color: #05A9C5; height: 1px;
}
.sub_heading {
    font-size: 14px;font-weight: bold;padding: 0 0 0 0;
}

#table-negotiations table tbody tr{
    margin:5px auto;
}
#table-negotiations table tbody tr td{
    width:auto;vertical-align:text-top;
}
#table-negotiations table tbody tr td:nth-of-type(1){
    width:170px;
}
#table-negotiations table tbody tr td input,
#table-negotiations table tbody tr td select{
	width:304px;
    height: 32px;
    border-radius: 0;
}

#table-negotiations table tbody tr td .join_us_phone {
    display: flex;
    width: 304px;
    border:1px solid #cccccc;
}
#table-negotiations table tbody tr td .join_us_phone select{
	flex:0 0 auto;
	width:auto;
	border:0;
	border-radius: 0;
	margin:auto;
}
#table-negotiations table tbody tr td .join_us_phone input{
    flex: 1 0 auto;
    width: 1%;
    border: 0;
    border-radius: 0;
    margin: auto;
}

.select_option{
    display: inline-block;vertical-align: top;width: 100%;margin-bottom: 15px;
}
.inputfild{
    margin-bottom: 15px;display: inline-block;vertical-align: top;width: 100%;max-width: 304px;
}
.errorMsg{
    color: #D8000C;background-color: #FFD2D2;
}
</style>
<script type="text/javascript">
selectValidCountryMsg = "<spring:message code='BzComposer.register.selectvalidcountry'/>";
selectValidStateMsg = "<spring:message code='BzComposer.register.selectvalidstate'/>";
selectValidZipcodeMsg = "<spring:message code='BzComposer.register.selectvalidzipcode'/>";
noRecordsFoundMsg = "<spring:message code='BzComposer.employee.norecordsfound'/>";

function loadPageDate(){
    $("#countryID").val(231).change();
}

$(document).ready(function(){
$("#countryID").change(function(){
    var countryId = document.getElementById("countryID").value;
    let countryCode = $("#countryID").find("option:selected").data("code");
    $("#phonecode").val(countryCode);
    $("#phonecode2").val(countryCode);
    loadStatesByCountryID(countryId, 1);
});
});

function goToLoginPage(){
	window.location = "/Login?tabid=loginPage";
}

function validateRegisterForm(){
    let response = grecaptcha.getResponse();
    jQuery('#captchaResponse').text('');
    if (response.length === 0) {
       $('#captchaResponse').text("reCAPTCHA is mandatory");
       return false;
    }
    return true;
}
function showLocale(){
	var lang = document.getElementById("locale").value;
	window.location = "/changeLocale?requestPage=RegisterPage&lang="+lang;
}
</script>
</head>
<body class="register" onload="loadPageDate();">
<div id="pageContent" style="width:100%;height: 100%;">
<header>
    <!-- Mobile View Start here -->
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
                            <button type="button" class="btn btn-primary" onclick="goToLoginPage()"><spring:message code="BzComposer.login"/></button>
                            <button type="button" class="btn btn-primary"><spring:message code="BzComposer.register"/></button>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            <div  style="position: relative;background: #e6e6e6;">&nbsp;</div>
        </div>
    </div>
    <!-- Web View Start here -->
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
                            <button type="button" class="btn btn-primary" onclick="goToLoginPage()"><spring:message code="BzComposer.login"/></button>
                            <button type="button" class="btn btn-primary"><spring:message code="BzComposer.register"/></button>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
    <div class="section section-navbar-1 bg-grey hidden-tablet-landscape" id="js-navbar-fixed">
        <nav><div class="au-navbar navbar-1">&nbsp;</div></nav>
    </div>
</header>
<section>
<form:form action="/addUserMember" method="post" name="MultiUserForm" modelAttribute="multiUserForm" onsubmit="return validateRegisterForm();">
	<div class="container">
	<div id="table-negotiations" style="width:70%;margin: 0px auto; margin-top:30px;float:left;">
	    <h2>
            <spring:message code="BzComposer.register.title"/>
        </h2>
		<table style="width:100%;border: 1px solid lightgray;padding: 20px;margin-bottom:10px;" cellspacing="10">
		    <tr><td colspan="3">&nbsp;</td></tr>
			<tr>
				<td colspan="3" style="text-align: center;">
					<div id="errorDiv" class="bg-danger">
						<div align="center" class="errorMsg">
							<FONT COLOR="Red">${errorMsg}<FONT COLOR="Red">
						</div>
	   				</div>
				</td>
			</tr>
			<tr>
   				<td colspan="3" class="sub_heading">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.membership"/>
                </td>
   	 		</tr>
   	 		<tr><td colspan="3"><hr></td></tr>
            <tr>
                <td style="width:25%;">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.membershiplevels"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td style="width:50%;">
                    <form:select path="membershipLevel">
                        <!-- <form:option value="select membership"><spring:message code="BzComposer.register.selectmembership"/></form:option> -->
                        <form:option value="standard"><spring:message code="BzComposer.register.standardmembership"/></form:option>
                        <form:option value="professional"><spring:message code="BzComposer.register.professionalmembership"/></form:option>
                        <form:option value="eSales"><spring:message code="BzComposer.register.esalesmembership"/></form:option>
                    </form:select>
                </td>
                <td style="width:25%;">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="3" class="sub_heading">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.aboutyou"/>
                </td>
            </tr>
            <tr>
                <td colspan="3"><hr></td>
            </tr>
            <tr>
                <td id="t_email">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.emailaddress"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="inputfild">
                    <form:input type="email" path="emailAddress" required="true" />
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td id="t_txtFirstName">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.firstname"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="firstName" required="true" />
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td id="t_txtLastName">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.lastname"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="lastName" required="true" />
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.jobposition"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="select_option" >
                    <form:select path="jobPosition">
                        <!-- <option value="select jobPosition"><spring:message code="BzComposer.register.selectjobposition"/></option> -->
                        <form:option value="president" ><spring:message code="BzComposer.register.president"/></form:option>
                        <form:option value="manager"><spring:message code="BzComposer.register.manager"/></form:option>
                        <form:option value="programmer"><spring:message code="BzComposer.register.programmer"/></form:option>
                    </form:select>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td colspan="3" class="sub_heading">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.aboutcompany"/>
                </td>
            </tr>
            <tr><td colspan="3"><hr></td></tr>
            <tr>
                <td id="t_txtCompanyName">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.companyname"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="companyName" required="true" />
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td id="t_txtCompanyName">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.companynickname"/>
                    <span class="inputHighlighted"></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="nickName" />
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td id="t_txtAddress1">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.addressone"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="address1" style="width: 370px;" required="true" />
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td id="t_txtAddress2">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.addresstwo"/>
                    <span class="inputHighlighted"></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="address2" style="width: 370px;" />
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="lblZipcodeShow">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.zipcode"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="lblPostalcodeShow">
                     &nbsp;&nbsp;<spring:message code="BzComposer.global.postalcodes"/>:
                     <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>

                <td class="inputfild">
                    <form:input type="text" path="zip" required="true" onfocusout="loadAddressDetailsByZipcode(this.value, 1)" onkeypress="return numbersonly(event, this.value)" />
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td id="t_txtcity">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.city"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="select_option">
                    <form:select path="city" id="cityID">
                        <option><spring:message code="BzComposer.register.selectcity"/></option>
                    </form:select>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="lblStateShow">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.state" />
                    <span class="inputHighlighted"><spring:message  code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="lblProvinceShow">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.province" />
                    <span class="inputHighlighted"><spring:message  code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="select_option">
                    <form:select path="state" id="stateID" onchange="loadCitiesByStateID(this.value, 1);">
                        <option><spring:message code="BzComposer.register.selectstate"/></option>
                        <option>state Id</option>
                    </form:select>
                    <form:hidden path="province" />
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td id="t_lbCountry">
                    &nbsp;&nbsp;<spring:message  code="BzComposer.register.counry"/>
                    <span class="inputHighlighted"><spring:message  code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="select_option">
                    <form:select path="country" id="countryID" required="true">
                        <form:option value="0"><spring:message  code="BzComposer.register.selectcounry"/></form:option>
                        <c:forEach items="${countryList}" var="curObject">
                            <form:option data-code="${curObject.phoneCode}" value="${curObject.countryId}">
                                <b>${curObject.countryName}</b>
                            </form:option>
                        </c:forEach>
                    </form:select>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td id="t_txtPhone">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.phone"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="select_option join_us_phoneWrap">
                    <div class="join_us_phone">
                        <form:select path="phonecode">
                            <c:forEach items="${countryList}" var="curObject">
                                <option value="${curObject.phoneCode}">
                                    <b>${curObject.phoneCode}</b>
                                </option>
                            </c:forEach>
                        </form:select>
                        <form:input type="tel" path="phone" minlength="10" maxlength="14" onchange="validateUSAPhoneNumber(this, false);" onkeypress="return numbersonly(event, this.value);" required="true" />
                    </div>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td id="t_txtMobile">
                    &nbsp;&nbsp;<spring:message code="BzComposer.global.mobileNumber"/>
                </td>
                <td class="select_option join_us_phoneWrap">
                    <div class="join_us_phone">
                        <select id="phonecode2">
                            <c:forEach items="${countryList}" var="curObject">
                                <option value="${curObject.phoneCode}">
                                    <b>${curObject.phoneCode}</b>
                                </option>
                            </c:forEach>
                        </select>
                        <form:input type="tel" path="cellPhone" minlength="10" maxlength="14" onchange="validateUSAPhoneNumber(this, false);" onkeypress="return numbersonly(event, this.value);" required="true" />
                    </div>
                </td>
                <td class="inputfild">
                    <form:checkbox path="sameAsPhoneNumber" style="width: 20px;vertical-align: middle;" />
                    (<spring:message code="BzComposer.global.isPhoneNumber" />)
                </td>
            </tr>
            <tr>
                <td id="t_txtFax">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.fax"/>
                    <span class="inputHighlighted"></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="fax" />
                </td>
                <td>&nbsp;</td>
            </tr>
        <!-- <tr>
                <td id="t_txtTaxID">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.taxid"/>
                    <span class="inputHighlighted"></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="taxID" />
                </td>
                <td>&nbsp;</td>
            </tr> -->
            <tr>
                <td colspan="3" class="sub_heading">
                    &nbsp;&nbsp;<spring:message code="BzComposer.register.security"/>
                </td>
            </tr>
            <tr><td colspan="3"><hr></td></tr>
            <!-- <tr>
                    <td id="t_uname" width="40%">
                        &nbsp;&nbsp;<spring:message code="BzComposer.register.loginname"/>
                        <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                    </td>
                    <td class="inputfild">
                        <form:input type="text" path="userName" required="true" />
                    </td>
                    <td>&nbsp;</td>
                </tr> -->
                <tr>
                    <td id="t_password">
                        &nbsp;&nbsp;<spring:message code="BzComposer.register.password"/>
                        <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                    </td>
                    <td class="inputfild">
                        <form:input type="password" path="password" required="true" />
                    </td>
                    <td align="center">
                        <div class="tooltip">
                            <img src="images/q.gif" border="0" width="20" height="20">
                                <span class="tooltiptext">Your password is case sensitive and must be between 6 and 16 characters in length.</span>
                        </div>

                    </td>
                </tr>
                <tr>
                    <td id="t_password_copy" nowrap>
                        &nbsp;&nbsp;<spring:message code="BzComposer.register.confirmpassword"/>
                        <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>&nbsp;
                    </td>
                    <td class="inputfild">
                        <form:input type="password" path="confirmPassword" required="true" />
                    </td>
                    <td>&nbsp;</td>
                </tr>
            <!-- <tr>
                    <td colspan="3" class="sub_heading">
                        &nbsp;&nbsp;<spring:message code="BzComposer.register.forgotpassword"/>
                    </td>
                </tr>
                <tr><td colspan="3"><hr></td></tr>
                <tr>
                    <td id="t_question">
                        &nbsp;&nbsp;<spring:message code="BzComposer.register.reminderquestion"/>
                        <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                    </td>
                    <td class="select_option">
                        <form:select path="passwordhint" required="true">
                            <option value="select a question"><spring:message code="BzComposer.register.selectquestion"/></option>
                            <option value="what is your nickname"><spring:message code="BzComposer.register.nickname"/></option>
                            <option value="what is your pet name"><spring:message code="BzComposer.register.petname"/></option>
                            <option value="what is your hobby"><spring:message code="BzComposer.register.hobby"/></option>
                            <option value="what is golden moment in your life"><spring:message code="BzComposer.register.goldenmoment"/></option>
                        </form:select>
                    </td>
                    <td align="center">
                        <div class="tooltip">
                        <img src="images/q.gif" border="0" width="20" height="20">
                            <span class="tooltiptext">
                                The Password Reminder Question is the question you will be asked if you forget your password.
                            </span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td id="t_answer">
                        &nbsp;&nbsp;<spring:message code="BzComposer.register.answer"/>
                        <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                    </td>
                    <td class="inputfild">
                        <form:input type="text" path="passwordAns" required="true" />
                    </td>
                    <td align="center">
                    <div class="tooltip">
                        <img src="images/q.gif" border="0" width="20" height="20">
                        <span class="tooltiptext">Answer For Password Reminder Question.</span>
                    </div>
                    </td>
                </tr> -->
                <tr>
                    <td></td>
                    <td id="t_captcha">
                        <div class="g-recaptcha" data-sitekey="6LdYG_YbAAAAABCyLjdCmVFrlS6rkKpHosXfMMoH"></div>
                        <span class="msg-error" id="captchaResponse"></span>
                        <br/>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td></td>
                    <td align="center">
                        <button type="submit" class="formButton"><spring:message code='BzComposer.global.submit'/></button> &nbsp;&nbsp;&nbsp;
                        <button type="button" class="formButton" onclick="goToLoginPage()"><spring:message code='BzComposer.global.cancel'/></button>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr><td colspan="3">&nbsp;</td></tr>
				</table>
			</div>
			<div style="margin: 0px auto; margin-top:50px;float:right;margin-bottom:50px;">
                <div style="width:300px;border: 1px solid lightgray;"><a href="https://www.joyofrelax.com/" target="_blank" rel="noopener noreferrer">
                    <img src="${pageContext.request.contextPath}/dist/template/images/loginBnA.png" style="width:300px;display:block;"></a>
                </div>
                <div style="width:300px;border: 1px solid lightgray;"><a href="https://www.ceragemusa.net" target="_blank" rel="noopener noreferrer">
                    <img src="${pageContext.request.contextPath}/dist/template/images/loginBnB.png" style="width:300px;display: block;"></a>
                </div>
                <div style="width:300px;border: 1px solid lightgray;"><a href="https://www.biomatplus.com/" target="_blank" rel="noopener noreferrer">
                    <img src="${pageContext.request.contextPath}/dist/template/images/loginBnC.png" style="width:300px;display: block;"></a>
                </div>
            </div>
		</div>
</form:form>
</section>
<!-- our services start -->
<%@ include file="templateFooter.jsp"%>
<div id="up-to-top">
    <i class="fa fa-angle-up"></i>
</div>
<%@ include file="templateScript.jsp"%>
</div>
<%@ include file="include/footer.jsp"%>
</body> 
</html>