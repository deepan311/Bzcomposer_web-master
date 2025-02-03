<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
	<%@include file="/WEB-INF/jsp/include/header.jsp"%>
	<%@include file="/WEB-INF/jsp/include/menu.jsp"%>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href='http://fonts.googleapis.com/css?family=Philosopher:400,700,400italic,700italic&subset=cyrillic,latin' rel='stylesheet' type='text/css'>

	<link
			href="${pageContext.request.contextPath}/styles/admin.css"
			media="screen" rel="Stylesheet" type="text/css">
	<link
			href="${pageContext.request.contextPath}/styles/form.css"
			media="screen" rel="Stylesheet" type="text/css">
	<link
			href="${pageContext.request.contextPath}/styles/basic.css"
			media="screen" rel="Stylesheet" type="text/css">
	<link
			href="${pageContext.request.contextPath}/styles/formitems.css"
			media="screen" rel="Stylesheet" type="text/css">
	<link
			href="${pageContext.request.contextPath}/styles/menu.css"
			media="screen" rel="Stylesheet" type="text/css">
	<link
			href="${pageContext.request.contextPath}/styles/textareas.css"
			media="screen" rel="Stylesheet" type="text/css">
	<link
			href="${pageContext.request.contextPath}/styles/lightbox.css"
			media="screen" rel="Stylesheet" type="text/css">
	<link
			href="${pageContext.request.contextPath}/styles/calendar.css"
			rel="stylesheet"></LINK>
	<script
			src="${pageContext.request.contextPath}/scripts/prototype.js"
			type="text/javascript"></script>
	<script
			src="${pageContext.request.contextPath}/scripts/effects.js"
			type="text/javascript"></script>
	<script
			src="${pageContext.request.contextPath}/scripts/dragdrop.js"
			type="text/javascript"></script>
	<script
			src="${pageContext.request.contextPath}/scripts/controls.js"
			type="text/javascript"></script>
	<script
			src="${pageContext.request.contextPath}/scripts/application.js"
			type="text/javascript"></script>
	<script
			src="${pageContext.request.contextPath}/scriptsBzComposer.option.payperiod/message.js"
			type="text/javascript"></script>
	<script
			src="${pageContext.request.contextPath}/scripts/calendar.js"
			type="text/javascript"></script>
	<script
			src="${pageContext.request.contextPath}/scripts/tabs.js"
			type="text/javascript"></script>
	<script
			src="${pageContext.request.contextPath}/dist/js/jquery.js"
			type="text/javascript"></script>

	<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
	<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>


	<title><spring:message code="BzComposer.addnewemployeetitle"/></title>
	<script type="text/javascript">
		$(function() {
 			$( "#tabs" ).tabs();
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
			$('#sid').attr('name','state')

		}
		function refreshItemsNow(val)
		{
			o = c(writeSelect);
            // oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?Cid=' + val)
			// oGET(o,'${pageContext.request.contextPath}/include/GetStates.jsp?st=state&Cid=' + val)
			oGET(o,"include/GetStateselected.jsp?Cid=" + val)
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
				{
					return false; //disable key press
				}
			}
		}
	</script>
	<style>
		input,textarea,select{
			display: inline-block;
			/* 	padding: 4px; */
			margin-bottom: 3px;
			line-height: 13px;
			/* 	color: #555555; */
			border: 1px solid #cccccc;
			-webkit-border-radius: 3px;
			-moz-border-radius: 3px;
			border-radius: 3px;
			margin-top: 3px
		}
	</style>
