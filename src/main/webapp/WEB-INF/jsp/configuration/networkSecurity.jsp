<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page errorPage="/include/sessionExpired.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/WEB-INF/jsp/include/headlogo.jsp" />
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<jsp:include page="/WEB-INF/jsp/include/menu.jsp" />
<title><spring:message code="BzComposer.networksecuritytitle" /></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/custom.js"></script>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<!-- Remember to include jQuery :) -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>  -->

<!-- jQuery Modal -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" /> -->

 <style type="text/css">
 /* * {
  padding: 0;
  margin: 5px;
  text-align: center;
} */
.modal {
  display: none; /* Hidden by default */
}
.fonts
{
	font-size: 1.2em;
}
.usersTblNew{
    vertical-align: top;width: 100%;border: 1px solid rgb(207, 207, 207);
}
.usersTblNew thead tr td{
    padding: 5px 0px 5px 5px;font-size: 12px;;
}
.usersTblNew tbody tr td{
    padding: 5px 0px 5px 5px;font-size: 12px;;
}
.draft td{ color: #ffffff;background: rgba(50, 58, 60, 0.63); }
</style>

<script type="text/javascript">
debugger;
var membershipLevel1 = "<%= request.getAttribute("membershipLevel")%>";
if(membershipLevel1 == "Standard"){
	$("#userlistlable11").hide();
	$("#userandpasswordheading").hide();
	$("#userandpassworddata").hide();
}



debugger;
var Role = "<%= request.getAttribute("Role")%>";
//$("#stateRow").hide();
if(Role=='User'){
	$("#userlistlable").hide();
	/* $("#userandpasswordheading").hide();
	$("#userandpassworddata").hide();
	$("#lastline").hide(); */

}else{
}
function adduser1() {
	debugger;
	var membershipLevel = "<%= request.getAttribute("membershipLevel")%>";
	var size = "<%= request.getAttribute("userSize")%>";
	if(membershipLevel == "Standard"){
		if(size>=1){
			debugger;
			return maxnumberofuserdialog();
		}else{
			$('#AddUser').modal('show');
		}
	}else if(membershipLevel == "Professional"){
		if(size>=5){
			debugger;
			return maxnumberofuserdialog();
		}else{
			$('#AddUser').modal('show');
		}
	}else if(membershipLevel == "Enterprise"){
		if(size>=10){
			debugger;
			return maxnumberofuserdialog();
		}else{
			$('#AddUser').modal('show');
		}
	}
	/* if(size>=4){
		debugger;
		return maxnumberofuserdialog();

	}else{
		$('#AddUser').modal('show');
	} */
}
function Deleteuser() {
    if($('#selectedUserId').val() == ''){
        alert("<bean:message key='BzComposer.common.selectUserFirst'/>");
        return false;
    }
    $("#showDeleteGroupConfirmDialog").dialog({
        resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "<spring:message code='BzComposer.global.ok'/>": function () {
                window.location.href = "Configuration?tabid=deleteUser&selectedUserId="+$('#selectedUserId').val()+"&userGroupId="+$('#userGroupId').val();
            },
            <spring:message code='BzComposer.global.cancel'/>: function () {
                $(this).dialog("close");
                return false;
            }
        }
    });
}
function backToDivFirst()
{
    document.getElementById("addUserNameAndPassword").style.display = "block";
    document.getElementById("addModuleAccessForUser").style.display = "none";
    document.getElementById("finish").style.display = "none";

    $("#next").attr('readonly',true);
    //$("#finish").style('display','none');
}

