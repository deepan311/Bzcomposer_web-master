<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.federaltaxinfotitle" /></title>
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
function setTaxInfo()
{
	var fid=document.forms[0].fdTaxId.value;
	var fmonth=document.forms[0].fcMonth.value;
	if(fid==null||fid=="")
	{


	   alert("<spring:message code='BzComposer.federaltax.federaltaxidisrequired'/>");
	   return false;
	}
	if(fmonth==null||fmonth=="")
	{


	   alert("<spring:message code='BzComposer.federaltax.fiscalmonthisrequired'/>");
	   return false;
	}
	document.forms[0].action = "fedTax?tabid=f1e1d1";
	document.forms[0].submit();
}

function setRate(obj)
{
	var str=obj.value;
	var len=0;
	len=str.indexOf("%");
	len++;
	var len1=0;
	len1=str.indexOf(".");
	len1++;
	var len2=0;
	len2=str.length-len1;
	if(len==0)
	{
		if(len1==0)
			obj.value="%"+str+".00";
		else
			obj.value="%"+str;
	}
	var st1=substring(len,len2);
	//str2=substring(len);

}
</script>
</head>
<body>

<form:form action="/fedTax" method="post" onsubmit="return v.exec();">
	<!-- begin shared/header -->
	<div id="ddcolortabsline">&nbsp;</div>
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
	<div>
		<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; 
		border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">

			<spring:message code="BzComposer.federaltax.federaltaxinformation" />
		</span>
	</div>
	<div class="table-negotiations">
	<table id="tabla-listados">
		<c:if test="${FedTaxlist != null}">
			<c:if test="${not empty FedTaxlist}">
				<c:forEach items="FedTaxlist" var="objList">
					<table class="tabla-listados" cellspacing="0">
						<thead>
							<tr>
								<th class="emblem" colspan="5">
									<spring:message code="BzComposer.federaltax.identification" />
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td align="right" id="t_fdTaxId">
									<spring:message code="BzComposer.federaltax.federaltaxid" />:
								</td>
								<td>
									<input type="text" name="objList" path="fdTaxId" size="15" />
								</td>
								<td>
									<spring:message code="BzComposer.federaltax.fiscalmonth" />:
								</td>
								<td align="left" id="t_fcMonth">
									<form:select name="objList" path="fcMonth">
										<form:option value="">Select Month</form:option>
										<form:option value="January">January</form:option>
										<form:option value="February">February</form:option>
										<form:option value="March">March</form:option>
										<form:option value="April">April</form:option>
										<form:option value="May">May</form:option>
										<form:option value="June">June</form:option>
										<form:option value="July">July</form:option>
										<form:option value="August">August</form:option>
										<form:option value="September">September</form:option>
										<form:option value="October">October</form:option>
										<form:option value="November">November</form:option>
										<form:option value="December">December</form:option>
									</form:select>
								</td>
							</tr>
						</tbody>
				</table>
				<table class="tabla-listados" cellspacing="0">
					<thead>
						<tr>
							<th class="emblem" colspan="5">
								<spring:message code="BzComposer.federaltax.federaltax" />
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="left">
								<c:if test="${objList.ficaVal == 'true'}">
									<input type="checkbox" name="ficaVal" checked="true" value="ON" />
								</c:if>
								<c:if test="${objList.ficaVal == 'false'}">
									<input type="checkbox" name="ficaVal" />
								</c:if>
								&nbsp;<spring:message code="BzComposer.federaltax.fica" />
							</td>
							<td align="left">
								<spring:message code="BzComposer.federaltax.rate" />:
								<input type="text" name="objList" name="ficaRate" size="8"
								onkeypress="return numbersonly(event,this.value)" />
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="right">
                                <c:if test="${objList.ssTaxVal == 'true'}">
									<input type="checkbox" name="ssTaxVal" checked="true" value="ON" />
								</c:if>
								<c:if test="${objList.ssTaxVal == 'false'}">
									<input type="checkbox" name="ssTaxVal" />
								</c:if>
								&nbsp; <spring:message code="BzComposer.federaltax.socialsecuritytax" />
							</td>
							<td align="left">
								<spring:message code="BzComposer.federaltax.rate" />:
								<input type="text" name="objList" path="ssTaxRate" size="8"
								onkeypress="return numbersonly(event,this.value)" />
							</td>
							<td>
								<spring:message code="BzComposer.federaltax.upto" />
								<input type="text" name="objList" path="ssTaxUpto" size="8"
								onkeypress="return numbersonly(event,this.value)" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<c:if test="${objList.medicareVal == 'true'}">
									<input type="checkbox" name="medicareVal" checked="true" value="ON" />
								</c:if>
								<c:if test="${objList.medicareVal == 'false'}">
									<input type="checkbox" name="medicareVal" />
								</c:if>
								&nbsp; <spring:message code="BzComposer.federaltax.medicare" />
							</td>
							<td align="left"><spring:message code="BzComposer.federaltax.rate" />:
							<input type="text" name="objList" path="medicareRate" size="8"
								onkeypress="return numbersonly(event,this.value)" /></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="left">
								<c:if test="${objList.fitVal == 'true'}">
									<input type="checkbox" name="fitVal" checked="true" value="ON" />
								</c:if>
								<c:if test="${objList.fitVal == 'false'}">
									<input type="checkbox" name="fitVal" />
								</c:if>
								&nbsp; <spring:message code="BzComposer.federaltax.fit" />
							</td>
							<td align="left">&nbsp;</td>
							<td>
								<spring:message code="BzComposer.federaltax.usingtables"/>
							</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
		</c:if>
	</c:if>
	</table>
	<table class="tabla-listados" cellspacing="0">
		<tbody>
			<tr>
				<td colspan="4" align="center">
					<input type="button" value="<spring:message code='BzComposer.federaltax.savefederaltaxbtn'/>"
					onclick="setTaxInfo();" class="formbutton">
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>
<!-- end Contents -->
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</form:form>
</body>
</html>