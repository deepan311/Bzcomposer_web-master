<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.nxsol.bizcomposer.common.BillingStatement"%>
<%@page import="com.pritesh.bizcomposer.accounting.bean.ReceivableListBean"%>
<%@page import="com.nxsol.bizcompser.global.table.TblCategory"%>
<%@page import="com.nxsol.bizcomposer.global.clientvendor.ClientVendor"%>
<%@page import="com.pritesh.bizcomposer.accounting.bean.TblPaymentType"%>
<%@page import="com.nxsol.bizcomposer.common.JProjectUtil"%>
<%@page isELIgnored="false"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.pritesh.bizcomposer.accounting.bean.TblPayment"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page import="com.pritesh.bizcomposer.accounting.bean.TblAccountCategory"%>
<%@page import="javax.script.ScriptEngineManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.pritesh.bizcomposer.accounting.bean.TblAccount"%>
<%@page import="java.util.Collections"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.billingboardtitle"/></title>

<!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>
	var ctx = "${pageContext.request.contextPath}";
</script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <script>var ctx = "${pageContext.request.contextPath}";</script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
<style type="text/css">
div#pie { /* 	color:#05A9C5;; */
	padding: 10px 0px 20px 0px;
}

table.tabla-listados {
	width: 100%;
	border: 1px solid rgb(207, 207, 207);
	margin: 20px 0px 20px 0px;
}

table.tabla-listados thead tr th {
	font-size: .7em;
	text-align: left;
	padding: 5px 10px;
	/* 	background: rgba(5, 169, 197, 0.11); */
	border-bottom: 1px solid rgba(5, 169, 197, 0.2);
	/* 	color: #333; */
	text-shadow: #999 0px 1px 1px;
	white-space: nowrap;
}

table.tabla-listados tbody tr td {
	font-size: .8em;
	/* 	color: #666; */
	padding: 5px 0px 5px 14px;
	/* 	border-bottom: 1px solid rgb(207, 207, 207); */
	background: #fff;
	vertical-align: top;
}

#popupWindow {
	/* width: 1500px;
    height: 900px;*/
	
}
/* .ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-draggable.ui-resizable {
    min-width: 55%;
    min-height: 500px;
    overflow:"auto";
   	position: absolute;
    height: auto;
    width: 300px;
    top: 300px !important;
    left: 356px;
    display: block;
    z-index: 101;
   
} */
.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-draggable.ui-resizable
	{
	min-width: 55%;
	height: auto;
	width: 300px;
	display: block;
	z-index: 101;
}

#popupWindow {
	
}

.highlight {
	background-color: #00CED1 !important;
	color: #fff;
	padding: 5px;
}
/* .table tr:hover { cursor: pointer;} */
.bz_dialogTable tr td {
	padding: 10px
}

.bz_dialogTable tr td label {
	font-size: 12px;
}

.bz_dialogTable tr td input {
	font-size: 12px;
}

.bz_dialogTable tr td select {
	font-size: 12px;
}

.bzbtn {
	position: relative;
	top: 87px;
}

