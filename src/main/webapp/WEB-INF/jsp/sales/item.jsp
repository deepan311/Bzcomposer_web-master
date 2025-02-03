<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%@include file="/WEB-INF/jsp/include/headlogo.jsp" %>
    <%@include file="/WEB-INF/jsp/include/header.jsp" %>
    <%@include file="/WEB-INF/jsp/include/menu.jsp" %>
    <title><spring:message code="BzComposer.itemtitle"/></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.ckeditor.com/4.12.1/full/ckeditor.js"></script>
    <script>
        $(document).ready(function () {
            $("#myInput").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#ItemDetails tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>
    <script>
        $(function () {
            $('#sortBy').change(function () {
                debugger;
                var sortBy = $(this).val();
                $.ajax({
                    type: "POST",
                    url: "ItemAjax?tabid=sortItem&SortBy=" + sortBy,
                    data: {sortBy: sortBy},
                }).done(function (data) {
                    $('#ItemDetails').html('');
                    var custDetails = "";
                    for (var i = 0; i < data.length; i++) {
                        debugger;
                        var objList = data[i];
                        custDetails = custDetails +
                            "<input type='hidden' name='listSize' id='lSize' value='" + data.length + "'>"
                            + "<tr id='" + i + "$$' onclick='setRowId(" + objList.inventoryId + "," + i + ");'>"
                            + "<td colspan='2' style='font-size:12px;'><b>(" + objList.itemCode + ")</b>" + objList.itemName + "</td></tr>";

                    }
                    $('#ItemDetails').html(custDetails);
                });
            });
        });
    </script>
    <script type="text/javascript">
        $(function(){
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
        })
        ;
    </script>
    <script type="text/javascript">
        function getData()
        {
            var data = CKEDITOR.instances.editor1.getData();
            $("#textAreaContent").val(data);
            document.getElementById("Editor").style.display = "none";
            document.getElementById("tAreaContent").style.display = "block";
        }
        function getEditor()
        {
            var data = document.getElementById("textAreaContent").value;
            CKEDITOR.instances['editor1'].setData(data);
            document.getElementById("Editor").style.display = "block";
            document.getElementById("tAreaContent").style.display = "none";
        }

        flag_state = 0;
        var funsequence = 0;
        var _1 = navigator.userAgent.toLowerCase();
        var ___ = (_1.indexOf("msie") != -1);
        var ___5 = (_1.indexOf("msie 5") != -1);
        var _io = (_1.indexOf("opera") != -1);
        var _im = (_1.indexOf("mac") != -1);
        var ____gi = (_1.indexOf("gecko") != -1);
        var i____s = (_1.indexOf("safari") != -1);
        var o = null;
        var o22 = null;
        var o33 = null;
        var oT = null;
        var r = null;

        function c(r) {
            if (___) {
                var t = (___5) ? "Microsoft.XMLHTTP" : "Msxml2.XMLHTTP";
                try {
                    o = new ActiveXObject(t);
                    o.onreadystatechange = r;
                } catch (ex) {
                    alert("<bean:message key='BzComposer.common.needToEnableActiveXObject'/> ts.." + ex);
                }
            } else {
                o = new XMLHttpRequest();
                o.onload = r;
                o.onerror = r;
            }
            return o;
        }

        function oGET(oo, url) {
            try {
                oo.open("GET", url, true);
                oo.send(null);
            } catch (ex) {
            }
        }


    </script>
    <script>

        function showCustomerValidationDialog() {
            event.preventDefault();
            $("#showCustomerValidationDialog").dialog({
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
            font-size: 12px;
            text-align: left;
            padding: 5px 10px;
            /* 	background: rgba(5, 169, 197, 0.11); */
            border-bottom: 1px solid rgba(5, 169, 197, 0.2);
            /* 	color: #333; */
            text-shadow: #999 0px 1px 1px;
            white-space: nowrap;
        }

        table.tabla-listados tbody tr td {
            font-size: 12px;
            padding: 5px 0px 5px 12px;
            /* 	border-bottom: 1px solid rgb(207, 207, 207); */
            background: #fff;
            vertical-align: top;
        }
    </style>
</head>
<body onload="initialize();">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<form:form name="CustomerForm" enctype="MULTIPART/FORM-DATA" method="post" modelAttribute="customerDto">
    <div id="cos">
        <div class="statusquo ok">
            <div id="hoja">
                <div id="blanquito">
                    <div id="padding">
                        <!-- begin Contents -->
                        <div>
                            <div style="float: left;">
	<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.item.iteminfo"/>
		<c:if test="${not empty actionMsg}">
		    <br/> ${actionMsg}
		</c:if>
	</span>
                                <table>
                                    <tr>
                                        <td>
                                            <spring:message code="BzComposer.customer.sortby"/>
                                        </td>
                                        <td>
                                            <select id="sortBy">
                                                <option value="1"><spring:message code="BzComposer.Item.ItemName"/></option>
                                                <option value="2"><spring:message code="BzComposer.Item.ItemCode"/></option>
                                            </select>
                                        </td>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
                                </table>
                                <table>
                                    <tr>
                                        <td>
                                            <spring:message code="BzComposer.searchbuttontext"/>:
                                        </td>
                                        <td>
                                            <input type="text" id="myInput">
                                        </td>
                                        <td colspan="4">&nbsp;</td>
                                    </tr>
                                </table>
                            </div>
                            <div style="float: right;">
                                <table style="font-size: 14px;">
                                    <tr align="right">
                                        <td colspan="6">
                                            <div>
                                                <input type="button" class="formbutton" onclick="addNewItem();" style="padding:7 15px;" value="<spring:message code='BzComposer.global.new'/>" />
                                                <input type="button" class="formbutton" onclick="manageItem('EDIT');" style="padding:7 15px;" value="<spring:message code='BzComposer.global.edit'/>" />
                                                <input type="button" class="formbutton" onclick="manageItem('DELETE');" style="padding:7 15px;" value="<spring:message code='BzComposer.global.delete'/>" />
                                            </div>
                                            <div>
                                                <form:hidden path="clientVendorID"/>
                                                <form:hidden path="selectedRowID"/>
                                                <input type="hidden" name="cvId" id="vendrId" value="0"/>
                                                <input type="hidden" name="SelectedRID" id="setRID" value="">
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <table style="width: 100%; apdding: 0;">
                            <tr>
                                <td>
                                    <table style="padding: 0;width: 100%; margin-top: 10px;" align="center">
                                        <tr>
                                            <td valign="top" colspan="1"
                                                style="width: 300px; padding: 0; height: 75vh; border: 1px solid #ccc;">
                                                <table class="tabla-listados" cellspacing="0" style=" border: 0; padding: 0;margin: 0; height: auto;">
                                                    <thead>
                                                    <tr valign="top">
                                                        <th class="emblem">
                                                            <div align="center"><spring:message code="BzComposer.additem.item"/></div>
                                                        </th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td >
                                                            <div id="custDiv" style="overflow: auto; height:auto;">
                                                                <div id="custDiv">
                                                                    <table id="custTable">
                                                                        <tbody id="ItemDetails">
                                                                        <c:if test="${not empty ItemDetails}">
                                                                            <input type="hidden" name="listSize" id="lSize" value='${ItemDetails.size()}'>
                                                                            <c:forEach items="${ItemDetails}" var="objList" varStatus="loop">
                                                                                <tr id='${loop.index}$$' onclick="setRowId(${objList.inventoryId}, ${loop.index});">
                                                                                    <td style="font-size:12px;">
                                                                                        <b>(${objList.itemCode})</b> <br/>${objList.itemName}
                                                                                    </td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                        </c:if>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </td>            <!-- Added on 26-04-2019 -->
                                                    </tr>                <!-- Added on 26-04-2019 -->
                                                    </tbody>
                                                </table>
                                            </td>
                                            <!-- Added on 26-04-2019 -->

                                            <td colspan="10" style="vertical-align: 0;">
                                                <div id="table-negotiations" style="height:auto;">
                                                        <%--													productInfo--%>

                                                    <table cellspacing="0" class="tabla-listados"
                                                           style="margin-top: 0; margin-left: 20px;">
                                                        <thead>
                                                        <tr>
                                                            <th colspan="12" style="font-size:12px;">
                                                                <spring:message code="BzComposer.Item.ItemInfo"/>
                                                                <input type="hidden" name="inventoryId" id="inventoryId11" value="${itemDto.inventoryId}"/>
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.itemcodecategory"/>:
                                                            </td>
                                                            <td>${itemDto.itemCode}</td>
                                                            <td colspan="10">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td></td>
                                                            <td>
                                                                <input type="checkbox" disabled="disabled" id="subItem" name="subItem" value="subItem">
                                                                <spring:message code="BzComposer.additem.categoryOf"/>:
                                                            </td>
                                                            <td>
                                                                <select path="tectcmd" disabled="false" id="drop_cat">
                                                                    <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                                                                    <c:forEach items="${itemCategory}" var="obj">
                                                                        <option value="${obj.value}" ${itemDto.tectcmd==obj.value?'selected':''}>${obj.label}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </td>
                                                            <td>
                                                                <input type="checkbox" disabled="disabled" checked="true" />
                                                                <spring:message code="BzComposer.receiveditem.itemreceived"/>
                                                            </td>
                                                            <td colspan="8">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.itemtitle"/>:
                                                            </td>
                                                            <td colspan="2">
                                                                    ${itemDto.itemName}
                                                            </td>
                                                            <td colspan="9">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" disabled="disabled"  id="itemTaxable" ${itemDto.itemTaxable==true?'checked':''} />
                                                                <spring:message code="BzComposer.additem.taxableitem"/>
                                                            </td>
                                                            <td>
                                                                <input type="checkbox" disabled="disabled" id="dropShipping" ${itemDto.dropShipping==true?'checked':''} />
                                                                <spring:message code="BzComposer.additem.dropshipping"/>
                                                            </td>
                                                            <td>
                                                                <input type="checkbox" disabled="disabled" id="discounted" ${itemDto.discounted==true?'checked':''} />
                                                                <spring:message code="BzComposer.additem.discounted"/>
                                                            </td>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.productAccountCategory"/>:
                                                            </td>
                                                            <td>
                                                                <select disabled="true"  path="accountId">
                                                                    <option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
                                                                    <c:forEach items="${AccountList}" var="obj">
                                                                        <option value="${obj.value}" ${itemDto.accountId==obj.value?'selected':''}>${obj.label}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </td>
                                                            <td>
                                                                <input type="checkbox" disabled="disabled" id="consignedItem" ${itemDto.consignedItem==true?'checked':''} />
                                                                <spring:message code="BzComposer.additem.iscosigneditem"/>
                                                            </td>
                                                            <td colspan="6">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.serialnum"/>:
                                                            </td>
                                                            <td>${itemDto.serialNum}</td>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.productsku"/>:
                                                            </td>
                                                            <td>${itemDto.productSKU}</td>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.orderunit"/>:
                                                            </td>
                                                            <td>${itemDto.orderUnit}</td>
                                                            <td colspan="6">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.qtyonhand"/>:
                                                            </td>
                                                            <td>${itemDto.qty}</td>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.reorderpoint"/>:
                                                            </td>
                                                            <td>${itemDto.reorderPoint}</td>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.location"/>:
                                                            </td>
                                                            <td>
                                                                <select disabled="true" path="locationId" id="location">
                                                                    <c:forEach items="${locationList}" var="obj">
                                                                        <option value="${obj.value}" ${itemDto.locationId==obj.value?'selected':''}>${obj.label}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </td>
                                                            <td colspan="6">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.publishedweight"/>
                                                            </td>
                                                            <td>${itemDto.weight}</td>
                                                            <td>
                                                                <select path="weightUnit" disabled="true" id="drop_cat1">
                                                                    <c:forEach items="${weightList}" var="obj">
                                                                        <option value="${obj.value}" ${itemDto.weightUnit==obj.value?'selected':''}>${obj.label}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </td>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.actualweight"/>:
                                                            </td>
                                                            <td>${itemDto.actualWeight}</td>
                                                            <td colspan="7">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.purchaseprice"/>
                                                            </td>
                                                            <td>${itemDto.purchasePrice}</td>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.saleprice"/>:
                                                            </td>
                                                            <td>${itemDto.salePrice}</td>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.dealerprice"/>
                                                            </td>
                                                            <td>${itemDto.dealerPrice}</td>
                                                            <td colspan="6">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <spring:message code="Bizcomposer.additem.type"/>
                                                            </td>
                                                            <td colspan="2">
                                                                <select path="measurementId" id="measurementList">
                                                                    <option value=""></option>
                                                                    <c:forEach items="${measurementList}" var="obj">
                                                                        <option value="${obj.value}" ${itemDto.measurementId==obj.value?'selected':''}>${obj.label}</option>
                                                                    </c:forEach>
                                                                </select>
                                                                &nbsp;&nbsp;&nbsp;
                                                                <select path="submeasurementId" id="unitMeasurement">
                                                                    <option value=""></option>
                                                                    <c:forEach items="${unitMeasurementList}" var="obj">
                                                                        <option value="${obj.value}" ${itemDto.submeasurementId==obj.value?'selected':''}>${obj.label}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </td>
                                                            <td colspan="9">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3">
                                                                <div id="displayHWL" style="display: none;">
                                                                    <table>
                                                                        <tr>
                                                                            <td>
                                                                                <spring:message code="BzComposer.additem.height"/>
                                                                            </td>
                                                                            <td>
                                                                                <input type="text" id="height" size="4">
                                                                            </td>
                                                                            <td>
                                                                                <spring:message code="BzComposer.additem.width"/>
                                                                            </td>
                                                                            <td>
                                                                                <input type="text" id="width" size="4">
                                                                            </td>
                                                                            <td>
                                                                                <spring:message code="BzComposer.additem.length"/>
                                                                            </td>
                                                                            <td>
                                                                                <input type="text" id="length" size="4">
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </div>
                                                            </td>
                                                            <td colspan="9">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.itemsupplier"/>
                                                            </td>
                                                            <td colspan="2" align="left">
                                                                <select path="vendorId" id="clientVendor" disabled="true">
                                                                        <%-- <option value="AddNew"><spring:message code="BzComposer.addNew"/></option> --%>
                                                                    <option value="10101010" id="defaultCvId">
                                                                        &nbsp;
                                                                    </option>
                                                                    <option value="0">
                                                                        <spring:message code="BzComposer.additem.addnew" />
                                                                    </option>
                                                                    <c:forEach items="${vendorNameList}" var="obj">
                                                                        <option value="${obj.value}">${obj.label}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </td>
                                                            <td></td>
                                                            <td colspan="8">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.supplierssku"/>
                                                            </td>
                                                            <td>${itemDto.supplierSKU}</td>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.supplierforthissku"/>
                                                            </td>
                                                            <td>
                                                                <input type="checkbox" id="primarySupplier" disabled="disabled" ${itemDto.primarySupplier==true?'checked':''} />
                                                                <spring:message code="BzComposer.additem.primarysupplier"/>
                                                            </td>
                                                            <td colspan="8">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3" rowspan="3">
                                                                <table border="1" style="width: 100%;height: 100%;">
                                                                    <tr>
                                                                        <td>
                                                                            <spring:message code="BzComposer.additem.suppliername"/>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <table id="supplierName">
                                                                                <tbody></tbody>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                            <td align="left"></td>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.minimumorderunit"/>
                                                            </td>
                                                            <td>${itemDto.minOrderUnit}</td>
                                                            <td colspan="6">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">
                                                                <!-- <input type="button" class="formbutton" value="<spring:message code='BzComposer.global.add'/>" />
                                                                <input type="button" class="formbutton" value="<spring:message code='BzComposer.global.edit'/>" />
                                                                <input type="button" class="formbutton" value="<spring:message code='BzComposer.global.delete'/>" /> -->
                                                            </td>
                                                            <td colspan="8">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left"></td>
                                                            <td colspan="8">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">
                                                                <spring:message code="BzComposer.Item.Description"/>
                                                                <input type="hidden" name="inventoryId" id="inventoryId11" value="${itemDto.inventoryId}"/>
                                                            </td>
                                                            <td colspan="11">
                                                                <textarea id="textAreaContent" rows="2" cols="120">${itemDto.textAreaContent}</textarea>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>

                                            <%-- description-3 --%>
                                                    <table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;display:none;">
                                                        <thead>
                                                        <tr>
                                                            <th colspan="10" style="font-size:12px;">
                                                                <spring:message code="BzComposer.Item.Description"/>
                                                                <input type="hidden" name="inventoryId" id="inventoryId11" value="${itemDto.inventoryId}"/>
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td id="tAreaContent">
                                                                <textarea id="textAreaContent" rows="2" cols="130">${itemDto.textAreaContent}</textarea>
                                                            </td>
                                                            <td id="Editor" style="display: none;">
                                                                <textarea name="editor1">${itemDto.textAreaContent}</textarea>
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
                                                                    CKEDITOR.replace( 'editor1' ); */
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
                                                                CKEDITOR.replace( 'editor1' ); */

                                                                    CKEDITOR.replace( 'editor1' );
                                                                </script>
                                                            </td>
                                                        </tr>
                                                        <!-- <tr>
                                                            <td>
                                                                <input type="button" class="formbutton" onclick="getData();" value="<spring:message code='BzComposer.additem.htmlbtn'/>">
                                                                &nbsp;&nbsp;
                                                                <input type="button" class="formbutton" onclick="getEditor();" value="<spring:message code='BzComposer.global.edit'/>">
                                                            </td>
                                                        </tr> -->
                                                        </tbody>
                                                    </table>

                                            <%-- pricing-2 --%>
                                                    <table cellspacing="0" class="tabla-listados"
                                                           style="margin-top: 0; margin-left: 20px;">
                                                        <thead>
                                                        <tr>
                                                            <th colspan="12" style="font-size:12px;">
                                                                <spring:message code="BzComposer.tabs.pricing"/>
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <input type="hidden" name="inventoryId" id="inventoryId11"
                                                               value="${itemDto.inventoryId}"/>
                                                        <tbody>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" id="priceLevel" name="priceLevel" checked>
                                                                <spring:message code="BzComposer.additem.applypricelevel"/>
                                                            </td>
                                                            <td colspan="11">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="11">
                                                                <div id="tabsPricing" style="height:400px;">
                                                                    <b><spring:message code="BzComposer.additem.pricelevel"/></b>
                                                                    <div id="priceLevel-1">
                                                                        <div id="content1" class="tabPage">
                                                                            <!-- add here the content of first tab -->
                                                                            <div id="table-negotiations">
                                                                                <table class="tabla-listados"
                                                                                       cellspacing="0">
                                                                                    <thead>
                                                                                    <tr>
                                                                                        <td colspan="12"
                                                                                            style="font-size:1.3em;">

                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="2" rowspan="3">
                                                                                            <table border="1"
                                                                                                   style="width: 200px;"
                                                                                                   id="">
                                                                                                <tr>
                                                                                                    <td>
                                                                                                        <spring:message
                                                                                                                code="BzComposer.additem.pricelevel"/>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <spring:message
                                                                                                                code="BzCompoer.additem.percentagebracket"/>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                    <%-- <select path="priceLevelId" id="customerPaymentMethod"> --%>
                                                                                                <c:if test="${not empty ItemForm.listOfExistingPriceLevels}">
                                                                                                    <%
                                                                                                        int priceLevelSize = 0;
                                                                                                    %>
                                                                                                    <c:forEach
                                                                                                            items="${ItemForm}"
                                                                                                            var="objList1">
                                                                                                        <%
                                                                                                            priceLevelSize++;
                                                                                                        %>
                                                                                                        <tr id="priceLevel"
                                                                                                            value="${objList1.priceLevelId}"
                                                                                                            onclick="showSelectedOption('${objList1.priceLevelId}',
                                                                                                                    '${objList1.priceLevel}',
                                                                                                                    '${objList1.pricePercentage}');">
                                                                                                            <td>
                                                                                                                    ${objList1.priceLevel}
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                    ${objList1.pricePercentage}
                                                                                                            </td>

                                                                                                        </tr>
                                                                                                    </c:forEach>
                                                                                                    <input type="hidden"
                                                                                                           id="priceLevelSize"
                                                                                                           name="priceLevelSize"
                                                                                                           value='<%=priceLevelSize%>'/>
                                                                                                </c:if>
                                                                                            </table>
                                                                                        </td>
                                                                                        <td>
                                                                                            <spring:message
                                                                                                    code="BzComposer.additem.pricelevelname"/>
                                                                                        </td>
                                                                                        <td>
                                                                                            <input type="text"
                                                                                                   path="priceLevel"
                                                                                                   id="priceLevelName"/>
                                                                                        </td>
                                                                                        <td>
                                                                                        </td>
                                                                                        <td>
                                                                                            <input type="checkbox"
                                                                                                   id="priceLevelInactive"
                                                                                                   name="priceLevelInactive">
                                                                                            <spring:message
                                                                                                    code="BzComposer.additem.pricelevelisinactive"/>
                                                                                        </td>
                                                                                        <td colspan="6">&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            <spring:message
                                                                                                    code="BzComposer.additem.thispricelevelwill"/>
                                                                                        </td>
                                                                                        <td>
                                                                                            <select id="priceLevelWill">
                                                                                                <option value="1">
                                                                                                    <spring:message
                                                                                                            code="BzComposer.additem.increase"/></option>
                                                                                                <option value="2">
                                                                                                    <spring:message
                                                                                                            code="BzComposer.additem.decrease"/></option>
                                                                                            </select>
                                                                                        </td>
                                                                                        <td>
                                                                                            <spring:message
                                                                                                    code="BzComposer.additem.itempricesby"/>
                                                                                        </td>
                                                                                        <td>
                                                                                            <input type="text"
                                                                                                   path="pricePercentage"
                                                                                                   id="pricePercentage"/>
                                                                                        </td>
                                                                                        <td colspan="6">&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            <spring:message
                                                                                                    code="BzComposer.additem.priceleveltype"/>
                                                                                        </td>
                                                                                        <td>
                                                                                            <select id="priceLevelOption">
                                                                                                <option value="1">
                                                                                                    <spring:message
                                                                                                            code="BzComposer.additem.fixed"/></option>
                                                                                                <option value="2">
                                                                                                    <spring:message
                                                                                                            code="BzComposer.additem.itemprice"/></option>
                                                                                            </select>
                                                                                        </td>
                                                                                        <td colspan="8">&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="8">&nbsp;</td>
                                                                                        <td>
                                                                                            <!-- <input type="button" id="UpdatePricelevel" name="UpdatePriceLevel" class="formbutton"
                                                                                                   value="<spring:message code='BzComposer.global.save'/>"> -->
                                                                                        </td>
                                                                                        <td>
                                                                                            <!-- <input type="reset" id="ClearPriceLevel" name="ClearPriceLevel" class="formbutton"
                                                                                                   value="<spring:message code='BzComposer.additem.clearbtn'/>"> -->
                                                                                        </td>
                                                                                        <td colspan="2">
                                                                                            &nbsp;
                                                                                        </td>
                                                                                    </tr>
                                                                                    </thead>
                                                                                    <tbody>
                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <b><spring:message code="BzComposer.additem.addpricelevel"/></b>
                                                                    <div id="addPriceLevel-2">
                                                                        <div id="content1" class="tabPage">
                                                                            <!-- add here the content of first tab -->
                                                                            <div id="table-negotiations">
                                                                                <table class="tabla-listados" cellspacing="0">
                                                                                    <thead>
                                                                                    <tr>
                                                                                        <td colspan="12"></td>
                                                                                    </tr>
                                                                                    </thead>
                                                                                    <tbody>
                                                                                    <tr>
                                                                                        <td>
                                                                                            <spring:message code="BzComposer.additem.pricelevelname"/>
                                                                                        </td>
                                                                                        <td>
                                                                                            <input type="text" id="priceLevelName" name="priceLevelName" />
                                                                                        </td>
                                                                                        <td>
                                                                                        </td>
                                                                                        <td>
                                                                                            <input type="checkbox"
                                                                                                   id="priceLevelInactive"
                                                                                                   name="priceLevelInactive">
                                                                                            <spring:message
                                                                                                    code="BzComposer.additem.pricelevelisinactive"/>
                                                                                        </td>
                                                                                        <td colspan="8">&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            <spring:message
                                                                                                    code="BzComposer.additem.thispricelevelwill"/>
                                                                                        </td>
                                                                                        <td>
                                                                                            <select id="priceLevel">
                                                                                                <option value="1">
                                                                                                    <spring:message
                                                                                                            code="BzComposer.additem.increase"/></option>
                                                                                                <option value="2">
                                                                                                    <spring:message
                                                                                                            code="BzComposer.additem.decrease"/></option>
                                                                                            </select>
                                                                                        </td>
                                                                                        <td>
                                                                                            <spring:message
                                                                                                    code="BzComposer.additem.itempricesby"/>
                                                                                        </td>
                                                                                        <td>
                                                                                            <input type="text" id="itemPrice">
                                                                                        </td>
                                                                                        <td colspan="8">&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            <spring:message code="BzComposer.additem.priceleveltype"/>
                                                                                        </td>
                                                                                        <td>
                                                                                            <select id="priceLevel">
                                                                                                <option value="1">
                                                                                                    <spring:message code="BzComposer.additem.fixed"/></option>
                                                                                                <option value="2">
                                                                                                    <spring:message code="BzComposer.additem.itemprice"/></option>
                                                                                            </select>
                                                                                        </td>
                                                                                        <td colspan="10">&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="8">&nbsp;</td>
                                                                                        <td>
                                                                                            <!-- <input type="button" id="addPriceLevel" name="addPriceLevel" class="formbutton"
                                                                                                   value="<spring:message code='BzComposer.global.add'/>" onclick="addData();"> -->
                                                                                        </td>
                                                                                        <td>
                                                                                            <!-- <input type="button" id="ClearPriceLevel2" name="ClearPriceLevel2" class="formbutton"
                                                                                                   value="<spring:message code='BzComposer.additem.clearbtn'/>"> -->
                                                                                        </td>
                                                                                        <td colspan="2">
                                                                                            &nbsp;
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
                                                        <tr>
                                                            <td colspan="12">
                                                                &nbsp;
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>

                                                <%-- channelSetting-4 --%>
                                                    <table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px; display:none;">
                                                        <thead>
                                                        <tr>
                                                            <th colspan="10" style="font-size:12px;">
                                                                <spring:message code="BzComposer.Item.ChannelSetting"/>
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <input type="hidden" name="inventoryId" id="inventoryId11" value="${itemDto.inventoryId}"/>

                                                        <tbody>
                                                        <tr>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.selectstore"/>
                                                            </td>
                                                            <td colspan="11">&nbsp;</td>
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
                                                            <td colspan="8">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3" align="left">
                                                                <spring:message code="BzComposer.additem.asinnote"/>
                                                            </td>
                                                            <td align="right">
                                                                <input type="button" id="edit" name="edit" class="formbutton" value="<spring:message code='BzComposer.global.edit'/>">
                                                            </td>
                                                            <td colspan="8">
                                                                &nbsp;
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                            <%-- alternateBarcodeSKU-5 --%>
                                                    <table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;display:none;">
                                                        <thead>
                                                        <tr>
                                                            <th colspan="10" style="font-size:12px;">
                                                                <spring:message code="BzComposer.Item.alternateBarcode&SKUs"/>
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <input type="hidden" name="inventoryId" id="inventoryId11"
                                                               value="${itemDto.inventoryId}"/>

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
                                                            <td colspan="2">
                                                                &nbsp;
                                                            </td>
                                                            <td colspan="7">
                                                                &nbsp;
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right">
                                                                <select id="barcodes" multiple="multiple" style="height: 190px;width: 130px;">
                                                                    <option value="0">
                                                                    </option>
                                                                </select>
                                                            </td>
                                                            <td>
                                                                &nbsp;
                                                            </td>
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
                                                                            <spring:message code="BzComposer.additem.barcode"/>
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
                                                                            <spring:message code="BzComposer.additem.sku"/>
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
                                                            <!-- <td>Barcode</td> -->
                                                            <!-- <td><input type="text" id="barcode" name="barcode" style="width: 60px;"></td> -->
                                                            <td colspan="8">&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>

                                            <!-- images-6 -->
                                                    <table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;">
                                                        <thead>
                                                        <tr>
                                                            <th colspan="10" style="font-size:12px;">
                                                                <spring:message code="BzComposer.tabs.images"/>
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <%-- <tr>
                                                            <td style="width:10%;">
                                                                <spring:message code="BzComposer.additem.image"/>
                                                            </td>
                                                            <td style="width: 15%;">
                                                                <input type="text" id="imageName" name="imageName" size="55">
                                                            </td>
                                                            <td align="right" style="width:20%;">
                                                                <input type="button" id="viewImage" name="viewImage" class="formbutton" value="<spring:message code='BzComposer.additem.viewimage'/>">
                                                            </td>
                                                            <td align="left" style="width: 20%;">
                                                                <input type="file" class="formbutton" id="imgupload" value=""/>
                                                                <!-- <input type="button" id="browseImage" name="browseImage" class="formbutton" value="Browse Image"> -->
                                                            </td>
                                                            <td colspan="6" style="width:35%;">&nbsp;</td>
                                                        </tr> --%>
                                                        <tr>
                                                            <td colspan="2" align="left" style="width: 45%;">
                                                                <img src="" id="resultImage" style="height: 139px; width: 299px;border: 1em;" />
                                                            </td>
                                                            <td colspan="8" style="width: 55%;">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="10">&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>

                                            <%-- webFields-7 --%>
                                                    <table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;display:none;">
                                                        <thead>
                                                        <tr>
                                                            <th colspan="11" style="font-size:12px;">
                                                                <spring:message code="BzComposer.tabs.webfields"/>
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                       <tbody>
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
                                                       </tbody>
                                                    </table>
                                            <%-- eBay-8 --%>
                                                    <table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;display:none;">
                                                        <thead>
                                                        <tr>
                                                            <th colspan="10" style="font-size:12px;">
                                                                <spring:message code="BzComposer.tabs.ebay"/>
                                                            </th>

                                                        </tr>
                                                        <tr>
                                                            <th colspan="12" style="font-size:12px;">
                                                                <spring:message code="BzComposer.additem.crosssell"/>
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td colspan="12" align="left">
                                                                <spring:message code="BzComposer.additem.productlist"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="4">
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
                                                            <td>
                                                                <input type="button" id="addCrossSell" class="formbutton" name="addCrossSell" value="<spring:message code='BzComposer.additem.addtocrosssellbtn'/>">
                                                            </td>
                                                            <td colspan="7">
                                                                &nbsp;
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="12">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="12" align="left">
                                                                <spring:message code="BzComposer.additem.crosssellproducts"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="4">
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
                                                            <td>
                                                                <input type="button" class="formbutton" id="deleteCrossSellProduct" name="deleteCrossSellProduct"
                                                                       value="<spring:message code='BzComposer.global.delete'/>">
                                                            </td>
                                                            <td colspan="7">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="12">&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                            <%-- itemCategory-9 --%>
                                                    <table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;display:none;">
                                                        <thead>
                                                        <tr>
                                                            <th colspan="10" style="font-size:12px;">
                                                                <spring:message code="BzComposer.tabs.itemcategory"/>
                                                            </th>
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
                                                                <select path="storeId" multiple="multiple" id="availableStores" style="height: 150px;width: 120px;">
                                                                    <c:forEach items="${itemStoreList}" var="obj">
                                                                        <option value="${obj.value}">${obj.label}</option>
                                                                    </c:forEach>
                                                                </select>
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
                                            <%-- customOption-10 --%>
                                                    <table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;display:none;">
                                                        <thead>
                                                        <tr>
                                                            <th colspan="12" style="font-size:12px;">
                                                                <spring:message code="BzComposer.tabs.customoption"/>
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td colspan="11" align="right">
                                                                <input type="button" id="addOption" name="addOption"
                                                                       value="<spring:message code='Bzcomposer.additem.addnewoption'/>" class="formbutton"/>
                                                            </td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="12">
                                                                <div>
                                                                    <table id="addTableRow">
                                                                        <tbody></tbody>
                                                                    </table>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                            <!-- History tab starts -->
                                                    <table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;display:none;">
                                                        <thead>
                                                        <tr>
                                                            <th colspan="13" style="font-size:12px;">
                                                                <spring:message code="BzComposer.tabs.history"/>
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td><spring:message code='BzComposer.additem.filteroption'/></td>
                                                            <td>
                                                                <select id="filterOption">
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
                                                            <td>
                                                                <spring:message code="BzComposer.additem.datefrom"/>
                                                                &nbsp;&nbsp;
                                                                <input type="date" id="dateFrom">
                                                            </td>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.dateto"/>
                                                                &nbsp;&nbsp;
                                                                <input type="date" id="dateTo">
                                                            </td>
                                                            <td>
                                                                <input type="text" id="search" name="search" class="formbutton"
                                                                       value="<spring:message code='BzComposer.additem.search'/>" size="5"/>
                                                            </td>
                                                            <td colspan="7">
                                                                &nbsp;
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <spring:message code="BzComposer.additem.itemcode"/>
                                                            </td>
                                                            <td>
                                                                <select>
                                                                    <option></option>
                                                                    <option></option>
                                                                </select>
                                                            </td>
                                                            <td>
                                                                <spring:message code='BzComposer.additem.startinginventoryquantity'/>
                                                            </td>
                                                            <td colspan="9">
                                                                &nbsp;
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="12">
                                                                <div id="stockInOut" style="height:300px;width:100%;">
                                                                   <b style="font-size:15px;"> <spring:message code="BzComposer.additem.stockinout"/></b>
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
                                                                                        <th></th>
                                                                                    </tr>
                                                                                    </thead>
                                                                                    <tbody>
                                                                                    <tr>
                                                                                        <td>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                        </td>
                                                                                    </tr>
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
                                                        <tr>
                                                            <td colspan="12">&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div>
                        <input type="hidden" name="tabid" id="tabid" value=""/>
                        <input type="hidden" id="selectedRID" value="${selectedRID}"/>
                        <input type="hidden" id="supplierIDs" value="${itemDto.supplierIDs}"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>
<script>
    function initialize() {
        var rid = document.getElementById('selectedRID').value+"$$";
        var rd = document.getElementById(rid);
        rd.className = "draft";
        <c:if test="${not empty VendorFrm}">tableValue();</c:if>
        setupSupplirNames();
    }
    function setupSupplirNames(){
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

    function setRowId(rowid, rid) {
        size = document.getElementById("lSize").value;
        //size1=document.getElementById("seSize").value;
        for (i = 0; i < size; i++) {
            var row1 = document.getElementById(i + "$$");
            row1.className = "";
        }
        var rd = document.getElementById(rid+'$$');
        rd.className = "draft";
        document.getElementById("setRID").value = rid+'$$';
        document.CustomerForm.clientVendorID.value = rowid;
        var custid = document.CustomerForm.clientVendorID.value;
        getVendorInfo(rowid, rid);
    }

    function getVendorInfo(inventoryId, rowId) {
        //document.getElementById('vendrId').value=vendorid;
        //document.getElementById('vendrId').value=document.CustomerForm.clientVendorID.value;
        //var rowID = rowId;
        window.location = "Item?tabid=SearchItemView&InvId="+inventoryId+"&selectedRID="+rowId;
    }

    function numbersonly(e, val) {
        var temp = val.indexOf(".");
        var key = e.charCode ? e.charCode : e.keyCode;
        if (window.event) {
            if (window.event.ctrlKey)
                isCtrl = true;
            else
                isCtrl = false;
        } else {
            if (e.ctrlKey)
                isCtrl = true;
            else
                isCtrl = false;
        }
        if (isCtrl) {
            if ("v" == String.fromCharCode(key).toLowerCase()) {
                return false;
            }
            if ("x" == String.fromCharCode(key).toLowerCase()) {
                return false;
            }
        } else if (key != 8) {
            var str = String(val);
            var temp = val.indexOf(".");
            index = 0;
            for (i = 0; i < str.length; i++) {
                if (str.charAt(i) == '.') {
                    index = 1;
                    break;
                }
            }
            if (key == 46 && temp == -1) {
                return true;
            } else if (key == 37 || key == 39) {
                return true;
            } else if (key == 110 && index == 0) {
                return true;
            } else if (key == 190 && index == 0) {
                return true;
            } else if (key >= 96 && key <= 105) {
                return true;
            } else if (key < 48 || key > 57) //if not a number
                return false; //disable key press
        }
    }

    function manageItem(cmd) {
        debugger;
        var itemid = document.getElementById("inventoryId11").value;
        if (itemid == 0) {

            return showCustomerValidationDialog();
            //return;
        } else {
            if (cmd == "EDIT") {
                showitem(itemid);

            } else if (cmd == "DELETE") {

                if (confirm("This will delete all the information related to this Customer (ID=" + itemid + ")\nAre you sure to delete ?") == true) {
                    window.location = "Item?tabid=DeleteItem&InvId=" + itemid;
                }
                /* event.preventDefault();
                $("#deleteCustomer").dialog({
                    resizable: false,
                    height: 200,
                    width: 500,
                    modal: true,
                    buttons: {
                        "
                <spring:message code='BzComposer.global.ok'/>": function () {
		                $(this).dialog("close");
		                document.forms[0].action="Customer?customerAction=DELETE&cvID="+cvid;
						document.forms[0].submit();
						$('form').submit();
		            },

                <spring:message code='BzComposer.global.cancel'/>: function () {
		                $(this).dialog("close");
		                return false;
		            }
				}
			});*/
                return false;
            }
        }
    }

    function addNewItem() {
        window.location = "Item?tabid=ShowAdd&ItemType=1&showHistoryPanel=1";
    }

    function showitem(itemid) {
        window.open("Item?tabid=SearchItem&InvId=" + itemid, null, "scrollbars=yes,height=600,width=1200,status=yes,toolbar=no,menubar=no,location=no");
    }
</script>
<!-- Dialog box used in sales order page -->
<div id="showCustomerValidationDialog" style="display:none;">
    <p><spring:message code="BzComposer.customerinfo.selectitemfirst"/></p>
</div>
<div id="deleteCustomer" style="display:none;">
    <p><spring:message code="BzComposer.customerinfo.deleteselecteditem"/></p>
</div>