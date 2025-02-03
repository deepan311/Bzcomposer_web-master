<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="myModal" class="modal">
	<!-- Modal content -->
	<div class="emailPopUpmodal-content">
    	<span class="close" onclick="closeModal()">&times;</span>
    	<!-- <div class="modelbody"> -->
    	<form:form action="SendMail?tabid=send" enctype="MULTIPART/FORM-DATA" method="post" modelAttribute="emailSenderDto">
    		<table> 
    			<tbody>
    				<tr>
    					<td><spring:message code="BzComposer.modal.to"/></td>
    					<td><form:input path="to"/></td>
    				</tr>
    				<tr>
    					<td><spring:message code="BzComposer.modal.cc"/></td>
    					<td><form:input path="cc"/></td>
    				</tr>
    				<tr>
    					<td><spring:message code="BzComposer.modal.bcc"/></td>
    					<td><form:input path="bcc"/></td>
    				</tr>
    				<tr>
    					<td><spring:message code="BzComposer.modal.subject"/></td>
    					<td><form:input path="subject"/></td>
    				</tr>
    				<tr>
    					<td style="vertical-align: -webkit-baseline-middle;">
    						<spring:message code="BzComposer.modal.message"/>
   						</td>
    					<td><form:textarea path="message" style="height: 150px"></form:textarea></td>
    				</tr>
    				<tr>
    					<td><spring:message code="BzComposer.modal.attachment"/></td>
    					<td><input type="file" name="attachFile" value="File" /></td>
    				</tr>    			
    				<tr>
    					<td></td>
    					<td style="padding-top: 15px;"><input type="submit" value="<spring:message code="BzComposer.modal.sendbtn"/>"></td>
    				</tr>
    			</tbody>
    		</table>
    	</form:form>
	</div>
</div>