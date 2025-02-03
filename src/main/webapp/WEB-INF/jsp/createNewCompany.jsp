<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="ISO-8859-1"> -->
<title><spring:message code="BzComposer.addnewcompanytitle" /></title>
<%@include file="include/header.jsp"%>
	<link href="${pageContext.request.contextPath}/dist/css/custom.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/styles/form.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/custom.js"></script>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
	
	<!-- For dialog -->
	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />


</head>
<script type="text/javascript">
function init()
		{
		debugger;
		var BusinessTypeId = 1;
		$('select[id="businessTypeId"]').find('option[value="'+BusinessTypeId+'"]').attr("selected",true);

		var date = new Date();
		var year = date.getFullYear();
  		document.getElementById("sEnddate1").defaultValue = "12-31-"+year;
		}
		
</script>
<body onload="init();">
<form action="CompanyNew" id="createNewCompany" method="post" commandName="companyInfoForm">
	<div class="bca_createnewcompanyimgleft">
		<img alt="" src="images/newCompany1.png" height="548px">
	</div>
	
	<!-- dialog box that used in this page -->
	<div id="quitMessage" style="display:none;font-size:1em;">
		<p><spring:message code="bca.quitmessage"/></p>
	</div>
	
	<div class="bca_createnewcompanyright" style="background-color: #fff;">
		<div class="bca_createnewcompanyright_title">
			<h3><spring:message code="bca.CreateNewCompany" /></h3>
		</div>
		<div class="bca_createnewcompanyright_body">
			<h4><spring:message code="bca.PleaseEnterFollowingInformation" /></h4>
			<div class="bca_formControl1">
				<p style="margin-bottom: 0px;"><spring:message code="bca.NewCompanyName" /></p>
				<input type="text" id="companyName" name="companyName"/>
				<%-- <input type="text" id="companyName"/> --%>
				<errors />
				<%-- <br/>
				<br/>
				<input type="checkbox" path="connenctasNetworkClient"/>
					<spring:message code="bca.ConnenctasNetworkClient" />
				<br/>
				<br/> --%>
				<%-- <p style="margin-top: 0px;">
					<spring:message code="bca.Thisoptionallowsyoulogcompanyprofileremoteserver" />
				</p> --%>
				<%-- <div class="bca_companyConnectMode">
					<h4><spring:message code="bca.ConnectionMode" /></h4>
				 	<ul>
			 		<li>
			 				<input type="radio" value="Single Mode" name="mode">
			 					<spring:message code="bca.SingleMode" />
		 					<br/> 
		 					<spring:message code="bca.ConnectasaSoleuser" />
	 					</li>
			 			<li>
			 				<input type="radio" value="Single Mode" name="mode">
			 					<spring:message code="bca.MultiUserMode" />
		 					<br/> 
		 					<spring:message code="bca.Connectasaoneofmanyuser" />
	 					</li>
				 		<li>
				 			<input type="radio" path="multiMode" value="0">
				 				<spring:message code="bca.SingleMode" />
				 				<br/> 
				 				<spring:message code="bca.ConnectasaSoleuser" />
			 				</radio>
		 				</li>
				 		<li>
				 			<input type="radio" path="multiMode" value="1">
				 				<spring:message code="bca.MultiUserMode" />
				 				<br/> 
				 				<spring:message code="bca.Connectasaoneofmanyuser" />
			 				</radio>
		 				</li>
				 	</ul>
				 </div> --%>
			</div>
			<div class="bca_formControl2">
				<p><spring:message code="bca.ThisOptionallowsyoutosetDefaultsettingofExistingCompany" /></p>
				<div class="bca_combos">
					<ul>
						<%-- <li>
							<spring:message code="bca.ExistingCompanies" />
						<select name="companyID">
							<option value="1">1</option>
								<c:if test="${not empty acList}">
									<c:forEach items="${acList}" var="objList" >
										<option value="${objList.companyID}">
											${objList.companyName}
										</option>
									</c:forEach>
								</c:if>
							</select>
							<select name="companyID">
								<option value="1">1</option>
								 <c:if test="${not empty CompanyInfoForm.listOfExistingCompanies}">
									<c:forEach items="${CompanyInfoForm.listOfExistingCompanies}" var="curObject" >
										<option value="${curObject.moduleName}">
												${curObject.moduleName},
												${curObject.moduleID}
											</option>
										<option value="${curObject.companyID}"
									     ${curObject.companyName}
										</option>
									</c:forEach>
								 </c:if>
							</select>
						</li> --%>
						<%-- <li>
							<spring:message code="bca.DefaultSettingPage" />
						<select name="startModuleID">
								<c:forEach items="${acList2}" var="curObject" >
									<option value="${curObject.startModuleID}">
										${curObject.moduleName}
									</option>
								</c:forEach>
							</select>
							<select name="startModuleID">
								<c:if test="${not empty CompanyInfoForm.listOfdefaultmodules}">
									<c:forEach items="${CompanyInfoForm.listOfdefaultmodules}" var="curObject">
										<option value="${curObject.moduleName}">
												${curObject.moduleName},
												${curObject.moduleID}
									    </option>
										<option value=${curObject.moduleID}>
											    ${curObject.moduleName}
										</option>
									</c:forEach>
							 	</c:if>
							</select>
						</li> --%>
						<li>
							<spring:message code="bca.BusinessType" />

							<select name="businessTypeId" id="businessTypeId">

									<c:forEach items="${CompanyInfoForm.listOfBusinessType}" var="curObject" >
									  <option value="${curObject.businessTypeId}">
									   ${curObject.businessName}
									  </option>
									</c:forEach>

							</select>
						</li>
					</ul>
				</div>
				<div class="bca_companyConnectMode">
					<h4><spring:message code="bca.EnterStrat/EndDate" /></h4>
				 	<p>
				 		<spring:message code="bca.Thestartdateisthedateyouchoosetobegintrackingyourbusiness" />
			 		</p>
				 	<ul>
				 		<%-- <li>
				 			<spring:message code="bca.StartDate" />
			 				<input type="text" id="sStartDate" name="sStartDate" size="15"/>
		 					<img src="${pageContext.request.contextPath}/images/cal.gif"
		 					onclick="displayCalendar(document.CompanyInfoForm.sStartDate,'mm-dd-yyyy',this);">
		 				</li> --%>
			 			<li>
			 				<spring:message code="bca.TaxYearEndingDate" />
		 					<input type="text" id="sEndDate" name="sEndDate" size="15" id="sEnddate1"/>
		 					<img src="${pageContext.request.contextPath}/images/cal.gif"
		 					onclick="displayCalendar(document.CompanyInfoForm.sEndDate,'mm-dd-yyyy',this);">
	 					</li>
				 	</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="bca_createnewcompanyright_bottom">
		<ul>
			<!-- <li><button class="formbutton">Back</button></li> -->
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
				<a class="formbutton" onclick="quit();">
					<spring:message code="BzComposer.global.close"/>
				</a>
			</li>
		</ul>
	</div>
</form>
<script type="text/javascript">
function submitform()
{
debugger;
	document.forms['createNewCompany'].action = "CompanyNew?tabid=createNewCompany3";
	document.forms['createNewCompany'].submit();
}
function finish() {
	document.forms['createNewCompany'].action = "CompanyNew?tabid=finish3";
	document.forms['createNewCompany'].submit();
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
                history.back(-1);
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