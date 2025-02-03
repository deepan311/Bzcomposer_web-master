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
<title><spring:message code="BzComposer.employeetitle" /></title>
<%-- <link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> --%>
<%-- <script src="${pageContext.request.contextPath}/dist/js/jquery.min.js"></script>  --%>
<%-- <script src="${pageContext.request.contextPath}/dist/js/bootstrap.min.js"></script> --%>
</head>
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
$(function() {
    $( "#tabs" ).tabs();
    $( "#tabs1" ).tabs();
  });
  
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
var oEmail = null;
var oT = null;
var nm="";
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
  
function trim(inputString) 
{
	// Removes the spaces  return from the passed string. 
	var retValue = inputString;
	var ch = retValue.substring(0, 1);
	while (ch == "\n" || ch == "\r" || ch==" " || ch=="\t" ) 
	{ 
		retValue = retValue.substring(1, retValue.length);
	    ch = retValue.substring(0, 1);
	}
	return retValue; 
}

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

function oGET(oo, url) 
{
	try 
	{
  		oo.open("GET", url, true);	
	    oo.send(null);
	} 
	catch (ex) 
	{}
}

function setJobValues(jobid,job,cost,desc,rid)
{
	size = document.getElementById('tabsize').value;
	for(i=0;i<size;i++)
	{
		var row1=document.getElementById(i+"$$");
		row1.className = "";
	}
	var rd=document.getElementById(rid);
	rd.className = "draft";
	document.getElementById('jcode').value = job;
	document.getElementById('cost').value = cost;
	document.getElementById('desc').value = desc;
	jid = jobid;
}

function wxToFixed(value,digits)
{
    factor = Math.pow(10,digits);
    fixed = ( Math.floor(value * factor + 0.5) ) / factor;
    fixedString = ''+fixed; // toString is JavaScript 1.1
    decimalPosition = fixedString.indexOf('.');
    if ( decimalPosition == -1 )
    {
        decimalsLength = 0;
        if ( digits > 0 )
        {
            fixedString = fixedString + '.';
        }
    }
    else
    {
        decimalsLength = (fixedString.substr(decimalPosition + 1)).length;
    }
    numZerosAppend = digits - decimalsLength;
    for ( i = 0; i < numZerosAppend; i++ )
    {
        fixedString = fixedString + '0';
    }
    return fixedString;
}

function SelectJob()
{
	if (o.readyState != 4 || o.status != 200) { 
	  return;
}
	document.getElementById("jobCodeTime").innerHTML = "";
    document.getElementById("jobCodeTime").innerHTML = o.responseText ;
}


function AddJobCode()
{
	
	code = trim(document.getElementById('jcode').value);
	if(code=="" || document.getElementById('jcode').value.length == 0)
	{

		return emptyjobcodedialog();
		document.getElementById('jcode').focus();
	}
	else
	{
		cst = wxToFixed(document.getElementById('cost').value,2);
		document.getElementById('cost').value = cst;
		if(cst=="" || cst==0)
		{

			return bigcostvalidationdialog();
			document.getElementById('cost').value="";
			document.getElementById('cost').focus();
		}
		else{
			event.preventDefault();
			$("#addjobcodedialog").dialog({
			    	resizable: false,
			        height: 200,
			        width: 500,
			        modal: true,
			        buttons: {
			            "<spring:message code='BzComposer.global.ok'/>": function () {
			            	$(this).dialog("close");
			            	description = document.getElementById('desc').value;
							window.location.href = 'Configuration?tabid=addEmployeeJobCode&code='+code+'&cost='+cst+'&desc='+description;
			            },
			            <spring:message code='BzComposer.global.cancel'/>: function () {
			                $(this).dialog("close");
			                return false;
			            }
			        }
			    });
			    return false;
		}
	}
}

