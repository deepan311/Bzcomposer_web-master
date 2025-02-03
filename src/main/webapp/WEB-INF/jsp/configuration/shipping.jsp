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
<title><spring:message code="BzComposer.shippingtitle" /></title>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<script type="text/javascript">
$(function() 
{
    $("#tabs1").tabs();
    $("#tabsTax").tabs();
    $("#tabs2").tabs();
    $("#tabs").tabs();
    $("#tabsFederalTax").tabs();
    $("#tabsCompanyTaxOption").tabs();
    
    var isUPS = $("#isUPSActive").val();
	var isUSPS = $("#isUSPSActive").val();
	var isFedex = $("#isFeDexActive").val();
	

	if(isUPS == 1){
		$("#upsUserId").attr('readonly',true);
		$("#upsPassword").attr('readonly',true);
		$("#accessKey").attr('readonly',true);
		$("#upsAccountNo").attr('readonly',true);
		$("#isUPSActive").attr('checked',true);
	}
	else{
		$("#upsUserId").attr('readonly',false);
		$("#upsPassword").attr('readonly',false);
		$("#accessKey").attr('readonly',false);
		$("#upsAccountNo").attr('readonly',false);
		$("#isUPSActive").attr('checked',false);
	}

	if(isUSPS == 1){
		$("#isUSPSActive").attr('checked',true);
		$("#uspsUserId").attr('readonly',true);
	}
	else{
		$("#isUSPSActive").attr('checked',false);
		$("#uspsUserId").attr('readonly',false);
	}

	if(isFedex == 1){
		$("#fedexAccountNumber").attr('readonly',true);
		$("#fedexMeterNumber").attr('readonly',true);
		$("#fedexPassword").attr('readonly',true);
		$("#fedexTestKey").attr('readonly',true);
	}
	else{
		$("#fedexAccountNumber").attr('readonly',false);
		$("#fedexMeterNumber").attr('readonly',false);
		$("#fedexPassword").attr('readonly',false);
		$("#fedexTestKey").attr('readonly',false);
	} 
});

