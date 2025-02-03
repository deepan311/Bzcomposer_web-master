<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
    <%@include file="/WEB-INF/jsp/include/header.jsp"%>
    <%@include file="/WEB-INF/jsp/include/menu.jsp"%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title><spring:message code="BzComposer.payroll.title.payrollList" /></title>

</head>
<body>
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos" style="margin: 7px;">
    <div style="border-width: 1.5px;border-style: solid;margin: 0;padding: 17px;"><label class="d-lg-flex" style="font-size: 28px;"><strong><spring:message code="BzComposer.payroll.payrollList"/></strong><br></label>
        <hr style="height: 0;min-height: 0px;margin: 7px;margin-top: -4px;margin-bottom: 17px;border-color: var(--dark);">
        <c:if test="${not empty message}">
            <div class="alert alert-primary alert-dismissible fade show" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                    ${message}
            </div>
        </c:if>
        <div class="row" style="margin: 0;">
            <div class="col">
                <div class="row">
                    <div class="col text-center"><label class="col-form-label" style="font-size: 13px;"><spring:message code="BzComposer.payroll.year"/></label></div>
                </div>
                <div class="row">
                    <div class="col"><select class="custom-select" style="font-size: 13px;">
                        <option value="undefined">Select Year</option>
                        <option value="2020">2020</option>
                        <option value="2019">2019</option>
                        <option value="2018">2018</option>
                        <option value="2017">2017</option>
                        <option value="2016">2016</option>
                        <option value="2015">2015</option>
                    </select></div>
                </div>
            </div>
            <div class="col">
                <div class="row">
                    <div class="col text-center"><label class="col-form-label" style="font-size: 13px;"><spring:message code="BzComposer.payroll.Month"/></label></div>
                </div>
                <div class="row">
                    <div class="col"><select class="custom-select" style="font-size: 13px;">
                        <option value="undefined">Select Month</option>
                        <option value="January">January</option>
                        <option value="February">February</option>
                        <option value="March">March</option>
                        <option value="April">April</option>
                        <option value="May">May</option>
                        <option value="June">June</option>
                        <option value="July">July</option>
                        <option value="August">August</option>
                    </select></div>
                </div>
            </div>
            <div class="col">
                <div class="row text-center">
                    <div class="col"><label class="col-form-label" style="font-size: 13px;"><spring:message code="BzComposer.payroll.week"/></label></div>
                </div>
                <div class="row">
                    <div class="col"><select class="custom-select" style="font-size: 13px;">
                        <option value="undefined">Select Week</option>
                        <option value="12">1 Week</option>
                        <option value="">2 Week</option>
                        <option value="14">3 Week</option>
                    </select></div>
                </div>
            </div>
            <div class="col align-self-end"><button class="btn btn-info" type="button"><spring:message code="BzComposer.payroll.Find"/></button></div>
            <div class="col"></div>
        </div>
    </div><br>
    <div style="border-style: none;">
        <div class="table-responsive table-bordered" style="border-width: 1.5px;border-style: none;">
            <table class="table table-bordered table-hover">
                <thead class="text-light" style="background: var(--cyan);text-align: center;">
                <tr>
                    <th><strong><spring:message code="BzComposer.payroll.SNO"/></strong></th>
                    <th><strong><spring:message code="BzComposer.payroll.EmployeeName"/></strong></th>
                    <th><strong><spring:message code="BzComposer.payroll.Amount"/></strong></th>
                    <th><strong><spring:message code="BzComposer.payroll.year"/></strong></th>
                    <th><strong><spring:message code="BzComposer.payroll.Month"/></strong></th>
                    <th><strong><spring:message code="BzComposer.payroll.week"/></strong></th>
                    <th><strong><spring:message code="BzComposer.payroll.Status"/></strong></th>
                    <th><strong><spring:message code="BzComposer.payroll.Action"/></strong></th>
                </tr>
                </thead>
                <tbody style="text-align: center;">
                <%int count = 1;%>
                <c:if test="${not empty payrollList}">
                    <c:forEach items="${payrollList}" var="objList">
                        <tr>
                            <td style="width: 210px;"><%=count%></td>
                            <td style="width: 210px;">${objList.employeeName}</td>
                            <td style="width: 115px;">${objList.netSalary}</td>
                            <td style="width: 77px;">${objList.year}</td>
                            <td style="width: 120px;">${objList.month}</td>
                            <td style="width: 94px;">${objList.week}</td>
                            <td style="width: 133px;"><button class="btn btn-info btn-sm" type="button"><i class="fa fa-check-square" style="color: var(--dark);"></i>${objList.status} <br></button></td>
                            <td class="text-center" style="width: 240px;margin: 7px;padding: 11px;padding-top: 11px;"><a style="color: white" class="btn btn-info btn-sm" onclick="downloadPayroll(${objList.payrollID})"><i class="fa fa-download" style="color: var(--dark);"></i><spring:message code="BzComposer.payroll.DownloadPayroll"/><br></a>&nbsp;&nbsp;<a style="color: white" onclick="deletePayroll(${objList.payrollID})" class="btn btn-info btn-sm" type="button"><i class="fa fa-trash" style="color: var(--dark);"></i>Delete<br></a></td>
                        </tr>
                        <%count++;%>
                    </c:forEach>
                </c:if>
                <c:if test="${empty payrollList}">
                    <tr>
                        <td colspan="8">
                            <h4><spring:message code="BzComposer.employee.norecordsfound"/></h4>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
<script type="text/javascript">
    function downloadPayroll(payrollid){
        window.location.href="${pageContext.request.contextPath}/DownloadPayroll?payrollID="+payrollid;
    }
    function deletePayroll(id){
        debugger;
        window.location.href= "${pageContext.request.contextPath}/Payroll?tabid=deletePayroll&payrollid="+id;
    }
</script>
</body>
</html>