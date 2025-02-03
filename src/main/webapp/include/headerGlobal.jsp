<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function() 
{
	var locale = "<%= request.getAttribute("selectedLocale")%>";
	$('select[id="locale"]').find('option[value="'+locale+'"]').attr("selected",true);
});  
function showLocale()
{
	debugger;
	var lang = document.getElementById("locale").value;
	if(lang == "")
		alert("<bean:message key='BzComposer.common.selectlanguagetochange'/>");
	else
	window.location="Locale.do?request_locale="+lang;

	/* window.location="Locale.do?method="+lang; */ 
	/* $.ajax({
		type: "POST",
		url:"Locale.do?request_locale="+lang,
		data:{lang : lang},
		}).done(function(data){
			debugger;
	}); */
}
function showLocaleMobile()
{
	debugger;
	var lang = document.getElementById("localeMobile").value;
	if(lang == "")
		alert("<bean:message key='BzComposer.common.selectlanguagetochange'/>");
	else
		window.location="Locale.do?request_locale="+lang;

	/* window.location="Locale.do?method="+lang; */ 
	/* $.ajax({
		type: "POST",
		url:"Locale.do?request_locale="+lang,
		data:{lang : lang},
		}).done(function(data){
			debugger;
	}); */
}
</script>
<header> 
	<!-- header / start-->
      <!-- form itself -->
      <!-- mfp-hide -->
      <div id="test-form" class="mfp-hide white-popup-block">
      
      <!-- SETUP -->
        <%-- <div id="setup">
            <html:form action="Login.do?tabid=welcomescreen" method="post">
            
					<input type="hidden" name="actionType" value="">
	          <div >
			<div class="form-signin">
			<h2 class="form-signin-heading">Please sign in</h2>		
			<div class="tr">
					<div class="bg-danger"><html:errors /></div>
			</div>
			  <div class="form-group">
			
			<label for="userName" class="">Email address</label>				
			 <html:text property="userName" styleId="userName" styleClass="form-control\" autoComplete=\"off" value="<%= userName %>"/>
			 
			 </div>
			 
			   <div class="form-group">
			 
			 <label for="password" class="">Password:</label>		
			<html:password property="password" styleId="password" styleClass="form-control" value="<%= pass %>" />
				</div>
				
				<div class="form-group">
					<html:checkbox property="rememberMe" value="<%= remember %>">Remember Me</html:checkbox>
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
		</html:form>
        </div>      --%>
      </div>
      
      <!-- forgot password starts -->
      <%-- <div id="forgotPassword-form" class="mfp-hide white-popup-block">
      	<div id="setup">
             <html:form action="Login.do?tabid=forgotPassword" method="post" styleId="forgotForm">
            	<input type="hidden" name="actionType" value="">
	          	<div>
					<div class="form-signin">
						<h2 class="form-signin-heading">Forgot your password?</h2>
						<div class="form-group">
							<b>To reset your password type your loginId.</b>
						</div>
						<div class="tr">
							<div class="bg-danger"><html:errors /></div>
						</div>
						<div class="form-group">
						<!-- <form id="forgotPasswordForm" action="Login.do?tabid=forgotPassword" method="post"> -->
						<html:form action="Login.do?tabid=forgotPassword" method="post" styleId="forgotPasswordForm">
							<input type="hidden" id="action" value=""/>
							<label id="message"></label>
			  			<div class="form-group">
			  				
							<label for="userName" class="">Your Email address</label>				
			 				<html:text property="userName" styleId="userName" styleClass="form-control\" autoComplete=\"off" />
			 				<input type="text" id="loginID" name="loginID" class="form-control" style="autocomplete:off"/>
			 			</div>
			 			<div class="form-group" align="right">
			 				<!-- <button type="button" class="btn btn-primary" value="Check" onclick="checkUser()">Check</button> -->
			 				<button type="submit" class="btn btn-primary" value="Check">Check</button>
			 			</div>
			 			</html:form>
			 			<!-- </form> -->
			 			</div>
			 			<div class="form-group">
			 				<label for="password" class="">Password Hint:</label>		
							<html:password property="password" styleId="password" styleClass="form-control" />
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
			 </html:form>
        </div>     
	</div> --%>
    <!-- forgot password ends -->  
    
	<div class="hidden-tablet-landscape-up">
		<div class="header header-mobile-1">
			<div class="container">
				<%-- <%@ include file="headerTabletLandscapeUpDiv.jsp" %> --%>
				<div align="center">
					<div class="logo" style="max-width: 100%;">
						<a href="index.jsp"> 
							<img src="${pageContext.request.contextPath}/dist/template/images/icons/BzComposerLogo.png" alt="Consulting" style="max-width: 100%"/>
						</a>
					</div>
				</div>
				<!-- <div class="contact-widget contact-widget-1"> -->
				<div align="center">
					<input type="text" id="searchBox" name="searchBox" placeholder="Search for keyword or Items" style="max-width: 100%;" />
					<button class="btn btn-primary" onclick="searchText()">Search</button>
					<select name="localeMobile" id="localeMobile" class="form-control" onchange="showLocaleMobile()">
						<option value="">Select Language</option>
						<option value="english">English</option>
						<option value="chinese">Chinese</option>
						<option value="spanish">Spanish</option>
					</select>
				</div>
				<div align="center">
					<button type="button" class="btn btn-primary" onclick="redirectToLogin()">Login</button>
					<button type="button" class="btn btn-primary" onclick="openRegisterPage()">Register</button>
					<button class="hamburger hamburger--spin hidden-tablet-landscape-up" id="toggle-icon">
						<span class="hamburger-box"> <span class="hamburger-inner"></span></span>
					</button>	
				</div>
			</div>
			
			<div class="au-navbar-mobile navbar-mobile-1">
				<ul class="au-navbar-menu">
					<li><a href="#"><bean:message key="BzComposer.index.home"/></a></li>
					<li><a href="${pageContext.request.contextPath}/bzComposer"><bean:message key="BzComposer.index.whatisbzcomposer"/></a></li>
					<li><a href="${pageContext.request.contextPath}/company.jsp">About us</a></li>
					<li><a href="${pageContext.request.contextPath}/existingCompetitors.jsp">Differences with the existing competitors</a></li>
					<li class="drop">
						<a href="#">Sample Companies</a>
							<span class="arrow"><i></i></span>
							<ul class="drop-menu bottom-right">
								<li><a href="Login.do?tabid=selectedCompany&selectedCompanyId=4&companyName=ABC eSales Company">ABC eSales Company</a></li>
								<li><a href="Login.do?tabid=selectedCompany&selectedCompanyId=2&companyName=ABC Retails Company">ABC Retails Company</a></li>
								<li><a href="Login.do?tabid=selectedCompany&selectedCompanyId=3&companyName=ABC Wholesale Company">ABC Wholesale Company</a></li>
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
					<li><a href="${pageContext.request.contextPath}/ourServices.jsp">Our Services</a></li>
					<li><a href="${pageContext.request.contextPath}/industries.jsp">Industries</a></li>
					<li class="drop"><a href="${pageContext.request.contextPath}/features.jsp">Features</a>
						 <span class="arrow"><i></i></span>
						<ul class="drop-menu bottom-right">
							<li><a href="${pageContext.request.contextPath}/features.jsp#easySetup">Easy Setup</a></li>
							<li><a href="${pageContext.request.contextPath}/features.jsp#enhancedFeatures">Enhanced Features</a></li>
							<li><a href="${pageContext.request.contextPath}/features.jsp#customerContactManagement">Customer & Contact Management</a></li>
							<li><a href="${pageContext.request.contextPath}/features.jsp#completeOrderManagement">Complete Order management</a></li>
							<li><a href="${pageContext.request.contextPath}/features.jsp#inventoryWarehouseManagement">Inventory & Warehouse Management</a></li>
							<li><a href="${pageContext.request.contextPath}/features.jsp#shippingPaymentIntegration">Shipping & Payment Integration</a></li>
                            <li><a href="${pageContext.request.contextPath}/features.jsp#fullFeaturedAccountingSystem">Full-featured Accounting System</a></li>
                            <li><a href="${pageContext.request.contextPath}/features.jsp#payRollTax">Payroll & Tax</a></li>
                            <li><a href="${pageContext.request.contextPath}/features.jsp#completeRealTimeReports">Complete Real-time Reports</a></li>
                            <li><a href="${pageContext.request.contextPath}/features.jsp#eCommerceIntegration">e-Commerce Integration</a></li>
						</ul>
					</li>
					<li class="drop">
						<a href="${pageContext.request.contextPath}/products.jsp">Products</a>
						 <span class="arrow"><i></i></span>
						<ul class="drop-menu bottom-right">
							<li><a href="${pageContext.request.contextPath}/products.jsp#bzcomposerStandard">BzComposer Standard</a></li>
							<li><a href="${pageContext.request.contextPath}/products.jsp#bzcomposerBilling">BzComposer Billing</a></li>
                            <li><a href="${pageContext.request.contextPath}/products.jsp#bzcomposereSales">BzComposer eSales</a></li>
                            <li><a href="${pageContext.request.contextPath}/products.jsp#bzcomposerProfessional">BzComposer Professional</a></li>
							<li><a href="${pageContext.request.contextPath}/products.jsp#bzcompserStandardShareware">BzComposer Standard Shareware</a></li>
						</ul>
					</li>
					<%-- <li><a href="${pageContext.request.contextPath}/partners.jsp">Partners</a></li> --%>
					<li><a href="${pageContext.request.contextPath}/contactUs.jsp">Contact Us</a></li>
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
						<a href="${pageContext.request.contextPath}"> <img
							src="${pageContext.request.contextPath}/dist/template/images/icons/BzComposerLogo.png"
							alt="Consulting" />
						</a>
					</div>
				</div>
				</div>
				<div class="col-md-5" style="text-align: right;">
					<div class="search-wrap" align="right">
						<div class="input-group" align="right">
							<input type="text" id="searchBox" name="searchBox" placeholder="Search for keyword or Items" class="form-control" />
					 		<div class="input-group-btn">
								<button class="btn btn-primary" class="form-control" onclick="searchText()">Search</button>
							</div>
						</div>
					</div>	
				</div>
				<div class="col-md-2" align="right">
					<div class="input-group" align="center">
						<select name="locale" id="locale" class="form-control" onchange="showLocale();">
							<option value="">Select Language</option>
							<option value="en">English</option>
							<option value="zh">Chinese</option>
							<option value="es">Spanish</option>
							<!-- <option value="en">Select Language</option>
							<option value="english">English</option>
							<option value="chinese">Chinese</option>
							<option value="spanish">Spanish</option> -->
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
				<div class="col-md-2" align="right">
					<div align="center">
						<div class="contact-widget contact-widget-1">
							<!-- <button type="button" class="btn btn-primary popup-with-form" href="#test-form" onclick="redirectToLogin()">Login</button> -->
							<button type="button" class="btn btn-primary" onclick="redirectToLogin()">Login</button>
							<button type="button" class="btn btn-primary" onclick="openRegisterPage()">Register</button>
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
					<a href="index.html"> <img
						src="${pageContext.request.contextPath}/dist/template/images/icons/BzComposerLogo.png"
						alt="Consulting"></a>
				</div>
			</div>
				<nav class="text-center">
				<div class="au-navbar navbar-1">
					<ul class="au-navbar-menu">
						<li><a href="#"><bean:message key="BzComposer.index.home"/></a></li>
						<li><a href="${pageContext.request.contextPath}/bzComposer"><bean:message key="BzComposer.index.whatisbzcomposer"/></a></li>
						<li><a href="${pageContext.request.contextPath}/company.jsp"><bean:message key="BzComposer.index.aboutus"/></a></li>
						<li><a href="${pageContext.request.contextPath}/existingCompetitors.jsp"><bean:message key="BzComposer.index.existingcompetitors"/></a></li>
						<%-- <li><a href="${pageContext.request.contextPath}/applicableIndustries.jsp">Applicable Industries</a></li> --%>
						<li class="drop"><a href="#"><bean:message key="BzComposer.index.samplecompaies"/></a>
							<span class="arrow"><i></i></span>
							<ul class="drop-menu bottom-right">
								<li><a href="#" onclick="openSampleCompany4();">ABC eSales Company</a></li>
								<li><a href="#" onclick="openSampleCompany2();">ABC Retails Company</a></li>
								<li><a href="#" onclick="openSampleCompany3();">ABC Wholesale Company</a></li>
							</ul>
							<%-- <ul class="drop-menu bottom-right" id="sampleCompanyList">
								<logic:iterate name="acList" id="objList1" indexId="ndx">
									<li id="<bean:write name="objList1" property="companyid" />"><a href="#"><bean:write name="objList1" property="companyName" /></a></li>
								</logic:iterate>
							</ul> --%>
						</li>
						<%-- <li><a href="${pageContext.request.contextPath}/applicableIndustries.jsp">Applicable Industries</a></li>
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
						<li><a href="${pageContext.request.contextPath}/ourServices.jsp"><bean:message key="BzComposer.index.ourservices"/></a></li>
						<li><a href="${pageContext.request.contextPath}/industries.jsp"><bean:message key="BzComposer.index.industires"/></a></li>
						<li class="drop"><a href="${pageContext.request.contextPath}/features.jsp"><bean:message key="BzComposer.index.features"/></a>
							<span class="arrow"><i></i></span>
							<ul class="drop-menu bottom-right">
								<li><a href="${pageContext.request.contextPath}/features.jsp#easySetup">Easy Setup</a></li>
								<li><a href="${pageContext.request.contextPath}/features.jsp#enhancedFeatures">Enhanced Features</a></li>
								<li><a href="${pageContext.request.contextPath}/features.jsp#customerContactManagement">Customer & Contact Management</a></li>
								<li><a href="${pageContext.request.contextPath}/features.jsp#completeOrderManagement">Complete Order management</a></li>
								<li><a href="${pageContext.request.contextPath}/features.jsp#inventoryWarehouseManagement">Inventory & Warehouse Management</a></li>
								<li><a href="${pageContext.request.contextPath}/features.jsp#shippingPaymentIntegration">Shipping & Payment Integration</a></li>
                            	<li><a href="${pageContext.request.contextPath}/features.jsp#fullFeaturedAccountingSystem">Full-featured Accounting System</a></li>
                            	<li><a href="${pageContext.request.contextPath}/features.jsp#payRollTax">Payroll & Tax</a></li>
                            	<li><a href="${pageContext.request.contextPath}/features.jsp#completeRealTimeReports">Complete Real-time Reports</a></li>
                            	<li><a href="${pageContext.request.contextPath}/features.jsp#eCommerceIntegration">e-Commerce Integration</a></li>
							</ul>
						</li>
						<li class="drop"><a href="${pageContext.request.contextPath}/products.jsp"><bean:message key="BzComposer.index.products"/></a>
							<span class="arrow"><i></i></span>
							<ul class="drop-menu bottom-right">
								<li><a href="${pageContext.request.contextPath}/products.jsp#bzcomposerStandard">BzComposer Standard</a></li>
								<li><a href="${pageContext.request.contextPath}/products.jsp#bzcomposerBilling">BzComposer Billing</a></li>
                            	<li><a href="${pageContext.request.contextPath}/products.jsp#bzcomposereSales">BzComposer eSales</a></li>
                            	<li><a href="${pageContext.request.contextPath}/products.jsp#bzcomposerProfessional">BzComposer Professional</a></li>
								<li><a href="${pageContext.request.contextPath}/products.jsp#bzcompserStandardShareware">BzComposer Standard Shareware</a></li>
							</ul>
						</li>
						<%-- <li><a href="${pageContext.request.contextPath}/partners.jsp">Partners</a></li> --%>
						<li><a href="${pageContext.request.contextPath}/contactUs.jsp"><bean:message key="BzComposer.index.contact"/></a></li>
					</ul>
				</div>
				</nav>
			</div>
		</div>
	<!-- header / end--> 
	</header>