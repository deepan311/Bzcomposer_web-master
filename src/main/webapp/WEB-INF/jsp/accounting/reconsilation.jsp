<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.reconsilationtitle"/></title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!--  Here Is the context path -->
 <script>var ctx = "${pageContext.request.contextPath}";</script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

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
/*.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-draggable.ui-resizable {
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
   
}*/

.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-draggable.ui-resizable
{
	min-width: 55%;
	height: auto;
	width: 300px;
	display: block;
	z-index: 101;
}

#highlight { background-color: blue; }
.highlight { background-color: #00CED1 !important;color: #fff }	 
	
</style>
</head>
<body>
<div class="clearfix"></div>
<div id="ddcolortabsline">&nbsp;</div>
<div class="content1 clearfix">
<div class="form-inline  d-flex justify-content-center mb-4">
	<div class="form-group ml-3" style="font-size:14px;">
		<label>
			<spring:message code="BzComposer.reconciliation.account"/>
		</label>
		<select class="form-control ml-1" id="account" onchange="executeQuery()">
		    <c:forEach items="${accountList}" var="curObject" varStatus="loop">
			<option value="${curObject.accountID}" label="${curObject.customerCurrentBalance}">${curObject.name}</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group ml-3" style="font-size:14px;">
		<label>
			<spring:message code="BzComposer.reconciliation.searchrange"/>		
		</label>
	</div>
	<div class="form-group ml-3" style="font-size:14px;">
		<label>
			<spring:message code="BzComposer.reconciliation.from"/>
		</label>
		<div class="d-flex ml-1">
			<input type="text" class="form-control" id="fromDate" name="fromDate" onselect="getDateVisePayment()" />
			<div class="input-group-append input-group-append1">
				<a class="input-group-text">&#9662;</a>
			</div>
		</div>
	</div>
	<div class="form-group ml-3" style="font-size:14px;">
		<label>
			<spring:message code="BzComposer.reconciliation.to"/>
		</label>
		<div class="d-flex ml-1">
			<input type="text" class="form-control" id="toDate" />
			<div class="input-group-append input-group-append1">
				<a class="input-group-text" href="#">&#9662;</a>
			</div>
		</div>
	</div>
	<div class="form-group ml-3" style="font-size:14px;">
		<label>
			<spring:message code="BzComposer.reconciliation.moveby1month"/>
		</label>
	</div>	
	<button class="btn btn-info btn-sm pt-0 pb-0 ml-2" onclick="getDateVisePayment('backward')">
		<img width="26px" src="${pageContext.request.contextPath}/images/left.png" />
	</button>
	<button class="btn btn-info btn-sm pt-0 pb-0  ml-2" onclick="getDateVisePayment('forward')">
		<img width="26px" src="${pageContext.request.contextPath}/images/right.png" />
	</button>
</div>
<div class="row">
    <div class="col-md-6">
        <h3 class="title1 text-center">
            <spring:message code="BzComposer.reconciliation.creditordeposits"/>
        </h3>
        <div class="table1  mb-3" id="DepositsTable">
            <table class="table table-bordered table-sm devDepositTable">
                <thead class="thead-light">
                    <tr>
                        <th scope="col"><spring:message code="BzComposer.reconciliation.crosssign"/></th>
                        <th scope="col"><spring:message code="BzComposer.reconciliation.date"/></th>
                        <th scope="col"><spring:message code="BzComposer.reconciliation.checknumber"/></th>
                        <th scope="col"><spring:message code="BzComposer.reconciliation.payer"/></th>
                        <th scope="col"><spring:message code="BzComposer.reconciliation.amount"/></th>
                        <th scope="col"><spring:message code="BzComposer.reconciliation.category"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listOfDepositPayments}" var="curObject" varStatus="loop">
                    <c:if test="${curObject.payerID == selectedAccount}">
                    <tr onclick="getPayments(${loop.index}, ${curObject.id}, ${selectedAccount}, 'Deposit')">
                        <td><input type="checkbox" /></td>
                        <td class="text-center">${curObject.dateAdded}</td>
                        <td class="text-right">
                            <c:if test="${not empty curObject.checkNumber}">
                                ${curObject.checkNumber}
                            </c:if>
                        </td>
                        <c:if test="${not empty curObject.cvName}">
                        <td value="${curObject.cvID}">${curObject.cvName}</td>
                        </c:if>
                        <c:if test="${empty curObject.cvName}">
                        <td value="-1"></td>
                        </c:if>
                        <td class="text-right">${curObject.amount}</td>
                        <td class="text-right" value="${curObject.categoryId}">${curObject.categoryName}</td>
                        <c:if test="${curObject.paymentTypeID != -1}">
                        <td hidden="paymentTypId" class="text-right" value="${curObject.paymentTypeID}"></td>
                        </c:if>
                        <c:if test="${curObject.paymentTypeID == -1}">
                        <td value="-1"></td>
                        </c:if>
                        <td hidden="oldAccountId" class="text-right" value="${curObject.accountID}"></td>
                    </tr>
                    </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="row">
            <div class="col-md-5" id="depositsCountBottom" style="font-size: 14px;">
                <strong>
                    <spring:message code="BzComposer.reconciliation.depositcount"/>:
                </strong>
                <label id="depositCount">
                    <c:set var="count" value="0" scope="page" />
                        <c:forEach items="${listOfDepositPayments}" var="curObject" varStatus="loop">
                        <c:if test="${curObject.payerID == selectedAccount}">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                        </c:if>
                        </c:forEach>
                    <c:out value="${count}" />
                </label>
            </div>
            <div class="col-md-7" id="depositsTotalBottom" style="font-size: 14px;">
                <strong>
                    <spring:message code="BzComposer.reconciliation.deposittotal"/>:
                </strong>
                <label id="depositTotal">
                    <c:set var="total" value="0" scope="page" />
                        <c:forEach items="${listOfDepositPayments}" var="curObject" varStatus="loop">
                        <c:if test="${curObject.payerID == selectedAccount}">
                            <c:set var="total" value="${total + curObject.amount}" scope="page"/>
                        </c:if>
                        </c:forEach>
                    <c:out value="${total}" />
                </label>
            </div>
        </div>
    </div>
	<div class="col-md-6">
		<h3 class="title1 text-center">
			<spring:message code="BzComposer.reconciliation.payments"/>
		</h3>
		<div class="table1 mb-3" id="PaymentsTable">
			<table class="table table-bordered table-sm devPaymentsTable">
		  		<thead class="thead-light">
		    		<tr>
		      			<th scope="col"><spring:message code="BzComposer.reconciliation.crosssign"/></th>
		      			<th scope="col"><spring:message code="BzComposer.reconciliation.date"/></th>
		      			<th scope="col"><spring:message code="BzComposer.reconciliation.checknumber"/></th>
		      			<th scope="col"><spring:message code="BzComposer.reconciliation.payee"/></th>
		      			<th scope="col"><spring:message code="BzComposer.reconciliation.amount"/></th>
		      			<th scope="col"><spring:message code="BzComposer.reconciliation.category"/></th>
		    		</tr>
		  		</thead>
		  		<tbody>
					<c:forEach items="${listOfPayments}" var="curObject" varStatus="loop">
					<c:if test="${curObject.payerID == selectedAccount}">
					<tr onclick="getPayments(${loop.index+1}, ${curObject.id}, ${selectedAccount}, 'Payment')">
						<td><input type="checkbox" /></td>
						<td class="text-center">${curObject.dateAdded}</td>
						<td class="text-right">${curObject.checkNumber}</td>
						<c:if test="${not empty curObject.cvName}">
						<td value="${curObject.cvID}">${curObject.cvName}</td>
						</c:if>
						<c:if test="${empty curObject.cvName}">
						<td value = "-1"></td>
						</c:if>
						<td class="text-right">${curObject.amount}</td>
						<td class="text-right" value="${curObject.categoryId}">${curObject.categoryName}</td>
						<c:if test="${curObject.paymentTypeID != -1}">
						<td hidden="paymentTypId" class="text-right" value="${curObject.paymentTypeID}"></td>
						</c:if>
						<c:if test="${curObject.paymentTypeID == -1}">
						<td value="-1"></td>
						</c:if>
						<td hidden="oldAccountId" class="text-right" value="${curObject.accountID}"></td>
					</tr>
					</c:if>
					</c:forEach>
				</tbody>	
			</table>
		</div>
		<div class="row">
			<div class="col-md-5" id="paymentsCountBottom" style="font-size: 14px;">
				<strong><spring:message code="BzComposer.reconciliation.paymentcount"/>:</strong>
				<label id="paymentCount">
				<c:set var="count" value="0" scope="page" />
				    <c:forEach items="${listOfPayments}" var="curObject" varStatus="loop">
                    <c:if test="${curObject.payerID == selectedAccount}">
                        <c:set var="count" value="${count + 1}" scope="page"/>
		   			</c:if>
                    </c:forEach>
                <c:out value="${count}" />
		 		</label>
			</div>
			<div class="col-md-7" id="paymentsTotalBottom" style="font-size: 14px;">
				<strong>
					<spring:message code="BzComposer.reconciliation.paymenttotal"/>:
				</strong>
				<label id="paymentTotal">
				<c:set var="total" value="0" scope="page" />
                    <c:forEach items="${listOfPayments}" var="curObject" varStatus="loop">
                    <c:if test="${curObject.payerID == selectedAccount}">
                        <c:set var="total" value="${total + curObject.amount}" scope="page"/>
                    </c:if>
                    </c:forEach>
                <c:out value="${total}" />
				</label>
			</div>
		</div>
	</div>