.highlight {
	background-color: #00CED1 !important;
	color: #fff
}
#errmsg
{
color: red;
}
</style>
</head>
<body>
	<div id="ddcolortabsline">&nbsp;</div>
	<div id="cos">
		<div class="row">
		    <div class="col-sm-6" style="margin-top:10px;">
		    <h3 class="title1"><spring:message code="BzComposer.BillingBoard"/></h3>
		    </div>
		</div>
		<div class="row">
			<div class="col-sm-9 ml-3">
				<div class="row border1">
					<div class="col-sm-4" style="border-right:2px solid #dddddd;">
					    <table class="form-horizontal" cellpadding="5">
					        <tr>
					            <th colspan="3"><spring:message code="BzComposer.billingboard.searchby"/></th>
					        </tr>
					        <tr>
                                <td><spring:message code="BzComposer.billingboard.column"/></td>
                                <td>
                                    <select id="advanceSearchCriteria" class="form-control" onchange="propertyDisableEnable()">
                                        <option></option>
                                        <option value="Invoice#"><spring:message code="BzComposer.billingboard.invoicenumber"/></option>
                                        <option value="Bill#"><spring:message code="BzComposer.billingboard.billnumber"/></option>
                                        <option value="First Name"><spring:message code="BzComposer.global.firstname"/></option>
                                        <option value="Last Name"><spring:message code="BzComposer.global.lastname"/></option>
                                        <option value="Email"><spring:message code="BzComposer.global.email"/></option>
                                    </select>
                                    <select id="searchDataForBillingStatement" class="form-control">
                                        <option></option>
                                        <option value="Statement#"><spring:message code="BzComposer.billingboard.statementnumber"/></option>
                                        <option value="FirstName"><spring:message code="BzComposer.billingboard.firstname"/></option>
                                        <option value="LastName"><spring:message code="BzComposer.billingboard.lastname"/></option>
                                    </select>
                                </td>
                                <td style="padding-right:20px;">
                                    <button class="btn btn-info" style="font-size: 14px;" onclick="return searchByColumn()" id="columnSearchId">
                                        <spring:message code="BzComposer.billingboard.search"/>
                                    </button>
                                    <button class="btn btn-info" onclick="return searchByColumnBillingStatement()" id="columnSearchForBilling">
                                        <spring:message code="BzComposer.billingboard.search"/>
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="BzComposer.billingboard.text"/></td>
                                <td><input type="text" class="form-control" id="advanceSearchData" /><span id="errmsg"></span></td>
                                <td><button class="btn btn-info" style="font-size: 14px;" disabled="disabled" id="clearButtonId">
                                        <spring:message code="BzComposer.global.clear"/>
                                    </button>
                                </td>
                            </tr>
                            <tr><td>&nbsp;</td></tr>
					    </table>
					</div>
					<div class="col col-sm-8">
					    <table cellpadding="5" id="section2DivId" style="margin: 0px;width: 90%;margin-left: 20px;">
					        <tr>
					            <td style="width:40%;"><spring:message code="BzComposer.billingboard.overdueinvoice"/></td>
					            <td><button type="submit" style="font-size: 14px;" class="btn btn-info"><spring:message code="BzComposer.billingboard.search"/></button></td>
					        </tr>
					        <tr>
					            <td colspan="2" style="padding-left: 40px;">
					                <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked />
					                <spring:message code="BzComposer.billingboard.showallonverdueinvoice"/>
					            </td>
					        </tr>
					        <tr>
                                <td style="padding-left: 40px;">
                                    <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" value="option2" />
                                    <spring:message code="BzComposer.billingboard.overduedate"/>
                                </td>
                                <td>
                                    <input type="text" id="inputAddress" style="width:150px;" />
                                    <spring:message code="BzComposer.billingboard.days"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-left: 25px;">
                                    <input class="form-check-input" type="checkbox" name="exampleCheckbos1" id="exampleCheckbos1" value="option1" checked />
                                    <spring:message code="BzComposer.billingboard.recurringbillinginvoice"/>
                                </td>
                                <td style="padding-left: 25px;">
                                    <input class="form-check-input" type="radio" name="exampleCheckbos2" id="exampleCheckbos2" value="option1" checked />
                                    <spring:message code="BzComposer.billingboard.showall"/>
                                </td>
                            </tr>
                            <tr><td>&nbsp;</td></tr>
					    </table>

					    <!-- second tab  div -->
					    <table cellpadding="5" id="section3DivId" style="margin: 0px;width: 90%;margin-left: 20px;">
                            <tr>
                                <td><spring:message code="BzComposer.billingboard.customername"/></td>
                            </tr>
                            <tr>
                                <td>
                                    <spring:message code="BzComposer.billingboard.overdueinvoiceamount"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <spring:message code="BzComposer.billingboard.recurringservicecharge"/>
                                </td>
                            </tr>
                        </table>
					</div>
				</div>
			</div>
			<div class="col-sm-2" id="buttonsTabDiv">
				<div class="form-group">
					<button type="button" class="btn btn-info" style="font-size: 14px; width: 190px;">
						<spring:message code="BzComposer.billingboard.createrecurringbillsbtn"/>
					</button>
				</div>
				<div class="form-group">
					<a type="button" class="btn btn-info" onclick="return PrintBilling()" style="color: #fff; font-size: 14px; width: 190px;">
						<spring:message code="BzComposer.billingboard.printbillingbtn"/>
					</a>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-info" disabled="disabled" style="color: #fff; font-size: 14px; width: 190px;">
						<spring:message code="BzComposer.billingboard.sendemailbtn"/>
					</button>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-info" onclick="return CreateBillingStatement()" style="font-size: 14px; width: 190px;">
						<spring:message code="BzComposer.billingboard.createbillingstatementbtn"/>
					</button>
				</div>
			</div>
		</div>

		<hr>

		<div class="content-tabs">


			<div id="tabs" class="mb-3">
				<ul>
					<li class="tab">
						<a href="#tabs-1">
							<spring:message code="BzComposer.billingboard.tabs.unpaidbillsandinvoice"/>
						</a>
					</li>
					<li class="tab">
						<a href="#tabs-2">
							<spring:message code="BzComposer.billingboard.tabs.paidbillsandinvoice"/>
						</a>
					</li>
					<li class="tab">
                        <a href="#tabs-3">
                            <spring:message code="BzComposer.billingboard.tabs.recurringbills"/>
                        </a>
                    </li>
				</ul>
				<div id="tabs-1" class="pl-2 pr-2 pt-3 pb-1">
					<div class="table-responsive" id="billingTab1">
						<table class="table table-bordered text-nowrap" id="recBillId">
							<thead>
								<tr>
									<th scope="col"><spring:message code="BzComposer.billingboard.select"/></th>
									<%-- <th scope="col"><spring:message code="BzComposer.billingboard.billinumber"/></th> --%>
									<th scope="col"><spring:message code="BzComposer.billingboard.invoicenumber"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.customername"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.customerjob"/></th>
									<%-- <th scope="col"><spring:message code="BzComposer.global.email"/></th> --%>
									<th scope="col"><spring:message code="BzComposer.billingboard.billingcycle"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.orderdate"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.duedate"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.amount"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.paidamount"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.lastpayment"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.balance"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.billed"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.scheduledbillingdate"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.emailed"/></th>
									<th scope="col"><spring:message code="BzComposer.global.memo"/></th>
								</tr>
							</thead>
							<tbody>
                                <c:forEach items="${billingList}" var="objList" varStatus="loop">
								<tr onclick="selctRow(${objList.invoiceID}, ${loop.index}">
									<th><input type="checkbox" name="checkbox"></th>
									<!-- <td>&nbsp;</td> -->
									<td class="text-right">${objList.orderNum}</td>
									<td class="text-right">${objList.cvName}</td>
									<td>&nbsp;</td>
									<!-- <td>&nbsp;</td> -->
									<td>&nbsp;</td>
									<td class="text-right">${objList.dateAdded}</td>
									<td class="text-right">${objList.overDueDate}</td>
									<td class="text-right">${objList.adjustedTotal}</td>
									<td class="text-right">${objList.paidAmount}</td>
									<td></td>
									<td class="text-right">${objList.balance}</td>
									<td><input type="checkbox"/></td>
									<td ></td>
									<th><input type="checkbox"></th>
									<td class="text-right">${objList.memo}</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>


				<div id="tabs-2" class="pl-2 pr-2 pt-3 pb-1">
					<div class="table-responsive" id="billingStatement">
						<table class="table table-bordered text-nowrap" id="BillingStatementId">
							<thead>
								<tr>
									<th scope="col"><spring:message code="BzComposer.billingboard.select"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.statementnumber"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.statementfor"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.customername"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.statementdate"/></th>
									<th scope="col"><spring:message code="BzComposer.billingboard.amount"/></th>
								</tr>
							</thead>
							<tbody>
							<% ArrayList<BillingStatement> billingStatementList = (ArrayList)request.getAttribute("billingStatementList"); 
							   for(int i=0;i<billingStatementList.size();i++)
							   {   
							%>
								<tr>
									<th><input type="checkbox" name="checkbox"></th>
									<td><% out.println(billingStatementList.get(i).getStatementNo());%></td>
									<td class="text-right"><%= billingStatementList.get(i).getStatementFor()%></td>
									<td class="text-right"><%= billingStatementList.get(i).getCustomerName()%></td>
									<td class="text-right"><%= JProjectUtil.getdateFormat().format(billingStatementList.get(i).getStatementDate())%></td>
									<td class="text-right"><%= String.format("%.2f", billingStatementList.get(i).getAmount())%></td>
								</tr>
								<% } %>
							</tbody>
						</table>
					</div>
				</div>
				<div id="tabs-3" class="pl-2 pr-2 pt-3 pb-1">
                    <div class="table-responsive" id="recurringBillId">
                    </div>
                </div>
			</div>
		</div>
		<div class="form-inline">
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="inlineRadioOptions" id="selectAll" value="option1">
				<label class="form-check-label" for="inlineRadio1">
					<spring:message code="BzComposer.billingboard.selectallbtn"/>
				</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="inlineRadioOptions" id="unSelectAll" value="option2">
				<label class="form-check-label" for="inlineRadio2">
					<spring:message code="BzComposer.billingboard.unselectallbtn"/>
				</label>
			</div>
			<div class="form-check form-check-inline">
				<button class="btn btn-info" style="font-size: 14px;">
					<spring:message code="BzComposer.billingboard.undobtn"/>
				</button>
			</div>
		</div>
		<script>
			$(function() {
				debugger;
				$("#tabs").tabs();
			});
		</script>
	</div>
