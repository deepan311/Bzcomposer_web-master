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

<title><spring:message code="BzComposer.addnewcompanystep7title" /></title>

</head>
<body>
	<form action="CompanyNew?tabid=createNewCompany8" method="post" class="myform" id="myform">
	
	<div class="bca_createnewcompanyimgleft">
		<img alt="" src="images/newCompany1.png" height="701px">
	</div>
	
	<div class="bca_createnewcompanyright" style="background-color: #fff;">
	
	<div class="bca_createnewcompanyright_title">
		<h3><spring:message code="BzComposer.addnewcompanystep7title" /></h3>
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
					<h6>Vendor List</h6>
					<div class="fieldset">
						<h6>Sort By</h6>

						<div class="radio">
							<label> <input type="radio" name="optionsRadios"
								id="optionsRadios1" value="option1" checked> Name
							</label>
						</div>
						<div class="radio">
							<label> <input type="radio" name="optionsRadios"
								id="optionsRadios2" value="option2"> Company
							</label>
						</div>
					</div>
					<div class="bca_companylist">
						<select multiple="multiple" id="customerlist" onclick="getCompanyname();">
						</select>
					</div>
				</div>
			</div>
			<div class="content-tabs createNewCoupaContant " id="tabs">
			    <div id="tabs" class="mb-3">
				<ul>
					<li class="tab"><a href="#tabs-1">General</a></li>
					<li class="tab"><a href="#tabs-2">Sales/Account</a></li>
				</ul>
				<!-- <div class="tabContant"> -->

					<div id="tabs-2" class="pl-2 pr-2 pt-3 pb-1">
						
							<div class="fieldset">
								<h6>Default Settings</h6>
								<div class="form-group row">
									<label  class="col-sm-2 control-label">Term:</label>
									<div class="col-sm-10">
										<%-- <select id="term"><c:forEach items="${vtermName}" var="objList"><option value="${curObject.termId}">${curObject.vTerm_Name}</option></c:forEach></select> --%>
										<select path="iVSalesTerm" Id="term">
											<c:if test="${not empty CompanyInfoForm.vListTerm_Name}">
												<c:forEach items="${CompanyInfoForm.vListTerm_Name}" var="curObject">
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
									<label  class="col-sm-2 control-label">Sales
										Rep:</label>
									<div class="col-sm-10">
										<%-- <select id="salesrep"><c:forEach items="${addSalesRep}" var="objList" ><option value="<bean:write name='objList' path="vSalesRep_ID"/>"><bean:write name='objList' path="vSalesRep_Name"/></option></c:forEach></select> --%>
										<select path="iVSalesRep" id="salesrep">
											<c:if test="${not empty CompanyInfoForm.vListSalesRepName}">
													<c:forEach items="${CompanyInfoForm.vListSalesRepName}" var="curObject">
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
									<label  class="col-sm-2 control-label">Payment
										Method:</label>
									<div class="col-sm-10">
										<%-- <select id="receivedmethod">
											<c:forEach items="${initPaymentType}" var="curObject">
											<option value="${curObject.commonid}">
											${curObject.name}
											</option>
											</c:forEach>
										</select> --%>
										<select path="iVSalesPayMethod" id="receivedmethod">
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
									<label  class="col-sm-2 control-label">Ship
										Carrier:</label>
									<div class="col-sm-10">
										<%-- <select id="shipcarrier"><c:forEach items="${shipCarrierType}" var="objList"><option value="<bean:write name='objList' path="commonid"/>"><bean:write name='objList' path="name"/></option></c:forEach></select> --%>
										<select path="iVSalesShipMethod" id="shipcarrier">
											<c:if test="${not empty CompanyInfoForm.vShipCarrier}">
												<c:forEach items="${CompanyInfoForm.vShipCarrier}" var="curObject">
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
								<h6>Account Transfer information</h6>


								<div class="form-group row">
									<label  class="col-sm-2 control-label">Unpaid
										Balance:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="unpaidbalance"
											placeholder="0">
									</div>
								</div>

								<div class="form-group row">
									<label  class="col-sm-2 control-label">Existing
										Credit:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="existingcredit"
											placeholder="0">
									</div>
								</div>


								<div class="form-group row">
									<label  class="col-sm-2 control-label">Start
										Date:</label>
									<div class="col-sm-10">
										<input type="date" class="form-control" id="transferstartdate"
											placeholder="">
									</div>
								</div>
								
								</div>
								
								
	<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------- -->
							<div class="fieldset">
						<h6>Memo</h6>
						
							<div class="form-group">
							   <textarea rows="20" cols="100%" id="memo"></textarea>
							</div> 
					</div>		
						
					</div>
			
				<div id="tabs-1" class="pl-2 pr-2 pt-3 pb-1">
					<div class="fieldset">
						<h6>Contact Information</h6>
							<div class="form-group">
								<label>Company Name:</label> <input type="text" id="companyname"
									class="form-control" required="required" name="vGeneralCompanyName">
							</div>
							<div class="form-group">
								<label>First Name:</label> <input type="text"
									class="form-control" id="fnm" required="required" name="vGeneralFirstName">
							</div>
							<div class="form-group">
								<label>Last Name:</label> <input type="text"
									class="form-control" id="lnm" required="required" name="vGeneralLastName">
							</div>
							<div class="form-group">
								<label>Address1*:</label> <input type="text"
									class="form-control" id="address1" required="required" name="vGeneralAddress1">
							</div>
							<div class="form-group">
								<label>Address2:</label> <input type="text"
									class="form-control" id="address2">
							</div>
							<div class="form-group">
								<label>City*:</label> <input type="text"
									class="form-control" id="city" required="required" name="vGeneralCity">
							</div>
							<div class="form-group">
								<label>State*:</label>
								<%-- <select class="form-control" id="state"><c:forEach items="${acListofStates}" var="objList"><option value='<bean:write name="objList" path="stateId" />'><bean:write name="objList" path="stateName" /></option></c:forEach></select> --%>
								<select path="iVGeneralState" id="state">
								<c:if test="${not empty CompanyInfoForm.listOfStates}">
									<c:forEach items="${CompanyInfoForm.listOfStates}" var="curObject">
										<option value="${curObject.stateCode}">
										${curObject.stateCode}
										</option>
									</c:forEach>
								</c:if> 
								</select>
							</div>
							<div class="form-group">
								<label>Province:</label> <input type="text"
									class="form-control" id="province">
							</div>
							<div class="form-group">
								<label>Zip Code*:</label> <input type="text"
									class="form-control" id="zipcode" required="required" name="vGeneralZip" onkeypress="return isNumber(event)">
							</div>
					<div class="form-group">
						<label>Country*:</label>
						<%-- <select class="form-control" id="country"><c:forEach items="${acListofCountries}" var="objList"><option value="${curObject.countryId}">${curObject.country}</option></c:forEach></select> --%>
						<select path="iVGeneralCountry" id="country">
						<c:if test="${not empty CompanyInfoForm.listOfCountries}">
							<c:forEach items="${CompanyInfoForm.listOfCountries}" var="curObject">
								<option value="${curObject.countryId}">
								   ${curObject.country}
								</option>
							</c:forEach>
						</c:if> 
						</select>
					</div>
					<div class="form-group">
						<label>Phone:</label> <input type="text"
							class="form-control" id="phone">
					</div>
					<div class="form-group">
						<label>Fax:</label> <input type="text"
							class="form-control" id="fax">
					</div>
					<div class="form-group">
						<label>Homepage:</label> <input type="text"
							class="form-control" id="homepage">
					</div>
					<div class="form-group">
						<label>Category:</label> 
					<%-- <select class="form-control" id="category"><c:forEach items="${initCCategory}" var="objList"><option value='<bean:write name="objList" path="commonid" />'><bean:write name="objList" path="name" /></option></c:forEach></select> --%>
						<label> <input type="checkbox" name="optionsCheck1"
							id="billVendorCategory" value="Bill Vendor">Bill Vendor
						</label>
						<label> <input type="checkbox" name="optionsCheck1"
							id="purchaseVendorCategory" value="Purchase Vendor" checked="checked">Purchase Vendor
						</label>
					</div>
					<div class="form-group">
						<label>Email Address1*:</label> <input type="text"
						class="form-control" id="email" required="required" name="vGeneralEmail">
					</div>
					<div class="form-inline">
						<div class="form-group">
							<label>Tax ID*:</label> <input type="text"
							class="form-control" id="taxid" style="width: 600px;">
						</div>
						<div class="checkbox-inline">
							<label> <input type="checkbox" name="optionsCheck1"
								id="taxableId" value="Taxable">Taxable
							</label>
						</div>
						<div class="checkbox-inline">
							<label> <input type="checkbox" name="optionsCheck2"
								id="vendorEligibleId" value="Vendor eligible for 1099"> Vendor eligible for 1099
							</label>
						</div>
					</div>	
				</div>
			</div>
			<!-- </div> -->
		</div>
	</div>
