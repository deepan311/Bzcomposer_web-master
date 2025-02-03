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
<title><spring:message code="BzComposer.devicemanagertitle" /></title>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
	
<!-- jQuery Modal -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

<style type="text/css">
.modal1 {
    overflow: visible;
    height: auto;
    vertical-align: top;
}
</style>
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
	$(function() {
		$("#tabs").tabs();
		$("#tabs1").tabs();
	});
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
	
	function setStoreTypeName()
	{
		alert("<bean:message key='BzComposer.configuration.esales.selectesalesstore'/>");
	}
	function setLeftToRight()
	{

		debugger
		var category = $.trim($("#eBayCategorySelect option:selected").text());
		var categoryId = $.trim($("#eBayCategorySelect option:selected").val());
		debugger
		var optionsAvailable = $('#defaultCategorySelect').find("option[value='"+categoryId+"']").val();
		var isActive = $.trim($('select[id="eBayCategorySelect"]').find('option[id="'+category+'"]').val());
		if(categoryId != optionsAvailable && isActive == "1")
		{
			/*var o = new Option(categoryId,category);
			$(o).html(category);
			$("#defaultCategorySelect").append(o);*/
			$('#defaultCategorySelect')
	         .append($("<option></option>")
	                    .attr("value",categoryId)
	                    .text(category)); 
		}
	}
	
	function setCategoryRightToLeft()
	{
		var value = $.trim($("#defaultCategorySelect option:selected").text());
		$("#r2l").attr('disabled',false);
	}
	
	function setRightToLeft()
	{
		$("#defaultCategorySelect option:selected").remove();
	}
	
	function setDefaultCategory()
	{
		debugger
		var category = $.trim($("#eBayCategorySelect option:selected").text());
		debugger
		var isActive = $.trim($('select[id="eBayCategorySelect"]').find('option[id="'+category+'"]').val());
		debugger

		if(isActive == 0)
		{
			$("#eBayCategorySelect option:selected").attr('readonly',true);
		}
		else
		{
			$("#eBayCategorySelect option:selected").attr('readonly',false);
			$("#l2r").attr('disabled',false);
		}
	}
	
	function showStore()
	{

		window.open("Configuration?tabid=showStore",null,"scrollbars=yes,height=600,width=1300,status=yes,toolbar=no,menubar=no,location=no" );
	}
	
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
	
	function addeBayCategory()
	{

	}
</script>
</head>
<!-- <body onload="init3();"> -->
<body onload="init();">
<!-- begin shared/header -->
<form:form action="Configuration?" enctype="MULTIPART/FORM-DATA" method="post" modelAttribute="configDto">
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos"></div>
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
	</div>
	<div id="table-negotiations"  style="padding: 0; border: 1px solid #ccc;">
		<table cellspacing="0" style="border: 0;width: 100%; overflow-y: scroll;" class="section-border">
			<tr>
			<span style="font-size:30px;cursor:pointer; margin-left: 20px;" onclick="toggleFunction()">&#9776;</span>
				<td id="leftMenu" style="position: relative; width: 180px;">
					<table>
						<tr>
							<td>
								<jsp:include page="menuPage.jsp" />
							</td>
						</tr>
						<%-- <tr align="center">
							<td>
								<input type="button" name="Revoke" class="formButton" onclick="RevokeValues();"
									value='<spring:message code="BizComposer.Configuration.RevokeButton"/>' />
								<input type="button" name="Save" class="formButton" onclick="SaveValues();"
									value='<spring:message code="BizComposer.Configuration.SaveButton"/>' />
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						</tr> --%>
					</table>
				</td>
				<td valign="top">
					<!-- Device Manager Starts -->
					<div id="deviceManager"  style="display:none;padding: 0; position: relative; left: 0;">
						<table class="table-notifications" width="100%">
							<tr>
								<th align="left" colspan="2" style="font-size: 14px; padding: 5px;">
									<spring:message code="BzComposer.configuration.connectionconfiguration" />
								</th>
							</tr>
							<tr>
								<td colspan="2" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.barcodescanner"/></b>
								</td>
							</tr>
							<tr>
								<td style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.portname"/> :
								</td>
								<td style="font-size: 14px;">
									<select id="portName" style="width:160px;">
										<option></option>
									</select>
								</td>
							</tr>
							<tr>
								<td style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.timeout"/> :
								</td>
								<td>
									<input type="text" id="portName" value="30000" style="font-size: 14px;"/>
								</td>
							</tr>
							<tr>
								<td style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.baudrate"/> :
								</td>
								<td>
									<input type="text" id="portName" value="38400" style="font-size: 14px;"/>
								</td>
							</tr>
							<tr>
								<td style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.buffersize"/> :
								</td>
								<td>
									<input type="text" id="bufferSize" value="200" style="font-size: 14px;"/>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="left" style="font-size: 14px; padding: 5px;">
									<b><spring:message code="BzComposer.configuration.barcodescannernote"/></b>
								</td>
							</tr>
							<tr>
								<td align="left" id="clientVendorUsingImportFile" style="display:none;">
									<fieldset><legend></legend>
									<spring:message code="BzComposer.configuration.clientvendorusingimportfile"/>
									<br>
									<spring:message code="BzComposer.configuration.browse"/>:
									<br>
									<input type="file" name="importFile" name="importFile">
									<br>
									<button type="button" id="ok" name="ok">
										<spring:message code="BzComposer.global.ok"/>
									</button>
									
									</fieldset>											
								</td>
								<td align="left" id="checkMoreRecordInDatabase" style="display:none;">
									<fieldset>
									<spring:message code="BzComposer.configuration.checkmorerecordindatabase"/>
									<br>
									<spring:message code="BzComposer.configuration.numberofcheckrecord"/> :
									<input type="text" name="numberOfRecord" name="numberOfRecord">
									<br>
									<button type="button" id="ok" name="ok">
										<spring:message code="BzComposer.global.ok"/>
									</button>
									</fieldset>											
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2" id="bothButton" style="display:none;">
									<button type="button" id="both" name="both">
										<spring:message code="BzComposer.configuration.bothbtn"/>
									</button>
								</td>
							</tr>
						</table>
					</div> 
					<!-- Device Manager Ends -->
				</td>
			</tr>
		</table>
		<div>
			<form:hidden path="empStateID" />
			<form:hidden path="labelName" />
			<form:hidden path="fileName" />
		</div>
		<div>
			<input type="hidden" name="tabid" id="tid" value="" />
		</div>
		</div>
		<div align="center" style="font-size: 14px;">
			<input type="button" value="<spring:message code='BzComposer.global.save'/>" />
			<%-- <form:cancel><spring:message code="BzComposer.global.cancel"/></form:cancel> --%>
			<input type="reset" name="Cancel" style="font-size:1em;" value="<spring:message code="BzComposer.global.cancel"/>"/>
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

</script>
</html>