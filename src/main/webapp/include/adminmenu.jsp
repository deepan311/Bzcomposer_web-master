<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<div style="width: 100%" class="header-section">
   <div class="bzclogo">
      <ul class="sf-menu" id="example" style="text-align: center; width: max-content;">
      	<li>
      		<a href="<bean:write name="path" property="pathvalue"/>/Dashboard.do?tabid=AdminDashboard" style="cursor: pointer;">
      			<bean:message key="BzComposer.admin.comapanylists" />
      		</a>
      	</li>
      	
      	<li>
      		<a href="<bean:write name="path" property="pathvalue"/>/Dashboard.do?tabid=ComapanyContacts" style="cursor: pointer;">
      			<bean:message key="BzComposer.admin.ComapanyContacts" />
      		</a>
      	</li>
      	
      	<li>
      		<a href="<bean:write name="path" property="pathvalue"/>/Dashboard.do?tabid=ApplicationUser" style="cursor: pointer;">
      			<bean:message key="BzComposer.admin.ApplicationUser" />
      		</a>
      	</li>
      	
      	<li>
      		<a href="<bean:write name="path" property="pathvalue"/>/Dashboard.do?tabid=Income" style="cursor: pointer;">
      			<bean:message key="BzComposer.admin.Income" />
      		</a>
      	</li>
      	
      	<li>
      		<a href="<bean:write name="path" property="pathvalue"/>/Dashboard.do?tabid=Visitor" style="cursor: pointer;">
      			<bean:message key="BzComposer.admin.Visitor" />
      		</a>
      	</li>
      </ul>
   </div>
</div>
<script type="text/javascript">
  
</script>