<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.CrmServices.Contractor.servicemanGroup.dao.DTOSGResponse" %>
<%@ page import="com.CrmServices.Contractor.UnderServiceman.UServicemanEntity" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<!DOCTYPE html>
<html>
<head>
    <title>Serviceman Groups and Under Contractor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <!-- Add Bootstrap JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    <style>
        .edit-btn {
            background-color: #05A9C5;
            border: none;
            padding: 0.75rem 1.5rem;
            font-weight: 500;
            transition: all 0.3s;
            color: white;
            cursor: pointer;
        }

        .edit-btn:hover {
            background-color: #048299;
            transform: translateY(-2px);
        }

        .tabla-listados {
            width: 100%;
            border-collapse: collapse;
        }

        /* Table header styles */
        .tabla-listados th {
            font-size: 12px;
            padding: 5px;
        }
        
        .tabla-listados tr[style*="background-color: #7f8182"] th {
            background-color: #98999a;
            color: rgb(36, 35, 35);
        }

        .tabla-listados td {
            padding: 5px;
            border: 1px solid #ccc;
            font-size: 12px;
        }

        .section-title {
            font-size: 1.2em;
            color: #fff;
            background-color: #05A9C5;
            padding: 5px;
            margin-bottom: 10px;
        }

        .card {
            border: 1px solid #ccc;
            margin-bottom: 10px;
        }

        .btn-primary {
            background-color: #05A9C5;
            border: none;
        }

        .btn-primary:hover {
            background-color: #048299;
        }

        /* Modal Styles */
        .modal-content {
            border-radius: 0;
            border: none;
        }

        .modal-header {
            background-color: #05A9C5;
            color: white;
            border-radius: 0;
            padding: 15px;
        }

        .modal-title {
            color: white;
            font-weight: 500;
        }

        .modal-body {
            padding: 20px;
        }

        .form-label {
            color: #333;
            font-weight: 500;
        }

        .form-control {
            border-radius: 0;
            border: 1px solid #ccc;
        }

        .form-control:focus {
            border-color: #05A9C5;
            box-shadow: none;
        }

        .modal-footer {
            border-top: none;
            padding: 15px;
        }

        .btn {
            border-radius: 0;
            padding: 8px 20px;
        }

        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
        }

        /* Container styles */
        .table-container {
            height: 300px;
            overflow-y: auto;
            border: 1px solid #ccc;
            margin-bottom: 20px;
        }

        /* Button container style */
        .button-container {
            margin-bottom: 20px;
        }

        /* Header section styles */
        .header-section {
            background-color: #f8f9fa;
            border-bottom: 1px solid #dee2e6;
            margin-bottom: 20px;
        }

        /* Logout button styles */
        .formbutton {
            background-color: #05A9C5;
            color: white;
            border: none;
            padding: 5px 15px;
            cursor: pointer;
        }

        .formbutton:hover {
            background-color: #048299;
        }

        .horizontal.first {
            background-color: transparent !important;
        }

        #cos {
            width: 100%;
            margin: 0 auto;
            padding: 0;
        }
        
        #hoja {
            width: 100%;
            margin: 0 auto;
            background-color: #fff;
        }
        
        #blanquito {
            width: 100%;
            margin: 0 auto;
            padding: 20px;
        }
        
        #padding {
            max-width: 1400px;
            margin: 0 auto;
            padding: 0 30px;
        }
        
        #ddcolortabsline {
            height: 2px;
            background-color: #05A9C5;
            margin-bottom: 20px;
        }
        
        .statusquo {
            width: 100%;
            margin: 0 auto;
            background-color: #f8f9fa;
        }
        
        .ok {
            min-height: calc(100vh - 180px);
        }

        /* Search box styles */
        .search-container {
            padding: 10px;
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
        }
        
        #servicemanSearch {
            padding: 8px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            font-size: 14px;
        }
        
        #servicemanSearch:focus {
            border-color: #05A9C5;
            box-shadow: 0 0 0 0.2rem rgba(5, 169, 197, 0.25);
            outline: none;
        }

        .custom-dropdown {
            position: relative;
        }

        .dropdown-content {
            display: none; /* Hide by default */
            position: absolute;
            background-color: white;
            border: 1px solid #ccc;
            z-index: 1;
            width: 100%;
            max-height: 200px; /* Limit height */
            overflow-y: auto; /* Scroll if too many items */
        }

        .custom-dropdown:hover .dropdown-content {
            display: block; /* Show on hover */
        }

        .dropdown-content .d-flex {
            margin-bottom: 10px; /* Add gap between rows */
        }
    </style>

