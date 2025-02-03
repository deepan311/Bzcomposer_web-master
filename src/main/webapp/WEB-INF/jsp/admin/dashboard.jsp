<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menuAdmin.jsp"%>
<title><spring:message code="BzComposer.Dashboard" /></title>
<style>
table.tabla-listados { width: 100%; border: 1px solid rgb(207, 207, 207); margin: 0px 0px 0px 0px; }
table.tabla-listados tbody tr.odd td { background: #e1e5e9; }
table.tabla-listados thead tr th { font-size: 14px; }
table.tabla-listados tbody tr td { font-size: 12px; }
</style>
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
				    <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> Visitors </div>
				</td>
				<td style="font-size:1.2em;color:#fff;width: 50%;font-size:1.2em;color:#fff;width: 50%;padding-left: 6px;">
				    <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> Customers </div>
				</td>
			</tr>
			<tr>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 6px 0 0;">
						<table class="tabla-listados" cellspacing="0" style="margin:0; border:0;text-decoration: none;">
							<thead>
								<tr style="background-color: #05A9C5;">
								    <th>ID</th>
									<th>Business Information</th>
									<th>Address</th>
									<th>Date</th>
									<th>Message</th>
								</tr>
							</thead>
							<tbody>
                                <tr>
                                    <td>1</td><td>ABC</td><td>123 Washington, 123456, USA</td><td>10-25-20</td><td>Test</td>
                                </tr>
                                <tr>
                                    <td>2</td><td>ABC</td><td>123 Washington, 123456, USA</td><td>10-25-20</td><td>Test</td>
                                </tr>
                                <tr>
                                    <td>3</td><td>ABC</td><td>123 Washington, 123456, USA</td><td>10-25-20</td><td>Test</td>
                                </tr>
							</tbody>
						</table>
					</div>
				</td>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 0 0 6px;">
					<table class="tabla-listados" cellspacing="0" style="margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th>ID</th>
                                <th>Name</th>
                                <th>Company</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Date</th>
							</tr>
						</thead>
						<tbody>
						    <tr>
                                <td>1</td><td>Dale Fine</td><td>Western Graphics</td><td>abc@gmail.com</td><td>+1234567890</td><td>09-16-2021</td>
                            </tr>
                            <tr>
                                <td>2</td><td>Dale Fine</td><td>Western Graphics</td><td>abc@gmail.com</td><td>+1234567890</td><td>09-16-2021</td>
                            </tr>
                            <tr>
                                <td>3</td><td>Dale Fine</td><td>Western Graphics</td><td>abc@gmail.com</td><td>+1234567890</td><td>09-16-2021</td>
                            </tr>
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
				    <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> Business Registrations </div>
				</td>
				<td style="font-size:1.2em;color:#fff;padding-left: 6px;">
				    <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> Business Info Changed </div>
				</td>
			</tr>
			<tr>
				<td  style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 6px 0 0;">
					<table class="tabla-listados" cellspacing="0" style="margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th>ID</th>
                                <th>Business Information</th>
                                <th>Address</th>
                                <th>Date</th>
                                <th>Message</th>
							</tr>
						</thead>
						<tbody>
						    <tr>
                                <td>1</td><td>ABC</td><td>123 Washington, 123456, USA</td><td>10-25-20</td><td>Test</td>
                            </tr>
                            <tr>
                                <td>2</td><td>ABC</td><td>123 Washington, 123456, USA</td><td>10-25-20</td><td>Test</td>
                            </tr>
                            <tr>
                                <td>3</td><td>ABC</td><td>123 Washington, 123456, USA</td><td>10-25-20</td><td>Test</td>
                            </tr>
						</tbody>
					</table>
					</div>
				</td>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 0 0 6px;">
					<table class="tabla-listados" cellspacing="0" style="margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th>ID</th>
                                <th>Name</th>
                                <th>Company</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Date</th>
							</tr>
						</thead>
						<tbody>
						    <tr>
                                <td>1</td><td>Maria Wedge</td><td>CDI Distributor</td><td>abc@gmail.com</td><td>+1234567890</td><td>09-16-2021</td>
                            </tr>
                            <tr>
                                <td>2</td><td>Maria Wedge</td><td>CDI Distributor</td><td>abc@gmail.com</td><td>+1234567890</td><td>09-16-2021</td>
                            </tr>
                            <tr>
                                <td>3</td><td>Maria Wedge</td><td>CDI Distributor</td><td>abc@gmail.com</td><td>+1234567890</td><td>09-16-2021</td>
                            </tr>
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
				    <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> Payments </div>
				</td>
				<td style="font-size:1.2em;color:#fff;padding-left:6px">
				    <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> Messages </div>
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