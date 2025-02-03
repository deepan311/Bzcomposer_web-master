<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<jsp:include page="/WEB-INF/jsp/include/headlogo.jsp" />
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<jsp:include page="/WEB-INF/jsp/include/menu.jsp" />
<title><spring:message code="BzComposer.customerinvoicetitle" /></title>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<jsp:include page="customerInvoiceFunctionPage.jsp"></jsp:include>

<script type="text/javascript">
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
    $("#refundReasonSel option").each(function() {
        if ($(this).val() == 1){
            $(this).css("color", 'Blue');
        }
    });

    debugger
    var countryId = '<%= request.getAttribute("countryId")%>';
    //$("#description").val("");

    var isChecked = $('#isRefundAllowed').val();
    if(isChecked == "on") {
        $("#isRefundAllowed").attr('checked', true);
        $("#refundReason").prop('readonly', false);
        $("#refundReasonSel").prop('readonly',false);
        $("#addRefundReason").prop('disabled',false);
        $("#updateRefundReason").prop('disabled',false);
        $("#deleteRefundReason").prop('disabled',false);
        $("#defaultReason").prop('disabled',false);
    }
    else if(isChecked == "off") {
        $("#refundReason").prop('readonly', true);
        $("#refundReasonSel").prop('readonly',true);
        $("#addRefundReason").prop('disabled',true);
        $("#updateRefundReason").prop('disabled',true);
        $("#deleteRefundReason").prop('disabled',true);
        $("#defaultReason").prop('disabled',true);
    }
    else {
        $("#refundReason").prop('readonly', isChecked);
        $("#refundReasonSel").prop('readonly',isChecked);
        $("#addRefundReason").prop('disabled',isChecked);
        $("#updateRefundReason").prop('disabled',isChecked);
        $("#deleteRefundReason").prop('disabled',isChecked);
        $("#defaultReason").prop('disabled',isChecked);
    }

    if(countryId==2){
        $('#customerState').prop('disabled', false);
    }
    else {
        $('#customerState').prop('disabled', true);
    }

    $("#isDefaultCreditTerm").change(function()
    {
        var isChecked = '<%= request.getAttribute("isDefault")%>';
        //var isChecked = "on";
        if($(this).prop("checked") == true){

            $("#isDefaultCreditTerm").attr('checked', true);

            isChecked = "on";
        }
        else if($(this).prop("checked") == false){

            $("#isDefaultCreditTerm").attr('checked', false);

             isChecked = "off";
        }else{
            $("#isDefaultCreditTerm").attr('checked', isChecked);
        }
        document.configurationForm.isDefaultCreditTerm.value = isChecked;
        $("#isDefaultCreditTerm").val(isChecked);
    });

    $("#custTaxable").change(function()
    {
        var isChecked = '<%= request.getAttribute("custTaxableStatus")%>';
        if($(this).prop("checked") == true){

            $("#custTaxable").attr('checked', true);
            isChecked = "on";
        }
        else if($(this).prop("checked") == false){

            $("#custTaxable").attr('checked', false);
             isChecked = "off";
        }
        else{
            $("#custTaxable").attr('checked', isChecked);
            document.configurationForm.custTaxable.value = isChecked;
        }
        $("#custTaxable").val(isChecked);
    });

    $("#addressSettings").change(function()
    {
        var isChecked = '<%= request.getAttribute("addressStatus")%>';
        if($(this).prop("checked") == true)
        {
            $("#addressSettings").attr('checked', true);
            isChecked = "on";
        }
        else if($(this).prop("checked") == false)
        {
            $("#addressSettings").attr('checked', false);
            isChecked = "off";
        }
        else{
            $("#custTaxable").attr('checked', isChecked);
            document.configurationForm.addressSettings.value = isChecked;
        }
        $("#addressSettings").val(isChecked);
    });
    $("#isSalesOrder").change(function()
    {
        var isChecked = '<%= request.getAttribute("salesOrderStatus")%>';
        if($(this).prop("checked") == true){
            $("#isSalesOrder").attr('checked', true);
            isChecked = "on";
        }
        else if($(this).prop("checked") == false){
            $("#isSalesOrder").attr('checked', false);
            isChecked = "off";
        }
        else
        {
            $("#isSalesOrder").attr('checked', isChecked);
            document.configurationForm.isSalesOrder.value = isChecked;
        }
        $("#isSalesOrder").val(isChecked);
    });


    /*for Sales&Invocie panel*/
    $("#saleShowCountry").change(function()
    {
        var isChecked = '<%=request.getAttribute("showCountry")%>';
        if($(this).prop("checked") == true){
            $("#saleShowCountry").attr('checked', true);
            isChecked = "on";
        }
        else if($(this).prop("checked") == false){
            $("#saleShowCountry").attr('checked', false);
            isChecked = "off";
        }
        else
        {
            $("#isSalesOrder").attr('checked', isChecked);
            document.configurationForm.saleShowCountry.value = isChecked;
        }
        $("#saleShowCountry").val(isChecked);
    });

    $("#ratePriceChangable").change(function()
    {
        var isChecked = '<%=request.getAttribute("ratePrice")%>';
        if($(this).prop("checked") == true){
            $("#ratePriceChangable").attr('checked', true);
            isChecked = "on";
        }
        else if($(this).prop("checked") == false){
            $("#ratePriceChangable").attr('checked', false);
             isChecked = "off";
        }
        else
        {
            $("#ratePriceChangable").attr('checked', isChecked);
            document.configurationForm.ratePriceChangable.value = isChecked;
        }
        $("#ratePriceChangable").val(isChecked);
    });

    $("#saleShowTelephone").change(function()
    {
        var isChecked = '<%= request.getAttribute("salesShowTelephone")%>';
        if($(this).prop("checked") == true){
            $("#saleShowTelephone").attr('checked', true);
            isChecked = "on";
        }
        else if($(this).prop("checked") == false){
            $("#saleShowTelephone").attr('checked', false);
            isChecked = "off";
        }
        else
        {
            $("#saleShowTelephone").attr('checked', isChecked);
            document.configurationForm.saleShowTelephone.value = isChecked;
        }
        $("#saleShowTelephone").val(isChecked);
    });

    $("#extraChargeApplicable").change(function()
    {
        var isChecked = '<%= request.getAttribute("extraCharge")%>';
        if($(this).prop("checked") == true){
            $("#extraChargeApplicable").attr('checked', true);
            isChecked = "on";
        }
        else if($(this).prop("checked") == false){
            $("#extraChargeApplicable").attr('checked', false);
             isChecked = "off";
        }
        else
        {
            $("#extraChargeApplicable").attr('checked', isChecked);
            document.configurationForm.extraChargeApplicable.value = isChecked;
        }
        $("#extraChargeApplicable").val(isChecked);
    });

    $("#recurringServiceBill").change(function()
    {
        var isChecked = "on";
        if($(this).prop("checked") == true){
            $("#recurringServiceBill").attr('checked', true);
            isChecked = "on";
        }
        else if($(this).prop("checked") == false){
            $("#recurringServiceBill").attr('checked', false);
             isChecked = "off";
        }
        else
        {
            $("#recurringServiceBill").attr('checked', isChecked);
            document.configurationForm.recurringServiceBill.value = isChecked;
        }
        $("#recurringServiceBill").val(isChecked);
    });

    $("#invoiceLocation").change(function(){
        var filePath = $("#invoiceLocation").val();

        $("invoiceLocation").val(filePath);
        //document.configurationForm.invoiceLocation.value = filePath;
        //debugger
    });

    $("#saveImage").change(function(){
        var filePath = $("#saveImage").val();

        $("saveImage").val(filePath);
        //document.configurationForm.invoiceLocation.value = filePath;
        //debugger
    });

    /* $("#form").submit(function(){
        debugger
        var form = $('form')[0]; // You need to use standard javascript object here
        debugger
        var formData = new FormData(form);
        formData.append('image', $('input[type=file]')[0].files[0]);
        $.ajax({
            url: 'Configuration?tabid=SaveCustomerInvocieSettings',
            data: formData,
            type: 'POST',
            contentType: false, // NEEDED, DON'T OMIT THIS (requires jQuery 1.6+)
            processData: false, // NEEDED, DON'T OMIT THIS
            // ... Other options like success and etc
        });
    }); */
    <%-- Commented on 05-05-2019 due to this error:
    The code of method _jspService(HttpServletRequest, HttpServletResponse) is exceeding the 65535 bytes limit --%>

     $("#isRefundAllowed").change(function ()
    {

        <%-- var isChecked = '<%= request.getAttribute("isRefundAllowed")%>'; --%>
        var checked = "on";
        if($(this).prop("checked") == true)
        {

            $("#isRefundAllowed").attr('checked', true);

            $("#refundReason").prop('readonly', false);
            $("#refundReasonSel").prop('readonly',false);
            $("#addRefundReason").prop('disabled',false);
            $("#updateRefundReason").prop('disabled',false);
            $("#deleteRefundReason").prop('disabled',false);
            $("#defaultReason").prop('disabled',false);

            document.configurationForm.isRefundAllowed.value = "on";
        }
        else if($(this).prop("checked") == false)
        {

            $("#refundReason").prop('readonly', true);
            $("#refundReasonSel").prop('readonly',true);
            $("#addRefundReason").prop('disabled',true);
            $("#updateRefundReason").prop('disabled',true);
            $("#deleteRefundReason").prop('disabled',true);
            $("#defaultReason").prop('disabled',true);

            document.configurationForm.isRefundAllowed.value = "off";
        }
      });
    /* $("#form").submit(function( event )
    {
        var text = $('#description').val();
        var setup = $("#setupID option:selected").val();
        if(text == "" || text == " ")
        {
            alert("<spring:message code='BzComposer.configuration.customerinvoice.emptydata'/>");
        }
        else
        {
            if(setup == "Location")
            {
                debugger
                $.ajax({
                    type: "POST",
                    url:"Configuration?tabid=addDescription&Description="+text,
                    data:  { location : text }
                    }).done(function(data){
                    debugger
                    //$("#state").html(data);
                    $(document).find('div#locationDiv table').replaceWith($(data).find('div#locationDiv').html());
                    //$('select[id="phonecode"]').find('option[id="'+selectedCountry+'"]').attr("selected",true);
                });

                /* document.getElementById('Description').value = text;
                document.getElementById('tabid').value="addDescription";
                document.forms[0].action = "Configuration";
                document.forms[0].submit();
                debugger */
            /*}
        }
    }); */

    $("#reason").val("");
    $("#parentReasonId option").prop("selected",false);
    $("#availableReasons option").prop("selected",false);

});

