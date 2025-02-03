<%@ page import="com.avibha.bizcomposer.employee.dao.Employee" %>
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
    <title><spring:message code="BzComposer.payroll.title.createPayroll" /></title>
<script type="text/javascript">
    function init(){
        debugger;
        var d = new Date();
        var monthname = (d.getMonth()+1);
        var yearname = d.getFullYear();
        document.getElementById("month").value = monthname;
        document.getElementById("year").value = yearname;
    }
    function getempdata(){
        debugger;
        var empids = document.getElementById("empids").value;
        var fields = empids.split(',');
        var empid = fields[0];
        var payperiod = fields[1];
        document.getElementById("payperiodid").value = payperiod;
        document.getElementById("payPeriodID1").value = payperiod;
    }
    function findEmployee(){
        debugger;
        var empids = document.getElementById("empids").value;
        var fields = empids.split(',');
        var empid = fields[0];
        var payperiodid = fields[1];
        var month = document.getElementById("month").value;
        var year = document.getElementById("year").value;
        $.ajax({
            type : "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            url : "${pageContext.request.contextPath}/GetPayroll?empid="+empid+"&payperiodid="+payperiodid+"&month="+month+"&year="+year,
            success : function(data) {
                document.getElementById("salary").value = data[0].totalSalary;
                document.getElementById("FederalWithholdingTax").value = data[0].frederalWithholdingTax;
                document.getElementById("MedicareTax").value = data[0].medicareTax;
                document.getElementById("SocialSecurityTax").value = data[0].socialSecurityTax;
                document.getElementById("StateWithholdingTax").value = data[0].stateWithholdingTax;
                document.getElementById("StateDisablitiyInsurance").value = data[0].stateDisablitiyInsurance;
                document.getElementById("FederalInsContributionAct").value = data[0].fICA;
                document.getElementById("TotalAllowances").value = data[0].totalAllowances;
                document.getElementById("TotalDeduction").value = data[0].totalDeduction;
                document.getElementById("NetSalary").value = data[0].netSalary;
                document.getElementById("employeeID").value = data[0].employeeID;
                document.getElementById("workingHours").value = data[0].workingHours;
            },
            error : function(data) {
                alert("<bean:message key='BzComposer.common.erroroccurred'/>");
            }
        });
    }

