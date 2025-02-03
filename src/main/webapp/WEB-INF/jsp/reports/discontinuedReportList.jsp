<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu_add.js"></script>
<%@include file="/include/header.jsp"%>
<title>${sessionScope.user} -<spring:message code="BzComposer.discountinuedinventorylisttitle" /></title>
<style>
table.tabla-customListOds { width: 80%; border: 1px solid rgb(207, 207, 207); margin:auto; }
table.tabla-customListOds tbody tr.odd td { background: #e1e5e9; }
table.tabla-customListOds thead tr th { font-size: 14px; }
table.tabla-customListOds tbody tr td { font-size: 14px; }
</style>
</head>
<body>
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<div class="clear"></div>
<form:form action="Item?tabid=DiscontinuedList" method="post" modelAttribute="itemDto">
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
	<!--search  -->
	<div class="report-form-underheader">
		<table>
	    	<tr>
	    		<td>
	    		  <label style="padding-right: 10px"><spring:message code="BzComposer.reportcenter.allinvoicelist.dates"/></label>
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
	    			<img src="images/cal.gif"
	    			onclick="displayCalendar(document.ItemForm.fromDate,'mm-dd-yyyy',this);" 
	    			style="padding-left: 5px">
    			</td>
	    		<td>
	    			<label style="padding-left: 15px">
	    				<spring:message code="BzComposer.sales.to"/>
    				</label>
   				</td>
	    		<td>
	    			<form:input path="toDate" size="15"/>
    			</td>
	    		<td>
	    			<img src="images/cal.gif"
	    			onclick="displayCalendar(document.ItemForm.toDate,'mm-dd-yyyy',this);" 
	    			style="padding-left: 5px">
    			</td>
	    		<td>
	    			<label style="padding-right: 10px;padding-left: 15px">
	    				<spring:message code="BzComposer.Report.labelSortBy"/>
    				</label>
   				</td>
	    		<td>
					<form:select path="sortBy">
	    		  		<form:option value="0"><spring:message code="BzComposer.reportcenter.allinvoicelist.sortby.default"/></form:option>
		    		  	<form:option value="1"><spring:message code="BzComposer.reportcenter.allinvoicelist.sortby.type"/></form:option>
		    		  	<form:option value="2"><spring:message code="BzComposer.reportcenter.allinvoicelist.sortby.id"/></form:option>
		    		  	<form:option value="3"><spring:message code="BzComposer.reportcenter.allinvoicelist.sortby.clientorvendor"/></form:option>
		    		  	<form:option value="4"><spring:message code="BzComposer.reportcenter.allinvoicelist.sortby.memo"/></form:option>
		    		  	<form:option value="4"><spring:message code="BzComposer.reportcenter.allinvoicelist.sortby.amount"/></form:option>
	    			</form:select>
    			</td>
	    	    <td>
	    	    	<input type="button" value='<spring:message code="BzComposer.reportcenter.allinvoicelist.searchbtn"/>' 
	    	    	class="formbutton mar" style="margin-left: 50px" onclick="search()">
    	    	</td>
	    	</tr>
		</table>
	</div>
	<!-- search -->
	<div id="headerBar">
        <h5 style="text-align: center;color: blue;padding-top: 20px">${sessionScope.user}</h5>
        <h6 style="text-align: center;color: blue;" id="headerBarValue">
            <spring:message code="BzComposer.report.discountinuedinventorylist.discountinuedinventorylist"/>
        </h6>
    </div>
	<div id="table-negotiations" style="overflow: auto; height: 400; text-align: center;">
		<table class="tabla-customListOds" id="exportPd" border="1">
			<thead>
				<tr>
					<th class="emblem">
						<spring:message code="BzComposer.report.discountinuedinventorylist.category" />
					</th>
					<th class="emblem">
						<spring:message code="BzComposer.report.discountinuedinventorylist.inventoryname" />
					</th>
					<th class="emblem">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</th>
					<th class="emblem">
						<spring:message code="BzComposer.report.discountinuedinventorylist.sku" />
					</th>
					<th class="emblem">
						<spring:message code="BzComposer.report.discountinuedinventorylist.quantity" />
					</th>
					<th class="emblem">
						<spring:message code="BzComposer.report.discountinuedinventorylist.weight" />
					</th>
					<th class="emblem">
						<spring:message code="BzComposer.report.discountinuedinventorylist.purchaseprice" />
					</th>
					<th class="emblem">
						<spring:message code="BzComposer.report.discountinuedinventorylist.saleprice" />
					</th>			
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty ItemDetails}">
                    <input type="hidden" name="listSize" id="lSize" value='${ItemDetails.size()}'>
                    <c:forEach items="${ItemDetails}" var="item">
                        <tr>
                            <td>${item.category}</td>
                            <td colspan="2">${item.itemName}</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;${item.qty}</td>
                            <td>&nbsp;${item.weight}</td>
                            <td>&nbsp;${item.purchasePrice}</td>
                            <td>&nbsp;${item.salePrice}</td>
                        </tr>
                    </c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<input type="hidden" name="ItemType"  value="1">
	<!-- end Contents -->
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
<%@ include file="/include/emailModal.jsp"%>
</body>
</html>
<script>
function callRefresh(){
	document.forms[0].action = "Item?tabid=DiscontinuedList";
	document.forms[0].submit();
}
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
function search()
{
	document.forms[0].action = "Item?tabid=DiscontinuedList";
	document.forms[0].submit();
}
function callRefresh(){
	document.forms[0].action = "Item?tabid=DiscontinuedList";
	document.forms[0].submit();
}
function sendMail() 
{
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
var modal = document.getElementById('myModal');
</script>