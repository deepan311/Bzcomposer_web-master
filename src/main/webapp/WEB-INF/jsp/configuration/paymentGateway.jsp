<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page errorPage="/include/sessionExpired.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/WEB-INF/jsp/include/headlogo.jsp" />
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<jsp:include page="/WEB-INF/jsp/include/menu.jsp" />
<title><spring:message code="BzComposer.paymentgatewaytitle" /></title>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<script type="text/javascript">
function toggleFunction() {
	debugger;
  var x = document.getElementById("divtoggle");
  var lftmenu = document.getElementById("leftMenu");
  if (x.style.display === "none") {
    x.style.display = "block";
    lftmenu.style.width = "180px";
    lftmenu.style.position = "relative";
  } else {
    x.style.display = "none";
    lftmenu.style.width = "0";
    lftmenu.style.position = "absolute";
  }
}
function init5(){
    showPaymentGateway();
    init();
}

function showpaypalUserName()
{
	document.getElementById("payPalUserName").style.display = 'none';
	var userName = $('#payPalUserName option:selected').text();
	document.getElementById("userName").style.display = 'block';
	document.getElementById("userName").value = userName;
}
function showpaypalPassword()
{
	document.getElementById("payPalPassword").style.display = 'none';
	document.getElementById("payPalPasswordTextbox").style.display = 'block';
	var password = $('#payPalPassword option:selected').text();
	document.getElementById("payPalPasswordTextbox").value = password;
}

function showpaypalApiSignature()
{
	document.getElementById("apiSignature").style.display = 'none';
	document.getElementById("apiSignatureTextbox").style.display = 'block';
	var signature = $('#apiSignature option:selected').text();
	document.getElementById("apiSignatureTextbox").value = signature;
}

function showAuthorizedID()
{
	document.getElementById("authorizeNetId").style.display = 'none';
	var id = $('#authorizeNetId option:selected').text();
	document.getElementById("authorizeIDTextbox").style.display = 'block';
	document.getElementById("authorizeIDTextbox").value = id;
}

function showAuthorizedTransactionKey()
{
	document.getElementById("authorizeNetTranscationKey").style.display = 'none';
	var key = $('#authorizeNetTranscationKey option:selected').text();
	document.getElementById("transactionKey").style.display = 'block';
	document.getElementById("transactionKey").value = key;
}

function eProcessingNetworkApiLoginId()
{
	document.getElementById("eProcessingLoginId").style.display = 'none';
	var Id = $('#eProcessingLoginId option:selected').text();
	document.getElementById("txteProcessingLoginId").style.display = 'block';
	document.getElementById("txteProcessingLoginId").value = Id;
}

function showApiPassword()
{
	document.getElementById("apiPassword").style.display = 'none';
	var password = $('#apiPassword option:selected').text();
	document.getElementById("apiPasswordTextbox").style.display = 'block';
	document.getElementById("apiPasswordTextbox").value = password;
}

function showMerchantID()
{
	document.getElementById("GoEMerchantID").style.display = 'none';
	var mID = $('#GoEMerchantID option:selected').text();
	document.getElementById("MerchantID").style.display = 'block';
	document.getElementById("MerchantID").value = mID;
}

function showRefund()
{
	document.getElementById("refundID").style.display = 'none';
	var refund = $('#refundID option:selected').text();
	document.getElementById("refund").style.display = 'block';
	document.getElementById("refund").value = refund;
}

function showEwayMerchantID()
{
	document.getElementById("eWayMerchantId").style.display = 'none';
	var mID = $('#eWayMerchantId option:selected').text();
	document.getElementById("txtMerchantID").style.display = 'block';
	document.getElementById("txtMerchantID").value = mID;
}

function showIntuitID()
{
	document.getElementById("intuitLoginID").style.display = 'none';
	var intuitID = $('#intuitLoginID option:selected').text();
	document.getElementById("txtIntuitID").style.display = 'block';
	document.getElementById("txtIntuitID").value = intuitID;
}

function showIntuitPassword()
{
	document.getElementById("intuitLoginPassword").style.display = 'none';
	var intuitPassword = $('#intuitLoginPassword option:selected').text();
	document.getElementById("txtIntuitPassword").style.display = 'block';
	document.getElementById("txtIntuitPassword").value = intuitPassword;
}