</script>
</head>
<body onload="init()">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<form action="/Payroll" method="post" name="PayrollForm" id="PayrollForm">
<div id="cos" style="margin: 7px;">
    <div style="border-width: 1.5px;border-style: solid;margin: 0;padding: 17px;">
        <label class="d-lg-flex" style="font-size: 28px;">
            <strong><spring:message code="BzComposer.payroll.createPayroll" /></strong>
        </label>
        <hr style="height: 0;min-height: 0px;margin: 7px;margin-top: -4px;margin-bottom: 17px;border-color: var(--dark);">
        <c:if test="${status ==true}">
            <c:if test="${not empty message}">
                <div class="alert alert-primary alert-dismissible fade show" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        ${message}
                </div>
            </c:if>
        </c:if>
        <c:if test="${status ==false}">
            <c:if test="${not empty message}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        ${message}
                </div>
            </c:if>
        </c:if>

        <div class="row" style="margin: 0;">
            <div class="col">
                <div class="row">
                    <div class="col text-center"><label class="col-form-label text-center" style="font-size: 13px;">
                        <spring:message code="BzComposer.Employee.EmployeeName" /></label></div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="hidden" name="employeeID" value="" id="employeeID">
                        <select id="empids"  class="custom-select" style="font-size: 13px;" onchange="getempdata();">
                            <option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
                            <c:if test="${not empty empList}">
                                <c:forEach items="${empList}" var="objList">
                                    <option value="${objList.employeeID},${objList.payperiod}">${objList.fname} ${objList.lname}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
            </div>
           <%-- <div class="col">
                <div class="row text-center">
                    <div class="col"><label class="col-form-label" style="font-size: 13px;"><spring:message code="BzComposer.option.payperiod" /></label></div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="hidden" name="payPeriodID" id="payPeriodID1">
                        <select id="payperiodid" class="custom-select" style="font-size: 13px;" disabled>
                            <option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
                            <c:forEach items="${periodList}" var="obj">
                                <option value="${obj.value}">${obj.label}</option>
                            </c:forEach>
                    </select>
                    </div>
                </div>
            </div>--%>
            <div class="col">
                <div class="row">
                    <div class="col text-center"><label class="col-form-label" style="font-size: 13px;"><spring:message code="BzComposer.payroll.Month"/></label></div>
                </div>
                <div class="row">
                    <div class="col">
                        <select id="month" class="custom-select" style="font-size: 13px;" name="month">
                        <option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
                        <option value="1">January</option>
                        <option value="2">February</option>
                        <option value="3">March</option>
                        <option value="4">April</option>
                        <option value="5">May</option>
                        <option value="6">June</option>
                        <option value="7">July</option>
                        <option value="8">August</option>
                        <option value="9">September</option>
                        <option value="10">October</option>
                        <option value="11">November</option>
                        <option value="12">December</option>
                    </select>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="row">
                    <div class="col text-center"><label class="col-form-label" style="font-size: 13px;"><spring:message code="BzComposer.payroll.year"/></label></div>
                </div>
                <div class="row">
                    <div class="col">
                        <select id="year" class="custom-select" style="font-size: 13px;" name="year">
                             <option value=""><spring:message code="BzComposer.ComboBox.Select"/></option>
                            <option value="2021">2021</option>
                            <option value="2020">2020</option>
                            <option value="2019">2019</option>
                            <option value="2018">2018</option>
                            <option value="2017">2017</option>
                            <option value="2016">2016</option>
                            <option value="2015">2015</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col align-self-center"><a class="btn btn-info" onclick="findEmployee()" style="color: white;"><spring:message code="BzComposer.payroll.Find"/></a></div>
        </div>
    </div><br>
    <div>
        <div class="row" style="margin: 0;padding: 0px;">
            <div class="col" style="border-width: 1.5px;border-style: solid;margin: 0;padding: 25px;height: 318px;font-size: 19px;">
                <div class="row" style="padding: 0px;margin: 0px;margin-top: -25px;margin-left: -16px;">
                    <div class="col"><label class="col-form-label" style="font-size: 28px;"><spring:message code="BzComposer.payroll.Allowances"/></label></div>
                </div>
                <div class="row">
                    <div class="col-4" style="text-align: left;width: 194px;margin-right: 0;"><label class="col-form-label" style="text-align: center;font-size: 13px;"><spring:message code="BzComposer.payroll.Salary"/></label></div>
                    <div class="col d-xl-flex align-self-center ml-auto" style="text-align: center;width: 200px;margin-left: 0px;padding: 0px;padding-left: 0px;padding-right: 0px;">
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">$</div>
                            </div>
                            <input type="hidden" class="form-control" value="0.0" id="workingHours" name="workingHours">
                            <input type="text" class="form-control" value="0.0" id="salary" name="totalSalary">
                        </div></div>
                </div>

            </div>
            <div class="col" style="margin: 0;padding: 0px;height: 318px;padding-right: 25px;padding-left: 25px;padding-top: 0px;padding-bottom: 0px;border-width: 1.5px;border-style: solid;border-right-style: none;border-left-style: none;">
                <div class="row" style="padding: 0px;margin: 0px;margin-top: -25px;margin-left: -16px;padding-top: 23px;">
                    <div class="col"><label class="col-form-label" style="font-size: 28px;"><spring:message code="BzComposer.payroll.Deductions"/></label></div>
                </div>
                <div class="row">
                    <div class="col-5" style="text-align: left;width: 194px;margin-right: 0;"><label class="col-form-label" style="text-align: left;font-size: 13px;"><spring:message code="BzComposer.payroll.FrederalWithholdingTax"/></label></div>
                    <div class="col d-xl-flex align-self-center ml-auto" style="text-align: center;width: 200px;margin-left: 0px;padding: 0px;padding-left: 0px;padding-right: 0px;">
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">$</div>
                            </div>
                            <input type="text" class="form-control" value="0.00" id="FederalWithholdingTax" name="frederalWithholdingTax">
                        </div></div>
                </div>
                <div class="row">
                    <div class="col-5" style="text-align: left;width: 194px;margin-right: 0;"><label class="col-form-label" style="text-align: left;font-size: 13px;"><spring:message code="BzComposer.payroll.MedicareTax"/></label></div>
                    <div class="col d-xl-flex align-self-center ml-auto" style="text-align: center;width: 200px;margin-left: 0px;padding: 0px;padding-left: 0px;padding-right: 0px;">
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">$</div>
                            </div>
                            <input type="text" class="form-control" value="0.00" id="MedicareTax" name="medicareTax">
                        </div></div>
                </div>
                <div class="row">
                    <div class="col-5" style="text-align: left;width: 194px;margin-right: 0;"><label class="col-form-label" style="text-align: center;font-size: 13px;"><spring:message code="BzComposer.payroll.SocialSecurityTax"/></label></div>
                    <div class="col d-xl-flex align-self-center ml-auto" style="text-align: center;width: 200px;margin-left: 0px;padding: 0px;padding-left: 0px;padding-right: 0px;">
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">$</div>
                            </div>
                            <input type="text" class="form-control" value="0.00" id="SocialSecurityTax" name="socialSecurityTax">
                        </div></div>
                </div>
                <div class="row">
                    <div class="col-5" style="text-align: left;width: 194px;margin-right: 0;"><label class="col-form-label" style="text-align: left;font-size: 13px;"><spring:message code="BzComposer.payroll.StateWithholdingTax"/></label></div>
                    <div class="col d-xl-flex align-self-center ml-auto" style="text-align: center;width: 200px;margin-left: 0px;padding: 0px;padding-left: 0px;padding-right: 0px;">
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">$</div>
                            </div>
                            <input type="text" class="form-control" value="0.00" id="StateWithholdingTax" name="stateWithholdingTax">
                        </div></div>
                </div>
                <div class="row">
                    <div class="col-5" style="text-align: left;width: 194px;margin-right: 0;"><label class="col-form-label" style="text-align: left;font-size: 13px;"><spring:message code="BzComposer.payroll.StateDisablitiyInsurance"/></label></div>
                    <div class="col d-xl-flex align-self-center ml-auto" style="text-align: center;width: 200px;margin-left: 0px;padding: 0px;padding-left: 0px;padding-right: 0px;">
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">$</div>
                            </div>
                            <input type="text" class="form-control" value="0.00" id="StateDisablitiyInsurance" name="stateDisablitiyInsurance">
                        </div></div>
                </div>
                <div class="row">
                    <div class="col-5" style="text-align: left;width: 194px;margin-right: 0;"><label class="col-form-label" style="text-align: left;font-size: 13px;"><spring:message code="BzComposer.payroll.FederalInsContributionAct"/></label></div>
                    <div class="col d-xl-flex align-self-center ml-auto" style="text-align: center;width: 200px;margin-left: 0px;padding: 0px;padding-left: 0px;padding-right: 0px;">
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">$</div>
                            </div>
                            <input type="text" class="form-control" value="0.00" id="FederalInsContributionAct" name="fICA">
                        </div></div>
                </div>
            </div>
            <div class="col" style="border-width: 1.5px;border-style: solid;margin: 0;padding: 25px;height: 318px;">
                <div class="row" style="padding: 0px;margin: 0px;margin-top: -25px;margin-left: -16px;">
                    <div class="col" style="padding-top: 0px;"><label class="col-form-label" style="font-size: 28px;padding-top: 6px;"><spring:message code="BzComposer.payroll.Summary"/></label></div>
                </div>
                <div class="row">
                    <div class="col-4" style="text-align: left;width: 194px;margin-right: 0;"><label class="col-form-label" style="text-align: left;font-size: 13px;"><spring:message code="BzComposer.payroll.TotalAllowances"/></label></div>
                    <div class="col d-xl-flex align-self-center ml-auto" style="text-align: center;width: 200px;margin-left: 0px;padding: 0px;padding-left: 0px;padding-right: 0px;">
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">$</div>
                            </div>
                            <input type="text" class="form-control" value="0.00" id="TotalAllowances" name="totalAllowances">
                        </div></div>
                </div>
                <div class="row">
                    <div class="col-4" style="text-align: left;width: 194px;margin-right: 0;"><label class="col-form-label" style="text-align: left;font-size: 13px;"><spring:message code="BzComposer.payroll.TotalDeduction"/></label></div>
                    <div class="col d-xl-flex align-self-center ml-auto" style="text-align: center;width: 200px;margin-left: 0px;padding: 0px;padding-left: 0px;padding-right: 0px;">
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">$</div>
                            </div>
                            <input type="text" class="form-control" value="0.00" id="TotalDeduction" name="totalDeduction">
                        </div></div>
                </div>
                <div class="row">
                    <div class="col-4" style="text-align: left;width: 194px;margin-right: 0;"><label class="col-form-label" style="text-align: left;font-size: 13px;"><spring:message code="BzComposer.payroll.NetSalary"/></label></div>
                    <div class="col d-xl-flex align-self-center ml-auto" style="text-align: center;width: 200px;margin-left: 0px;padding: 0px;padding-left: 0px;padding-right: 0px;">
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">$</div>
                            </div>
                            <input type="text" class="form-control" value="0.00" id="NetSalary" name="netSalary">
                        </div></div>
                </div>
            </div>
        </div>
    </div><br>
    <div style="border-width: 1.5px;border-style: solid;margin: 0;padding: 17px;">
        <div class="row" style="margin: 0;">
            <div class="col"></div>
            <div class="col">
                <div class="row text-center">
                    <div class="col"><label class="col-form-label" style="font-size: 13px;"><spring:message code="BzComposer.payroll.PaymentMethod"/></label></div>
                </div>
                <div class="row">
                    <div class="col"><select class="custom-select" style="font-size: 13px;" id="PaymentMethod" name="paymentMethod">
                        <option value=""><spring:message code="BzComposer.ComboBox.Select"/></option>
                        <option value="<spring:message code="BzComposer.payroll.Cash"/>"><spring:message code="BzComposer.payroll.Cash"/></option>
                        <option value="<spring:message code="BzComposer.payroll.Cheque"/>"><spring:message code="BzComposer.payroll.Cheque"/></option>
                    </select></div>
                </div>
            </div>
            <div class="col">
                <div class="row">
                    <div class="col text-center"><label class="col-form-label" style="font-size: 13px;"><spring:message code="BzComposer.payroll.Status"/></label></div>
                </div>
                <div class="row">
                    <div class="col"><select class="custom-select" style="font-size: 13px;" id="Status" name="status">
                        <option value="Paid"><spring:message code="BzComposer.payroll.Paid"/></option>
                        <option value="Unpaid"><spring:message code="BzComposer.payroll.UnPaid"/></option>
                    </select></div>
                </div>
            </div>
            <div class="col text-center align-self-end mx-auto">
                <a class="btn btn-info" onclick="createPayroll()" style="color: white;"><spring:message code="BzComposer.payroll.CreatePayroll"/></a>
            </div>
            <div class="col align-self-end"></div>
        </div>
    </div>
