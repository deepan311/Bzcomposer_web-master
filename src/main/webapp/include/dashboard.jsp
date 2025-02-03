<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/include/headlogo.jsp"%>
<%@include file="/include/header.jsp"%>
<%@include file="/include/menu.jsp"%>
<title>Dashboard</title>
</head>
<body>
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
	<div>
		<span style="font-size: 16px; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; 
		border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			Dashboard
		</span>
	</div>
	<div>
		<table cellspacing="0"  style="width: 100%;overflow-y:scroll;border:0;" class="section-border">
			<tr>
				<td style="font-size:1.2em;color:#fff;padding-right: 6px; width: 50%;"> <div style="width: 100%;background-color: #05A9C5;padding: 5px;">Purchase</div> </td>
				<td style="font-size:1.2em;color:#fff;width: 50%;font-size:1.2em;color:#fff;width: 50%;padding-left: 6px;"> <div style="width: 100%;background-color: #05A9C5;padding: 5px;"> Sales Order </div> </td>
			</tr>
			<tr>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 6px 0 0;">
						<table class="tabla-listados" cellspacing="0" style="margin:0; border:0;text-decoration: none;">
							<thead>
								<tr style="background-color: #05A9C5;">
									<th style="font-size:12px;">
										PONum
									</th>
									<th style="font-size:12px;">
										Date Added
									</th>
									<th style="font-size:12px;">
										Full Name
									</th>
									<th style="font-size:12px;">
										Amount
									</th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="purchaseDetails" id="objList">
									<tr>
										<td>
											<bean:write name="objList" property="poNum"/>
										</td>
										<td>
											<bean:write name="objList" property="dateAdded"/>
										</td>
										<td>
											<bean:write name="objList" property="firstName"/> <bean:write name="objList" property="lastName"/>
										</td>
										<td>
											<bean:write name="objList" property="total"/>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</td>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 0 0 6px;">
					<table class="tabla-listados" cellspacing="0" style="margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th style="font-size:12px;">
									SONum
								</th>
								<th style="font-size:12px;">
									Date Added
								</th>
								<th style="font-size:12px;">
									Full Name
								</th>
								<th style="font-size:12px;">
									Adjust Total
								</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="salesOrderDetails" id="objList1">
								<tr>
									<td>
										<bean:write name="objList1" property="orderNo"/>
									</td>
									<td>
										<bean:write name="objList1" property="dateAdded"/>
									</td>
									<td>
										<bean:write name="objList1" property="firstName"/> <bean:write name="objList1" property="lastName"/>
									</td>
									<td>
										<bean:write name="objList1" property="total"/>
									</td>
								</tr>
							</logic:iterate>
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
				<td style="font-size:1.2em;color:#fff;padding-right: 6px;"> <div style="width: 100%;background-color: #05A9C5;padding: 5px;">Invoice </div> </td>
				<td style="font-size:1.2em;color:#fff;padding-left: 6px;"> <div style="width: 100%;background-color: #05A9C5;padding: 5px;">Estimate </div> </td>
			</tr>
			<tr>
				<td  style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 6px 0 0;">
					<table class="tabla-listados" cellspacing="0" style="margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th style="font-size:12px;">
									OrderNum
								</th>
								<th style="font-size:12px;">
									Date Added
								</th>
								<th style="font-size:12px;">
									Full Name
								</th>
								<th style="font-size:12px;">
									Amount
								</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="invoiceDetails" id="objList">
								<tr>
									<td>
										<bean:write name="objList" property="orderNo"/>
									</td>
									<td>
										<bean:write name="objList" property="dateAdded"/>
									</td>
									<td>
										<bean:write name="objList" property="firstName"/> <bean:write name="objList" property="lastName"/>
									</td>
									<td>
										<bean:write name="objList" property="total"/>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
				</td>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 0 0 6px;">
					<table class="tabla-listados" cellspacing="0" style="margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th style="font-size:12px;">
									EstNum
								</th>
								<th style="font-size:12px;">
									Date Added
								</th>
								<th style="font-size:12px;">
									Full Name
								</th>
								<th style="font-size:12px;">
									Adjusted Total	
								</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="estimateDetails" id="objList">
								<tr>
									<td>
										<bean:write name="objList" property="orderNo"/>
									</td>
									<td>
										<bean:write name="objList" property="dateAdded"/>
									</td>
									<td>
										<bean:write name="objList" property="firstName"/> <bean:write name="objList" property="lastName"/>
									</td>
									<td>
										<bean:write name="objList" property="total"/>
									</td>
								</tr>
							</logic:iterate>
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
				<td style="font-size:1.2em;color:#fff;padding-right: 6px;"> <div style="width: 100%;background-color: #05A9C5;padding: 5px;">Item List </div> </td>
				<td style="font-size:1.2em;color:#fff;padding-left:6px"> <div style="width: 100%;background-color: #05A9C5;padding: 5px;">Reorder Item List </div> </td>
			</tr>
			<tr>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 6px 0 0;">
					<table class="tabla-listados" cellspacing="0" style="overflow-x:auto;overflow-y:auto;margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th style="font-size:12px;">
									Category
								</th>
								<th style="font-size:12px;">
									Item Code 
								</th>
								<th style="font-size:12px;">
									Item Type
								</th>
								<th style="font-size:12px;">
									Item Title
								</th>
								<th style="font-size:12px;">
									Qty
								</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="itemListDetails" id="objList">
								<tr>
									<td>
										<bean:write name="objList" property="itemCode"/>
									</td>
									<td>
										<bean:write name="objList" property="itemCode"/>
									</td>
									<td>
										<bean:write name="objList" property="itemType"/> 
									</td>
									<td>
										<bean:write name="objList" property="itemName"/>
									</td>
									<td>
										<bean:write name="objList" property="qty"/>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
				</td>
				<td style="border:0;vertical-align: top;">
					<div style="height:200px;overflow-y: scroll;border:1px solid #ccc;margin: 0 0 0 6px;">
					<table class="tabla-listados" cellspacing="0" style="overflow-x:auto;overflow-y:auto;margin:0; border:0;text-decoration: none;">
						<thead>
							<tr>
								<th style="font-size:12px;">
									Item Code
								</th>
								<th style="font-size:12px;">
									Item Type
								</th>
								<th style="font-size:12px;">
									Item Title
								</th>
								<th style="font-size:12px;">
									Qty
								</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="purchaseDetails" id="objList">
								<tr>
									<td>
										<bean:write name="objList" property="poNum"/>
									</td>
									<td>
										<bean:write name="objList" property="dateAdded"/>
									</td>
									<td>
										<bean:write name="objList" property="firstName"/> <bean:write name="objList" property="lastName"/>
									</td>
									<td>
										<bean:write name="objList" property="total"/>
									</td>
								</tr>
							</logic:iterate>
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
<%@ include file="/include/footer.jsp"%>
</html>