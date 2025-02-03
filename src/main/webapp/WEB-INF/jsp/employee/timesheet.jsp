<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="../include/headlogo.jsp" %>
    <%@include file="../include/header.jsp" %>
    <%@include file="../include/menu.jsp" %>
    <title><spring:message code="BzComposer.timesheettitle"/></title>

    <%--    Timepicker Script--%>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.css'>
    <link rel='stylesheet' href='https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css'>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.js'></script>
    <script src="${pageContext.request.contextPath}/scripts/timepiker.js" type="text/javascript"></script>

    <script>
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
        var flag1 = false;
        var flag2 = false;

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
        }
    </script>
    <style>
        body {
            /* 	background: #f1f1f1; */
            margin: 0px;
            padding: 0px;
            font-family: "Philosopher", "Bitstream Vera Sans", "Lucida Grande", "Trebuchet MS",Arial, Helvetica, sans-serif !important;
        }
        .matchprent{
            width: 100%;
            height: 100%;
        }
    </style>
    <script>

    $(document).ready(function() {
	    $('#chkHired').change(function() {
            loadEmployeeList();
        });
        $('#chkTerminated').change(function() {
            loadEmployeeList();
         });


       });

       function loadEmployeeList(){
                   var sortBy = 1;
                   var hired = $('#chkHired').is(":checked");
                   var terminated = $('#chkTerminated').is(":checked");
                   var tabId = '';
                   if(hired && terminated){
                       tabId = 'loadAll';
                   }else if(terminated){
                       tabId = 'loadTerminated';
                   }else if(hired){
                       tabId = 'loadHired';
                   }



                   $.ajax({  type: "POST",
                   					url:"EmployeeAjax?tabid="+tabId+"&sortBy="+sortBy,
                   					data:{sortBy:sortBy},
                   				}).done(function(data){
                   					$('#employeeid').children().remove();
                   					$('#employeeid').append(new Option("Select Employee", "0"));
                   					var custDetails = "";
                   					for(var i=0; i<data.length; i++){
                    						var objList = data[i];
                                         $('#employeeid').append(new Option(objList.fname+" "+objList.lname, objList.employeeID));

                   					}
                    			});
               }

    </script>
</head>
<body>
    <!-- begin shared/header -->
    <div id="ddcolortabsline">&nbsp;</div>
    <div id="cos">

