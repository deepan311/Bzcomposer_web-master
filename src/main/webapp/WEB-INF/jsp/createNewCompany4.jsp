<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="include/header.jsp"%>
<link href="${pageContext.request.contextPath}/dist/css/custom.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/custom.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->

<!-- For dialog -->
<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />

<title><spring:message code="BzComposer.addnewcompanystep4title" /></title>
<style type="text/css">
ul, #myUL {
  list-style-type: none;
}
/* Remove margins and padding from the parent ul */
#myUL {
  margin: 0;
  padding: 0;
}
/* Style the caret/arrow */
.caret, .caret2 {
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
/* Rotate the caret/arrow icon when clicked on (using JavaScript) */
.caret-down::before {
  transform: rotate(90deg); 
}
/* Hide the nested list */
.nested, .nested2 {
  display: none;
  margin-left: 20px;
  padding: 0px;
}

ul.nested li{list-style: circle !important;}
ul.nested li:hover{ background-color: #ccc; color: #000; }
ul.nested2 li:hover{ background-color: #ccc; color: #000; }

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
	
	<!-- dialog box that used in this page -->
	<div id="quitMessage" style="display:none;font-size:1em;">
		<p><spring:message code="bca.quitmessage"/></p>
	</div>
	
	<div class="bca_createnewcompanyright" style="background-color: #fff;">
		<div class="bca_createnewcompanyright_title">
			<h3><spring:message code="BzComposer.addnewcompanystep4title" /></h3>
		</div>
		<div class="bca_createnewcompanyright_body" style="height: 640px;">
			<div class="bca_creatingchatsofaccounts">
				<div class="bca_bankaccount_left">
					<div class="bca_bankaccounttitle">
						<img src="${pageContext.request.contextPath}/images/folder_open.png"
						height="20px" width="20px" /> 
						<spring:message code="bca.bankaccounts"/>
					</div>
					<div class="bca_bankaccountlist">
						<%-- <ul id="testul">
						<<c:forEach items="${bca_acctcategory}" var="objList">
							<li id="${curObject.vAcctCategory_ID}" onclick="vAcctCategory();">
							${curObject.vAcctCategory_Name}
							</li>
						</c:forEach>
						</ul> --%>
						<ul id="myUL">
							<c:forEach items="${bca_acctcategory}" var="curObject" >
								<li class="caret" id="${curObject.vAcctCategory_ID}" onclick="vAcctCategory();">
								    ${curObject.vAcctCategory_Name}
									<ul class="nested" id="nested">
							    	</ul>
								</li>
							</c:forEach>
							  <!-- <li><span class="caret">Beverages</span>
							    <ul class="nested">
							      <li>Water</li>
							      <li>Coffee</li>
							    </ul>
							  </li> -->
							  <!-- <li><span class="caret2">Beverages</span>
							    <ul class="nested2">
							      <li>Water</li>
							      <li>Coffee</li>
							    </ul>
							  </li> -->
						</ul>
					</div>
				</div>	
				<div class="bca_bankaccount_right"> 
					<div class="bca_accountinformationtitle">
						<h2><spring:message code="bca.AccountInformation" /></h2>
					</div>
					<div class="bca_accountinformationbody">
						<table>
							<tbody>
								<tr>
									<td><spring:message code="bca.AccountName" />:</td>
									<td><input type="text" id="accname" name="vAcctType_Name"> </td>
								</tr>
								<tr>
									<td><spring:message code="bca.Category" />:</td>
									<td>
										<select class="selecttest" id="selecttest">
											<c:forEach items="${bca_acctcategory}" var="curObject">
												<option value="${curObject.vAcctCategory_ID}" id="combo">
													${curObject.vAcctCategory_Name}
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td><spring:message code="bca.StartingBalance" />:</td>
									<td><input type="text" value="0"></td>
								</tr>
							</tbody>
						</table>	
						<div class="bca_accountinformationbody_button">
							<a>
								<spring:message code="BzComposer.global.new"/>
							</a>
							<a onclick="add();" id="bca_add">
								<spring:message code="BzComposer.global.add"/>
							</a>
							<a>
								<spring:message code="BzComposer.global.delete"/>
							</a>
						</div>									
					</div>
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
	document.forms[0].action = "CompanyNew?tabid=createNewCompany5";
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
                $(this).dialog("close");
                window.history.go(-4);
            },
            "<spring:message code='bca.quitmessage.no'/>": function () {
                $(this).dialog("close");
            }
        }
    });
}
</script>	
<script type="text/javascript">
var items = document.querySelectorAll(".bca_bankaccountlist #nested li");
for(var i=0; i< items.length; i++)
{
	items[i].onclick = function(){
		document.getElementById("accname").value = this.innerHTML;
	};
}
</script>
<script>
/* $(".caret").click(function(){
    $(".nested").show();
    this.classList.show("caret-down");
}); */
$('.caret').on('DOMSubtreeModified', function(event) {
    // dos tuff
	 $(".nested").show();
	// this.classList.show("caret-down");
});
/* $(".caret2").click(function(){
    $(".nested2").toggle();
    this.classList.toggle("caret-down");
});
*/
</script>
</body>
</html>