</script>
</head>
<!-- <body onload="init1();"> -->
<body onload="init();">
<!-- begin shared/header -->
<%-- <form:form action="Configuration?" enctype="multipart/form-data" method="post" id="form"> --%>
<form:form name="configurationForm" action="Configuration" method="post" id="form" modelAttribute="configDto">
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
                <c:forEach items="${Labels}" var="lbl" varStatus="loop">
                    <input type="hidden" id='${loop.index}lid' name='${loop.index}lidname' value='${lbl.value}' />
                    <input type="hidden" id='${loop.index}lname' name='${loop.index}lnm' value='${lbl.label}' />
                </c:forEach>
			</c:if>
		</div>
		<div id="table-negotiations" style="padding: 0; border: 1px solid #ccc;">
			<table cellspacing="0"  style="border: 0;width: 100%;overflow-y:scroll;" class="section-border">
			<span style="font-size:30px;cursor:pointer; margin-left: 20px;" onclick="toggleFunction()">&#9776;</span>
				<tr>
					<td  id="leftMenu" style="position: relative; width: 180px;">
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
						<!-- customerInvoice Starts -->
						<div id="customerInvoice"  style="display:none;padding: 0; position: relative; left: 0;" >
							<div id="tabs" style="height:auto;">
  								<ul>
    								<li style="font-size:12px;"><a href="#Customer"><spring:message code="BzComposer.configuration.tab.customer" /></a></li>
    								<li style="font-size: 12px;"><a href="#orderTemplate"><spring:message code="BzComposer.configuration.tab.orderTemplate" /></a></li>
    								<li style="font-size:12px;"><a href="#soAndEstimation"><spring:message code="BzComposer.configuration.salesorderAndEstimation"/></a></li>
    								<li style="font-size:12px;"><a href="#InvoiceTab"><spring:message code="Bzcomposer.configuration.invoice" /></a></li>
    								<li style="font-size:12px;"><a href="#BillingTab"><spring:message code="BzComposer.accountreceivable.billing" /></a></li>
    								<li style="font-size:12px;"><a href="#TaxTab"><spring:message code="BizComposer.Configuration.Tax" /></a></li>
    								<li style="font-size:12px;"><a href="#RefundSettings" id="RefundSettings0"><spring:message code="BzComposer.configuration.tab.refundsetting" /></a></li>
    								<li style="font-size:12px;"><a href="#CustomerJob" id="Customer&Job0"><spring:message code="BzComposer.configuration.tab.customerjob" /></a></li>
    								<!-- <li style="font-size:12px;"><a href="#Rma"><spring:message code="BzComposer.RMA"/></a></li> -->
    								<li style="font-size:12px;"><a href="#financeCharges"><spring:message code="BzComposer.addnewcustomer.tabs.financecharges"/></a></li>
  								</ul>
  								<div id="Customer">
									<div id="content1" class="tabPage">
   						 				<table class="table-notifications" width="100%">
											<tr>
												<th colspan="5" align="left" style="font-size:12px; padding: 5px;">
													<spring:message code="BzComposer.configuration.tab.customer" />
												</th>
											</tr>
											<tr>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.customerlistsortby" />:
												</td>
												<td style="font-size:12px;">
													<form:select path="sortBy" id="sortBy">
														<form:option value="0"><spring:message code="BzComposer.configuration.companyname"/></form:option>
														<form:option value="1"><spring:message code="BzComposer.global.lastname"/></form:option>
														<%-- <form:option value="0"><spring:message code="BzComposer.configuration.select"/></form:option> --%>
													</form:select>
												</td>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.customerGroup"/>:
												</td>
												<td style="font-size:12px;">
													<form:select path="customerGroup" id="customerGroup">
														<c:if test="${not empty configDto.listOfExistingCustomerGroup}">
															<c:forEach items="${configDto.listOfExistingCustomerGroup}" var="objList1">
																<form:option value="${objList1.customerGroup}">${objList1.groupName}</form:option>
															</c:forEach>
														</c:if>
													</form:select>
												</td>
												<td style="font-size:12px;">
													<%-- <form:file path="saveImage" id="saveImage" accept="image/*"></form:file> --%>
												</td>
											</tr>
											<tr>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.customerCountry"/>
												</td>
												<td id="country" style="font-size:12px;">
													<form:select path="custDefaultCountryID" id="customerCountry" onchange="disable()">
														<c:if test="${not empty configDto.listOfExistingCountry}">
															<c:forEach items="${configDto.listOfExistingCountry}" var="objList1">
																<form:option value="${objList1.countryId}">${objList1.countryName}</form:option>
															</c:forEach>
														</c:if>
													</form:select>
												</td>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.customerState"/>:
												</td>
												<td id="state" style="font-size:12px;">
													<form:select path="selectedStateId" id="customerState">
													    <c:if test="${not empty configDto.listOfExistingState}">
                                                            <c:forEach items="${configDto.listOfExistingState}" var="objList1">
                                                                <form:option value="${objList1.selectedStateId}">${objList1.stateName}</form:option>
                                                            </c:forEach>
                                                        </c:if>
													</form:select>
												</td>
											</tr>
											<tr>
												<td colspan="2" style="font-size:12px;">
													<%-- <form:checkbox path="taxable"> --%>
													<input type="checkbox" name="custTaxable" id="custTaxable" value="${configDto.custTaxable}" ${configDto.custTaxable=='on'?'checked':''} />
													<spring:message code="BzComposer.configuration.allCustomerAreTaxable"/>
												</td>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.province"/>:
												</td>
												<td style="font-size:12px;">
													<form:input path="customerProvince" />
												</td>
											</tr>
											<tr>
												<th colspan="5" align="left" style="font-size:12px; padding: 5px;">
													<spring:message code="BzComposer.configuration.customerprefrence"/>
												</th>
											</tr>
											<tr>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.shipping"/> :
												</td>
												<td style="font-size:12px;">
													<form:select path="customerShippingId" id="customerShippingId" style="width:150px;">
														<!-- <option value="0"></option> -->
														<c:if test="${not empty configDto.listOfExistingShipping}">
                                                            <c:forEach items="${configDto.listOfExistingShipping}" var="objList1">
                                                                <form:option value="${objList1.selectedShippingId}">${objList1.shippingName}</form:option>
                                                            </c:forEach>
                                                        </c:if>
													</form:select>
												</td>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.term"/> :
												</td>
												<td style="font-size:12px;">
									  				<form:select path="selectedTermId" id="selectedTermId" style="width:150px;">
														<!-- <option value="0"></option>	 -->
														<c:if test="${not empty configDto.listOfExistingTerm}">
                                                            <c:forEach items="${configDto.listOfExistingTerm}" var="objList1">
                                                                <form:option value="${objList1.selectedTermId}">${objList1.termName}</form:option>
                                                            </c:forEach>
                                                        </c:if>
													</form:select>
												</td>
											</tr>
											<tr>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.rep"/> :
												</td>
												<td style="font-size:12px;">
													<form:select path="selectedSalesRepId" id="selectedSalesRepId" style="width:150px;">
														<!-- <option value="0"></option> -->
														<c:if test="${not empty configDto.listOfExistingSalesRep}">
                                                            <c:forEach items="${configDto.listOfExistingSalesRep}" var="objList1">
                                                                <option value="${objList1.selectedSalesRepId}">${objList1.salesRepName}</option>
                                                            </c:forEach>
                                                        </c:if>
													</form:select>
												</td>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.paymethod"/>:
												</td>
												<td style="font-size:12px;">
													<form:select path="selectedPaymentId" id="selectedPaymentId" style="width:150px;">
													    <c:if test="${not empty configDto.listOfExistingPayment}">
                                                            <c:forEach items="${configDto.listOfExistingPayment}" var="objList1">
                                                                <form:option value="${objList1.paymentId}">${objList1.paymentName}</form:option>
                                                            </c:forEach>
                                                        </c:if>
													</form:select>
												</td>
											</tr>
											<tr>
                                                <td style="font-size:12px;">
                                                    <spring:message code="BzComposer.common.displayPeriod"/>
                                                </td>
                                                <td style="font-size:12px;">
                                                    <form:select path="displayPeriod" id="displayPeriod" style="width:150px;">
                                                        <form:option value="3">3 <spring:message code="BzComposer.payroll.Month"/></form:option>
                                                        <form:option value="6">6 <spring:message code="BzComposer.payroll.Month"/></form:option>
                                                        <form:option value="12">1 <spring:message code="BzComposer.payroll.year"/></form:option>
                                                        <form:option value="24">2 <spring:message code="BzComposer.payroll.year"/></form:option>
                                                        <form:option value="36">3 <spring:message code="BzComposer.payroll.year"/></form:option>
                                                        <form:option value="48">4 <spring:message code="BzComposer.payroll.year"/></form:option>
                                                        <form:option value="60">5 <spring:message code="BzComposer.payroll.year"/></form:option>
                                                    </form:select>
                                                </td>
                                                <td colspan="3">&nbsp;</td>
                                            </tr>
											<tr>
                                                <th colspan="5" align="left" style="font-size:12px; padding: 5px;">
                                                    <spring:message code="BzComposer.configuration.addresssettings"/>
                                                </th>
                                            </tr>
											<tr>
												<td colspan="5" style="font-size:12px;">
													<input type="checkbox" id="addressSettings" name="addressSettings" value="${configDto.addressSettings}" ${configDto.addressSettings=='on'?'checked':''} />
													<label><spring:message code="BzComposer.configuration.addresssettingstext"/></label>
												</td>
											</tr>
											<!--<tr>
												<th colspan="5" align="left" style="font-size:12px; padding: 5px;">
													<spring:message code="BzComposer.configuration.salesorderpreference"/>
												</th>
											</tr>
											<tr>
												<td colspan="5" style="font-size:12px;">
													<form:checkbox path="isSalesOrder" id="isSalesOrder" value="${configDto.isSalesOrder}" />
													<spring:message code="BzComposer.configuration.usesalesorder"/>
												</td>
											</tr>-->
										</table>
 									</div>
								</div>

								<!-- Sales-Order and Estimation Starts -->
								<div id="soAndEstimation">
									<table class="table-notifications">
										<tr>
                                            <th align="left" colspan="2" style="font-size:12px; padding: 5px;">
                                                <spring:message code="BzComposer.configuration.salesorderpreference"/>
                                            </th>
                                        </tr>
                                        <tr>
                                            <td style="font-size:12px;width:30%;">
                                                <spring:message code="BzComposer.configuration.startingSalesOrderNumber"/>:
                                            </td>
                                            <td style="font-size:12px;">
                                                <form:input path="startSalesOrderNum" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="font-size:12px;">
                                                <spring:message code="BzComposer.configuration.salesOrderStyle"/> :
                                            </td>
                                            <td style="font-size:12px;">
                                                <form:select path="soStyleID" id="soStyleID">
                                                    <c:if test="${not empty configDto.listOfExistingInvoiceStyle}">
                                                        <c:forEach items="${configDto.listOfExistingInvoiceStyle}" var="objList1">
                                                            <form:option value="${objList1.selectedInvoiceStyleId}">${objList1.invoiceStyle}</form:option>
                                                        </c:forEach>
                                                    </c:if>
                                                </form:select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th align="left" colspan="2" style="font-size:12px; padding: 5px;">
                                                <spring:message code="BzComposer.configuration.estimationpreference"/>
                                            </th>
                                        </tr>
                                        <tr>
                                            <td style="font-size:12px;">
                                                <spring:message code="BzComposer.configuration.startingestimationnumber"/>:
                                            </td>
                                            <td style="font-size:12px;">
                                                <form:input path="startEstimationNum" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="font-size:12px;">
                                                <spring:message code="BzComposer.Estimation.EstimationStyle"/> :
                                            </td>
                                            <td style="font-size:12px;">
                                                <form:select path="estimationStyleID" id="estimationStyleID">
                                                    <c:if test="${not empty configDto.listOfExistingInvoiceStyle}">
                                                        <c:forEach items="${configDto.listOfExistingInvoiceStyle}" var="objList1">
                                                            <form:option value="${objList1.selectedInvoiceStyleId}">${objList1.invoiceStyle}</form:option>
                                                        </c:forEach>
                                                    </c:if>
                                                </form:select>
                                            </td>
                                        </tr>
                                    </table>
								</div>
								<!-- estimation Ends -->
								<div id="InvoiceTab">
									<div id="content1" class="tabPage">
   										<table class="table-notifications" width="100%">
											<tr>
												<th colspan="4" align="left" style="font-size:12px; padding: 5px;">
													<spring:message code="BzComposer.configuration.invoicesalespreference" />
												</th>
											</tr>
											<tr>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.startinvoicenumber"/> :
												</td>
												<td style="font-size:12px;">
													<form:input path="startInvoiceNum" id="startInvoiceNum" />
												</td>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.defaultpackingslipstyle"/> :
												</td>
												<td style="font-size:12px;">
													<form:select path="packingSlipTemplateId" id="packingSlipTemplateId">
													    <c:if test="${not empty configDto.listOfExistingPackingSlipTemplate}">
                                                            <c:forEach items="${configDto.listOfExistingPackingSlipTemplate}" var="objList1">
                                                                <form:option value="${objList1.packingSlipTemplateId}">${objList1.packingSlipTemplateName}</form:option>
                                                            </c:forEach>
                                                        </c:if>
													</form:select>
												</td>
											</tr>
											<tr>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.invoicestyle"/> :
												</td>
												<td style="font-size:12px;">
													<form:select path="invStyleID" id="invStyleID">
													    <c:if test="${not empty configDto.listOfExistingInvoiceStyle}">
                                                            <c:forEach items="${configDto.listOfExistingInvoiceStyle}" var="objList1">
                                                                <form:option value="${objList1.selectedInvoiceStyleId}">${objList1.invoiceStyle}</form:option>
                                                            </c:forEach>
                                                        </c:if>
													</form:select>
												</td>
												<!--<td style="font-size:12px;">
													<spring:message code="Bizcomposer.PoNumPrefix"/>
												</td>-->
												<td style="font-size:12px;">
													<form:hidden path="poNumPrefix" id="poNumPrefix" />
												</td>
											</tr>
											<tr>
												<td style="font-size:12px;">
													<spring:message code="BzComposer.configuration.defaultfootnote"/> :
												</td>
												<td style="font-size:12px;">
													<form:select path="selectedMessageId" id="selectedMessageId">
													    <c:if test="${not empty configDto.messages}">
                                                            <c:forEach items="${configDto.messages}" var="objList1">
                                                                <form:option value="${objList1.selectedMessageId}">${objList1.messageName}</form:option>
                                                            </c:forEach>
                                                        </c:if>
													</form:select>
												</td>
											</tr>
											<tr>
											<td style="font-size:12px;">
												<input type="checkbox" name="saleShowCountry" id="saleShowCountry" value="${configDto.saleShowCountry}" ${configDto.saleShowCountry=='on'?'checked':''} />
												<label><spring:message code="BzComposer.configuration.showcountrynameoninvoice"/></label>
											</td>
											<td style="font-size:12px;">
												<input type="checkbox" name="ratePriceChangable" id="ratePriceChangable" value="${configDto.ratePriceChangable}" ${configDto.ratePriceChangable=='on'?'checked':''} />
												<label><spring:message code="BzComposer.configuration.ratepricechangable"/></label>
											</td>
											<td style="font-size:12px;">
												<input type="checkbox" name="saleShowTelephone" id="saleShowTelephone" value="${configDto.saleShowTelephone}" ${configDto.saleShowTelephone=='on'?'checked':''} />
												<label><spring:message code="BzComposer.configuration.showtelephonefaxoninvoice"/></label>
											</td>
											<!-- <td style="font-size:12px;">
												<input type="checkbox" name="isSalePrefix" id="isSalePrefix" value="${configDto.isSalePrefix}" ${configDto.isSalePrefix=='on'?'checked':''} />
												<label><spring:message code="BzComposer.configuration.useprefix"/></label>
											</td> -->
										</tr>
							<tr>
								<th colspan="4" align="left" style="font-size:12px; padding: 5px;">
								    <spring:message code="BzComposer.configuration.dropshippingcharge" />
								</th>
							</tr>
							<tr>
								<td style="font-size:12px;">
									<spring:message code="BzComposer.configuration.dropshippingcharge"/> ($) :
								</td>
								<td style="font-size:12px;">
									<form:input path="dropShipCharge" id="dropShipCharge" />
								</td>
							</tr>
							<tr>
								<td style="font-size:12px;">
									<form:radiobutton path="isShowDropShipItems" value="0" />
									<label><spring:message code="BzComposer.configuration.showallitems"/></label>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="left" style="font-size:12px;">
									<form:radiobutton path="isShowDropShipItems" value="1" />
									<label><spring:message code="BzComposer.configuration.showdropshipitemsonly"/></label>
								</td>
							</tr>
							<tr>
								<th colspan="4" align="left" style="font-size:12px; padding: 5px;"><spring:message code="BzComposer.configuration.extrachargeforinvoice" /></th>
							</tr>
							<tr>
								<td style="font-size:12px;">
									<input type="checkbox" name="extraChargeApplicable" value="${configDto.extraChargeApplicable}" ${configDto.extraChargeApplicable=='on'?'checked':''} />
									<label><spring:message code="BzComposer.configuration.isextrachargeppplicable"/></label>
								</td>
							</tr>
							<tr>
								<td style="font-size:12px;">
									<spring:message code="BzComposer.configuration.chargeextra"/> ($) :
								</td>
								<td style="font-size:12px;">
									<form:input path="chargeAmount" />
								</td>
								<td style="font-size:12px;">
									<spring:message code="BzComposer.configuration.iftotalorderislessthan"/> :
								</td>
								<td style="font-size:12px;">
									<form:input path="orderAmount" />
								</td>
							</tr>
							<tr>
								<td style="font-size:12px;">
									<input type="checkbox" name="backOrderNeeded" value="0" />
									<spring:message code="BzComposer.configuration.backorderconfirmationneeded"/>
								</td>
							</tr>

						</table>
 					</div>
				</div>

				<!-- ================== BillingTab ================== -->
					<div id="BillingTab">
					<div id="content1" class="tabPage">
   						 <table class="table-notifications" width="100%">
							<tr>
                                <th colspan="4" align="left" style="font-size:12px; padding: 5px;">
                                    <spring:message code="BzComposer.configuration.recurringservicebilling" />
                                </th>
                            </tr>
                            <tr>
                                <td style="font-size:12px;">
                                    <input type="checkbox" name="recurringServiceBill" id="recurringServiceBill" value="${configDto.recurringServiceBill}" ${configDto.recurringServiceBill=='on'?'checked':''} />
                                    <label><spring:message code="BzComposer.configuration.userecurringservicebill"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size:12px;">
                                    <spring:message code="BzComposer.configuration.recurringservicebillingname"/> :
                                </td>
                                <td style="font-size:12px;">
                                    <input type="text" id="serviceBillName" name="serviceBillName" value="Recurring Service Billing" style="width:200px;">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="font-size:12px;">
                                    <spring:message code="BzComposer.configuration.recurringservicebillingnote"/>
                                </td>
                            </tr>
						</table>
 					</div>
				</div>

				<!-- ======================== TaxTab========================= -->
                <div id="TaxTab">
                    <table class="table-notifications">
                        <tr>
                            <th colspan="2" style="font-size:12px; padding: 5px;">
                                <spring:message code="BizComposer.Configuration.Tax"/>
                            </th>
                        </tr>
                        <tr>
                            <td style="font-size:12px;width:30%;">
                                <spring:message code="BzComposer.configuration.taxcode"/> :
                            </td>
                            <td style="font-size:12px;">
                                <form:input path="salesTaxCode" id="salesTaxCode" />
                            </td>
                        </tr>
                        <tr>
                            <td style="font-size:12px;">
                                <spring:message code="BzComposer.common.LACounty"/> <spring:message code="BzComposer.configuration.taxrate"/>
                            </td>
                            <td style="font-size:12px;">
                                <form:input path="saleTaxRate" id="saleTaxRate" onkeypress="return numbersonly(event, this.value)" />
                            </td>
                        </tr>
                        <tr>
                            <td style="font-size:12px;">
                                <spring:message code="BzComposer.common.OrangeCounty"/> <spring:message code="BzComposer.configuration.taxrate"/>
                            </td>
                            <td style="font-size:12px;">
                                <form:input path="saleTaxRate2" id="saleTaxRate2" onkeypress="return numbersonly(event, this.value)" />
                            </td>
                        </tr>
                        <tr>
                            <td style="font-size:12px;">
                                <spring:message code="BzComposer.configuration.howoftenyoupaysalestax"/> :
                            </td>
                            <td style="font-size:12px;">
                                <form:radiobutton path="howOftenSalestax" value="1" id="monthlySalesTax" />
                                <label><spring:message code="BzComposer.configuration.monthly"/></label>
                                &nbsp;
                                <form:radiobutton path="howOftenSalestax" value="2" id="quartely" />
                                <label><spring:message code="BzComposer.configuration.quarterly"/></label>
                                &nbsp;
                                <form:radiobutton path="howOftenSalestax" value="3" id="annually" />
                                <label><spring:message code="BzComposer.configuration.annually"/></label>
                            </td>
                        </tr>
                    </table>
                </div>

				<div id="RefundSettings">
					<div id="content1" class="tabPage">
						<input type="checkbox" name="isRefundAllowed" id="isRefundAllowed" value="${configDto.isRefundAllowed}" ${configDto.isRefundAllowed=='on'?'checked':''} />
						<label><spring:message code="BzComposer.configuration.allowrefundforsaleorder"/></label>
						<div id="refundDiv" style="display:block;">
   						<table class="table-notifications" width="100%">
							<tr>
								<th colspan="2" style="font-size:12px; padding: 5px;">
									<spring:message code="BzComposer.configuration.refundsetting" />
						        </th>
							</tr>
							<tr>
								<td align="justify" style="font-size:12px;width:40%;">
									<select id="refundReasonSel" size="10" style="display:block; width: 200px; height: 200px;" onchange="setReason(this);">
									<option id="0" value="0"><spring:message code="BzComposer.configuration.defaultreason"/></option>
									<c:if test="${not empty configDto.listOfExistingRefundReason}">
                                        <c:forEach items="${configDto.listOfExistingRefundReason}" var="objList1">
                                            <option id="${objList1.selectedRefundReasonId}" value="${objList1.isDefaultRefundReason}">${objList1.refundReason}</option>
                                        </c:forEach>
                                    </c:if>
									</select>
									<br>
									<button type="button" id="defaultReason" name="defaultReason" onclick="setDefaultReason()" class="formButton" style="font-size: 14px;">
										<spring:message code="BzComposer.configuration.setasdefaultreasonbtn"/>
									</button>
									<br><br>
									<spring:message code="BzComposer.configuration.defaultreasonnote"/>
								</td>
								<td style="font-size:12px;">
                                    <br><br><b><spring:message code="BzComposer.configuration.reason" /></b><br>
                                    <input type="text" id="refundReason" name="refundReason" style="width:70%;" /><br><br>
                                    <button type="button" class="formButton" id="AddRefundReason" onclick="addNewRefundReason()" style="width:60px;font-size: 14px;">
                                        <spring:message code="BzComposer.global.add"/>
                                    </button>&nbsp;&nbsp;
                                    <button type="button" id="updateRefundReason" class="formButton" onclick="updateExistingRefundReason()" style="width: 80px; font-size: 14px;">
                                        <spring:message code="BzComposer.global.update"/>
                                    </button>&nbsp;&nbsp;
                                    <button type="button" id="deleteRefundReason" class="formbutton" onclick="deleteSelectedRefundReason()" style="width: 60px; font-size: 14px;">
                                        <spring:message code="BzComposer.global.delete"/>
                                    </button>
                                </td>
							</tr>
						</table>
						<table class="table-notifications" width="100%">
                            <tr>
                                <th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
                                    <b><spring:message code="BzComposer.RMA"/> : <spring:message code="BzComposer.configuration.addmodifyreason" /></b>
                                </th>
                            </tr>
                            <tr>
                                <td rowspan="2" align="justify" style="font-size:12px;width:40%;">
                                    <form:select path="parentReasonId"  id="parentReasonId" size="10" style="height:200px;font-size: 12px;" onchange="selectReason()">
                                        <c:if test="${not empty configDto.listOfExistingMasterReasonType}">
                                            <c:forEach items="${configDto.listOfExistingMasterReasonType}" var="objList1">
                                                <option name="${objList1.reason}" id="${objList1.parentReasonId}" value="${objList1.reasonId}">${objList1.reason}</option>
                                                <%-- <option style="display: none;" id="${objList1.reason}" value="${objList1.parentReasonId}">${objList1.reasonType}</option> --%>
                                            </c:forEach>
                                        </c:if>
                                    </form:select>
                                </td>
                                <td style="font-size: 12px;">
                                    <br><br>
                                    <b><spring:message code="BzComposer.configuration.reason" /></b><br>
                                    <form:input path="reason" id="reason" style="width:70%;" />
                                    <br><br>
                                    <spring:message code="BzComposer.configuration.type"/>&nbsp;&nbsp;&nbsp;
                                    <form:select path="reasonTypeId" id="availableReasons">
                                        <c:if test="${not empty configDto.listOfExistingReasonType}">
                                            <c:forEach items="${configDto.listOfExistingReasonType}" var="objList1">
                                                <option value="${objList1.reasonTypeId}">${objList1.reasonType}</option>
                                            </c:forEach>
                                        </c:if>
                                    </form:select>
                                    <br><br>
                                    <button type="button" class="formButton" id="Save" style="width:70px;" onclick="addNewReason()">
                                        <spring:message code='BzComposer.global.save'/>
                                    </button>
                                    <button type="button" class="formButton" id="Update" style="width:70px;" onclick="updateRMAReason()">
                                        <spring:message code='BzComposer.global.update'/>
                                    </button>
                                    <button type="button" class="formButton" id="Delete" style="width:70px;" onclick="deleteReason()">
                                        <spring:message code='BzComposer.global.delete'/>
                                    </button>
                                    <button type="button" class="formButton" id="Clear" style="width:70px;" onclick="clearValues()">
                                        <spring:message code='BzComposer.global.clear'/>
                                    </button>
                                </td>
                            </tr>
                        </table>
						</div>
 					</div>
				</div>

				<div id="CustomerJob">
					<div id="content1" class="tabPage">
   						 <div id="refundDiv" style="display:block;">
   						<table class="table-notifications" width="100%">
							<tr>
								<th colspan="4" align="left" style="font-size:12px; padding: 5px;">
									<spring:message code="BzComposer.configuration.tab.customerjob" />
								</th>
							</tr>
							<tr>
								<td style="font-size:14px;">
									<b><spring:message code="BzComposer.configuration.jobcategory" /></b><br>
									<input type="text" id="txtJobCategory" name="txtJobCategory" /><br><br>
									<button type="button" id="addJobCategory" name="addJobCategory" onclick="addNewJobCategory()" class="formButton">
										<spring:message code="BzComposer.global.add"/>
									</button>&nbsp;&nbsp;
									<button type="button" id="updateJobCategory" name="updateJobCategory" onclick="updateExistingJobCategory()" class="formButton">
										<spring:message code="BzComposer.global.update"/>
									</button>&nbsp;&nbsp;
									<button type="button" id="deleteJobCategory" name="deleteJobCategory" onclick="deleteSelectedJobCategory()" class="formButton">
										<spring:message code="BzComposer.global.delete"/>
									</button>
								</td>
								<td rowspan="2" align="justify">
									<select id="jobCategory" name="jobCategory" style="display:block; width: 200px; height: 200px;font-size:12px;" multiple="multiple">
									<option value="" onclick="setCategory()"><spring:message code="BzComposer.configuration.defaultcategory"/></option>
									<c:if test="${not empty configDto.listOfExistingJobCategory}">
                                        <c:forEach items="${configDto.listOfExistingJobCategory}" var="objList1">
                                            <option id="${objList1.recurringServiceBill}" onclick="setCategory()" value="${objList1.jobCategoryId}">${objList1.jobCategory}</option>
                                        </c:forEach>
                                    </c:if>
									</select>
								</td>
							</tr>
						</table> 
						</div>   
 					</div>
				</div>

				<!-- RMA Start here -->
                <div id="Rma" style="display:none;">
                    <div id="content1" class="tabPage">
                        <!-- RMA Starts -->
                        <div id="tabs2" style="height:450px;">
                            <ul>
                                <li style="font-size: 12px; padding: 5px;"><a href="#addModifyReason">
                                    <spring:message code="BzComposer.configuration.addmodifyreason" /></a>
                                </li>
                                <li style="font-size: 12px; padding: 5px;"><a href="#chargeForRMAItem" style="display:none;">
                                    <spring:message code="BzComposer.configuration.chargeforrma" /></a>
                                </li>
                            </ul>
                            <div id="addModifyReason">

                            </div>
                            <div id="chargeForRMAItem" style="display:none;">
                                <table class="table-notifications" width="100%">
                                    <tr>
                                        <th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
                                            <b><spring:message code="BzComposer.configuration.chargeforrma" /></b>
                                        </th>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 12px;">
                                            <spring:message code="BzComposer.configuration.chargeforrmaitem"/> :
                                        </td>
                                        <td>
                                            <%-- <form:input path="" /> --%>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <!-- RMA Ends -->
                    </div>
                </div>

                <!-- finance-Charges Starts -->
                <div id="financeCharges">
                    <div id="table-negotiations" style="width:100%">
                        <table class="tabla-listados" cellspacing="0">
                            <thead>
                                <tr>
                                    <th colspan="4" style="font-size: 12px;">
                                        <spring:message code="BzComposer.addnewcustomer.financemsg"/>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <div>
                                            <div id="table-negotiations" style="width:48%;float: left;">
                                                <table class="tabla-listados" cellspacing="0">
                                                    <thead>
                                                        <tr>
                                                            <th colspan="4" style="font-size: 12px;">
                                                                <spring:message code="BzComposer.configuration.chargerate"/>
                                                            </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td style="font-size: 12px;">
                                                                <spring:message code="BzComposer.configuration.annualinterestrate"/>
                                                            </td>
                                                            <td colspan="3">
                                                                <form:input onkeypress="return numbersonly(event, this.value)" path="annualInterestRate" />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="font-size: 12px;">
                                                                <spring:message code="BzComposer.configuration.minimumfinancerate" />
                                                            </td>
                                                            <td colspan="3" style="font-size: 12px;">
                                                                <form:input onkeypress="return numbersonly(event, this.value)" path="minCharge" />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="font-size: 12px;">
                                                                <spring:message code="BzComposer.configuration.graceperiodindays" />
                                                            </td>
                                                            <td colspan="3">
                                                                <form:input onkeypress="return numbersonly(event, this.value)" path="gracePeriod" />
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div id="table-negotiations" style="width:48%;float: right;">
                                                <table class="tabla-listados" cellspacing="0">
                                                    <thead>
                                                        <tr>
                                                            <th colspan="4" style="font-size: 12px;">
                                                                <spring:message code="BzComposer.configuration.applyingcharges" />
                                                            </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td colspan="4" style="font-size: 12px;">
                                                                <input type="checkbox" name="assessFinanceCharge" id="assessFinanceCharge" value="${configDto.assessFinanceCharge}" ${configDto.assessFinanceCharge=='on'?'checked':''} />
                                                                <spring:message code="BzComposer.addnewcustomer.assessfinance"/>
                                                            </td>
                                                        </tr>
                                                        <tr><td colspan="4">&nbsp;</td></tr>
                                                        <tr>
                                                            <td colspan="4" style="font-size: 12px;">
                                                                <input type="checkbox" name="markFinanceCharge" id="markFinanceCharge" value="${configDto.markFinanceCharge}" ${configDto.markFinanceCharge=='on'?'checked':''} />
                                                                <spring:message code="BzComposer.addnewcustomer.markfinanacechargeinvoice"/>
                                                            </td>
                                                        </tr>
                                                        <tr><td colspan="4">&nbsp;</td></tr>
                                                        <tr><td colspan="4">&nbsp;</td></tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </td>
                                    <td colspan="3">&nbsp;</td>
                                </tr>
                                <tr><td colspan="4">&nbsp;</td></tr>
                            </table>
                        </div>
                </div>
                <!-- finance-Charges Ends -->
                <!-- Order-Template-Start -->
                <div id="orderTemplate" style="font-size: 12px;">
                    <div id="content3" class="tabPage">
                        <table class="table-notifications" width="100%">
                            <tr>
                                <th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
                                <spring:message code="BzComposer.configuration.importantordertemplate" />
                                </th>
                            </tr>
                            <tr>
                                <td style="font-size: 12px;">
                                    <spring:message code="BzComposer.configuration.importantordertemplate.templatename" />
                                </td>
                                <td style="font-size: 12px;">
                                    <form:input path="templateName" />
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size: 12px;">
                                    <spring:message code="BzComposer.configuration.choosefilebtn"/>
                                </td>
                                <td style="font-size: 12px;">
                                    <input type="file" name="exelFile" accept=".xls" />
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td colspan="2" style="font-size: 12px;">
                                    <spring:message code="Bzcomposer.configuration.choosefilebtn.onlyexcelfiles"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size: 12px;">
                                    <input type="checkbox" name="def" id="def" value="${configDto.def}" ${configDto.def=='on'?'checked':''} />
                                    <label><spring:message code="BzComposer.configuration.defaultchkbox" /></label>
                                </td>
                            </tr>
                            <tr>
                                <th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
                                    <spring:message code="BzComposer.configuration.fieldsmapping" />
                                </th>
                            </tr>
                            <tr>
                                <td style="font-size: 12px;"><b><spring:message code="BzComposer.configuration.databasefieldsname"/> </b></td>
                                <td style="font-size: 12px;"><b><spring:message code="BzComposer.configuration.mappingfieldsname"/></b></td>
                            </tr>
                            <tr>
                                <td style="font-size: 12px;"><spring:message code= "BzComposer.configuration.itemcode"/>*</td>
                                <td style="font-size: 12px;"><form:input path="itemCode" /></td>
                            </tr>
                            <tr>
                                <td style="font-size: 12px;"><spring:message code = "BzComposer.configuration.itemname"/></td>
                                <td style="font-size: 12px;"><form:input path="itemName" /></td>
                            </tr>
                            <tr>
                                <td style="font-size: 12px;"><spring:message code="BzComposer.configuration.qty"/>*</td>
                                <td style="font-size: 12px;"><form:input path="qty" /></td>
                            </tr>
                            <tr>
                                <td style="font-size: 12px;"><spring:message code="BzComposer.configuration.unitprice"/>* ($)</td>
                                <td style="font-size: 12px;"><form:input path="unitPrice" /></td>
                            </tr>
                            <tr>
                                <td style="font-size: 12px;"><spring:message code="BzComposer.configuration.unitweight"/></td>
                                <td style="font-size: 12px;"><form:input path="weight" /></td>
                            </tr>
                            <tr>
                                <td style="font-size: 12px;"><spring:message code="BzComposer.configuration.taxable"/></td>
                                <td style="font-size: 12px;"><form:input path="taxable" /></td>
                            </tr>
                        </table>
                        <div id="orderTemplateList">
                            <table class="table-notifications" width="80%">
                                <tr>
                                    <th colspan="2" align="left" style="font-size: 12px;  padding: 5px;">
                                        <spring:message code="BzComposer.configuration.ordertemplatelist" />
                                    </th>
                                </tr>
                                <tr>
                                    <td></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- Order-Template-End -->

                </div></div>
                <!-- customerInvoice Ends -->
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
		<input type="hidden" name="selectedSalesRepId" id="selectedSalesRepId" value="" />

		<input type="hidden" name="custTaxable" id="custTaxable" value=""/>
		<input type="hidden" name="addressSettings" id="addressSettngs" value=""/>
		<input type="hidden" name="isSalesOrder" id="isSalesOrder" value=""/>
		
		<input type="hidden" name="Description" id="Description" value=""/>
		<input type="hidden" name="isDefault" id="isDefault" value=""/>
		<input type="hidden" name="creditTermDays" id="creditTermDays" value=""/>
		
		<input type="hidden" name="saleShowCountry" id="saleShowCountry" value=""/>
		<input type="hidden" name="ratePriceChangable" id="ratePriceChangable" value=""/>
		<input type="hidden" name="saleShowTelephone" id="saleShowTelephone" value=""/>
		<input type="hidden" name="isSalePrefix" id="isSalePrefix" value=""/>
		<input type="hidden" name="extraChargeApplicable" id="extraChargeApplicable" value=""/>
		<input type="hidden" name="resonId" id="resonId" />
		<input type="hidden" id="pageActiveTab" value="${pageActiveTab}" />
	</div>
	</div>
	<div align="center">
		<input type="submit" class="bottomButton formButton" onclick="SaveValues()" value="<spring:message code='BzComposer.global.save'/>" />
		<input type="reset" class="bottomButton formButton" onclick="RevokeValues()" value="<spring:message code='BzComposer.global.cancel'/>" />
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
let pageActiveTabID = document.getElementById("pageActiveTab").value;
if(pageActiveTabID != undefined && pageActiveTabID != ''){
    document.getElementById(pageActiveTabID).click()
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
                $(this).dialog("close");
                debugger;
                /*Customer Panel*/
                document.configurationForm.sortBy.value = document.configurationForm.sortBy.value;
                document.configurationForm.customerGroup.value = document.configurationForm.customerGroup.value;
                document.configurationForm.custDefaultCountryID.value = document.configurationForm.custDefaultCountryID.value;
                document.configurationForm.selectedStateId.value = document.configurationForm.selectedStateId.value;
                document.configurationForm.custTaxable.value = $("#custTaxable").val();
                document.configurationForm.customerProvince.value = document.configurationForm.customerProvince.value;
                document.configurationForm.customerShippingId.value = document.configurationForm.customerShippingId.value;
                document.configurationForm.selectedTermId.value = document.configurationForm.selectedTermId.value;
                document.configurationForm.selectedSalesRepId.value = document.configurationForm.selectedSalesRepId.value;
                document.configurationForm.selectedPaymentId.value = document.configurationForm.selectedPaymentId.value;
                document.getElementById('custTaxable').value = $("#custTaxable").val();
                document.getElementById('addressSettings').value = $("#addressSettings").val();
                document.getElementById('isSalesOrder').value = $("#isSalesOrder").val();

                /*Sales&Invoice Panel*/
                document.configurationForm.startInvoiceNum.value = document.configurationForm.startInvoiceNum.value;
                document.configurationForm.packingSlipTemplateId.value = document.configurationForm.packingSlipTemplateId.value;
                document.configurationForm.invStyleID.value = document.configurationForm.invStyleID.value;
                document.configurationForm.poNumPrefix.value = document.configurationForm.poNumPrefix.value;
                document.configurationForm.selectedMessageId.value = document.configurationForm.selectedMessageId.value;

                document.configurationForm.saleShowCountry.value = $("#saleShowCountry").val();
                document.configurationForm.ratePriceChangable.value = $("#ratePriceChangable").val();
                document.configurationForm.saleShowTelephone.value = $("#saleShowTelephone").val();
                document.configurationForm.isSalePrefix.value = $("#isSalePrefix").val();

                document.configurationForm.salesTaxCode.value = document.configurationForm.salesTaxCode.value;
                document.configurationForm.saleTaxRate.value = document.configurationForm.saleTaxRate.value;
                document.configurationForm.howOftenSalestax.value = document.configurationForm.howOftenSalestax.value;
                document.configurationForm.dropShipCharge.value = document.configurationForm.dropShipCharge.value;
                document.configurationForm.isShowDropShipItems.value = document.configurationForm.isShowDropShipItems.value;
                document.configurationForm.extraChargeApplicable.value = $("#extraChargeApplicable").val();
                document.configurationForm.chargeAmount.value = document.configurationForm.chargeAmount.value;
                document.configurationForm.orderAmount.value = document.configurationForm.orderAmount.value;

                /*for Refund Settings panel*/
                document.configurationForm.isRefundAllowed.value =  $("#isRefundAllowed").val();
                document.configurationForm.recurringServiceBill.value =  $("#recurringServiceBill").val();

                /*for Invoice Save Option*/
                //document.configurationForm.invoiceLocation.value = document.configurationForm.invoiceLocation.value ;

                document.getElementById('saleShowCountry').value = $("#saleShowCountry").val();
                document.getElementById('ratePriceChangable').value = $("#ratePriceChangable").val();
                document.getElementById('saleShowTelephone').value = $("#saleShowTelephone").val();
                document.getElementById('isSalePrefix').value = $("#isSalePrefix").val();
                document.getElementById('extraChargeApplicable').value = $("#extraChargeApplicable").val();
                document.getElementById('creditTermDays').value = $("#isRefundAllowed").val();
                if($("#parentReasonId").val() == null){
                    $("#parentReasonId").val(0);
                }

                //Estimation value
                document.configurationForm.startEstimationNum.value = document.configurationForm.startEstimationNum.value;
                document.configurationForm.assessFinanceCharge.value = $("#assessFinanceCharge").prop('checked')?'on':'off';
                document.configurationForm.markFinanceCharge.value = $("#markFinanceCharge").prop('checked')?'on':'off';

                /* document.getElementById('tabid').value="SaveCustomerInvoiceSettings";
                document.forms["form"].action = "Configuration";
                document.forms["form"].submit(); */

                var formData = $('form').serialize();
                $.ajax({
                    type : "POST",
                    url : "ConfigurationAjax/SaveConfiguration?tabid=SaveCustomerInvoiceSettings",
                    data : formData,
                    success : function(data) {
                        showsSuccessDialog();
                    },
                    error : function(data) {
                        alert("<bean:message key='BzComposer.common.erroroccurred'/>");
                    }
                });
            },
            "<spring:message code='BzComposer.global.cancel'/>": function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    return false;
}
function setDescription()
{
	var text = $('#location :selected').text();
	document.getElementById("description").value = text;
}

