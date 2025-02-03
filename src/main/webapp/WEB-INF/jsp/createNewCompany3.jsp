<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="ISO-8859-1"> -->
	<%@include file="include/header.jsp"%>
	<link href="${pageContext.request.contextPath}/dist/css/custom.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/styles/form.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/custom.js"></script>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
	<!-- For dialog -->
	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
<title>
	<spring:message code="BzComposer.addnewcompanystep3title" />
</title>
</head>
<script type="text/javascript">
function init() {
	debugger;
	var  value =  document.getElementById("iCountry").value;
	if(value == 39){
		$("#stateRow").hide();
		$("#ProvinceRow").show();
	}else{
		$("#stateRow").show();
		$("#ProvinceRow").hide();
	}
}
function refreshItemsNow(val){
	debugger;
	var  value =  document.getElementById("iCountry").value;
		if(value == 39){
			$("#stateRow").hide();
			$("#ProvinceRow").show();
		}else{
			$("#stateRow").show();
			$("#ProvinceRow").hide();
		} 
	}
</script>
<body onload="init();">
<form action="CompanyNew" id="createNewCompany" method="post" commandName="companyInfoForm">
	<div class="bca_createnewcompanyimgleft">
		<img alt="" src="images/newCompany1.png" height="701px">
	</div>
	
	<!-- dialog box that used in this page -->
	<div id="quitMessage" style="display:none;font-size:1em;">
		<p><spring:message code="bca.quitmessage"/></p>
	</div>
	
	<div class="bca_createnewcompanyright" style="background-color: #fff;">
		<div class="bca_createnewcompanyright_title">
			<h3><spring:message code="BzComposer.addnewcompanystep3title" /></h3>
		</div>
		<div class="bca_createnewcompanyright_body" style="height: 640px;">
			<h4 style="width: 30%; margin-top: 20px;">
				<b><spring:message code="bca.Pleaseprovidethefollowinginformationforyourcompany" /></b>
			</h4>
			<h4 style="width: 50%; margin-top: 20px;">
				<b><spring:message code="bca.Fieldsmarkedwitharecompulsory" /></b>
			</h4>
			<div class="bca_companyInformationForm">
				<table>
					<tbody>
					<%--<div style="color:red;font-size: 18px;">
    						<errors/>
    					</div> --%>
						<tr>
							<td><spring:message code="bca.companyname" /></td>
							<%-- <td><input type="text" disabled="disabled" name="companyname" value='<bean:write  name="companyname" />'></td> --%>
							<td>
								<input type="text" name="companyName" value='${CompanyInfoForm.companyName}' disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td><spring:message code="bca.CompanyNickName" /></td>
							<!-- <td><input type="text" value="NextBit"></td> -->
							<td>
								<input type="text" name="sNickName" value="NextBit"/>
							</td>
						</tr>
						<tr>
							<td><spring:message code="bca.FirstName" /></td>
							<!-- <td><input type="text" value="jason"></td> -->
							<td>
								<input type="text" name="sfirstName" value="jason"/>
							</td>
						</tr>
						<tr>
							<td><spring:message code="bca.LastName" /></td>
							<!-- <td><input type="text" value="Lee"></td> -->
							<td>
								<input type="text" name="slastName" value="lee"/>
							</td>
						</tr>
						<tr>
							<td><spring:message code="bca.Address1" /></td>
							<!-- <td><input type="text" value="fullerton"></td> -->
							<td>
								<input type="text" name="sAddress1" errorKey="org.apache.struts.action.ERROR" errorStyleClass="error" value="Fullortone"/>
							</td>
							 <td><errors path="sAddress1" /></td>
						</tr>
						<tr>
							<td><spring:message code="bca.Address2" /></td>
							<!-- <td><input type="text"></td> -->
							<td>
								<input type="text" name="sAddress2"/>
							</td>
						</tr>
						<tr>
							<td><spring:message code="bca.City" /></td>
							<!-- <td><input type="text" value="Fullerton"></td> -->
							<td>
								<input type="text" name="sCity" errorKey="org.apache.struts.action.ERROR" errorStyleClass="error" value="Fullortone"/>
							</td>
							<td><errors path="sCity" /></td>
						</tr>
						<tr id="stateRow">
							<td><spring:message code="bca.State" /></td>
							<td>
							<%-- <select path="iState">
							<c:forEach items="${acListofStates}" var="curObject">
								<option value="${curObject.stateId}">
								${curObject.stateName}
								</option>
							</c:forEach>
							</select><spring:message code="bca.USOnly" /> --%>
							<select path="iState" id="iState">
							 <c:if test="${not empty CompanyInfoForm.listOfStates}">
								<c:forEach items="${CompanyInfoForm.listOfStates}" var="curObject" >
									<option value="${curObject.stateCode}">
									${curObject.stateName}
									</option>
								</c:forEach>
							 </c:if>
							</select>
							</td>
							<!-- <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
							
						</tr>
						<tr id="ProvinceRow">
							<td><spring:message code="bca.Province" /></td>
							<%-- <td><input type="text"><spring:message code="bca.OutsidetheUSOnly" /></td> --%>
							<td>
								<input type="text" name="sProvince" id="sProvince"/>
							</td>
						</tr>
						<tr>
						<td><spring:message code="bca.ZipCode" /></td>
							<td>
								<input type="text" name="sZip" errorKey="org.apache.struts.action.ERROR" errorStyleClass="error" value="360001"/>
							</td>
							<td><errors path="sZip" /></td>
						</tr>
						<tr>
							<td><spring:message code="bca.Country" /></td>
							<td>
							<%-- <select path="iCountry">
							<c:forEach items="${acListofCountries}" var="curObject" >
								<option value="${curObject.countryId}">
								${curObject.country}
								</option>
							</c:forEach>
							</select> --%>
							<select path="iCountry" name="iCountry" onchange="refreshItemsNow(this.value);" id="iCountry">
							 <c:if test="${not empty CompanyInfoForm.listOfCountries}">
								<c:forEach items="${CompanyInfoForm.listOfCountries}" var="curObject" >
									<option value="${curObject.countryId}">
									${curObject.country}
									</option>
								</c:forEach>
							</c:if>
							</select>
							</td>
						</tr>
						<%-- <tr>
							<td><spring:message code="bca.Province" /></td>
							<td><input type="text"><spring:message code="bca.OutsidetheUSOnly" /></td>
							<td>
								<input type="text" name="sProvince" id="sProvince"/>
							</td>
						</tr> --%>
						<tr>
							<td><spring:message code="bca.BusinessNumber" /></td>
							<td>
								<input type="text" name="businessNumber"/>
							</td>
						</tr>
						<tr>
							<td><spring:message code="bca.CellPhoneNumber" /></td>
							<td>
								<input type="text" name="sPhone2"/>
							</td>
						</tr>
						<tr>
							<td><spring:message code="bca.Fax" /></td>
							<td>
								<input type="text" name="sFax1"/>
							</td>
						</tr>
						<tr>
							<td><spring:message code="bca.Email" /></td>
							<!-- <td><input type="text" value="nextbits.jason@gmail.com" size="35"></td> -->
							<td>
								<input type="text" name="sEmail" value="nextbits.jason@gmail.com" size="35" errorKey="org.apache.struts.action.ERROR" errorStyleClass="error"/>
							</td>
							<td><errors path="sEmail" /></td>
						</tr>
						<tr>
							<td><spring:message code="bca.Homepage" /></td>
							<td>
								<input type="text" name="sHomePage"/>
							</td>
						</tr>
						<tr>
							<td><spring:message code="bca.FederalEmployerID" /></td>
							<!-- <td><input type="text"></td> -->
							<td>
								<input type="text" name="sFID"/>
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><spring:message code="bca.StateEmployerID" /></td>
							<!-- <td><input type="text"></td> -->
							<td>
								<input type="text" name="sSID"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="bca_createnewcompanyright_bottom">
		<ul>
			<li>
				<a onclick="history.back(-1)" class="formbutton">
					<spring:message code="BzComposer.global.goback"/>
				</a>
			</li>
			<li>
				<a class="formbutton" onclick="submitform();">
					<spring:message code="BzComposer.global.next"/>
				</a>
			</li>
			<li>
				<a class="formbutton" onclick="finish()">
					<spring:message code="BzComposer.global.finish"/>
				</a>
			</li>
			<li>
				<a class="formbutton" onclick="quit()">
					<spring:message code="BzComposer.global.close"/>
				</a>
			</li>
		</ul>
	</div>
</form>
<script type="text/javascript">
function submitform()
{
	document.forms['createNewCompany'].action = "CompanyNew?tabid=createNewCompany4";
	document.forms['createNewCompany'].submit();
}
function finish()
{
	 document.forms[0].action = "CompanyNew?tabid=finish3";
	 document.forms[0].submit();
}
function quit(){
	$("#quitMessage").dialog({
    	resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
        	"<spring:message code='bca.quitmessage.yes'/>": function () {
                $("#quitMessage").dialog("close");
                window.history.go(-3);
            },
            "<spring:message code='bca.quitmessage.no'/>": function () {
                $("#quitMessage").dialog("close");
                return false;
            }
        }
    });
}
</script>	
</body>
</html>