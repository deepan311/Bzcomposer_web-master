<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">

.horizontal { display: inline; border-left: 1px solid #999;; padding-left: 0.3em; }
.first { border-left: none; padding-left: 0; }

</style>

<!-- for opening dialog these files added on 26-09-2019 -->
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />

<script type="text/javascript">
function logout()
{
	window.location = "./Logout";
}
function showLocale()
{
	var lang = document.getElementById("locale").value;
	var locale = "<%= request.getAttribute("selectedLocale")%>";
	
	if(lang == "" && locale != "")
	{
		$('select[id="locale"]').find('option[value="'+locale+'"]').attr("selected",true);
		return showLanguageDialog();
	}
	else
	{
		window.location="./changeLocale?lang="+lang;
	}
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
            "<bean:message key='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
</script>
</head>
<body>
<div style="width:100%" class="header-section" >
<div class="bzclogo" style="float:left;">
<a href="<bean:write name="path" property="pathvalue"/>/Invoice.do?tabid=Invoice">
        <img src = "images/bzcweb.jpg" width="289" height="75"  style="
    margin-left: 30; alt="bzcomposer"/> </a>
</div>
<br>
<div style="float:right;padding-right: 51px;padding-top: 10px;">
<div  style="color: #05A9C5;font-weight: bold;">
 <%-- commented on 12-06-2019 
 user:<%=  (String)session.getAttribute("username") %> --%>
  <bean:message key="BzComposer.welcome"/>, <%=  (String)session.getAttribute("username") %>
<%--  <div class="horizontal">
	<bean:message key="BzComposer.common.companyname"/> <%=  (String)session.getAttribute("user") %>
	</div> --%>
	<!-- <div class="horizontal"> 
		<select name="locale" id="locale" onchange="showLocale();">
			<option value=""><bean:message key="BzComposer.selectlanguage"/></option>
			<option value="en"><bean:message key="BzComposer.selectlanguage.english"/></option>
			<option value="zh"><bean:message key="BzComposer.selectlanguage.chinese"/></option>
			<option value="es"><bean:message key="BzComposer.selectlanguage.spanish"/></option>
		</select>
	</div> -->
	<div class="horizontal">
		<html:button styleId="btnLogout" property="Logout" title="logout" styleClass="formbutton" onclick="logout();">
			<bean:message key="BzComposer.Logout" />
		</html:button>
	</div>
	<input type="hidden" id="request_locale" name="request_locale" value=""/>
 <div class="clear"></div>
 </div>
 </div>
 </div>
 
</body>
</html>
<!-- dialog box that used in this page -->
<div id="showLanguageDialog" style="display:none;">
	<p><bean:message key="BzComposer.common.selectlanguagetochange"/></p>
</div>