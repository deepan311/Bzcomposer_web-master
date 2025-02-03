<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/include/header.jsp"%>
<title>${sessionScope.user} -<spring:message code="BzComposer.vendorcontactlisttitle"/></title>
<style>
table.tabla-customListOds { width: 80%; border: 1px solid rgb(207, 207, 207); margin:auto; }
table.tabla-customListOds tbody tr.odd td { background: #e1e5e9; }
table.tabla-customListOds thead tr th { font-size: 14px; }
table.tabla-customListOds tbody tr td { font-size: 14px; }
</style>
</head>
<body>
<form:form action="PurchaseBoard?tabid=VendorContactList" method="post" modelAttribute="purchaseBoardDto">
	<div class="report-form-headerpanel" id="headerPanel">
		<table>
			<tr>
			<%--<td><input type="button" value='<spring:message code="BzComposer.Report.btn.ModifyReport"/>' class="formbutton mar"></td> --%>
		   	<%--<td><input type="button" value='<spring:message code="BzComposer.Report.btn.Print"/>' class="formbutton mar" onclick="printPage()"></td> --%>
		   		<td>
		   			<input type="button" value='<spring:message code="BzComposer.reportcenter.allinvoicelist.emailbtn"/>' 
		   			class="formbutton mar" onclick="sendMail()" id="email">
	   			</td>
		   		<td>
		   			<input id="btnHeader1" type="button" value='<spring:message code="BzComposer.reportcenter.allinvoicelist.hideheaderbtn"/>' 
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

	<div class="report-form-underheader">
		<table>
			<tr>
		    	<td>
					<label style="padding-right: 10px">
						<spring:message code="BzComposer.reportcenter.allinvoicelist.dates"/>
					</label>
	    		</td>
	    		<td>
					<form:select path="datesCombo">
	    		  		<form:option value="0">
	    		  			<spring:message code="BzComposer.reportcenter.allinvoicelist.dates.all"/>
	    		  		</form:option>
		    		  	<form:option value="1">
		    		  		<spring:message code="BzComposer.reportcenter.allinvoicelist.dates.today"/>
	    		  		</form:option>
		    		  	<form:option value="2">
		    		  		<spring:message code="BzComposer.reportcenter.allinvoicelist.dates.thisweek"/>
	    		  		</form:option>
		    		  	<form:option value="3">
		    		  		<spring:message code="BzComposer.reportcenter.allinvoicelist.dates.thisweektodate"/>
	    		  		</form:option>
		    		  	<form:option value="4">
		    		  		<spring:message code="BzComposer.reportcenter.allinvoicelist.dates.thismonth"/>
	    		  		</form:option>
		    		  	<form:option value="5">
		    		  		<spring:message code="BzComposer.reportcenter.allinvoicelist.dates.thismonthtodate"/>
	    		  		</form:option>
		    		  	<form:option value="6">
		    		  		<spring:message code="BzComposer.reportcenter.allinvoicelist.dates.fiscalquarter"/>
	    		  		</form:option>
		    		  	<form:option value="7">
		    		  		<spring:message code="BzComposer.reportcenter.allinvoicelist.dates.fiscalquartertodate"/>
	    		  		</form:option>
		    		  	<form:option value="8">
		    		  		<spring:message code="BzComposer.reportcenter.allinvoicelist.dates.custom"/>
	    		  		</form:option>
		    		  	<form:option value="9">
		    		  		<spring:message code="BzComposer.reportcenter.allinvoicelist.dates.last10days"/>
	    		  		</form:option>
		    		  	<form:option value="10">
		    		  		<spring:message code="BzComposer.reportcenter.allinvoicelist.dates.last30days"/>
	    		  		</form:option>
		    		  	<form:option value="11">
		    		  		<spring:message code="BizComposer.amazonBulkMailer.DateSelect.60Days"/>
	    		  		</form:option>
					</form:select>
	    		</td>
	    		<td>
	    			<label style="padding-left: 15px">
	    				<spring:message code="BzComposer.reportcenter.allinvoicelist.from"/>
    				</label>
   				</td>
	    		<td>
	    			<form:input path="fromDate" size="15"/>
    			</td>
	    		<td>
	    			<img src="images/cal.gif" onclick="displayCalendar(document.PurchaseBoardForm.fromDate,'mm-dd-yyyy',this);" style="padding-left: 5px">
    			</td>
	    		<td>
	    			<label style="padding-left: 15px">
	    				<spring:message code="BzComposer.reportcenter.allinvoicelist.to"/>
    				</label>
   				</td>
	    		<td>
	    			<form:input path="toDate" size="15"/>
    			</td>
	    		<td>
	    			<img src="images/cal.gif" onclick="displayCalendar(document.PurchaseBoardForm.toDate,'mm-dd-yyyy',this);" style="padding-left: 5px">
    			</td>
	    		<td>
	    			<label style="padding-right: 10px;padding-left: 15px">
	    				<spring:message code="BzComposer.reportcenter.allinvoicelist.sortby"/>
    				</label>
   				</td>
	    		<td>
					<form:select path="sortBy">
	    		  		<form:option value="0"><spring:message code="BzComposer.Report.labelSortBy.Default"/></form:option>
	    		  		<form:option value="1"><spring:message code="BzComposer.Report.labelSortBy.Type"/></form:option>
	    		  		<form:option value="2"><spring:message code="BzComposer.Report.labelSortBy.#ID"/></form:option>
	    		  		<form:option value="3"><spring:message code="BzComposer.Report.labelSortBy.client/vendor"/></form:option>
	    		  		<form:option value="4"><spring:message code="BzComposer.Report.labelSortBy.memo"/></form:option>
	    		  		<form:option value="4"><spring:message code="BzComposer.Report.labelSortBy.amount"/></form:option>
	    			</form:select>
	    		</td>
	    	    <td>
	    	    	<input type="button" value='<spring:message code="BzComposer.reportcenter.allinvoicelist.searchbtn"/>' 
	    	    	class="formbutton mar" style="margin-left: 50px" onclick="search()">
    	    	</td>
	    	</tr>
		</table>
	</div>
	<div id="headerBar">
        <h5 style="text-align: center;color: blue;padding-top: 20px">${sessionScope.user}</h5>
        <h6 style="text-align: center;color: blue;" id="headerBarValue">
            <spring:message code="BzComposer.report.itempricelist.itempricelist"/>
        </h6>
    </div>
	<div id="table-negotiations">
		<table align="center">
		</table>
		<div id="table-negotiations" style="overflow: auto; height: 500; text-align: center;">
			<table class="tabla-customListOds" id="exportPd" border="1">
				<thead>
					<tr>
						<th class="emblem">
							<spring:message code="BzComposer.report.vendorpurchaselist.companyname" />
						</th>
						<th>
							<spring:message code="BzComposer.global.firstname" />
						</th> 
						<th>
							<spring:message code="BzComposer.global.firstname" />
						</th> 									
						<th>
							<spring:message code="BzComposer.report.vendorcontactlist.billto" />
						</th>													
						<th>
							<spring:message code="BzComposer.global.phone" />
						</th>		
						<th>
							<spring:message code="BzComposer.global.fax" />
						</th>
						<th>
							<spring:message code="BzComposer.global.email" />
						</th>
					</tr>
				</thead>
				<tbody>
				    <c:if test="${not empty VendorDetails}">
                        <input type="hidden" name="sListSize" id="lSize" value='${VendorDetails.size()}'>
                        <c:forEach items="${VendorDetails}" var="vendor">
                            <tr>
                                <td>${vendor.cname}</td>
                                <td>${vendor.firstName}</td>
                                <td>${vendor.lastName}</td>
                                <td>${vendor.phone}</td>
                                <td>${vendor.fax}</td>
                                <td>${vendor.email}</td>
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
	document.forms[0].action = "PurchaseBoard?tabid=VendorContactList";
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