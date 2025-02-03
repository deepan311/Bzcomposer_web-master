<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*"%>
<style>
.highlight 
{
  background-color: yellow;
}
</style>
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
	var txt1 = $('#features').html();
	if(term.length>1)
	{
		if(term == 'Style' || term == 'style' || term == 'Class' || term == 'class ' || term == 'para-temp')
		{

			document.getElementById("features").innerHTML = txt1;
		}
		else
		{
			var txt = $('#features').html().replace(highlightRe,'$1');
			if(term !== '') 
			{
				txt = txt.replace(new RegExp('(' + term + ')', 'gi'), highlightHtml);
			}    
			$("#features").html(txt);
			setTimeout(function(){ 
				document.getElementById("features").innerHTML = txt1;
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
	var txt1 = $('#features').html();
	if(term.length>1)
	{
		if(term == 'Style' || term == 'style' || term == 'Class' || term == 'class ' || term == 'para-temp')
		{

			document.getElementById("features").innerHTML = txt1;
		}
		else
		{
			var txt = $('#features').html().replace(highlightRe,'$1');
			if(term !== '') 
			{
				txt = txt.replace(new RegExp('(' + term + ')', 'gi'), highlightHtml);
			}    
			$("#features").html(txt);
			setTimeout(function(){ 
				document.getElementById("features").innerHTML = txt1;
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
<%-- <html:html lang="en"> --%>
<html>
<title><spring:message code="BzComposer.featurestitle"/></title>
<%@ include file="templateHeader.jsp"%>
<body>
<!--  <header> -->
<div>
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
							<!-- <div class="contact-widget contact-widget-1"> -->
							<button type="button" class="btn btn-primary" onclick="redirectToLogin()"><spring:message code="BzComposer.login"/></button>
							<button type="button" class="btn btn-primary" onclick="openRegisterPage()"><spring:message code="BzComposer.register"/></button>
							<button class="hamburger hamburger--spin hidden-tablet-landscape-up" id="toggle-icon">
								<span class="hamburger-box"> <span class="hamburger-inner"></span></span>
							</button>	
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
		<!--</div> -->
		</div>
	</div>
	<div class="section section-navbar-1 bg-grey hidden-tablet-landscape" id="js-navbar-fixed">
		<div class="text-center">
			<div class="text-center">
				<div class="logo-mobile">
					<a href="${pageContext.request.contextPath}/index.jsp"> 
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
	</div>	
</div>
<div id="features" class="container">
	<h2>
		<b>
			<spring:message code="BzComposer.feature.features"/>
		</b>
	</h2>
	<div class="para-temp">
		<spring:message code="BzComposer.feature.featurescontent"/>
		<!-- The Starter Store is perfect for any merchant who is just getting started and unsure about doing commerce over the Internet. Regardless of which four store types merchant build, they are able to upgrade to a higher store with a click of a button. Register now and open your very own shop/site today! -->
	</div>
	
	<div id="easySetup" class="para-temp">
		<h4 style="float: left; width: 100%;">
			<b>
				<spring:message code="BzComposer.feature.easysetup"/>			
				<!-- 1.Easy Setup --> 
			</b>
		</h4>
	    <div class="b_feature-description">
	   		<h4 style="float: left; width: 100%;">
	   			<b>
	   				<spring:message code="BzComposer.feature.startingwizards"/>
	   				<!-- a. Starting Wizards -->
   				</b>
			</h4>  	
			<spring:message code="BzComposer.feature.startingwizardscontent"/>	
			<!-- commented on 11-10-2019 -->
     		<!-- The BzComposer wizard automates the creation of company accounting systems in just a few minutes with minimum system requirements. 
     		The wizard setup is easy to update with more than 12 sections (Company Name & Info, Chart of Accounts, Preference, Customer & Vendor 
     		Input, Inventory, and more).<br>
     		BzComposer automates product uploading using batch methods with a built-in Data Import and Export feature. 
     		Merchants are able to upload their products manually or import records using spreadsheets or text files. 
     		Included are five sample companies (retail, wholesale, eSales, contractors, and accounting) to jumpstart your use of the 
     		application easily. -->
		</div>
	    <div class="b_feature-description">
			<h4 style="float: left; width: 100%;">
				<b>
					<spring:message code="BzComposer.feature.easyconfiguration"/>
					<!-- b. Easy Configuration -->
				</b>
			</h4> 
			<spring:message code="BzComposer.feature.easyconfigurationcontent"/> 
   			<!-- commented on 11-10-2019 -->
   			<!-- The BzComposer configuration covers with sub categories of  Accounting & payment, Networking & Security, Billing, Customer & Invoice, Inventory Setting, 
			Form Customization, Purchase Order, Employee, Tax, Reminders, eMail Setup, Performance, Shipping, RMA, eSales, Payment Options, Device manager, 
			Payment gateways and Printer Setup.	 -->	
     		
     		<!-- The BzComposer configuration covers with sub categories of
     		<ul>
     			<li>Accounting & payment</li>
     			<li>Networking & Security</li>
     			<li>Billing</li>
     			<li>Customer & Invoice</li>
     			<li>Inventory Setting</li>
     			<li>Form Customization</li>
     			<li>Purchase Order</li>
     			<li>Employee</li>
     			<li>Tax</li>
     			<li>Reminders</li>
     			<li>eMail Setup</li>
     			<li>Performance</li>
     			<li>Shipping</li>
     			<li>RMA</li>
     			<li>eSales</li>
     			<li>Payment Options</li>
     			<li>Device manager</li>
     			<li>Payment gateways</li>
     			<li>Printer Setup</li>
     		</ul> -->
		</div>
	     	
		<div class="b_feature-description">
	   		<h4 style="float: left; width: 100%;">
	   			<b>
	   				<spring:message code="BzComposer.feature.easynavigation"/>
	   				<!-- c. Easy Navigation -->
   				</b>
			</h4>  		
     		<spring:message code="BzComposer.feature.easynavigationcontent"/>
     		<!-- The Navigation menu (left-hand side) of BzComposer shows all detailed usages of the features easily. 
     		It covers Starting, Configuration, Customer, Vendor, Sales, Purchase, Items, RMA, Accounting, Billing, Employee, eSales, and Reports. 
     		Each navigational menu shows all related features with all possible related pages. -->
	     </div>			
	    </div>
	    <div id="enhancedFeatures" class="para-temp">
			<h4 style="float: left; width: 100%;">
				<b>
					<spring:message code="BzComposer.feature.enhancedfeatures"/>
					<!-- 2. Enhanced Features -->
				</b>
			</h4>
	   		<div class="b_feature-description">
				<h4 style="float: left; width: 100%;">
					<b>
						<spring:message code="BzComposer.feature.performanceenhancement"/>
						<!-- a. Performance enhancement -->
					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.performanceenhancementcontent"/>
	     		<!-- The software speed is dependent on the existing database sizes. 
	     		As the sizes of customers, inventory, or orders are increased, the software is affected.  
	     		BzComposer users can enhance the program's speed by selecting a low, medium, high, or user-defined database size. --> 
	     	</div>
	     	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.networking"/>
	   					<!-- b. Networking -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.networkingcontent"/>
	     		<!-- BzComposer's accounting system provides a networking feature with a common database server with MS SQL, Oracle, My SQL database, 
	     		or optional MS Access. 
	     		It supports unlimited workstations with SQL database. 
	     		The back office workstation houses the backup of all transactions from up front and allows the administrator to manage the inventory 
	     		and sales database and run analyses and reports. -->
	     	</div>
	     	<div class="b_feature-description">
				<h4 style="float: left; width: 100%;">
					<b>
						<spring:message code="BzComposer.feature.easydatamanagement"/>
						<!-- c. Easy Data management -->
					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.easydatamanagementcontent"/>
	     		<!-- Nextbits Electronic Data Interchange (EDI) is a data transfer utility tool that uses .txt, .csv, MS Excel, MS Access, MS SQL, 
	     		My SQL and Oracle databases with a few simple steps. 
	     		With the options to create, select, or match database table to get the best results, users are able to easily import and export files 
	     		regardless of the data structure, and match and map fields between the imported and destination files.  
	     		It allows you to handle databases regardless of structure by mapping database fields.  
	     		The export process is similar to the import process, but with the ability to choose a different database to export into. 
	     		It gives users the power of speed, flexibility, knowledge, and reliability in data manipulation, reducing the cost of data integration. --> 
	     	</div>
	     	<div class="b_feature-description">
				<h4 style="float: left; width: 100%;">
					<b>
						<spring:message code="BzComposer.feature.backupandrestore"/>
						<!-- d. Backup & Restore -->
					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.backupandrestorecontent"/>
	     		<!-- Whether you're a small business or an individual, backing up your files is an important exercise. 
	     		The smallest thing could bring everything crashing to the ground, so you have to be prepared in case something happens. 
	     		If your system stays safe, you can breathe a sigh of relief knowing you would be prepared in the event of a collapse. 
	     		Your backup should be simple to set up and easy to restore. 
	     		Accessing restored data should be automatic, and the backup should preserve original data files and paths. 
	     		You can configure the backup time and place to fit your needs. -->
	     	</div>
	        <div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.multicompanyandcurrency"/>
	   					<!-- e. Multi-company and Multi-currency support -->
   					</b>
				</h4>  		
				<spring:message code="BzComposer.feature.multicompanyandcurrencycontent"/>
				<!--Whether you're a small business or an individual, backing up your files is an important exercise. 
	     		The smallest thing could bring everything crashing to the ground, so you have to be prepared in case something happens. 
	     		If your system stays safe, you can breathe a sigh of relief knowing you would be prepared in the event of a collapse. 
	     		Your backup should be simple to set up and easy to restore. 
	     		Accessing restored data should be automatic, and the backup should preserve original data files and paths. 
	     		You can configure the backup time and place to fit your needs. -->
	     		
	     		<!-- commented on 11-10-2019 -->
	     		<!-- BzComposer supports multi-company, allowing the creation of an unlimited number of companies.
	     		Users are able to select the local currency in the configuration. 
				The enterprise version of BzComposer will support multi-company with extra documents and management features for related activities. --> 
			</div>
			<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.calendercalculatorreminder"/>
	   					<!-- f. Calendar, Calculator, & Reminder -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.calendercalculatorremindercontent"/>
	     		<!-- BzComposer includes Calendar, Calculator  & Reminders features. -->
	     	</div>
	     	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.industriyspacificsystem"/>
	   					<!-- g. Industry Specific system -->
   					</b>
				</h4>  			     		
	     		<spring:message code="BzComposer.feature.industriyspacificsystemcontent"/>
	     		<!-- Retailers and wholesalers are generally concerned with order and inventory management. 
				Some service industries such as accounting firms, however, might need more billing oriented features, 
				while service contractor companies in the construction industry might need both service and order features. 
				BzComposer offers a template dependent on the industry. 
				It offers five templates in Retail, Manufacture & Wholesale, Professional Service, Service Contractor, and eSales. -->
	     	</div>
		</div>
		<div id="customerContactManagement" class="para-temp">
			<h4 style="float: left; width: 100%;">
				<b>
					<spring:message code="BzComposer.feature.customercontactmanagement"/>
					<!-- 3. Customer & Contact Management -->
				</b>
			</h4>
	   		<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.customermailinglabels"/>
	   					<!-- a. Customer & Mailing labels -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.customermailinglabelscontent"/>
	     		<!-- Track and manage your customers, visitor statistics, dealer lists, and all other customer-related transactions and histories with 
	     		our Customer Manager. 
	     		You are able to send emails automatically to your customer with a variety of email templates such as Welcome, 
	     		Password Retrieval, Order Confirmation, Credit Card Declined, Shipping Confirmation, Order Cancellation, and other. 
	     		The Customer Manager can be printed with varied sizes of mailing labels and can be imported/exported to customer lists 
	     		in the .csv, MS Excel, .txt format files. -->
	     	</div>
	   		<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.emailmanagement"/>
	   					<!-- b. eMail management -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.emailmanagementcontent"/>
	     		<!-- BzComposer provides email to the customer with an invoice. 
	     		The store owner can send personal mail, group emails, and news letters to the selected customers. 
	     		You can send e-mails automatically to your customer with a variety of e-mail templates such as Order Confirmation, 
	     		Credit card Declined, Shipping Confirmation and Order Cancellation. -->  
	     	</div>
	     	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.groupbilling"/>
	   					<!-- c. Group Billing & eMails -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.groupbillingcontent"/>
	     		<!-- BzComposer Billing is the easiest billing program to use for service oriented professionals such as doctors, accountants, 
	     		and lawyers (etc) with group billing statements. 
	     		The recurring billing feature handles subscriptions and installment sales. 
	     		With standard industry billing forms, BzComposer Billing handles accurate billing, customer mailing labels, reports, and more. 
	     		Bzcomposer Billing was created to help you bill your customers more easily and effectively. -->
			</div>
		</div>
		<div id="completeOrderManagement" class="para-temp">
	    	<h4 style="float: left; width: 100%;">
	    		<b>
	    			<spring:message code="BzComposer.feature.completeordermanagement"/>
	    			<!-- 4. Complete Order management -->
    			</b>
   			</h4>
	    	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
						<spring:message code="BzComposer.feature.invoiceestimationpo"/>	   				
	   					<!-- a. Invoice, Estimation, and PO -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.invoiceestimationpocontent"/>
	     		<!-- The invoice has the capability to add a logo. 
	     		You are able to print out invoices of industry standard size and change the order status manually to Shipped, Card Declined, 
	     		Cancelled, or Returned. 
	     		You are able to choose from several different invoice forms to suit your need: Product Invoice, Manufacturer Invoice, Finance Invoice, 
	     		Professional Invoice, Service Invoice, and P.O.S. Invoice. 
	     		Also, you are able to customize your invoice form as well as the purchase order form as needed. 
	     		The created estimation, memorized orders and pending orders can be easily transformed to sales orders. 
	     		Back orders are also easily transformed to purchase orders. --> 
	     	</div>
	     	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.discountpricemanagement"/>
	   					<!-- b. Discount & Price management -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.discountpricemanagementcontent"/>
	     		<!-- BzComposer has a feature that allows for multiple prices in the invoice. 
	     		The customer based pricing is available so that each customer could have different discount rates. 
	     		Special customers can receive special discounts without an operator having to complete any additional manual steps. 
	     		The rate will be configured by percentage method from the configuration. --> 
	     	</div>
	     	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.backorder"/>
	   					<!-- c. Back Order -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.backordercontent"/>
	     		<!-- BzComposer keeps track of your back-ordered, and a purchase order could then be created from the sales order with back-ordered items.  
	     		An open purchase order by item report would tell you, at any time, which items are on back-order. 
	     		When the items are received against the open purchase orders, these purchase orders are no longer showing as open. --> 
	     	</div>
	     	<div class="b_feature-description">
				<h4 style="float: left; width: 100%;">
					<b>
						<spring:message code="BzComposer.feature.reibursement"/>					
						<!-- d. Reimbursement -->
					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.reibursementcontent"/>
	     		<!-- When you use personal money, from either your or your employees, to buy something for the company you work for, 
	     		the company pays you back reimbursement. 
	     		Common examples are firms compensating individuals who buy supplies for their company/firm and compensating employees on field or 
	     		out-of-town assignments who pay for their own stay and transportation. --> 
	     	</div>
	     	<div class="b_feature-description">
				<h4 style="float: left; width: 100%;">
					<b>
						<spring:message code="BzComposer.feature.depositlayaways"/>
						<!-- e. Upfront Deposit, Layaways, Consignment, and Gift Certificate -->
					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.depositlayawayscontent"/>
	     		<!-- Some retailers and wholesalers may need up-front deposits, layaways, consignments or a gift certificate. 
	     		These features are not required by many users but there is an option to include this feature in the configuration if needed. --> 
			</div>
		</div>
		<div id="inventoryWarehouseManagement" class="para-temp">
	    	<h4 style="float: left; width: 100%;">
	    		<b>
	    			<spring:message code="BzComposer.feature.inventorywarehousemanagement"/>
	    			<!-- 5. Inventory & Warehouse Management -->
    			</b>
   			</h4>
	    	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.inventorymanagement"/>
	   					<!-- a. Inventory -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.inventorymanagementcontent"/>
	     		<!-- The task of managing real-time inventory is simplified with our Inventory Manager. 
	     		You are able to easily search through inventory and change the unit price of stock status for either a single or multiple items in 
	     		one click. 
	     		It shows the details and statistics of sold-out items and items lacking descriptions or images. 
	     		Bzcomposer provides integration with the supplier, order management, and store inventory system. 
	     		It offers a solution that allows our customers to fulfill more orders, across the channel, regardless of the specific inventory position. 
	     		The inventory lists are importable and exportable in .csv, MS Excel, and .txt file formats. --> 
	     	</div>
	     	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.warehousemanagement"/>
	   					<!-- b. Warehouse Management -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.warehousemanagementcontent"/>
	     		<!-- BzComposer provides real-time inventory integration between your sales system and your warehouse. 
	     		If a customer purchases your product in the store or in the Internet shopping mall, the system can automatically remove it from your 
	     		inventory and Internet sales channels and vice versa. 
	     		The ERP system can be upgraded with a new barcode tracking system for the warehouse and an improved inventory counting process. 
	     		Real-time inventory lookup at the store level allows for a quick view of the inventory by branch location, including the distribution 
	     		center. --> 
	     	</div>
	     	<div class="b_feature-description">
				<h4 style="float: left; width: 100%;">
					<b>
						<spring:message code="BzComposer.feature.wholesupportwithdropshipping"/>
						<!-- c. Wholesaler support with a Drop-shipping & Order Import -->
					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.wholesupportwithdropshippingcontent"/>
	     		<!-- One fulfillment trend that eases the strain on a wholesaler's pocketbook is drop shipping, a process in which a third party, 
	     		often the manufacturer of a product, ships them to the customer on behalf of the retailer. 
	     		This supports both wholesaler and retailer, offering separate or combined sites. 
	     		Drop-shipping is an important element that all wholesalers should support retailers with. 
	     		Drop-shippers are able to send order confirmations and shipment status reports via email or .txt file transfers into the order 
	     		management system. --> 
	     	</div>
			<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.rmatrackingreturnedpackage"/>
	   					<!-- d. RMA & Tracking a Returned Package -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.rmatrackingreturnedpackagecontent"/>
	     		<!-- Every company needs to process customer returns with Return Merchandise Authorization (RMA) handling. 
	     		As customers demand becomes more sophisticated, tracking and resolving these returns can be an increasing challenge. 
	     		Tracking a returned package is not an easy process because there is no visibility at all into the progress of a returned item. 
	     		Your customer might ask for credit back in forms of a replacement, refund, or repair. 
	     		Combining the RMA module with the required Sales Order and Inventory modules lets you offer customer satisfaction service. 
	     		BzComposer provides more information about the status of a customer's returns. -->
	     	</div>	
	     	<div class="b_feature-description">
				<h4 style="float: left; width: 100%;">
					<b>
						<spring:message code="BzComposer.feature.posandrfid"/>
						<!-- e. POS and RFID -->
					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.posandrfidcontent"/>
	     		<!-- BzComposer is able to scan each product barcode using a popular scanner and read orders through POS. -->
	     	</div>	
	    </div>
	    <div id="shippingPaymentIntegration" class="para-temp">
	    	<h4 style="float: left; width: 100%;">
	    		<b>
	    			<spring:message code="BzComposer.feature.shippingpaymentintegration"/>
	    			<!-- 6. Shipping & Payment Integration -->
    			</b>
   			</h4>
	    	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.paymentintegration"/>
	   					<!-- a. Payment Integration -->
   					</b>
				</h4>
				<spring:message code="BzComposer.feature.paymentintegrationcontent"/>  		
	     		<!-- One of the most powerful Nextbits features is the integrated payment and shipping methods. 
	     		Most online shoppers still pay with credit cards or money order, but payment alternatives are slowly gaining traction with 
	     		Paypal or Google Checkout. 
	     		We support 20 major payement gateways like Authorize.net, Verisign, Paypal, and Google Checkout, to name a few. 
	     		Nextbits leaves it up to you to choose the payment methods. -->
	     	</div>
	     	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.shippingintegrationandstatus"/>
	   					<!-- b. Shipping Integration & Shipping Status* -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.shippingintegrationandstatuscontent"/>
	     		<!-- Shipping has always been a critical service for online retailers who rely on fast, reliable, and efficient deliveries to compete 
	     		against the immediate satisfaction and personal touch that consumers receive in physical stores. 
	     		Having the shipping information completely accessible and in real-time gives online retailers more control over their business and 
	     		allows them to generate sales today instead of tomorrow. 
	     		We support USPS, UPS, FeDEX, and customized shipping rate. 
	     		Also, it shows shipping status for USPS, UPS, and FeDEX through online. --> 
	     	</div>
	    </div>
	    <div id="fullFeaturedAccountingSystem" class="para-temp">
			<h4 style="float: left; width: 100%;">
				<b>
					<spring:message code="BzComposer.feature.accountingsystem"/>
					<!-- 7. Full-featured Accounting System -->
				</b>
			</h4>
	    	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.completingaccounting"/>
	   					<!-- a. Complete Accounting --> 
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.completingaccountingcontent"/>
	     		<!-- BzComposer has full features for banking, accounting receivable, and accounting payable. 
	     		In banking, multiple checking and saving accounts, credit card accounts, and cash are managed. 
	     		It allows for multiple payment options in account payable along with the option to map transactions to various general ledger accounts. 
	     		It provides the ability to generate complete balance sheets, profits, lost statements and other standard financial reports. 
	     		It is guaranteed accurate and timely collaboration on forecasts. -->
	     	</div>
	     	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.lineofcredit"/>
	   					<!-- b. Line of Credit, Discount, and Credit -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.lineofcreditcontent"/>
	     		<!-- BzComposer handles the line of credit to each customer. 
	     		The store credits, refunds, discounts, and gift certificates can be maintained more efficiently in the newer system. 
	     		All activities will be recorded on the daily log file. -->
	     	</div>
	     	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.budgetplannig"/>
	   					<!-- c. Budget & Planning -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.budgetplannigcontent"/>
	     		<!-- Budget & Planning calculates your average spending in any category and can turn it into a budget with one click. 
	     		Compare your spending year-to-year or month-to-month. 
	     		BzComposer makes it easier to create a budget that works for you. 
	     		You are able to select a plan for each category of insurance, auto expense and purchase along with one-time only expenses. 
	     		It allows the tracking of your progress toward profits and loss improvement. -->
	     	</div>
	    </div>
		<div id="payRollTax" class="para-temp">
	   		<h4 style="float: left; width: 100%;">
	   			<b>
	   				<spring:message code="BzComposer.feature.payrolltax"/>
	   				<!-- 8. Payroll & Tax -->
   				</b>
			</h4>
			<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.payroll"/>
	   					<!-- a. Payroll* -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.payrollcontent"/>
	     		<!-- BzComposer has a payroll feature available through the website. -->
	     	</div>
	     	<div class="b_feature-description">
	   			<h4 style="float: left; width: 100%;">
	   				<b>
	   					<spring:message code="BzComposer.feature.salestax"/>
	   					<!-- b. Sales Tax -->
   					</b>
				</h4>  		
	     		<spring:message code="BzComposer.feature.salestaxcontent"/>
	     		<!-- BzComposer handles sales tax with its detailed information monthly, quarterly, and yearly. -->
	     	</div>		
	   	</div>
		<div id="completeRealTimeReports" class="para-temp">
	    	<h4 style="float: left; width: 100%;">
	    		<b>
	    			<spring:message code="BzComposer.feature.completerealtimereports"/>
	    			<!-- 9. Complete Real-time Reports -->
    			</b>
   			</h4>
		    <spring:message code="BzComposer.feature.completerealtimereportscontent"/>
		    <!-- BzComposer provides a complete and real-time report with a daily sales report. 
		    The reports include credit/refunds, account receivable/payable, profit/loss, sales reports by customer/items/sales person, and various charts. 
		    The order history can be viewed daily, weekly, monthly, or yearly and item statistics show the number of sold items and analyzes expected profit 
		    of each product. -->
	    </div>
	    <div id="eCommerceIntegration" class="para-temp">
	    	<h4 style="float: left; width: 100%;">
	    		<b>
	    			<spring:message code="BzComposer.feature.ecommerceintegration"/>
	    			<!-- 10. e-Commerce Integration -->
    			</b>
   			</h4>
   			<spring:message code="BzComposer.feature.ecommerceintegrationcontent"/>
		    <!-- Retail trends are evolving every day and if you want to stay competitive, it is necessary to keep up with these trends. 
		    Retailers and wholesalers are continuously searching for the most cost-effective way to expand their businesses and to do so, 
		    they need to compete with larger retailers and maintain presence in the industry. 
		    The current focus for these small to midsize retailers is integrated ecommerce, which produces an expanded client base and increased sales, 
		    all the while keeping costs down and working within the limited resources of the smaller retailer. 
		    BzComposer is integrated with Amazon and eBay. 
		    Through any type of stores, merchants are able to post products from their online store directly onto Amazon and eBay. -->
	    </div>
	</div>
	<%@ include file="templateFooter.jsp"%>
    <div id="up-to-top">
		<i class="fa fa-angle-up"></i>
	</div>
    <%@ include file="templateScript.jsp"%>
	</body>
</html>