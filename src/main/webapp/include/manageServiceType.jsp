<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@	page
	import="com.avibha.bizcomposer.configuration.dao.ConfigurationInfo,java.util.ArrayList"%>
<%
		String action = request.getParameter("action");
			if (action != null) {
				// Add new service type
				if(action.equalsIgnoreCase("Add")){  
					ConfigurationInfo configInfo = new ConfigurationInfo();
					String sName = request.getParameter("SName");
					int invId = Integer.parseInt(request.getParameter("InvStyleId"));
					boolean isAdded = configInfo.addServiceType(sName,invId);
					if(isAdded){
						ArrayList list = new ArrayList();
						list = configInfo.serviceTypeList(request);
						int s = list.size();
						request.setAttribute("ServiceType",list);
						out.println(request.getAttribute("ServList")+"!!"+s+"!!");
					}
				}
				// edit existing service type
				else if(action.equalsIgnoreCase("Edit")){ 
					ConfigurationInfo configInfo = new ConfigurationInfo();
					String sName = request.getParameter("SName");
					int invId = Integer.parseInt(request.getParameter("InvStyleId"));
					int serviceId = Integer.parseInt(request.getParameter("ServiceID"));
					boolean isEdited = configInfo.editServiceType(sName,invId,serviceId);
					if(isEdited){
						ArrayList list = new ArrayList();
						list = configInfo.serviceTypeList(request);
						int s = list.size();
						request.setAttribute("ServiceType",list);
						out.println(request.getAttribute("ServList")+"!!"+s+"!!");
					}
				}
				 // delete existing service type
				else if(action.equalsIgnoreCase("Delete")){   
					ConfigurationInfo configInfo = new ConfigurationInfo();
					int serviceId = Integer.parseInt(request.getParameter("ServiceID"));
					boolean isDeleted = configInfo.deleteServiceType(serviceId);
					if(isDeleted){
						ArrayList list = new ArrayList();
						list = configInfo.serviceTypeList(request);
						int s = list.size();
						request.setAttribute("ServiceType",list);
						out.println(request.getAttribute("ServList")+"!!"+s+"!!");
					}
				}
			}

%>

							
								<table class="tabla-listados" cellspacing="0" border="1">
									<thead>
										<tr>
											<th>
												<div align="center">
													<bean:message key="BizComposer.Configuration.ManageServiceType.ServiceName"/>
												</div>		
											</th>
											<th>
												<div align="center">
													<bean:message key="BizComposer.Configuration.ManageServiceType.InvoiceType"/>
												</div>
											</th>
										</tr>
									</thead>
									<tbody>
										<logic:present name="ServiceType">
											<bean:size name="ServiceType" id="sersize"/>
											<input type="hidden" name="ServiceSize" id="ssize" value='<bean:write name="sersize"/>'/>
											<script>
												var servicename = new Array(100);
												var serids = new Array(100);
												count=0;
												ser_size = document.getElementById('ssize').value;
												for(cnt=0;cnt<ser_size;cnt++){
													serids[cnt] = -1;
													servicename[cnt] = "";
												}
												serids[cnt] = -1;
												servicename[cnt] = "";
											</script>
											<logic:iterate name="ServiceType" id="stype" indexId="index">
												<tr id='<bean:write name="index"/>@@'
													onclick="setServiceType('<bean:write name="stype" property="serviceID"/>','<bean:write name="stype" property="serviceName"/>',
														'<bean:write name="stype" property="invStyleID"/>','<bean:write name="index" />@@');"  >
													<td nowrap="nowrap">
														<bean:write name="stype" property="serviceName"/>	
													</td>
													<td nowrap="nowrap">
														<bean:write name="stype" property="invName"/>	
													</td>
												</tr>
												<script>
													
													serids[count]= '<bean:write name="stype" property="serviceID"/>';
													servicename[count] = '<bean:write name="stype" property="serviceName"/>';
													count++;
												</script>
											</logic:iterate>
										</logic:present>
									</tbody>
								</table>
							
	