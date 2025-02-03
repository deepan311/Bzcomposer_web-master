<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- <script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu_add.js"></script> -->
<%@include file="../include/headlogo.jsp"%>
<%@include file="../include/menu.jsp"%>
<%@include file="../include/header.jsp"%>
<title><spring:message code="BzComposer.additemtitle"/> </title>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<script src="https://cdn.ckeditor.com/4.12.1/full/ckeditor.js"></script>
<style type="text/css">
.myTable { height: 150px; overflow-y: scroll; }
tr.highlight td { background-color: navy; }
table.sortable thead { background-color: #eee; color: #666666; font-weight: bold; cursor: default; }
table.tabla-listados { width: 100%; border: 1px solid rgb(207, 207, 207); margin: 0px 0px 0px 0px; }
table.tabla-listados tbody tr.odd td { background: #e1e5e9; }
table.tabla-listados thead tr th { font-size: 14px; }
table.tabla-listados tbody tr td { font-size: 12px; }

#custTable_filter { margin-right:-20px; }
#custTable_paginate { margin-right:-20px; }
.dataTables_length{ font-size:14px; }
.dataTables_filter{ font-size:14px; }
.dataTables_info{ font-size:14px; }
.dataTables_paginate{ font-size:14px; }
</style>
<script type="text/javascript">
$(document).ready(function(){
    $("body").show();
    $('#custTable').DataTable({
        "iDisplayLength": 10,
        "ordering": false,
        "fnDrawCallback": function( oSettings ) {
            setRowId(0, 0, false);
            hightlightROW();
        }
    });
});

var pId,pName,pSKU,pQty,pAmount;
self.moveTo(100,100);

function openThumbnail()
{
  document.getElementById("imgupload1").click();
  var ima = $('#imgupload1').val();
  /* var im = $('input[type=file]').val().replace(/C:\\fakepath\\/i, '');
  debugger */
  $('#thumbnail').val(ima);
}

function showSelectedOption(priceLevelId,priceLevelDesc,priceLevelPercentage){
	size = document.getElementById('priceLevelSize').value;
	$('#priceLevelName').val(priceLevelDesc);
	$('#pricePercentage').val(priceLevelPercentage);
}


function highlightSelectedSupplier(supplierId)
{
	$("#supplierName tr").each(function() {
		$("#supplierName tr").removeClass("draft");
	});
	var rows = document.getElementById("supplierName").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	debugger
	for(i=0;i<rows;i++)
	{
		var row1=document.getElementById("supplier"+supplierId);
		row1.className = "draft";
	}
}

function showSelectedCrossSellProduct(productId)
{
	$("#crossSellProductTable tr").each(function() {
		$("#crossSellProductTable tr").removeClass("draft");
	});
	var rows = document.getElementById("crossSellProductTable").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	debugger
	for(i=0;i<rows;i++)
	{
		var row1=document.getElementById("crossSellProductId"+productId);
		row1.className = "draft";
	}
}

function addData(){

}

$(function() {
    $("#tabs").tabs();
    $("#tabsPricing").tabs();
    $('#stockInOut').tabs()
    $('#drop_cat').prop('disabled', 'disabled');
    $('select[id="unitMeasurement"]').find('option[value=" "]').attr("selected",true);
    $('select[id="clientVendor"]').find('option[id="defaultCvId"]').attr("selected",true);
    var historyVisible = <%= request.getAttribute("showHistoryPanel")%>;
    if(historyVisible >0)
    {
    	$("#liHistory-11").show();
    }
    else
    {
    	$("#liHistory-11").hide();
    }
  });

 $(function(){
	 var result;
	 var id = 1;

   $('#createCategory1').click(function(){
		var category = $('#serviceCode1').val();
		if(category == ""){
			return showCategoryDialog();
		}
	});

	$('#clientVendor').change(function(){
		var id = $("#clientVendor").val();
		var name = $('#clientVendor option:selected').text();
		var myWindow;
		if(id == 0){
			mywindow = window.open("Customer?tabid=addSupplier","_blank","scrollbars=No,height=900,width=1400,status=yes,toolbar=no,menubar=no,location=no");
			myWindow.moveTo(90,90);
		}
		else if(id = 10101010){
			window.stop();
		}
	});

	$('#addSupplierBtn').click(function(){
		debugger
		var id = $("#clientVendor").val();
		var name = $('#clientVendor option:selected').text();
		var names = name.split(' ');
		if(id == 0){
			alert("<bean:message key='BzComposer.common.selectSupplier'/>");
		}
		else{
			var rows = document.getElementById("supplierName").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			if(rows<3){
				debugger
				var isAvailable = $('#supplierName tr > td:contains('+name+')').length;
				if(isAvailable == 0){
					$('#supplierName').append("<tr id='supplier"+id+"' onclick='highlightSelectedSupplier("+id+");'><td id="+id+">"+name+"</td></tr>");
				}
				else{
					alert("<bean:message key='BzComposer.common.supplierAlreadyAdded'/>");
				}
			}
			else{
				alert("<bean:message key='BzComposer.common.supplierReachedMaxLimit'/>");
			}

		}
	});

	$('#deleteSupplierBtn').click(function(){
		if($("#supplierName tr").hasClass('draft')){
			$("#supplierName tr").each(function() {
				if($(this).hasClass('draft'))
				{
					if(confirm("Do you want to delete this item?")){
						$(this).remove();
					}
				}
			});
		}else{
			alert("<bean:message key='BzComposer.common.selectSupplier'/>");
		}
	});

	$('#measurementList').change(function(){
		$('select[id="unitMeasurement"]').show();
		var measurement = $('#measurementList option:selected').text();
		var measurementId = $('#measurementList').val();
		if(measurementId == "3" || measurementId == "4" || measurementId == "5")
		{
			document.getElementById("displayHWL").style.display = "block";
		}
		else
		{
			document.getElementById("displayHWL").style.display = "none";
		}
		$("#unitMeasurement > option").each(function() {
			if(measurementId == "3" || measurementId == "4" || measurementId == "5")
			{
				$('select[id="unitMeasurement"]').find('option[value="2"]').show();
			}
			else if(this.value == measurementId)
			{
				$('select[id="unitMeasurement"]').find('option[value="'+this.value+'"]').show();
			}
			else
			{
				$('select[id="unitMeasurement"]').find('option[value="'+this.value+'"]').hide();
			}
		});
	});

	$('#priceLevel').change(function(){
		if($(this).prop("checked") == true)
		{
			$('#tabsPricing').tabs( "enable", 0 );
			$('#tabsPricing').tabs( "enable", 1 );

			$('#UpdatePricelevel').prop('disabled',false);
			$('#ClearPriceLevel').prop('disabled',false);

			$('#addPriceLevel').prop('disabled',false);
			$('#ClearPriceLevel2').prop('disabled',false);
		}
		else
		{
			$('#tabsPricing').tabs( { disabled: [0, 1] });

			$('#UpdatePricelevel').prop('disabled','disabled');
			$('#ClearPriceLevel').prop('disabled','disabled');

			$('#addPriceLevel').prop('disabled','disabled');
			$('#ClearPriceLevel2').prop('disabled','disabled');
		}
	});

	$('#subItem').change(function(){
		if($(this).prop("checked") == true)
		{
			$('#drop_cat').prop('disabled', false);
		}
		else
		{
			$('#drop_cat').prop('disabled', 'disabled');
		}
	});

	$('#subItem3').change(function(){
		if($(this).prop("checked") == true)
		{
			$('#subItemSelect3').prop('disabled', false);
		}
		else
		{
			$('#subItemSelect3').prop('disabled', 'disabled');
		}
	});

	$('#discounted').change(function(){
		if($(this).prop("checked") == true)
		{
			$('#AddBtn').prop('disabled','disabled');
			$('#editBtn').prop('disabled','disabled');
		}
		else
		{
			$('#AddBtn').prop('disabled',false);
			$('#editBtn').prop('disabled',false);
		}
	});

	$('#imgupload').change(function(e)
	{
	    let filename = '';
	    if(e.target.files.length > 5){
            alert("<bean:message key='BzComposer.common.cantUploadMoreFiles'/>");
            e.target.value = '';
            return;
        }
	    for(let x=0; x<e.target.files.length; x++){
	        result = $('#imgupload').val();
            filename = filename+e.target.files[x].name+', ';
	        if(x==0){
                $('#resultImage').fadeIn("fast").attr('src',URL.createObjectURL(e.target.files[0]));
            }
            if(x==1){
                $('#resultImageTest2').fadeIn("fast").attr('src',URL.createObjectURL(e.target.files[1]));
            }
            if(x==2){
                $('#resultImageTest3').fadeIn("fast").attr('src',URL.createObjectURL(e.target.files[2]));
            }
            if(x==3){
                $('#resultImageTest4').fadeIn("fast").attr('src',URL.createObjectURL(e.target.files[3]));
            }
            if(x==4){
                $('#resultImageTest5').fadeIn("fast").attr('src',URL.createObjectURL(e.target.files[4]));
                break;
            }
		}
		filename = filename.substring(0, filename.length-2);
		$('#imageName').val(filename);
	});

	$('#viewImage').click(function(e)
	{
		/* $('#imgupload').trigger('click'); */
		var largeImage = document.getElementById('resultImage');
		var url=largeImage.getAttribute('src');
		var myWindow = window.open(url,'myImage','height=250,width=250,resizable=1');
		myWindow.moveTo(700,250);
	});

	$('#imgupload1').change(function(e)
	{
		$('#resultImageThumbnail').attr('src',URL.createObjectURL(e.target.files[0]));
		result = $('#imgupload1').val();
		var filename = $('input[type=file]').val().replace(/C:\\fakepath\\/i, '');
		var i = result.replace(/C:\\fakepath\\/i, '');
		$('#thumbnail').val(i);

		$('#viewImage1').attr("disabled",false);
	});

	$('#viewImage1').click(function(e){
		var im = document.getElementById('resultImageThumbnail');
		var u = im.getAttribute('src');
		var myWindow1 = window.open(u,'myImage','height=250,width=250,resizable=1');
		myWindow1.moveTo(700,250);
	});

	$('#imgupload2').change(function(e)
	{
		$('#resultImage2').attr('src',URL.createObjectURL(e.target.files[0]));
		result = $('#imgupload2').val();
		var filename = $('input[type=file]').val().replace(/C:\\fakepath\\/i, '');
		var i = result.replace(/C:\\fakepath\\/i, '');
		$('#image2').val(i);
		$('#viewImage2').attr("disabled",false);
	});

	$('#viewImage2').click(function(e){
		var im = document.getElementById('resultImage2');
		var u = im.getAttribute('src');
		var myWindow1 = window.open(u,'myImage','height=250,width=250,resizable=1');
		myWindow1.moveTo(700,250);
	});

});
</script>
</head>
<body onload="Init();" style="display:none">
<div id="ddcolortabsline">
&nbsp;
</div>
<form:form action="Item" method="post" enctype="MULTIPART/FORM-DATA" id="AddItemfrm" name="AddItemfrm" modelAttribute="itemDto">
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div>
	<span style="font-size:1.2em;font-weight:normal;color:#838383;margin:30px 0px 15px 0px;border-bottom:1px dotted #333;padding:0 0 .3em 0;">
		<spring:message code="BzComposer.additem.additemtitle"/>
		<c:if test="${not empty Status}">
            <br/><span class="msgstyle">* ${Status}</span>
        </c:if>
        <c:if test="${not empty SaveStatus}">
            <br/><span class="msgstyle">* ${SaveStatus}</span>
        </c:if>
	</span>
	<br />
</div>
<div id="ItemListArea" class="float-left" style="width:40%;">
    <table id="custTable" class="tabla-listados sortable" cellspacing="0" style="width: 100%; margin-top: 10px; border: 0; padding: 0;height: auto;" align="center">
        <thead>
        <tr valign="top">
            <th align="center"><spring:message code="BzComposer.categorymanager.category"/></th>
            <th align="center"><spring:message code="BzComposer.Item.ItemCode"/></th>
            <th align="center" style="padding-right:130px;"><spring:message code="BzComposer.additem.itemtitle"/></th>
            <th><spring:message code="BzComposer.additem.quantity"/></th>
        </tr>
        </thead>
        <tbody>
            <c:if test="${not empty ItemDetails}">
                <c:forEach items="${ItemDetails}" var="objList" varStatus="loop">
                    <tr id='${loop.index}$$' onclick="setRowId(${objList.inventoryId}, ${loop.index}, true);">
                        <td><b>${objList.categoryName}</b></td>
                        <td>${objList.itemCode}</td>
                        <td>${objList.itemName}</td>
                        <td>${objList.qty}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
    <input type="hidden" id="lSize" value='${ItemDetails.size()}' />
</div>
<div id="addNewItemArea" class="float-right" style="width:55%;">
	<table style="font-size:14px;width:100%;" cellpadding="5" cellspacing="5">
        <tr>
            <td style="width:20%;"><spring:message code="BzComposer.global.type" />:</td>
            <td style="width:80%;">
            <form:select path="itemType" onchange="call11(this.value);" value='<%=request.getParameter("ItemType") %>'>
                <%-- <option value="1"><spring:message code="BzComposer.Item.Inventory" /></option>
                <option value="2"><spring:message code="BzComposer.Item.Discount" /></option>
                <option value="3"><spring:message code="BzComposer.Item.Subtotal" /></option>
                <option value="4"><spring:message code="BzComposer.Item.Service" /></option> --%>

                <option value="1"><spring:message code="BzComposer.additem.product" /></option>
                <option value="2"><spring:message code="BzComposer.additem.service" /></option>
                <option value="3"><spring:message code="BzComposer.additem.recurringservice"/></option>
                <option value="4"><spring:message code="BzComposer.additem.giftcertificate"/></option>
                <option value="5"><spring:message code="BzComposer.additem.inventoryassembly"/></option>
            </form:select>
            </td>
        </tr>
        <tr>
            <td><spring:message code="BzComposer.global.loadTemplate" />:</td>
            <td>
                <select id="loadTemplateID" onchange="loadTemplate(this.value);" style="width:90%;">
                    <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                    <c:forEach items="${ItemNameList}" var="objList1">
                        <option value="${objList1.inventoryId}">${objList1.itemCode} -> ${objList1.itemName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
<table cellpadding="0" cellspacing="0" border="0" align=center  style="width: 100%;">
	<tr>
	<td colspan="3">
	<div id="table-negotiations" style="width:100%">
		<table cellspacing="0" align="left" style="width:100%">
			<tr>
				<td colspan="3" align="center" style="padding-right: 14px;">
					<!-- productTab Starts -->
					<div id="productTab">
						<div id="tabs" style="height:660px;">
							<ul id="tabsAddItem">
								<li style="font-size: 12px;"><a href="#productInfo-1"><spring:message code="BzComposer.tabs.productinformation"/></a></li>
								<li style="font-size: 12px;"><a href="#pricing-2"><spring:message code="BzComposer.tabs.pricing"/></a></li>
								<li style="font-size: 12px;"><a href="#images-6"><spring:message code="BzComposer.tabs.images"/></a></li>
								<li style="font-size: 12px;" id="liHistory-11"><a href="#history-11"><spring:message code="BzComposer.tabs.history"/></a></li>
							</ul>

							<div id="productInfo-1">
								<div id="content1" class="tabPage">
									<!-- add here the content of first tab -->
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0">
											<thead>
											<!-- <tr><th colspan="5"><spring:message code="BzComposer.additem.iteminfo"/></th></tr> -->
											</thead>
											<tbody>
											<tr>
												<td>
													<spring:message code="BzComposer.additem.itemcode"/>
													<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td>
													<form:input type="text" path="itemCode" />
												</td>
												<td>
													<input type="checkbox" id="subItem" name="subItem" value="subItem" />
													<spring:message code="BzComposer.additem.categoryOf"/>
										        </td>
                                                <td>
													<form:select path="tectcmd" disabled="false" id="drop_cat">
                                                        <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                                                        <form:options items="${itemCategory}" itemValue="value" itemLabel="label" />
                                                    </form:select>
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.additem.itemtitle"/>
												</td>
												<td colspan="3">
													<form:input type="text" path="itemName" style="width:100%;" />
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<form:checkbox path="itemTaxable" />
													<spring:message code="BzComposer.additem.taxableitem"/>
												</td>
												<td>
													<form:checkbox path="dropShipping" />
													<spring:message code="BzComposer.additem.dropshipping"/>
												</td>
												<td>
													<form:checkbox path="discounted" />
													<spring:message code="BzComposer.additem.discounted"/>
												</td>
												<td>
                                                    <form:checkbox path="consignedItem" />
                                                    <spring:message code="BzComposer.additem.iscosigneditem"/>
                                                </td>
												<td>&nbsp;</td>
											</tr>
											<tr>
											    <td>
                                                    <spring:message code="BzComposer.additem.productAccountCategory"/>
                                                </td>
                                                <td>
                                                    <form:select path="accountId" style="width:150px;">
                                                        <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                                                        <form:options items="${AccountList}" itemValue="value" itemLabel="label" />
                                                    </form:select>
                                                </td>
                                                <td colspan="3">&nbsp;</td>
                                            </tr>
											<tr>
												<td>
													<spring:message code="BzComposer.additem.serialnum"/>
												</td>
												<td>
													<form:input type="text" path="serialNum" />
												</td>
												<td>
													<spring:message code="BzComposer.additem.productsku"/>
												</td>
												<td>
													<form:input type="text" path="productSKU" />
												</td>
												<td>&nbsp;</td>
                                            </tr>
                                            <tr>
												<td>
													<spring:message code="BzComposer.additem.orderunit"/>
												</td>
												<td>
													<form:input path="orderUnit" onkeydown="return numbersonly(event, this.value)" />
												</td>
												<td>
													<spring:message code="BzComposer.additem.qtyonhand"/>
													<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td>
                                                    <form:input path="qty" onkeydown="return numbersonly(event, this.value)" />
                                                </td>
												<td>&nbsp;</td>
                                            </tr>
                                            <tr>
												<td>
													<spring:message code="BzComposer.additem.reorderpoint"/>
												</td>
												<td>
													<form:input type="text" path="reorderPoint" onkeydown="return numbersonly(event, this.value)" />
												</td>
												<td>
													<spring:message code="BzComposer.additem.location"/>
												</td>
												<td>
													<form:select path="locationId">
														<form:options items="${locationList}" itemValue="value" itemLabel="label" />
													</form:select>
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.additem.publishedweight"/>
													<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td>
													<form:input path="weight" style="width:100px;" onkeypress="return numbersonly(event, this.value)" />
													<form:select path="weightUnit">
                                                        <form:options items="${weightList}" itemValue="value" itemLabel="label" />
                                                    </form:select>
												</td>
												<td>
													<spring:message code="BzComposer.additem.actualweight"/>
												</td>
												<td>
													<form:input path="actualWeight" onkeypress="return numbersonly(event, this.value)" />
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
											    <td>
                                                    <spring:message code="BzComposer.additem.purchaseprice"/>
                                                    <span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                                </td>
                                                <td>
                                                    $<form:input path="purchasePrice" onkeypress="return numbersonly(event, this.value)" />
                                                </td>
											    <td>
                                                    <spring:message code="BzComposer.additem.saleprice"/>
                                                    <span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                                </td>
                                                <td>
                                                    $<form:input path="salePrice" onkeypress="return numbersonly(event, this.value)" />
                                                </td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <spring:message code="BzComposer.additem.dealerprice"/>
                                                </td>
                                                <td>
                                                    $<form:input path="dealerPrice" onkeypress="return numbersonly(event, this.value)" />
                                                </td>
                                                <td>
                                                    <spring:message code="Bizcomposer.additem.type"/>
                                                </td>
                                                <td>
                                                    <form:select path="measurementId" id="measurementList">
                                                        <form:options items="${measurementList}" itemValue="value" itemLabel="label" />
                                                    </form:select>
                                                    &nbsp;&nbsp;
                                                    <form:select path="submeasurementId" id="unitMeasurement">
                                                        <form:options items="${unitMeasurementList}" itemValue="value" itemLabel="label" />
                                                    </form:select>
                                                </td>
                                                <td>&nbsp;</td>
                                            </tr>
											<tr><td colspan="5">&nbsp;</td></tr>
											<tr>
												<td>
													<spring:message code="BzComposer.additem.itemsupplier"/>
												</td>
												<td>
													<form:select path="vendorId" id="clientVendor">
														<%-- <option value="AddNew"><spring:message code="BzComposer.addNew"/></option> --%>
														<option value="10101010" id="defaultCvId">&nbsp;</option>
														<option value="0"><spring:message code="BzComposer.additem.addnew" /></option>
														<form:options items="${vendorNameList}" itemValue="value" itemLabel="label" />
													</form:select>
												</td>
												<td colspan="3">&nbsp;</td>
											</tr>
											<tr>
											    <td>
                                                    <spring:message code="BzComposer.additem.supplierssku"/>
                                                </td>
                                                <td><form:input type="text" path="supplierSKU" /></td>
												<td>
													<spring:message code="BzComposer.additem.supplierforthissku"/>
												</td>
												<td>
													<form:checkbox path="primarySupplier" />
													<spring:message code="BzComposer.additem.primarysupplier"/>
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td colspan="3" rowspan="3" style="border: 1px solid rgb(207, 207, 207);">
													<table border="1" style="width: 100%;height: 100%;">
														<tr>
															<td><spring:message code="BzComposer.additem.suppliername"/></td>
														</tr>
														<tr>
															<td>
																<table id="supplierName">
																	<tbody></tbody>
																</table>
																<form:hidden path="supplierIDs" />
															</td>
														</tr>
													</table>
												</td>
												<td>
													&nbsp;<button type="button" class="formbutton" id="addSupplierBtn" style="width:50px;"><spring:message code='BzComposer.global.add'/></button>
												    &nbsp;&nbsp;&nbsp;
												    <spring:message code="BzComposer.additem.minimumorderunit"/></td>
                                                <td>
                                                    <form:input path="minOrderUnit" style="width:50px;" onkeydown="return numbersonly(event, this.value)" />
                                                </td>
											</tr>
											<tr>
												<td>
													&nbsp;<button type="button" class="formbutton" id="editBtn" style="width:50px;"><spring:message code='BzComposer.global.edit'/></button>
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
												    &nbsp;<button type="button" class="formbutton" id="deleteSupplierBtn" style="width:50px;"><spring:message code='BzComposer.global.delete'/></button>
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr><td colspan="5">&nbsp;</td></tr>
											<tr>
                                                <td>
                                                    <spring:message code="BzComposer.Item.Description"/>
                                                </td>
                                                <td colspan="4">
                                                    <form:textarea path="textAreaContent" rows="2" style="width:99%;" />
                                                </td>
                                            </tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>

							<div id="pricing-2">
								<div id="content2" class="tabPage">
									<!-- add here the content of second tab -->
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0">
											<thead>
											<tr>
												<th colspan="6">
													<spring:message code="BzComposer.tabs.pricing" />
												</th>
											</tr>
											</thead>
											<tbody>
											<tr>
												<td>
													&nbsp;<input type="checkbox" id="priceLevel" name="priceLevel" checked>
													<spring:message code="BzComposer.additem.applypricelevel"/>
												</td>
												<td colspan="5">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="5">
													<div id="tabsPricing" style="height:400px;">
														<ul>
															<li style="font-size: 12px;">
																<a href="#priceLevel-1">
																	<spring:message code="BzComposer.additem.pricelevel"/>
																</a>
															</li>
															<li style="font-size: 12px;">
																<a href="#addPriceLevel-2">
																	<spring:message code="BzComposer.additem.addpricelevel"/>
																</a>
															</li>
														</ul>
														<div id="priceLevel-1">
															<div id="content1" class="tabPage">
																<!-- add here the content of first tab -->
																<div id="table-negotiations">
																	<table class="tabla-listados" cellspacing="0">
																		<thead>
																		<tr>
																			<td rowspan="3" style="width:100px;border: 1px solid rgb(207, 207, 207);">
																				<table border="1" style="width: 100%;">
																					<tr>
																						<td>
																							<spring:message code="BzComposer.additem.pricelevel"/>
																						</td>
																						<td>
																							<spring:message code="BzCompoer.additem.percentagebracket"/>
																						</td>
																					</tr>
																					<%-- <select path="priceLevelId" id="customerPaymentMethod"> --%>
																					<c:if test="${not empty ItemForm.listOfExistingPriceLevels}">
																						<%
																							int priceLevelSize = 0;
																						%>
																						<c:forEach items="${ItemForm}" var="objList1">
																							<%
																								priceLevelSize++;
																							%>
																							<tr id="priceLevel" value="${objList1.priceLevelId}"
																								onclick="showSelectedOption('${objList1.priceLevelId}',
																										'${objList1.priceLevel}', '${objList1.pricePercentage}');">
																								<td>
																										${objList1.priceLevel}
																								</td>
																								<td>
																										${objList1.pricePercentage}
																								</td>

																							</tr>
																						</c:forEach>
																						<input type="hidden" id="priceLevelSize" name="priceLevelSize" value='<%=priceLevelSize%>' />
																					</c:if>
																				</table>
																			</td>
																			<td style="padding-left:20px;">
																				<spring:message code="BzComposer.additem.pricelevelname"/>
																			</td>
																			<td>
																				<form:input type="text" path="priceLevel" id="priceLevelName" />
																			</td>
																			<td></td>
																			<td>
																				<input type="checkbox" id="priceLevelInactive" name="priceLevelInactive">
																				<spring:message code="BzComposer.additem.pricelevelisinactive"/>
																			</td>
																		</tr>
																		<tr>
																			<td style="padding-left:20px;">
																				<spring:message code="BzComposer.additem.thispricelevelwill"/>
																			</td>
																			<td>
																				<select id="priceLevelWill" style="width:150px;">
																					<option value="1"><spring:message code="BzComposer.additem.increase"/></option>
																					<option value="2"><spring:message code="BzComposer.additem.decrease"/></option>
																				</select>
																			</td>
																			<td>
																				<spring:message code="BzComposer.additem.itempricesby"/>
																			</td>
																			<td>
																				<form:input type="text" path="pricePercentage" id="pricePercentage"/>
																			</td>
																			<td>&nbsp;</td>
																		</tr>
																		<tr>
																			<td style="padding-left:20px;">
																				<spring:message code="BzComposer.additem.priceleveltype"/>
																			</td>
																			<td>
																				<select id="priceLevelOption" style="width:150px;">
																					<option value="1"><spring:message code="BzComposer.additem.fixed"/></option>
																					<option value="2"><spring:message code="BzComposer.additem.itemprice"/></option>
																				</select>
																			</td>
																			<td colspan="3">&nbsp;</td>
																		</tr>
																		<tr>
																			<td colspan="3">&nbsp;</td>
																			<td>
																				<input type="button" id="UpdatePricelevel" name="UpdatePriceLevel" class="formbutton" value="<spring:message code='BzComposer.global.update'/>">
																			</td>
																			<td>
																				<input type="reset" id="ClearPriceLevel" name="ClearPriceLevel" class="formbutton" value="<spring:message code='BzComposer.additem.clearbtn'/>">
																			</td>
																		</tr>
																		</thead>
																		<tbody>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
														<div id="addPriceLevel-2">
															<div id="content1" class="tabPage">
																<!-- add here the content of first tab -->
																<div id="table-negotiations">
																	<table class="tabla-listados" cellspacing="0">
																		<tbody>
																		<tr>
																			<td>
																				<spring:message code="BzComposer.additem.pricelevelname"/>
																			</td>
																			<td>
																				<input type="text" id="priceLevelName" name="priceLevelName">
																			</td>
																			<td></td>
																			<td>
																				<input type="checkbox" id="priceLevelInactive" name="priceLevelInactive">
																				<spring:message code="BzComposer.additem.pricelevelisinactive"/>
																			</td>
																			<td colspan="2">&nbsp;</td>
																		</tr>
																		<tr>
																			<td>
																				<spring:message code="BzComposer.additem.thispricelevelwill"/>
																			</td>
																			<td>
																				<select id="priceLevel">
																					<option value="1"><spring:message code="BzComposer.additem.increase"/></option>
																					<option value="2"><spring:message code="BzComposer.additem.decrease"/></option>
																				</select>
																			</td>
																			<td>
																				<spring:message code="BzComposer.additem.itempricesby"/>
																			</td>
																			<td>
																				<input type="text" id="itemPrice">
																			</td>
																			<td colspan="2">&nbsp;</td>
																		</tr>
																		<tr>
																			<td>
																				<spring:message code="BzComposer.additem.priceleveltype"/>
																			</td>
																			<td>
																				<select id="priceLevel">
																					<option value="1"><spring:message code="BzComposer.additem.fixed"/></option>
																					<option value="2"><spring:message code="BzComposer.additem.itemprice"/></option>
																				</select>
																			</td>
																			<td colspan="4">&nbsp;</td>
																		</tr>
																		<tr>
																			<td colspan="4">&nbsp;</td>
																			<td>
																				<input type="button" id="addPriceLevel" name="addPriceLevel" class="formbutton" value="<spring:message code='BzComposer.global.add'/>" onclick="addData();">
																			</td>
																			<td>
																				<input type="button" id="ClearPriceLevel2" name="ClearPriceLevel2" class="formbutton" value="<spring:message code='BzComposer.additem.clearbtn'/>">
																			</td>
																		</tr>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
													</div>
												</td>
												<td>&nbsp;</td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>

							<div id="images-6">
								<div id="content2" class="tabPage">
									<!-- add here the content of sixth tab -->
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0">
											<tr>
												<td colspan="3" style="width: 80%;">
													<spring:message code="BzComposer.additem.image"/>&nbsp;&nbsp;&nbsp;
													<input type="text" id="imageName" name="imageName" style="width: 90%;" />
												</td>
												<td align="right" style="width: 5%;">
													<input type="button" id="viewImage" name="viewImage" class="formbutton" value="<spring:message code='BzComposer.additem.viewimage'/>">
												</td>
												<td align="left" style="width: 13%;">
													<input type="file" class="formbutton" id="imgupload" multiple />
													<!--multiple <input type="button" id="browseImage" name="browseImage" class="formbutton" value="Browse Image"> -->
												</td>
												<td style="width: 2%;">&nbsp;</td>
											</tr>
											<tr>
												<td style="width: 20%;">
													<img src="" id="resultImage" style="height: 200px;width: 100%;border: 1px;" />
												</td>
												<td style="width: 20%;">
                                                    <img src="" id="resultImageTest2" style="height: 200px;width: 100%;border: 1px;" />
                                                </td>
                                                <td style="width: 20%;">
                                                    <img src="" id="resultImageTest3" style="height: 200px;width: 100%;border: 1px;" />
                                                </td>
                                                <td style="width: 19%;">
                                                    <img src="" id="resultImageTest4" style="height: 200px;width: 100%;border: 1px;" />
                                                </td>
                                                <td style="width: 19%;">
                                                    <img src="" id="resultImageTest5" style="height: 200px;width: 100%;border: 1px;" />
                                                </td>
                                                <td style="width: 2%;">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="6">&nbsp;</td>
											</tr>
										</table>
									</div>
								</div>
							</div>

							<!-- History tab starts -->
							<div id="history-11">
								<div id="content2" class="tabPage">
									<!-- add here the content of tenth tab -->
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0">
											<tbody>
											<tr>
												<td><spring:message code='BzComposer.additem.filteroption'/>
													<select id="filterOption" style="width:150px;">
														<option>All</option>
														<option>Custom</option>
														<option>Today</option>
														<option>This Month</option>
														<option>This Quarter</option>
														<option>This Year</option>
														<option>1 Year</option>
														<option>2 Year</option>
														<option>3 Year</option>
														<option>This Month-to-Date</option>
														<option>This Quarter-to-Date</option>
														<option>This Year-to-Date</option>
														<option>Last 10 days</option>
														<option>Last 30 days</option>
														<option>Last 60 days</option>
														<option>1 Week</option>
													</select>
												</td>
												<td>&nbsp;</td>
												<td>
                                                    <input type="text" id="search" name="search" class="formbutton"
                                                           value="<spring:message code='BzComposer.additem.search'/>" size="5"/>
                                                </td>
                                            </tr>
                                            <tr>
												<td>
													<spring:message code="BzComposer.additem.datefrom"/>
													&nbsp;&nbsp;
													<input type="date" id="dateFrom" style="width:150px;" />
												</td>
												<td>
													<spring:message code="BzComposer.additem.dateto"/>
													&nbsp;&nbsp;
													<input type="date" id="dateTo" style="width:150px;" />
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.additem.itemcode"/>&nbsp;&nbsp;&nbsp;
													<select style="width:150px;">
														<option></option>
													</select>
												</td>
												<td>
													<spring:message code='BzComposer.additem.startinginventoryquantity'/>
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td colspan="3">
													<div id="stockInOut" style="height:300px;width:100%;">
														<ul id="stockInOut">
															<li style="font-size: 12px;"><a href="#stockInOut-1">
																<spring:message code="BzComposer.additem.stockinout"/>
															</a></li>
														</ul>
														<div id="stockInOut-1">
															<div id="content1" class="tabPage">
																<!-- add here the content of first tab -->
																<div id="table-negotiations">
																	<table class="tabla-listados" cellspacing="0">
																		<thead>
																		<tr>
																			<th style="font-size:12px;">
																				<spring:message code="BzComposer.additem.date"/>
																			</th>
																			<th style="font-size:12px;">
																				<spring:message code="BzComposer.additem.customerorvendor"/>
																			</th>
																			<th style="font-size:12px;">
																				<spring:message code="BzComposer.additem.invoiceorbill"/>
																			</th>
																			<th style="font-size:12px;">
																				<spring:message code="BzComposer.additem.invoicetype"/>
																			</th>
																			<th style="font-size:12px;">
																				<spring:message code="BzComposer.additem.quantity"/>
																			</th>
																			<th style="font-size:12px;">
																				<spring:message code="BzComposer.additem.qtyonhand"/>
																			</th>
																		</tr>
																		</thead>
																		<tbody>

																		</tbody>
																	</table>
																</div>
															</div>
														</div>
													</div>
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>

						</div>
					</div>
					<!-- productTab Ends -->

					<!-- serviceTab Starts -->
					<div id="serviceTab" style="display:none;">
						<div id="table-negotiations">
							<table class="tabla-listados" cellspacing="0" style="margin-bottom:250px;">
								<thead>
								<tr>
									<th colspan="6">
										<spring:message code="BzComposer.Item.ServiceInformation" />
									</th>
								</tr>
								</thead>
                                <tbody>
								<tr>
									<td width="10%">
										<spring:message code="BzComposer.ServiceCode"/>
										<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
									</td>
									<td width="15%">
										<input type="text" id="serviceCode1" />
									</td>
									<td width="10%">
										<input type="button" class="formbutton" id="createCategory1" name="createCategory1" value="Create Category"/>
									</td>
									<td colspan="3">&nbsp;</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<input type="checkbox" id="subItem3" name="subItem3" value="subItem3">
										<spring:message code="BzComposer.Item.SubItemOf"/>
									</td>
									<td>
										<select id="subItemSelect3" disabled style="width:150px;">
											<option value="0"> <spring:message code="BzComposer.ComboBox.Select" /> </option>
											<option value="1"> Root</option>
										</select>
									</td>
									<td colspan="3">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Item.ServiceTitle"/>
										<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
									</td>
									<td colspan="3">
										<input type="text" id="serviceCode" size="30" />
									</td>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Item.ServiceRate"/>
									</td>
									<td>
										<input type="text" id="serviceRate"/>
									</td>
									<td>
										<spring:message code="BzComposer.TimeUnit"/>
									</td>
									<td width="10%">
										<select id="unitTime" style="width:150px;">
											<option value="0">Select</option>
											<option value="1">weekly</option>
											<option value="2">semi-monthly</option>
											<option value="3">monthly</option>
											<option value="4">quarterly</option>
											<option value="4">semi-yearly</option>
											<option value="5">yearly</option>
										</select>
									</td>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Item.ServiceDescription"/>
									</td>
									<td colspan="2">
										<textarea rows="5" cols="45"></textarea>
									</td>
									<td colspan="3">&nbsp;</td>
								</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- serviceTab Ends -->

					<!-- recurringServiceTab Starts -->
					<div id="recurringServiceTab" style="display:none;">
						<div id="table-negotiations">
							<table class="tabla-listados" cellspacing="0" style="margin-bottom:250px;">
								<thead>
								<tr>
									<th colspan="6">
										<spring:message code="BzComposer.Item.ServiceInformation" />
									</th>
								</tr>
								</thead>
                                <tbody>
								<tr>
									<td width="10%">
										<spring:message code="BzComposer.ServiceCode"/>
										<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
									</td>
									<td width="15%">
										<input type="text" id="serviceCode" />
									</td>
									<td width="10%">&nbsp;</td>
									<td width="10%">&nbsp;</td>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Item.ServiceTitle"/>
										<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
									</td>
									<td>
										<input type="text" id="serviceCode" />
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Item.ServiceRate"/>
									</td>
									<td>
										<input type="text" id="serviceRate"/>
									</td>
									<td>
										<spring:message code="BzComposer.TimeUnit"/>
									</td>
									<td>
										<select id="unitTime">
											<option value="0">Select</option>
											<option value="1">weekly</option>
											<option value="2">semi-monthly</option>
											<option value="3">monthly</option>
											<option value="4">quarterly</option>
											<option value="4">semi-yearly</option>
											<option value="5">yearly</option>
										</select>
									</td>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Item.ServiceDescription" />
									</td>
									<td colspan="2">
										<textarea rows="5" cols="45" ></textarea>
									</td>
									<td colspan="3">&nbsp;</td>
								</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- recurringServiceTab Ends -->

					<!-- giftCertificateTab Starts -->
					<div id="giftCertificateTab" style="display:none;">
						<div id="table-negotiations">
							<table class="tabla-listados" cellspacing="0" style="margin-bottom:100px;">
								<thead>
								<tr>
									<th colspan="6">
										<spring:message code="BzComposer.Item.GiftCertificate"/>
									</th>
								</tr>
								</thead>
                                <tbody>
								<tr>
									<td width="10%">
										<spring:message code="BzComposer.Code"/>
										<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
									</td>
									<td width="20%">
										<input type="text" id="certificateCode" value="GC0001" readonly="readonly"/>
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Item.ItemName"/>
										<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
									</td>
									<td>
										<input type="text" id="itemName" size="60"/>
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="Bizcomposer.Configuration.active"/>
									</td>
									<td>
										<input type="radio" id="itemActive" name="itemActive" value="Active"/>
										<spring:message code="Bizcomposer.active"/>
										<input type="radio" id="itemInActive" name="itemInActive" value="Inactive"/>
										<spring:message code="BzComposer.InActive"/>
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Price"/>
										<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
									</td>
									<td>
										<input type="text" id="itemPrice" name="itemPrice"/>
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.RedeemPrice"/>
										<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
									</td>
									<td>
										<input type="text" id="itemRedeemPrice" name="itemRedeemPrice"/>
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Details"/>
									</td>
									<td>
										<textarea rows="3" cols="45" id="details"></textarea>
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.UseDays"/>
										<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
									</td>
									<td>
										<input type="text" id="useDays" name="useDays"/>
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Note"/>
									</td>
									<td>
										<textarea rows="5" cols="45"></textarea>
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.CertificateLogo"/>
										<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
									</td>
									<td>
										<input type="file" id="certificateLogo" name="certificateLogo"/>
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- giftCertificateTab Ends -->

					<!-- inventoryAssemblyTab Starts -->
					<div id="inventortAssemblyTab" style="display:none;">
						<div id="table-negotiations">
							<table class="tabla-listados" cellspacing="0" style="margin-bottom:100px;">
								<thead>
								<tr>
									<th colspan="7">
										<spring:message code="BzComposer.InventoryAssemblyInformation"/>
									</th>
								</tr>
								</thead>
                                <tbody>
								<tr>
									<td width="10%">
										<spring:message code="BzComposer.ItemNameNumber"/>
									</td>
									<td width="15%">
										<input type="text" id="itemNameNumber" />
									</td>
									<td width="10%">
										<spring:message code="BzComposer.Report.vendor.BalanceSummary.Account"/>
									</td>
									<td width="15%">
										<form:select path="accountId">
											<option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
											<form:options items="${AccountList}" itemValue="value" itemLabel="label" />
										</form:select>
									</td>
									<td width="10%">&nbsp;</td>
									<td width="15%">&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Purchase.PurchaseOrder.Cost"/>
									</td>
									<td>
										<input type="text" id="itemCost"/>
									</td>
									<td>
										<spring:message code="BzComposer.SalesTax"/>
									</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.SalesPrice"/>
									</td>
									<td>
										<input type="text" id="salesPrice" name="salesPrice"/>
									</td>
									<td>
										<spring:message code="BzComposer.Item.PurchasePrice"/>
									</td>
									<td>
										<input type="text" id="purchasePrice1" value="${itemDto.purchasePrice}"/>
									</td>
									<td colspan="3">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.Item.Description"/>
									</td>
									<td>
										<textarea rows="5" cols="45"></textarea>
									</td>
									<td colspan="5">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<input type="checkbox" id="itemTaxable" name="itemTaxable">
										<spring:message code="BzComposer.Item.TaxableItem"/>
									</td>
									<td colspan="6">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="3">
										<table border="1" style="width: 100%">
											<tr>
												<td><spring:message code="BzComposer.sales.Item"/></td>
												<td><spring:message code="BzComposer.Item.Description"/></td>
												<td><spring:message code="BzComposer.Customer.Type"/></td>
												<td><spring:message code="BzComposer.Purchase.PurchaseOrder.Cost"/></td>
												<td><spring:message code="Bizcomposer.qty"/></td>
												<td><spring:message code="eSalesInvoiceDetails.Total"/></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
										</table>
									</td>
									<td>
										<input type="button" id="delete" class="formbutton" name="delete" value="Delete"/>
									</td>
									<td colspan="3">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<spring:message code="BzComposer.BuildPoint"/>
									</td>
									<td>
										<input type="text" id="buildPoint" name="buildPoint"/>
									</td>
									<td>
										<spring:message code="BzComposer.OnHand"/>
									</td>
									<td>
										<input type="text" id="onHand" name="onHand"/>
									</td>
									<td>
										<spring:message code="Bzcomposer.TotalValue"/>
									</td>
									<td>
										<input type="text" id="totalValue" name="totalValue"/>
									</td>
									<td>&nbsp;</td>
								</tr>
								</tbody>
							</table>
						</div>
					</div>

				</td>
			</tr>
			<tr align="center">
				<td colspan="3">
					<table style="font-size:14px;">
						<tr>
							<td>
								<button type="button" class="formbutton" style="padding:7 15px;" onclick="reloadPage();"><spring:message code='BzComposer.global.cancel' /></button>
								<button type="button" class="formbutton" style="padding:7 15px;" onclick="CheckMambership(this.form);"><spring:message code='BzComposer.global.save' /></button>
							</td>
						</tr>
					</table>
				</td>
			</tr>
	</table>
</div>
</td>
</tr>
</table>
</div>
</div>
</div>
</div>
</div>
</div>
<!-- end Contents -->
    <input type="hidden" name="tabid" id="tabid" value="" />
    <input type="hidden" name="itemIndex" id="itemIndex" value="${itemIndex}" />
    <input type="hidden" name="inventoryId" id="inventoryId" value="${itemDto.inventoryId}" />
</form:form>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" />
<script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
</body>
</html>
<script>
let itemID = 0;
let itemIndex = 0;
function Init(){
	call11(<%= request.getParameter("ItemType")%>);
	let lSize = document.getElementById("lSize").value;
    if(lSize > 0){
        document.getElementById('0$$').className = "even";
    }
    itemIndex = document.getElementById('itemIndex').value;
    hightlightROW();
}
function hightlightROW(){
    let currROW2 = document.getElementById(itemIndex+'$$');
    if(currROW2 != null){
        currROW2.className = "draft";
    }
}

function numbersonly(e, val){
    var temp = val.indexOf(".");
    var unicode = e.charCode? e.charCode : e.keyCode;
    if (unicode != 8){
        //if the key is not the backspace key (which we should allow)
        if(unicode == 46 && temp == -1){
            return true;
        }
        else if (unicode < 48||unicode > 57) //if not a number
            return false; //disable key press
    }
}

function setRowId(rowID, rowIndex, flag) {
    let lSize = document.getElementById("lSize").value;
    for (i=0; i<lSize; i++) {
        let currROW = document.getElementById(i+'$$');
        if(currROW == null) continue;
        if(i%2 == 1){
            currROW.className = "odd";
        }else{
            currROW.className = "even";
        }
    }
    if(flag){
        itemID = rowID;
        itemIndex = rowIndex;
        if(rowIndex%2 == 1){ ;
            document.getElementById(rowIndex+"$$").classList.remove('odd');
        }
        document.getElementById(rowIndex+'$$').classList.add('draft');
        loadTemplate(itemID);
    }
}

function hide_other(val){
	cat = false;    //document.getElementById('chk_cat').checked;
	if(cat==true){
		document.getElementById("hide_all").style.visibility="hidden";
		document.getElementById('labelid').style.visibility="hidden";
		document.getElementById('photoid').style.visibility="hidden";
		document.getElementById('drop_cat').disabled=true;
	}
	else{
		document.getElementById("hide_all").style.visibility="visible";
		document.getElementById('labelid').style.visibility="visible";
		document.getElementById('photoid').style.visibility="visible";
		document.getElementById('drop_cat').disabled=false;
	}
}

function reloadPage() {
    location.reload();
}
function CheckMambership(form) {
	debugger;
	var membershipLevel = "<%= request.getAttribute("membershipLevel")%>";
	var size = "<%= request.getAttribute("itemSize")%>";
	if(membershipLevel == "Standard"){
		if(size>=100){
			debugger;
			return maxnumberofuserdialog();
		}else {
			return  ShowAdd(form);
		}
	}else if(membershipLevel == "Professional"){
		if(size>=500){
			debugger;
			return maxnumberofuserdialog();
		}else {
			return  ShowAdd(form);
		}
	}
}
function maxnumberofuserdialog()
{
	event.preventDefault();
	$("#maxnumberofuserdialog").dialog({
    	resizable: false,
        height: 250,
        width: 800,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function ShowAdd(form){
	val = form.itemType.value;
	$("#drop_cat").removeAttr("disabled");
	if(val==1){
	    document.getElementById('purchasePrice').value = document.getElementById('purchasePrice').value
	}else{
	    document.getElementById('purchasePrice').value = document.getElementById('purchasePrice1').value
	}
	if(val=="1"){
		cat = false;    // document.getElementById('chk_cat').checked;
		if(cat==false){
			if(form.itemCode.value==""){
				return showitemcodedialog();
				form.itemCode.focus();
			}
			else if(document.getElementById('purchasePrice').value==""){
				return showpurchasepricedialog();
				form.purchasePrice.focus();
			}
			else if(form.salePrice.value==""){
                return showsalepricedialog();
                form.salePrice.focus();
            }
			else if(form.qty.value==""){
				return showqueantitydialog();
				form.qty.focus();
			}
			else if(form.weight.value==""){
				return showweightdialog();
				form.weight.focus();
			}
			else{
				event.preventDefault();
				$("#showItemDialog").dialog({
                    resizable: false,
                    height: 200,
                    width: 500,
                    modal: true,
                    buttons: {
                        "<spring:message code='BzComposer.global.ok'/>": function () {
                            document.getElementById('tabid').value = "AddItem";
                            let supplierIDsItems = $('#supplierName tr > td');
                            let supplierIDs = '';
                            for(let x=0; x<supplierIDsItems.length; x++){
                                supplierIDs = supplierIDs+','+supplierIDsItems[x].id;
                            }
                            $('#supplierIDs').val(supplierIDs);

                            document.forms['AddItemfrm'].action = "Item?tabid=AddItem&ItemType="+val;
                            document.forms['AddItemfrm'].submit();
                        },
                        <spring:message code='BzComposer.global.cancel'/>: function () {
                            $(this).dialog("close");
                            return false;
                        }
                    }
                });
                return false;
			}
		}
		else if(cat==true){
			if(form.itemCode.value==""){
				return showitemcodedialog();
				form.itemCode.focus();
			}else{
				form.salePrice.value="0";
				form.purchasePrice.value="0";
				form.qty.value="0";
				form.weight.value="0";
				event.preventDefault();
				$("#showItemDialog").dialog({
				    	resizable: false,
				        height: 200,
				        width: 500,
				        modal: true,
				        buttons: {
				            "<spring:message code='BzComposer.global.ok'/>": function () {
				            	document.forms[0].action = "Item?tabid=AddItem&ItemType="+val;
								document.forms[0].submit();
				            },
				            <spring:message code='BzComposer.global.cancel'/>: function () {
				                $(this).dialog("close");
				                return false;
				            }
				        }
				    });
				    return false;
			}
		}
	}
	else if(val=="2"){
        event.preventDefault();
        $("#showItemDialog").dialog({
            resizable: false,
            height: 200,
            width: 500,
            modal: true,
            buttons: {

                "<spring:message code='BzComposer.global.ok'/>": function () {
                    document.forms['AddItemfrm'].action = "Item?tabid=AddItem&ItemType="+val;
                    document.forms['AddItemfrm'].submit();
                },
                <spring:message code='BzComposer.global.cancel'/>: function () {
                    $(this).dialog("close");
                    return false;
                }
            }
        });
	    return false;
	}
	else if(val=="3"){
		if(form.itemCodeSub.value==""){
			return showitemcodesubdialog();
			form.itemCodeSub.focus();
		}else{
			event.preventDefault();
			$("#showItemDialog").dialog({
			    	resizable: false,
			        height: 200,
			        width: 500,
			        modal: true,
			        buttons: {
			            "<spring:message code='BzComposer.global.ok'/>": function () {
			            	document.forms[0].action = "Item?tabid=AddItem&ItemType="+val;
							document.forms[0].submit();
			            },
			            <spring:message code='BzComposer.global.cancel'/>: function () {
			                $(this).dialog("close");
			                return false;
			            }
			        }
			    });
			    return false;
		}
	}
	else if(val=="4"){
		if(form.itemCodeSer.value==""){
			return showservicenamedialog();
			form.itemCodeSer.focus();
		}
		else{
            event.preventDefault();
            $("#showItemDialog").dialog({
                resizable: false,
                height: 200,
                width: 500,
                modal: true,
                buttons: {
                    "<spring:message code='BzComposer.global.ok'/>": function () {
                        document.forms['AddItemfrm'].action = "Item?tabid=AddItem&ItemType="+val;
                        document.forms['AddItemfrm'].submit();
                        //$('form').submit();
                    },
                    <spring:message code='BzComposer.global.cancel'/>: function () {
                        $(this).dialog("close");
                        return false;
                    }
                }
            });
            return false;
		}
	}
}

function call11(value){
	if(value==1){
		document.getElementById('productTab').style.display='block';
		document.getElementById('serviceTab').style.display='none';
		document.getElementById('recurringServiceTab').style.display='none';
		document.getElementById('giftCertificateTab').style.display='none';
		document.getElementById('inventortAssemblyTab').style.display='none';
	}
	else if(value==2){
		document.getElementById('productTab').style.display='none';
		document.getElementById('serviceTab').style.display='block';
		document.getElementById('recurringServiceTab').style.display='none';
		document.getElementById('giftCertificateTab').style.display='none';
		document.getElementById('inventortAssemblyTab').style.display='none';
	}
	else if(value==3){
		document.getElementById('productTab').style.display='none';
		document.getElementById('serviceTab').style.display='none';
		document.getElementById('recurringServiceTab').style.display='block';
		document.getElementById('giftCertificateTab').style.display='none';
		document.getElementById('inventortAssemblyTab').style.display='none';
	}
	else if(value==4)
	{
		document.getElementById('productTab').style.display='none';
		document.getElementById('serviceTab').style.display='none';
		document.getElementById('recurringServiceTab').style.display='none';
		document.getElementById('giftCertificateTab').style.display='block';
		document.getElementById('inventortAssemblyTab').style.display='none';
	}
	else if(value==5)
	{
		document.getElementById('productTab').style.display='none';
		document.getElementById('serviceTab').style.display='none';
		document.getElementById('recurringServiceTab').style.display='none';
		document.getElementById('giftCertificateTab').style.display='none';
		document.getElementById('inventortAssemblyTab').style.display='block';
	}
}

function hide_service(val){
	category = document.getElementById('sercat');
	if(category.checked==true){
		document.getElementById("hide_ser").style.visibility="hidden";
	}
	else{
		document.getElementById("hide_ser").style.visibility="visible";
	}
}

function setupSupplirNames(){
    $('#supplierName').html('');
    let supplierIDs = document.getElementById('supplierIDs').value;
    if(supplierIDs!=null && supplierIDs.trim()!=""){
        let supplierIDArray = supplierIDs.split(',');
        for(let x=0; x<supplierIDArray.length; x++){
            let clientVendorOptions = document.getElementById('clientVendor').options;
            for(let y=0; y<clientVendorOptions.length; y++){
                let option = clientVendorOptions[y];
                if (option.value == supplierIDArray[x]) {
                    let supID = supplierIDArray[x];
                    let supName = option.text;
                    $('#supplierName').append("<tr id='supplier"+supID+"' onclick='highlightSelectedSupplier("+supID+");'><td id="+supID+">"+supName+"</td></tr>");
                    break;
                }
            }
        }
    }
}
function loadTemplate(InvId) {
    if(InvId==0) return;
    $.ajax({
        type: "POST",
        url: "ItemAjax?tabid=loadItemByTemplate&InvId="+InvId,
        data: {InvId: InvId},
    }).done(function (data) {
        //$("html").html(data);
        $("#inventoryId").val(data.inventoryId);
        $("#itemCode").val(data.itemCode);
        $("#itemName").val(data.itemName);
        $("#drop_cat").val(data.tectcmd);
        $('#itemTaxable1').prop( "checked", data.itemTaxable?true:false );
        $('#dropShipping1').prop( "checked", data.dropShipping?true:false );
        $('#discounted1').prop( "checked", data.discounted?true:false );
        $('#consignedItem1').prop( "checked", data.consignedItem?true:false );
        $("#accountId").val(data.accountId);
        $("#serialNum").val(data.serialNum);
        $("#productSKU").val(data.productSKU);
        $("#orderUnit").val(data.orderUnit);
        $("#qty").val(data.qty);
        $("#reorderPoint").val(data.reorderPoint);
        $("#locationId").val(data.locationId);
        $("#weight").val(data.weight);
        $("#weightUnit").val(data.weightUnit);
        $("#actualWeight").val(data.actualWeight);
        $("#purchasePrice").val(data.purchasePrice);
        $("#salePrice").val(data.salePrice);
        $("#dealerPrice").val(data.dealerPrice);
        $("#supplierSKU").val(data.supplierSKU);
        $('#primarySupplier1').prop( "checked", data.primarySupplier?true:false );
        $("#supplierIDs").val(data.supplierIDs);
        setupSupplirNames();
        $("#minOrderUnit").val(data.minOrderUnit);
        $("#textAreaContent").val(data.textAreaContent);
    });
}

function showCategoryDialog(){
	event.preventDefault();
	$("#showCategoryDialog").dialog({
		resizable: false,
	    height: 200,
	    width: 350,
	    modal: true,
	    buttons: {
			"<spring:message code='BzComposer.global.ok'/>": function () {
	        	$(this).dialog("close");
			}
		}
	});
	return false;
}
function showCantaddItemDialog(){
	event.preventDefault();
	$("#showCantaddItemDialog").dialog({
		resizable: false,
	    height: 200,
	    width: 550,
	    modal: true,
	    buttons: {
			"<spring:message code='BzComposer.global.ok'/>": function () {
	        	$(this).dialog("close");
			}
		}
	});
	return false;
}

function showitemcodedialog(){
	event.preventDefault();
	$("#showitemcodedialog").dialog({
		resizable: false,
	    height: 200,
	    width: 350,
	    modal: true,
	    buttons: {
			"<spring:message code='BzComposer.global.ok'/>": function () {
	        	$(this).dialog("close");
			}
		}
	});
	return false;
}

function showsalepricedialog(){
	event.preventDefault();
	$("#showsalepricedialog").dialog({
		resizable: false,
	    height: 200,
	    width: 350,
	    modal: true,
	    buttons: {
			"<spring:message code='BzComposer.global.ok'/>": function () {
	        	$(this).dialog("close");
			}
		}
	});
	return false;
}

function showpurchasepricedialog(){
	event.preventDefault();
	$("#showpurchasepricedialog").dialog({
		resizable: false,
	    height: 200,
	    width: 350,
	    modal: true,
	    buttons: {
			"<spring:message code='BzComposer.global.ok'/>": function () {
	        	$(this).dialog("close");
			}
		}
	});
	return false;
}

function showqueantitydialog(){
	event.preventDefault();
	$("#showqueantitydialog").dialog({
		resizable: false,
	    height: 200,
	    width: 350,
	    modal: true,
	    buttons: {
			"<spring:message code='BzComposer.global.ok'/>": function () {
	        	$(this).dialog("close");
			}
		}
	});
	return false;
}

function showweightdialog(){
	event.preventDefault();
	$("#showweightdialog").dialog({
		resizable: false,
	    height: 200,
	    width: 350,
	    modal: true,
	    buttons: {
			"<spring:message code='BzComposer.global.ok'/>": function () {
	        	$(this).dialog("close");
			}
		}
	});
	return false;
}

function showcodediscountdialog(){
	event.preventDefault();
	$("#showcodediscountdialog").dialog({
		resizable: false,
	    height: 200,
	    width: 350,
	    modal: true,
	    buttons: {
			"<spring:message code='BzComposer.global.ok'/>": function () {
	        	$(this).dialog("close");
			}
		}
	});
	return false;
}

function showitemcodesubdialog(){
	event.preventDefault();
	$("#showitemcodesubdialog").dialog({
		resizable: false,
	    height: 200,
	    width: 350,
	    modal: true,
	    buttons: {
			"<spring:message code='BzComposer.global.ok'/>": function () {
	        	$(this).dialog("close");
			}
		}
	});
	return false;
}

function showservicenamedialog(){
	event.preventDefault();
	$("#showservicenamedialog").dialog({
		resizable: false,
	    height: 200,
	    width: 350,
	    modal: true,
	    buttons: {
			"<spring:message code='BzComposer.global.ok'/>": function () {
	        	$(this).dialog("close");
			}
		}
	});
	return false;
}
</script>
<!-- Dialog box used in sales order page -->
<div id="showCategoryDialog" style="display:none;">
	<p><spring:message code="Bzcomposer.additem.selectcategoryname"/></p>
</div>
<div id="showCantaddItemDialog" style="display:none;">
	<p>
		<spring:message code="Bzcomposer.additem.notadditem"/>
		<!-- You can't add this item because it is already available -->
	</p>
</div>
<div id="deleteCrossSellProductDialog" style="display:none;">
	<p>
		<spring:message code="Bzcomposer.additem.deleteselecteditem"/>
		<!-- Do you want to delete this item? -->
	</p>
</div>
<div id="showitemcodedialog" style="display:none;">
	<p><spring:message code="BzComposer.additem.inventorynamevalidation"/></p>
</div>
<div id="showsalepricedialog" style="display:none;">
	<p><spring:message code="BzComposer.additem.salepricevalidation" /></p>
</div>
<div id="showpurchasepricedialog" style="display:none;">
	<p><spring:message code="BzComposer.additem.purchasepricevalidation" /></p>
</div>
<div id="showqueantitydialog" style="display:none;">
	<p><spring:message code="BzComposer.additem.quantityvalidation" /></p>
</div>
<div id="showweightdialog" style="display:none;">
	<p><spring:message code="BzComposer.additem.weightvalidation" /></p>
</div>
<div id="showcodediscountdialog" style="display:none;">
	<p><spring:message code="BzComposer.additem.discountvalidation" /></p>
</div>
<div id="showitemcodesubdialog" style="display:none;">
	<p><spring:message code="BzComposer.additem.subtotalvalidation" /></p>
</div>
<div id="showservicenamedialog" style="display:none;">
	<p><spring:message code="BzComposer.additem.servicenamevalidation" /></p>
</div>
<div id="showItemDialog" style="display:none;">
	<p><spring:message code="BzComposer.additem.saveitemdetails" /></p>
</div>
<!-- Dialog box used in this page -->
<div id="maxnumberofuserdialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.maxnumberofitem"/></p>
</div>