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
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<title><spring:message code="BzComposer.homepagetitle"/></title>

<!-- for opening dialog these files added on 26-09-2019 -->
<%-- <script src="<bean:write name="path" property="pathvalue"/>/includeAll/jquery-1.11.1.min.js"></script> --%>
<script src="../includeAll/jquery-1.11.1.min.js"></script> 
<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script> 
<link rel="stylesheet" href="../includeAll/jquery-ui.css" />
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<%@ include file="templateHeader.jsp"%>

<style>
 table td{
vertical-align:top;
border:solid 1px #888;
padding:10px;
align: center;

border: unset;
  border-top-style: unset;
  border-right-style: unset;
  border-bottom-style: unset;
  border-left-style: unset;
border-style: double; 

}  
td h1{
text-align :center;
}
</style>
</head>
<body modelAttribute="errorBean">
<div id="pageContent" style="width:100%;height: 100%;">
	<!-- page load-->
	<div class="page-loader">
		<div class="loader"></div>
	</div>
	<header> 
	<!-- header / start-->
      <!-- form itself -->
      <!-- mfp-hide -->
      <div id="test-form" class="mfp-hide white-popup-block">
      
      <!-- SETUP -->
        <%-- <div id="setup">
            <form:form action="Login.do?tabid=welcomescreen" method="post">
					<input type="hidden" name="actionType" value="">
	          <div >
			<div class="form-signin">
			<h2 class="form-signin-heading">Please sign in</h2>
			<div class="form-group">
			
			<label for="userName" class="">Email address</label>				
			 <form:input name="userName" id="userName" styleClass="form-control\" autoComplete=\"off" value="<%= userName %>"/>
			 
			 </div>
			 
			   <div class="form-group">
			 
			 <label for="password" class="">Password:</label>		
			<form:password name="password" id="password" styleClass="form-control" value="<%= pass %>" />
				</div>
				
				<div class="form-group">
					<input type="checkbox" name="rememberMe" value="<%= remember %>" />Remember Me
					<input type="checkbox" id="remember" name="remember"/> Remember Me
				</div> 

			   <div class="form-group">
				<!-- <a href="http://216.116.104.10/bzcomposerweb2/recover.jsp"><span style="cursor: pointer; font-size:13px;">Forgot Password ?</span></a> -->
				<a href="http://localhost:8080/bzcomposerweb2/recover.jsp"><span style="cursor: pointer; font-size:13px;">Forgot Password ?</span></a>
				</div>
				
			   <div class="form-group bca_loginbtn" align="right">
				<input type="submit"  class="btn btn-primary imgClass" value="LOGIN" onclick="formValue1()"/>			
				<!-- <a href="http://localhost:8080/bzcomposerweb2/register.jsp"><span style="cursor: pointer; font-size:13px; text-transform: uppercase;margin-left: 10px;">Sign Up</span></a> -->
				<!-- <button type="button" class="btn btn-primary" onclick="openRegisterPage()" value="Sign Up">Sign Up</button> -->
				</div>				
				
			</div>
			</div>
		</form:form>
        </div>      --%>
      </div>
      
      <!-- forgot password starts -->
      <%-- <div id="forgotPassword-form" class="mfp-hide white-popup-block">
      	<div id="setup">
             <form:form action="Login.do?tabid=forgotPassword" method="post" id="forgotForm">
            	<input type="hidden" name="actionType" value="">
	          	<div>
					<div class="form-signin">
						<h2 class="form-signin-heading">Forgot your password?</h2>
						<div class="form-group">
							<b>To reset your password type your loginId.</b>
						</div>
						<div class="form-group">
						<!-- <form id="forgotPasswordForm" action="Login.do?tabid=forgotPassword" method="post"> -->
						<form:form action="Login.do?tabid=forgotPassword" method="post" id="forgotPasswordForm">
							<input type="hidden" id="action" value=""/>
							<label id="message"></label>
			  			<div class="form-group">
			  				
							<label for="userName" class="">Your Email address</label>				
			 				<form:input name="userName" id="userName" styleClass="form-control\" autoComplete=\"off" />
			 				<input type="text" id="loginID" name="loginID" class="form-control" style="autocomplete:off"/>
			 			</div>
			 			<div class="form-group" align="right">
			 				<!-- <button type="button" class="btn btn-primary" value="Check" onclick="checkUser()">Check</button> -->
			 				<button type="submit" class="btn btn-primary" value="Check">Check</button>
			 			</div>
			 			</form:form>
			 			<!-- </form> -->
			 			</div>
			 			<div class="form-group">
			 				<label for="password" class="">Password Hint:</label>		
							<form:password name="password" id="password" styleClass="form-control" />
							<select id="passwordHint" class="form-control">
								<option value="0">Select a question</option>
								<option value="1">What is your nick name</option>
								<option value="2">What is your pet's name</option>
								<option value="3">What is your hobby</option>
								<option value="4">What is golden moment in you life</option>
							</select>
						</div>
						<div class="form-group">
							<label for="password" class="">Password Answer:</label>
							<input type="text" id="passwordAnswer" class="form-control" style="autocomplete:off"/>
						</div> 
						<div class="form-group bca_loginbtn">
							<input type="submit"  class="btn btn-primary" value="Next" onclick="showValues()"/>			
							<input type="button" class="btn btn-primary popup-with-form" href="#test-form" value="Cancel"/>
						</div>				
					</div>
				</div>
			 </form:form>
        </div>     
	</div> --%>
    <!-- forgot password ends -->  
    
	<div class="hidden-tablet-landscape-up">
		<div class="header header-mobile-1">
			<div class="container">
				<%-- <%@ include file="headerTabletLandscapeUpDiv.jsp" %> --%>
				<div class="row">
					<div class="col-md-3" align="center">
						<div class="logo" style="max-width: 100%;">
							<a href="index.jsp"> 
								<img src="${pageContext.request.contextPath}/dist/template/images/icons/BzComposerLogo.png" alt="Consulting" style="max-width: 100%"/>
							</a>
						</div>
					</div>
				<!-- <div class="contact-widget contact-widget-1"> -->
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
							<option value=""><spring:message code="BzComposer.selectlanguage"/></option>
							<option value="en"><spring:message code="BzComposer.selectlanguage.english"/></option>
							<option value="zh"><spring:message code="BzComposer.selectlanguage.chinese"/></option>
							<option value="es"><spring:message code="BzComposer.selectlanguage.spanish"/></option>
						</select>
					</div>
				</div>
				<div class="col-md-3" align="center">
					<div align="center">
						<div class="contact-widget contact-widget-1">
							<button type="button" class="btn btn-primary" onclick="redirectToLogin()"><spring:message code="BzComposer.login"/></button>
							<button type="button" class="btn btn-primary" onclick="openRegisterPage()"><spring:message code="BzComposer.register"/></button>
							<button class="hamburger hamburger--spin hidden-tablet-landscape-up" id="toggle-icon">
								<span class="hamburger-box"> <span class="hamburger-inner"></span></span>
							</button>	
						</div>
					</div>
				</div>
			</div>
			</div>
		
			<div class="au-navbar-mobile navbar-mobile-1">
				<ul class="au-navbar-menu">
					<li><a href="#"><spring:message code="BzComposer.home"/></a></li>
					<li><a href="${pageContext.request.contextPath}/BzComposer"><spring:message code="BzComposer.whatisbzcomposer"/></a></li>
					<li><a href="${pageContext.request.contextPath}/aboutUS"><spring:message code="BzComposer.aboutus"/></a></li>
					<li><a href="${pageContext.request.contextPath}/existingCompetitors"><spring:message code="BzComposer.existingcompetitors"/></a></li>
					<li class="drop">
						<a href="#"><spring:message code="BzComposer.samplecompaies"/></a>
							<span class="arrow"><i></i></span>
							<ul class="drop-menu bottom-right">
								<li><a href="Login.do?tabid=selectedCompany&selectedCompanyId=4&companyName=ABC eSales Company"><spring:message code="BzComposer.samplecompaies.eSalecompany"/></a></li>
								<li><a href="Login.do?tabid=selectedCompany&selectedCompanyId=2&companyName=ABC Retails Company"><spring:message code="BzComposer.samplecompaies.retailcompany"/></a></li>
								<li><a href="Login.do?tabid=selectedCompany&selectedCompanyId=3&companyName=ABC Wholesale Company"><spring:message code="BzComposer.samplecompaies.wholesalecompany"/></a></li>
							</ul>
							<%-- <ul class="drop-menu bottom-right" id="sampleCompanyList">
							    <c:forEach items="${acList}" var="objList1" varStatus="ndx">
									<li id="${objList1.companyid}"><a href="#">${objList1.companyName}</a></li>
								</c:forEach>
							</ul> --%>
						</li>
					<%-- <li><a href="${pageContext.request.contextPath}/applicableIndustries.jsp">Applicable Industries</a></li>
					<li class="drop">
						<a href="${pageContext.request.contextPath}/possibleMarketingWays.jsp">Possible Marketing Ways</a>
						<span class="arrow"><i></i></span>
						<ul class="drop-menu bottom-right">
							<li><a href="${pageContext.request.contextPath}/possibleMarketingWays.jsp#valueAddedReseller">Value Added Reseller</a></li>
							<li><a href="${pageContext.request.contextPath}/possibleMarketingWays.jsp#erp">Electronic Resource Planning(ERP)</a></li>
							<li><a href="${pageContext.request.contextPath}/possibleMarketingWays.jsp#openSourceProject">Open Source Project</a></li>
							<li><a href="${pageContext.request.contextPath}/possibleMarketingWays.jsp#sharewareDownloads">Shareware Downloads</a></li>
						</ul>
					</li>
					<li><a href="${pageContext.request.contextPath}/futureUpdate.jsp">Future Update</a></li> --%>
					<li><a href="${pageContext.request.contextPath}/ourServices"><spring:message code="BzComposer.ourservices"/></a></li>
					<li><a href="${pageContext.request.contextPath}/industries"><spring:message code="BzComposer.industires"/></a></li>
					<li class="drop"><a href="${pageContext.request.contextPath}/features><spring:message code="BzComposer.features"/></a>
						 <span class="arrow"><i></i></span>
						<ul class="drop-menu bottom-right">
							<li><a href="${pageContext.request.contextPath}/features#easySetup"><spring:message code="BzComposer.features.easysetup"/></a></li>
							<li><a href="${pageContext.request.contextPath}/features#enhancedFeatures"><spring:message code="BzComposer.features.enshancedfeature"/></a></li>
							<li><a href="${pageContext.request.contextPath}/features#customerContactManagement"><spring:message code="BzComposer.features.customercontactmanagement"/></a></li>
							<li><a href="${pageContext.request.contextPath}/features#completeOrderManagement"><spring:message code="BzComposer.features.completeordermanagement"/></a></li>
							<li><a href="${pageContext.request.contextPath}/features#inventoryWarehouseManagement"><spring:message code="BzComposer.features.inventorywarehousemanagement"/></a></li>
							<li><a href="${pageContext.request.contextPath}/features#shippingPaymentIntegration"><spring:message code="BzComposer.features.shippingpaymentintegration"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/features#fullFeaturedAccountingSystem"><spring:message code="BzComposer.features.accountsystem"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/features#payRollTax"><spring:message code="BzComposer.features.payrolltax"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/features#completeRealTimeReports"><spring:message code="BzComposer.features.completerealtimreports"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/features#eCommerceIntegration"><spring:message code="BzComposer.features.ecommerceintegration"/></a></li>
						</ul>
					</li>
					<li class="drop">
						<a href="${pageContext.request.contextPath}/products"><spring:message code="BzComposer.products"/></a>
						 <span class="arrow"><i></i></span>
						<ul class="drop-menu bottom-right">
							<li><a href="${pageContext.request.contextPath}/products#bzcomposerStandard"><spring:message code="BzComposer.products.bzcomposerstandard"/></a></li>
							<li><a href="${pageContext.request.contextPath}/products#bzcomposerBilling"><spring:message code="BzComposer.products.billing"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/products#bzcomposereSales"><spring:message code="BzComposer.products.esales"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/products#bzcomposerProfessional"><spring:message code="BzComposer.products.professional"/></a></li>
							<li><a href="${pageContext.request.contextPath}/products#bzcompserStandardShareware"><spring:message code="BzComposer.products.standardshareware"/></a></li>
						</ul>
					</li>
					<li class="drop">
                        <a href="">Demo</a><span class="arrow"><i></i></span>
                        <ul class="drop-menu bottom-right">
                            <li><a href="#"></a></li>
                            <c:forEach items="${acList}" var="objList1">
                                <li><a href="#">${objList1.companyName}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
					<%-- <li><a href="${pageContext.request.contextPath}/partners.jsp">Partners</a></li> --%>
					<li><a href="${pageContext.request.contextPath}/contactUs"><spring:message code="BzComposer.contact"/>ASAD</a></li>
				</ul>
			</div>
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
							<!-- <option value=""><spring:message code="BzComposer.selectlanguage"/></option> -->
							<option value="en" ${currentLocale=='en'?'selected':''}><spring:message code="BzComposer.selectlanguage.english"/></option>
							<option value="zh" ${currentLocale=='zh'?'selected':''}><spring:message code="BzComposer.selectlanguage.chinese"/></option>
							<option value="es" ${currentLocale=='es'?'selected':''}><spring:message code="BzComposer.selectlanguage.spanish"/></option>
						</select>
						<%-- <s:url id="localeEN" namespace="/" action="locale" >
						   <s:param name="request_locale" >en</s:param>
						</s:url>
						<s:url id="localeHN" namespace="/" action="locale" >
						   <s:param name="request_locale" >hn</s:param>
						</s:url>
						<s:url id="localeES" namespace="/" action="locale" >
						   <s:param name="request_locale" >es</s:param>
						</s:url>
						<s:url id="localezhCN" namespace="/" action="locale" >
						   <s:param name="request_locale" >zh_CN</s:param>
						</s:url>
						<s:url id="localeDE" namespace="/" action="locale" >
						   <s:param name="request_locale" >de</s:param>
						</s:url>
						<s:url id="localeFR" namespace="/" action="locale" >
						   <s:param name="request_locale" >fr</s:param>
						</s:url> --%>
						 
						<!-- <a href="/changeLocale?lang=en" >English</a>
						<a href="/changeLocale?lang=hn" >Hindi</a>
						<a href="/changeLocale?lang=es" >Spanish</a>
						<a href="/changeLocale?lang=zh_CN" >Chinese</a>
						<a href="/changeLocale?lang=de" >German</a>
						<a href="/changeLocale?lang=fr" >France</a> -->
					</div>
				</div>
				<!-- dialog box that used in this page -->
				<div id="showLanguageDialog" style="display:none;">
					<p>Select any language to change.</p>
				</div>
				<div class="col-md-3" align="right">
					<div align="center">
						<div class="contact-widget contact-widget-1">
							<!-- <button type="button" class="btn btn-primary popup-with-form" href="#test-form" onclick="redirectToLogin()">Login</button> -->
							<button type="button" class="btn btn-primary" onclick="redirectToLogin()"><spring:message code="BzComposer.login"/></button>
							<button type="button" class="btn btn-primary" onclick="openRegisterPage()"><spring:message code="BzComposer.register"/></button>
							<button class="hamburger hamburger--spin hidden-tablet-landscape-up" id="toggle-icon">
								<span class="hamburger-box"> <span class="hamburger-inner"></span>
								</span>
							</button>
						</div>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
	<div class="section section-navbar-1 bg-grey hidden-tablet-landscape" id="js-navbar-fixed">
		<div class="text-center">
			<div class="text-center">
				<div class="logo-mobile">
					<a href="index.html"> 
						<img src="${pageContext.request.contextPath}/dist/template/images/icons/BzComposerLogo.png" alt="Consulting"/>
					</a>
				</div>
			</div>
				<nav class="text-center">
				<div class="au-navbar navbar-1">
					<ul class="au-navbar-menu">
						<li><a href="#"><spring:message code="BzComposer.home"/></a></li>
						<li><a href="${pageContext.request.contextPath}/BzComposer"><spring:message code="BzComposer.whatisbzcomposer"/></a></li>
						<li><a href="${pageContext.request.contextPath}/aboutUS"><spring:message code="BzComposer.aboutus"/></a></li>
						<li><a href="${pageContext.request.contextPath}/existingCompetitors"><spring:message code="BzComposer.existingcompetitors"/></a></li>
						<%-- <li><a href="${pageContext.request.contextPath}/applicableindustries">Applicable Industries</a></li> --%>
						<li class="drop"><a href="#"><spring:message code="BzComposer.samplecompaies"/></a>
							<span class="arrow"><i></i></span>
							<ul class="drop-menu bottom-right">
								<li><a href="#" onclick="openSampleCompany4();"><spring:message code="BzComposer.samplecompaies.eSalecompany"/></a></li>
								<li><a href="#" onclick="openSampleCompany2();"><spring:message code="BzComposer.samplecompaies.retailcompany"/></a></li>
								<li><a href="#" onclick="openSampleCompany3();"><spring:message code="BzComposer.samplecompaies.wholesalecompany"/></a></li>
							</ul>
							<%-- <ul class="drop-menu bottom-right" id="sampleCompanyList">
								<c:forEach items="${acList}" var="objList1" varStatus="ndx">
									<li id="<bean:write name="objList1" property="companyid" />"><a href="#"><bean:write name="objList1" property="companyName" /></a></li>
								</c:forEach>
							</ul> --%>
						</li>
						<%-- <li><a href="${pageContext.request.contextPath}/applicableindustries">Applicable Industries</a></li>
						<li class="drop"><a href="${pageContext.request.contextPath}/possibleMarketingWays.jsp">Possible Marketing Ways</a>
							<span class="arrow"><i></i></span>
							<ul class="drop-menu bottom-right">
								<li><a href="${pageContext.request.contextPath}/possibleMarketingWays.jsp#valueAddedReseller">Value Added Reseller</a></li>
								<li><a href="${pageContext.request.contextPath}/possibleMarketingWays.jsp#erp">Electronic Resource Planning(ERP)</a></li>
								<li><a href="${pageContext.request.contextPath}/possibleMarketingWays.jsp#openSourceProject">Open Source Project</a></li>
								<li><a href="${pageContext.request.contextPath}/possibleMarketingWays.jsp#sharewareDownloads">Shareware Downloads</a></li>
							</ul>
						</li>
						<li><a href="${pageContext.request.contextPath}/futureUpdate.jsp">Future Update</a></li> --%>
						<li><a href="${pageContext.request.contextPath}/ourServices"><spring:message code="BzComposer.ourservices"/></a></li>
						<li><a href="${pageContext.request.contextPath}/industries"><spring:message code="BzComposer.industires"/></a></li>
						<li class="drop"><a href="${pageContext.request.contextPath}/features"><spring:message code="BzComposer.features"/></a>
							<span class="arrow"><i></i></span>
							<ul class="drop-menu bottom-right">
								<li><a href="${pageContext.request.contextPath}/features#easySetup"><spring:message code="BzComposer.features.easysetup"/></a></li>
								<li><a href="${pageContext.request.contextPath}/features#enhancedFeatures"><spring:message code="BzComposer.features.enshancedfeature"/></a></li>
								<li><a href="${pageContext.request.contextPath}/features#customerContactManagement"><spring:message code="BzComposer.features.customercontactmanagement"/></a></li>
								<li><a href="${pageContext.request.contextPath}/features#completeOrderManagement"><spring:message code="BzComposer.features.completeordermanagement"/></a></li>
								<li><a href="${pageContext.request.contextPath}/features#inventoryWarehouseManagement"><spring:message code="BzComposer.features.inventorywarehousemanagement"/></a></li>
								<li><a href="${pageContext.request.contextPath}/features#shippingPaymentIntegration"><spring:message code="BzComposer.features.shippingpaymentintegration"/></a></li>
                            	<li><a href="${pageContext.request.contextPath}/features#fullFeaturedAccountingSystem"><spring:message code="BzComposer.features.accountsystem"/></a></li>
                            	<li><a href="${pageContext.request.contextPath}/features#payRollTax"><spring:message code="BzComposer.features.payrolltax"/></a></li>
                            	<li><a href="${pageContext.request.contextPath}/features#completeRealTimeReports"><spring:message code="BzComposer.features.completerealtimreports"/></a></li>
                            	<li><a href="${pageContext.request.contextPath}/features#eCommerceIntegration"><spring:message code="BzComposer.features.ecommerceintegration"/></a></li>
							</ul>
						</li>
						<li class="drop"><a href="${pageContext.request.contextPath}/products"><spring:message code="BzComposer.products"/></a>
							<span class="arrow"><i></i></span>
							<ul class="drop-menu bottom-right">
								<li><a href="${pageContext.request.contextPath}/products#bzcomposerStandard"><spring:message code="BzComposer.products.bzcomposerstandard"/></a></li>
								<li><a href="${pageContext.request.contextPath}/products#bzcomposerBilling"><spring:message code="BzComposer.products.billing"/></a></li>
                            	<li><a href="${pageContext.request.contextPath}/products#bzcomposereSales"><spring:message code="BzComposer.products.esales"/></a></li>
                            	<li><a href="${pageContext.request.contextPath}/products#bzcomposerProfessional"><spring:message code="BzComposer.products.professional"/></a></li>
								<li><a href="${pageContext.request.contextPath}/products#bzcompserStandardShareware"><spring:message code="BzComposer.products.standardshareware"/></a></li>
							</ul>
						</li>
						<li class="drop">
                            <a href="">Demo</a><span class="arrow"><i></i></span>
                            <ul class="drop-menu bottom-right">
                                <li><a href="#"></a></li>
                                <c:forEach items="${acList}" var="objList1">
                                    <li><a href="#">${objList1.companyName}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
						<%-- <li><a href="${pageContext.request.contextPath}/partners.jsp">Partners</a></li> --%>
						<li><a href="${pageContext.request.contextPath}/contactUs"><spring:message code="BzComposer.contact"/></a></li>
					</ul>
				</div>
				</nav>
			</div>
		</div>
	<!-- header / end--> 
	</header>
	<section>
   <div>
    <table align="center">
    	<tr><td colspan="2"><h1>Some Problem In the Application</h1></td></tr>
        <tr>
            <td>Date</td>
            <td>${errorBean.date}</td>
        </tr>
        <tr>
            <td>Error</td>
            <td>${errorBean.errorMessage}</td>
        </tr>
        <tr>
            <td>Status</td>
            <td>${errorBean.statusCode}</td>
        </tr>
        <tr>
            <td>Go To Home Page</td>
            <td><a href="${errorBean.forwardPage}">Home<a/></td>
        </tr>
        
      <%--   <tr>
            <td>Message</td>
            <td>${message}</td>
        </tr>
        <tr>
            <td>Exception</td>
            <td>${exception}</td>
        </tr>
        <tr> 
            <td>Trace</td>
            <td>
                <pre>${trace}</pre>
            </td>
        </tr>--%>
    </table></div></section>
    <%@ include file="templateFooter.jsp"%>
	<div id="up-to-top">
		<i class="fa fa-angle-up"></i>
	</div>
<%@ include file="templateScript.jsp"%>
</body>
</html>