function showPayFlowUser()
{
	document.getElementById("payFlowUser").style.display = 'none';
	var payFlowUser = $('#payFlowUser option:selected').text();
	document.getElementById("txtPayFlowUser").style.display = 'block';
	document.getElementById("txtPayFlowUser").value = payFlowUser;
}

function showPayFlowVendor()
{
	document.getElementById("payFlowVendor").style.display = 'none';
	var payFlowVendor = $('#payFlowVendor option:selected').text();
	document.getElementById("txtPayFlowVendor").style.display = 'block';
	document.getElementById("txtPayFlowVendor").value = payFlowVendor;
}

function showPayFlowPartner()
{
	document.getElementById("payFlowPartner").style.display = 'none';
	var payFlowPartner = $('#payFlowPartner option:selected').text();
	document.getElementById("txtPayFlowPartner").style.display = 'block';
	document.getElementById("txtPayFlowPartner").value = payFlowPartner;
}

function showPayFlowPassword()
{
	document.getElementById("payFlowPassword").style.display = 'none';
	var payFlowPassword = $('#payFlowPassword option:selected').text();
	document.getElementById("txtPayFlowPassword").style.display = 'block';
	document.getElementById("txtPayFlowPassword").value = payFlowPassword;
}

function showPgMerchantID()
{
	document.getElementById("pgMerchantID").style.display = 'none';
	var payFlowPassword = $('#pgMerchantID option:selected').text();
	document.getElementById("txtPgMerchantID").style.display = 'block';
	document.getElementById("txtPgMerchantID").value = payFlowPassword;
}

function showPgPassword()
{
	document.getElementById("pgPassword").style.display = 'none';
	var payFlowPassword = $('#pgPassword option:selected').text();
	document.getElementById("txtPgPassword").style.display = 'block';
	document.getElementById("txtPgPassword").value = payFlowPassword;
}

function showPsiLoginID()
{
	document.getElementById("psiLoginID").style.display = 'none';
	var psiLoginID = $('#psiLoginID option:selected').text();
	document.getElementById("txtPsiLoginID").style.display = 'block';
	document.getElementById("txtPsiLoginID").value = psiLoginID;
}

function showPsiPassword()
{
	document.getElementById("psiPassword").style.display = 'none';
	var psiPassword = $('#psiPassword option:selected').text();
	document.getElementById("txtPsiPassword").style.display = 'block';
	document.getElementById("txtPsiPassword").value = psiPassword;
}

function showRtWareMerchantID()
{
	document.getElementById("rtWareMerchantId").style.display = 'none';
	var psiPassword = $('#rtWareMerchantId option:selected').text();
	document.getElementById("txtRtWareMerchantId").style.display = 'block';
	document.getElementById("txtRtWareMerchantId").value = psiPassword;
}

function showRtWareMerchantPassword()
{
	document.getElementById("rtWareMerchantPassword").style.display = 'none';
	var psiPassword = $('#rtWareMerchantPassword option:selected').text();
	document.getElementById("txtRtWareMerchantPassword").style.display = 'block';
	document.getElementById("txtRtWareMerchantPassword").value = psiPassword;
}

function showSkipJackSerialNumber()
{
	document.getElementById("skipJackSerialNumber").style.display = 'none';
	var sjSerialNumber = $('#skipJackSerialNumber option:selected').text();
	document.getElementById("txtSkipJackSerialNumber").style.display = 'block';
	document.getElementById("txtSkipJackSerialNumber").value = sjSerialNumber;
}

function showSkipJackDeveloerSerialNumber()
{
	document.getElementById("skipJackDeveloperSerialNumber").style.display = 'none';
	var sjDeveloperSNumber = $('#skipJackDeveloperSerialNumber option:selected').text();
	document.getElementById("txtSkipJackDeveloperSerialNumber").style.display = 'block';
	document.getElementById("txtSkipJackDeveloperSerialNumber").value = sjDeveloperSNumber;
}

function showUSAePayTransactionKey()
{
	document.getElementById("usaEPayTranscationKey").style.display = 'none';
	var usaTransKey = $('#usaEPayTranscationKey option:selected').text();
	document.getElementById("txtUSAePayTransactionKey").style.display = 'block';
	document.getElementById("txtUSAePayTransactionKey").value = usaTransKey;
}

