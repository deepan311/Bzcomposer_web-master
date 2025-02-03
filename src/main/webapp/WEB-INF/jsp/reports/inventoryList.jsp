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
<style>
table.tabla-customListOds { width: 80%; border: 1px solid rgb(207, 207, 207); margin:auto; }
table.tabla-customListOds tbody tr.odd td { background: #e1e5e9; }
table.tabla-customListOds thead tr th { font-size: 14px; }
table.tabla-customListOds tbody tr td { font-size: 14px; }
</style>
</head>
<body>
<form:form action="Item" method="post">
	<div class="report-form-headerpanel" id="headerPanel">
		<table>
			<tr>
		   		<%--<td>
		   				<input type="button" value='<spring:message code="BzComposer.Report.btn.ModifyReport"/>' 
		   				class="formbutton mar">
	   				</td> --%>
		   		<%--<td>
		   				<input type="button" value='<spring:message code="BzComposer.Report.btn.Print"/>' 
		   				class="formbutton mar" onclick="printPage()">
	   				</td> --%>
		   			<td>
		   				<input type="button" value='<spring:message code="BzComposer.reportcenter.allinvoicelist.emailbtn"/>' 
		   				class="formbutton mar" onclick="sendMail()" id="email">
	   				</td>
		   			<td>
		   				<input id="btnHeader1" type="button" 
		   				value='<spring:message code="BzComposer.reportcenter.allinvoicelist.hideheaderbtn"/>' class="formbutton mar" 
		   				onclick="hideShowHeader()">
	   				</td>
		   			<td>
		   				<input type="button" value='<spring:message code="BzComposer.reportcenter.allinvoicelist.refreshbtn"/>' 
		   				class="formbutton mar" onclick="callRefresh()">
	   				</td>
	   				<td>
                        <input type="button" value='<spring:message code="BzComposer.global.PDFPrinting"/>'
                            class="formbutton mar" onclick="printInventoryList()" />
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
							<th class="emblem" style="padding-right:100px;"><spring:message code="BzComposer.report.inventorylist.itemname" /></th>
							<th class="emblem" style="padding-left:150px;padding-right:150px;"><spring:message code="BzComposer.report.inventorylist.title" /></th>
							<th class="emblem"><spring:message code="BzComposer.report.inventorylist.quantity" /></th>
							<th class="emblem"><spring:message code="BzComposer.report.inventorylist.purchaseprice" /></th>
							<th class="emblem"><spring:message code="BzComposer.report.inventorylist.saleprice" /></th>
							<th class="emblem"><spring:message code="BzComposer.report.inventorylist.serialnumber" /></th>
							<th class="emblem"><spring:message code="BzComposer.report.inventorylist.weight" /></th>
							<th class="emblem"><spring:message code="BzComposer.report.inventorylist.location" /></th>
							<th class="emblem"><spring:message code="BzComposer.report.inventorylist.taxable" /></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty ItemDetails}">
                            <input type="hidden" name="listSize" id="lSize" value='${ItemDetails.size()}'>
                            <c:forEach items="${ItemDetails}" var="item">
                                <tr>
                                    <td>${item.putcharacter}${item.itemCode}</td>
                                    <td>${item.itemName}</td>
                                    <td>&nbsp;${item.qty}</td>
                                    <td>&nbsp;${item.purchasePrice}</td>
                                    <td>&nbsp;${item.salePrice}</td>
                                    <td>&nbsp;${item.serialNum}</td>
                                    <td>&nbsp;${item.weight}</td>
                                    <td>&nbsp;${item.location}</td>
                                    <td>&nbsp;${item.taxable}</td>
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
function hideShowHeader()
{
	debugger;
	document.getElementById("headerBar").style.display = "none";
	/* $("#btnHeader1").hide(); */
	document.getElementById("headerBar").style.display = "none";
	$("#btnHeader1").replaceWith("<input id='btnHeader2' type='button' value='<spring:message code='BzComposer.reportcenter.allinvoicelist.showheaderbtn'/>' class='formbutton mar' onclick='ShowHeader()'>");
}
function ShowHeader()
{
	document.getElementById("headerBar").style.display = "block";
	$("#btnHeader2").replaceWith("<input id='btnHeader1' type='button' value='<spring:message code='BzComposer.reportcenter.allinvoicelist.hideheaderbtn'/>' class='formbutton mar' onclick='hideShowHeader()'>");
}
function printPage()
{
	/*   debugger;
	  var doc = new jsPDF("1", "pt","a2");  
	  var source = $("#printContent")[0]; 
	  doc.fromHTML(source); 
	  doc.save($("#headerBarValue").html()+".pdf");  */
	  
	  //for creating pdf 
	   var divToPrint=document.getElementById("exportPd");
	   var header = document.getElementById("headerBar");
	   newWin= window.open("");
	   newWin.document.write(header.outerHTML+divToPrint.outerHTML);
	   newWin.print();
	   newWin.close(); 
	 
	   //for creating excel
	   debugger;
	   str="";

  var myTableHead = document.getElementById('ProfitLossItem');
  var rowCount = myTableHead.rows.length;
  var colCount = myTableHead.getElementsByTagName("tr")[0].getElementsByTagName("th").length; 

var ExcelApp = new ActiveXObject("Excel.Application");
var ExcelSheet = new ActiveXObject("Excel.Sheet");
ExcelSheet.Application.Visible = true;

for(var i=0; i<rowCount; i++) 
{   
    for(var j=0; j<colCount; j++) 
    {           
        str= myTableHead.getElementsByTagName("tr")[i].getElementsByTagName("th")[j].innerHTML;
        ExcelSheet.ActiveSheet.Cells(i+1,j+1).Value = str;
    }
}
	/* window.open('data:application/vnd.ms-excel,' + $('#ProfitLossItem').html()); */
}
function search()
{
	document.forms[0].action = "Item?tabid=ProfitLossByItem";
	document.forms[0].submit();
}
function callRefresh(){
	document.forms[0].action = "Item?tabid=InventoryList";
	document.forms[0].submit();
}
function sendMail() {
	modal.style.display = "block";
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
}
function closeModal()
{
	modal.style.display = "none";
}
function printInventoryList(){
    var style = "<style>";
    style = style + "table {width: 100%;font: 12px Calibri;}";
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