function checkValidation(){
    debugger;
    var userEmail = $("#userEmail").val();
    var password1 = $("#userPassword").val();
    var password2 = $("#cpwd").val();
    var groupID = $("#groupID").val();
    var inputadminpassword = $("#adminpassword").val();
    var AdminPassword = '<%= request.getAttribute("AdminPassword")%>';
    var checkEmailAddress = (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(userEmail));
    if (checkEmailAddress == false){
        alert("<bean:message key='BzComposer.common.enterValidEmail'/>");
        return;
    }
    else if(userEmail == ""){
        alert("<bean:message key='BzComposer.common.emailCantBlank'/>");
        return;
    }
    else if(password1.length < 6){
        alert("<bean:message key='BzComposer.common.passwordHint'/>");
        return;
    }
    else if(password1 != password2){
         alert("<bean:message key='BzComposer.common.bothPwdsNotMatch'/>");
         return;
    }
    else if(inputadminpassword != AdminPassword){
         alert("<bean:message key='BzComposer.common.enterValidAdminPwd'/>");
         return;
    }
    else if(groupID == "" || groupID == 0){
        alert("<bean:message key='BzComposer.common.selectGroupFirst'/>");
        return;
    }
    else{
        var formData = $('addnewuser1').serialize();
        $.ajax({
            type : "POST",
            url : "ConfigurationAjax/SaveConfiguration?tabid=addNewUser&userName="+userEmail+"&userpassword="+password1+"&groupID="+groupID,
            data : formData,
            success : function(data) {
                debugger
                $("#showsuccessdialog").dialog({
                    resizable: false,
                    height: 200,
                    width: 500,
                    modal: true,
                    buttons: {
                        "<spring:message code='BzComposer.global.ok'/>": function () {
                            $(this).dialog("close");
                            window.location.href= "Configuration?tabid=config04&&tab=tr4";
                            return false;
                        },
                        "<spring:message code='BzComposer.global.cancel'/>": function () {
                            $(this).dialog("close");
                            window.location.href= "Configuration?tabid=config04&&tab=tr4";
                            return false;
                        }
                    }
                });
            },
            error : function(data) {
                alert("<bean:message key='BzComposer.common.erroroccurred'/>");
                return false;
            }
        });
    }
}

