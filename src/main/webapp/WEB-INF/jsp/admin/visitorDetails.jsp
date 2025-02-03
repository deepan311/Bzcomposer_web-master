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
<%@ include file="/WEB-INF/jsp/templateHeader.jsp"%>
<%@ page import="java.util.*"%>
<style type="text/css">
.msg-error {
    text-align: center; color:red !important; font-weight: 500;
}
input,textarea,select{
	display: inline-block;
	padding: 4px;
	margin-bottom: 9px;
	line-height: 14px;
	color: #555555;
	border: 1px solid #cccccc;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	width:304px;
    height: 25px;
    font-size: 15px;
}
img,div,a,input {
	behavior: url(iepngfix.htc)
}

.sub_heading {
    font-size: 14px;font-weight: bold;padding: 0 0 0 0;
}

#table-negotiations table tbody tr{
    margin:5px auto;
}
#table-negotiations table tbody tr td{
    width:auto;vertical-align:text-top;font-size: 15px;padding-left: 5px;
}
#table-negotiations table tbody tr td:nth-of-type(1){
    width:170px;
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
    margin-bottom: 15px;display: inline-block;vertical-align: top;width: 100%;;
}
.errorMsg{
    color: #D8000C;background-color: #FFD2D2;
}
.successMsg{
    color: green;
}
</style>
<script type="text/javascript">
self.moveTo(100, 10);
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

function closeMe(){
	window.close();
}

