<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" language="JavaScript1.2"
	src="tree-menu/apytmenu.js"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="tree-menu/apytmenu_add.js"></script>
	
<%@include file="/include/header.jsp"%>
<link
	href="${pageContext.request.contextPath}/styles/d_print.css"
	media="print" rel="Stylesheet" type="text/css">


<title><bean:message key="BizComposer.PrintInvoice.Title"/></title>
</head>
<body>
<html:form action="Invoice.do?tabid=Invoice" enctype="MULTIPART/FORM-DATA" method="post">

	<div id="cos">

	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
	<table class="tabla-listados" cellspacing="0">
		<tr><td align="center">
				<div class="noPrint"> <input type="button" class="formbutton" value="Print" onClick="window.print()" title="Print invoice"> 
					<input type="button" class="formbutton" value="close" onclick="window.close()"/>
				</div>
			</td>
		</tr>
	</table>
	</div>
	</div>
	</div>
	</div>
	</div>
	<!-- end Contents -->
</html:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>

</body>
<script>

</script>
</html>

