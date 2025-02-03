<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.Dashboard" /></title>
</head>
<body>
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
	<div>
		<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px;border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.Dashboard" />
		</span>
	</div>
	<div>
		<table cellspacing="0"  style="width: 100%;overflow-y:scroll;border:0;" class="section-border">
			<tr>
				<td style="font-size:1.2em;color:#fff;padding-right: 6px; width: 50%;">
				    <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> <spring:message code="BzComposer.Purchase" /> </div>
				</td>
				<td style="font-size:1.2em;color:#fff;width: 50%;font-size:1.2em;color:#fff;width: 50%;padding-left: 6px;">
				    <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> <spring:message code="BzComposer.sales.SalesOrder" /> </div>
				</td>
			</tr>
			<tr>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 6px 0 0;">
						<table class="tabla-listados" cellspacing="0" style="margin:0; border:0;text-decoration: none;">
							<thead>
								<tr style="background-color: #05A9C5;">
									<th style="font-size:12px;">
										<spring:message code="BzComposer.purchase.PONum" />
									</th>
									<th style="font-size:12px;">
										<spring:message code="BzComposer.global.dateadded" />
									</th>
									<th style="font-size:12px;">
										<spring:message code="BzCompoer.report.customercontract.fullname" />
									</th>
									<th style="font-size:12px;">
										<spring:message code="BzComposer.Invoice.Amt" />
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${purchaseDetails}" var="objList">
									<tr>
										<td>${objList.poNum}</td>
										<td>${objList.dateAdded}</td>
										<td>${objList.firstName} ${objList.lastName}</td>
										<td>${objList.total}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</td>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 0 0 6px;">
					<table class="tabla-listados" cellspacing="0" style="margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th style="font-size:12px;"><spring:message code="BzComposer.salesorder.soNum" /></th>
								<th style="font-size:12px;"><spring:message code="BzComposer.global.dateadded" /></th>
								<th style="font-size:12px;"><spring:message code="BzCompoer.report.customercontract.fullname" /></th>
								<th style="font-size:12px;"><spring:message code="BzComposer.Invoice.AdjTotal" /></th>
							</tr>
						</thead>
						<tbody>
						    <c:forEach items="${salesOrderDetails}" var="objList">
                                <tr>
                                    <td>${objList.orderNo}</td>
                                    <td>${objList.dateAdded}</td>
                                    <td>${objList.firstName} ${objList.lastName}</td>
                                    <td>${objList.total}</td>
                                </tr>
                            </c:forEach>
						</tbody>
					</table>
					</div>
				</td>
			</tr>
			<tr>
				<td style="font-size:12px">&nbsp;</td>
				<td></td>
			</tr>
			<tr>
				<td style="font-size:1.2em;color:#fff;padding-right: 6px;">
				    <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> <spring:message code="Bizcomposer.invoice" /></div>
				</td>
				<td style="font-size:1.2em;color:#fff;padding-left: 6px;">
				    <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> <spring:message code="Bizcomposer.estimate" /></div>
				</td>
			</tr>
			<tr>
				<td  style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 6px 0 0;">
					<table class="tabla-listados" cellspacing="0" style="margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th style="font-size:12px;"> <spring:message code="BzComposer.sales.Order" /></th>
								<th style="font-size:12px;"> <spring:message code="BzComposer.global.dateadded" /></th>
								<th style="font-size:12px;"> <spring:message code="BzCompoer.report.customercontract.fullname" /></th>
								<th style="font-size:12px;"> <spring:message code="BzComposer.Invoice.Amt" /></th>
							</tr>
						</thead>
						<tbody>
						    <c:forEach items="${invoiceDetails}" var="objList">
                                <tr>
                                    <td>${objList.orderNo}</td>
                                    <td>${objList.dateAdded}</td>
                                    <td>${objList.firstName} ${objList.lastName}</td>
                                    <td>${objList.total}</td>
                                </tr>
                            </c:forEach>
						</tbody>
					</table>
					</div>
				</td>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 0 0 6px;">
					<table class="tabla-listados" cellspacing="0" style="margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th style="font-size:12px;"> <spring:message code="BzComposer.Estimaion.EstNum" /></th>
								<th style="font-size:12px;"> <spring:message code="BzComposer.global.dateadded" /></th>
								<th style="font-size:12px;"> <spring:message code="BzCompoer.report.customercontract.fullname" /></th>
								<th style="font-size:12px;"> <spring:message code="BzComposer.Invoice.AdjTotal" /></th>
							</tr>
						</thead>
						<tbody>
						    <c:forEach items="${estimateDetails}" var="objList">
                                <tr>
                                    <td>${objList.orderNo}</td>
                                    <td>${objList.dateAdded}</td>
                                    <td>${objList.firstName} ${objList.lastName}</td>
                                    <td>${objList.total}</td>
                                </tr>
                            </c:forEach>
						</tbody>
					</table>
					</div>
				</td>
			</tr>
			<tr>
				<td style="font-size:12px">&nbsp;</td>
				<td></td>
			</tr>
			<tr>
				<td style="font-size:1.2em;color:#fff;padding-right: 6px;">
				    <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> <spring:message code="BzComposer.Item.ItemList" /> </div>
				</td>
				<td style="font-size:1.2em;color:#fff;padding-left:6px">
				    <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> <spring:message code="BzComposer.common.ReorderItemList" /> </div>
				</td>
			</tr>
			<tr>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 6px 0 0;">
					<table class="tabla-listados" cellspacing="0" style="overflow-x:auto;overflow-y:auto;margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th style="font-size:12px;"> <spring:message code="bca.Category" /></th>
								<th style="font-size:12px;"> <spring:message code="Bizcomposer.itemCode" /></th>
								<th style="font-size:12px;"> <spring:message code="BzComposer.item.itemtype" /></th>
								<th style="font-size:12px;"> <spring:message code="BzComposer.Item.ItemTitle" /></th>
								<th style="font-size:12px;"> <spring:message code="Bizcomposer.qty" /></th>
							</tr>
						</thead>
						<tbody>
						    <c:forEach items="${itemListDetails}" var="objList">
                                <tr>
                                    <td>${objList.itemCode}</td>
                                    <td>${objList.itemCode}</td>
                                    <td>${objList.itemType}</td>
                                    <td>${objList.itemName}</td>
                                    <td>${objList.qty}</td>
                                </tr>
                            </c:forEach>
						</tbody>
					</table>
					</div>
				</td>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 0 0 6px;">
					<table class="tabla-listados" cellspacing="0" style="overflow-x:auto;overflow-y:auto;margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th style="font-size:12px;"> <spring:message code="Bizcomposer.itemCode" /></th>
                                <th style="font-size:12px;"> <spring:message code="BzComposer.item.itemtype" /></th>
								<th style="font-size:12px;"> <spring:message code="BzComposer.Item.ItemTitle" /></th>
								<th style="font-size:12px;"> <spring:message code="Bizcomposer.qty" /></th>
							</tr>
						</thead>
						<tbody>
						    <c:forEach items="${purchaseDetails}" var="objList">
                                <tr>
                                    <td>${objList.poNum}</td>
                                    <td>${objList.dateAdded}</td>
                                    <td>${objList.firstName} ${objList.lastName}</td>
                                    <td>${objList.total}</td>
                                </tr>
                            </c:forEach>
						</tbody>
					</table>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
	</div>		
</div>
</div>
</div>
</div>
</div>
</body>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</html>