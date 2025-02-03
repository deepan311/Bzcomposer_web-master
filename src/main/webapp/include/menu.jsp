<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div id="menubar2" style="width: 100%;display:none" class="header-section">
	<div class="bzclogo">
		<ul class="sf-menu" id="example"
			style="text-align: center; width: max-content;">
			<li>
				<a title="File" style="cursor: pointer;">
					<spring:message code="BzComposer.File" />
				</a>
				<ul>
					<li>
						<a href="Dashboard?tabid=Dashboard" style="cursor: pointer;"> <span>Dashboard</span></a>
					</li>
					<li>
						<a href="
                 File?tabid=CompanyInfo" style="cursor: pointer;">
                  <span>
                     <spring:message code="BzComposer.Companyinformation" />
                  </span>
						</a>
					</li>
					<li>
						<a href="#" onclick="SetUpprintForms();">
                     <span>
                        <spring:message code="menu.file.SetUpprintForms" />
                     </span>
						</a>
					</li>
					<li>
						<a href="#" onclick="MultiPrintInvoice();" style="cursor: pointer;">
                     <span>
                        <spring:message code="menu.file.MultiPrintInvoice" />
                     </span>
						</a>
					</li>
					<li>
						<a href="
                 SalesBord?tabid=ShowList" style="cursor: pointer;">
                  <span>
                     <spring:message code="menu.file.PrintInvoice" />
                  </span>
						</a>
					</li>
					<li>
						<a href="#" style="cursor: pointer;">
                     <span>
                        <spring:message code="menu.file.Import" />
                     </span>
						</a>
						<ul>
							<%--<li><a href="<%= session.getAttribute("path")%>/File?tabid=ImportCustomer" style="cursor: pointer;">
							<spring:message code="BzComposer.customer.Customer" /></a></li> --%>
							<li>
								<a href="
                       File?tabid=ImportCustomer"
								   style="cursor: pointer;">
                        <span>
                           <spring:message code="BzComposer.customer.Customer" />
                        </span>
								</a>
							</li>
							<%-- <li><a href="#" onclick="customerImport()"
                               style="cursor: pointer;"> <spring:message code="BzComposer.customer.Customer" />
                               </a></li> --%>
							<%-- <li><a href="<%= session.getAttribute("path")%>/File?tabid=CompanyInfo" style="cursor: pointer;">
							    <spring:message code="BzComposer.vendor.vendors" /></a></li> --%>
							<li>
								<a href="
                       File?tabid=ImportVendor"
								   style="cursor: pointer;">
                        <span>
                           <spring:message code="BzComposer.vendor.vendors" />
                        </span>
								</a>
							</li>
							<%-- <li><a href="#" onclick="vendorImport()" style="cursor: pointer;">
							<spring:message code="BzComposer.vendor.vendors" />
                               </a></li> --%>
							<%-- <li><a href="<%= session.getAttribute("path")%>/File?tabid=CompanyInfo" style="cursor: pointer;">
							    <spring:message code="NavigationTree.Items" /></a></li> --%>
							<li>
								<a href="
                       Item?tabid=UploadItem"
								   style="cursor: pointer;">
                        <span>
                           <spring:message code="NavigationTree.Items" />
                        </span>
								</a>
							</li>
							<%-- <li><a href="#" onclick="uploadItem()" style="cursor: pointer;">
							<spring:message code="NavigationTree.Items" />
                               </a></li> --%>
						</ul>
					</li>
					<li>
						<a href="#" style="cursor: pointer;">
                     <span>
                        <spring:message code="menu.file.ExportTo" />
                     </span>
						</a>
						<ul>
							<%-- <li><a href="<%= session.getAttribute("path")%>/File?tabid=CompanyInfo" style="cursor: pointer;">
							    <spring:message code="BzComposer.customer.Customer" /></a></li> --%>
							<li>
								<a href="#" onclick="exportCustomer()"
								   style="cursor: pointer;">
									<spring:message code="BzComposer.customer.Customer" />
								</a>
							</li>
							<%--<li><a href="<%= session.getAttribute("path")%>/File?tabid=CompanyInfo" style="cursor: pointer;">
							<spring:message code="BzComposer.vendor.vendors" /></a></li> --%>
							<li>
								<a href="#" onclick="exportVendor()"
								   style="cursor: pointer;">
									<spring:message code="BzComposer.vendor.vendors" />
								</a>
							</li>
							<%-- <li><a href="<%= session.getAttribute("path")%>/File?tabid=CompanyInfo" style="cursor: pointer;">
							<spring:message code="NavigationTree.Items" /></a></li> --%>
							<li>
								<a href="#" onclick="exportItem()"
								   style="cursor: pointer;">
									<spring:message code="NavigationTree.Items" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<%-- <a href="<%= session.getAttribute("path")%>/File?tabid=CompanyInfo" style="cursor: pointer;">
                           <span><spring:message code="menu.file.QBImport" /></span></a> --%>
					<li>
						<a href="#" onclick="quickBookImport()"
						   style="cursor: pointer;">
							<spring:message code="menu.file.QBImport" />
						</a>
					</li>
					</li>
					<%-- <li>
                       <a href="<%= session.getAttribute("path")%>/File?tabid=CompanyInfo" style="cursor: pointer;">
                           <span><spring:message code="menu.file.Order_Import" /></span></a>
                       </li> --%>
					<li>
						<a href="#" onclick="orderImport()" style="cursor: pointer;">
							<spring:message code="menu.file.Order_Import" />
						</a>
					</li>
					</li>
					<li>
						<a href="#" style="cursor: pointer;">
                     <span>
                        <spring:message code="menu.file.Utilities" />
                     </span>
						</a>
						<ul>
							<li>
								<a href="#ex1" rel="modal:open">
                           <span>
                              <spring:message code="menu.file.Calculator" />
                           </span>
								</a>
							</li>
							<li>
								<a href="#" onClick="openCouponDesign()">
                           <span>
                              <spring:message code="menu.file.CouponDesign" />
                           </span>
								</a>
							</li>
						</ul>
					</li>
					<%-- <li>
                       <a href="<%= session.getAttribute("path")%>/File?tabid=CompanyInfo" style="cursor: pointer;">
                       <span><spring:message code="menu.file.Module_Upload" /></span></a>
                       </li> --%>
					<li>
						<a href="#" onclick="moduleImport()"
						   style="cursor: pointer;">
							<spring:message code="menu.file.Module_Upload" />
						</a>
					</li>
					<li>
						<a href="#" style="cursor: pointer;">
                     <span>
                        <spring:message code="menu.file.Module_Download" />
                     </span>
						</a>
						<ul>
							<li>
								<a href="#" style="cursor: pointer;">
                           <span>
                              <spring:message code="menu.file.Module_Download_Shipping" />
                           </span>
								</a>
								<ul>
									<%--	<li>
                                       <a href="<%= session.getAttribute("path")%>/File?tabid=CompanyInfo" style="cursor: pointer;">
                                       <span>
                                           <spring:message code="menu.file.Module_Download_UPS" />
                                       </span>
                                       </a>
                                       </li>
                                       <li> --%>
									<li>
										<a href="http://www.nextbits.com?modulename=UPS"
										   style="cursor: pointer;">
                                 <span>
                                    <spring:message code="menu.file.Module_Download_UPS" />
                                 </span>
										</a>
									</li>
									<%-- <a href="<%= session.getAttribute("path")%>/File?tabid=CompanyInfo" style="cursor: pointer;">
                                       <span>
                                           <spring:message code="menu.file.Module_Download_USPS" />
                                       </span>
                                       </a>
                                       </li> --%>
									<li>
										<a href="http://www.nextbits.com?modulename=USPS"
										   style="cursor: pointer;">
                                 <span>
                                    <spring:message code="menu.file.Module_Download_USPS" />
                                 </span>
										</a>
									</li>
									<%-- <li>
                                       <a href="<%= session.getAttribute("path")%>/File?tabid=CompanyInfo" style="cursor: pointer;">
                                       <span>
                                           <spring:message code="menu.file.Module_Download_FEDEX" />
                                       </span>
                                       </a>
                                       </li> --%>
									<li>
										<a href="http://www.nextbits.com?modulename=FEDEX"
										   style="cursor: pointer;">
                                 <span>
                                    <spring:message code="menu.file.Module_Download_FEDEX" />
                                 </span>
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li>
						<a href="./Logout" style="cursor: pointer;">
                  <span>
                     <spring:message code="menu.file.Exit" />
                  </span>
						</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="Customer?tabid=Customer" title="Customer">
               <span>
                 <spring:message code="BzComposer.sales.Customer" />
               </span>
				</a>
				<ul>
					<li>
						<a href="Customer?tabid=NewCustomer"
						   title="Add New Customer">
                     <span>
                        <spring:message code="BzComposer.customer.AddNewCutomer" />
                     </span>
						</a>
					</li>
					<li>
						<a href="Customer?tabid=openCustomer" title="Customer">
                     <span>
                        <spring:message code="BzComposer.sales.CustomerList" />
                     </span>
						</a>
					</li>
					<li>
						<a href="Customer?tabid=PrintLabels"
						   title="Print AddressLabel">
                     <span>
                        <spring:message code="BzComposer.customer.PrintAddressLabels" />
                     </span>
						</a>
					</li>
				</ul>
			</li>
			<li>
				<a
						href="Invoice?tabid=Invoice"
						title="Sales">
					<spring:message code="BzComposer.Sales" />
				</a>
				<ul>
					<li>
						<a
								href="
                 Invoice?tabid=Invoice"
								title="">
                  <span>
                     <spring:message code="BzComposer.sales.Invoice" />
                  </span>
						</a>
					</li>
					<li>
						<a href="Estimation?tabid=Estimation" title="Estimation">
                     <span>
                        <spring:message code="BzComposer.sales.Estimation" />
                     </span>
						</a>
					</li>
					<li>
						<a href="SalesOrder?tabid=SalesOrder" title="salesorder">
                     <span>
                        <spring:message code="BzComposer.sales.SalesOrder" />
                     </span>
						</a>
					</li>
					<li>
						<a href="SalesBord?tabid=ShowList" title="invoiceboard">
                     <span>
                        <spring:message code="BzComposer.sales.InvoiceBoard" />
                     </span>
						</a>
					</li>
					<li>
						<a href="EstimationBoard?tabid=ShowList"
						   title="EstimationBoard">
                     <span>
                        <spring:message code="BzComposer.sales.EstimationBoard" />
                     </span>
						</a>
					</li>
					<li>
						<a href="SalesOrderBoard?tabid=ShowList"
						   title="salesorderboard">
                     <span>
                        <spring:message code="BzComposer.sales.SalesOrderBoard" />
                     </span>
						</a>
					</li>
					<li class="current">
						<a href="javascript: void(0)" title="RMA">
                     <span>
                        <spring:message code="BzComposer.RMA" />
                     </span>
						</a>
						<ul>
							<li class="current">
								<a href="<%= session.getAttribute("path")%>/RMA?tabid=R0M0A0">
									<spring:message code="BzComposer.RMA.CreateRma" />
								</a>
							</li>
							<li>
								<a href="<%= session.getAttribute("path")%>/RMA?tabid=R0L0S0">
									<spring:message code="BzComposer.RMA.RMAList" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="DataManager?tabid=datamanager" title="DataManager">
                            <span><spring:message code="BzComposer.sales.DataManager" /></span>
						</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="Item?tabid=Item" title="Item">
               <span>
                  <spring:message code="BzComposer.sales.Item" />
               </span>
				</a>
				<ul>

					<li>
						<!-- Earlier <a> tab, changed on 16-07-2019
                           <a href="Item?tabid=NewItem&ItemType=1">
                            -->
						<a href="Item?tabid=ShowAdd&ItemType=1&showHistoryPanel=1">
                     <span>
                        <spring:message code="BzComposer.Item.AddItem" />
                     </span>
						</a>
					</li>
					<li>
						<a href="Item?tabid=Item" title="Item List">
						  <span>
							 <spring:message code="BzComposer.Item.ItemList" />
						  </span>
						</a>
					</li>
					<li>
						<a href="PurchaseBoard?tabid=ShowReceivedItems">
							<spring:message code="BzComposer.Purchase.ReceivedItem" />
						</a>
					</li>
					<li>
						<a href="Item?tabid=AdjustInventory"
						   title="Adujust Inventory">
                     <span>
                        <spring:message code="BzComposer.Item.AdjustInventory" />
                     </span>
						</a>
					</li>

				</ul>
			</li>
			<li>
				<a
						href="PurchaseOrder?tabid=PurchaseOrder"
						title="Purchase">
            <span>
               <spring:message code="BzComposer.Purchase" />
            </span>
				</a>
				<ul>
					<li>
						<% %>
						<a href="PurchaseOrder?tabid=PurchaseOrder"
						   title="Purchase">
                  <span>
                     <spring:message code="BzComposer.purchase.PurchaseOrder" />
                  </span>
						</a>
					</li>
					<li>
						<a href="javascript: void(0)">
							<spring:message code="BzComposer.vendor.vendors" />
						</a>
						<ul>
							<li>
								<a href="Vendor?tabid=VONODO">
									<spring:message code="BzComposer.vendor.vendorslist" />
								</a>
							</li>
							<li>
								<a href="Vendor?tabid=AODOVO">
									<spring:message code="BzComposer.vendor.AddNewCutomer" />
								</a>
							</li>
							<li>
								<a href="PrintLBL?tabid=PrintLabel">
									<spring:message code="BzComposer.vendor.PrintLabels" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a
								href="
                 PurchaseBoard?tabid=ShowList"
								title="">
                  <span>
                     <spring:message code="BzComposer.purchase.Purchase.PurchaseBoard" />
                  </span>
						</a>
					<li>
						<a href="CheckPO?tabid=ShowListCheckPO">
							<spring:message code="BzComposer.purchase.PurchaseOrder.CheckPOOrders" />
						</a>
					</li>
					<%-- <li><a href="ReceivedItems?tabid=ShowReceivedItems"  >
					<spring:message code="BzComposer.Purchase.ReceivedItem" /></a></li> --%>
				</ul>
			</li>
			<!-- 	<li><a	href="javascript: void(0)" title="Accounting" ><spring:message code="BzComposer.Accounting" /></a></li> -->
			<li>
				<a href="Banking?tabid=Banking" title="Accounting"
				   style="cursor: pointer;">
					<spring:message code="BzComposer.Accounting" />
				</a>
				<ul>
					<li>
						<!-- CHANGED BY PRITESH BELOW TWO LINE 23-04-2018 --> <!-- <a  onclick="companyinfo();" style="cursor: pointer;"><span>
