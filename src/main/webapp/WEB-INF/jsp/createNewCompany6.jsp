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
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->

<!-- For dialog -->
<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />

<title><spring:message code="BzComposer.addnewcompanystep6title" /></title>
</head>
<body>
	<form action="CompanyNew?tabid=createNewCompany7" method="post" id="newcompany6">
	<div class="bca_createnewcompanyimgleft">
		<img alt="" src="images/newCompany1.png" height="701px">
	</div>

	<div class="bca_createnewcompanyright" style="background-color: #fff;">
	<div class="bca_createnewcompanyright_title">
		<h3><spring:message code="BzComposer.addnewcompanystep6title" /></h3>
	</div>
	
	<!-- dialog box that used in this page -->
	<div id="quitMessage" style="display:none;font-size:1em;">
		<p><spring:message code="bca.quitmessage"/></p>
	</div>
	<div id="errorOccurred" style="display:none;font-size:1em;">
		<p><spring:message code="BzComposer.common.erroroccurred"/></p>
	</div>

	<div class="createNewCoupa">
		<div class="clearfix">
			<div class="createNewCoupaLeft">
				<div class="fieldset">
					<h6>Customer List</h6>
					<div class="fieldset">
						<h6>Sort By</h6>
						<div class="radio">
							<label> <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1"> Name </label>
						</div>
						<div class="radio">
							<label> <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2"> Company</label>
						</div>
					</div>
					<div class="bca_companylist">
						<!-- <select multiple="multiple" id="customerlist" onclick="getCompanyname();">
						</select> -->
						<select path="a" multiple="multiple" id="customerlist" onclick="getCompanyname()">
						</select>
					</div>
				</div>
			</div>
			<div class="content-tabs createNewCoupaContant " id="tabs">
			    <div id="tabs" class="mb-3">
				<ul>
					<li class="tab"><a href="#tabs-1">General</a></li>
					<li class="tab"><a href="#tabs-2">Sales/Account</a></li>
					<li class="tab"><a href="#tabs-3">Billing Address</a></li>
					<li class="tab"><a href="#tabs-4">Shipping Address</a></li>
					<li class="tab"><a href="#tabs-5">Memo</a></li>
				</ul>
				<!-- <div class="tabContant"> -->
					<div id="tabs-2" class="pl-2 pr-2 pt-3 pb-1">
							<div class="fieldset">
								<h6>Default Settings</h6>
								   <div class="form-group row">
									<label  class="col-sm-2 control-label">Term:</label>
									 <div class="col-sm-10">
								  <%-- <select id="term"><c:forEach items="${vtermName}" var="curObject"><option value="${curObject.termId}">${curObject.vTerm_Name}</option></c:forEach></select> --%>
									<select path="iSalesTerm" id="term">
											<c:if test="${not empty CompanyInfoForm.vListTerm_Name}">
												<c:forEach items="${CompanyInfoForm.vListTerm_Name}" var="curObject" >
												<%-- <li id='<bean:write name='objList' path="termId"/>'><bean:write name='objList' path="vTerm_Name"/></li> --%>
													<option value="${curObject.vTerm_ID}">
													      ${curObject.vTerm_Name}
													</option>
												</c:forEach>
											 </c:if>
										</select>
									</div>
								</div>
								<div class="form-group row">
									<label  class="col-sm-2 control-label">Sales Rep:</label>
									<div class="col-sm-10">
										<%-- <select id="salesrep"><c:forEach items="${addSalesRep}" var="objList" ><option value="${curObject.vSalesRep_ID}">${curObject.vSalesRep_Name}</option></c:forEach></select> --%>
										<select path="iSalesRep" id="salesrep">
											<c:if test="${not empty CompanyInfoForm.vListSalesRepName}" >
													<c:forEach items="${CompanyInfoForm.vListSalesRepName}" var="curObject" >
													<%-- <li id='<bean:write name='objList' path="vSalesRep_ID"/>'><bean:write name='objList' path="vSalesRep_Name"/></li> --%>
													<option value="${curObject.vSalesRep_ID}">
													   ${curObject.vSalesRep_Name}
													</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>

								<div class="form-group row">
									<label  class="col-sm-2 control-label">Received Method:</label>
									<div class="col-sm-10">
										<%-- <select id="receivedmethod"><c:forEach items="${initPaymentType}" var="objList" ><option value="<bean:write name='objList' path="commonid"/>"><bean:write name='objList' path="name"/></option></c:forEach></select> --%>
										<select path="iSalesPayMethod" id="receivedmethod">
											<c:if test="${not empty CompanyInfoForm.vListReceivedType}">
												<c:forEach items="${CompanyInfoForm.vListReceivedType}" var="curObject">
												<%-- <li id='<bean:write name='objList' path="commonid"/>'><bean:write name='objList' path="name"/></li> --%>
												<option value="${curObject.commonid}">
												   ${curObject.name}
												</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>
								<div class="form-group row">
									<label  class="col-sm-2 control-label">Ship Carrier:</label>
									<div class="col-sm-10">
										<%-- <select id="shipcarrier"><c:forEach items="${shipCarrierType}" var="objList" ><option value="<bean:write name='objList' path="commonid"/>"><bean:write name='objList' path="name"/></option></c:forEach></select> --%>
										<select path="iSalesShipMethod" id="shipcarrier">
											<c:if test="${not empty CompanyInfoForm.vShipCarrier}" >
												<c:forEach items="${CompanyInfoForm.vShipCarrier}" var="curObject" >
												<%-- <li id='<bean:write name='objList' path="commonid"/>'><bean:write name='objList' path="name"/></li> --%>
												<option value="${curObject.vShipCarrier_ID}">
												   ${curObject.vShipCarrier_Name}
												 </option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>
							</div>
							<div class="fieldset">
								<h6>Credit Card information</h6>
								<div class="form-group row">
									<label  class="col-sm-2 control-label">Card Type:</label>
									<div class="col-sm-10">
										<%-- <select id="cardtype">
										<c:forEach items="${initCCType}" var="objList" ><option value='<bean:write name='objList' path="commonid"/>'><bean:write name='objList' path="name"/></option></c:forEach></select> --%>
										<select path="iSalesCardType" id="cardtype">
											<c:if test="${not empty CompanyInfoForm.vCCType_Name}" >
												<c:forEach items="${CompanyInfoForm.vCCType_Name}" var="curObject" >
												<%-- <li id='<bean:write name='objList' path="commonid"/>'><bean:write name='objList' path="name"/></li> --%>
												<option value="${curObject.commonid}">
												 ${curObject.name}
												</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>

								<div class="form-group row">
									<label  class="col-sm-2 control-label">Card
										Holder Name:</label>
									<div class="col-sm-10">
										<input type="text" name="sSalesCardName" id="cardholdername"/>
									</div>
								</div>
								<div class="form-group row">
									<label  class="col-sm-2 control-label">Card Number:</label>
									<div class="col-sm-10">
										<input type="text" name="sSalesCardNumber" id="cardnumber">
									</div>
								</div>
								<div class="form-group row">
									<label  class="col-sm-2 control-label">Exp. Date:</label>
									<div class="col-sm-10">
									   <div class="form-inline">
									      <div class="form-group">
												<input type="text" name="iSalesCardExpMonth" id="iSalesCardExpMonth" value=01 >
												<input type="text" name="iSalesCardExpYear" id="iSalesCardExpYear" value=01>
											</div>
										</div>
									</div>
								</div>
								</div>
								
								
	<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------- -->
					<div class="fieldset">
						<h6>Account Transfer Information</h6>
						<div class="form-group row">
							<label  class="col-sm-2 control-label">Unpaid Balance:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="unpaidbalance" name="unpaidbalance" placeholder="0.0">
							</div>
						</div>
						<div class="form-group row">
							<label  class="col-sm-2 control-label">Existing Credit:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="existingcredit" name="existingcredit" placeholder="0.0">
							</div>
						</div>
						<div class="form-group row">
								<label  class="col-sm-2 control-label">Start Date:</label>
							<div class="col-sm-10">
								<input type="date" class="form-control" id="transferstartdate"	placeholder="">
							</div>
						</div>
					</div>			
				</div>
				<div id="tabs-1" class="pl-2 pr-2 pt-3 pb-1">
					<div class="fieldset">
						<h6>Contact Information</h6>
						<div class="form-group">
							<label >Company Name:</label>
							<input type="text" id="companyname" name="sGeneralCompanyName"	class="form-control" required="required">
						</div>
						<div class="form-group">
							<label >First Name:</label> <input type="text" name="sGeneralFirstName" class="form-control" id="fnm" required="required">
						</div>
						<div class="form-group">
							<label >Last Name:</label> <input type="text" name="sGeneralLastName" class="form-control" id="lnm" required="required">
						</div>
						<div class="form-group">
							<label >Address1*:</label> <input type="text" name="sGeneralAddress1" class="form-control" id="address1" required="required">
						</div>
						<div class="form-group">
							<label >Address2:</label> <input type="text" name="sGeneralAddress2" class="form-control" id="address2">
						</div>
						<div class="form-group">
							<label >City*:</label> <input type="text" name="sGeneralCity" class="form-control" id="city" required="required">
						</div>
						<div class="form-group">
							<label >State*:</label>
							<!-- <select class="form-control" id="state"> -->
							<%-- <c:forEach items="${acListofStates}" var="objList" > <option value='<bean:write name="objList" path="stateId" />'><bean:write name="objList" path="stateName" /></option></c:forEach> --%>
							<select path="iGeneralState" id="state">
							<c:if test="${not empty CompanyInfoForm.listOfStates}" >
								<c:forEach items="${CompanyInfoForm.listOfStates}" var="curObject">
									<option value="${curObject.stateCode}">
									    ${curObject.stateName}
									</option>
								</c:forEach>
							</c:if>
							</select>
							<!-- </select> -->
						</div>
						<div class="form-group">
							<label >Province:</label> <input type="text" name="sGeneralProvince" class="form-control" id="province">
						</div>
						<div class="form-group">
							<label >Zip Code*:</label> <input type="text" name="sGeneralZip" class="form-control" id="zipcode" required="required" onkeypress="return isNumber(event)">
						</div>
						<div class="form-group">
							<label >Country*:</label>
							<%-- <select class="form-control" id="country"> <c:forEach items="${acListofCountries}" var="objList"><option value="${curObject.countryId}"> ${curObject.country} </option> </c:forEach></select> --%>
							<select path="iGeneralCountry" id="country">
							  <c:if test="${not empty CompanyInfoForm.listOfCountries}">
								<c:forEach items="${CompanyInfoForm.listOfCountries}" var="curObject" >
									<option value="${curObject.countryId}">
									${curObject.country}
									</option>
								</c:forEach>
							</c:if>
							</select>
						</div>
						<div class="form-group">
							<label >Phone:</label> <input type="text" name="sGeneralPhone" class="form-control" id="phone">
						</div>
						<div class="form-group">
							<label >Fax:</label> <input type="text" name="sGeneralFax" class="form-control" id="fax">
						</div>
						<div class="form-group">
							<label >Homepage:</label> <input type="text" name="sGeneralHomepage" class="form-control" id="homepage">
						</div>
						<div class="form-group">
							<label >Category*:</label>
							 <%-- <select class="form-control" id="category"><c:forEach items="${initCCategory}" var="objList" ><option value="${curObject.commonid}">${curObject.name}</option></c:forEach></select> --%>
							<select path="iGeneralCategory" id="category">
								<c:if test="${not empty CompanyInfoForm.cCategory_Name}">
									<c:forEach items="${CompanyInfoForm.cCategory_Name}" var="curObject" >
										<option value="${curObject.commonid}">
										${curObject.name}
										</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
						<div class="form-group">
							<label >Email ID*:</label> <input type="text" name="sGeneralEmail" class="form-control" id="email" required="required">
						</div>
						<div class="form-inline">
							<div class="form-group">
								<label >Tax ID*:</label> <input type="text" name="sGeneralTaxID" class="form-control" id="taxid" style="width: 600px;">
							</div>
							<div class="checkbox-inline">
								<!-- <label> <input type="radio" name="optionsRadios2" id="taxable" value="Taxable">Taxable	</label> -->
								<label> <input type="checkbox" id="taxable">Taxable </label>
							</div>
							<div class="checkbox-inline">
								<!-- <label> <input type="radio" name="optionsRadios2" id="isAlsoVendor" value="Is Also Vendor">Is Also Vendor</label> -->
								<label> <input type="checkbox" id="isAlsoVendor">Is Also Vendor</label>
							</div>
						</div>
					</div>
				</div>
				<div id="tabs-3" class="pl-2 pr-2 pt-3 pb-1">
					<div class="fieldset">
						<h6>Billing Address</h6>
						<div class="form-group">
						    <input type="checkbox" id="billing_usedefaultadd" checked="checked">Use Default Address
						</div> 
						 <div>   
							<label >Company</label> <input type="text" name="sBillingAddressCompany" class="form-control" id="billing_companyname">
						</div>
						<div class="form-group">
							<label >First Name:</label> <input type="text" name="sBillingAddressFirstName" class="form-control" id="billing_fnm">
						</div>
						<div class="form-group">
							<label >Last Name:</label> <input type="text" name="sBillingAddressLastName" class="form-control" id="billing_lnm">
						</div>
						<div class="form-group">
							<label >Address1*:</label> <input type="text" name="sBillingAddressAddress1" class="form-control" id="billing_add1">
						</div>
						<div class="form-group">
							<label >Address2:</label> <input type="text" name="sBillingAddressAddress2" class="form-control" id="billing_add2">
						</div>
						<div class="form-group">
							<label >City*:</label> <input type="text" name="sBillingAddressCity" class="form-control" id="billing_city">
						</div>
						<div class="form-group">
							<label >State*:</label>
								<%--  <select class="form-control" id="billing_state"><c:forEach items="${acListofStates}" var="objList" ><option value="${curObject.stateId}">${curObject.stateName}</option></c:forEach></select> --%>
							<select path="iBillingAddressState" id="billing_state">
								<c:if test="${not empty CompanyInfoForm.listOfStates}" >
									<c:forEach items="${CompanyInfoForm.listOfStates}" var="curObject">
										<option value="${curObject.stateCode}">
										   ${curObject.stateName}
										</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
						<div class="form-group">
							<label >Province:</label> <input type="text" name="sBillingAddressProvince" class="form-control" id="billing_province">
						</div>
						<div class="form-group">
							<label >Zip Code*:</label> <input type="text" name="sBillingAddressZip" class="form-control" id="billing_zipcode" onkeypress="return isNumber(event)">
						</div>
						<div class="form-group">
							<label >Country*:</label> 
								<%-- <select class="form-control" id="billing_country"><c:forEach items="${acListofCountries}" var="objList"><option value="${curObject.countryId}">${curObject.country}</option></c:forEach></select> --%>
							<select path="iBillingAddressCountry" id="billing_country">
								<c:if test="${not empty CompanyInfoForm.listOfCountries}">
									<c:forEach items="${CompanyInfoForm.listOfCountries}" var="curObject">
										<option value="${curObject.countryId}">
										   ${curObject.country}
										</option>
									</c:forEach>
								</c:if>
							</select>
						</div>			
					</div>
				</div>
				<div id="tabs-4" class="pl-2 pr-2 pt-3 pb-1">
					<div class="fieldset">
						<h6>Shipping Address</h6>
						<div class="form-group">
						    <input type="checkbox" id="shipping_defaultadd" checked="checked">Use Default Address
						</div> 
						 <div>   
							<label >Company</label> <input type="text" name="sShippingAddressCompany" class="form-control" id="shipping_companyname">
						</div>
						<div class="form-group">
							<label >First Name:</label> <input type="text" name="sShippingAddressFirstName" class="form-control" id="shipping_fnm">
						</div>
						<div class="form-group">
							<label >Last Name:</label> <input type="text" name="sShippingAddressLastName" class="form-control" id="shipping_lnm">
						</div>
						<div class="form-group">
							<label >Address1*:</label> <input type="text" name="sShippingAddressAddress1" class="form-control" id="shipping_address1">
						</div>
						<div class="form-group">
							<label >Address2:</label> <input type="text" name="sShippingAddressAddress2" class="form-control" id="shipping_address2">
						</div>
						<div class="form-group">
							<label >City*:</label> <input type="text" name="sShippingAddressCity" class="form-control" id="shipping_city">
						</div>
						<div class="form-group">
							<label >State*:</label>
							<%--  <select class="form-control" id="shipping_state"><c:forEach items="${acListofStates}" var="objList" ><option value='<bean:write name="objList" path="stateId" />'><bean:write name="objList" path="stateName" /></option></c:forEach></select> --%>
							<select path="iShippingAddressState" id="shipping_state">
							<c:if test="${not empty CompanyInfoForm.listOfStates}">
								<c:forEach items="${CompanyInfoForm.listOfStates}" var="curObject" >
									<option value="${curObject.stateCode}">
									${curObject.stateName}
									</option>
								</c:forEach>
							</c:if>
							</select>
						</div>
						<div class="form-group">
							<label >Province:</label> <input type="text" name="sShippingAddressProvince" class="form-control" id="shipping_province">
						</div>
						<div class="form-group">
							<label >Zip Code*:</label> <input type="text" name="sShippingAddressZip" class="form-control" id="shipping_zipcode" onkeypress="return isNumber(event)">
						</div>
						<div class="form-group">
							<label >Country*:</label> 
							<%-- <select class="form-control" id="shipping_country"><c:forEach items="${acListofCountries}" var="objList"><option value="${curObject.countryId}">${curObject.country}</option></c:forEach></select> --%>
							<select path="iShippingAddressCountry" id="shipping_country">
							 <c:if test="${not empty CompanyInfoForm.listOfCountries}">
								<c:forEach items="${CompanyInfoForm.listOfCountries}" var="curObject">
									<option value="${curObject.countryId}">
									${curObject.country}
									</option>
								</c:forEach>
							 </c:if>
							</select>
						</div>
					</div>
				</div>
				<div id="tabs-5" class="pl-2 pr-2 pt-3 pb-1">
					<div class="fieldset">
						<h6>Memo</h6>
						<div class="form-group">
							<textarea rows="50" cols="100%" id="memo" name=""></textarea>
						</div> 
					</div>
				</div>
			<!-- </div> -->
			</div>
		</div>
