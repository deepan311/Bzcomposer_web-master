<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/include/header.jsp"%>
<title><spring:message code="BzComposer.editfootnotetitle"/></title>
<script type="text/javascript">
self.moveTo(100,100);
</script>
<style type="text/css">
table.tabla-listados tbody tr td 
{
	padding:6px 16px 5px 14px !important;
}
</style>
</head>
<body onunload="closeWin();">
<form:form name="configurationForm" action="Configuration?tabid=ShowEditFootnote" method="post" modelAttribute="configDto">
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div>
	<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; 
	border-bottom: 1px dotted #333; padding: 0 0 .3em 0;"></span>
</div>
<div style="width: 100%;">
	<table cellspacing="0">
		<c:if test="${not empty Status}">
			<tr>
				<td>
					<font color="blue">
						<strong> ${Status} </strong>
					</font>
				</td>
			</tr>
		</c:if>
		<tr>
			<td>
				<table cellspacing="0" border="0" cellpadding="0">
					<tr>
						<td valign="top" onclick="HideList();" style="font-size:1em;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<spring:message code="BzComposer.configuration.footnoteselection" />
						</td>
						<td>
							&nbsp;&nbsp; 
							<input type="text" style="width:190;font-size: 1em;" maxlength="40" id="txt" name="FootName" /> 
							<img src='images/downArrow.jpg' onclick="getList();" />
							<div id="footid" style="display:none;font-size: 1em;">
								&nbsp;&nbsp;
								<c:if test="${not empty FoootNoteDetails}">
							 		<form:select path="footnote" style="width:210;" onchange="SelectFootnote(this.value);" size="4" onkeydown="SelectFootnote(this.value);">
										<form:option value="0">
											<spring:message code="BzComposer.ComboBox.Select" />
										</form:option>
										<form:options items="${FoootNoteDetails}" itemValue="footnote" itemLabel="footnoteName" />
									</form:select>
								</c:if>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table class="tabla-listados" cellspacing="0" border="0">
					<thead>
						<tr>
							<th align="left" style="font-size:1.2em;">
								<spring:message code="BzComposer.configuration.description" /> :
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="font-size:1em;">
								<spring:message code="BzComposer.configuration.termmessage" />
							</td>
						</tr>
						<tr>
							<td style="font-size:1em;">
								<spring:message code="BzComposer.configuration.enterkeymessage" />
							</td>
						</tr>
						<tr>
							<td style="font-size:1em;">
								<form:textarea path="desc" rows="10" cols="80"></form:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr onclick="HideList();">
			<td>
				<div align="center" style="font-size:1em;">
					<input type="button" name="clear" class="formButton" onclick="ClearValues();" value='<spring:message code="BzComposer.global.clear"/>' />
					<input type="button" name="Save" class="formButton" onclick="SaveFootnote();" value='<spring:message code="BzComposer.global.save"/>' />
					<input type="button" name="Delete" class="formButton" onclick="DeleteFootnote();" value='<spring:message code="BzComposer.global.delete"/>' />
					<input type="button" name="Close" class="formButton" onclick="CloseWindow();" value='<spring:message code="BzComposer.global.close"/>' />
				</div>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</div>
</div>
</div>
<div id="FootnoteValues">
	<c:if test="${not empty FoootNoteDetails}">
        <input type="hidden" id="size" value='${FoootNoteDetails.size()}' name="FootSize" />
        <c:forEach items="${FoootNoteDetails}" var="detail" varStatus="loop">
            <input type="hidden" id='fid${loop.index}' name='fidname${loop.index}' value='${detail.footnote}' />
            <input type="hidden" id='fname${loop.index}' name='fnameName${loop.index}' value='${detail.footnoteName}' />
            <input type="hidden" id='desc${loop.index}' name='fdesc${loop.index}' value='${detail.desc}' />
        </c:forEach>
	</c:if>
</div>
<div id="vfoot" style="display:none;">
	<c:if test="${not empty Footnote}">
		<form:select path="vendorDefaultFootnoteID" style="width:100;">
			<form:option value="0">
				<spring:message code="BzComposer.ComboBox.Select" />
			</form:option>
			<form:options items="${Footnote}" itemValue="value" itemLabel="label" />
		</form:select>
	</c:if>
