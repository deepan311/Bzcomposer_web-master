<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false"%>
<%-- <%@ page errorPage="/include/sessionExpired.jsp"%> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/WEB-INF/jsp/include/headlogo.jsp" />
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<jsp:include page="/WEB-INF/jsp/include/menu.jsp" />
<title><spring:message code="BzComposer.formcustomizationtitle" /></title>
<script src="${pageContext.request.contextPath}/dist/js/custom.js"></script>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
</head>
<script>
$(function() {
    $("#tabs").tabs();
});
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
<style>
.switch { position: relative;display: inline-block;width: 40px;height: 22px;margin-left:10px; }
.switch input { opacity: 0;width: 0;height: 0; margin-top: 15px; }
.slider {
  position: absolute;cursor: pointer;top: 0;left: 0;right: 0;bottom: 0;
  background-color: #ccc;-webkit-transition: .4s;transition: .4s;
}
.slider:before {
  position: absolute;content: "";height: 15px;width: 15px;left: 4px;bottom: 4px;
  background-color: white;-webkit-transition: .4s;transition: .4s;
}
input:checked + .slider { background-color: #2196F3; }

input:checked + .slider:before {
  -webkit-transform: translateX(15px);
  -ms-transform: translateX(15px);
  transform: translateX(15px);
}

/* Rounded sliders */
.slider.round { border-radius: 20px; }
.slider.round:before { border-radius: 50%; }

.parentImage {
	-webkit-transition: all 0.5s ease;
	-moz-transition: all 0.5s ease;
	-o-transition: all 0.5s ease;  
	transition: all 0.5s ease;
}
.parentImage:hover {
    -moz-transform: scale(2);
	-webkit-transform: scale(2);
	-o-transform: scale(2);
	-ms-transform: scale(2);  
	transform: scale(2);
}
</style>
</head>
<!-- <body onload="init1();"> -->
<body onload="" style="min-width: 1366px;">
<!-- begin shared/header -->
<form:form name="formCustomization" enctype="MULTIPART/FORM-DATA" method="post" id="frmcust" modelAttribute="formTemplateList">
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
			<span style="font-size:30px;cursor:pointer; margin-left: 20px;" onclick="toggleFunction()">&#9776;</span>
			<table cellspacing="0"  style="border: 0;width: 100%;overflow-y:scroll;" class="section-border">
				<tr>
					<td id="leftMenu" valign="top" style="position: relative; width: 180px;">
						<table>
							<tr><td><jsp:include page="menuPage.jsp" /></td></tr>
						</table>
					</td>
					<td valign="top" style="padding-right: 20px; padding-bottom: 20px;">
					    <div id="formCustomization" style="padding: 0; position: relative; left:0;">
					    <div id="tabs" style="height: auto;">
					        <ul>
                                <li style="font-size: 12px;"><a href="#invoiceTab"><spring:message code="BzComposer.invoice.invoicetitle"/></a></li>
                                <li style="font-size: 12px;"><a href="#estimationTab"><spring:message code="BzComposer.estimation.estimationtitle"/></a></li>
                                <li style="font-size: 12px;"><a href="#salesorderTab"><spring:message code="BzComposer.salesorder.salesordertitle"/></a></li>
                                <li style="font-size: 12px;"><a href="#purchaseorderTab"><spring:message code="BzComposer.purchase.PurchaseOrder"/></a></li>
                                <li style="font-size: 12px;"><a href="#packingslipsTab"><spring:message code="BzComposer.configuration.packingslips"/></a></li>
                                <li style="font-size: 12px;"><a href="#serviceTab"><spring:message code="BzComposer.configuration.bills"/></a></li>
                            </ul>
  
			            <!-- invoiceTab Starts -->
                       <%--  <div id="invoiceTab" class="tabPage">
                            <table class="table-notifications" style="width:100%">
                                <tr>
                                    <th colspan="4" align="left" style="font-size:12px; padding:5px;">
                                        <spring:message code="BzComposer.invoice.invoicetitle" />
                                    </th>
                                </tr>
                           
                                
                                
                                <tr style="border-top: 1px solid grey;">
                                    <td style="padding-top:25px;font-size:12px;">
                                        <label class="switch switchIV">
                                          <input type="checkbox" value="3" onchange="setTemplateValues(this, 'IV', 3);" ${configDto.invoiceTemplateType==3?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Product Standard</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/PrintInvoice1.png" style="width:100%;" />
                                    </td>
                                    <td style="padding-top:25px;font-size:12px;">
                                        <label class="switch switchIV">
                                          <input type="checkbox" value="4" onchange="setTemplateValues(this, 'IV', 4);" ${configDto.invoiceTemplateType==4?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Product Charcoal</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/PrintInvoice2.png" style="width:100%;" />
                                    </td>
                                    <td style="padding-top:25px;font-size:12px;">
                                        <label class="switch switchIV">
                                          <input type="checkbox" value="5" onchange="setTemplateValues(this, 'IV', 5);" ${configDto.invoiceTemplateType==5?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Professional Standard</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/PrintInvoice3.png" style="width:96%;" />
                                    </td>
                                    <td style="padding-top:25px;font-size:12px;">
                                        <label class="switch switchIV">
                                          <input type="checkbox" value="6" onchange="setTemplateValues(this, 'IV', 6);" ${configDto.invoiceTemplateType==6?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Professional Charcoal</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/PrintInvoice4.png" style="width:96%;" />
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <!-- invoiceTab Ends -->

                        <!-- estimationTab Starts -->
                        <div id="estimationTab" class="tabPage">
                            <table class="table-notifications" style="width:100%">
                                <tr>
                                    <th colspan="4" align="left" style="font-size:12px; padding:5px;">
                                        <spring:message code="BzComposer.estimation.estimationtitle" />
                                    </th>
                                </tr>
                                <tr style="border-top: 1px solid grey;">
                                    <td style="width:25%;padding-top:25px;font-size:12px;">
                                        <label class="switch switchEST">
                                          <input type="checkbox" name="estTemplateType" value="3" onchange="setTemplateValues(this, 'EST', 3);" ${configDto.estTemplateType==3?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Product Standard</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/PrintEst1.png" style="width:100%;" />
                                    </td>
                                    <td style="width:25%;padding-top:25px;font-size:12px;">
                                        <label class="switch switchEST">
                                          <input type="checkbox" name="estTemplateType" value="4" onchange="setTemplateValues(this, 'EST', 4);" ${configDto.estTemplateType==4?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Product Charcoal</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/PrintEst2.png" style="width:100%;" />
                                    </td>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                            </table>
                        </div>
                        <!-- estimationTab Ends -->

                        <!-- salesOrderTab Starts -->
                        <div id="salesOrderTab" class="tabPage">
                            <table class="table-notifications" style="width:100%">
                                <tr>
                                    <th colspan="4" align="left" style="font-size:12px; padding:5px;">
                                        <spring:message code="BzComposer.salesorder.salesordertitle" />
                                    </th>
                                </tr>
                                <tr style="border-top: 1px solid grey;">
                                    <td style="width:25%;padding-top:25px;font-size:12px;">
                                        <label class="switch switchSO">
                                          <input type="checkbox" name="soTemplateType" value="1" onchange="setTemplateValues(this, 'SO', 1);" ${configDto.soTemplateType==1?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Sales Order Standard</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/PrintSO1.png" style="width:100%;" />
                                    </td>
                                    <td style="width:25%;padding-top:25px;font-size:12px;">
                                        <label class="switch switchSO">
                                          <input type="checkbox" name="soTemplateType" value="2" onchange="setTemplateValues(this, 'SO', 2);" ${configDto.soTemplateType==2?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Sales Order Charcoal</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/PrintSO2.png" style="width:100%;" />
                                    </td>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                            </table>
                        </div>
                        <!-- salesOrderTab Ends -->

                        <!-- purchaseOrderTab Starts -->
                        <div id="purchaseOrderTab" class="tabPage">
                            <table class="table-notifications" style="width:100%;max-height:1000px;">
                                <tr>
                                    <th colspan="4" align="left" style="font-size:12px; padding:5px;">
                                        <spring:message code="BzComposer.purchase.PurchaseOrder" />
                                    </th>
                                </tr>
                                <tr style="border-top: 1px solid grey;">
                                    <td style="width:25%;padding-top:25px;font-size:12px;">
                                        <label class="switch switchPO">
                                          <input type="checkbox" name="poTemplateType" value="7" onchange="setTemplateValues(this, 'PO', 7);" ${configDto.poTemplateType==7?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>PO Standard</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/PrintPO1.png" style="width:100%;" />
                                    </td>
                                    <td style="width:25%;padding-top:25px;font-size:12px;">
                                        <label class="switch switchPO">
                                          <input type="checkbox" name="poTemplateType" value="8" onchange="setTemplateValues(this, 'PO', 8);" ${configDto.poTemplateType==8?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>PO Charcoal</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/PrintPO2.png" style="width:100%;" />
                                    </td>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                            </table>
                        </div>
                        <!-- purchaseOrderTab Ends -->

                        <!-- packingslipsTab Starts - packingSlipsTemplateType -->
                        <div id="packingslipsTab" class="tabPage">
                            <table class="table-notifications" style="width:100%">
                                <tr>
                                    <th colspan="4" align="left" style="font-size:12px; padding:5px;">
                                        <spring:message code="BzComposer.configuration.packingslips" />
                                    </th>
                                </tr>
                                <tr style="border-top: 1px solid grey;">
                                    <td style="width:25%;padding-top:25px;font-size:12px;">
                                        <label class="switch switchPS">
                                          <input type="checkbox" name="psTemplateType" value="1" onchange="setTemplateValues(this, 'PS', 1);" ${configDto.psTemplateType==1?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong><spring:message code="BzComposer.configuration.packingslips" /></strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/PackSlip1.png" style="width:100%;" />
                                    </td>
                                    <td colspan="3">&nbsp;</td>
                                </tr>
                            </table>
                        </div>
                        <!-- packingslipsTab Ends -->--%>

                        <!-- billsTab Starts - billsTemplateType -->
                     <%--    <div id="serviceBillsTab" class="tabPage">
                            <table class="table-notifications" style="width:100%">
                                <tr>
                                    <th colspan="3" align="left" style="font-size:12px; padding:5px;">
                                        <spring:message code="BzComposer.configuration.bills" />
                                    </th>
                                </tr>    
                                <tr style="border-top: 1px solid grey;">
                                    <td style="padding-top:25px;font-size:12px;">
                                        <label class="switch switchSB">
                                          <input type="checkbox" value="3" onchange="setTemplateValues(this, 'SB', 1);" ${configDto.billsTemplateType==1?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Service Basic</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/printService1.png" style="width:100%;" />
                                    </td>
                                    <td style="padding-top:25px;font-size:12px;">
                                        <label class="switch switchSB">
                                          <input type="checkbox" value="4" onchange="setTemplateValues(this, 'SB', 2);" ${configDto.billsTemplateType==2?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Service Simple</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/printService2.png" style="width:100%;" />
                                    </td>
                                    <td style="padding-top:25px;font-size:12px;">
                                        <label class="switch switchSB">
                                          <input type="checkbox" value="5" onchange="setTemplateValues(this, 'SB', 3);" ${configDto.billsTemplateType==3?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Service Accounting</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/printService3.png" style="width:96%;" />
                                    </td>
                                    <td style="padding-top:25px;font-size:12px;">
                                        <label class="switch switchSB">
                                          <input type="checkbox" value="6" onchange="setTemplateValues(this, 'SB', 4);" ${configDto.billsTemplateType==4?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Service Hourly</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/printService4.png" style="width:96%;" />
                                    </td>
                                </tr>
                                <tr style="border-top: 1px solid grey;">
                                	 <td style="padding-top:25px;font-size:12px;">
                                        <label class="switch switchSB">
                                          <input type="checkbox" value="5" onchange="setTemplateValues(this, 'SB', 5);" ${configDto.billsTemplateType==5?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Service Labor & Parts</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/printService5.png" style="width:96%;" />
                                    </td>
                                    <td style="padding-top:25px;font-size:12px;">
                                        <label class="switch switchSB">
                                          <input type="checkbox" value="6" onchange="setTemplateValues(this, 'SB', 6);" ${configDto.billsTemplateType==6?'checked':''} />
                                          <span class="slider round"></span>
                                        </label>
                                        <strong>Service Repair</strong><br/>
                                        <img class="parentImage" src="${pageContext.request.contextPath}/images/new/printService6.png" style="width:96%;" />
                                    </td>
                                </tr>
                            </table>
                        </div>  --%>
                        <!-- billsTab Ends -->
                        
                        <input type="hidden" name=invType value="">
                        <c:forEach var="element" items="${formTemplateList}">
                        		<div id="${fn:replace(fn:toLowerCase(element.templateName),' ','')}Tab" class="tabPage">
		                        <table class="table-notifications" style="width:100%">
		                        <tr>
				                                    <th colspan="4" align="left" style="font-size:12px; padding:5px;">  
				                                        <%-- <spring:message code="BzComposer.invoice.invoicetitle" /> --%>
				                                        	${element.templateName}
				                                    </th>
				                                    <c:set var="invoiceType" scope="page" value="${fn:toLowerCase(fn:substring(element.templateName,0,3))}"/> 
			                     </tr><tr style="border-top: 1px solid grey;">
		                        	 <c:forEach var="data" items="${element.mappingData}"  varStatus="status">
		                        	 			<c:if test="${status.count ==4 }">
			                                		<tr style="border-top: 1px solid grey;">
			                               		</c:if>
			                                            <td style="padding-top:25px;font-size:12px;">
			                                			<label class="switch switch${invoiceType}">
			                                			<c:set var="checkboxId" scope="page" value="${invoiceType}${data.templateNo}"/>
				                                          <input type="checkbox" id ="${checkboxId}" value="${checkboxId}" onchange="setTemplateValues(this, ${checkboxId}, ${checkboxId});" ${data.isSelected==true?'checked':''} />
				                                          <span class="slider round"></span>
				                                        </label>
				                                         <strong>${data.templateType}</strong><br/>
                                        				 <img class="parentImage" src="${pageContext.request.contextPath}${data.imagePath}" style="width:100%;" />
                                        				 <c:if test="${data.isSelected==true}">
                                        				 	<input type="hidden" id=" ${invoiceType}${data.templateNo}" value="${data.isSelected}">
                                          				 </c:if>
			                                		</td>
			                                		<c:if test="${fn:length(element.mappingData)<3 && status.count== fn:length(element.mappingData)}">
			                                			<c:set var="colspan" scope="page" value="${3-fn:length(element.mappingData)}"/>
			                                			<td colspan="${colspan}" style="padding-top:25px;font-size:12px;width:${100/3*colspan}%;">&nbsp;</td>
			                                		</c:if>
			                                	<c:if test="${status.count ==3 || status.count == 6}"></tr></c:if>
			                          </c:forEach>
			                   </table></div>
		                             
                        </c:forEach>

			        </div>
			        </div>
			</td>
		</tr>
	</table>
	<div>
	    <%-- <form:hidden path="empStateID" />
	    <form:hidden path="labelName" />
	    <form:hidden path="fileName" /> --%>
	    <input type="hidden" name="tabid" id="tid" value="" />
	    <%-- <input type="hidden" id="tempValueIV" value="${configDto.invoiceTemplateType}" />
	    <input type="hidden" id="tempValueEST" value="${configDto.estTemplateType}" />
	    <input type="hidden" id="tempValueSO" value="${configDto.soTemplateType}" />
	    <input type="hidden" id="tempValuePO" value="${configDto.poTemplateType}" />
	    <input type="hidden" id="tempValuePS" value="${configDto.psTemplateType}" />
	     <input type="hidden" id="tempValueSB" value="${configDto.sbTemplateType}" /> --%>
	</div>
	</div>
	<div>
	<div align="center">
	<input type="button" class="bottomButton formButton" onclick="saveTemplateTypes();" value="<spring:message code='BzComposer.global.save'/>" />
	<input type="reset" class="bottomButton formButton" name="Cancel" value="<spring:message code='BzComposer.global.cancel'/>" />
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</form:form>
<script language="javascript">

function setTemplateValues(tempElement, tempName, tempVal){
    let isSelected =  tempElement.checked;
   
    var element = tempName.id;
    if (isSelected && element.includes("inv")){
        tempName.value = tempVal.value;
        $('.switchinv input').prop('checked', false);
    }  
    else if (isSelected && element.includes("est")){
        tempName.value = tempVal.value;
        $('.switchest input').prop('checked', false);
    }
    else if (isSelected && element.includes("sal")){
        tempName.value = tempVal.value;
        $('.switchsal input').prop('checked', false);
    }
    else if (isSelected && element.includes("pur")){
        tempName.value = tempVal.value;
        $('.switchpur input').prop('checked', false);
    }
    else if (isSelected && element.includes("pac")){
        tempName.value = tempVal.value;
        $('.switchpac input').prop(tempName, false);
    }
    else if (isSelected && element.includes("ser")){
        tempName.value = tempVal.value;
        $('.switchser input').prop('checked', false);
    }
    tempElement.checked = isSelected;
}
function saveTemplateTypes(){
/*     let params = "invoiceTemplateType="+document.getElementById('tempValueIV').value
                +"&estTemplateType="+document.getElementById('tempValueEST').value
                +"&soTemplateType="+document.getElementById('tempValueSO').value
                +"&poTemplateType="+document.getElementById('tempValuePO').value
                +"&psTemplateType="+document.getElementById('tempValuePS').value
                +"&sbTemplateType="+document.getElementById('tempValueSB').value */
    let params="";
   // const params= [];
    var checkedBoxes = document.querySelectorAll( 'input[type="checkbox"]:checked' );
    var token="splitter";
    for( var i =0;i< checkedBoxes.length; i++ ) {
    	if(i==checkedBoxes.length-1){
    		token="";
    	}
    	params+=checkedBoxes[i].value.substr(3)+token;
    }
        console.log( params);
     $.ajax({  
        type : "GET",
        url : "/saveTemplateDetails?tabid=setPrintingTemplates",
        data : "formTemplateList="+params,
        success : function(data) {
            if(data == true){
                showSuccessDialog();
            }else{
                errorOccureddialog();
            }
        },
        error : function(data) {
            alert("<bean:message key='BzComposer.common.erroroccurred'/>");
        }
    }); 
    
    
    
}

function showSuccessDialog(){
    $("#showSuccessDialog").dialog({
        resizable: false,
        height: 200,
        width: 400,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
}
function errorOccureddialog(){
    $("#errorOccureddialog").dialog({
        resizable: false,
        height: 200,
        width: 400,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
}
function selectAnyTemplateDialog(){
    $("#selectAnyTemplateDialog").dialog({
        resizable: false,
        height: 200,
        width: 400,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    
}
//alert("${pageContext.request.contextPath}");


</script>

    

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
</body>
</html>
<!-- Dialog box used in this page -->
<div id="showSuccessDialog" style="display:none;">
	<p><spring:message code='BzComposer.common.recordUpdated'/></p>
</div>
<div id="errorOccureddialog" style="display:none;">
	<p><spring:message code='BzComposer.common.erroroccurred'/></p>
</div>
<div id="selectAnyTemplateDialog" style="display:none;">
	<p><spring:message code='BzComposer.common.selectAtleast1Template'/></p>
</div>