<spring:message code="BzComposer.Banking" /></span></a> -->
						<a
								href="Banking?tabid=Banking" title="Banking">
                     <span>
                        <spring:message code="BzComposer.Banking" />
                     </span>
						</a>
					</li>
					<li>
						<a href="BillingBoard?tabid=billingBoard"
						   title="BillingBoard">
                     <span>
                        <spring:message code="BzComposer.BillingBoard" />
                     </span>
						</a>
					</li>
					<li>
						<!-- CHANGED BY PRITESH BELOW TWO LINE 23-04-2018 --> <!-- <a  onclick="companyinfo();" style="cursor: pointer;"><span>
<spring:message code="BzComposer.AccountReceiveble" /></span></a> -->
						<a href="AccountReceiveble?tabid=AccountReceiveble"
						   title="AccountReceiveble">
                     <span>
                        <spring:message code="BzComposer.AccountReceiveble" />
                     </span>
						</a>
					</li>
					<li>
						<a href="PoPayable?tabid=popayable" title="PoPayable">
                     <span>
                        <spring:message code="BzComposer.Popayable" />
                     </span>
						</a>
					</li>
					<li>
						<a href="BillPayable?tabid=billpayable"
						   title="BillPayable">
                     <span>
                        <spring:message code="BzComposer.Billpayable" />
                     </span>
						</a>
					</li>
					<li>
						<a href="Reconsilation?tabid=reconsilation"
						   style="cursor: pointer;">
                     <span>
                        <spring:message code="BzComposer.Reconsilation" />
                     </span>
						</a>
					</li>
					<li>
						<a href="CategoryDetail?tabid=categoryDetail"
						   style="cursor: pointer;">
                     <span>
                        <spring:message code="BzComposer.CategoryDetail" />
                     </span>
						</a>
					</li>
					<li>
						<a href="CategoryManager?tabid=CategoryManager"
						   style="cursor: pointer;">
                     <span>
                        <spring:message code="BzComposer.CategoryManager" />
                     </span>
						</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="employee" title="Employee">
					<spring:message code="BzComposer.Employee" />
				</a>
				<ul>
					<li class="current">
						<a href="employee" title="Employee">
							<spring:message code="BzComposer.EmployeeList" />
						</a>
						<ul>
							<li>
								<a href="employeelist?type=hired"
								   title="View Employees">
									<spring:message code="BzComposer.Employee.HiredEmployee" />
								</a>
							</li>
							<li>
								<a href="employeelist?type=terminated"
								   title="View Employees">
									<spring:message code="BzComposer.Employee.TerminatedEmployee" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="manageemployee?act=add"
						   title="Add New Employee">
							<spring:message code="BzComposer.Employee.AddNewEmployee" />
						</a>
					</li>
					<li>
						<a href="manageemployee?act=print" title="Print Label">
							<spring:message code="BzComposer.Employee.PrintLabel" />
						</a>
					</li>
					<li>
						<a href="manageemployee?act=timesheet"
						   title="Time Sheet">
							<spring:message code="BzComposer.Employee.TimeSheet" />
						</a>
					</li>
					<%--					<li>--%>
					<%--						<a href="manageemployee?act=search"--%>
					<%--						   title="Search Employee">--%>
					<%--							<spring:message code="BzComposer.Employee.Search" />--%>
					<%--						</a>--%>
					<%--					</li>--%>
					<%--					<li>--%>
					<%--						<a href="javascript: void(0)" title="TaxResource">--%>
					<%--                     <span>--%>
					<%--                        <spring:message code="BzComposer.Employee.TaxResource" />--%>
					<%--                     </span>--%>
					<%--						</a>--%>
					<%--						<ul>--%>
					<%--							<li>--%>
					<%--								<a href="taxinfo" title="Tax Info">--%>
					<%--                           <span>--%>
					<%--                              <spring:message code="BzComposer.Employee.TaxInfo" />--%>
					<%--                           </span>--%>
					<%--								</a>--%>
					<%--								<ul>--%>
					<%--									<li>--%>
					<%--										<a href="fedTax?tabid=f0e0d0" title="Federal Tax">--%>
					<%--											<spring:message code="BzComposer.Employee.FederalTax" />--%>
					<%--										</a>--%>
					<%--									</li>--%>
					<%--									<li>--%>
					<%--										<a href="StateTax?tabid=s0t0a0" title="State Tax">--%>
					<%--											<spring:message code="BzComposer.Employee.StateTax" />--%>
					<%--										</a>--%>
					<%--									</li>--%>
					<%--								</ul>--%>
					<%--							</li>--%>
					<%--							<li>--%>
					<%--								<a href="companytax" title="Company Tax Option">--%>
					<%--                           <span>--%>
					<%--                              <spring:message code="BzComposer.Employee.CompanyTaxOption" />--%>
					<%--                           </span>--%>
					<%--								</a>--%>
					<%--								<ul>--%>
					<%--									<li>--%>
					<%--										<a href="Deduction?tabid=c1o1m1" title="Deduction">--%>
					<%--											<spring:message code="BzComposer.Employee.Deduction" />--%>
					<%--										</a>--%>
					<%--									</li>--%>
					<%--									<li>--%>
					<%--										<a href="CompanyTaxOption?tabid=t1x1o1"--%>
					<%--										   title="Option">--%>
					<%--											<spring:message code="BzComposer.Employee.Option" />--%>
					<%--										</a>--%>
					<%--									</li>--%>
					<%--								</ul>--%>
					<%--							</li>--%>
					<%--						</ul>--%>
					<%--					</li>--%>
				</ul>
			</li>
			<li>
				<a href="Reports?tabid=ReportsCenter"
				   title="Report Center">
               <span>
                  <spring:message code="BzComposer.Report.ReportTitle" />
               </span>
				</a>
				<ul>
					<li>
						<a
								href="
                 Reports?tabid=ReportsCenter"
								title="Report Center">
                  <span>
                     <spring:message code="BzComposer.Report.ReportCenter" />
                  </span>
						</a>
					</li>
					<li>
						<a href="javascript: void(0)">
                     <span>
                        <spring:message code="BzComposer.reportcenter.customerandreceivables" />
                     </span>
						</a>
						<ul>
							<li>
								<a href="#" onclick="ShowCustomerList()">
									<spring:message code="BzComposer.reportcenter.listing.customerlist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowCustomerPhoneList()">
									<spring:message code="BzComposer.reportcenter.listing.customerphonelist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowCustomerContactList()">
									<spring:message code="BzComposer.reportcenter.listing.customercontactlist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowTrancsactionbylistCustomer()">
									<spring:message code="BzComposer.reportcenter.listing.transactionlistbycustomer" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowCustomerBalSummary()">
									<spring:message code="BzComposer.reportcenter.balance.customerbalancesummary" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowCustomerBalDetail()">
									<spring:message code="BzComposer.reportcenter.balance.customerbalancedetail" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowCustomerList()">
									<spring:message code="BzComposer.reportcenter.sales.salesbycustomerdetails" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowSalesByCustomerSummary()">
									<spring:message code="BzComposer.reportcenter.sales.salesbycustomersummary" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowIncomeCustomerSummary()">
									<spring:message code="BzComposer.reportcenter.income.incomebycustomersummary" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowIncomeCustomerDetail()">
									<spring:message code="BzComposer.reportcenter.income.incomebycustomerdetail" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript: void(0)">
                     <span>
                        <spring:message code="BzComposer.Sales" />
                     </span>
						</a>
						<ul>
							<li>
								<a href="#" onclick="showSalesReport('SalesRBC');">
									<spring:message code="BzComposer.reportcenter.sales.salesreportbycustomer" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showSalesReport('SalesRID');">
									<spring:message code="BzComposer.reportcenter.sales.salesbyitem" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showSalesReport('SalesRBI');">
									<spring:message code="BzComposer.reportcenter.sales.salesreportbyitem" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showInvoiceList('AllInvoice');">
									<spring:message code="BzComposer.reportcenter.creditrefund.allcreditrefundsalesreport" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showInvoiceList('PaidInvoice');">
									<spring:message code="BzComposer.reportcenter.creditrefund.paidcreditrefund" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showInvoiceList('PaidInvoice');">
									<spring:message code="BzComposer.reportcenter.creditrefund.unpaidcreditrefund" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showInvoiceList('AllInvoice');">
									<spring:message code="BzComposer.Report.AllInvoice" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showInvoiceList('PaidInvoice');">
									<spring:message code="BzComposer.Report.PaidInvoice" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showInvoiceList('UnPaidInvoice');">
									<spring:message code="BzComposer.Report.UnPaidInvoice" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showEstimationList()">
									<spring:message code="BzComposer.reportcenter.estimation.allestimation" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript: void(0)">
                     <span>
                        <spring:message code="BzComposer.Report.ItemInventory" />
                     </span>
						</a>
						<ul>
							<li>
								<a href="#" onclick="showInventoryList();">
									<spring:message code="BzComposer.Report.InventoryList" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showReservedInventoryList();">
									<spring:message code="BzComposer.Report.ReservedInventoryList" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showItemPriceList();">
									<spring:message code="BzComposer.Report.ReservedInventoryList.ItemPriceList" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showDiscontinuedInventoryList();">
									<spring:message code="BzComposer.Report.Discontinued.InventoryList" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showDamageInventoryList();">
									<spring:message code="BzComposer.reportcenter.listing.damageinventorylist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="MissingInventoryList();">
									<spring:message code="BzComposer.reportcenter.listing.missinginventorylist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ReturnInventoryList();">
									<spring:message code="BzComposer.reportcenter.listing.returninventorylist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showDamagedInventoryList();">
									<spring:message code="BzComposer.reportcenter.listing.damagedinventorylist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showDiscontinuedInventoryList();">
									<spring:message code="BzComposer.reportcenter.listing.returnedinventorylist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showUnknownInventoryList();">
									<spring:message code="BzComposer.reportcenter.listing.unknowninventorylist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showReturnedInventoryList();">
									<spring:message code="BzComposer.reportcenter.listing.returnedinventorylist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showDailyItemSummary();">
									<spring:message code="BzComposer.reportcenter.listing.dailyitemsummary" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showDailySalesSummary();">
									<spring:message code="BzComposer.reportcenter.listing.dailysalessummary" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showInvValSummary();">
									<spring:message code="BzComposer.reportcenter.valuation.inventoryvaluationsummary" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showInvValDetail();">
									<spring:message code="BzComposer.reportcenter.valuation.inventoryvaluationdetail" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showInvOrderReport();">
									<spring:message code="BzComposer.reportcenter.inventoryorder.inventoryorderreport" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showInvStatistic();">
									<spring:message code="BzComposer.reportcenter.inventorystatistics.currentinventorystatictics" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript: void(0)">
                     <span>
                        <spring:message code="BzComposer.Report.VendorPurchase" />
                     </span>
						</a>
						<ul>
							<li>
								<a href="#" onclick="ShowvendorList();">
									<spring:message code="BzComposer.Report.VendorList" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowvendorPhoneList()">
									<spring:message code="BzComposer.Report.VendorPhoneList" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowVendorContactList();">
									<spring:message code="BzComposer.Report.VendorContactList" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowsvendorBalanceDetails();">
									<spring:message code="BzComposer.Report.vendor.BalanceDetails" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowsvendorBalanceSymmary();">
									<spring:message code="BzComposer.reportcenter.balance.vendorbalancesummary" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowsAllPurchaseorders();">
									<spring:message code="BzComposer.Report.PurchaseOrders" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowAllPurchaseBills();">
									<spring:message code="BzComposer.reportcenter.allpurchasebills" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowPaidPurchaseBills();">
									<spring:message code="BzComposer.reportcenter.paidpurchasebills" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowUnPaidPurchaseBills();">
									<spring:message code="BzComposer.reportcenter.unpaidpurchasebills" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowCancelledPurchaseRefBill();">
									<spring:message code="BzComposer.reportcenter.cancelledpurchasebillrefundlist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowVendor1099List();">
									<spring:message code="BzComposer.reportcenter.vendor1099list" />
								</a>
							</li>
							<li>
								<a href="#" onclick="Vendor1099TransactionSummary();">
									<spring:message code="BzComposer.reportcenter.vendor1099transactionsummary" />
								</a>
							</li>
							<li>
								<a href="#" onclick="vendor1099TransactionDetail();">
									<spring:message code="BzComposer.reportcenter.vendor1099transactiondetail" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript: void(0)">
                     <span>
                        <spring:message code="BzComposer.Report.Employee" />
                     </span>
						</a>
						<ul>
							<li>
								<a href="#" onclick="ShowEmployeeSalesByRep();">
									<spring:message code="BzComposer.Report.Employee.SalesByRepDetails" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowEmployeeSalesReportByRep();">
									<spring:message code="BzComposer.Report.Employee.SalesReportByRep" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript: void(0)">
                     <span>
                        <spring:message code="BzComposer.reportcenter.bankingandaccounting" />
                     </span>
						</a>
						<ul>
							<li>
								<a href="#" onclick="ShowCheckDetail();">
									<spring:message code="BzComposer.reportcenter.bankingandtransactiondetail.checkdetail" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowDepositDetail();">
									<spring:message code="BzComposer.reportcenter.bankingandtransactiondetail.depositdetail" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowBillDetail();">
									<spring:message code="BzComposer.reportcenter.bankingandtransactiondetail.billdetail" />
								</a>
							</li>
							<li>
								<a href="#" onclick="TransactionDeatail();">
									<spring:message code="BzComposer.reportcenter.bankingandtransactiondetail.transactiondeatails" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowAccountReceivableGraph();">
									<spring:message code="BzComposer.reportcenter.graph.accountreceivablegraph" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowAccountReceivable();">
									<spring:message code="BzComposer.reportcenter.account.accountreceivable" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowAccountPayable();">
									<spring:message code="BzComposer.reportcenter.accountpayable" />
								</a>
							</li>
							<li>
								<a href="#" onclick="AccountPayableGraph();">
									<spring:message code="BzComposer.reportcenter.accountpayablegraph" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript: void(0)">
                     <span>
                        <spring:message code="BzComposer.reportcenter.profitandbudget" />
                     </span>
						</a>
						<ul>
							<li>
								<a href="#" onclick="ShowProfitLoss();">
									<spring:message code="BzComposer.reportcenter.profitorloss.profitlossstandard" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowProfitLossDetail();">
									<spring:message code="BzComposer.reportcenter.profitorloss.profitlossdetail" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowProfitLossByJob();">
									<spring:message code="BzComposer.reportcenter.profitorloss.profitlossbyjob" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showProfitLossByItem();">
									<spring:message code="BzComposer.reportcenter.profitorloss.profitlossbyitem" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showBudgetOverview();">
									<spring:message code="BzComposer.reportcenter.profitorloss.profitlossbudgetoverview" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showBudgetVsActual();">
									<spring:message code="BzComposer.reportcenter.profitorloss.profitlossbudgetactual" />
								</a>
							</li>
							<li>
								<a href="#" onclick="showIncomeStatement();">
									<spring:message code="BzComposer.reportcenter.financialreport.incomestatement" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowBalSheet();">
									<spring:message code="BzComposer.reportcenter.financialreport.balancesheet" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowCashFlowForeCast();">
									<spring:message code="BzComposer.reportcenter.cashflow.cashflowforecast" />
								</a>
							</li>
							<li>
								<a href="#" onclick="IncomeExpenseGraph();">
									<spring:message code="BzComposer.reportcenter.graph.incomeandexpensegraph" />
								</a>
							</li>
							<li>
								<a href="#" onclick="Networth();">
									<spring:message code="BzComposer.reportcenter.graph.networthgraph" />
								</a>
							</li>
							<li>
								<a href="#" onclick="BudgetvsActualGraph();">
									<spring:message code="BzComposer.reportcenter.graph.budgetvsactualgraph" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript: void(0)">
                     <span>
                        <spring:message code="BzComposer.reportcenter.tax" />
                     </span>
						</a>
						<ul>
							<li>
								<a href="#" onclick="ShowSalesTaxSummary();">
									<spring:message code="BzComposer.reportcenter.tax.salestax.summary" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShowReportTaxDetail();">
									<spring:message code="BzComposer.reportcenter.tax.salestax.detail" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript: void(0)">
                     <span>
                        <spring:message code="BzComposer.reportcenter.lists" />
                     </span>
						</a>
						<ul>
							<li>
								<a href="#" onclick="ChartsofCategories();">
									<spring:message code="BzComposer.reportcenter.lists.chartsofcategories" />
								</a>
							</li>
							<li>
								<a href="#" onclick="TermList();">
									<spring:message code="BzComposer.reportcenter.lists.termlist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="SaleRepList();">
									<spring:message code="BzComposer.reportcenter.lists.salereplist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="PaymentMethodList();">
									<spring:message code="BzComposer.reportcenter.lists.paymentmethodlist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ShipViaList();">
									<spring:message code="BzComposer.reportcenter.lists.shipvialist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="TaxTypeList();">
									<spring:message code="BzComposer.reportcenter.lists.taxtypelist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="FootnoteList();">
									<spring:message code="BzComposer.reportcenter.lists.footnotelist" />
								</a>
							</li>
							<li>
								<a href="#" onclick="MessageList();">
									<spring:message code="BzComposer.reportcenter.lists.messagelist" />
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript: void(0)">
                     <span>
                        <spring:message code="BzComposer.reportcenter.esales" />
                     </span>
						</a>
						<ul>
							<li>
								<a href="#" onclick="ESales_Invoice_Detail();">
									<spring:message code="BzComposer.reportcenter.esalesinvoicedetail" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ESales_Refund_Detail();">
									<spring:message code="BzComposer.reportcenter.esalesrefunddetail" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ESales_sale_Detail();">
									<spring:message code="BzComposer.reportcenter.esalessaledetail" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ESales_Inventory_Sale_Statistics();">
									<spring:message code="BzComposer.reportcenter.esalesinventorysalestatistics" />
								</a>
							</li>
							<li>
								<a href="#" onclick="Cross_Sell_Inventory_Report();">
									<spring:message code="BzComposer.reportcenter.crosssale.crosssaledetail" />
								</a>
							</li>
							<li>
								<a href="#" onclick="ESale_Sales_Graph();">
									<spring:message code="BzComposer.reportcenter.graph.esalesalesgraph" />
								</a>
							</li>
						</ul>
					</li>
					<li>
					<li>
				</ul>
			</li>
			<!-- eSales navigation -->
			<%-- <li>
               <a href="eSalesBoard?tabid=eSalesSalesBoard" title="eSales"  ><span>
               <spring:message code="menu.eSales.eSalesSalesBoard" /></span></a>
               <ul>
               <li>
               <a href="<%= session.getAttribute("path")%>/Reports?tabid=ReportsCenter" title="eSalesBoard">
                   <span><spring:message code="menu.eSales.eSalesSalesBoardName" /></span>
               </a>

               </li>

               <li>
               <a href="<%= session.getAttribute("path")%>/Reports?tabid=ReportsCenter" title="Product_Submisson">
                   <span><spring:message code="menu.eSales.eSales_Product_Submisson" /></span>
               </a>

               </li>
               </ul>
               </li> --%>
			<!-- eSales navigation over -->
			<li>
				<a
						href="
           Configuration?tabid=config"
						title="Confuguration">
					<spring:message code="BzComposer.Confuguration" />
				</a>
			</li>
		</ul>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#menubar2").show();
	});
	var screenHeight = $(window).height()/2;
	var screenWidth = $(window).width()/2;
	var top = $(window).height() / 4;
	var left = $(window).width() / 4;
	function companyinfo()
	{
		//window.open("File?tabid=CompanyInfo",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no," );
	}
	function openCouponDesign()	//added on 08-01-2019
	{
		window.open("File?tabid=CouponDesign",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function showInvoiceList(action)
	{
		if(action == 'AllInvoice')
		{
			window.open("SalesBord?tabid=AllInvoiceList&ilist=1",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
		}
		else if (action == 'PaidInvoice')
		{
			window.open("SalesBord?tabid=PaidInvoiceList&ilist=2",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
		}
		else if (action == 'UnPaidInvoice')
		{
			window.open("SalesBord?tabid=UnPaidInvoiceList&ilist=3",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
		}
	}
	function showRefundInvoiceReport()
	{
		window.open("SalesBord?tabid=refundInvoiceReport",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function showBudgetVsActual()
	{
		//window.open("SalesBord?tabid=refundInvoiceReport",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
		window.open("Customer?tabid=BudgetVsActual",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function showBudgetOverview()
	{
		window.open("Customer?tabid=BudgetOverview",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowIncomeCustomerDetail()
	{
		window.open("Customer?tabid=IncomeCustomerSummary",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowProfitLossByJob()
	{
		alert("<bean:message key='BzComposer.common.notSupportedYet'/>");
	}
	function showSalesReport(action)
	{
		if(action == 'SalesRBC')
		{
			window.open("SalesBord?tabid=SalesRBC&ilist=1",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
		}
		else if(action == 'SalesRID')
		{
			window.open("SalesBord?tabid=SalesRID&ilist=3",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
		}
		else if(action == 'SalesRBI')
		{
			window.open("SalesBord?tabid=SalesRBI&ilist=2",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
		}
	}
	function showEstimationList()
	{
		window.open("EstimationBoard?tabid=AllEstimationList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowvendorList()
	{
		window.open("PurchaseBoard?tabid=AllVendorList",null,"scrollbars=yes,height=500,width=1250,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowvendorPhoneList()
	{
		window.open("PurchaseBoard?tabid=VendorPhoneList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowVendorContactList()
	{
		window.open("PurchaseBoard?tabid=VendorContactList",null,"scrollbars=yes,height=500,width=1000,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowsvendorBalanceDetails()
	{
		window.open("PurchaseBoard?tabid=VendorBalancedetails",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowsvendorBalanceSymmary()
	{
		window.open("PurchaseBoard?tabid=VendorBalancesymmary",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowsAllPurchaseorders()
	{
		window.open("PurchaseBoard?tabid=AllPurchaseOrderList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowAllPurchaseBills()
	{
		window.open("PurchaseBoard?tabid=AllPurchaseBillList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowPaidPurchaseBills()
	{
		window.open("PurchaseBoard?tabid=PaidPurchaseBillList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowUnPaidPurchaseBills()
	{
		window.open("PurchaseBoard?tabid=ShowUnPaidPurchaseBills",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function showInventoryList()
	{
		window.open("Item?tabid=InventoryList",null,"scrollbars=yes,height=600,width=1000,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowCancelledPurchaseRefBill()
	{
		window.open("PurchaseBoard?tabid=CancelledPurREfBill",null,"scrollbars=yes,height=600,width=1000,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowVendor1099List()
	{
		window.open("PurchaseBoard?tabid=Vendor1099List",null,"scrollbars=yes,height=600,width=1000,status=yes,toolbar=no,menubar=no,location=no" );
	}

	function Vendor1099TransactionSummary()
	{
		window.open("PurchaseBoard?tabid=vendor1099TransactionSummary",null,"scrollbars=yes,height=600,width=1000,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function vendor1099TransactionDetail()
	{
		window.open("PurchaseBoard?tabid=vendor1099TransactionDetail",null,"scrollbars=yes,height=600,width=1000,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function showReservedInventoryList()
	{
		window.open("SalesOrderBoard?tabid=ReservedInventoryList",null,"scrollbars=yes,height=600,width=1000,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowCustomerList()
	{
		window.open("Customer?tabid=CustomerList",null,"scrollbars=yes,height=600,width=1000,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowCustomerPhoneList()
	{
		window.open("Customer?tabid=CustomerPhoneList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowCustomerContactList()
	{
		window.open("Customer?tabid=CustomerContactList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowTrancsactionbylistCustomer()
	{
		window.open("Customer?tabid=CustomerTransactionList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowCustomerBalSummary()
	{
		window.open("Customer?tabid=CustomerBalanceSummary",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowCustomerBalDetail()
	{
		window.open("Customer?tabid=CustomerBalDetail",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowSalesByCustomerSummary()
	{
		window.open("Customer?tabid=SalesByCustomerSummary",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowIncomeCustomerSummary()
	{
		window.open("Customer?tabid=IncomeCustomerSummary",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function showItemPriceList()
	{
		window.open("Item?tabid=ItemPriceList",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function showDiscontinuedInventoryList()
	{
		window.open("Item?tabid=DiscontinuedList",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function showInvValSummary()
	{
		window.open("Item?tabid=InventoryValSummary",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function showInvValDetail()
	{
		window.open("Item?tabid=InvValDetail",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function showInvOrderReport()
	{
		window.open("Item?tabid=InvOrderReport",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function showInvStatistic()
	{
		window.open("Item?tabid=InvStatistic",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function ShowSalesTaxSummary()
	{
		//window.open("Item?tabid=ShowSalesTaxSummary",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
		alert("<bean:message key='BzComposer.common.notSupportedYet'/>");
	}
	function ShowReportTaxDetail()
	{
		//window.open("Item?tabid=ShowSalesTaxSummary",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
		alert("<bean:message key='BzComposer.common.notSupportedYet'/>");
	}
	function showDamagedInventoryList()
	{
		window.open("Item?tabid=DamagedInvList",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function showDamageInventoryList()
	{
		window.open("Item?tabid=showDamageInventoryList",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function showUnknownInventoryList()
	{
		window.open("Item?tabid=showUnknownInventoryList",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function showReturnedInventoryList()
	{
		window.open("Item?tabid=showReturnedInventoryList",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function showDailyItemSummary()
	{
		window.open("Item?tabid=showDailyItemSummary",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no");
	}
	function showDailySalesSummary()
	{
		window.open("Item?tabid=showDailySalesSummary",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no");
	}
	function MissingInventoryList()
	{
		window.open("Item?tabid=MissingInventoryList",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ReturnInventoryList()
	{
		window.open("Item?tabid=ReturnInventoryList",null,"scrollbars=yes,height=600,width=850,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowEmployeeSalesByRep()
	{
		window.open("SalesBord?tabid=SalesByRepDetail",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowEmployeeSalesReportByRep()
	{
		window.open("SalesBord?tabid=SalesReportByRep",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function banking()
	{
		window.open("Accounting?tabid=Banking",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowCheckDetail()
	{
		window.open("BankingAccounting?tabid=CheckDetail",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowDepositDetail()
	{
		window.open("BankingAccounting?tabid=DepositDetail",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowBillDetail()
	{
		window.open("BankingAccounting?tabid=BillDetail",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function TransactionDeatail()
	{
		//window.open("BankingAccounting?tabid=BillDetail",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
		alert("<bean:message key='BzComposer.common.notSupportedYet'/>");
	}
	function ShowAccountReceivableGraph()
	{
		window.open("BankingAccounting?tabid=ARGraph",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowAccountReceivable()
	{
		window.open("AccountReceivableAR?tabid=AccontReceivableReport",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowAccountPayable()
	{
		window.open("Customer?tabid=AccountPayable",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function AccountPayableGraph()
	{
		window.open("Customer?tabid=AccountPayableGraph",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowProfitLoss()
	{
		window.open("Category?tabid=ProfitLoss",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowProfitLossDetail()
	{
		window.open("Customer?tabid=ProfitLossDetail",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function showProfitLossByItem()
	{
		window.open("Item?tabid=ProfitLossByItem",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function showIncomeStatement()
	{
		window.open("Category?tabid=IncomeStatement",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowBalSheet()
	{
		window.open("Accounting?tabid=BalanceSheet",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowCashFlow()
	{
		window.open("ShowCashFlow?tabid=CashFlowStatement",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShowCashFlowForeCast()
	{
		//window.open("ShowCashFlowForeCast?tabid=ShowCashFlowForeCast",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
		alert("<bean:message key='BzComposer.common.notSupportedYet'/>");
	}
	function IncomeExpenseGraph()
	{
		//window.open("ShowCashFlowForeCast?tabid=ShowCashFlowForeCast",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
		window.open("BankingAccounting?tabid=IncomeExpenseGraph",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");

	}
	function Networth()
	{
		//window.open("ShowCashFlowForeCast?tabid=ShowCashFlowForeCast",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
		window.open("BankingAccounting?tabid=NetworthGraph",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");

	}
	function BudgetvsActualGraph()
	{
		//window.open("ShowCashFlowForeCast?tabid=ShowCashFlowForeCast",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
		window.open("BankingAccounting?tabid=BudgetvsActualGraph",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");

	}
	function ChartsofCategories()
	{
		window.open("ReportCenterLists?tabid=ChartsofCategories",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function TermList()
	{
		window.open("ReportCenterLists?tabid=TermList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function SaleRepList()
	{
		window.open("ReportCenterLists?tabid=SaleRepList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function PaymentMethodList()
	{
		window.open("ReportCenterLists?tabid=PaymentMethodList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ShipViaList()
	{
		window.open("ReportCenterLists?tabid=ShipViaList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function TaxTypeList()
	{
		window.open("ReportCenterLists?tabid=TaxTypeList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function FootnoteList()
	{
		window.open("ReportCenterLists?tabid=FootnoteList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function MessageList()
	{
		window.open("ReportCenterLists?tabid=MessageList",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ESales_Invoice_Detail()
	{
		window.open("ReportCenterESales?tabid=ESalesInvoiceDetail",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ESales_Refund_Detail()
	{
		window.open("ReportCenterESales?tabid=ESalesRefundDetail",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ESales_sale_Detail()
	{
		window.open("ReportCenterESales?tabid=ESalessaleDetail",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ESales_Inventory_Sale_Statistics()
	{
		window.open("ReportCenterESales?tabid=ESalesInventorySaleStatistics",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function Cross_Sell_Inventory_Report()
	{
		window.open("ReportCenterESales?tabid=CrossSellInventoryReport",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function ESale_Sales_Graph()
	{
		window.open("ReportCenterESales?tabid=ESaleSalesGraph",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	/*file menu*/
	function SetUpprintForms()
	{
		window.open("File?tabid=SetUpprintForms",null,"scrollbars=no,height=300,width=800,status=yes,toolbar=no,menubar=no,location=center");
	}
	function MultiPrintInvoice()
	{
		window.open("File?tabid=MultiPrintInvoice",null,"scrollbars=no,height=300,width=800,status=yes,toolbar=no,menubar=no,location=no");
	}
	function customerImport()
	{
		window.location("File?tabid=ImportCustomer",null,"scrollbars=no,height="+screenHeight+",width ="+screenWidth+",left = "+left+",top = "+top+",status=yes,toolbar=no,menubar=no,location=no");
	}
	function vendorImport()
	{
		window.open("File?tabid=ImportVendor",null,"scrollbars=no,height="+screenHeight+",width ="+screenWidth+",left = "+left+",top = "+top+",status=yes,toolbar=no,menubar=no,location=no");
	}
	function uploadItem()
	{
		window.open("Item?tabid=UploadItem",null,"scrollbars=no,height="+screenHeight+",width ="+screenWidth+",left = "+left+",top = "+top+",status=yes,toolbar=no,menubar=no,location=no");
	}
	function exportCustomer()
	{
		window.open("File?tabid=ExportCustomer",null,"scrollbars=no,height="+screenHeight+",width ="+screenWidth+",left = "+left+",top = "+top+",status=yes,toolbar=no,menubar=no,location=no");
	}
	function exportVendor()
	{
		window.open("File?tabid=ExportVendor",null,"scrollbars=no,height="+screenHeight+",width ="+screenWidth+",left = "+left+",top = "+top+",status=yes,toolbar=no,menubar=no,location=no");
	}
	function exportItem()
	{
		window.open("Item?tabid=ExportItem",null,"scrollbars=no,height="+screenHeight+",width ="+screenWidth+",left = "+left+",top = "+top+",status=yes,toolbar=no,menubar=no,location=no");
	}
	function quickBookImport()
	{
		window.open("File?tabid=QuickBookImport",null,"scrollbars=no,height="+screenHeight+",width ="+screenWidth+",left = "+left+",top = "+top+",status=yes,toolbar=no,menubar=no,location=no");
	}
	function orderImport()
	{
		window.open("File?tabid=OrderImport",null,"scrollbars=no,height="+screenHeight+",width ="+screenWidth+",left = "+left+",top = "+top+",status=yes,toolbar=no,menubar=no,location=no");
	}
	function moduleImport()
	{
		window.open("Module?tabid=ImportModule",null,"scrollbars=no,height="+screenHeight+",width ="+screenWidth+",left = "+left+",top = "+top+",status=yes,toolbar=no,menubar=no,location=no");
	}
	/*esales board*/
	/* function eSalesSalesBoard()
    {
        window.open("eSalesBoard?tabid=eSalesSalesBoard",null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
    } */

	/**/
</script>
<!-- Remember to include jQuery :) -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script> -->
<!-- jQuery Modal -->
<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet"
	  href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<style type="text/css">
	/* * {
    padding: 0;
    margin: 5px;
    text-align: center;
    } */
	.modal {
		display: none; /* Hidden by default */
	}
	body {
		background-color: white;
		overflow: auto;
	}
	.calculator {
		width: 350px;
		height: 320px;
		box-shadow: 0px 0px 0px 10px #666;
		border: 1px solid;
		border-radius: 2px;
		text-align: center
	}
	#display {
		width: 320px;
		height: 40px;
		text-align: right;
		border: 1px solid black;
		font-size: 20px;
		left: 2px;
		top: 2px;
		color: black;
	}
	.btnTop {
		color: white;
		background-color: black;
		font-size: 14px;
		margin: auto;
		width: 50px;
		height: 25px;
	}
	.btnNum {
		color: black;
		font-size: 20px;
		margin: auto;
		width: 50px;
		height: 25px;
	}
	.btnMath {
		color: black;
		font-size: 20px;
		margin: auto;
		width: 50px;
		height: 25px;
	}
	.btnOpps {
		color: black;
		font-size: 20px;
		margin: auto;
		width: 50px;
		height: 25px;
	}
	.modal1 {
		overflow: visible;
		height: auto;
		vertical-align: top;
	}
</style>
<div id="ex1" class="modal modal1">
	<form name="sci-calc">
		<center>
			<table class="calculator" cellspacing="0" cellpadding="1">
				<tr>
					<td colspan="5"><input id="display" name="display" value="0"
										   size="28" maxlength="25"></td>
				</tr>
				<tr>
					<td><input type="button" class="btnTop" name="btnTop"
							   value="C" onclick="this.form.display.value=  0 "></td>
					<td><input type="button" class="btnTop" name="btnTop"
							   value="<--" onclick="deleteChar(this.form.display)"></td>
					<td><input type="button" class="btnTop" name="btnTop"
							   value="="
							   onclick="if(checkNum(this.form.display.value)) { compute(this.form) }"></td>
					<td><input type="button" class="btnOpps" name="btnOpps"
							   value="&#960;"
							   onclick="addChar(this.form.display,'3.14159265359')"></td>
					<td><input type="button" class="btnMath" name="btnMath"
							   value="%" onclick=" percent(this.form.display)"></td>
				</tr>
				<tr>
					<td><input type="button" class="btnNum" name="btnNum"
							   value="7" onclick="addChar(this.form.display, '7')"></td>
					<td><input type="button" class="btnNum" name="btnNum"
							   value="8" onclick="addChar(this.form.display, '8')"></td>
					<td><input type="button" class="btnNum" name="btnNum"
							   value="9" onclick="addChar(this.form.display, '9')"></td>
					<td><input type="button" class="btnOpps" name="btnOpps"
							   value="x&#94;"
							   onclick="if(checkNum(this.form.display.value)) { exp(this.form) }"></td>
					<td><input type="button" class="btnMath" name="btnMath"
							   value="/" onclick="addChar(this.form.display, '/')"></td>
				<tr>
					<td><input type="button" class="btnNum" name="btnNum"
							   value="4" onclick="addChar(this.form.display, '4')"></td>
					<td><input type="button" class="btnNum" name="btnNum"
							   value="5" onclick="addChar(this.form.display, '5')"></td>
					<td><input type="button" class="btnNum" name="btnNum"
							   value="6" onclick="addChar(this.form.display, '6')"></td>
					<td><input type="button" class="btnOpps" name="btnOpps"
							   value="ln"
							   onclick="if(checkNum(this.form.display.value)) { ln(this.form) }"></td>
					<td><input type="button" class="btnMath" name="btnMath"
							   value="*" onclick="addChar(this.form.display, '*')"></td>
				</tr>
				<tr>
					<td><input type="button" class="btnNum" name="btnNum"
							   value="1" onclick="addChar(this.form.display, '1')"></td>
					<td><input type="button" class="btnNum" name="btnNum"
							   value="2" onclick="addChar(this.form.display, '2')"></td>
					<td><input type="button" class="btnNum" name="btnNum"
							   value="3" onclick="addChar(this.form.display, '3')"></td>
					<td><input type="button" class="btnOpps" name="btnOpps"
							   value="&radic;"
							   onclick="if(checkNum(this.form.display.value)) { sqrt(this.form) }"></td>
					<td><input type="button" class="btnMath" name="btnMath"
							   value="-" onclick="addChar(this.form.display, '-')"></td>
				</tr>
				<tr>
					<td><input type="button" class="btnMath" name="btnMath"
							   value="&#177" onclick="changeSign(this.form.display)"></td>
					<td><input type="button" class="btnNum" name="btnNum"
							   value="0" onclick="addChar(this.form.display, '0')"></td>
					<td><input type="button" class="btnMath" name="btnMath"
							   value="&#46;" onclick="addChar(this.form.display, '&#46;')"></td>
					<td><input type="button" class="btnOpps" name="btnOpps"
							   value="x&#50;"
							   onclick="if(checkNum(this.form.display.value)) { square(this.form) }"></td>
					<td><input type="button" class="btnMath" name="btnMath"
							   value="+" onclick="addChar(this.form.display, '+')"></td>
				</tr>
				<tr>
					<td><input type="button" class="btnMath" name="btnMath"
							   value="(" onclick="addChar(this.form.display, '(')"></td>
					<td><input type="button" class="btnMath" name="btnMath"
							   value=")" onclick="addChar(this.form.display,')')"></td>
					<td><input type="button" class="btnMath" name="btnMath"
							   value="cos"
							   onclick="if(checkNum(this.form.display.value)) { cos(this.form) }"></td>
					<td><input type="button" class="btnMath" name="btnMath"
							   value="sin"
							   onclick="if(checkNum(this.form.display.value)) { sin(this.form) }"></td>
					<td><input type="button" class="btnMath" name="btnMath"
							   value="tan"
							   onclick="if(checkNum(this.form.display.value)) { tan(this.form) }"></td>
				</tr>
			</table>
	</form>
	<br> <a href="#" rel="modal:close"><spring:message code="BzComposer.global.close" /></a>
	</center>
</div>
<script type="text/javascript">
	function ShowCustomerList()
	{
		window.open("Customer?tabid=CustomerList",null,"scrollbars=yes,height=600,width=1000,status=yes,toolbar=no,menubar=no,location=no" );
	}
	function addChar(input, character)
	{
		if(input.value == null || input.value == "0")
			input.value = character
		else
			input.value += character
	}
	function cos(form)
	{
		form.display.value = Math.cos(form.display.value);
	}
	function sin(form)
	{
		form.display.value = Math.sin(form.display.value);
	}
	function tan(form)
	{
		form.display.value = Math.tan(form.display.value);
	}
	function sqrt(form)
	{
		form.display.value = Math.sqrt(form.display.value);
	}
	function ln(form)
	{
		form.display.value = Math.log(form.display.value);
	}
	function exp(form)
	{
		form.display.value = Math.exp(form.display.value);
	}
	function deleteChar(input)
	{
		input.value = input.value.substring(0, input.value.length - 1)
	}
	var val = 0.0;
	function percent(input)
	{
		val = input.value;
		input.value = input.value + "%";
	}
	function changeSign(input)
	{
		if(input.value.substring(0, 1) == "-")
			input.value = input.value.substring(1, input.value.length)
		else
			input.value = "-" + input.value
	}
	function compute(form)
	{
		//if (val !== 0.0) {
		// var percent = form.display.value;
		// percent = pcent.substring(percent.indexOf("%")+1);
		// form.display.value = parseFloat(percent)/100 * val;
		//val = 0.0;
		// } else
		form.display.value = eval(form.display.value);
	}
	function square(form)
	{
		form.display.value = eval(form.display.value) * eval(form.display.value)
	}
	function checkNum(str)
	{
		for (var i = 0; i < str.length; i++)
		{
			var ch = str.charAt(i);
			if (ch < "0" || ch > "9")
			{
				if (ch != "/" && ch != "*" && ch != "+" && ch != "-" && ch != "." && ch != "(" && ch!= ")" && ch != "%")
				{
					alert("<bean:message key='BzComposer.common.invalidEntry'/>");
					return false
				}
			}
		}
		return true
	}
</script>