function toggleFunction() {
	debugger;
  var x = document.getElementById("divtoggle");
  var lftmenu = document.getElementById("leftMenu");
  if (x.style.display === "none") {
    x.style.display = "block";
    lftmenu.style.width = "180px";
    lftmenu.style.position = "relative";
  } else {
    x.style.display = "none";
    lftmenu.style.width = "0";
    lftmenu.style.position = "absolute";
  }
} 
$(function() {
    $( "#tabs" ).tabs();
    $( "#tabs1" ).tabs();
    $( "#tabs2" ).tabs();
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
	var o22 = null;
	var o33 = null;
	var oEmail = null;
	var oT = null;
	var nm="";
	var r = null;

	function TestConnection(){
		d = new Date();
		var host = document.configurationForm.mailServer.value;
		oEmail = c(CheckEmailConnection);
		oGET(oEmail,'${pageContext.request.contextPath}/include/testMailServerConnection.jsp?HostName='+host+'&Date='+d);
	}
	
	function getData()
	{
		var modalNewPassword = document.getElementById('modalNewPassword').value;
		var modalConfirmPassword = document.getElementById('modalConfirmPassword').value;
		/* if(modalNewPassword == null){
			alert("<bean:message key='BzComposer.common.bothPwdsNotMatch'/>"+ modalNewPassword);
		}else{
			window.location.href= "Configuration?tabid=ChangeAdministratorPassword&modalNewPassword="+modalNewPassword;
		} */
		window.location.href= "Configuration?tabid=ChangeAdministratorPassword&modalNewPassword="+modalNewPassword;
		//changeUserName&Password
		/* var formData = $('form').serialize();
		$.ajax({
			type : "POST",
			url :   "ConfigurationAjax/SaveConfiguration?tabid=ChangeAdministratorPassword&modalNewPassword="+modalNewPassword,
			data : formData,
			success : function(data) {
				$("#showsuccessdialog").dialog({
						resizable: false,
				        height: 200,
				        width: 500,
				        modal: true,
				        buttons: {
				        	"<spring:message code='BzComposer.global.ok'/>": function () {
				        		$(this).dialog("close");
				                return false;
				        	},
				            "<spring:message code='BzComposer.global.cancel'/>": function () {
				                $(this).dialog("close");
				                return false;
				            }
			        	}
				});
			},
			error : function(data) {
				alert("<bean:message key='BzComposer.common.erroroccurred'/>");
			}
		}); */
	} 
	
	function checkRecords(){
		var size = $("#selectedUser option").length;
		if(size>=4){
			debugger;
			return maxnumberofuserdialog();

		}else{
			window.open("Configuration?tabid=NewUser",null,"scrollbars=yes,height=300,width=700,status=yes,toolbar=no,menubar=no,location=no" );
		}
	}
	function showInActive(){
		var uId = $("#selectedGroupStatus option:selected").val();
		$('select[id="selectedGroupStatus"]').find('option[id="'+uId+'"]').attr("hidden",false);
	}
	function hideInActive(){
        var uId = $("#selectedGroupStatus option:selected").val();
        $('select[id="selectedGroupStatus"]').find('option[id="'+uId+'"]').attr("hidden",true);
    }

    function viewGroupPermissions(){
        if($('#groupID').val() == ''){
            alert("<bean:message key='BzComposer.common.selectGroupFirst'/>");
        }else{
            window.open("Configuration?tabid=addNewGroup&selectedGroupId="+$('#groupID').val()+"&isViewGroupPermissions=true",null,"scrollbars=yes,height=600,width=1200,status=yes,toolbar=no,menubar=no,location=no" );
        }
    }
	
	function addNewGroup(){
		window.open("Configuration?tabid=addNewGroup",null,"scrollbars=yes,height=600,width=1200,status=yes,toolbar=no,menubar=no,location=no" );
		/*var left = (screen.width/2)-(w/2);
		var top = (screen.height/2)-(h/2);
		window.open("Configuration?tabid=addNewGroup",null, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);*/
	}
	function editGroup(){
        if($('#selectedGroupId').val()>0){
            window.open("Configuration?tabid=addNewGroup&selectedGroupId="+$('#selectedGroupId').val(),null,"scrollbars=yes,height=600,width=1200,status=yes,toolbar=no,menubar=no,location=no" );
        }else{
            alert("<bean:message key='BzComposer.common.selectGroupFirst'/>");
        }
    }
	
	function selectUserData(selectedUserId, userGroupId, rID){
		var size = document.getElementById('usersSize').value;
        for(i=0; i<size; i++){
            if(document.getElementById(i+"usrIndex").classList.contains('draft')){
                document.getElementById(i+"usrIndex").classList.remove('draft');
            }
        }
        document.getElementById(rID).className = "draft";
        document.getElementById('selectedUserId').value = selectedUserId;
        document.getElementById('userGroupId').value = userGroupId;
	}

	function selectGroupData(selectedGroupId, rID){
        var size = document.getElementById('groupSize').value;
        for(i=0; i<size; i++){
            if(document.getElementById(i+"gpIndex").classList.contains('draft')){
                document.getElementById(i+"gpIndex").classList.remove('draft');
            }
        }
        document.getElementById(rID).className = "draft";
        document.getElementById('selectedGroupId').value = selectedGroupId;
	}

	function deleteGroup(){
        if($('#selectedGroupId').val() == '' || $('#selectedGroupId').val() == 0){
            alert("<bean:message key='BzComposer.common.selectGroupFirst'/>");
            return false;
        }
        $("#showDeleteGroupConfirmDialog").dialog({
            resizable: false,
            height: 200,
            width: 500,
            modal: true,
            buttons: {
                "<spring:message code='BzComposer.global.ok'/>": function () {
                    window.location.href = "Configuration?tabid=deleteGroup&selectedGroupId="+$('#selectedGroupId').val();
                },
                <spring:message code='BzComposer.global.cancel'/>: function () {
                    $(this).dialog("close");
                    return false;
                }
            }
        });
    }

</script>
</head>
<body onload="init();">
<!-- begin shared/header -->
<form:form name="configurationForm" enctype="MULTIPART/FORM-DATA" method="post" id="form" modelAttribute="configDto">
	<div id="ddcolortabsline">&nbsp;</div>
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">

    <div>
        <span
            style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
            <spring:message code="BzComposer.configuration.configurationtitle" />
        </span>
    </div>
    <div>
        <div>
            <c:if test="${not empty Labels}">
                <input type="hidden" name="lsize" id="lblsize" value='${Labels.size()}' />
                <c:forEach items="${Labels}" var="lbl" varStatus="loop">
                    <input type="hidden" id='${loop.index}lid' name='${loop.index}lidname' value='${lbl.value}' />
                    <input type="hidden" id='${loop.index}lname' name='${loop.index}lnm' value='${lbl.label}' />
                </c:forEach>
            </c:if>
        </div>
        <div id="table-negotiations" style="padding: 0; border: 1px solid #ccc;">
            <span style="font-size:30px;cursor:pointer; margin-left: 20px;" onclick="toggleFunction()">&#9776;</span>
            <table cellspacing="0" style="border: 0;width: 100%; overflow-y: scroll;"
                class="section-border">
                <tr>
                    <td id="leftMenu" style="position: relative; width: 180px;">
                        <table>
                            <tr>
                                <td><jsp:include page="menuPage.jsp" /></td>
                            </tr>
				<%-- <tr align="center">
					<td><input type="button" name="Revoke" class="formButton" onclick="RevokeValues();"
						    value='<spring:message code="BizComposer.Configuration.RevokeButton"/>' />
					<input type="button" name="Save" class="formButton" onclick="SaveValues();"
						    value='<spring:message code="BizComposer.Configuration.SaveButton"/>' />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr> --%>
			</table>
			</td>

			<td valign="top" style="padding-right: 20px; padding-bottom: 20px;">
			<div id="uName" class="modal" style="height:auto;">
  				<form name="changeUserName&Password">
 					<table border="2" style="width:80%">
 					<tr>
     					<td colspan="2" align="center" style="font-size: 12px; padding: 5px;">
     					<spring:message code="BzComposer.configuration.configadmin"  /></td>
   					</tr>
   					<tr>
   						<td style="font-size: 14px;">
   							<spring:message code="BzComposer.configuration.username"/>:
   						</td>
   						<td style="font-size: 14px;">
   							<form:input path="emailAddress" id="modalUserName" />
   						</td>
   					</tr>
   					<tr>
   						<td style="font-size: 14px;">
   							<spring:message code="BzComposer.configuration.oldpassword"/>:
   						</td>

   						<td style="font-size: 14px;">
   							<form:input type="password" path="password" id="modalOldPassword" />
   						</td>
   					</tr>
   					<tr>
   						<td style="font-size: 14px;">
   							<spring:message code="BzComposer.configuration.newpassword"/>:
   						</td>
   						<td style="font-size: 14px;">
   							<form:input type="password" path="newPassword" id="modalNewPassword" />
   						</td>
   					</tr>
   					<tr>
   						<td style="font-size: 14px;">
   							<spring:message code="BzComposer.configuration.confirmpassword"/>:
   						</td>
   						<td style="font-size:14px;">
   							<form:input type="password" path="newPassword" id="modalConfirmPassword" />
   						</td>
   					</tr>
   					<tr>
   						<td style="font-size:14px;">
   							<input type="submit" name="Save" id="Save" value='<spring:message code="BzComposer.configuration.configadminbtn"/>' onclick="getData()" style="font-size:1em;"/>
   						</td>
   						<td style="font-size:14px;">
   							<a href="#" rel="modal:close">
   								<input type="reset" name="Cancel" id="Cancel" value='<spring:message code="BzComposer.global.cancel"/>' style="font-size:1em;"/>
							</a>
   						</td>
					</tr>
    				</table>

   				</form>
   				<br>
			</div>
			<div id="AddUser" class="modal" style="height:auto;">
  				<form name="addnewuser1">
 					<div id="ddcolortabsline">&nbsp;</div>
					<div id="cos">
						<div class="statusquo ok">
							<div id="hoja">
								<div id="blanquito">
									<div id="padding">
										<div>
											<div id="table-negotiations">
												<table>
													<tr>
														<%-- <td style="width:auto; height:auto;">
															<img id="img1" src='images/bzcomposer logo3.gif' style="display:block" />
														</td> --%>
														<td colspan="3">
															<div id="addUserNameAndPassword" style="display: block; width: 450px;">
																<table style="font-size: 12px;">
																	<tr height="30px;">
																		<th colspan="2" align="left">
																			<spring:message code="BizComposer.Configuration.Networking.userNameAndPassword"/>
																		</th>
																	</tr>
																	<tr height="30px;">
																		<th colspan="2" align="left">
																			<spring:message code="BizComposer.Configuration.Networking.provideUserNameAndPassword"/>
																		</th>
																	</tr>
																	<tr height="30px;">
																		<td>
																			<spring:message code="BzComposer.contactus.formemail"/>
																		</td>
																		<td>
																			<input type="email" name="userEmail" id="userEmail" style="width:250px;">
																		</td>
																	</tr>
																	<tr height="30px;">
																		<td>
																			<spring:message code="Bizcomposer.password"/>
																		</td>
																		<td>
																			<input type="password" name="userPassword" id="userPassword" style="width:250px;">
																		</td>
																	</tr>
																	<tr height="30px;">
																		<td>
																			<spring:message code="Bizcomposer.confirmPassword"/>
																		</td>
																		<td>
																			<input type="password" id="cpwd" style="width: 250px;">
																		</td>
																	</tr>
																	<tr height="30px;">
																		<td>
																			<spring:message code="Bizcomposer.adminPassword"/>
																		</td>
																		<td>
																			<input type="password"  name="adminpassword" id="adminpassword" style="width: 250px;">
																		</td>
																	</tr>
																	<tr height="30px;">
																		<th colspan="2" align="left">
																			<spring:message code="BizComposer.Configuration.Networking.selectGroupForAccessPermissions"/>
																		</th>
																	</tr>
																	<tr height="30px;">
																		<td width="120px;">
																			<spring:message code="Bizcomposer.groupName"/>
																		</td>
																		<td>
																			<form:select path="groupID" style="width:250px;">
																			    <form:options items="${configDto.listOfExistingGroup}" itemValue="selectedGroupId" itemLabel="groupName" />
																			</form:select>
																		</td>
																	</tr>
																	<tr>
																		<td></td>
																		<td style="font-size:14px;">
																			<button type="button" class="formButton" onclick="viewGroupPermissions()">
																				<spring:message code="BizComposer.Configuration.Networking.viewGroupPermissions"/>
																			</button>
																		</td>
																	</tr>
																	<tr>
                                                                        <td colspan="2" align="right" style="font-size:14px;padding-top:10px;">
                                                                            <button type="button" class="formButton" id="next" onclick="checkValidation()">
                                                                                <spring:message code="BzComposer.global.save" />
                                                                            </button>&nbsp;&nbsp;
                                                                            <input type="button" class="formButton" id="finish" value="Finish" readonly="readonly" style="display:none;" onclick="submitForm()">
                                                                            <button type="button" class="formButton" id="help" onclick="showHelp()">
                                                                                <spring:message code="BzComposer.Help" />
                                                                            </button>&nbsp;&nbsp;
                                                                            <a href="#" rel="modal:close">
                                                                                <button type="reset" class="formButton" name="Cancel" id="Cancel">
                                                                                    <spring:message code="BzComposer.global.cancel" />
                                                                                </button>
                                                                            </a>
                                                                        </td>
                                                                    </tr>
																</table>
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
					</div>
   				</form>
   				<br>
			</div>
			<!--  Networking & Security Starts -->
			<div id="nw" style="display:none;">
			<table class="table-notifications" width="80%">
				<tr>
					<th colspan="5" align="left" style="font-size: 12px; padding: 5px;">
						<spring:message code="BzComposer.configuration.administrator" />
					</th>
				</tr>
				<tr>
					<td style="font-size: 12px;">
						<spring:message code="BzComposer.configuration.username" />:
					</td>
					<td style="font-size: 12px;">
						<%-- <b><form:text path="UserName" id="UserName"></form:text></b> --%>
						<form:input path="emailAddress"  id="UserName" readonly="true" />
					</td>
				</tr>
				<tr>
					<td style="font-size: 12px;">
						<spring:message code="BzComposer.configuration.password" />
					</td>
					<td style="font-size: 12px;">
						<form:input type="password" path="password" size="20" maxlength="30" readonly="true" />
					</td>
					<td style="font-size:12px;">
						<!-- <input type="button" id="changePassword" name="changePassword" value="changePassword" onclick="password();"> -->
						<a href="#uName" rel="modal:open">
							<button type="button" name="changePassword" class="formButton" style="font-size: 14px; width:140px;">
							    <spring:message code="Bizcomposer.changePassword"/>
							</button>
						</a>
					</td>
				</tr>
				<tr>
					<th colspan="5" align="left" style="font-size: 12px; padding: 5px;">
						<spring:message code="BzComposer.configuration.swithcusermode" />
					</th>
				</tr>
				<tr>
					<td colspan="5" style="font-size: 12px;">
						<form:radiobutton path="multiUserConnection" id="multiUserConnection"  value="0" />
						<spring:message code="BzComposer.configuration.usermode.singleusermode" />
					</td>
				</tr>
				<tr>
					<td colspan="5" style="font-size: 12px;">
						<form:radiobutton path="multiUserConnection" id="multiUserConnection" value="1" />
						<spring:message code="BzComposer.configuration.usermode.multiusermode" />
					</td>
				</tr>
				<tr id="userlistlable11">
					<th colspan="5" align="left" style="font-size: 12px; padding: 5px;">
						<spring:message code="BzComposer.configuration.userlist" />
					</th>
				</tr>
				<tr>
                    <td colspan="3" style="font-size:12px;">
                    <div style="overflow:auto;height:200;" class="section-border">
                    <table cellspacing="0" border="1" class="usersTblNew">
                        <thead>
                            <tr>
                                <td><b><spring:message code="BzComposer.configuration.username" /></b></td>
                                <td><b><spring:message code="BzComposer.configuration.password" /></b></td>
                                <td><b><spring:message code="BzComposer.configuration.groupname" /></b></td>
                                <td><b><spring:message code="BzComposer.configuration.status" /></b></td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${not empty configDto.listOfExistingUserList}">
                                <input type="hidden" id="usersSize" value='${configDto.listOfExistingUserList.size()}' />
                                <c:forEach items="${configDto.listOfExistingUserList}" var="objList1" varStatus="loop">
                                    <tr id='${loop.index}usrIndex' onclick="selectUserData('${objList1.upsUserId}', '${objList1.groupID}', '${loop.index}usrIndex');">
                                        <td>${objList1.emailAddress}</td>
                                        <td>${objList1.password}</td>
                                        <td>${objList1.groupName}</td>
                                        <td>${objList1.status}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                    </div>
                    </td>
                    <td colspan="2" align="center" valign="middle" style="font-size: 14px;padding-top:40px;">
                        <button type="button" class="formButton" onclick="adduser1();" style="width:120px;">
                            <spring:message code="BzComposer.configuration.adduserbtn"/>
                        </button><br/><br/>
                        <button type="button" class="formButton" style="width:120px;">
                            <spring:message code="BzComposer.configuration.savechangesbtn"/>
                        </button><br/><br/>
                        <button type="button" class="formButton" onclick="Deleteuser();" style="width:120px;">
                            <spring:message code="BzComposer.global.delete"/>
                        </button>
                    </td>
                </tr>
				<tr id="lastline"></tr>
				<tr id="userlistlable11">
                    <th colspan="5" align="left" style="font-size: 12px; padding: 5px;">
                        <spring:message code="BizComposer.Configuration.groupList" />
                    </th>
                </tr>
                <tr>
                    <td colspan="3" style="font-size:12px;">
                    <div style="overflow:auto;height:200;" class="section-border">
                    <table cellspacing="0" border="1" class="usersTblNew">
                        <thead>
                            <tr>
                                <td><b><spring:message code="BzComposer.configuration.groupname" /></b></td>
                                <td><b><spring:message code="BzComposer.configuration.status" /></b></td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${not empty configDto.listOfExistingGroup}">
                                <input type="hidden" id="groupSize" value='${configDto.listOfExistingGroup.size()}' />
                                <c:forEach items="${configDto.listOfExistingGroup}" var="objList1" varStatus="loop">
                                    <tr id='${loop.index}gpIndex' onclick="selectGroupData('${objList1.selectedGroupId}', '${loop.index}gpIndex');">
                                        <td>${objList1.groupName}</td>
                                        <td>${objList1.status}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                    </div>
                    </td>
                    <td colspan="2" align="center" valign="middle" style="font-size: 14px;padding-top:20px;">
                        <button type="button" class="formButton" style="width:120px;" onclick="addNewGroup()">
                            <spring:message code="BzComposer.configuration.addgroupbtn"/>
                        </button><br/><br/>
                        <button type="button" class="formButton" style="width:120px;" onclick="editGroup()">
                            <spring:message code="BzComposer.configuration.editgroupbtn"/>
                        </button><br/><br/>
                        <button type="button" class="formButton" style="width:120px;">
                            <spring:message code="BzComposer.configuration.savechangesbtn"/>
                        </button><br/><br/>
                        <button type="button" class="formButton" style="width:120px;" onclick="deleteGroup()">
                            <spring:message code="BzComposer.global.delete"/>
                        </button>
                    </td>
                </tr>
			</table>
			</div>
			<!-- nw Ends -->
			</td>
			</tr>
	</table>
	<div>
		<form:hidden path="empStateID" />
		<form:hidden path="labelName" />
		<form:hidden path="fileName" />
		<form:hidden path="selectedGroupId" />
	 </div>
	<div>
		<input type="hidden" name="tabid" id="tid" value="" />
        <input type="hidden" name="selectedUserId" id="selectedUserId" />
        <input type="hidden" name="userGroupId" id="userGroupId" />
	</div>
	</div>
	<div>
		<div align="center">
		    <input type="button" onclick="SaveValues()" style="font-size: 1em;" value='<spring:message code="BzComposer.global.save"/>' />
		    <%-- <form:cancel style="font-size:12px;"><spring:message code="BzComposer.global.cancel"/></form:cancel> --%>
		    <input type="reset" name="Cancel" style="font-size:1em;" value="<spring:message code="BzComposer.global.cancel"/>"/>
	    </div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</form:form>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
</body>
<script type="text/javascript">
function SaveValues(){
	debugger;
	/* if(confirm('<spring:message code="BzComposer.configuration.saveconfirm"/>')){
		document.configurationForm.annualInterestRate.value = wxToFixed(document.configurationForm.annualInterestRate.value,2);
		document.configurationForm.minCharge.value = wxToFixed(document.configurationForm.minCharge.value,2);
		
		document.configurationForm.startInvoiceNo.value = parseInt(document.configurationForm.startInvoiceNo.value);	
		document.configurationForm.startPONum.value = parseInt(document.configurationForm.startPONum.value);
		document.configurationForm.startRINum.value = parseInt(document.configurationForm.startRINum.value);
		document.configurationForm.timeSheet.value = parseInt(document.configurationForm.timeSheet.value);
		
		document.configurationForm.invoiceMemoDays.value = parseInt(document.configurationForm.invoiceMemoDays.value);
		document.configurationForm.overdueInvoiceDays.value = parseInt(document.configurationForm.overdueInvoiceDays.value);
		document.configurationForm.inventoryOrderDays.value = parseInt(document.configurationForm.inventoryOrderDays.value);
		document.configurationForm.billsToPayDays.value = parseInt(document.configurationForm.billsToPayDays.value);
		document.configurationForm.gracePeriod.value = parseInt(document.configurationForm.gracePeriod.value);
		
		performance_value = document.configurationForm.userDefinePerform.value;
		if(document.configurationForm.timeSheet.value <2){
			document.configurationForm.timeSheet.value = 2;
		}
		if(performance_value == "" || parseInt(performance_value) <= 10000 || (!IsNumeric(performance_value))){
			document.configurationForm.userDefinePerform.value = 10001;
			
		}
		document.getElementById('tid').value="SaveConfiguration";
		document.forms[0].action = "Configuration";
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
	            	/* document.configurationForm.annualInterestRate.value = wxToFixed(document.configurationForm.annualInterestRate.value,2);
	        		document.configurationForm.minCharge.value = wxToFixed(document.configurationForm.minCharge.value,2);
	        		
	        		document.configurationForm.startInvoiceNo.value = parseInt(document.configurationForm.startInvoiceNo.value);	
	        		document.configurationForm.startPONum.value = parseInt(document.configurationForm.startPONum.value);
	        		document.configurationForm.startRINum.value = parseInt(document.configurationForm.startRINum.value);
	        		document.configurationForm.timeSheet.value = parseInt(document.configurationForm.timeSheet.value);
	        		
	        		document.configurationForm.invoiceMemoDays.value = parseInt(document.configurationForm.invoiceMemoDays.value);
	        		document.configurationForm.overdueInvoiceDays.value = parseInt(document.configurationForm.overdueInvoiceDays.value);
	        		document.configurationForm.inventoryOrderDays.value = parseInt(document.configurationForm.inventoryOrderDays.value);
	        		document.configurationForm.billsToPayDays.value = parseInt(document.configurationForm.billsToPayDays.value);
	        		document.configurationForm.gracePeriod.value = parseInt(document.configurationForm.gracePeriod.value);
	        		*/
	        		/* performance_value = document.configurationForm.userDefinePerform.value;
	        		if(document.configurationForm.timeSheet.value <2){
	        			
	        			document.configurationForm.timeSheet.value = 2;
	        		}
	        		if(performance_value == "" || parseInt(performance_value) <= 10000 || (!IsNumeric(performance_value))){
	        			document.configurationForm.userDefinePerform.value = 10001;
	        			
	        		}  */
	        		
	        		/* document.getElementById('tid').value="SaveConfiguration";
	        		document.forms[0].action = "Configuration";
	        		document.forms[0].submit();  */
					//$('form').submit();
	        		var multiUserConnection = document.configurationForm.multiUserConnection.value;
	            
					var formData = $('form').serialize();
	        		
	        		$.ajax({
	        			type : "POST",
	        			url :  "ConfigurationAjax/SaveConfiguration?tabid=SaveConfiguration&multiUserConnection="+multiUserConnection,
	        			data : formData,
	        			success : function(data) {
	        				debugger
	        				$("#showsaverecorddialog").dialog("close");
	        				$("#showsuccessdialog").dialog({
	        						resizable: false,
	        				        height: 200,
	        				        width: 500,
	        				        modal: true,
	        				        buttons: {
	        				        	"<spring:message code='BzComposer.global.ok'/>": function () {
	        				        		$(this).dialog("close");
	        				                return false;
	        				        	},
	        				            "<spring:message code='BzComposer.global.cancel'/>": function () {
	        				                $(this).dialog("close");
	        				                return false;
	        				            }
        				        	}
	        				});
	        			},
	        			error : function(data) {
	        				alert("<bean:message key='BzComposer.common.erroroccurred'/>");
	        			}
	        		});
	            },
	            <spring:message code='BzComposer.global.cancel'/>: function () {
	                $(this).dialog("close");
	                return false;
	            }
	        }
	    });
	    return false;
}

