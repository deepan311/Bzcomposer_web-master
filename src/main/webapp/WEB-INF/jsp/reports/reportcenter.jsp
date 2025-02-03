<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ page errorPage="/include/sessionExpired.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<c:if test="${not empty AdminURL && AdminURL=='administer'}">
    <%@include file="/WEB-INF/jsp/include/menuAdmin.jsp"%>
</c:if>
<c:if test="${empty AdminURL}">
    <%@include file="/WEB-INF/jsp/include/menu.jsp"%>
</c:if>
<title><spring:message code="BzComposer.reportcentertitle" /></title>
<style type="text/css">
.selected{ background-color: rgba(5, 169, 197, 0.63); color: white; padding: 4px; cursor: pointer; }
.nonselected{ color: #666;padding: 4px;cursor: pointer; }
#reports { width:200px; }
#reports a{ text-decoration: none !important;color: #05A9C5 !important;cursor: pointer; }
#reports a:HOVER{ text-decoration:underline !important;color: #666 !important;cursor: pointer; }
div#pie { /* 	color:#05A9C5;; */
	padding: 10px 0px 20px 0px;
}
table.tabla-listados { width: 100%;border: 1px solid rgb(207, 207, 207);margin: 20px 0px 20px 0px; }
table.tabla-listados thead tr th { font-size: .7em;text-align: left;padding: 5px 10px;
    border-bottom: 1px solid rgba(5, 169, 197, 0.2);text-shadow: #999 0px 1px 1px;white-space: nowrap;
}
table.tabla-listados tbody tr td { font-size: .8em;padding: 5px 0px 5px 14px;background: #fff;vertical-align: top; }
</style>
</head>
<body onload="init();">
<!-- begin shared/header -->
<form action="Configuration.do?" enctype="MULTIPART/FORM-DATA" method="post">
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div>
	<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.reportcenter.reportcentertitle"/>
	</span>
</div>
		<div>
		<br/>
			<div id="table-negotiations">
				<table cellspacing="0"  style="width: 100%;" class="section-border">
					<tr height="450">
						<!-- left side start -->
						<td valign="top"  style="width:1%;border-right: 1px solid rgb(207, 207, 207);">
							<div id="table-negotiations" style="width:170;">
								<table class="tabla-listados" cellspacing="0" style="margin: 0px 0px 0px 0px;">
									<tr onclick="SetRow('tr0');" id="tr0">
										<td style="padding-left: 1px;">
											<div id="img01" class="nonselected"><spring:message code="BzComposer.reportcenter.customerandreceivables" /></div>
							 				<div id="img02" class="selected"><spring:message code="BzComposer.reportcenter.customerandreceivables" /></div>
										</td>
									</tr>
									<tr onclick="SetRow('tr1');" id="tr1">
										<td style="padding-left: 1px;">
											<div id="img11" class="nonselected"><spring:message code="BzComposer.reportcenter.sales" /></div>						
											<div id="img12" class="selected"><spring:message code="BzComposer.reportcenter.sales" /></div>
										</td>
									</tr>
									<tr onclick="SetRow('tr2');" id="tr2">
										<td style="padding-left: 1px;">
											<div id="img21" class="nonselected"><spring:message code="BzComposer.reportcenter.itemandinventory" /></div>						
											<div id="img22" class="selected" ><spring:message code="BzComposer.reportcenter.itemandinventory" /></div>							
											<%--<div id="img21" class="nonselected"><spring:message code="BzComposer.Report.CustomerReceivables" /></div>						 --%>
											<%--<div id="img22" class="selected" ><spring:message code="BzComposer.Report.CustomerReceivables" /></div> --%>
										</td>
									</tr>
									<tr onclick="SetRow('tr2A');" id="tr2A" style="display:none;">
                                        <td style="padding-left: 1px;">
                                            <div id="img2A1" class="nonselected">Adjust Inventory</div>
                                            <div id="img2A2" class="selected" >Adjust Inventory</div>
                                        </td>
                                    </tr>
									<tr onclick="SetRow('tr3');" id="tr3">
										<td style="padding-left: 1px;">	
						    				<div id="img31" class="nonselected"><spring:message code="BzComposer.reportcenter.vendorandpurchase" /></div>						
											<div id="img32" class="selected" ><spring:message code="BzComposer.reportcenter.vendorandpurchase" /></div>
										</td>
									</tr>
			        				<tr onclick="SetRow('tr4');" id="tr4">
										<td style="padding-left: 1px;">
											<div id="img41" class="nonselected"><spring:message code="BzComposer.reportcenter.employee" /></div>						
											<div id="img42" class="selected"><spring:message code="BzComposer.reportcenter.employee" /></div>
											<%--<div  id="img41" class="nonselected"><spring:message code="BzComposer.Report.CustomerReceivables" /></div>						 --%>
											<%--<div id="img42" class="selected"><spring:message code="BzComposer.Report.CustomerReceivables" /></div>								 --%>
						   				</td>
									</tr> 
									<!-- Banking & Accounting -->
									<tr onclick="SetRow('tr5');" id="tr5">
										<td style="padding-left: 1px;">
											<div id="img51" class="nonselected"><spring:message code="BzComposer.reportcenter.bankingandaccounting" /></div>						
											<div id="img52" class="selected"><spring:message code="BzComposer.reportcenter.bankingandaccounting" /></div>																												
											<%--<div id="img41" class="nonselected"><spring:message code="BzComposer.Report.CustomerReceivables" /></div>						 --%>
											<%--<div id="img42" class="selected"><spring:message code="BzComposer.Report.CustomerReceivables" /></div>								 --%>
						   				</td>
									</tr> 
									<!-- End of Banking & Accounting -->
									<!-- Profit & Budget Start -->
									<tr onclick="SetRow('tr6');" id="tr6">
										<td style="padding-left: 1px;">
											<div id="img61" class="nonselected"><spring:message code="BzComposer.reportcenter.profitandbudget" /></div>						
											<div id="img62" class="selected"><spring:message code="BzComposer.reportcenter.profitandbudget" /></div>
						   				</td>
									</tr> 
						<!-- End of Profit & Budget -->
						<!-- Start of Tax -->
						 <tr onclick="SetRow('tr7');" id="tr7">
							<td style="padding-left: 1px;">
								<div id="img71" class="nonselected"><spring:message code="BzComposer.reportcenter.tax" /></div>						
								<div id="img72" class="selected"><spring:message code="BzComposer.reportcenter.tax" /></div>
						   </td>
						</tr> 
						<!-- End of Tax -->
						<!-- Start of lists -->
						<tr onclick="SetRow('tr8');" id="tr8">
							<td style="padding-left: 1px;">
								<div id="img81" class="nonselected"><spring:message code="BzComposer.reportcenter.lists" /></div>						
								<div id="img82" class="selected"><spring:message code="BzComposer.reportcenter.lists" /></div>
						   </td>
						</tr> 
						<!-- End Of Lists -->
						
						<!-- Start of eSales -->
						<tr onclick="SetRow('tr15');" id="tr15">
							<td style="padding-left: 1px;">
							<div id="esalesnonsel" class="nonselected"><spring:message code="BzComposer.reportcenter.esales" /></div>						
							<div id="esalessel" class="selected"><spring:message code="BzComposer.reportcenter.esales" /></div>
						   </td>
						</tr> 
						<!-- End Of eSales -->
						
					<!--  
						<tr onclick="SetRow('tr5');" id="tr5">
							<td style="padding-left: 1px;">
							
							<div  id="img51" class="nonselected">Items</div>						
							<div id="img52" class="selected">Items</div>
								
								</td>
						</tr>
							
						<tr onclick="SetRow('tr6');" id="tr6">
							<td style="padding-left: 1px;">
							
							<div  id="img61" class="nonselected">Test</div>						
							<div id="img62" class="selected">Test</div>
								
								</td>
						</tr>
						
						<tr onclick="SetRow('tr7');" id="tr7">
							<td style="padding-left: 1px;">
							
							<div  id="img71" class="nonselected">GGGGGGGG</div>						
							<div id="img72" class="selected">GGGGGGGG</div>
								
								</td>
						</tr>
						<tr onclick="SetRow('tr8');" id="tr8">
							<td style="padding-left: 1px;">
							
							<div  id="img81" class="nonselected">HHHHHHHHH</div>						
							<div id="img82" class="selected">HHHHHHHHH</div>
								
								</td>
						</tr>
						<tr onclick="SetRow('tr9');" id="tr9">
							<td style="padding-left: 1px;">
							
							<div  id="img91" class="nonselected">IIIIIIIIIII</div>						
							<div id="img92" class="selected">IIIIIIIIII</div>
								
								</td>
						</tr>
						<tr onclick="SetRow('tr10');" id="tr10">
							<td style="padding-left: 1px;">
							
							<div  id="img101" class="nonselected">JJJJJJJJ</div>						
							<div id="img102" class="selected">JJJJJJJJ</div>
								
								</td>
						</tr>
						<tr onclick="SetRow('tr11');" id="tr11">
								<td style="padding-left: 1px;">
							
							<div  id="img111" class="nonselected">KKKKKKK</div>						
							<div id="img112" class="selected">KKKKKKK</div>
								
								</td>
						</tr>
						<tr onclick="SetRow('tr12');" id="tr12">
							<td style="padding-left: 1px;">
							
							<div  id="img121" class="nonselected">LLLLLLL</div>						
							<div id="img122" class="selected">LLLLLLL</div>
								
								</td>
						</tr>  -->
					</table>
					</div>
			</td>
	
	<!-- right side start -->
	
			<td valign="top" style="padding-top: 2%;padding-right: 4%;padding-left: 2%;">
			<!-- General(sales) panel -->

			<div id="general">
			<!-- Sale start -->
			<a><span><spring:message code="BzComposer.reportcenter.sales" /></span></a> <br/>
			 	  <table class="tabla-listados">
			<tr>
			<td id="reports"><spring:message code="BzComposer.reportcenter.sales.salesdescription" /></td>		<!-- removed </a> -->		
			</tr>					
			</table>
			
					<a><span><spring:message code="BzComposer.reportcenter.sales" /></span></a> 
					<!-- from here start to check -->
		
		 	<table class="tabla-listados" cellspacing="0">
            <tr>
					<td width="45">
					<img src="images/invoice.png" />
					</td>
					<td id="reports"><a onclick="showSalesReport('SalesRBC');" ><spring:message code="BzComposer.reportcenter.sales.salesreportbycustomer" /></a></td>
					<td><spring:message code="BzComposer.reportcenter.sales.salesreportbycustomer.description" /></td>			
			</tr>
			<tr>
					<td width="45">
					<img src="images/invoice.png" />
					</td>
					<td id="reports"><a onclick="showSalesReport('SalesRID');"><spring:message code="BzComposer.reportcenter.sales.salesbyitem" /></a></td>
					<td><spring:message code="BzComposer.reportcenter.sales.salesbyitem.description" /></td>			
			</tr>		
			<tr>
					<td width="45">
					<img src="images/invoice.png" />
					</td>
					<td id="reports"><a onclick="showSalesReport('SalesRBI');"><spring:message code="BzComposer.reportcenter.sales.salesreportbyitem" /></a></td>
					<td><spring:message code="BzComposer.reportcenter.sales.salesreportbyitem.description" /></td>			
			</tr>		
			</table>   
			<!-- Sale End  -->
		  <a><span><spring:message code="BzComposer.reportcenter.creditrefund.creditrefund" /></span></a> 
		
		 	<table class="tabla-listados" cellspacing="0">
            <tr>
					<td width="45">
					<img src="images/invoice.png" />
					</td>
					<td id="reports"><a onclick="showInvoiceList('AllInvoice');" ><spring:message code="BzComposer.reportcenter.creditrefund.allcreditrefundsalesreport" /></a></td>
					<td><spring:message code="BzComposer.reportcenter.creditrefund.allcreditrefundsalesreport.description" /></td>			
			</tr>
			<tr>
					<td width="45">
					<img src="images/invoice.png" />
					</td>
					<td id="reports"><a onclick="showInvoiceList('PaidInvoice');"><spring:message code="BzComposer.reportcenter.creditrefund.paidcreditrefund" /></a></td>
					<td><spring:message code="BzComposer.reportcenter.creditrefund.paidcreditrefund.description" /></td>			
			</tr>		
			<tr>
					<td width="45">
					<img src="images/invoice.png" />
					</td>
					<td id="reports"><a onclick="showInvoiceList('UnPaidInvoice');"><spring:message code="BzComposer.reportcenter.creditrefund.unpaidcreditrefund" /></a></td>
					<td><spring:message code="BzComposer.reportcenter.creditrefund.unpaidcreditrefund.description" /></td>			
			</tr>		
			</table>   
			 <!-- <table class="tabla-listados"><tr><td>hello</tr></table> --> 
			<a ><span><spring:message code="BzComposer.reportcenter.invoice" /></span></a>
			<br>
			<table class="tabla-listados" cellspacing="0">
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="showInvoiceList('AllInvoice');" ><spring:message code="BzComposer.reportcenter.invoice.allinvoice" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.invoice.allinvoice.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="showInvoiceList('PaidInvoice');"><spring:message code="BzComposer.reportcenter.invoice.paidinvoice" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.invoice.paidinvoice.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="showInvoiceList('UnPaidInvoice');" ><spring:message code="BzComposer.reportcenter.invoice.unpaidinvoice" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.invoice.unpaidinvoice.description" /></td>			
			</tr>
			
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="showRefundInvoiceReport();"><spring:message code="BzComposer.reportcenter.invoice.refundinvoicereport" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.invoice.refundinvoicereport.description" /></td>			
			</tr>
			
			</table>
	        <a ><span><spring:message code="BzComposer.reportcenter.estimation" /></span></a>
			<br>
			<table class="tabla-listados" cellspacing="0">
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="showEstimationList();" ><spring:message code="BzComposer.reportcenter.estimation.allestimation" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.estimation.allestimation.description" /></td>			
			</tr>					
			</table>
			
			</div>

			<!-- Item & Inventory Start-->
			<div id="nw" style="display:none;">
			  <a><span><spring:message code="BzComposer.reportcenter.inventory" /></span></a>	<br/>			  
			  <table class="tabla-listados">
			<tr>
			<td id="reports" > <spring:message code="BzComposer.reportcenter.inventory.description" /></a></td>		
			</tr>					
			</table>
			
			 <a><span><spring:message code="BzComposer.reportcenter.listing" /></span></a>
			<br>
			<table class="tabla-listados" cellspacing="0">
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width:140px;"><a onclick="showInventoryList();" ><spring:message code="BzComposer.reportcenter.listing.inventorylist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.inventorylist.description" /></td>
			</tr>
			<tr>
                <td width="45"><img src="images/invoice.png" /></td>
                <td id="reports" style="width:140px;">
                    <a onclick="showReceivedItemList();" ><spring:message code="BzComposer.reportcenter.listing.receivedItemList" /></a>
                </td>
                <td><spring:message code="BzComposer.reportcenter.listing.receivedItemList.description" /></td>
            </tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width: 140px;"><a onclick="showReservedInventoryList();" ><spring:message code="BzComposer.reportcenter.listing.reservedinventorylist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.reservedinventorylist.description" /></td>			
			</tr>	
			
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width: 140px;"><a onclick="showItemPriceList();" ><spring:message code="BzComposer.reportcenter.listing.itempricelist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.itempricelist.description" /></td>			
			</tr>
				
		    <tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width: 160px;"><a  onclick="showDiscontinuedInventoryList();"  ><spring:message code="BzComposer.reportcenter.listing.discontinuedinventorylist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.discontinuedinventorylist.description" /></td>			
			</tr>		
			<!-- Remaining From here -->
				<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width: 160px;"><a  onclick="showDamageInventoryList();"  ><spring:message code="BzComposer.reportcenter.listing.damageinventorylist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.damageinventorylist.description" /></td>			
			</tr>	
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width: 160px;"><a  onclick="MissingInventoryList();"  ><spring:message code="BzComposer.reportcenter.listing.missinginventorylist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.returninventorylist.description" /></td>			
			</tr>	
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width: 160px;"><a  onclick="ReturnInventoryList();"  ><spring:message code="BzComposer.reportcenter.listing.returninventorylist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.returninventorylist.description" /></td>			
			</tr>	
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width: 160px;"><a  onclick="showDamagedInventoryList();"  ><spring:message code="BzComposer.reportcenter.listing.damagedinventorylist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.damagedinventorylist.description" /></td>			
			</tr>	
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width: 160px;"><a  onclick="showDiscontinuedInventoryList();"  ><spring:message code="BzComposer.reportcenter.listing.returnedinventorylist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.returnedinventorylist.description" /></td>			
			</tr>	
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width: 160px;"><a  onclick="showUnknownInventoryList();"  ><spring:message code="BzComposer.reportcenter.listing.unknowninventorylist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.unknowninventorylist.description" /></td>			
			</tr>	
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width: 160px;"><a  onclick="showReturnedInventoryList();"  ><spring:message code="BzComposer.reportcenter.listing.returnedinventorylist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.returnedinventorylist.description" /></td>			
			</tr>	
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width: 160px;"><a  onclick="showDailyItemSummary();"  ><spring:message code="BzComposer.reportcenter.listing.dailyitemsummary" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.dailyitemsummary.description" /></td>			
			</tr>	
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports" style="width: 160px;"><a  onclick="showDailySalesSummary();"  ><spring:message code="BzComposer.reportcenter.listing.dailysalessummary" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.dailysalessummary.description" /></td>			
			</tr>				
			<!-- End of Remaining Here -->	
			</table>
			 <a><span><spring:message code="BzComposer.reportcenter.valuation" /></span></a>	<br/>			  
			  <table class="tabla-listados" cellspacing="0">
			    <tr>
					<td width="45">
					<img src="images/invoice.png" />
					</td>
					<td id="reports" style="width: 140px;"><a onclick="showInvValSummary();" ><spring:message code="BzComposer.reportcenter.valuation.inventoryvaluationsummary" /></a></td>
					<td><spring:message code="BzComposer.reportcenter.valuation.inventoryvaluationsummary.description" /></td>			
			   </tr> 
			   <tr>
					<td width="45">
					<img src="images/invoice.png" />
					</td>
					<td id="reports" style="width: 140px;"><a onclick="showInvValDetail();" ><spring:message code="BzComposer.reportcenter.valuation.inventoryvaluationdetail" /></a></td>
					<td><spring:message code="BzComposer.reportcenter.valuation.inventoryvaluationdetail.description" /></td>			
			   </tr> 
			     			
			  </table>
			  
			  <a><span><spring:message code="BzComposer.reportcenter.inventoryorder" /></span></a>	<br/>			  
			  <table class="tabla-listados" cellspacing="0">
			    <tr>
					<td width="45">
					<img src="images/invoice.png" />
					</td>
					<td id="reports" style="width: 140px;"><a onclick="showInvOrderReport();" ><spring:message code="BzComposer.reportcenter.inventoryorder.inventoryorderreport" /></a></td>
					<td><spring:message code="BzComposer.reportcenter.inventoryorder.inventoryorderreport.description" /></td>			
			   </tr> 
			  </table>
			  
			  <a><span><spring:message code="BzComposer.reportcenter.inventorystatistics" /></span></a>	<br/>			  
			  <table class="tabla-listados" cellspacing="0">
			    <tr>
					<td width="45">
					<img src="images/invoice.png" />
					</td>
					<td id="reports" style="width: 140px;"><a onclick="showInvStatistic();" ><spring:message code="BzComposer.reportcenter.inventorystatistics.currentinventorystatictics" /></a></td>
					<td><spring:message code="BzComposer.reportcenter.inventorystatistics.currentinventorystatictics.description" /></td>			
			   </tr> 
			  </table> 
			</div>
           <!-- Item & Inventory End-->

           <!-- Adjust-Inventory Start-->
            <div id="AdjustInventoryDiv" style="display:none;">
              <a><span>Adjust Inventory</span></a>	<br/>
              <table class="tabla-listados">
            <tr>
            <td id="reports" > Find the Adjusted Inventory details for any particular date.</a></td>
            </tr>
            </table>
             <a><span><spring:message code="BzComposer.reportcenter.listing" /></span></a>
            <br>
            <table class="tabla-listados" cellspacing="0">
            <c:forEach items="${AdjustInventoryList}" var="curObject" varStatus="loop">
            <tr>
                <td width="45"><img src="images/invoice.png" /></td>
                <td id="reports" style="width:300px;"><a onclick="showAdjustInventoryList(${curObject.id});" >${curObject.reportName}</a></td>
                <td>It will show the Adjusted Inventory details for the date: ${curObject.dateAdded}</td>
            </tr>
            </c:forEach>
            </table>
            </div>
              <!-- Adjust-Inventory End-->
			
			<!-- Purchase & Vendor -->
			<div id="purchase" style="display:none;">
			 <a ><span><spring:message code="BzComposer.reportcenter.vendorpayable" /></span></a>	<br/>			  
			  <table class="tabla-listados">
			<tr>
			<td id="reports" > <spring:message code="BzComposer.reportcenter.vendorpayable.description" /></a></td>		
			</tr>					
			</table>
				
				<a ><span><spring:message code="BzComposer.reportcenter.listing"/></span></a>	
			<br>
			<table class="tabla-listados" cellspacing="0">
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowvendorList()" ><spring:message code="BzComposer.reportcenter.listng.vendorlist"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.listng.vendorlist.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowvendorPhoneList()" ><spring:message code="BzComposer.reportcenter.listng.vendorphonelist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listng.vendorphonelist.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowVendorContactList()" ><spring:message code="BzComposer.reportcenter.listng.vendorcontactlist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listng.vendorcontactlist.description" /></td>			
			</tr>
			
		
			</table>
			 <a ><span><spring:message code="BzComposer.reportcenter.balance" /></span></a>	<br/>
			
				   <table class="tabla-listados">
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowsvendorBalanceDetails()" ><spring:message code="BzComposer.reportcenter.balance.vendorbalancedetails"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.balance.vendorbalancedetails.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowsvendorBalanceSymmary()" ><spring:message code="BzComposer.reportcenter.balance.vendorbalancesummary" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.balance.vendorbalancesummary.description" /></td>			
			</tr>

			</table>
				 <a ><span><spring:message code="BzComposer.reportcenter.purchase" /></span></a>	<br/>			  
			  <table class="tabla-listados">
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowsAllPurchaseorders()" ><spring:message code="BzComposer.reportcenter.purchase.allpurchaseorders"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.purchase.allpurchaseorders.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowAllPurchaseBills()" ><spring:message code="BzComposer.reportcenter.allpurchasebills"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.allpurchasebills.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowPaidPurchaseBills()" ><spring:message code="BzComposer.reportcenter.paidpurchasebills"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.paidpurchasebills.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowUnPaidPurchaseBills()" ><spring:message code="BzComposer.reportcenter.unpaidpurchasebills"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.unpaidpurchasebills.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowCancelledPurchaseRefBill()" ><spring:message code="BzComposer.reportcenter.cancelledpurchasebillrefundlist"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.cancelledpurchasebillrefundlist.description" /></td>			
			</tr>
			</table>
			
			<!-- Vendor 1099 -->
				 <a ><span><spring:message code="BzComposer.reportcenter.vendor1099list" /></span></a>	<br/>			  
			  <table class="tabla-listados">
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowVendor1099List()" ><spring:message code="BzComposer.reportcenter.vendor1099list"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.vendor1099.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="Vendor1099TransactionSummary()" ><spring:message code="BzComposer.reportcenter.vendor1099transactionsummary"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.vendor1099transactionsummary.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="vendor1099TransactionDetail()" ><spring:message code="BzComposer.reportcenter.vendor1099transactiondetail"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.vendor1099transactiondetail.description" /></td>			
			</tr>
			</table>
			
			
			<!--End  -->
			
			</div>

			<!-- Sales & Customer  -->
			<div id="sales" style="display:none;">	   
			 <a ><span><spring:message code="BzComposer.reportcenter.employee" /></span></a>	<br/>			  
			  <table class="tabla-listados">
			<tr>
			<td id="reports" > <spring:message code="BzComposer.reportcenter.employee.description" /></a></td>		
			</tr>					
			</table>				
				<a><span><spring:message code="BzComposer.reportcenter.employee.salesbyrepdetails"/></span></a>	
			<br>
			<table class="tabla-listados" cellspacing="0">
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowEmployeeSalesByRep()" ><spring:message code="BzComposer.reportcenter.employee.salesbyrepdetails"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.employee.salesbyrepdetails.description" /></td>			
			</tr>
			</table>
			 <a ><span><spring:message code="BzComposer.reportcenter.employee.salesreportbyrep" /></span></a>	<br/>
			
				   <table class="tabla-listados">
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowEmployeeSalesReportByRep()" ><spring:message code="BzComposer.reportcenter.employee.salesreportbyrep"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.employee.salesreportbyrep.description" /></td>			
			</tr>
			

			</table>
			</div>
			
			<!--  customer -->
			<div id="customer" style="display:none;">
			 <a><span><spring:message code="BzComposer.reportcenter.customerreceivables" /></span></a> <br/>
			 	  <table class="tabla-listados">
			<tr>
			<td id="reports" ><spring:message code="BzComposer.reportcenter.customerandreceivables.description" /></a></td>		
			</tr>					
			</table>
				
				<a><span><spring:message code="BzComposer.reportcenter.listing"/></span></a>	
			<br>
			
		
			<table class="tabla-listados" cellspacing="0">
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowCustomerList()" ><spring:message code="BzComposer.reportcenter.listing.customerlist"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.customerlist.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowCustomerPhoneList()" ><spring:message code="BzComposer.reportcenter.listing.customerphonelist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.customerphonelist.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowCustomerContactList()" ><spring:message code="BzComposer.reportcenter.listing.customercontactlist" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.customercontactlist.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowTrancsactionbylistCustomer()" ><spring:message code="BzComposer.reportcenter.listing.transactionlistbycustomer" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.listing.customercontactlist.description" /></td>			
			</tr>
			</table>
			
				<a><span><spring:message code="BzComposer.reportcenter.balance"/></span></a>
				<br>
				
				<table class="tabla-listados" cellspacing="0">
					<tr>
					<td width="45">
						<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShowCustomerBalSummary()" ><spring:message code="BzComposer.reportcenter.balance.customerbalancesummary"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.balance.customerbalancesummary.description" /></td>			
					</tr>
					<tr>
					<td width="45">
						<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShowCustomerBalDetail()" ><spring:message code="BzComposer.reportcenter.balance.customerbalancedetail"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.balance.customerbalancedetail.description" /></td>			
					</tr>
				</table>
				
				<!-- Starting sales for customer -->
						<a><span><spring:message code="BzComposer.reportcenter.sales"/></span></a>	
			<br>
			
		
			<table class="tabla-listados" cellspacing="0">
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowCustomerList()" ><spring:message code="BzComposer.reportcenter.sales.salesbycustomerdetails"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.sales.salesbycustomerdetails.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowSalesByCustomerSummary()" ><spring:message code="BzComposer.reportcenter.sales.salesbycustomersummary" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.sales.salesbycustomersummary.description" /></td>			
			</tr>
			</table>
				<!-- end of sales for customer -->
				
			<!-- Starting Income for customer -->
						<a><span><spring:message code="BzComposer.reportcenter.income"/></span></a>	
			<br>
			<table class="tabla-listados" cellspacing="0">
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowIncomeCustomerSummary()" ><spring:message code="BzComposer.reportcenter.income.incomebycustomersummary"/></a></td>
			<td><spring:message code="BzComposer.reportcenter.income.incomebycustomersummary.description" /></td>			
			</tr>
			<tr>
			<td width="45">
			<img src="images/invoice.png" />
			</td>
			<td id="reports"><a onclick="ShowIncomeCustomerDetail()" ><spring:message code="BzComposer.reportcenter.income.incomebycustomerdetail" /></a></td>
			<td><spring:message code="BzComposer.reportcenter.income.incomebycustomerdetail.description" /></td>			
			</tr>
			</table>
				<!-- end of Income for customer -->	
				
			</div>
			
			<!-- Banking & Accounting Started -->
			<div id="Banking&Accounting">
				<a><span><spring:message code="BzComposer.reportcenter.bankingreports"/></span></a>
				<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td id="BankingReports" > <spring:message code="BzComposer.reportcenter.bankingreports.description" /></a></td>		
					</tr>	
				</table>
				<a><span><spring:message code="BzComposer.reportcenter.bankingandtransactiondetail"/></span></a>
				<br>
				<table class="tabla-listados" cellspacing="0">
					<tr>
					<td width="45">
						<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShowCheckDetail()" ><spring:message code="BzComposer.reportcenter.bankingandtransactiondetail.checkdetail"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.bankingandtransactiondetail.checkdetail.description" /></td>			
					</tr>
					<tr>
					<td width="45">
						<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShowDepositDetail()" ><spring:message code="BzComposer.reportcenter.bankingandtransactiondetail.depositdetail"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.bankingandtransactiondetail.depositdetail.description" /></td>			
					</tr>
					<tr>
					<td width="45">
						<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShowBillDetail()" ><spring:message code="BzComposer.reportcenter.bankingandtransactiondetail.billdetail"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.bankingandtransactiondetail.billdetail.description" /></td>			
					</tr>
					<tr>
					<td width="45">
						<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="TransactionDeatail()" ><spring:message code="BzComposer.reportcenter.bankingandtransactiondetail.transactiondeatails"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.bankingandtransactiondetail.transactiondeatails.description" /></td>			
					</tr>
					</table>
					
					<a><span><spring:message code="BzComposer.reportcenter.graph"/></span></a>
					<br>
						<table class="tabla-listados" cellspacing="0"">
							<tr>
								<td width="45">
									<img src="images/invoice.png" />
									</td>
									<td id="reports"><a onclick="ShowAccountReceivableGraph()" ><spring:message code="BzComposer.reportcenter.graph.accountreceivablegraph"/></a></td>
									<td><spring:message code="BzComposer.reportcenter.graph.accountreceivablegraph.description" /></td>			
								</tr>
						</table>
						<a><span><spring:message code="BzComposer.reportcenter.account"/></span></a>
					<br>
						<table class="tabla-listados" cellspacing="0"">
							<tr>
								<td width="45">
									<img src="images/invoice.png" />
									</td>
									<td id="reports"><a onclick="ShowAccountReceivable()" ><spring:message code="BzComposer.reportcenter.account.accountreceivable"/></a></td>
									<td><spring:message code="BzComposer.reportcenter.account.accountreceivable.description" /></td>			
								</tr>
						</table>
						<a><span><spring:message code="BzComposer.reportcenter.accountpayable"/></span></a>
					<br>
						<table class="tabla-listados" cellspacing="0"">
							<tr>
								<td width="45">
									<img src="images/invoice.png" />
									</td>
									<td id="reports"><a onclick="ShowAccountPayable()" ><spring:message code="BzComposer.reportcenter.accountpayable"/></a></td>
									<td><spring:message code="BzComposer.reportcenter.accountpayable.description" /></td>			
								</tr>
						</table>
						<a><span><spring:message code="BzComposer.reportcenter.graph"/></span></a>
					<br>
						<table class="tabla-listados" cellspacing="0"">
							<tr>
								<td width="45">
									<img src="images/invoice.png" />
									</td>
									<td id="reports"><a onclick="AccountPayableGraph()" ><spring:message code="BzComposer.reportcenter.accountpayablegraph"/></a></td>
									<td><spring:message code="BzComposer.reportcenter.accountpayablegraph.description" /></td>			
								</tr>
						</table>
			</div>
			<!-- End of Banking & Accounting -->
            <!-- Profit & Budget Start -->
            	<div id="Profit&Budget">
            	  <a><span><spring:message code="BzComposer.reportcenter.profitorloss.profitlossreports"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td id="BankingReports" > <spring:message code="BzComposer.reportcenter.profitorloss.profitlossreports.description" /></a></td>		
					</tr>	
				</table>
				 <a><span><spring:message code="BzComposer.reportcenter.profitorloss.profitlossreports"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShowProfitLoss()" ><spring:message code="BzComposer.reportcenter.profitorloss.profitlossstandard"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.profitorloss.profitlossstandard.description" /></td>			
					</tr>
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShowProfitLossDetail()"><spring:message code="BzComposer.reportcenter.profitorloss.profitlossdetail"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.profitorloss.profitlossdetail.description" /></td>			
					</tr>
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShowProfitLossByJob()"><spring:message code="BzComposer.reportcenter.profitorloss.profitlossbyjob"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.profitorloss.profitlossbyjob.description" /></td>			
					</tr>
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="showProfitLossByItem()" ><spring:message code="BzComposer.reportcenter.profitorloss.profitlossbyitem"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.profitorloss.profitlossbyitem.description" /></td>			
					</tr>
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="showBudgetOverview();"><spring:message code="BzComposer.reportcenter.profitorloss.profitlossbudgetoverview"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.profitorloss.profitlossbudgetoverview.description" /></td>			
					</tr>
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="showBudgetVsActual();"><spring:message code="BzComposer.reportcenter.profitorloss.profitlossbudgetactual"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.profitorloss.profitlossbudgetactual.description" /></td>			
					</tr>
				</table>
				
				 <a><span><spring:message code="BzComposer.reportcenter.financialreport"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="showIncomeStatement()" ><spring:message code="BzComposer.reportcenter.financialreport.incomestatement"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.financialreport.incomestatement.description" /></td>			
					</tr>
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShowBalSheet()" ><spring:message code="BzComposer.reportcenter.financialreport.balancesheet"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.financialreport.balancesheet.description" /></td>			
					</tr>
				</table>
				<a><span><spring:message code="BzComposer.reportcenter.cashflow"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports">
						<a onclick="ShowCashFlow()">
							<spring:message code="BzComposer.reportcenter.cashflow.cashflowstatement"/>
						</a>
						</td>
						<td><spring:message code="BzComposer.reportcenter.cashflow.cashflowstatement.description" /></td>			
					</tr>
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShowCashFlowForeCast()" ><spring:message code="BzComposer.reportcenter.cashflow.cashflowforecast"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.cashflow.cashflowforecast.description" /></td>			
					</tr>
				</table>
				
				<a><span><spring:message code="BzComposer.reportcenter.graph"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="IncomeExpenseGraph()" ><spring:message code="BzComposer.reportcenter.graph.incomeandexpensegraph"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.graph.incomeandexpensegraph.description" /></td>			
					</tr>
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="Networth()" ><spring:message code="BzComposer.reportcenter.graph.networthgraph"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.graph.networthgraph.description" /></td>			
					</tr>
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="BudgetvsActualGraph()" ><spring:message code="BzComposer.reportcenter.graph.budgetvsactualgraph"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.graph.budgetvsactualgraph.description" /></td>			
					</tr>
				</table>
            	</div>
            
            <!-- End of Profit & Budget -->

			<!-- Tax Starting -->
			<div id="taxR">
            	  <a><span><spring:message code="BzComposer.reportcenter.tax"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td id="BankingReports" > <spring:message code="BzComposer.reportcenter.tax.description" /></a></td>		
					</tr>	
				</table>
				 <a><span><spring:message code="BzComposer.reportcenter.tax.salestax"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShowSalesTaxSummary()" ><spring:message code="BzComposer.reportcenter.tax.salestax.summary"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.tax.salestax.summary.description" /></td>			
					</tr>
					<tr>
					<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShowReportTaxDetail()" ><spring:message code="BzComposer.reportcenter.tax.salestax.detail"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.tax.salestax.detail.description" /></td>			
					</tr>
			 	</table>
			</div>
			<!-- End of Tax -->
			
			<!-- Start of Lists -->
			 <div id="lists">
			     <a><span><spring:message code="BzComposer.reportcenter.lists"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td id="BankingReports" > <spring:message code="BzComposer.reportcenter.lists.description" /></a></td>		
					</tr>	
				</table>
				 <a><span><spring:message code="BzComposer.reportcenter.lists"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ChartsofCategories()" ><spring:message code="BzComposer.reportcenter.lists.chartsofcategories"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.lists.chartsofcategories.description" /></td>			
					</tr>
					<tr>
					<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="TermList()" ><spring:message code="BzComposer.reportcenter.lists.termlist"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.lists.termlist.description" /></td>			
					</tr>
					<tr>
					<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="SaleRepList()" ><spring:message code="BzComposer.reportcenter.lists.salereplist"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.lists.salereplist.description" /></td>			
					</tr>
					<tr>
					<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="PaymentMethodList()" ><spring:message code="BzComposer.reportcenter.lists.paymentmethodlist"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.lists.paymentmethodlist.description" /></td>			
					</tr>
					<tr>
					<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ShipViaList()" ><spring:message code="BzComposer.reportcenter.lists.shipvialist"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.lists.shipvialist.description" /></td>			
					</tr>
					<tr>
					<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="TaxTypeList()" ><spring:message code="BzComposer.reportcenter.lists.taxtypelist"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.lists.taxtypelist.description" /></td>			
					</tr>
					<tr>
					<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="FootnoteList()" ><spring:message code="BzComposer.reportcenter.lists.footnotelist"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.lists.footnotelist.description" /></td>			
					</tr>
					<tr>
					<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="MessageList()" ><spring:message code="BzComposer.reportcenter.lists.messagelist"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.lists.messagelist.description" /></td>			
					</tr>
			 	</table>
			 </div>
			<!-- End of Lists -->
			
			
			<!-- Start of eSales -->
			 <div id="eSales">
			     <a><span><spring:message code="BzComposer.reportcenter.esales"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td id="BankingReports" > <spring:message code="BzComposer.reportcenter.esales.description" /></a></td>		
					</tr>	
				</table>
				 <a><span><spring:message code="BzComposer.reportcenter.esalesdetail"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ESales_Invoice_Detail()" ><spring:message code="BzComposer.reportcenter.esalesinvoicedetail"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.esalesinvoicedetail.description" /></td>			
					</tr>
					<tr>
					<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ESales_Refund_Detail()" ><spring:message code="BzComposer.reportcenter.esalesrefunddetail"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.esalesrefunddetail.description" /></td>			
					</tr>
			 	</table>
			 	
			 	 <a><span><spring:message code="BzComposer.reportcenter.esale.sale"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ESales_sale_Detail()" ><spring:message code="BzComposer.reportcenter.esalessaledetail"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.esalessaledetail.description" /></td>			
					</tr>
					<tr>
					<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ESales_Inventory_Sale_Statistics()" ><spring:message code="BzComposer.reportcenter.esalesinventorysalestatistics"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.esalesinventorysalestatistics.description" /></td>			
					</tr>
			 	</table>
			 	
			 	 <a><span><spring:message code="BzComposer.reportcenter.crosssale"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="Cross_Sell_Inventory_Report()" ><spring:message code="BzComposer.reportcenter.crosssale.crosssaledetail"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.crosssale.crosssaledetail.description" /></td>			
					</tr>
			 	</table>
			 	
			 	 <a><span><spring:message code="BzComposer.reportcenter.graph"/></span></a>
					<br>
				<table class="tabla-listados" cellspacing="0"">
					<tr>
						<td width="45">
							<img src="images/invoice.png" />
						</td>
						<td id="reports"><a onclick="ESale_Sales_Graph()" ><spring:message code="BzComposer.reportcenter.graph.esalesalesgraph"/></a></td>
						<td><spring:message code="BzComposer.reportcenter.graph.esalesalesgraph.description"/></td>			
					</tr>
			 	</table>
			 	
			 </div>
			<!-- End of eSales -->
			
			<!--  Inventory -->
			<div id="inventory">
			Five Content
			</div>

			<!-- Employee -->

			<div id="employee" >
			six content
			</div>

			<!-- Tax -->
			<div id="tax">
			Seven Content
			</div>

			<!--  Reminders -->
			<div id="reminder" >
			Nine Content
			</div>

			<!--  Finance charges -->
			<div id="finance">
			Ten Content
			</div>

			<!-- SMTP Setup -->
			<div id="smtp" >
			ELE Content
			</div>

			<!-- Performance -->
			<div id="perfomance">
			Twe Content
			</div>

			<!-- Manage Service Type -->
			<div id="servicetype" >
			Thit Content
			</div>
			</td>
		</tr>
	</table>
	</div>
	</div>
	</div></div></div></div></div>
</form>
<%@ include file="../include/footer.jsp"%>

</body>
</html>

<script type="text/javascript">


// function showAllInvoiceList(){
// 	window.open("SalesBord.do?tabid=AllInvoiceList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );

	
// }

	function SetRow(rid){
		debugger;
		setTableVisible(rid);
	}	
	function setTableVisible(rid){
		if(rid=="tr0"){
			debugger;
			document.getElementById('customer').style.display='block';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			document.getElementById('Banking&Accounting').style.display='none';
			document.getElementById('Profit&Budget').style.display='none';
			document.getElementById('taxR').style.display='none';
			document.getElementById('lists').style.display='none';
			document.getElementById('eSales').style.display='none';
			
			
			document.getElementById('img01').style.display='none';
			document.getElementById('img02').style.display='block';
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/* document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block'; */
			/* document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block'; */
			/* document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block'; */
			debugger;
			document.getElementById('esalesnonsel').style.display='block';
			document.getElementById('esalessel').style.display='none';
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr1"){
			debugger;
			document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='block';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			document.getElementById('Banking&Accounting').style.display='none';
			document.getElementById('Profit&Budget').style.display='none';
			document.getElementById('taxR').style.display='none';
			document.getElementById('lists').style.display='none';
			document.getElementById('eSales').style.display='none';
			
			document.getElementById('img01').style.display='block';
			document.getElementById('img02').style.display='none';
			document.getElementById('img11').style.display='none';
			document.getElementById('img12').style.display='block';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img31').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/* document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block'; */

			document.getElementById('esalesnonsel').style.display='block';
			document.getElementById('esalessel').style.display='none';
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr2"){
			document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='block';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			document.getElementById('Banking&Accounting').style.display='none';
			document.getElementById('Profit&Budget').style.display='none';
			document.getElementById('taxR').style.display='none';
			document.getElementById('lists').style.display='none';
			document.getElementById('eSales').style.display='none';
			
			
			document.getElementById('esalesnonsel').style.display='block';
			document.getElementById('esalessel').style.display='none';
			document.getElementById('img01').style.display='block';
			document.getElementById('img02').style.display='none';
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='block';
			document.getElementById('img21').style.display='none';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/* document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block'; */
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr3"){
			document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='block';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			document.getElementById('Banking&Accounting').style.display='none';
			document.getElementById('Profit&Budget').style.display='none';
			document.getElementById('taxR').style.display='none';
			document.getElementById('lists').style.display='none';
			document.getElementById('eSales').style.display='none';
			
			document.getElementById('esalesnonsel').style.display='block';
			document.getElementById('esalessel').style.display='none';
			document.getElementById('img01').style.display='block';
			document.getElementById('img02').style.display='none';
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img22').style.display='none';
			document.getElementById('img31').style.display='none';
			document.getElementById('img32').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/* document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block'; 
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block';*/
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr4"){
			document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='block';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			document.getElementById('Banking&Accounting').style.display='none';
			document.getElementById('Profit&Budget').style.display='none';
			document.getElementById('taxR').style.display='none';
			document.getElementById('lists').style.display='none';
			document.getElementById('eSales').style.display='none';
            document.getElementById('esalesnonsel').style.display='block';
            document.getElementById('esalessel').style.display='none';

			document.getElementById('img01').style.display='block';
			document.getElementById('img02').style.display='none';
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='block';
			document.getElementById('img41').style.display='none';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/* document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block';*/
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		/* else if(rid=="tr5"){
			document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='block';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			
			document.getElementById('img01').style.display='block';
			document.getElementById('img02').style.display='none';
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='block';
			document.getElementById('img51').style.display='none';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block';
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		} */
		else if(rid=="tr5"){
			debugger;
			document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			document.getElementById('Banking&Accounting').style.display='block';
			document.getElementById('Profit&Budget').style.display='none';
			document.getElementById('taxR').style.display='none';
			document.getElementById('lists').style.display='none';
			document.getElementById('eSales').style.display='none';
            document.getElementById('esalesnonsel').style.display='block';
            document.getElementById('esalessel').style.display='none';
			
			document.getElementById('esalesnonsel').style.display='block';
			document.getElementById('esalessel').style.display='none';
			document.getElementById('img01').style.display='block';
			document.getElementById('img02').style.display='none';
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='block';
			document.getElementById('img51').style.display='none';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/* document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block'; 
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block';*/
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr6"){
			/* document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='block';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			
			document.getElementById('img01').style.display='block';
			document.getElementById('img02').style.display='none';
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='block';
			document.getElementById('img61').style.display='none';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block'; */
			
			document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			document.getElementById('Banking&Accounting').style.display='none';
			document.getElementById('Profit&Budget').style.display='block';
			document.getElementById('taxR').style.display='none';
			document.getElementById('lists').style.display='none';
			document.getElementById('eSales').style.display='none';
            document.getElementById('esalesnonsel').style.display='block';
            document.getElementById('esalessel').style.display='none';
			
			document.getElementById('img01').style.display='block';
			document.getElementById('img02').style.display='none';
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='block';
			document.getElementById('img61').style.display='none';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/* document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block';*/
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr7"){
			/* document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='block';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='block';
			document.getElementById('img71').style.display='none';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block'; */
			
			document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			document.getElementById('Banking&Accounting').style.display='none';
			document.getElementById('Profit&Budget').style.display='none';
			document.getElementById('taxR').style.display='block';
			document.getElementById('lists').style.display='none';
			document.getElementById('eSales').style.display='none';
            document.getElementById('esalesnonsel').style.display='block';
            document.getElementById('esalessel').style.display='none';
			
			document.getElementById('img01').style.display='block';
			document.getElementById('img02').style.display='none';
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='block';
			document.getElementById('img71').style.display='none';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/* document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block'; 
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block';*/
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr8"){
			/* document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='block';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='block';
			document.getElementById('img81').style.display='none';
			document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block'; */
			
			document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			document.getElementById('Banking&Accounting').style.display='none';
			document.getElementById('Profit&Budget').style.display='none';
			document.getElementById('taxR').style.display='none';
			document.getElementById('eSales').style.display='none';
			document.getElementById('lists').style.display='block';
			

			document.getElementById('esalesnonsel').style.display='block';
			document.getElementById('esalessel').style.display='none';
			document.getElementById('img01').style.display='block';
			document.getElementById('img02').style.display='none';
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='block';
			document.getElementById('img81').style.display='none';
			/* document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block';*/
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr9"){
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='block';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/* document.getElementById('img92').style.display='block';
			document.getElementById('img91').style.display='none'; 
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block';*/
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr10"){
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='block';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/* document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='block';
			document.getElementById('img101').style.display='none';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block';*/
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr11"){
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='block';
			document.getElementById('servicetype').style.display='none';
			
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/* document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='block';
			document.getElementById('img111').style.display='none';
			document.getElementById('img122').style.display='none';
			document.getElementById('img121').style.display='block';*/
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr12"){
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='block';
			
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/*document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='block';
			document.getElementById('img121').style.display='none';*/
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr15"){
			document.getElementById('customer').style.display='none';
			document.getElementById('general').style.display='none';
			document.getElementById('nw').style.display='none';
			document.getElementById('sales').style.display='none';
			document.getElementById('purchase').style.display='none';
			document.getElementById('inventory').style.display='none';
			document.getElementById('employee').style.display='none';
			document.getElementById('tax').style.display='none';
			document.getElementById('reminder').style.display='none';
			document.getElementById('finance').style.display='none';
			document.getElementById('smtp').style.display='none';
			document.getElementById('perfomance').style.display='none';
			document.getElementById('servicetype').style.display='none';
			document.getElementById('Banking&Accounting').style.display='none';
			document.getElementById('Profit&Budget').style.display='none';
			document.getElementById('taxR').style.display='none';
			document.getElementById('lists').style.display='none';
			document.getElementById('eSales').style.display='block';
			
			
			document.getElementById('img01').style.display='block';
			document.getElementById('img02').style.display='none';
			document.getElementById('img11').style.display='block';
			document.getElementById('img12').style.display='none';
			document.getElementById('img22').style.display='none';
			document.getElementById('img21').style.display='block';
			document.getElementById('img32').style.display='none';
			document.getElementById('img31').style.display='block';
			document.getElementById('img42').style.display='none';
			document.getElementById('img41').style.display='block';
			document.getElementById('img52').style.display='none';
			document.getElementById('img51').style.display='block';
			document.getElementById('img62').style.display='none';
			document.getElementById('img61').style.display='block';
			document.getElementById('img72').style.display='none';
			document.getElementById('img71').style.display='block';
			document.getElementById('img82').style.display='none';
			document.getElementById('img81').style.display='block';
			/* document.getElementById('img92').style.display='none';
			document.getElementById('img91').style.display='block';
			document.getElementById('img102').style.display='none';
			document.getElementById('img101').style.display='block';
			document.getElementById('img112').style.display='none';
			document.getElementById('img111').style.display='block';
			document.getElementById('img122').style.display='block';
			document.getElementById('img121').style.display='none'; */
			document.getElementById('esalesnonsel').style.display='none';
			document.getElementById('esalessel').style.display='block';
			document.getElementById('img2A1').style.display='block';
            document.getElementById('img2A2').style.display='none';
            document.getElementById('AdjustInventoryDiv').style.display='none';
		}
		else if(rid=="tr2A"){
            document.getElementById('customer').style.display='none';
            document.getElementById('general').style.display='none';
            document.getElementById('nw').style.display='none';
            document.getElementById('sales').style.display='none';
            document.getElementById('purchase').style.display='none';
            document.getElementById('inventory').style.display='none';
            document.getElementById('employee').style.display='none';
            document.getElementById('tax').style.display='none';
            document.getElementById('reminder').style.display='none';
            document.getElementById('finance').style.display='none';
            document.getElementById('smtp').style.display='none';
            document.getElementById('perfomance').style.display='none';
            document.getElementById('servicetype').style.display='none';
            document.getElementById('Banking&Accounting').style.display='none';
            document.getElementById('Profit&Budget').style.display='none';
            document.getElementById('taxR').style.display='none';
            document.getElementById('lists').style.display='none';
            document.getElementById('eSales').style.display='none';

            document.getElementById('esalesnonsel').style.display='block';
            document.getElementById('esalessel').style.display='none';
            document.getElementById('img01').style.display='block';
            document.getElementById('img02').style.display='none';
            document.getElementById('img11').style.display='block';
            document.getElementById('img12').style.display='none';
            document.getElementById('img21').style.display='block';
            document.getElementById('img22').style.display='none';
            document.getElementById('img32').style.display='none';
            document.getElementById('img31').style.display='block';
            document.getElementById('img42').style.display='none';
            document.getElementById('img41').style.display='block';
            document.getElementById('img52').style.display='none';
            document.getElementById('img51').style.display='block';
            document.getElementById('img62').style.display='none';
            document.getElementById('img61').style.display='block';
            document.getElementById('img72').style.display='none';
            document.getElementById('img71').style.display='block';
            document.getElementById('img82').style.display='none';
            document.getElementById('img81').style.display='block';
            document.getElementById('img2A1').style.display='none';
            document.getElementById('img2A2').style.display='block';
            document.getElementById('AdjustInventoryDiv').style.display='block';
        }
	}
	
function init()
{
	debugger;
	SetRow('tr0');
}
</script>