function setDescription1()
{
	var text = $('#message :selected').text();
	document.getElementById("description").value = text;
}

function setDescription2()
{
	var text = $('#rep :selected').text();
	document.getElementById("description").value = text;
}

function setDescription3()
{
	var text = $('#terms :selected').text();
	document.getElementById("description").value = text;
}

function setDescription4()
{
	var text = $('#salesTax :selected').text();
	var e = document.getElementById("salesTax");
	var strUser = e.options[e.selectedIndex].value;
	document.getElementById("description").value = text;
	document.getElementById("txtTerms1").value = strUser;
}

function setDescription5(){
	var text = $('#creditTerm :selected').text();
	document.getElementById("description").value = text;
	
	var e = document.getElementById("creditTerm");
	var strUser = e.options[e.selectedIndex].value;
	document.getElementById("txtDays").value = strUser;
}
function numbersonly(e, val){
	var temp=val.indexOf(".");
	var unicode=e.charCode? e.charCode : e.keyCode;
	if (unicode!=8){
 		if(unicode==46 && temp==-1){
 			return true;
		}
		else if (unicode<48||unicode>57) {
			return false; //disable key press
		}
	}
}
function showsSuccessDialog(){
    event.preventDefault();
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
}
</script>
</html>
<!-- Dialog box used in this page -->
<div id="showsaverecorddialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.saveconfirm"/></p>
</div>
<!-- Dialog box used in this page -->
<div id="showsuccessdialog" style="display:none;">
	<p>Record updated</p>
</div>
<div id="deleteSelectedRecordDialog" style="display:none;">
	<p>Record deleted</p>
</div>