</div>
<div class="text-center mt-5">
	<button class="btn btn-info" style="font-size: 14px;"><spring:message code="BzComposer.reconciliation.resetbtn"/></button>
	<button class="btn btn-info" style="font-size: 14px;" onclick="return deletePayment()"><spring:message code="BzComposer.global.delete"/></button>
	<button class="btn btn-info" style="font-size: 14px;" id="ReconcileButton"><spring:message code="BzComposer.reconciliation.reconcilebtn"/></button>
	<button class="btn btn-info" style="font-size: 14px;" id="AssetButton"><spring:message code="BzComposer.reconciliation.assetsbtn"/></button>
	<button class="btn btn-info" style="font-size: 14px;" id="AddButton"><spring:message code="BzComposer.global.add"/></button>
	<button class="btn btn-info" style="font-size: 14px;" id="EditButton"><spring:message code="BzComposer.global.edit"/></button>
</div>
<div id="ReconcileDlgId">
	<div class="form2">
		<h3 class="title1 text-center mt-2">
			<label id="ReconcileBankName">
				<spring:message code="BzComposer.reconciliation.reconcilebusinesschecking"/>
			</label>
		</h3>
		<hr>
		<div class="form-horizontal">
			<div class="">
				<label>
					<spring:message code="BzComposer.reconciliation.addmissingdisbursements"/>
				</label>
			</div>
			<div class="form-group row">
				<label class="col-md-2"></label>
				<div class="col-md-9">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1">
					  	<label class="form-check-label" for="inlineCheckbox1">
					  		<spring:message code="BzComposer.reconciliation.addpayments"/>
				  		</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2">
					  	<label class="form-check-label" for="inlineCheckbox2">
					  		<spring:message code="BzComposer.reconciliation.adddeposit"/>
				  		</label>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-6">
				<div class="form-horizontal">
					<div class="form-group row">
						<label class="col-md-5 col-form-label">
							<spring:message code="BzComposer.reconciliation.paymenttype"/>:
						</label>
						<div class="col-md-7">
							<select class="form-control" id="CategoryTypeForReconsile" onchange="getCategoryAndCharge()">
							    <c:forEach items="${categoryType}" var="curObject" varStatus="loop">
                                    <c:if test="${curObject.categoryTypeName == 'EXPENSE' || curObject.categoryTypeName == 'INCOME'}">
								    <option value="${curObject.categoryTypeID}">${curObject.categoryTypeName}</option>
								    </c:if>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-5 col-form-label">
							<spring:message code="BzComposer.reconciliation.selectcategory"/>:
						</label>
						<div class="col-md-7" id="CategoryForReconsileDiv">
							<select class="form-control" id="CategoryForReconsile" onchange="getCharge()">
							    <c:forEach items="${initCategory}" var="curObject" varStatus="loop">
                                    <option value="${curObject.id}">${curObject.categoryNumber}</option>
                                </c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-5 col-form-label">
							<spring:message code="BzComposer.reconciliation.selectcharge"/>:
						</label>
						<div class="col-md-7" id="ChrgeForReconsileDiv">
							<select class="form-control" id="ChrgeForReconsile">
							    <c:forEach items="${initCharge}" var="curObject" varStatus="loop">
                                    <option value="${curObject.id}">${curObject.categoryNumber}</option>
                                </c:forEach>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-horizontal">
					<div class="form-group row">
						<label class="col-md-5 col-form-label">
							<spring:message code="BzComposer.reconciliation.date"/>:
						</label>
						<div class="col-md-7">
							<div class="d-flex">
								<input type="text" class="form-control" id="ReconcileDate">
								<div class="input-group-append input-group-append1">
									<a class="input-group-text" href="#" >&#9662;</a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-5 col-form-label">
							<spring:message code="BzComposer.reconciliation.amount"/>:
						</label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="AmountForReconcileDlg">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-5 col-form-label">
							<input type="checkbox" id="CheckBoxForReconcile" onclick="getCheckboxForReconcile()"> 
							<spring:message code="BzComposer.reconciliation.checknumber"/>
						</label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="checkNumberForReconcile">
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-6">
				<button class="btn btn-info" onclick="return addNewSituation()">
					<spring:message code="BzComposer.reconciliation.addnewsituationbtn"/>
				</button>
			</div>
			<div class="col-md-6 text-right">
				<button class="btn btn-info" onclick="return AddFromReconcileDlg()">
					<spring:message code="BzComposer.global.add"/>
				</button>
				<button class="btn btn-info">
					<spring:message code="BzComposer.global.close"/>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="AssetDlgId">
	<div class="form2">
		<h3 class="title1 text-center mt-2">
			<spring:message code="BzComposer.reconciliation.assetsaccount"/>
		</h3>
		<hr>
		<div class="row">
			<div class="col-md-6">
				<div class="form-horizontal">
					<div class="form-group row">
						<label class="col-md-5 col-form-label">
							<spring:message code="BzComposer.reconciliation.selectcategory"/>:
						</label>
						<div class="col-md-7">
							<select class="form-control" onchange="changeAssetCategory()" id="AssetCategoryId">
							    <c:forEach items="${getCategoryListForAsset}" var="curObject" varStatus="loop">
								<option value="${curObject.id}">${curObject.categoryNumber}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-5 col-form-label">
							<spring:message code="BzComposer.reconciliation.selectcharge"/>:
						</label>
						<div class="col-md-7" id="AssetChargeCategoryDiv">
							<select class="form-control" id="ChrgeIdForAsset">
							    <c:forEach items="${subCategoryChrgeListForAsset}" var="curObject" varStatus="loop">
								<option>${curObject.categoryNumber}</option>
								</c:forEach>
								<c:if test="${empty subCategoryChrgeListForAsset}">
								<option></option>
								</c:if>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-horizontal">
					<div class="form-group row">
						<label class="col-md-5 col-form-label">
							<spring:message code="BzComposer.reconciliation.date"/>:
						</label>
						<div class="col-md-7">
							<div class="d-flex">
								<input type="text" class="form-control" id="AssetDate">
								<div class="input-group-append input-group-append1">
									<a class="input-group-text" href="#" id="billDuedateCalendar">&#9662;</a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-5 col-form-label">
							<spring:message code="BzComposer.reconciliation.amount"/>:
						</label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="AssetAmount">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-5 col-form-label">
							<spring:message code="BzComposer.reconciliation.depreciation"/>:
						</label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="AssetDescription">
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-12 text-center">
				<button class="btn btn-info" onclick="return AddAssets()">
					<spring:message code="BzComposer.global.add"/>
				</button>
				<button class="btn btn-info">
					<spring:message code="BzComposer.global.close"/>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="AddDlgId">
	<div class="form2">
		<div id="tabs">
			<ul>
				<li>
					<a href="#tabs-1">
						<spring:message code="BzComposer.reconciliation.fundtransfer"/>
					</a>
				</li>
				<li>
					<a href="#tabs-2">
						<spring:message code="BzComposer.reconciliation.payment"/>
					</a>
				</li>
				<li>
					<a href="#tabs-3">
						<spring:message code="BzComposer.reconciliation.deposit"/>
					</a>
				</li>
			</ul>
			<div id="tabs-1" class="pl-2 pr-2 pt-3 pb-1">
				<h3 class="title1 text-left mt-2">
					<spring:message code="BzComposer.reconciliation.payerinformation"/>
				</h3>
				<hr>
				<div class="row">
					<div class="col-md-6">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.payeraccount"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.payamount"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.paymethod"/>:
								</label>
								<div class="col-md-7">
									<select class="form-control">
										<option value="Check">
											<spring:message code="BzComposer.reconciliation.check"/>
										</option>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.date"/>:
								</label>
								<div class="col-md-7">
									<div class="d-flex">
										<input type="text" class="form-control" id="billDuedateCalendarText">
										<div class="input-group-append input-group-append1">
											<a class="input-group-text" href="#" id="billDuedateCalendar">&#9662;</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.endingbalance"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">&nbsp;</label>
								<div class="col-md-7">
									&nbsp;
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.checknumber"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-6">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.payeeaccount"/>:
								</label>
								<div class="col-md-7">
									<select class="form-control">
										<option value="Check">
											<spring:message code="BzComposer.reconciliation.check"/>
										</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.endingbalance"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-12 text-right">
						<button class="btn btn-info" disabled><spring:message code="BzComposer.global.add"/></button>
						<button class="btn btn-info"><spring:message code="BzComposer.global.cancel"/></button>
					</div>
				</div>
			</div>
			<div id="tabs-2" class="pl-2 pr-2 pt-3 pb-1">
				<h3 class="title1 text-left mt-2">
					<spring:message code="BzComposer.reconciliation.payerinformation"/>
				</h3>
				<hr>
				<div class="row">
					<div class="col-md-6">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.payeraccount"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.payamount"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.paymethod"/>:
								</label>
								<div class="col-md-7">
									<select class="form-control">
										<option value="Check">
											<spring:message code="BzComposer.reconciliation.check"/>
										</option>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.date"/>:
								</label>
								<div class="col-md-7">
									<div class="d-flex">
										<input type="text" class="form-control" id="billDuedateCalendarText">
										<div class="input-group-append input-group-append1">
											<a class="input-group-text" href="#" id="billDuedateCalendar">&#9662;</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.endingbalance"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">&nbsp;</label>
								<div class="col-md-7">
									&nbsp;
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.checknumber"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-6">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.payeeaccount"/>:
								</label>
								<div class="col-md-7">
									<select class="form-control" style="overflow-x: auto;" multiple>
										<option>Check Check</option>
										<option>Check4</option>
										<option>Check2</option>
										<option>Check</option>
										<option>Check4</option>
										<option>Check7</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.endingbalance"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.category"/>:
								</label>
								<div class="col-md-7">
									<select class="form-control">
										<option></option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-12 text-right">
						<button class="btn btn-info"><spring:message code="BzComposer.global.add"/></button>
						<button class="btn btn-info"><spring:message code="BzComposer.global.cancel"/></button>
					</div>
				</div>
			</div>
			<div id="tabs-3" class="pl-2 pr-2 pt-3 pb-1">
				<h3 class="title1 text-left mt-2">
					<spring:message code="BzComposer.reconciliation.payerinformation"/>
				</h3>
				<hr>
				<div class="row">
					<div class="col-md-8">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-4 col-form-label">
									<spring:message code="BzComposer.reconciliation.payeraccount"/>:
								</label>
								<div class="col-md-8">
									<select class="form-control" style="overflow-x: auto;" multiple>
										<option>Check Check</option>
										<option>Check4</option>
										<option>Check2</option>
										<option>Check</option>
										<option>Check4</option>
										<option>Check7</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.endingbalance"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.payamount"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.paymethod"/>:
								</label>
								<div class="col-md-7">
									<select class="form-control">
										<option value="Check"><spring:message code="BzComposer.reconciliation.check"/></option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.date"/>:
								</label>
								<div class="col-md-7">
									<div class="d-flex">
										<input type="text" class="form-control" id="billDuedateCalendarText">
										<div class="input-group-append input-group-append1">
											<a class="input-group-text" href="#" id="billDuedateCalendar">&#9662;</a>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">&nbsp;</label>
								<div class="col-md-7">
									&nbsp;
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.checknumber"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-6">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.payeeaccount"/>:
								</label>
								<div class="col-md-7">
									<select class="form-control">
										<option>Check Check  </option>
										<option>Check4</option>
										<option>Check2</option>
										<option>Check</option>
										<option>Check4</option>
										<option>Check7</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-horizontal">
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.endingbalance"/>:
								</label>
								<div class="col-md-7">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-5 col-form-label">
									<spring:message code="BzComposer.reconciliation.category"/>:
								</label>
								<div class="col-md-7">
									<select class="form-control">
										<option>3000 Products Sales</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-12 text-right">
						<button class="btn btn-info"><spring:message code="BzComposer.global.add"/></button>
						<button class="btn btn-info"><spring:message code="BzComposer.global.cancel"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="container-fluid" id="EditDlgId">
	<div class="table-responsive" style="padding-top: 20px">  
  		<div class="d-flex flex-column full-height">      
   			<div class="modal-body1">
  				<div class=" row">
  					<div class=" col-12">
  						<div class="border1  clearfix">
  							<form>	
	 							<div class="row">
	 								<div class="col-md-7" id="editTable1">
	 									<table class="bz_dialogTable devFirstEditTable">
	 										<tr>		
		 										<td>
		 											<label>
		 												<spring:message code="BzComposer.reconciliation.payer"/>
	 												</label>
 												</td>
		 										<td>
		 											<select class="payerField" id="payerForEdit" onchange="" style="width:100%;">
		 											<c:forEach items="${allClientVendor}" var="curObject" varStatus="loop">
		 		 										<option value="${curObject.cvID}">
		 													${curObject.firstName} ${curObject.lastName} ( ${curObject.name})
	 													</option>
		 											</c:forEach>
		 											</select>
	 											</td>
		 										<td>
		 											<input type="hidden" value="" id="categoryIdForPayerForDeposit">
	 											</td>
	 										</tr>
	 										<tr>
		 										<td>
		 											<label>
		 												<spring:message code="BzComposer.reconciliation.payee"/>
	 												</label>
 												</td>
		 										<td>
		 											<select class="accountFieldForEdit" id="payeeForEdit" style="width:100%;">
		 											<c:forEach items="${accountList}" var="curObject" varStatus="loop">
		 		 										<option value="${curObject.accountID}">${curObject.name}</option>
			 										</c:forEach>
		 											</select>
	 											</td>
	 										</tr>
		 									<tr>
	 											<td>
	 												<label>
	 													<spring:message code="BzComposer.reconciliation.paymethod"/>
 													</label>
												</td>
	 											<td>
	 												<select class="payMethodForEdit" style="width:100%" onchange="" id="payMethodForEdit" style="width:100%;">
	 												<c:forEach items="${allPaymentList}" var="curObject" varStatus="loop">
											 			 <option value="${curObject.id}">${curObject.typeName}</option>
	 												</c:forEach>
	 												</select>
 												</td>
	 										</tr>
	 		 								<tr>
	 											<td>
	 												<label>
	 													<spring:message code="BzComposer.reconciliation.category"/>
 													</label>
												</td>
	 											<td>
	 												<select class="categoryForEdit" id="categoryForEdit" style="width:100%;">
	 												<c:forEach items="${allCategoryList}" var="curObject" varStatus="loop">
	 			 										<option value="${curObject.id}">
	 														${curObject.name} ${curObject.categoryNumber}
 														</option>
	 												</c:forEach>
	 												</select>
 												</td>
	 										</tr>				
	 									</table>	
	 								</div>
	 								<div class="col-md-5 " id="editTable2">
	 									<table class="bz_dialogTable devSecondEditTable">
	 										<tr>
	 											<td>
	 												<label>
	 													<spring:message code="BzComposer.reconciliation.amount"/>
 													</label>
												</td>
	 											<td>
	 												<input class="devAmount" type="text" id="amountToEdit"/>
 												</td>
	 										</tr>
	 										<tr>
	 											<td>
	 												<label>
	 													<spring:message code="BzComposer.reconciliation.date"/>
 													</label>
												</td>
	 											<td>
	 												<input type="text" class="devDate" id="devDateForEdit"/>
 												</td> 
	 										</tr>
	 										<tr>
	 											<td>
	 												<label>
	 													<spring:message code="BzComposer.reconciliation.checknumber"/>
 													</label>
												</td>
	 											<td>
	 												<input type="text" class="checkNumberForEdit" id="checkNumberEdit"/>
 												</td>
	 										</tr>
	 									</table>
	 								</div>
	 							</div>
  							</form>
						</div>
					</div>
  				</div> 
 			</div>
 		</div>
 	</div> 
	<div class="bzbtn">
  		<button type="button" class="btn btn-info" style="float: right;font-size: 14px;">
  			<spring:message code="BzComposer.global.cancel"/>
  		</button>	
  		<button type="button" class="btn btn-info" style="float: right;margin-right: 10px;font-size: 14px;" onclick="return editTransaction()" id="addButtonForDeposit">
  			<spring:message code="BzComposer.global.save"/>
  		</button>
	</div> 	