</head>
<body onload="NewEmployee();">
<div id="ddcolortabsline">&nbsp;</div>
<form action="/addemployee" method="post" name="AddEmployeeForm" id="addemployeefrm">
	<div id="cos">
		<div class="statusquo ok">
			<div id="hoja">
				<div id="blanquito">
					<div id="padding">
						<div>
	<span style="font-size:1.6em;font-weight:normal;color:#838383;margin:30px 0px 15px 0px;border-bottom:1px dotted #333;padding:0 0 .3em 0;">
		<spring:message code="BzComposer.newemployee.addnewemployeetitle" />
	</span>
						</div>
						<br/>
						<div  style="height:630px;width: 1600px;">
							<div id="General-1">
								<div id="content1" class="tabPage" >
									<!-- add here the content of first tab -->
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0" >
											<thead>
											<tr>
												<th colspan="9" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.addnewemployee"/>
												</th>
											</tr>
											</thead>
											<tbody>
											<tr>
												<td id="t_title" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.title"/>
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td style="font-size:1em;">
													<select name="title"   style="font-size:1em;">
														<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
														<c:forEach items="${titleList}" var="obj">
															<option value="${obj.value}">${obj.label}</option>
														</c:forEach>
													</select>
													<errors path="title" />
												</td>

					                            <td id="t_fname" style="font-size:1em;">
													<spring:message code="BzComposer.global.firstname" />
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td style="font-size:1em;">
													<input type="text" name="fname" style="width:90%" />
													<errors path="fname" />
												</td>
												<td id="t_nname" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.middlename" />
												</td>
												<td style="font-size:1em;">
													<input type="text" name="mname" style="width:90%" />
												</td>
												<td colspan="3">&nbsp;</td>
											</tr>
											<tr>
                                                <td id="t_lname" style="font-size:1em;">
													<spring:message code="BzComposer.global.lastname"/>
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span></td>
												<td style="font-size:1em;">
													<input type="text" name="lname" />
													<errors path="lname" />
												</td>
                                                <td id="t_ssn" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.ssn" />
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td style="font-size:1em;">
													<input type="text" name="ssn" />
													<errors path="ssn" />
												</td>
                                                <td nowrap id="t_dob" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.dateofbirth"/>
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td nowrap style="font-size:1em;">
													<input type="text" name="dob" name="dob"  size="10" readonly="true" />
													<img src="${pageContext.request.contextPath}/images/cal.gif"
														 onclick="displayCalendar(document.AddEmployeeForm.dob,'mm-dd-yyyy',this);">
													<br/>
													<errors path="dob" />
												</td>
												<td colspan="3">&nbsp;</td>
											</tr>
											<tr>
                                                <td id="t_jtitle" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.jobtitle" />
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td style="font-size:1em;">
													<select name="jtitle"   style="font-size:1em;">
														<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
														<c:forEach items="${jtitleList}" var="obj">
															<option value="${obj.value}">${obj.label}</option>
														</c:forEach>
													</select>
													<errors name="jtitle" />
												</td>
												<td colspan="7">&nbsp;</td>

											</tr>
                                            <tr>
												<td id="t_address1" style="font-size:1em;">
													<spring:message code="BzComposer.global.address1"/>
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td colspan="5" style="font-size:1em;">
													<!-- <textarea name="address1"  cols="40"></textarea> -->
 													<input type="text" name="address1" style="width:50%" />
													<errors path="address1"/>
												</td>
												<td colspan="3">&nbsp;</td>
											</tr>

                                            <tr>
												<td id="t_address2" style="font-size:1em;">
													<spring:message code="BzComposer.global.address2"/>
												</td>
												<td colspan="5" style="font-size:1em;">
													<!-- <textarea name="address2"  cols="40"></textarea> -->
													<input type="text" name="address2" style="width:50%" />

												</td>
												<td colspan="3">&nbsp;</td>
											</tr>
                                            <tr>
                                                <td id="t_zip" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.zip"/>
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td style="font-size:1em;">
													<input type="text" name="zip" />
													<errors path="zip" />
												</td>
                                                <td id="t_city" style="font-size:1em;">
													<spring:message code="BzComposer.global.city" />
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td style="font-size:1em;">
													<input type="text" name="city" />
													<errors path="city"/>
												</td>
                                                <td id="t_state" style="font-size:1em;">
													<spring:message code="BzComposer.global.state"/>
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td id="t_statedata" colspan="2" style="font-size:1em;">
													<errors path=""/>
												</td>
                                               <td id="t_province" style="font-size:1em;">
													<spring:message code="BzComposer.global.province"/>
													<span class="inputHighlighted"></span>
												</td>
												<td style="font-size:1em;">
													<input type="text" name="province"  />
												</td>
 											</tr>
                                            <tr>
												<td id="t_country" style="font-size:1em;">
													<spring:message code="BzComposer.global.country"/>
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td style="font-size:1em;">
													<select name="country"  style="font-size:1em;">
														<option value="0"><spring:message code="BzComposer.ComboBox.Select" /></option>
														<c:forEach items="${cList}" var="obj">
															<option value="${obj.value}">${obj.label}</option>
														</c:forEach>
													</select>
													<errors name="country"/>
												</td>
                                                <td id="t_phone" style="font-size:1em;">
                                                    <spring:message code="BzComposer.global.phone"/>
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td style="font-size:1em;">
													<input type="text" name="phone" onkeypress="return numbersonly(event,this.value)" />
													<errors path="phone"/>
												</td>
												<td style="font-size:1em;" colspan="3">
                                                    <input type="checkbox" name="sameAsMobileNumber" />
                                                    <spring:message code="BzComposer.global.sameasmobileno"/>
                                                </td>
                                                <td id="t_phone" style="font-size:1em;">
                                                     <spring:message code="BzComposer.newemployee.mobile" />
													 <span class="inputHighlighted"></span>
												</td>
                                                <td style="font-size:1em;">
                                                     <input type="text" name="mobile" onkeypress="return numbersonly(event,this.value)" />                                                                <errors path="phone"/>
                                                </td>

											</tr>
                                            <tr>
												<td id="t_email" style="font-size:1em;">
													<spring:message code="BzComposer.global.email"/>
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td style="font-size:1em;">
													<input type="text" name="email" />
													<errors path="email" />
												</td>
                                                <td id="t_emptype" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.employeetype"/>
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td style="font-size:1em;">
													<select name="emptype"  style="font-size:1em;">
														<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
														<c:forEach items="${emptypeList}" var="obj">
															<option value="${obj.value}">${obj.label}</option>
														</c:forEach>
													</select>
													<errors path="emptype"/>
												</td>
                                                <td nowrap id="t_dos" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.dateofstarted" />
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td nowrap style="font-size:1em;">
													<input type="text" name="dos" name="dos"  size="10" readonly="true" />
													<img src="${pageContext.request.contextPath}/images/cal.gif"
														 onclick="displayCalendar(document.AddEmployeeForm.dos,'mm-dd-yyyy',this);">
													<br/>
													<errors path="dos" />
												</td>

												<td colspan="3">&nbsp;</td>
											</tr>
											<tr>
												<td nowrap id="t_doa" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.dateofadded"/>
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td style="font-size:1em;">
													<input type="text" path="doa" name="doa"  size="10" readonly="true" />
													<img src="${pageContext.request.contextPath}/images/cal.gif"
														 onclick="displayCalendar(document.AddEmployeeForm.doa,'mm-dd-yyyy',this);">
													<br/>
													<errors path="doa" />
												</td>
											    <td id="t_terminated" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.terminated"/>
												</td>
												<td style="font-size:1em;">
													<input type="checkbox" name="terminated" value="y" />
												</td>
												<td id="t_dot" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.terminateddate"/>
												</td>
												<td style="font-size:1em;">
													<input type="text" name="dot" name="dot" size="10" readonly="true" />
													<img src="${pageContext.request.contextPath}/images/cal.gif"
														 onclick="displayCalendar(document.AddEmployeeForm.dot,'mm-dd-yyyy',this);">
												</td>
												<td colspan="3">&nbsp;</td>
											</tr>
											<tr bgcolor="#ffffff">
												<td id="t_memo" style="font-size:1em;">
													<spring:message code="BzComposer.global.memo" />
												</td>
												<td colspan="5" style="font-size:1em;">
													<textarea name="memo" cols="40"></textarea>
												</td>
												<td colspan="3">&nbsp;</td>
											</tr>
											<div align="center" id="error_registration" style="display: block;"></div>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div id="payroll-2">
								<div id="content2" class="tabPage">
									<div id="table-negotiations">
										<table class="tabla-listados" cellspacing="0">
											<thead>
											<tr>
												<th class="emblem" colspan="8" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.payrolltaxinfo"/>
												</th>
											</tr>
											</thead>
											<tbody>
											<tr>
												<td id="t_filing" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.filingstatus" />
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td id="t_filling" style="font-size:1em;">
													<select name="filingStatus"   style="font-size:1em;">
														<option value="" ><spring:message code="BzComposer.ComboBox.Select" /></option>
														<c:forEach items="${filingList}" var="obj">
															<option value="${obj.value}">${obj.label}</option>
														</c:forEach>
													</select>
													<errors path="filingStatus"/>
												</td>
												<td id="t_allowance" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.allowance" />
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td id="t_allowance" style="font-size:1em;">
													<input type="text" name="allowance" Class="ctrl" maxlength="2" onkeypress="return numbersonly(event,this.value)" />
													<errors path="allowance" />
												</td>
												<td id="t_stateworked" align="right" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.stateworked" />
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td id="t_stateworked" style="font-size:1em;">
													<select name="stateworked"  style="font-size:1em;">
														<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
														<c:forEach items="${statewList}" var="obj">
															<option value="${obj.value}">${obj.label}</option>
														</c:forEach>
													</select>
													<errors path="stateworked"/>
												</td>
												<td colspan="2">&nbsp;</td>
											</tr>
											<tr>
												<td id="t_payrollmethod" style="font-size:1em;">
													<b><spring:message code="BzComposer.newemployee.payrollmethod"/></b>
													<span class="inputHighlighted"></span>
												</td>
												<td colspan="7">&nbsp;</td>
											</tr>
											<tr>
												<td id="t_period" style="font-size:1em;"></td>
												<td id="t_period" style="font-size:1em;">
													<input type="radio" name="payMethod" value="1"/>
													&nbsp;<spring:message code="BzComposer.newemployee.hourly"/>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="radio" name="payMethod" value="2"/>
													&nbsp;<spring:message code="BzComposer.newemployee.daily"/>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="radio" name="payMethod" value="3"/>
													&nbsp;<spring:message code="BzComposer.newemployee.salary"/>
													&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
												<td id="t_period" style="font-size:1em;"></td>
												<td colspan="5">&nbsp;</td>
											</tr>
											<tr>
												<td id="t_amount" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.amount"/>
													<span class="inputHighlighted">*</span>
												</td>
												<td id="t_fname" style="font-size:1em;">
													<input type="text" name="amount" />
													<errors path="amount"/>
												</td>
												<td id="t_lname" style="font-size:1em;">
													<spring:message code="BzComposer.newemployee.payperiod"/>
													<span class="inputHighlighted"><spring:message code="BzComposer.CompulsoryField.Validation" /></span>
												</td>
												<td id="t_lname" width="16%" style="font-size:1em;">
													<select name="payperiod" Class="ctrl"  style="font-size:1em;">
														<option value=""><spring:message code="BzComposer.ComboBox.Select" /></option>
														<c:forEach items="${periodList}" var="obj">
															<option value="${obj.value}">${obj.label}</option>
														</c:forEach>
													</select>
													<errors path="payperiod"/>
												</td>
												<td width="20%" id="t_lname" align="right" style="font-size:1em;">&nbsp;
													<span class="inputHighlighted"></span>
												</td>
												<td width="20%" id="t_lname" align="right" style="font-size:1em;">
													&nbsp;<span class="inputHighlighted"></span>
												</td>
												<td colspan="2">&nbsp;</td>
											</tr>
											</tbody>
										</table>
										<input type="hidden" name="status" value="N" />
										<input type="hidden" name="employeeID" value="N" />
									</div>
								</div>
							</div>
						</div>
						<br/>
						<table cellpadding="0" cellspacing="0" border="0" width=100% align=center>
							<tr>
								<td colspan="6" align="center" style="font-size:1em;">
									<input type="reset" path="Clear" Class="formbutton" style="font-size:1em;" value="<spring:message code='BzComposer.newemployee.cleardata'/>">

									<%-- <input type="reset" value="Clear Data" Class="formbutton" style="font-size:1em;"/> --%>
									&nbsp;&nbsp;&nbsp;
									<a Class="formbutton" style="font-size:1em;" onclick="updateemployee();"><spring:message code='BzComposer.newemployee.savenewemployee'/></a>

									<%-- <input type="submit" value="Save New Employee" Class="formbutton" style="font-size:1em;"/> --%>
								</td>
							</tr>
						</table>
						<!-- 	<input type="hidden" name="eid" action="add" /> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