function showFastChargeID()
{
	document.getElementById("fastChargeMerchantID").style.display = 'none';
	var usaTransKey = $('#fastChargeMerchantID option:selected').text();
	document.getElementById("txtFastChargeMerchantID").style.display = 'block';
	document.getElementById("txtFastChargeMerchantID").value = usaTransKey;
}

function showiTransactLogInId()
{
	document.getElementById("iTransactLoginID").style.display = 'none';
	var usaTransKey = $('#iTransactLoginID option:selected').text();
	document.getElementById("txtiTransactLogInID").style.display = 'block';
	document.getElementById("txtiTransactLogInID").value = usaTransKey;
}

function showiTransactDomainName()
{
	document.getElementById("iTransactDomainname").style.display = 'none';
	var usaTransKey = $('#iTransactDomainname option:selected').text();
	document.getElementById("txtiTransactDomainName").style.display = 'block';
	document.getElementById("txtiTransactDomainName").value = usaTransKey;
}

function showcyberSourceMerchantID()
{
	document.getElementById("cyberSourceMerchantID").style.display = 'none';
	var usaTransKey = $('#cyberSourceMerchantID option:selected').text();
	document.getElementById("merchantID").style.display = 'block';
	document.getElementById("merchantID").value = usaTransKey;
}

function showPaymentGateway()
{
	var id = document.configurationForm.selectedPaymentGatewayId.value; 


	if(id == 1||id == 17 || id == 33 || id == 50 || id == 66 || id == 82 || id == 98 || id == 114 || id == 130 || id == 146)
	{
		document.getElementById("authorizeNet").style.display = 'block';
		document.getElementById("cyberSource").style.display = 'none'; 
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
		
		$('select[name="authorizeNetId"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="authorizeNetTranscationKey"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 2 || id == 18 || id == 34 || id == 51 || id == 83 || id == 99)
	{

		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none'; 
		document.getElementById("eProcessingNetwork").style.display = 'block';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
		debugger
		
		$('select[name="eProcessingLoginId"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="apiPassword"]').find('option[value="'+id+'"]').attr("selected",true);
		
	}
	else if(id == 3)
	{
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'block';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
		
		$('select[name="payPalUserName"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="payPalPassword"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="apiSignature"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 4)
	{
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'block';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
		
		$('select[name="eWayMerchantId"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 5)
	{
		debugger
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'block';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
		
		$('select[name="fastChargeMerchantID"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 6)
	{
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'block';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
		
		$('select[name="GoEMerchantID"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="refundID"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 7)
	{
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'block';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
		
		$('select[name="intuitLoginID"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="intuitLoginPassword"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 8)
	{
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'block';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
		
		$('select[name="psiLoginID"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="psiPassword"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 9)
	{
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'block';
		document.getElementById("USAePay").style.display = 'none';
		
		$('select[name="skipJackSerialNumber"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="skipJackDeveloperSerialNumber"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 10)
	{
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'block';
		
		$('select[name="usaEPayTranscationKey"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 11)
	{
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'block';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
		
		$('select[name="iTransactLoginID"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="iTransactDomainname"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 12)
	{
		debugger
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'block';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
		
		$('select[name="payFlowUser"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="payFlowVendor"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="payFlowPartner"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="payFlowPassword"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 13)
	{
		debugger;
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'block';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
		
		debugger;
		$('select[name="linkPointUserID"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="LinkPointPassword"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 14)
	{
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'block';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
		
		$('select[name="rtWareMerchantId"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="rtWareMerchantPassword"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 15)
	{
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'none';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'block';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';	
		
		$('select[name="pgMerchantID"]').find('option[value="'+id+'"]').attr("selected",true);
		$('select[name="pgPassword"]').find('option[value="'+id+'"]').attr("selected",true);
	}
	else if(id == 16)
	{
		document.getElementById("authorizeNet").style.display = 'none';
		document.getElementById("cyberSource").style.display = 'block';
		document.getElementById("eProcessingNetwork").style.display = 'none';
		document.getElementById("eWay").style.display = 'none';
		document.getElementById("fastCharge").style.display = 'none';
		document.getElementById("goEMerchant").style.display = 'none';
		document.getElementById("intuit").style.display = 'none';
		document.getElementById("iTransact").style.display = 'none';
		document.getElementById("linkPoint").style.display = 'none';
		document.getElementById("payFlow").style.display = 'none';
		document.getElementById("paymentsGateway").style.display = 'none';
		document.getElementById("paypal").style.display = 'none';
		document.getElementById("psiGate").style.display = 'none';
		document.getElementById("rtWare").style.display = 'none';
		document.getElementById("skipJack").style.display = 'none';
		document.getElementById("USAePay").style.display = 'none';
	}
}

</script>
</head>
<body onload="init5();">
<!-- begin shared/header -->
<form:form name="configurationForm" action="Configuration?" enctype="MULTIPART/FORM-DATA" method="post" modelAttribute="configDto">
	<div id="ddcolortabsline">&nbsp;</div>
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">

	<div><span
		style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; 
		border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.configuration.configurationtitle"/>
	</span></div>
	<div>
	<div>
	    <c:if test="${not empty Labels}">
		<input type="hidden" name="lsize" id="lblsize" value='${Labels.size()}' />
		<c:forEach items="${Labels}" var="lbl" varStatus="loop">
			<input type="hidden" id='${loop.index}lid' name='${loop.index}lidname' value='${lbl.value}' />
			<input type="hidden" id='${loop.index}lname' name='${loop.index}lnm' value='${lbl.label}' />
		</c:forEach>
	</c:if></div>
	<div id="table-negotiations" style="padding: 0; border: 1px solid #ccc;">
	<table cellspacing="0"  style="border: 0;width: 100%;overflow-y:scroll;" class="section-border">
			<span style="font-size:30px;cursor:pointer; margin-left: 20px;" onclick="toggleFunction()">&#9776;</span>
		<tr>

			<td id="leftMenu" style="position: relative; width: 180px;">
			<table>
				<tr>
					<td>
						<jsp:include page="menuPage.jsp" />
					</td>
				</tr>
				<%-- <tr align="center">
					<td><input type="button" name="Revoke" class="formButton"
						onclick="RevokeValues();"
						value='<spring:message code="BizComposer.Configuration.RevokeButton"/>' />
					<input type="button" name="Save" class="formButton"
						onclick="SaveValues();"
						value='<spring:message code="BizComposer.Configuration.SaveButton"/>' />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr> --%>
			</table>
			</td>

			<td valign="top" style="padding-right: 20px; padding-bottom: 20px;">
			<!-- Payment Gateway Starts -->
			<div id="paymentGateway" style="display:none;padding: 0; position: relative; left: 0;">
				<table class="table-notifications" width="100%">
				<tr>
					<th colspan="2" align="left" style="font-size: 12px; padding: 5px;">
						<spring:message code="BzComposer.configuration.selectpaymentgateway" />
					</th>
				</tr>
				<tr>
					<td style="font-size: 12px;">
						<b><spring:message code="BzComposer.configuration.defaultpaymentgateways"/> :</b>
					</td>
					<td style="font-size: 12px;">
						<form:select path="selectedPaymentGatewayId" onchange="showPaymentGateway()">
						    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                            	<c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                            		<option value="${objList1.gateWayId}">${objList1.gatewayName}</option>
                            	</c:forEach>
                            </c:if>
						</form:select>
					</td>
				</tr>
				<tr>
					<td style="font-size: 12px;">
						<b><spring:message code="BzComposer.configuration.defaultpaymentenvironment"/></b>
					</td>
					<td style="font-size: 12px;">
						<select id="paymentEnviromentId">
							<option value="0">
								<spring:message code="BzComposer.configuration.livepaymentprocessing"/>
							</option>
							<option value="1" selected>
								<spring:message code="BzComposer.configuration.sandboxpaymentprocessing"/>
							</option>
						</select>
					</td>
				</tr>
				</table>
				<div style="background-color:#D3D3D3;border:threedlightshadow;width:800px;text-align:center;">
				<div id="authorizeNet" style="display:none;">
					<div>			
						<table style="width:600px;height:300px;">
							<tr>
								<td align="center">
									<img id="img1" src='Payment Gateways/authorize.jpg'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
								<h1><b><spring:message code="BzComposer.configuration.authorizenet"/></b></h1>	  
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.authorizenetid"/>:</b>
								</td>
								<td align="center" style="font-size: 1em;">
									<select id="authorizeNetId" name="authorizeNetId" onclick="showAuthorizedID()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                        	<c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                        		<option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                        	</c:forEach>
                                        </c:if>
									</select>
									<input type="text" id="authorizeIDTextbox" name="authorizeIDTextbox" style="display:none; width:200px; ">
								</td>
							</tr>
							<tr>
							<td align="center" style="font-size: 14px;">
								<b><spring:message code="BzComposer.configuration.authorizenettranscationkey"/>:</b>
							</td>
							<td align="center" style="font-size: 14px;">
								<select id="authorizeNetTranscationKey" name="authorizeNetTranscationKey" onclick="showAuthorizedTransactionKey()" style="display:block;">
								    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                        <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                            <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                        </c:forEach>
                                    </c:if>
								</select>
								<input type="text" id="transactionKey" name="transactionKey" style="display:none; width:200px;">
							</td>
						</table>
					</div>
				</div>
				<div id="eProcessingNetwork" style="display:none;">
					<div>			
						<table style="width:600px;height:300px;">
							<tr>
								<td align="center">
									<img id="img2" src='${pageContext.request.contextPath}/Payment Gateways/eProcess.jpg'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
								<h1><b><spring:message code="BzComposer.configuration.eprocessingnetwork"/></b></h1>	  
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.eprocessingnetworkapiloginid"/> :
								</td>
								<td align="center" style="font-size: 14px;">
								<select id="eProcessingLoginId" name="eProcessingLoginId" onclick="eProcessingNetworkApiLoginId()" style="display:block;">
								<c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                    <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                        <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                    </c:forEach>
                                </c:if>
								</select>
								<input type="text" id="txteProcessingLoginId" style="display: none; width:350px;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.apipassword"/> :
								</td>
								<td align="center" style="font-size: 14px;">
									<select id="apiPassword" name="apiPassword" onclick="showApiPassword()" style="display:block;">
									<c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                        <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                            <option value="${objList1.fieldName2}">${objList1.fieldName2}</option>
                                        </c:forEach>
                                    </c:if>
									</select>
									<input type="password" id="apiPasswordTextbox" style="display: none; width:350px;">
								</td>
						</table>
					</div>
				</div>
				<div id="paypal" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img3" src='${pageContext.request.contextPath}/Payment Gateways/PayPal_New.gif'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.paypal"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.paypal.username"/> :</b>
								</td>
								<td style="font-size: 14px;">
								<select id="payPalUserName" name="payPalUserName" onclick="showpaypalUserName()" style="display:block;">
								<c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                    <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                        <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                    </c:forEach>
                                </c:if>
								</select>
								<input type="text" id="userName" name="userName" style="display: none; width:350px;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.paypal.password"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="payPalPassword" name="payPalPassword" onclick="showpaypalPassword()" style="display:block;">
									<c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                        <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                            <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                        </c:forEach>
                                    </c:if>
									</select>
									<input type="password" id="payPalPasswordTextbox" style="display: none;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.apisignature"/> :</b>
								</td>
								<td style="font-size: 14px;">
									 <select id="apiSignature" name="apiSignature" onclick="showpaypalApiSignature()" style="display:block;">
									 <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                         <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                             <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName4}</option>
                                         </c:forEach>
                                     </c:if>
									</select> 
									<input type="text" id="apiSignatureTextbox" style="display: none; width:500px;">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="cyberSource" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img4" src='${pageContext.request.contextPath}/Payment Gateways/CyberSource.gif'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.cybersource"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.merchantid"/> :
								</td>
								<td style="font-size: 14px;">
								<select id="cyberSourceMerchantID" name="cyberSourceMerchantID" onclick="showcyberSourceMerchantID()" style="display:block;">
								<c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                     <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                         <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                     </c:forEach>
                                 </c:if>
								</select>
								<input type="text" id="merchantID" name="merchantID" style="display:none;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.certificatepath"/> :
								</td>
								<td style="font-size: 14px;">
									<form:input type="file" path="gatewayName" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="eWay" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img5" src='${pageContext.request.contextPath}/Payment Gateways/Eway.GIF'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.eway"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.ewaymerchantid"/> :
								</td>
								<td style="font-size: 14px;">
								<select id="eWayMerchantId" name="eWayMerchantId" onclick="showEwayMerchantID()" style="display:block;">
								<c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                     <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                         <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                     </c:forEach>
                                 </c:if>
								</select>
								<input type="text" id="txtMerchantID" name="txtMerchantID" style="display:none;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.ewayrefundpassword"/> :
								</td>
								<td style="font-size: 14px;">
									<form:input type="password" path="eWayRefundPassword" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="fastCharge" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img6" src='${pageContext.request.contextPath}/Payment Gateways/FastCharge.gif'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.fastcharge"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.fastchargemerchantid"/> :</b>
								</td>
								<td style="font-size: 14px;">
								<select id="fastChargeMerchantID" name="fastChargeMerchantID" onclick="showFastChargeID()" style="display:block;">
								<c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                     <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                         <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                     </c:forEach>
                                 </c:if>
								</select>
								<input type="text" id="txtFastChargeMerchantID" name="txtFastChargeMerchantID" style="display:none;">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="goEMerchant" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img8" src='${pageContext.request.contextPath}/Payment Gateways/goemerchant.gif'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.goemerchant"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.loginid"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="GoEMerchantID" name="GoEMerchantID" onclick="showMerchantID()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="text" id="MerchantID" name="MerchantID" style="display:none;">
								</td> 
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.merchantpassword"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<input type="password" id="merchantPassword" name="merchantPassword">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.refundid"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="refundID" name="refundID" onclick="showRefund()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="text" id="refund" name="refund" style="display:none;">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="intuit" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img8" src='${pageContext.request.contextPath}/Payment Gateways/innovative.jpg'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.intuit"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.intuitloginid"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="intuitLoginID" name="intuitLoginID" onclick="showIntuitID()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="text" id="txtIntuitID" name="txtIntuitID" style="display:none;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.intuitloginpassword"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="intuitLoginPassword" name="intuitLoginPassword" onclick="showIntuitPassword()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="password" id="txtIntuitPassword" name="txtIntuitPassword" style="display:none;">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="iTransact" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img8" src='${pageContext.request.contextPath}/Payment Gateways/itransact.gif'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.itransact"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.merchantid"/> :</b>
								</td>
								<td style="font-size: 14px;">
									 <select id="iTransactLoginID" name="iTransactLoginID" onclick="showiTransactLogInId()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                             </c:forEach>
                                         </c:if>
									</select> 
									<input type="text" id="txtiTransactLogInID" name="txtiTransactLogInID" style="display:none;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.domainname"/> :</b>
								</td>
								<td style="font-size: 14px;">
									 <select id="iTransactDomainname" name="iTransactDomainname" onclick="showiTransactDomainName()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                             </c:forEach>
                                         </c:if>
									</select> 
									<input type="text" id="txtiTransactDomainName" name="txtiTransactDomainName" style="display:none;">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="linkPoint" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img8" src='${pageContext.request.contextPath}/Payment Gateways/linkpoint.gif'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.linkpoint"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.userid"/> :
								</td>
								<td style="font-size: 14px;">
									<select name="linkPointUserID">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.password"/> :
								</td>
								<td style="font-size: 14px;">
									<select name="linkPointPassword">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<spring:message code="BzComposer.configuration.certificatepath"/> :
								</td>
								<td style="font-size: 14px;">
									<form:input type="file" path="gatewayName" value="D:\Pritesh\bzc_12_04_2018(MySQL)\settings\linkpoint\CID_1" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="payFlow" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img8" src='${pageContext.request.contextPath}/Payment Gateways/payflow.gif'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.payflow"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.user"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="payFlowUser" name="payFlowUser" onclick="showPayFlowUser()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="text" id="txtPayFlowUser" name="txtPayFlowUser" style="display:none;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.vendor"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="payFlowVendor" name="payFlowVendor" onclick="showPayFlowVendor()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="text" id="txtPayFlowVendor" name="txtPayFlowVendor" style="display:none;">
								</td>
							</tr>
							<tr>
								<td></td>
							</tr>
							<tr>
								<td></td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.partner"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="payFlowPartner" name="payFlowPartner" onclick="showPayFlowPartner()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName3}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="text" id="txtPayFlowPartner" name="txtPayFlowPartner" style="display:none;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.password"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="payFlowPassword" name="payFlowPassword" onclick="showPayFlowPassword()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName4}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="password" id="txtPayFlowPassword" name="txtPayFlowPassword" style="display:none;">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="paymentsGateway" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img8" src='${pageContext.request.contextPath}/Payment Gateways/PaymentsGateway.gif'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center" style="font-size: 14px;">
									 <h1><b><spring:message code="BzComposer.configuration.paymentsgateway"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.merchantid"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="pgMerchantID" name="pgMerchantID" onclick="showPgMerchantID()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="text" id="txtPgMerchantID" name="txtPgMerchantID" style="display:none;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.password"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="pgPassword" name="pgPassword" onclick="showPgPassword()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="password" id="txtPgPassword" name="txtPgPassword" style="display:none;">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="psiGate" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img8" src='${pageContext.request.contextPath}/Payment Gateways/payment_gateway_logo_47.gif'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.psigate"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.loginid"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="psiLoginID" name="psiLoginID" onclick="showPsiLoginID()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="text" id="txtPsiLoginID" name="txtPsiLoginID" style="display:none;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.merchantpassword"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="psiPassword" name="psiPassword" onclick="showPsiPassword()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="password" id="txtPsiPassword" name="txtPsiPassword" style="display:none;">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="rtWare" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img8" src='${pageContext.request.contextPath}/Payment Gateways/rtware.jpg'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.rtware"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.rtwaremerchantid"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="rtWareMerchantId" name="rtWareMerchantId" onclick="showRtWareMerchantID()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="text" id="txtRtWareMerchantId" name="txtRtWareMerchantId" style="display:none;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.rtwaremerchantpassword"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="rtWareMerchantPassword" name="rtWareMerchantPassword" onclick="showRtWareMerchantPassword()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="password" id="txtRtWareMerchantPassword" name="txtRtWareMerchantPassword" style="display:none;">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="skipJack" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img8" src='${pageContext.request.contextPath}/Payment Gateways/SkipJack.gif'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.skipjack"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.skipjackserialnumber"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="skipJackSerialNumber" name="skipJackSerialNumber" onclick="showSkipJackSerialNumber()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName1}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="text" id="txtSkipJackSerialNumber" name="txtSkipJackSerialNumber" style="display:none;">
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.skipjackdeveloperserialnumber"/> :</b>
								</td>
								<td style="font-size: 14px;">
									<select id="skipJackDeveloperSerialNumber" name="skipJackDeveloperSerialNumber" onclick="showSkipJackDeveloerSerialNumber()" style="display:block;">
									    <c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                             <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                                 <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                             </c:forEach>
                                         </c:if>
									</select>
									<input type="text" id="txtSkipJackDeveloperSerialNumber" name="txtSkipJackDeveloperSerialNumber" style="display:none;">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="USAePay" style="display:none;">
					<div>		
						<table style="width:750px;height:300px;">
							<tr>
								<td align="center">
									<img id="img7" src='${pageContext.request.contextPath}/Payment Gateways/USAePay_New.gif'
									style="display:block;width:150px;height:50px;" />
								</td>
								<td align="center">
									 <h1><b><spring:message code="BzComposer.configuration.usaepay"/></b></h1> 
								</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 14px;">
									<b><spring:message code="BzComposer.configuration.usaepaytranscationkey"/> :</b>
								</td>
								<td style="font-size: 14px;">
								<select id="usaEPayTranscationKey" name="usaEPayTranscationKey" onclick="showUSAePayTransactionKey()" style="display:block;">
								<c:if test="${not empty configDto.listOfExistingPaymentGateways}">
                                     <c:forEach items="${configDto.listOfExistingPaymentGateways}" var="objList1">
                                         <option value="${objList1.gateWayId}" hidden disabled>${objList1.fieldName2}</option>
                                     </c:forEach>
                                 </c:if>
								</select>
								<input type="text" id="txtUSAePayTransactionKey" name="txtUSAePayTransactionKey" style="display:none;width:300px;">
								</td>
							</tr>
						</table>
					</div>
					</div>
					</div>
					</div>
					<!-- Payment Gateway Ends -->
					</td>
					</tr>
				</table>
				</div>
				<div align="center">
					<input type="button" class="bottomButton formButton" value='<spring:message code="BzComposer.global.save"/>' />
					<input type="reset" class="bottomButton formButton" name="Cancel" value='<spring:message code="BzComposer.global.cancel"/>' />
				</div>
				</div>
				</div>
				</div>
				</div>
				</div>
			</div>
		</form:form>
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
	</body>
</html>