</div>
<script type="text/javascript">
var inv = -1;
var paymentId = -1;
var acId = -1;
var accountId = -1;
var tableName = "";
function getPayments(index,payId,accId,type)
{
	debugger;
	this.inv = index;
	this.paymentId = payId;
	this.accountId = accId; 
	var accountCombo = document.getElementById("account");
	this.acId = accountCombo.options[accountCombo.selectedIndex].value; 
	this.tableName = type;
	
	if(tableName == "Payment")
	{	
			$("select.payerField").val($('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(4)').attr('value'));
			$("select.categoryForEdit").val($('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(6)').attr('value'));
			$(".devAmount").val($('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(5)').text());
			$("select.accountFieldForEdit").val(accountId);
			$("select.payMethodForEdit").val($('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(7)').attr('value'));
			
			if($('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(7)').attr('value') == '1' || $('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(7)').attr('value') == '192' || $('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(7)').attr('value') == '-1')
			{
				if($('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(7)').attr('value') == '-1')
				{
					$("#checkNumberEdit").prop("disabled",true);
				}
				else
				{
					$("#checkNumberEdit").prop("disabled",false);
				}
				if($('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(3)').text() != '')
				{	
					$(".checkNumberForEdit").val($('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(3)').text());
				}	
			}
			else
			{
				$(".checkNumberForEdit").val('');
			}
			$(".devDate").val($('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(2)').text());
	}
	else
	{
		$("select.payerField").val($('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(4)').attr('value'));
		$("select.categoryForEdit").val($('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(6)').attr('value'));
		$(".devAmount").val($('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(5)').text());
		$("select.accountFieldForEdit").val(accountId);
		$("select.payMethodForEdit").val($('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(7)').attr('value'));
		
		if($('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(7)').attr('value') == '1' || $('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(7)').attr('value') == '192' || $('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(7)').attr('value') == '-1')
		{
			if($('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(7)').attr('value') == '-1')
			{
				$("#checkNumberEdit").prop("disabled",true);
			}
			else
			{
				$("#checkNumberEdit").prop("disabled",false);
			}
			if($('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(3)').text() != '')
			{	
				$(".checkNumberForEdit").val($('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(3)').text());
			}	
		}
		else
		{
			$(".checkNumberForEdit").val('');
		}
		$(".devDate").val($('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(2)').text());
	}
}
$(document).ready(function () {
   
}); 
$(document).ready(function(){
	debugger;
	$("#fromDate").val("11/14/2007");
	$("#toDate").val((new Date().getMonth()+1) + "/" + new Date().getDate() + "/" + new Date().getFullYear());
	$("#ReconcileDate").val((new Date().getMonth()+1) + "/" + new Date().getDate() + "/" + new Date().getFullYear());
    document.getElementById("account").selectedIndex = 1;
    $("#ReconcileDlgId").hide();
    $("#AssetDlgId").hide();
    $("#AddDlgId").hide();
    $("#EditDlgId").hide();
    $("#CheckBoxForReconcile").prop("checked",false);
    $("#checkNumberForReconcile").prop("disabled",true);
    $("#AssetDate").val((new Date().getMonth()+1) + "/" + new Date().getDate() + "/" + new Date().getFullYear());
});
$('tr').click(function () {
    var selected = $(this).hasClass("highlight"); 
    $("tr").removeClass("highlight");
    if(!selected)
        $(this).addClass("highlight");
});
$(function() {
	
	$("#AssetDate" ).datepicker();
	$("#ReconcileDate" ).datepicker();
});

