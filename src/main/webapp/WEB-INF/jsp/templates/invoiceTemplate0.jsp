<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script>
	self.moveTo(100, 100);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<title><spring:message code="BzComposer.addnewcustomertitle" /></title>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<style>
th,td { font-size:14px;padding: 5px }
.paddingTbl { padding: 5px 20px 5px 20px;}
.tdBorder { border: 2px solid grey; text-align: center; }
.tdBorderBottom { border-bottom: 3px solid grey;}
.alignLeft { text-align: left !important; }
.alignRight { text-align: right !important; }
input,textarea,select{ font-size:12px !important; }
.okDiv{
    width: 97% !important; height: 1000px; padding: 20px; margin: 20px !important;
    border: 2px solid grey; border-radius: 5px !important; box-shadow: 5px 5px grey;
}
@media print {
    .thColor {
        -webkit-print-color-adjust: exact;
        color-adjust: exact;
    }
}
</style>
</head>
<body>
<!-- begin shared/header -->
<div id="cos">
<div style="width: 100%;padding: 10px 30px 0px 0px;text-align: right;">
    Click Invoice Number : <strong>
    <c:forEach items="${PrintOrderNums}" var="curObject">
        <a href="${baseURL}&custID=${custID}&ttype=${templateType}&orderNo=${curObject}">${curObject}</a>&nbsp;&nbsp;
    </c:forEach>
    </strong>
    <button type="button" class="formbutton" onclick="printInvoice();" style="padding: 5px 20px 5px 20px; font-size: 16px;">
        <spring:message code='BzComposer.global.print' />
    </button>