function RevokeValues(){
	document.getElementById('tid').value="config";
	document.forms[0].action = "Configuration";
	document.forms[0].submit();
}

function SetLabelName(lblid){
	size = document.getElementById('lblsize').value;
	for(cnt=0;cnt<size;cnt++){
		lid = document.getElementById(cnt+'lid').value;
		if(lblid == lid){
			document.configurationForm.labelName.value =  document.getElementById(cnt+'lname').value;
			break;
		}
	}
}

/* Commented to prevent general  on 24-11-2019
function ChangePassword()
{

}  */

function maxnumberofuserdialog()
{
	event.preventDefault();
	$("#maxnumberofuserdialog").dialog({
    	resizable: false,
        height: 250,
        width: 800,
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
<div id="maxnumberofuserdialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.networksecurity.maxnumberofuser"/></p>
</div>
<div id="showsaverecorddialog" style="display:none;">
	<p><spring:message code="BzComposer.configuration.saveconfirm"/></p>
</div>
<!-- Dialog box used in this page -->
<div id="showsuccessdialog" style="display:none;">
	<p>Record updated</p>
</div>
<div id="showDeleteGroupConfirmDialog" style="display:none;">
	<p><spring:message code="BizComposer.PurchaseOrder.Delete.Validation"/></p>
</div>