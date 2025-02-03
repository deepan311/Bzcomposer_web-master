<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="table-negotiations" style="width: 180px;padding: 0; overflow-x:auto;max-height: 850px; position: relative;">
	<table class="tabla-listados" cellspacing="0" style="border: 0; margin: 0;">
		<thead style="position: top;">
			<tr>
				<td style="padding-left: 15px;">
					<%-- <form:button styleClass="closebtn" onclick="closeNav()" style="width: 130px;"> &times; </form:button> --%>
					<%-- <form:button id="togglebtn" onclick="toggleFunction()" style="width: 130px;"> + </form:button> --%>
				</td>
			</tr>
		</thead>
		<tbody id="divtoggle">
		<tr id="tr2">
			<td>
				<c:choose>
                <c:when test="${sessionScope.configActiveTab=='generalTab'}">
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config&&tab=tr2">
                        <img id="img4" src='ConfigurationImages/GeneralNewActive.png' />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config&&tab=tr2">
                        <img id="img3" src='ConfigurationImages/GeneralNew.png' />
                    </a>
                </c:otherwise>
                </c:choose>
			</td>
		</tr>
		<tr id="tr5">
            <td>
                <c:choose>
                <c:when test="${sessionScope.configActiveTab=='moduleTab'}">
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=configModule&tab=tr5">
                        <img id="img4A" src='ConfigurationImages/moduleActive.png' />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=configModule&tab=tr5">
                        <img id="img4B" src='ConfigurationImages/module.png' />
                    </a>
                </c:otherwise>
                </c:choose>
            </td>
        </tr>
		<tr id="tr28">
			<td>
				<c:choose>
                <c:when test="${sessionScope.configActiveTab=='dataManagerTab'}">
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config28&&tab=tr28">
                        <img id="img281" src='ConfigurationImages/data_managerY.png' />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config28&&tab=tr28">
                        <img id="img28" src='ConfigurationImages/data_manager.png' />
                    </a>
                </c:otherwise>
                </c:choose>
			</td>
		</tr>
		<tr id="tr6">
			<td>
				<c:choose>
                <c:when test="${sessionScope.configActiveTab=='customerInvoiceTab'}">
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config6&&tab=tr6">
                        <img id="img12" src='ConfigurationImages/CustomerInvoiceActive.png' />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config6&&tab=tr6">
                        <img id="img11" src='ConfigurationImages/CustomerInvoice.png' />
                    </a>
                </c:otherwise>
                </c:choose>
			</td>
		</tr>
		<tr id="tr10">
			<td>
				 <c:choose>
                 <c:when test="${sessionScope.configActiveTab=='vendorPurchaseOrderTab'}">
                     <a href="${pageContext.request.contextPath}/Configuration?tabid=config10&&tab=tr10">
                         <img id="img20" src='ConfigurationImages/VendorPurchaseOrderActive.png' />
                     </a>
                 </c:when>
                 <c:otherwise>
                     <a href="${pageContext.request.contextPath}/Configuration?tabid=config10&&tab=tr10">
                        <img id="img19" src='ConfigurationImages/VendorPurchaseOrder.png' />
                     </a>
                 </c:otherwise>
                 </c:choose>
			 </td>
		</tr>
		<tr id="tr3">
            <td>
                <c:choose>
                <c:when test="${sessionScope.configActiveTab=='accountPaymentTab'}">
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config03&&tab=tr3">
                        <img id="img6" src='ConfigurationImages/AccountPaymentActive.png' />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config03&&tab=tr3">
                        <img id="img5" src='ConfigurationImages/AccountPayment.png' />
                    </a>
                </c:otherwise>
                </c:choose>
            </td>
        </tr>
		<tr id="tr15">
			<td>
				<c:choose>
                <c:when test="${sessionScope.configActiveTab=='shippingTab'}">
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config15&&tab=tr15">
                        <img id="img30" src='ConfigurationImages/ShippingNewActive.png' />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config15&&tab=tr15">
                        <img id="img29" src='ConfigurationImages/ShippingNew.png' />
                    </a>
                </c:otherwise>
                </c:choose>
			</td>
		</tr>
		<tr id="tr9">
			<td>
				<c:choose>
                <c:when test="${sessionScope.configActiveTab=='customizationTab'}">
                    <a href="${pageContext.request.contextPath}/formCustomization?tabid=config9&&tab=tr9">
                        <img id="img18" src='ConfigurationImages/FormCustomizationActive.png' />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/formCustomization?tabid=config9&&tab=tr9">
                        <img id="img17" src='ConfigurationImages/FormCustomization.png' />
                    </a>
                </c:otherwise>
                </c:choose>
			</td>
		</tr>
		<tr id="tr11">
			<!-- <td>
				 <c:choose>
                 <c:when test="${sessionScope.configActiveTab=='employeeTab'}">
                     <a href="${pageContext.request.contextPath}/Configuration?tabid=config11&&tab=tr11">
                        <img id="img22" src='ConfigurationImages/EmployeeActive.png' />
                     </a>
                 </c:when>
                 <c:otherwise>
                     <a href="${pageContext.request.contextPath}/Configuration?tabid=config11&&tab=tr11">
                        <img id="img21"src='ConfigurationImages/Employee.png' />
                    </a>
                 </c:otherwise>
                 </c:choose>
			 </td> -->
		</tr>
		<tr id="tr12">
			<!-- <td>
				<c:choose>
                <c:when test="${sessionScope.configActiveTab=='taxTab'}">
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config12&&tab=tr12">
                        <img id="img24" src='ConfigurationImages/TaxActive.png' />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config12&&tab=tr12">
                        <img id="img23" src='ConfigurationImages/Tax.png' />
                    </a>
                </c:otherwise>
                </c:choose>
			</td> -->
		</tr>
		<tr id="tr4">
			<!-- <td>
				<c:choose>
                <c:when test="${sessionScope.configActiveTab=='securityTab'}">
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config04&&tab=tr4">
                        <img id="img8" src='ConfigurationImages/SecurityActive.png' />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config04&&tab=tr4">
                        <img id="img7" src='ConfigurationImages/Security.png' />
                    </a>
                </c:otherwise>
                </c:choose>
			</td> -->
		</tr>
		<tr id="tr21">
			<td>
				<c:choose>
                <c:when test="${sessionScope.configActiveTab=='paymentGatewayTab'}">
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config21&&tab=tr21">
                        <img id="img42" src='ConfigurationImages/PaymentGatewaysActive.png' />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config21&&tab=tr21">
                        <img id="img41" src='ConfigurationImages/PaymentGateways.png' />
                    </a>
                </c:otherwise>
                </c:choose>
			</td>
		</tr>
		<tr id="tr22">
            <td>
                <c:choose>
                <c:when test="${sessionScope.configActiveTab=='deviceManagerTab'}">
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config22&&tab=tr22">
                        <img id="img40" src='ConfigurationImages/printer-new-active.png' />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config22&&tab=tr22">
                        <img id="img39" src='ConfigurationImages/printer-new.png' />
                    </a>
                </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr id="tr23">
            <td>
                <c:choose>
                <c:when test="${sessionScope.configActiveTab=='membershipTab'}">
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config29&tab=tr23">
                        <img id="img40" src='ConfigurationImages/MembershipActive.png' />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/Configuration?tabid=config29&tab=tr23">
                        <img id="img39" src='ConfigurationImages/Membership.png' />
                    </a>
                </c:otherwise>
                </c:choose>
            </td>
        </tr>
		</tbody>
	</table>
