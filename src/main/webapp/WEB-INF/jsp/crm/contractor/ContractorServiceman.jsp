<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.CrmServices.Contractor.servicemanGroup.dao.DTOSGResponse" %>
<%@ page import="com.CrmServices.Contractor.servicemanGroup.dao.UCServicemanEntity" %>

<!DOCTYPE html>
<html>
<head>
    <title>Serviceman Groups and Under Contractor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="container py-4">
    <h2>Serviceman Groups</h2>
    
    <div>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Group ID</th>
                    <th>Group Name</th>
                    <th>Serviceman Names</th>
                    <th>Leader Name</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<DTOSGResponse> servicemanGroups = (List<DTOSGResponse>) request.getAttribute("serviceman_group");
                    if (servicemanGroups != null && !servicemanGroups.isEmpty()) {
                        for (DTOSGResponse group : servicemanGroups) {
                %>
                <tr>    
                    <td><%= group.getGroupId() %></td>
                    <td><%= group.getGroupName() %></td>
                    <td><%= String.join(", ", group.getServicemanName()) %></td>
                    <td><%= group.getLeaderServicemanName() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" class="text-center">No serviceman groups found</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <h2>Under Contractor Servicemen</h2>
        <div>

        <%
            List<UCServicemanEntity> underContractorServicemen = (List<UCServicemanEntity>) request.getAttribute("under_contractor");

            if (underContractorServicemen != null && !underContractorServicemen.isEmpty()) {
                for (UCServicemanEntity serviceman : underContractorServicemen) {
        %>
        <pre><%= serviceman.toString() %></pre>
        <%
                }
            } else {
        %>
        <p class="text-center">No under contractor servicemen found</p>
        <%
            }
        %>
        </div>
    </div>
</body>
</html>
