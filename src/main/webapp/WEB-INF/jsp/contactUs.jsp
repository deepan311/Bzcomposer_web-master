<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*"%>
<style>
.error
{
	color: #f00;
    font-size: 1em;
    font-weight: normal;
}
</style>
<script type="text/javascript">
function redirectToLogin()
{
	window.location = "Login?tabid=loginPage";
}
function openRegisterPage()
{
	window.location = "Login?tabid=register";
}
var a, b;
function getRandom() 
{
    var numbers = new Array();

    for(var i=1;i<100;i++) {
        numbers.push(i);
    }
	a = numbers[Math.floor(Math.random() * numbers.length)];
    b = numbers[Math.floor(Math.random() * numbers.length)];
    document.getElementById("firstNumber").innerHTML = a;
    document.getElementById("secondNumber").innerHTML = b;

}
function ValidCaptcha()
{
	var result = document.getElementById("CaptchaInput").value;

    if(result == (parseInt(a) + parseInt(b))) {

        return true;
    } else {

        return false;
    }
}
function check() 
{
    var result = document.getElementById("CaptchaInput").value;

    if(result == (parseInt(a) + parseInt(b))) {

        return true;
    } else {

        return false;
    }
}
function checkform(theform)
{
	debugger
	var theform = document.getElementById('frmContactUs');
	var why = "";
	if(theform.txtName.value == "" || theform.txtName.value == " ")
	{
		debugger
		document.getElementById('errorMessageName').innerHTML="<spring:message code='BzComposer.contactus.formerrormessage'/>";
	}
	if(theform.txtEmail.value == "" || theform.txtEmail.value == " ")
	{

		document.getElementById('errorMessageEmail').innerHTML="<spring:message code='BzComposer.contactus.formerrormessage'/>";
		//event.preventDefault();
	}
	if(theform.comments.value == "" || theform.comments.value == " ")
	{

		document.getElementById('errorMessageComments').innerHTML="<spring:message code='BzComposer.contactus.formerrormessage'/>";
		//event.preventDefault();
	}
	if(theform.CaptchaInput.value == "")
	{
		debugger
		document.getElementById('errorMessageCaptcha').innerHTML = "<spring:message code='BzComposer.contactus.formcaptchacode'/>";
		event.preventDefault();
	}
	else
	{
		if(ValidCaptcha(theform.CaptchaInput.value) == false)
		{
			document.getElementById('errorMessageCaptcha').innerHTML = "<spring:message code='BzComposer.contactus.forminvalidcaptcha'/>";
			event.preventDefault();
		}
		else
		{
			var UserName = theform.txtName.value;
			var email = theform.txtEmail.value;
			var phone = theform.txtPhone.value;
			var subject = theform.subject.value;
			var comments = theform.comments.value;
			
			/* document.getElementById('hiddenName').value = UserName;
			document.getElementById('hiddenEmail').value = email;
			document.getElementById('hiddenPhone').value = phone;
			document.getElementById('hiddenSubject').value = subject;
			document.getElementById('hiddenComments').value = comments; */
			
			document.forms['frmContactUs'].action = "Login?tabid=contactUs&hiddenName="+UserName+"&hiddenEmail="+email+"&hiddenPhone="+phone+"&hiddenSubject="+subject+"&hiddenComments="+comments;
			document.forms['frmContactUs'].submit();
		}
	}
	
	if(why != "")
	{
		return false;
	}
}
function openSampleCompany2()
{
	window.location.href="Login?tabid=selectedCompanyHome&selectedCompanyId=2&companyName=ABC Retails Company";
}

function openSampleCompany3()
{
	window.location.href="Login?tabid=selectedCompanyHome&selectedCompanyId=3&companyName=ABC Wholesale Company";
}