$(document).ready(function(){
	$('#shippingTypeWeight').scroll(function(){
	    var length = $(this).scrollTop();
	    $('#shippingTypePrice').scrollTop(length);
	});
	$('#shippingTypePrice').scroll(function(){
		var length = $(this).scrollTop();
		$('#shippingTypeWeight').scrollTop(length);
	});
	
	$("#modifySeletedWeight").attr('disabled',true);
 	$("#deleteSeletedWeight").attr('disabled',true);
 	
 	//$("#selectedMailTypeId")append(new Option("", "0"));
 	var o = new Option("", "0");
	$(o).html("");
	var o1 = new Option("", "0");
	$(o1).html("");
	var o2 = new Option("", "0");
	$(o2).html("");
	$("#selectedMailTypeId").append(o);
	$("#selectedPackageSizeId").append(o1);
	$("#selectedContainerId").append(o2);
 	$('select[id="selectedMailTypeId"]').find('option[value="'+0+'"]').attr("selected",true);
 	$('select[id="selectedPackageSizeId"]').find('option[value="'+0+'"]').attr("selected",true);
 	$('select[id="selectedContainerId"]').find('option[value="'+0+'"]').attr("selected",true);
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
function setPrice(elementID)
{
    let selectedIndex = document.getElementById(elementID).selectedIndex;
    $('select[id="shippingTypeWeight"]').find('option').attr("selected", false);
    $('select[id="shippingTypePrice"]').find('option').attr("selected", false);
 	$("#shippingTypeWeight").prop('selectedIndex', selectedIndex);
 	$("#shippingTypePrice").prop('selectedIndex', selectedIndex);

 	$("#upsWeight").val( $("#shippingTypeWeight option:selected").text() );
 	$("#upsShippingFee").val( $("#shippingTypePrice option:selected").text() );
 	$("#modifySeletedWeight").attr('disabled',false);
 	$("#deleteSeletedWeight").attr('disabled',false);
}
function showPanel() {
    var selectedTab = $("#tabs1").tabs('option','active');
    if(selectedTab == 2){
    	document.getElementById("shippingFreeMethodDiv").style.display = "none";
    }
    else{
    	document.getElementById("shippingFreeMethodDiv").style.display = "block";
    }
}

function addSelectedWeightFee(){
	var udShipTypeId = $("#userShippingType").val();
	var udShipWeight = $("#upsWeight").val();
	var udShipPrice = $("#upsShippingFee").val();
    if(udShipTypeId == null || udShipTypeId == "" || udShipTypeId == 0){
        return selectshippingtypedialog();
    }
    else if(udShipWeight == "" || udShipPrice == ""){
        return enterWeightAndPriceDialog();
    } else {
        $.ajax({
            type: "POST",
            url:"/ConfigurationAjaxTest?tabid=addUserDefinedShippingWeightAndPrice",
            data:{userDefinedShippingTypeId : parseInt(udShipTypeId), userDefinedShippingWeight : udShipWeight, userDefinedShippingPrice : udShipPrice},
            success:function(data){
                if(data){   location.reload(); }
            },
            error:function(){
              alert("<bean:message key='BzComposer.common.erroroccurred'/>");
            }
        });
    }
}
function updateSelectedWeightFee(){
	var udShipId = $("#shippingTypeWeight").val();
    var udShipWeight = $("#upsWeight").val();
    var udShipPrice = $("#upsShippingFee").val();
    if(udShipId == null || udShipId == "" || udShipId == 0){
        return selectAtleast1RecordDialog();
    }
    else if(udShipWeight == "" || udShipPrice == ""){
        return enterWeightAndPriceDialog();
    } else {
        $.ajax({
            type: "POST",
            url:"/ConfigurationAjaxTest?tabid=updateUserDefinedShippingWeightAndPrice",
            data:{userDefinedShippingId : parseInt(udShipId), userDefinedShippingWeight : udShipWeight, userDefinedShippingPrice : udShipPrice},
            success:function(data){
                if(data){   location.reload(); }
            },
            error:function(){
              alert("<bean:message key='BzComposer.common.erroroccurred'/>");
            }
        });
    }
}
function deleteSelectedWeightFee(){
    var udShipId = $("#shippingTypeWeight").val();
    if(udShipId == null || udShipId == "" || udShipId == 0){
        return selectAtleast1RecordDialog();
    } else {
        if(confirm('<spring:message code="BzComposer.common.wantToDelete"/>')){
            $.ajax({
                type: "GET",
                url:"/ConfigurationAjaxTest?tabid=deleteUserDefinedShippingWeightAndPrice&udShippingRateID="+udShipId,
                success:function(data){
                    if(data){   location.reload(); }
                },
                error:function(){
                  alert("<bean:message key='BzComposer.common.erroroccurred'/>");
                }
            });
        }
    }
}

function setServices(){
	debugger
	var serviceName = $.trim($("#upsSelect option:selected").text());
	$("#upsServiceName").val(serviceName);
}

function setUSPSService(){
	var uspsService = $("#uspsSelect option:selected").text();
	$("#uspsServiceName").val(uspsService);
}

function setWeightPrice(){
	var shippingTypeID = $("#userShippingType").val();
	if(shippingTypeID == 0){
		return selectshippingtypedialog();
	} else {
		$("#modifySeletedWeight").attr('disabled',true);
	 	$("#deleteSeletedWeight").attr('disabled',true);
		$.ajax({
            type: "GET",
            url:"/ConfigurationAjaxTest?tabid=getUserDefinedShippingWeightAndPrice&shippingCarrierId="+shippingTypeID,
            success:function(data){
                let weightDropdown = $("#shippingTypeWeight"), option = "";
                let priceDropdown = $("#shippingTypePrice"), option2 = "";
                weightDropdown.empty();
                priceDropdown.empty();
                for(var i=0;i<data.length;i++){
                    option = option+"<option value='"+data[i].userDefinedShippingId+"'>"+data[i].userDefinedShippingWeight+"</option>"
                    option2 = option2+"<option value='"+data[i].userDefinedShippingId+"'>"+data[i].userDefinedShippingPrice+"</option>"
                }
                weightDropdown.append(option);
                priceDropdown.append(option2);
            },
            error:function(){
                alert("<bean:message key='BzComposer.common.erroroccurred'/>");
            }
        });
	}
}

function addModalShippingType(){
	var sType = $.trim($("#modalShippingType option:selected").text());
	$("#selectedShippingType").val(sType);
}

function setModalDescription(){
	debugger
	var sType = $.trim($("#modalShippingType option:selected").text());
	$("#selectedShippingType").val(sType);
}

function saveTemplate(){

}

function deleteTemplate(){

}
function saveModalShippingType()
{
	debugger
	var selectedSType = $.trim($("#modalShippingType option:selected").text());
	debugger
	var textboxValue = $("#selectedShippingType").val();
	debugger
	if(textboxValue ==""){

		return emptyvaluedialog();
	}
	else if(selectedSType == textboxValue){

		return duplicatevaluedialog();
	}
	else {
		//var id = document.getElementById("id1234").value;
		window.location.href= "Configuration?tabid=addshippingtype&shippingtype="+textboxValue;
	}
}

function editModalShippingType() {
	debugger;
	newID = document.getElementById("modalShippingType").value;
	var textboxValue = $("#selectedShippingType").val();
	window.location.href= "Configuration?tabid=editshippingtype&oldshippingtype="+textboxValue+"&oldId="+newID;
}
function deleteModalShippingType() {
	debugger;
	newID = document.getElementById("modalShippingType").value;
	window.location.href= "Configuration?tabid=deleteshippingtype&oldId="+newID;
}

function addNewTemplate(){
	debugger	
	document.getElementById("templateName").style.display = "block";
	document.getElementById("templateSubject").style.display = "block";
	document.getElementById("emailText").style.display = "block";
	document.getElementById("txtTemplateText").style.display = "none";
	document.getElementById("txtTemplateName").style.display = "none";
	document.getElementById("txtTemplateSubject").style.display = "none";
	document.getElementById("emailText").value = "<<name>>"+'\n'+"<<company name>>"+'\n'+"<<address>>"+'\n'+"<<phonenumber>>";	
}
function setContent(){
	debugger
	var id = $("#selectedTemplateId option:selected").val();

	document.getElementById("templateName").style.display = "none";
	document.getElementById("txtTemplateName").style.display = "block";
	document.getElementById("templateSubject").style.display = "none";
	document.getElementById("txtTemplateSubject").style.display = "block";
	document.getElementById("emailText").style.display = "none";
	document.getElementById("txtTemplateText").style.display = "block";
	
	//window.open("Configuration?tabid=config21&templateId="+id,null,"scrollbars=yes,height=600,width=1300,status=yes,toolbar=no,menubar=no,location=no");
}

function showTime(){
	var h = document.getElementById("hours").value;
	var m = document.getElementById("minutes").value;
	var t = document.getElementById("selectedTime").value;
	if(h>=0 && h<10)	
	{
		h = "0"+h;
	}
	if(m>=0 && m<10)	
	{
		m = "0"+m;
	}
	var time = h+" : "+ m +" "+ t;
	$("#scheduleTime").append("<option value=" + time + ">"+ time + "</option>");
}
function removeTime(){
	$('#scheduleTime option:selected').remove();
}
</script>
</head>
<body onload="init();">
<!-- begin shared/header -->
<form:form action="Configuration?" name="configurationForm" enctype="MULTIPART/FORM-DATA" method="post" id="frmshipping" modelAttribute="configDto">
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div>
	<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.configuration.configurationtitle"/>
	</span>
</div>
<div>
	<div>
		<c:if test="${not empty Labels}">
            <input type="hidden" name="lsize" id="lblsize" value='${Labels.size()}' />
            <c:forEach items="Labels" var="lbl" varStatus="loop">
                <input type="hidden" id='${loop.index}lid' name='${loop.index}lidname' value='${lbl.value}' />
                <input type="hidden" id='${loop.index}lname' name='${loop.index}lnm' value='${lbl.label}' />
            </c:forEach>
		</c:if>
	</div>
	<div id="table-negotiations" style="padding: 0; border: 1px solid #ccc;">
	<span style="font-size:30px;cursor:pointer; margin-left: 20px;" onclick="toggleFunction()">&#9776;</span>
		<table cellspacing="0"  style="border: 0;width: 100%;overflow-y:scroll;" class="section-border">
			<tr>
				<td id="leftMenu" style="position: relative; width: 180px;">
					<table>
						<tr>
							<td>
								<jsp:include page="menuPage.jsp" />
							</td>
						</tr>
						<%-- <tr align="center">
							<td>
								<input type="button" name="Revoke" class="formButton" onclick="RevokeValues();" value='<spring:message code="BizComposer.Configuration.RevokeButton"/>' />
								<input type="button" name="Save" class="formButton" onclick="SaveValues();" value='<spring:message code="BizComposer.Configuration.SaveButton"/>' />
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						</tr> --%>
					</table>
				</td>
				<td valign="top">
					<!-- shipping Starts -->
					<div id="shipping" style="display:none;padding: 0; position: relative; left: 0;">
						<div id="shippingFreeMethodDiv">
                            <table class="table-notifications" width="100%">
                                <tr><th align="left" style="font-size:12px; padding: 5px;">
                                <b><spring:message code="BzComposer.configuration.defaultshippingfreemethod"/></b>
                                </th></tr>
                            </table>
                            <div class="form-check" style="font-size:12px;">
                                <input type="checkbox" id="manualInsertion" style="class:form-check-input"/>
                                    <spring:message code="BzComposer.configuration.mannualinsertion"/>
                            </div>
							<br/>
						</div>
						<div id="tabs1" style="height:auto;">
							<ul>
								<li onclick="showPanel()" style="font-size:12px;"><a href="#userDefinedShippingMethod"><spring:message code="BzComposer.configuration.tab.userdefinedshippingmehod" /></a></li>
								<li onclick="showPanel()" style="font-size:12px;"><a href="#realTimeShippingAPI"><spring:message code="BzComposer.configuration.tab.realtimeapishipping" /></a></li>
								<li onclick="showPanel()" style="font-size:12px;"><a href="#shipping"><spring:message code="BzComposer.configuration.tab.shipping" /></a></li>
								<li onclick="showPanel()" style="font-size:12px;"><a href="#valueAddedAervices"><spring:message code="BzComposer.configuration.valueaddedservices" /></a></li>
							</ul>
							<div id="userDefinedShippingMethod">
								<table style="width:100%; height:500px;">
									<tr>
										<td style="font-size:12px;">
											<input type="checkbox" id="userDataInsertion">
											<spring:message code="BzComposer.configuration.userdatainsertion"/>
										</td>
										<td style="font-size:14px;">
											<a href="#modalAddModify" rel="modal:open">
											<button type="button" class="formButton" id="AddModify">
											    <spring:message code="BzComposer.configuration.addmodifybtn"/>
											</button>
											</a>
										</td>
									</tr>
									<tr>
										<td style="font-size:12px;">
											<spring:message code="BzComposer.configuration.shippingtype"/>
											<br>
											<form:select id="userShippingType" path="selectedUserDefinedShippingTypeId" style="width:180px;" onchange="setWeightPrice()">
												<!-- <option value="0"> </option> -->
												<c:if test="${not empty configDto.listOfExistingUserDefiedShippingType}">
                                                	<c:forEach items="${configDto.listOfExistingUserDefiedShippingType}" var="objList1">
                                                		<option value="${objList1.userDefinedShippingTypeId}">${objList1.userDefinedShipping}</option>
                                                	</c:forEach>
                                                </c:if>
											</form:select>
										</td>
										<td style="font-size:14px;">
											<a href="#modalviewServices" rel="modal:open">
											<button type="button" class="formButton" id="viewServices">
											    <spring:message code="BzComposer.configuration.viewservices"/>
											</button>
											</a>
										</td>
									</tr>
									<tr>
										<td style="font-size:12px;" colspan="4">
											<table>
											    <tr>
											        <td style="font-size:12px;" valign="top">
                                                        <spring:message code="BzComposer.configuration.weightlbs"/> :
                                                    </td>
                                                    <td style="font-size:12px;" valign="top">
                                                        <input type="text" id="upsWeight" onkeypress="return numbersOnlyFloat(event, this.value)" />
                                                    </td>
                                                    <td style="width:50px;">&nbsp;</td>
                                                    <td style="font-size:12px;" valign="top">
                                                        <spring:message code="BzComposer.configuration.shippingfee"/> ($) :
                                                    </td>
                                                    <td style="font-size:12px;" valign="top">
                                                        <input type="text" id="upsShippingFee" onkeypress="return numbersOnlyFloat(event, this.value)" />
                                                    </td>
											    </tr>
											</table>
										</td>
									</tr>
									<tr>
										<td rowspan="6" style="font-size:12px;width:350px;">
											<table>
												<tr>
													<td style="font-size:12px;">
														<spring:message code="BzComposer.configuration.weight"/>
													</td>
													<td style="font-size:12px;">
														<spring:message code="BzComposer.configuration.fee"/> ($)
													</td>
												</tr>
												<tr>
													<td style="font-size:12px;">
														<form:select path="listOfExistingUserDefiedShippingWeightAndPrice" id="shippingTypeWeight" multiple="multiple" style="width:150px; height:250px;" onchange="setPrice('shippingTypeWeight')">
														    <c:if test="${not empty configDto.listOfExistingUserDefiedShippingWeightAndPrice}">
                                                            	<c:forEach items="${configDto.listOfExistingUserDefiedShippingWeightAndPrice}" var="objList1">
                                                            		<option value="${objList1.userDefinedShippingId}">${objList1.userDefinedShippingWeight}</option>
                                                            	</c:forEach>
                                                            </c:if>
														</form:select>
													</td>
													<td style="font-size:12px;">
														 <form:select path="listOfExistingUserDefiedShippingWeightAndPrice" id="shippingTypePrice" multiple="multiple" style="width:150px; height:250px;" onchange="setPrice('shippingTypePrice')">
														    <c:if test="${not empty configDto.listOfExistingUserDefiedShippingWeightAndPrice}">
                                                            	<c:forEach items="${configDto.listOfExistingUserDefiedShippingWeightAndPrice}" var="objList1">
                                                            		<option value="${objList1.userDefinedShippingId}">${objList1.userDefinedShippingPrice}</option>
                                                            	</c:forEach>
                                                            </c:if>
														</form:select>
													</td>
												</tr>
											</table>
										</td>
										<td rowspan="6" style="font-size:14px;">
											<input type="button" class="formButton" id="addSelectedWeight" onclick="addSelectedWeightFee()" style="width:80px;" value="<spring:message code='BzComposer.global.add'/>" />
											<br>
											<input type="button" class="formButton" id="modifySeletedWeight" onclick="updateSelectedWeightFee()" style="width:80px;" value="<spring:message code='BzComposer.configuration.modify'/>" />
											<br>
											<input type="button" class="formButton" id="deleteSeletedWeight" onclick="deleteSelectedWeightFee()" style="width:80px;" value="<spring:message code='BzComposer.global.delete'/>" />
										</td>
									</tr>
								</table>
							</div>
							<div id="modalAddModify" class="modal modal1" role="dialog">
								<div class="modal-dialog">
									<!-- Modal content-->
    								<div class="modal-content">
      									<div class="modal-header">
        									<h4 class="modal-title" style="font-size:12px; padding: 5px;">
        										<b><spring:message code="BzComposer.configuration.shippingtype"/></b>
        									</h4>
      									</div>
      									<div class="modal-body" style="font-size:12px;">
        								<form:select id="modalShippingType" multiple="multiple" path="selectedUserDefinedShippingTypeId"
											style="width:400px; height:180px;" onchange="addModalShippingType()">
											    <c:if test="${not empty configDto.listOfExistingUserDefiedShippingType}">
                                                	<c:forEach items="${configDto.listOfExistingUserDefiedShippingType}" var="objList1">
                                                		<option value="${objList1.userDefinedShippingTypeId}">${objList1.userDefinedShipping}</option>
                                                	</c:forEach>
                                                </c:if>
											</form:select>
      									</div>
      									<div class="modal-body" style="font-size:12px;">
      									<b><spring:message code="BzComposer.configuration.description"/> :</b>
      									<br>
      									<input type="text" id="selectedShippingType" style="width:400px;">
      									</div>
      									<div class="modal-footer" style="font-size:14px;">
      										<button type="button" class="formButton" id="addShippingType" name="addShippingType" onclick="saveModalShippingType()">
      											<spring:message code="BzComposer.global.add"/>
      										</button>
      										<button type="button" class="formButton" id="modifyShippingType" name="modifyShippingType" onclick="editModalShippingType()">
      											<spring:message code="BzComposer.configuration.modify"/>
      										</button>
      										<button type="button" class="formButton" id="deleteShippingType" name="deleteShippingType" onclick="deleteModalShippingType()">
      											<spring:message code="BzComposer.global.delete"/>
      										</button>
        									<a href="#" rel="modal:close">
        										<button type="button" class="formButton" data-dismiss="modal" id="closeShippingType" name="closeShippingType">
        											<spring:message code="BzComposer.global.close"/>
        										</button>
       										</a>
      									</div>
    								</div>
   								</div>
							</div>
							<div id="realTimeShippingAPI" class="tabPage">
								<table class="table-notifications" style="height: 500px;">
									<tr>
										<td style="width:50px;font-size: 12px;">
											<input type="checkbox" name="shippingAPI" id="shippingAPI" value="${configDto.shippingAPI}" ${configDto.shippingAPI==0?'':'checked'} />
											<spring:message code="BzComposer.configuration.shippingapi"/>
										</td>
										<td style="width:150px;font-size:12px;">
											<spring:message code="BzComposer.configuration.accountforeachservice"/>
										</td>
									</tr>
									<tr>
										<td style="font-size:12px;">
											<spring:message code="BzComposer.configuration.availableshippingapis"/>
										</td>
										<td>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left" style="width:auto;">
											<div id="tabs" style="height: 650px;">
												<ul>
													<li style="font-size:12px;"><a href="#ups">
														<b><spring:message code="BzComposer.configuration.tab.ups" /></b>
													</a></li>
													<li style="font-size:12px;"><a href="#usps">
														<b><spring:message code="BzComposer.configuration.tab.usps" /></b>
													</a></li>
													<li style="font-size:12px;"><a href="#fedex">
														<b><spring:message code="BzComposer.configuration.tab.fedex" /></b>
													</a></li>
												</ul>
												<div id="ups" style="width:auto;">
													<table style="width:100%;">
														<tr>
															<th colspan="3" align="left" style="font-size:12px; padding: 5px;">
																<spring:message code="BzComposer.configuration.upsaccountsettings"/>
															</th>
														</tr>
														<tr>
															<td style="font-size:12px;width:300px;">
															<c:if test="${not empty configDto.listOfExistingUpsUSers}">
                                                            	<c:forEach items="${configDto.listOfExistingUpsUSers}" var="objList1">
                                                            		<input type="checkbox" id="isUPSActive" name="isUPSActive" value="${objList1.isUPSActive}"/>
                                                                    <spring:message code="BzComposer.configuration.tab.ups"/>
                                                            	</c:forEach>
                                                            </c:if>
															</td>
															<td style="width:200px;">&nbsp;</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.upsuserid"/>
															</td>
															<td style="font-size:12px;">
																<c:if test="${not empty configDto.listOfExistingUpsUSers}">
                                                                	<c:forEach items="${configDto.listOfExistingUpsUSers}" var="objList1">
                                                                		<input type="text" id="upsUserId" name="upsUserId" value="${objList1.upsUserId}"/>
                                                                	</c:forEach>
                                                                </c:if>
															</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.upspassword"/>
															</td>
															<td style="font-size:12px;">
																<c:if test="${not empty configDto.listOfExistingUpsUSers}">
                                                                	<c:forEach items="${configDto.listOfExistingUpsUSers}" var="objList1">
                                                                		<input type="password" id="upsPassword" name="upsPassword" value="${objList1.upsPassword}"/>
                                                                	</c:forEach>
                                                                </c:if>
															</td>
														</tr>
														<tr>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.accesskey"/>
															</td>
															<td style="font-size:12px;">
																<c:if test="${not empty configDto.listOfExistingUpsUSers}">
                                                                	<c:forEach items="${configDto.listOfExistingUpsUSers}" var="objList1">
                                                                		<input type="text" id="accessKey" name="accessKey" value="${objList1.accesskey}"/>
                                                                	</c:forEach>
                                                                </c:if>
															</td>
														</tr>
														<tr>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.upsaccountnumber"/>
															</td>
															<td style="font-size:12px;">
																<c:if test="${not empty configDto.listOfExistingUpsUSers}">
                                                                	<c:forEach items="${configDto.listOfExistingUpsUSers}" var="objList1">
                                                                		<input type="text" id="upsAccountNo" name="upsAccountNo" value="${objList1.upsAccountNo}"/>
                                                                	</c:forEach>
                                                                </c:if>
															</td>
														</tr>
														<tr>
															<th colspan="3" align="left" style="font-size:12px; padding: 5px;">
																<spring:message code="BzComposer.configuration.valueaddedservices"/>
															</th>
														</tr>
														<tr>
															<td colspan="3" align="left" style="font-size:12px;">
																<spring:message code="BzComposer.configuration.availableservices"/>
															</td>
														</tr>
														<tr>
															<td rowspan="4" style="font-size:12px;">
																<form:select path="selectedRealTimeShippingServiceId" multiple="multiple" style="width:200px; height:200px;" onclick="setServices()" id="upsSelect">
																    <c:if test="${not empty configDto.listOfExistingRealTimeShippingServices}">
                                                                    	<c:forEach items="${configDto.listOfExistingRealTimeShippingServices}" var="objList1">
                                                                    		<option value="${objList1.realTimeShippingServiceId}">${objList1.realTimeShippingService}</option>
                                                                    		${objList1.realTimeShippingPrice}
                                                                    	</c:forEach>
                                                                    </c:if>
																</form:select>
															</td>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.enterservicename"/> :
																<br>
																<form:input path="upsServiceName" id="upsServiceName" readonly="true" />
															</td>
															<td style="font-size:14px;">
																<button type="button" id="add" style="width:60px;" name="add" class="formButton">
																    <spring:message code="BzComposer.global.add"/>
																</button>
															</td>
														</tr>
														<tr>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.enterserviceprice"/> ($) :
																<br>
																<form:input path="upsServicePrice" />
															</td>
															<td style="font-size:14px;">
																<button type="button" id="edit" style="width:60px;" class="formButton">
																    <spring:message code="BzComposer.global.edit"/>
																</button>
															</td>
														</tr>
														<tr>
															<td>
															</td>
															<td style="font-size:14px;">
																<button type="button" id="edit" style="width:60px;"class="formButton">
																    <spring:message code="BzComposer.global.delete"/>
																</button>
															</td>
														</tr>
													</table>
												</div>
												<div id="usps">
													<table style="width:100%;">
														<tr>
															<th colspan="3" align="left" style="font-size:12px; padding: 5px;">
																<spring:message code="BzComposer.configuration.uspsaccountsettings"/>
															</th>
														</tr>
														<tr>
															<td style="font-size:12px;width:300px;">
																<%-- <form:checkbox path="isUSPSActive" /> --%>
																<c:if test="${not empty configDto.listOfExistingUspsUSers}">
                                                                	<c:forEach items="${configDto.listOfExistingUspsUSers}" var="objList1">
                                                                		<input type="checkbox" id="isUSPSActive" name="isUSPSActive" value="${objList1.isUSPSActive}"/>
                                                                        <spring:message code="BzComposer.configuration.tab.usps"/>
                                                                	</c:forEach>
                                                                </c:if>
															</td>
															<td style="width:200px;">&nbsp;</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.uspsuserid"/> :
															</td>
															<td style="font-size:12px;">
																<%-- <form:input path="uspsUserId"></form:input> --%>
																<c:if test="${not empty configDto.listOfExistingUspsUSers}">
                                                                	<c:forEach items="${configDto.listOfExistingUspsUSers}" var="objList1">
                                                                		<input type="text" id="uspsUserId" name="uspsUserId" value="${objList1.uspsUserId}"/>
                                                                	</c:forEach>
                                                                </c:if>
															</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<th colspan="3" align="left" style="font-size:12px; padding: 5px;">
																<spring:message code="BzComposer.configuration.valueaddedservices"/>
															</th>
														</tr>
														<tr>
															<td colspan="3" align="left" style="font-size:12px; padding: 5px;">
																<spring:message code="BzComposer.configuration.availableservices"/>
															</td>
														</tr>
														<tr>
															<td rowspan="4" style="font-size:12px;">
																<form:select path="selectedRealTimeShippingServiceId" multiple="multiple" id="uspsSelect" style="width:200px; height:200px;" onclick="setUSPSService()">
																    <c:if test="${not empty configDto.listOfExistingRealTimeShippingServices1}">
                                                                    	<c:forEach items="${configDto.listOfExistingRealTimeShippingServices1}" var="objList1">
                                                                    		<option value="${objList1.realTimeShippingServiceId}">${objList1.realTimeShippingService}</option>
                                                                    	</c:forEach>
                                                                    </c:if>
																</form:select>
															</td>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.enterservicename"/> :
																<br>
																<form:input path="upsServiceName" id="uspsServiceName" />
															</td>
															<td style="font-size:14px;">
																<button type="button" id="add" style="width: 60px;" name="add" class="formButton">
																    <spring:message code="BzComposer.global.add"/>
																</button>
															</td>
														</tr>
														<tr>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.enterserviceprice"/> ($)
																<br>
																<form:input path="upsServicePrice" />
															</td>
															<td style="font-size:14px;">
																<button type="button" id="edit"style="width: 60px;" class="formButton">
																    <spring:message code="BzComposer.global.edit"/>
																</button>
															</td>
														</tr>
														<tr>
															<td>
															</td>
															<td style="font-size:14px;">
																<button type="button" id="edit" style="width: 60px;" class="formButton">
																    <spring:message code="BzComposer.global.delete"/>
																</button>
															</td>
														</tr>
													</table>
												</div>
												<div id="fedex">
													<table style="width:100%;">
														<tr>
															<th colspan="3" align="left" style="font-size:12px; padding: 5px;">
																<spring:message code="BzComposer.configuration.fedexaccountsettings"/>
															</th>
														</tr>
														<tr>
															<td style="font-size:12px;width:300px;">
																<%-- <form:checkbox path="isFeDexActive">
																	<spring:message code="BizComposer.Configuration.Shipping.fedex"/> --%>
																<c:if test="${not empty configDto.listOfExistingFedexUSers}">
                                                                	<c:forEach items="${configDto.listOfExistingFedexUSers}" var="objList1">
                                                                		<input type="checkbox" id="isFeDexActive" name="isFeDexActive" value="${objList1.isFeDexActive}"/>
                                                                        <spring:message code="BizComposer.Configuration.Shipping.fedex"/>
                                                                	</c:forEach>
                                                                </c:if>
															</td>
															<td style="width:200px;">&nbsp;</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.accountnumber"/> :
															</td>
															<td style="font-size:12px;">
																<%-- <form:input path="fedexAccountNumber"></form:input> --%>
																<c:if test="${not empty configDto.listOfExistingFedexUSers}">
                                                                	<c:forEach items="${configDto.listOfExistingFedexUSers}" var="objList1">
                                                                		<input type="text" id="fedexAccountNumber" name="fedexAccountNumber" value="${objList1.fedexAccountNumber}"/>
                                                                	</c:forEach>
                                                                </c:if>
															</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.meternumber"/> :
															</td>
															<td style="font-size:12px;">
																<%-- <form:password path="fedexMeterNumber"></form:password> --%>
																<c:if test="${not empty configDto.listOfExistingFedexUSers}">
                                                                	<c:forEach items="${configDto.listOfExistingFedexUSers}" var="objList1">
                                                                		<input type="text" id="fedexMeterNumber" name="fedexMeterNumber" value="${objList1.fedexMeterNumber}"/>
                                                                	</c:forEach>
                                                                </c:if>
															</td>
														</tr>
														<tr>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.password"/> :
															</td>
															<td style="font-size:12px;">
																<%-- <form:input path="fedexPassword"></form:input> --%>
																<c:if test="${not empty configDto.listOfExistingFedexUSers}">
                                                                	<c:forEach items="${configDto.listOfExistingFedexUSers}" var="objList1">
                                                                		<input type="password" id="fedexPassword" name="fedexPassword" value="${objList1.fedexPassword}"/>
                                                                	</c:forEach>
                                                                </c:if>
															</td>
														</tr>
														<tr>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.productiontestkey"/>
															</td>
															<td style="font-size:12px;">
																<%-- <form:input path="fedexTestKey"></form:input> --%>
																<c:if test="${not empty configDto.listOfExistingFedexUSers}">
                                                                	<c:forEach items="${configDto.listOfExistingFedexUSers}" var="objList1">
                                                                		<input type="text" id="fedexTestKey" name="fedexTestKey" value="${objList1.fedexTestKey}"/>
                                                                	</c:forEach>
                                                                </c:if>
															</td>
														</tr>
														<tr>
															<th colspan="3" align="left" style="font-size:12px; padding: 5px;">
																<spring:message code="BzComposer.configuration.valueaddedservices"/>
															</th>
														</tr>
														<tr>
															<td colspan="3" align="left" style="font-size:12px;">
																<b><spring:message code="BzComposer.configuration.availableservices"/></b>
															</td>
														</tr>
														<tr>
															<td rowspan="4" style="font-size:12px;">
																<form:select path="selectedRealTimeShippingServiceId" multiple="multiple" style="width:200px; height:200px;">
																    <c:if test="${not empty configDto.listOfExistingRealTimeShippingServices2}">
                                                                    	<c:forEach items="${configDto.listOfExistingRealTimeShippingServices2}" var="objList1">
                                                                    		<option value="${objList1.realTimeShippingServiceId}">${objList1.realTimeShippingService}</option>
                                                                    	</c:forEach>
                                                                    </c:if>
																</form:select>
															</td>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.enterservicename"/> :
																<br>
																<form:input path="upsServiceName" />
															</td>
															<td style="font-size:14px;">
																<button type="button" id="add" name="add" style="width:60px;" class="formButton">
																    <spring:message code="BzComposer.global.add"/>
																</button>
															</td>
														</tr>
														<tr>
															<td style="font-size:12px;">
																<spring:message code="BzComposer.configuration.enterserviceprice"/> ($) :
																<br>
																<form:input path="upsServicePrice" />
															</td>
															<td style="font-size:14px;">
																<button type="button" id="edit" style="width:60px;"class="formButton">
																    <spring:message code="BzComposer.global.edit"/>
																</button>
															</td>
														</tr>
														<tr>
															<td>
															</td>
															<td style="font-size:14px;">
																<button type="button" style="width: 60px;" id="edit" class="formButton">
																    <spring:message code="BzComposer.global.delete"/>
																</button>
															</td>
														</tr>
													</table>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</div>
							<div id="shipping" class="tabPage">
								<div class="form-check">
									<input type="checkbox" id="shippingdData" style="font-size:12px;" />
									<b style="font-size:12px;"><spring:message code="BzComposer.configuration.allowupdateshippingdatamultiple"/></b>
								</div>
								<div class="form-inline mt-2 align-items-start">
									<div class="form-inline mb-2" style="font-size:12px;">
										<spring:message code="BzComposer.configuration.scheduletimes"/> :
									</div>
									<div class="form-inline mx-sm-3 mb-2" style="font-size:12px;">
										<select id="scheduleTime" name="scheduleTime" multiple="multiple" style="width: 180px;">
										</select>
									</div>
									<div class="form-inline mx-sm-3 mb-2 d-block">	
										<p>
										<br>
										<a href="#m1" rel="modal:open">
											<!-- <button type="button" class="formbutton" style="font-size:1em;width:60px;" data-toggle="modal" data-target="#modal">
												Add
											</button> -->
											<button type="button" class="formButton" style="width:60px;font-size:14px;" name="Add" data-target="#modal" data-toggle="modal">
											    <spring:message code='BzComposer.global.add'/>
											</button>
										</a>
										<br><br>
										<button type="button" class="formButton" style="font-size:14px;width:80px;" onclick="removeTime()">
											<spring:message code="BzComposer.configuration.remove"/>
										</button>
										</p>
										<div id="m1" class="modal modal1">
  											<div id="container">
  												<div id="title" style="text-align: center;font-size:12px; padding: 5px;">
  													<h3>
  														<spring:message code="BzComposer.configuration.selecttime"/>
  													</h3>
  												</div>
  												<div id="container">
  													<div class="row" align="center">
  														<table style="text-align: center; width: 600px;">
  															<tr>
  																<td style="font-size: 12px;">
  																	<b>
  																		<spring:message code="BzComposer.configuration.time"/> :
 																	</b>
  																</td>
  																<td style="font-size: 12px;">
  																	<select id="hours">
																		<option value="1">1</option>
																		<option value="2">2</option>
																		<option value="3">3</option>
																		<option value="4">4</option>
																		<option value="5">5</option>
																		<option value="6">6</option>
																		<option value="7">7</option>
																		<option value="8">8</option>
																		<option value="9">9</option>
																		<option value="10">10</option>
																		<option value="11">11</option>
																		<option value="12">12</option>
																	</select>	
																</td>
																<td style="font-size: 12px;">
																	<select id="minutes"> 
																		<option value="0">0</option>
																		<option value="1">1</option>
																		<option value="2">2</option>
																		<option value="3">3</option>
																		<option value="4">4</option>
																		<option value="5">5</option>
																		<option value="6">6</option>
																		<option value="7">7</option>
																		<option value="8">8</option>
																		<option value="9">9</option>
																		<option value="10">10</option>
																		<option value="11">11</option>
																		<option value="12">12</option>
																		<option value="13">13</option>
																		<option value="14">14</option>
																		<option value="15">15</option>
																		<option value="16">16</option>
																		<option value="17">17</option>
																		<option value="18">18</option>
																		<option value="19">19</option>
																		<option value="20">20</option>
																		<option value="21">21</option>
																		<option value="22">22</option>
																		<option value="23">23</option>
																		<option value="24">24</option>
																		<option value="25">25</option>
																		<option value="26">26</option>
																		<option value="27">27</option>
																		<option value="28">28</option>
																		<option value="29">29</option>
																		<option value="30">30</option>
																		<option value="31">31</option>
																		<option value="32">32</option>
																		<option value="33">33</option>
																		<option value="34">34</option>
																		<option value="35">35</option>
																		<option value="36">36</option>
																		<option value="37">37</option>
																		<option value="38">38</option>
																		<option value="39">39</option>
																		<option value="40">40</option>
																		<option value="41">41</option>
																		<option value="42">42</option>
																		<option value="43">43</option>
																		<option value="44">44</option>
																		<option value="45">45</option>
																		<option value="46">46</option>
																		<option value="47">47</option>
																		<option value="48">48</option>
																		<option value="49">49</option>
																		<option value="50">50</option>
																		<option value="51">51</option>
																		<option value="52">52</option>
																		<option value="53">53</option>
																		<option value="54">54</option>
																		<option value="55">55</option>
																		<option value="56">56</option>
																		<option value="57">57</option>
																		<option value="58">58</option>
																		<option value="59">59</option>
																	</select>
																</td>
																<td style="font-size: 12px;">
																	<select id="selectedTime">
																		<option value="AM">
																			<spring:message code="BzComposer.configuration.am"/>
																		</option>
																		<option value="PM">
																			<spring:message code="BzComposer.configuration.pm"/>
																		</option>
																	</select>
																</td>
															</tr>
															<tr>
																<td>
																</td>
																<td style="font-size: 12px;">
																	<b>
																		<%-- <spring:message code="BzComposer.configuration.hours"/> --%>
																		[Hours]
																	</b>
																</td>
																<td style="font-size: 12px;">
																	<b>
																		<%-- <spring:message code="BzComposer.configuration.miuntes"/> --%>
																		[Min.]
																	</b>
																</td>
																<td>
																</td>
  															</tr>
  															<tr>
  																<td colspan="2" style="font-size: 12px;">
  																	<input type="submit" id="ok" style="width:90px;" name="ok" class="formbutton"
  																	value="<spring:message code='BzComposer.global.ok'/>" onclick="showTime()">
  																</td>
  																<td colspan="2" style="font-size: 12px;">
  																	<a href="#" rel="modal:close">
  																		<input type="reset" id="cancel" style="width:90px;" name="cancel" 
  																		class="formbutton" value="<spring:message code='BzComposer.global.cancel'/>">
																	</a>
  																</td>
  															</tr>
  														</table>
  													</div>
  												</div>
  											</div> 
										</div>						
									</div>
									<div class="container" style="font-size: 12px;">
										<b><spring:message code="BzComposer.configuration.shippingdatabasepath"/></b>
									</div>
									<div style="font-size: 12px;">
										<input type="radio" id="rbdDatabasePath" name="rbdDatabasePath"/>
										<spring:message code="BzComposer.ComboBox.Select"/>
										&nbsp;&nbsp;
										<input type="radio" id="rbdDatabasePath" name="rbdDatabasePath"/>
										<spring:message code="BzComposer.configuration.create"/>
										<br>
										<input type="file" id="selectDatabase" accept="(/*.mdb)" value="SHIPPING.mdb">
									</div> 
								</div>
							</div>
							<!-- Value-Added-Services-Start -->
							<div id="valueAddedAervices" style="width:auto;">
                                <table class="table-notifications">
                                    <tr>
                                        <th colspan="2" align="left" style="font-size:12px; padding: 5px;">
                                            <spring:message code="BzComposer.configuration.valueaddedservices" />
                                        </th>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12px;width:200px;">
                                            <spring:message code="BzComposer.configuration.mailtype"/> :
                                        </td>
                                        <td style="font-size:12px;">
                                            <form:select id="selectedMailTypeId" path="selectedMailTypeId" style="width:100px;">
                                                <c:if test="${not empty configDto.listOfExistingMailType}">
                                                    <c:forEach items="${configDto.listOfExistingMailType}" var="objList1">
                                                        <option value="${objList1.mailTypeId}">${objList1.mailType}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </form:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12px;">
                                            <spring:message code="BzComposer.configuration.packagesize"/> :
                                        </td>
                                        <td style="font-size:12px;">
                                            <form:select id="selectedPackageSizeId" path="selectedPackageSizeId" style="width:100px;">
                                                <c:if test="${not empty configDto.listOfExistingPackageSize}">
                                                    <c:forEach items="${configDto.listOfExistingPackageSize}" var="objList1">
                                                        <option value="${objList1.packageSizeId}">${objList1.packageSize}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </form:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12px;">
                                            <spring:message code="BzComposer.configuration.container"/> :
                                        </td>
                                        <td style="font-size:12px;">
                                            <form:select id="selectedContainerId" path="selectedContainerId" style="width:100px;">
                                                <c:if test="${not empty configDto.listOfExistingContainer}">
                                                    <c:forEach items="${configDto.listOfExistingContainer}" var="objList1">
                                                        <option value="${objList1.containerId}">${objList1.container}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </form:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12px;">
                                            <spring:message code="BzComposer.configuration.specialhandlingfee"/> ($) :
                                        </td>
                                        <td style="font-size:12px;">
                                            <input type="text" id="specialHandlingFee" style="width:100px;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td style="font-size:14px;">
                                            <button type="button" class="formButton" id="addService">
                                                <spring:message code="BzComposer.configuration.addservicebtn"/>
                                            </button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th colspan="2" style="font-size:12px; padding: 5px;">
                                            <spring:message code="BzComposer.configuration.valueaddedcalculator" />
                                        </th>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="font-size:12px;">
                                            <spring:message code="BzComposer.configuration.calculatornote"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <div id="valueAddedCalculator">
                                                <fieldset>

                                                    <div class="col-md-12" style="font-size:12px;">
                                                        <div class="col-md-6" align="left" style="font-size:12px;">
                                                            <spring:message code="BzComposer.configuration.extraamount"/> :
                                                            &nbsp;
                                                            <input type="text" id="extraAmount" style="font-size:12px;">
                                                            &nbsp;&nbsp;
                                                            <spring:message code="BzComposer.configuration.unit"/> :
                                                            &nbsp;
                                                            <select id="unit">
                                                                <option>$</option>
                                                                <option>%</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </fieldset>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
							<!-- Value-Added-Services-End -->
					</div>
			</div>
			<!-- shipping Ends -->
			</td>
		</tr>
	</table>
	<div><form:hidden path="fileName" /></div>
	<div><input type="hidden" name="tabid" id="tid" value="" />
	</div>
	</div>
	<div>
	<div align="center">
		<input type="button" class="bottomButton formButton" name="save" value="<spring:message code='BzComposer.global.save'/>" />
		<%-- <form:cancel><spring:message code="BzComposer.global.cancel"/></form:cancel> --%>
		<input type="reset" class="bottomButton formButton" name="Cancel" value="<spring:message code="BzComposer.global.cancel"/>"/>
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div></div>
</form:form>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
</body>
<script type="text/javascript">
function selectshippingtypedialog(){
	event.preventDefault();
	$("#selectshippingtypedialog").dialog({
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

function emptyvaluedialog(){
	event.preventDefault();
	$("#emptyvaluedialog").dialog({
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

function duplicatevaluedialog(){
	event.preventDefault();
	$("#duplicatevaluedialog").dialog({
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

function enterWeightAndPriceDialog(){
	event.preventDefault();
	$("#enterWeightAndPriceDialog").dialog({
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
function selectAtleast1RecordDialog(){
	event.preventDefault();
	$("#selectAtleast1RecordDialog").dialog({
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
</html>
<!-- Dialog box used in this page -->
<div id="selectshippingtypedialog" style="display:none;">
	<p><spring:message code='BzComposer.configuration.tax.selectshippingtype'/></p>
</div>
<div id="selectAtleast1RecordDialog" style="display:none;">
	<p><spring:message code='BzComposer.common.selectAtleast1Record'/></p>
</div>
<div id="enterWeightAndPriceDialog" style="display:none;">
	<p><spring:message code='BzComposer.configuration.tax.enterWeightAndPrice'/></p>
</div>
<div id="emptyvaluedialog" style="display:none;">
	<p><spring:message code='BzComposer.configuration.tax.selectshippingviatoupdate'/></p>
</div>
<div id="duplicatevaluedialog" style="display:none;">
	<p><spring:message code='BzComposer.configuration.tax.duplicatevalue'/></p>
</div>
<!-- Dialog box used in this page -->
<div id="showsuccessdialog" style="display:none;">
	<p>Record updated</p>
</div>