function AddJobTitle()
{
	debugger;
	var jobTitle = $("#txtjobTitle").val();
	debugger
	var availableJobTitle = $.trim($("#selectedJobTitle option:selected").val());
	
	var availablejobs = $.trim($("#selectedJobTitle option[value='"+jobTitle+"']").val());
	debugger
	var camelized = jobTitle.toLowerCase().replace(/\b[a-z]/g, function(letter) {
	    return letter.toUpperCase();
	});
	debugger

	debugger
	if(jobTitle == "")
	{

		return emptyjobtitledialog();
	}
	/* else if(availableJobTitle == "")
	{
		alert("<bean:message key='BzComposer.common.selectARecordFirst'/>");
	} */
	else if(camelized == availableJobTitle || jobTitle == availablejobs)
	{

		return duplicatejobtitledialog();
	}
	else 
	{
		window.location.href="Configuration?tabid=saveJobTitle&jobTitle="+jobTitle+"&operation=add";
		//window.open("Configuration?tabid=saveJobTitle&jobTitle="+jobTitle+"&operation=add");
		/* var formData = $('frmemployee').serialize();
		$.ajax({
			type : "POST",
			url : "Configuration?tabid=saveJobTitle&jobTitle="+jobTitle+"&operation=add",
			data : formData,
			success : function(data) {
				debugger
				//$("#showsaverecorddialog").dialog("close");
				$("#showsuccessdialog").dialog({
						resizable: false,
				        height: 200,
				        width: 500,
				        modal: true,
				        buttons: {
				        	"<spring:message code='BzComposer.global.ok'/>": function () {
				        		$(this).dialog("close");
				                return false;
				        	},
				            "<spring:message code='BzComposer.global.cancel'/>": function () {
				                $(this).dialog("close");
				                return false;
				            }
			        	}
				});
			},
			error : function(data) {
				alert("<bean:message key='BzComposer.common.erroroccurred'/>");
			}
		}); */
		
		
		
	}
}

function EditJobTitle()
{
	var jobTitle = $("#txtjobTitle").val();
	debugger
	var availableJobTitle = $.trim($("#selectedJobTitle option:selected").val());
	
	var selectedJobtitleId = $("#selectedJobTitle").children(":selected").attr("id");
	
	var availablejobs = $.trim($("#selectedJobTitle option[value='"+jobTitle+"']").val());
	debugger
	var camelized = jobTitle.toLowerCase().replace(/\b[a-z]/g, function(letter) {
	    return letter.toUpperCase();
	});
	debugger

	debugger
	if(jobTitle == "")
	{

		return emptyjobtitledialog();
	}
	
	else if(availableJobTitle == "")
	{

		return selectrowdialog();
	}
	
	else if(camelized == availableJobTitle || jobTitle == availablejobs)
	{

		return duplicatejobtitledialog();
	}
	else 
	{

		window.location.href="Configuration?tabid=saveJobTitle&jobTitle="+jobTitle+"&titleId="+selectedJobtitleId+"&operation=edit";
	}
}

function DeleteJobTitle()
{
	debugger
	var selectedJobTitleId = $("#selectedJobTitle").children(":selected").attr("id");
	debugger
	var deletedJobTitle = $("#selectedJobTitle option:selected").val();
	debugger
	var jobTitle = $("#txtjobTitle").val();
	debugger
	if(selectedJobTitleId == "")
	{

		return selectrowdialog();
	}
	else if(jobTitle == "")
	{

		return emptyjobtitledialog();
	}
	else 
	{

		window.location.href="Configuration?tabid=saveJobTitle&titleId="+selectedJobTitleId+"&operation=delete";
	}
	
}

function EditJobCode()
{	
	if(jid==0)
	{

		return selectjobcodedialog();
	}
	else
	{
		code = trim(document.getElementById('jcode').value);

		if(code=="" || document.getElementById('jcode').value.length == 0)
		{

			return emptyjobcodedialog();
			document.getElementById('jcode').focus();
		}
		else
		{
			cst = wxToFixed(document.getElementById('cost').value,2);
			document.getElementById('cost').value = cst;
			if(cst=="" || cst==0)
			{

				return bigcostvalidationdialog();
				document.getElementById('cost').value="";
				document.getElementById('cost').focus();
			}
			else{
				event.preventDefault();
				$("#editjobcodedialog").dialog({
                    resizable: false,
                    height: 200,
                    width: 500,
                    modal: true,
                    buttons: {
                        "<spring:message code='BzComposer.global.ok'/>": function () {
                            $(this).dialog("close");
                            description = document.getElementById('desc').value;
                            window.location.href = 'Configuration?tabid=editEmployeeJobCode&code='+code+'&cost='+cst+'&desc='+description+'&jobId='+jid;
                        },
                        <spring:message code='BzComposer.global.cancel'/>: function () {
                            $(this).dialog("close");
                            return false;
                        }
                    }
                });
                return false;
			}
		}
	}
}