function validateRegisterForm(){
    if (document.multiUserForm.dateExpiry.value.length === 0) {
       document.getElementById("dateExpiry").style.borderColor = "red";
       return false;
    }
    return true;
}
</script>
</head>
<body class="register" onload="loadPageDate();">
<div id="pageContent" style="width:100%;height: 100%;">
<section>
<form:form action="/administer/addUserMember" method="post" name="multiUserForm" modelAttribute="multiUserForm" onsubmit="return validateRegisterForm();">
	<div class="container" style="width:95%;">
	<div id="table-negotiations" style="width:100%;margin: 0px auto; margin-top:10px;float:left;">
	    <h3>
            <spring:message code="BzComposer.register.title"/>
        </h3>
		<table style="width:100%;border: 1px solid lightgray;margin-bottom:10px;" cellspacing="10">
		    <tr><td colspan="3">&nbsp;</td></tr>
			<tr>
				<td colspan="3" style="text-align: center;">
					<div id="errorDiv" class="bg-danger">
						<div align="center" class="errorMsg" id="errorMsgID">
							<FONT COLOR="Red">${errorMsg}</FONT>
						</div>
	   				</div>
	   				<div id="errorDiv" class="bg-success">
                        <div align="center" class="successMsg">
                            <FONT COLOR="Red"><b>${successMsg}</b></FONT>
                        </div>
                    </div>
				</td>
			</tr>
			<tr>
   				<td colspan="5" class="sub_heading">
                    <spring:message code="BzComposer.register.membership"/>
                </td>
   	 		</tr>
   	 		<tr><td colspan="3"><hr></td></tr>
            <tr>
                <td>
                    <spring:message code="BzComposer.register.membershiplevels"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td>
                    <form:select path="membershipLevel">
                        <!-- <form:option value="select membership"><spring:message code="BzComposer.register.selectmembership"/></form:option> -->
                        <form:option value="standard"><spring:message code="BzComposer.register.standardmembership"/></form:option>
                        <form:option value="professional"><spring:message code="BzComposer.register.professionalmembership"/></form:option>
                        <form:option value="eSales"><spring:message code="BzComposer.register.esalesmembership"/></form:option>
                    </form:select>
                </td>
                <td colspan="3">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="5" class="sub_heading">
                    <spring:message code="BzComposer.register.aboutyou"/>
                </td>
            </tr>
            <tr>
                <td colspan="5"><hr></td>
            </tr>
            <tr>
                <td id="t_txtFirstName">
                    <spring:message code="BzComposer.register.firstname"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="firstName" required="true" />
                </td>
                <td id="t_txtLastName">
                    <spring:message code="BzComposer.register.lastname"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="lastName" required="true" />
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td id="t_email">
                    <spring:message code="BzComposer.register.emailaddress"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="inputfild">
                    <form:input type="email" path="emailAddress" required="true" />
                </td>
                <td>
                    <spring:message code="BzComposer.register.jobposition"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="select_option" >
                    <form:select path="jobPosition">
                        <!-- <option value="select jobPosition"><spring:message code="BzComposer.register.selectjobposition"/></option> -->
                        <form:option value="visitor">Visitor</form:option>
                        <form:option value="president" ><spring:message code="BzComposer.register.president"/></form:option>
                        <form:option value="manager"><spring:message code="BzComposer.register.manager"/></form:option>
                        <form:option value="programmer"><spring:message code="BzComposer.register.programmer"/></form:option>
                    </form:select>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td colspan="3" class="sub_heading">
                    <spring:message code="BzComposer.register.aboutcompany"/>
                </td>
            </tr>
            <tr><td colspan="3"><hr></td></tr>
            <tr>
                <td id="t_txtCompanyName">
                    <spring:message code="BzComposer.register.companyname"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="companyName" required="true" />
                </td>
                <td id="t_txtCompanyName">
                    <spring:message code="BzComposer.register.companynickname"/>
                    <span class="inputHighlighted"></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="nickName" />
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td id="t_txtAddress1">
                    <spring:message code="BzComposer.register.addressone"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="address1" required="true" />
                </td>
                <td id="t_txtAddress2">
                    <spring:message code="BzComposer.register.addresstwo"/>
                    <span class="inputHighlighted"></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="address2" />
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="lblZipcodeShow">
                    <spring:message code="BzComposer.register.zipcode"/>
                    <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="lblPostalcodeShow">
                     <spring:message code="BzComposer.global.postalcodes"/>:
                     <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="zip" required="true" onfocusout="loadAddressDetailsByZipcode(this.value, 1)" onkeypress="return numbersonly(event, this.value)" />
                </td>
                <td id="t_txtcity">
                    <spring:message code="BzComposer.register.city"/>
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
                    <spring:message code="BzComposer.register.state" />
                    <span class="inputHighlighted"><spring:message  code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="lblProvinceShow">
                    <spring:message code="BzComposer.register.province" />
                    <span class="inputHighlighted"><spring:message  code="BzComposer.CompulsoryField.Validation" /></span>
                </td>
                <td class="select_option">
                    <form:select path="state" id="stateID" onchange="loadCitiesByStateID(this.value, 1);">
                        <option><spring:message code="BzComposer.register.selectstate"/></option>
                        <option>state Id</option>
                    </form:select>
                    <form:hidden path="province" />
                </td>
                <td id="t_lbCountry">
                    <spring:message  code="BzComposer.register.counry"/>
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
                    <spring:message code="BzComposer.register.phone"/>
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
                <td id="t_txtMobile">
                    <spring:message code="BzComposer.global.mobileNumber"/>
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
                <td>
                    <spring:message code="BzComposer.register.fax"/>
                    <span class="inputHighlighted"></span>
                </td>
                <td class="inputfild">
                    <form:input type="text" path="fax" />
                </td>
                <td>
                    <spring:message code="BzComposer.global.expdate"/>
                    <span class="inputHighlighted"></span>
                </td>
                <td class="inputfild">
                    <form:input path="dateExpiry" readonly="true" required="true" />
                    <img src="${pageContext.request.contextPath}/images/cal.gif" onclick="displayCalendar(document.multiUserForm.dateExpiry,'mm-dd-yyyy',this);">
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td colspan="5" class="sub_heading">
                    <spring:message code="BzComposer.register.security"/>
                </td>
            </tr>
            <tr><td colspan="5"><hr></td></tr>
                <tr>
                    <td id="t_password">
                        <spring:message code="BzComposer.register.password"/>
                        <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                    </td>
                    <td class="inputfild">
                        <form:input type="password" path="password" required="true" />
                    </td>
                    <td id="t_password_copy" nowrap>
                        <spring:message code="BzComposer.register.confirmpassword"/>
                        <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>&nbsp;
                    </td>
                    <td class="inputfild">
                        <form:input type="password" path="confirmPassword" required="true" />
                    </td>
                    <td>
                        <form:hidden path="userID" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                    <td colspan="2">
                        <button type="submit" class="formButton"><spring:message code='BzComposer.global.submit'/></button> &nbsp;&nbsp;&nbsp;
                        <button type="button" class="formButton" onclick="closeMe()"><spring:message code='BzComposer.global.cancel'/></button>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr><td colspan="5">&nbsp;</td></tr>
				</table>
			</div>
		</div>
</form:form>
</section>
<!-- our services start -->
<div id="up-to-top">
    <i class="fa fa-angle-up"></i>
</div>
<%@ include file="/WEB-INF/jsp/templateScript.jsp"%>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>