<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function invaliddaysdialog()
{
	event.preventDefault();
	$("#invaliddaysdialog").dialog({
    	resizable: false,
        height: 200,
        width: 450,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
$(document).ready(function()
{
	//var invoiceMemoDays = $("#invoiceMemoDays").val();
	debugger
	var invoiceMemo = document.configurationForm.invoiceMemo.value;
	var memorizeEstimation = document.configurationForm.memorizeEstimation.value;
	var overdueInvoice = document.configurationForm.overdueInvoice.value;
	var inventoryOrder = document.configurationForm.inventoryOrder.value;
	var serviceBilling = document.configurationForm.serviceBilling.value;
	var billsPay = document.configurationForm.billsToPay.value;
	var memorizeBill = document.configurationForm.memorizeBill.value;
	var purchaseOrder= memorizeBill;
	
	<%-- var showReminder = '<%= request.getAttribute("showReminderStatus")%>';

	if(showReminder == "on")
	{
		$("#showReminder").attr("checked",true);
	}
	else
	{
		$("#showReminder").attr("checked",false);
	} --%>
	 
	debugger
	
	if(invoiceMemo == 1)
	{
		$("#invoiceMenoDontRemindMe").prop("checked",true);
	}
	else
	{
		$("#invoiceMemoRemindMe").prop("checked",true);
	}
	
	//var memorizeEstimationDays = $("#memorizeEstimationDays").val();
	if(memorizeEstimation == 1)
	{
		$("#memorizeDontRemindMe").prop("checked",true);
	}
	else
	{
		$("#memorizeRemindMe").prop("checked",true);
	}
	
	//var overdueInvoiceDays = $("#overdueInvoiceDays").val();
	if(overdueInvoice == 1)
	{
		$("#overdueInvoiceDontRemindMe").prop("checked",true);
	}
	else
	{
		$("#overdueInvoiceRemindMe").prop("checked",true);
	}
	//var inventoryOrderDays = $("#inventoryOrderDays").val();
	if(inventoryOrder == 1)
	{
		$("#inventoryOrderDontRemindme").prop("checked",true);	
	}
	else
	{
		$("#inventoryOrderRemindMe").prop("checked",true);
	}
	
	//var serviceBillingDays = $("#serviceBillingDays").val();
	if(serviceBilling == 1)
	{
		$("#serviceBillingDontRemindMe").prop("checked",true);
	}
	else
	{
		$("#serviceBillingRemindMe").prop("checked",true);
	}
	
	//var billsToPayDays = $("#billsToPayDays").val();
	if(billsPay == 1)
	{
		$("#billsToPayDontRemindMe").prop("checked",true);
	}
	else
	{
		$("#billsToPayRemindMe").prop("checked",true);
	}
	
	//var memorizePurchaseOrderDays = $("#memorizePurchaseOrderDays").val();
	if(purchaseOrder == 1)
	{
		$("#memorizeBillRemindMe").prop("checked",true);
		$("#memorizePurchaseOrderDontRemindMe").prop("checked",false);
	}
	else
	{
		$("#memorizePurchaseOrderDontRemindMe").prop("checked",true);
		$("#memorizeBillRemindMe").prop("checked",false);
	}
	
	//var memorizeBillDays = $("#memorizeBillDays").val();
	if(memorizeBill == 1)
	{
		$("#memorizeBillDontRemindMe").prop("checked",true);
		$("#memorizePurchaseOrderRemindMe").prop("checked",false);
	}
	else
	{
		$("#memorizePurchaseOrderRemindMe").prop("checked",true);
		$("#memorizeBillDontRemindMe").prop("checked",false);
	}
	
	$("#invoiceMemoRemindMe").click(function()
	{

		debugger
		document.configurationForm.invoiceMemo.value = 1;
		debugger
	});
	
	$("#invoiceMenoDontRemindMe").click(function()
	{

		debugger
		document.configurationForm.invoiceMemo.value = 0;
		debugger
	});
	
	$('#invoiceMemoDays').change(function()
	{
		var inoviceMemoDays = $("#invoiceMemoDays").val();
		if(inoviceMemoDays > 180)
		{

			return invaliddaysdialog();
			$("#invoiceMemoDays").focus();
		}
	});		
	$('#memorizeEstimationDays').change(function()
	{
		var estimateDays = $("#memorizeEstimationDays").val();
		if(estimateDays > 180)
		{

			return invaliddaysdialog();
			$("#memorizeEstimationDays").focus();
		}
	});
	$('#overdueInvoiceDays').change(function()
	{
		var invoiceDays = $("#overdueInvoiceDays").val();
		if(invoiceDays > 180)
		{

			return invaliddaysdialog();
			$("#overdueInvoiceDays").focus();
		}
	});
	$('#inventoryOrderDays').change(function()
	{
		var inventoryOrderDays = $("#inventoryOrderDays").val();
		if(inventoryOrderDays > 180)
		{

			return invaliddaysdialog();
			$("#inventoryOrderDays").focus();
		}
	});
	$('#serviceBillingDays').change(function()
	{
		var serviceBillingDays = $("#serviceBillingDays").val();
		if(serviceBillingDays > 180)
		{

			return invaliddaysdialog();
			$("#serviceBillingDays").focus();
		}
	});
	$('#billsToPayDays').change(function()
	{
		var billPayDays = $("#billsToPayDays").val();
		if(billPayDays > 180)
		{

			return invaliddaysdialog();
			$("#billsToPayDays").focus();
		}
	});
	$('#memorizePurchaseOrderDays').change(function()
	{
		var purchaseOrderDays = $("#memorizePurchaseOrderDays").val();
		if(purchaseOrderDays > 180)
		{

			return invaliddaysdialog();
			$("#memorizePurchaseOrderDays").focus();
		}
	});
	$('#memorizeBillDays').change(function()
	{
		var memorizeBillDays = $("#memorizePurchaseOrderDays").val();
		if(memorizeBillDays > 180)
		{

			return invaliddaysdialog();
			$("#memorizePurchaseOrderDays").focus();
		}
	});
	
	$('#showReminder').change(function()
	{
		var isChecked = '<%= request.getAttribute("showReminderStatus")%>';
		debugger
		if($(this).prop("checked") == true)
		{

	        $("#showReminder").attr('checked', true);
	        debugger
	        isChecked = "on"; 
		}
	    else if($(this).prop("checked") == false)
	    {

	        $("#showReminder").attr('checked', false);
	        debugger
	        isChecked = "off";
		}	
	    else
	    {

	        $("#showReminder").attr('checked', isChecked);
	        debugger
	    	document.configurationForm.showReminder.value = isChecked;
	    	debugger
	    }	
		$("#showReminder").val(isChecked);
	});
});
function numbersonly(e,val)
{
	var temp=val.indexOf(".");
	var unicode=e.charCode? e.charCode : e.keyCode;
	if (unicode!=8)
	{
 		//if the key isn't the backspace key (which we should allow)
		if(unicode==46 && temp==-1)
		{
 			return true;
		} 
		else 
		if (unicode<48||unicode>57) //if not a number
			return false; //disable key press
	}
}
</script>
<table class="table-notifications" width="100%">
	<tr>
		<td style="font-size:12px;"> 
			<input type="checkbox" path="showReminder" id="showReminder" value="${configDto.showReminder}" ${configDto.showReminder=='on'?'checked':''}  />
			<label><spring:message code="BzComposer.configuration.showreminder" /></label>
		</td>
	</tr>
	<tr>
		<th align="left" style="font-size:12px; padding: 5px;">
			<spring:message code="BzComposer.configuration.reminderlist" />
		</th>
	</tr>
	<tr>
		<td>
			<table style="width:60%;">
				<tr>
					<td style="width:100px;"></td>
					<td align="center" style="width:100px;font-size:12px;"><b><spring:message code="BzComposer.configuration.remindme" /></b></td>
					<td align="center" style="width:150px;font-size:12px;"><b><spring:message code="BzComposer.configuration.dontremindme" /></b></td>
					<td style="width:100px;font-size:12px;"><b><spring:message code="TermsList.Days" /></b></td>
					<td style="width:150px;"></td>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.memorizeinvoice" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="invoiceMemo" value="1" id="invoiceMemoRemindMe" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="invoiceMemo" value="0" id="invoiceMenoDontRemindMe" />
					</td>
					<td style="font-size:12px;">
						<form:input path="invoiceMemoDays" id="invoiceMemoDays" size="10" maxlength="4" onkeypress="return numbersonly(event,this.value);" />
					</td>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.daysaftermemorizedate" />
					</td>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.memorizeestimation" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="memorizeEstimation" value="1" id="memorizeRemindMe" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="memorizeEstimation" value="0" id="memorizeDontRemindMe" />
					</td>
					<td style="font-size:12px;">
						<form:input path="memorizeEstimationDays" id="memorizeEstimationDays" size="10" maxlength="4" onkeypress="return numbersonly(event,this.value);" />
					</td>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.daysaftermemorizedate" />
					</td>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.overdueinvoices" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="overdueInvoice" value="1" id="overdueInvoice" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="overdueInvoice" value="0" id="overdueInvoiceDontRemindMe" />
					</td>
					<td style="font-size:12px;">
						<form:input path="overdueInvoiceDays" id="overdueInvoiceDays" size="10" maxlength="4" onkeypress="return numbersonly(event,this.value);" />
					</td>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.daysbeforeduedate" />
					</td>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.inventorytoreorder" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="inventoryOrder" value="1" id="inventoryOrderRemindMe" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="inventoryOrder" value="0" id="inventoryOrderDontRemindme" />
					</td>
					<td style="font-size:12px;">
						<form:input path="inventoryOrderDays" id="inventoryOrderDays" size="10" maxlength="4" onkeypress="return numbersonly(event,this.value);" />
					</td>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.qtybeforereorderpoint" />
					</td>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.servicebilling" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="serviceBilling" value="1" id="serviceBillingRemindMe" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="serviceBilling" value="0" id="serviceBillingDontRemindMe" />
					</td>
					<td style="font-size:12px;">
						<form:input path="serviceBillingDays" id="serviceBillingDays" size="10" maxlength="4" onkeypress="return numbersonly(event,this.value);" />
					</td>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.daysbeforeschedulebillingdate" />
					</td>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.billstopay" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="billsToPay" value="1" id="billsToPayRemindMe" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="billsToPay" value="0" id="billsToPayDontRemindMe" />
					</td>
					<td style="font-size:12px;">
						<form:input path="billsToPayDays" id="billsToPayDays" size="10" maxlength="4" onkeypress="return numbersonly(event,this.value);" />
					</td>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.daysbeforeduedate" />
					</td>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.memorizepurchaseorder" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="memorizePurchaseOrder" value="1" id="memorizePurchaseOrderRemindMe" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="memorizePurchaseOrder" value="0" id="memorizePurchaseOrderDontRemindMe" />
					</td>
					<td style="font-size:12px;">
						<form:input path="memorizePurchaseOrderDays" size="10" maxlength="4" onkeypress="return numbersonly(event,this.value);" />
					</td>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.daysaftermemorizedate" />
					</td>
				</tr>
				<tr>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.memorizebill" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="memorizeBill" value="1" id="memorizeBillRemindMe" />
					</td>
					<td align="center" style="font-size:12px;">
						<form:radiobutton path="memorizeBill" value="0" id="memorizeBillDontRemindMe" />
					</td>
					<td style="font-size:12px;">
						<form:input path="memorizeBillDays" id="memorizeBillDays" size="10" maxlength="4" onkeypress="return numbersonly(event,this.value);" />
					</td>
					<td style="font-size:12px;">
						<spring:message code="BzComposer.configuration.daysaftermemorizedate" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div>
	<input type="hidden" name="showReminderStatus" id="showReminderStatus" value="" />	
</div>
<div id="invaliddaysdialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.reminder.insertedvaluenotgreater"/></p>
</div>