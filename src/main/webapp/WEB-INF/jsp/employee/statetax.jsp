<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html xmlns="http://www.w3.org/1999/xhtml" version="-//W3C//DTD XHTML 1.1//EN" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<%@include file="../include/headlogo.jsp"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/menu.jsp"%>
<script>
window.onload = initShowHideDivs;
</script>
<script type="text/javascript">
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
<script>
window.onload = initShowHideDivs;
</script>
<title><spring:message code="BzComposer.statetaxinfotitle" /></title>
</head>
<body>
<form:form action="/StateTax" method="post" name="StateTaxForm">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div>
	<span style="font-size: 1.6em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; 
	border-bottom: 1px #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.statetax.statetaxinformation" />
	</span>
</div>
<div id="table-negotiations">
<table class="tabla-listados" cellspacing="0">
	<tr>
		<td>
			<c:if test="${StTaxlist != null}">
				<c:if test="${not empty StTaxlist}">
					<c:forEach items="${StTaxlist}" var="objList">
						<table class="tabla-listados" cellspacing="0">
							<thead>
								<tr>
									<th class="emblem" colspan="5" style="font-size:1.6em;">

										<spring:message code="BzComposer.statetax.identification"/>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td align="right" style="font-size:1em;">
										<spring:message code="BzComposer.statetax.filingstate" />
									</td>
									<td width="20" style="font-size:1em;">
										<div id="StList" style="display: block;">
                                        	<select name="flSt" onchange="getTaxInfo(this);" onkeypress="editStateInfo(this);">
                                        		<c:forEach items="${Statelist}" var="stateObj">
                                                    <option value="${stateObj.flSt}"><c:out value="${stateObj.flSt}"/></option>
                                        		</c:forEach>
                                        	</select>
                                        </div>
										<div id="newSt" style="display: none;font-size:1em;">
											<input type="text" name="fstate" size="6" />
										</div>
									</td>
									<td align="right" style="font-size:1em;">
										<spring:message code="BzComposer.statetax.statetaxid" />:
									</td>
									<td align="left" style="font-size:1em;">
										<input type="text" value="${objList.stTaxId}" size="12" />
									</td>
								</tr>
							</tbody>
						</table>
					</td>
					<td rowspan="3" valign="top">
						<table class="tabla-listados" cellspacing="0">
							<thead>
								<tr>
									<th class="emblem" style="font-size:1.6em;">
										<spring:message code="BzComposer.statetax.fillingstatelist"/>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
								<!--	<td align="center" height="25">
										<select name="flist" size="19" ondblclick="getTaxInfo1()" style="font-size:1em;">
											<c:if test="${Statelist != null}">
												<c:if test="${not empty Statelist}">
													<c:forEach items="${Statelist}" var="objList1">
														<c:if test="${objList1.flSt != requestScope.stval}">
															<option value='${objList1.flSt}" />'>
																${objList1.flSt}
															</option>
														</c:if>
														<c:if test="${objList1.flSt == requestScope.stval}">
															<option selected="selected" value='${objList1.flSt}'>
																${objList1.flSt}
															</option>
														</c:if>
													</c:forEach>
												</c:if>
											</c:if>
										</select>
									</td>-->
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="tabla-listados" cellspacing="0">
							<thead>
								<tr>
									<th class="emblem" colspan="4" style="font-size:1.6em;">
										<spring:message code="BzComposer.statetax.taxinformationofstate"/>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td align="left" colspan="4" style="font-size:1em;">
										<spring:message code="BzComposer.statetax.stateincometax" />:
									</td>
								</tr>
								<tr>
									<td align="right" style="font-size:1em;">
									    <c:if test="${objList.sitVal == 'true'}">
											<input type="checkbox" name="sitVal" checked="true" value="ON" />
										</c:if>
										<c:if test="${objList.sitVal == 'false'}">
											<input type="checkbox" name="sitVal" />
										</c:if>
									</td>
									<td align="left">
										<input type="text" value="${objList.sitName}" size="12" style="font-size:1em;"/>
									</td>
									<td colspan="2" style="font-size:1em;">
										<spring:message code="BzComposer.statetax.usetable"/>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="4" style="font-size:1em;">
										<spring:message code="BzComposer.statetax.otherstatetax"/>:
									</td>
								</tr>
								<tr>
									<td align="right" style="font-size:1em;">
									    <c:if test="${objList.othVal1 == 'true'}">
											<input type="checkbox" name="othVal1" checked="true" value="ON" />
										</c:if>
										<c:if test="${objList.othVal1 == 'false'}">
											<input type="checkbox" name="othVal1" />
										</c:if>
									</td>
									<td align="left" style="font-size:1em;">
										<input type="text" value="${objList.othName1}" size="12" />
									</td>
									<td style="font-size:1em;">
										<spring:message code="BzComposer.statetax.rate"/>
										<input type="text" value="${objList.othRate1}" size="12" onkeypress="return numbersonly(event,this.value)" />
									</td>
									<td style="font-size:1em;">
										<spring:message code="BzComposer.statetax.upto"/> 
										<input type="text" value="${objList.othUpto}" size="12" onkeypress="return numbersonly(event,this.value)"/>
									</td>
								</tr>
								<tr>
									<td align="right" style="font-size:1em;">
									    <c:if test="${objList.othVal2 == 'true'}">
											<input type="checkbox" name="othVal2" checked="true" value="ON" />
										</c:if>
										<c:if test="${objList.othVal2 == 'false'}">
											<input type="checkbox" name="othVal2" />
										</c:if>
									</td>
									<td align="left" style="font-size:1em;">
										<input type="text" value="${objList.othName2}" size="12" />
									</td>
									<td colspan="2" style="font-size:1em;">
										<spring:message code="BzComposer.statetax.rate" />
										<input type="text" value="${objList.othRate2}" size="12"
										onkeypress="return numbersonly(event,this.value)" />
									</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
				</c:if>
			</c:if>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>
			<table class="tabla-listados" cellspacing="0">
				<tbody>
					<tr>
						<td colspan="4" align="center" style="font-size:1em;">
							<input type="button" value="<spring:message code='BzComposer.statetax.clearbtn'/>"
							onclick="clearTaxInfo();" class="formbutton">
							&nbsp;&nbsp;&nbsp;
							<input type="button" value="<spring:message code='BzComposer.statetax.savestatetaxbtn'/>"
							onclick="setTaxInfo();" class="formbutton">
							&nbsp;&nbsp;&nbsp;
							<input type="button" value="<spring:message code='BzComposer.global.delete'/>"
							onclick="deleteTaxInfo();" class="formbutton">
						</td>
					</tr>
				</tbody>
			</table>
		</td>
		<td></td>
	</tr>
