<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<%@include file="../include/headlogo.jsp"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/menu.jsp"%>
<title><spring:message code="BzComposer.searchemployeetitle" /></title>
<!-- <script> -->
<!-- window.onload = initShowHideDivs; -->
<!-- </script> -->
<script type="text/javascript">
var funsequence = 0;
var _1 = navigator.userAgent.toLowerCase();
var ___ = (_1.indexOf("msie") != -1);
var ___5 = (_1.indexOf("msie 5") != -1);
var _io = (_1.indexOf("opera") != -1);
var _im = (_1.indexOf("mac") != -1);
var ____gi = (_1.indexOf("gecko") != -1);
var i____s = (_1.indexOf("safari") != -1);
var o = null;

var r = null;
var type="2";
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
function oGET(oo, url) {
  try {
    oo.open("GET", url, true);	
    oo.send(null);
  } catch (ex) {
  }
}

function writeSelect()
{
  
   if (o.readyState != 4 || o.status != 200) { 
      return;
    }
     
    document.getElementById("t_statedata").innerHTML = o.responseText ;
}

function refreshItemsNow(val)
{
  o = c(writeSelect);
  oGET(o,"${pageContext.request.contextPath}/include/GetStates.jsp?Cid=" + val)
}
</script>
</head>
<body>
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding"><!-- begin Contents --> 
<!-- <span id="waitMessage"><img -->
<%-- 	src="${pageContext.request.contextPath}/images/spinner.gif"> --%>
<!-- Loading Contents...</span> -->
<!-- <div class="dhtmlgoodies_question"> -->
<div>
	<span style="font-size: 1.6em;font-weight:normal;color:#838383;margin:30px 0px 15px 0px;border-bottom:1px dotted #333;padding:0 0 .3em 0;">
		<spring:message code="BzComposer.searchemployee.searchemployeetitle"/>
	</span>
</div>
<!-- <div class="dhtmlgoodies_answer"> -->
<div>
	<div id="table-negotiations">
		<form:form action="search" method="post" name="SearchForm">
			<table class="tabla-listados" cellspacing="0">
				<thead>
					<tr>
						<th class="emblem" colspan="5" style="font-size:1.6em;">
							<spring:message code="BzComposer.searchemployee.addemployeeinfo" />
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td></td>
						<td colspan=2 " align="center" style="font-size: 1.6em;">
							<input type="radio" name="type" value="1"></input>
							<spring:message code="BzComposer.searchemployee.hiredemployee" /> 
							<input type="radio" name="type" value="0"></input>
							<spring:message code="BzComposer.searchemployee.terminatedemployee" /> 
							<input type="radio" name="type" value="2"></input>
							<spring:message code="BzComposer.searchemployee.allemployee" />
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td id="t_fname" align="right" style="font-size:1em;">
							&nbsp;<spring:message code="BzComposer.global.firstname"/>:
							<span class="inputHighlighted"></span>
						</td>
						<td style="font-size:1em;">
							<input type="text" name="fname" size="30" value="" />
						</td>
						<td align="left" style="font-size:1em;">
							<spring:message code="BzComposer.global.lastname" />:
						</td>
						<td style="font-size:1em;">
							<input type="text" name="lname" size="20" value="" />
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="font-size:1em;">
							&nbsp;<spring:message code="BzComposer.searchemployee.dateofbirth" />:
						</td>
						<td style="font-size:1em;">
							<input type="text" name="dob" size="12" value="" />
							<img src="${pageContext.request.contextPath}/images/cal.gif"
							onclick="displayCalendar(document.SearchForm.dob,'mm-dd-yyyy',this);"/>
						</td>
						<td align="left" style="font-size:1em;">
							<spring:message code="BzComposer.searchemployee.dateofstarted" />:
						</td>
						<td style="font-size:1em;">
							<input type="text" name="dos" size="12" value="" />
							<img src="${pageContext.request.contextPath}/images/cal.gif"
							onclick="displayCalendar(document.SearchForm.dos,'mm-dd-yyyy',this);"/>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td id="t_fname" align="right" style="font-size:1em;">
							&nbsp;<spring:message code="BzComposer.global.city"/>
							<span class="inputHighlighted"></span>
						</td>
						<td width="16%" style="font-size:1em;">
							<input type="text" name="city" size="20" value="" />
						</td>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td id="t_fname" align="right" style="font-size:1em;">
							&nbsp;<spring:message code="BzComposer.global.country" />
							<span class="inputHighlighted"></span>
						</td>
						<td style="font-size:1em;">
							<select name="country" onchange="refreshItemsNow(this.value);" onblur="refreshItemsNow(this.value);">
								<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
								<c:forEach items="${cList}" var="obj">
                                		<option value="${obj.value}"><c:out value="${obj.label}"/></option>
                                </c:forEach>
							</select>
						</td>
						<td id="t_state" align="left" style="font-size:1em;">
							<spring:message code="BzComposer.global.state"/>
							<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
						</td>
						<td id="t_statedata" align="left" style="font-size:1em;">
						</td>
						<td>&nbsp;</td>
						<script>
							document.forms['SearchForm'].country.value="239";
				     		refreshItemsNow("239");
						</script>
					</tr>
					<tr>
						<td colspan="5" align="center" style="font-size:1em;">
							<input type="submit" name="search" value='<spring:message code="BzComposer.searchemployee.searchbtn"/>' class="formbutton">
							&nbsp;
							<input type="reset" name="clear" value='<spring:message code="BzComposer.searchemployee.clearbtn"/>' class="formbutton">
						</td>
					</tr>
				</tbody>
			</table>
	</form:form>
</div>
</div>
<!-- end Contents --></div>
</div>
</div>
</div>
<%@ include file="/include/footer.jsp"%></div>
<script>
document.SearchForm.type[0].checked=true;

</script>
</body>
</html>
