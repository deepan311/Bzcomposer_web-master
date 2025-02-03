<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/WEB-INF/jsp/include/headlogo.jsp" />
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<jsp:include page="/WEB-INF/jsp/include/menu.jsp" />
<title><spring:message code="BzComposer.accountingtitle" /></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />

<style type="text/css">
.modal { display: none; }
.fonts{ font: 12px; }
.fontsTitle{ font:12px; padding: 5px; }
.highlight0 { background-color: #00CED1 !important;color: #fff; }
.btngroup2-fixed { position: relative; }
#categoryTypeIDRow thead tr th { padding: 7px; font-size: 12px; }
#categoryTypeIDRow tbody tr td { padding: 7px; font-size: 12px;}
#CatListDetailsID thead tr th { padding: 7px; font-size: 12px; }
#CatListDetailsID tbody tr td { padding: 7px; font-size: 12px; }
</style>

<script type="text/javascript">
let okButtonMsg = "<spring:message code='BzComposer.global.ok'/>";
let cancelButtonMsg = "<spring:message code='BzComposer.global.cancel'/>";
let categoryUsedForOtherInvoice = "<spring:message code='BzComposer.categorymanager.categoryusedforotherinvoice'/>";
let categoryNameExistMsg = "<spring:message code='BzComposer.categorymanager.categorynameexist'/>";
let itemCantDeleteMsg = "<spring:message code='BzComposer.categorymanager.itemcantdelete'/>";
let someErrorOccurredMsg = "<spring:message code='BzComposer.reconciliation.someerroroccurred'/>";
let selectCategoryMsg = "<spring:message code='BzComposer.categorymanager.selectcategory'/>";
let wantToDeleteMsg = "<spring:message code='BzComposer.categorymanager.wanttodelete'/>";

$(function() {
    $("#tabs1").tabs();
    setTimeout(function(){
        let activeTabID = sessionStorage.getItem("activeTabID");
        if(activeTabID != null && activeTabID != ''){
            $("#"+activeTabID).find('a').click();
        }
    }, 1000);
});
function toggleFunction() {
	debugger;
  var x = document.getElementById("divtoggle");
  var lftmenu = document.getElementById("leftMenu");
  if (x.style.display === "none") {
    x.style.display = "block";
    lftmenu.style.width = "180px";
    lftmenu.style.position = "relative";
  } else {
    x.style.display = "none";
    lftmenu.style.width = "0";
    lftmenu.style.position = "absolute";
  }
} 

$(document).ready(function()
{
	var id = '<%= request.getAttribute("selectedId")%>';
	var pId = '<%=request.getAttribute("paymentId")%>';
	var cateID = '<%= request.getAttribute("cateId")%>';
	
	var arCatID = '<%= request.getAttribute("arCatId")%>';
	var poCatID = '<%= request.getAttribute("poCatId")%>';
	var bpCatID = '<%= request.getAttribute("bpCatId")%>';
	
	var arReceive = '<%= request.getAttribute("arReceiveType")%>';
	var poReceive = '<%= request.getAttribute("poReceiveType")%>';
	var bpReceive = '<%= request.getAttribute("bpReceiveType")%>';
	
	var arDeposit = '<%= request.getAttribute("arDepositTo")%>';
	var poDeposit = '<%= request.getAttribute("poDepositTo")%>';
	var bpDeposit = '<%= request.getAttribute("bpDepositTo")%>';
	
	$('select[id="defaultDepositToId"]').find('option[value="'+id+'"]').attr("selected",true);
	$('select[id="defaultPaymentMethodId"]').find('option[value="'+pId+'"]').attr("selected",true);
	$('select[id="defaultCategoryId"]').find('option[value="'+cateID+'"]').attr("selected",true);
	
	$('select[id="arCategory"]').find('option[value="'+arCatID+'"]').attr("selected",true);
	$('select[id="poCategory"]').find('option[value="'+poCatID+'"]').attr("selected",true);
	$('select[id="bpCategory"]').find('option[value="'+bpCatID+'"]').attr("selected",true);
	
	$('select[id="arReceivedType"]').find('option[value="'+arReceive+'"]').attr("selected",true);
	$('select[id="poReceivedType"]').find('option[value="'+poReceive+'"]').attr("selected",true);
	$('select[id="bpReceivedType"]').find('option[value="'+bpReceive+'"]').attr("selected",true);
	
	$('select[id="arDepositTo"]').find('option[value="'+arDeposit+'"]').attr("selected",true);
	$('select[id="poDepositTo"]').find('option[value="'+poDeposit+'"]').attr("selected",true);
	$('select[id="bpDepositTo"]').find('option[value="'+bpDeposit+'"]').attr("selected",true);
	
	if(document.configurationForm.scheduleDays.value == 0)
	{
		$("#noNeedToSetup").prop("checked",true);
		$("#needSetup").prop("checked",false);
	}
	else
	{
		$("#needSetup").prop("checked",true);
		$("#noNeedToSetup").prop("checked",false);
	}
	
	if(document.configurationForm.reimbursementSettings.value == 2)
	{
		$("#askWhatToDo").prop("checked",true);
		$("#dontAddAny").prop("checked",false);	
		$("#timeForPrompt").prop("checked",false);
	}
	else if(document.configurationForm.reimbursementSettings.value == 1)
	{
		$("#askWhatToDo").prop("checked",false);
		$("#dontAddAny").prop("checked",true);
		$("#timeForPrompt").prop("checked",false);
	}
	else
	{
		$("#askWhatToDo").prop("checked",false);
		$("#dontAddAny").prop("checked",false);
		$("#timeForPrompt").prop("checked",true);
	}
	
	if(document.configurationForm.scheduleDays.value >0)
	{
		$("#scheduleDays").prop("checked",true);
		var scheduleDays = $("#scheduleDays").val();
		document.configurationForm.scheduleDays.value = scheduleDays;
	}
	else
	{
		$("#noNeedToSetup").prop("checked",true);
		document.configurationForm.scheduleDays.value = 0;
	}
	
	/* if($("#noNeedToSetup").prop("checked",true))
	{
		document.configurationForm.scheduleDays.value = 0;
	}
	else
	{
		var scheduleDays = $("#scheduleDays").val();
		document.configurationForm.scheduleDays.value = scheduleDays;
	} */
	
	if(document.configurationForm.reimbursementSettings.value == 2)
	{
		$("#askWhatToDo").prop("checked",true);
		document.configurationForm.reimbursementSettings.value = 2;
	}
	
	else if(document.configurationForm.reimbursementSettings.value == 1)
	{
		$("#dontAddAny").prop("checked",true);
		document.configurationForm.reimbursementSettings.value = 1;
	}
	
	else
	{
		$("#timeForPrompt").prop("checked",true);
		document.configurationForm.reimbursementSettings.value = 0;
	}
	
	/* if($("#askWhatToDo").prop("checked",true))
	{
		document.configurationForm.reimbursementSettings.value = 2;
	}
	else if($("#dontAddAny").prop("checked",true))
	{
		document.configurationForm.reimbursementSettings.value = 1;
	}
	else
	{
		document.configurationForm.reimbursementSettings.value = 0;
	} */
	
	document.configurationForm.showCombinedBilling.value = '<%= request.getAttribute("showCmbValue")%>';
	var template = '<%= request.getAttribute("billingTemplateID")%>';
	 
	$('select[id="showBillingStatStyle"]').find('option[value="'+template+'"]').attr("selected",true);
});
 
 function numbersonly(e,val)
 {
 	var temp=val.indexOf(".");
 	var unicode=e.charCode? e.charCode : e.keyCode;
 	if (unicode!=8)
 	{
  		//if the key isn't the backspace key (which we should allow)
 		if(unicode==46 && temp==-1)
 		{
  			return true;
 		} 
 		else 
 		if (unicode<48||unicode>57) //if not a number
 			return false; //disable key press
 	}
 }
 
 /* Added on 04-05-2020 */
 function setDiv()
	{
		var value = $("#stores option:selected").val();

		if(value == 39)
		{
			document.getElementById("forOnlineOption").style.display = "block";
			document.getElementById("forCdromusaOption").style.display = "none";
		}
		else
		{
			document.getElementById("forOnlineOption").style.display = "none";
			document.getElementById("forCdromusaOption").style.display = "block";
		}
	}
	function seteSalesStores()
	{
		$('select[id="eSalesChannels"]').find('option').attr("selected",false);
		var eSalesStore = $.trim($("#eSalesStoreId option:selected").text());	//selected select option value
		var selectedeSalesStore = $.trim($("#eSalesStoreId option:selected").val()); //selected select option id
		var ab = $.trim($('select[id="eSalesStoreId"]').find('option[id="'+selectedeSalesStore+'"]').val());	//abbreviation of selected option 
		
		var storeChannel = $.trim($('select[id="eSalesStoreId"]').find('option[id="'+eSalesStore+'"]').val());
		

		
		$('select[id="eSalesStoreId"]').find('option[id="'+selectedeSalesStore+'"]').attr("selected",true);
		$('select[id="eSalesStoreId"]').find('option[id="'+eSalesStore+'"]').attr("selected",true);
		$('select[id="eSalesChannels"]').find('option[id="'+storeChannel+'"]').attr("selected",true);
		
		$("#storeTypeName").val(eSalesStore);
		$("#eSalesAbbreviation").val(ab);
		
	}
	
	function clearFields()
	{
		$("#storeTypeName").val("");
		$("#eSalesAbbreviation").val("");
		$("#btnAddeBayCategory").attr('disabled',false);
	}
</script>
</head>
<body onload="init();">
<!-- begin shared/header -->
<form:form name="configurationForm" enctype="MULTIPART/FORM-DATA" method="post" id="accountPaymentfrm" modelAttribute="configDto">
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div class="fontsTitle">
	<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.configuration.configurationtitle"/>
	</span>
</div>
<div>
	<div>
		<c:if test="${not empty Labels}">
            <input type="hidden" name="lsize" id="lblsize" value='${Labels}' />
            <c:forEach items="${Labels}" var="lbl" varStatus="loop">
                <input type="hidden" id='${loop.index}lid' name='${loop.index}lidname' value='${lbl.value}' />
                <input type="hidden" id='${loop.index}lname' name='${loop.index}lnm' value='${lbl.label}' />
            </c:forEach>
		</c:if>
	</div>
	<div id="table-negotiations" style="padding: 0; border: 1px solid #ccc;">
		<span style="font-size:30px;cursor:pointer; margin-left: 20px;" onclick="toggleFunction()">&#9776;</span>
		<table cellspacing="0"  style="border: 0;width: 100%;overflow-y:scroll;" class="section-border">
			<tr>
				<td id="leftMenu" style="position: relative; width: 180px;vertical-align:top;">
				<table>
					<tr>
						<td>
							<jsp:include page="menuPage.jsp" />
						</td>
					</tr>
				</table>
				</td>
				<td valign="top" >
					<!-- Account&Payment Starts -->
					<div id="accountPayment"  style="display:none;padding: 0; position: relative; left: 0;" >
						<div id="tabs1" style="height:auto;">
  							<ul>
    							<li style="font-size: 12px;" id="tabNo1"><a href="#AccountSetting"><spring:message code="BzComposer.configuration.tab.accountsetting" /></a></li>
    							<li style="font-size: 12px;" id="tabNo2"><a href="#AccountReceivable"><spring:message code="BzComposer.configuration.tab.accountrecivable" /></a></li>
    							<li style="font-size: 12px;" id="tabNo3"><a href="#POPayable"><spring:message code="BzComposer.configuration.tab.popayable" /></a></li>
    							<li style="font-size: 12px;" id="tabNo4"><a href="#billing"><spring:message code="BzComposer.accountreceivable.billing" /></a</li>
    							<li style="font-size: 12px;" id="tabNo5"><a href="#CategoryManager"><spring:message code="BzComposer.CategoryManager" /></a</li>
    							<li style="font-size: 12px;display:none;"><a href="#PaymentType"><spring:message code="BzComposer.popayable.paymenttype" /></a></li>
   								<li style="font-size: 12px;display:none;"><a href="#ReceivedType"><spring:message code="BzComposer.configuration.tab.receivedtype" /></a></li>
  							</ul>
							<div id="AccountSetting">
								<div id="content1" class="tabPage">
   									<!-- add here the content of first tab -->
										<table class="table-notifications" width="100%">
											<tr>
												<th colspan="4" align="left" style="font-size: 12px; padding: 5px;">
													<spring:message code="BzComposer.configuration.tab.accountsetting" />
												</th>
											</tr>
											<tr>
												<td style="font-size: 12px;">
													<spring:message code="BzComposer.configuration.defaultpaymentmethod" />:
												</td>
												<td style="font-size: 12px;">
													<form:select path="defaultPaymentMethodId" id="defaultPaymentMethodId">
														<c:if test="${not empty configDto.listOfExistingAccounts}">
														    <c:forEach items="${configDto.listOfExistingAccounts}" var="objList1">
																<option value="${objList1.accountNumber}">${objList1.accountName}</option>
															</c:forEach>
														</c:if>
													</form:select>
												</td>
												<td style="font-size: 12px;">
													<spring:message code="BzComposer.configuration.defaultreceivetype"/>:
												</td>
												<td style="font-size: 12px;">
													<form:select path="selectedPaymentId">
													    <c:if test="${not empty configDto.listOfExistingPayment}">
                                                            <c:forEach items="${configDto.listOfExistingPayment}" var="objList1">
                                                                <option value="${objList1.paymentId}">${objList1.paymentName}</option>
                                                            </c:forEach>
                                                        </c:if>
													</form:select>
												</td>
											</tr>
											<tr>
												<td style="font-size: 12px;">
													<spring:message code="BzComposer.configuration.defaultcategory" />:
												</td>
												<td style="font-size: 12px;">
													<form:select path="defaultCategoryId" id="defaultCategoryId">
														<c:if test="${not empty configDto.listOfExistingCategory}">
                                                            <c:forEach items="${configDto.listOfExistingCategory}" var="objList1">
                                                                <option value="${objList1.selectedCategoryId}">${objList1.categoryName}&nbsp;${objList1.categoryNumber}</option>
                                                            </c:forEach>
                                                        </c:if>
													</form:select>
												</td>
												<td style="font-size: 12px;">
													<spring:message code="BzComposer.configuration.depositto"/>:
												</td>
												<td style="font-size: 12px;">
													<form:select path="defaultDepositToId" id="defaultDepositToId">
													    <c:if test="${not empty configDto.listOfExistingAccounts}">
                                                            <c:forEach items="${configDto.listOfExistingAccounts}" var="objList1">
                                                                <option value="${objList1.defaultDepositToId}">${objList1.accountName}</option>
                                                            </c:forEach>
                                                        </c:if>
													</form:select>
												</td>
											</tr>
											<tr>
												<th colspan="4" align="left" style="font-size: 12px;  padding: 5px;"><spring:message code="BzComposer.configuration.paymentschdulesetting" /></th>
											</tr>
											<tr colspan="2">
												<td style="font-size: 12px;">
													<form:radiobutton path="needSetUp" id="noNeedToSetup" value="0" />
													&nbsp;<spring:message code="BzComposer.configuration.noneedtosetup"/>
												</td>
											</tr>
											<tr colspan="2">
												<td style="font-size: 12px;">
													<form:radiobutton path="needSetUp" id="needSetup" value="0" />&nbsp;
													<form:input path="scheduleDays" id="scheduleDays" />
													&nbsp;<spring:message code="BzComposer.configuration.daysbeforeduedate"/>
												</td>
											</tr>
										<tr>
											<th colspan="4" align="left" style="font-size: 12px; padding: 5px;">
												<spring:message code="BzComposer.configuration.reimbursementsettings" />
											</th>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<form:radiobutton path="reimbursementSettings" value="0" id="timeForPrompt" />
												&nbsp;<spring:message code="BzComposer.configuration.reimbursementsettings.timeforprompt"/>
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<form:radiobutton path="reimbursementSettings" value="1" id="dontAddAny" />
												&nbsp;<spring:message code="BzComposer.configuration.reimbursementsettings.dontaddany"/>
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<form:radiobutton path="reimbursementSettings" value="2" id="askWhatToDo" />
												&nbsp;<spring:message code="BzComposer.configuration.reimbursementsettings.askwhattodo"/>
											</td>
										</tr>
										<tr>
											<th colspan="4" align="left" style="font-size: 12px; padding: 5px;">
												<spring:message code="BzComposer.configuration.setubudgetyear" />
											</th>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.startmonth"/>:
											</td>
											<td style="font-size: 12px;">
												<form:select path="startMonth" id="startMonth">
													<form:option value="0"><spring:message code="BzComposer.configuration.month.january"/></form:option>
													<form:option value="1"><spring:message code="BzComposer.configuration.month.february"/></form:option>
													<form:option value="2"><spring:message code="BzComposer.configuration.month.march"/></form:option>
													<form:option value="3"><spring:message code="BzComposer.configuration.month.april"/></form:option>
													<form:option value="4"><spring:message code="BzComposer.configuration.month.may"/></form:option>
													<form:option value="5"><spring:message code="BzComposer.configuration.month.june"/></form:option>
													<form:option value="6"><spring:message code="BzComposer.configuration.month.july"/></form:option>
													<form:option value="7"><spring:message code="BzComposer.configuration.month.august"/></form:option>
													<form:option value="8"><spring:message code="BzComposer.configuration.month.september"/></form:option>
													<form:option value="9"><spring:message code="BzComposer.configuration.month.october"/></form:option>
													<form:option value="10"><spring:message code="BzComposer.configuration.month.november"/></form:option>
													<form:option value="11"><spring:message code="BzComposer.configuration.month.december"/></form:option>
												</form:select>
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.endmonth"/>:
											</td>
											<td style="font-size: 12px;">
												<form:select path="endMonth" id="endMonth">
													<form:option value="0"><spring:message code="BzComposer.configuration.month.january"/></form:option>
													<form:option value="1"><spring:message code="BzComposer.configuration.month.february"/></form:option>
													<form:option value="2"><spring:message code="BzComposer.configuration.month.march"/></form:option>
													<form:option value="3"><spring:message code="BzComposer.configuration.month.april"/></form:option>
													<form:option value="4"><spring:message code="BzComposer.configuration.month.may"/></form:option>
													<form:option value="5"><spring:message code="BzComposer.configuration.month.june"/></form:option>
													<form:option value="6"><spring:message code="BzComposer.configuration.month.july"/></form:option>
													<form:option value="7"><spring:message code="BzComposer.configuration.month.august"/></form:option>
													<form:option value="8"><spring:message code="BzComposer.configuration.month.september"/></form:option>
													<form:option value="9"><spring:message code="BzComposer.configuration.month.october"/></form:option>
													<form:option value="10"><spring:message code="BzComposer.configuration.month.november"/></form:option>
													<form:option value="11"><spring:message code="BzComposer.configuration.month.december"/></form:option>
												</form:select>
											</td>
										</tr>
									</table>	
								</div>  
							</div>
		
					  		<div id="AccountReceivable">
					   			<div id="content2" class="tabPage">
					   				<!-- add here the content of second tab -->		
					   				<table class="table-notifications" width="100%">
										<tr>
											<th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
												<spring:message code="BzComposer.configuration.tab.accountrecivable" />
											</th>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.category" />:
											</td>
											<td style="font-size: 12px;">
												<form:select path="arCategory" id="arCategory">
												    <c:if test="${not empty configDto.listOfExistingCategory}">
                                                        <c:forEach items="${configDto.listOfExistingCategory}" var="objList1">
                                                            <option value="${objList1.arCategory}">${objList1.categoryName}&nbsp;${objList1.categoryNumber}</option>
                                                        </c:forEach>
                                                    </c:if>
												</form:select>
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.defaultreceivetype"/>:
											</td>
											<td style="font-size: 12px;">
												<form:select path="arReceivedType" id="arReceivedType">
												    <c:if test="${not empty configDto.listOfExistingPayment}">
                                                        <c:forEach items="${configDto.listOfExistingPayment}" var="objList1">
                                                            <option value="${objList1.arReceivedType}">${objList1.paymentName}</option>
                                                        </c:forEach>
                                                    </c:if>
												</form:select>
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.depositto" />:
											</td>
											<td style="font-size: 12px;">
												<form:select path="arDepositTo" id="arDepositTo">
												    <c:if test="${not empty configDto.listOfExistingAccounts}">
                                                        <c:forEach items="${configDto.listOfExistingAccounts}" var="objList1">
                                                            <option value="${objList1.arDepositTo}">${objList1.accountName}</option>
                                                        </c:forEach>
                                                    </c:if>
												</form:select>
											</td>
										</tr>
									</table>
								</div>  
							</div>
							<div id="POPayable">
								<div id="content3" class="tabPage">
								<!-- add here the content of first tab -->
									<table class="table-notifications" width="100%">
										<tr>
											<th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
												<spring:message code="BzComposer.configuration.tab.popayable" />
											</th>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.category" />:
											</td>
											<td style="font-size: 12px;">
												<form:select path="poCategory" id="poCategory">
												    <c:if test="${not empty configDto.listOfExistingCategory}">
                                                        <c:forEach items="${configDto.listOfExistingCategory}" var="objList1">
                                                            <option value="${objList1.poCategory}">${objList1.categoryName}&nbsp;${objList1.categoryNumber}</option>
                                                        </c:forEach>
                                                    </c:if>
												</form:select>
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.defaultreceivetype"/>:
											</td>
											<td style="font-size: 12px;">
												<form:select path="poReceivedType" id="poReceivedType">
												    <c:if test="${not empty configDto.listOfExistingPaymentGeneralAccount}">
                                                        <c:forEach items="${configDto.listOfExistingPaymentGeneralAccount}" var="objList1">
                                                            <option value="${objList1.poReceivedType}">${objList1.paymentName}</option>
                                                        </c:forEach>
                                                    </c:if>
												</form:select>
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.depositto" />:
											</td>
											<td style="font-size: 12px;">
												<form:select path="poDepositTo" id="poDepositTo">
												    <c:if test="${not empty configDto.listOfExistingAccounts}">
                                                        <c:forEach items="${configDto.listOfExistingAccounts}" var="objList1">
                                                            <option value="${objList1.poDepositTo}">${objList1.accountName}</option>
                                                        </c:forEach>
                                                    </c:if>
												</form:select>
											</td>
										</tr>
									</table>			
								</div>  
							</div>
							<div id="billing">
							    <div id="content4" class="tabPage">
								<table class="table-notifications" width="80%">
									<tr>
										<th colspan="2" align="left" style="font-size:12px; padding: 5px;">
										    <spring:message code="BzComposer.accountreceivable.billing" />
										</th>
									</tr>
									<tr>
										<td style="font-size:12px;">
											<spring:message code="BzComposer.configuration.startbillinginvoicenumber"/>:
										</td>
										<td colspan="2" align="center" style="font-size:12px;">
											<form:input path="startingBillNumber" />
										</td>
									</tr>
									<tr>
										<th colspan="2" align="left" style="font-size:12px; padding: 5px;"><spring:message code="BzComposer.configuration.billingstatementpreference" /></th>
									</tr>
									<tr>
										<td colspan="2" style="font-size:12px;">
											<input type="checkbox" name="showCombinedBilling" id="showCombinedBilling" value="${configDto.showCombinedBilling}" ${configDto.showCombinedBilling=='on'?'checked':''} />
											<spring:message code="BzComposer.configuration.showbillingstatement"/>
										</td>
									</tr>
									<tr>
										<td style="font-size:12px;">
											<spring:message code="BzComposer.configuration.defaultbillingtemplateforprint"/>
										</td>
										<td colspan="2" align="center" style="font-size:12px;">
											<form:select path="showBillingStatStyle" id="showBillingStatStyle">
											    <c:if test="${not empty configDto.listOfExistingBillingType}">
                                                    <c:forEach items="${configDto.listOfExistingBillingType}" var="objList1">
                                                        <option value="${objList1.showBillingStatStyle}">${objList1.billingTypeName}</option>
                                                    </c:forEach>
                                                </c:if>
											</form:select>
										</td>
									</tr>
									<tr>
										<td style="font-size:12px;">
											<%-- <input type="checkbox" id="mailToCustomer" name="mailToCustomer">
											<spring:message code="Bizcomposer.emailToCustomers"/>&nbsp;&nbsp; --%>
											<input type="checkbox" name="mailToCustomer" id="mailToCustomer" value="${configDto.mailToCustomer}" ${configDto.mailToCustomer=='on'?'checked':''} />
											<spring:message code="BzComposer.configuration.emailtocustomers"/>
											&nbsp;&nbsp;
											<%-- <input type="checkbox" id="printBills" name="printBills">
											<spring:message code="Bizcomposer.printBills"/> --%>
											<input type="checkbox" name="printBills" id="printBills" value="${configDto.printBills}" ${configDto.printBills=='on'?'checked':''} />
											<spring:message code="BzComposer.configuration.printbills"/>
										</td>
									</tr>
									<tr>
                                        <th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
                                            <spring:message code="BzComposer.configuration.defaultaccountsetting" /> (<spring:message code="BzComposer.configuration.tab.billingpayable" />)
                                        </th>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 12px;">
                                            <spring:message code="BzComposer.configuration.category" />:
                                        </td>
                                        <td style="font-size: 12px;">
                                            <form:select path="bpCategory" id="bpCategory">
                                                <c:if test="${not empty configDto.listOfExistingCategory}">
                                                    <c:forEach items="${configDto.listOfExistingCategory}" var="objList1">
                                                        <option value="${objList1.bpCategory}">${objList1.categoryName}&nbsp;${objList1.categoryNumber}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </form:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 12px;">
                                            <spring:message code="BzComposer.configuration.defaultreceivetype"/>:
                                        </td>
                                        <td style="font-size: 12px;">
                                            <form:select path="bpReceivedType" id="bpReceivedType" styleClass="width:50%" >
                                                <c:if test="${not empty configDto.listOfExistingPaymentGeneralAccount}">
                                                    <c:forEach items="${configDto.listOfExistingPaymentGeneralAccount}" var="objList1">
                                                        <option value="${objList1.bpReceivedType}">${objList1.paymentName}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </form:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 12px;">
                                            <spring:message code="BzComposer.configuration.depositto" />:
                                        </td>
                                        <td style="font-size: 12px;">
                                            <form:select path="bpDepositTo" id="bpDepositTo">
                                                <c:if test="${not empty configDto.listOfExistingAccounts}">
                                                    <c:forEach items="${configDto.listOfExistingAccounts}" var="objList1">
                                                        <option value="${objList1.bpDepositTo}">${objList1.accountName}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </form:select>
                                        </td>
                                    </tr>
								</table>
								</div>
							</div>

							<div id="CategoryManager">
                                <div id="content5" class="tabPage">
                                <table class="table-notifications">
                                    <tr>
                                        <th colspan="2" style="font-size:12px; padding: 5px;">
                                            <spring:message code="BzComposer.CategoryManager" />
                                        </th>
                                    </tr>
                                    <tr><td colspan="2">
                                    <div class="mb-3 clear custom-fixed-tabs">
                                        <div class="tab-content">
                                            <div class="table1 table2" id="devCategoryTable" style="width:100%;">
                                            <div class="float-left" style="width:15%;">
                                                <table class="table table-bordered table-sm" id="categoryTypeIDRow">
                                                    <thead class="thead-light">
                                                        <tr>
                                                            <th style="text-align: center;"><spring:message code="BzComposer.categorymanager.tab.categorylist"/></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${categoryType}" var="curObject" varStatus="loop">
                                                            <tr class="hideShowMyCatType" onclick="hideShowMyCat(${curObject.categoryTypeID})">
                                                                <td hidden="categoryTypeID">${curObject.categoryTypeID}</td>
                                                                <td><strong>${curObject.categoryTypeName}</strong></td>
                                                            </tr>
                                                        </c:forEach>
                                                    <tbody>
                                                </table>
                                            </div>
                                            <div style="width:80%;" class="float-right">
                                                <table class="table table-bordered table-sm devAcCategoryListTable" id="CatListDetailsID">
                                                    <thead class="thead-light">
                                                        <tr>
                                                            <th><spring:message code="BzComposer.categorymanager.type"/></th>
                                                            <th><spring:message code="BzComposer.categorymanager.name"/></th>
                                                            <th><spring:message code="BzComposer.categorymanager.categorynumber"/></th>
                                                            <th><spring:message code="BzComposer.categorymanager.budgetgroup"/></th>
                                                            <th><spring:message code="BzComposer.categorymanager.active"/></th>
                                                            <th><spring:message code="BzComposer.categorymanager.description"/></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${listOfCategory}" var="curObject" varStatus="loop">
                                                        <tr class="hideShowMyCat" onclick="selectRow(${curObject.id},${loop.index})">
                                                            <td hidden="categoryId">${curObject.id}</td>
                                                            <td hidden="categoryTypeID">${curObject.categoryTypeID}</td>
                                                            <td value="${curObject.categoryTypeName}">${curObject.categoryTypeName}</td>
                                                            <td>
                                                                <c:if test="${curObject.parent=='root'}"><b>${curObject.name}</b></c:if>
                                                                <c:if test="${curObject.parent!='root'}">^ ${curObject.name}</c:if>
                                                            </td>
                                                            <td>${curObject.categoryNumber}</td>
                                                            <td>${curObject.budgetCategoryName}</td>
                                                            <td>${curObject.active}</td>
                                                            <td>${curObject.description}</td>
                                                            <td hidden="budgetCatId" value="${curObject.budgetCategoryID}">${curObject.budgetCategoryID}</td>
                                                            <td hidden="parentId" value="${curObject.parent}">${curObject.parent}</td>
                                                        </tr>
                                                        </c:forEach>
                                                        <tr><td colspan="6">&nbsp;</td></tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            </div>
                                            <div class="text-center ">
                                                <div class="btngroup2-fixed">
                                                    <button class="btn btn-info" style="font-size: 14px;" id="AddNewCategory">
                                                        <spring:message code="BzComposer.global.add"/>
                                                    </button>
                                                    <button class="btn btn-info" style="font-size: 14px;" id="EditCategory">
                                                        <spring:message code="BzComposer.global.edit"/>
                                                    </button>
                                                    <button class="btn btn-info" style="font-size: 14px;" onclick="return deleteCategory()">
                                                        <spring:message code="BzComposer.global.delete"/>
                                                    </button>
                                                </div>
                                            </div>
                                    	</div>
                                    </div>
                                    </td></tr>
                                </table>
                                </div>
							</div>
							<div id="PaymentType" style="display:none;">
								<div id="content6" class="tabPage">
									<table class="table-notifications">
										<tr>
											<th colspan="4" align="left" style="font-size: 12px; padding: 5px;">
												<spring:message code="BzComposer.configuration.tab.paymenttype" />
											</th>
										</tr>
										<tr>
											<td rowspan="4" style="font-size: 12px;">
												<table border="2" style='height: 100px; width: 300px;'>
													<tr>
														<td align="center">
															<b><spring:message code="BzComposer.configuration.paymenttypename" /></b>
														</td>
														<td align="center">
															<b><spring:message code="BzComposer.configuration.tab.paymenttype" /></b>
														</td>
													</tr>
													<c:if test="${not empty configDto.listOfExistingPaymentType}">
                                                        <c:forEach items="${configDto.listOfExistingPaymentType}" var="objList1">
														<tr>
                                                            <td id="paymentTypeId" value="${objList1.paymentTypeId}" onclick="showType();" align="center" style="font-size: 1em;">
                                                                <label id="pName" name="pName" value="${objList1.paymentTypeId}">
                                                                    ${objList1.paymentName}
                                                                </label>
                                                            </td>
                                                            <td align="center" style="font-size: 12px;">${objList1.paymentType}</td>
														</tr>
														</c:forEach>
													</c:if>
												</table>
											</td>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.tab.paymenttype" /> :
											</td>
											<td style="font-size: 12px;">
												<input type="text" id="paymentType1">
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.type" /> :
											</td>
											<td style="font-size: 12px;">
												<form:select path="listOfExistingCreditCardType">
												    <c:if test="${not empty configDto.listOfExistingCreditCardType}">
                                                        <c:forEach items="${configDto.listOfExistingCreditCardType}" var="objList1">
															<option value="${objList1.creditCardName}">${objList1.creditCardName}</option>
														</c:forEach>
													</c:if>
												</form:select>
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.accountcategory" /> :
											</td>
											<td style="font-size: 12px;">
												<select>
													<option></option>
													<option>1</option>
													<option>2</option>
												</select>
											</td>
										</tr>
										<tr>
											<td style="font-size: 14px;">
												<button type="button" id="Add" name="Add" class="formbutton" style="width: 60px;" readonly>
												    <spring:message code='BzComposer.global.add'/>
												</button>
												&nbsp;&nbsp;
												<button type="button" id="Update" name="Update" class="formbutton" style="width: 60px;">
												    <spring:message code='BzComposer.global.update'/>
												</button>
												&nbsp;&nbsp;
												<button type="button" id="Delete" name="Delete" class="formbutton" style="width: 60px;">
												    <spring:message code='BzComposer.global.delete'/>
												</button>
												&nbsp;&nbsp;
												<button type="button" id="Clear" name="Clear" class="formbutton" style="width: 60px;">
												    <spring:message code='BzComposer.global.clear'/>
												</button>
											</td>
										</tr>
										<tr>
                                            <th colspan="4" align="left" style="font-size: 12px; padding: 5px;">
                                                <spring:message code="BzComposer.configuration.creditcardtype" />
                                            </th>
                                        </tr>
                                        <tr>
                                            <td rowspan="3">
                                                <table border="2" width="300px;">
                                                    <tr>
                                                        <td align="center" style="font-size: 12px;">
                                                            <b><spring:message code="BzComposer.configuration.creditcardtype" /></b>
                                                        </td>
                                                        <td align="center" style="font-size: 12px;">
                                                            <b><spring:message code="BzComposer.configuration.active" /></b>
                                                        </td>
                                                    </tr>
                                                    <c:if test="${not empty configDto.listOfExistingCreditCard}">
                                                        <c:forEach items="${configDto.listOfExistingCreditCard}" var="objList1" varStatus="loop">
                                                        <tr>
                                                            <td id="${objList1.creditCardTypeId}" onclick="showType(this.val);" align="center" style="font-size: 1em;">
                                                                <label id="cType" name="cType" value="${objList1.creditCardName}">
                                                                    ${objList1.creditCardName}
                                                                </label>
                                                            </td>
                                                            <td align="center"><input type="checkbox" name="isActive" id="isActive${loop.index}" value="true" /></td>
                                                        </tr>
                                                        </c:forEach>
                                                    </c:if>
                                                </table>
                                            </td>
                                            <td style="font-size: 12px;">
                                                <spring:message code="BzComposer.configuration.creditcardtype" /> :
                                            </td>
                                            <td>
                                                <input type="text" id="creditCardName" style="font-size: 12px;">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="font-size: 12px;">
                                                <spring:message code="BzComposer.configuration.active" />
                                            </td>
                                            <td>
                                                <input type="checkbox" id="active" checked>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="font-size: 14px;">
                                                <button type="button" class="formbutton" id="Save" name="Save" style="width:60px;">
                                                    <spring:message code="BzComposer.global.save"/>
                                                </button>
                                                &nbsp;&nbsp;
                                                <button type="button" class="formbutton" id="Add" name="Add" style="width:80px;">
                                                    <spring:message code="BzComposer.configuration.addnewbtn"/>
                                                </button>
                                                &nbsp;&nbsp;
                                                <button type="button" class="formbutton" id="Delete" name="Delete" style="width:60px;">
                                                    <spring:message code="BzComposer.global.delete"/>
                                                </button>
                                            </td>
                                        </tr>
										<!-- <tr>
											<td colspan="4" align="center" style="font-size: 12px;">
												<h1>
													<a href="/bzcomposerweb2/Banking?tabid=Banking">
														<spring:message code="BzComposer.configuration.gotobank" />
													</a>
												</h1>
											</td>
										</tr> -->
									</table>
								</div>
							</div>
							<div id="ReceivedType" style="display:none;">
								<div id="content7" class="tabPage">
									<table class="table-notifications">
										<tr>
											<th colspan="4" align="left" style="font-size: 12px; padding: 5px;">
												<spring:message code="BzComposer.configuration.tab.paymenttype" />
											</th>
										</tr>
										<tr>
											<td rowspan="4" style="font-size: 12px;">
												<table border="2" width="300px;">
													<tr>
														<td align="center" style="font-size: 12px;">
															<b><spring:message code="BzComposer.configuration.receivetypename" /></b>
														</td>
														<td align="center" style="font-size: 12px;">
															<b><spring:message code="BzComposer.configuration.tab.receivedtype" /></b>
														</td>
													</tr>
													<c:if test="${not empty configDto.listOfExistingReceivedType}">
                                                        <c:forEach items="${configDto.listOfExistingReceivedType}" var="objList1">
														<tr>
                                                            <td id="paymentTypeId" value="${objList1.paymentTypeId}" onclick="showType();" align="center">
                                                                <label id="pName" name="pName" value="${objList1.paymentTypeId}" style="font-size: 1em;">
                                                                    ${objList1.paymentName}
                                                                </label>
                                                            </td>
                                                            <td align="center">${objList1.paymentType}</td>
														</tr>
														</c:forEach>
													</c:if>
												</table>
											</td>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.paymenttypename" /> :
											</td>
											<td style="font-size: 12px;">
												<input type="text" id="paymentType1">
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px;">
												<spring:message code="BzComposer.configuration.type" /> :
											</td>
											<td style="font-size: 12px;">
												<form:select path="listOfExistingCreditCardType">
												    <c:if test="${not empty configDto.listOfExistingCreditCardType}">
                                                        <c:forEach items="${configDto.listOfExistingCreditCardType}" var="objList1">
															<option value="${objList1.creditCardName}">${objList1.creditCardName}</option>
														</c:forEach>
													</c:if>
												</form:select>
											</td>
											<tr>
												<td style="font-size: 12px;">
													<spring:message code="BzComposer.configuration.accountcategory" /> :
												</td>
												<td style="font-size: 12px;">
													<select>
														<option></option>
														<option>1</option>
														<option>2</option>
													</select>
												</td>
											</tr>
											<tr>
												<td style="font-size: 14px;">
													<button type="button" id="Add" name="Add" class="formbutton" readonly style="width: 60px;">
													    <spring:message code='BzComposer.global.add'/>
													</button>
													&nbsp;&nbsp;
													<button type="button" id="Update" name="Update" class="formbutton" style="width: 60px;">
													    <spring:message code='BzComposer.global.update'/>
													</button>
													&nbsp;&nbsp;
													<button type="button" id="Delete" name="Delete" class="formbutton" style="width: 60px;">
													    <spring:message code='BzComposer.global.delete'/>
													</button>
													&nbsp;&nbsp;
													<button type="button" id="Clear" name="Clear" class="formbutton" style="width: 60px;">
													    <spring:message code='BzComposer.global.clear'/>
													</button>
												</td>
											</tr>
											<tr>
                                                <th colspan="4" align="left" style="font-size: 12px; padding: 5px;">
                                                    <spring:message code="BzComposer.configuration.creditcardtype" />
                                                </th>
                                            </tr>
                                            <tr>
                                                <td rowspan="3" style="font-size: 12px;">
                                                    <table border="2" width="300px;">
                                                        <tr>
                                                            <td align="center" style="font-size: 12px;">
                                                                <b><spring:message code="BzComposer.configuration.creditcardtype" /></b>
                                                            </td>
                                                            <td align="center" style="font-size: 12px;">
                                                                <b><spring:message code="BzComposer.configuration.active" /></b>
                                                            </td>
                                                        </tr>
                                                        <c:if test="${not empty configDto.listOfExistingCreditCard}">
                                                            <c:forEach items="${configDto.listOfExistingCreditCard}" var="objList1" varStatus="loop">
                                                            <tr>
                                                                <td id="${objList1.creditCardTypeId}" onclick="showType();" align="center">
                                                                    <label id="cType" name="cTyoe" value="${objList1.creditCardTypeId}">
                                                                        ${objList1.creditCardName}
                                                                    </label>
                                                                </td>
                                                                <td align="center"><input type="checkbox" name="isActive" id="isActive${loop.index}" value="0"/></td>
                                                            </tr>
                                                            </c:forEach>
                                                        </c:if>
                                                    </table>
                                                </td>
                                                <td style="font-size: 12px;">
                                                    <spring:message code="BzComposer.configuration.creditcardtype" /> :
                                                </td>
                                                <td style="font-size: 12px;">
                                                    <input type="text" id="creditCardName">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="font-size: 12px;">
                                                    <spring:message code="BzComposer.configuration.active" /> :
                                                </td>
                                                <td style="font-size: 12px;">
                                                    <input type="checkbox" id="active" value="true" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="font-size: 14px;">
                                                    <button type="button" class="formbutton" id="Save" name="Save" readonly style="width:60px;">
                                                        <spring:message code="BzComposer.global.save"/>
                                                    </button>
                                                    &nbsp;&nbsp;
                                                    <button type="button" class="formbutton" id="Add" name="Add" style="width:80px;">
                                                        <spring:message code="BzComposer.configuration.addnewbtn"/>
                                                    </button>
                                                    &nbsp;&nbsp;
                                                    <button type="button" class="formbutton" id="Delete" name="Delete" style="width:60px;">
                                                        <spring:message code="BzComposer.global.delete"/>
                                                    </button>
                                                </td>
                                            </tr>
											<!-- <tr>
												<td colspan="4" align="center" style="font-size: 12px;">
													<h1>
														<a href="/bzcomposerweb2/Banking?tabid=Banking">
															<spring:message code="BzComposer.configuration.gotobank" />
														</a>
													</h1>
												</td>
											</tr> -->
										</table>
								</div>
							</div>
						</div>	
					</div>
					<!-- Account&Payment Ends -->
				</td>
			</tr>
		</table>
		<div>
			<form:hidden path="empStateID" />
			<form:hidden path="labelName" />
		 	<form:hidden path="fileName" />
		 	<input type="hidden" name="tabid" id="tabid" value="" />
	 	</div>
	</div>
		<div align="center">
			<input type="Submit" name="Submit" class="bottomButton formButton" onclick="SaveValues()" value="<spring:message code='BzComposer.global.save'/>"/>
		    <input type="reset" name="Cancel" class="bottomButton formButton" onclick="RevokeValues()" value="<spring:message code='BzComposer.global.cancel'/>"/>
		</div>		
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</form:form>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/categoryManager.js"></script>
</body>
<script type="text/javascript">
function SaveValues()
{
    debugger;
    event.preventDefault();
    $("#showsaverecorddialog").dialog({
        resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                debugger;
                let tabID = $('div#tabs1 ul li.ui-state-active').attr('id');
                sessionStorage.setItem("activeTabID", tabID);

                //General Account Setting panel options
                document.configurationForm.defaultPaymentMethodId.value = document.configurationForm.defaultPaymentMethodId.value;
                document.configurationForm.defaultCategoryId.value = document.configurationForm.defaultCategoryId.value;
                document.configurationForm.scheduleDays.value = document.configurationForm.scheduleDays.value;

                if($("#askWhatToDo").prop("checked",true)){
                    document.configurationForm.reimbursementSettings.value = 2;
                }
                else if($("#dontAddAny").prop("checked",true)){
                    document.configurationForm.reimbursementSettings.value = 1;
                }
                else{
                    document.configurationForm.reimbursementSettings.value = 0;
                }

                document.configurationForm.reimbursementSettings.value = document.configurationForm.reimbursementSettings.value;
                document.configurationForm.startMonth.value = document.configurationForm.startMonth.value;
                document.configurationForm.endMonth.value = document.configurationForm.endMonth.value;

                //Account Receivable panel options
                document.configurationForm.arCategory.value = document.configurationForm.arCategory.value;
                document.configurationForm.arReceivedType.value = document.configurationForm.arReceivedType.value;
                document.configurationForm.arDepositTo.value = document.configurationForm.arDepositTo.value;

                //Po Payable panel options
                document.configurationForm.poCategory.value = document.configurationForm.poCategory.value;
                document.configurationForm.poReceivedType.value = document.configurationForm.poReceivedType.value;
                document.configurationForm.poDepositTo.value = document.configurationForm.poDepositTo.value;

                //Billing Payable panel options
                document.configurationForm.bpCategory.value = document.configurationForm.bpCategory.value;
                document.configurationForm.bpReceivedType.value = document.configurationForm.bpReceivedType.value;
                document.configurationForm.bpDepositTo.value = document.configurationForm.bpDepositTo.value;

                //Billing options
                var selectedBillingType = $.trim($("#selectedBillingTypeId option:selected").text());
                //var printBill = $("#printBills").val();
                //var mailCustomer = $("#mailToCustomer").val();
                //var combinedBilling = $("#showCombinedBilling").val();

                document.configurationForm.startingBillNumber.value = document.configurationForm.startingBillNumber.value;
                document.configurationForm.showCombinedBilling.value = document.configurationForm.showCombinedBilling.value;
                document.configurationForm.showBillingStatStyle.value =  document.configurationForm.showBillingStatStyle.value;

                document.configurationForm.mailToCustomer.value = document.configurationForm.mailToCustomer.value;
                document.configurationForm.printBills.value = document.configurationForm.printBills.value;

                //document.getElementById('printBillsValue').value= printBill;
                //document.getElementById("mailToCust").value = mailCustomer;
                //document.getElementById("showCmbBilling").value = combinedBilling;
                document.getElementById('tabid').value="SaveConfigurationAccountPayment";

                document.forms['accountPaymentfrm'].action = "Configuration?tabid=SaveConfigurationAccountPayment";
                document.forms['accountPaymentfrm'].submit();
                $('form').submit();
            },
            "<spring:message code='BzComposer.global.cancel'/>": function () {
                $(this).dialog("close");
                return false;
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

<div id="AddNewCategoryDlgId" style="display:none;">
    <div class="form1">
        <h6><spring:message code="BzComposer.categorymanager.accountinformation"/></h6>
        <div class="form-horizontal">
            <div class="form-group row" id="addNewCategoryCatTypeDivId">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.type"/>
                </label>
                <div class="col-md-6">
                    <select class="form-control" id="categoryTypeForAdd">
                        <c:forEach items="${categoryType}" var="curObject" varStatus="loop">
                            <option value="${curObject.categoryTypeID}">${curObject.categoryTypeName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.name"/>
                </label>
                <div class="col-md-6">
                    <input type="text" class="form-control" id="CategoryNameForAdd"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-3 col-form-label"></label>
                <div class="col-md-6 form-inline" id="subCategoryList">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="subAccountOfCheckBox">
                        <label class="form-check-label" for="inlineCheckbox1">
                            <spring:message code="BzComposer.categorymanager.subaccountof"/>
                        </label>
                    </div>
                    <select class="form-control" id="addCategoryCombo">
                        <c:forEach items="${listOfCategory}" var="curObject" varStatus="loop">
                            <option value="${curObject.id}">${curObject.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.accountnumber"/>
                </label>
                <div class="col-md-6">
                    <input type="text" class="form-control" id="accountNumberForAdd">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.budgetcategory"/>
                </label>
                <div class="col-md-6">
                    <select class="form-control" id="budgetCategoryId">
                        <c:forEach items="${budgetCategoryList}" var="curObject" varStatus="loop">
                            <option value="${curObject.budgetCategoryID}">${curObject.budgetCategoryName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.description"/>
                </label>
                <div class="col-md-6">
                    <input type="text" class="form-control" id="descriptionForAdd">
                </div>
            </div>
        </div>
        <div class="text-center row pb-2">
            <label class="col-md-3">&nbsp;</label>
            <div class="col-md-6" style="font-size: 14px;">
                <button type="button" class="btn btn-info" onclick="return saveNewCategory()" id="saveNewcategory">
                    <spring:message code="BzComposer.global.save"/>
                </button>
                <button type="button" class="btn btn-info" onclick="closeAddDialog();">
                    <spring:message code="BzComposer.global.close"/>
                </button>
            </div>
        </div>
    </div>
</div>
<div id="EditCategoryDlgId" style="display:none;">
    <div class="form1">
        <h6><spring:message code="BzComposer.categorymanager.accountinformation"/></h6>
        <div class="form-horizontal">
            <div class="form-group row" id="EditCategoryCatTypeDivId">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.type"/>
                </label>
                <div class="col-md-6">
                    <select class="form-control" id="CategoryTypeForUpdate">
                        <c:forEach items="${categoryType}" var="curObject" varStatus="loop">
                            <option value="${curObject.categoryTypeID}">${curObject.categoryTypeName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.name"/>
                </label>
                <div class="col-md-6">
                    <input type="text" class="form-control" id="CategoryNameForUpdate"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-3 col-form-label"></label>
                <div class="col-md-6 form-inline" id="subCategoryList">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="subAccountOfCheckForUpdate">
                        <label class="form-check-label" for="inlineCheckbox1">
                            <spring:message code="BzComposer.categorymanager.subaccountof"/>
                        </label>
                    </div>
                    <select class="form-control" id="SubAccountOfCategoryForUpdate">
                        <c:forEach items="${listOfCategory}" var="curObject" varStatus="loop">
                            <option value="${curObject.id}">${curObject.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.accountnumber"/>
                </label>
                <div class="col-md-6">
                    <input type="text" class="form-control" id="AccountNumberForUpdate">
                </div>
            </div>

            <div class="form-group row">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.budgetcategory"/>
                </label>
                <div class="col-md-6">
                    <select class="form-control" id="BudgetCategoryForUpdate">
                        <c:forEach items="${budgetCategoryList}" var="curObject" varStatus="loop">
                            <option value="${curObject.budgetCategoryID}">${curObject.budgetCategoryName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.description"/>
                </label>
                <div class="col-md-6">
                    <input type="text" class="form-control" id="DescriptionForUpdate">
                </div>
            </div>
        </div>
        <div class="text-center row pb-2">
            <label class="col-md-3">&nbsp;</label>
            <div class="col-md-6" style="font-size: 14px;">
                <button type="button" class="btn btn-info" onclick="return updateCategory()">
                    <spring:message code="BzComposer.global.update"/>
                </button>
                <button type="button" class="btn btn-info" onclick="closeEditDialog();">
                    <spring:message code="BzComposer.global.close"/>
                </button>
            </div>
        </div>
    </div>
</div>