</div>
</form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>

<script type="text/javascript">

    function createPayroll(){
        var empid = document.getElementById("empids").value;
        var PaymentMethod = document.getElementById("PaymentMethod").value;
        var workingHours = document.getElementById("workingHours").value;
        if (empid == ""){
            return selectemployeeDialog();
        }else if (workingHours == 0 || workingHours == 0.0){
            return emptyemployeetimesheetDialog();
        }else if (PaymentMethod == ""){
            return selectPaymentMethodDialog();
        }else {
            document.forms['PayrollForm'].action="Payroll?tabid=createList";
            document.forms['PayrollForm'].submit();
        }
    }

    function selectemployeeDialog() {
        event.preventDefault();
        $("#selectemployeeDialog").dialog({
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

    function selectPaymentMethodDialog() {
        event.preventDefault();
        $("#selectPaymentMethodDialog").dialog({
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

    function emptyemployeetimesheetDialog() {
        event.preventDefault();
        $("#emptyemployeetimesheetDialog").dialog({
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



</script>
</body>
</html>

<div id="selectemployeeDialog" style="display:none;">
    <p><spring:message code="BzComposer.payroll.employee.problem"/></p>
</div>

<div id="selectPaymentMethodDialog" style="display:none;">
    <p><spring:message code="BzComposer.payroll.PaymentMethod.problem"/></p>
</div>

<div id="emptyemployeetimesheetDialog" style="display:none;">
    <p><spring:message code="BzComposer.payroll.emptyemployeetimesheet.problem"/></p>
</div>