function RemoveJobCode()
{
	if(jid==0)
	{

		return selectjobcodedialog();
	}
	else{
		event.preventDefault();
		$("#removejobcodedialog").dialog({
		    	resizable: false,
		        height: 200,
		        width: 500,
		        modal: true,
		        buttons: {
		            "<spring:message code='BzComposer.global.ok'/>": function () {
		            	$(this).dialog("close");
		            	window.location.href = 'Configuration?tabid=deleteEmployeeJobCode&jobId='+jid;
		            },
		            <spring:message code='BzComposer.global.cancel'/>: function () {
		                $(this).dialog("close");
		                return false;
		            }
		        }
		    });
		    return false;
	}
}

function ClearJobField()
{
	document.getElementById('jcode').value = "";
	document.getElementById('cost').value = "";
	document.getElementById('desc').value = "";
	
	size = document.getElementById('tabsize').value;
	for(i=0;i<size;i++)
	{
		var row1=document.getElementById(i+"$$");
		row1.className = "";
	}
	jid=0;
	document.getElementById('jcode').focus();
}


function disable() 
{
	var value = document.configurationForm.selectedCountryId.value;
	if(value == "2"){
		document.configurationForm.selectedStateId.disabled=false;
	}
	else{
		document.configurationForm.selectedStateId.disabled=true;
	}
}

function disable1() 
{
	var value = document.configurationForm.selectedCountryId1.value;
	if(value == "2")
	{
		document.configurationForm.selectedStateId1.disabled=false;
	}
	else
	{
		document.configurationForm.selectedStateId1.disabled=true;
	}
}

