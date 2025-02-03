<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<title><spring:message code="BzComposer.printlabelstitle" /></title>
<style type="text/css">
.height250 { height: 315px; }
.fht-tbody{
    height: 405px !important;
    overflow: auto !important;
    overflow-x: auto !important;
}
.fht-table-wrapper .fht-tbody { }
table.tabla-listados {
    width: 100%;
    border: 1px solid rgb(207, 207, 207);
    margin: 0px 0px 0px 0px;
    margin: 0px 0px 0px 0px;
    font-family: arial, sans-serif; border-collapse: collapse;
}
table.tabla-listados thead tr th,td {
    font-size:12px;
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}
tr:nth-child(even) { background-color: #dddddd; }
</style>
</head>
<script type="text/javascript">
function showCustomerValidationDialog()
{
	event.preventDefault();
	$("#showCustomerValidationDialog").dialog({
		resizable: false,
	    height: 200,
	    width: 400,
	    modal: true,
	    buttons: {
			"<spring:message code='BzComposer.global.ok'/>": function () {
	        	$(this).dialog("close");
			}
		}
	    });
	    return false;
}
function showlabelValidationDialog()
{
	event.preventDefault();
	$("#showlabelValidationDialog").dialog({
		resizable: false,
	    height: 200,
	    width: 400,
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
<body>
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<form:form action="Sales" method="post" id="printlabels">
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<div id="pagebreak"><P style="page-break-before: always" /></div>
<div>
	<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		<spring:message code="BzComposer.printlabels.printlabeltitle"/>
	</span>
</div>
<div>
	<div id="table-negotiations" style="overflow:auto;width:100%">
		<table cellspacing="0" style="width: 100%;">
			<tbody>
				<tr>
					<td valign="top">
						<!-- <div style="overflow-x:auto;width:925px;"> -->							<!-- commented on 04-10-2019 -->
						<div style="overflow-x:auto;width:1200px;">								
	                 		<table id="printLabelCustomerList" class="tabla-listados" cellpadding="0" cellspacing="0">
					   			<thead style="font-weight: bold;">
									<tr>
										<th><spring:message code="BzComposer.printlabels.id"/></th>
										<th><spring:message code="BzComposer.Invoice.Company"/></th>
										<th><spring:message code="BzComposer.global.prefix"/></th>
                                        <th><spring:message code="BzComposer.global.firstname"/></th>
                                        <th><spring:message code="BzComposer.global.lastname"/></th>
										<th style="width:200px;"><spring:message code="BzComposer.global.address1"/></th>
										<!-- <th style="width:200px;"><spring:message code="BzComposer.global.address2"/></th> -->
										<th><spring:message code="BzComposer.global.city"/></th>
										<th><spring:message code="BzComposer.global.zipcode"/></th>
										<th><spring:message code="BzComposer.global.country"/></th>
										<th><spring:message code="BzComposer.printlabels.dateadded"/></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty CustomerDetails}">
                                        <input type="hidden" name="listSize" id="lSize" value='${CustomerDetails.size()}' />
                                        <c:forEach items="${CustomerDetails}" var="objList" varStatus="loop">
                                            <tr id='${loop.index}$$' onclick="setRowId(${objList.clientVendorID},'${loop.index}$$');"
                                                ondblclick="setRidOnDblClk(${objList.clientVendorID},'${loop.index}$$');">
                                                <td>${objList.clientVendorID}</td>
                                                <td>${objList.companyName}</td>
                                                <td>${objList.title}</td>
                                                <td>${objList.firstName}</td>
                                                <td>${objList.lastName}</td>
                                                <td>${objList.address1}</td>
                                                <!-- <td>${objList.address2}</td> -->
                                                <td>&nbsp;${objList.city}</td>
                                                <td>${objList.zipCode}</td>
                                                <td>${objList.country}</td>
                                                <td>&nbsp;${objList.dateAdded}</td>
                                            </tr>
                                        </c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</td>
					<td valign="top" style="300px;">
						<table class="tabla-listados" cellspacing="0" style="height: 406px;">
							<thead>
								<tr>
									<th>
										<spring:message code="BzComposer.printlabels.selectaddresslabeltype"/>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<select name="lblType" id="ltype">
											<c:if test="${not empty Labels}">
												<c:forEach items="${Labels}" var="Labels">
													<option value="${Labels.id}">${Labels.labeltype}</option>
												</c:forEach>
											</c:if>
										</select>
									</td>
								</tr>
								<tr>
									<td align="center" style="background: #C3C5C7;">
										<strong><spring:message code="BzComposer.printlabels.setuplabel"/></strong>
									</td>
								</tr>
								<tr>
									<td align="center">
										<table class="tabla-listados">
											<tr>
												<td>
													<input type="button" class="formbutton" onclick="AddLabel();" value="<spring:message code='BzComposer.global.add' />"/>
												</td>
												<td>
													<input type="button" class="formbutton" onclick="UpdateLabel();" value="<spring:message code='BzComposer.global.update' />" />
												</td>
												<td>
													<input type="button" class="formbutton" onclick="Refresh();" value="<spring:message code='BzComposer.printlabels.refresh' />"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td align="center">
										<spring:message code="BzComposer.printlabels.customerlisttoprint"/>
									</td>
								</tr>
								<tr>
									<td>
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
	<div>
		<div style="float: left;font-size:14px;">
			<input type="button" value="<spring:message code="BzComposer.printlabels.sendto" />" class="formbutton" onclick="sendTo();">
		</div>
		<div style="float: right;font-size:14px;">
			<input type="hidden" name="SelectedRID" id="setRID" value=""> 
			<input type="button" value="<spring:message code='BzComposer.printlabels.printlabelbtn'/>" onclick="printlabel();" class="formbutton">
			&nbsp;&nbsp;&nbsp; 
			<input type="button" value="<spring:message code="BzComposer.printlabels.cleardata" />" class="formbutton" onclick="clearData();" />
		</div>
	</div>
	</div>
</div>
</div>
</div>
</div>
</div>
</div>
<!-- end Contents -->
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>
<script type="text/javascript">

function printlabel()
{
	debugger;
	//lbltype
	 var list=document.getElementById("list");
	 var ops=list.getElementsByTagName('OPTION');
	 var pagebreak= document.getElementById("pagebreak").innerHTML;
	 var customerdetails;
	/*  for (var i = 0; i < ops.length; i++) {
		 var customerdetails = ops[i].textContent;
		
	 } */
	 
	 if(ops.length == 0){
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
    var name = cells[0].innerHTML +" : "+cells[1].innerHTML +",  "+ cells[3].innerHTML+" "+cells[4].innerHTML +",  "+ cells[8].innerHTML;
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
		return showCustomerValidationDialog();
	}else{
	  	var sel=document.getElementById(rid);
		var cells= sel.getElementsByTagName("td");
		var name=cells[0].innerHTML +" : "+cells[1].innerHTML +"   "+ cells[2].innerHTML +"   "+ cells[3].innerHTML;;
		var op=document.createElement('OPTION');
		var txt=document.createTextNode(name);
		op.setAttribute('value',name);
		op.appendChild(txt);
		document.getElementById("list").appendChild(op);
	}
}
function setRidOnDblClk(rowid,rid)
{
	setRowId(rowid,rid);
	//sendTo();
}
function clearData()
{
	 var list=document.getElementById("list");
	 var ops=list.getElementsByTagName('OPTION');
	 list.options.length=0;
}
function UpdateLabel()
{
	debugger;
	lbltype=document.getElementById('ltype').value;
	debugger;
	if(lbltype==""){

		debugger;
		return showlabelValidationDialog();	
	}else{
		window.open("Customer?tabid=UpdateLabel&lblId="+lbltype,null,"scrollbars=yes,height=475,width=650,status=yes,toolbar=no,menubar=no,location=no" );
	}
}
function AddLabel()
{
	window.open("Customer?tabid=AddNewLabel",null,"scrollbars=yes,height=475,width=650,status=yes,toolbar=no,menubar=no,location=no" );
}
function Refresh()
{
	//document.forms[0].action="Customer?tabid=PrintLabels";
	//document.forms[0].submit();
	//Added by tulsi
	window.location = "Customer?tabid=PrintLabels";
}
</script>
<!-- Dialog box used in sales order page -->
<div id="showCustomerValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.printlabels.selectcustomer"/></p>
</div>
<div id="showlabelValidationDialog" style="display:none;">
	<p><spring:message code="BzComposer.printlabels.selectlabeltoupdate"/></p>
</div>