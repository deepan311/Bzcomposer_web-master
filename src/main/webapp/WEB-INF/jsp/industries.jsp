<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%-- <html:html lang="en"> --%>
<html>
<title><spring:message code="BzComposer.industriestitle"/></title>
<style>
.highlight
{
	background-color: yellow;
}
</style>
<%@ include file="templateHeader.jsp"%>
<script type="text/javascript">
function redirectToLogin()
{
	window.location = "Login.do?tabid=loginPage";	
}
function openRegisterPage()
{
	window.location = "Login.do?tabid=register";
}
function searchText()
{    
	var highlightRe = /<span class="highlight">(.*?)<\/span>/g,
	highlightHtml = '<span class="highlight">$1</span>';
	var term = document.getElementById("searchBox").value;
	var txt1 = $('#divIndustires').html();
	if(term.length>1)
	{
		if(term == 'Style' || term == 'style' || term == 'Class' || term == 'class ' || term == 'para-temp')
		{
			document.getElementById("divIndustires").innerHTML = txt1;
		}
		else
		{
			var txt = $('#divIndustires').html().replace(highlightRe,'$1');
			
			if(term !== '') {
			        txt = txt.replace(new RegExp('(' + term + ')', 'gi'), highlightHtml);
			}    
			$("#divIndustires").html(txt);
			setTimeout(function(){ 
				document.getElementById("divIndustires").innerHTML = txt1;
			}, 5000);
		}	
	} 
	else
	{
		alert("<bean:message key='BzComposer.common.emterword'/>");
	}
 }
function searchTextMobile()
{
	var highlightRe = /<span class="highlight">(.*?)<\/span>/g,
	highlightHtml = '<span class="highlight">$1</span>';
	var term = document.getElementById("searchBoxMobile").value;
	var txt1 = $('#divIndustires').html();
	if(term.length>1)
	{
		if(term == 'Style' || term == 'style' || term == 'Class' || term == 'class ' || term == 'para-temp')
		{
			document.getElementById("divIndustires").innerHTML = txt1;
		}
		else
		{
			var txt = $('#divIndustires').html().replace(highlightRe,'$1');
			
			if(term !== '') {
			        txt = txt.replace(new RegExp('(' + term + ')', 'gi'), highlightHtml);
			}    
			$("#divIndustires").html(txt);
			setTimeout(function(){ 
				document.getElementById("divIndustires").innerHTML = txt1;
			}, 5000);
		}
	}
	else
	{
		alert("<bean:message key='BzComposer.common.emterword'/>");
	}
}
function openSampleCompany2()
{
	window.location.href="Login.do?tabid=selectedCompanyHome&selectedCompanyId=2&companyName=ABC Retails Company";	
}

function openSampleCompany3()
{
	window.location.href="Login.do?tabid=selectedCompanyHome&selectedCompanyId=3&companyName=ABC Wholesale Company";
}