</table>
</div>
</div>
<!-- end Contents -->
</div>
</div>
</div>
</div>
<%@ include file="../include/footer.jsp"%></div>
</form:form>
</body>
</html>
<script>
function setTaxInfo()
{
	document.forms['StateTaxForm'].action = "StateTax?tabid=s2t2a2";
	document.forms['StateTaxForm'].submit();
}
function deleteTaxInfo()
{
	document.forms['StateTaxForm'].action = "StateTax?tabid=s3t3a3";
	document.forms['StateTaxForm'].submit();
}
function clearTaxInfo()
{
	document.forms['StateTaxForm'].action = "StateTax?tabid=s0t0a0";
	document.forms['StateTaxForm'].submit();
}
function getTaxInfo(obj)
{
	if(obj.value=="")
	{   document.forms['StateTaxForm'].action = "StateTax?tabid=s0t0a0";
		document.forms['StateTaxForm'].submit();
	}
	else
	{
		document.forms['StateTaxForm'].action = "StateTax?tabid=s1t1a1";
		document.forms['StateTaxForm'].submit();
	}
}
function getTaxInfo1()
{
	document.StateTaxForm.flSt.value=document.StateTaxForm.flist.value;
	document.forms['StateTaxForm'].action = "StateTax?tabid=s1t1a1&ckDb=y";
	document.forms['StateTaxForm'].submit();
}
function editStateInfo(obj)
{
	function hide_div(div_id) 
	{
		// hide this div
		document.getElementById(div_id).style.display = "none";
	}
	function show_div(div_id) 
	{
		// hide this div
		document.getElementById(div_id).style.display = "block";
	}
	if(obj.value=="")
	{   
		hide_div('StList');show_div('newSt');
	}
	else
	{
		hide_div('newSt');show_div('StList');
	}
}
</script>