</div>

<script type="text/javascript">
	 function EnableDisableFields(){
		/* if(document.configurationForm.mailAuth.checked==true){
			document.configurationForm.mailUserName.disabled=false;
			document.configurationForm.mailPassword.disabled=false;
		}
		else{
			document.configurationForm.mailUserName.disabled=true;
			document.configurationForm.mailPassword.disabled=true;
		} */
	}

	function SetLabelName(lblid){
		size = document.getElementById('lblsize').value;
		for(cnt=0;cnt<size;cnt++){
			lid = document.getElementById(cnt+'lid').value;
			if(lblid == lid){
				document.configurationForm.labelName.value =  document.getElementById(cnt+'lname').value;
				break;
			}
		}
	}

	function init(){
		debugger;
		var t = <%= request.getParameter("tab") %>
		var tabId = <%= request.getAttribute("tab")%>;
		if(t){
			SetRow1(t);
			setLogo();
			<c:if test="${not empty EmpState}">
				refreshItemsNow(document.configurationForm.empCountryID.value,'${EmpState}');
			</c:if>
			EnableDisableFields();
		}
		if(tabId){
			SetRow(tabId);
			setLogo();
			/* <c:if test="${not empty EmpState}">
				refreshItemsNow(document.configurationForm.empCountryID.value,'${EmpState}');
			</c:if> */
			EnableDisableFields();
		}
		else
		{
			SetRow('tr1');
			setLogo();
			<c:if test="${not empty EmpState}">
				refreshItemsNow(document.configurationForm.empCountryID.value,'${EmpState}');
			</c:if>
			EnableDisableFields();
		}
	}

	function SetRow(rid){
		setTableVisible(rid);
	}

	function SetRow1(t)
	{
		var t1 = '<%= request.getParameter("tab") %>';
		setTableVisible1(t1);
	}

	function setTableVisible(rid)
	{
		if(rid=="tr1"){
			document.getElementById('general').style.display='block';
		}
		else if(rid=="tr2"){
			document.getElementById('general').style.display='block';
		}
		else if(rid=="tr3"){
			document.getElementById('accountPayment').style.display='block';
		}
		else if(rid=="tr4"){
			document.getElementById('nw').style.display='block';
		}
		else if(rid=="tr5"){
            document.getElementById('moduleMenu').style.display='block';
        }
		else if(rid=="tr28") {
			document.getElementById("datamanager").style.display='block';
		}
		else if(rid=="tr6"){
			document.getElementById("customerInvoice").style.display='block';
		}
		else if(rid=="tr8"){
			document.getElementById('inventory').style.display='block';
		}
		else if(rid=="tr9"){
			document.getElementById('formCustomization').style.display='block';
		}
		else if(rid=="tr10"){
			document.getElementById('purchase').style.display='block';
		}
		else if(rid=="tr11"){
			document.getElementById('employee').style.display='block';
		}
		else if(rid=="tr12"){
			document.getElementById('tax').style.display='block';
		}
		/*else if(rid=="tr13"){
			document.getElementById('reminder').style.display='block';
		}
		else if(rid=="tr14"){
			document.getElementById('emailSetUp').style.display='block';
		}*/
		else if(rid=="tr15")
		{
			document.getElementById('shipping').style.display='block';
		}
		/*else if(rid=="tr16"){
			document.getElementById('rma').style.display='block';
		}
		else if(rid=="tr17"){
			document.getElementById('sales').style.display='block';
		}
		else if(rid=="tr18"){
			document.getElementById('Payment&Received-Options').style.display='block';
		}
		else if(rid=="tr19"){
			document.getElementById('MySQLConfiguration').style.display='block';
		}*/
		else if(rid=="tr20"){
			document.getElementById('deviceManager').style.display='block';
		}
		else if(rid=="tr21"){
			document.getElementById('paymentGateway').style.display='block';
		}
		else if(rid=="tr22"){
			document.getElementById('printerSetup').style.display='block';
		}
		else if(rid=="tr23"){
			document.getElementById('membership').style.display='block';
		}
		/*else if(rid=="tr24"){
			document.getElementById('smtp').style.display='block';
		}
		else if(rid=="tr25"){
			document.getElementById('perfomance').style.display='block';
		}
		else if(rid=="tr26"){
			document.getElementById('servicetype').style.display='block';
		}
		else if(rid=="tr27"){
			document.getElementById('dashboard').style.display='block';
		}*/
	}

	function setTableVisible1(t1)
	{
		if(t1=="tr1"){
			document.getElementById('general').style.display='block';
		}
		else if(t1=="tr2"){
			document.getElementById('general').style.display='block';
		}
		else if(t1=="tr3"){
			document.getElementById('accountPayment').style.display='block';
		}
		else if(t1=="tr4"){
			document.getElementById('nw').style.display='block';
		}
		else if(t1=="tr5"){
			document.getElementById('moduleMenu').style.display='block';
		}
		else if(t1=="tr28") {
			document.getElementById("datamanager").style.display='block';
		}
		else if(t1=="tr6"){
			document.getElementById("customerInvoice").style.display='block';
		}
		/*else if(t1=="tr7"){
			document.getElementById('estimation').style.display='block';
		}*/
		else if(t1=="tr8"){
			document.getElementById('inventory').style.display='block';
		}
		else if(t1=="tr9"){
			document.getElementById('formCustomization').style.display='block';
		}
		else if(t1=="tr10"){
			document.getElementById('purchase').style.display='block';
		}
		else if(t1=="tr11"){
			document.getElementById('employee').style.display='block';
		}
		else if(t1=="tr12"){
			document.getElementById('tax').style.display='block';
		}
		/*else if(t1=="tr13"){
			document.getElementById('reminder').style.display='block';
		}
		else if(t1=="tr14"){
			document.getElementById('emailSetUp').style.display='block';
		}*/
		else if(t1=="tr15"){
			document.getElementById('shipping').style.display='block';
		}
		/*else if(t1=="tr16"){
			document.getElementById('rma').style.display='block';
		}
		else if(t1=="tr17"){
			document.getElementById('sales').style.display='block';
		}
		else if(t1=="tr18"){
			document.getElementById('Payment&Received-Options').style.display='block';
		}
		else if(t1=="tr19"){
			document.getElementById('MySQLConfiguration').style.display='block';
		}*/
		else if(t1=="tr20"){
			document.getElementById('deviceManager').style.display='block';
		}
		else if(t1=="tr21"){
			document.getElementById('paymentGateway').style.display='block';
		}
		else if(t1=="tr22"){
			document.getElementById('printerSetup').style.display='block';
		}
		else if(t1=="tr23"){
			document.getElementById('membership').style.display='block';
		}
		/*else if(t1=="tr24"){
			document.getElementById('smtp').style.display='block';
		}
		else if(t1=="tr25"){
			document.getElementById('perfomance').style.display='block';
		}
		else if(t1=="tr26"){
			document.getElementById('servicetype').style.display='block';
		}
		else if(t1=="tr27"){
			document.getElementById('dashboard').style.display='block';
		}*/
	}

	function SetRowColor(rid)
	{
		for(i=1;i<=12;i++)
		{
			var row1=document.getElementById("tr"+i);
			if(row1.className == "draft")
				row1.className = "draft";
			else
				row1.style.background  = '#FFFFFF';
		}
		var rd=document.getElementById(rid);
		rd.style.background = '#8798DE';
	}

	function SetRowColor(t)
	{
		for(i=1;i<=12;i++)
		{
			var row1=document.getElementById(i);
			if(row1.className == "draft")
				row1.className = "draft";
			else
				row1.style.background  = '#FFFFFF';
		}
		var rd=document.getElementById("tab");
		rd.style.background = '#8798DE';
	}

	function setImagePreview()
	{
		pathv = document.configurationForm.invoiceDefaultLogo.value;
		image = document.getElementById('previewIMG');
		if(window.event)
		{
			path = pathv.replace(/\\/, '/');
		}
		else
		{
			path = 'File:\/\/' + pathv;
		}
		image.src=path;
		image.style.display = 'block';
		image.style.width = "150px";
		image.style.height = "150px";
	}
	function setLogo()
	{
		image = document.getElementById('previewIMG');
		<c:if test="${not empty path}">
			<c:if test="${not empty Image}">
                path = 'uploadedImages/${Image}';
                path = path.replace(/\\/, '/');
                image.src=path;
                image.style.display = 'block';
                image.style.width = "150px";
                image.style.height = "150px";
			</c:if>
		</c:if>
	}
</script>