function openSampleCompany4()
{
	window.location.href="Login?tabid=selectedCompanyHome&selectedCompanyId=4&companyName=ABC eSales Company";
}
</script>
<%-- <html:html lang="en"> --%>
<html>
<title><spring:message code="BzComposer.contactustitle"/></title>
<%@ include file="templateHeader.jsp"%>
<body onload="getRandom();">
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
							<li><a href="Login?tabid=selectedCompany&selectedCompanyId=4&companyName=ABC eSales Company"><spring:message code="BzComposer.samplecompaies.eSalecompany"/></a></li>
							<li><a href="Login?tabid=selectedCompany&selectedCompanyId=2&companyName=ABC Retails Company"><spring:message code="BzComposer.samplecompaies.retailcompany"/></a></li>
							<li><a href="Login?tabid=selectedCompany&selectedCompanyId=3&companyName=ABC Wholesale Company"><spring:message code="BzComposer.samplecompaies.wholesalecompany"/></a></li>
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
								<a href="${pageContext.request.contextPath}"> 
									<img src="${pageContext.request.contextPath}/dist/template/images/icons/BzComposerLogo.png" alt="Consulting" />
								</a>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="search-wrap" align="right">
							<div class="input-group" align="right">
								<input type="text" name="searchbox" placeholder="<spring:message code="BzComposer.searchtext"/>" style="width: 241px" />
								<div class="input-group-btn">
									<button class="btn btn-primary"><spring:message code="BzComposer.searchbuttontext"/></button>
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
	<div class="section contact-us bg-grey-light p-t-20 p-b-40" id="ContactForm">
		<div class="container">
			<h1 class="entry-title">
				<spring:message code="BzComposer.contactus.title"/>
				<!-- Contact Us -->
			</h1>
			<p class="contact-us-text-block">
				<spring:message code="BzComposer.contactus.contentone"/>
				<!-- If you have any questions regarding our products or services, 
				please contact us and we will get back to you as soon as possible. -->
			</p>
			<p class="contact-us-text-block">
				<spring:message code="BzComposer.contactus.contenttwo"/>
				<!-- We look forward to hearing from you!. -->
			</p>
			<form id="frmContactUs" name="frmContactUs" onsubmit="checkform(this.form)" action="/Login" method="post">
				<!-- <input type="hidden" id="hiddenName" value=""/>
				<input type="hidden" id="hiddenEmail" value=""/>
				<input type="hidden" id="hiddenPhone" value=""/>
				<input type="hidden" id="hiddenSubject" value=""/>
				<input type="hidden" id="hiddenComments" value=""/> -->
				<div class="row">
					<div class="col-md-6" align="center">
						<label>
							<spring:message code="BzComposer.contactus.formname"/>
							<!-- Name -->
						</label>
						<span class="red">*</span>
					</div>
					<div class="col-md-6" align="center">
						<input type="text" id="txtName" name="txtName" class="form-control" autocomplete="off"/>
						<label id="errorMessageName" class="error"></label>
					</div>
					<br/>
				</div>
				<div class="row">
					<div class="col-md-6" align="center">
						<label>
							<spring:message code="BzComposer.contactus.formemail"/>
							<!-- Email -->
						</label>
						<span class="red">*</span>
					</div>
					<div class="col-md-6" align="center">
						<input type="text" id="txtEmail" name="txtEmail" class="form-control" autocomplete="off"/>
						<label id="errorMessageEmail" class="error"></label>
					</div>
					<br/>
				</div>
				<div class="row">
					<div class="col-md-6" align="center">
						<label>
							<spring:message code="BzComposer.contactus.formphone"/>
							<!-- Phone -->
						</label>
					</div>
					<div class="col-md-6" align="center">
						<input type="text" id="txtPhone" name="txtPhone" class="form-control" maxlength="10" autocomplete="off"/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6" align="center">
						<label>
							<spring:message code="BzComposer.contactus.formsubject"/>
							<!-- Subject -->
						</label>
					</div>
					<div class="col-md-6" align="center">
						<select id="subject" name="subject" class="form-control">
							<option value="About Advertising"><spring:message code="BzComposer.contactus.advertising"/><!-- About Advertising --></option>
							<option value="About Business Listing"><spring:message code="BzComposer.contactus.businesslisting"/><!-- About Business Listing --></option>
							<option value="About Business Information Change"><spring:message code="BzComposer.contactus.businessinfochange"/><!-- About Business Information Change --></option>
							<option value="About Login &amp; Registration Issues"><spring:message code="BzComposer.contactus.loginregisterissues"/><!-- About Login &amp; Registration Issues --></option>
							<option value="About Website Issues"><spring:message code="BzComposer.contactus.websiteissues"/><!-- About Website Issues --></option>
							<option value="About Shopping"><spring:message code="BzComposer.contactus.aboutshopping"/><!-- About Shopping --></option>
							<option value="About Legal Issues"><spring:message code="BzComposer.contactus.legalissues"/><!-- About Legal Issues --></option>
						</select>	
					</div>
				</div>
				<div class="row">
					<div class="col-md-6" align="center">
						<label>
							<spring:message code="BzComposer.contactus.formcomment"/>
							<!-- Comment -->
						</label>
						<span class="red">*</span>
					</div>
					<div class="col-md-6" align="center">
						<textarea id="comments" name="comments" rows="" cols="" class="form-control"
						style="width: 99%;"></textarea>
						<label id="errorMessageComments" class="error"></label>
					</div>
					<br/>
				</div>
				<div class="row">
					<div class="col-md-6">
					</div>
					<div class="col-md-6" align="center">
						<!-- START CAPTCHA -->
						<!-- <div class="capbox">
						<div id="CaptchaDiv"></div>
							<div class="capbox-inner">
							Type the above number:<br> 
							<input type="hidden" id="txtCaptcha"> 
							<input type="text" name="CaptchaInput" id="CaptchaInput" size="15"><br>
						</div>
						</div> -->
						<!-- END CAPTCHA -->
						 <!-- <p> -->
            				<label id="firstNumber"></label>
            				+
            				<label id="secondNumber"></label>
            				=
            				<input type="text" name="CaptchaInput" id="CaptchaInput" autocomplete="off"/>
            				<!-- <input type="button" class="btn btn-primary" value="Check" onclick="check();" /> -->
            				<br/>
            				<label id="errorMessageCaptcha" class="error"></label>
        				<!-- </p> -->
					</div>
		</div>
		<div class="row">
			<div class="col-md-4">
			</div>
			<div class="col-md-4">
				<input type="submit" class="btn btn-primary b_submitbtn" value="<spring:message code='BzComposer.contactus.submit'/>"/>
			</div>
			<div class="col-md-4">
			</div>
		</div>		
	</form>
</div>
	</div>
     <%@ include file="templateFooter.jsp"%>
     <%@ include file="templateScript.jsp"%>
	</body>
</html>