$(function() {
	   $( "#ReconcileButton").on("click", function(){
		  debugger;
		  var bankAccountCombo = document.getElementById("account");
		  $("#ReconcileBankName").html("Reconcile"+ " "+bankAccountCombo.options[bankAccountCombo.selectedIndex].text);
		  
		   $( "#ReconcileDlgId").dialog({
	    	   modal: true,
	    	   title: 'Reconcile'
	        });   
	    });
	    
	   $(document.forms[0]).submit(function(event) {
		    event.preventDefault();
		});
	 });
$(function() {
	   $( "#AssetButton").on("click", function(){
		  debugger;
		
		   $( "#AssetDlgId").dialog({
	    	   modal: true,
	    	   title: 'Set Assest Amount'
	        });   
	    });
	    
	   $(document.forms[0]).submit(function(event) {
		    event.preventDefault();
		});
	 });
/* $(function() {
	   $( "#AddButton").on("click", function(){
		  debugger;
		
		   $( "#AddDlgId").dialog({
	    	   modal: true,
	    	   title: 'Add Transaction'
	        });   
	    });
	    
	   $(document.forms[0]).submit(function(event) {
		    event.preventDefault();
		});
	 }); */
	 $(function() {
		   $( "#AddButton").on("click", function(){
			  debugger;

				return gotobankandadddialog();
		    });
		 });