<!-- -----------------------------------------------------------Common----------------------------------------------------------------------------------------- -->
		<div class="createNewCoupaRight">
			<div class="fieldset">
				<h6>Customer Group</h6>
				<div class="radio">
					<label> <input type="radio" name="customerGroupID" id="customerGroupID" value="0" checked> Dealers
					</label>
				</div>
				<div class="radio">
					<label> <input type="radio" name="customerGroupID" id="customerGroupID" value="1"> Local Customer
					</label>
				</div>
				<div class="radio">
					<label> <input type="radio" name="customerGroupID" id="customerGroupID" value="option3"> eSales Customer
					</label>
				</div>
			<%-- 	<c:forEach items="${CompanyInfoForm.vListCustomerGroup}" var="objList1"><div class="radio"><label><html:radio path="customerGroupID" value="<bean:write name='objList1' path='customerGroupID' />"><bean:write name="objList1" path="country"/></radio></label></div></c:forEach> --%>
			</div>
			<br/>
			<div style="float: left; margin-top: 20px;">
				<ul style="margin: 0px; padding: 0px;">
					<li><a class="formbutton" onclick="addnewrecord();">
						<spring:message code="BzComposer.global.new"/>
					</a></li>
					<br/><br/>
					<li><a class="formbutton" onclick=" return addgeneraldetail();" id="addcompanybtn">
						<spring:message code="BzComposer.global.add"/>
					</a></li>
					<br/><br/>
					<li><a class="formbutton" onclick="removeSelectedCompany();">
						<spring:message code="BzComposer.global.delete"/>
					</a></li>
				</ul>
			</div>
		</div>
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
				<a class="formbutton" onclick="finish();">
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
$(function() {
	$("#tabs").tabs();
});
</script>
<script type="text/javascript">
function submitform()
{
	debugger;
	if(!confirm("Do you want to save changes?"))
	{
		var expmonth = document.getElementById("iSalesCardExpMonth").value;
		var expyear = document.getElementById("iSalesCardExpYear").value; 

	 document.forms['newcompany6'].action = "CompanyNew?tabid=createNewCompany7";
	 document.forms['newcompany6'].submit();
	}
	else{
		debugger;
		addgeneraldetail();

		var json = JSON.stringify(objGlobalForGeneral);
		 $.ajax({

			type : "POST",
			url : "CompanyNew?tabid=addCustomer",
			data : "json="+json ,
		    success : function(data) {
				document.forms['newcompany6'].action = "CompanyNew?tabid=createNewCompany7";
				document.forms['newcompany6'].submit();
			},
			 error : function(data) {
				 $("#errorOccurred").dialog({
				    	resizable: false,
				        height: 200,
				        width: 500,
				        modal: true,
				        buttons: {
				            "Ok": function () {
				                $(this).dialog("close");
				            }
				        }
				    });
					return false;
			}
		});
	}
}
function finish()
{
	if(objGlobalForGeneral.length > 0)
	{
			var json = JSON.stringify(objGlobalForGeneral);
			 $.ajax({

				type : "POST",
				url : "CompanyNew?tabid=addCustomer",
				data : "json=" +json ,
			    success : function(data) {
					document.forms[0].action = "CompanyNew?tabid=finish3";
					document.forms[0].submit();
				},
				 error : function(data) {
					 $("#errorOccurred").dialog({
				    	resizable: false,
				        height: 200,
				        width: 500,
				        modal: true,
				        buttons: {
				            "Ok": function () {
				                $(this).dialog("close");
				            }
				        }
				    });
					return false;
				}
			});
	}
	else{
		document.forms[0].action = "CompanyNew?tabid=finish3";
		document.forms[0].submit();
	}
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
                window.history.go(-6);
            },
            "<spring:message code='bca.quitmessage.no'/>": function () {
                $("#quitMessage").dialog("close");
                return false;
            }
        }
    });
}
</script>
<script type="text/javascript">
var objGlobalForGeneral = [];
var i = 0;
var result;
var numbers = /^[0-9]+$/;
var emailRegex = /^([a-zA-Z_.+-])+\@(([a-zA-Z-])+\.)+([a-zA-Z])+$/;
var a;var fnm;var address1;var zipcode;var address2;
var province;var city;var state;var zipcode;var country;
var phone;var fax;var homepage;var category;var email;var taxid;
var term;var salesrep;var receivedmethod;var shipcarrier;var cardtype;
var cardholdername;var cardnumber;var unpaidbalance;var existingcredit;
var billing_companyname;var billing_fnm;var billing_lnm;var billing_add1;
var billing_add2;var billing_city;var billing_state;var billing_province;
var billing_zipcode;var billing_country;var bBillingAddressUseDefault;var shipping_companyname;
var shipping_fnm;var shipping_lnm;var shipping_address1;var shipping_address2;var shipping_city;
var shipping_state;var shipping_province;var shipping_zipcode;var shipping_country;
var bShippingAddressUseDefault;var memo;var isAlsoVendor;var taxable;var groupID;