<script type="text/javascript">
var invoiceId = -1;
var index = -1;
function selctRow(id,inv)
{
	debugger;
	this.invoiceId = id;
	this.index = inv;
}
function selectinvoicefirstdialog()
{
	event.preventDefault();
	$("#selectinvoicefirstdialog").dialog({
    	resizable: false,
        height: 200,
        width: 450,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function showerrordialog()
{
	event.preventDefault();
	$("#showerrordialog").dialog({
    	resizable: false,
        height: 200,
        width: 450,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function showemptydatadialog()
{
	event.preventDefault();
	$("#showemptydatadialog").dialog({
    	resizable: false,
        height: 200,
        width: 450,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
$("#tabs").tabs({
  
	activate: function(event, ui) {
		debugger;
        
        var activeTab = $('#tabs').tabs('option', 'active');
        if(activeTab == 1)
        {
        	$("#buttonsTabDiv").hide();
        	$("#section2DivId").hide();
        	$('input[name="checkbox"]', '#BillingStatementId').prop('checked', false);
        	$("#section3DivId").show();
        	$("#searchDataForBillingStatement").show();
        	$("#advanceSearchCriteria").hide();
        	$("#columnSearchId").hide();
        	$("#columnSearchForBilling").show();
        }
        else
        {
        	$("#buttonsTabDiv").show();
        	$("#section2DivId").show();
        	$("#section3DivId").hide();
        	$("#searchDataForBillingStatement").hide();
        	$("#advanceSearchCriteria").show();
        	$("#columnSearchId").show();
        	$("#columnSearchForBilling").hide();
        }
    }
});

function PrintBilling()
{
	if(invoiceId == -1){

		return selectinvoicefirstdialog();
		return false;
	}
	$.ajax({
        type : "POST",
        url : "BillingBoardStatement?tabid=PrintBill",
        data:"invoiceId=" +invoiceId ,
        success : function(data){
            debugger;
            updateBillingBoard(data);
        },
        error : function(data) {

            return showerrordialog();
        }
    });
}

function CreateBillingStatement()
{
	if(invoiceId == -1)
	{

		return selectinvoicefirstdialog();
		return false;
	}
	$.ajax({
			
			type : "POST",
			url : "BillingBoardStatement?tabid=CreateBillingStatement",
		 	data:"invoiceId=" +invoiceId ,
		    success : function(data)
		    {
		    debugger;
		    updateBillingBoard(data);
				 
			},
			 error : function(data) {

				 return showerrordialog();
			} 

});
}
function searchByColumn()
{
	debugger;
	var searchCriteriaCombo = document.getElementById("advanceSearchCriteria");
	var advanceSerchCriteria = searchCriteriaCombo.options[searchCriteriaCombo.selectedIndex].value;
	
	var advanceSearchData = $("#advanceSearchData").val();
	if(advanceSearchData == '')
	{

		return showemptydatadialog();
		return false;
	}
	 $.ajax({
			
			type : "POST",
			url : "BillingBoardStatement?tabid=searchByColumn",
		 	data:"advanceSerchCriteria=" + advanceSerchCriteria + "&advanceSearchData=" +advanceSearchData,
		    success : function(data)
		    {
		    debugger;
		    updateBillingBoard(data);
				 
			},
			 error : function(data) {

				 return showerrordialog();
			} 

});
	
}
function searchByColumnBillingStatement()
{
	debugger;
	var searchCriteriaCombo = document.getElementById("searchDataForBillingStatement");
	var advanceSerchCriteria = searchCriteriaCombo.options[searchCriteriaCombo.selectedIndex].value;
	
	var advanceSearchData = $("#advanceSearchData").val();
	if(advanceSearchData == '')
	{

		return showemptydatadialog()
		return false;
	}
	 $.ajax({
			
			type : "POST",
			url : "BillingBoardStatement?tabid=searchForBillingStatement",
		 	data:"advanceSerchCriteria=" + advanceSerchCriteria + "&advanceSearchData=" +advanceSearchData,
		    success : function(data)
		    {
		    debugger;
		    updateBillingBoard(data);
				 
			},
			 error : function(data) {

				 return showerrordialog()
			} 

});
	
}

function updateBillingBoard(data)
{
	$(document).find('div#billingTab1 table').replaceWith($(data).find('div#billingTab1').html());
	$(document).find('div#billingStatement table').replaceWith($(data).find('div#billingStatement').html());
}
$(document).ready(function () {
	var searchCriteriaCombo = document.getElementById("advanceSearchCriteria");
	var advanceSerchCriteria = searchCriteriaCombo.options[searchCriteriaCombo.selectedIndex].value;
	if(advanceSerchCriteria == '')
	{
		$("#columnSearchId").prop("disabled",true);
		$("#clearButtonId").prop("disabled",true);
	}
	$("#section3DivId").hide();
	$("#searchDataForBillingStatement").hide();
	$("#columnSearchForBilling").hide();
});
/* $("#advanceSearchData").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    debugger;
    var searchCriteriaCombo = document.getElementById("advanceSearchCriteria");
	var advanceSerchCriteria = searchCriteriaCombo.options[searchCriteriaCombo.selectedIndex].value;
if(advanceSerchCriteria == "Invoice#" || advanceSerchCriteria == "Bill#")
{	
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
   
} 
else
{
	 var keyCode = e.which ? e.which : e.keyCode;
	 var result = ((keyCode >= 65 && keyCode <= 90) || 
             (keyCode >= 97 && keyCode <= 122));
	 if(result == false)
	{
		 $("#errmsg").html("Digits Not Allowed").show().fadeOut("slow");
         return false;
	}
}
  }); */
function propertyDisableEnable()
{
	var searchCriteriaCombo = document.getElementById("advanceSearchCriteria");
	var advanceSerchCriteria = searchCriteriaCombo.options[searchCriteriaCombo.selectedIndex].value;
	if(advanceSerchCriteria != '')
	{
		$("#columnSearchId").prop("disabled",false);
		$("#clearButtonId").prop("disabled",false);
	}
	else
	{
		$("#columnSearchId").prop("disabled",true);
		$("#clearButtonId").prop("disabled",true);
	}
	$("#advanceSearchData").val('');
}
$(document).ready(function () {
    $('tr').click(function () {
         var selected = $(this).hasClass("highlight"); 
         $("tr").removeClass("highlight");
         if(!selected)
             $(this).addClass("highlight");
    });
}); 	
$(document).ready(function () {
	  $('body').on('click', '#selectAll', function () {
	    if ($(this).hasClass('allChecked')) {
	        $('input[name="checkbox"]', '#recBillId').prop('checked', false);
	        $('input[name="checkbox"]', '#BillingStatementId').prop('checked', false);
	    } else {
	        $('input[name="checkbox"]', '#recBillId').prop('checked', true);
	        $('input[name="checkbox"]', '#BillingStatementId').prop('checked', true);
	    }
	   /*  $(this).toggleClass('allChecked'); */
	  })
	  $('body').on('click', '#unSelectAll', function () {
	    if ($(this).hasClass('allChecked')) {
	        $('input[name="checkbox"]', '#recBillId').prop('checked', true);
	        $('input[name="checkbox"]', '#BillingStatementId').prop('checked', true);
	    } else {
	        $('input[name="checkbox"]', '#recBillId').prop('checked', false);
	        $('input[name="checkbox"]', '#BillingStatementId').prop('checked', false);
	    }
	   /*  $(this).toggleClass('allChecked'); */
	  })
});
</script>	
</body>
</html>
<!-- Dialog box used in this page -->
<div id="selectinvoicefirstdialog" style="display:none;">
	<p><spring:message code='BzComposer.billingboard.selectinvoicefirst'/></p>
</div>
<div id="showerrordialog" style="display:none;">
	<p><spring:message code='BzComposer.billingboard.someerroroccurred'/></p>
</div>
<div id="showemptydatadialog" style="display:none;">
	<p><spring:message code='BzComposer.billingboard.dataisempty'/></p>
</div>