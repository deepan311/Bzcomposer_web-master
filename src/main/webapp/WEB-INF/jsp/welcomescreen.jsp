<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>
		<spring:message code="BzComposer.welcomescreentilte"/>
	</title>
	<%@include file="/WEB-INF/jsp/include/headlogo2.jsp"%>


	<!-- for opening dialog these files added on 26-09-2019 -->
	<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />

	<style type="text/css">
		.bca_create
		{
			text-align: right;
			margin-top: 12%;
			margin-left: 30px;
			margin-right: 30px;
		}
		/*formbutton class is added on 17-06-2019*/
		.formbutton
		{
			background-position: 0px 0px;
			background-repeat: no-repeat;
			/* width: 70px; */
			/* height: 30px; */
			border: 0px;
			overflow: hidden;
			/* text-indent: -9999px; */
			background-color: #05A9C5 ;
			color: white !important;
			cursor: pointer;
			padding: 5px;

			border-radius: 2px;
		}
	</style>
	<script type="text/javascript">
		function assignRole()
		{

			<%-- var role = '<%= request.getAttribute("Role")%>';

            if(role == "Admin" || role =="Super Admin")
            {
                $("#selectedRetailCompanyId").show();
            }
            else
            {
                $("#selectedRetailCompanyId").hide();
            } --%>
		}
	</script>
	<!-- <meta charset="ISO-8859-1"> -->


	<link href="<c:url value="/dist/css/custom.css" />" rel="stylesheet">
	<script src="<c:url value="/dist/js/custom.js" />"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body onload="assignRole();">
<form action="Login" method="post">
	<div class="container">
		<div class="bca_welcomescreen">
			<!-- <div style="float: left; width: 100%;" class="loginheader"><img alt="companyLogo" src="images/bzcomposerweb2.png"/></div> -->
			<div class="bca_login_body" style="background-image: url('images/welcome_screen.gif'); background-repeat: no-repeat;background-size: cover;">
				<!-- <ul class="bca_create"> -->
				<%-- <logic:iterate name="acList" id="objList1" indexId="ndx"><li><a href="#"><bean:write name="objList1" property="companyName" /></a></li></logic:iterate> --%>
				<!-- Commented on 17-06-2019 -->
				<%-- <select size="4" id="selectedRetailCompanyId" onclick="readOnlyNonRetailCompanyList()"><c:if test="${not empty acList}"><c:forEach items="${acList}" var="objList1"><option value="${curObject.companyid}"><a href="#">${curObject.companyName}></a></option></c:forEach></c:if></select> --%>
				<!-- </ul> -->
				<ul>
					<select size="4" id="selectedRetailCompanyId" onclick="readOnlyNonRetailCompanyList()" ondblclick="dblclickcompany()">
						<c:if test="${not empty acList}">
							<c:forEach items="${acList}" var="objList1">
								<option value='${objList1.companyid}'>
									<a href="#">${objList1.companyName}</a>
								</option>
							</c:forEach>
						</c:if>
					</select>
				</ul>
				<!-- This div is for open,delete and create company -->
				<div class="bca_create_view_company_right">
					<ul>
						<li onclick="openCompany()" style="cursor: pointer;">
							<img alt="" src="images/start_company.gif"><p><spring:message code="bca.OpenSelectedCompany" /></p>
						</li>
						<li onclick="createNewCompany()">
							<%-- <a href="${pageContext.request.contextPath}/CompanyNew.do?tabid=createNewCompany"> --%>
							<img alt="" src="images/open_company.gif"><p><spring:message code="bca.CreateNewCompany"/></p>
							<!-- </a> -->
						</li>
						<li onclick="deleteCompany()">
							<!-- <a href=""> -->
							<img alt="" src="images/delete_company.gif"><p><spring:message code="bca.DeleteCompany"/></p>
							<!-- </a> -->
						</li>
						<!-- <li><input type="submit">Crete</li> -->
					</ul>
				</div>
				<!-- Div ends -->

				<!-- This div is used to display list of user-dedfined companies -->
				<!-- <div class="bca_create_view_company"> -->
				<div class="bca_create">
					<h3>
						<font color="#0099ff"><spring:message code="bca.Create/ViewCompany" /></font>
					</h3>
					<%-- <ul>
                            <c:forEach items="${acList2}" var="objList1" >
                                <li><a href="#">${curObject.companyName}</a></li>
                            </c:forEach>
                        </ul> --%>
					<select size="10" style="width: 10%;text-align: center;" id="selectedNonRetailCompanyId" onclick="readOnlyRetailCompanyList()" ondblclick="dblclickcompany()">
						<c:if test="${not empty acList2}">
							<c:forEach items="${acList2}" var="objList1">
								<option value='${objList1.companyid}'>
										${objList1.companyName}
								</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
			</div>
			<!-- Div Ends -->
		</div>
	</div>
</form>
<script type="text/javascript">
	function dblclickcompany() {
		openCompany();
	}
	function readOnlyNonRetailCompanyList()
	{
		debugger;
		var companyId= $("#selectedRetailCompanyId").val();
		if(companyId!="")
		{
			//$("#selectedNonRetailCompanyId").attr("disabled", true);
		}
	}
	function readOnlyRetailCompanyList()
	{
		debugger;
		var companyId= $("#selectedNonRetailCompanyId").val();
		if(companyId!="")
		{
			//$("#selectedRetailCompanyId").attr("disabled", true);
		}
	}
	/*login create new company*/
	function createNewCompany()
	{
		document.forms[0].action = "CompanyNew?tabid=createNewCompany";
		document.forms[0].submit();
	}
	function openCompany()
	{
		var selectedRetailCompanyId = $("#selectedRetailCompanyId").val();
		var selectedNonRetailCompanyId = $("#selectedNonRetailCompanyId").val();
		if(selectedRetailCompanyId == null)
		{
			companyId = selectedNonRetailCompanyId;
			companyName =$("#selectedNonRetailCompanyId").find(":selected").text().replace(/\s/g,'');
		}
		else
		{
			companyId = selectedRetailCompanyId;
			companyName = $("#selectedRetailCompanyId").find(":selected").text().replace(/\s/g,'');
		}
		if((companyId && companyName) != null)
		{
			document.forms[0].action = "LoginPost?tabid=selectedCompany&selectedCompanyId="+companyId+"&companyName="+companyName;
			document.forms[0].submit();
		}
		else
		{
			alert("<spring:message code='BzComposer.welcomescreen.selectcompany'/>");
		}
		selectedRetailCompanyId = null;
		selectedNonRetailCompanyId = null;
		companyId = null;
		companyName = null;
	}
	function deleteCompany()
	{
		//var selectedRetailCompanyId = $("#selectedRetailCompanyId").val();
		var selectedNonRetailCompanyId = $("#selectedNonRetailCompanyId").val();
		/*if(selectedNonRetailCompanyId == null)
         {
            companyId = selectedNonRetailCompanyId;
            //companyName =$("#selectedNonRetailCompanyId").find(":selected").text().replace(/\s/g,'');
        }
        else
        {
            companyId = selectedRetailCompanyId;
            //companyName = $("#selectedRetailCompanyId").find(":selected").text().replace(/\s/g,'');
        } */
		companyId = selectedNonRetailCompanyId;

		if(companyId  != null)
		{
			window.location.href= "Login?tabid=DeleteCompany&&CompanyID="+companyId;
			//document.forms[0].submit();
		}
		else
		{
			alert("<spring:message code='BzComposer.welcomescreen.selectcompany'/>");
		}
	}
</script>
</body>
</html>