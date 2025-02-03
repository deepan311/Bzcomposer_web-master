<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@	page import="com.avibha.common.utility.CountryState,java.util.ArrayList"%>
<%	ArrayList iList = new ArrayList();
	if (null != request.getParameter("Cid") && ("" + request.getParameter("Cid")).length() > 0) {
        CountryState cs = new CountryState();
        iList = cs.getStates(""	+ request.getParameter("Cid"));
        //iList = cs.getStatesNew(""	+ request.getParameter("Cid"));
        request.setAttribute("iList",iList);
        String stateval = request.getParameter("sval");
        if(stateval==null)
            stateval="";
%>
<ROOT>
<c:if test="${not empty iList}">
    <select name=<%= request.getParameter("st") %> id="sid" onchange="retrieveCity()" style="width:99%;">
        <option value="0"><spring:message code="BzComposer.Employee.SelectState" /></option>
        <c:forEach items="${iList}" var="list">
            <c:if test="${list.id == stateval}">
                <%-- <option selected="selected" value="${list.id}"> ${list.name} </option> --%>
                <option selected="selected" value="${list.id}">${list.name}</option>
            </c:if>
            <c:if test="${list.id != stateval}">
                <option value="${list.id}">${list.name}</option>
            </c:if>
        </c:forEach>
    </select>
</c:if>
<c:if test="${empty iList}">
    <%-- <select name="<%= request.getParameter("st") %>" id="sid" style="font-size:1em;width:285px;"> --%>
    <select name="<%= request.getParameter("st") %>" id="sid" style="width:99%;">
        <option value=""><spring:message code="BizComposer.GetStates.noStates" /></option>
    </select>
    <!-- <input type="text" id="sid" style="font-size:1em;width:285px;"/> -->
</c:if>
</ROOT>
<%
	}
	else
	{
		%>
		<div>No States</div>
<%
	}
%>