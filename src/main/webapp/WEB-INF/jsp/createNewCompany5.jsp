<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="ISO-8859-1"> -->
<%@include file="include/header.jsp"%>
<link href="${pageContext.request.contextPath}/dist/css/custom.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/custom.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->

<!-- For dialog -->
<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />

<title><spring:message code="BzComposer.addnewcompanystep5title" /></title>
<style type="text/css">
.active{ background-color: black; color: #fff;}
ul, #myUL {
  list-style-type: none;
}
/* Remove margins and padding from the parent ul */
#myUL {
  margin: 0;
  padding: 0;
}

/* Style the caret/arrow */
.caret, .caret2, .caret3, .caret4, .caret5, .caret6,  .caret7 {
  cursor: pointer; 
  user-select: none; /* Prevent text selection */
}
/* Create the caret/arrow with a unicode, and style it */
.caret::before{
  content: "\25B6";
  color: black;
  display: inline-block;
  margin-right: 6px;
}
.caret2::before, .caret5::before, .caret6::before, .caret7::before {
  content: "\25B6";
  color: black;
  display: inline-block;
  margin-right: 6px;
}
.caret3::before{
  content: "\25B6";
  color: black;
  display: inline-block;
  margin-right: 6px;
}
.caret4::before{
  content: "\25B6";
  color: black;
  display: inline-block;
  margin-right: 6px;
}
/* Rotate the caret/arrow icon when clicked on (using JavaScript) */
.caret-down::before {
  transform: rotate(90deg); 
}

/* Hide the nested list */
.nested, .nested2, .nested3, .nested4, .nested5, .nested6, .nested7  {
  display: none;
  margin-left: 20px;
  padding: 0px;
}

ul.nested li{list-style: circle !important;}
ul.nested2 li{list-style: circle !important;}
ul.nested3 li{list-style: circle !important;}
ul.nested4 li{list-style: circle !important;}
ul.nested5 li{list-style: circle !important;}
ul.nested6 li{list-style: circle !important;}
ul.nested7 li{list-style: circle !important;}