</div>
<div id="foot" style="display:none;">
	<c:if test="${not empty Footnote}">
		<form:select path="defaultFootnoteID" style="width:100;">
			<form:option value="0">
				<spring:message code="BzComposer.ComboBox.Select" />
			</form:option>
			<form:options items="${Footnote}" itemValue="value" itemLabel="label" />
		</form:select>
	</c:if>
</div>
<div>
	<input type="hidden" name="tabid" id="tid" value="" />
</div>
<!-- end Contents -->
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
<script>	
function CloseWindow()
{
		window.opener.document.getElementById('dfoot').innerHTML=document.getElementById('foot').innerHTML;
		window.opener.document.getElementById('vdfoot').innerHTML=document.getElementById('vfoot').innerHTML;
		window.close();
}
	
function getList()
{
		if(document.getElementById('footid').style.display=='block')
		{
			document.getElementById('footid').style.display='none';
		}
		else
			document.getElementById('footid').style.display='block';
}
function SelectFootnote(fid)
{
		if(fid==0)
		{
			document.configurationForm.desc.value = "";
			document.getElementById('txt').value = '<spring:message code="BzComposer.ComboBox.Select" />';
		}
		else
		{
			fsize = document.getElementById('size').value;
			for(cnt=0;cnt<fsize;cnt++)
			{
				id = document.getElementById('fid'+cnt).value;
				if(id==fid)
				{
					document.configurationForm.desc.value = document.getElementById('desc'+cnt).value;
					document.getElementById('txt').value = document.getElementById('fname'+cnt).value;
					break;
				}
			}
		}
		document.getElementById('footid').style.display='none';
}
	
function ClearValues()
{
		HideList();
		document.configurationForm.footnote.value=0;
		document.getElementById('footid').style.display='none';
		document.getElementById('txt').value = "";
		document.configurationForm.desc.value = "";
		document.getElementById('txt').focus();
}
	
function HideList()
{
		document.getElementById('footid').style.display='none';
}
	
function DeleteFootnote()
{
		HideList();
		id = document.configurationForm.footnote.value;
		if(id==0)
		{

			return emptyfootnotevalidationdialog();
			document.getElementById('txt').focus();
		}
		else
		{
			/* if(confirm('<spring:message code="BzComposer.configuration.editfootnote.deleteconfirm"/>'))
			{
				document.getElementById('tid').value="DeleteFootnote";
				document.forms[0].action="Configuration.do";
				document.forms[0].submit();
			} */
			event.preventDefault();
			$("#showdeleterecorddialog").dialog({
			    	resizable: false,
			        height: 200,
			        width: 500,
			        modal: true,
			        buttons: {
			        	"<spring:message code='BzComposer.global.ok'/>": function () {
			            	debugger;
			            	document.getElementById('tid').value="UpdateFootnote";
			        		document.forms[0].action="Configuration.do";
			        		document.forms[0].submit();
							//$('form').submit();
			            },
			            "<spring:message code='BzComposer.global.cancel'/>": function () {
			                $(this).dialog("close");
			                return false;
			            }
			        }
			    });
			    return false;
		}	
}
	
function closeWin()
{
		HideList();
		window.opener.document.getElementById('dfoot').innerHTML=document.getElementById('foot').innerHTML;
		window.opener.document.getElementById('vdfoot').innerHTML=document.getElementById('vfoot').innerHTML;
}
	
function trim(inputString) 
{
	   // Removes leading carriage return from the passed string. Also removes
	   // consecutive carriage return.
	   var retValue = inputString;
	   var ch = retValue.substring(0, 1);
	   while (ch == "\n" || ch == "\r" || ch==" " || ch=="\t" ) 
	   { 
		   // Check for carriage return at the beginning of the string
	      	retValue = retValue.substring(1, retValue.length);
	      	ch = retValue.substring(0, 1);
	   }
	   return retValue; 
}

