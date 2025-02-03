<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/include/header.jsp"%>
<title>${sessionScope.user} - <spring:message code="BzComposer.inventorylisttitle"/></title>
</head>
<body>
<form:form action="Item" method="post">
	<div class="report-form-headerpanel" id="headerPanel">
		<table>
			<tr>
                <td>
                    <button type="button" class="formbutton mar" onclick="sendMail()" id="email">
                        <spring:message code="BzComposer.reportcenter.allinvoicelist.emailbtn"/>
                    </button>
                </td>
                <td>
                    <button type="button" id="btnHeader1" class="formbutton mar" onclick="hideShowHeader()">
                        <spring:message code="BzComposer.reportcenter.allinvoicelist.hideheaderbtn"/>
                    </button>
                </td>
                <td>
                    <button type="button" class="formbutton mar" onclick="callRefresh()">
                        <spring:message code="BzComposer.reportcenter.allinvoicelist.refreshbtn"/>
                    </button>
                </td>
                <td>
                    <button type="button" class="formbutton mar" onclick="printInventoryList()">
                        <spring:message code="BzComposer.global.PDFPrinting"/>
                    </button>
                </td>
            </tr>
        </table>
    </div>
    <div id="printContent">
        <div id="headerBar">
            <h5 style="text-align: center;color: blue;padding-top: 20px">${sessionScope.user}</h5>
            <h6 style="text-align: center;color: blue;" id="headerBarValue">
                <spring:message code="BzComposer.report.inventorylist.inventorylisttitle"/>
            </h6>
        </div>
        <div id="table-negotiations" style="overflow: auto; height: 400; text-align: center;">
            <table class="tabla-customListOds" id="exportPd" border="1">
                <thead>
                    <tr>
                        <th class="emblem"><spring:message code="bca.itemcategory"/></th>
                        <th class="emblem" style="padding-right:100px;"><spring:message code="BzComposer.report.inventorylist.itemname" /></th>
                        <th class="emblem" style="padding-left:150px;padding-right:150px;"><spring:message code="BzComposer.report.inventorylist.title" /></th>
                        <th class="emblem"><spring:message code="BzComposer.report.inventorylist.location" /></th>
                        <th class="emblem"><spring:message code="BzComposer.Item.ExpectedQty" /></th>
                        <th class="emblem"><spring:message code="BzComposer.Item.CountedQty" /></th>
                        <th class="emblem"><spring:message code="BzComposer.report.inventorylist.purchaseprice" /></th>
                        <th class="emblem"><spring:message code="BzComposer.report.inventorylist.saleprice" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty AdjustInventoryList}">
                        <input type="hidden" name="listSize" id="lSize" value='${AdjustInventoryList.size()}'>
                        <c:forEach items="${AdjustInventoryList}" var="item">
                            <tr>
                                <td>${item.categoryName}</td>
                                <td>${item.itemCode}</td>
                                <td>${item.itemName}</td>
                                <td>&nbsp;${item.location}</td>
                                <td>&nbsp;${item.expectedQty}</td>
                                <td>&nbsp;${item.qty}</td>
                                <td>&nbsp;${item.purchasePrice}</td>
                                <td>&nbsp;${item.salePrice}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
<%@ include file="/include/emailModal.jsp"%>
<!-- Javascript begins here -->
<script type="text/javascript">
var modal = document.getElementById('myModal');
function hideShowHeader(){
	document.getElementById("headerBar").style.display = "none";
	document.getElementById("headerBar").style.display = "none";
	$("#btnHeader1").replaceWith("<input id='btnHeader2' type='button' value='<spring:message code='BzComposer.reportcenter.allinvoicelist.showheaderbtn'/>' class='formbutton mar' onclick='ShowHeader()'>");
}
function ShowHeader(){
	document.getElementById("headerBar").style.display = "block";
	$("#btnHeader2").replaceWith("<input id='btnHeader1' type='button' value='<spring:message code='BzComposer.reportcenter.allinvoicelist.hideheaderbtn'/>' class='formbutton mar' onclick='hideShowHeader()'>");
}

function callRefresh(){
	window.location = "Item?tabid=AdjustInventoryList";
}
function sendMail() {
	modal.style.display = "block";
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
}
function printInventoryList(){
    var style = "<style>";
    style = style + "table {width: 100%;font: 16px Calibri;}";
    style = style + "table, th, td {border: solid 1px #DDD; border-collapse: collapse;padding: 2px 3px;text-align: center;}";
    style = style + "</style>";
    // CREATE A WINDOW OBJECT.
    var win = window.open('', '', 'height=500,width=700');
    win.document.write('<html><head>');
    win.document.write('<title>Received Item List</title>');
    win.document.write(style);
    win.document.write('</head>');
    win.document.write('<body>');
    win.document.write(document.getElementById("table-negotiations").innerHTML);
    win.document.write('</body></html>');
    win.document.close();
    win.print();
}
</script>
<!-- Javascript end here -->
</body>
</html>