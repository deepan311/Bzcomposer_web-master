<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page errorPage="/include/sessionExpired.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.inventorysettingtitle" /></title>
<%-- <link href="tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> --%>

<!-- jQuery Modal -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<%-- <script src="${pageContext.request.contextPath}/dist/js/jquery.min.js"></script>  --%>
<%-- <script src="${pageContext.request.contextPath}/dist/js/bootstrap.min.js"></script> --%>
</head>
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
</script>
<style type="text/css">
.biggerFonts
{
	font-size:1em;
}
.modal1 {
    overflow: visible;
    height: auto;
    vertical-align: top;
}
</style>
</head>
<!-- <body onload="init1();"> -->
<body onload="init();">
<!-- begin shared/header -->
<form:form name="configurationForm" action="Configuration?" method="post" modelAttribute="configDto">
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
					<td id="leftMenu" style="position: relative; width: 180px;">
						<table>
							<tr>
								<td><%@include file="menuPage.jsp" %></td>
							</tr>
						</table>
					</td>
					<td valign="top" style="padding-top: 20px;padding-right: 20px;">
			<!--  Inventory Starts -->
			<div id="inventory" style="display:none;padding: 0; position: relative; left: 0;">
			<table class="table-notifications" width="100%">
				<tr>
					<th style="font-size:12px; padding: 5px;"><spring:message code="BzComposer.configuration.inventorysetting" /></th>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<input type="checkbox" id="showReorderPointWarning2" ${configDto.showReorderPointWarning=='on'?'checked':''} />
						<spring:message code="BzComposer.configuration.showoutofstockwarning"/>
					</td>
				</tr>
				<tr>
					<td style="font-size: 12px;">
						<input type="checkbox" id="productTaxable2" ${configDto.productTaxable=='on'?'checked':''} />
						<spring:message code="BzComposer.configuration.allproductsaretaxable"/>
					</td>
				<tr>
					<td style="font-size:12px;">
						<input type="checkbox" id="reservedQuantity2" value="${configDto.reservedQuantity}" ${configDto.reservedQuantity=='on'?'checked':''} />
						<spring:message code="BzComposer.configuration.quantityreservedforpendingbuilds"/>
					</td>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<input type="checkbox" id="salesOrderQty2" ${configDto.salesOrderQty=='on'?'checked':''} />
						<spring:message code="BzComposer.configuration.quantityonsalesorder"/>
					</td>
				</tr>
			</table>
			</div>
			<!-- Inventory Ends -->
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
		<input type="hidden" name="showReorderPointWarning" id="showReorderPointWarning" value="" />
		<input type="hidden" name="reservedQuantity" id="reservedQuantity" value="" />
		<input type="hidden" name="salesOrderQty" id="salesOrderQty" value="" />
		<input type="hidden" name="productTaxable" id="productTaxable" value="" />
	</div>
	</div>
	<div align="center">
		<input type="Submit" name="Submit" onclick="SaveValues()" style="font-size:1em;;" value="<spring:message code="BzComposer.global.save"/>"/>
		<input type="reset" name=Cancel" onclick="RevokeValues()" style="font-size:1em;" value="<spring:message code="BzComposer.global.cancel"/>"/>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</form:form>

<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
<script type="text/javascript">
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
	            	debugger;
	        		document.getElementById('showReorderPointWarning').value = $('#showReorderPointWarning2').prop("checked")?'on':'off';
	        		document.getElementById('reservedQuantity').value = $("#reservedQuantity2").prop("checked")?'on':'off';
	        		document.getElementById('salesOrderQty').value = $("#salesOrderQty2").prop("checked")?'on':'off';
	        		document.getElementById('productTaxable').value = $("#productTaxable2").prop("checked")?'on':'off';

	        		var formData = $('form').serialize();
	        		$.ajax({
	        			type : "POST",
	        			url : "ConfigurationAjax/SaveConfiguration?tabid=SaveConfigurationInventorySettng",
	        			data : formData,
	        			success : function(data) {
	        				debugger
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
</script>
</html>
 <!-- Dialog box used in this page -->
<div id="showsaverecorddialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.saveconfirm"/></p>
</div>
<div id="showsuccessdialog" style="display:none;">
	<p>Record updated</p>
</div>