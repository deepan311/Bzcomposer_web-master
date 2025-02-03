<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title><spring:message code="BzComposer.categorymanagertitle"/></title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!--  Here Is the context path -->
 <script>var ctx = "${pageContext.request.contextPath}";</script>
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
.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-draggable.ui-resizable
	{
	min-width: 55%;
	min-height: 500px;
	height: auto;
	width: 300px;
	display: block;
	z-index: 101;
}
#highlight {
	
 		background-color: blue; 
 
	 }
.highlight { background-color: #00CED1 !important;color: #fff }	 
	
</style>

</head>
<body onload="init();">
<div class="mb-3 clear custom-fixed-tabs">
    <div id="ddcolortabsline">&nbsp;</div>
    <div style="float: left; padding-top:10px;">
    	<span style="font-size: 1.2em; font-weight: bold; color: #05A9C5; margin: 30px 0px 15px 20px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
    		<spring:message code="BzComposer.categorymanager.tab.categorylist"/>
    	</span>
    </div>
	<div class="tab-content" style="padding:20px;">
        <div class="table1 table2" id="devCategoryTable" style="width:100%;">
        <div class="float-left" style="width:15%;">
            <table class="table table-bordered table-sm" id="categoryTypeIDRow">
                <thead class="thead-light">
                    <tr>
                        <th style="text-align: center;"><spring:message code="BzComposer.categorymanager.tab.categorylist"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${categoryType}" var="curObject" varStatus="loop">
                        <tr class="hideShowMyCatType" onclick="hideShowMyCat(${curObject.categoryTypeID})">
                            <td hidden="categoryTypeID">${curObject.categoryTypeID}</td>
                            <td><strong>${curObject.categoryTypeName}</strong></td>
                        </tr>
                    </c:forEach>
                <tbody>
            </table>
        </div>
        <div style="width:80%;" class="float-right">
            <table class="table table-bordered table-sm devAcCategoryListTable" id="CatListDetailsID">
                <thead class="thead-light">
                    <tr>
                        <th><spring:message code="BzComposer.categorymanager.type"/></th>
                        <th><spring:message code="BzComposer.categorymanager.name"/></th>
                        <th><spring:message code="BzComposer.categorymanager.categorynumber"/></th>
                        <th><spring:message code="BzComposer.categorymanager.budgetgroup"/></th>
                        <th><spring:message code="BzComposer.categorymanager.active"/></th>
                        <th><spring:message code="BzComposer.categorymanager.description"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listOfCategory}" var="curObject" varStatus="loop">
                    <tr class="hideShowMyCat" onclick="selectrow(${curObject.id},${loop.index})">
                        <td hidden="categoryId">${curObject.id}</td>
                        <td hidden="categoryTypeID">${curObject.categoryTypeID}</td>
                        <td value="${curObject.categoryTypeName}">${curObject.categoryTypeName}</td>
                        <td style="color: black! important;">
                            <c:if test="${curObject.parent=='root'}">
                                <span class="font-weight-bold" style="color: black! important;">${curObject.name}</span>
                            </c:if>
                            <c:if test="${curObject.parent!='root'}">
                                <span style="color: black! important;"> ^ ${curObject.name}</span>
                            </c:if>
                        </td>
                        <td>${curObject.categoryNumber}</td>
                        <td>${curObject.budgetCategoryName}</td>
                        <td>${curObject.active}</td>
                        <td>${curObject.description}</td>
                        <td hidden="budgetCatId" value="${curObject.budgetCategoryID}">${curObject.budgetCategoryID}</td>
                        <td hidden="parentId" value="${curObject.parent}">${curObject.parent}</td>
                    </tr>
                    </c:forEach>
                    <tr><td colspan="6">&nbsp;</td></tr>
                </tbody>
            </table>
        </div>
        </div>
        <div class="text-center ">
            <div class="btngroup2-fixed">
                <button class="btn btn-info" style="font-size: 14px;" id="AddNewCategory">
                    <spring:message code="BzComposer.global.add"/>
                </button>
                <button class="btn btn-info" style="font-size: 14px;" id="EditCategory">
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
<div id="AddNewCategoryDlgId">
	<div class="form1">
	    <h6><spring:message code="BzComposer.categorymanager.accountinformation"/></h6>
	    <div class="form-horizontal">
	        <div class="form-group row" id="addNewCategoryCatTypeDivId">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.type"/>
                </label>
                <div class="col-md-4">
                    <select class="form-control" id="categoryTypeForAdd">
                        <c:forEach items="${categoryType}" var="curObject" varStatus="loop">
                            <option value="${curObject.categoryTypeID}">${curObject.categoryTypeName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
			<div class="form-group row">
				<label class="col-md-3 col-form-label">
					<spring:message code="BzComposer.categorymanager.name"/>
				</label>
				<div class="col-md-4">
					<input type="text" class="form-control" id="CategoryNameForAdd"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-md-3 col-form-label"></label>
				<div class="col-md-4 form-inline" id="subCategoryList">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" id="subAccountOfCheckBox">
						<label class="form-check-label" for="inlineCheckbox1">
							<spring:message code="BzComposer.categorymanager.subaccountof"/>
						</label>
					</div>
					<select class="form-control" id="addCategoryCombo">
					    <c:forEach items="${listOfCategory}" var="curObject" varStatus="loop">
                            <option value="${curObject.id}">${curObject.name}</option>
                        </c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-md-3 col-form-label">
					<spring:message code="BzComposer.categorymanager.accountnumber"/>
				</label>
				<div class="col-md-4">
					<input type="text" class="form-control" id="accountNumberForAdd">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-md-3 col-form-label">
					<spring:message code="BzComposer.categorymanager.budgetcategory"/>
				</label>
				<div class="col-md-4">
					<select class="form-control" id="budgetCategoryId">
					    <c:forEach items="${budgetCategoryList}" var="curObject" varStatus="loop">
                            <option value="${curObject.budgetCategoryID}">${curObject.budgetCategoryName}</option>
                        </c:forEach>
					</select>
				</div>
			</div>
            <div class="form-group row">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.description"/>
                </label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="descriptionForAdd">
                </div>
            </div>
		</div>
		<div class="text-center row pb-2">
			<div class="col-md-6" style="font-size: 14px;">
				<button type="button" class="btn btn-info" onclick="return saveNewCategory()" id="saveNewcategory">
					<spring:message code="BzComposer.global.save"/>
				</button>
				<button type="button" class="btn btn-info" onclick="closeAddDialog();">
					<spring:message code="BzComposer.global.close"/>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="EditCategoryDlgId">
	<div class="form1">
	    <h6><spring:message code="BzComposer.categorymanager.accountinformation"/></h6>
	    <div class="form-horizontal">
	        <div class="form-group row" id="EditCategoryCatTypeDivId">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.type"/>
                </label>
                <div class="col-md-4">
                    <select class="form-control" id="CategoryTypeForUpdate">
                        <c:forEach items="${categoryType}" var="curObject" varStatus="loop">
                            <option value="${curObject.categoryTypeID}">${curObject.categoryTypeName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
			<div class="form-group row">
				<label class="col-md-3 col-form-label">
					<spring:message code="BzComposer.categorymanager.name"/>
				</label>
				<div class="col-md-4">
					<input type="text" class="form-control" id="CategoryNameForUpdate"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-md-3 col-form-label"></label>
				<div class="col-md-4 form-inline" id="subCategoryList">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" id="subAccountOfCheckForUpdate">
						<label class="form-check-label" for="inlineCheckbox1">
							<spring:message code="BzComposer.categorymanager.subaccountof"/>
						</label>
					</div>
					<select class="form-control" id="SubAccountOfCategoryForUpdate">
					    <c:forEach items="${listOfCategory}" var="curObject" varStatus="loop">
                            <option value="${curObject.id}">${curObject.name}</option>
                        </c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-md-3 col-form-label">
					<spring:message code="BzComposer.categorymanager.accountnumber"/>
				</label>
				<div class="col-md-4">
					<input type="text" class="form-control" id="AccountNumberForUpdate">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-md-3 col-form-label">
					<spring:message code="BzComposer.categorymanager.budgetcategory"/>
				</label>
				<div class="col-md-4">
					<select class="form-control" id="BudgetCategoryForUpdate">
					    <c:forEach items="${budgetCategoryList}" var="curObject" varStatus="loop">
                            <option value="${curObject.budgetCategoryID}">${curObject.budgetCategoryName}</option>
                        </c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group row">
                <label class="col-md-3 col-form-label">
                    <spring:message code="BzComposer.categorymanager.description"/>
                </label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="DescriptionForUpdate">
                </div>
            </div>
		</div>
		<div class="text-center row pb-2">
			<div class="col-md-6" style="font-size: 14px;">
				<button type="button" class="btn btn-info" onclick="return updateCategory()">
					<spring:message code="BzComposer.global.update"/>
				</button>
				<button type="button" class="btn btn-info" onclick="closeEditDialog();">
					<spring:message code="BzComposer.global.close"/>
				</button>
			</div>
		</div>
	</div>
</div>
<script>
var categoryId = -1;
var index = -1;
var categoryName = "";

function closeAddDialog(){
    $('#AddNewCategoryDlgId').dialog('close');
}
function closeEditDialog(){
    $('#EditCategoryDlgId').dialog('close');
}
function init(){

    let row1ID = $('.hideShowMyCatType')[0].cells[0].innerText;
    hideShowMyCat(row1ID);
}
function selectrow(catId, inv)
{
	debugger;
	this.categoryId = catId;
	this.index = inv;
	let selectedROW = $('table.devAcCategoryListTable tbody tr:nth-child('+index+')');
	$('select.CategoryTypeForUpdate').val(selectedROW.find('td:nth-child(2)').attr('value'));
	var parent = selectedROW.find('td:nth-child(8)').attr('value');
	if(parent == 'root')
	{
		$("#subAccountOfCheckForUpdate").prop("checked", false);
		$("#SubAccountOfCategoryForUpdate").prop('disabled', true);
	}
	else
	{
		$("#subAccountOfCheckForUpdate").prop("checked", true);
		$("#SubAccountOfCategoryForUpdate").prop('disabled', false);
		/* $("#SubAccountOfCategoryForUpdate").text(parent); */
		/* var combo = document.getElementById("SubAccountOfCategoryForUpdate")
		combo.options[combo.selectedIndex].label = parent; */
	}
	categoryName = selectedROW.find('td:nth-child(3)').text();
	if(categoryName.startsWith(" "))
	{
		categoryName = categoryName.substr(4);
	}
	$("#CategoryNameForUpdate").val(categoryName);
	$("select#BudgetCategoryForUpdate").val(selectedROW.find('td:nth-child(7)').attr('value'));
	$("#AccountNumberForUpdate").val(selectedROW.find('td:nth-child(4)').text());
	$("#DescriptionForUpdate").val(selectedROW.find('td:nth-child(6)').text());
	var d;
}

$(document).ready(function () {
    debugger;
    $('tr').click(function () {
         var selected = $(this).hasClass("highlight");
         $("tr").removeClass("highlight");
         if(!selected)
             $(this).addClass("highlight");
    });

	$("#AddNewCategoryDlgId").hide();
	$("#EditCategoryDlgId").hide();
	$("#addCategoryCombo").prop('disabled', true);
	$("#SubAccountOfCategoryForUpdate").prop('disabled', true);

     $( "#AddNewCategory").on("click", function(){
        /*   $("#dateForAddAccount").val(dName+" "+((new Date().getMonth())+1)+"-"+new Date().getDate()+"-"+new Date().getFullYear()); */
         debugger;
         $("#addCategoryCombo").val("");
         $( "#AddNewCategoryDlgId").dialog({
            modal: true,
            title: 'Add Category'
         });
     });
     $( "#EditCategory").on("click", function(){
        debugger;
        $("#SubAccountOfCategoryForUpdate").val("");
        if(categoryId == -1){
            return selectcategorydialog();
            return false;
        }
        $( "#EditCategoryDlgId").dialog({
           modal: true,
           title: 'Edit Category'
        });
        loadCategoryForUpdate();
     });

});

function loadCategoryForUpdate(){
    $.ajax({
        type : "GET",
        url : "CategoryManagerAJAX?tabid=getCategoryDetails&catID="+categoryId,
        success : function(data) {
            document.getElementById("CategoryTypeForUpdate").value = data.categoryTypeID;
            document.getElementById("CategoryNameForUpdate").value = data.name;
            document.getElementById("AccountNumberForUpdate").value = data.categoryNumber;
            document.getElementById("BudgetCategoryForUpdate").value = data.budgetCategoryID;
            document.getElementById("DescriptionForUpdate").value = data.description;

            if(data.parent == 'root'){
                $("#SubAccountOfCategoryForUpdate").prop('disabled', true);
            }else{
                $("#SubAccountOfCategoryForUpdate").prop('disabled', false);
                document.getElementById("subAccountOfCheckForUpdate").checked = data.parent!='root'?true:false;
                document.getElementById("SubAccountOfCategoryForUpdate").value = data.parent;
            }

        },
         error : function(data) {
            return showerrordialog();
        }
    });
}

 function deleteCategory()
 {
     if(categoryId == -1){
        return selectcategorydialog();
        return false;
    }
	$.ajax({
        type : "GET",
        url : "CategoryManagerAJAX/Exists?tabid=CheckChildCategory",
        data :"CategoryId=" + categoryId,
        success : function(isExists) {
            debugger;
            if(isExists === false){
                 $.ajax({
                     type : "GET",
                        url : "CategoryManagerAJAX/Exists?tabid=CheckIsCategoryID",
                        data :"CategoryId=" + categoryId,
                        success : function(isExists2) {
                            if(isExists2 === true)
                            {
                                debugger;
                                return otherinvoicedialog();
                                event.preventDefault();
                                $("#deletedialogbox").dialog({
                                    resizable: false,
                                    height: 200,
                                    width: 500,
                                    modal: true,
                                    buttons: {
                                        "<spring:message code='BzComposer.global.ok'/>": function () {
                                            $(this).dialog("close");
                                            $.ajax({
                                                type : "POST",
                                                url : "categoryManagerPost?tabid=DeleteCategory",
                                                data :"CategoryId=" + categoryId,
                                                success : function(data) {
                                                    //updateCategoryManager(data);
                                                    location.reload();
                                                }
                                             });
                                        },
                                        "<spring:message code='BzComposer.global.cancel'/>": function () {
                                            $(this).dialog("close");
                                            return false;
                                        }
                                    }
                                });
                            }
                            else{
                                debugger;
                                event.preventDefault();
                                $("#deletedialogbox").dialog({
                                    resizable: false,
                                    height: 200,
                                    width: 500,
                                    modal: true,
                                    buttons: {
                                        "<spring:message code='BzComposer.global.ok'/>": function () {
                                            $(this).dialog("close");
                                            $.ajax({
                                                 type : "POST",
                                                url : "categoryManagerPost?tabid=DeleteCategory",
                                                data :"CategoryId=" + categoryId,
                                                success : function(data) {
                                                    //updateCategoryManager(data);
                                                    location.reload();
                                                }
                                             });
                                        },
                                        "<spring:message code='BzComposer.global.cancel'/>": function () {
                                            $(this).dialog("close");
                                            return false;
                                        }
                                    }
                                });
                            }
                        },
                 });
            }
            else{

                return itemcantdeletedialog();
                return false;
            }
        },
         error : function(data) {

            return showerrordialog()
        }
    });
   $(document.forms[0]).submit(function(event) {
        event.preventDefault();
   });
}
  
$(function() {
	$("#subAccountOfCheckBox").on("click", function(){
		if($("#subAccountOfCheckBox").prop("checked")){
			 $("#addCategoryCombo").prop('disabled', false);
		}else{
			$("#addCategoryCombo").prop('disabled', true);
		}
	});

	$("#subAccountOfCheckForUpdate").on("click", function(){
		if($("#subAccountOfCheckForUpdate").prop("checked")){
			 $("#SubAccountOfCategoryForUpdate").prop('disabled', false);
		}else{
			$("#SubAccountOfCategoryForUpdate").prop('disabled', true);
		}	 
	});

});

function saveNewCategory(){
    debugger;
    document.getElementById("CategoryNameForAdd").style = '';
    document.getElementById("accountNumberForAdd").style = '';
    document.getElementById("descriptionForAdd").style = '';

    var categoryTypeId = document.getElementById("categoryTypeForAdd").value;
    var categoryName = $("#CategoryNameForAdd").val();
    var subAccountOf = $("#subAccountOfCheckBox").prop("checked");
    var parent = document.getElementById("addCategoryCombo").value;
    var categoryNumber = $("#accountNumberForAdd").val();
    var description = $("#descriptionForAdd").val();
    var budgetCategoryId = document.getElementById("budgetCategoryId").value;
    if(categoryName.trim()==''){
        document.getElementById("CategoryNameForAdd").style.borderColor = "red";
        return;
    }
    if(categoryNumber.trim()==''){
        document.getElementById("accountNumberForAdd").style.borderColor = "red";
        return;
    }
    if(description.trim()==''){
        document.getElementById("descriptionForAdd").style.borderColor = "red";
        return;
    }
    TblCategory = {
         "categoryTypeID":categoryTypeId,
         "name":categoryName,
         "subAccountOf":subAccountOf,
         "parent":parent,
         "categoryNumber":categoryNumber,
         "description":description,
         "budgetCategoryID":budgetCategoryId
    };

    var obj = JSON.stringify(TblCategory);
    $.ajax({
        type : "POST",
        url : "categoryManagerPost?tabid=AddNewCategory",
        data :"data=" + obj,
        success : function(data) {
            $('#AddNewCategoryDlgId').dialog('close');
            if(data != ""){
                //updateCategoryManager(data);
                location.reload();
            }
            else{

                return categorynameexistdialog();
                categoryManageWithoutUpdate();
            }
        },
         error : function(data) {

            return showerrordialog();
        }
    });
    $(document.forms[0]).submit(function( event ) {
        event.preventDefault();
    });
}

  function updateCategory(){
     debugger;
     document.getElementById("CategoryNameForUpdate").style = '';
     document.getElementById("AccountNumberForUpdate").style = '';
     document.getElementById("DescriptionForUpdate").style = '';

     var categoryTypeId = $("#CategoryTypeForUpdate").val();
     var categoryName = $("#CategoryNameForUpdate").val();
     var subAccountOf = $("#subAccountOfCheckForUpdate").prop("checked");
     var parent = $("#SubAccountOfCategoryForUpdate").val();
     var categoryNumber = $("#AccountNumberForUpdate").val();
     var description = $("#DescriptionForUpdate").val();
     var budgetCategoryId = $("#BudgetCategoryForUpdate").val();
     if(categoryName.trim()==''){
         document.getElementById("CategoryNameForUpdate").style.borderColor = "red";
         return;
     }
     if(categoryNumber.trim()==''){
         document.getElementById("AccountNumberForUpdate").style.borderColor = "red";
         return;
     }
     if(description.trim()==''){
         document.getElementById("DescriptionForUpdate").style.borderColor = "red";
         return;
     }
     TblCategory = {
          "id":categoryId,
          "categoryTypeID":categoryTypeId,
          "name":categoryName,
          "subAccountOf":subAccountOf,
          "parent":parent,
          "categoryNumber":categoryNumber,
          "description":description,
          "budgetCategoryID":budgetCategoryId
     };

     var obj = JSON.stringify(TblCategory);
     $.ajax({
        type : "POST",
        url : "categoryManagerPost?tabid=UpdateCategory",
        data :"data=" + obj,
        success : function(data) {
            $('#EditCategoryDlgId').dialog('close');
            //updateCategoryManager(data);
            categoryId = -1;
            location.reload();
        },
         error : function(data) {
            return showerrordialog();
        }
    });
    $(document.forms[0]).submit(function( event ) {
        event.preventDefault();
    });
  }

  function addCss()
  {
	  $(document).ready(function () {
		    $('tr').click(function () {
		         var selected = $(this).hasClass("highlight"); 
		         $("tr").removeClass("highlight");
		         if(!selected)
		             $(this).addClass("highlight");
		    });
		});
  }
  function updateCategoryManager(data)
  {
	  debugger;
	  $(document).find('div#devCategoryTable table').replaceWith($(data).find('div#devCategoryTable').html());
	  $(document).find('div#subCategoryList select').replaceWith("<select class='form-control' id='addCategoryCombo'>"+$(data).find('div#subCategoryList select').html() + "</select>");
	  $('#AddNewCategoryDlgId').find('input:text').val('');
	  $('#EditCategoryDlgId').find('input:text').val('');
	  $("#subAccountOfCheckBox").prop("checked", false);
	  $("#subAccountOfCheckForUpdate").prop("checked", false);
	  $("#addCategoryCombo").prop('disabled', true);
	  $("#SubAccountOfCategoryForUpdate").prop('disabled', true);
      $("#categoryTypeForAdd").val($("#categoryTypeForAdd option:first").val());
      $("#CategoryTypeForUpdate").val($("#categoryTypeForAdd option:first").val());
      $("#budgetCategoryId").val($("#budgetCategoryId option:first").val());
      $("#BudgetCategoryForUpdate").val($("#budgetCategoryId option:first").val());
	  addCss();
  }
  function categoryManageWithoutUpdate()
  {
	  $('#AddNewCategoryDlgId').find('input:text').val('');
	  $('#EditCategoryDlgId').find('input:text').val('');
	  $("#subAccountOfCheckBox").prop("checked",false);
	  $("#addCategoryCombo").prop('disabled',true);
      $("#categoryTypeForAdd").val($("#categoryTypeForAdd option:first").val());
      $("#budgetCategoryId").val($("#budgetCategoryId option:first").val());
      addCss();
  }

  function selectcategorydialog()
  {
	  event.preventDefault();
		$("#selectcategorydialog").dialog({
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
  function itemcantdeletedialog()
  {
	  event.preventDefault();
		$("#itemcantdeletedialog").dialog({
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
  
  function categorynameexistdialog()
  {
	  event.preventDefault();
		$("#categorynameexistdialog").dialog({
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
  function otherinvoicedialog()
  {
	  event.preventDefault();
		$("#otherinvoicedialog").dialog({
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

    function hideShowMyCat(catTypeID){
        let catRows = $('.hideShowMyCat');
        for(let x=0; x<catRows.length; x++){
            let myRow = catRows[x];
            if(catTypeID == myRow.cells[1].innerText)
                myRow.style.display = "table-row";
            else
                myRow.style.display = "none";
        }
  }

</script>
</body>
<!-- Dialog box used in this page -->
<div id="selectcategorydialog" style="display:none;">
	<p><spring:message code='BzComposer.categorymanager.selectcategory'/></p>
</div>
<div id="showerrordialog" style="display:none;">
	<p><spring:message code='BzComposer.reconciliation.someerroroccurred'/></p>
</div>
<div id="itemcantdeletedialog" style="display:none;">
	<p><spring:message code='BzComposer.categorymanager.itemcantdelete'/></p>
</div>
<div id="categorynameexistdialog" style="display:none;">
	<p><spring:message code='BzComposer.categorymanager.categorynameexist'/></p>
</div>
<div id="deletedialogbox" style="display:none;">
	<p><spring:message code='BzComposer.categorymanager.wanttodelete'/></p>
</div>
<div id="otherinvoicedialog" style="display:none;">
	<p><spring:message code='BzComposer.categorymanager.categoryusedforotherinvoice'/></p>
</div>