<span style="font-size: 1.6em; font-weight: bold !important; color: #05A9C5 !important;border-bottom: none !important;padding: 0 0 .3em 0;">
        <spring:message code="BzComposer.timesheet.employeetimesheet"/>
        </span>
        <br><h5><spring:message code="BzComposer.Employee"/></h5>
         <div class="row">
                    <div class="col-sm-12">
                    	<input type="checkbox" id="chkHired" name="hired" checked>
                         <spring:message code="BzComposer.searchemployee.hiredemployee" />
                         &nbsp;
                         <input type="checkbox" id="chkTerminated" name="terminated" value="Bike">
                         <spring:message code="BzComposer.searchemployee.terminatedemployee" />
                    </div>
         </div>
        <div class="row">
            <div class="col-sm-3">

                <select class="custom-select" id="employeeid" onchange="SetEmpId()">
                    <option value="0">Select Employee</option>
                    <c:forEach items="${empList}" var="obj">
                        <option value="${obj.employeeID}">${obj.fname} ${obj.lname}</option>
                    </c:forEach>
                </select>
                <br><br>
                <div id="datePicker" align="center"></div>
            </div>
            <div class="col-sm-9">
                <div  style="border: 1px solid #dee2e6;">
                    <h5><spring:message code="BzComposer.timesheet.employeetimesheet"/></h5>
                    <div class="table-responsive">
                        <table width="100%" class=" table-bordered tabla-editables">
                            <thead>
                            <tr>
                                <th> <spring:message code="BzComposer.timesheet.weekday"/></th>
                                <th><spring:message code="BzComposer.timesheet.date"/></th>
                                <th><spring:message code="BzComposer.timesheet.startwork1"/></th>
                                <th><spring:message code="BzComposer.timesheet.endwork1"/></th>
                                <th><spring:message code="BzComposer.timesheet.startwork2"/></th>
                                <th><spring:message code="BzComposer.timesheet.endwork2"/></th>
                                <th><spring:message code="BzComposer.timesheet.break"/></th>
                                <th><spring:message code="BzComposer.timesheet.totalhours"/></th>
                            </tr>
                            </thead>
                            <tbody id="tBody">
                            </tbody>
                        </table>
                    </div>
                    <div id="tsheet">
                        <input type="hidden" name="tsdata" id="tsdata" value="">
                    </div>
                    <input type="hidden" name="empid" id="empid" value=""/>
                    <input type="hidden" name="weeksdays" id="weekdays" value="">
                </div>
            </div>
        </div>
        <table class="table table-bordered">
            <tbody>
            <tr>
                <td colspan="2">
                    <b><spring:message code="BzComposer.timesheet.weeklytotal"/></b>
                </td>
            </tr>
            <tr>
                <td style="width: 150px">
                    <spring:message code="BzComposer.timesheet.totalhours"/>:
                    <br>
                    <spring:message code="BzComposer.timesheet.regularhours"/>:
                    <br>
                    <spring:message code="BzComposer.timesheet.overtimehours"/>:
                </td>
                <td >
                    <b><span id="totalHours" >0.0</span></b>
                    <br>
                    <b> 0.0</b>
                    <br>
                    <b> 0.0</b>
                </td>
            </tr>
            <td colspan="2" align="center" style="font-size:1em;">
                <input type="button" value="<spring:message code='BzComposer.timesheet.cleardata'/>" class="formbutton">
                <a class="formbutton" onclick="saveTimsSheet();"><spring:message code='BzComposer.timesheet.savetimesheet'/></a>
                <input type="button" value="<spring:message code='BzComposer.timesheet.printtimesheet'/>" class="formbutton">
            </td>
            </tbody>
        </table>
    </div>
    <script type="text/javascript">
        function drawTimesheet() {
            var dates = document.getElementById("weekdays").value;
            var id = document.getElementById("empid").value
            if (id != null || id != "") {
                o = c(writeSelect1);

                oGET(o, '${pageContext.request.contextPath}/include/EmployeeTimeSheet.jsp?id=10&dates=' + dates);
                var rawdata = document.getElementById("tsdata").value;

            }
        }

        function writeSelect1() {
            if (o.readyState != 4 || o.status != 200) {
                return;
            }

            document.getElementById("tsheet").innerHTML = "";
            document.getElementById("tsheet").innerHTML = o.responseText;

        }
        function saveTimsSheet(){
            var empid = document.getElementById("employeeid").value;
            if (empid == ""){
                alert("<bean:message key='BzComposer.common.selectEmployee'/>");
                return false;
            }
            $.ajax({
                type: "POST",
                contentType : 'application/json; charset=utf-8',
                dataType : 'json',
                url: "${pageContext.request.contextPath}/TimeSheet",
                data: JSON.stringify(TimeSheetForm), // Note it is important
                success :function(result) {

                }
            });
            location.reload();
        }

    </script>

    <script>
        var myObject = [];
        function GetTimeSheetData(empid){
             var monStartWeekDays = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'];
            $.ajax({
                type : "POST",
                contentType : 'application/json; charset=utf-8',
                dataType : 'json',
                url : "${pageContext.request.contextPath}/GetTimeSheet?empid="+empid,
                success : function(data) {
                    for(var i = 0 ;i<=data.length;i++){
                         for(var j = 0; j < monStartWeekDays.length; j++) {
                            //document.getElementById("startTime"+monStartWeekDays[i]).value = data[i].day;
                            var rr = document.getElementById("timerdate"+monStartWeekDays[j]).value;

                            if(data[i] && rr == data[i].day ){


                                document.getElementById("startTime1"+monStartWeekDays[j]).value = data[i].timeIn1;
                                document.getElementById("finishTime1"+monStartWeekDays[j]).value = data[i].timeOut1;
                                document.getElementById("startTime2"+monStartWeekDays[j]).value = data[i].timeIn2;
                                document.getElementById("finishTime2"+monStartWeekDays[j]).value = data[i].timeOut2;
                                document.getElementById("break"+monStartWeekDays[j]).value = data[i].break;


                                var timerdate = document.getElementById("timerdate"+monStartWeekDays[j]).value;
                                startVal1 = document.getElementById("startTime1"+monStartWeekDays[j]).value;
                                finishVal1 = document.getElementById("finishTime1"+monStartWeekDays[j]).value;
                                startVal2 = document.getElementById("startTime2"+monStartWeekDays[j]).value;
                                finishVal2 = document.getElementById("finishTime2"+monStartWeekDays[j]).value;

                                var startTime1 = new Date( "01/01/2007 "+startVal1);
                                var finishTime1 = new Date( "01/01/2007 "+finishVal1 );
                                var startTime2 = new Date( "01/01/2007 "+startVal2);
                                var finishTime2 = new Date( "01/01/2007 "+finishVal2 );

                                var breakTime = document.getElementById("break"+monStartWeekDays[j]).value;
                                var hoursWorked = (finishTime1.getTime() - startTime1.getTime()) / 1000;

                                if (startVal2 && finishVal2) {
                                       hoursWorked = hoursWorked + ((finishTime2.getTime() - startTime2.getTime()) / 1000)
                                }
                                hoursWorked /= (60 * 60);
                                hoursWorked -= breakTime;
                                //set data in object
                                TimeSheetForm[timerdate] = {
                                    "date" : timerdate+" 00:00:00",
                                    "Start Work 1" : timerdate+" "+startVal1+":00",
                                    "End Work 1" : timerdate+" "+finishVal1+":00",
                                    "Start Work 2" : timerdate+" "+startVal2+":00",
                                    "End Work 2" : timerdate+" "+finishVal2+":00",
                                    "Break" : breakTime,
                                    "hoursWorked":hoursWorked
                                }
                                if (startVal1 && finishVal1) { //providing both start and finish times are set
                                    if (hoursWorked >= 0) { //if normal day shift
                                        $("#hoursWorked"+monStartWeekDays[j]).html(hoursWorked);
                                    } else { //if night shift
                                        $("#hoursWorked"+monStartWeekDays[j]).html(24 + hoursWorked);
                                    }
                                }
                                updateTotal();
                            }
                        }
                    }
                },
                error : function(data) {
                    alert("<bean:message key='BzComposer.common.erroroccurred'/>");
                }
            });
        }
        function updateTotal() { //function to update the total hours worked
            let totalHoursWorked = 0;
            let hrs = document.querySelectorAll('.hours-worked');
            hrs.forEach(function(val) {
                totalHoursWorked += Number(val.innerHTML);
            });
            document.querySelector('#totalHours').innerHTML = totalHoursWorked;

        }
        function clearData(){
            var monStartWeekDays = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'];
            for(var j = 0; j < monStartWeekDays.length; j++) {
                document.getElementById("startTime1"+monStartWeekDays[j]).value = "";
                document.getElementById("finishTime1"+monStartWeekDays[j]).value = "";
                document.getElementById("startTime2"+monStartWeekDays[j]).value = "";
                document.getElementById("finishTime2"+monStartWeekDays[j]).value = "";

                document.getElementById("break"+monStartWeekDays[j]).value = "";
                
                document.querySelector('#hoursWorked'+monStartWeekDays[j]).innerHTML = 0;
                document.querySelector('#totalHours').innerHTML = "0.0"
            }
        }
    </script>
    <%@ include file="../include/footer.jsp" %>
</body>
</html>