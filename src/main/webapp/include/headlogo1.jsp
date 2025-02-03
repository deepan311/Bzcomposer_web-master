<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<style type="text/css">

.horizontal { display: inline; border-left: 1px solid #999;; padding-left: 0.311em; }
.first { border-left: none; padding-left: 0; }

</style>
<script type="text/javascript">
function logout(){
	window.location = "./Logout";
}
</script>
</head>
<body>
<div style="width:100%" class="header-section">
	<div class="bzclogo" style="float:left;">
		<a href="/Invoice?tabid=Invoice">
        	<img src="images/bzcweb.jpg" width="289" height="75"  style=" margin-left: 30; alt="bzcomposer"/>
       	</a>
 	</div>
 <%-- <div style="float:right;padding-right: 51px;padding-top: 10px;">
 <div  style="color: #05A9C5;font-weight: bold;">
  Welcome, <%=  (String)session.getAttribute("username") %>
<div class="horizontal">
	<button title="logout" class="formbutton" onclick="logout();">
		<spring:message code="BzComposer.Logout" />
	</button>
</div>
 <div class="clear"></div>
 </div>
 </div> --%>
 </div>
</body>
</html>