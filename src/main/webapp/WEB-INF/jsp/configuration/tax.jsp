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
<title><spring:message code="BzComposer.taxinfotitle" /></title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
    integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
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
$(function()
{
    $("#tabs1").tabs();
    $("#tabsTax").tabs();
    $("#tabs2").tabs();
    $("#tabs").tabs();
    $("#tabsFederalTax").tabs();
    $("#tabsEmployeeFederalTax").tabs({activate: function(event, ui) {
                                              if(ui.newTab.index() == 0){
                                                  $("#trFUTA").hide();
                                                  $("#trFIT").show();
                                              }else if(ui.newTab.index() == 1){
                                                  $("#trFUTA").show();
                                                  $("#trFIT").hide();
                                              }

                                          }});
    $("#tabsCompanyTaxOption").tabs();
    $("#tabsEmployeeStateTax").tabs();

    $("#resetSIDOthers").hide();
    $("#sitStateActive").hide();
    $("#sitStateInActive").hide();
    $("#setAsDefault").hide();
    $("#sitSave").hide();



    var isUPS = $("#isUPSActive").val();
	var isUSPS = $("#isUSPSActive").val();
	var isFedex = $("#isFeDexActive").val();

	if(isUPS == 1)
	{
		$("#upsUserId").attr('readonly',true);
		$("#upsPassword").attr('readonly',true);
		$("#accessKey").attr('readonly',true);
		$("#upsAccountNo").attr('readonly',true);
		$("#isUPSActive").attr('checked',true);
	}
	else
	{
		$("#upsUserId").attr('readonly',false);
		$("#upsPassword").attr('readonly',false);
		$("#accessKey").attr('readonly',false);
		$("#upsAccountNo").attr('readonly',false);
		$("#isUPSActive").attr('checked',false);
	}

	if(isUSPS == 1)
	{
		$("#isUSPSActive").attr('checked',true);
		$("#uspsUserId").attr('readonly',true);
	}
	else
	{
		$("#isUSPSActive").attr('checked',false);
		$("#uspsUserId").attr('readonly',false);
	}

	if(isFedex == 1)
	{
		$("#fedexAccountNumber").attr('readonly',true);
		$("#fedexMeterNumber").attr('readonly',true);
		$("#fedexPassword").attr('readonly',true);
		$("#fedexTestKey").attr('readonly',true);
	}
	else
	{
		$("#fedexAccountNumber").attr('readonly',false);
		$("#fedexMeterNumber").attr('readonly',false);
		$("#fedexPassword").attr('readonly',false);
		$("#fedexTestKey").attr('readonly',false);
	}
	$("#weekendSaturday").click(function () {
        if ($(this).is(":checked")) {
            $("#weekendSaturdayRate").removeAttr("disabled");
            $("#weekendSaturdayRate").focus();
        } else {
            $("#weekendSaturdayRate").attr("disabled", "disabled");
        }
    });

   $("#weekendSunday").click(function () {
        if ($(this).is(":checked")) {
            $("#weekendSundayRate").removeAttr("disabled");
            $("#weekendSundayRate").focus();
        } else {
            $("#weekendSundayRate").attr("disabled", "disabled");
        }
    });

     $("#holiday").click(function () {
        if ($(this).is(":checked")) {
            $("#holidayRate").removeAttr("disabled");
            $("#holidayRate").focus();
        } else {
            $("#holidayRate").attr("disabled", "disabled");
        }
    });

    $("#daysOfWeek").click(function () {
        if ($(this).is(":checked")) {
            $("#txtDayOfWeek").removeAttr("disabled");
            $("#txtDayOfWeek").focus();
        } else {
            $("#txtDayOfWeek").attr("disabled", "disabled");
        }
    });

    $("#daysOfMonth").click(function () {
        if ($(this).is(":checked")) {
            $("#txtDayOfMonth").removeAttr("disabled");
            $("#txtDayOfMonth").focus();
        } else {
            $("#txtDayOfMonth").attr("disabled", "disabled");
        }
    });

    $("#otherStateChck_1").click(function () {
        if ($(this).is(":checked")) {
            $("#otherStateInput_1").removeAttr("disabled");
            $("#otherStateTaxRate_1").removeAttr("disabled");
            $("#otherStateUpto_1").removeAttr("disabled");
            $("#otherStateInput_1").focus();
        } else {
            $("#otherStateInput_1").attr("disabled", "disabled");
            $("#otherStateTaxRate_1").attr("disabled", "disabled");
            $("#otherStateUpto_1").attr("disabled", "disabled");
        }
    });
    $("#otherStateChck_2").click(function () {
        if ($(this).is(":checked")) {
            $("#otherStateInput_2").removeAttr("disabled");
            $("#otherStateTaxRate_2").removeAttr("disabled");
            $("#otherStateUpto_2").removeAttr("disabled");
            $("#otherStateInput_2").focus();
        } else {
            $("#otherStateInput_2").attr("disabled", "disabled");
            $("#otherStateTaxRate_2").attr("disabled", "disabled");
            $("#otherStateUpto_2").attr("disabled", "disabled");
        }
    });

   $("#otherStateChck_3").click(function () {
        if ($(this).is(":checked")) {
            $("#otherStateInput_3").removeAttr("disabled");
            $("#otherStateTaxRate_3").removeAttr("disabled");
            $("#otherStateUpto_3").removeAttr("disabled");
            $("#otherStateInput_3").focus();
        } else {
            $("#otherStateInput_3").attr("disabled", "disabled");
            $("#otherStateTaxRate_3").attr("disabled", "disabled");
            $("#otherStateUpto_3").attr("disabled", "disabled");
        }
   });

    $("#deductRateChck").click(function () {
            if ($(this).is(":checked")) {
                $("#deductionRate").removeAttr("disabled");
                $("#deductionAmount").attr("disabled", "disabled");
                $("#deductionRate").focus();}
    });

    $("#deductAmountChck").click(function () {
            if ($(this).is(":checked")) {
                $("#deductionAmount").removeAttr("disabled");
                $("#deductionRate").attr("disabled", "disabled");
                $("#deductionAmount").focus();}
    });

    $("#dailyOverHours").click(function () {
            if ($(this).is(":checked")) {
                $("#dailyHours").removeAttr("disabled");
                $("#weeklyHours").attr("disabled", "disabled");
                $("#dailyHours").focus();
            }
    });

    $("#weeklyOverHours").click(function () {
                if ($(this).is(":checked")) {
                    $("#weeklyHours").removeAttr("disabled");
                    $("#dailyHours").attr("disabled", "disabled");
                    $("#weeklyHours").focus();
                }
    });
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

    var nowY = new Date().getFullYear(),
        options = "",
        defaultVal = nowY;
    for(var Y=nowY; Y>=1980; Y--) {
      options += "<option value='"+Y+"'>"+ Y +"</option>";
    }
    $("#selectedTaxYear").append( options );
    $("#selectedTaxYear").val(defaultVal);
    $("#selectedTaxYearEmplyr").append( options );
    $("#selectedTaxYearEmplyr").val(defaultVal);

    $('#selectedTaxYear').on('change', function() {
        $.ajax({
        								type: "GET",
        						   		url:"Configuration/FederalTax/companyTaxInfo/loadTaxYear/"+this.value,
        					 			success : function(res) {
        					 			    console.log('res',res)
        					 			    if(res){
                                                $('#autoFIT').val(''+res.autoFIT);
                                                $('#yearFIT').val(res.yearFIT);
                                                $('#rateFICA').val(res.rateFICA);
                                                $('#rateSocialTax').val(res.rateSocialTax);
                                                $('#socialTaxLimit').val(res.socialTaxLimit);
                                                $('#rateMedicareTax').val(res.rateMedicareTax);
                                                $('#rateFUTA').val(res.rateFUTA);
                                                $('#rateFIT').val(res.rateFIT);
                                            }

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

    });

    $('#selectedTaxYearEmplyr').on('change', function() {

     });


     $('#stateList').on('change', function() {
            $.ajax({
                   								type: "GET",
                   						   		url:"Configuration/StateTax/"+this.value,
                   					 			success : function(res) {
                   					 			    console.log('res',res)

                                                    $("#resetSIDOthers").show();
                                                    $("#sitStateActive").hide();
                                                    $("#sitStateInActive").hide();
                                                    $("#setAsDefault").hide();
                                                    $("#sitSave").show();

                   					 			    if(res){
                                                        if(res.active){
                                                         $("#sitStateInActive").show();
                                                        }else{
                                                         $("#sitStateActive").show();
                                                        }
                                                        if(!res.asDefault){
                                                           $("#setAsDefault").show();
                                                        }
                                                    }








                   					 			     $('#otherStateChck_1').prop("checked",false);
                   					 			     if(res.otherStateChck1){
                   					 			        $('#otherStateChck_1').click();

                   					 			     }

                   					 			     $('#otherStateChck_2').prop("checked",false);
                   					 			     if(res.otherStateChck2){
                   					 			        $('#otherStateChck_2').click();

                   					 			     }

                   					 			     $('#otherStateChck_3').prop("checked",false);
                   					 			     if(res.otherStateChck3){
                   					 			        $('#otherStateChck_3').click();

                   					 			     }



                                                      $('#otherStateInput_1').val(res.otherStateInput1);
                                                      $('#otherStateInput_2').val(res.otherStateInput2);
                                                      $('#otherStateInput_3').val(res.otherStateInput3);

                                                      $('#otherStateTaxRate_1').val(res.otherStateTaxRate1);
                                                      $('#otherStateTaxRate_2').val(res.otherStateTaxRate2);
                                                      $('#otherStateTaxRate_3').val(res.otherStateTaxRate3);

                                                      $('#otherStateUpto_1').val(res.otherStateUpto1);
                                                      $('#otherStateUpto_2').val(res.otherStateUpto2);
                                                      $('#otherStateUpto_3').val(res.otherStateUpto3);


                                                      $('#upToEtt').val(res.upToEtt);
                                                      $('#upToSdi').val(res.upToSdi);
                                                      $('#pitRate').val(res.pitRate);


                                                      $('#ettRate').val(res.ettRate);
                                                      $('#uiRate').val(res.uiRate);
                                                      $('#sdiRate').val(res.sdiRate);

                                                      $('#upToui').val(res.upToui);
                                                      $('#stateTaxId').val(res.stateTaxId);


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
     });

});
//Extra Code start
function setPrice()
{
	$('select[id="shippingTypePrice"]').find('option').attr("selected",false);
 	var weight = $.trim($("#shippingTypeWeight option:selected").val());
 	$('select[id="shippingTypePrice"]').find('option[value="'+weight+'"]').attr("selected",true);
 	var price = $.trim($("#shippingTypePrice option:selected").text());
 	$("#upsWeight").val(weight);
 	$("#upsShippingFee").val("$"+price);
 	$("#modifySeletedWeight").attr('disabled',false);
 	$("#deleteSeletedWeight").attr('disabled',false);
}
function showPanel()
{
    var selectedTab = $("#tabs1").tabs('option','active');
    if(selectedTab == 2)
    {
    	document.getElementById("shippingFreeMethodDiv").style.display = "none";
    	document.getElementById("valueAddedCalculator").style.display = "none";
    }
    else
    {
    	document.getElementById("shippingFreeMethodDiv").style.display = "block";
    	document.getElementById("valueAddedCalculator").style.display = "block";
    }
}

/* comented on 24-11-2019 to prevent simple
function updateSelectedWeight()
{

}

function deleteSelectedWeight()
{

}*/
function setServices()
{
	debugger
	var serviceName = $.trim($("#upsSelect option:selected").text());
	$("#upsServiceName").val(serviceName);
}

function setUSPSService()
{
	var uspsService = $("#uspsSelect option:selected").text();
	$("#uspsServiceName").val(uspsService);
}

function setWeightPrice()
{
	var shippingType = $("#userShippingType option:selected").val();
	if(shippingType == 0)
	{

		return selectshippingtypedialog();
	}
	else
	{

		$("#modifySeletedWeight").attr('disabled',true);
	 	$("#deleteSeletedWeight").attr('disabled',true);
		window.open("Configuration?tabid=config30&shippingCarrierId="+shippingType);
		//window.open("Configuration?tabid=showStore");
	}
}

function addModalShippingType()
{
	var sType = $.trim($("#modalShippingType option:selected").text());
	$("#selectedShippingType").val(sType);
}

function setModalDescription()
{
	debugger
	var sType = $.trim($("#modalShippingType option:selected").text());
	$("#selectedShippingType").val(sType);
}

/* comented on 24-11-2019 to prevent simple
function saveTemplate()
{

}
function deleteTemplate()
{

} */
function saveModalShippingType()
{
	debugger
	var selectedSType = $.trim($("#modalShippingType option:selected").text());
	debugger
	var textboxValue = $("#selectedShippingType").val();
	debugger
	if(textboxValue =="")
	{

		return selectshippingtypetoupdatedialog();
	}
	else if(selectedSType == textboxValue)
	{

		return duplicatevaluedialog();
	}
	else
	{
		return ;
	}		//This else added on 24-11-2019
	/* else
	{

	} */
}

function addNewTemplate()
{
	debugger
	document.getElementById("templateName").style.display = "block";
	document.getElementById("templateSubject").style.display = "block";
	document.getElementById("emailText").style.display = "block";
	document.getElementById("txtTemplateText").style.display = "none";
	document.getElementById("txtTemplateName").style.display = "none";
	document.getElementById("txtTemplateSubject").style.display = "none";
	document.getElementById("emailText").value = "<<name>>"+'\n'+"<<company name>>"+'\n'+"<<address>>"+'\n'+"<<phonenumber>>";
}
function setContent()
{
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

function showTime()
{
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
function removeTime()
{
	$('#scheduleTime option:selected').remove();
}
//Extra Code end
</script>

<!-- <body onload="init2();"> -->
<body onload="init();">


<!-- begin shared/header -->
<form:form action="Configuration?" enctype="MULTIPART/FORM-DATA" method="post" modelAttribute="configDto">
<%--Header-Body line separator--%>
<div id="ddcolortabsline">&nbsp;</div>

<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">

<%--Configuration Title addition start--%>
<div>
	<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.configuration.configurationtitle"/>
	</span>
</div>
<%--Configuration Title addition end--%>

<div><%--Tax Body start--%>

    <div><%--Hidden Labels start--%>
        <%--
                !!!!! Code not converted from spring to struts
        --%>
        <logic:present name="Labels">
            <bean:size name="Labels" id="size" />
                <input type="hidden" name="lsize" id="lblsize" value='<bean:write name="size" />' />
                <logic:iterate name="Labels" id="lbl" indexId="index">
                    <input type="hidden" id='<bean:write name="index" />lid' name='<bean:write name="index" />lidname'
                        value='<bean:write name="lbl" property="value" />' />
                    <input type="hidden" id='<bean:write name="index" />lname' name='<bean:write name="index" />lnm'
                        value='<bean:write name="lbl" property="label" />' />
                </logic:iterate>
        </logic:present>
    </div><%--Hidden Labels end--%>

    <div id="table-negotiations" style="padding: 0; border: 1px solid #ccc;"><%--Configuration tab border start--%>
            <span style="font-size:30px;cursor:pointer; margin-left: 20px;" onclick="toggleFunction()">&#9776;</span>
            <table cellspacing="0"  style="border: 0;width: 100%;overflow-y:scroll;" class="section-border">
                <tr>
                    <%--Configuration menu start--%>
                    <td id="leftMenu" style="position: relative; width: 180px;">
                        <table>
                            <tr>
                                <td>
                                    <jsp:include page="menuPage.jsp" />
                                </td>
                            </tr>
                            <%--
                                <tr align="center">
                                    <td>
                                        <input type="button" name="Revoke" class="formButton" onclick="RevokeValues();" value='<spring:message code="BizComposer.Configuration.RevokeButton"/>' />
                                        <input type="button" name="Save" class="formButton" onclick="SaveValues();" value='<spring:message code="BizComposer.Configuration.SaveButton"/>' />
                                    </td>
                                    <td>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </td>
                                </tr>
                            --%>
                        </table>
                    </td>
                    <%--Configuration menu end--%>
                    <%--Tax page start--%>
                    <td valign="top">
                        <%--Tax tab start--%>
                        <div id="tax" style="display:none;padding: 0; position: relative; left: 0;">
                        <%--Tax Inner tabs start--%>
                        <div id="tabsTax" style="height:auto;">
                            <ul>
                                <li style="font-size:12px;">
                                    <a href="#federalTax">
                                        <spring:message code="BzComposer.configuration.tab.federaltax" />
                                    </a>
                                </li>
                                <li style="font-size:12px;">
                                    <a href="#stateTax">
                                        <spring:message code="BzComposer.configuration.tab.statetax" />
                                    </a>
                                </li>
                            </ul>
                            <%--federalTax tab starts--%>
                            <div id="federalTax">
                            <div id="tabsFederalTax" style="height:auto;">
                                <ul>
                                    <li style="font-size:12px;">
                                        <a href="#companyTaxInformation">
                                            <spring:message code="BzComposer.configuration.tab.companytaxinformation" />
                                        </a>
                                    </li>
                                    <li style="font-size:12px;">
                                        <a href="#companyTaxOption">
                                            <spring:message code="BzComposer.configuration.tab.companytaxoption" />
                                        </a>
                                    </li>
                                </ul>
                                <%--companyTaxInformation tab starts--%>
                                <div id="companyTaxInformation">
                                <table class="table-notifications" width="100%">
                                    <tr>
                                        <th colspan="4" align="left" style="font-size:12px; padding: 5px;">
                                            <b><spring:message code="BzComposer.configuration.identification" /></b>
                                        </th>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="font-size:12px;">
                                            <spring:message code="BzComposer.configuration.federaltaxid"/> :
                                            <form:input path="federalTaxID" />

                                        </td>
                                        <td colspan="2" style="font-size:12px;">
                                            <spring:message code="BzComposer.configuration.fiscalmonth"/> :
                                            <form:select path="fiscalMonth">
                                                <option value="January" ${configDto.fiscalMonth == "January" ? 'selected' : ''}><spring:message code='BzComposer.configuration.month.january'/></option>
                                                <option value="February" ${configDto.fiscalMonth == "February" ? 'selected' : ''}><spring:message code='BzComposer.configuration.month.february'/></option>
                                                <option value="March" ${configDto.fiscalMonth == "March" ? 'selected' : ''}><spring:message code='BzComposer.configuration.month.march'/></option>
                                                <option value="April" ${configDto.fiscalMonth == "April" ? 'selected' : ''}><spring:message code='BzComposer.configuration.month.april'/></option>
                                                <option value="May" ${configDto.fiscalMonth == "May" ? 'selected' : ''}><spring:message code='BzComposer.configuration.month.may'/></option>
                                                <option value="June" ${configDto.fiscalMonth == "June" ? 'selected' : ''}><spring:message code='BzComposer.configuration.month.june'/></option>
                                                <option value="July" ${configDto.fiscalMonth == "July" ? 'selected' : ''}><spring:message code='BzComposer.configuration.month.july'/></option>
                                                <option value="August" ${configDto.fiscalMonth == "August" ? 'selected' : ''}><spring:message code='BzComposer.configuration.month.august'/></option>
                                                <option value="September" ${configDto.fiscalMonth == "September" ? 'selected' : ''}><spring:message code='BzComposer.configuration.month.september'/></option>
                                                <option value="October" ${configDto.fiscalMonth == "October" ? 'selected' : ''}><spring:message code='BzComposer.configuration.month.october'/></option>
                                                <option value="November" ${configDto.fiscalMonth == "November" ? 'selected' : ''}><spring:message code='BzComposer.configuration.month.november'/></option>
                                                <option value="December" ${configDto.fiscalMonth == "December" ? 'selected' : ''}><spring:message code='BzComposer.configuration.month.december'/></option>
                                            </form:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th colspan="4" align="left" style="font-size:12px; padding: 5px;">
                                            <b><spring:message code="BzComposer.configuration.federaltax" /></b>
                                        </th>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                        <div id="tabsEmployeeFederalTax">
                                        <ul>
                                            <li style="font-size:12px;">
                                                <a href="#employeeTaxInfo2" style="font-size:12px;">
                                                    <spring:message code="BzComposer.Employee" />
                                                </a>
                                            </li>
                                            <li style="font-size:12px;">
                                                <a href="#employerTaxInfo2" style="font-size:12px;">
                                                    <spring:message code="BzComposer.configuration.employer" />
                                                </a>
                                            </li>
                                        </ul>
                                        <div id="employeeTaxInfo2"></div><div id="employerTaxInfo2"></div>

                                        <%--Employee FederalTax tab starts--%>
                                        <div id="employeeTaxInfo">
                                            <table class="table-notifications" width="100%">
                                            <tr>
                                                <th colspan="4" align="left" style="font-size:12px; padding: 5px;">
                                                    <b><spring:message code="BzComposer.configuration.incometaxwithhold"/></b>
                                                </th>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="font-size:12px;">
                                                    <spring:message code="BzComposer.configuration.federalincometax"/> :
                                                    <form:select path="autoFIT">
                                                        <option value="1"  ${configDto.autoFIT == 1 ? 'selected' : ''}>
                                                            <spring:message code="BzComposer.configuration.usefederalincometaxtable"/>
                                                        </option>
                                                        <option value="0" ${configDto.autoFIT == 0 ? 'selected' : ''}>
                                                            <spring:message code="BzComposer.configuration.usemanualestimatedtaxrate"/>
                                                        </option>
                                                    </form:select>
                                                </td>
                                                <td colspan="2" style="font-size:12px;">
                                                    <spring:message code="BzComposer.configuration.taxyear"/> :
                                                    <form:select path="selectedTaxYear">
                                                        <%--<c:if test="${not empty configDto.listOfExistingTaxYear}">
                                                            <c:forEach items="${configDto.listOfExistingTaxYear}" var="objList1">
                                                                <option value="${objList1.availableTaxYear}">${objList1.selectedTaxYear}</option>
                                                            </c:forEach>
                                                        </c:if>--%>
                                                        <option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
                                                    </form:select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="4" align="left" style="font-size:12px;">
                                                    <b><spring:message code="BzComposer.configuration.ficaDeductionFull" />
                                                        <%--<spring:message code="BzComposer.configuration.ficadeduction"/>--%></b>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="4" align="left" style="font-size:12px;">
                                                    <%--<input type="checkbox" id="fica">--%>
                                                    <%--<spring:message code="BzComposer.configuration.fica"/> :--%>
                                                    <%--<spring:message code="BzComposer.configuration.rate"/>--%>
                                                    <div class="input-group">
                                                        <spring:message code="BzComposer.configuration.ficaDeductionRate" /> : &nbsp;&nbsp;
                                                      <form:input type="text" path="rateFICA" placeholder="0.00" style="height:25px;"/>
                                                      <div class="input-group-append">
                                                       <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                                    </div></div>

                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="15%" style="font-size:12px;">
                                                    <%--<input type="checkbox" id="socicalSecurityTax">--%>
                                                    <spring:message code="BzComposer.federaltax.socialsecuritytax" /> :
                                                </td>
                                                <td width="25%"style="font-size:12px;">
                                                    <div class="input-group">
                                                      <spring:message code="BzComposer.configuration.rate"/>
                                                      &nbsp;&nbsp;
                                                      <form:input path="rateSocialTax" type="text" placeholder="0.00" style="height:25px;"/>
                                                      <div class="input-group-append">
                                                      <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                                    </div></div>
                                                </td>
                                                <td colspan="2" style="font-size:12px;">
                                                    <div class="input-group">
                                                      <spring:message code="BzComposer.configuration.upto"/>
                                                      &nbsp;&nbsp;
                                                      <form:input type="text" path="socialTaxLimit" placeholder="0.00" style="height:25px;"/>
                                                      <div class="input-group-append">
                                                      <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">$</span>
                                                    </div></div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="15%"style="font-size:12px;">
                                                    <%--<input type="checkbox" id="medicare">--%>
                                                    <spring:message code="BzComposer.configuration.medicare"/> :
                                                </td>
                                                <td width="20%"style="font-size:12px;">
                                                    <div class="input-group">
                                                      <spring:message code="BzComposer.configuration.rate"/>
                                                      &nbsp;&nbsp;
                                                      <form:input path="rateMedicareTax" type="text" placeholder="0.00" style="height:25px;" />
                                                      <div class="input-group-append">
                                                      <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                                    </div></div>
                                                </td>
                                                <td style="font-size:12px;" colspan="2"><spring:message code="BzComposer.configuration.noLimit" /></td>
                                            </tr>
                                            <tr id="trFIT">
                                                <td colspan="4" align="left" style="font-size:12px;">
                                                    <%--<input type="checkbox" id="fica">--%>
                                                    <%--<spring:message code="BzComposer.configuration.fit"/> :--%>
                                                    <div class="input-group">
                                                      <spring:message code="BzComposer.configuration.federalIncomeTax" /> :
                                                      &nbsp;&nbsp;
                                                      <form:input path="rateFIT" type="text" placeholder="0.00" style="height:25px;"/>
                                                      <div class="input-group-append">
                                                      <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                                    </div></div>
                                                </td>
                                            </tr>
                                            <tr id="trFUTA" style="display:none">
                                                                                            <td colspan="4" align="left" style="font-size:12px;">
                                                                                                <%--<input type="checkbox" id="futa">--%>
                                                                                                <%--<spring:message code="BzComposer.configuration.futa"/> :--%>
                                                                                                <div class="input-group">
                                                                                                  <spring:message code="BzComposer.configuration.federalUnemployment" /> :
                                                                                                  &nbsp;&nbsp;
                                                                                                  <form:input path="rateFUTA" type="text" placeholder="0.00" style="height:25px;" />
                                                                                                  <div class="input-group-append">
                                                                                                  <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                                                                                </div></div>
                                                                                            </td>
                                                                                        </tr>
                                            </table>
                                        </div>
                                        <%--Employee FederalTax tab ends--%>
                                        <%--Employer FederalTax tab starts--%>
                                         <div id="employerTaxInfo" style="display:none">
                                                                                    <table class="table-notifications" width="100%">
                                                                                    <tr>
                                                                                        <th colspan="4" align="left" style="font-size:12px; padding: 5px;">
                                                                                            <b><spring:message code="BzComposer.configuration.incometaxwithhold"/></b>
                                                                                        </th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="2" style="font-size:12px;">
                                                                                            <spring:message code="BzComposer.configuration.federalincometax"/> :
                                                                                             <select id="federalIncomeTaxEmplyr">
                                                                                                                                                    <option value="1"  ${configDto.autoFIT == "1" ? 'selected' : ''}>
                                                                                                                                                        <spring:message code="BzComposer.configuration.usefederalincometaxtable"/>
                                                                                                                                                    </option>
                                                                                                                                                    <option value="0" ${configDto.autoFIT == "0" ? 'selected' : ''}>
                                                                                                                                                        <spring:message code="BzComposer.configuration.usemanualestimatedtaxrate"/>
                                                                                                                                                    </option>
                                                                                                                                                </select>
                                                                                        </td>
                                                                                        <td colspan="2" style="font-size:12px;">
                                                                                            <spring:message code="BzComposer.configuration.taxyear"/> :
                                                                                            <form:select id="selectedTaxYearEmplyr" path="selectedTaxYear">

                                                                                               <%-- <c:if test="${not empty configDto.listOfExistingTaxYear}">
                                                                                                    <c:forEach items="${configDto.listOfExistingTaxYear}" var="objList1">
                                                                                                        <option value="${objList1.availableTaxYear}">${objList1.selectedTaxYear}</option>
                                                                                                    </c:forEach>
                                                                                                </c:if>--%>
                                                                                            </form:select>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="4" align="left" style="font-size:12px;">
                                                                                            <b>FICA (Federal Insurance Contribution Act) Deductions<%--<spring:message code="BzComposer.configuration.ficadeduction"/>--%></b>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="4" align="left" style="font-size:12px;">
                                                                                            <%--<input type="checkbox" id="fica">--%>
                                                                                            <%--<spring:message code="BzComposer.configuration.fica"/> :--%>
                                                                                            <%--<spring:message code="BzComposer.configuration.rate"/>--%>
                                                                                            <div class="input-group">
                                                                                              <spring:message code="BzComposer.configuration.ficaDeductionRate" /> : &nbsp;&nbsp;
                                                                                              <input value="${configDto.rateFICA}"  type="text" placeholder="0.00" style="height:25px;"  id="ficaRateEmplyr"/>
                                                                                              <div class="input-group-append">
                                                                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                                                                            </div></div>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td width="15%" style="font-size:12px;">
                                                                                            <%--<input type="checkbox" id="socicalSecurityTax">--%>
                                                                                            <spring:message code="BzComposer.federaltax.socialsecuritytax" /> :
                                                                                        </td>
                                                                                        <td width="20%" style="font-size:12px;">
                                                                                            <div class="input-group">
                                                                                              <spring:message code="BzComposer.configuration.rate"/>
                                                                                              &nbsp;&nbsp;
                                                                                              <input value="${configDto.rateSocialTax}" type="text" placeholder="0.00" style="height:25px;" id="sstRateEmplyr"/>
                                                                                              <div class="input-group-append">
                                                                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                                                                            </div></div>
                                                                                        </td>
                                                                                        <td colspan="2" style="font-size:12px;">
                                                                                            <div class="input-group">
                                                                                              <spring:message code="BzComposer.configuration.upto"/>
                                                                                              &nbsp;&nbsp;
                                                                                              <input value="${configDto.socialTaxLimit}"   type="text" placeholder="0.00" style="height:25px;" id="upToEmplyr"/>
                                                                                              <div class="input-group-append">
                                                                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">$</span>
                                                                                            </div></div>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td width="15%" style="font-size:12px;">
                                                                                            <%--<input type="checkbox" id="medicare">--%>
                                                                                            <spring:message code="BzComposer.configuration.medicare"/> :
                                                                                        </td>
                                                                                        <td width="20%" style="font-size:12px;">
                                                                                            <div class="input-group">
                                                                                              <spring:message code="BzComposer.configuration.rate"/>
                                                                                              &nbsp;&nbsp;
                                                                                              <input value="${configDto.rateMedicareTax}" type="text" placeholder="0.00" style="height:25px;" id="medicareRateEmplyr" />
                                                                                              <div class="input-group-append">
                                                                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                                                                            </div></div>
                                                                                        </td>
                                                                                        <td style="font-size:12px;" colspan="2"><spring:message code="BzComposer.configuration.noLimit" /></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="4" align="left" style="font-size:12px;">
                                                                                            <%--<input type="checkbox" id="futa">--%>
                                                                                            <%--<spring:message code="BzComposer.configuration.futa"/> :--%>
                                                                                            <div class="input-group">
                                                                                              Federal Unemployment (FUTA) :
                                                                                              &nbsp;&nbsp;
                                                                                              <form:input path="rateFUTA" type="text" placeholder="0.00" style="height:25px;" />
                                                                                              <div class="input-group-append">
                                                                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                                                                            </div></div>
                                                                                        </td>
                                                                                    </tr>
                                                                                    </table>
                                                                                </div>
                                                                                <%--Employer FederalTax tab ends--%>
                                                                                </div>
                                        <%--Employer FederalTax tab ends--%>
                                        </div>
                                        </td>
                                    </tr>
                                </table>
                                <div align="right">
                                    <button type="button" class="formButton" style="font-size:14px;" onclick="saveFederalTax()">
                                        <spring:message code="BzComposer.configuration.savefederaltaxbtn"/>
                                    </button>
                                </div>
                                </div>
                                <%--companyTaxInformation tab end--%>
                                <%--companyTaxOption tab starts--%>
                                <div id="companyTaxOption">
                               <%-- <div id="tabsCompanyTaxOption" style="height:700px;">--%>
                               <div id="tabsCompanyTaxOption">
                                    <ul>
                                        <li style="font-size:12px;">
                                            <a href="#deduction">
                                                <spring:message code="BzComposer.configuration.tab.deduction" />
                                            </a>
                                        </li>
                                        <li style="font-size:12px;">
                                            <a href="#option">
                                                <spring:message code="BzComposer.configuration.tab.option" />
                                            </a>
                                        </li>
                                    </ul>
                                    <%--deduction tab starts--%>
                                    <div id="deduction">
                                    <table class="table-notifications" width="100%">
                                        <tr>
                                            <th colspan="4" align="left" style="font-size:12px; padding: 5px;">
                                                <b><spring:message code="BzComposer.configuration.deductioninformation" /></b>
                                            </th>
                                        </tr>
                                        <tr>
                                            <td width="10%" style="font-size:12px;">
                                                <spring:message code="BzComposer.configuration.name"/> :
                                            </td>
                                            <td width="25%" style="font-size:12px;">
                                                <input type="text" style="width: 200px" id="deductionName">
                                                <input type="hidden"  id="deductionListId">
                                            </td>
                                            <td width="25%" style="font-size:12px;">
                                                <input type="checkbox" id="taxExemption"/>
                                                <spring:message code="BzComposer.configuration.taxexemption"/>
                                            </td>
                                            <td style="font-size:12px;">

                                            </td>
                                        </tr>
                                         <tr>
                                                                                    <td width="10%" style="font-size:12px;">
                                                                                        <input type="radio" name="deductType" id="deductAmountChck" checked/>
                                                                                        <spring:message code="BzComposer.configuration.amount"/> :
                                                                                    </td>
                                                                                    <td width="25%" style="font-size:12px;">
                                                                                        <div class="input-group">
                                                                                          <input type="text" placeholder="0.00" style="height:25px;" id="deductionAmount">
                                                                                          <div class="input-group-append">
                                                                                          <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">$</span>
                                                                                        </div></div>
                                                                                    </td>
                                                                                    <td width="25%" style="font-size:12px;">
                                                                                        &nbsp; &nbsp;
                                                                                    </td>
                                                                                    <td style="font-size:14px;">

                                                                                    </td>
                                                                                </tr>
                                             <tr>
                                            <td width="10%" style="font-size:12px;">
                                                <input type="radio"  name="deductType" id="deductRateChck"/>
                                                <spring:message code="BzComposer.configuration.rate"/> :
                                            </td>
                                            <td width="25%" style="font-size:12px;">
                                                <div class="input-group">
                                                  <input type="text" placeholder="0.00" disabled="disabled" style="height:25px;" id="deductionRate">
                                                  <div class="input-group-append">
                                                  <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                                </div></div>
                                            </td>
                                            <td width="25%" style="font-size:12px;">
                                            &nbsp; &nbsp;
                                            </td>
                                            <td style="font-size:14px;">

                                            </td>
                                        </tr>

                                        <tr>
                                            <th colspan="4" align="left" style="font-size:12px; padding: 5px;">
                                                <b><spring:message code="BzComposer.configuration.deductionlist"/></b>
                                            </th>
                                        </tr>
                                        <tr>
                                            <td colspan="4" align="left" style="font-size:12px; padding: 5px;">
                                            <%--Deduction List starts--%>
                                            <div>
                                            <table id="tbl-comtaxoptions-deduction-list" class="tabla-listados" cellspacing="0" border="1">
                                            <thead>
                                             <tr>
                                                <td width="20%" style="font-size:12px;">&nbsp;&nbsp;
                                                    <b><spring:message code="BzComposer.Employee.Deduction" /></b>
                                                </td>
                                                <td width="20%" style="font-size:12px;">&nbsp;&nbsp;
                                                    <b><spring:message code="BzComposer.Report.Amount" /></b>
                                                </td>
                                                <td width="20%" style="font-size:12px;">&nbsp;&nbsp;
                                                    <b><spring:message code="BizComposer.Configuration.Tax.rate" /></b>
                                                </td>
                                                <td width="20%" style="font-size:12px;">&nbsp;&nbsp;
                                                    <b><spring:message code="BzComposer.configuration.useRate" /></b>
                                                </td>
                                                <td width="20%" style="font-size:12px;">&nbsp;&nbsp;
                                                    <b><spring:message code="BzComposer.deduction.taxexempt" /></b>
                                                </td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${configDto.deductionList}" var="deduct" varStatus="loop">
                                                                                <tr class="row-comp-tax-deduction" id='${loop.index}-row-comp-tax-deduction' onclick="setCompanyTaxDeduction(this, '${deduct.deductionListId}', '${deduct.deductionListName}', '${deduct.deductionAmount}','${deduct.deductionRate}','${deduct.useRate}','${deduct.isTaxExempt}')">
                                                                                                                               <td width="20%" style="font-size:12px;">
                                                                                                                                   ${deduct.deductionListName}
                                                                                                                               </td>
                                                                                                                               <td width="20%" style="font-size:12px;">
                                                                                                                                   ${deduct.deductionAmount}
                                                                                                                               </td>
                                                                                                                               <td width="20%" style="font-size:12px;">
                                                                                                                                   ${deduct.deductionRate}
                                                                                                                               </td>
                                                                                                                               <td width="20%" style="font-size:12px;">
                                                                                                                                   ${deduct.useRate}

                                                                                                                               </td>
                                                                                                                               <td width="20%" style="font-size:12px;">
                                                                                                                                   ${deduct.isTaxExempt == 1 ? 'Yes' : 'No' }

                                                                                                                               </td>
                                                                                                                           </tr>

                                             </c:forEach>
                                            </tbody>
                                            </table>
                                            </div>
                                            <%--Deduction List ends--%>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" align="center" style="font-size:14px; padding: 5px;">
                                                 <button type="button" class="formButton" onclick="saveCompanyTaxDeduction(false);">
                                                         <spring:message code="BzComposer.configuration.adddeductionbtn"/>
                                                 </button>
                                                 <button type="button" class="formButton" onclick="saveCompanyTaxDeduction(true)">
                                                    <spring:message code="BzComposer.global.edit"/>
                                                 </button>
                                                <button type="button" id="delete" class="formButton" onclick="deleteCompanyTaxDeduction()">
                                                        <spring:message code="BzComposer.global.delete"/>
                                                 </button>
                                                 <button type="reset" class="formButton" onclick="clearCompanyTaxDeduction()">
                                                    <spring:message code="BzComposer.global.clear"/>
                                                </button>
                                            </td>
                                        </tr>
                                    </table>
                                    </div>
                                    <%--deduction tab ends--%>
                                    <%--option tab starts--%>
                                    <div id="option">
                                    <table class="table-notifications" width="100%">
                                    <tr>
                                        <th colspan="4" align="left" style="font-size:12px; padding: 5px;">
                                            <b><spring:message code="BzComposer.configuration.setpayrollday"/></b>
                                        </th>
                                    </tr>
                                    <tr>
                                        <td colspan="4" style="font-size:12px;">
                                            <spring:message code="BzComposer.configuration.startingdate"/> :
                                            &nbsp;
                                            <input type="date" id="selectStartingDate"/>
                                            <input type="hidden" id="optionId"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" style="font-size:12px;">

                                            <div class="input-group">
                                              <input type="checkbox" id="daysOfWeek">
                                              &nbsp;&nbsp;
                                              <input type="text" disabled="disabled"  style="height:25px;" id="txtDayOfWeek">
                                              <div class="input-group-append">
                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">
                                                <spring:message code="BzComposer.configuration.dayofweek"/></span>
                                            </div></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" style="font-size:12px;">
                                            <div class="input-group">
                                              <input type="checkbox" id="daysOfMonth">
                                              &nbsp;&nbsp;
                                              <input type="text" disabled="disabled" style="height:25px;" id="txtDayOfMonth">
                                              <div class="input-group-append">
                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">
                                                <spring:message code="BzComposer.configuration.dayofmonth"/></span>
                                            </div></div>
                                        </td>
                                    </tr>                                    <tr>
                                        <th colspan="4" align="left" style="font-size:12px; padding: 5px;">
                                            <b><spring:message code="BzComposer.configuration.payperiod" /></b>
                                        </th>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12px;">
                                            <input type="checkbox" id="dailyOrMiscellaneous" />
                                            <spring:message code="BzComposer.configuration.dailyormiscellaneous"/>
                                        </td>
                                        <td style="font-size:12px;">
                                            <input type="checkbox" id="weekly" />
                                            <spring:message code="BzComposer.configuration.weekly"/>
                                        </td>
                                        <td colspan="2" style="font-size:12px;">
                                            <input type="checkbox" id="biWeekly">
                                            <spring:message code="BzComposer.configuration.biweekly"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12px;">
                                            <input type="checkbox" id="semiMonthly">
                                            <spring:message code="BzComposer.configuration.semimonthly"/>
                                        </td>
                                        <td style="font-size:12px;">
                                            <input type="checkbox" id="monthly">
                                            <spring:message code="BzComposer.configuration.monthly"/>
                                        </td>
                                        <td colspan="2" style="font-size:12px;">
                                            <input type="checkbox" id="quartely">
                                            <spring:message code="BzComposer.configuration.quarterly"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"style="font-size:12px;">
                                            <input type="checkbox" id="semiAnnually">
                                            <spring:message code="BzComposer.configuration.semiannualy"/>
                                        </td>
                                        <td colspan="2" style="font-size:12px;">
                                            <input type="checkbox" id="annually">
                                            <spring:message code="BzComposer.configuration.annually"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th colspan="2" align="left" style="font-size:12px; padding: 5px;">
                                            <spring:message code="BzComposer.configuration.setovertime"/>
                                        </th>
                                        <th colspan="2" align="left" style="font-size:12px; padding:5px;">
                                            <spring:message code="BzComposer.configuration.setholidaytime"/>
                                        </th>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12px;">
                                            <input type="radio" name="payPeriod" id="dailyOverHours">
                                            <spring:message code="BzComposer.configuration.dailyover"/>
                                        </td>
                                        <td style="font-size:12px;">
                                            <div class="input-group">
                                              <input type="text" style="height:25px;" disabled="disabled" id="dailyHours">
                                              <div class="input-group-append">
                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">
                                                <spring:message code="Bzcomposer.configuration.hours"/></span>
                                            </div></div>
                                        </td>
                                        <td style="font-size:12px;">
                                            <input type="checkbox" id="weekendSaturday">
                                            <spring:message code="BzComposer.configuration.weekendsaturday"/>
                                        </td>
                                        <td style="font-size:12px;">
                                            <div class="input-group">
                                              <spring:message code="BzComposer.configuration.rate"/>
                                              &nbsp;&nbsp;
                                              <input type="text" placeholder="0.00" style="height:25px;" disabled="disabled" id="weekendSaturdayRate">
                                              <div class="input-group-append">
                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                            </div></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12px;">
                                                <input type="radio" name="payPeriod" id="weeklyOverHours">
                                            <spring:message code="BzComposer.configuration.weeklyover"/>
                                        </td>
                                        <td style="font-size:12px;">
                                            <div class="input-group">
                                              <input type="text" style="height:25px;" disabled="disabled" id="weeklyHours">
                                              <div class="input-group-append">
                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">
                                                <spring:message code="Bzcomposer.configuration.hours"/></span>
                                            </div></div>
                                        </td>
                                        <td style="font-size:12px;">
                                            <input type="checkbox" id="weekendSunday">
                                            <spring:message code="BzComposer.configuration.weekendsunday"/>
                                        </td>
                                        <td style="font-size:12px;">
                                            <div class="input-group">
                                              <spring:message code="BzComposer.configuration.rate"/>
                                              &nbsp;&nbsp;
                                              <input type="text" placeholder="0.00" disabled="disabled" style="height:25px;" id="weekendSundayRate">
                                              <div class="input-group-append">
                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                            </div></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12px;">
                                            <spring:message code="BzComposer.configuration.rate"/>
                                        </td>
                                        <td style="font-size:12px;">
                                            <div class="input-group">
                                              <input type="text" placeholder="0.00" style="height:25px;" id="overtimeRate">
                                              <div class="input-group-append">
                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                            </div></div>
                                        </td>
                                        <td style="font-size:12px;">
                                            <input type="checkbox" id="holiday">
                                            <spring:message code="BzComposer.configuration.holiday"/> :
                                        </td>
                                        <td style="font-size:12px;">
                                            <div class="input-group">
                                              <spring:message code="BzComposer.configuration.rate"/>
                                              &nbsp;&nbsp;
                                              <input type="text" placeholder="0.00" disabled="disabled" style="height:25px;" id="holidayRate">
                                              <div class="input-group-append">
                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                            </div></div>
                                        </td>
                                    </tr>

                                    <tr>
                                     <td colspan="4" align="right" style="font-size:14px; padding: 5px;">
                                     <table id="tbl-comtaxoptions-option-list" class="tabla-listados" cellspacing="0" border="1">
                                         <thead>
                                          <tr>
                                             <td width="50%" style="font-size:12px;">
                                                &nbsp;&nbsp;<b><spring:message code="BzComposer.Employee.Deduction" /></b>
                                             </td>
                                             <td width="50%" style="font-size:12px;">
                                                &nbsp;&nbsp;<b><spring:message code="BzComposer.Report.Amount" /></b>
                                             </td>
                                          </tr>
                                         </thead>
                                         <tbody>
                                             <c:forEach items="${configDto.companyTaxOptionDtos}" var="deduct" varStatus="loop">
                                                 <tr class="row-comp-tax-option" id='${loop.index}-row-comp-tax-option' onclick="setCompanyTaxOption(this, '${deduct.startingDate}' , '${deduct.daily}' , '${deduct.weekly}' , '${deduct.monthly}' , '${deduct.annually}' , '${deduct.biweekly}' , '${deduct.quarterly}' , '${deduct.semiAnnually}' , '${deduct.semiMonthly}' , '${deduct.dailyOver}' , '${deduct.weeklyOver}' , '${deduct.dailyOverVal}' , '${deduct.weeklyOverVal}' , '${deduct.wendSt}' , '${deduct.wendStRate}' , '${deduct.wendSn}' , '${deduct.wendSnRate}' , '${deduct.holiday}' , '${deduct.overtimeRate}' ,'${deduct.holidayRate}' , '${deduct.dayOfWeek}' , '${deduct.dayOfWeekVal}' , '${deduct.dayOfMonth}' , '${deduct.dayOfMonthVal}' , '${deduct.optionId}')">
                                                    <td width="50%" style="font-size:12px;">
                                                        ${deduct.startingDate}
                                                    </td>
                                                    <td width="50%" style="font-size:12px;">
                                                        ${deduct.createdAt}
                                                    </td>
                                                </tr>
                                          </c:forEach>
                                         </tbody>
                                     </table>
                                     </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" align="center" style="font-size:14px; padding: 5px;">
                                            <button type="button" class="formButton" onclick="saveCompanyTaxOption(false)">
                                                <spring:message code="BzComposer.configuration.saveoptionbtn"/>
                                            </button>

                                                                                             <button type="button" class="formButton" onclick="saveCompanyTaxOption(true)">
                                                                                                <spring:message code="BzComposer.global.edit"/>
                                                                                             </button>
                                                                                            <button type="button" id="delete" class="formButton" onclick="deleteCompanyTaxOption()">
                                                                                                    <spring:message code="BzComposer.global.delete"/>
                                                                                             </button>
                                                                                             <button type="reset" id="resetClearOption" class="formButton" onclick="clearCompanyTaxOption()">
                                                                                                <spring:message code="BzComposer.global.clear"/>
                                                                                            </button>
                                        </td>
                                    </tr>
                                    </table>
                                    </div>
                                    <%--option tab ends--%>
                                </div>
                                </div>
                                <%--companyTaxOption tab ends--%>
                            </div>
                            </div>
                            <%--federalTax tab end--%>
                            <%--stateTax tab start--%>
                            <div id="stateTax">
                            <table class="table-notifications" width="100%">
                            <tr>
                                <td style="font-size:12px;">
                                    <spring:message code="BzComposer.configuration.stateincometax"/> :
                                </td>
                                <td style="font-size:12px;">
                                    <select id="stateIncomeTax">
                                        <option value="User Manual Income Tax Rate">
                                            <spring:message code="BzComposer.configuration.usermanualincometaxrate"/>
                                        </option>
                                        <option value="User Manual Income Tax Table">
                                            <spring:message code="BzComposer.configuration.usermanualincometaxtable"/>
                                        </option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td rowspan="9" style="font-size:12px;">
                                    <b><spring:message code="BzComposer.configuration.fillingstatelist"/></b>
                                    <br>
                                    <select id="stateList" multiple="multiple" style="height:280px; width:150px;">
                                        <option value="6">California (CA)</option>
                                        <option value="27">Michigan  (MI)</option>
                                    </select>
                                </td>
                                <td style="font-size:12px;">
                                    <b><spring:message code="BzComposer.configuration.taxinformationofstate"/></b>
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size:12px;">
                                    <spring:message code="BzComposer.configuration.statetaxid"/> :
                                </td>
                                </td>
                                <td style="font-size:12px;">
                                    <input type="text" style="width:200px;" id="stateTaxId">
                                </td>
                            </tr>
                           <!-- <tr>
                                <td style="font-size:12px;">
                                    <spring:message code="BzComposer.configuration.stateincometaxrate"/> :
                                </td>
                                <td style="font-size:12px;">
                                    <div class="input-group">
                                      <input type="text" placeholder="0.00" style="height:25px;" id="stateTaxRate">
                                      <div class="input-group-append">
                                      <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                    </div></div>
                                </td>
                            </tr> -->
                            <tr>
                                <th colspan="3" align="left" style="font-size:12px; padding: 5px;">
                                    <b>State Tax<%--<spring:message code="BzComposer.configuration.otherstatetax"/>--%></b>
                                </th>
                            </tr>
                            <tr>
                                <td colspan="3" style="font-size:12px; padding: 5px;">
                                <%--tabsEmployeeStateTax start--%>
                                <div id="tabsEmployeeStateTax">
                                    <ul>
                                        <li style="font-size:12px;">
                                            <a href="#employeeStateTaxInfo" style="font-size:12px;">
                                                <spring:message code="BzComposer.Employee" />
                                            </a>
                                        </li>
                                        <li style="font-size:12px;">
                                            <a href="#employerStateTaxInfo" style="font-size:12px;">
                                                <spring:message code="BzComposer.configuration.employer" />
                                            </a>
                                        </li>
                                    </ul>
                                    <%--employeeStateTaxInfo start--%>
                                    <div id="employeeStateTaxInfo">
                                    <table width="100%">
                                    <tr>
                                        <td width="20%" style="font-size:12px;">
                                            <spring:message code="BzComposer.configuration.personalIncomeTax" /> :
                                        </td>
                                        <td width="25%"style="font-size:12px;">
                                            <div class="input-group">
                                              <spring:message code="BzComposer.configuration.rate"/>
                                              &nbsp;&nbsp;
                                              <input type="text" placeholder="0.00" style="height:25px;" id="pitRate">
                                              <div class="input-group-append">
                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                            </div></div>
                                        </td>
                                        <td style="font-size:12px;"><spring:message code="BzComposer.configuration.noLimit" /></td>
                                    </tr>
                                    <tr>
                                        <td width="20%" style="font-size:12px;">
                                            <spring:message code="BzComposer.configuration.stateDisabilityInsurance" /> :
                                        </td>
                                        <td width="25%"style="font-size:12px;">
                                            <div class="input-group">
                                              <spring:message code="BzComposer.configuration.rate"/>
                                              &nbsp;&nbsp;
                                              <input type="text" placeholder="0.00" style="height:25px;" id="sdiRate">
                                              <div class="input-group-append">
                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                            </div></div>
                                        </td>
                                        <td style="font-size:12px;">
                                            <div class="input-group">
                                              <spring:message code="BzComposer.configuration.upto"/>
                                              &nbsp;&nbsp;
                                              <input type="text" placeholder="0.00" style="height:25px;" id="upToSdi">
                                              <div class="input-group-append">
                                              <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">$</span>
                                            </div></div>
                                        </td>
                                    </tr>
                                    </table>
                                    </div>
                                    <%--employeeStateTaxInfo end--%>
                                    <%--employerStateTaxInfo start--%>
                                    <div id="employerStateTaxInfo">
                                    <table width="100%">
                                        <tr>
                                            <td width="20%" style="font-size:12px;">
                                                <spring:message code="BzComposer.configuration.unemploymentInsurance" /> :
                                            </td>
                                            <td width="25%"style="font-size:12px;">
                                                <div class="input-group">
                                                  <spring:message code="BzComposer.configuration.rate"/>
                                                  &nbsp;&nbsp;
                                                  <input type="text" placeholder="0.00" style="height:25px;" id="uiRate">
                                                  <div class="input-group-append">
                                                  <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                                </div></div>
                                            </td>
                                            <td style="font-size:12px;">
                                                <div class="input-group">
                                                  <spring:message code="BzComposer.configuration.upto"/>
                                                  &nbsp;&nbsp;
                                                  <input type="text" placeholder="0.00" style="height:25px;" id="upToui">
                                                  <div class="input-group-append">
                                                  <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">$</span>
                                                </div></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="20%" style="font-size:12px;">
                                                <spring:message code="BzComposer.configuration.employmentTraningTax"/> :
                                            </td>
                                            <td width="20%"style="font-size:12px;">
                                                <div class="input-group">
                                                  <spring:message code="BzComposer.configuration.rate"/>
                                                  &nbsp;&nbsp;
                                                  <input type="text" placeholder="0.00" style="height:25px;" id="ettRate">
                                                  <div class="input-group-append">
                                                  <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                                </div></div>
                                            </td>
                                            <td style="font-size:12px;">
                                                <div class="input-group">
                                                  <spring:message code="BzComposer.configuration.upto"/>
                                                  &nbsp;&nbsp;
                                                  <input type="text" placeholder="0.00" style="height:25px;" id="upToEtt">
                                                  <div class="input-group-append">
                                                  <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">$</span>
                                                </div></div>
                                            </td>
                                        </tr>
                                    </table>
                                    </div>
                                    <%--employerStateTaxInfo end--%>
                                </div>
                                <%--tabsEmployeeStateTax end--%>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="left" style="font-size:12px; padding: 5px;">
                                    <b><spring:message code="BzComposer.configuration.otherstatetax"/></b>
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size:12px;">
                                    <input type="checkbox" id="otherStateChck_1">
                                    <input type="text" disabled="disabled" id="otherStateInput_1">
                                </td>
                                <td style="font-size:12px;">
                                    <div class="input-group">
                                      <spring:message code="BzComposer.configuration.rate"/>
                                      &nbsp;&nbsp;
                                      <input type="text" placeholder="0.00"   style="height:25px;" id="otherStateTaxRate_1">
                                      <div class="input-group-append">
                                      <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                    </div></div>
                                </td>
                                <td style="font-size:12px;">
                                    <div class="input-group">
                                      <spring:message code="BzComposer.configuration.upto"/>
                                      &nbsp;&nbsp;
                                      <input type="text" placeholder="0.00" disabled="disabled" style="height:25px;" id="otherStateUpto_1">
                                      <div class="input-group-append">
                                      <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">$</span>
                                    </div></div>
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size:12px;">
                                    <input type="checkbox" id="otherStateChck_2">
                                    <input type="text" disabled="disabled" id="otherStateInput_2">
                                </td>
                                <td style="font-size:12px;">
                                    <div class="input-group">
                                      <spring:message code="BzComposer.configuration.rate"/>
                                      &nbsp;&nbsp;
                                      <input type="text" placeholder="0.00" disabled="disabled" style="height:25px;" id="otherStateTaxRate_2">
                                      <div class="input-group-append">
                                      <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                    </div></div>
                                </td>
                                <td style="font-size:12px;">
                                    <div class="input-group">
                                      <spring:message code="BzComposer.configuration.upto"/>
                                      &nbsp;&nbsp;
                                      <input type="text" placeholder="0.00" disabled="disabled" style="height:25px;" id="otherStateUpto_2">
                                      <div class="input-group-append">
                                      <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">$</span>
                                    </div></div>
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size:12px;">
                                    <input type="checkbox" id="otherStateChck_3">
                                    <input type="text" disabled="disabled" id="otherStateInput_3">
                                </td>
                                <td style="font-size:12px;">
                                    <div class="input-group">
                                      <spring:message code="BzComposer.configuration.rate"/>
                                      &nbsp;&nbsp;
                                      <input type="text" placeholder="0.00" disabled="disabled" style="height:25px;" id="otherStateTaxRate_3">
                                      <div class="input-group-append">
                                      <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">%</span>
                                    </div></div>
                                </td>
                                <td style="font-size:12px;">
                                    <div class="input-group">
                                      <spring:message code="BzComposer.configuration.upto"/>
                                      &nbsp;&nbsp;
                                      <input type="text" placeholder="0.00" disabled="disabled" style="height:25px;" id="otherStateUpto_3">
                                      <div class="input-group-append">
                                      <span class="input-group-text" style="height:25px; font-size:12px; color:black !important;">$</span>
                                    </div></div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"  style="font-size:14px;">
                                   <!-- <button type="button" class="formButton">
                                        <spring:message code="BzComposer.configuration.showavailablestatesbtn"/>
                                    </button> -->
                                    <button type="button" id="sitSave" name="sitSave" class="formButton" onclick="saveSID()">
                                        <spring:message code="BzComposer.global.save"/>
                                    </button>
                                    &nbsp;
                                       <button type="reset" id="resetSIDOthers" name="clear" class="formButton" onclick="clearSID()">
                                        <spring:message code="BzComposer.global.clear"/>
                                    </button>
                                    &nbsp;

                                    <!-- <button type="button" id="delete" name="delete" class="formButton" onclick="deleteSID()">
                                        <spring:message code="BzComposer.global.delete"/>
                                    </button> -->
                                    <button type="button" id="sitStateActive" name="sitStateActive" class="formButton" onclick="setSITStateActive(true)">
                                                                            <spring:message code="Bizcomposer.active"/>
                                                                        </button>
                                    <button type="button" id="sitStateInActive" name="sitStateInActive" class="formButton" onclick="setSITStateActive(false)">
                                                                                                                <spring:message code="BzComposer.InActive"/>
                                                                                                            </button>
                                    &nbsp;
                                    <button type="button" id="setAsDefault" name="setAsDefault" class="formButton"   onclick="setSITAsDefault()">
                                        <spring:message code="BzComposer.configuration.setasdefaultbtn"/>
                                    </button>
                                </td>
                            </tr>
                            </table>
                            </div>
                            <%--stateTax tab end--%>
                        </div>
                        <%--Tax Inner tabs end--%>
                        </div>
                        <%--Tax tab end--%>
                    </td>
                    <%--Tax page end--%>
                </tr>
            </table>
    </div><%--Configuration tab border end--%>

</div><%--Tax Body end--%>


</div><%--div class="statusquo ok" end--%>
</div><%--div id="hoja" end--%>
</div><%--div id="blanquito" end--%>
</div><%--div id="padding" end--%>
</form:form>

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
</body>
<%--Extra Code start--%>
<script type="text/javascript">
function selectshippingtypedialog()
{
	event.preventDefault();
	$("#selectshippingtypedialog").dialog({
    	resizable: false,
        height: 200,
        width: 450,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function selectshippingtypetoupdatedialog()
{
	event.preventDefault();
	$("#selectshippingtypetoupdatedialog").dialog({
    	resizable: false,
        height: 200,
        width: 450,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function duplicatevaluedialog()
{
	event.preventDefault();
	$("#duplicatevaluedialog").dialog({
    	resizable: false,
        height: 200,
        width: 450,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}


function saveFederalTax(){
 var active = $("#tabsEmployeeFederalTax").tabs("option", "active");

$("#saveFederaTax").dialog({
			    	resizable: false,
			        height: 200,
			        width: 500,
			        modal: true,
			        buttons: {
			        	"<spring:message code='BzComposer.global.ok'/>": function () {
                             var postData ={
 			            	    autoFIT : parseInt($('#autoFIT').val()),
			            	    yearFIT : $('#selectedTaxYear').val(),
			            	    rateFICA : $('#rateFICA').val(),
			            	    rateSocialTax : $('#rateSocialTax').val(),
                                socialTaxLimit : $('#socialTaxLimit').val(),
                                rateMedicareTax : $('#rateMedicareTax').val(),
                                rateFUTA : $('#rateFUTA').val(),
                                rateFIT : $('#rateFIT').val()
			            	 }
			            	$(this).dialog("close");

                            	 $.ajax({
                                        								type: "POST",
                                        						   		url:"Configuration/FederalTax/companyTaxInfo",
                                         						   		data : postData,
                                                                        success : function(res) {
                                        					 			    // console.log('res',res)


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


			            },
			            "<spring:message code='BzComposer.global.cancel'/>": function () {
			                $(this).dialog("close");
			                return false;
			            }
			        }
			    });


}

function clearCompanyTaxDeduction(){

    $('.row-comp-tax-deduction').removeClass('draft');

 	$('#deductionListId').val('');
 	$('#deductionName').val('');

 	$('#deductionRate').val('');
 	$('#deductionAmount').val('');
 	$('#deductionRate').val('');
    $('#deductionAmount').val('');
    $("#deductionAmount").removeAttr("disabled");
    $('#deductAmountChck').prop("checked",true);
    $('#deductRateChck').prop("checked",false)
    $('#taxExemption').prop("checked",false);
    $("#deductionRate").attr("disabled", "disabled");

}


function clearCompanyTaxOption(){
    $('.row-comp-tax-option').removeClass('draft');
 	$('#optionId').val('');


$("#holidayRate").attr("disabled", "disabled");
              $("#weekendSundayRate").attr("disabled", "disabled");
$("#dailyHours").attr("disabled", "disabled");
               $("#weekendSaturdayRate").attr("disabled", "disabled");
                                      $("#weeklyHours").attr("disabled", "disabled");
        $("#txtDayOfMonth").attr("disabled", "disabled");
        $("#txtDayOfWeek").attr("disabled", "disabled");


}




function deleteCompanyTaxDeduction()
{
  var   deductionListId =  $('#deductionListId').val();


  // validation....
     if(deductionListId == ''){
              $("#federaltaxemptydata").dialog({
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
      return;
     }

  $("#deleteFederalTaxCompanyDeduction").dialog({
   			    	resizable: false,
   			        height: 200,
   			        width: 500,
   			        modal: true,
   			        buttons: {
   			        	"<spring:message code='BzComposer.global.ok'/>": function () {
   			        	 var postData ={
    			            	    deductionListId : deductionListId
    			           }

   			            	$(this).dialog("close");

                               	 $.ajax({
                                           								type: "POST",
                                           						   		url:"Configuration/FederalTax/companyTaxOption/deduction/delete",
                                            						   		data : postData,
                                                                           success : function(res) {
                                           					 			      // console.log('res',res)
                                                                              clearCompanyTaxDeduction();

                                                                              var html = '';
                                                                              for (let step = 0; step < res.length; step++) {
                                                                                  var d = res[step];
                                                                                  var str = 'setCompanyTaxDeduction(this,"'+d.deductionListId+'","'+d.deductionListName+'","'+d.deductionAmount+'","'+d.deductionRate+'","'+d.useRate+'","'+d.isTaxExempt+'")';
                                                                                  html += "<tr class='row-comp-tax-deduction' id='"+step+"-row-comp-tax-deduction' onclick='"+str+"'>"
                                                                                  html += "<td width='20%' style='font-size:12px;'>"+ d.deductionListName+"</td>"
                                                                                  html += "<td width='20%' style='font-size:12px;'>"+ d.deductionAmount+"</td>"
                                                                                  html += "<td width='20%' style='font-size:12px;'>"+ d.deductionRate+"</td>"
                                                                                  html += "<td width='20%' style='font-size:12px;'>"+ d.useRate+"</td>"
                                                                                  html += "<td width='20%' style='font-size:12px;'>"+ (d.isTaxExempt == 1 ? 'Yes' : 'No')+"</td>"
                                                                                  html +="</tr>";
                                                                              }
                                                                              $('#tbl-comtaxoptions-deduction-list > tbody').html('')
                                                                              $('#tbl-comtaxoptions-deduction-list > tbody').html(html)

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


   			            },
   			            "<spring:message code='BzComposer.global.cancel'/>": function () {
   			                $(this).dialog("close");
   			                return false;
   			            }
   			        }
   			    });



}



function deleteCompanyTaxOption()
{
  var   optionId =  $('#optionId').val();


  // validation....
     if(optionId == ''){
              $("#federaltaxemptydata").dialog({
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
      return;
     }

  $("#deleteFederalTaxCompanyOption").dialog({
   			    	resizable: false,
   			        height: 200,
   			        width: 500,
   			        modal: true,
   			        buttons: {
   			        	"<spring:message code='BzComposer.global.ok'/>": function () {
   			        	 var postData ={
    			            	    optionId : optionId
    			           }

   			            	$(this).dialog("close");

                               	 $.ajax({
                                           								type: "POST",
                                           						   		url:"Configuration/FederalTax/companyTaxOption/option/delete",
                                            						   		data : postData,
                                                                           success : function(res) {
                                           					 			      // console.log('res',res)
                                                                              // clearCompanyTaxDeduction();

                                                                               $("#resetClearOption").click()

                                                                                                                                                            var html = '';
                                                                                                                                                            for (let step = 0; step < res.length; step++) {
                                                                                                                                                                var deduct = res[step];
                                                                                                                                                                var str = 'setCompanyTaxOption(this, "'+deduct.startingDate+'","'+deduct.daily+'","'+deduct.weekly+'","'+deduct.monthly +'","'+deduct.annually+'","'+deduct.biweekly +'","'+deduct.quarterly+'","'+deduct.semiAnnually+'","'+deduct.semiMonthly+'","'+deduct.dailyOver+'","'+deduct.weeklyOver+'","'+deduct.dailyOverVal+'","'+deduct.weeklyOverVal+'","'+deduct.wendSt+'","'+deduct.wendStRate+'","'+deduct.wendSn+'","'+deduct.wendSnRate+'","'+deduct.holiday+'","'+deduct.overtimeRate+'","'+deduct.holidayRate+'","'+deduct.dayOfWeek+'","'+deduct.dayOfWeekVal+'","'+deduct.dayOfMonth+'","'+deduct.dayOfMonthVal+'","'+deduct.optionId+'")';
                                                                                                                                                                html += "<tr class='row-comp-tax-option' id='"+step+"-row-comp-tax-option' onclick='"+str+"'>"
                                                                                                                                                                html += "<td width='50%' style='font-size:12px;'>"+ deduct.startingDate+"</td>"
                                                                                                                                                                html += "<td width='50%' style='font-size:12px;'>"+ deduct.createdAt+"</td>"
                                                                                                                                                                html +="</tr>";
                                                                                                                                                            }
                                                                                                                                                            $('#tbl-comtaxoptions-option-list > tbody').html('')
                                                                                                                                                            $('#tbl-comtaxoptions-option-list > tbody').html(html)
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


   			            },
   			            "<spring:message code='BzComposer.global.cancel'/>": function () {
   			                $(this).dialog("close");
   			                return false;
   			            }
   			        }
   			    });



}


function saveCompanyTaxDeduction(edit)
{
  var   deductionListId = edit ? $('#deductionListId').val() : "";
  var 	deductionListName = $('#deductionName').val();
  var 	deductionRate = $('#deductionRate').val();
  var 	deductionAmount =	$('#deductionAmount').val();
  var   deductionRate = 0;
  var   deductionAmount = 0;
  var   isTaxExempt = $('#taxExemption').is(":checked") ? 1 : 0;

   if ($('#deductRateChck').is(":checked")) {
     deductionRate = $("#deductionRate").val();
   }else if ($('#deductAmountChck').is(":checked")) {
     deductionAmount = $("#deductionAmount").val();
   }

   // validation....
   if((edit && deductionListId == "") || (!edit && deductionListId != "") || deductionListName == ''){
            $("#federaltaxemptydata").dialog({
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
    return;
   }








   $("#saveFederalTaxCompanyDeduction").dialog({
   			    	resizable: false,
   			        height: 200,
   			        width: 500,
   			        modal: true,
   			        buttons: {
   			        	"<spring:message code='BzComposer.global.ok'/>": function () {
   			        	 var postData ={
    			            	    deductionListId : deductionListId == "" ? "0" : deductionListId,
    			            	    deductionListName : deductionListName,
    			            	    deductionRate : deductionRate,
    			            	    deductionAmount : deductionAmount,
    			            	    deductionRate : deductionRate,
    			            	    deductionAmount : deductionAmount,
    			            	    isTaxExempt : isTaxExempt

   			            	 }

   			            	$(this).dialog("close");

                               	 $.ajax({
                                           								type: "POST",
                                           						   		url:"Configuration/FederalTax/companyTaxOption/deduction",
                                            						   		data : postData,
                                                                           success : function(res) {
                                           					 			      // console.log('res',res)
                                                                              clearCompanyTaxDeduction();

                                                                              var html = '';
                                                                              for (let step = 0; step < res.length; step++) {
                                                                                  var d = res[step];
                                                                                  var str = 'setCompanyTaxDeduction(this,"'+d.deductionListId+'","'+d.deductionListName+'","'+d.deductionAmount+'","'+d.deductionRate+'","'+d.useRate+'","'+d.isTaxExempt+'")';
                                                                                  html += "<tr class='row-comp-tax-deduction' id='"+step+"-row-comp-tax-deduction' onclick='"+str+"'>"
                                                                                  html += "<td width='20%' style='font-size:12px;'>"+ d.deductionListName+"</td>"
                                                                                  html += "<td width='20%' style='font-size:12px;'>"+ d.deductionAmount+"</td>"
                                                                                  html += "<td width='20%' style='font-size:12px;'>"+ d.deductionRate+"</td>"
                                                                                  html += "<td width='20%' style='font-size:12px;'>"+ d.useRate+"</td>"
                                                                                  html += "<td width='20%' style='font-size:12px;'>"+ (d.isTaxExempt == 1 ? 'Yes' : 'No')+"</td>"
                                                                                  html +="</tr>";
                                                                              }
                                                                              $('#tbl-comtaxoptions-deduction-list > tbody').html('')
                                                                              $('#tbl-comtaxoptions-deduction-list > tbody').html(html)

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


   			            },
   			            "<spring:message code='BzComposer.global.cancel'/>": function () {
   			                $(this).dialog("close");
   			                return false;
   			            }
   			        }
   			    });



}



function saveCompanyTaxOption(edit)
{

  var   startingDate = $('#selectStartingDate').val()
  var   optionId = edit ? $('#optionId').val() : "";
  var   selectStartingDate = $('#selectStartingDate').val()
  var   daysOfWeek = $('#daysOfWeek').is(":checked") ? 1 : 0;
  var   txtDayOfWeek = $('#txtDayOfWeek').val()
  var   daysOfMonth = $('#daysOfMonth').is(":checked") ? 1 : 0;
  var   txtDayOfMonth = $('#txtDayOfMonth').val()
  var   dailyOrMiscellaneous = $('#dailyOrMiscellaneous').is(":checked") ? 1 : 0;
  var   weekly = $('#weekly').is(":checked") ? 1 : 0;
  var   biWeekly = $('#biWeekly').is(":checked") ? 1 : 0;
  var   semiMonthly = $('#semiMonthly').is(":checked") ? 1 : 0;
  var   monthly = $('#monthly').is(":checked") ? 1 : 0;
  var   quartely = $('#quartely').is(":checked") ? 1 : 0;
  var   semiAnnually = $('#semiAnnually').is(":checked") ? 1 : 0;
  var   annually = $('#annually').is(":checked") ? 1 : 0;
  var   dailyOverHours = $('#dailyOverHours').is(":checked") ? 1 : 0;
  var   dailyHours = $('#dailyHours').val()
  var   weekendSaturday = $('#weekendSaturday').is(":checked") ? 1 : 0;
  var   weekendSaturdayRate = $('#weekendSaturdayRate').val()
  var   weeklyOverHours = $('#weeklyOverHours').is(":checked") ? 1 : 0;
  var   weeklyHours = $('#weeklyHours').val()
  var   weekendSunday = $('#weekendSunday').is(":checked") ? 1 : 0;
  var   weekendSundayRate = $('#weekendSundayRate').val()
  var   holiday = $('#holiday').is(":checked") ? 1 : 0;
  var   holidayRate = $('#holidayRate').val();
  var   overtimeRate = $('#overtimeRate').val();


  // validation....
   if((edit && optionId == "") || (!edit && optionId != "")){
            $("#federaltaxemptydata").dialog({
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
    return;
   }


   $("#saveFederalTaxCompanyOption").dialog({
   			    	resizable: false,
   			        height: 200,
   			        width: 500,
   			        modal: true,
   			        buttons: {
   			        	"<spring:message code='BzComposer.global.ok'/>": function () {
   			        	 var postData ={
                                     startingDate : startingDate,
                                     daily : dailyOrMiscellaneous,
                                     weekly : weekly,
                                     monthly : monthly,
                                     annually : annually,
                                     biweekly : biWeekly,
                                     quarterly : quartely,
                                     semiAnnually : semiAnnually,
                                     semiMonthly : semiMonthly,
                                     dailyOver : dailyOverHours,
                                     weeklyOver : weeklyOverHours,
                                     dailyOverVal : dailyHours,
                                     weeklyOverVal : weeklyHours,
                                     wendSt : weekendSaturday,
                                     wendStRate : weekendSaturdayRate,
                                     wendSn : weekendSunday,
                                     wendSnRate : weekendSundayRate,
                                     holiday : holiday,
                                     holidayRate : holidayRate,
                                     dayOfWeek : daysOfWeek,
                                     dayOfWeekVal : txtDayOfWeek,
                                     dayOfMonth : daysOfMonth,
                                     dayOfMonthVal : txtDayOfMonth,
                                     overtimeRate : overtimeRate,
                                     optionId : optionId == "" ? "0" : optionId
                            }

   			            	$(this).dialog("close");

                               	 $.ajax({
                                           								type: "POST",
                                           						   		url:"Configuration/FederalTax/companyTaxOption/option",
                                            						   		data : postData,
                                                                           success : function(res) {
                                           					 			      // console.log('res',res)
                                                                              // clearCompanyTaxOption();
                                                                              $("#resetClearOption").click()

                                                                              var html = '';
                                                                              for (let step = 0; step < res.length; step++) {
                                                                                  var deduct = res[step];
                                                                                  var str = 'setCompanyTaxOption(this, "'+deduct.startingDate+'","'+deduct.daily+'","'+deduct.weekly+'","'+deduct.monthly +'","'+deduct.annually+'","'+deduct.biweekly +'","'+deduct.quarterly+'","'+deduct.semiAnnually+'","'+deduct.semiMonthly+'","'+deduct.dailyOver+'","'+deduct.weeklyOver+'","'+deduct.dailyOverVal+'","'+deduct.weeklyOverVal+'","'+deduct.wendSt+'","'+deduct.wendStRate+'","'+deduct.wendSn+'","'+deduct.wendSnRate+'","'+deduct.holiday+'","'+deduct.overtimeRate+'","'+deduct.holidayRate+'","'+deduct.dayOfWeek+'","'+deduct.dayOfWeekVal+'","'+deduct.dayOfMonth+'","'+deduct.dayOfMonthVal+'","'+deduct.optionId+'")';
                                                                                  html += "<tr class='row-comp-tax-option' id='"+step+"-row-comp-tax-option' onclick='"+str+"'>"
                                                                                  html += "<td width='50%' style='font-size:12px;'>"+ deduct.startingDate+"</td>"
                                                                                  html += "<td width='50%' style='font-size:12px;'>"+ deduct.createdAt+"</td>"
                                                                                  html +="</tr>";
                                                                              }
                                                                              $('#tbl-comtaxoptions-option-list > tbody').html('')
                                                                              $('#tbl-comtaxoptions-option-list > tbody').html(html)

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


   			            },
   			            "<spring:message code='BzComposer.global.cancel'/>": function () {
   			                $(this).dialog("close");
   			                return false;
   			            }
   			        }
   			    });



}

function setCompanyTaxDeduction(obj, deductionListId,deductionListName, deductionAmount, deductionRate, useRate, isTaxExempt)
{
    // row-comp-tax-deduction
    console.log(deductionListId,deductionListName, deductionAmount, deductionRate, useRate, isTaxExempt);
 	$('.row-comp-tax-deduction').removeClass('draft');
 	$(obj).addClass('draft');

 	$(obj).addClass('draft');
 	$('#deductionListId').val(deductionListId);
 	$('#deductionName').val(deductionListName);

 	$('#deductionRate').val('');
 	$('#deductionAmount').val('');



 	if(deductionRate && deductionRate > 0){
 	    $('#deductRateChck').prop("checked",true);
 	    $('#deductAmountChck').prop("checked",false);
        $('#deductionRate').val(deductionRate);
 	    $("#deductionRate").removeAttr("disabled");
        $("#deductionAmount").attr("disabled", "disabled");
    }else if(deductionAmount &&   deductionAmount > 0){
     	$('#deductRateChck').prop("checked",false);
     	$('#deductAmountChck').prop("checked",true);
     	$('#deductionAmount').val(deductionAmount);
     	$("#deductionAmount").removeAttr("disabled");
        $("#deductionRate").attr("disabled", "disabled");

    }

    if(isTaxExempt &&   isTaxExempt == 1){
        $('#taxExemption').prop("checked",true);
    } else{
        $('#taxExemption').prop("checked",false);
    }



}


function setCompanyTaxOption(obj,startingDate,daily,weekly,monthly ,annually,biweekly ,quarterly,semiAnnually,semiMonthly,dailyOver,weeklyOver,dailyOverVal,weeklyOverVal,wendSt,wendStRate,wendSn,wendSnRate,holiday,overtimeRate,holidayRate,dayOfWeek,dayOfWeekVal,dayOfMonth,dayOfMonthVal,optionId)
{

    // row-comp-tax-deduction
  	$('.row-comp-tax-option').removeClass('draft');
 	$(obj).addClass('draft');
  	$(obj).addClass('draft');
 	$('#optionId').val(optionId);

 	$('#selectStartingDate').val(startingDate);

    if(dayOfWeek &&   dayOfWeek == 1){
        $('#daysOfWeek').prop("checked",true);
        $("#txtDayOfWeek").removeAttr("disabled");
    } else{
        $('#daysOfWeek').prop("checked",false);
        $("#txtDayOfWeek").attr("disabled", "disabled");
    }



    $('#txtDayOfWeek').val(dayOfWeekVal);

     if(dayOfMonth &&   dayOfMonth == 1){
        $('#daysOfMonth').prop("checked",true);
        $("#txtDayOfMonth").removeAttr("disabled");
    } else{
        $('#daysOfMonth').prop("checked",false);
        $("#txtDayOfMonth").attr("disabled", "disabled");
    }

    $('#txtDayOfMonth').val(dayOfMonthVal);

    if(daily &&   daily == 1){
        $('#dailyOrMiscellaneous').prop("checked",true);
    } else{
        $('#dailyOrMiscellaneous').prop("checked",false);
    }

    if(weekly &&   weekly == 1){
        $('#weekly').prop("checked",true);
    } else{
        $('#weekly').prop("checked",false);
    }

    if(biweekly &&   biweekly == 1){
        $('#biWeekly').prop("checked",true);
    } else{
        $('#biWeekly').prop("checked",false);
    }
     if(semiMonthly &&   semiMonthly == 1){
        $('#semiMonthly').prop("checked",true);
    } else{
        $('#semiMonthly').prop("checked",false);
    }
     if(monthly &&   monthly == 1){
        $('#monthly').prop("checked",true);
    } else{
        $('#monthly').prop("checked",false);
    }
     if(quarterly &&   quarterly == 1){
        $('#quartely').prop("checked",true);
    } else{
        $('#quartely').prop("checked",false);
    }

     if(semiAnnually &&   semiAnnually == 1){
          $('#semiAnnually').prop("checked",true);
      } else{
          $('#semiAnnually').prop("checked",false);
      }

    if(annually  &&   annually == 1){
         $('#annually').prop("checked",true);
     } else{
         $('#annually').prop("checked",false);
     }
     if(dailyOver &&   dailyOver == 1){
             $('#dailyOverHours').prop("checked",true);
                      $("#dailyHours").removeAttr("disabled");
                                      $("#weeklyHours").attr("disabled", "disabled");
         } else{
             $('#dailyOverHours').prop("checked",false);
         }


      $('#dailyHours').val(dailyOverVal)

      if(wendSt &&   wendSt == 1){
                   $("#weekendSaturdayRate").removeAttr("disabled");
                  $('#weekendSaturday').prop("checked",true);
          } else{
              $('#weekendSaturday').prop("checked",false);
               $("#weekendSaturdayRate").attr("disabled", "disabled");
          }


           $('#weekendSaturdayRate').val(wendStRate)

      if(weeklyOver &&   weeklyOver == 1){
              $('#weeklyOverHours').prop("checked",true);
               $("#weeklyHours").removeAttr("disabled");
               $("#dailyHours").attr("disabled", "disabled");
          } else{
              $('#weeklyOverHours').prop("checked",false);


          }
       $('#weeklyHours').val(weeklyOverVal)

      if(wendSn &&   wendSn == 1){
              $('#weekendSunday').prop("checked",true);
              $("#weekendSundayRate").removeAttr("disabled");

          } else{
              $('#weekendSunday').prop("checked",false);
              $("#weekendSundayRate").attr("disabled", "disabled");
          }


     $('#weekendSundayRate').val(wendSnRate)


     if(holiday &&   holiday == 1){
                                  $("#holidayRate").removeAttr("disabled");
        $('#holiday').prop("checked",true);
         } else{
             $('#holiday').prop("checked",false);
                              $("#holidayRate").attr("disabled", "disabled");
         }



        $('#holidayRate').val(holidayRate);
        $('#overtimeRate').val(overtimeRate);




}



function setSIDOther(obj, ID, TaxName, TaxRate, UpTo )
{
   	$('.row-state-tax-other').removeClass('draft');
 	$(obj).addClass('draft');
  	$(obj).addClass('draft');
  	$('#sidOtherName').val(TaxName);

    $('#sidOtherId').val(ID);
    $('#sidOtherRate').val(TaxRate)
    $('#sidOtherUpTo').val(UpTo);
}


function clearSIDOthers(){
    $('.row-state-tax-other').removeClass('draft');
 	$('#sidOtherId').val('');
   $('#sidOtherId').val('');
      $('#sidOtherRate').val('')
      $('#sidOtherUpTo').val('');
        	$('#sidOtherName').val('');

}


function saveSID(){


  var   otherStateChck1 = $('#otherStateChck_1').is(":checked") ? 1 : 0;
  var   otherStateChck2 = $('#otherStateChck_2').is(":checked") ? 1 : 0;
  var   otherStateChck3 = $('#otherStateChck_3').is(":checked") ? 1 : 0;

  var otherStateInput1 = $('#otherStateInput_1').val();
  var otherStateInput2 = $('#otherStateInput_2').val();
  var otherStateInput3 = $('#otherStateInput_3').val();

  var otherStateTaxRate1 = $('#otherStateTaxRate_1').val();
  var otherStateTaxRate2 = $('#otherStateTaxRate_2').val();
  var otherStateTaxRate3 = $('#otherStateTaxRate_3').val();


  var otherStateUpto1 = $('#otherStateUpto_1').val();
  var otherStateUpto2 = $('#otherStateUpto_2').val();
  var otherStateUpto3 = $('#otherStateUpto_3').val();

  var ettRate = $('#ettRate').val();
  var uiRate = $('#uiRate').val();
  var sdiRate = $('#sdiRate').val();
  var pitRate = $('#pitRate').val();


  var upToSdi = $('#upToSdi').val();
  var upToEtt = $('#upToEtt').val();
  var upToui = $('#upToui').val();
  var stateTaxId = $('#stateTaxId').val();

  var stateId = $('#stateList').find(":selected").val();




   $("#saveFederalTaxCompanyOption").dialog({
   			    	resizable: false,
   			        height: 200,
   			        width: 500,
   			        modal: true,
   			        buttons: {
   			        	"<spring:message code='BzComposer.global.ok'/>": function () {
   			        	 var postData ={
                                     otherStateChck1 : otherStateChck1,
                                     otherStateChck2 : otherStateChck2,
                                     otherStateChck3 : otherStateChck3,

                                     otherStateInput1 : otherStateInput1,
                                     otherStateInput2 : otherStateInput2,
                                     otherStateInput3 : otherStateInput3,

                                     otherStateTaxRate1 : otherStateTaxRate1,
                                     otherStateTaxRate2 : otherStateTaxRate2,
                                     otherStateTaxRate3 : otherStateTaxRate3,

                                     otherStateUpto1 : otherStateUpto1,
                                     otherStateUpto2 : otherStateUpto2,
                                     otherStateUpto3 : otherStateUpto3,


                                     ettRate : ettRate,
                                     uiRate : uiRate,
                                     sdiRate : sdiRate,
                                     pitRate : pitRate,

                                     upToSdi : upToSdi,
                                     upToEtt : upToEtt,
                                     upToui : upToui,


                                     stateTaxId : stateTaxId,
                                     stateId : stateId
                             }

                            console.log('postData -->',postData)

   			            	$(this).dialog("close");

                               	 $.ajax({
                                           								type: "POST",
                                           						   		url:"Configuration/StateTax",
                                            						   		data : postData,
                                                                           success : function(res) {
                                           					 			      // console.log('res',res)
                                                                              // clearCompanyTaxOption();
                                                                              $("#resetSIDOthers").click()

 var html = '';
                                                                              for (let step = 0; step < res.length; step++) {
                                                                                  var deduct = res[step];
                                                                                  console.log(deduct);
                                                                                  // ID, TaxName, TaxRate, UpTo
                                                                                  var str = 'setSIDOther(this, "'+deduct.id+'","'+deduct.name+'","'+deduct.rate+'","'+deduct.upTo +'","'+deduct.stateId+'")';
                                                                                  html += "<tr class='row-state-tax-other' id='"+step+"-row-state-tax-other' onclick='"+str+"'>"
                                                                                  html += "<td width='40%' style='font-size:12px;'>"+ deduct.name+"</td>"
                                                                                  html += "<td width='30%' style='font-size:12px;'>"+ deduct.rate+"</td>"
                                                                                  html += "<td width='30%' style='font-size:12px;'>"+ deduct.upTo+"</td>"
                                                                                  html +="</tr>";
                                                                              }
                                                                              $('#tbl-statetax-other-list > tbody').html('')
                                                                              $('#tbl-statetax-other-list > tbody').html(html)
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


   			            },
   			            "<spring:message code='BzComposer.global.cancel'/>": function () {
   			                $(this).dialog("close");
   			                return false;
   			            }
   			        }
   			    });

}



function deleteSIDOthers()
{
  var   sidOtherId =  $('#sidOtherId').val();


  // validation....
     if(optionId == ''){
              $("#federaltaxemptydata").dialog({
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
      return;
     }

  $("#deleteFederalTaxCompanyOption").dialog({
   			    	resizable: false,
   			        height: 200,
   			        width: 500,
   			        modal: true,
   			        buttons: {
   			        	"<spring:message code='BzComposer.global.ok'/>": function () {
   			        	 var postData ={
    			            	    id : sidOtherId,
    			            	    stateId : 6
    			           }

   			            	$(this).dialog("close");

                               	 $.ajax({
                                           								type: "POST",
                                           						   		url:"Configuration/StateTax/OtherTax/delete",
                                            						   		data : postData,
                                                                           success : function(res) {
                                           					 			      // console.log('res',res)
                                                                              // clearCompanyTaxDeduction();

                                                                               $("#resetSIDOthers").click()

                                                                                                                                                            var html = '';
 var html = '';
                                                                              for (let step = 0; step < res.length; step++) {
                                                                                  var deduct = res[step];
                                                                                  console.log(deduct);
                                                                                  // ID, TaxName, TaxRate, UpTo
                                                                                  var str = 'setSIDOther(this, "'+deduct.id+'","'+deduct.name+'","'+deduct.rate+'","'+deduct.upTo +'","'+deduct.stateId+'")';
                                                                                  html += "<tr class='row-state-tax-other' id='"+step+"-row-state-tax-other' onclick='"+str+"'>"
                                                                                  html += "<td width='40%' style='font-size:12px;'>"+ deduct.name+"</td>"
                                                                                  html += "<td width='30%' style='font-size:12px;'>"+ deduct.rate+"</td>"
                                                                                  html += "<td width='30%' style='font-size:12px;'>"+ deduct.upTo+"</td>"
                                                                                  html +="</tr>";
                                                                              }
                                                                              $('#tbl-statetax-other-list > tbody').html('')
                                                                              $('#tbl-statetax-other-list > tbody').html(html)
                                                                                                                                                                 for (let step = 0; step < res.length; step++) {
                                                                                                                                                                var deduct = res[step];
                                                                                                                                                                var str = 'setCompanyTaxOption(this, "'+deduct.startingDate+'","'+deduct.daily+'","'+deduct.weekly+'","'+deduct.monthly +'","'+deduct.annually+'","'+deduct.biweekly +'","'+deduct.quarterly+'","'+deduct.semiAnnually+'","'+deduct.semiMonthly+'","'+deduct.dailyOver+'","'+deduct.weeklyOver+'","'+deduct.dailyOverVal+'","'+deduct.weeklyOverVal+'","'+deduct.wendSt+'","'+deduct.wendStRate+'","'+deduct.wendSn+'","'+deduct.wendSnRate+'","'+deduct.holiday+'","'+deduct.overtimeRate+'","'+deduct.holidayRate+'","'+deduct.dayOfWeek+'","'+deduct.dayOfWeekVal+'","'+deduct.dayOfMonth+'","'+deduct.dayOfMonthVal+'","'+deduct.optionId+'")';
                                                                                                                                                                html += "<tr class='row-comp-tax-option' id='"+step+"-row-comp-tax-option' onclick='"+str+"'>"
                                                                                                                                                                html += "<td width='50%' style='font-size:12px;'>"+ deduct.startingDate+"</td>"
                                                                                                                                                                html += "<td width='50%' style='font-size:12px;'>"+ deduct.createdAt+"</td>"
                                                                                                                                                                html +="</tr>";
                                                                                                                                                            }
                                                                                                                                                            $('#tbl-comtaxoptions-option-list > tbody').html('')
                                                                                                                                                            $('#tbl-comtaxoptions-option-list > tbody').html(html)
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


   			            },
   			            "<spring:message code='BzComposer.global.cancel'/>": function () {
   			                $(this).dialog("close");
   			                return false;
   			            }
   			        }
   			    });



}

function setSITAsDefault(){

  var stateId = $('#stateList').find(":selected").val();


    $('#setAsDefault').hide();

$("#saveSITsetAsDefault").dialog({
   			    	resizable: false,
   			        height: 200,
   			        width: 500,
   			        modal: true,
   			        buttons: {
   			        	"<spring:message code='BzComposer.global.ok'/>": function () {
   			        	 var postData ={
                                       stateId : stateId
                             }

                            console.log('postData -->',postData)

   			            	$(this).dialog("close");

                               	 $.ajax({
                                           								type: "POST",
                                           						   		url:"Configuration/StateTax/setAsDefault",
                                            						   		data : postData,
                                                                           success : function(res) {
                                           					 			      // console.log('res',res)
                                                                              // clearCompanyTaxOption();

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
                                           								    $('#setAsDefault').show();

                                           								    return false;
                                           								}
                                           							});


   			            },
   			            "<spring:message code='BzComposer.global.cancel'/>": function () {
   			                $(this).dialog("close");
   			                $('#setAsDefault').show();

   			                return false;
   			            }
   			        }
   			    });

}

function setSITStateActive(active){


  var stateId = $('#stateList').find(":selected").val();

var v = active ? '#saveSITStateStatusActive' : '#saveSITStateStatusInActive';

$(v).dialog({
   			    	resizable: false,
   			        height: 200,
   			        width: 500,
   			        modal: true,
   			        buttons: {
   			        	"<spring:message code='BzComposer.global.ok'/>": function () {
   			        	 var postData ={
                                       active : active,
                                       stateId : stateId
                             }

                            console.log('postData -->',postData)

   			            	$(this).dialog("close");

                               	 $.ajax({
                                           								type: "POST",
                                           						   		url:"Configuration/StateTax/setActive",
                                            						   		data : postData,
                                                                           success : function(res) {
                                           					 			      console.log('res',res)
                                                                              // clearCompanyTaxOption();

                                                                              $('#sitStateActive').hide();
                                                                              $('#sitStateInActive').hide();

                                                                              if(res.active){
                                                                                $('#sitStateInActive').show()
                                                                              }else{$('#sitStateActive').show()}

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
                                           								    $('#setAsDefault').show();

                                           								    return false;
                                           								}
                                           							});


   			            },
   			            "<spring:message code='BzComposer.global.cancel'/>": function () {
   			                $(this).dialog("close");
   			                $('#setAsDefault').show();

   			                return false;
   			            }
   			        }
   			    });

}

</script>
</html>
<!-- Dialog box used in this page -->
<div id="selectshippingtypedialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.tax.selectshippingtype"/></p>
</div>
<div id="selectshippingtypetoupdatedialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.tax.selectshippingviatoupdate"/></p>
</div>
<div id="duplicatevaluedialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.tax.duplicatevalue"/></p>
</div>
<div id="saveFederaTax" style="display:none;">
	<p><spring:message code="BzComposer.federaltax.savefederaltax"/></p>
</div>
<div id="saveSITStateStatusActive" style="display:none;">
	<p><spring:message code="BzComposer.statetax.saveactive"/></p>
</div>
<div id="saveSITStateStatusInActive" style="display:none;">
	<p><spring:message code="BzComposer.statetax.saveinactive"/></p>
</div>
<div id="saveSITsetAsDefault" style="display:none;">
	<p><spring:message code="BzComposer.statetax.savesetasdefault"/></p>
</div>


<div id="saveFederalTaxCompanyDeduction" style="display:none;">
	<p><spring:message code="BzComposer.federaltax.savefederaltaxcompanydeduction"/></p>
</div>
<div id="saveFederalTaxCompanyOption" style="display:none;">
	<p><spring:message code="BzComposer.federaltax.savefederaltaxcompanydeduction"/></p>
</div>
<div id="deleteFederalTaxCompanyDeduction" style="display:none;">
	<p><spring:message code="BzComposer.federaltax.deletefederaltaxcompanydeduction"/></p>
</div>
<div id="deleteFederalTaxCompanyOption" style="display:none;">
	<p><spring:message code="BzComposer.federaltax.deletefederaltaxcompanydeduction"/></p>
</div>
<div id="errorOccurred" style="display:none;font-size:12px;">
	<p><spring:message code="BzComposer.common.erroroccurred"/></p>
</div>
<div id="federaltaxemptydata" style="display:none;font-size:12px;">
	<p><spring:message code="BzComposer.federaltax.emptydata"/></p>
</div>



<%--Extra Code end--%>