function ShowEditFoootenote()
{
	/*if(window.showModalDialog){
		window.showModalDialog("Configuration?tabid=ShowEditFootnote","BizComposer - Select Footnote to be printed","dialogWidth:600px;dialogHeight:600px");
	}
	else*/
		window.open("Configuration?tabid=ShowEditFootnote",null,"scrollbars=yes,height=400,width=600,status=yes,toolbar=no,menubar=no,location=no,modal=yes");
}	
function showSetupID()
{
	var val = document.getElementById("setupID").value;

		
		if(val == "Location")
		{
			document.getElementById("location").style.display = 'block';
			document.getElementById("message").style.display = 'none';
			document.getElementById("rep").style.display = 'none';
			document.getElementById("terms").style.display = 'none';
			document.getElementById("days").style.display = 'none';
			document.getElementById("salesTax").style.display = 'none';
			document.getElementById("tax").style.display = 'none';
			document.getElementById("creditTerm").style.display = 'none';
			document.getElementById("days1").style.display = 'none';
		}
		else if(val == "Message")
		{
			document.getElementById("location").style.display = 'none';
			document.getElementById("message").style.display = 'block';
			document.getElementById("rep").style.display = 'none';
			document.getElementById("terms").style.display = 'none';
			document.getElementById("days").style.display = 'none';
			document.getElementById("salesTax").style.display = 'none';
			document.getElementById("tax").style.display = 'none';
			document.getElementById("creditTerm").style.display = 'none';
			document.getElementById("days1").style.display = 'none';
		}
		else if(val == "REP")
		{
			document.getElementById("location").style.display = 'none';
			document.getElementById("message").style.display = 'none';
			document.getElementById("rep").style.display = 'block';
			document.getElementById("terms").style.display = 'none';
			document.getElementById("days").style.display = 'none';
			document.getElementById("salesTax").style.display = 'none';
			document.getElementById("tax").style.display = 'none';
			document.getElementById("creditTerm").style.display = 'none';
			document.getElementById("days1").style.display = 'none';
		}
		else if(val == "Terms")
		{
			document.getElementById("location").style.display = 'none';
			document.getElementById("message").style.display = 'none';
			document.getElementById("rep").style.display = 'none';
			document.getElementById("terms").style.display = 'block';
			document.getElementById("days").style.display = 'block';
			document.getElementById("salesTax").style.display = 'none';
			document.getElementById("tax").style.display = 'none';
			document.getElementById("creditTerm").style.display = 'none';
			document.getElementById("days1").style.display = 'none';
		}
		else if(val == "SalesTax")
		{
			document.getElementById("location").style.display = 'none';
			document.getElementById("message").style.display = 'none';
			document.getElementById("rep").style.display = 'none';
			document.getElementById("terms").style.display = 'none';
			document.getElementById("days").style.display = 'none';
			document.getElementById("salesTax").style.display = 'block';
			document.getElementById("tax").style.display = 'block';
			document.getElementById("creditTerm").style.display = 'none';
			document.getElementById("days1").style.display = 'none';
		}
		else if(val == "creditTerm")
		{
			document.getElementById("location").style.display = 'none';
			document.getElementById("message").style.display = 'none';
			document.getElementById("rep").style.display = 'none';
			document.getElementById("terms").style.display = 'none';
			document.getElementById("days").style.display = 'none';
			document.getElementById("salesTax").style.display = 'none';
			document.getElementById("tax").style.display = 'none';
			document.getElementById("creditTerm").style.display = 'block';
			document.getElementById("days1").style.display = 'block';
		}
	}
	
	function setDefaultReason()
	{
		debugger
		var reason = $('#refundReasonSel :selected').text();
		if(reason == "")
		{
			debugger
			alert("<spring:message code='BzComposer.configuration.customerinvoice.selectreasonfromlist'/>");
		}
	}
	
	function setJobTitle()
	{
		debugger
		var jobTitle = $.trim($("#selectedJobTitle option:selected").val());
		debugger
		//$("#txtJobTitle").html(jobTitle);
		document.getElementById("txtjobTitle").value = jobTitle;
		debugger
	}
	
	function setReason()
	{
		debugger
		var oldReason = $('#refundReasonSel option:selected').text();
		$('#refundReasonSel option:selected').remove();
    	document.getElementById("refundReason").value = oldReason;	
	}
	
	function setCategory()
	{
		debugger
		var oldCategory = $('#jobCategory option:selected').text();
		$('#jobCategory option:selected').remove();
    	document.getElementById("txtJobCategory").value = oldCategory;	
	}
	function setDescription()
	{
		var text = $('#location :selected').text();
		document.getElementById("description").value = text;
	}
	
	function setDescription1()
	{
		var text = $('#message :selected').text();
		document.getElementById("description").value = text;
	}
	
	function setDescription2()
	{
		var text = $('#rep :selected').text();
		document.getElementById("description").value = text;
	}
	
	function setDescription3()
	{
		var text = $('#terms :selected').text();
		document.getElementById("description").value = text;
	}
	
	function setDescription4()
	{
		var text = $('#salesTax :selected').text();
		var e = document.getElementById("salesTax");
		var strUser = e.options[e.selectedIndex].value;
		document.getElementById("description").value = text;
		document.getElementById("txtTerms1").value = strUser;
	}
	
	function setDescription5()
	{
		
		var text = $('#creditTerm :selected').text();
		document.getElementById("description").value = text;
		
		var e = document.getElementById("creditTerm");
		var strUser = e.options[e.selectedIndex].value;
		document.getElementById("txtDays").value = strUser;
	}
	
	function AddDescription()
	{
		debugger
		var text = $('#description').val();
		if(text == "" || text == " ")
		{
			alert("<spring:message code='BzComposer.configuration.customerinvoice.emptydata'/>");
		}
	}
	
	function deleteDescription()
	{
		var t =  $('#description').val();
		if(t == "" || t == " ")
		{
			alert("<spring:message code='BzComposer.configuration.customerinvoice.selectitemtodelete'/>");
		}
	}
	
	function updateDescription()
	{
		var t =  $('#description').val();
		if(t == "" || t == " ")
		{
			alert("<spring:message code='BzComposer.configuration.customerinvoice.emptydata'/>");
		}
	}
	
	$(function () 
	{
        $("#allowRefundSetting").click(function () 
		{
            if ($(this).is(":checked")) {
                $("#refundReason").prop('readonly', false);
                $("#refundReasonSel").prop('readonly',false);
                $("#addRefundReason").prop('disabled',false);
                $("#updateRefundReason").prop('disabled',false);
                $("#deleteRefundReason").prop('disabled',false);
                $("#defaultReason").prop('disabled',false);
            } else {
                $("#refundReason").prop('readonly', true);
                $("#refundReasonSel").prop('readonly',true);
                $("#addRefundReason").prop('disabled',true);
                $("#updateRefundReason").prop('disabled',true);
                $("#deleteRefundReason").prop('disabled',true);
                $("#defaultReason").prop('disabled',true);
            }
        });
    });
	
	$(function () {
        $("#addRefundReason").click(function (e) {
        	
        	debugger
        	//var item = $('#refundReasonSel option').val();
        	var r = $("#refundReason").val();
            if (r == '') {
                alert("<spring:message code='BzComposer.configuration.customerinvoice.enterrefundreason'/>");
            }
            else if(r == ' ')
            {
            	alert("<spring:message code='BzComposer.configuration.customerinvoice.whitespaceisnotallowed'/>");
            }
            else
            {
            	var itemExists = false;
                e.preventDefault();
                $("#refundReasonSel option").each(function() {
                    if ($(this).text() == $.trim(r)) {
                        itemExists = true;
                        alert("<spring:message code='BzComposer.configuration.customerinvoice.reasonalreadyexists'/>");
                        $("#refundReason").val('');
                    }
                });

              if (!itemExists) {
              $("#refundReasonSel").append("<option value="+r+" onclick=setReason()>" + r + "</option>");
              $("#refundReason").val('');
              }
            
            }
        });
    });
	
	$(function () {
        $("#updateRefundReason").click(function () 
        {
        	debugger
        	var oldReason = $('#refundReasonSel option:selected').text();
        	var newReason = $('#refundReason').text();
            if (oldReason == '' || oldReason == ' ') {
                alert("<spring:message code='BzComposer.configuration.customerinvoice.selectreasonfromlist'/>");
            }
            else if(oldReason == newReason)
            {
            	alert("<spring:message code='BzComposer.configuration.customerinvoice.samereasoncantupdate'/>");	
            }
            else
            {
            	debugger
            	//var addDisc = $("#refundReason").text();
            	$('#refundReasonSel option:selected').remove();
            	$('#refundReasonSel').append($("<option value="+$('#refundReason').val()+" onclick=setReason()>" + $('#refundReason').val() + "</option>"));
            	/*$('#refundReasonSel').append($("<option>" + newReason + "</option>")); */
            }
        });
    });
	
	$(function () {
        $("#deleteRefundReason").click(function () {
        	debugger
        	var reason = $('#refundReasonSel selected').val();
            if (reason == '') {
                alert("<spring:message code='BzComposer.configuration.customerinvoice.selectreasonfromlist'/>");
            }
            else if(reason == ' ')
            {
            	alert("<spring:message code='BzComposer.configuration.customerinvoice.whitespaceisnotallowed'/>");
            }
            else
            {
            	debugger
            	var con = confirm("<spring:message code='BzComposer.configuration.customerinvoice.removereason'/>");

            	if(con)
            	//$('#refundReasonSel option:selected').remove();
            	$("#refundReason").val('');
            		
            }
        });
    });
	
	$(function () {
        $("#addJobCategory").click(function (e) {
        	debugger
        	var cat = $("#txtJobCategory").val();
        	//var items = $("#jobCategory option").val();
            if (cat == '') {
                alert("<spring:message code='BzComposer.configuration.customerinvoice.enterjobcategory'/>");
            }
            else if(cat == ' ')
            {
            	alert("<spring:message code='BzComposer.configuration.customerinvoice.whitespaceisnotallowed'/>");
            }
            else
            {
            	//$('#jobCategory').append($("<option value="+cat+">" + cat + "</option>"));
            	var itemExists = false;
                //var txt = $("#refundReason").val();
                e.preventDefault();
                $("#jobCategory option").each(function() {
                    if ($(this).text() == $.trim(cat)) {
                        itemExists = true;
                        alert('<spring:message code="BzComposer.configuration.customerinvoice.jobcategoryexists"/>');
                        $("#txtJobCategory").val('');
                    }
                });

              if (!itemExists) {
              $("#jobCategory").append("<option value="+cat+" onclick=setCategory()>" + cat + "</option>");
              $("#txtJobCategory").val('');
              }
            }
        });
    });
	
	$(function () {
        $("#updateJobCategory").click(function () {
        	var oldCategory = $('#jobCategory option:selected').text();
        	var newCategory = $('#txtJobCategory').val();
        	debugger
            if (oldCategory == "") {
                alert("<spring:message code='BzComposer.configuration.customerinvoice.selectjobfromlist'/>");
            }
            else if(oldCategory == ' ')
            {
            	alert("<spring:message code='BzComposer.configuration.customerinvoice.whitespaceisnotallowed'/>");
            }
            else
            {
            	debugger
            	$('#jobCategory option:selected').remove();
            	$('#jobCategory').append($("<option value="+newCategory+" onclick=setCategory()>" + newCategory + "</option>"));
            }
        });
    });
	
	$(function () {
        $("#deleteJobCategory").click(function () {
        	debugger
            if ($("#jobCategory option").text() == "") {
                alert("<spring:message code='BzComposer.configuration.customerinvoice.selectjobfromlist'/>");
            }
            else
            {
            	debugger
            	var con = confirm("<spring:message code='BzComposer.configuration.customerinvoice.removeselectedjob'/>");
            	if(con)
            	//$('#jobCategory option:selected').remove();
            	$("#txtJobCategory").val('');
            }
        });
    });
	
	
	function clearDescription()
	{
		document.getElementById("description").value = "";
	}
	
	function disable() 
	{
		var value = document.configurationForm.selectedCountryId.value;
		if(value == "2")
		{
			document.configurationForm.selectedStateId.disabled=false;
		}
		else
		{
			document.configurationForm.selectedStateId.disabled=true;
		}
	}