function addgeneraldetail()
{
	if($("#addcompanybtn").text() == 'Edit')
	{
		var a = document.getElementById("companyname").value;
		var fnm = document.getElementById("fnm").value;
		var lnm = document.getElementById("lnm").value;
		var address1 = document.getElementById("address1").value;
		var address2 = document.getElementById("address2").value;
		var city = document.getElementById("city").value;
		var state = document.getElementById("state").value;
		var province = document.getElementById("province").value;
		var zipcode = document.getElementById("zipcode").value;
		var country = document.getElementById("country").value;
		var phone = document.getElementById("phone").value;
		var fax = document.getElementById("fax").value;
		var homepage = document.getElementById("homepage").value;
		var category = document.getElementById("category").value;
		var email = document.getElementById("email").value;
		var taxid = document.getElementById("taxid").value;

		var conceptName = $('#customerlist :selected').text();
		objGlobalForGeneral.splice(objGlobalForGeneral.indexOf(conceptName), 1);
		objGlobalForGeneral.push({
			name:a,
			firstname:fnm,
			lastname:lnm,
			address1:address1,
			address2:address2,
			city:city,
			state:state,
			province:province,
			zipcode:zipcode,
			country:country,
			phone:phone,
			fax:fax,
			homepage:homepage,
			email:email,
			category:category,
			taxid:taxid
		});
		var eventTypeName = $("#customerlist option:selected");
		eventTypeName.text(a);
		$('#myform')[0].reset();
		$("#addcompanybtn").text("Add");
	}
	else
	{
		groupID = document.getElementById("customerGroupID").value == ""?null:document.getElementById("customerGroupID").value;
		taxable = $("#taxable").prop("checked");
		isAlsoVendor = $("#isAlsoVendor").prop("checked");
		a = document.getElementById("companyname").value == ""?null:document.getElementById("companyname").value;
		fnm = document.getElementById("fnm").value == ""?null:document.getElementById("fnm").value;
		lnm = document.getElementById("lnm").value == ""?null:document.getElementById("lnm").value;
		address1 = document.getElementById("address1").value == ""?null:document.getElementById("address1").value;
		address2 = document.getElementById("address2").value == ""?null:document.getElementById("address2").value;
		city = document.getElementById("city").value == ""?null:document.getElementById("city").value;
		state = $('#state').find(":selected").val();
		province = document.getElementById("province").value == ""?null:document.getElementById("province").value;
		zipcode = document.getElementById("zipcode").value == ""?null:document.getElementById("zipcode").value;
		country = $('#country').find(":selected").val();
		phone = document.getElementById("phone").value == ""?null:document.getElementById("phone").value;
		fax = document.getElementById("fax").value == ""?null:document.getElementById("fax").value;
		homepage = document.getElementById("homepage").value == ""?null:document.getElementById("homepage").value;
		category = $('#category').find(":selected").val();
		email = document.getElementById("email").value == ""?null:document.getElementById("email").value;
		taxid = document.getElementById("taxid").value == ""?null:document.getElementById("taxid").value;

		/*sales/account*/
		term = document.getElementById("term").value == ""?null:document.getElementById("term").value;
		salesrep = document.getElementById("salesrep").value == ""?null:document.getElementById("salesrep").value;
		receivedmethod = document.getElementById("receivedmethod").value == ""?null:document.getElementById("receivedmethod").value;
		shipcarrier = document.getElementById("shipcarrier").value == ""?null:document.getElementById("shipcarrier").value;
		cardtype = document.getElementById("cardtype").value == ""?null:document.getElementById("cardtype").value;
		cardholdername = document.getElementById("cardholdername").value == ""?null:document.getElementById("cardholdername").value;
		cardnumber = document.getElementById("cardnumber").value == ""?null:document.getElementById("cardnumber").value;
		var expmonth = document.getElementById("iSalesCardExpMonth").value;
		var expyear = document.getElementById("iSalesCardExpYear").value;
		unpaidbalance = document.getElementById("unpaidbalance").value == ""?null:document.getElementById("unpaidbalance").value;
		existingcredit = document.getElementById("existingcredit").value == ""?null:document.getElementById("existingcredit").value;
		/* var transferstartdate = document.getElementById("transferstartdate").value; */

		/*billing tab*/
		billing_companyname = document.getElementById("billing_companyname").value == ""?null:document.getElementById("billing_companyname").value;
		billing_fnm = document.getElementById("billing_fnm").value == ""?null:document.getElementById("billing_fnm").value;
		billing_lnm = document.getElementById("billing_lnm").value == ""?null:document.getElementById("billing_lnm").value;
		billing_add1 = document.getElementById("billing_add1").value == ""?null:document.getElementById("billing_add1").value;
		billing_add2 = document.getElementById("billing_add2").value == ""?null:document.getElementById("billing_add2").value;
		billing_city = document.getElementById("billing_city").value == ""?null:document.getElementById("billing_city").value;
		billing_state = $('#billing_state').find(":selected").val();
		billing_province = document.getElementById("billing_province").value == ""?null:document.getElementById("billing_province").value;
		billing_zipcode = document.getElementById("billing_zipcode").value == ""?null:document.getElementById("billing_zipcode").value;
		billing_country = $('#billing_country').find(":selected").val();
		bBillingAddressUseDefault = $("#billing_usedefaultadd").prop("checked");

		/*shipping tab*/
		shipping_companyname = document.getElementById("billing_companyname").value == ""?null:document.getElementById("billing_companyname").value;
		shipping_fnm = document.getElementById("billing_fnm").value == ""?null:document.getElementById("billing_fnm").value;
		shipping_lnm = document.getElementById("billing_lnm").value == ""?null:document.getElementById("billing_lnm").value;
		shipping_address1 = document.getElementById("billing_add1").value == ""?null:document.getElementById("billing_add1").value;
		shipping_address2 = document.getElementById("billing_add2").value == ""?null:document.getElementById("billing_add2").value;
		shipping_city = document.getElementById("billing_city").value == ""?null:document.getElementById("billing_city").value;
		shipping_state = $('#shipping_state').find(":selected").val();
		shipping_province = document.getElementById("billing_province").value == ""?null:document.getElementById("billing_province").value;
		shipping_zipcode = document.getElementById("billing_zipcode").value == ""?null:document.getElementById("billing_zipcode").value;
		shipping_country = $('#shipping_country').find(":selected").val();
		bShippingAddressUseDefault = $("#shipping_defaultadd").prop("checked");

		/*memo*/
		memo = document.getElementById("memo").value == ""?null:document.getElementById("memo").value;

		if(a==null)
		{
			alert("<bean:message key='BzComposer.NewCustomer.Name.Validation'/>");
			CompanyInfoForm.sGeneralCompanyName.focus();
			return false;
		}
		if(fnm == null)
		{
			alert("<bean:message key='BzComposer.NewCustomer.FirstName.Validation'/>");
			CompanyInfoForm.sGeneralFirstName.focus();
			return false;
		}
		if(lnm == null)
		{
			alert("<bean:message key='BzComposer.NewCustomer.LastName.Validation'/>");
			CompanyInfoForm.sGeneralLastName.focus();
			return false;
		}
		if(address1 == null)
		{
			alert("<bean:message key='BzComposer.companyinfo.enterAddress'/>");
			CompanyInfoForm.sGeneralAddress1.focus();
			return false;
		}
		if(zipcode == null)
		{
			alert("<bean:message key='BzComposer.NewCustomer.ZipCode.Validation'/>");
			CompanyInfoForm.sGeneralZip.focus();
			return false;
		}
		if(email == null)
		{
			alert("<bean:message key='BzComposer.companyinfo.enteremailaddress'/>");
			CompanyInfoForm.sGeneralEmail.focus();
			return false;
		}
		if(!zipcode.match(numbers))
		{
			alert("<bean:message key='BzComposer.common.onlyNumbersAllowedInZip'/>");
			CompanyInfoForm.sGeneralZip.focus();
			return false;
		}
		if(!email.match(emailRegex))
		{
			alert("<bean:message key='BzComposer.common.enterValidEmail'/>");
			CompanyInfoForm.sGeneralEmail.focus();
			return false;
		}
		objGlobalForGeneral.push({
			sGeneralCompanyName:a,
			sGeneralFirstName:fnm,
			sGeneralLastName:lnm,
			sGeneralAddress1:address1,
			sGeneralAddress2:address2,
			sGeneralCity:city,
			iGeneralState:state,
			sGeneralProvince:province,
			sGeneralZip:zipcode,
			iGeneralCountry:country,
			sGeneralPhone:phone,
			sGeneralFax:fax,
			sGeneralHomepage:homepage,
			sGeneralEmail:email,
			iGeneralCategory:category,
			sGeneralTaxID:taxid,
			bGeneralTaxable:taxable,
			customerGroupID:groupID,
			bIsAlsoVendor:isAlsoVendor,
			iSalesTerm:term,                          //starting sales
			iSalesRep:salesrep,
			iSalesPayMethod:receivedmethod,
			iSalesShipMethod:shipcarrier,
		    iSalesCardType:cardtype,
			sSalesCardName:cardholdername,
			sSalesCardNumber:cardnumber,
			SalesCardExpMonth:expmonth,
			iSalesCardExpYear:expyear,
		 	dSalesUnpaidBalance:unpaidbalance,
			dSalesExistingCredit:existingcredit,
			/* iSalesStartDay:
			iSalesStartMonth:
			iSalesStartYear:	 */
		    bBillingAddressUseDefault:bBillingAddressUseDefault,              //start billing address
			sBillingAddressCompany:billing_companyname,
			sBillingAddressFirstName:billing_fnm,
			sBillingAddressLastName:billing_lnm,
			sBillingAddressAddress1:billing_add1,
			sBillingAddressAddress2:billing_add2,
			sBillingAddressCity:billing_city,
			iBillingAddressState:billing_state,
			sBillingAddressProvince:billing_province,
			sBillingAddressZip:billing_zipcode,
			iBillingAddressCountry:billing_country,
			bShippingAddressUseDefault:bShippingAddressUseDefault,                                      //start shipping address
			sShippingAddressCompany:shipping_companyname,
			sShippingAddressFirstName:shipping_fnm,
			sShippingAddressLastName:shipping_lnm,
			sShippingAddressAddress1:shipping_address1,
			sShippingAddressAddress2:shipping_address2,
			sShippingAddressCity:shipping_city,
			iShippingAddressState:shipping_state,
			sShippingAddressProvince:shipping_province,
			sShippingAddressZip:shipping_zipcode,
			iShippingAddressCountry:shipping_country,
			sMemoText:memo                            //memo start
		});
		$(".bca_companylist select").append(new Option(objGlobalForGeneral[i].sGeneralFirstName,objGlobalForGeneral[i].sGeneralCompanyName));
		$('#newcompany6')[0].reset();
		i++;
	}
}	
function getCompanyname()
{
	var conceptName = $('#customerlist :selected').text();
	result = objGlobalForGeneral.find(obj =>  {
   		return obj.firstname === conceptName
 	});
	
	document.getElementById("companyname").value=result.sGeneralCompanyName;
	document.getElementById("fnm").value=result.sGeneralFirstName;
	document.getElementById("lnm").value=result.sGeneralLastName;
	document.getElementById("address1").value=result.sGeneralAddress1;
	document.getElementById("address2").value=result.sGeneralAddress2;
	document.getElementById("city").value=result.sGeneralCity;
	/*document.getElementById("state").value=result.iGeneralState; */
	//$('#state option:selected').html(result.state);
	document.getElementById("province").value=result.sGeneralProvince;
	document.getElementById("zipcode").value=result.sGeneralZip;
	/*document.getElementById("country").value=result.iGeneralCountry; */
	document.getElementById("phone").value=result.sGeneralPhone;
	document.getElementById("fax").value=result.sGeneralFax;
	document.getElementById("homepage").value=result.sGeneralHomepage;
 	/*document.getElementById("category").value=result.iGeneralCategory; */
	document.getElementById("email").value=result.sGeneralEmail;
	document.getElementById("taxid").value=result.sGeneralTaxID;
	  
	/*sales/account*/
	document.getElementById("term").value=result.term;
	document.getElementById("salesrep").value=result.salesrep;
	document.getElementById("receivedmethod").value=result.receivedmethod;
	document.getElementById("shipcarrier").value=result.shipcarrier;
	document.getElementById("cardtype").value=result.cardtype;
	document.getElementById("cardholdername").value=result.cardholdername;
	document.getElementById("cardnumber").value=result.cardnumber;
	/*  document.getElementById("expmonth").value=result.expmonth; */
	/*  document.getElementById("expyear").value=result.expyear; */
	document.getElementById("unpaidbalance").value=result.unpaidbalance;
	document.getElementById("existingcredit").value=result.existingcredit;
	/* document.getElementById("transferstartdate").value=result.transferstartdate; */	 
	  
	/*billing tab*/
	document.getElementById("billing_companyname").value=result.billing_companyname;
	document.getElementById("billing_fnm").value=result.billing_fnm;
	document.getElementById("billing_lnm").value=result.billing_lnm;
	document.getElementById("billing_add1").value=result.billing_add1;
	document.getElementById("billing_add2").value=result.billing_add2;
	document.getElementById("billing_city").value=result.billing_city;
	document.getElementById("billing_state").value=result.billing_state;
	document.getElementById("billing_province").value=result.billing_province;
	document.getElementById("billing_zipcode").value=result.billing_zipcode;
	document.getElementById("billing_country").value=result.billing_country; 
	  
	/*shipping tab*/
	document.getElementById("shipping_companyname").value=result.shipping_companyname;
	document.getElementById("shipping_fnm").value=result.shipping_fnm;
	document.getElementById("shipping_lnm").value=result.shipping_lnm;
	document.getElementById("shipping_address1").value=result.shipping_address1;
	document.getElementById("shipping_address2").value=result.shipping_address2;
	document.getElementById("shipping_city").value=result.shipping_city;
	document.getElementById("shipping_state").value=result.shipping_state;
	document.getElementById("shipping_province").value=result.shipping_province;
	document.getElementById("shipping_zipcode").value=result.shipping_zipcode;
	document.getElementById("shipping_country").value=result.shipping_country; 
	  
	/*memo*/
	document.getElementById("memo").value=result.memo; 
		  
	$("#addcompanybtn").text("Edit");
}
function removeSelectedCompany()
{
	$('#customerlist option:selected').remove();	
}
function addnewrecord()
{
	$('#myform')[0].reset();
	$("#addcompanybtn").text("Add");
}
function sortlist() {
	var lb = document.getElementById('mylist');
	arrTexts = new Array();
	for(i=0; i<lb.length; i++)  {
	  arrTexts[i] = lb.options[i].text;
	}
	arrTexts.sort();
	for(i=0; i<lb.length; i++)  {
	  lb.options[i].text = arrTexts[i];
	  lb.options[i].value = arrTexts[i];
	}
}
$("#billing_usedefaultadd").click(function() {
    if(this.checked)
	{
       	var a = document.getElementById("companyname").value;
       	var fnm = document.getElementById("fnm").value;
       	var lnm = document.getElementById("lnm").value;
       	var address1 = document.getElementById("address1").value; 
       	var address2 = document.getElementById("address2").value;
       	var city = document.getElementById("city").value;
       	var state = document.getElementById("state").value;
       	var province = document.getElementById("province").value;
       	var zipcode = document.getElementById("zipcode").value;
       	var country = document.getElementById("country").value;
        	
       	document.getElementById("billing_companyname").value=a;
       	document.getElementById("billing_fnm").value=fnm;
       	document.getElementById("billing_lnm").value=lnm;
       	document.getElementById("billing_add1").value=address1;
       	document.getElementById("billing_add2").value=address2;
       	document.getElementById("billing_city").value=city;
       	document.getElementById("billing_state").value=state;
       	document.getElementById("billing_province").value=province;
       	document.getElementById("billing_zipcode").value=zipcode;
       	document.getElementById("billing_country").value=country;
	}
	else
	{
        document.getElementById("billing_companyname").value="";
       	document.getElementById("billing_fnm").value="";
       	document.getElementById("billing_lnm").value="";
       	document.getElementById("billing_add1").value="";
       	document.getElementById("billing_add2").value="";
       	document.getElementById("billing_city").value="";
       	document.getElementById("billing_state").value="";
       	document.getElementById("billing_province").value="";
       	document.getElementById("billing_zipcode").value="";
       	document.getElementById("billing_country").value="";
	}        
});
$("#shipping_defaultadd").click(function() {
    if (this.checked)
    {
    	var a = document.getElementById("companyname").value;
    	var fnm = document.getElementById("fnm").value;
    	var lnm = document.getElementById("lnm").value;
    	var address1 = document.getElementById("address1").value; 
    	var address2 = document.getElementById("address2").value;
    	var city = document.getElementById("city").value;
    	var state = document.getElementById("state").value;
    	var province = document.getElementById("province").value;
    	var zipcode = document.getElementById("zipcode").value;
    	var country = document.getElementById("country").value;
    	
    	document.getElementById("shipping_companyname").value=a;
    	document.getElementById("shipping_fnm").value=fnm;
    	document.getElementById("shipping_lnm").value=lnm;
    	document.getElementById("shipping_address1").value=address1;
    	document.getElementById("shipping_address2").value=address2;
    	document.getElementById("shipping_city").value=city;
    	document.getElementById("shipping_state").value=state;
    	document.getElementById("shipping_province").value=province;
    	document.getElementById("shipping_zipcode").value=zipcode;
    	document.getElementById("shipping_country").value=country;
    }
    else
    {
    	document.getElementById("shipping_companyname").value="";
    	document.getElementById("shipping_fnm").value="";
    	document.getElementById("shipping_lnm").value="";
    	document.getElementById("shipping_address1").value="";
    	document.getElementById("shipping_address2").value="";
    	document.getElementById("shipping_city").value="";
    	document.getElementById("shipping_state").value="";
    	document.getElementById("shipping_province").value="";
    	document.getElementById("shipping_zipcode").value="";
    	document.getElementById("shipping_country").value="";	
    }
});
$("#optionsRadios1").click(function() {
	 var $r = $("#customerlist option");
	    $r.sort(function(a, b) {
	        if (a.text < b.text) return -1;
	        if (a.text == b.text) return 0;
	        return 1;
	    });
	    $($r).remove();
	    $("#customerlist").append($($r));
});
$("#optionsRadios2").click(function() {
	 var $r = $("#customerlist option");
	    $r.sort(function(a, b) {
	        if (a.value < b.value) return -1;
	        if (a.value == b.value) return 0;
	        return 1;
	    });
	    $($r).remove();
	    $("#customerlist").html($($r));
});
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
$("#companyname,#fnm,#lnm,#address1,#address2,#city,#state,#province,#zipcode,#country").change(function () {
   var formArray = [$("#companyname").val(), $("#fnm").val(), $("#lnm").val(), $("#address1").val(),$("#address2").val(),$("#city").val(),$("#state").find(":selected").val(),$("#province").val(),
		$("#zipcode").val(),$("#country").find(":selected").val()];
	
    $("#billing_companyname").val(formArray[0]);
    $("#billing_fnm").val(formArray[1]);
    $("#billing_lnm").val(formArray[2]);
    $("#billing_add1").val(formArray[3]);
    $("#billing_add2").val(formArray[4]);
    $("#billing_city").val(formArray[5]);
    $("#billing_state").val(formArray[6]);
    $("#billing_province").val(formArray[7]);
    $("#billing_zipcode").val(formArray[8]);
    $("#billing_country").val(formArray[9]);
    
    $("#shipping_companyname").val(formArray[0]);
    $("#shipping_fnm").val(formArray[1]);
    $("#shipping_lnm").val(formArray[2]);
    $("#shipping_address1").val(formArray[3]);
    $("#shipping_address2").val(formArray[4]);
    $("#shipping_city").val(formArray[5]);
    $("#shipping_state").val(formArray[6]);
    $("#shipping_province").val(formArray[7]);
    $("#shipping_zipcode").val(formArray[8]);
    $("#shipping_country").val(formArray[9]);
	
});
</script>
</body>
</html>