ul.nested li:hover{ background-color: #ccc; color: #000; }
ul.nested2 li:hover{ background-color: #ccc; color: #000; }
ul.nested3 li:hover{ background-color: #ccc; color: #000; }
ul.nested4 li:hover{ background-color: #ccc; color: #000; }
ul.nested5 li:hover{ background-color: #ccc; color: #000; }
ul.nested6 li:hover{ background-color: #ccc; color: #000; }
ul.nested7 li:hover{ background-color: #ccc; color: #000; }

/* Show the nested list when the user clicks on the caret/arrow (with JavaScript) */
.active {
  display: block;
}
</style>
</head>
<body>
<form action="CompanyNew?tabid=createNewCompany5" method="post">
	<div class="bca_createnewcompanyimgleft">
		<img alt="" src="images/newCompany1.png" height="701px">
	</div>
	<div class="bca_createnewcompanyright" style="background-color: #fff;">
		<div class="bca_createnewcompanyright_title">
			<h3><spring:message code="BzComposer.addnewcompanystep5title" /></h3>
		</div>
		
		<!-- dialog box that used in this page -->
		<div id="quitMessage" style="display:none;font-size:1em;">
			<p><spring:message code="bca.quitmessage"/></p>
		</div>
		
		<div class="bca_createnewcompanyright_body" style="height: 640px;">
		<div class="bca_creatingchatsofaccounts">
			<div class="bca_setuppreference_left">
				<div class="bca_bankaccounttitle">
					<img src="${pageContext.request.contextPath}/images/folder_open.png" height="20px" width="20px" />
					<spring:message code="bca.preference"/>
				</div>
				<div class="bca_preferencelist">
					<%--<ul id="testul"><c:forEach items="${bca_acctcategory}" var="objList" ><li id="${curObject.vAcctCategory_ID}" onclick="vAcctCategory();">${curObject.vAcctCategory_Name}</li></c:forEach></ul> --%>
						<ul id="myUL">
							<li class="caret" id="1">
								<spring:message code="bca.terms"/>
								<ul class="nested" id="nested">
									<c:if test="${not empty CompanyInfoForm.vListTerm_Name}">
										<c:forEach items="${CompanyInfoForm.vListTerm_Name}" var="objList">
											<li id="${objList.termId}">
												${objList.vTerm_Name}
											</li>
										</c:forEach>
									</c:if>
								</ul>
							</li>
							<li class="caret2" id="2">
								<spring:message code="bca.salesrepresentative"/>
								<ul class="nested2" id="nested">
									<%-- <c:forEach items="${addSalesRep}" var="objList"><li id="${curObject.vSalesRep_ID}">${curObject.vSalesRep_Name}</li></c:forEach>  --%>
									<c:if test="${not empty CompanyInfoForm.vListSalesRepName}">
										<c:forEach items="${CompanyInfoForm.vListSalesRepName}" var="objList">
											<li id="${objList.vSalesRep_ID}">
												${objList.vSalesRep_Name}
											</li>
										</c:forEach>
									</c:if>
								</ul>
							</li>
							<li class="caret3" id="3">
								<spring:message code="bca.itemcategory"/>
								<ul class="nested3" id="nested">
									<%-- <c:forEach items="${addItemCategory}" var="objList" ><li id="${curObject.vItemCategory_Name}">${curObject.vItemCategory_Name}</li></c:forEach>  --%>
									<c:if test="${not empty CompanyInfoForm.vListItemCatName}">
										<c:forEach items="${CompanyInfoForm.vListItemCatName}" var="objList">
											<li id="${objList.vItemCategory_Name}">
												${objList.vItemCategory_Name}
											</li>
										</c:forEach>
									</c:if>
								</ul>
							</li>
							<li class="caret4" id="4">
								<spring:message code="bca.creditcardtype"/>
								<ul class="nested4" id="nested">
									<%-- <c:forEach items="${initCCType}" var="objList" ><li id="${curObject.commonid}"><${curObject.name}</li></c:forEach>  --%>
									<c:if test="${not empty CompanyInfoForm.vCCType_Name}">
										<c:forEach items="${CompanyInfoForm.vCCType_Name}" var="objList" >
											<li id="${objList.commonid}">
												${objList.name}
											</li>
										</c:forEach>
									</c:if>
								</ul>
							</li>
							<li class="caret5" id="5">
								<spring:message code="bca.paymenttype"/>
								<ul class="nested5" id="nested">
									<%-- <c:forEach items="${initPaymentType}" var="objList" ><li id="${curObject.commonid}">${curObject.name}</li></c:forEach>  --%>
									<c:if test="${not empty CompanyInfoForm.vPaymentType}">
										<c:forEach items="${CompanyInfoForm.vPaymentType}" var="objList">
											<li id="${objList.commonid}">
												${objList.name}
											</li>
										</c:forEach>
									</c:if>
								</ul>
							</li>
							<li class="caret6" id="6">
								<spring:message code="bca.shipcarrier"/>
								<ul class="nested6" id="nested">
									<%-- <c:forEach items="${shipCarrierType}" var="objList"><li id="${curObject.commonid}">${curObject.name}</li></c:forEach>  --%>
									<c:if test="${not empty CompanyInfoForm.vShipCarrier}">
										<c:forEach items="${CompanyInfoForm.vShipCarrier}" var="objList">
											<li id="${objList.vShipCarrier_ID}">
												${objList.vShipCarrier_Name}
											</li>
										</c:forEach>
									</c:if>
								</ul>
							</li>
							<li class="caret7" id="7">
								<spring:message code="bca.receivedtypes"/>
								<ul class="nested7" id="nested">
									<%-- <c:forEach items="${masterReceivedTypes}" var="objList"><li id="${curObject.commonid}">${curObject.name}</li></c:forEach>  --%>
									<c:if test="${not empty CompanyInfoForm.vListReceivedType}">
										<c:forEach items="${CompanyInfoForm.vListReceivedType}" var="objList">
											<li id="${objList.commonid}">
												${objList.name}
											</li>
										</c:forEach>
									</c:if>
								</ul>
							</li>
						<!--<li><span class="caret">Beverages</span><ul class="nested"><li>Water</li><li>Coffee</li></ul></li> -->
					  <!-- <li><span class="caret2">Beverages</span><ul class="nested2"><li>Water</li><li>Coffee</li></ul></li> -->
						</ul>
					</div>
				</div>	
				<div class="bca_twobutton">
					<ul>
						<li class="bca_setuppreference_add" id="add">
							<spring:message code="bca.addarrows"/>
						</li>
						<li class="bca_setuppreference_remove">
							<spring:message code="bca.removearrows"/>
						</li>
					</ul>
				</div>
				<div class="bca_bankaccount_right" id="bca_initTerm"> 
					<!-- term -->
					<div class="bca_accountinformationtitle">
						<h2 id="bca_bca_setuppreference_rightbox_title">
							<spring:message code="bca.term"/>
						</h2>
					</div>
					<div class="bca_accountinformationbody">
						<table id="pref_terms">
							<tbody>
								<tr>
									<td><spring:message code="bca.name"/>:</td>
									<td><input type="text" id="caret" name="vTerm_Name"></td>
								</tr>
								<tr>
									<td><spring:message code="bca.days"/>:</td>
									<td><input type="text" id="term_day" name="vTerm_Days"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- term over -->
				</div>
				<div class="bca_bankaccount_right" id="bca_initSalesRep"> 
					<!-- term -->
					<div class="bca_accountinformationtitle">
						<h2 id="bca_bca_setuppreference_rightbox_title">
							<spring:message code="bca.salesrep"/>
						</h2>
					</div>
					<div class="bca_accountinformationbody">
						<table id="pref_terms">
							<tbody>
								<tr>
									<td><spring:message code="bca.name"/>:</td>
									<td><input type="text" id="salerep_"></td>
								</tr>				
							</tbody>
						</table>
					</div>
				</div>
				<div class="bca_bankaccount_right" id="bca_initItenCategory"> 
					<!-- term -->
					<div class="bca_accountinformationtitle">
						<h2 id="bca_bca_setuppreference_rightbox_title">
							<spring:message code="bca.itemcategory"/>
						</h2>
					</div>
					<div class="bca_accountinformationbody">
						<table id="pref_terms">
							<tbody>
								<tr>
									<td><spring:message code="bca.name"/>:</td>
									<td><input type="text" id="itemcate_"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- term over -->
				</div>
				<div class="bca_bankaccount_right" id="bca_initCreditCardType"> 
					<!-- term -->
					<div class="bca_accountinformationtitle">
						<h2 id="bca_bca_setuppreference_rightbox_title">
							<spring:message code="bca.creditcardtype"/>
						</h2>
					</div>
					<div class="bca_accountinformationbody">
						<table id="pref_terms">
							<tbody>
								<tr>
									<td><spring:message code="bca.name"/>:</td>
									<td><input type="text" id="creditcardtype_"></td>
								</tr>
							</tbody>
						</table>
					</div>	
					<!-- term over -->
				</div>	
				<div class="bca_bankaccount_right" id="bca_initPaymentType"> 
					<!-- term -->
					<div class="bca_accountinformationtitle">
						<h2 id="bca_bca_setuppreference_rightbox_title">
							<spring:message code="bca.paymenttype"/>
						</h2>
					</div>
					<div class="bca_accountinformationbody">
						<table id="pref_terms">
							<tbody>
								<tr>
									<td><spring:message code="bca.name"/>:</td>
									<td><input type="text" id="initPaymentType_"></td>
								</tr>
								<tr>
									<td><spring:message code="bca.type"/>:</td>
									<td>
										<select id="initPaymentType">
											<c:forEach items="${CompanyInfoForm.vPaymentType}" var="curObject" >
												<option value="${curObject.commonid}"
												${curObject.name}
											</c:forEach>
										</select>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- term over -->
				</div>	
				<div class="bca_bankaccount_right" id="bca_initShipCarrier"> 
					<!-- term -->
					<div class="bca_accountinformationtitle">
						<h2 id="bca_bca_setuppreference_rightbox_title">
							<spring:message code="bca.shipcarrier"/>
						</h2>
					</div>
					<div class="bca_accountinformationbody">
						<table id="pref_terms">
							<tbody>
								<tr>
									<td><spring:message code="bca.name"/>:</td>
									<td>
										<input type="text" id="initShipCarrier_">
									</td>
								</tr>
							</tbody>				
						</table>				
					</div>
					<!-- term over -->
				</div>	
				<div class="bca_bankaccount_right" id="bca_initReceivedType"> 
					<!-- term -->
					<div class="bca_accountinformationtitle">
						<h2 id="bca_bca_setuppreference_rightbox_title">
							<spring:message code="bca.receivedtypes"/>
						</h2>
					</div>		
					<div class="bca_accountinformationbody">
						<table id="pref_terms">
							<tbody>
								<tr>
									<td><spring:message code="bca.name"/>:</td>
									<td><input type="text" id="initReceivedType_"></td>
								</tr>
								<tr>
									<td><spring:message code="bca.type"/>:</td>
									<td>
										<select id="initReceivedType">
										<%-- <c:forEach items="${masterReceivedTypes}" var="curObject" ><option value="${curObject.commonid}">${curObject.name}</option></c:forEach> --%>
											<c:if test="${not empty CompanyInfoForm.vListReceivedType}">
												<c:forEach items="${CompanyInfoForm.vListReceivedType}" var="curObject">
													<option value="${curObject.commonid}">
													${curObject.name}
													</option>
												</c:forEach>
											</c:if>
										</select>
									</td>
								</tr>					
							</tbody>
						</table>
					</div>
					<!-- term over -->
				</div>	
			</div>
		</div>
	</div>
	<div class="bca_createnewcompanyright_bottom">
		<ul>
			<li>
				<a onclick="history.back(-1)" class="formbutton">
					<spring:message code="BzComposer.global.goback"/>
				</a>
			</li>
			<li>
				<a class="formbutton" onclick="submitform();">
					<spring:message code="BzComposer.global.next"/>
				</a>
			</li>
			<li>
				<a class="formbutton" onclick="finish();">
					<spring:message code="BzComposer.global.finish"/>
				</a>
			</li>
			<li>
				<a class="formbutton" onclick="quit();">
					<spring:message code="BzComposer.global.close"/>
				</a>
			</li>
		</ul>
	</div>
</form>
<script type="text/javascript">
function submitform()
{
	document.forms[0].action = "CompanyNew?tabid=createNewCompany6";
	document.forms[0].submit();
}
function finish() {
	document.forms[0].action = "CompanyNew?tabid=finish3";
	document.forms[0].submit(); 
}
function quit(){
	$("#quitMessage").dialog({
    	resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
        	"<spring:message code='bca.quitmessage.yes'/>": function () {
                $("#quitMessage").dialog("close");
                window.history.go(-5);
            },
            "<spring:message code='bca.quitmessage.no'/>": function () {
                $("#quitMessage").dialog("close");
                return false;
            }
        }
    });
}
</script>	
<script type="text/javascript">

/* debugger;
var items = document.querySelectorAll(".bca_bankaccountlist #nested li");
for(var i=0; i< items.length; i++)
	{
	debugger;
		items[i].onclick = function(){
			debugger;
			document.getElementById("accname").value = this.innerHTML;
		};
} */

$(document).ready(function(){
	$("#bca_initTerm").show();
	$("#bca_initSalesRep").hide();
	$("#bca_initItenCategory").hide();
	$("#bca_initCreditCardType").hide();
	$("#bca_initPaymentType").hide();
	$("#bca_initShipCarrier").hide();
	$("#bca_initReceivedType").hide();	
});
	
$(document).on('click', '.bca_preferencelist #myUL .caret #nested', function(){
	$("#bca_initTerm").show();
	$("#bca_initSalesRep").hide();
	$("#bca_initItenCategory").hide();
	$("#bca_initCreditCardType").hide();
	$("#bca_initPaymentType").hide();
	$("#bca_initShipCarrier").hide();
	$("#bca_initReceivedType").hide();
});
	
$(document).on('click', '.bca_preferencelist #myUL .caret2 #nested', function(){
	$("#bca_initTerm").hide();
	$("#bca_initSalesRep").show();
	$("#bca_initItenCategory").hide();
	$("#bca_initCreditCardType").hide();
	$("#bca_initPaymentType").hide();
	$("#bca_initShipCarrier").hide();
	$("#bca_initReceivedType").hide();
});
	
$(document).on('click', '.bca_preferencelist #myUL .caret3 #nested', function(){
	$("#bca_initItenCategory").show();
	$("#bca_initTerm").hide();
	$("#bca_initSalesRep").hide();
	$("#bca_initCreditCardType").hide();
	$("#bca_initPaymentType").hide();
	$("#bca_initShipCarrier").hide();
	$("#bca_initReceivedType").hide();		
});
	
	
$(document).on('click', '.bca_preferencelist #myUL .caret4 #nested', function(){
	$("#bca_initCreditCardType").show();
	$("#bca_initTerm").hide();
	$("#bca_initSalesRep").hide();
	$("#bca_initItenCategory").hide();
	$("#bca_initPaymentType").hide();
	$("#bca_initShipCarrier").hide();
	$("#bca_initReceivedType").hide();		
});
	
$(document).on('click', '.bca_preferencelist #myUL .caret5 #nested', function(){
	$("#bca_initPaymentType").show();
	$("#bca_initCreditCardType").hide();
	$("#bca_initTerm").hide();
	$("#bca_initSalesRep").hide();
	$("#bca_initItenCategory").hide();
	$("#bca_initShipCarrier").hide();
	$("#bca_initReceivedType").hide();	
});

$(document).on('click', '.bca_preferencelist #myUL .caret6 #nested', function(){
	$("#bca_initShipCarrier").show();
	$("#bca_initCreditCardType").hide();
	$("#bca_initTerm").hide();
	$("#bca_initSalesRep").hide();
	$("#bca_initItenCategory").hide();
	$("#bca_initPaymentType").hide();
	$("#bca_initReceivedType").hide();	
});
	
$(document).on('click', '.bca_preferencelist #myUL .caret7 #nested', function(){
	$("#bca_initReceivedType").show();
	$("#bca_initShipCarrier").hide();
	$("#bca_initCreditCardType").hide();
	$("#bca_initTerm").hide();
	$("#bca_initSalesRep").hide();
	$("#bca_initItenCategory").hide();
	$("#bca_initPaymentType").hide();
});
</script>
<script>
 $(".caret").click(function(){
    $(".nested").show("2000");
    this.classList.show("caret-down");
});
 
$(".caret2").click(function(){
    $(".nested2").show("2000");
    this.classList.show("caret-down");
});
 
$(".caret3").click(function(){
    $(".nested3").show("2000");
    this.classList.show("caret-down");
});

$(".caret4").click(function(){
    $(".nested4").show("2000");
    this.classList.show("caret-down");
});
 
$(".caret5").click(function(){
    $(".nested5").show("2000");
    this.classList.show("caret-down");
});
 
$(".caret6").click(function(){
    $(".nested6").show("2000");
    this.classList.show("caret-down");
});
 
$(".caret7").click(function(){
    $(".nested7").show("2000");
    this.classList.show("caret-down");
});
</script>
</body>
</html>