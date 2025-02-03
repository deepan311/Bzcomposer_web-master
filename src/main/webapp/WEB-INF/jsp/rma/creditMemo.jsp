<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.creatermatitle" /></title>
<style type="text/css">
.dhtmlgoodies_answer{ height: 600px !important; }
.height250 { height: 250px; }
.fht-tbody{ height: 180px !important; border-bottom: 1px solid rgb(207, 207, 207); }
#creditMemoTable {width: 100%;border: 1px solid rgb(207, 207, 207);margin: 0px 0px 0px 0px;}
#creditMemoTable thead th{ font-size:14px; border: 1px solid rgb(207, 207, 207); }
#creditMemoTable tbody td{ font-size:14px; border: 1px solid rgb(207, 207, 207); }
#creditMemoTable tbody tr:nth-child(even) td { background: #f2f2f2; }
</style>
</head>
<body>
<!-- begin shared/header -->
<form:form id="RMAForm" name="RMAForm" method="post" modelAttribute="rmaDto">
<div id="ddcolortabsline">&nbsp;</div>
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding"><!-- begin Contents --> <span id="waitMessage"> </span>
	<br/>
	<div>
	    <div class="row" style="font-size:14px;">
	    <div class="col-sm-4">
	        <div>
                <span style="font-size: 2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
                    <spring:message code="BzComposer.common.creditMemo"/>
                </span>
            </div>
            <br/>
	        <div><spring:message code="BzComposer.Item.Date"/>: March 12, 2020</div>
            <div><spring:message code="BzComposer.common.creditNo"/>: 1001</div>
	    </div>
	    <div class="col-sm-4">
	        <br/><br/><br/>
	        <div><spring:message code="BzComposer.Report.Amount"/>: 1120.00</div>
	        <div><spring:message code="BzComposer.Item.Discount"/>: 0.00</div>
	        <div><spring:message code="Bizcomposer.tax"/> 125.00</div>
	        <div><b><spring:message code="BzComposer.common.totalAmount"/>: 1245.00 $</b></div>
	    </div>
        <div class="col-sm-4 text-right">
            <div>Adam Luis</div>
            <div>abc.gmail.com</div>
            <div>+1234567890</div>
            <div>12, Lane A, Industrial area, Sector-12</div>
            <div>Washington,</div>
            <div>123456,</div>
            <div>United State,</div>
        </div>
	    </div>
	    <br/>
	</div>
	<div>
	<div class="grid_8 height250" id="table-negotiations">
	    <div>
            <span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
                <spring:message code="BzComposer.file.option.order"/>
            </span>
        </div>
		<table id="creditMemoTable" class="tabla-listados" cellpadding="0" cellspacing="0">
		<thead style="font-weight: bold;">
			<tr>
				<th><spring:message code="BzComposer.sales.Order"/></th>
				<th><spring:message code="BzComposer.sales.Item"/></th>
				<th><spring:message code="Bizcomposer.qty"/></th>
				<th><spring:message code="CategoryList.Description"/></th>
				<th><spring:message code="BzComposer.RMA.OrderDate"/></th>
				<th><spring:message code="BzComposer.RMA.SaleDate"/></th>
				<th><spring:message code="BzComposer.Price"/> ($)</th>
			</tr>
		</thead>
		<tbody>
            <tr>
                <td>1</td>
                <td>Laptop</td>
                <td>1</td>
                <td>Not working</td>
                <td>12-24-2020</td>
                <td>12-24-2020</td>
                <td align="right">800.00</td>
            </tr>
            <tr>
                <td>2</td>
                <td>LED</td>
                <td>2</td>
                <td>Not working</td>
                <td>12-24-2020</td>
                <td>12-24-2020</td>
                <td align="right">320.00</td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td align="right">Discount</td>
                <td align="right">0.00</td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td align="right">Taxes</td>
                <td align="right">125.00</td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td align="right"><b>Total</b></td>
                <td align="right"><b>1240.00</b></td>
            </tr>
		</tbody>
		</table>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>