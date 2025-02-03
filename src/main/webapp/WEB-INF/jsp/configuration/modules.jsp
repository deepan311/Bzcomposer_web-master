<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page errorPage="/include/sessionExpired.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/WEB-INF/jsp/include/headlogo.jsp" />
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<jsp:include page="/WEB-INF/jsp/include/menu.jsp" />
<title><spring:message code="BzComposer.modulestitle" /></title>
<script src="${pageContext.request.contextPath}/dist/js/custom.js"></script>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
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
</head>
<body onload="init();" style="min-width: 1366px;">
<!-- begin shared/header -->
<form:form name="configurationForm" enctype="MULTIPART/FORM-DATA" method="post" id="frmcust" modelAttribute="configDto">
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
					<td id="leftMenu" style="position: relative; width: 180px;">
						<table>
							<tr><td><jsp:include page="menuPage.jsp" /></td></tr>
						</table>
					</td>
					<td valign="top" style="padding-right: 20px; padding-bottom: 20px;">
					    <div id="moduleMenu" style="display:none;padding: 0; position: relative; left:0;">
					    <div id="tabs" style="height: auto;">
					        <ul>
                                <li style="font-size: 12px;"><a href="#formCustomizationTab"><spring:message code="BzComposer.customization" /></a></li>
                                <li style="font-size: 12px;"><a href="#moduleUploadTab"><spring:message code="menu.file.Module_Upload" /></a></li>
                                <li style="font-size: 12px;"><a href="#moduleDownloadTab"><spring:message code="menu.file.Module_Download"/></a></li>
                            </ul>
						<!-- Module-Upload Starts -->
                            <div id="formCustomizationTab" class="tabPage">
                                <table class="table-notifications" width="100%">
                                <tr>
                                    <th colspan="3" align="left" style="font-size:12px; padding:5px;">
                                        <spring:message code="BzComposer.configuration.templatecustomization" />
                                    </th>
                                </tr>
                                <!-- <tr>
                                    <td style="font-size:12px;">
                                        <spring:message code="BzComposer.configuration.selecttype"/>:
                                    </td>
                                    <td colspan="2" style="font-size:12px;">
                                        <select id="templateCust">
                                            <option value="0"><spring:message code="BzComposer.configuration.defaultstartingpage.invoice"/></option>
                                            <option value="1"><spring:message code="BzComposer.configuration.salesorder"/></option>
                                            <option value="2"><spring:message code="BzComposer.configuration.bills"/></option>
                                            <option value="3"><spring:message code="BzComposer.configuration.packingslips"/></option>
                                        </select>
                                    </td>
                                </tr> -->
                                <tr>
                                    <td style="font-size:12px;">
                                        <spring:message code="BzComposer.configuration.active"/>
                                    </td>
                                    <td>&nbsp;&nbsp;
                                    </td>
                                    <td style="font-size:12px;">
                                        <spring:message code="BzComposer.configuration.notactive"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width:60px;font-size:12px;">
                                        <select id="ActiveInvoiceStyleId" name="InvoiceStyleId" style="display:block; width: 200px; height: 200px;" multiple="multiple">
                                            <c:if test="${not empty configDto.listOfExistingInvoiceStyle}">
                                                <c:forEach items="${configDto.listOfExistingInvoiceStyle}" var="objList1">
                                                    <option value="${objList1.invoiceStyleId}">${objList1.invoiceStyle}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                        <c:if test="${not empty configDto.listOfExistingInvoiceStyle}">
                                            <input type="hidden" name="invoiceStyleId" id="invoiceStyleId" value="${configDto.listOfExistingInvoiceStyle[configDto.listOfExistingInvoiceStyle.size()-1].invoiceStyle}">
                                        </c:if>
                                        <c:if test="${empty configDto.listOfExistingInvoiceStyle}">
                                            <input type="hidden" name="invoiceStyleId" id="invoiceStyleId" value="">
                                        </c:if>
                                    </td>

                                    <td align="center" style="font-size:12px;width: 100px;">
                                        <br><br><br><br>
                                        <input type="button" id="addL2R" class="formButton" name="addL2R" onclick="setdeactivate()"  value="<spring:message code="BzComposer.configuration.lefttorightbtn"/>" style="width: 40px;">
                                        <br><br>
                                        <input type="button" id="addR2L" style="width:40px;" class="formButton" name="addR2L" onclick="setactivate()"  value="<spring:message code="BzComposer.configuration.righttoleftbtn"/>">
                                    </td>
                                    <td style="font-size:12px;">
                                    <select id="InvoiceStyleId1" name="InvoiceStyleId1" style="display:block; width: 200px; height: 200px;" multiple="multiple">
                                        <c:if test="${not empty configDto.listOfExistingInvoiceStyle1}">
                                            <c:forEach items="${configDto.listOfExistingInvoiceStyle1}" var="objList1">
                                                <option value="${objList1.invoiceStyleId1}" onclick="setDescription()">${objList1.invoiceStyle1}</option>
                                            </c:forEach>
                                        </c:if>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td style="width: 50px;">
                                        <button type="button" id="Add" style="width: 40px;font-size:14px;" name="Add" class="formButton">
                                            <spring:message code="BzComposer.global.add"/>
                                        </button>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                    <table class="table-notifications" >
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
											<td style="width: 60px;">&nbsp;&nbsp;</td>
											<td style="font-size: 12px;">
												<b>
													<spring:message code="BzComposer.configuration.features.selectedmodules" />
												</b>
											</td>
										</tr>
										<tr>
											<td style="font-size: 12px; width: 250px;">
												<form:select path="selectedModuleId" id="selectedModuleId" style="width: 200px; height: 200px;font-size: 1em;" class="featureName1" multiple="true">
												    <c:if test="${not empty configDto.listOfExistingModules}">
                                                    	<c:forEach items="${configDto.listOfExistingModules}" var="objList1">
                                                    		<option value="${objList1.selectedModuleId}">${objList1.featureName}</option>
                                                    	</c:forEach>
                                                    </c:if>
												</form:select>
											</td>
											<td style="font-size: 12px; width: 100px;">
												<br><br>
												<a class="addfeature" style="font-size: 16px;padding-right: 10px; padding-left: 10px;color: #fff; background-color: #05A9C5; ">
													<spring:message code="BzComposer.configuration.lefttorightbtn"/>
												</a>
												<br/><br/>
												<a class="removefeature" style="font-size: 16px;padding-right: 10px; padding-left: 10px;color: #fff; background-color: #05A9C5; ">
													<spring:message code="BzComposer.configuration.righttoleftbtn"/>
												</a>
											</td>
											<td >
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
									</td>
							    <tr>
							</table>
			            </div>
			            <!-- formCustomizationTab Ends -->

			            <!-- moduleUploadTab Starts -->
                        <div id="moduleUploadTab" class="tabPage">
                            <table class="table-notifications" width="100%">
                                <tr>
                                    <th colspan="3" align="left" style="font-size:12px; padding:5px;">
                                        <spring:message code="menu.file.Module_Upload" />
                                    </th>
                                </tr>
                                <tr>
                                    <td colspan="3" style="font-size:18px;padding-left:20px;">
                                        <a href="#" onclick="moduleImport()" style="cursor: pointer;color: #36c;">
                                            <spring:message code="menu.file.Module_Upload" />
                                        </a>
                                    </td>
                                </tr>
                                <tr><td colspan="3">&nbsp;</td></tr>
                            </table>
                        </div>
                        <!-- moduleUploadTab Ends -->

                        <!-- moduleDownloadTab Starts -->
                        <div id="moduleDownloadTab" class="tabPage">
                            <table class="table-notifications" width="100%">
                                <tr>
                                    <th colspan="3" align="left" style="font-size:12px; padding:5px;">
                                        <spring:message code="menu.file.Module_Download" />
                                    </th>
                                </tr>
                                <tr>
                                    <td colspan="3" align="left" style="font-size:12px; padding:5px;">
                                        <strong><spring:message code="menu.file.Module_Download_Shipping" /></strong>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" style="font-size:18px;padding-left:20px;">
                                        <a href="http://www.nextbits.com?modulename=UPS" style="cursor: pointer;color: #36c;" target="_blank">
                                            <spring:message code="menu.file.Module_Download_UPS" />
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" style="font-size:18px;padding-left:20px;">
                                        <a href="http://www.nextbits.com?modulename=USPS" style="cursor: pointer;color: #36c;" target="_blank">
                                            <spring:message code="menu.file.Module_Download_USPS" />
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" style="font-size:18px;padding-left:20px;">
                                        <a href="http://www.nextbits.com?modulename=FEDEX" style="cursor: pointer;color: #36c;" target="_blank">
                                            <spring:message code="menu.file.Module_Download_FEDEX" />
                                        </a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <!-- moduleDownloadTab Ends -->

			        </div>
			        </div>
			</td>
		</tr>
	</table>
	<div>
	    <form:hidden path="empStateID" />
	    <form:hidden path="labelName" />
	    <form:hidden path="fileName" />
	</div>
	<div><input type="hidden" name="tabid" id="tid" value="" />
	</div>
	</div>
	<div>
	<div align="center">
	<input type="button" class="bottomButton formButton" onclick="saveValue();" value="<spring:message code='BzComposer.global.save'/>" />
		<%-- <form:cancel><spring:message code="BzComposer.global.cancel"/></form:cancel> --%>
	<input type="reset" class="bottomButton formButton" name="Cancel" value="<spring:message code='BzComposer.global.cancel'/>"/>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</form:form>
