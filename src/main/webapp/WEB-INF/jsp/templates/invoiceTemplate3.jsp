<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<title><spring:message code="BzComposer.addnewcustomertitle" /></title>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<script>
	self.moveTo(100, 10);
</script>
<style>
th,td { font-size:14px;padding: 5px }
.paddingTbl { padding: 5px 20px 5px 20px;}
.tdBorder { border: 2px solid grey; text-align: center; }
.tdBorderBottom { border-bottom: 3px solid grey;}
.alignLeft { text-align: left !important; }
.alignRight { text-align: right !important; }
input,textarea,select{ font-size:12px !important; }
.okDiv{
    width: 97% !important; padding: 20px; margin: 20px !important;
    border: 2px solid grey; border-radius: 5px !important; box-shadow: 5px 5px grey;
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
                    <div style="float:left">
                        <img src="${pageContext.request.contextPath}/images/new/NextBitsLogo.jpg" />
                    </div>
                    <div>
                        <strong>Nextbits Corporation</strong><br/>
                        6771 Beach Blvd #F<br/>
                        Buena Park, CA 90621<br/>
                        (714)788-3954<br/>
                        www.nextbits.com<br/>
                        USA
                    </div>
                </td>
                <td style="width:9%;">&nbsp;</td>
                <td style="width:45%;" align="right" valign="bottom">
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
                <td class="tdBorder alignLeft tdBorderBottom" valign="top">
                    Christine Lee <br/>Ceragem Healing Center <br/>4860 Irvine Blvd #203 <br/>Irvine, CA 92620 <br/>1(657)660-5245
                </td>
                <td>&nbsp;</td>
                <td class="tdBorder alignLeft tdBorderBottom" valign="top">
                    Christine Lee <br/>Ceragem Healing Center <br/>4860 Irvine Blvd #203 <br/>Irvine, CA 92620 <br/>1(657)660-5245
                </td>
            </tr>
            <tr><td colspan="3">&nbsp;</td></tr>
            <tr>
                <td colspan="3">
                    <table style="width:100%;">
                        <tr>
                            <th class="tdBorder"><spring:message code="BzComposer.Invoice.PayMethod" /></th>
                            <th class="tdBorder"><spring:message code="BzComposer.Invoice.Rep" /></th>
                            <th class="tdBorder"><spring:message code="BzComposer.Invoice.ShipDateLower" /></th>
                            <th class="tdBorder"><spring:message code="BzComposer.Invoice.Via" /></th>
                            <th class="tdBorder"><spring:message code="BzComposer.common.noOfBoxes" /></th>
                            <th class="tdBorder"><spring:message code="BzComposer.Invoice.Term" /></th>
                        </tr>
                        <tr>
                            <td class="tdBorder paddingTbl">${PrintOrderDetails.payMethod}</td>
                            <td class="tdBorder paddingTbl">${PrintOrderDetails.rep}</td>
                            <td class="tdBorder paddingTbl">${PrintOrderDetails.shipDate}</td>
                            <td class="tdBorder paddingTbl">${PrintOrderDetails.via}</td>
                            <td class="tdBorder paddingTbl">1</td>
                            <td class="tdBorder paddingTbl">${PrintOrderDetails.term}</td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr><td colspan="3">&nbsp;</td></tr>
            <tr>
                <td colspan="3">
                    <table style="width:100%;">
                        <tr>
                            <th class="tdBorder"><spring:message code="BzComposer.Item.ItemCode" /></th>
                            <th class="tdBorder"><spring:message code="BzComposer.Item.ItemTitle" /></th>
                            <th class="tdBorder"><spring:message code="BzComposer.Invoice.Qty" /></th>
                            <th class="tdBorder"><spring:message code="SalesTaxList.Rate" /></th>
                            <th class="tdBorder"><spring:message code="BzComposer.Invoice.Amt" /></th>
                        </tr>
                        <c:forEach items="${PrintOrderDetails.cart}" var="cart" varStatus="loop">
                        <tr id='${loop.index}row'>
                            <td class="tdBorder">${cart.invCode}</td>
                            <td class="tdBorder">${cart.invDesc}</td>
                            <td class="tdBorder">${cart.qty}</td>
                            <td class="tdBorder">${cart.uprice}</td>
                            <td class="tdBorder">${cart.amount}</td>
                        </tr>
                    </c:forEach>
                    </table>
                </td>
            </tr>
            <tr><td colspan="3">&nbsp;</td></tr>
            <tr><td colspan="3">&nbsp;</td></tr>
            <tr>
                <td valign="top">
                    <table style="width:70%;margin-top:5px;" align="right">
                        <tr>
                            <td><spring:message code="BzComposer.common.totalWeight" /></td>
                            <td class="tdBorder" style="width:100px;">${PrintOrderDetails.weight}</td>
                        </tr>
                    </table>
                </td>
                <td>&nbsp;</td>
                <td>
                    <table style="width:95%;margin-top:5px;" align="right">
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
function printInvoice(){
    //window.open();
    //window.document.write(document.getElementById('invoiceContentDiv').innerHTML);
    window.print();
    //window.close();
}
</script>