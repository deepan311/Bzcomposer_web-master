<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu_add.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="dist/jsPdf/jspdf.min.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="dist/jsPdf/html2canvas.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>	
<%@include file="/include/header.jsp"%>
<%-- <title><spring:message code="BzComposer.Report.ReservedInventoryList.ItemPriceList" /></title> --%>
</head>
<body>
<div id="content">
<%-- <img alt="" src="${pageContext.request.contextPath}/ChartReports/AccountReceivablePie${sessionScope.userID}.png"> --%>

<div style="text-align: center;">
	<button type="button" class="btn btn-primary" onclick="printPage()" id="printButton">Print</button>
	<!-- <button id="pdfDownloader">generate PDF</button> -->
</div>

<div style="display: flex;justify-content:center;" id="pdf">
  <img alt="" src="${pageContext.request.contextPath}/ChartReports/Account Payable By Month${sessionScope.userID}.png">
</div>
<div style="display: flex;justify-content:center;padding-top: 30px" id="pdf1">  
  <img alt="" src="${pageContext.request.contextPath}/ChartReports/Account Payable${sessionScope.userID}.png">
</div>

<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</div>
</body>
</html>
<script>
 function printPage()
 {
	 printButton.style.visibility='hidden';
	 window.print();
	 printButton.style.visibility='visible';
 }
</script>