</head>
<body>
    <div style="width:100%" class="header-section">
        <div style="display: flex; justify-content: space-between; align-items: center; padding: 10px 30px; max-width: 1400px; margin: 0 auto;">
            <div class="bzclogo">
                <a href="${pageContext.request.contextPath}/Dashboard?tabid=Dashboard">
                    <img src="${pageContext.request.contextPath}/images/bzcweb.jpg" width="289" height="75" alt="bzcomposer"/>
                </a>
            </div>
            
            <div style="text-align: center; flex-grow: 1; margin: 0 20px;">
                <span style="font-size: 1.5em; font-weight: bold; color: #262525; padding: 0 0 .3em 0;">
                    Contractor Serviceman Management
                </span>
            </div>
            
            <div style="display: flex; align-items: center; white-space: nowrap;">
                <span style="color: #05A9C5; font-weight: bold; display: flex; align-items: center;">
                    <spring:message code="BzComposer.welcome"/>, <%=  (String)session.getAttribute("username") %>
                    <span style="margin: 0 10px; border-left: 1px solid #999; height: 15px;"></span>
                    <spring:message code="BzComposer.common.companyname"/> <%=  (String)session.getAttribute("user") %>
                </span>
                <span class="horizontal first" style="margin-left: 15px;">
                    <html:button styleId="btnLogout" property="Logout" title="logout" styleClass="formbutton" onclick="logout();">
                        <spring:message code="BzComposer.Logout" />
                    </html:button>
                </span>
            </div>
        </div>
    </div>
    
    <div id="ddcolortabsline">&nbsp;</div>
    <div id="cos">
        <div class="statusquo ok">
            <div id="hoja">
                <div id="blanquito">
                    <div id="padding">
                        <!-- Main content starts here -->
                        <div class="button-container">
                            <button class="edit-btn" data-bs-toggle="modal" data-bs-target="#createGroupModal">
                                Create New Group
                            </button>
                        </div>

                        <div>
                            <div class="section-title">Serviceman Groups</div>
                            <div class="search-container" style="margin-bottom: 10px;">
                                <input type="text" 
                                    id="groupSearch" 
                                    class="form-control" 
                                    placeholder="Search group by name..."
                                    style="width: 300px; display: inline-block; margin-right: 10px;">
                            </div>
                            <div class="table-container">
                                <table class="tabla-listados">
                                    <thead>
                                        <tr style="background-color: #98999a; color: rgb(36, 35, 35);">
                                            <th>Group ID</th>
                                            <th>Group Name</th>
                                            <th>Serviceman Names</th>
                                            <th>Leader Name</th>
                                            <th>Actions</th>
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
                                            <td>
                                                <button class="edit-btn" onclick="editGroup('<%= group.getGroupId() %>')">Edit</button>
                                                <button class="edit-btn" onclick="deleteGroup('<%= group.getGroupId() %>')">Delete</button>
                                            </td>
                                        </tr>
                                        <% 
                                            }
                                        } else {
                                        %>
                                        <tr>
                                            <td colspan="5" style="text-align: center;">No serviceman groups found</td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div style="margin-top: 20px;">
                            <div class="section-title">Under Contractor Servicemen</div>
                            <div class="search-container" style="margin-bottom: 10px;">
                                <input type="text" 
                                    id="servicemanSearch" 
                                    class="form-control" 
                                    placeholder="Search serviceman by name..."
                                    style="width: 300px; display: inline-block; margin-right: 10px;">
                            </div>
                            <div class="table-container">
                                <table class="tabla-listados">
                                    <thead>
                                        <tr style="background-color: #7f8182; color: rgb(36, 35, 35);">
                                            <th>ID</th>
                                            <th>Name</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                        List<UServicemanEntity> underContractorServicemen = (List<UServicemanEntity>) request.getAttribute("under_contractor");
                                        if (underContractorServicemen != null && !underContractorServicemen.isEmpty()) {
                                            for (UServicemanEntity serviceman : underContractorServicemen) {
                                        %>
                                        <tr>
                                            <td><%= serviceman.getUid() %></td>
                                            <td><a href="#" onclick="showServicemanDetails(<%= serviceman.getServicemanId() %>)"><%= serviceman.getServicemanEntity().getName() %></a></td>
                                        </tr>
                                        <% 
                                            }
                                        } else {
                                        %>
                                        <tr>
                                            <td colspan="2" style="text-align: center;">No under contractor servicemen found</td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!-- Create Group Modal -->
                        <div class="modal fade" id="createGroupModal" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Create New Serviceman Group</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form id="createGroupForm">
                                            <div class="mb-3">
                                                <label class="form-label">Group Name</label>
                                                <input type="text" class="form-control" name="groupName" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Lead Serviceman</label>
                                                <div class="custom-dropdown">
                                                    <select class="form-control" name="leadServicemanId" required>
                                                        <option value="" disabled selected>Select Lead Serviceman</option>
                                                        <% for (UServicemanEntity serviceman : underContractorServicemen) { %>
                                                            <option value="<%= serviceman.getServicemanId() %>">
                                                                <%= serviceman.getServicemanEntity().getName() %>
                                                            </option>
                                                        <% } %>
                                                    </select>
                                                    <div class="dropdown-content">
                                                        <% for (UServicemanEntity serviceman : underContractorServicemen) { %>
                                                            <div class="d-flex align-items-center mb-2">
                                                                <span><%= serviceman.getServicemanEntity().getName() %></span>
                                                                <button type="button" class="btn btn-secondary btn-sm ms-2">Assign</button>
                                                            </div>
                                                        <% } %>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Select Servicemen</label>
                                                <div class="custom-dropdown">
                                                    <select class="form-control" name="servicemanIds" multiple required>
                                                        <% for (UServicemanEntity serviceman : underContractorServicemen) { %>
                                                            <option value="<%= serviceman.getServicemanId() %>">
                                                                <%= serviceman.getServicemanEntity().getName() %>
                                                            </option>
                                                        <% } %>
                                                    </select>
                                                    <div class="dropdown-content">
                                                        <% for (UServicemanEntity serviceman : underContractorServicemen) { %>
                                                            <div class="d-flex align-items-center mb-2">
                                                                <span><%= serviceman.getServicemanEntity().getName() %></span>
                                                                <button type="button" class="btn btn-secondary btn-sm ms-2">Assign</button>
                                                            </div>
                                                        <% } %>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary" onclick="createGroup()">Create Group</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Edit Group Modal -->
                        <div class="modal fade" id="editGroupModal" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Edit Serviceman Group</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form id="editGroupForm">
                                            <input type="hidden" name="groupId">
                                            <div class="mb-3">
                                                <label class="form-label">Group Name</label>
                                                <input type="text" class="form-control" name="groupName" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Lead Serviceman</label>
                                                <select class="form-control" name="leadServicemanId" required>
                                                    <% for (UServicemanEntity serviceman : underContractorServicemen) { %>
                                                        <option value="<%= serviceman.getServicemanId() %>">
                                                            <%= serviceman.getServicemanEntity().getName() %>
                                                        </option>
                                                    <% } %>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Select Servicemen</label>
                                                <select class="form-control" name="servicemanIds" multiple required>
                                                    <% for (UServicemanEntity serviceman : underContractorServicemen) { %>
                                                        <option value="<%= serviceman.getServicemanId() %>">
                                                            <%= serviceman.getServicemanEntity().getName() %>
                                                        </option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary" onclick="updateGroup()">Save Changes</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Serviceman Details Modal -->
                        <div class="modal fade" id="servicemanDetailsModal" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Serviceman Details</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                    </div>
                                    <div class="modal-body" id="servicemanDetailsContent">
                                        <!-- Details will be populated here -->
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Main content ends here -->
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Search functionality
        $(document).ready(function() {
            // Search for serviceman groups
            $("#groupSearch").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $(".tabla-listados:eq(0) tbody tr").each(function() {
                    var groupName = $(this).find("td:eq(1)").text().toLowerCase();
                    $(this).toggle(groupName.indexOf(value) > -1);
                });
            });

            // Search for servicemen using the API endpoint
            let searchTimeout;
            $("#servicemanSearch").on("keyup", function() {
                clearTimeout(searchTimeout);
                const searchValue = $(this).val();
                
                // Only search if there's a value
                if (searchValue.trim().length > 0) {
                    searchTimeout = setTimeout(function() {
                        searchServiceman(searchValue);
                    }, 500); // Debounce for 500ms
                } else {
                    // If search is empty, show all original rows
                    $(".tabla-listados:eq(1) tbody tr").show();
                }
            });
        });

        function searchServiceman(keyword) {
            $.ajax({
                url: '/serviceman/search',
                method: 'GET',
                data: {
                    keyword: keyword,
                    columnName: 'name', // Default search by name
                    page: 1,
                    limit: 10
                },
                success: function(response) {
                    updateServicemanTable(response);
                },
                error: function(xhr) {
                    if (xhr.status === 401) {
                        alert('Session expired. Please login again.');
                        window.location.href = '/serviceman/auth';
                    } else {
                        console.error('Search failed:', xhr.responseJSON?.error || 'Unknown error');
                    }
                }
            });
        }

        function updateServicemanTable(servicemen) {
            var tbody = $(".tabla-listados:eq(1) tbody");
            tbody.empty();

            if (servicemen && servicemen.length > 0) {
                for (var i = 0; i < servicemen.length; i++) {
                    var serviceman = servicemen[i];
                    var name = '';
                    var id = '';

                    // Safely get the name
                    if (serviceman.name) {
                        name = serviceman.name;
                    } else if (serviceman.servicemanEntity && serviceman.servicemanEntity.name) {
                        name = serviceman.servicemanEntity.name;
                    }

                    // Safely get the ID
                    if (serviceman.uid) {
                        id = serviceman.uid;
                    } else if (serviceman.id) {
                        id = serviceman.id;
                    }

                    var row = '<tr>' +
                        '<td>' + escapeHtml(id.toString()) + '</td>' +
                        '<td>' + escapeHtml(name) + '</td>' +
                        '</tr>';
                    
                    tbody.append(row);
                }
            } else {
                tbody.append('<tr><td colspan="2" style="text-align: center;">No servicemen found</td></tr>');
            }
        }

        // Add a helper function to escape HTML and prevent XSS
        function escapeHtml(str) {
            if (!str) return '';
            return str.toString()
                .replace(/&/g, '&amp;')
                .replace(/</g, '&lt;')
                .replace(/>/g, '&gt;')
                .replace(/"/g, '&quot;')
                .replace(/'/g, '&#039;');
        }

        // Create Group
        function createGroup() {
            const formData = {
                groupName: $('#createGroupForm [name="groupName"]').val(),
                leadServicemanId: parseInt($('#createGroupForm [name="leadServicemanId"]').val()),
                servicemanIds: $('#createGroupForm [name="servicemanIds"]').val().map(Number)
            };

            $.ajax({
                url: '/contractor/serviceman',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function(response) {
                    alert('Group created successfully');
                    location.reload();
                },
                error: function(xhr) {
                    alert('Error: ' + (xhr.responseJSON?.error || 'Unknown error occurred'));
                }
            });
        }

        // Edit Group
        function editGroup(groupId) {
            // Find the group data from the table
            const row = $(`tr:has(button[onclick="editGroup(${groupId})"])`);
            const groupName = row.find('td:eq(1)').text();
            const leaderName = row.find('td:eq(3)').text();
            
            // Find the leader's ID from the select options
            const leaderOption = $('#editGroupForm [name="leadServicemanId"] option').filter(function() {
                return $(this).text().trim() === leaderName.trim();
            });
            const leaderId = leaderOption.val();
            
            // Get serviceman names from the table
            const servicemanNames = row.find('td:eq(2)').text().split(', ');
            
            // Find corresponding serviceman IDs
            const servicemanIds = [];
            servicemanNames.forEach(name => {
                const option = $('#editGroupForm [name="servicemanIds"] option').filter(function() {
                    return $(this).text().trim() === name.trim();
                });
                if (option.val()) {
                    servicemanIds.push(option.val());
                }
            });

            // Populate the edit form
            $('#editGroupForm [name="groupId"]').val(groupId);
            $('#editGroupForm [name="groupName"]').val(groupName);
            $('#editGroupForm [name="leadServicemanId"]').val(leaderId);
            $('#editGroupForm [name="servicemanIds"]').val(servicemanIds);
            
            // Show the modal
            $('#editGroupModal').modal('show');
        }

        function updateGroup() {
            const formData = {
                groupId: parseInt($('#editGroupForm [name="groupId"]').val()),
                groupName: $('#editGroupForm [name="groupName"]').val(),
                leadServicemanId: parseInt($('#editGroupForm [name="leadServicemanId"]').val()),
                servicemanId: $('#editGroupForm [name="servicemanIds"]').val().map(Number)  // Note: using servicemanId to match DTO
            };

            $.ajax({
                url: '/contractor/serviceman',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function(response) {
                    $('#editGroupModal').modal('hide');
                    alert('Group updated successfully');
                    location.reload();
                },
                error: function(xhr) {
                    const errorMsg = xhr.responseJSON?.error || 'Unknown error occurred';
                    alert('Error updating group: ' + errorMsg);
                }
            });
        }

        // Delete Group
        function deleteGroup(groupId) {
            if (confirm('Are you sure you want to delete this group?')) {
                $.ajax({
                    url: '/contractor/serviceman',
                    type: 'DELETE',
                    contentType: 'application/json',
                    data: JSON.stringify({ groupId: groupId }),
                    success: function(response) {
                        alert('Group deleted successfully');
                        location.reload();
                    },
                    error: function(xhr) {
                        alert('Error: ' + (xhr.responseJSON?.error || 'Unknown error occurred'));
                    }
                });
            }
        }

        function showServicemanDetails(servicemanId) {
            console.log("Fetching details for serviceman ID:", servicemanId); // Debug log
            $.ajax({
                url: '/serviceman/details',
                method: 'GET',
                data: { servicemanId: servicemanId },
                success: function(response) {
                    // Populate the modal with serviceman details
                    let detailsHtml = `
                        <p><strong>ID:</strong> ${response.id}</p>
                        <p><strong>Name:</strong> ${response.name}</p>
                        <p><strong>Email:</strong> ${response.email}</p>
                        <p><strong>Phone:</strong> ${response.phone}</p>
                        <p><strong>Address:</strong> ${response.address}</p>
                    `;
                    $('#servicemanDetailsContent').html(detailsHtml);
                    $('#servicemanDetailsModal').modal('show');
                },
                error: function(xhr) {
                    console.error('Error fetching serviceman details:', xhr); // Log the error response
                    alert('Error fetching serviceman details: ' + (xhr.responseJSON?.error || 'Unknown error occurred'));
                }
            });
        }
    </script>
    <%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>