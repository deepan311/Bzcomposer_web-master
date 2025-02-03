<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/include/header.jsp"%>
<title>${sessionScope.user} - <spring:message code="BzComposer.reportcenter.listing.customerphonelist"/></title>
<style>
table.tabla-customListOds { width: 90%; border: 1px solid rgb(207, 207, 207); margin: 5px; }
table.tabla-customListOds tbody tr.odd td { background: #e1e5e9; }
table.tabla-customListOds thead tr th { font-size: 14px; }
table.tabla-customListOds tbody tr td { font-size: 14px; }
</style>
</head>
<body>
<form:form action="Customer?tabid=CustomerPhoneList" method="post" modelAttribute="customerDto">
	<div class="report-form-headerpanel" id="headerPanel">
		<table>
		   <tr>
		   <%-- 	<td><input type="button" value='<spring:message code="BzComposer.Report.btn.ModifyReport"/>' class="formbutton mar"></td> --%>
		   <%-- 	<td><input type="button" value='<spring:message code="BzComposer.Report.btn.Print"/>' class="formbutton mar" onclick="printPage()"></td> --%>
		   	<td><input type="button" value='<spring:message code="BzComposer.reportcenter.allinvoicelist.emailbtn"/>' class="formbutton mar" onclick="sendMail()" id="email"></td>
		   	<td><input id="btnHeader1" type="button" value='<spring:message code="BzComposer.reportcenter.allinvoicelist.hideheaderbtn"/>' class="formbutton mar" onclick="hideShowHeader()"></td>
		   	<td><input type="button" value='<spring:message code="BzComposer.reportcenter.allinvoicelist.refreshbtn"/>' class="formbutton mar" onclick="search()"></td>
		   </tr>
		</table>
	</div>
	
	<div id="ddcolortabsline">&nbsp;</div>
	
		<%-- <span
		style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		
		<spring:message code="BzComposer.Report.CustomerPhoneList" /></span> --%>
		
	<div id="printContent">
		<div id="headerBar">
			<h5 style="text-align: center; color: blue; padding-top: 20px">${sessionScope.user}</h5>
			<h6 style="text-align: center; color: blue;" id="headerBarValue">
				<spring:message code="BzComposer.reportcenter.listing.customerphonelist" />
			</h6>
		</div>
	
		
	<div id="table-negotiations" style="overflow: auto; height: 500; text-align: center;">
	<table id="exportPd" class="tabla-customListOds" border="1">

		<thead>
			<tr>
			<th><spring:message code="BzComposer.configuration.tab.customer" /></th>
			<th><spring:message code="BzComposer.global.phone" /></th>
			</tr>
		</thead>
		<tbody>
            <c:if test="${not empty customerphonelist}">
                <input type="hidden" name="sListSize" id="lSize" value='${customerphonelist.size()}'>
                <c:forEach items="${customerphonelist}" var="customer">
                    <tr >
                        <td>${customer.cname}</td>
                        <td>${customer.phone}</td>
                        <%-- <td>${customer.lastName}</td>
                        <td>${customer.phone}</td>
                        <td>${customer.fax}</td>
                        <td>${customer.email}</td>
                        <td>${customer.address1}</td>
                        <td>${customer.address2}</td>
                        <td>${customer.city}</td>
                        <td>${customer.stateName}</td>
                        <td>${customer.province}</td>
                        <td>${customer.country}</td>
                        <td>${customer.zipCode}</td>
                        <td>${customer.memo}</td>	 --%>
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
	document.forms[0].action = "Customer?tabid=CustomerPhoneList";
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