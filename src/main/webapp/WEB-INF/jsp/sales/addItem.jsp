<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<script>
	self.moveTo(100, 100);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="../include/header.jsp"%>
<title><spring:message code="BzComposer.additemtitle"/> </title>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<script src="https://cdn.ckeditor.com/4.12.1/full/ckeditor.js"></script>
<style type="text/css">
.myTable {
    height: 150px;
    overflow-y: scroll;
}
tr.highlight td { background-color: navy; }
table.sortable thead { background-color: #eee; color: #666666; font-weight: bold; cursor: default; }
table.tabla-listados { width: 100%; border: 1px solid rgb(207, 207, 207); margin: 0px 0px 0px 0px; }
table.tabla-listados tbody tr.odd td { background: #e1e5e9; }
table.tabla-listados thead tr th { font-size: 14px; }
table.tabla-listados tbody tr td { font-size: 12px; }
</style>
<script type="text/javascript">
var pId,pName,pSKU,pQty,pAmount;
function getData(){
	var data = CKEDITOR.instances.textAreaContent.getData();
	$("#textAreaContent2").val(data);
	document.getElementById("Editor").style.display = "none";
	document.getElementById("tAreaContent").style.display = "block";
}
function getEditor(){
	var data = document.getElementById("textAreaContent2").value;
	CKEDITOR.instances['textAreaContent'].setData(data);
	document.getElementById("Editor").style.display = "block";
	document.getElementById("tAreaContent").style.display = "none";
}

function openThumbnail()
{
  document.getElementById("imgupload1").click();
  var ima = $('#imgupload1').val();
  /* var im = $('input[type=file]').val().replace(/C:\\fakepath\\/i, '');
  debugger */
  $('#thumbnail').val(ima);
}

function showSelectedOption(priceLevelId,priceLevelDesc,priceLevelPercentage)
{
	size = document.getElementById('priceLevelSize').value;
	/* for(i=0;i<size;i++)
	{
		var row1=document.getElementById(i+"$$");
		row1.className = "";
	} */
	/* var rd=document.getElementById(rid);
	rd.className = "draft"; */

	$('#priceLevelName').val(priceLevelDesc);
	$('#pricePercentage').val(priceLevelPercentage);
}

function showSelectedProduct(productId,productName,productCode,productQty,productType,productPrice)
{
	var product = productId;
	pId = productId;
	pName = productCode;
	pSKU = productName;
	pQty = productQty;
	pAmount = productPrice;
	debugger
	$("#eBayProductTable tr").each(function() {
		$("#eBayProductTable tr").removeClass("draft");
	});
	var rows = document.getElementById("eBayProductTable").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	debugger
	for(i=0;i<rows;i++){
		var row1=document.getElementById("productId"+productId);
		row1.className = "draft";
	}
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

function addToCrossSellProduct()
{
	debugger
	var rows = document.getElementById("eBayProductTable").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	debugger
	for(i=0;i<rows;i++)
	{
		var rowId = document.getElementById("ProductId");
		if(rowId.className == "draft")
		{

		}
		/*var row1=document.getElementById("productId"+productId);
		row1.className = "draft"; */
	}
}

function Remove() {
	/* var row = document.getElementById("rowId").value;
   	debugger
   	//var name = row.getElementsByTagName("TD")[0].innerHTML;
   	if (confirm("Do you want to delete: " + row + "Row?")) {
	//Get the reference of the Table.
	var table = document.getElementById("addTableRow");
	debugger */
    $(this).parent().parent('tr').remove();
	//}
};

function addData()
{

}

function showCategoryDialog()
{
	debugger;
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
function showCantaddItemDialog()
{
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

function showitemcodedialog()
{
	debugger;
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

function showsalepricedialog()
{
	debugger;
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
	debugger;
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

function showqueantitydialog()
{
	debugger;
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

function showweightdialog()
{
	debugger;
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

function showcodediscountdialog()
{
	debugger;
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

function showitemcodesubdialog()
{
	debugger;
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

function showservicenamedialog()
{
	debugger;
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
<script>
	self.moveTo(100,100);

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
	/*  $('#browseImage').click(function(e)
	 {
		 //$('#imgupload').trigger('click');
		 $('#resultImage').fadeIn("fast").attr('src',URL.createObjectURL(e.target.files[0]));
	 });  */

	/*  $(document).on('click', "[id^=deleteOption]", function() {
		  $(this).parents("tr").remove();
	}); */

	/* $('#eBayProductTable tr').click(function (event) {
        $(this).parent().addClass('highlight');
   }); */

	$('#createCategory').click(function(){
		var category = $('#itemCode').val();
		if(category == ""){
			return showCategoryDialog();
		}else{
		    $('#tabid').val('AddItemAsCategory');
		    document.forms['AddItemfrm'].action = "Item?tabid=AddItemAsCategory";
            document.forms['AddItemfrm'].submit();
		}
	});

   $('#createCategory1').click(function(){
		var category = $('#serviceCode1').val();
		if(category == "")
		{
			return showCategoryDialog();
		}
	});

   $('#addCrossSell').click(function(){
	var isAvailable = $('#crossSellProductTable tr > td:contains('+pName+')').length;
	if(isAvailable == 0)
	{
		$('#crossSellProductTable').append("<tr id='crossSellProductId"+pId+"' value="+pId+" onclick='showSelectedCrossSellProduct("+pId+");'><td>"+pName+"</td><td>"+pSKU+"</td><td>"+pQty+"</td><td>"+pAmount+"</td></tr>");
	}
	else
	{
		return showCantaddItemDialog();
	}
   });

	$('#deleteCrossSellProduct').click(function(){
		if($("#crossSellProductTable tr").hasClass('draft'))
		{
			$("#crossSellProductTable tr").each(function() {
				if($(this).hasClass('draft'))
				{
					/*if(confirm("Do you want to delete this item?"))
					{
						$(this).remove();
					}*/
					debugger;
					event.preventDefault();
					$("#deleteCrossSellProductDialog").dialog({
				    	resizable: false,
				        height: 200,
				        width: 500,
				        modal: true,
				        buttons: {
				            "<spring:message code='BzComposer.global.ok'/>": function () {
				            	debugger;
				                $(this).dialog("close");
				                $(this).remove();
				                debugger;
				            },
				            "<spring:message code='BzComposer.global.cancel'/>": function () {
				                $(this).dialog("close");
				                return false;
				            }
				        }
				    });
				    return false;
				}
			});
		}
		else
		{
			alert("<bean:message key='BzComposer.common.selectCrossellProduct'/>");
		}
   });
	$('#availableStores').change(function(){
		var storeId = $(this).val();
		if(storeId == 3 || storeId == 9)
		{
			alert("<bean:message key='BzComposer.configuration.esales.selectesalesstore'/>");
		}
	});

	$('#clientVendor').change(function()
	{
		debugger
		var id = $("#clientVendor").val();
		debugger
		var name = $('#clientVendor option:selected').text();
		var myWindow;
		if(id == 0)
		{
			debugger
			 mywindow = window.open("Customer?tabid=addSupplier","_blank","scrollbars=No,height=900,width=1400,status=yes,toolbar=no,menubar=no,location=no");
			debugger
			myWindow.moveTo(90,90);
		}
		else if(id = 10101010)
		{
			window.stop();
		}
	});

	$('#addSupplierBtn').click(function(){
		debugger
		var id = $("#clientVendor").val();
		debugger
		var name = $('#clientVendor option:selected').text();
		var names = name.split(' ');
		debugger
		if(id == 0){
			alert("<bean:message key='BzComposer.common.selectSupplier'/>");
		}
		else{
			var rows = document.getElementById("supplierName").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			debugger
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
		if($("#supplierName tr").hasClass('draft'))
		{
			$("#supplierName tr").each(function() {
				if($(this).hasClass('draft'))
				{
					if(confirm("Do you want to delete this item?"))
					{
						$(this).remove();
					}
				}
			});
		}
		else
		{
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


	$('#addOption').click(function(){
		var table ="<table border='1'><input type='hidden' id='rowId' value="+id+" name='rowId'/><tr><td>Title</td><td>ItemType</td><td>Is Required</td><td>Sort Order</td><td></td></tr>"+
    		"<tr><td><input type='text' id='title' value='"+id+"'/></td><td><input type='text' id='itemType'/></td><td><input type='text' id='title'/></td>"+
    		"<td><input type='text' id='title'/></td><td align='center'><input type='button' class='formbutton' id='deleteOptionBtn' name='deleteOptionBtn' value='Delete Option'/></td></tr></table><br><br>";

    	id++;
		$('#addTableRow').append(table);
	});


	/* $('#deleteOptionBtn').click(function(){
		debugger
	    $(this).parent().parent('tr').remove();
		debugger
	});  */

	/* $('#addTableRow').on('click', 'input[type="button"]', function () {
	    //$(this).parent().parent().remove();
	    $(this).closest('tr').remove();
	}); */

	/* $(document).on('click', '.formbutton', function() {
	    $(this).parent().parent('tr').remove();
	}); */

	$('#deleteOptionBtn').click(function(){
		//$(this).parents("tr").remove();
		$("addTableRow").find('input[name="deleteOptionBtn"]').each(function(){
			if($(this).is(":clicked")){
                $(this).parents("tr").remove();
            }
       });
	});
});
</script>
</head>
<body onload="Init();">
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
            <% session.removeAttribute("SaveStatus"); %>
        </c:if>
	</span>
	<br />
</div>
<div id="addNewItemArea">
	<table style="font-size:14px;width:100%;" cellpadding="5" cellspacing="5">
        <tr>
            <td><spring:message code="BzComposer.global.type" />:</td>
            <td>
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
						<div id="tabs" style="height:650px;">
							<ul id="tabsAddItem">
								<li style="font-size: 12px;"><a href="#productInfo-1"><spring:message code="BzComposer.tabs.productinformation"/></a></li>
								<li style="font-size: 12px;"><a href="#pricing-2"><spring:message code="BzComposer.tabs.pricing"/></a></li>
								<!-- <li style="font-size: 12px;"><a href="#description-3"><spring:message code="BzComposer.tabs.description"/></a></li> -->
								<!-- <li style="font-size: 12px;"><a href="#channelSetting-4"><spring:message code="BzComposer.tabs.channelsetting"/></a></li> -->
								<!-- <li style="font-size: 12px;"><a href="#alternateBarcodeSKU-5"><spring:message code="BzComposer.tabs.alternatebarcodeandskus"/></a></li> -->
								<li style="font-size: 12px;"><a href="#images-6"><spring:message code="BzComposer.tabs.images"/></a></li>
								<!-- <li style="font-size: 12px;"><a href="#webFields-7"><spring:message code="BzComposer.tabs.webfields"/></a></li> -->
								<!-- <li style="font-size: 12px;"><a href="#eBay-8"><spring:message code="BzComposer.tabs.ebay"/></a></li> -->
								<!-- <li style="font-size: 12px;"><a href="#itemCategory-9"><spring:message code="BzComposer.tabs.itemcategory"/></a></li> -->
								<!-- <li style="font-size: 12px;"><a href="#customOption-10"><spring:message code="BzComposer.tabs.customoption"/></a></li> -->
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
													<!-- <input type="button" id="createCategory" class="formbutton" value="<spring:message code='BzComposer.additem.createcategorybtn'/>" /> -->
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
													<input type="text" id="itemTitle" name="itemTitle" style="width:100%;" />
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
													<form:input type="number" path="orderUnit" value="0" />
												</td>
												<td>
													<spring:message code="BzComposer.additem.qtyonhand"/>
													<span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td>
                                                    <form:input type="number" path="qty" value="0" />
                                                </td>
												<td>&nbsp;</td>
                                            </tr>
                                            <tr>
												<td>
													<spring:message code="BzComposer.additem.reorderpoint"/>
												</td>
												<td>
													<form:input type="text" path="reorderPoint" />
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
													<form:input type="number" path="weight" value="0" style="width:100px;" />
													<form:select path="weightUnit">
                                                        <form:options items="${weightList}" itemValue="value" itemLabel="label" />
                                                    </form:select>
												</td>
												<td>
													<spring:message code="BzComposer.additem.actualweight"/>
												</td>
												<td>
													<form:input type="number" path="actualWeight" value="0" />
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
											    <td>
                                                    <spring:message code="BzComposer.additem.purchaseprice"/>
                                                    <span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                                </td>
                                                <td>
                                                    $<form:input type="number" path="purchasePrice" value="0.00" />
                                                </td>
											    <td>
                                                    <spring:message code="BzComposer.additem.saleprice"/>
                                                    <span><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
                                                </td>
                                                <td>
                                                    $<form:input type="number" path="salePrice" value="0.00" />
                                                </td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <spring:message code="BzComposer.additem.dealerprice"/>
                                                </td>
                                                <td>
                                                    $<form:input type="number" path="dealerPrice" value="0.00" />
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
                                            <tr>
                                                <td>
                                                    <strong><spring:message code="BzComposer.PriceLevel"/></strong>
                                                </td>
                                                <td colspan="4">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="font-size:12px;">
                                                    <spring:message code="BzComposer.customer.CustomerType"/> :
                                                </td>
                                                <td style="font-size:12px;">
                                                    <form:select path="customerType">
                                                        <form:option value="1"><spring:message code="BzComposer.common.general"/></form:option>
                                                        <form:option value="2"><spring:message code="BzComposer.common.priority"/></form:option>
                                                        <form:option value="3"><spring:message code="BzComposer.common.dealer"/></form:option>
                                                        <form:option value="4"><spring:message code="BzComposer.customer.Customer"/></form:option>
                                                    </form:select>
                                                </td>
                                                <td colspan="3">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="BzComposer.common.priority"/></td>
                                                <td><input value="${defaultCongurationData.priceLevelPriority}" disabled="disabled" />%</td>
                                                <td><spring:message code="BzComposer.common.dealer"/></td>
                                                <td><input value="${defaultCongurationData.priceLevelDealer}" disabled="disabled" />%</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="BzComposer.customer.Customer"/></td>
                                                <td><input value="${defaultCongurationData.priceLevelCustomer}" disabled="disabled" />%</td>
                                                <td><spring:message code="BzComposer.common.general"/></td>
                                                <td><input value="${defaultCongurationData.priceLevelGeneral}" disabled="disabled" />%</td>
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
                                                    <form:input type="number" path="minOrderUnit" value="0" style="width:50px;" />
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

							<div id="description-3" style="display:none;">
								<div id="content2" class="tabPage">
									<!-- add here the content of third tab -->
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0">
											<tr>
												<td id="Editor" style="display: block;">
													<!-- <form:textarea path="textAreaContent" /> -->
													<script>
														/* CKEDITOR.editorConfig = function( config ) {
                                                        config.toolbar = [
                                                            { name: 'document', groups: [ 'mode', 'document', 'doctools' ], items: [ 'Source', '-', 'Save', 'NewPage', 'Preview', 'Print', '-', 'Templates' ] },
                                                            { name: 'clipboard', groups: [ 'clipboard', 'undo' ], items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
                                                            { name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ], items: [ 'Find', 'Replace', '-', 'SelectAll', '-', 'Scayt' ] },
                                                            { name: 'forms', items: [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ] },
                                                            '/',
                                                            { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ], items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'CopyFormatting', 'RemoveFormat' ] },
                                                            { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ], items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl', 'Language' ] },
                                                            { name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
                                                            { name: 'insert', items: [ 'Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe' ] },
                                                            '/',
                                                            { name: 'styles', items: [ 'Styles', 'Format', 'Font', 'FontSize' ] },
                                                            { name: 'colors', items: [ 'TextColor', 'BGColor' ] },
                                                            { name: 'tools', items: [ 'Maximize', 'ShowBlocks' ] },
                                                            { name: 'others', items: [ '-' ] },
                                                            { name: 'about', items: [ 'About' ] }
                                                        ];

                                                        // Toolbar groups configuration.
                                                        config.toolbarGroups = [
                                                            { name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
                                                            { name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
                                                            { name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ] },
                                                            { name: 'forms' },
                                                            '/',
                                                            { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
                                                            { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
                                                            { name: 'links' },
                                                            { name: 'insert' },
                                                            '/',
                                                            { name: 'styles' },
                                                            { name: 'colors' },
                                                            { name: 'tools' },
                                                            { name: 'others' },
                                                            { name: 'about' }
                                                        ];
                                                        };
                                                        CKEDITOR.replace( 'textAreaContent' ); */
														/* CKEDITOR.editorConfig = function( config ) {
                                                             */
														/* config.toolbar = [
															{ name: 'save', items: [ 'savebtn','Undo','Redo' ] },
															{ name: 'clipboard', items: [ 'Cut','Copy','Paste','PasteText','PasteFromWord'] },
															{ name: 'document', items: [ 'Find','Replace'] },
															'/',
															{ name: 'lists', items: [ 'NumberedList','BulletedList','Outdent','Indent'] },
															{ name: 'insert', items: [ 'Image','Table','Smiley','SpecialChar'] },
															{ name: 'link', items: ['Link','Unlink'] },
															'/',
															{ name: 'basicstyles', items: [ 'Font','FontSize','Bold','Italic','Underline','Strike','Subscript','Superscript'] },
															//'/',
															{ name: 'align', items: [ 'JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'] }
														]; */
														/* CKEDITOR.config.toolbar = [
															   ['Styles','Format','Font','FontSize'],
															   '/',
															   ['Bold','Italic','Underline','StrikeThrough','-','Undo','Redo','-','Cut','Copy','Paste','Find','Replace','-','Outdent','Indent','-','Print'],
															   '/',
															   ['NumberedList','BulletedList','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
															   ['Image','Table','-','Link','Flash','Smiley','TextColor','BGColor','Source']
															] ;
													};
													CKEDITOR.replace( 'textAreaContent' ); */
													// CKEDITOR.replace( 'textAreaContent' );
													</script>
												</td>
												<td id="tAreaContent" style="display: none;">
													<textarea id="textAreaContent2" rows="10" cols="130"></textarea>
												</td>
											</tr>
											<tr>
												<td>
													<button type="button" class="formbutton" onclick="getData();"><spring:message code='BzComposer.additem.htmlbtn'/></button>
													&nbsp;&nbsp;
													<button type="button" class="formbutton" onclick="getEditor();"><spring:message code='BzComposer.global.edit'/></button>
												</td>
											</tr>
											<!-- <td>Description</td>
                                            <td>
                                                <select id="fontStyle">
                                                    <option id="BodyText">Body Text</option>
                                                    <option id="Paragraph">Paragraph</option>
                                                    <option id="Blockquote">Blockquote</option>
                                                    <option id="Preformatted">Preformatted</option>
                                                    <option id="Heading-1">Heading 1</option>
                                                    <option id="Heading-2">Heading 2</option>
                                                    <option id="Heading-3">Heading 3</option>
                                                    <option id="Heading-4">Heading 4</option>
                                                    <option id="Heading-5">Heading 5</option>
                                                    <option id="Heading-6">Heading 6</option>
                                                    <option id="UnorderedList">Unordered List</option>
                                                    <option id="OrderedList">Ordered List</option>
                                                </select>
                                            </td>
                                            <td>
                                                <select id="fontType">
                                                    <option value="Default">Default</option>
                                                    <option value="serif">serif</option>
                                                    <option value="sans-serif">sans-serif</option>
                                                    <option value="monospaced">monospaced</option>
                                                </select>
                                            </td>
                                            <td>
                                                <font size="5"><a><b>A</b></a></font>
                                                &nbsp;
                                                <input type="color" name="favcolor">
                                            </td>
                                            <td>
                                                <font size="5"><a><b>B</b></a></font>
                                                &nbsp;
                                                <font size="5"><a><i>I</i></a></font>
                                                &nbsp;
                                                <font size="5"><a><u>U</u></a></font>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="12">&nbsp;</td>
                                        </tr> -->
										</table>
									</div>
								</div>
							</div>

							<div id="channelSetting-4" style="display:none;">
								<div id="content2" class="tabPage">
									<!-- add here the content of fourth tab -->
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0">
											<tr>
												<td>
													<spring:message code="BzComposer.additem.selectstore"/>
												</td>
												<td colspan="4">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="4">
													<table class="tabla-listados" border="1">
														<thead>
														<tr>
															<td><spring:message code="Bzcomposer.additem.active"/></td>
															<td><spring:message code="Bzcomposer.additem.storename"/></td>
															<td><spring:message code="BzComposer.additem.idasin"/></td>
															<td><spring:message code="BzComposer.additem.price"/></td>
														</tr>
														</thead>
														<tbody>
														<tr>
															<!-- <td>
                                                                <input type="checkbox" id="active" name="active" value="active">
                                                            </td> -->
															<td>
																<c:if test="${not empty storeList}">
																	<c:forEach items="${storeList}" var="objList1">
																		<tr id="channelSettings">
																			<td>
																				<input type="checkbox" id="active" name="active" value="active">
																			</td>
																			<td>
																				${objList1.channelSettingName}
																			</td>
																			<td>&nbsp;</td>
																			<td>0.0</td>
																		</tr>
																	</c:forEach>
																</c:if>
															</td>
														</tr>
											<!-- Amazon Market Place File-Amazon Market
                                                    </td>
                                                    <td>&nbsp;</td>
                                                    <td>0.0</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" id="active" name="active" value="active">
                                                    </td>
                                                    <td>
                                                        Amazon Seller File Import-CDI Amazon Seller
                                                    </td>
                                                    <td>&nbsp;</td>
                                                    <td>0.0</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" id="active" name="active" value="active">
                                                    </td>
                                                    <td>
                                                        Amazon Seller online-cdromusa
                                                    </td>
                                                    <td>&nbsp;</td>
                                                    <td>0.0</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" id="active" name="active" value="active">
                                                    </td>
                                                    <td>
                                                        eBay online-online
                                                    </td>
                                                    <td>&nbsp;</td>
                                                    <td>0.0</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" id="active" name="active" value="active">
                                                    </td>
                                                    <td>
                                                        WebDesignForUs.com-cdromusa
                                                    </td>
                                                    <td>&nbsp;</td>
                                                    <td>0.0</td>
                                                </tr> -->
														</tbody>
													</table>
												</td>
												<td colspan="1">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="3" align="left">
													<spring:message code="BzComposer.additem.asinnote"/>
												</td>
												<td align="right">
													<input type="button" id="edit" name="edit" class="formbutton" value="<spring:message code='BzComposer.global.edit'/>">
												</td>
												<td colspan="1">
													&nbsp;
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>

							<div id="alternateBarcodeSKU-5" style="display:none;">
								<div id="content2" class="tabPage">
									<!-- add here the content of fifth tab -->
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0">
											<tbody>
											<tr>
												<td align="right">
													<spring:message code="BzComposer.additem.alternatebarcodes"/>
												</td>
												<td>
													&nbsp;
												</td>
												<td align="left">
													<spring:message code="BzComposer.additem.alternateskus"/>
												</td>
												<td colspan="2">&nbsp;</td>
											</tr>
											<tr>
												<td align="right">
													<select id="barcodes" multiple="multiple" style="height: 190px;width: 130px;">
														<option value="0">
														</option>
													</select>
												</td>
												<td>&nbsp;</td>
												<td align="left">
													<select id="sku" multiple="multiple" style="height: 190px;width: 130px;">
														<option value="0">
														</option>
													</select>
												</td>
												<td>
													<table>
														<tr>
															<td colspan="2">&nbsp;</td>
														</tr>
														<tr>
															<td>
																<spring:message code="BzComposer.additem.barcode"/>:
															</td>
															<td>
																<input type="text" id="barcode" name="barcode" style="width: 60px;">
															</td>
														</tr>
														<tr>
															<td colspan="2">&nbsp;</td>
														</tr>
														<tr>
															<td>
																<spring:message code="BzComposer.additem.sku"/>:
															</td>
															<td>
																<input type="text" id="barcode" name="barcode" style="width: 60px;">
															</td>
														</tr>
														<tr>
															<td colspan="2">&nbsp;</td>
														</tr>
														<tr>
															<td>
																<input type="submit" id="add" name="add" class="formbutton" value="<spring:message code='BzComposer.global.add'/>">
															</td>
															<td>
																<input type="submit" id="delete" name="delete" class="formbutton" value="<spring:message code='BzComposer.global.delete'/>">
															</td>
														</tr>
													</table>
												</td>
												<td colspan="1">&nbsp;</td>
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

							<div id="webFields-7" style="display:none;">
								<div id="content2" class="tabPage">
									<!-- add here the content of seventh tab -->
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0">
											<tr>
												<td>
													<spring:message code="BzComposer.additem.producttitle"/>
												</td>
												<td style="width: 80px;">
													<input type="text" id="productTitle" name="productTitle">
												</td>
												<td colspan="10">&nbsp;</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.additem.keyword"/>
												</td>
												<td style="width: 80px;">
													<input type="text" id="keyword" name="keyword">
												</td>
												<td colspan="10">&nbsp;</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.additem.thumbnail"/>
												</td>
												<td style="width: 50px;">
													<input type="text" id="thumbnail" name="thumbnail">
												</td>
												<td>
													<input type="button" id="viewImage1" class="formbutton" value="<spring:message code='BzComposer.additem.viewimage'/>" disabled="disabled">
												</td>
												<td>
													<input type="file" id="imgupload1" class="formbutton" value=""/>
													<!-- <input type="button" id="browseImage" name="browseImage" onclick="openThumbnail();" class="formbutton" value="Browse Image"> -->
													<img src="" id="resultImageThumbnail" style="display: none;">
												</td>
												<td colspan="8">&nbsp;</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.additem.image"/>
												</td>
												<td style="width: 50px;">
													<input type="text" id="image2" name="image2">
												</td>
												<td>
													<input type="button" id="viewImage2" class="formbutton" value="<spring:message code='BzComposer.additem.viewimage'/>" disabled="disabled">
												</td>
												<td>
													<input type="file" id="imgupload2" class="formbutton" value=""/>
													<!-- <input type="button" id="browseImage2" name="browseImage2" class="formbutton" value="Browse Image"> -->
													<img src="" id="resultImage2" style="display: none;">
												</td>
												<td colspan="8">&nbsp;</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.additem.header"/>
												</td>
												<td>
													<textarea rows="5" cols="12" id="itemHeader"></textarea>
												</td>
												<td colspan="10">&nbsp;</td>
											</tr>
											<tr>
												<td>
													<spring:message code="Bzcomposer.additem.footer"/>
												</td>
												<td>
													<textarea rows="5" cols="12" id="itemFooter"></textarea>
												</td>
												<td colspan="10">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="10">
													<table class="tabla-listados" style="width: 500px;">
														<thead>
														<tr>
															<td colspan="2">
																<b><spring:message code="BzComposer.additem.esaleschannelsetting"/></b>
															</td>
														</tr>
														<tr>
															<td colspan="2"><u></u></td>
														</tr>
														</thead>
														<tbody>
														<tr>
															<td colspan="2">
																<table border="1" style="width: 100%;">
																	<tr>
																		<td>
																			<spring:message code="BzComposer.additem.description"/>
																		</td>
																		<td>
																			<spring:message code="BzComposer.ComboBox.Select"/>
																		</td>
																	</tr>
																	<c:if test="${not empty ItemForm}">
																		<%--<logic:present name="" path="listOfExistingeSaleChannelList" >--%>
																		<c:forEach items="${ItemForm}" var="objList1">
																			<tr id="channelSettings">
																				<td>
																						${objList1.eSaleChannelListName}
																				</td>
																				<td>
																					<input type="checkbox" id="active" name="active" value="active">
																				</td>
																			</tr>
																		</c:forEach>
																	</c:if>
																	<!-- <tr>
                                                                        <td>
                                                                        </td>
                                                                        <td>
                                                                            <input type="checkbox" id="active" name="active" checked>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            Don't Synch with Amazon Seller online
                                                                        </td>
                                                                        <td>
                                                                            <input type="checkbox" id="active" name="active" checked>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            Don't Synch with Amazon Market Place File
                                                                        </td>
                                                                        <td>
                                                                            <input type="checkbox" id="active" name="active" checked>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            Don't Synch with WebDesignForUs.com
                                                                        </td>
                                                                        <td>
                                                                            <input type="checkbox" id="active" name="active" checked>
                                                                        </td>
                                                                    </tr> -->
																</table>
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<input type="checkbox" id="ignoreQOH" name="ignoreQOH">
																<spring:message code="Bzcomposer.additem.ignoreqoh"/>
															</td>
														</tr>
														</tbody>
													</table>
												</td>
												<td colspan="2">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="12">&nbsp;</td>
											</tr>
										</table>
									</div>
								</div>
							</div>

							<div id="eBay-8" style="display:none;">
								<div id="content2" class="tabPage">
									<!-- add here the content of eighth tab -->
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0">
											<thead>
											<tr>
												<th colspan="4">
													<spring:message code="BzComposer.additem.crosssell"/>
												</th>
											</tr>
											</thead>
											<tbody>
											<tr>
												<td colspan="4" align="left">
													<spring:message code="BzComposer.additem.productlist"/>
												</td>
											</tr>
											<tr>
												<td colspan="2" style="width: 60%;">
													<div class="myTable">
														<table id="eBayProductTable" border="1" style="width: 100%;">
															<thead>
															<tr>
																<td>
																	<spring:message code="BzComposer.additem.itemname"/>
																</td>
																<td>
																	<spring:message code="BzComposer.additem.sku"/>
																</td>
																<td>
																	<spring:message code="BzComposer.additem.itemtitle"/>
																</td>
																<td>
																	<spring:message code="BzComposer.additem.qty"/>
																</td>
															</tr>
															</thead>
															<tbody>
															<c:if test="${not empty ItemForm}">
																<%
																	int eBayProductListSize = 0;
																%>
																<%--														<logic:present name="" path="listOfExistingeBayProducts">--%>
																<c:forEach items="${ItemForm}" var="objList1">
																	<tr id="productId${objList1.eBayProductId}" value="${objList1.eBayProductId}"
																		onclick="showSelectedProduct('${objList1.eBayProductId}',
																				'${objList1.eBayProductName}',
																				'${objList1.eBayProductCode}',
																				'${objList1.eBayProductQty}',
																				'${objList1.eBayProductType}',
																				'${objList1.eBayProductPrice};')">
																		<td id="productCode${objList1.eBayProductId}">
																				${objList1.eBayProductId.eBayProductCode}
																		</td>
																		<td id="productName${objList1.eBayProductId}">
																				${objList1.eBayProductCode}
																		</td>
																		<td id="productType${objList1.eBayProductId}">
																				${objList1.eBayProductId.eBayProductType}
																		</td>
																		<td id="productQty${objList1.eBayProductId}">
																				${objList1.eBayProductQty}
																			<input type="hidden" value="${objList1.eBayProductPrice}"/>
																			<input type="hidden" value="${objList1.eBayProductName}"/>
																		</td>
																	</tr>
																</c:forEach>
																<input type="hidden" id="productListSize" name="productListSize" value='<%=eBayProductListSize%>' />
															</c:if>
															</tbody>
														</table>
													</div>
												</td>
												<td align="center">
													<input type="button" id="addCrossSell" class="formbutton" name="addCrossSell" value="<spring:message code='BzComposer.additem.addtocrosssellbtn'/>">
												</td>
												<td colspan="1">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="4">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="4" align="left">
													<spring:message code="BzComposer.additem.crosssellproducts"/>
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<table border="1" id="crossSellProductTable" style="width: 100%;">
														<thead>
														<tr>
															<td>
																<spring:message code="BzComposer.additem.itemname"/>
															</td>
															<td>
																<spring:message code="BzComposer.additem.sku"/>
															</td>
															<td>
																<spring:message code="BzComposer.additem.qty"/>
															</td>
															<td>
																<spring:message code="BzComposer.additem.amount"/>
															</td>
														</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</td>
												<td align="center">
													<input type="button" class="formbutton" id="deleteCrossSellProduct" name="deleteCrossSellProduct"
														   value="<spring:message code='BzComposer.global.delete'/>">
												</td>
												<td colspan="1"></td>
											</tr>
											<tr>
												<td colspan="4">&nbsp;</td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>

							<div id="itemCategory-9" style="display:none;">
								<div id="content2" class="tabPage">
									<!-- add here the content of ninth tab -->
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0">
											<thead>
											<tr>
												<th colspan="12">&nbsp;</th>
											</tr>
											</thead>
											<tbody>
											<tr>
												<td colspan="12" align="left">
													<spring:message code="Bzcomposer.additem.storelist"/>
												</td>
											</tr>
											<tr>
												<td style="width: 20%;">
													<form:select path="storeId" multiple="multiple" id="availableStores" style="height: 150px;width: 150px;">
														<c:forEach items="${itemStoreList}" var="obj">
															<option value="${obj.value}">${obj.label}</option>
														</c:forEach>
													</form:select>
												</td>
												<td align="left">
													<table>
														<tr>
															<td>
																<spring:message code="Bzcomposer.additem.selectedcategories"/>
															</td>
															<td>
																<select id="categories">
																	<option value="0"></option>
																</select>
															</td>
														</tr>
														<tr>
															<td colspan="2" align="center">
																<input type="submit" id="save" class="formbutton" name="save"
																	   value="<spring:message code='BzComposer.global.save'/>">
															</td>
														</tr>
													</table>
												</td>
												<td colspan="10">
													&nbsp;
												</td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>

							<div id="customOption-10" style="display:none;">
								<div id="content2" class="tabPage">
									<!-- add here the content of tenth tab -->
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0">
											<thead>
											<tr>
												<th colspan="2">
													<spring:message code="BzComposer.tabs.customoption"/>
												</th>
											</tr>
											</thead>
											<tbody>
											<tr>
												<td align="right">
													<input type="button" id="addOption" name="addOption"
														   value="<spring:message code='Bzcomposer.additem.addnewoption'/>" class="formbutton"/>
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td colspan="2">
													<div>
														<table id="addTableRow">
															<tbody></tbody>
														</table>
													</div>
												</td>
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
										<form:input type="text" path="purchasePrice"/>
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

					<!-- inventoryAssemblyTab Ends -->

					<!-- inventoryTab starts -->
					<%-- <div id="inventoryTab">
						<table style="width: 100%;" class="tabla-listados" cellspacing="0">
							<thead>
								<tr>
									<th colspan="12">
										<spring:message code="BzComposer.Item.InventoryInformation" />
									</th>
								</tr>
							</thead>
							<tr>
								<td>
									<table style="padding-left: 17px;padding-top: 13px;">
									<td><spring:message code="BzComposer.Item.InventryName" /></td>
										<tr>
											<td>Inventory code</td>
											<td><form:input path="itemCode" /></td>
											<td colspan="10">&nbsp;</td>
										</tr>
									</table>
								</td>
								<td colspan="11">&nbsp;</td>
							</tr>
							<tr>
								<td>
									<table style="padding-left: 116px;padding-top: 11px;">
										<tr>
											<td>
												<input type="checkbox" name="iscategory" id="chk_cat" onclick="hide_other(this.value);">
											<td>
												<spring:message code="BzComposer.Item.isCategory" />
											</td>
											<td>
												<spring:message code="BzComposer.Item.Subcategoryof" />
											</td>
											<td>
												<form:select path="tectcmd" disabled="false" value="0" id="drop_cat">
													<option value="0">
														<spring:message code="BzComposer.ComboBox.Select" />
													</option>
													<form:options items="${fillList}" itemValue="labelType" itemLabel="labelName" />
												</form:select>
											</td>
											<td>
												<spring:message code="BzComposer.Item.Barcode" />
											</td>
											<td>
												<form:input path="barcode" onkeydown="return numbersonly(event,this.value)" />
											</td>
											<td>
												<input type="checkbox" name="discontinued">
											</td>
											<td>
												<spring:message code="BzComposer.Item.Discontinued" />
											</td>
											<td>
												<div id="labelid">
													<spring:message code="BzComposer.Item.AddPicture" />
												</div>
											</td>
											<td>
												<div id="photoid">
													<form:input type="file" path="photoName" />
												</div>
											</td>
											<td colspan="2"></td>
										</tr>
									</table>
								</td>
								<td colspan="11">&nbsp;</td>
							</tr>
							<tr id="hide_all">
								<td colspan="12">
									<table>
										<tr>
											<td>
												<table>
													<tr>
														<td>
															<spring:message code="BzComposer.Item.InventoryTitle" />
														</td>
														<td>
															<input type="text" name="itemName" id="itm_name" size="60" />
														</td>
														<td>
															<input type="checkbox" name="taxable" id="chk_tax">
															<spring:message code="BzComposer.Item.Taxable" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table>
													<tr>
														<td style="padding-left: 30px;">
															<spring:message code="BzComposer.Item.Description" />
														</td>
														<td>
															<textarea name="invTitle" id="txt_invtitle" cols="100"></textarea>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table align="center" style="padding-left:25px">
													<tr>
														<td>
															<spring:message code="BzComposer.Item.SalePrice" />
														</td>
														<td>
															<input type="text" name="salePrice" id="sale_price"
															onkeydown="return numbersonly(event,this.value)" />
														</td>
														<td>
															<spring:message code="BzComposer.Item.PurchasePrice" />
														</td>
														<td>
															<input type="number" name="purchasePrice" id="Pur_price"
															onkeydown="return numbersonly(event,this.value)" />
														</td>
														<td>
															<spring:message code="BzComposer.Item.QtyonHand" />
														</td>
														<td>
															<input type="number" name="qty" id="qty" onkeydown="return numbersonly(event,this.value)" />
														</td>
														<td>
															<spring:message code="BzComposer.Item.Weight" />
														</td>
														<td>
															<input type="number" name="weight" id="weight" onkeydown="return numbersonly(event,this.value)" />
														</td>
													</tr>
													<tr>
														<td>
															<spring:message code="BzComposer.Item.Location" />
														</td>
														<td>
															<input type="text" name="location" id="loc" />
														</td>
														<td>
															<spring:message code="BzComposer.Item.Serial" />
														</td>
														<td>
															<input type="text" name="serialNum" id="serial" onkeydown="return numbersonly(event,this.value)" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div> --%>
					<!-- inventoryTab ends -->

					<!----servicesTab starts -->
					<%-- <div id="servicesTab" style="display:none">
						<table style="width: 100%;" class="tabla-listados" cellspacing="0">
							<thead>
								<tr>
									<th colspan="9">
										<spring:message code="BzComposer.Item.ServiceInformation" />
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<table style="padding-left: 17px;padding-top: 13px;">
										<td><spring:message code="BzComposer.Item.InventryName" /></td>
											<tr>
												<td>
													<spring:message code="BzComposer.Item.ServiceName" />
												</td>
												<td>
													<form:input path="itemCodeSer" />
												</td>
												<td>
													<spring:message code="BzComposer.Item.Subcategoryof" />
												</td>
												<td>
													<form:select path="tectcmdSer" disabled="disabled">
														<option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
														<form:options items="${fillList}" itemValue="labelType" itemLabel="labelName" />
													</form:select>
												</td>
											</tr>
										</table>
									</td>
									<td colspan="8">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<table style="padding-left: 116px;padding-top: 11px;">
											<tr>
												<td>
													<input type="checkbox" name="iscat" id="sercat" onclick="hide_service(this.value);">
													<spring:message code="BzComposer.Item.isCategory" />
												</td>
											</tr>
										</table>
									</td>
									<td colspan="8">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<table id="hide_ser" style="padding-left: 116px;padding-top: 11px;">
											<tr>
												<td>
													<spring:message code="BzComposer.Item.ServiceTitle" />
												</td>
												<td>
													<form:input path="itemNameSer" />
												</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.Item.ServiceDescription" />
												</td>
												<td>
													<form:textarea path="invTitleSer" />
												</td>
												<td>
													<input type="checkbox" name="taxableSer" id="taxser">
													<spring:message code="BzComposer.Item.Taxable" />
												</td>
											</tr>
											<tr>
												<td>
													<spring:message code="BzComposer.Item.ServiceRate" />
												</td>
												<td>
													<form:input path="serviceRate" onkeydown="return numbersonly(event,this.value)" />
												</td>
											</tr>
										</table>
									</td>
									<td colspan="8">&nbsp;</td>
								</tr>
							</tbody>
						</table>
					</div> --%>
					<!-- servicesTab ends -->

					<!---subtotalTab starts -->
					<%-- <div id="subtotalTab" style="display:none;">
						<table align="center" valign="Top" style="width: 100%;" class="tabla-listados" cellspacing="0">
							<thead>
								<tr>
									<th colspan="9">
										<spring:message code="BzComposer.Item.Subtotal" />
									</th>
								</tr>
							</thead>
							<tr>
								<td>
									<table style="padding-left: 17px;padding-top: 13px;">
										<td><spring:message code="BzComposer.Item.InventryName" /></td>
										<tr>
											<td>
												<spring:message code="BzComposer.Item.SubtotalName" />
											</td>
											<td>
												<form:input path="itemCodeSub" />
											</td>
										</tr>
										<tr>
										</tr>
										<tr>
											<td>
												<spring:message code="BzComposer.Item.Description" />
											</td>
											<td>
												<form:input path="itemNameSub" />
											</td>
										</tr>
										<tr>
										</tr>
									</table>
								</td>
								<td colspan="8">&nbsp;</td>
							</tr>
						</table>
					</div> --%>
					<!-- subTotalTab ends -->

					<!-- discountTab starts  -->
					<%-- <div id="discountTab" style="display:none;">
						<table style="width: 100%;" class="tabla-listados" cellspacing="0">
							<thead>
								<tr>
									<th colspan="9">
										<spring:message code="BzComposer.Item.Discount" />
									</th>
								</tr>
							</thead>
							<tr>
								<td>
									<table>
										<tr>
											<td>
												<spring:message code="BzComposer.Item.DiscountName" />
											</td>
											<td>
												<form:input path="itemCodeDis" />
											</td>
										</tr>
										<tr>
											<td>
												<spring:message code="BzComposer.Item.AmountofDiscount" />
											</td>
											<td>
												<form:input path="discountAmt" onkeydown="return numbersonly(event,this.value)" />
											</td>
										</tr>
										<tr>
											<td>
												<spring:message code="BzComposer.Item.DiscountTitle" />
											</td>
											<td>
												<form:textarea path="invTitleDis" />
											</td>
										</tr>
									</table>
								</td>
								<td colspan="8">
									&nbsp;
								</td>
							</tr>
						</table>
					</div> --%>
					<!-- discountTab ends -->
				</td>
			</tr>
			<tr align="center">
				<td colspan="3">
					<table style="font-size:14px;">
						<tr>
							<td>
								<button type="button" class="formbutton" onclick="CheckMambership(this.form);"><spring:message code='BzComposer.global.save' /></button>
							</td>
							<td>
								<button type="button" class="formbutton" onclick="ClearMe(this.form);"><spring:message code='BzComposer.additem.clearbtn' /></button>
							</td>
							<td>
								<button type="button" class="formbutton" onclick="CloseMe();"><spring:message code="BzComposer.global.close" /></button>
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
</form:form>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" />
<script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
</body>
</html>
<script>
window.onunload = refreshParent;
function refreshParent() {
    window.opener.location.reload();
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
function showCombo(){
	if(chkbox.checked==true){
		document.getElementById("cmdbox").disabled=false;
	}
	else
		document.getElementById("cmdbox").disabled=true;
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
	/* else if(membershipLevel == "Enterprise"){
		if(size>=10){
			debugger;
			return maxnumberofuserdialog();
		}else{
			$('#AddUser').modal('show');
		}
	} */
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
			else if(form.weight.value=="")
			{

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
		else if(cat==true){
			if(form.itemCode.value=="")
			{
				/* alert('<spring:message code="BzComposer.AddItem.InventoryName.Validation" />'); */
				return showitemcodedialog();
				form.itemCode.focus();
			}
			else
			{
				form.salePrice.value="0";
				form.purchasePrice.value="0";
				form.qty.value="0";
				form.weight.value="0";
				/* var res=window.confirm('<spring:message code="BzComposer.AddItem.Confirmation.Validation" />') */
				/* var res=window.confirm('<spring:message code="BzComposer.additem.insertitemvalidation" />')
				if (res)
				{
					document.forms[0].action = "Item?tabid=AddItem&ItemType="+val;
					document.forms[0].submit();
				} */
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
	else if(val=="2"){
		//if(form.itemCodeDis.value==""){
			/* alert('<spring:message code="BzComposer.AddItem.Discount.Validation" />'); */

		//	return showcodediscountdialog();
		//	form.itemCodeDis.focus();
		//}
		//else{
			/* var res=window.confirm('<spring:message code="BzComposer.AddItem.Confirmation.Validation" />') */
			/* var res=window.confirm('<spring:message code="BzComposer.additem.insertitemvalidation" />')
			 if (res){
				form.itemCode.value=form.itemCodeDis.value;
				document.forms[0].action = "Item?tabid=AddItem&ItemType="+val;
				document.forms[0].submit();
			} */
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
		//}
	}
	else if(val=="3"){
		if(form.itemCodeSub.value==""){
			/* alert('<spring:message code="BzComposer.AddItem.Subtotal.Validation" />'); */

			return showitemcodesubdialog();
			form.itemCodeSub.focus();
		}
		else{
			/* var res=window.confirm('<spring:message code="BzComposer.AddItem.Confirmation.Validation" />') */
			/* var res=window.confirm('<spring:message code="BzComposer.additem.insertitemvalidation" />')
			 if (res){
				form.itemCode.value=form.itemCodeSub.value;
				document.forms[0].action = "Item?tabid=AddItem&ItemType="+val;
				document.forms[0].submit();
			} */
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
	else if(val=="4"){
		if(form.itemCodeSer.value==""){
			/* alert('<spring:message code="BzComposer.AddItem.Service.Validation" />'); */

			return showservicenamedialog();
			form.itemCodeSer.focus();
		}
		else{
			/* var res=window.confirm('<spring:message code="BzComposer.AddItem.Confirmation.Validation" />') */
			/* var res=window.confirm('<spring:message code="BzComposer.additem.insertitemvalidation" />')
				if (res){
					form.itemCode.value=form.itemCodeSer.value;
					document.forms[0].action = "Item?tabid=AddItem&ItemType="+val;
					document.forms[0].submit();
				} */
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


function numbersonly(e,val){
		var temp=val.indexOf(".");
		var key=e.charCode? e.charCode : e.keyCode;
		if(window.event){
               	if(window.event.ctrlKey)
                        isCtrl = true;
                else
                    isCtrl = false;
        }
        else{
                if(e.ctrlKey)
                        isCtrl = true;
                else
                        isCtrl = false;
        }
        if(isCtrl){
	        if("v" == String.fromCharCode(key).toLowerCase()) {
	        	return false;
            }
            if("x" == String.fromCharCode(key).toLowerCase()) {
                return false;
            }
        }
		else if (key!=8){
			var str =String(val);
			var temp=val.indexOf(".");
			index=0;
			for(i=0;i<str.length;i++){
				if(str.charAt(i)=='.'){
					index=1;
					break;
				}
			}
			if(key==46 && temp==-1){
				 return true;
			}
			else if(key==37 || key==39){
				return true;
			}
			else if(key==110 && index==0){
				return true;
			}
			else if(key==190 && index==0){
				return true;
			}
			else if(key>=96 && key<=105){
				return true;
			}
			else if (key<48||key>57) //if not a number
				return false; //disable key press
		}
	}

function CloseMe(){
	window.close();
}
function ClearMe(form){
	form.tectcmd.value="0";
	form.photoName.value="";
	form.itemCode.value="";
	form.tectcmdSer.value="0";
	form.itemCodeSer.value="";
	form.barcode.value="";
	form.itemCodeSub.value="";
	form.itemCodeDis.value="";
	form.itemNameSub.value="";
	form.invTitleDis.value="";
	form.discountAmt.value="";
	form.itemNameSer.value="";
	form.invTitleSer.value="";
	form.serviceRate.value="";

	document.getElementById('itm_name').value="";
	document.getElementById('txt_invtitle').value="";
	document.getElementById('sale_price').value="";
	document.getElementById('Pur_price').value="";
	document.getElementById('qty').value="";
	document.getElementById('weight').value="";
	document.getElementById('loc').value="";
	document.getElementById('serial').value="";

	document.getElementById('chk_cat').checked=false;
	document.getElementById('chk_tax').checked=false;

	document.getElementById('taxser').checked=false;
	document.getElementById('sercat').checked=false;

	hide_other(0);
	hide_service(0);
}
function call11(value){
	/* if(value==1){
		document.getElementById('inventoryTab').style.display='block';
		document.getElementById('discountTab').style.display='none';
		document.getElementById('subtotalTab').style.display='none';
		document.getElementById('servicesTab').style.display='none';
	}
	else if(value==2){
		debugger
		document.getElementById('inventoryTab').style.display='none';
		debugger
		document.getElementById('discountTab').style.display='block';
		debugger
		document.getElementById('subtotalTab').style.display='none';
		debugger
		document.getElementById('servicesTab').style.display='none';
	}
	else if(value==3){
		debugger
		document.getElementById('inventoryTab').style.display='none';
		debugger
		document.getElementById('discountTab').style.display='none';
		debugger
		document.getElementById('subtotalTab').style.display='block';
		debugger
		document.getElementById('servicesTab').style.display='none';
	}
	else if(value==4)
	{
		document.getElementById('inventoryTab').style.display='none';
		debugger
		document.getElementById('discountTab').style.display='none';
		debugger
		document.getElementById('subtotalTab').style.display='none';
		debugger
		document.getElementById('servicesTab').style.display='block';
		debugger
	} */
	if(value==1)
	{
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
function Init(){
	call11(<%= request.getParameter("ItemType")%>);
	document.getElementById('availableStores').value = 3;
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
        $("#itemCode").val(data.itemCode);
        $("#itemTitle").val(data.itemName);
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
        //$("#textAreaContent2").val(data.textAreaContent);
        //getEditor();
    });
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
	<p><spring:message code="BzComposer.additem.insertitemvalidation" /></p>
</div>
<!-- Dialog box used in this page -->
<div id="maxnumberofuserdialog" style="display:none;">
	<p><spring:message code="BzComposer.addnewcustomer.maxnumberofitem"/></p>
</div>