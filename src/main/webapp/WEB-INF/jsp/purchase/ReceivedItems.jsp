<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="BzComposer.receiveditemtitle"/></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/sortable.js"></script>
<%@include file="../include/headlogo.jsp"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/menu.jsp"%>
<script>
    $(document).ready(function(){
        $("#myInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#ReceivedItem tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
<style type="text/css">
.dhtmlgoodies_answer{ height: 600px !important;}
.height250 { height:100%; }
.fht-tbody{
    height:85% !important; /*  change table height*/
    border-bottom: 1px solid rgb(207, 207, 207);
}
table.tabla-listados {
    width: 100%;
    border: 1px solid rgb(207, 207, 207);
    margin: 0px 0px 0px 0px;
}
table.tabla-listados tbody tr.odd td { background: #e1e5e9; }
table.tabla-listados thead tr th { font-size:14px; border: 1px solid #dddddd; }
table.tabla-listados tbody tr td { font-size:12px; border: 1px solid #dddddd; }
table.tabla-listados tbody tr:nth-child(even) { background-color: #dddddd; }
</style>
</head>
<body>
<!-- begin shared/header -->
<form action="PurchaseBoard?tabid=ShowReceivedItems" method="post">
	<div id="">
		<div id="ddcolortabsline">&nbsp;</div>
		<div id="cos">
			<div class="statusquo ok">
				<div id="hoja">
					<div id="blanquito">
						<div id="padding">
							<div>
								<div style="float:left;">
                                    <span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
                                        <spring:message code="BzComposer.receiveditem.receiveditemtitle" />
                                    </span>
									<table>
										<tr>
											<td><spring:message code="BzComposer.searchbuttontext" />:</td>
											<td><input type="text" id="myInput" /></td>
											<td colspan="4">&nbsp;</td>
										</tr>
									</table>
								</div>
								<div style="float:right;"><br/>
									<table style="font-size: 14px;">
										<tr>
											<td><a class="formbutton" onclick="callsave();" style="padding:5 20px;"><spring:message code='BzComposer.global.save'/></a></td>
											<!-- <td><a class="formbutton" onclick="callClearReceivedQty();" style="padding:5 20px;"><spring:message code='BzComposer.global.new'/></a></td> -->
											<!-- <td><a class="formbutton" onclick="callEdit();" style="padding:5 20px;"><spring:message code='BzComposer.global.edit'/></a></td> -->
											<!-- <td><a class="formbutton" onclick="callAdd();"><spring:message code='BzComposer.global.add'/></a></td> -->
											<!-- <td><a class="formbutton" onclick="callDelete();"><spring:message code='BzComposer.global.delete'/></a></td> -->
											<!-- <td><input type="submit" class="formbutton" name="findBtn" value="<spring:message code='BzComposer.receiveditem.refresh'/>"></td> -->
										</tr>
									</table>
								</div>
							</div>
							<div style="clear: both;">
								<div id="table-negotiations">
									<table cellspacing="0" align="center" style="width: 100%;">
										<tr align="left">
											<td>
												<c:if test="${IsUpdated}">
													<strong><span class="msgstyle">${IsUpdated}</span></strong>
												</c:if>
											</td>
										</tr>
									</table>
									<div>
										<table align="center">
											<tr>
												<td align="center">
													<c:if test="${not empty msg}">
														<font color="red"><b>${msg}</b></font>
													</c:if>
												</td>
											</tr>
										</table>
									</div>
									<div>
										<div class="grid_8 height250 tabla-listados" id="table-negotiations" >
											<table id="receivedItemList" class="tabla-listados sortable" cellpadding="0" cellspacing="0">
												<thead>
												<tr>
													<th><spring:message code="BzComposer.receiveditem.ponumber" /></th>
													<th><spring:message code="bca.itemcategory" /></th>
													<th><spring:message code="BzComposer.receiveditem.itemcode" /></th>
													<th style="padding-right:200px;"><spring:message code="BzComposer.receiveditem.itemname" /></th>
													<th><spring:message code="BzComposer.receiveditem.vendor" /></th>
													<th><spring:message code="BzComposer.Invoice.Date.DatePurchased" /></th>
													<th><spring:message code="BzComposer.purchaseorderboard.poreceived" /></th>
													<th><spring:message code="BzComposer.receiveditem.orderedquantity" /></th>
													<th><spring:message code="BzComposer.receiveditem.receivedquantity" /></th>
													<th><spring:message code="BzComposer.receiveditem.difference" /></th>
													<th><spring:message code="BzComposer.item.location" /></th>
													<th><spring:message code="BzComposer.ReorderPoint" /></th>
													<th><spring:message code="BzComposer.Invoice.Date.UpdatedDate" /></th>
													<th><spring:message code="BzComposer.global.memo" /></th>
													<!-- <th><spring:message code="BzComposer.receiveditem.cost" /></th> -->
												</tr>
												</thead>
												<tbody id="ReceivedItem">
												<c:if test="${PurchaseBoardDetails != null}">
													<c:if test="${not empty PurchaseBoardDetails}">
														<input type="hidden" id="SalesListSize" name="SalesListSize" value='${PurchaseBoardDetails.size()}'/>
														<input type="hidden" name="sListSize" id="lSize" value='${PurchaseBoardDetails.size()}'/>
														<c:forEach items="${PurchaseBoardDetails}" var="objList" varStatus="ndx">
															<tr id='${ndx.index}$$' onclick="callClick('${ndx.index}$$', ${objList.invoiceID}, ${objList.cartID});">
																<td hidden="invoiceID">${objList.invoiceID}</td>
																<td hidden="cartID">${objList.cartID}</td>
																<td><spring:message code="BzComposer.purchase.PONumPrefix" /> ${objList.po_no}</td>
																<td>${objList.categoryName}</td>
																<td>${objList.inventoryCode}</td>
																<td>${objList.itemName}</td>
																<td>${objList.lastName}, ${objList.firstName}</td>
																<td>${objList.dateAdded}</td>
																<td align="center">
                                                                    <input type="checkbox" ${objList.isReceived == '1'?"checked='true'":""} />
                                                                </td>
																<td align="center">${objList.orderQty}</td>
																<td align="center">
																    <input id="receivedQty${ndx.index}" value="${objList.receivedQty}" style="width:100px;"
																        onchange="addRowIndex(${ndx.index})" onkeypress="return numbersonly(event, this.value)" />
                                                                </td>
																<td align="center">${objList.orderQty - objList.receivedQty}</td>
																<td>${objList.location}</td>
																<td>${objList.reorderPoint}</td>
																<td>${objList.dateUpdated}</td>
																<td><input id="memo${ndx.index}" style="width:300px;" value="${objList.memo}" onchange="addRowIndex(${ndx.index})" /></td>
																<!-- <td align="center">${objList.total}</td> -->
															</tr>
														</c:forEach>
													</c:if>
												</c:if>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<table align="left">
									<tr align="center">
										<td>

											<!-- 				<input type="button" -->
											<!-- 				class="formbutton" disabled="disabled" id="modi" -->
											<!-- 				onclick="makeUpdateInList();" title="Apply" -->
											<!-- 				name="modifyBtn" -->
											<%-- 				value='<spring:message code="BzComposer.purchase.Apply" />'> --%>

											<input type="hidden" name="ONum" id="ONumId">
											<input type="hidden" name="sEmail" id="sEmailID">
											<input type="hidden" name="rNum" id="rowONum">
											<input type="hidden" name="senderEmail" id="EID">

										</td>
									</tr>
								</table>
								<input type="hidden" id="ordId" name="OrderValue" value="">
								<input type="hidden" id="statusId" name="StatusValue" value="">
								<input type="hidden" id="ordSize" name="Size" value="">
								<!-- end Contents -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<input type="hidden" id="ord_value" />
	<input type="hidden" id="po_value" />
</form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>

<script type="text/javascript">
var invoiceID;
let cartID=0;
let rowID = 0;
let selectedRowIndexs = [];

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
function callClick(rid, invId, cartID2) {
    debugger;
    let size = document.getElementById("lSize").value;
    let rowValue = rid.replace("$$", "");
    rowID = rowValue;
    invoiceID = invId;
    setInvId = invId;
    cartID = cartID2;
    /*for (i = 0; i < size; i++) {
        document.getElementById(i + "$$").classList.remove('draft');
        if (i % 2 == 0) {
            if (size != (i + 1)) {
                document.getElementById((i + 1) + "$$").classList.add('odd');
            }
        }
    }
    if (rowValue % 2 != 0) {
        document.getElementById(rid).classList.remove('odd');
    }
    document.getElementById(rid).classList.add('draft');
    */

    var selectedRow = document.getElementById(rid);
    if(selectedRow.classList.contains('draft')){
        //selectedRow.classList.remove('draft');
    }else{
        //selectedRow.classList.add('draft');
    }
}

function callClearReceivedQty(){
    if(cartID == undefined || cartID == 0){
        alert("<bean:message key='BzComposer.common.selectanyrecord'/>");
    }else{
        window.location="PurchaseBoard?tabid=clearReceivedQty&cartID=" + cartID;
    }
}
function callEdit(){
    if(invoiceID == undefined){
        alert("<bean:message key='BzComposer.common.selectanyrecord'/>");
    }else{
        window.open("PurchaseBoard?tabid=editReceivedItems&invoiceID=" + invoiceID, null, "scrollbars=yes,height=600,width=900,status=yes,toolbar=no,menubar=no,location=no");
    }
}
function callAdd(){
    window.location="Item?tabid=ShowAdd&ItemType=1&showHistoryPanel=1";
}
function callsave(){
    //let receivedQty = $("#receivedQty"+rowID).val();
    //let memo = $("#memo"+rowID).val();
    /*event.preventDefault();
    $("#updatePBDialog").dialog({
        resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                window.location.href="PurchaseBoard?tabid=updateReceivedItemDetails&invoiceID="+invoiceID+"&cartID="+cartID+"&receivedQty="+receivedQty+"&memo="+memo;
            },
            "<spring:message code='BzComposer.global.cancel'/>": function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
    */

    let ItemArr = [];
    //let selectedRows = $("#ReceivedItem tr.draft");
    for(let x=0; x<selectedRowIndexs.length; x++){
        let ItemCells = document.getElementById(selectedRowIndexs[x]+'$$').cells;
        ItemArr[x] = {
            "invoiceID" : ItemCells[0].innerText,
            "cartID" : ItemCells[1].innerText,
            "receivedQty" : ItemCells[10].getElementsByTagName("input")[0].value,
            "memo" : ItemCells[15].getElementsByTagName("input")[0].value
        };
        if(parseInt(ItemCells[10].getElementsByTagName("input")[0].value) < 0){
            alert("<bean:message key='BzComposer.common.enterValidQty'/>");
            return false;
        }
    }
    if(ItemArr.length>0){
        $("#updatePBDialog").dialog({
            resizable: false,
            height: 200,
            width: 500,
            modal: true,
            buttons: {
                "<spring:message code='BzComposer.global.ok'/>": function () {
                    $.ajax({
                        type : "POST",
                        url : "PurchaseBoard?tabid=updateReceivedItemDetails",
                        data: "ItemArr=" +JSON.stringify(ItemArr),
                        success : function(data) {
                            location.reload();
                        },
                         error : function(data) {
                            return showerrordialog();
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
}
function callDelete() {
    debugger;
    if (typeof setInvId === 'undefined') {
        return showItemValidationDialog();
    }
    if (setInvId == 0) {

        return showItemValidationDialog();
    } else {
        debugger;
        event.preventDefault();
        $("#deleteItemValidationDialog").dialog({
            resizable : false,
            height : 200,
            width : 400,
            modal : true,
            buttons : {
                "<spring:message code='BzComposer.global.ok'/>" : function() {
                    $(this).dialog("close");
                    window.location.href= "PurchaseBoard?tabid=DeleteReceivedItems&InvId="+setInvId;
                },
                "<spring:message code='BzComposer.global.cancel'/>" : function() {
                    $(this).dialog("close");
                    return false;
                }
            }
        });
        return false;
    }
}

function setIsEmail(){

    document.getElementById("isEmailChk").value=document.getElementById("EmailId").value;
    document.forms[0].action = "Email.do?tabid=EOSOLO";
    document.forms[0].submit();
}
function checkName(){
    if(document.RMAForm.fnameTxt.value=="" && document.RMAForm.lnameTxt.value=="")	{

        return checkduplicatenamedialog();
    }
    else{
        document.forms[0].action = "RMA.do?tabid=R0S0C0" ;
        document.forms[0].submit();
    }
}

function setRowId(rowId,email,rid){
    document.getElementById("po_value").value=rowId;

    //orderNo=document.getElementById("ord_value").value;
    size=document.getElementById("lSize").value;
    type=document.getElementById("sType").value;
    typeVal=document.getElementById("SearchVal").value
    for(i=0;i<size;i++){
        var row1=document.getElementById(i+"$$");
        row1.className = "";
    }
    var rd=document.getElementById(rid);
    rd.className = "draft";
    document.getElementById("smail").disabled=false;
    document.getElementById("sEmailID").value=email;
    document.getElementById("rowONum").value=rowId;
}

var ordarr = new Array();
var statusarr = new Array();
var cnt=0;
var ord="";
var status="";

function makeUpdate(orderno,obj){
    var flag=0;
    document.getElementById("modi").disabled=false;
    val=obj.checked;
    for(j=0;j<=cnt;j++){
        if(orderno==ordarr[j]){
            flag=1;
            statusarr[j]=val;
        }
    }
    if(flag==0){
        ordarr[cnt]=orderno;
        statusarr[cnt]=val;
        cnt++;
    }
}

function makeUpdateInList(){
    for(j=0;j<cnt;j++){
        ord+=ordarr[j]+";";
        status+=statusarr[j]+";";
    }
    document.getElementById('ordSize').value=cnt;
    document.getElementById('ordId').value=ord;
    document.getElementById("statusId").value=status;
    /* response = window.confirm("Do you want to update record?");
    if(response){
        document.getElementById("modi").disabled=false;
        document.forms[0].action = "CheckPO.do?tabid=UpdateCheckPO";
        document.forms[0].submit();
    } */
    return showUpdateListDialog();
}

function SaleSearch(){
    Myid="";
    count=0;
    size=document.getElementById("lSize").value;
    type=document.getElementById("sType").value;
    typeVal=document.getElementById("SearchVal").value;

    for(i=0;i<size;i++){
        var row1=document.getElementById(i+"$$");
        row1.className = "";
    }
    for(i=0;i<size;i++){
        var row1=document.getElementById(i+"$$");
        row1.className = "";
        var cells=row1.getElementsByTagName("td");
        var data1=cells[1].innerHTML;//value in first td

        var data2=cells[2].innerHTML;//value in second td
        var data3=cells[3].innerHTML;//value in third td
        var data4=cells[4].innerHTML;//value in forth td
        var data5=cells[5].innerHTML;//value in fifth td
        var data6=cells[6].innerHTML;//value in sixth td
        var data7=cells[7].innerHTML;//value in seventh td

        data1=data1.toLowerCase();
        data2=data2.toLowerCase();
        data3=data3.toLowerCase();
        data4=data4.toLowerCase();
        data5=data5.toLowerCase();
        data6=data6.toLowerCase();
        data7=data7.toLowerCase();

        typeVal=typeVal.toLowerCase();

        if(type=="1" && data3.match(typeVal))
        {row1.className = "draft"; break;}
        else if(type=="2" && data1.match(typeVal))
        {row1.className = "draft"; break;}
        else if(type=="3" && data2.match(typeVal))
        { row1.className = "draft"; break;}
        else if(type=="4" && data4.match(typeVal))
        { row1.className = "draft"; break;}
        else if(type=="5" && data6.match(typeVal))
        { row1.className = "draft"; break;}
        else if(type=="6" && data7.match(typeVal))
        { row1.className = "draft"; break;}
    }
}

function checkduplicatenamedialog(){
    debugger;
    event.preventDefault();
    $("#checkduplicatenamedialog").dialog({
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
function showUpdateListDialog(){
    debugger;
    event.preventDefault();
    $("#showUpdateListDialog").dialog({
        resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
                document.getElementById("modi").disabled=false;
                document.forms[0].action = "CheckPO.do?tabid=UpdateCheckPO";
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
function showItemValidationDialog() {
    event.preventDefault();
    $("#showItemValidationDialog").dialog({
        resizable : false,
        height : 200,
        width : 400,
        modal : true,
        buttons : {
            "<spring:message code='BzComposer.global.ok'/>" : function() {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
</script>
<!-- Dialog box used in received item page -->
<div id="checkduplicatenamedialog" style="display:none;">
	<p><spring:message code='BzComposer.checkpurchaseorder.enterfirstorlastname'/></p>
</div>
<div id="showUpdateListDialog" style="display:none;">
	<p><spring:message code="BzComposer.checkpurchaseorder.updaterecordmessage"/></p>
</div>
<div id="deleteItemValidationDialog" style="display: none;">
	<p><spring:message code="BizComposer.Item.Delete.Validation" /></p>
</div>
<div id="showItemValidationDialog" style="display: none;">
	<p><spring:message code="BizComposer.Item.Item.Validation" /></p>
</div>
<div id="updatePBDialog" style="display:none;">
    <p><spring:message code="BizComposer.ItemDetails.Update.Validation"/></p>
</div>