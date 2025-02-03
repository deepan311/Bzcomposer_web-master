<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
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
<title><spring:message code="BzComposer.optiontitle" /></title>
</head>
<body>
<form:form action="/CompanyTaxOption" method="post">
	<!-- begin shared/header -->
	<div id="ddcolortabsline">&nbsp;</div>
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
		<!-- begin Contents --> 
		<span id="waitMessage">
			<img src="${pageContext.request.contextPath}/images/spinner.gif">
			<spring:message code="BzComposer.option.loadingcontents"/>
		</span>
		<div class="dhtmlgoodies_question">
			<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; 
			border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
				<spring:message code="BzComposer.option.optioninformation" />
			</span>
		</div>
		<div class="dhtmlgoodies_answer">
			<div id="table-negotiations">
            <c:if test="${CompTaxOptionlist != null}">
                <c:if test="${not empty CompTaxOptionlist}">
					<c:forEach items="CompTaxOptionlist" var="objList">
						<table class="tabla-listados" cellspacing="0">
							<thead>
								<tr>
									<th class="emblem" colspan="5">
										<spring:message code="BzComposer.option.payperiod" />:
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td align="right">
										<input type="checkbox" value="${objList.daily}" />&nbsp;
										<spring:message code="BzComposer.option.dailyormiscellaneous" />
									</td>
									<td>
										<input type="checkbox" value="${objList.weekly}" />&nbsp;
										<spring:message code="BzComposer.option.weekly" />
									</td>
									<td align="left">
										<input type="checkbox" value="${objList.biweekly}" />&nbsp;
										<spring:message code="BzComposer.option.biweekly" />
									</td>
								</tr>
								<tr>
									<td align="right">
										<input type="checkbox" value="${objList.semiMonthly}" />&nbsp;
										<spring:message code="BzComposer.option.semimonthly" />
									</td>
									<td>
										<input type="checkbox" value="${objList.monthly}" />&nbsp;
										<spring:message code="BzComposer.option.dailyormiscellaneous" />
									</td>
									<td align="left">
										<input type="checkbox" value="${objList.quarterly}" />&nbsp;
										<spring:message code="BzComposer.option.quarterly" />
									</td>
								</tr>
								<tr>
									<td align="right">
										<input type="checkbox" value="${objList.semiAnnualy}" />&nbsp;
										<spring:message code="BzComposer.option.semiannualy" />
									</td>
									<td>
										<input type="checkbox" value="${objList.annualy}" />&nbsp;
										<spring:message code="BzComposer.option.annualy" />
									</td>
									<td>&nbsp;</td>
								</tr>
							</tbody>
						</table>
						<table cellspacing="0" width="100%">
							<tbody>
								<tr>
									<td>
										<table class="tabla-listados" cellspacing="0">
											<thead>
												<tr>
													<th class="emblem" colspan="5">
														<spring:message code="BzComposer.option.setovertime" />
													</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td align="right">
														<input type="radio" name="objList.dailyOverVal" value="1"
														onclick="changeWeekly();" /> 
														<spring:message code="BzComposer.option.dailyover" />
													</td>
													<c:if test="${objList.dailyOverVal == 'true'}">
														<script>
															document.CompanyTaxOptionForm.dailyOverVal.checked=true;
															document.CompanyTaxOptionForm.weeklyOverVal.checked=false;
														</script>
													</c:if>
													<td>
														<input type="text" value="${objList.dailyOver}"
														onkeypress="return numbersonly(event,this.value)" /> 
														(<spring:message code="BzComposer.option.hours" />)
													</td>
												</tr>
												<tr>
													<td align="right">
														<input type="radio" name="objList" value="1" path="weeklyOverVal"
														onclick="changeDaily();" />
														<spring:message code="BzComposer.option.weeklyover" />
													</td>
													<c:if test="${objList.weeklyOverVal == 'true'}">
														<script>
															document.CompanyTaxOptionForm.dailyOverVal.checked=false;
															document.CompanyTaxOptionForm.weeklyOverVal.checked=true;
														</script>
													</c:if>
													<td>
														<input type="text" value="${objList.weeklyOver}"
														onkeypress="return numbersonly(event,this.value)" /> 
														( <spring:message code="BzComposer.option.hours" /> )
													</td>
												</tr>
												<tr>
													<td align="right">
														<spring:message code="BzComposer.option.rate" />:
													</td>
													<td>
														<input type="text" value="${objList.rate}"
														onkeypress="return numbersonly(event,this.value)" />
													</td>
												</tr>
											</tbody>
										</table>
									</td>
									<td>
										<table class="tabla-listados" cellspacing="0">
											<thead>
												<tr>
													<th class="emblem" colspan="5">
														<spring:message code="BzComposer.option.setholidaytime" />
													</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td align="right">
														<input type="checkbox" value="${objList.wendSt}" />&nbsp;
														<spring:message code="BzComposer.option.weekendsaturday" />
													</td>
													<td>
														<spring:message code="BzComposer.option.rate" /> : 
														<input type="text" value="${objList.wendStRate}"
														onkeypress="return numbersonly(event,this.value)" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<input type="checkbox" value="${objList.wendSn}" />&nbsp;
														<spring:message code="BzComposer.option.weekendsunday" />
													</td>
													<td>
														<spring:message code="BzComposer.option.rate" /> : 
														<input type="text" value="${objList.wendSnRate}"
														onkeypress="return numbersonly(event,this.value)" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<input type="checkbox" value="${objList.holyday}" />&nbsp;
														<spring:message code="BzComposer.option.holiday" />
													</td>
													<td>
														<spring:message code="BzComposer.option.rate" /> :
														<input type="text" value="${objList.holydayRate}"
														onkeypress="return numbersonly(event,this.value)" />
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
							</tbody>
						</table>
						<table class="tabla-listados" cellspacing="0">
							<thead>
								<tr>
									<th class="emblem" colspan="5">
										<spring:message code="BzComposer.option.setpayrollday" />
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td align="right">
										<input type="checkbox" value="${objList.dayOfWeekVal}" />&nbsp;
										<input type="text" value="${objList.dayOfWeek}"
										onkeypress="return numbersonly(event,this.value)" />&nbsp; 
										<spring:message code="BzComposer.option.dayofweek" />
									</td>
									<td align="left">
										<input type="checkbox" value="${objList.dayOfMonthVal}" />&nbsp;
										<input type="text" value="${objList.dayOfMonth}"
										onkeypress="return numbersonly(event,this.value)" />&nbsp; 
										<spring:message code="BzComposer.option.dayofmonth" />
									</td>
								</tr>
							</tbody>
						</table>	
						<table class="tabla-listados" cellspacing="0">
							<tbody>
								<tr>
									<td colspan="4" align="center">
										<input type="button" value="<spring:message code="BzComposer.option.saveoptions"/>"
										class="formbutton" onclick="editOption();">
									</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
				</c:if>
            </c:if>
		</div>
	</div>
	<!-- end Contents --></div>
	</div>
	</div>
	</div>
	<%@ include file="../include/footer.jsp"%></div>
</form:form>
</body>
</html>
<script>
function editOption()
{
	document.forms[0].action = "CompanyTaxOption?tabid=t2x2o2";
	document.forms[0].submit();
}
function changeDaily()
{
	document.CompanyTaxOptionForm.dailyOverVal.checked=false;
	document.CompanyTaxOptionForm.weeklyOverVal.checked=true;
}
function changeWeekly()
{
	document.CompanyTaxOptionForm.dailyOverVal.checked=true;
	document.CompanyTaxOptionForm.weeklyOverVal.checked=false;
}
</script>