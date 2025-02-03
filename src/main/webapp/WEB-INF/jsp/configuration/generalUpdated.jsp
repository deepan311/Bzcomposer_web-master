<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/WEB-INF/jsp/include/headlogo.jsp" />
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<jsp:include page="/WEB-INF/jsp/include/menu.jsp" />
<title><spring:message code="BzComposer.generaltitle" /></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/custom.js"></script>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
#companyInfoTable tr th { font-size: 14px; }
#companyInfoTable tr td { font-size: 14px; }
.modal { display: none; }
.fonts{ font-size: 1.2em; }
.usersTblNew{ vertical-align: top;width: 100%;border: 1px solid rgb(207, 207, 207); }
.usersTblNew thead tr td{ padding: 5px 0px 5px 5px;font-size: 12px; }
.usersTblNew tbody tr td{ padding: 5px 0px 5px 5px;font-size: 12px; }
.draft td{ color: #ffffff;background: rgba(50, 58, 60, 0.63); }
</style>
</head>

<script>
var membershipLevel1 = "<%= request.getAttribute("membershipLevel")%>";
if(membershipLevel1 == "Standard"){
	$("#userlistlable11").hide();
	$("#userandpasswordheading").hide();
	$("#userandpassworddata").hide();
}

var Role = "<%= request.getAttribute("Role")%>";
if(Role=='User'){
	$("#userlistlable").hide();
}
function toggleFunction() {
	debugger;
  var x = document.getElementById("divtoggle");
  var lftmenu = document.getElementById("leftMenu");
  if (x.style.display === "none") {
    x.style.display = "block";
    lftmenu.style.width = "180px";
    lftmenu.style.position = "relative";
    /* document.getElementById("togglebtn").value = "+"; */
  } else {
    x.style.display = "none";
    lftmenu.style.width = "0";
    lftmenu.style.position = "absolute";
    /* document.getElementById("togglebtn").value = "-"; */
  }
}
</script>
<script type="text/javascript">
var funsequence = 0;
var _1 = navigator.userAgent.toLowerCase();
var ___ = (_1.indexOf("msie") != -1);
var ___5 = (_1.indexOf("msie 5") != -1);
var _io = (_1.indexOf("opera") != -1);
var _im = (_1.indexOf("mac") != -1);
var ____gi = (_1.indexOf("gecko") != -1);
var i____s = (_1.indexOf("safari") != -1);
var o = null;
var o22 = null;
var o33 = null;
var oEmail = null;
var oT = null;
var nm="";
var r = null;

function c(r) {
  if (___) {
	var t = (___5) ? "Microsoft.XMLHTTP" : "Msxml2.XMLHTTP";
    try {
	  o = new ActiveXObject(t);
      o.onreadystatechange = r;
	} catch (ex) {
      alert("<spring:message code='BzComposer.common.needToEnableActiveXObject'/> ts.." + ex);
	}
  } else {
	o = new XMLHttpRequest();
    o.onload = r;
	o.onerror = r;
  }
  return o;
}
function oGET(oo, url) {
  try {
	oo.open("GET", url, true);
    oo.send(null);
  }
  catch (ex) {
  }
}

function showLocale(lang){
	//var lang = document.getElementById("locale").value;
	window.location = "./changeLocale?requestPage=ConfigPage&lang="+lang;
}
function showLanguageDialog()
{
	event.preventDefault();
	$("#showLanguageDialog").dialog({
    	resizable: false,
        height: 200,
        width: 400,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
$(function() {
    $("#tabs").tabs();
    $("#tabs1").tabs();
});
$(document).ready(function()
{
	debugger;
	var isSOBChecked = "<%= request.getAttribute("isSOBChecked")%>";
	var isISBChecked = "<%= request.getAttribute("isISBChecked")%>";
	var isIRBChecked = "<%= request.getAttribute("isIRBChecked")%>";
	var isPOBChecked = "<%= request.getAttribute("isPOBChecked")%>";
	var isSelectedWeightID = "<%= request.getAttribute("isSelectedWeightID")%>";

	if($("#countryID").find("option:selected").text().trim() == 'Canada'){
        $('.lblProvinceShow').show();
        $('.lblPostalcodeShow').show();
        $('.lblStateShow').hide();
        $('.lblZipcodeShow').hide();
    }else{
        $('.lblProvinceShow').hide();
        $('.lblPostalcodeShow').hide();
        $('.lblStateShow').show();
        $('.lblZipcodeShow').show();
    }

	$('select[id="weightID"]').find('option[value="'+isSelectedWeightID+'"]').attr("selected",true);
	$('#salesOrderBoard').change(function(){
		var isChecked = isSOBChecked;
		if($(this).prop("checked") == true){
	        $("#salesOrderBoard").attr('checked', true);
	        isChecked = "on";
		}
	    else if($(this).prop("checked") == false){
	        $("#salesOrderBoard").attr('checked', false);
	        isChecked = "off";
		}else{
	        $("#salesOrderBoard").attr('checked', isChecked);
	    }
		document.configurationForm.salesOrderBoard.value = isChecked;
		$("#salesOrderBoard").val(isChecked);
	});

	$('#itemReceivedBoard').change(function(){
		var isChecked = isIRBChecked;
		if($(this).prop("checked") == true){
	        $("#itemReceivedBoard").attr('checked', true);
	        isChecked = "on";
		}
	    else if($(this).prop("checked") == false){
	        $("#itemReceivedBoard").attr('checked', false);
	        isChecked = "off";
		}else{
	        $("#itemReceivedBoard").attr('checked', isChecked);
	    }
		document.configurationForm.itemReceivedBoard.value = isChecked;
		$("#itemReceivedBoard").val(isChecked);
	});

	$('#poboard').change(function(){
		var isChecked = isPOBChecked;
		if($(this).prop("checked") == true){
	        $("#poboard").attr('checked', true);
	        isChecked = "on";
		}
	    else if($(this).prop("checked") == false){
	        $("#poboard").attr('checked', false);
	        isChecked = "off";
		}else{
	        $("#poboard").attr('checked', isChecked);
	    }
    	document.configurationForm.poboard.value = isChecked;
		$("#poboard").val(isChecked);
	});

	$('#itemShippedBoard').change(function(){
		var isChecked = isISBChecked;
		if($(this).prop("checked") == true){
	        $("#itemShippedBoard").attr('checked', true);
	        isChecked = "on";
		}
	    else if($(this).prop("checked") == false){
	        $("#itemShippedBoard").attr('checked', false);
	        isChecked = "off";
		}else{
	        $("#itemShippedBoard").attr('checked', isChecked);
	    }
    	document.configurationForm.itemShippedBoard.value = isChecked;
		$("#itemShippedBoard").val(isChecked);
	});
});

function TestConnection()
{
    var authType = 'false';
	var host = document.configurationForm.mailServer.value;
	var userEmail = document.configurationForm.mailUserName.value;
	var password = document.configurationForm.mailPassword.value;
	if(document.configurationForm.mailAuth.checked){
        if(userEmail.length < 5 || !userEmail.includes('@')){
            alert("<spring:message code='BzComposer.configuration.invalidEmail' />");
            return;
        }
        else if(password.length < 3){
            alert("<spring:message code='BzComposer.configuration.invalidPassword' />");
            return;
        }
        authType = 'true';
    }
	oEmail = c(CheckEmailConnection);
	oGET(oEmail, 'include/testMailServerConnection.jsp?HostName='+host+'&authType='+authType+'&userEmail='+userEmail+'&password='+password);
}

function CheckEmailConnection()
{
    $('#pleaseWaitDialog').dialog("close");
	if (oEmail.readyState != 4 || oEmail.status != 200) {
	  	return;
	}
	response = parseInt(trim(oEmail.responseText));
	if(response == 1)
	{
		return serverConnectedSeccessDialog();
		document.configurationForm.mailAuth.disabled=false;
		EnableDisableFields2();
	}
	else
	{
		return serverConnectedErrorDialog();
		document.configurationForm.mailAuth.disabled=true;
		EnableDisableFields2();
	}
}

function EnableDisableFields2(){
	if(document.configurationForm.mailAuth.checked==true){
		document.configurationForm.mailUserName.disabled=false;
		document.configurationForm.mailPassword.disabled=false;
	}
	else{
		document.configurationForm.mailUserName.disabled=true;
		document.configurationForm.mailPassword.disabled=true;
	}
}
function trim(inputString) {
   // Removes the spaces  return from the passed string.
   var retValue = inputString;
   var ch = retValue.substring(0, 1);
   while (ch == "\n" || ch == "\r" || ch==" " || ch=="\t" ) {
  retValue = retValue.substring(1, retValue.length);
      ch = retValue.substring(0, 1);
   }
   return retValue;
}
function serverConnectedErrorDialog(){
	event.preventDefault();
	$("#serverConnectedErrorDialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function serverConnectedSeccessDialog(){
	event.preventDefault();
	$("#serverConnectedSeccessDialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function pleaseWaitDialog(){
	event.preventDefault();
	$("#pleaseWaitDialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
</script>
<body onload="init();">
<form:form name="configurationForm" enctype="MULTIPART/FORM-DATA" method="post" modelAttribute="configDto">
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div>
	<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px;
	    border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.configuration.configurationtitle"/>
	</span>
</div>
<div>
	<div>
		<c:if test="${not empty Labels}">
            <input type="hidden" name="lsize" id="lblsize" value='${Labels.size()}' />
            <c:forEach items="${Labels}" var="lbl" varStatus="loop">
                <input type="hidden" id='${loop.index}lid' name='${loop.index}lidname' value='${lbl.value}' />
                <input type="hidden" id='${loop.index}lname' name='${loop.index}lnm' value='${lbl.label}' />
            </c:forEach>
		</c:if>
	</div>
	<div id="table-negotiations" style="padding: 0; border: 1px solid #ccc;">
		<table cellspacing="0" style="border: 0; padding: 0; width: 100%;overflow-y:scroll;" class="section-border">
			<!-- <tr>
				<td>
					<span style="font-size:30px;cursor:pointer; margin-left: 30px;" onclick="toggleFunction()">&#9776;</span>
				</td>
				<td></td>
			</tr> -->
			<tr>
			<span style="font-size:30px;cursor:pointer; margin-left: 20px;" onclick="toggleFunction()">&#9776;</span>
				<td id="leftMenu" style="position: relative; width: 180px;" valign="top">
					<table>
						<tr>
							<td>
								<jsp:include page="menuPage.jsp" />
							</td>
						</tr>
					</table>
				</td>
				<td valign="top">
					<!-- General -->
					<div></div>
					<!-- general page content starts -->
					<div id="general" style="display:none;padding: 0; position: relative; left: 0;">
						<div id="tabs" style="height: auto;">
  							<ul>
    							<li style="font-size: 12px;"><a href="#companyinfo"><spring:message code="BzComposer.companyinfo.title"/></a></li>
    							<li style="font-size: 12px;"><a href="#GeneralSetting"><spring:message code="BzComposer.configuration.tab.generalsettings" /></a></li>
    							<li style="font-size: 12px;"><a href="#security"><spring:message code="BzComposer.register.security"/></a></li>
    							<!-- <li style="font-size: 12px;"><a href="#modules"><spring:message code="BzComposer.common.modules" /></a></li> -->
								<li style="font-size: 12px;"><a href="#reminder"><spring:message code="BizComposer.Configuration.Reminders"/></a></li>
								<li style="font-size: 12px;"><a href="#emailSetup"><spring:message code="BzComposer.tab.eMailSetup"/></a></li>
  							</ul>
  							<div id="GeneralSetting">
								<div id="content1" class="tabPage">
									<table class="table-notifications" width="80%">
										<tr>
											<th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
												<spring:message code="BzComposer.configuration.generaltitle" />
											</th>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.currency" />
											</td>
											<td style="font-size: 12px;">
												<form:select path="currencyID" id="currencyID">
													<form:option value="0">
														<spring:message code="BzComposer.configuration.select"/>
													</form:option>
													<form:option value="1">
														<spring:message code="BzComposer.configuration.currency.baht"/>
													</form:option>
													<form:option value="2">
														<spring:message code="BzComposer.configuration.currency.bolivar"/>
													</form:option>
													<form:option value="3">
														<spring:message code="BzComposer.configuration.currency.boliviano"/>
													</form:option>
													<form:option value="4">
														<spring:message code="BzComposer.configuration.currency.cedi"/>
													</form:option>
													<form:option value="5">
														<spring:message code="BzComposer.configuration.currency.dirham"/>
													</form:option>
													<form:option value="6">
														<spring:message code="BzComposer.configuration.currency.dinar"/>
													</form:option>
													<form:option value="7" selected="selected">
														<spring:message code="BzComposer.configuration.currency.dollar"/>
													</form:option>
													<form:option value="8">
														<spring:message code="BzComposer.configuration.currency.dong"/>
													</form:option>
													<form:option value="9">
														<spring:message code="BzComposer.configuration.currency.euro"/>
													</form:option>
													<form:option value="10">
														<spring:message code="BzComposer.configuration.currency.forint"/>
													</form:option>
													<form:option value="11">
														<spring:message code="BzComposer.configuration.currency.franc"/>
													</form:option>
													<form:option value="12">
														<spring:message code="BzComposer.configuration.currency.koruna"/>
													</form:option>
													<form:option value="13">
														<spring:message code="BzComposer.configuration.currency.krona"/>
													</form:option>
													<form:option value="14">
														<spring:message code="BzComposer.configuration.currency.krone"/>
													</form:option>
													<form:option value="15">
														<spring:message code="BzComposer.configuration.currency.newshekel"/>
													</form:option>
													<form:option value="16">
														<spring:message code="BzComposer.configuration.currency.nuevosol"/>
													</form:option>
													<form:option value="17">
														<spring:message code="BzComposer.configuration.currency.peso"/>
													</form:option>
													<form:option value="18">
														<spring:message code="BzComposer.configuration.currency.pound"/>
													</form:option>
													<form:option value="19">
														<spring:message code="BzComposer.configuration.currency.pula"/>
													</form:option>
													<form:option value="20">
														<spring:message code="BzComposer.configuration.currency.quetzal"/>
													</form:option>
													<form:option value="21">
														<spring:message code="BzComposer.configuration.currency.rand"/>
													</form:option>
													<form:option value="22">
														<spring:message code="BzComposer.configuration.currency.real"/>
													</form:option>
													<form:option value="23">
														<spring:message code="BzComposer.configuration.currency.ringgit"/>
													</form:option>
													<form:option value="24">
														<spring:message code="BzComposer.configuration.currency.riyal"/>
													</form:option>
													<form:option value="25">
														<spring:message code="BzComposer.configuration.currency.riyali"/>
													</form:option>
													<form:option value="26">
														<spring:message code="BzComposer.configuration.currency.rouble"/>
													</form:option>
													<form:option value="27">
														<spring:message code="BzComposer.configuration.currency.rupee"/>
													</form:option>
													<form:option value="28">
														<spring:message code="BzComposer.configuration.currency.rupiah"/>
													</form:option>
													<form:option value="29">
														<spring:message code="BzComposer.configuration.currency.schilling"/>
													</form:option>
													<form:option value="30">
														<spring:message code="BzComposer.configuration.currency.sucre"/>
													</form:option>
													<form:option value="31">
														<spring:message code="BzComposer.configuration.currency.won"/>
													</form:option>
													<form:option value="32">
														<spring:message code="BzComposer.configuration.currency.yen"/>
													</form:option>
													<form:option value="33">
														<spring:message code="BzComposer.configuration.currency.yuan"/>
													</form:option>
												</form:select>
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.weight" />
											</td>
											<td style="font-size: 12px;">
												<form:select path="weightID" style="font-size: 12px;" id="weightID">
													<c:if test="${not empty configDto.listOfExistingWeights}">
														<c:forEach items="${configDto.listOfExistingWeights}" var="objList1">
															<option value="${objList1.weightID}">${objList1.weightName}</option>
														</c:forEach>
													</c:if>
												</form:select>
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.language" />
											</td>
											<td>
												<select name="locale" id="locale" onchange="showLocale(this.value);">
													<!-- <option value=""><spring:message code="BzComposer.selectlanguage"/></option> -->
													<option value="en" ${sessionScope.currentLocale=='en'?'selected':''}><spring:message code="BzComposer.selectlanguage.english"/></option>
													<option value="zh" ${sessionScope.currentLocale=='zh'?'selected':''}><spring:message code="BzComposer.selectlanguage.chinese"/></option>
													<option value="es" ${sessionScope.currentLocale=='es'?'selected':''}><spring:message code="BzComposer.selectlanguage.spanish"/></option>
												</select>
											</td>
										</tr>
										<tr>
											<th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
												<spring:message code="BzComposer.configuration.addresslabel" />
											</th>
										</tr>
										<tr colspan="2">
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.defaultlabel" />
											</td>
											<td style="font-size: 12px;">
												<c:if test="${not empty Labels}">
													<form:select path="defaultLabelID" onchange="SetLabelName(this.value);" style="width:200" id="defaultLabelID">
														<form:options items="${Labels}" itemValue="value" itemLabel="label" />
													</form:select>
												</c:if>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center" style="font-size: 14px;">
												<button type="button" class="formButton" title='<spring:message code="BzComposer.configuration.setuplabeltooltip"/>'>
												    <spring:message code="BzComposer.configuration.setuplabelbtn"/>
												</button>
											</td>
										</tr>
										<!-- <tr>
											<th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
												<spring:message code="BzComposer.configuration.defaultfilteroption" />
											</th>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.defaultfilteroption" />:
											</td>
											<td style="font-size: 12px;">
												<form:select path="filterOption" id="filterOption">
													<form:option value="All"><spring:message code="BzComposer.reportcenter.allinvoicelist.dates.all"/></form:option>
													<form:option value="Custom"><spring:message code="BzComposer.reportcenter.allinvoicelist.dates.custom"/></form:option>
													<form:option value="Today"><spring:message code="BzComposer.reportcenter.allinvoicelist.dates.today"/></form:option>
													<form:option value="This Month"><spring:message code="BzComposer.reportcenter.allinvoicelist.dates.thismonth"/></form:option>
													<form:option value="This Quarter"><spring:message code="BzComposer.configuration.filteroption.thisquarter"/></form:option>
													<form:option value="This Year"><spring:message code="BzComposer.configuration.filteroption.thisyear"/></form:option>
													<form:option value="1 Year"><spring:message code="BzComposer.configuration.filteroption.oneyear"/></form:option>
													<form:option value="2 Year"><spring:message code="BzComposer.configuration.filteroption.twoyear"/></form:option>
													<form:option value="3 Year"><spring:message code="BzComposer.configuration.filteroption.threeyear"/></form:option>
													<form:option value="This Month-to-Date"><spring:message code="BzComposer.reportcenter.allinvoicelist.dates.thismonthtodate"/></form:option>
													<form:option value="This Quarter-to-Date"><spring:message code="BzComposer.configuration.filteroption.thisquartertodate"/></form:option>
													<form:option value="This Year-to-Date"><spring:message code="BzComposer.configuration.filteroption.thisyeartodate"/></form:option>
													<form:option value="Last 10 days"><spring:message code="BzComposer.reportcenter.allinvoicelist.dates.last10days"/></form:option>
													<form:option value="Last 30 days"><spring:message code="BzComposer.reportcenter.allinvoicelist.dates.last30days"/></form:option>
													<form:option value="Last 60 days"><spring:message code="BzComposer.reportcenter.allinvoicelist.dates.last60days"/></form:option>
													<form:option value="1 Week"><spring:message code="BzComposer.configuration.filteroption.oneweek"/></form:option>
												</form:select>
											</td>
										</tr> -->
										<!-- <tr>
											<th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
												<spring:message code="BizCompozer.StartingPageSetting" />
											</th>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.defaultstartingpage" />:
											</td>
											<td style="font-size: 12px;">
												<form:select path="moduleID" id="moduleID">
													<form:option value="3"><spring:message code="BzComposer.configuration.defaultstartingpage.customer"/></form:option>
													<form:option value="5"><spring:message code="BzComposer.configuration.defaultstartingpage.inventory"/></form:option>
													<form:option value="1"><spring:message code="BzComposer.configuration.defaultstartingpage.invoice"/></form:option>
													<form:option value="6"><spring:message code="BzComposer.configuration.defaultstartingpage.navigationpage"/></form:option>
													<form:option value="2"><spring:message code="BzComposer.configuration.defaultstartingpage.po"/></form:option>
													<form:option value="4"><spring:message code="BzComposer.configuration.defaultstartingpage.vendor"/></form:option>
												</form:select>
											</td>
										</tr> -->
										<tr>
											<th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
												<spring:message code="BzComposer.configuration.defaultdashboardsetting" />
												<form:hidden path="moduleID" />
												<form:hidden path="filterOption" />
											</th>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.defaultcustomdashboard" />:
											</td>
											<td align="left" style="font-size: 12px;">
												<input type="checkbox" name="salesOrderBoard" id="salesOrderBoard" value="${configDto.salesOrderBoard}" ${configDto.salesOrderBoard=='on'?'checked':''} />
												<label><spring:message code="BzComposer.configuration.defaultdashboard.opensalesorders" /></label>&nbsp;&nbsp;&nbsp;
												<input type="checkbox" name="itemReceivedBoard" id="itemReceivedBoard" value="${configDto.itemReceivedBoard}" ${configDto.itemReceivedBoard=='on'?'checked':''} />
												<label><spring:message code="BzComposer.configuration.defaultdashboard.itemreceived" /></label>&nbsp;&nbsp;&nbsp;
												<form:checkbox path="showUSAInBillShipAddress" id="showUSAInBillShipAddress" />
                                                <label><spring:message code="BzComposer.configuration.defaultdashboard.showUSAInBillShipAddress" /></label>
												<br/>
												<input type="checkbox" name="poboard" id="poboard" value='${configDto.poboard}' ${configDto.poboard=='on'?'checked':''} />
												<label><spring:message code="BzComposer.configuration.defaultdashboard.poboard" /></label>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="checkbox" name="itemShippedBoard" id="itemShippedBoard" value='${configDto.itemShippedBoard}' ${configDto.itemShippedBoard=='on'?'checked':''} />
												<label><spring:message code="BzComposer.configuration.defaultdashboard.itemshiped" /></label>
											</td>
										</tr>
									</table>
								</div>
							</div>
							<div id="modules" style="display:none;">
   								<div id="content2" class="tabPage">
			   					 	<table class="table-notifications" width="80%">
										<tr>
											<th colspan="3" align="left" style="font-size: 12px; padding: 5px;">
												<spring:message code="BzComposer.configuration.feturesheader" />
											</th>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<b>
													<spring:message code="BzComposer.configuration.features.availablemodules" />
												</b>
											</td>
											<td>&nbsp;&nbsp;</td>
											<td style="font-size: 12px;">
												<b>
													<spring:message code="BzComposer.configuration.features.selectedmodules" />
												</b>
											</td>
										</tr>
										<tr>
											<td width="25%" style="font-size: 12px;">
												<form:select path="selectedModuleId" id="selectedModuleId" style="width: 200px; height: 200px;font-size: 1em;" class="featureName1" multiple="true">
													<c:if test="${not empty configDto.listOfExistingModules}">
														<c:forEach items="${configDto.listOfExistingModules}" var="objList1">
															<option value="${objList1.selectedModuleId}">${objList1.featureName}</option>
														</c:forEach>
													</c:if>
												</form:select>
											</td>
											<td style="font-size: 12px;">
												<br><br>
												<a class="addfeature" style="cursor: pointer; border: 1px solid #000; padding: 5px; background-color: #fff ">
													<spring:message code="BzComposer.configuration.lefttorightbtn"/>
												</a>
												<br/><br/>
												<a class="removefeature" style="cursor: pointer;border: 1px solid #000; padding: 5px; background-color: #fff ">
													<spring:message code="BzComposer.configuration.righttoleftbtn"/>
												</a>
											</td>
											<td>
												<form:select path="selectedModules" id="selectedModules" style="width: 200px; height: 200px;font-size: 1em;" class="featureName2" multiple="true">
													<c:if test="${not empty configDto.listOfExistingselectedModules}">
														<c:forEach items="${configDto.listOfExistingselectedModules}" var="objList1">
															<option value="${objList1.selectedModuleId}">${objList1.featureName}</option>
														</c:forEach>
													</c:if>
												</form:select>
											</td>
										</tr>
									</table>
								</div>
							</div>

							<!--  Reminders Starts -->
							<div id="reminder">
								<div id="content4" class="tabPage">
									<jsp:include page="ReminderNew.jsp"/>
								</div>
							</div>
							<div id="emailSetup">
								<div id="tabs1" style="height:600px;">
  									<ul>
    									<li style="font-size: 12px;">
    										<a href="#smtpServerSettings">
    											<spring:message code="BzComposer.tab.smtpServerSettings"/>
    										</a>
										</li>
    									<li style="font-size: 12px;">
    										<a href="#emailTemplate">
    											<spring:message code="BzComposer.configuration.emailtemplate"/>
   											</a>
										</li>
									</ul>
									<div id="smtpServerSettings">
										<div id="content" class="tabPage">
											<%-- <jsp:include page="/WEB-INF/jsp/configuration/smtpSetup.jsp"/> --%>
											<!-- SMTP Setup Starts -->
											<table class="table-notifications" width="100%">
												<tr>
													<td colspan="2" style="font-size: 12px;">
														<b><spring:message code="BzComposer.configuration.setuploginnote" /></b>
													</td>
												</tr>
												<tr>
													<th align="left" colspan="2" style="font-size: 12px; padding: 5px;">
														<spring:message code="BzComposer.configuration.serverinformation" />
													</th>
												</tr>
												<tr>
                                                    <td style="font-size: 12px;">
                                                        <spring:message code="BzComposer.configuration.smtpserver" />
                                                    </td>
                                                    <td style="font-size: 12px;">
                                                        <form:input path="mailServer" size="30" maxlength="45" />
                                                    </td>
                                                </tr>
												<tr>
													<td style="font-size: 12px;">
														<spring:message code="BzComposer.configuration.senderemailaddress" />
													</td>
													<td style="font-size: 12px;">
														<form:input path="senderEmail" size="30" maxlength="45" />
													</td>
												</tr>
												<tr>
													<td colspan="2" style="font-size: 12px;">
														<input type="button" name="testMailConnection" class="formButton" size="25" onclick="TestConnection()"
														    value='<spring:message code="BzComposer.configuration.testconnectiontomailserverbtn" />' />
													</td>
												</tr>
												<tr>
													<td colspan="2" style="font-size: 12px;">
														<spring:message code="BzComposer.configuration.mailserverauthentication" />
													</td>
												</tr>
												<tr>
													<td colspan="2" style="font-size: 12px;">
														<input type="checkbox" name="mailAuth" id="mailAuth" onclick="EnableDisableFields2();" value='${configDto.mailAuth}' ${configDto.mailAuth=='on'?'checked':''} />
														<label><spring:message code="BzComposer.configuration.serverrequeiresauthentication" /></label>
													</td>
												</tr>
												<tr>
													<th align="left" colspan="2" style="font-size: 12px; padding: 5px;">
														<spring:message code="BzComposer.configuration.userinformation" />
													</th>
												</tr>
												<tr>
													<td style="font-size: 12px;">
														<spring:message code="BzComposer.configuration.username" />
													</td>
													<td style="font-size: 12px;">
														<form:input type="email" path="mailUserName" size="30" maxlength="45" />
													</td>
												</tr>
												<tr>
													<td style="font-size: 12px;">
														<spring:message code="BzComposer.configuration.password" />
													</td>
													<td style="font-size: 12px;">
														<form:input path="mailPassword" size="30" maxlength="35" />
													</td>
												</tr>
											</table>
											<!-- SMTP Setup Ends -->
										</div>
									</div>
									<div id="emailTemplate">
										<div id="content2" class="tabPage">
                                            <jsp:include page="emailSetupNew.jsp"/>
                                        </div>
									</div>
								</div>
							</div>

							<!-- Company-Information Start -->
							<div id="companyinfo">
							    <div id="content" class="tabPage">
							        <form:form name="CompanyInfoForm" method="post" id="frmUpdateCompany" modelAttribute="companyInfoDto">
                                    <table id="companyInfoTable" class="table-notifications">
                                        <tr>
                                            <th colspan="3" style="padding: 5px;">
                                                <spring:message code="BzComposer.companyinfo.title" />
                                            </th>
                                        </tr>
                                        <tr>
                                            <td colspan="3" align="center"><div id="errors" style="color: red;"></div></td>
                                        </tr>
                                        <tr>
                                            <td style="width:30%;">
                                                <spring:message code="BzComposer.register.membershiplevels"/>
                                                <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                            </td>
                                            <td style="width:30%;">
                                                <form:select path="membershipLevel" style="width:350px;">
                                                    <form:option value="standard"><spring:message code="BzComposer.register.standardmembership"/></form:option>
                                                    <form:option value="professional"><spring:message code="BzComposer.register.professionalmembership"/></form:option>
                                                    <form:option value="eSales"><spring:message code="BzComposer.register.esalesmembership"/></form:option>
                                                </form:select>
                                            </td>
                                            <td style="width:40%;">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.companyinfo.emailaddress" /> <span class="inputHighlighted">*</span></td>
                                            <td><form:input path="email" size="42" maxlength="45" /></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.Companyinformation.FirstName" /> <span class="inputHighlighted">*</span></td>
                                            <td><form:input path="firstName" size="42" maxlength="45" /></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.Companyinformation.LastName" /> <span class="inputHighlighted">*</span></td>
                                            <td><form:input path="lastName" size="42" maxlength="45" /></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.register.jobposition" /> <span class="inputHighlighted">*</span></td>
                                            <td>
                                                <form:select path="jobPosition" style="width:350px;">
                                                    <form:option value="president" ><spring:message code="BzComposer.register.president"/></form:option>
                                                    <form:option value="manager"><spring:message code="BzComposer.register.manager"/></form:option>
                                                    <form:option value="programmer"><spring:message code="BzComposer.register.programmer"/></form:option>
                                                </form:select>
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.language" /></td>
                                            <td>
                                                <select name="locale" id="locale" onchange="showLocale(this.value);" style="width:350px;">
                                                    <option value="en" ${currentLocale=='en'?'selected':''}><spring:message code="BzComposer.selectlanguage.english"/></option>
                                                    <option value="zh" ${currentLocale=='zh'?'selected':''}><spring:message code="BzComposer.selectlanguage.chinese"/></option>
                                                    <option value="es" ${currentLocale=='es'?'selected':''}><spring:message code="BzComposer.selectlanguage.spanish"/></option>
                                                </select>
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <td colspan="3"><strong><spring:message code="BzComposer.companyinfo.aboutcompany"/></strong></td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.Companyinformation.CompanyName" /> <span class="inputHighlighted">*</span></td>
                                            <td><form:input path="companyName" size="42" maxlength="45" /></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.register.dbaName" /></td>
                                            <td><form:input path="nickName" size="42" maxlength="45" /></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.Companyinformation.Type" /> <span class="inputHighlighted">*</span></td>
                                            <td>
                                                <form:select path="businessTypeId" id="businessTypeId" style="width:350px;">
                                                    <c:forEach items="${companyInfoDto.listOfBusinessType}" var="objList1">
                                                        <form:option value="${objList1.businessTypeId}">${objList1.businessName}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.Companyinformation.Address1" /> <span class="inputHighlighted">*</span></td>
                                            <td><form:input path="address1" size="42" maxlength="45" /></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.Companyinformation.Address2" /></td>
                                            <td><form:input path="address2" size="42" maxlength="45" /></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="lblZipcodeShow"><spring:message code="BzComposer.companyinfo.zipcode" /> <span class="inputHighlighted">*</span></div>
                                                <div class="lblPostalcodeShow"><spring:message code="BzComposer.global.postalcodes" /> <span class="inputHighlighted">*</span></div>
                                            </td>
                                            <td><form:input type="text" path="zip" onfocusout="loadAddressDetailsByZipcode(this.value, 1);" onkeypress="return numbersonly(event, this.value)" size="42" maxlength="10" /></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.companyinfo.city"/> <span class="inputHighlighted">*</span></td>
                                            <td>
                                                <form:select path="city" id="cityID" style="width:200px;">
                                                    <form:option value="0"><spring:message code="BzComposer.register.selectcity" /></form:option>
                                                    <c:forEach items="${cityList}" var="currObject">
                                                        <form:option value="${currObject.cityId}">${currObject.cityName}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td class="lblStateShow"><spring:message code="BzComposer.Customer.State" /> <span class="inputHighlighted">*</span></td>
                                            <td class="lblProvinceShow"><spring:message code="BzComposer.Companyinformation.Province" /> <span class="inputHighlighted">*</span></td>
                                            <td>
                                                <form:select path="state" id="stateID" onchange="loadCitiesByStateID(this.value, 1);">
                                                    <form:option value="0"><spring:message code="BzComposer.register.selectstate" /></form:option>
                                                    <c:forEach items="${stateList}" var="currObject">
                                                        <form:option value="${currObject.stateId}">${currObject.state}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                                <form:hidden path="province" />
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.Customer.Country" /> <span class="inputHighlighted">*</span></td>
                                            <td>
                                                <form:select path="countryId" id="countryID" onchange="loadStatesByCountryID(this.value, 1);" style="width:350px;">
                                                    <form:option value="0"><spring:message code="BzComposer.ComboBox.Select" /></form:option>
                                                    <c:forEach items="${countryList}" var="curObject">
                                                        <form:option value="${curObject.countryId}">${curObject.countryName}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.Companyinformation.Phone"/> <span class="inputHighlighted">*</span></td>
                                            <td><form:input path="phone" onchange="setPhonePattern(this)" size="42" maxlength="15" /></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.global.mobileNumber"/></td>
                                            <td><form:input path="cellPhone" onchange="setPhonePattern(this)" size="42" maxlength="15" /></td>
                                            <td>
                                                &nbsp;<form:checkbox path="sameAsPhoneNumber" onchange="copyPhoneNumber(this.form);" />
                                                (<spring:message code="BzComposer.global.isPhoneNumber" />)
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="BzComposer.companyinfo.fax"/></td>
                                            <td><form:input path="fax" onchange="setPhonePattern(this)" size="42" maxlength="15" /></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="bca.EmployerID"/></td>
                                            <td><form:input path="taxID" size="42" maxlength="15" /></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </table>
                                    </form:form>
                                </div>
							</div>
							<!-- Company-Information End -->
							<!-- Security Start here -->
							<div id="security">
                                <div id="content" class="tabPage">
                                <!-- ------------------- Company-Security Start here ----------------- -->
                                    <form:form name="SecurityForm" method="post" id="SecurityForm" modelAttribute="companyInfoDto">
                                    <table id="companyInfoTable" class="table-notifications">
                                        <tr>
                                            <th colspan="3" style="padding: 5px;font-size: 12px;">
                                                <spring:message code="BzComposer.register.security" />
                                            </th>
                                        </tr>
                                        <tr>
                                            <td colspan="3" align="center"><div id="errorsPWD" style="color: red;"></div></td>
                                        </tr>
                                        <tr>
                                            <td style="width:30%;font-size: 12px;">
                                                <spring:message code="BzComposer.register.password"/>
                                                <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                            </td>
                                            <td style="width:30%;font-size: 12px;">
                                                <form:input type="password" path="password" size="40" maxlength="45" required="true" />
                                            </td>
                                            <td style="width:40%;">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td nowrap style="font-size: 12px;">
                                                <spring:message code="BzComposer.register.confirmpassword"/>
                                                <span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>&nbsp;
                                            </td>
                                            <td style="font-size: 12px;">
                                                <form:input type="password" path="confirmPassword" size="40" maxlength="45" required="true" />
                                            </td>
                                            <td>
                                                <button type="button" class="formbutton" onclick="updateComapanySecurity();" style="padding:5 10px;font-size:14px;">
                                                    <spring:message code='BzComposer.global.update'/>
                                                </button>
                                            </td>
                                        </tr>
                                    </table>
                                    </form:form>

                                    <!-- ------------------- Admin-Security Start here ----------------- -->
                                    <form:form name="AdminSecurityForm" method="post" id="form" modelAttribute="configDto">
                                        <div id="uName" class="modal" style="height:auto;">
                                            <form name="changeUserName&Password">
                                                <table border="2" style="width:100%">
                                                <tr>
                                                    <td colspan="2" align="center" style="font-size: 12px; padding: 5px;">
                                                    <spring:message code="BzComposer.configuration.configadmin"  /></td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 14px;">
                                                        <spring:message code="BzComposer.configuration.username"/>:
                                                    </td>
                                                    <td style="font-size: 14px;">
                                                        <form:input path="emailAddress" id="modalUserName" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 14px;">
                                                        <spring:message code="BzComposer.configuration.oldpassword"/>:
                                                    </td>

                                                    <td style="font-size: 14px;">
                                                        <form:input type="password" path="password" id="modalOldPassword" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 14px;">
                                                        <spring:message code="BzComposer.configuration.newpassword"/>:
                                                    </td>
                                                    <td style="font-size: 14px;">
                                                        <form:input type="password" path="newPassword" id="modalNewPassword" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 14px;">
                                                        <spring:message code="BzComposer.configuration.confirmpassword"/>:
                                                    </td>
                                                    <td style="font-size:14px;">
                                                        <form:input type="password" path="newPassword" id="modalConfirmPassword" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size:14px;">
                                                        <input type="submit" class="bottomButton formButton" name="Save" id="Save" value='<spring:message code="BzComposer.configuration.configadminbtn"/>' onclick="getData()" />
                                                    </td>
                                                    <td style="font-size:14px;">
                                                        <a href="#" rel="modal:close">
                                                            <input type="reset" class="bottomButton formButton" name="Cancel" id="Cancel" value='<spring:message code="BzComposer.global.cancel"/>' />
                                                        </a>
                                                    </td>
                                                </tr>
                                                </table>

                                            </form>
                                            <br>
                                        </div>
                                        <div id="AddUser" class="modal" style="height:auto;">
                                            <form name="addnewuser1">
                                                <div id="ddcolortabsline">&nbsp;</div>
                                                <div id="cos">
                                                    <div class="statusquo ok">
                                                        <div id="hoja">
                                                            <div id="blanquito">
                                                                <div id="padding">
                                                                    <div>
                                                                        <div id="table-negotiations">
                                                                            <table>
                                                                                <tr>
                                                                                    <td colspan="3">
                                                                                        <div id="addUserNameAndPassword" style="display: block; width: 450px;">
                                                                                            <table style="font-size: 12px;">
                                                                                                <tr height="30px;">
                                                                                                    <th colspan="2" align="left">
                                                                                                        <spring:message code="BizComposer.Configuration.Networking.userNameAndPassword"/>
                                                                                                    </th>
                                                                                                </tr>
                                                                                                <tr height="30px;">
                                                                                                    <th colspan="2" align="left">
                                                                                                        <spring:message code="BizComposer.Configuration.Networking.provideUserNameAndPassword"/>
                                                                                                    </th>
                                                                                                </tr>
                                                                                                <tr height="30px;">
                                                                                                    <td>
                                                                                                        <spring:message code="BzComposer.contactus.formemail"/>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <input type="email" name="userEmail" id="userEmail" style="width:250px;">
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr height="30px;">
                                                                                                    <td>
                                                                                                        <spring:message code="Bizcomposer.password"/>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <input type="password" name="userPassword" id="userPassword" style="width:250px;">
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr height="30px;">
                                                                                                    <td>
                                                                                                        <spring:message code="Bizcomposer.confirmPassword"/>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <input type="password" id="cpwd" style="width: 250px;">
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr height="30px;">
                                                                                                    <td>
                                                                                                        <spring:message code="Bizcomposer.adminPassword"/>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <input type="password"  name="adminpassword" id="adminpassword" style="width: 250px;">
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr height="30px;">
                                                                                                    <th colspan="2" align="left">
                                                                                                        <spring:message code="BizComposer.Configuration.Networking.selectGroupForAccessPermissions"/>
                                                                                                    </th>
                                                                                                </tr>
                                                                                                <tr height="30px;">
                                                                                                    <td width="120px;">
                                                                                                        <spring:message code="Bizcomposer.groupName"/>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <form:select path="groupID" style="width:250px;">
                                                                                                            <form:options items="${configDto.listOfExistingGroup}" itemValue="selectedGroupId" itemLabel="groupName" />
                                                                                                        </form:select>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td></td>
                                                                                                    <td style="font-size:14px;">
                                                                                                        <button type="button" class="formButton" onclick="viewGroupPermissions()">
                                                                                                            <spring:message code="BizComposer.Configuration.Networking.viewGroupPermissions"/>
                                                                                                        </button>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td colspan="2" align="right" style="font-size:14px;padding-top:10px;">
                                                                                                        <button type="button" class="formButton" id="next" onclick="checkValidation()">
                                                                                                            <spring:message code="BzComposer.global.save" />
                                                                                                        </button>&nbsp;&nbsp;
                                                                                                        <input type="button" class="formButton" id="finish" value="Finish" readonly="readonly" style="display:none;" onclick="submitForm()">
                                                                                                        <button type="button" class="formButton" id="help" onclick="showHelp()">
                                                                                                            <spring:message code="BzComposer.Help" />
                                                                                                        </button>&nbsp;&nbsp;
                                                                                                        <a href="#" rel="modal:close">
                                                                                                            <button type="reset" class="formButton" name="Cancel" id="Cancel">
                                                                                                                <spring:message code="BzComposer.global.cancel" />
                                                                                                            </button>
                                                                                                        </a>
                                                                                                    </td>
                                                                                                </tr>
                                                                                            </table>
                                                                                        </div>
                                                                                    </td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                            <br>
                                        </div>

                                        <!--  Networking & Security Starts -->
                                        <div id="nw">
                                        <table class="table-notifications" width="80%">
                                            <tr>
                                                <th colspan="5" align="left" style="font-size: 12px; padding: 5px;">
                                                    <spring:message code="BzComposer.configuration.administrator" />
                                                </th>
                                            </tr>
                                            <tr>
                                                <td style="font-size: 12px;">
                                                    <spring:message code="BzComposer.configuration.username" />:
                                                </td>
                                                <td style="font-size: 12px;">
                                                    <%-- <b><form:text path="UserName" id="UserName"></form:text></b> --%>
                                                    <form:input path="emailAddress" id="UserName" readonly="true" size="40" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="font-size: 12px;">
                                                    <spring:message code="BzComposer.configuration.password" />
                                                </td>
                                                <td style="font-size: 12px;">
                                                    <form:input type="password" path="password" maxlength="30" readonly="true" size="40" />
                                                </td>
                                                <td style="font-size:12px;">
                                                    <!-- <input type="button" id="changePassword" name="changePassword" value="changePassword" onclick="password();"> -->
                                                    <a href="#uName" rel="modal:open">
                                                        <button type="button" name="changePassword" class="formButton" style="font-size: 14px; width:140px;">
                                                            <spring:message code="Bizcomposer.changePassword"/>
                                                        </button>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th colspan="5" align="left" style="font-size: 12px; padding: 5px;">
                                                    <spring:message code="BzComposer.configuration.swithcusermode" />
                                                </th>
                                            </tr>
                                            <tr>
                                                <td colspan="5" style="font-size: 12px;">
                                                    <form:radiobutton path="multiUserConnection" id="multiUserConnection1"  value="0" />
                                                    <spring:message code="BzComposer.configuration.usermode.singleusermode" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="5" style="font-size: 12px;">
                                                    <form:radiobutton path="multiUserConnection" id="multiUserConnection2" value="1" />
                                                    <spring:message code="BzComposer.configuration.usermode.multiusermode" />
                                                </td>
                                            </tr>
                                            <tr id="userlistlable11">
                                                <th colspan="5" align="left" style="font-size: 12px; padding: 5px;">
                                                    <spring:message code="BzComposer.configuration.userlist" />
                                                </th>
                                            </tr>
                                            <tr>
                                                <td colspan="3" style="font-size:12px;">
                                                <div style="overflow:auto;height:200;" class="section-border">
                                                <table cellspacing="0" border="1" class="usersTblNew">
                                                    <thead>
                                                        <tr>
                                                            <td><b><spring:message code="BzComposer.configuration.username" /></b></td>
                                                            <td><b><spring:message code="BzComposer.configuration.password" /></b></td>
                                                            <td><b><spring:message code="BzComposer.configuration.groupname" /></b></td>
                                                            <td><b><spring:message code="BzComposer.configuration.status" /></b></td>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:if test="${not empty configDto.listOfExistingUserList}">
                                                            <input type="hidden" id="usersSize" value='${configDto.listOfExistingUserList.size()}' />
                                                            <c:forEach items="${configDto.listOfExistingUserList}" var="objList1" varStatus="loop">
                                                                <tr id='${loop.index}usrIndex' onclick="selectUserData('${objList1.upsUserId}', '${objList1.groupID}', '${loop.index}usrIndex');">
                                                                    <td>${objList1.emailAddress}</td>
                                                                    <td>${objList1.password}</td>
                                                                    <td>${objList1.groupName}</td>
                                                                    <td>${objList1.status}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </c:if>
                                                    </tbody>
                                                </table>
                                                </div>
                                                </td>
                                                <td colspan="2" align="center" valign="middle" style="font-size: 14px;padding-top:40px;">
                                                    <button type="button" class="formButton" onclick="adduser1();" style="width:120px;">
                                                        <spring:message code="BzComposer.configuration.adduserbtn"/>
                                                    </button><br/><br/>
                                                    <button type="button" class="formButton" style="width:120px;">
                                                        <spring:message code="BzComposer.configuration.savechangesbtn"/>
                                                    </button><br/><br/>
                                                    <button type="button" class="formButton" onclick="Deleteuser();" style="width:120px;">
                                                        <spring:message code="BzComposer.global.delete"/>
                                                    </button>
                                                </td>
                                            </tr>
                                            <tr id="lastline"></tr>
                                            <tr id="userlistlable11">
                                                <th colspan="5" align="left" style="font-size: 12px; padding: 5px;">
                                                    <spring:message code="BizComposer.Configuration.groupList" />
                                                </th>
                                            </tr>
                                            <tr>
                                                <td colspan="3" style="font-size:12px;">
                                                <div style="overflow:auto;height:200;" class="section-border">
                                                <table cellspacing="0" border="1" class="usersTblNew">
                                                    <thead>
                                                        <tr>
                                                            <td><b><spring:message code="BzComposer.configuration.groupname" /></b></td>
                                                            <td><b><spring:message code="BzComposer.configuration.status" /></b></td>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:if test="${not empty configDto.listOfExistingGroup}">
                                                            <input type="hidden" id="groupSize" value='${configDto.listOfExistingGroup.size()}' />
                                                            <c:forEach items="${configDto.listOfExistingGroup}" var="objList1" varStatus="loop">
                                                                <tr id='${loop.index}gpIndex' onclick="selectGroupData('${objList1.selectedGroupId}', '${loop.index}gpIndex');">
                                                                    <td>${objList1.groupName}</td>
                                                                    <td>${objList1.status}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </c:if>
                                                    </tbody>
                                                </table>
                                                </div>
                                                </td>
                                                <td colspan="2" align="center" valign="middle" style="font-size: 14px;padding-top:20px;">
                                                    <button type="button" class="formButton" style="width:120px;" onclick="addNewGroup()">
                                                        <spring:message code="BzComposer.configuration.addgroupbtn"/>
                                                    </button><br/><br/>
                                                    <button type="button" class="formButton" style="width:120px;" onclick="editGroup()">
                                                        <spring:message code="BzComposer.configuration.editgroupbtn"/>
                                                    </button><br/><br/>
                                                    <button type="button" class="formButton" style="width:120px;">
                                                        <spring:message code="BzComposer.configuration.savechangesbtn"/>
                                                    </button><br/><br/>
                                                    <button type="button" class="formButton" style="width:120px;" onclick="deleteGroup()">
                                                        <spring:message code="BzComposer.global.delete"/>
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                        </div>
                                        <!-- nw Ends -->

                                        <div>
                                            <form:hidden path="empStateID" />
                                            <form:hidden path="labelName" />
                                            <form:hidden path="fileName" />
                                            <form:hidden path="selectedGroupId" />
                                            <input type="hidden" name="tabid" id="tid" value="" />
                                            <input type="hidden" name="selectedUserId" id="selectedUserId" />
                                            <input type="hidden" name="userGroupId" id="userGroupId" />
                                        </div>
                                    </form:form>
                                    <!-- Admin-Security End -->
                                </div>
                            </div>
							<!-- Company-Security End -->
						</div>
					</div>
					<!-- general page content ends -->
				</td>
			</tr>
	</table>
	<div>
		<form:hidden path="empStateID" />
		<form:hidden path="labelName" />
		<form:hidden path="fileName" />
	 </div>
	<div>
		<input type="hidden" name="tabid" id="tabid" value="" />
		<input type="hidden" name="salesOrderBoard" value=""/>
		<input type="hidden" name="itemReceivedBoard" value=""/>
		<input type="hidden" name="itemShippedBoard" value=""/>
		<input type="hidden" name="poboard" value=""/>
	</div>
	<div align="center" id="generalButtons" style="display: block;">
		<input type="button" class="bottomButton formButton" name="Save" id="Save" onclick="updateComapany()" value="<spring:message code='BzComposer.global.save'/>" />
		<input type="reset" class="bottomButton formButton" id="Cancel" name="Cancel" onclick="RevokeValues()" value="<spring:message code='BzComposer.global.cancel'/>" />
	</div>
	</div>
	</div>
	</div>
	</div>
</div>
</div>
</div>
</form:form>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
</body>
<script type="text/javascript">
EnableDisableFields2();
function updateComapany()
{
    debugger;
	var sel = document.getElementById("businessTypeId");
    var businessTypeId = sel.options[sel.selectedIndex].value;
	var email = document.getElementById("email").value;
	var fName = document.getElementById("firstName").value;
	var lName = document.getElementById("lastName").value;
	var companyName = document.getElementById("companyName").value;
	var nickName = document.getElementById("nickName").value;
	var address1 = document.getElementById("address1").value;
	var address2 = document.getElementById("address2").value;
	var zip = document.getElementById("zip").value;
	var stateID = document.getElementById("stateID").value;
	var cityID = document.getElementById("cityID").value;
	var countryId = document.getElementById("countryID").value;
	var phone = document.getElementById("phone").value;
	var province = document.getElementById("province").value;
	var cellphone = document.getElementById("cellPhone").value;
	var fax = document.getElementById("fax").value;
	var membershipLevel = document.getElementById("membershipLevel").value;
	var sameAsPhoneNumber = document.getElementById("sameAsPhoneNumber1").checked;
	var taxID = document.getElementById("taxID").value;
	var jobPosition = document.getElementById("jobPosition").value;

	var errorMessage="";
	if(email == "" && fName == "" && lName == "" && companyName == "" && address1 == "" && cityID == ""){
		errorMessage = "<spring:message code='MultiUserForm'/>";
	}
	else
	{
		if(email == "" || email == null){
			errorMessage = "<h3><spring:message code='BzComposer.companyinfo.enteremailaddress'/></h3>";
		}
		else if (!ValidateEmail(email)) {
	        errorMessage = "<h3><spring:message code='err.Email'/></h3>";
	    }
		else if(fName == "" || fName == null){
			errorMessage = "<h3><spring:message code='err.Firstname'/></h3>";
		}
		else if(lName == "" || lName == null){
			errorMessage = "<h3><spring:message code='err.LastName'/></h3>";
		}
		else if(companyName == "" || companyName == null){
			errorMessage = "<h3><spring:message code='BzComposer.companyinfo.enterCompanyName'/></h3>";
		}
		else if(address1 == "" || address1 == null){
			errorMessage = "<h3><spring:message code='BzComposer.companyinfo.enterAddress'/></h3>";
		}
		else if(zip == "" || zip == null){
			errorMessage = "<h3><spring:message code='err.Zip'/></h3>";
		}
	}
	if(errorMessage !=""){
		document.getElementById("errors").innerHTML = errorMessage;
		return false;
		event.preventDefault();
	}
	else
	{
		$.ajax({
			type: "POST",
			url:"updateEditedCompanyinfo?tabid=edit&companyName="+companyName+"&nickName="+nickName+"&businessTypeId="+businessTypeId
                    +"&fName="+fName+"&lName="+lName+"&add1="+address1+"&add2="+address2+"&cityID="+cityID+"&zip="+zip+"&province="+province
                    +"&countryId="+countryId+"&phone="+phone+"&cellphone="+cellphone+"&fax="+fax+"&email="+email+"&stateID="+stateID
                    +"&membershipLevel="+membershipLevel+"&sameAsPhoneNumber="+sameAsPhoneNumber+"&taxID="+taxID+"&jobPosition="+jobPosition,
 			success : function() {
				$("#stateID").find('option[value="'+stateID+'"]').attr("selected",true);
				document.getElementById("errors").innerHTML = "<h3>Record updated</h3>";
				SaveValues();
				//SaveSecurityValues();
	   		},
			error : function(data) {
				event.preventDefault();
				alert("<spring:message code='BzComposer.common.erroroccurred'/>");
			}
		});
	}
}
function SaveValues()
{
	event.preventDefault();
	$("#showsaverecorddialog").dialog({
	    	resizable: false,
	        height: 200,
	        width: 500,
	        modal: true,
	        buttons: {
	        	"<spring:message code='BzComposer.global.ok'/>": function () {

	        	    // document.configurationForm.currencyID.value = parseInt(document.configurationForm.currencyID.value);
	        		// var currencyValue = $.trim($("#currencyID option:selected").text());

	        		var currencyID=document.getElementById("currencyID").value;
	        		var weightID=document.getElementById("weightID").value;
	        		var defaultLabelID=document.getElementById("defaultLabelID").value;
	        		var filterOption=document.getElementById("filterOption").value;
	        		var moduleID=document.getElementById("moduleID").value;

	        		var salesOrderBoard = $("#salesOrderBoard").val();
	        		var itemReceivedBoard = $("#itemReceivedBoard").val();
	        		var poboard = $("#poboard").val();
	        		var itemShippedBoard = $("#itemShippedBoard").val();
	        		document.getElementById('salesOrderBoard').value = salesOrderBoard;
	        		document.getElementById('itemReceivedBoard').value = itemReceivedBoard;
	        		document.getElementById('itemShippedBoard').value = itemShippedBoard;
	        		document.getElementById('poboard').value = poboard;

	        		var mailServer = document.getElementById("mailServer").value;
	        		var senderEmail = document.getElementById("senderEmail").value;
	        		var mailUserName = document.getElementById("mailUserName").value;
	        		var mailPassword = document.getElementById("mailPassword").value;
	        		let showUSAInBillShipAddress = document.getElementById("showUSAInBillShipAddress").checked;
	        		let multiUserConnection = 0;
	        		if(document.getElementById("multiUserConnection2").checked){
	        		    multiUserConnection = 1;
	        		}
	        	    /* document.getElementById('tabid').value="SaveConfigurationGeneral";
	        		document.forms[0].action = "Configuration";
	        		document.forms[0].submit(); */
	        		window.location.href= "${pageContext.request.contextPath}/Configuration?tabid=SaveConfigurationGeneral&salesOrderBoard="+salesOrderBoard
	        		    +"&itemReceivedBoard="+itemReceivedBoard+"&itemShippedBoard="+itemShippedBoard+"&poboard="+poboard+"&currencyID="+currencyID+"&weightID="+weightID
	        			+"&defaultLabelID="+defaultLabelID+"&filterOption="+filterOption+"&moduleID="+moduleID+"&mailServer="+mailServer+"&senderEmail="+senderEmail
	        			+"&mailUserName="+mailUserName+"&mailPassword="+mailPassword+"&showUSAInBillShipAddress="+showUSAInBillShipAddress+"&multiUserConnection="+multiUserConnection;
	            },
	            "<spring:message code='BzComposer.global.cancel'/>": function () {
	                $(this).dialog("close");
	                return false;
	            }
	        }
	});
	return false;
}
function SaveSecurityValues(){
	debugger;
	event.preventDefault();
	$("#showsaverecorddialog").dialog({
        resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                var multiUserConnection = 0;
                if(document.getElementById("multiUserConnection2").checked){
                    multiUserConnection = 1;
                }
                var formData = $('form').serialize();
                $.ajax({
                    type : "POST",
                    url :  "ConfigurationAjax/SaveConfiguration?tabid=SaveConfiguration&multiUserConnection="+multiUserConnection,
                    data : formData,
                    success : function(data) {
                        $("#showsaverecorddialog").dialog("close");
                        $("#showsuccessdialog").dialog({
                            resizable: false,
                            height: 200,
                            width: 500,
                            modal: true,
                            buttons: {
                                "<spring:message code='BzComposer.global.ok'/>": function () {
                                    $(this).dialog("close");
                                    return false;
                                },
                                "<spring:message code='BzComposer.global.cancel'/>": function () {
                                    $(this).dialog("close");
                                    return false;
                                }
                            }
                        });
                    },
                    error : function(data) {
                        alert("<spring:message code='BzComposer.common.erroroccurred'/>");
                    }
                });
            },
            <spring:message code='BzComposer.global.cancel'/>: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}

function SaveValuesFeatures()
{
	if(confirm('<spring:message code="BzComposer.configuration.saveconfirm"/>'))
	{
		event.preventDefault();
		$("#showsaverecorddialog").dialog({
		    	resizable: false,
		        height: 200,
		        width: 500,
		        modal: true,
		        buttons: {
		        	"<spring:message code='BzComposer.global.ok'/>": function () {
		            	document.configurationForm.selectedModules.value = document.configurationForm.selectedModules.value;
		        		document.configurationForm.selectedModuleId.value = document.configurationForm.selectedModuleId.value;

		        		var x = document.getElementById("selectedModules");
		        	    var txt = "";
		        	    var i;
		        	    for (i = 0; i < x.length; i++)
		        	    {
		        	        txt = txt + "\n" + x.options[i].text;
		        	    }
		        	    document.configurationForm.selectedModules.value = txt;

		        	    var x = document.getElementById("selectedModuleId");
		        	    var txt1 = "";
		        	    var i;
		        	    for (i = 0; i < x.length; i++)
		        	    {
		        	        txt1 = txt1 + "\n" + x.options[i].text;
		        	    }

						//$('form').submit();
		            },
		            "<spring:message code='BzComposer.global.cancel'/>": function () {
		                $(this).dialog("close");
		                return false;
		            }
		        }
		    });
		    return false;
	}
}
function RevokeValues(){
	document.getElementById('tid').value="config";
	document.forms[0].action = "Configuration";
	document.forms[0].submit();
}

function SetLabelName(lblid){
	size = document.getElementById('lblsize').value;
	for(cnt=0;cnt<size;cnt++){
		lid = document.getElementById(cnt+'lid').value;
		if(lblid == lid){
			document.configurationForm.labelName.value =  document.getElementById(cnt+'lname').value;
			break;
		}
	}
}

function updateComapanySecurity()
{
    debugger;
	var password = document.getElementById("password").value;
	var confirmPassword = document.getElementById("confirmPassword").value;

	var errorMessage="";
	if(password == "" || password == null || confirmPassword == "" || confirmPassword == null){
        errorMessage = "<h3><spring:message code='BzComposer.signin.pleaseenterpassword'/></h3>";
    }
    else if(password != confirmPassword){
        errorMessage = "<h3><spring:message code='BzComposer.common.bothPwdsNotMatch'/></h3>";
    }
	if(errorMessage !=""){
		document.getElementById("errorsPWD").innerHTML = errorMessage;
		return false;
		event.preventDefault();
	}
	else
	{
		$.ajax({
			type: "POST",
			url:"updateEditedCompanyinfo?tabid=editSecurity&password="+password,
 			success : function() {
 			    document.getElementById("errorsPWD").innerHTML = "<h3>Record updated</h3>";
	   		},
			error : function(data) {
				event.preventDefault();
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

function ValidateEmail(email) {
    var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return expr.test(email);
}
function copyPhoneNumber(form){
    if(form.sameAsPhoneNumber.checked){
        form.cellPhone.value = form.phone.value
    }
}
function setPhonePattern(phoneItem){
    phone = phoneItem.value;
	if(phone.length < 10 || phone.length > 14){
		event.preventDefault();
		$("#enterPhoneNumber").dialog({
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
	let cleaned = ('' + phone).replace(/\D/g, '');
	let match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
	if (match) {
		phoneItem.value = '('+ match[1] +') '+ match[2] +'-'+ match[3];
  	}
}

// form fields description structure
var a_fields = {
	'uname' : {
		'l': 'Login Name',  // label
		'r': true,    // required
		'f': 'alphanum',  // format (see below)
		't': 't_uname',// id of the element to highlight if input not validated
		'm': null,     // must match specified form field
		'mn': 4,       // minimum length
		'mx': 16       // maximum length
	},
	'pass' : {'l':'Password','r':true,'f':'alphanum','t':'t_password','m':'pass_con','mn':'6','mx':'16'},
	'pass_con' : {'l':'Password confirm','r':true,'f':'alphanum','t':'t_password_copy','mn':'6','mx':'16'},
	'email' : {'l':'E-mail','r':true,'f':'email','t':'t_email','m':'email_con'},
	'email_con' : {'l':'E-mail confirm','r':true,'f':'email','t':'t_emailcon'},
	'question' : {'l':'Password Reminder Question','r':true,'f':'alphanum','t':'t_question'},
	'answer' : {'l':'Answer','r':true,'f':'alphanum','t':'t_answer'}
},
o_config = {
	'to_disable' : ['Submit'],
	'alert' : 1
}
// validator constructor call
var v = new validator('login', a_fields, o_config);

//========================================== Security-Module-JS =================================================
function adduser1() {
	debugger;
	var membershipLevel = "<%= request.getAttribute("membershipLevel")%>";
	var size = "<%= request.getAttribute("userSize")%>";
	if(membershipLevel == "Standard"){
		if(size>=1){
			debugger;
			return maxnumberofuserdialog();
		}else{
			$('#AddUser').modal('show');
		}
	}else if(membershipLevel == "Professional"){
		if(size>=5){
			debugger;
			return maxnumberofuserdialog();
		}else{
			$('#AddUser').modal('show');
		}
	}else if(membershipLevel == "Enterprise"){
		if(size>=10){
			debugger;
			return maxnumberofuserdialog();
		}else{
			$('#AddUser').modal('show');
		}
	}
}
function Deleteuser() {
    if($('#selectedUserId').val() == ''){
        alert("<spring:message code='BzComposer.common.selectUserFirst'/>");
        return false;
    }
    $("#showDeleteGroupConfirmDialog").dialog({
        resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                window.location.href = "Configuration?tabid=deleteUser&selectedUserId="+$('#selectedUserId').val()+"&userGroupId="+$('#userGroupId').val();
            },
            <spring:message code='BzComposer.global.cancel'/>: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
}

function checkValidation(){
    debugger;
    var userEmail = $("#userEmail").val();
    var password1 = $("#userPassword").val();
    var password2 = $("#cpwd").val();
    var groupID = $("#groupID").val();
    var adminPWD = $("#adminpassword").val();
    var AdminPassword = '<%= request.getAttribute("AdminPassword")%>';
    var checkEmailAddress = (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(userEmail));
    if (checkEmailAddress == false){
        alert("<spring:message code='BzComposer.common.enterValidEmail'/>");
        return;
    }
    else if(userEmail == ""){
        alert("<spring:message code='BzComposer.common.emailCantBlank'/>");
        return;
    }
    else if(password1.length < 6){
        alert("<spring:message code='BzComposer.common.passwordHint'/>");
        return;
    }
    else if(password1 != password2){
         alert("<spring:message code='BzComposer.common.bothPwdsNotMatch'/>");
         alert(password1+'\n'+password2);
         return;
    }
    else if(adminPWD != AdminPassword){
         alert("<spring:message code='BzComposer.common.enterValidAdminPwd'/>");
         return;
    }
    else if(groupID == "" || groupID == 0){
        alert("<spring:message code='BzComposer.common.selectGroupFirst'/>");
        return;
    }
    else{
        var formData = $('addnewuser1').serialize();
        $.ajax({
            type : "POST",
            url : "ConfigurationAjax/SaveConfiguration?tabid=addNewUser&userName="+userEmail+"&userpassword="+password1+"&groupID="+groupID,
            data : formData,
            success : function(data) {
                if(data == 'emailExists'){
                    $("#showEmailAlreadyExistsDialog").dialog({
                        resizable: false,
                        height: 200,
                        width: 500,
                        modal: true,
                        buttons: {
                            "<spring:message code='BzComposer.global.ok'/>": function () {
                                $(this).dialog("close");
                                return false;
                            }
                        }
                    });
                }else{
                    $("#showsuccessdialog").dialog({
                        resizable: false,
                        height: 200,
                        width: 500,
                        modal: true,
                        buttons: {
                            "<spring:message code='BzComposer.global.ok'/>": function () {
                                $(this).dialog("close");
                                window.location.href= "Configuration?tabid=config&tab=tr2";
                                return false;
                            },
                            "<spring:message code='BzComposer.global.cancel'/>": function () {
                                $(this).dialog("close");
                                window.location.href= "Configuration?tabid=config&tab=tr2";
                                return false;
                            }
                        }
                    });
                }
            },
            error : function(data) {
                alert("<spring:message code='BzComposer.common.erroroccurred'/>");
                return false;
            }
        });
    }
}

function getData(){
    var modalNewPassword = document.getElementById('modalNewPassword').value;
    var modalConfirmPassword = document.getElementById('modalConfirmPassword').value;
    window.location.href= "Configuration?tabid=ChangeAdministratorPassword&modalNewPassword="+modalNewPassword;
}

function viewGroupPermissions(){
    if($('#groupID').val() == ''){
        alert("<spring:message code='BzComposer.common.selectGroupFirst'/>");
    }else{
        window.open("Configuration?tabid=addNewGroup&selectedGroupId="+$('#groupID').val()+"&isViewGroupPermissions=true",null,"scrollbars=yes,height=600,width=1200,status=yes,toolbar=no,menubar=no,location=no" );
    }
}

function addNewGroup(){
    window.open("Configuration?tabid=addNewGroup",null,"scrollbars=yes,height=600,width=1200,status=yes,toolbar=no,menubar=no,location=no" );
}
function editGroup(){
    if($('#selectedGroupId').val()>0){
        window.open("Configuration?tabid=addNewGroup&selectedGroupId="+$('#selectedGroupId').val(),null,"scrollbars=yes,height=600,width=1200,status=yes,toolbar=no,menubar=no,location=no" );
    }else{
        alert("<spring:message code='BzComposer.common.selectGroupFirst'/>");
    }
}

function selectUserData(selectedUserId, userGroupId, rID){
    var size = document.getElementById('usersSize').value;
    for(i=0; i<size; i++){
        if(document.getElementById(i+"usrIndex").classList.contains('draft')){
            document.getElementById(i+"usrIndex").classList.remove('draft');
        }
    }
    document.getElementById(rID).className = "draft";
    document.getElementById('selectedUserId').value = selectedUserId;
    document.getElementById('userGroupId').value = userGroupId;
}

function selectGroupData(selectedGroupId, rID){
    var size = document.getElementById('groupSize').value;
    for(i=0; i<size; i++){
        if(document.getElementById(i+"gpIndex").classList.contains('draft')){
            document.getElementById(i+"gpIndex").classList.remove('draft');
        }
    }
    document.getElementById(rID).className = "draft";
    document.getElementById('selectedGroupId').value = selectedGroupId;
}

function deleteGroup(){
    if($('#selectedGroupId').val() == '' || $('#selectedGroupId').val() == 0){
        alert("<spring:message code='BzComposer.common.selectGroupFirst'/>");
        return false;
    }
    $("#showDeleteGroupConfirmDialog").dialog({
        resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                window.location.href = "Configuration?tabid=deleteGroup&selectedGroupId="+$('#selectedGroupId').val();
            },
            <spring:message code='BzComposer.global.cancel'/>: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
}

function maxnumberofuserdialog()
{
	event.preventDefault();
	$("#maxnumberofuserdialog").dialog({
    	resizable: false,
        height: 250,
        width: 800,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
</script>
</html>
<!-- Dialog box used in this page -->
<div id="showsaverecorddialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.saveconfirm"/></p>
</div>
<div id="serverConnectedErrorDialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.manageservicetype.serverconnectederror"/></p>
</div>
<div id="serverConnectedSeccessDialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.manageservicetype.serverconnectedsuccess"/></p>
</div>
<div id="pleaseWaitDialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.pleaseWait"/></p>
</div>
<div id="errorOccurred" style="display:none;font-size:1em;">
	<p><spring:message code="BzComposer.common.erroroccurred"/></p>
</div>
<div id="enterPhoneNumber" style="display:none;font-size:1em;">
	<p><spring:message code="BzComposer.companyinfo.enterphonenumber"/></p>
</div>
<div id="enterZip" style="display:none;font-size:1em;">
	<p><spring:message code="err.Zip"/></p>
</div>
<div id="showsuccessdialog" style="display:none;">
	<p><spring:message code='BzComposer.common.recordUpdated'/></p>
</div>
<div id="maxnumberofuserdialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.networksecurity.maxnumberofuser"/></p>
</div>
<div id="showDeleteGroupConfirmDialog" style="display:none;">
	<p><spring:message code="BizComposer.PurchaseOrder.Delete.Validation"/></p>
</div>
<div id="showEmailAlreadyExistsDialog" style="display:none;">
	<p><spring:message code="BzComposer.common.emailAlreadyExists"/></p>
</div>