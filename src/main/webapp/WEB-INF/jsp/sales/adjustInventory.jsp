<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="BzComposer.adjustinventorytitle" /></title>
<%@include file="../include/headlogo.jsp"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/menu.jsp"%>
<script>
$(document).ready(function(){
    $("#myInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#ItemDetails tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});
</script>
<style type="text/css">
.height250 { height:100%; }
.fht-tbody{
    height:85% !important; /*  change table height*/
    border-bottom: 1px solid rgb(207, 207, 207);
}
table.sortable thead { background-color: #eee; color: #666666; font-weight: bold; cursor: default; }
table.tabla-listados { width: 100%; border: 1px solid rgb(207, 207, 207); margin: 0px 0px 0px 0px; }
table.tabla-listados tbody tr.odd td { background: #e1e5e9; }
table.tabla-listados thead tr th { font-size: 14px; }
table.tabla-listados tbody tr td { font-size: 12px; border: 1px solid #dddddd; }
table.tabla-listados tbody tr:nth-child(even) { background-color: #dddddd; }
</style>
</head>
<body>
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<form action="Item" method="post" name="adjustInventoryForm" id="adjustInventoryForm">
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<!-- begin Contents -->
<div>
    <div style="float:left;">
	<span style="font-size:1.2em;font-weight:normal;color:#838383;margin:30px 0px 15px 0px;border-bottom:1px dotted #333;padding:0 0 .3em 0;">
		<spring:message code="BzComposer.adjustinventory.adjustinventorytitle"/>
	</span>
	<table>
		<tr>
			<td><spring:message code="BzComposer.searchbuttontext" />:</td>
			<td><input type="text" id="myInput"></td>
			<td colspan="4">&nbsp;</td>
		</tr>
	</table>
	</div>
	<div style="float:right;"><br/>
        <table style="font-size: 14px;">
            <tr>
                <!--<td><a class="formbutton" onclick="callEdit();" style="padding:5 20px;"><spring:message code='BzComposer.global.edit'/></a></td>-->
                <td><a class="formbutton" onclick="ApplyInventory();" style="padding:5 20px;"><spring:message code='BzComposer.global.save'/></a></td>
                <td><a class="formbutton" onclick="resetInventoryPage();" style="padding:5 20px;"><spring:message code='BzComposer.global.new'/></a></td>
                <td><a class="formbutton" onclick="printInventoryList('SavingInventoryLists');" style="padding:5 20px;" id="InventoryPrintingBtn"><spring:message code='BzComposer.global.SavingInventoryLists'/></a>
                    <a class="formbutton" onclick="printInventoryList('SavingCheckLists');" style="padding:5 20px;display:none;" id="InvPrintingCheckListBtn"><spring:message code='BzComposer.global.SavingCheckLists'/></a>
                </td>
            </tr>
        </table>
    </div>
</div>
<br/>
<div style="clear: both;">
	<div>
		<div class="grid_8 height250 tabla-listados" id="table-negotiations" >
      		<table id="adjustInventory" class="tabla-listados sortable" cellpadding="0" cellspacing="0">
				<thead style="font-weight: bold;">
					<tr>
						<th class="emblem"><spring:message code="bca.itemcategory"/></th>
						<th class="emblem"><spring:message code="Bizcomposer.itemCode"/></th>
						<th class="emblem" style="padding-right:200px;"><spring:message code="Bizcomposer.itemName"/></th>
                        <th class="emblem"><spring:message code="BzComposer.item.location" /></th>
                        <th class="emblem"><spring:message code="BzComposer.ReorderPoint" /></th>
                        <th class="emblem"><spring:message code="BzComposer.Item.ExpectedQty" /></th>
                        <th class="emblem"><spring:message code="BzComposer.Item.CountedQty" /></th>
						<th class="emblem"><spring:message code="BzComposer.receiveditem.difference" /></th>
						<th class="emblem"><spring:message code="BzComposer.Item.checkedDate" /></th>
                        <th class="emblem"><spring:message code="BzComposer.global.memo" /></th>
					</tr>
				</thead>
				<tbody id="ItemDetails">
					<c:if test="${ItemDetails != null}">
						<c:if test="${not empty ItemDetails}">
							<input type="hidden" name="ItemDetailsSize" id="ItemDetailsSize" value='${ItemDetails.size()}'/>
							<input type="hidden" name="listSize" id="lSize" value='${ItemDetails.size()}'/>
							<c:forEach items="${ItemDetails}" var="objList1" varStatus="loop">
                                <tr id="${loop.index}$$" onclick="callClick('${loop.index}$$', ${objList1.inventoryId});">
                                    <td hidden="id">${objList1.inventoryId}</td>
                                    <td>${objList1.categoryName}</td>
                                    <td>${objList1.putcharacter} ${objList1.itemCode}</td>
                                <c:if test="${objList1.itemType == '1'}">
                                    <td>${objList1.itemName}</td>
                                    <td>${objList1.location}</td>
                                    <td>${objList1.reorderPoint}</td>
                                    <td><input id='${loop.index}expectedQty' value="${objList1.expectedQty}" onchange="addRowIndex(${loop.index})" onkeypress="return numbersonly(event, this.value)" style="width:100px;" /></td>
                                    <td><input id='${loop.index}countedQty' value="${objList1.qty}" onchange="addRowIndex(${loop.index})" onkeypress="return numbersonly(event, this.value)" style="width:100px;" /></td>
                                    <td align="center">${objList1.qty - objList1.expectedQty}</td>
                                    <td>${objList1.dateReceived}</td>
                                    <td><input id="${loop.index}memo" style="width:300px;" value="${objList1.memo}" onchange="addRowIndex(${loop.index})" /></td>
                                </c:if>

                                <c:if test="${objList1.itemType != '2'}">
                                    <!-- Subtotal -->
                                    <c:if test="${objList1.itemType == '3'}">
                                        <td>${objList1.itemName}</td>
                                        <td>${objList1.qty}</td>
                                        <td>
                                            <input id='qty${loop.index}' name='newQty${objList1.inventoryId}' onkeypress="return numbersonly(event, this.value)" disabled="disabled" />
                                        </td>
                                    </c:if>

                                    <c:if test="${objList1.itemType != '3'}">
                                        <!-- Services -->
                                        <c:if test="${objList1.itemType == '4'}">
                                            <c:if test="${objList1.iscategory == 'true'}">
                                                <td>${objList1.itemName}</td>
                                                <td>${objList1.qty}</td>
                                                <td style="font-size: 1em;">
                                                    <input id='qty${loop.index}' name='newQty${objList1.inventoryId}' onkeypress="return numbersonly(event, this.value)" disabled="disabled" />
                                                </td>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${objList1.itemType == '4'}">
                                            <c:if test="${objList1.iscategory == 'false'}">
                                                <td>${objList1.itemName}</td>
                                                <td>${objList1.qty}</td>
                                                <td>
                                                    <input id='qty${loop.index}' name='newQty${objList1.inventoryId}' onkeypress="return numbersonly(event, this.value)" />
                                                </td>
                                            </c:if>
                                        </c:if>
									</c:if>
									</c:if>
									</tr>
								</c:forEach>
							</c:if>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- end Contents -->
	<! -- Write here for bottom/follter -->
	</div>
	</div>
	</div>
	</div>
	</div>
	<input type="hidden" id="inventoryId" />
	<input type="hidden" id="selectedRowIndex" />
</form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>
<script>
	const getCellValue = (tr, idx) => tr.children[idx].innerText || tr.children[idx].textContent;

	const comparer = (idx, asc) => (a, b) => ((v1, v2) =>
					v1 !== '' && v2 !== '' && !isNaN(v1) && !isNaN(v2) ? v1 - v2 : v1.toString().localeCompare(v2)
	)(getCellValue(asc ? a : b, idx), getCellValue(asc ? b : a, idx));

	document.querySelectorAll('.sortcolumn').forEach(th => th.addEventListener('click', (() => {
		const table = th.closest('table');
		Array.from(table.querySelectorAll('tr:nth-child(n+2)'))
				.sort(comparer(Array.from(th.parentNode.children).indexOf(th), this.asc = !this.asc))
				.forEach(tr => table.appendChild(tr));
	})));

let selectedRowIndexs = [];
let isInventoryPageReset = false;

function SetWriteAble(id){

// document.getElementById(id).disabled =false;
}

function numbersonly(e, val){
    var unicode = e.charCode? e.charCode : e.keyCode;
    if (unicode != 8){
        if (unicode < 48||unicode > 57) //if not a number
            return false; //disable key press
    }
}

function addRowIndex(rowIndex){
    let isFound = false;
    for(let x=0; x<selectedRowIndexs.length; x++){
        if(selectedRowIndexs[x] == rowIndex){
            isFound = true;
            break;
        }
    }
    if(!isFound){
        selectedRowIndexs.push(rowIndex);
    }
}

//callClick("0$$");
function callClick(rid, inventoryId){
	let size = document.getElementById("lSize").value;
	let rowValue= rid.replace("$$","");
 	for(i=0;i<size;i++){
		document.getElementById(i+"$$").classList.remove('draft');
		if(i%2==0){
			if(size !=(i+1)){
				document.getElementById((i+1)+"$$").classList.add('odd');
			}
		}
	}
	if(rowValue%2 !=0){ document.getElementById(rowValue+"$$").classList.remove('odd'); }
	var rd = document.getElementById(rid).classList.add('draft');
	document.getElementById('inventoryId').value = inventoryId;
	document.getElementById('selectedRowIndex').value = rowValue;

	var selectedRow = document.getElementById(rid);
	if(selectedRow.classList.contains('draft')){
	    //selectedRow.classList.remove('draft');
	}else{
        //selectedRow.classList.add('draft');
	}
}

function callEdit(){
    let InvId = document.getElementById('inventoryId').value;
    if(InvId == undefined || InvId == ""){
        alert('<spring:message code="BzComposer.categorymanager.selectitemrow" />');
    }else{
        window.open("Item?tabid=editInventory&InvId=" + InvId, null, "scrollbars=yes,height=600,width=900,status=yes,toolbar=no,menubar=no,location=no");
    }
}
function ApplyInventory(){
    /*let InvId = document.getElementById('inventoryId').value;
    if(InvId == undefined || InvId == ""){
        alert('<spring:message code="BzComposer.categorymanager.selectitemrow" />');
        return false;
    }
    let rowIndex = document.getElementById('selectedRowIndex').value;
    let expectedQty = document.getElementById(rowIndex +'expectedQty').value.trim();
    let memo = document.getElementById(rowIndex +'memo').value.trim();
    if(expectedQty == "" || expectedQty<0){
        alert('<spring:message code="BzComposer.billcreation.entervalidnumber" />');
        document.getElementById(rowIndex +'expectedQty').style.borderColor = "red";
        return false;
    }
	var resp = window.confirm('<spring:message code="BzComposer.AdjustInventory.Confirm.Validation" />')
	if(resp){
	    window.location = "Item?tabid=UpdateInventory&InvId="+InvId+"&expectedQty="+expectedQty+"&memo="+memo;
	}*/

    debugger;
    let InventoryArr = [];
    //let selectedRows = $("#ItemDetails tr.draft");
    for(let x=0; x<selectedRowIndexs.length; x++){
        let ItemCells = document.getElementById(selectedRowIndexs[x]+'$$').cells;
        let invItem = {
            "inventoryId" : ItemCells[0].innerText,
            "expectedQty" : ItemCells[4].getElementsByTagName("input")[0].value,
            "countedQty" : ItemCells[5].getElementsByTagName("input")[0].value,
            "memo" : ItemCells[8].getElementsByTagName("input")[0].value
        };
        InventoryArr[x] = invItem;
    }
    if(InventoryArr.length>0){
        $("#updatePBDialog").dialog({
            resizable: false,
            height: 200,
            width: 500,
            modal: true,
            buttons: {
                "<spring:message code='BzComposer.global.ok'/>": function () {
                    $(this).dialog("close");
                    $("#DownloadInvReportDialog").dialog({
                        resizable: false,
                        height: 200,
                        width: 500,
                        modal: true,
                        buttons: {
                            "<spring:message code='BzComposer.global.yes'/>": function () {
                                $(this).dialog("close");
                                createAdjustInventoryReport(true, 'SaveInventory');
                                setTimeout(function (){
                                    ApplyInventoryCall(true, InventoryArr);
                                }, 1500);
                            },
                            "<spring:message code='BzComposer.global.no'/>": function () {
                                $(this).dialog("close");
                                ApplyInventoryCall(false, InventoryArr);
                            }
                        }
                    });
                },
                "<spring:message code='BzComposer.global.cancel'/>": function () {
                    $(this).dialog("close");
                    return false;
                }
            }
        });
    }else{

        window.location = "Item?tabid=AdjustInventory";
    }
}
function ApplyInventoryCall(reportFlag, InventoryArr){
    $.ajax({
        type : "POST",
        url : "Item?tabid=UpdateInventory",
        data: "InventoryArr=" +JSON.stringify(InventoryArr),
        success : function(data) {
            location.reload();
        },
         error : function(data) {
            return showerrordialog();
        }
    });
}
function resetInventoryPage(){
    //window.location = "Item?tabid=AdjustInventory";
    let size = document.getElementById("lSize").value;
    for(i=0;i<size;i++){
        document.getElementById(i+"countedQty").value = "";
        document.getElementById(i+"memo").value = "";

        let ItemCells = document.getElementById(i+'$$').cells;
        ItemCells[6].innerText = '';
        ItemCells[7].innerText = '';
    }
    isInventoryPageReset = true;
    selectedRowIndexs = [];
    for(let x=0; x<size; x++){
        selectedRowIndexs.push(x);
    }
    document.getElementById('InventoryPrintingBtn').style.display = "none";
    document.getElementById('InvPrintingCheckListBtn').style.display = "";
}

function printInventoryList(reportName){
    /*
    var style = "<style>";
    style = style + "table {width: 100%;font: 16px Calibri;}";
    style = style + "table, th, td {border: solid 1px #DDD; border-collapse: collapse;padding: 2px 3px;text-align: center;}";
    style = style + "</style>";
    // CREATE A WINDOW OBJECT.
    var win = window.open('', '', 'height=500,width=700');
    win.document.write('<html><head>');
    win.document.write('<title>Inventory List</title>');
    win.document.write(style);
    win.document.write('</head>');
    win.document.write('<body>');
    win.document.write(document.getElementById("table-negotiations").innerHTML);
    win.document.write('</body></html>');
    win.document.close();
    win.print();
    */
    createAdjustInventoryReport(false, reportName);
}
function createAdjustInventoryReport(isWholeData, reportName){
    window.location = "/downloadAdjustInventoryReport?isWholeData="+isWholeData+"&reportName="+reportName;
}

function showerrordialog(){
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
</script>
<!-- Dialog box used in sales order page -->
<div id="applyInventoryDialog" style="display:none;">
	<p><spring:message code="BzComposer.adjustinventory.confirmvalidation" /></p>
</div>
<div id="showerrordialog" style="display:none;">
	<p><spring:message code='BzComposer.reconciliation.someerroroccurred'/></p>
</div>
<div id="updatePBDialog" style="display:none;">
    <p><spring:message code="BizComposer.ItemDetails.Update.Validation"/></p>
</div>
<div id="DownloadInvReportDialog" style="display:none;">
    <p><spring:message code="BizComposer.ItemDetails.DownloadAdjustInvReport"/></p>
</div>