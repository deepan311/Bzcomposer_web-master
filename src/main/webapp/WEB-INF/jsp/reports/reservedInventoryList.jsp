<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="BzComposer.reservedinventorylisttitle"/></title>
<%@include file="/include/header.jsp"%>
<style>
table.tabla-customListOds { width: 80%; border: 1px solid rgb(207, 207, 207); margin:auto; }
table.tabla-customListOds tbody tr.odd td { background: #e1e5e9; }
table.tabla-customListOds thead tr th { font-size: 14px; }
table.tabla-customListOds tbody tr td { font-size: 14px; }
</style>
</head>
<body>
<!-- begin shared/header -->
<form:form action="SalesOrderBoard?tabid=ReservedInventoryList" method="post">
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
		   		value='<spring:message code="BzComposer.reportcenter.allinvoicelist.hideheaderbtn"/>' 
		   		class="formbutton mar" onclick="hideShowHeader()">
	   		</td>
		   	<td>
		   		<input type="button" value='<spring:message code="BzComposer.reportcenter.allinvoicelist.refreshbtn"/>' 
		   		class="formbutton mar" onclick="search()">
	   		</td>
		</tr>
	</table>
</div>
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos"></div>
<%-- <div><span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
	<spring:message code="BzComposer.Report.ReservedInventoryList" /></span></div> --%>
	<table align="center">
		<tr>
			<td align="center">
				<c:if test="${not empty msg}">
					<font color="red"><b>${msg}</b></font>
				</c:if>
			</td>
		</tr>
	</table>
	<div id="printContent">
		<div id="headerBar">
			<h5 style="text-align: center; color: blue; padding-top: 20px">${sessionScope.user}</h5>
			<h6 style="text-align: center; color: blue;" id="headerBarValue">
				<spring:message code="BzComposer.report.inventorylist.reservedinventorylist" />
			</h6>
		</div>
		<div id="table-negotiations" style="overflow: auto; height: 400; text-align: center;">
			<table class="tabla-customListOds" id="exportPd" border="1">
				<thead>
					<tr>
						<th>
							<spring:message code="BzComposer.report.reservedinventorylist.sonumber" />
						</th>
						<th width="150">
							<spring:message code="BzComposer.report.reservedinventorylist.date" />
						</th>
						<th>
							<spring:message code="BzComposer.report.reservedinventorylist.inventorycode" />
						</th>
						<th>
							<spring:message code="BzComposer.report.reservedinventorylist.productname" />
						</th>
						<th>
							<spring:message code="BzComposer.report.reservedinventorylist.reservedquantity" />
						</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty SalesOrderBoardDetails}">
                        <input type="hidden" name="sListSize" id="lSize" value='${SalesOrderBoardDetails.size()}'>
                        <c:forEach items="${SalesOrderBoardDetails}" var="sale">
                            <tr id='${ndx}$$' ondblclick="sendToInvoice()" onclick="setRowId(${sale.so_no},'${sale.email}','${ndx}$$');">
                                <td align="left">${sale.so_no}</td>
                                <td align="left" width="60">${sale.dateAdded}</td>
                                <td align="left">${sale.inventoryCode}</td>
                                <td align="left">${sale.itemName}</td>
                                <td align="center">${sale.inventoryQty}</td>
                            </tr>
                        </c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</form:form>
<%@ include file="/include/emailModal.jsp"%>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
<!-- Javascript begins here -->
<script type="text/javascript">
var modal = document.getElementById('myModal');
function hideShowHeader()
{
	debugger;
	document.getElementById("headerBar").style.display = "none";
	/* $("#btnHeader1").hide(); */
	document.getElementById("headerBar").style.display = "none";
	$("#btnHeader1").replaceWith("<input id='btnHeader2' type='button' value='<spring:message code='BzComposer.Report.btn.ShowHeader'/>' class='formbutton mar' onclick='ShowHeader()'>");
}
function ShowHeader()
{
	document.getElementById("headerBar").style.display = "block";
	$("#btnHeader2").replaceWith("<input id='btnHeader1' type='button' value='<spring:message code='BzComposer.Report.btn.HideHeader'/>' class='formbutton mar' onclick='hideShowHeader()'>");
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
	document.forms[0].action = "SalesOrderBoard?tabid=ReservedInventoryList";
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
</script>
<!-- Javascript end here -->
</body>
</html>