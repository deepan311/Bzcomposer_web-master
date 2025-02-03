<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<script>
	self.moveTo(100,100);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu_add.js"></script>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<title><spring:message code="BizComposer.PayHistory.Title"/></title>
</head>
<body>
<form action="Item" enctype="MULTIPART/FORM-DATA" method="post">
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
	<div>
	<table class="tabla-listados" cellspacing="0">
		<c:if test="${not empty CustName}">
			<tr>
				<td align="right"><strong><spring:message code="BzComposer.Invoice.Customer" /> :- </strong></td>
				<td>${CustName}</td>
			</tr>
			<tr>
				<td align="right"><strong><spring:message code="BzComposer.Invoice.Company" /> :- </strong></td>
				<td>${Company}</td>
			</tr>
		</c:if>
        <tr>
			<td colspan="2">
			<div id="table-negotiations"
				style="overflow:auto;height:400;width:100%">
			<table class="tabla-listados" cellspacing="0">
				<thead>
					<tr>
						<th><strong><spring:message code="BzComposer.Invoice.InvoiceNo" /></strong></th>
						<th><strong><spring:message code="BzComposer.Customer.Company" /></strong></th>
						<th><strong><spring:message code="BzComposer.customer.Customer" /></strong></th>
						<th><strong><spring:message code="BzComposer.Invoice.Date" /></strong></th>
						<th><strong><spring:message code="BzComposer.Invoice.Amount" /></strong></th>
						<th><strong><spring:message code="BzComposer.Invoice.Balance" /></strong></th>
						<th><strong><spring:message code="BzComposer.Invoice.Paid" /></strong></th>
						<th><strong><spring:message code="BzComposer.Customer.term" /></strong></th>
						<th><strong><spring:message code="BzComposer.Report.ReservedInventoryList.Category" /></strong></th>
						<th><strong><spring:message code="BzComposer.Invoice.Type" /></strong></th>
						<th><strong><spring:message code="BzComposer.Customer.Memo" /></strong></th>
					</tr>
				</thead>
                <c:if test="${not empty PayHistory}">
                    <c:forEach items="${PayHistory}" var="history">
                        <tr>
                    		<td>${history.orderNo}</td>
                    		<td>${history.companyName}</td>
                    		<td>${history.customer}</td>
                            <td>${history.orderDate}</td>
                            <td>${history.total}</td>
                            <td>${history.balance}</td>
                            <td>${history.paid=='1'?'Yes':'No'}Yes</td>
                            <td>${history.term}</td>
                            <td>${history.category}</td>
                            <td>${history.type}</td>
                            <td>${history.memo}</td>
                        </tr>
                    </c:forEach>
				</c:if>
				<tr></tr>
				<tr></tr>
				<c:if test="${not empty Total}">
					<table>
						<c:forEach items="${Total}" var="list">
							<tr align="center"><td>&nbsp;</td></tr>
							<tr align="center">
								<td align="Center">
								    <strong><spring:message code="BzComposer.Invoice.Total" />
								    <spring:message code="BizComposer.PayHistory.for"/>${list.type}</strong>
								</td>
							</tr>
							<tr align="center">
								<td align="Center"><strong><spring:message code="BzComposer.Invoice.Amount"/> :-</strong></td>
								<td></td>
								<td>${list.total}</td>
						    </tr>
						    <tr align="center">
								<td align="Center"><strong><spring:message code="BzComposer.Invoice.Balance"/> :-</strong></td>
								<td></td>
								<td>${list.balance}</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</table>
			</div>
			</td>
		</tr>
		<tr align="center">
			<td align="center" colspan="2"><input type="button"
				class="formbutton"
				Value='<spring:message code="BzComposer.Invoice.Close"/>'
				onclick="window.close();" title="Close this window" /></td>
		</tr>
	</table>


	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	<!-- end Contents -->
<form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>

</html>

