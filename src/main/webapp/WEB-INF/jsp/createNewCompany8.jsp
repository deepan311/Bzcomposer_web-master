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

<title><spring:message code="BzComposer.addnewcompanystep8title" /></title>
<style type="text/css">
.highlight { background-color: #00CED1 !important;color: #fff }	 
</style>
</head>
<body>
<form action="CompanyNew?tabid=createNewCompany9" method="post" id="newcompany8">
	<div class="bca_createnewcompanyimgleft">
		<img alt="" src="images/newCompany1.png" height="701px">
	</div>
	<div class="bca_createnewcompanyright" style="background-color: #fff;">
		<div class="bca_createnewcompanyright_title">
			<h3><spring:message code="BzComposer.addnewcompanystep8title" /></h3>
		</div>
		
		<!-- dialog box that used in this page -->
		<div id="quitMessage" style="display:none;font-size:1em;">
			<p><spring:message code="bca.quitmessage"/></p>	
		</div>
		<div id="errorOccurred" style="display:none;font-size:1em;">
			<p><spring:message code="BzComposer.common.erroroccurred"/></p>	
		</div>
		
		<div class="bca_createnewcompanyright_body" style="height: 640px;">
				<div class="bca_settingupinventory_top">
				<div class="bca_settingupinventory_top_table">
					<table>
						<tbody>
							<tr>
								<td>Item Code</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width: 275px" name="inventoryCode" id="inventoryCode"></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> Subitem of  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<c:if test="${not empty CompanyInfoForm.vListItemCatName}">
											<select id="parentId" name="sParent">
												<c:forEach items="${CompanyInfoForm.vListItemCatName}" var="curObject">
												<option id="${curObject.vItemCategory_Name}">
												${curObject.vItemCategory_Name}
												</option>
												</c:forEach>
											</select>	 
								</c:if>
								 </td>
							</tr>
							<tr>
								<td>Item Title</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width: 275px" name="inventoryName" id="inventoryName"> </td>
							</tr>
							
							<tr>
								<td>Description</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width: 275px" name="description" id="description"> </td>
							</tr>
						</tbody>
					</table>
				</div>	
				<div class="bca_settingupinventory_top_button">
					<ul>
						<li><a class="formbutton">New</a></li>
						<li><a class="formbutton" onclick="return addGeneralDetail()" id="addInv">Add</a></li>
						<li><a class="formbutton" onclick="return remove()">Delete</a></li>
					</ul>
				</div>
			<div class="bca_settingupinventory_top_table_sec">
				<table>
					<tbody>
						<tr>
							<td>Qty. on Hand &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" onkeypress="return isNumber(event)" name="qty" id="qty"></td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>Weight &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" onkeypress="return isNumber(event)" name="weight" id="weight"></td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>Inventory Barcode &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="inventoryBarCode" id="inventoryBarCode"></td>
						</tr>
						<tr>
							<td>Inventory SKU &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="SKU" id="SKU"></td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>Serial# &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="serialNum" id="serialNum"></td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>Sale Price &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" onkeypress="return isNumber(event)" name="salePrice" id="salePrice"></td>
						</tr>
						<tr>
							<td>Purchase Price &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" onkeypress="return isNumber(event)" name="purchasePrice" id="purchasePrice"></td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>Reorder Point &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" onkeypress="return isNumber(event)" name="reorderPoint" id="reorderPoint"></td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>Order Unit &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="number" onkeypress="return isNumber(event)" name="orderUnit" id="orderUnit"></td>
						</tr>
					</tbody>
				</table>
			<div class="bca_threecheckbox">
				<ul>
					<li><input type="checkbox" name="taxable" id="inventoryTaxable">Taxable Item</li>
					<li><input type="checkbox" name="dropship" id="inventoryDropShipping">Drop Shipping</li>
					<li><input type="checkbox" name="discontinued" id="inventoryDiscontinued">Discontinued</li>
				</ul>
			</div>
			</div>
			</div>
			<div class="bca_settingupinventory_bottom" id="invTable">
				<fieldset>
					<h6>Inventory List</h6>
					<table border="1">
						<thead>
							<tr>
								<th>ITEM CODE</th>
								<th>SERIAL #</th>
								<th>DESCRIPTION</th>
								<th>PURCHASE PRICE</th>
								<th>SALE PRICE</th>
								<th>WEIGHT</th>
								<th>QTY ON HAND</th>
								<!-- <th>TAXABLE</th> -->
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
		</fieldset>
			</div>				
		</div>
	</div>
	<div class="bca_createnewcompanyright_bottom">
		<ul>
			<li><a onclick="history.back(-1)" class="formbutton"><spring:message code="BzComposer.global.goback"/></a></li>
			<li><a class="formbutton" onclick="submitform();"><spring:message code="BzComposer.global.next"/></a></li>
			<li><a class="formbutton" onclick="finish()"><spring:message code="BzComposer.global.finish"/></a></li>
			<li><a class="formbutton" onclick="quit();"><spring:message code="BzComposer.global.close"/></a></li>
		</ul>
	</div>
</form>
<script type="text/javascript">
var objGlobal = [];
var i = 0;
var index = -1;
var parentId;var inventoryName;var inventoryCode;var serialNum;var itemTypeId;var description;var qty;var weight;var purchasePrice;
var salePrice;var inventoryBarCode;var orderUnit;var inventoryDropShipping;var inventoryDiscontinued;var inventoryTaxable;var SKU;var reorderPoint;

function submitform()
{
	debugger;
	if(!confirm("Do you want to save changes?"))
	{
		document.forms[0].action = "CompanyNew?tabid=createNewCompany9";
		document.forms[0].submit();
	} 
	else{
		/* addgeneraldetail(); */
		var json = JSON.stringify(objGlobal);
		 $.ajax({
			
			type : "POST",
			url : "CompanyNew?tabid=addInventory",
			data : "json=" +json ,
			 /*success : function(data) {

			 },
			 error : function(data) {
				 $("#errorOccurred").dialog({
					 resizable: false,
					 height: 200,
					 width: 500,
					 modal: true,
					 buttons: {
						 "Ok": function () {
							 $(this).dialog("close");
						 }
					 }
				 });
				 return false;
			 }*/

		});
		alert("<bean:message key='BzComposer.common.saveSuccess'/>");
		document.forms['newcompany8'].action = "CompanyNew?tabid=createNewCompany9";
		document.forms['newcompany8'].submit();

	}
}

function isNumber(evt) {
    debugger;
	evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
function addGeneralDetail()
{
	debugger;
	var inventoryName = $("#inventoryName").val() == ""?null:$("#inventoryName").val();
	var inventoryCode = $("#inventoryCode").val() == ""?null:$("#inventoryCode").val();
	var serialNum = $("#serialNum").val() == ""?null:$("#serialNum").val();
	var description = $("#description").val() == ""?null:$("#description").val();
	var qty = $("#qty").val() == ""?0.00:$("#qty").val();
	var weight = $("#weight").val() == ""?0.00:$("#weight").val();
	var purchasePrice = $("#purchasePrice").val() == ""?0.00:$("#purchasePrice").val();
	var reorderPoint = $("#reorderPoint").val() == ""?0.00:$("#reorderPoint").val();
	var salePrice = $("#salePrice").val() == ""?0.00:$("#salePrice").val();
	var inventoryBarCode = $("#inventoryBarCode").val() == ""?null:$("#inventoryBarCode").val();
	var SKU = $("#SKU").val() == ""?null:$("#SKU").val();
	var orderUnit = $("#orderUnit").val() == ""?null:$("#orderUnit").val();
	var inventoryDropShipping = $("#inventoryDropShipping").prop("checked");
	var inventoryDiscontinued = $("#inventoryDiscontinued").prop("checked");
	var inventoryTaxable = $("#inventoryTaxable").prop("checked");
	var itemTypeId = $("#itemTypeId").val() == ""?null:$(".itemTypeId").val();
	
	if(inventoryCode == null)
	{
		alert("<bean:message key='BzComposer.common.insertItemDetails'/>");
		return false;
	}	
	
	if($("#addInv").text() == 'Add')
	{	
		objGlobal.push({
			InventoryName:inventoryName,
			InventoryCode:inventoryCode,
			SerialNum:serialNum,
			description:description,
			qty:qty,
			weight:weight,
			purchasePrice:purchasePrice,
			reorderPoint:reorderPoint,
			salePrice:salePrice,
			inventoryBarCode:inventoryBarCode,
			SKU:SKU,
			orderUnit:orderUnit,
			inventoryDropShipping:inventoryDropShipping,
			inventoryDiscontinued:inventoryDiscontinued,
			inventoryTaxable:inventoryTaxable,
			itemTypeId:itemTypeId,
		});
	
			if(objGlobal.length > 0)
			{
				$("#invTable tbody").append("<tr style ='cursor:pointer;' onclick = 'selectRow("+i+")' id = 'row"+i+"'><td>"+objGlobal[i].InventoryCode+"</td><td>"+objGlobal[i].SerialNum+"</td><td>"+objGlobal[i].description+"</td><td>"+objGlobal[i].purchasePrice+"</td><td>"+objGlobal[i].salePrice+"</td><td>"+objGlobal[i].weight+"</td><td>"+objGlobal[i].qty+"</td></tr>");
			}	
			else{
				i--;
			}
			i++;
	
	}	
	else {
			debugger;
			objGlobal[index] = {
				InventoryName:inventoryName,
				InventoryCode:inventoryCode,
				SerialNum:serialNum,
				description:description,
				qty:qty,
				weight:weight,
				purchasePrice:purchasePrice,
				reorderPoint:reorderPoint,
				salePrice:salePrice,
				inventoryBarCode:inventoryBarCode,
				SKU:SKU,
				orderUnit:orderUnit,
				inventoryDropShipping:inventoryDropShipping,
				inventoryDiscontinued:inventoryDiscontinued,
				inventoryTaxable:inventoryTaxable,
				itemTypeId:itemTypeId,
			};
			$("#row"+index+"").replaceWith("<tr style ='cursor:pointer;' onclick = 'selectRow("+index+")' id = 'row"+index+"'><td>"+objGlobal[index].InventoryCode+"</td><td>"+objGlobal[index].SerialNum+"</td><td>"+objGlobal[index].description+"</td><td>"+objGlobal[index].purchasePrice+"</td><td>"+objGlobal[index].salePrice+"</td><td>"+objGlobal[index].weight+"</td><td>"+objGlobal[index].qty+"</td></tr>");
			$("#addInv").text("Add");
		}
			$('#myform')[0].reset();
}
function selectRow(i)
{
	debugger;
	index = i;	
	$("#inventoryCode").val(objGlobal[i].InventoryCode);
	$("#inventoryName").val(objGlobal[i].InventoryName);
	$("#description").val(objGlobal[i].description);
	$("#qty").val(objGlobal[i].qty); 
	$("#weight").val(objGlobal[i].weight);
	$("#inventoryBarCode").val(objGlobal[i].inventoryBarCode);
	$("#SKU").val(objGlobal[i].SKU);
	$("#serialNum").val(objGlobal[i].SerialNum);
	$("#salePrice").val(objGlobal[i].salePrice);
	$("#purchasePrice").val(objGlobal[i].purchasePrice);
	$("#reorderPoint").val(objGlobal[i].reorderPoint);
	$("#orderUnit").val(objGlobal[i].orderUnit); 
	$("#inventoryDropShipping").prop("checked",objGlobal[i].inventoryDropShipping);
	$("#inventoryDiscontinued").prop("checked",objGlobal[i].inventoryDiscontinued);
	$("#inventoryTaxable").prop("checked",objGlobal[i].inventoryTaxable);
	$("#addInv").text("Edit");
}
function remove()
{
	debugger;
	if(index != -1)
	{
		$("#row"+index+"").remove();
		objGlobal.splice(index,1);
	}else{
		alert("<bean:message key='BzComposer.common.selectAtleast1Record'/>");
		return false;
	}	
	index = -1;
	$('#myform')[0].reset();
}
function finish()
{
	debugger;
	if(objGlobal.length > 0)
	{		
			var json = JSON.stringify(objGlobal);
			 $.ajax({
				
				type : "POST",
				url : "CompanyNew?tabid=addInventory",
				data : "json=" +json ,
			    success : function(data) {
					debugger;
					alert("<bean:message key='BzComposer.common.saveSuccess'/>");
					document.forms[0].action = "CompanyNew?tabid=finish3";
					document.forms[0].submit(); 
				},
				 error : function(data) {
					 $("#errorOccurred").dialog({
					    	resizable: false,
					        height: 200,
					        width: 500,
					        modal: true,
					        buttons: {
					            "Ok": function () {
					                $(this).dialog("close");
					            }
					        }
					    });
						return false;
				} 
			});
	}	
	else{
		document.forms[0].action = "CompanyNew?tabid=finish3";
		document.forms[0].submit(); 
	}
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
                window.history.go(-8);
            },
            "<spring:message code='bca.quitmessage.no'/>": function () {
                $("#quitMessage").dialog("close");
                return false;
            }
        }
    });
}
</script>	
</body>
</html>