function openSampleCompany4()
{
	window.location.href="Login.do?tabid=selectedCompanyHome&selectedCompanyId=4&companyName=ABC eSales Company";
}
</script>
<body>
<header>
	<!-- div for mobile screen starts -->
	<div class="hidden-tablet-landscape-up">
		<div class="header header-mobile-1">
			<div class="container">
				<div class="row">
					<div class="col-md-3" align="center">
						<div class="logo" style="max-width: 100%;">
							<a href="${pageContext.request.contextPath}/index.jsp"> 
								<img src="${pageContext.request.contextPath}/dist/template/images/icons/BzComposerLogo.png" alt="Consulting" style="max-width: 100%"/>
							</a>
						</div>
					</div>
					<div class="col-md-3">
						<div class="search-wrap" align="right">
							<div class="input-group" align="center">
								<input type="text" id="searchBoxMobile" name="searchBoxMobile" placeholder="<spring:message code="BzComposer.searchtext"/>" style="max-width: 100%;" />
								<div class="input-group-btn">
									<button class="btn btn-primary" onclick="searchTextMobile()"><spring:message code="BzComposer.searchbuttontext"/></button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-3" align="center">
						<div class="input-group">	
							<select name="localeMobile" id="localeMobile" class="form-control" onchange="showLocaleMobile();">
								<option value=""><spring:message code="BzComposer.selectlanguage"/></option>
								<option value="english"><spring:message code="BzComposer.selectlanguage.english"/></option>
								<option value="chinese"><spring:message code="BzComposer.selectlanguage.chinese"/></option>
								<option value="spanish"><spring:message code="BzComposer.selectlanguage.spanish"/></option>
							</select>
						</div>
					</div>
					<div class="col-md-3" align="center">
						<div align="center">
							<div class="contact-widget contact-widget-1">
								<button type="button" class="btn btn-primary" onclick="redirectToLogin()"><spring:message code="BzComposer.login"/></button>
								<button type="button" class="btn btn-primary" onclick="openRegisterPage()"><spring:message code="BzComposer.register"/></button>
								<button class="hamburger hamburger--spin hidden-tablet-landscape-up" id="toggle-icon">
									<span class="hamburger-box"> 
										<span class="hamburger-inner"></span>
									</span>
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
                            <logic:iterate name="acList" id="objList1" indexId="ndx">
                                <li id="<bean:write name="objList1" property="companyid" />"><a href="#"><bean:write name="objList1" property="companyName" /></a></li>
                            </logic:iterate>
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
					<%-- <li><a href="${pageContext.request.contextPath}/partners.jsp">Partners</a></li> --%>
					<li><a href="${pageContext.request.contextPath}/contactUs"><spring:message code="BzComposer.contact"/></a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- div for mobile screen ends -->
	
	<!-- div for bigger screen starts -->
	<div class="hidden-tablet-landscape">
		<div class="header header-1">
			<div class="container">
				<div class="row">
					<div class="col-md-3" align="left">
						<div class="block-left">
							<div class="logo">
								<a href="${pageContext.request.contextPath}/index.jsp"> 
									<img src="${pageContext.request.contextPath}/dist/template/images/icons/BzComposerLogo.png" alt="Consulting" />
								</a>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="search-wrap" align="right">
							<div class="input-group" align="right">
								<input type="text" id="searchBox" name="searchBox" placeholder="<spring:message code="BzComposer.searchtext"/>" style="width: 241px" />
								<div class="input-group-btn">
									<button class="btn btn-primary" onclick="searchText()"><spring:message code="BzComposer.searchbuttontext"/></button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-3" align="right">
						<div class="input-group">
							<select name="locale" id="locale" class="form-control" onchange="showLocale();">
								<option value=""><spring:message code="BzComposer.selectlanguage"/></option>
								<option value="en"><spring:message code="BzComposer.selectlanguage.english"/></option>
								<option value="zh"><spring:message code="BzComposer.selectlanguage.chinese"/></option>
								<option value="es"><spring:message code="BzComposer.selectlanguage.spanish"/></option>
							</select>
						</div>
					</div>
					<div class="col-md-3" align="right">
						<div align="center">
							<div class="contact-widget contact-widget-1">
								<button type="button" class="btn btn-primary" onclick="redirectToLogin()"><spring:message code="BzComposer.login"/></button>
								<button type="button" class="btn btn-primary" onclick="openRegisterPage()"><spring:message code="BzComposer.register"/></button>
								<button class="hamburger hamburger--spin hidden-tablet-landscape-up" id="toggle-icon">
									<span class="hamburger-box"> 
										<span class="hamburger-inner"></span>
									</span>
								</button>							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="section section-navbar-1 bg-grey hidden-tablet-landscape"
		id="js-navbar-fixed">
		<div class="text-center">
			<div class="text-center">
				<div class="logo-mobile">
					<a href="${pageContext.request.contextPath}/index.jsp"> 
						<img src="${pageContext.request.contextPath}/dist/template/images/icons/BzComposerLogo.png" alt="Consulting"/></a>
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
                                    <logic:iterate name="acList" id="objList1" indexId="ndx">
                                        <li id="<bean:write name="objList1" property="companyid" />"><a href="#"><bean:write name="objList1" property="companyName" /></a></li>
                                    </logic:iterate>
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
							<%-- <li><a href="${pageContext.request.contextPath}/partners.jsp">Partners</a></li> --%>
							<li><a href="${pageContext.request.contextPath}/contactUs"><spring:message code="BzComposer.contact"/></a></li>
						</ul>
					</div>
				</nav>
			</div>
	</div>
	<!-- div for bigger screen ends -->
</header>
<!-- header / end-->

<div id="divIndustires" class="container">
	<h2>
		<spring:message code="BzComposer.industries.industries"/>
	</h2>
	
	<div class="para-temp">
		<spring:message code="BzComposer.industries.industriescontent"/>
	</div>
	<div class="para-temp">
		<h4 style="float: left; width: 100%;">
			<b>
				<spring:message code="BzComposer.industries.retailers"/>
			</b>
		</h4>
	    <p class="para-temp">
	    	<spring:message code="BzComposer.industries.retailerscontentone"/>
	    </p>
	    <p class="para-temp">
	    	<spring:message code="BzComposer.industries.retailerscontenttwo"/>
	    </p> 
	</div>
	<div class="para-temp">
		<h4 style="float: left; width: 100%;">
			<b>
				<spring:message code="BzComposer.industries.manuacturerandwholesaler"/>			
			</b>
		</h4>
	    <spring:message code="BzComposer.industries.manuacturerandwholesalercontent"/>	    
	</div>
	<div class="para-temp">
		<h4 style="float: left; width: 100%;">
			<b>
				<spring:message code="BzComposer.industries.professionalservicecompany"/>
			</b>
		</h4>
	    <spring:message code="BzComposer.industries.professionalservicecompanycontent"/>
	</div>
	<div class="para-temp">
		<h4 style="float: left; width: 100%;">
	   		<b>
	   			<spring:message code="BzComposer.industries.ecommercecompany"/>
   			</b>
		</h4>
	    <spring:message code="BzComposer.industries.ecommercecompanycontent"/>
	</div>
</div>
<%@ include file="templateFooter.jsp"%>
<div id="up-to-top">
	<i class="fa fa-angle-up"></i>
</div>
<%@ include file="templateScript.jsp"%>
</body>
</html>