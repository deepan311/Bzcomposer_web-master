<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ROOT>
<table class="tabla-listados" cellspacing="0">
    <thead>
        <tr>
            <th><spring:message code="BzComposer.UpdateInvoice.NUMBER" /></th>
            <th><spring:message code="BzComposer.UpdateInvoice.DATE" /></th>
            <th><spring:message code="BzComposer.UpdateInvoice.TYPE" /></th>
            <th><spring:message code="BzComposer.invoiceboard.shipped" /></th>
            <th><spring:message code="BzComposer.invoiceboard.isemailed" /></th>
            <th><spring:message code="BzComposer.UpdateInvoice.AMOUNT($)" /></th>
            <th><spring:message code="BzComposer.UpdateInvoice.BALANCE($)" /></th>
            <th><spring:message code="BzComposer.Invoice.Memo" /></th>
        </tr>
    </thead>
    <tbody>
    <c:if test="${not empty LookupDetails}">
        <input type="hidden" name="sListSize" id="lSize" value='${LookupDetails.size()}'>
        <c:forEach items="${LookupDetails}" var="objList" varStatus="loop">
            <tr id='${loop.index}$$'>
                <td>&nbsp;${objList.orderNum}</td>
                <td>&nbsp;${objList.dateAdded}</td>
                <td>&nbsp;${objList.name}</td>
                <td>&nbsp;<input type="checkbox" name="shipped${loop.index}" disabled="true" ${objList.shipped=='1'?'checked':''} /></td>
                <td>&nbsp;<input type="checkbox" name="emailed${loop.index}" disabled="true" ${objList.emailed=='1'?'checked':''} /></td>
                <td>&nbsp;${objList.total}</td>
                <td>&nbsp;${objList.balance}</td>
                <td>&nbsp;${objList.memo}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="4"></td>
            <td align="right"><strong>Total:-</strong> &nbsp;&nbsp;</td>
            <td><strong>&nbsp;${FinalTotal}</strong></td>
            <td><strong>&nbsp;${FinalBalance}</strong></td>
            <td></td>
        </tr>
        </c:if>
    </tbody>
</table>
</ROOT>