<script>
        var defaultCountry = 2;
        var defaultStateWork = 2;

        $(document).ready(function(){

        $("input[name='province']").hide();


        $("input[name='mobile']").prop( "disabled", true);

        $("input[name='sameAsMobileNumber']").change(function() {
               $("input[name='mobile']").prop( "disabled", !this.checked );
        });


        $("input[name='sameAsMobileNumber']").change(function() {
               $("input[name='mobile']").prop( "disabled", !this.checked );
        });

        $("select[name='country']").on('change', function() {

           $.ajax({
                                               								type: "GET",
                                               						   		url:"/GetState/"+this.value,
                                               					 			success : function(res) {
                                               					 			    console.log('res',res)
                                               					 			       $("select[name='state']").children().remove();
                                               					 			       $("select[name='state']").hide();
                                               					 			       $("input[name='province']").hide();

                                                                                   if(res && res.length > 0){
                                                                                      $("select[name='state']").show();
                                                                                        $.each(res, function( index, value ) {
                                                                                         console.log(index,value)
                                                                                         $("select[name='state']").append(new Option(value.name, value.id));
                                                                                        });

                                                                                  }else{
                                                                                        $("input[name='province']").show();
                                                                                  }



                                                						   		},
                                               								error : function(data) {
                                               									event.preventDefault();

                                               								    return false;
                                               								}
                                               							});

        });




        $("input[name='zip']").on("input",function(){
                if($.isNumeric($(this).val() )){
                     $.ajax({
                            								type: "GET",
                            						   		url:"/CountryAndState/"+this.value,
                            					 			success : function(res) {
                            					 			    console.log('res',res)
                                                                if(res && res.length > 0){
                                                                    $("input[name='city']").val(res[0])
                                                                    $("select[name='state'] option").filter(function () { return $(this).html() == res[1]; }).prop('selected', true)

                                                                }

                             						   		},
                            								error : function(data) {
                            									event.preventDefault();

                            								    return false;
                            								}
                            							});
                }
                else{
                    $(this).val('')
                }
          });
        });

	document.AddEmployeeForm.payMethod[0].checked=true;
	function NewEmployee(){
		document.AddEmployeeForm.fname.value="";
		document.AddEmployeeForm.lname.value="";
		document.AddEmployeeForm.mname.value="";
		document.AddEmployeeForm.title.value="";
		document.AddEmployeeForm.jtitle.value="";
		document.AddEmployeeForm.dob.value="";
		// document.AddEmployeeForm.dos.value="";
		document.AddEmployeeForm.doa.value="";
		document.AddEmployeeForm.address1.value="";
		document.AddEmployeeForm.address2.value="";
		document.AddEmployeeForm.city.value="";
		document.AddEmployeeForm.zip.value="";
		document.AddEmployeeForm.province.value="";
		document.AddEmployeeForm.country.value=defaultCountry;
		refreshItemsNow(defaultCountry)
        // 	document.AddEmployeeForm.state.value="";
		document.AddEmployeeForm.phone.value="";
		document.AddEmployeeForm.mobile.value="";
		document.AddEmployeeForm.email.value="";
		document.AddEmployeeForm.emptype.value="";
		document.AddEmployeeForm.ssn.value="";
		document.AddEmployeeForm.terminated.checked = false;
		document.AddEmployeeForm.dot.value="";
		document.AddEmployeeForm.memo.value="";
		document.AddEmployeeForm.filingStatus.value="";
		document.AddEmployeeForm.allowance.value="";
		document.AddEmployeeForm.stateworked.value= defaultStateWork;
		document.AddEmployeeForm.payMethod.value="";
		document.AddEmployeeForm.amount.value="";
		document.AddEmployeeForm.payperiod.value="";
	}



	function updateemployee() {
		if(document.AddEmployeeForm.fname.value=="")
		{
			return enterfirstNameDialog();
			document.AddEmployeeForm.fname.focus();
		}else if(document.AddEmployeeForm.lname.value==""){
			return enterlastNameDialog();
			document.AddEmployeeForm.lname.focus();
		}else if(document.AddEmployeeForm.title.value==""){
			return entertitleDialog();
			document.AddEmployeeForm.title.focus();
		}else if(document.AddEmployeeForm.jtitle.value==""){
			return enterjtitleDialog();
			document.AddEmployeeForm.jtitle.focus();
		}else if(document.AddEmployeeForm.dob.value==""){
			return enterdobDialog();
			document.AddEmployeeForm.dob.focus();
		}else if(document.AddEmployeeForm.dos.value==""){
			return enterdosDialog();
			document.AddEmployeeForm.dos.focus();
		}else if(document.AddEmployeeForm.doa.value==""){
			return enterdoaDialog();
			document.AddEmployeeForm.doa.focus();
		}else if(document.AddEmployeeForm.address1.value==""){
			return enteraddress1Dialog();
			document.AddEmployeeForm.address1.focus();
		}else if(document.AddEmployeeForm.address2.value==""){
			return enteraddress2Dialog();
			document.AddEmployeeForm.address2.focus();
		}else if(document.AddEmployeeForm.city.value==""){
			return entercityDialog();
			document.AddEmployeeForm.city.focus();
		}else if(document.AddEmployeeForm.zip.value==""){
			return enterzipDialog();
			document.AddEmployeeForm.zip.focus();
		}else if(document.AddEmployeeForm.country.value==""){
			return entercountryDialog();
			document.AddEmployeeForm.country.focus();
		}else if(document.AddEmployeeForm.state.value==""){
			return enterstateDialog();
			document.AddEmployeeForm.state.focus();
		}else if(document.AddEmployeeForm.phone.value==""){
			return enterphoneDialog();
			document.AddEmployeeForm.phone.focus();
		}else if(document.AddEmployeeForm.sameAsMobileNumber.checked && document.AddEmployeeForm.mobile.value==""){
			return enterphoneDialog();
			document.AddEmployeeForm.mobile.focus();
		}else if(document.AddEmployeeForm.email.value==""){
			return enteremailDialog();
			document.AddEmployeeForm.email.focus();
		}else if(document.AddEmployeeForm.emptype.value==""){
			return enteremptypeDialog();
			document.AddEmployeeForm.emptype.focus();
		}else if(document.AddEmployeeForm.ssn.value==""){
			return enterssnDialog();
			document.AddEmployeeForm.ssn.focus();
		}else if(document.AddEmployeeForm.filingStatus.value==""){
			return enterfilingStatusDialog();
			document.AddEmployeeForm.filingStatus.focus();
		}else if(document.AddEmployeeForm.allowance.value==""){
			return enterallowanceDialog();
			document.AddEmployeeForm.allowance.focus();
		}else if(document.AddEmployeeForm.stateworked.value==""){
			return enterstateworkedDialog();
			document.AddEmployeeForm.stateworked.focus();
		}else if(document.AddEmployeeForm.amount.value==""){
			return enteramountDialog();
			document.AddEmployeeForm.amount.focus();
		}else if(document.AddEmployeeForm.payperiod.value==""){
			return enterpayperiodDialog();
			document.AddEmployeeForm.payperiod.focus();
		}else

		{
			if(validate())
			{
 				event.preventDefault();
				$("#addNewemployeeDialog").dialog({
					resizable: false,
					height: 200,
					width: 500,
					modal: true,
					buttons: {
						"<spring:message code='BzComposer.global.ok'/>": function () {
 							$(this).dialog("close");
							document.forms['addemployeefrm'].action="addemployee";
							document.forms['addemployeefrm'].submit();
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
	}



	function enterfirstNameDialog()
	{
		event.preventDefault();
		$("#enterfirstNameDialog").dialog({
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

	function enterlastNameDialog() {
		event.preventDefault();
		$("#enterlastNameDialog").dialog({
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

	function entertitleDialog() {
		event.preventDefault();
		$("#entertitleDialog").dialog({
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

	function enterjtitleDialog() {
		event.preventDefault();
		$("#enterjtitleDialog").dialog({
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

	function enterdobDialog() {
		event.preventDefault();
		$("#enterdobDialog").dialog({
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
	function enterdosDialog() {
		event.preventDefault();
		$("#enterdosDialog").dialog({
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
	function enterdoaDialog() {
		event.preventDefault();
		$("#enterdoaDialog").dialog({
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

	function enteraddress1Dialog() {
		event.preventDefault();
		$("#enteraddress1Dialog").dialog({
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

	function enteraddress2Dialog() {
		event.preventDefault();
		$("#enteraddress2Dialog").dialog({
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

	function entercityDialog() {
		event.preventDefault();
		$("#entercityDialog").dialog({
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

	function enterzipDialog() {
		event.preventDefault();
		$("#enterzipDialog").dialog({
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

	function entercountryDialog() {
		event.preventDefault();
		$("#entercountryDialog").dialog({
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

	function enterstateDialog() {
		event.preventDefault();
		$("#enterstateDialog").dialog({
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

	function enterphoneDialog() {
		event.preventDefault();
		$("#enterphoneDialog").dialog({
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

	function enteremailDialog() {
		event.preventDefault();
		$("#enteremailDialog").dialog({
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

	function enteremptypeDialog() {
		event.preventDefault();
		$("#enteremptypeDialog").dialog({
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

	function enterssnDialog() {
		event.preventDefault();
		$("#enterssnDialog").dialog({
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

	function enterfilingStatusDialog() {
		event.preventDefault();
		$("#enterfilingStatusDialog").dialog({
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

	function enterallowanceDialog() {
		event.preventDefault();
		$("#enterallowanceDialog").dialog({
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

	function enterstateworkedDialog() {
		event.preventDefault();
		$("#enterstateworkedDialog").dialog({
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


	function enteramountDialog() {
		event.preventDefault();
		$("#enteramountDialog").dialog({
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

	function enterpayperiodDialog() {
		event.preventDefault();
		$("#enterpayperiodDialog").dialog({
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

	function validate()
	{
		var email = document.AddEmployeeForm.email.value;
		var mail =String(email);
		var pattern=/^[_0-9a-zA-z]+(\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\.[A-Za-z]+)+$/;
		if(email=="")
		{
			return enterEmailValidationDialog();
		}
		else if (!pattern.test(email))
		{


			return showEmailValidationDialog();
			document.AddEmployeeForm.email.focus();
			return false;
		}
		if(mail.length>=50)
		{

			return showEmailLengthValidationDialog();
			document.AddEmployeeForm.email.value="";
			document.AddEmployeeForm.email.focus();
			return false;
		}

		return true;
	}

	function showEmailValidationDialog()
    	{
    		event.preventDefault();
    		$("#showEmailValidationDialog").dialog({
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

	function enterEmailValidationDialog()
	{
		event.preventDefault();
		$("#enterEmailValidationDialog").dialog({
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
<!-- Dialog box used in this page -->
<div id="enterEmailValidationDialog" style="display:none;">
	<p><spring:message code="Bzcomposer.updatevendor.enteremailaddress"/></p>
</div>
<div id="showEmailValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.invalidEmail"/></p>
</div>
<div id="enterfirstNameDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.fname.problem"/></p>
</div>
<div id="enterlastNameDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.lname.problem"/></p>
</div>
<div id="entertitleDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.title.problem"/></p>
</div>
<div id="enterjtitleDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.jtitle.problem"/></p>
</div>
<div id="enterdobDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.dob.problem"/></p>
</div>
<div id="enterdosDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.dos.problem"/></p>
</div>
<div id="enterdoaDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.doa.problem"/></p>
</div>
<div id="enteraddress1Dialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.address1.problem"/></p>
</div>
<div id="enteraddress2Dialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.address1.problem"/></p>
</div>
<div id="entercityDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.city.problem"/></p>
</div>
<div id="enterzipDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.zip.problem"/></p>
</div>
<div id="entercountryDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.country.problem"/></p>
</div>
<div id="enterstateDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.state.problem"/></p>
</div>
<div id="enterphoneDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.phone.problem"/></p>
</div>
<div id="enteremailDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.email.problem"/></p>
</div>
<div id="enteremptypeDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.emptype.problem"/></p>
</div>
<div id="enterssnDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.ssn.problem"/></p>
</div>
<div id="enterfilingStatusDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.filingStatus.problem"/></p>
</div>
<div id="enterallowanceDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.allowance.problem"/></p>
</div>
<div id="enterstateworkedDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.stateworked.problem"/></p>
</div>
<div id="enteramountDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.amount.problem"/></p>
</div>
<div id="enterpayperiodDialog" style="display:none;">
	<p><spring:message code="AddEmployeeForm.payperiod.problem"/></p>
</div>
<div id="addNewemployeeDialog" style="display:none;">
	<p>Do You Want To Insert New Employee?</p>
</div>