</script>
</head>
<!-- <body onload="init1();"> -->
<body onload="init();">
<!-- begin shared/header -->
<form:form name="configurationForm" action="Configuration?" enctype="MULTIPART/FORM-DATA" method="post" id="frmemployee" modelAttribute="configDto">
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">

	<div>
		<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.configuration.configurationtitle"/>
		</span>
	</div>
	<div>
		<div>
			<c:if test="${not empty Labels}">
                <input type="hidden" name="lsize" id="lblsize" value='${Labels.size()}' />
                <c:forEach items="${Labels}" var="lbl" varStatus="loop">
                    <input type="hidden" id='${loop.index}lid' name='${loop.index}lidname' value='${lbl.value}' />
                    <input type="hidden" id='${loop.index}lname' name='${loop.index}lnm' value='${lbl.label}' />
                </c:forEach>
			</c:if>
		</div>
		<div id="table-negotiations"  style="padding: 0; border: 1px solid #ccc;">
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
								<td>
									<input type="button" name="Revoke" class="formButton" onclick="RevokeValues();" value='<spring:message code="BizComposer.Configuration.RevokeButton"/>' />
									<input type="button" name="Save" class="formButton" onclick="SaveValues();" value='<spring:message code="BizComposer.Configuration.SaveButton"/>' />
								</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr> --%>
						</table>
					</td>
					<td valign="top" style="padding-top: 20px;padding-right: 20px; padding-bottom: 20px;">
			
			<!-- Employee Starts-->

			<div id="employee" style="display:none;padding: 0; position: relative; left: 0;">
			<table width="100%">
				<tr>
					<td>
					<table class="table-notifications" width="100%">
						<tr>
							<th colspan="3" align="left" style="font-size:12px; padding: 5px;">
								<spring:message code="BzComposer.configuration.generaltitle" />
							</th>
						</tr>
						<tr>
							<td style="font-size:12px;">
								<spring:message code="BzComposer.global.state" /> :
							</td>
							<td id="state" style="font-size:12px;">
								<form:select path="selectedStateId">
									<c:if test="${not empty configDto.listOfExistingState}">
                                        <c:forEach items="${configDto.listOfExistingState}" var="objList1">
                                            <option value="${objList1.selectedStateId}">${objList1.stateName}</option>
                                        </c:forEach>
                                    </c:if>
								</form:select>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td style="font-size:12px;">
								<spring:message code="BzComposer.global.country" /> :
							</td>
							<td style="font-size:12px;">
								<form:select path="selectedCountryId" onchange="disable()">
									<option value="0"></option>
									<c:if test="${not empty configDto.listOfExistingCountry}">
                                        <c:forEach items="${configDto.listOfExistingCountry}" var="objList1">
                                            <option value="${objList1.countryId}">${objList1.countryName}</option>
                                        </c:forEach>
                                    </c:if>
								</form:select>
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<th colspan="3" align="left" style="font-size:12px; padding: 5px;">
								<spring:message code="BzComposer.configuration.jobcodetimesheet" />
							</th>
						</tr>
						<tr>
							<td style="font-size:12px;">
								<spring:message code="BzComposer.configuration.jobcode" />* :
							</td>
							<td colspan="2" style="font-size:12px;">
								<input type="text" size="35" name="jobcode" id="jcode" maxlength="35" />
							</td>
						</tr>
						<tr>
							<td style="font-size:12px;">
								<spring:message code="BzComposer.configuration.jobcost" />* :
							</td>
							<td colspan="2" style="font-size:12px;">
								<input type="text" size="20" name="jobcost" id="cost" onkeypress="return numbersonly(event,this.value);" maxlength="10" />
							</td>
						</tr>
						<tr>
							<td style="font-size:12px;">
								<spring:message code="BzComposer.configuration.description" /> :
							</td>
							<td colspan="2" style="font-size:12px;">
								<input type="text" size="45" name="jobdesc" id="desc" maxlength="60" />
							</td>
						</tr>
					</table>
					</td>
				<tr>
					<td align="center" style="font-size:14px;">
					<div style="overflow:auto;height:200;" id="jobCodeTime" class="section-border">
					<table class="tabla-listados" cellspacing="0" border="1">
						<thead>
							<tr>
								<th class="emblem" style="font-size:12px;">
									<spring:message code="BzComposer.configuration.job" />
								</th>
								<th class="emblem" style="font-size:12px;">
									<spring:message code="BzComposer.configuration.cost" />
								</th>
								<th class="emblem" style="font-size:12px;">
									<spring:message code="BzComposer.configuration.description" />
								</th>
							</tr>
						</thead>
						<tbody style="font-size:14px;">
							<c:if test="${not empty JobCodeDetail}">
								<input type="hidden" id="tabsize" name="size" value='${JobCodeDetail.size()}' />
								<c:forEach items="${JobCodeDetail}" var="jobcode" varStatus="loop">
                                    <tr id='${loop.index}$$' onclick="setJobValues('${jobcode.jobCodeID}','${jobcode.job}',
                                            '${jobcode.cost}','${jobcode.description}','${loop.index}$$');">
                                        <td>${jobcode.job}</td>
                                        <td align="left">${jobcode.cost}</td>
                                        <td>&nbsp;${jobcode.description}</td>
                                    </tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td style="font-size:14px;">
						<table class="table-notifications" width="100%">
							<tr align="center">
								<td colspan="3" style="font-size:14px;">
									<button type="button" class="formButton" name="add" onclick="AddJobCode();">
									    <spring:message code="BzComposer.global.add"/>
									</button>
									<button type="button" class="formButton" name="edit" onclick="EditJobCode();">
									    <spring:message code="BzComposer.global.edit"/>
								    </button>
									<button type="button" class="formButton" name="remove" onclick="RemoveJobCode();">
									    <spring:message code="BzComposer.configuration.remove"/>
									</button>
									<button type="button" class="formButton" name="clear" onclick="ClearJobField();">
									    <spring:message code="BzComposer.global.clear"/>
									</button>
								</td>
							</tr>
							<tr>
								<th colspan="3" align="left" style="font-size:12px; padding: 5px;">
									<spring:message code="BzComposer.configuration.timesheet" />
								</th>
							</tr>
							<tr>
								<td colspan="3" style="font-size:12px;">
									<spring:message code="BzComposer.configuration.noofinouttimesheet" /> :
									<form:input path="timeSheet" style="width:100;" maxlength="10"
										onkeypress="return numbersonly(event,this.value);" />
								</td>
							</tr>
							<tr>
								<th colspan="3" align="left" style="font-size:12px; padding: 5px;">
									<spring:message code="BzComposer.configuration.jobtitlesettings"/> :
								</th>
							</tr>
							<tr>
								<td colspan="3" style="font-size:14px;">
									<spring:message code="BzComposer.configuration.jobtitle"/> :
									&nbsp;&nbsp;<form:input id="txtjobTitle" path="jobTitleName" class="" autoComplete="off" />
									<!-- <input type="text" id="txtJobTitle" /> -->
								</td>
							</tr>
							<tr>
								<td style="font-size:12px;">
									<form:select id="selectedJobTitle" path="selecctedJobTitleId" multiple="multiple" style="width:300px;hieight:500px;" onclick="setJobTitle()">
										<c:if test="${not empty configDto.listOfJobTitle}">
											<c:forEach items="${configDto.listOfJobTitle}" var="objList1">
												<option id="${objList1.jobTitleId}" value="${objList1.jobTitleName}">${objList1.jobTitleName}</option>
											</c:forEach>
										</c:if>
									</form:select>
								</td>
								<td style="font-size:14px;">
									<button type="button" class="formButton" name="add" onclick="AddJobTitle();">
									    <spring:message code="BzComposer.global.add"/>
									</button>
									<button type="button" class="formButton" name="edit" onclick="EditJobTitle();">
									    <spring:message code="BzComposer.global.edit"/>
									</button>
									<button type="button" class="formButton" name="delete" onclick="DeleteJobTitle()">
									    <spring:message code="BzComposer.global.delete"/>
									</button>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</div>
			<!-- Employee Ends -->
			
			</td>
		</tr>
	</table>
	<div><form:hidden path="empStateID" />
	<form:hidden path="labelName" />
	<form:hidden path="fileName" /></div>
	<div><input type="hidden" name="tabid" id="tid" value="" />
	</div>
	</div>
	<div>
		<center>
		    <input type="button" class="bottomButton formButton" onclick="saveValue();" value="<spring:message code='BzComposer.global.save'/>" />
		    <input type="reset" class="bottomButton formButton" value="<spring:message code='BzComposer.global.cancel'/>" />
		</center>
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
<script type="text/javascript">
function emptyjobcodedialog()
{
	event.preventDefault();
	$("#emptyjobcodedialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function bigcostvalidationdialog()
{
	event.preventDefault();
	$("#bigcostvalidationdialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function selectrowdialog()
{
	event.preventDefault();
	$("#selectrowdialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function emptyjobtitledialog()
{
	event.preventDefault();
	$("#emptyjobtitledialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function duplicatejobtitledialog()
{
	event.preventDefault();
	$("#duplicatejobtitledialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                $(this).dialog("close");
            }
        }
    });
    return false;
}
function selectjobcodedialog()
{
	event.preventDefault();
	$("#selectjobcodedialog").dialog({
    	resizable: false,
        height: 200,
        width: 350,
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
<!-- Dialog box used in this page -->
<div id="emptyjobcodedialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.employee.jobcodeemptyvalidation"/></p>
</div>
<div id="bigcostvalidationdialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.employee.costgreatervalidation"/></p>
</div>
<div id="selectrowdialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.employee.selectrowfirst"/></p>
</div>
<div id="emptyjobtitledialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.employee.emptyjobtitlevalidation"/></p>
</div>
<div id="duplicatejobtitledialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.employee.duplicatejobtitleaddvalidation"/></p>
</div>
<div id="selectjobcodedialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.employee.selectjobcodevalidation"/></p>
</div>
<div id="removejobcodedialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.employee.removejobcodeconfirm"/></p>
</div>
<div id="editjobcodedialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.employee.editjobcodeconfirm"/></p>
</div>
<div id="addjobcodedialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.employee.addjobcodeconfirm"/></p>
</div>
<!-- Dialog box used in this page -->
<div id="showsuccessdialog" style="display:none;">
	<p>Record updated</p>
</div>