<script type="text/javascript">
function saveValue(){
	debugger;
	var i, x = document.getElementById("ActiveInvoiceStyleId");
    var ActiveInvoiceStyle = "";
    var ActiveInvoiceStylelist = [];
    for (i = 0; i < x.length; i++){
    	ActiveInvoiceStyle =x.options[i].value;
    	ActiveInvoiceStylelist.push(ActiveInvoiceStyle);
    }
    var x1 = document.getElementById("InvoiceStyleId1");
    var DeActiveInvoiceStyle = "";
    var i1;
    var DeActiveInvoiceStylelist = [];
    for (i1 = 0; i1 < x1.length; i1++){
    	DeActiveInvoiceStyle =x1.options[i1].value;
    	DeActiveInvoiceStylelist.push(DeActiveInvoiceStyle);
    }
	var formData = $('frmcust').serialize();
	$.ajax({
		type : "POST",
		url : "ConfigurationAjax/SaveConfiguration?tabid=formCustomization&ActiveInvoiceStylelist="+ActiveInvoiceStylelist+"&DeActiveInvoiceStylelist="+DeActiveInvoiceStylelist,
		data : formData,
		success : function(data) {
			debugger

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
			alert("<bean:message key='BzComposer.common.erroroccurred'/>");
		}
	});
}

function setdeactivate() {
	debugger;
	var sel = document.getElementById("ActiveInvoiceStyleId");
	var selectValue = sel.options[sel.selectedIndex].text;
	var selectValue1 = sel.options[sel.selectedIndex].value;

	var sel1 = document.getElementById('InvoiceStyleId1');
	sel.remove(sel.selectedIndex);

	var opt = document.createElement('option');
	opt.appendChild( document.createTextNode(selectValue) );
	opt.value = selectValue1;
	sel1.appendChild(opt);
}
function setactivate() {
	debugger;
	var sel = document.getElementById("InvoiceStyleId1");
	var selectValue = sel.options[sel.selectedIndex].text;
	var selectValue1 = sel.options[sel.selectedIndex].value;

	var sel1 = document.getElementById('ActiveInvoiceStyleId');
	sel.remove(sel.selectedIndex);

	var opt = document.createElement('option');
	opt.appendChild( document.createTextNode(selectValue) );
	opt.value = selectValue1;
	sel1.appendChild(opt);
}
</script>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
</body>
</html>
<!-- Dialog box used in this page -->
<div id="showsuccessdialog" style="display:none;">
	<p>Record updated</p>
</div>