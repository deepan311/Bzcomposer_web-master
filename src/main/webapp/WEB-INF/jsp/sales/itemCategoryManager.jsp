<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title><spring:message code="BzComposer.categorymanagertitle"/></title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style type="text/css">
div#pie {
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
	border-bottom: 1px solid rgba(5, 169, 197, 0.2);
	text-shadow: #999 0px 1px 1px;
	white-space: nowrap;
}
table.tabla-listados tbody tr td {
	font-size: .8em;
	padding: 5px 0px 5px 14px;
	background: #fff;
	vertical-align: top;
}
.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-draggable.ui-resizable {
	min-width: 55%;
	min-height: 300px;
	height: auto;
	width: 200px;
	display: block;
	z-index: 101;
}
#highlight { background-color: blue; }
.highlight { background-color: #00CED1 !important;color: #fff }

.switch { position: relative; display: inline-block; width: 50px; height: 28px; }
.slider { position: absolute; cursor: pointer;
    top: 0; left: 0; right: 0; bottom: 0;
    background-color: #ccc; -webkit-transition: .4s; transition: .4s;
}
.slider:before { position: absolute; content: "";
    height: 20px; width: 20px; left: 4px; bottom: 4px;
    background-color: white; -webkit-transition: .4s; transition: .4s;
}
input:checked + .slider { background-color: #17a2b8; }
input:checked + .slider:before {
  -webkit-transform: translateX(20px);
  -ms-transform: translateX(20px);
  transform: translateX(20px);
}

/* --------- Rounded sliders ---------- */
.slider.round { border-radius: 34px; }
.slider.round:before { border-radius: 50%; }
.paddingLeft30PX{ padding-left: 30px! important; }
.inactiveText { color: #ccc; }
</style>

</head>
<body onload="init();">
<div class="mb-3 clear custom-fixed-tabs">
    <div id="ddcolortabsline">&nbsp;</div>
    <div style="float: left; padding-top:10px;">
    	<span style="font-size: 1.2em; font-weight: bold; color: #05A9C5; margin: 30px 0px 15px 20px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
    		<spring:message code="BzComposer.categorymanager.itemcategorymanager"/>
    	</span>
    </div>
	<div class="tab-content" style="padding:20px;">
        <div class="table1 table2" id="devCategoryTable">
        <div class="float-left" style="width:20%;">
            <table class="table table-bordered table-sm" id="CategoryListTbl">
                <thead class="thead-light">
                    <tr>
                        <th class="text-center"><spring:message code="BzComposer.categorymanager.tab.categorylist"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${mainCategoryList}" var="curObject" varStatus="loop">
                        <tr onclick="mainCategoryClicked(${curObject.categoryID}, ${curObject.active}, '${curObject.categoryName}', '${curObject.description}')">
                            <td class="${curObject.active?'':'inactiveText'}"><strong>${curObject.categoryName} (${curObject.subCatCount})</strong></td>
                        </tr>
                        <c:forEach items="${subCategoryList}" var="curObject2">
                            <c:if test="${curObject.categoryID == curObject2.parentID}">
                            <tr class=="hideShowSubCatItems" onclick="subCategoryClicked(${curObject2.categoryID}, ${curObject2.parentID}, ${curObject2.active}, '${curObject2.categoryName}', '${curObject2.description}')">
                                <td class="paddingLeft30PX ${curObject2.active?'':'inactiveText'}">${curObject2.categoryName} (${curObject2.itemCount})</td>
                            </tr>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                <tbody>
            </table>
            <div class="btngroup2-fixed mt-3 text-center" style="position:relative;">
                <a style="color: #fff; font-size: 14px;" class="btn btn-info" id="AddCategoryBtn"><spring:message code="BzComposer.categorymanager.addcategory"/></a>
                <a style="color: #fff; font-size: 14px;" class="btn btn-info" id="EditCategoryBtn"><spring:message code="BzComposer.categorymanager.tab.editcategory"/></a>
            </div>
        </div>
        <div style="width:80%;" class="float-right">
            <div class="form-horizontal">
                <div class="form-group row">
                    <div class="col-md-3"></div>
                    <label class="col-md-2 col-form-label text-right"><spring:message code="BzComposer.categorymanager.enablecategory"/></label>
                    <div class="col-md-3">
                        <label class="switch">
                            <input type="checkbox" style="margin:5px;" id="categoryEnabled" /><span class="slider round"></span>
                        </label>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-3"></div>
                    <label class="col-md-2 col-form-label text-right"><spring:message code="BzComposer.categorymanager.categoryname"/></label>
                    <div class="col-md-3">
                        <input type="text" class="form-control" id="categoryNameA" />
                        <input type="hidden" class="form-control" id="categoryIDA" />
                        <input type="hidden" class="form-control" id="parentIDA" />
                        <input type="hidden" class="form-control" id="categoryTypeA" />

                    </div>
                </div>
            <!--    <div class="form-group row">
                    <div class="col-md-3"></div>
                    <label class="col-md-2 col-form-label text-right"><spring:message code="BzComposer.categorymanager.description"/></label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" id="categoryDetailsA" />
                    </div>
                </div> -->
                <div class="row">
                    <div class="col-md-2"></div>
                    <label class="col-md-2"><h6><spring:message code="BzComposer.categorymanager.itemlist"/></h6></label>
                </div>
            </div>
            <div class="form-horizontal">
            <div class="form-group row">
                <div class="col-md-2"></div>
            <table class="table table-bordered table-sm devAcCategoryListTable col-md-9" id="ItemListTbl">
                <thead class="thead-light">
                    <tr>
                        <th></th>
                        <th><spring:message code="BzComposer.categorymanager.categoryname"/></th>
                        <th><spring:message code="Bizcomposer.itemName"/></th>
                        <th><spring:message code="BzComposer.categorymanager.description"/></th>
                        <th><spring:message code="BzComposer.global.dateadded"/></th>
                        <th><spring:message code="BzComposer.categorymanager.active"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${itemList}" var="curObject" varStatus="loop">
                    <tr class="ItemDetailsRow">
                        <td hidden="id">${curObject.itemID}</td>
                        <td hidden="categoryID">${curObject.categoryID}</td>
                        <td hidden="description">${curObject.description}</td>
                        <td><input type="checkbox" class="itemListChk" value="${curObject.itemID}" /></td>
                        <td>${curObject.categoryName}</td>
                        <td>${curObject.itemName}</td>
                        <td>${curObject.description}</td>
                        <td>${curObject.dateAdded}</td>
                        <td>${curObject.active}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            </div>
            </div>
        </div>
        </div>
        <div class="text-center">
            <div class="btngroup2-fixed">
                <button class="btn btn-info" style="font-size: 14px;" id="AddItemBtn">
                    <spring:message code="BzComposer.global.add"/>
                </button>
                <button class="btn btn-info" style="font-size: 14px;" id="EditItemBtn">
                    <spring:message code="BzComposer.global.edit"/>
                </button>
                <button class="btn btn-info" style="font-size: 14px;" onclick="return deleteCategory()">
                    <spring:message code="BzComposer.global.delete"/>
                </button>
            </div>
        </div>
	</div>
</div>
<div class="btngroup2"></div>

<!--    Here is the AddCategory dialog started -->
<div class="container-fluid" id="AddEditCategoryDlg">
	<div class="table-responsive" style="padding:10 20px;font-size: 14px;border: 1px solid #ced4da;border-radius: .25rem;min-height: 220px">
         <h6><spring:message code="BzComposer.banking.maincategoryinfo"/></h6>
         <hr/>
	     <form:form action="/ItemCategoryManager?tabid=SaveItemCategory" method="post" onsubmit="return validateAddCategoryForm(this);" modelAttribute="itemCategoryDto">
	        <div class="row">
	            <div class="col-md-1"></div>
                <label class="col-md-3 text-right"><spring:message code="BzComposer.categorymanager.type"/></label>
                <div class="col-md-5">
					<form:select class="form-control" path="categoryType" onchange="setCategoryType(this.value)">
                        <option value="0"><spring:message code="BzComposer.categorymanager.maincategory"/></option>
                        <option value="1"><spring:message code="BzComposer.categorymanager.subcategory"/></option>
                    </form:select>
                    <form:hidden path="categoryID" />
				</div>
				<div class="col-md-3"></div>
			</div>
			<div class="row" id="parentIdDev">
                <div class="col-md-1"></div>
                <label class="col-md-3 text-right"><spring:message code="BzComposer.categorymanager.maincategory"/></label>
                <div class="col-md-5">
                    <form:select class="form-control" path="parentID">
                        <c:forEach items="${mainCategoryList}" var="curObject" varStatus="loop">
                            <option value="${curObject.categoryID}">${curObject.categoryName}</option>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-1"></div>
                <label class="col-md-3 text-right"><spring:message code="BzComposer.categorymanager.categoryname"/></label>
                <div class="col-md-5">
                    <form:input type="text" class="form-control" path="categoryName" />
                </div>
                <div class="col-md-3"></div>
            </div>
			<div class="row">
			    <div class="col-md-1"></div>
				<label class="col-md-3 text-right"><spring:message code="BzComposer.categorymanager.active"/></label>
				<div class="col-md-5">
					<form:select class="form-control" path="active">
                        <option value="1"><spring:message code="BzComposer.categorymanager.active"/></option>
                        <option value="0"><spring:message code="BzComposer.categorymanager.inactive"/></option>
                    </form:select>
				</div>
				<div class="col-md-3"></div>
			</div>
			<div class="row">
			    <div class="col-md-1"></div>
                <label class="col-md-3 text-right">
                    <spring:message code="BzComposer.categorymanager.description"/>
                </label>
                <div class="col-md-5">
                    <form:input type="text" class="form-control" path="description" />
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-8 pt-2" style="font-size: 14px;">
                    <button type="submit" class="btn btn-info"><spring:message code="BzComposer.global.save"/></button>
                    <button type="button" class="btn btn-info" onclick="closeCategoryDialog();"><spring:message code="BzComposer.global.close"/></button>
                </div>
            </div>
         </form:form>
	</div>
</div>

<!--    Here is the AddItem dialog started -->
<div class="container-fluid" id="AddEditItemDlg">
	<div class="table-responsive" style="padding:10 20px;font-size: 14px;border: 1px solid #ced4da;border-radius: .25rem;min-height: 220px">
         <h6><spring:message code="BzComposer.additem.iteminformation"/></h6>
         <hr/>
	     <form:form action="/ItemCategoryManager?tabid=SaveItemDetails" method="post" onsubmit="return validateAddItemForm(this);" modelAttribute="itemCategoryDto">
	        <div class="row">
                <div class="col-md-1"></div>
                <label class="col-md-3 text-right"><spring:message code="BzComposer.categorymanager.categoryname"/></label>
                <div class="col-md-5">
                    <form:select class="form-control" path="itemCategoryID">
                        <c:forEach items="${subCategoryList}" var="curObject" varStatus="loop">
                            <option value="${curObject.categoryID}">${curObject.categoryName}</option>
                        </c:forEach>
                    </form:select>
                    <form:hidden path="itemID" />
                    <form:hidden path="itemIDs" />
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-1"></div>
                <label class="col-md-3 text-right"><spring:message code="BzComposer.categorymanager.name"/></label>
                <div class="col-md-5">
                    <form:input type="text" class="form-control" path="itemName" />
                </div>
                <div class="col-md-3"></div>
            </div>
			<div class="row">
			    <div class="col-md-1"></div>
				<label class="col-md-3 text-right"><spring:message code="BzComposer.categorymanager.active"/></label>
				<div class="col-md-5">
                    <form:select class="form-control" path="itemActive">
                        <option value="1"><spring:message code="BzComposer.categorymanager.active"/></option>
                        <option value="0"><spring:message code="BzComposer.categorymanager.inactive"/></option>
                    </form:select>
				</div>
				<div class="col-md-3"></div>
			</div>
			<div class="row" id="itemDescriptionROW">
			    <div class="col-md-1"></div>
                <label class="col-md-3 text-right">
                    <spring:message code="BzComposer.categorymanager.description"/>
                </label>
                <div class="col-md-5">
                    <form:input type="text" class="form-control" path="itemDescription" />
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-8 pt-2" style="font-size: 14px;">
                    <button type="submit" class="btn btn-info"><spring:message code="BzComposer.global.save"/></button>
                    <button type="button" class="btn btn-info" onclick="closeItemDialog();"><spring:message code="BzComposer.global.close"/></button>
                </div>
            </div>
         </form:form>
	</div>
</div>

<script>
$(document).ready(function () {
    debugger;
    $('tr').click(function () {
         var selected = $(this).hasClass("highlight");
         $("tr").removeClass("highlight");
         if(!selected)
             $(this).addClass("highlight");
    });

	 $("#AddEditCategoryDlg").hide();
	 $("#AddEditItemDlg").hide();
     $( "#AddCategoryBtn").on("click", function(){
         $( "#AddEditCategoryDlg").dialog({
            modal: true,
            title: 'Add Category'
         });
         $("#categoryID").val('0');
         $("#categoryName").val('');
         $("#description").val('');
         document.getElementById('parentIdDev').style.display = "none";
     });
     $( "#EditCategoryBtn").on("click", function(){
         if($("#CategoryListTbl tr.highlight").length==0){
            return selectcategorydialog();
            return false;
         }
         $( "#AddEditCategoryDlg").dialog({
            modal: true,
            title: 'Edit Category'
         });
         setCategoryType($("#categoryTypeA").val());
         $("#categoryType").val($("#categoryTypeA").val())
         $("#categoryID").val($("#categoryIDA").val());
         $("#parentID").val($("#parentIDA").val());
         $("#categoryName").val($("#categoryNameA").val());
         //$("#description").val($("#categoryDetailsA").val());
         $("#active").val($("#categoryEnabled").is(":checked")?1:0);
     });
     $( "#AddItemBtn").on("click", function(){
          $( "#AddEditItemDlg").dialog({
             modal: true,
             title: 'Add Item'
          });
          $("#itemID").val('0');
          $("#itemName").val('');
          $("#itemDescription").val('');
          if($("#categoryTypeA").val()==1){
            $("#itemCategoryID").val($("#categoryIDA").val());
          }
     });
     $( "#EditItemBtn").on("click", function(){
         if($("#ItemListTbl tr.highlight").length==0){
            return selectitemdialog();
            return false;
         }
         $( "#AddEditItemDlg").dialog({
             modal: true,
             title: 'Edit Item'
         });
         let ItemListTblCells = $("#ItemListTbl tr.highlight")[0].cells;
         $("#itemID").val(ItemListTblCells[0].innerText);
         $("#itemCategoryID").val(ItemListTblCells[1].innerText);
         $("#itemDescription").val(ItemListTblCells[2].innerText);
         $("#itemName").val(ItemListTblCells[5].innerText);
         $("#itemActive").val(ItemListTblCells[7].innerText=='true'?1:0);
         $("#itemDescriptionROW").show();

         let itemIDs = '';
         let itemNames = '';
         var inputs = document.querySelectorAll('.itemListChk');
         for (var i = 0; i < inputs.length; i++) {
             let curCheckbox = inputs[i];
             if(curCheckbox.checked){
                 itemIDs = itemIDs + curCheckbox.value + ',';
                 itemNames = itemNames + curCheckbox.parentElement.parentElement.cells[5].innerText + ',';
             }
         }
         itemIDs = itemIDs.substring(0, itemIDs.length-1);
         itemNames = itemNames.substring(0, itemNames.length-1);
         if(itemIDs.split(',').length>1){
            $("#itemDescriptionROW").hide();
            $("#itemName").val(itemNames);
            $("#itemIDs").val(itemIDs);
         }
     });
});

function init(){

    //let row1ID = $('.hideShowSubCatItems')[0].cells[0].innerText;

    if($("#CategoryListTbl tr").length>2){
        $("#CategoryListTbl tr")[2].cells[0].click();
    }
}

function closeCategoryDialog(){
    $('#AddEditCategoryDlg').dialog('close');
}
function closeItemDialog(){
    $('#AddEditItemDlg').dialog('close');
}
function mainCategoryClicked(categoryID, active, categoryName, description){
    setCategoryType(0);
    document.getElementById('categoryTypeA').value = 0;
    document.getElementById('parentIDA').value = '';
    document.getElementById('categoryIDA').value = categoryID;
    document.getElementById('categoryEnabled').checked = active;
    document.getElementById('categoryNameA').value = categoryName;
    //document.getElementById('categoryDetailsA').value = description;
}
function subCategoryClicked(categoryID, parentID, active, categoryName, description){
    let tblRows = document.getElementById('ItemListTbl').getElementsByTagName("tr");
    for(let x=1; x<tblRows.length; x++){
        let myRow = tblRows[x];
        if(myRow.cells[1].innerText == categoryID)
            myRow.style.display = "table-row";
        else
            myRow.style.display = "none";
    }
    document.getElementById('categoryTypeA').value = 1;
    document.getElementById('categoryIDA').value = categoryID;
    document.getElementById('parentIDA').value = parentID;
    document.getElementById('categoryEnabled').checked = active;
    document.getElementById('categoryNameA').value = categoryName;
    //document.getElementById('categoryDetailsA').value = description;
}

function setCategoryType(catType){
    if(catType==0){
        document.getElementById('parentIdDev').style.display = "none";
    }else{
        document.getElementById('parentIdDev').style.display = "flex";
    }
}

function validateAddCategoryForm(form){
    if(form.categoryType.value == 1 && form.parentID.value == ''){
        form.parentID.style.borderColor = "red";
        return false;
    }
    else if(form.categoryName.value.trim() == ''){
        form.categoryName.style.borderColor = "red";
        return false;
    }
    return true;
}
function validateAddItemForm(form){
    if(form.itemCategoryID.value == ''){
        form.itemCategoryID.style.borderColor = "red";
        return false;
    }
    else if(form.itemName.value.trim() == ''){
        form.itemName.style.borderColor = "red";
        return false;
    }
    return true;
}
function selectcategorydialog(){
    event.preventDefault();
    $("#selectcategorydialog").dialog({
        resizable: false,
        height: 200,
        width: 400,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function selectitemdialog(){
    event.preventDefault();
    $("#selectitemdialog").dialog({
        resizable: false,
        height: 200,
        width: 400,
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
</body>
<!-- Dialog box used in this page -->
<div id="selectcategorydialog" style="display:none;">
	<p><spring:message code='BzComposer.categorymanager.selectcategory'/></p>
</div>
<div id="selectitemdialog" style="display:none;">
	<p><spring:message code='BzComposer.categorymanager.selectitemrow'/></p>
</div>
<div id="showerrordialog" style="display:none;">
	<p><spring:message code='BzComposer.reconciliation.someerroroccurred'/></p>
</div>
<div id="deletedialogbox" style="display:none;">
	<p><spring:message code='BzComposer.categorymanager.wanttodelete'/></p>
</div>