function SaveFootnote()
{
		HideList();
		fid = document.configurationForm.footnote.value;
		txtv = document.getElementById('txt').value.toLowerCase();
		
		if(txtv=='select')
		{

			return emptyfootnotevalidationdialog();
			document.getElementById('txt').focus();
		}
		else
		{
			txtval = trim(document.getElementById('txt').value);
			if(txtval=="")
			{

				return emptyfootnotevalidationdialog();
				document.getElementById('txt').focus();
			}
			else
			{
				if(fid==0)
				{
					Save();
				}
				else
				{
					var footnm = "";
					fsize = document.getElementById('size').value;
					for(cnt=0;cnt<fsize;cnt++)
					{
						id = document.getElementById('fid'+cnt).value;
						if(id==fid)
						{
							footnm = document.getElementById('fname'+cnt).value;			
							break;
						}
					}
				
					if(footnm==txtval)
					{
						Update();
					}
					else
					{
						Save();
					}
				}
			}
		}
}
	
function Save()
{
	/* if(confirm('<spring:message code="BzComposer.configuration.editfootnote.saveconfirm" />'))
	{
		document.getElementById('tid').value="SaveFootnote";
		document.forms[0].action="Configuration.do";
		document.forms[0].submit();
	} */
	
	event.preventDefault();
	$("#showsaverecorddialog").dialog({
	    	resizable: false,
	        height: 200,
	        width: 500,
	        modal: true,
	        buttons: {
	        	"<spring:message code='BzComposer.global.ok'/>": function () {
	            	debugger;
	            	document.getElementById('tid').value="SaveFootnote";
	        		document.forms[0].action="Configuration.do";
	        		document.forms[0].submit();
					//$('form').submit();
	            },
	            "<spring:message code='BzComposer.global.cancel'/>": function () {
	                $(this).dialog("close");
	                return false;
	            }
	        }
	    });
	    return false;
	
}
	