<!-- -----------------------------------------------------------Common----------------------------------------------------------------------------------------- -->
	<div class="createNewCoupaRight">
		<div class="fieldset">
			<h6>Note</h6>
			<div class="notecheckbox">
				<label> <input type="checkbox" name="notecheckbox1"
					id="isAlsoCustomer" value="is also Customer">is also Customer
				</label>
			</div>
		</div>
		<br/>
		<div style="float: left; margin-top: 20px;">
			<ul style="margin: 0px; padding: 0px;">
				<li><a class="formbutton" onclick="addnewrecord();">
					<spring:message code="BzComposer.global.new"/>
				</a></li><br/><br/>
				<li><a class="formbutton" onclick="addgeneraldetail();" id="addcompanybtn">
					<spring:message code="BzComposer.global.add"/>
				</a></li><br/><br/>
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
$(function() {
	debugger;
	$("#tabs").tabs();
});
function submitform()
{
	debugger;
	if(!confirm("Do you want to save changes?"))
	{	
	 document.forms[0].action = "CompanyNew?tabid=createNewCompany8";
	 document.forms[0].submit();
	}
	else{
		addgeneraldetail();
		var json = JSON.stringify(objGlobal);
		 $.ajax({
			
			type : "POST",
			url : "CompanyNew?tabid=addVendor",
			data : "json=" +json ,
		    success : function(data) {
				alert("<bean:message key='BzComposer.common.saveSuccess'/>");
				document.forms[0].action = "CompanyNew?tabid=createNewCompany8";
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
}	
function finish() {
	if(objGlobal.length > 0)
	{	
		var json = JSON.stringify(objGlobal);
		 $.ajax({
			
			type : "POST",
			url : "CompanyNew?tabid=addVendor",
			data : "json=" +json ,
		    success : function(data) {
				debugger;
				alert("<bean:message key='BzComposer.common.saveSuccess'/>");
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
                window.history.go(-7);
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
var objGlobal = [];
var i = 0;
var result;
var numbers = /^[0-9]+$/;
var emailRegex = /^([a-zA-Z_.+-])+\@(([a-zA-Z-])+\.)+([a-zA-Z])+$/;
var a;var fnm;var lnm;var address1;var address2;
var city;var state;var province;var zipcode;var country;
var phone;var fax;var homepage;var email;var taxid;
var term;var salesrep;var receivedmethod;var shipcarrier;
var unpaidbalance;var existingcredit;var transferstartdate;var taxable;var isCustomer;var isBilling;var memo

function addgeneraldetail()
{
	debugger;
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
		
		//objGlobal.remove(i);
		
		//$.each(data, function(i, el){ if (this.id == id){ data.splice(i, 1); } });
		debugger;
		objGlobal.splice(objGlobal.indexOf(conceptName), 1);
		//list.splice( list.indexOf('foo'), 1 );

		debugger;
		objGlobal.push({name: a, firstname: fnm, lastname :lnm, address1 : address1, address2 : address2, city:city});
		
		 //$(".bca_companylist select").append(new Option(objGlobal[i].name,objGlobal[i].name));
		 
		 //$('.bca_companylist select option[value="'+objGlobal[i].name+'"]').text(objGlobal[i].name);
		 
		var eventTypeName = $("#customerlist option:selected");
		eventTypeName.text(a);
		$('#myform')[0].reset();
		$("#addcompanybtn").text("Add");
	}
	else{
		debugger;
		a = document.getElementById("companyname").value == ""?null:document.getElementById("companyname").value;
		fnm = document.getElementById("fnm").value == ""?null:document.getElementById("fnm").value;
		lnm = document.getElementById("lnm").value == ""?null:document.getElementById("lnm").value;
		address1 = document.getElementById("address1").value == ""?null:document.getElementById("address1").value; 
		address2 = document.getElementById("address2").value == ""?null:document.getElementById("address2").value;
		city = document.getElementById("city").value == ""?null:document.getElementById("city").value;
		state = document.getElementById("state").value == ""?null:document.getElementById("state").value;
		province = document.getElementById("province").value == ""?null:document.getElementById("province").value;
		zipcode = document.getElementById("zipcode").value == ""?null:document.getElementById("zipcode").value;
		country = document.getElementById("country").value == ""?null:document.getElementById("country").value;
		phone = document.getElementById("phone").value == ""?null:document.getElementById("phone").value;
		fax = document.getElementById("fax").value == ""?null:document.getElementById("fax").value;
		homepage = document.getElementById("homepage").value == ""?null:document.getElementById("homepage").value;
		/* var category = document.getElementById("category").value; */
		email = document.getElementById("email").value == ""?null:document.getElementById("email").value;
		taxid = document.getElementById("taxid").value == ""?null:document.getElementById("taxid").value;
		
		/*sales/account*/
		term = document.getElementById("term").value == ""?null:document.getElementById("term").value;
		salesrep = document.getElementById("salesrep").value == ""?null:document.getElementById("salesrep").value;
		receivedmethod = document.getElementById("receivedmethod").value == ""?null:document.getElementById("receivedmethod").value;
		shipcarrier = document.getElementById("shipcarrier").value == ""?null:document.getElementById("shipcarrier").value;
		unpaidbalance = document.getElementById("unpaidbalance").value == ""?null:document.getElementById("unpaidbalance").value;
		existingcredit = document.getElementById("existingcredit").value == ""?null:document.getElementById("existingcredit").value;
		transferstartdate = document.getElementById("transferstartdate").value == ""?null:document.getElementById("transferstartdate").value;
		taxable = $("#taxableId").prop("checked");
		isCustomer = $("#isAlsoCustomer").prop("checked");
		isBilling = $("#billVendorCategory").prop("checked");
		/*memo*/
		memo = document.getElementById("memo").value == ""?null:document.getElementById("memo").value;
		
		debugger;
		if(a==null)
		{
			alert("<bean:message key='BzComposer.NewCustomer.Name.Validation'/>");
			CompanyInfoForm.vGeneralCompanyName.focus();
			return false;
		}	
		if(fnm == null)
		{
			alert("<bean:message key='BzComposer.NewCustomer.FirstName.Validation'/>");
			CompanyInfoForm.vGeneralFirstName.focus();
			return false;
		}	
		if(lnm == null)
		{
			alert("<bean:message key='BzComposer.NewCustomer.LastName.Validation'/>");
			CompanyInfoForm.vGeneralLastName.focus();
			return false;
		}	
		if(address1 == null)
		{
			alert("<bean:message key='BzComposer.companyinfo.enterAddress'/>");
			CompanyInfoForm.vGeneralAddress1.focus();
			return false;
		}	
		if(zipcode == null)
		{
			alert("<bean:message key='BzComposer.NewCustomer.ZipCode.Validation'/>");
			CompanyInfoForm.vGeneralZip.focus();
			return false;
		}	
		if(email == null)
		{
			alert("<bean:message key='BzComposer.companyinfo.enteremailaddress'/>");
			CompanyInfoForm.vGeneralEmail.focus();
			return false;
		}
		if(!zipcode.match(numbers))
		{
			alert("<bean:message key='BzComposer.common.onlyNumbersAllowedInZip'/>");
			CompanyInfoForm.vGeneralZip.focus();
			return false;
		}	
		if(!email.match(emailRegex))
		{
			alert("<bean:message key='BzComposer.common.enterValidEmail'/>");
			CompanyInfoForm.vGeneralEmail.focus();
			return false;
		}	
			 	
		objGlobal.push({
			vGeneralCompanyName:a,
			vGeneralFirstName:fnm,
			vGeneralLastName:lnm,
			vGeneralAddress1:address1,
			vGeneralAddress2:address2,
			vGeneralCity:city,
			iVGeneralState:state,
			vGeneralProvince:province,
			iVGeneralCountry:country,
			vGeneralZip:zipcode,
			vGeneralPhone:phone,
			vGeneralFax:fax,
			vGeneralEmail:email,
			vGeneralHomepage:homepage,
			vGeneralTaxID:taxid,
			bVGeneralTaxable:taxable,
			dVSalesUnpaidBalance:unpaidbalance,
			dVSalesExistingCredit:existingcredit,
			bVIsAlsoCustomer:isCustomer,
			iVSalesTerm:term,
			iVSalesRep:salesrep,
			iVSalesPayMethod:receivedmethod,
			iVSalesShipMethod:shipcarrier,
			bIsElligibleTo1099:isBilling,
			vMemoText:memo,
		});			    
		debugger;
		  
		$(".bca_companylist select").append(new Option(objGlobal[i].vGeneralFirstName,objGlobal[i].vGeneralCompanyName));	
		debugger;	

		$('#myform')[0].reset();
		 
		debugger;
		i++;
	}	 
}
function getCompanyname()
{
	debugger;
	var conceptName = $('#customerlist :selected').text();
	debugger;
	result = objGlobal.find(obj => {
		return obj.firstname === conceptName
	 });
	
	  debugger;
	  document.getElementById("companyname").value=result.name;
	  document.getElementById("fnm").value=result.firstname;
	  document.getElementById("lnm").value=result.lastname;
	  document.getElementById("address1").value=result.address1;
	  document.getElementById("address2").value=result.address2;
	  document.getElementById("city").value=result.city;
	  document.getElementById("state").value=result.state;
	  document.getElementById("province").value=result.province;
	  document.getElementById("zipcode").value=result.zipcode;
	  document.getElementById("country").value=result.country;
	  document.getElementById("phone").value=result.phone;
	  document.getElementById("fax").value=result.fax;
	  document.getElementById("homepage").value=result.homepage;
	  document.getElementById("category").value=result.category;
	  document.getElementById("email").value=result.email;
	  document.getElementById("taxid").value=result.taxid;
	  
	  /*sales/account*/
	  document.getElementById("term").value=result.term;
	  document.getElementById("salesrep").value=result.salesrep;
	  document.getElementById("receivedmethod").value=result.receivedmethod;
	  document.getElementById("shipcarrier").value=result.shipcarrier;
	  //document.getElementById("cardtype").value=result.cardtype;
	  //document.getElementById("cardholdername").value=result.cardholdername;
	  //document.getElementById("cardnumber").value=result.cardnumber;
	  //document.getElementById("expmonth").value=result.expmonth;
	  //document.getElementById("expyear").value=result.expyear;
	  document.getElementById("unpaidbalance").value=result.unpaidbalance;
	  document.getElementById("existingcredit").value=result.existingcredit;
	  document.getElementById("transferstartdate").value=result.transferstartdate;	
	 
	  /*memo*/
	  document.getElementById("memo").value=result.memo;	  
	  $("#addcompanybtn").text("Edit");
}
function removeSelectedCompany()
{
	debugger;
	$('#customerlist option:selected').remove();	
	 $('#myform')[0].reset();
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
$("#optionsRadios1").click(function() {	
	debugger;
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
	debugger;
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
</script>
</body>
</html>