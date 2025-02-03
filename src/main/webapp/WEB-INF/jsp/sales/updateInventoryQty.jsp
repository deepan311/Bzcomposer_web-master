<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
	<script>
		/* self.moveTo(130,130); */
		window.moveTo((screen.width - 800) / 2, (screen.height - 600) / 2);
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<%@include file="/include/header.jsp"%>

	<title><spring:message code="BzComposer.updateitemtitle" /></title>
	<link href="/WEB-INF/jsp/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
	<script src="/WEB-INF/jsp/tableStyle/tab/jquery-ui.js"></script>
</head>
<body onload="init();">
<script type="text/javascript">
	$(function() {

	});
</script>
<form:form method="post" modelAttribute="ItemDetails" >
	<div id="cos">
		<div class="statusquo ok">
			<div id="hoja">
				<div id="blanquito">
					<div id="padding">
						<div>
			<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
				<spring:message code="BzComposer.global.edit" /> <spring:message code="BzComposer.adjustinventory.adjustinventorytitle" />
			</span>
						</div>
						<!-- begin Contents -->
						<!-- add the code for tab here -->
						<div>
							<c:if test="${not empty SaveStatus}">
                                <span class="msgstyle">${SaveStatus}</span>
							</c:if>
						</div>
						<div>
							<div id="tabs" style="height:250px;width: 800px;">
								<div id="General-1">
									<div id="content1" class="tabPage">
										<!-- add here the content of first tab -->
										<div id="table-negotiations">
											<table class="tabla-listados" cellspacing="0">
												<thead>
												<tr>
													<th colspan="4" style="font-size:14px;">
														<spring:message code="BizComposer.Estimaion.Header.ItemInfo"/>
													</th>
												</tr>
												</thead>
												<tbody>
												<tr>
													<td><spring:message code="BzComposer.receiveditem.itemcode" /></td>
													<td><form:input type="text" path="itemCode" disabled="true" /></td>
													<td colspan="2">&nbsp;</td>
												</tr>
												<tr>
                                                    <td><spring:message code="BzComposer.receiveditem.itemname" /></td>
                                                    <td colspan="3"><form:input type="text" path="itemName" style="width:100%;" disabled="true" /></td>
                                                </tr>
												<tr>
													<td><spring:message code="BzComposer.Item.Date" /></td>
													<td><form:input type="text" path="dateAdded" disabled="true" /></td>
													<td><spring:message code="BzComposer.Item.checkedDate" /></td>
													<td><form:input type="text" path="dateReceived" disabled="true" /></td>
												</tr>
												<tr>
													<td><spring:message code="BzComposer.Item.CurrentQty" /></td>
													<td><form:input type="number" path="qty" id="orderQty2" /></td>
													<td><spring:message code="BzComposer.receiveditem.difference" /></td>
													<td><input type="text" value="0" disabled="true" /></td>
												</tr>
												<tr>
													<td><spring:message code="BzComposer.global.memo" />:</td>
													<td><form:textarea path="memo" cols="20" /></td>
													<td colspan="2"></td>
												</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<table cellpadding="0" cellspacing="0" border="0" align=center  style="width: 100%;">
								<td align="center">
									<button type="button" class="formbutton" onclick="updateItemData();"><spring:message code='BzComposer.global.update'/></button>
									<button type="button" class="formbutton" onclick="closeme();"><spring:message code='BzComposer.global.close'/></button>
									<input type="hidden" name="stname" value="" id="stateId">
								</td>
							</table>
							<div>
							    <form:hidden path="inventoryId" id="inventoryId" />
							    <input type="hidden" name="tabid" id="tabid" />
                            </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
<div style="height:500px;width: 1300px;padding-top:450px;">
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</div>
</body>
</html>
<script>
function closeme(){
    this.window.close();
    window.opener.location.reload();
}

function updateItemData()
{
    if($("#orderQty2").val()==""){
        alert("<bean:message key='BzComposer.common.enterValidQty'/>");
    }
    else{
        $("#updatePBDialog").dialog({
            resizable: false,
            height: 200,
            width: 500,
            modal: true,
            buttons: {
                "<spring:message code='BzComposer.global.ok'/>": function () {
                    let inventoryId = $("#inventoryId").val();
                    let orderQty2 = $("#orderQty2").val();
                    let memo = $("#memo").val();
                    document.getElementById('tabid').value="UpdateInventory";
                    document.forms[0].action="Item?tabid=UpdateInventory&InvId="+inventoryId+"&qty="+orderQty2+"&memo="+memo;
                    document.forms[0].submit();
                },
                <spring:message code='BzComposer.global.cancel'/>: function () {
                    $(this).dialog("close");
                    return false;
                }
            }
        });
    }
}
</script>
<!-- dialog box used in this page -->
<div id="updatePBDialog" style="display:none;">
    <p><spring:message code="BizComposer.ItemDetails.Update.Validation"/></p>
</div>