</div>
<div id="invoiceContentDiv" class="statusquo ok okDiv">
<div>
    <c:if test="${empty PrintOrderDetails}">
        <span style="width:100%;text-align: right;color:red;"><strong> <spring:message code="BzComposer.employee.norecordsfound" /> </strong></span>
    </c:if>
    <c:if test="${not empty PrintOrderDetails}">
    <table style="width:100%;" cellspacing="0">
        <tbody>
            <tr>
                <td style="width:45%;">
                    <strong>
                        ${PrintOrderDetails.fullName}<br/>${PrintOrderDetails.companyName}<br/>
                        ${PrintOrderDetails.address1},<br/>${PrintOrderDetails.address2}<br/>
                        ${PrintOrderDetails.city} ${PrintOrderDetails.state} ${PrintOrderDetails.zipcode}<br/>
                        ${PrintOrderDetails.country}<br/>${PrintOrderDetails.emailAddr}
                    </strong>
                </td>
                <td style="width:10%;">&nbsp;</td>
                <td style="width:45%;">&nbsp;</td>
            </tr>
            <tr>
                <td><strong>${PrintOrderDetails.emailAddr}</td>
                <td>&nbsp;</td>
                <td align="right">
                    <table style="width:60%;">
                        <tr>
                            <td><spring:message code="BzComposer.accounting.Date" /></td>
                            <th>${PrintOrderDetails.orderDate}</th>
                        </tr>
                        <tr>
                            <td><spring:message code="eSalesRefundReportDetails.Invoice_No." /></td>
                            <th>${PrintOrderDetails.orderNo}</th>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr><td colspan="3">&nbsp;</td></tr>
            <tr>
                <th class="tdBorder alignLeft"><spring:message code="BzComposer.Invoice.BillTo" /></th>
                <th>&nbsp;</th>
                <th class="tdBorder alignLeft"><spring:message code="BzComposer.Invoice.ShipTo" /></th>
            </tr>
            <tr>
                <td class="tdBorder alignLeft">${PrintOrderDetails.billTo}</td>
                <td>&nbsp;</td>
                <td class="tdBorder alignLeft">${PrintOrderDetails.shipTo}</td>
            </tr>
            <tr><td colspan="3">&nbsp;</td></tr>
            <tr>
                <td colspan="3">
                    <table>
                        <tr>
                            <th class="tdBorder ShipDateCls"><spring:message code="BzComposer.Invoice.ShipDateLower" /></th>
                            <th class="tdBorder RepCls"><spring:message code="BzComposer.Invoice.Rep" /></th>
                            <th class="tdBorder TermCls"><spring:message code="BzComposer.Invoice.Term" /></th>
                            <th class="tdBorder PayMethodCls"><spring:message code="BzComposer.Invoice.PayMethod" /></th>
                            <th class="tdBorder ViaCls"><spring:message code="BzComposer.Invoice.Via" /></th>
                        </tr>
                        <tr>
                            <td class="tdBorder paddingTbl ShipDateCls">${PrintOrderDetails.shipDate}</td>
                            <td class="tdBorder paddingTbl RepCls">${PrintOrderDetails.rep}</td>
                            <td class="tdBorder paddingTbl TermCls">${PrintOrderDetails.term}</td>
                            <td class="tdBorder paddingTbl PayMethodCls">${PrintOrderDetails.payMethod}</td>
                            <td class="tdBorder paddingTbl ViaCls">${PrintOrderDetails.via}</td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr><td colspan="3">&nbsp;</td></tr>
            <tr>
                <td colspan="3">
                    <table style="width:100%;">
                        <tr>
                            <th class="tdBorder ItemIDCls"><spring:message code="BzComposer.Invoice.ItemID" /></th>
                            <th class="tdBorder ItemNameCls"><spring:message code="BzComposer.Invoice.ItemName" /></th>
                            <th class="tdBorder SerialNoCls"><spring:message code="BzComposer.Invoice.SerialNo" /></th>
                            <th class="tdBorder UnitPriceCls"><spring:message code="BzComposer.Invoice.UnitPrice" /></th>
                            <th class="tdBorder QtyCls"><spring:message code="BzComposer.Invoice.Qty" /></th>
                            <th class="tdBorder AmtCls"><spring:message code="BzComposer.Invoice.Amt" /></th>
                            <th class="tdBorder WeightCls"><spring:message code="BzComposer.Invoice.Weight" /></th>
                            <th class="tdBorder TaxCls"><spring:message code="BzComposer.Invoice.Tax" /></th>
                        </tr>
                        <c:forEach items="${PrintOrderDetails.cart}" var="cart" varStatus="loop">
                        <tr id='${loop.index}row'>
                            <td class="tdBorder ItemIDCls">${cart.invCode}</td>
                            <td class="tdBorder ItemNameCls">${cart.invDesc}</td>
                            <td class="tdBorder SerialNoCls">${cart.serialNo}</td>
                            <td class="tdBorder UnitPriceCls">${cart.uprice}</td>
                            <td class="tdBorder QtyCls">${cart.qty}</td>
                            <td class="tdBorder AmtCls">${cart.amount}</td>
                            <td class="tdBorder WeightCls">${cart.weight}</td>
                            <td class="tdBorder TaxCls">${cart.tax}</td>
                        </tr>
                    </c:forEach>
                    </table>
                </td>
            </tr>
            <tr><td colspan="3">&nbsp;</td></tr>
            <tr><td colspan="3">&nbsp;</td></tr>
            <tr>
                <td valign="top">
                    <table style="width:60%;" align="right">
                        <tr>
                            <td><spring:message code="BzComposer.common.totalWeight" /></td>
                            <td class="tdBorder">${PrintOrderDetails.weight}</td>
                        </tr>
                    </table>
                </td>
                <td>&nbsp;</td>
                <td>
                    <table style="width:80%;" align="right">
                        <tr>
                            <td><spring:message code="BzComposer.common.shippingAndHandling" /></td>
                            <td class="tdBorder alignRight" style="width:150px;">${PrintOrderDetails.shipping}</td>
                        </tr>
                        <tr>
                            <td><spring:message code="BzComposer.Invoice.Tax" /></td>
                            <td class="tdBorder alignRight">${PrintOrderDetails.tax}</td>
                        </tr>
                        <tr>
                            <td><spring:message code="eSalesRefundReportDetails.Total" /></td>
                            <td class="tdBorder alignRight">${PrintOrderDetails.total}</td>
                        </tr>
                        <tr>
                            <td><strong><spring:message code="BizComposer.Invoice.Paid" /></strong></td>
                            <td class="tdBorder alignRight">${PrintOrderDetails.total - PrintOrderDetails.balance}</td>
                        </tr>
                        <tr>
                            <td><strong><spring:message code="BzComposer.Invoice.balance" /></strong></td>
                            <td class="tdBorder alignRight">${PrintOrderDetails.balance}</td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr><td colspan="3">&nbsp;</td></tr>
            <tr><td colspan="3"><hr/></td></tr>
            <tr><td colspan="3">${PrintOrderDetails.message}</td></tr>
            <tr><td colspan="3">&nbsp;</td></tr>
        </tbody>
    </table>
    </c:if>
