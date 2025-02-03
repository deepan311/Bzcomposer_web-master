<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<ROOT>
<html:form action="Item.do" method="post" >
	<table class="tabla-listados" cellspacing="0">
		<tr>
			<td><bean:message key="BzComposer.Item.SubtotalName" /></td>
		</tr>
		<tr>
			<td><html:text property="itemCode" /></td>
		</tr>
		<tr>
			<td><bean:message key="BzComposer.Item.Description" /></td>
		</tr>
		<tr>
			<td><html:text property="itemName" /></td>
		</tr>
	</table>
</html:form>
</ROOT>