$(function() {
	   $( "#EditButton").on("click", function(){
		  debugger;
		
		   $( "#EditDlgId").dialog({
	    	   modal: true,
	    	   title: 'Edit Transaction'
	        });   
	    });
	    
	   $(document.forms[0]).submit(function(event) {
		    event.preventDefault();
		});
	 });
function editTransaction()
{
	debugger;
	var oldClientVendorId;
	var oldAccountId;
	var oldPaymentTypeId;
	
	if(tableName == "Payment")
	{	
		oldClientVendorId = $('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(4)').attr('value');
		oldAccountId = $('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(8)').attr('value');
		oldPaymentTypeId = $('table.devPaymentsTable tbody tr:nth-child('+inv+')').find('td:nth-child(7)').attr('value');
	}
	else
	{
		oldClientVendorId = $('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(4)').attr('value');
		oldAccountId = $('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(8)').attr('value');
		oldPaymentTypeId = $('table.devDepositTable tbody tr:nth-child('+inv+')').find('td:nth-child(7)').attr('value');
	}
	
	var customerIdString = document.getElementById("payerForEdit");
	var customerId = customerIdString.options[customerIdString.selectedIndex].value;
	
	var paymenttypeIdString = document.getElementById("payMethodForEdit");
	var paymentTypeId = paymenttypeIdString.options[paymenttypeIdString.selectedIndex].value;
	
	var accountIdString = document.getElementById("payeeForEdit");
	var accountId = accountIdString.options[accountIdString.selectedIndex].value;
	
	var categoryIdString = document.getElementById("categoryForEdit");
	var categoryId = categoryIdString.options[categoryIdString.selectedIndex].value; 
	
	var paymenTypeNameString = document.getElementById("payMethodForEdit");
	var paymentTypeName = paymenTypeNameString.options[paymenTypeNameString.selectedIndex].text; 
	
	var checkNumber = document.getElementById("checkNumberEdit").value;
	var amount = document.getElementById("amountToEdit").value;
	
	var date = document.getElementById("devDateForEdit").value;
	
	var TblPayment={
			   "cvID":customerId,
			   "paymentTypeID":paymentTypeId,	   
			   "accountID":accountId,
			   "categoryID":categoryId,
			   "oldclientVendorID":oldClientVendorId,
			   "oldAccountID":oldAccountId,
			   "paymentTypeName":paymentTypeName,
			   "oldPaymentTypeId":oldPaymentTypeId,
			   "checkNumber":checkNumber,
	   };
	
	var obj=JSON.stringify(TblPayment);
	$('#EditDlgId').dialog('close'); 
	$.ajax({
		
		type : "POST",
		url : "ReconsilationPost?tabid=EditTransaction",
		data : "row=" + obj + "&PaymentId=" + paymentId + "&amount=" +amount + "&date=" + date + "&tableName=" +tableName,
	    success : function(data) {
			/* var html = "" + data.msg; */
			debugger;   
			updateReconsilationTab(data);
		
		},
		 error : function(data) {

			return showerrordialog();
		} 
	});
}
function executeQuery()
{
	debugger;
	var accountCombo = document.getElementById("account");
	var accountId = accountCombo.options[accountCombo.selectedIndex].value;
	
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	
	var TblPayment={
			  
			 "accountID":accountId,
			 "fromDate":fromDate,
			 "toDate":toDate
	};
   var obj=JSON.stringify(TblPayment); 
	$.ajax({
		
		type : "POST",
	 	url : "ReconsilationPost?tabid=Payment",
		data :"data=" + obj,
	    success : function(data) {
			debugger;
	    	
			updateReconsilationTab(data);
		},
		 error : function(data) {

			 return showerrordialog();
		} 
	}); 
}
function deletePayment()
{
	if(paymentId == -1)
	{

		return selectpaymentdialog();
		return false;
	}
	/* var status = window.confirm("<spring:message code='BzComposer.reconciliation.liketoremovetransaction'/>");
	if(status == true)
	{	
	
		$.ajax({
				
				type : "POST",
			 	url : "Reconsilation?tabid=DeletePayment",
				data :"PaymentId=" + paymentId + "&AccountId=" +acId,
			    success : function(data) {
					debugger;
			    	
					updateReconsilationTab(data);
					paymentId = -1;
				},
				 error : function(data) {

					return showerrordialog();
				} 
			});
	}
	else{
		return false;
	} */
	
	debugger;
	event.preventDefault();
	$("#removeselectedtransactiondialog").dialog({
	    	resizable: false,
	        height: 200,
	        width: 500,
	        modal: true,
	        buttons: {
	        	"<spring:message code='BzComposer.global.ok'/>": function () {
	        		$.ajax({
	    				
	    				type : "POST",
	    			 	url : "ReconsilationPost?tabid=DeletePayment",
	    				data :"PaymentId=" + paymentId + "&AccountId=" +acId,
	    			    success : function(data) {
	    					debugger;
	    			    	
	    					updateReconsilationTab(data);
	    					paymentId = -1;
	    				},
	    				 error : function(data) {

	    					return showerrordialog();
	    				} 
	    			});
					/* $('form').submit(); */
	            },
	            "<spring:message code='BzComposer.global.cancel'/>": function () {
	                $(this).dialog("close");
	                return false;
	            }
	        }
	    });
	    return false;
}
function getCategoryAndCharge()
{
	var categoryTypCombo = document.getElementById("CategoryTypeForReconsile");
	var categoryTypeId = categoryTypCombo.options[categoryTypCombo.selectedIndex].value;
	
	var bankAccountCombo = document.getElementById("account");
	var accountId = bankAccountCombo.options[bankAccountCombo.selectedIndex].value;
	TblCategoryType = {
			
		"categoryTypeID":categoryTypeId,	
		"accountID": accountId
	};
	var obj = JSON.stringify(TblCategoryType);
	
$.ajax({
		
		type : "POST",
	 	url : "ReconsilationPost?tabid=CategoryType",
		data :"data1=" + obj,
	    success : function(data) {
			debugger;
	    	
			updateReconsilationTab(data);
		},
		 error : function(data) {

			return showerrordialog()
		} 
	}); 
}
function getCharge()
{
	var categoryCombo = document.getElementById("CategoryForReconsile");
	var categoryId = categoryCombo.options[categoryCombo.selectedIndex].value;
	
	/* var categoryTypCombo = document.getElementById("CategoryTypeForReconsile");
	var categoryTypeId = categoryTypCombo.options[categoryTypCombo.selectedIndex].value; */
	
	var bankAccountCombo = document.getElementById("account");
	var accountId = bankAccountCombo.options[bankAccountCombo.selectedIndex].value;
	
	TblCategory = {
			
		"id":categoryId,
		"accountID": accountId
	};
	/* TblCategoryType = {
			
			"categoryTypeID":categoryTypeId	
		}; */
	/* var obj1 = JSON.stringify(TblCategoryType); */
	var obj = JSON.stringify(TblCategory);
	
$.ajax({
		
		type : "POST",
	 	url : "ReconsilationPost?tabid=CategoryType",
		data :"data2=" + obj,
	    success : function(data) {
			debugger;
	    	
			/* updateReconsilationTab(data); */
			updateChargeDropdown(data);
		},
		 error : function(data) {

			return showerrordialog()
		} 
	}); 
}
function updateChargeDropdown(data)
{
	$(document).find('div#ChrgeForReconsileDiv select').replaceWith($(data).find("div#ChrgeForReconsileDiv").html());
}
function getCheckboxForReconcile()
{
	if($("#CheckBoxForReconcile").prop("checked"))
	{
		$("#checkNumberForReconcile").prop("disabled",false);
	}
	else
	{
		$("#checkNumberForReconcile").prop("disabled",true);
	}
}
function AddFromReconcileDlg()
{
	debugger;
	
	var checkNumber = "";
	var categoryTypeCombo = document.getElementById("CategoryTypeForReconsile");
	var categoryTypeId = categoryTypeCombo.options[categoryTypeCombo.selectedIndex].value;
	var categoryCombo = document.getElementById("CategoryForReconsile");
	var categoryId = categoryCombo.options[categoryCombo.selectedIndex].value;
	
	var chargeForreconcileCombo = document.getElementById("ChrgeForReconsile");
	if(chargeForreconcileCombo.length > 0)
	{	
		var chargeForReconcileCategoryId = chargeForreconcileCombo.options[chargeForreconcileCombo.selectedIndex].value;
	}	
	else
	{

		return entervalidnamedialog();
		return false;
	}
	
	var date = $("#ReconcileDate").val();
	var amount = $("#AmountForReconcileDlg").val();
	if(amount == '')
	{

		return entervalidamountdialog();
		return false;
	}
	var checkboxForReconcile = $("#CheckBoxForReconcile").prop("checked");
	
	if(checkboxForReconcile)
	{
		checkNumber = $("#checkNumberForReconcile").val();
		if(checkNumber == '' || checkNumber == '0')
		{

			return entervalidchecknumberdialog();
			return false;
		}
	}
	var bankAccountCombo = document.getElementById("account");
	var accountId = bankAccountCombo.options[bankAccountCombo.selectedIndex].value;
	var customerCurrentbalance = bankAccountCombo.options[bankAccountCombo.selectedIndex].label
	
	TblPayment = {
			"accountID":accountId ,
			"categoryId":chargeForReconcileCategoryId ,
			"amount":amount,
			"selectedCheckbox":checkboxForReconcile,
			"checkNumber":checkNumber,
			"fromDate":date 
	};
	var obj = JSON.stringify(TblPayment);
	 $('#ReconcileDlgId').dialog('close');
$.ajax({
		
		type : "POST",
	  	url : "ReconsilationPost?tabid=AddReconcile",
		data :"data3=" + obj,
	    success : function(data) {
			debugger;
	    	
			updateReconsilationTab(data);
		},
		 error : function(data) {

			return showerrordialog();
		} 
	}); 
}
function changeAssetCategory()
{
	debugger;
	var categoryCombo = document.getElementById("AssetCategoryId");
	var categoryId = categoryCombo.options[categoryCombo.selectedIndex].value; 
	
	var bankAccountCombo = document.getElementById("account");
	var accountId = bankAccountCombo.options[bankAccountCombo.selectedIndex].value;
	
	TblCategory = {
			"id":categoryId,
			"accountID": accountId
	};
	var obj = JSON.stringify(TblCategory);
$.ajax({
		
		type : "POST",
	  	url : "ReconsilationPost?tabid=SubAssetCategory",
		data :"data4=" + obj,
	    success : function(data) {
			debugger;
	    	
			$(document).find('div#AssetChargeCategoryDiv select').replaceWith($(data).find("div#AssetChargeCategoryDiv").html());
		},
		 error : function(data) {
			 return showerrordialog();
		} 
	}); 
}
function AddAssets()
{
	debugger;
	 $('#AssetDlgId').dialog('close');
	 $("#AssetAmount").val("");
	 $("#AssetCategoryId").val($("#AssetCategoryId option:first").val());
	 $("#ChrgeIdForAsset").children('option').hide();
	 $("#AssetDescription").val("");
}
function addNewSituation()
{

	return gotobankandeditdialog();
	return false;
}
function addCss()
{
	$('tr').click(function () {
	    var selected = $(this).hasClass("highlight"); 
	    $("tr").removeClass("highlight");
	    if(!selected)
	        $(this).addClass("highlight");
	});
}
function updateReconsilationTab(data)
{
	$(document).find('div#PaymentsTable table').replaceWith($(data).find('div#PaymentsTable').html());
	$(document).find('div#DepositsTable table').replaceWith($(data).find('div#DepositsTable').html());
	$(document).find('div#CategoryForReconsileDiv select').replaceWith($(data).find("div#CategoryForReconsileDiv").html());
	$(document).find('div#ChrgeForReconsileDiv select').replaceWith($(data).find("div#ChrgeForReconsileDiv").html());
	
	if($(data).find('div#paymentsCountBottom label').eq(0).text(this.value).text() == "")
	{	
		$(document).find('div#paymentsCountBottom label').eq(0).text(this.value).replaceWith("<label id='paymentCount'>"+0+"</label>");
	}
	else
	{
		$(document).find('div#paymentsCountBottom label').eq(0).text(this.value).replaceWith($(data).find('div#paymentsCountBottom label').eq(0).text(this.value));
	}
	if($(data).find('div#paymentsTotalBottom label').eq(0).text(this.value).text() == "")
	{
		$(document).find('div#paymentsTotalBottom label').eq(0).text(this.value).replaceWith("<label id='paymentTotal'>"+0.00+"</label>");
	}
	else
	{
		$(document).find('div#paymentsTotalBottom label').eq(0).text(this.value).replaceWith($(data).find('div#paymentsTotalBottom label').eq(0).text(this.value));
	}
	if($(data).find('div#depositsCountBottom label').eq(0).text(this.value).text() == "")
	{
		$(document).find('div#depositsCountBottom label').eq(0).text(this.value).replaceWith("<label id='depositCount'>"+0+"</label>");
	}
	else
	{
		$(document).find('div#depositsCountBottom label').eq(0).text(this.value).replaceWith($(data).find('div#depositsCountBottom label').eq(0).text(this.value));
	}
	if($(data).find('div#depositsTotalBottom label').eq(0).text(this.value).text() == "")
	{
		$(document).find('div#depositsTotalBottom label').eq(0).text(this.value).replaceWith("<label id='depositTotal'>"+0.00+"</label>");
	}
	else
	{
		$(document).find('div#depositsTotalBottom label').eq(0).text(this.value).replaceWith($(data).find('div#depositsTotalBottom label').eq(0).text(this.value));
	}
	$("#AmountForReconcileDlg").val('');
	$("#ReconcileDate").val((new Date().getMonth()+1) + "/" + new Date().getDate() + "/" + new Date().getFullYear());
	$("#CheckBoxForReconcile").prop("checked",false);
	$("#checkNumberForReconcile").prop("disabled",true);
	$("#AssetAmount").val("");
	$("#AssetCategoryId").val($("#AssetCategoryId option:first").val());
	$("#AssetDescription").val("");
	addCss();
}
$( function() {
    $( "#tabs" ).tabs();
  });