<input type="hidden" id="invoiceStyle" value="${PrintOrderDetails.invoiceStyle}" />

</div>
</div>
</div>
</body>
</html>
<script>
let invoiceStyle = document.getElementById("invoiceStyle").value;
StyleChange(invoiceStyle);
ShippingChange(invoiceStyle);
function StyleChange(inoiceStyle)
{
    if(inoiceStyle == 1){
        $('.ItemIDCls').show();
        $('.ItemNameCls').show();
        $('.SerialNoCls').hide();
        $('.UnitPriceCls').show();
        $('.QtyCls').show();
        $('.AmtCls').hide();
        $('.WeightCls').hide();
        $('.TaxCls').show();
    } else {
         $('.ItemIDCls').show();
         $('.ItemNameCls').show();
         $('.SerialNoCls').hide();
         $('.UnitPriceCls').show();
         $('.QtyCls').show();
         $('.AmtCls').show();
         $('.WeightCls').show();
         $('.TaxCls').show();
    }
}

function ShippingChange(inoiceStyle)
{
    if(inoiceStyle == 1 || inoiceStyle == 2 || inoiceStyle == 5 || inoiceStyle == 6){
        $('.ShipDateCls').hide();
        $('.RepCls').hide();
        $('.TermCls').show();
        $('.PayMethodCls').show();
        $('.ViaCls').hide();
    } else {
         $('.ShipDateCls').show();
         $('.RepCls').show();
         $('.TermCls').show();
         $('.PayMethodCls').show();
         $('.ViaCls').show();
    }
}

function printInvoice(){
    //window.open();
    //window.document.write(document.getElementById('invoiceContentDiv').innerHTML);
    window.print();
    //window.close();
}
function printInvoice22(){
    let style = "<style>";
    style = style + "th,td { font-size:14px;padding: 5px } ";
    style = style + ".paddingTbl { padding: 5px 20px 5px 20px;} ";
    style = style + ".tdBorder { border: 1px solid grey; text-align: center; } ";
    style = style + ".tdBorderBottom { border-bottom: 3px solid grey;} ";
    style = style + ".alignLeft { text-align: left !important; } ";
    style = style + ".alignRight { text-align: right !important; } ";
    style = style + "input,textarea,select{ font-size:12px !important; } ";
    style = style + ".okDiv{ width: 97% !important; padding: 20px; margin: 20px !important;border: 2px solid grey; border-radius: 5px !important; box-shadow: 5px 5px grey; } ";
    style = style + "</style>";
    // CREATE A WINDOW OBJECT.
    var win = window.open('', '', 'height=500,width=700');
    win.document.write('<html><head>');
    win.document.write('<title>Received Item List</title>');
    win.document.write(style);
    win.document.write('</head>');
    win.document.write('<body>');
    win.document.write(document.getElementById("invoiceContentDiv").innerHTML);
    win.document.write('</body></html>');
    win.document.close();
    win.print();
}
</script>