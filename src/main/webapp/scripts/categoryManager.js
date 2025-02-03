//Author: Sarfraz-Malik
var categoryId = -1;
var index = -1;
var categoryName = "";

$(document).ready(function () {
    let row1ID = $('.hideShowMyCatType')[0].cells[0].innerText;
    hideShowMyCat(row1ID);

    $("#AddNewCategoryDlgId").hide();
    $("#EditCategoryDlgId").hide();
    $("#addCategoryCombo").prop('disabled', true);
    $("#SubAccountOfCategoryForUpdate").prop('disabled', true);

    $('.hideShowMyCat').click(function () {
    debugger
         var selected = $(this).hasClass("highlight0");
         $(".hideShowMyCat").removeClass("highlight0");
         if(!selected)
             $(this).addClass("highlight0");
    });

     $( "#AddNewCategory").on("click", function(){
         event.preventDefault();
         $("#addCategoryCombo").val("");
         $( "#AddNewCategoryDlgId").dialog({
            height: 500,
            width: 600,
            modal: true,
            title: 'Add Category'
         });
     });
     $( "#EditCategory").on("click", function(){
        event.preventDefault();
        $("#SubAccountOfCategoryForUpdate").val("");
        if(categoryId == -1){
            alert(selectCategoryMsg);
            return false;
        }
        $( "#EditCategoryDlgId").dialog({
            height: 500,
            width: 600,
            modal: true,
            title: 'Edit Category'
        });
        loadCategoryForUpdate();
     });

});

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
function selectRow(catId, inv)
{
	debugger;
	this.categoryId = catId;
	this.index = inv;
	let selectedROW = $('table.devAcCategoryListTable tbody tr:nth-child('+index+')');
	$('select.CategoryTypeForUpdate').val(selectedROW.find('td:nth-child(2)').attr('value'));
	var parent = selectedROW.find('td:nth-child(8)').attr('value');
	if(parent == 'root'){
		$("#subAccountOfCheckForUpdate").prop("checked", false);
		$("#SubAccountOfCategoryForUpdate").prop('disabled', true);
	}
	else{
		$("#subAccountOfCheckForUpdate").prop("checked", true);
		$("#SubAccountOfCategoryForUpdate").prop('disabled', false);
	}
	categoryName = selectedROW.find('td:nth-child(3)').text().trim();
	$("#CategoryNameForUpdate").val(categoryName);
	$("select#BudgetCategoryForUpdate").val(selectedROW.find('td:nth-child(7)').attr('value'));
	$("#AccountNumberForUpdate").val(selectedROW.find('td:nth-child(4)').text());
	$("#DescriptionForUpdate").val(selectedROW.find('td:nth-child(6)').text());
	var d;
}

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
            alert(someErrorOccurredMsg);
        }
    });
}

 function deleteCategory()
 {
    event.preventDefault();
     if(categoryId == -1){
        alert(selectCategoryMsg);
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
                        if(isExists2 === true){
                            return alert(categoryUsedForOtherInvoice);
                        }
                        else{
                            debugger;
                            if(window.confirm(wantToDeleteMsg) == true){
                                $.ajax({
                                    type : "POST",
                                    url : "categoryManagerPost?tabid=DeleteCategory",
                                    data :"CategoryId=" + categoryId,
                                    success : function(data) {
                                        //updateCategoryManager(data);
                                        location.reload();
                                    }
                                });
                            }
                        }
                    },
                 });
            }
            else{
                alert(itemCantDeleteMsg);
                return false;
            }
        },
         error : function(data) {
            alert(someErrorOccurredMsg);
            return false
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
                return alert(categoryNameExistMsg);
                categoryManageWithoutUpdate();
            }
        },
         error : function(data) {
            alert(someErrorOccurredMsg);
            return false
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
            alert(someErrorOccurredMsg);
            return false
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
		         var selected = $(this).hasClass("highlight0");
		         $("tr").removeClass("highlight0");
		         if(!selected)
		             $(this).addClass("highlight0");
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


function closeAddDialog(){
    $('#AddNewCategoryDlgId').dialog('close');
}
function closeEditDialog(){
    $('#EditCategoryDlgId').dialog('close');
}