function Update()
{
	/* if(confirm('<spring:message code="BzComposer.configuration.editfootnote.updateconfirm" />'))
	{
		document.getElementById('tid').value="UpdateFootnote";
		document.forms[0].action="Configuration.do";
		document.forms[0].submit();
	} */
	event.preventDefault();
	$("#showupdaterecorddialog").dialog({
	    	resizable: false,
	        height: 200,
	        width: 500,
	        modal: true,
	        buttons: {
	        	"<spring:message code='BzComposer.global.ok'/>": function () {
	            	debugger;
	            	document.getElementById('tid').value="UpdateFootnote";
	        		document.forms[0].action="Configuration.do";
	        		document.forms[0].submit();
					//$('form').submit();
	            },
	            "<spring:message code='BzComposer.global.cancel'/>": function () {
	                $(this).dialog("close");
	                return false;
	            }
	        }
	    });
	    return false;
}
function emptyfootnotevalidationdialog()
{
	event.preventDefault();
	$("#emptyfootnotevalidationdialog").dialog({
    	resizable: false,
        height: 200,
        width: 500,
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
</html>
<!-- Dialog box used in this page -->
<div id="showsaverecorddialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.saveconfirm"/></p>
</div>
<div id="showupdaterecorddialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.editfootnote.updateconfirm"/></p>
</div>
<div id="emptyfootnotevalidationdialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.editfootnote.footnoteemptyvalidation"/></p>
</div>
<div id="showdeleterecorddialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.editfootnote.deleteconfirm"/></p>
</div>
<%-- <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/include/header.jsp"%>
<title>BzComposer-EditFootNote</title>
<script type="text/javascript">
self.moveTo(100,100);
</script>
<style type="text/css">
table.tabla-listados tbody tr td 
{
	padding:6px 16px 5px 14px !important;
}
</style>
</head>
<body onunload="closeWin();">
<form:form name="configurationForm" action="Configuration?tabid=ShowEditFootnote" method="post" modelAttribute="configDto">
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
	<div>
		<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;"></span>
	</div>
	<div style="width: 100%;">
		<table cellspacing="0">
			<c:if test="${not empty Status}">
				<tr>
					<td>
						<font color="blue"> <strong> ${Status} </strong></font>
					</td>
				</tr>
			</c:if>
			<tr>
				<td>
					<table cellspacing="0" border="0" cellpadding="0">
						<tr>
							<td valign="top">
								<spring:message code="BizComposer.Configuration.EditFootnote.FootnoteSelection" />
							</td>
							<td>
								<div style="width:190;">
									<form:select path="footnote" onchange="SelectFootnote(this.value);">
										<form:option value="0">
											<spring:message code="BzComposer.ComboBox.Select" />
										</form:option>
										<c:if test="${not empty configDto.footnote}">
											<form:options items="${FoootNoteDetails}" itemValue="footnote" itemLabel="footnoteName"/>
										</c:if>
									</form:select>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="tabla-listados" cellspacing="0" border="0">
						<thead>
							<tr>
								<th align="left">
									<spring:message code="BizComposer.Configuration.EditFootnote.Description" />
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<spring:message code="BizComposer.Configuration.EditFootnote.TermMsg" />
								</td>
							</tr>
							<tr>
								<td>
									<spring:message code="BizComposer.Configuration.EditFootnote.EnterKeyMsg" />
								</td>
							</tr>
							<tr>
								<td>
									<form:textarea path="desc" rows="10" cols="80"></form:textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr onclick="HideList();">
				<td>
					<div align="center">
						<input type="button" name="clear" class="formButton" onclick="ClearValues();" value='<spring:message code="BizComposer.Configuration.EditFootnote.Clear"/>' />
						<input type="button" name="Save" class="formButton" onclick="SaveFootnote();" value='<spring:message code="BizComposer.Configuration.EditFootnote.Save"/>' />
						<input type="button" name="Delete" class="formButton" onclick="DeleteFootnote();" value='<spring:message code="BizComposer.Configuration.EditFootnote.Delete"/>' />
						<input type="button" name="Close" class="formButton" onclick="CloseWindow();" value='<spring:message code="BizComposer.Configuration.EditFootnote.Close"/>' />
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>
</div>
</div>
</div>
</div>
	<div id="FootnoteValues">
		<c:if test="${not empty FoootNoteDetails}">
            <input type="hidden" id="size" value='${FoootNoteDetails.size()}' name="FootSize" />
            <c:forEach items="${FoootNoteDetails}" var="detail" varStatus="loop">
                <input type="hidden" id='fid${loop.index}' name='fidname${loop.index}' value='${detail.footnote}' />
                <input type="hidden" id='fname${loop.index}' name='fnameName${loop.index}' value='${detail.footnoteName}' />
                <input type="hidden" id='desc${loop.index}' name='fdesc${loop.index}' value='${detail.desc}' />
            </c:forEach>
		</c:if>
	</div>
	<div id="vfoot" style="display:none;">
		<c:if test="${not empty Footnote}">
			<form:select path="vendorDefaultFootnoteID" style="width:100;">
				<form:option value="0">
					<spring:message code="BzComposer.ComboBox.Select" />
				</form:option>
				<form:options items="${Footnote}" itemValue="value" itemLabel="label" />
			</form:select>
		</c:if>
	</div>
	<div id="foot" style="display:none;">
		<c:if test="${not empty Footnote}">
			<form:select property="defaultFootnoteID" style="width:100;">
				<form:option value="0">
					<spring:message code="BzComposer.ComboBox.Select" />
				</form:option>
				<form:options items="${Footnote}" itemValue="value" itemLabel="label" />
			</form:select>
		</c:if>
	</div>
	<div>
		<input type="hidden" name="tabid" id="tid" value="" />
	</div>
	<!-- end Contents -->
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
<script>
	function CloseWindow(){
		window.opener.document.getElementById('dfoot').innerHTML=document.getElementById('foot').innerHTML;
		window.opener.document.getElementById('vdfoot').innerHTML=document.getElementById('vfoot').innerHTML;
		window.close();
	}
	
	function getList(){
		if(document.getElementById('footid').style.display=='block'){
			document.getElementById('footid').style.display='none';
		}
		else
			document.getElementById('footid').style.display='block';
		
	}
	function SelectFootnote(fid){
		if(fid==0){
			document.configurationForm.desc.value = "";
			document.getElementById('txt').value = '<spring:message code="BzComposer.ComboBox.Select" />';
		}
		else{
			fsize = document.getElementById('size').value;
			for(cnt=0;cnt<fsize;cnt++){
				id = document.getElementById('fid'+cnt).value;
				if(id==fid){
					document.configurationForm.desc.value = document.getElementById('desc'+cnt).value;
					document.getElementById('txt').value = document.getElementById('fname'+cnt).value;
					break;
				}
			}
		}
		document.getElementById('footid').style.display='none';
	}
	
	function ClearValues(){
		HideList();
		document.configurationForm.footnote.value=0;
		document.getElementById('footid').style.display='none';
		document.getElementById('txt').value = "";
		document.configurationForm.desc.value = "";
		document.getElementById('txt').focus();
	}
	
	function HideList(){
		document.getElementById('footid').style.display='none';
	}
	
	function DeleteFootnote(){
		HideList();
		id = document.configurationForm.footnote.value;
		if(id==0){
			alert('<spring:message code="BizComposer.Configuration.EditFootnote.Delete.FootnoteEmptyValidation"/>');
			document.getElementById('txt').focus();
		}
		else{
			if(confirm('<spring:message code="BizComposer.Configuration.EditFootnote.DeleteConfirm"/>')){
				document.getElementById('tid').value="DeleteFootnote";
				document.forms[0].action="Configuration.do";
				document.forms[0].submit();
			}
		}
		
	}
	
	function closeWin(){
		HideList();
		window.opener.document.getElementById('dfoot').innerHTML=document.getElementById('foot').innerHTML;
		window.opener.document.getElementById('vdfoot').innerHTML=document.getElementById('vfoot').innerHTML;
	}
	
	function trim(inputString) {
	   // Removes leading carriage return from the passed string. Also removes
	   // consecutive carriage return.
	   var retValue = inputString;
	   var ch = retValue.substring(0, 1);
	   while (ch == "\n" || ch == "\r" || ch==" " || ch=="\t" ) { // Check for carriage return at the beginning of the string
	      retValue = retValue.substring(1, retValue.length);
	      ch = retValue.substring(0, 1);
	   }
	   return retValue; 
	}
	
	
	function SaveFootnote(){
		HideList();
		fid = document.configurationForm.footnote.value;
		txtv = document.getElementById('txt').value.toLowerCase();
		
		if(txtv=='select'){
			alert('<spring:message code="BizComposer.Configuration.EditFootnote.Delete.FootnoteEmptyValidation"/>')
			document.getElementById('txt').focus();
		}
		else{
			txtval = trim(document.getElementById('txt').value);
			if(txtval==""){
				alert('<spring:message code="BizComposer.Configuration.EditFootnote.Delete.FootnoteEmptyValidation"/>')
				document.getElementById('txt').focus();
			}
			else{
				if(fid==0){
					Save();
				}
				else{
					var footnm = "";
					fsize = document.getElementById('size').value;
					for(cnt=0;cnt<fsize;cnt++){
						id = document.getElementById('fid'+cnt).value;
						if(id==fid){
							footnm = document.getElementById('fname'+cnt).value;			
							break;
						}
					}
				
					if(footnm==txtval){
						Update();
					}
					else{
						Save();
					}
				}
			}
		}
	}
	
	function Save(){
		if(confirm('<spring:message code="BizComposer.Configuration.EditFootnote.SaveConfirm" />')){
			document.getElementById('tid').value="SaveFootnote";
			document.forms[0].action="Configuration.do";
			document.forms[0].submit();
		}
	}
	
	function Update(){
		if(confirm('<spring:message code="BizComposer.Configuration.EditFootnote.UpdateConfirm" />')){
			document.getElementById('tid').value="UpdateFootnote";
			document.forms[0].action="Configuration.do";
			document.forms[0].submit();
		}
	}
</script>
</html> --%>