function gotobankandadddialog()
{
	event.preventDefault();
	$("#gotobankandadddialog").dialog({
    	resizable: false,
        height: 200,
        width: 500,
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
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function selectpaymentdialog()
{
	event.preventDefault();
	$("#selectpaymentdialog").dialog({
    	resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function entervalidnamedialog()
{
	event.preventDefault();
	$("#entervalidnamedialog").dialog({
    	resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function entervalidamountdialog()
{
	event.preventDefault();
	$("#entervalidamountdialog").dialog({
    	resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function entervalidchecknumberdialog()
{
	event.preventDefault();
	$("#entervalidchecknumberdialog").dialog({
    	resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function gotobankandeditdialog()
{
	event.preventDefault();
	$("#gotobankandeditdialog").dialog({
    	resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}


function getDateVisePayment(status)
{
if(status == 'backward')
{	
	fromDate = $("#fromDate").val();
	if(new Date(this.fromDate).getMonth() == 2)
	{
		this.fromDate = new Date(this.fromDate).getMonth() + "/" + 28 + "/" + new Date(this.fromDate).getFullYear();
		this.fromDate = 2 + "/" + new Date(this.fromDate).getDate() + "/" + new Date(this.fromDate).getFullYear();
	}
	else if(this.fromDate.substring(0) == '0')
	{
		this.fromDate = 12 + "/" + 28 + "/" + this.fromYear - 1;
		this.fromDate = new Date(this.fromDate).getMonth() + "/" + new Date(this.fromDate).getDate() + "/" + new Date(this.fromDate).getFullYear();
	} 
	else
	{	
		this.fromDate = new Date(this.fromDate).getMonth() + "/" + new Date(this.fromDate).getDate() + "/" + new Date(this.fromDate).getFullYear();
		if(this.fromDate.substring(0,1) == '0')
		{
			this.fromDate = 12 + "/" + 28 + "/" + (fromYear-1);
			this.fromYear = (fromYear-1);
		}
	}	
	toDate = $("#toDate").val();
	if(new Date(this.toDate).getMonth() == 2)
	{
		this.toDate = new Date(this.toDate).getMonth() + "/" + 28 + "/" + new Date(this.toDate).getFullYear();
		this.toDate = 2 + "/" + new Date(this.toDate).getDate() + "/" + new Date(this.toDate).getFullYear();
	}
	else if(this.toDate.substring(0) == '0')
	{
		this.toDate = 12 + "/" + 28 + "/" + this.toYear - 1;
		this.toDate = new Date(this.toDate).getMonth() + "/" + new Date(this.toDate).getDate() + "/" + new Date(this.toDate).getFullYear();
	} 
	else if(new Date(this.toDate).getDate() == 31)
	{
		this.toDate = new Date(this.toDate).getMonth() + "/" + (new Date(this.toDate).getDate()-1) + "/" + new Date(this.toDate).getFullYear();
	}	
	else
	{	
		this.toDate = new Date(this.toDate).getMonth() + "/" + new Date(this.toDate).getDate() + "/" + new Date(this.toDate).getFullYear();
		if(this.toDate.substring(0,1) == '0')
		{
			this.toDate = 12 + "/" + 28 + "/" + (toYear-1);
			this.toYear = (toYear-1);
		}
	}	
	$("#fromDate").val(this.fromDate);
	$("#toDate").val(this.toDate);
	/* executeQuery(); */
}
 else
{
	 fromDate = $("#fromDate").val();
	if((new Date(this.fromDate).getMonth()+2) == 2)
	{
		this.fromDate = (new Date(this.fromDate).getMonth()+2) + "/" + 28 + "/" + new Date(this.fromDate).getFullYear();
		this.fromDate = 2 + "/" + new Date(this.fromDate).getDate() + "/" + new Date(this.fromDate).getFullYear();
	}
	else if(this.fromDate.substring(0) == '0')
	{
		this.fromDate = 12 + "/" + 28 + "/" + this.fromYear - 1;
		this.fromDate = (new Date(this.fromDate).getMonth()+2) + "/" + new Date(this.fromDate).getDate() + "/" + new Date(this.fromDate).getFullYear();
	} 
	else
	{	
		this.fromDate = (new Date(this.fromDate).getMonth()+2) + "/" + new Date(this.fromDate).getDate() + "/" + new Date(this.fromDate).getFullYear();
		if(this.fromDate.substring(0,1) == '11')
		{
			this.fromDate = 12 + "/" + 28 + "/" + (fromYear-1);
			this.fromYear = (fromYear-1);
		}
		if(this.fromDate.substring(0,2) == '13')
		{
			this.fromDate = 1 + "/" + 28 + "/" + (fromYear+1);
			this.fromYear = fromYear + 1;
		}
	}	
	toDate = $("#toDate").val();
	if((new Date(this.fromDate).getMonth()+2) == 2)
	{
		this.toDate = (new Date(this.fromDate).getMonth()+2) + "/" + 28 + "/" + new Date(this.toDate).getFullYear();
		this.toDate = 2 + "/" + new Date(this.toDate).getDate() + "/" + new Date(this.toDate).getFullYear();
	}
	else if(this.toDate.substring(0) == '0')
	{
		this.toDate = 12 + "/" + 28 + "/" + this.toYear - 1;
		this.toDate = (new Date(this.fromDate).getMonth()+2) + "/" + new Date(this.toDate).getDate() + "/" + new Date(this.toDate).getFullYear();
	} 
	else if(new Date(this.toDate).getDate() == 31)
	{
		this.toDate = (new Date(this.fromDate).getMonth()+2) + "/" + (new Date(this.toDate).getDate()-1) + "/" + new Date(this.toDate).getFullYear();
	}	
	else
	{	
		this.toDate = (new Date(this.fromDate).getMonth()+2) + "/" + new Date(this.toDate).getDate() + "/" + new Date(this.toDate).getFullYear();
		if(this.toDate.substring(0,1) == '11')
		{
			this.toDate = 12 + "/" + 28 + "/" + new Date(this.toDate).getFullYear();
			
		}
		if(this.toDate.substring(0,2) == '13')
		{
			this.toDate = 1 + "/" + 28 + "/" + (toYear+1);
			this.toYear = toYear + 1;
		}
	}	
	$("#fromDate").val(this.fromDate);
	$("#toDate").val(this.toDate);
}
 debugger;
	executeQuery();									//calling method to execute
}
</script>
</body>
</html>
<!-- Dialog box used in this page -->
<div id="gotobankandadddialog" style="display:none;">
	<p><spring:message code='BzComposer.reconciliation.gotobankingtabandaddbutton'/></p>
</div>
<div id="showerrordialog" style="display:none;">
	<p><spring:message code='BzComposer.reconciliation.someerroroccurred'/></p>
</div>
<div id="selectpaymentdialog" style="display:none;">
	<p><spring:message code='BzComposer.reconciliation.selectpaymentfirst'/></p>
</div>
<div id="removeselectedtransactiondialog" style="display:none;">
	<p><spring:message code='BzComposer.reconciliation.liketoremovetransaction'/></p>
</div>
<div id="entervalidnamedialog" style="display:none;">
	<p><spring:message code='BzComposer.reconciliation.changenamecantempty'/></p>
</div>
<div id="entervalidamountdialog" style="display:none;">
	<p><spring:message code='BzComposer.reconciliation.entervalidamount'/></p>
</div>
<div id="entervalidchecknumberdialog" style="display:none;">
	<p><spring:message code='BzComposer.reconciliation.entervalidchecknumber'/></p>
</div>
<div id="gotobankandeditdialog" style="display:none;">
	<p><spring:message code='BzComposer.reconciliation.gotocategorymanagerandeditbutton'/></p>
</div>