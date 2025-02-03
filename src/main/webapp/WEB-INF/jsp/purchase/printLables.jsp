<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.printlabeltitle" /></title>
<style type="text/css">
.height250 {height: 315px;}
.fht-tbody{
    height: 405px !important;
    overflow: auto !important;
    overflow-x: auto !important;
}
.fht-table-wrapper .fht-tbody { }
table.tabla-listados {width: 100%;border: 1px solid rgb(207, 207, 207);margin: 0px 0px 0px 0px;}
table.tabla-listados tbody tr.odd td { background: #e1e5e9; }
table.tabla-listados thead tr th,td {
    font-size:12px;
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}
</style>
<script type="text/javascript">
function showselectRecordDialog(){
    debugger;
    event.preventDefault();
    $("#showselectRecordDialog").dialog({
        resizable: false,
        height: 200,
        width: 350,
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
</head>
<body onload="Initialize();">
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<form action="PrintLBL" method="post">
	<div id="cos">
		<div class="statusquo ok">
			<div id="hoja">
				<div id="blanquito">
					<div id="padding">
						<div id="pagebreak"><P style="page-break-before: always" /></div>
						<!-- begin Contents -->
						<div>
                            <span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
                                <spring:message code="BzComposer.vendor.VendorAddressLabels" />
                            </span>
						</div>
						<div>
							<div id="table-negotiations" style="overflow:auto;width:100%">
								<table cellspacing="0" style="width:100%;">
									<tbody>
									<tr>
										<td valign="top">
											<div style="overflow-x:auto;width:1250px;margin-right:20px;">
												<table id="printLabelList" class="tabla-listados" cellpadding="0" cellspacing="0">
													<thead>
													<tr>
													    <th><spring:message code="BzComposer.printlabels.id"/></th>
														<th><spring:message code="BzComposer.printlabel.companyname" /></th>
														<th><spring:message code="BzComposer.customer.Customer"/></th>
														<th style="width:200px;"><spring:message code="BzComposer.global.address1" /></th>
														<th><spring:message code="BzComposer.global.city" /></th>
														<th><spring:message code="BzComposer.global.state" /></th>
														<th><spring:message code="BzComposer.global.zipcode" /></th>
														<th><spring:message code="BzComposer.global.country" /></th>
														<th><spring:message code="BzComposer.global.email" /></th>
														<th><spring:message code="BzComposer.printlabel.dateadded" /></th>
													</tr>
													</thead>
													<tbody>
													<c:if test="${not empty VendorList}">
													    <input type="hidden" name="listSize" id="lSize" value='${VendorList.size()}' />
														<c:forEach items="${VendorList}" var="objList" varStatus="loop">
															<tr id='${loop.index}$$' onclick="setRowId(${objList.clientVendorID}, '${loop.index}$$');"
																ondblclick="setRidOnDblClk(${objList.clientVendorID}, '${loop.index}$$');">
																<td>${objList.clientVendorID}</td>
																<td>${objList.cname}</td>
																<td>${objList.fullName}</td>
																<td>${objList.address1}</td>
																<td>${objList.city}</td>
																<td>${objList.stateName}</td>
																<td>${objList.zipCode}</td>
																<td>${objList.country}</td>
																<td>${objList.email}</td>
																<td>${objList.dateAdded}</td>
															</tr>
															<c:if test="${not empty Services}">
																	<input type="hidden" name="serSize" id="seSize" value='${ssize}' />
																	<c:forEach items="${Services}" var="service">
																		<c:if test = "${service.clientVendorID == service.clientVendorID}">
																			<tr id='${index}*$$' onclick="setRowId(${service.clientVendorID},'${index}*$$');"
																				ondblclick="setRidOnDblClk(${service.clientVendorID},'${index}*$$');">
																				<td colspan="3" nowrap="nowrap">
																					&nbsp;&nbsp;&nbsp;&nbsp;
																					<img src="WEB-INF/jsp/images/arrow.jpg"></img>
																					${service.serviceName}
																				</td>
																				<td>&nbsp;&nbsp;</td>
																				<td>&nbsp;&nbsp;</td>
																				<td>&nbsp;&nbsp;</td>
																				<td>&nbsp;&nbsp;</td>
																				<td>&nbsp;&nbsp;</td>
																				<td>&nbsp;&nbsp;</td>
																				<td>&nbsp;&nbsp;</td>
																				<td>&nbsp;&nbsp;</td>
																				<td>&nbsp;&nbsp;</td>
																			</tr>
																		</c:if>
																	</c:forEach>
															</c:if>
														</c:forEach>
													</c:if>
													</tbody>
												</table>
											</div>
										</td>
										<!-- 				<div align="center"> -->
										<!-- 				<table> -->
										<!-- 					<tr> -->
											<%-- 						<td><logic:present name="PageValue"> --%>
											<%-- 							<logic:notEqual name="PageValue" --%>
											<%-- 								value='<%=(String)session.getAttribute("StartPage") %>'> --%>
										<!-- 									&nbsp;&nbsp;&nbsp; -->
											<%-- 									<a href="#" onclick="getPreviousRecord();"> <strong> <bean:message --%>
										<!-- 									key="BzComposer.Vendor.PrintLabel.Previous" /> </strong> </a> -->
											<%-- 							</logic:notEqual> --%>
											<%-- 						</logic:present> <logic:iterate name="Total" id="tot"> --%>
											<%-- 							<logic:equal name="tot" --%>
											<%-- 								value='<%= (String)request.getAttribute("PageValue") %>'> --%>
											<%-- 									&nbsp;&nbsp;&nbsp;<font color="red"><bean:write name="tot" /></font> --%>
											<%-- 							</logic:equal> --%>
											<%-- 							<logic:notEqual name="tot" --%>
											<%-- 								value='<%= (String)request.getAttribute("PageValue") %>'> --%>
										<!-- 									&nbsp;&nbsp;&nbsp;<a href="#" -->
											<%-- 									onclick="getPrintLabelRecord('<bean:write name="tot"/>');"><bean:write --%>
										<!-- 									name="tot" /></a> -->
											<%-- 							</logic:notEqual> --%>
											<%-- 						</logic:iterate> <logic:present name="PageValue"> --%>
											<%-- 							<logic:notEqual name="PageValue" --%>
											<%-- 								value='<%=(String)request.getAttribute("TotalPages") %>'> --%>
										<!-- 									&nbsp;&nbsp;&nbsp; -->
											<%-- 									<a href="#" onclick="getNextRecord();"> <strong> <bean:message --%>
										<!-- 									key="BzComposer.Vendor.PrintLabel.Next" /> </strong> </a> -->
											<%-- 							</logic:notEqual> --%>
											<%-- 						</logic:present></td> --%>
										<!-- 					</tr> -->
										<!-- 				</table> -->
										<!-- 				</div> -->

										<td valign="top" style="300px;">
											<table class="tabla-listados" cellspacing="0" style="height: 406px;">
												<thead>
                                                    <tr>
                                                        <th>
                                                            <spring:message code="BzComposer.printlabel.selectaddresslabeltype" />
                                                        </th>
                                                    </tr>
												</thead>
												<tbody>
												<tr align="center">
													<td align="left">
														<div id="lbltypeid">
															<select name="lblType" id="ltype" style="width:180px;">
															    <c:if test="${not empty Labels}">
																	<c:forEach items="${Labels}" var="objList1">
																		<option value=${objList1.id}>${objList1.labeltype}</option>
																	</c:forEach>
																</c:if>
															</select>
														</div>
													</td>
												</tr>
												<tr>
													<td align="center" style="padding:10px;">
														<table class="tabla-listados">
															<tr>
																<td style="border-right: 1px solid #dddddd;">
                                                                    <input type="button" class="formbutton" onclick="AddLabel();" value="<spring:message code='BzComposer.global.add' />"/>
                                                                </td>
                                                                <td style="border-right: 1px solid #dddddd;">
                                                                    <input type="button" class="formbutton" onclick="UpdateLabel();" value="<spring:message code='BzComposer.global.update' />"/>
                                                                </td>
                                                                <td>
                                                                    <input type="button" class="formbutton" onclick="Refresh();" value="<spring:message code='BzComposer.printlabels.refresh' />"/>
                                                                </td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
                                                    <td>
                                                        <spring:message code="BzComposer.printlabel.vendorlisttoprint"/>
                                                    </td>
                                                </tr>
												<tr>
													<td align="center">
														<select id="list" name="lbltype" size="32" style="width:300px;font-size:12px;">
														</select>
													</td>
												</tr>
												</tbody>
											</table>
										</td>
									</tr>
									</tbody>
								</table>
							</div>
							<!-- 	<table class="tabla-listados" cellspacing="0"> -->
							<!-- 		<tbody> -->
							<!-- 			<tr> -->
							<!-- 				<td colspan="3" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input -->
							<!-- 					type="button" -->
								<%-- 					value="<spring:message code="BzComposer.Employee.SendTo" />" --%>
							<!-- 					class="formbutton" onclick="sendTo();"></td> -->
							<!-- 				<td align="right"><input type="hidden" name="SelectedRID" -->
							<!-- 					id="setRID" value=""> <input type="button" -->
								<%-- 					value='<spring:message code="BzComposer.Vendor.PrintLabel.PrintLabelButton"/>' --%>
							<!-- 					class="formbutton" disabled="disabled" />&nbsp;&nbsp;&nbsp; <input -->
							<!-- 					type="button" -->
								<%-- 					value="<spring:message code="BzComposer.Vendor.PrintLabel.ClearAllButton" />" --%>
							<!-- 					class="formbutton" onclick="clearData();"></td> -->
							<!-- 			</tr> -->
							<!-- 		</tbody> -->
							<!-- 	</table> -->

							<div>
								<div style="float: left;">
									<input type="button" class="formbutton" onclick="sendTo();" value='<spring:message code="BzComposer.printlabel.sendto" />' />
								</div>
								<div style="float: right;">
									<input type="hidden" name="SelectedRID" id="setRID" value="">
									<input type="button" class="formbutton" onclick="printlabel();" value='<spring:message code="BzComposer.printlabel.printlabelbutton"/>'/>
									&nbsp;&nbsp;&nbsp;
									<input type="button" class="formbutton" onclick="clearData();" value='<spring:message code="BzComposer.printlabel.clearallbutton" />' />
								</div>
							</div>
						</div>
					</div>
					<div id="VendorInfo">
						<c:if test="${not empty VendorList}">
							<c:forEach items="${VendorList}" var="objList">
								<input type="hidden" value='${objList.fullName}' id='${objList.clientVendorID}' name='${objList.fullName}fname' />
							</c:forEach>
						</c:if>
					</div>
					<!-- end Contents -->
					<div>
						<input type="hidden" id="tab" name="tabid" value="PrintLabel" />
						<input type="hidden" id="listvalue" name="VendorName" />
						<input type="hidden" id="startPage" name="startPage" />
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>
<script type="text/javascript">
function printlabel(){
    debugger;
    var list=document.getElementById("list");
    var ops=list.getElementsByTagName('OPTION');
    var pagebreak= document.getElementById("pagebreak").innerHTML;
    var customerdetails;
    /*  for (var i = 0; i < ops.length; i++) {
         var customerdetails = ops[i].textContent;
     } */

    if(customerdetails == ""){
        alert("<bean:message key='BzComposer.common.selectCustomer'/>");
    }else{
        var i;
        var a = window.open('', '', 'height=500, width=500');
        a.document.write('<html>');
        a.document.write('<body>');
        var lengt = ops.length;
        for ( i = 0; i < ops.length; i++) {
            var j =lengt-1;
            var customerdetails = ops[i].textContent;
            if(j != i){
                a.document.write(customerdetails,pagebreak);
            }else{
                a.document.write(customerdetails);
            }
        }
        //a.document.write(customerdetails);
        a.document.write('</body></html>');
        a.document.close();
        a.print();
    }
}

//setRowId(null, "0$$");
function setRowId(rowid, rid){
    var selectedRowClass = document.getElementById(rid).classList;
    if(selectedRowClass == 'draft'){
        document.getElementById(rid).classList.remove('draft');
        deleteSelectedCustomerFromList(rid);
    }else{
        document.getElementById(rid).classList.add('draft');
        addSelectedCustomerIntoList(rid);
    }
}
function addSelectedCustomerIntoList(rid){
    var sel = document.getElementById(rid);
    var cells= sel.getElementsByTagName("td");
    var name = cells[0].innerHTML +" : "+cells[1].innerHTML +",  "+ cells[2].innerHTML +",  "+ cells[7].innerHTML;
    var op = document.createElement('OPTION');
    op.setAttribute('value', cells[0].innerHTML);
    op.appendChild(document.createTextNode(name));
    document.getElementById("list").appendChild(op);
}
function deleteSelectedCustomerFromList(rid){
    var sel = document.getElementById(rid);
    var cells = sel.getElementsByTagName("td");

    var list = document.getElementById("list");
    var ops = list.getElementsByTagName('OPTION');
    for (i = 0; i < ops.length; i++) {
        if(ops[i].value == cells[0].innerHTML){
            list.remove(i);
            break;
        }
    }
}
function sendTo2(){
    debugger;
    rid = document.getElementById("setRID").value
    if(rid==""){
        return showselectRecordDialog();
    }else{
        var sel=document.getElementById(rid);
        var cells= sel.getElementsByTagName("td");
        var name=vendorid+" "+document.getElementById(vendorid).value;
        var op=document.createElement('OPTION');
        var txt=document.createTextNode(name);
        op.setAttribute('value',name);
        op.appendChild(txt);
        document.getElementById("list").appendChild(op);
        var val = document.getElementById('listvalue').value;
        document.getElementById('listvalue').value=val+vendorid+" "+document.getElementById(vendorid).value+"/";
    }
}

function setRidOnDblClk(rowid,rid){
    setRowId(rowid,rid);
    //sendTo();
}
function clearData(){
    document.getElementById('listvalue').value ="";
    var list=document.getElementById("list");
    var ops=list.getElementsByTagName('OPTION');
    list.options.length=0;
}
function Refresh(){
    window.location = "PrintLBL?tabid=PrintLabel";
}
function AddLabel(){
    window.open("PrintLBL?tabid=AddNewLabel",null,"scrollbars=yes,height=475,width=650,status=yes,toolbar=no,menubar=no,location=no" );
}
function UpdateLabel(){
    lbltype=document.getElementById('ltype').value;
    window.open("PrintLBL?tabid=UpdateLabel&lblId="+lbltype+"&FormValue=Vendor",null,"scrollbars=yes,height=475,width=650,status=yes,toolbar=no,menubar=no,location=no");
}
function getPrintLabelRecord(no){
    document.getElementById('tab').value="PrintLabelList";
    document.PrintLabelForm.startPage.value=no;
    document.forms[0].action = "PrintLBL.do";
    document.forms[0].submit();
}
function getPreviousRecord(){
    document.getElementById('tab').value="PrintLabelList";
    document.PrintLabelForm.startPage.value=(parseInt(document.PrintLabelForm.startPage.value) - 1);
    document.forms[0].action = "PrintLBL.do";
    document.forms[0].submit();
}
function getNextRecord(){
    document.getElementById('tab').value="PrintLabelList";
    document.PrintLabelForm.startPage.value=(parseInt(document.PrintLabelForm.startPage.value) + 1);
    document.forms[0].action = "PrintLBL.do";
    document.forms[0].submit();
}

var lst_value = "";
function Initialize(){
    <c:if test="${not empty VendorList}">
    document.getElementById('listvalue').value = '${VendorList}';
    lst_value = '${VendorList}';
    var records=lst_value.split("/");
    for(cnt=0;cnt<records.length-1;cnt++){
        AssignValues(records[cnt]);
    }
    </c:if>
}

function AssignValues(name){
    var op=document.createElement('OPTION');
    var txt=document.createTextNode(name);
    op.setAttribute('value',name);
    op.appendChild(txt);
    document.getElementById("list").appendChild(op);
}
</script>
<!-- dialogBox used in this page.. -->
<div id="showselectRecordDialog" style="display:none;">
	<p><spring:message code="BzComposer.common.selectanyrecord"/></p>
</div>