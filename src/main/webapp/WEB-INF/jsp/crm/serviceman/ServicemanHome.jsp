<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Serviceman-Dashboard</title>
    <%@ include file="/WEB-INF/jsp/include/header.jsp"%>
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
    </style>
</head>
<body>
<div style="width:100%" class="header-section">
    <div style="display: flex; justify-content: space-between; align-items: center; padding: 10px 30px;">
        <div class="bzclogo">
            <a href="${pageContext.request.contextPath}/Dashboard?tabid=Dashboard">
                <img src="images/bzcweb.jpg" width="289" height="75" alt="bzcomposer"/>
            </a>
        </div>
        
        <div style="text-align: center;">
            <span style="font-size: 1.8em; font-weight: bold; color: #262525; padding: 0 0 .3em 0;">
                Serviceman Dashboard
            </span>
        </div>
        
        <div style="display: flex; align-items: center; gap: 15px;">
            <span style="color: #05A9C5; font-weight: bold;">
                <spring:message code="BzComposer.welcome"/>, ${servicemanData.name}
            </span>
            <span class="horizontal first">
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

    <!-- <div>
        <span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px;border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
            Serviceman Dashboard
        </span>
    </div> -->

    <div class="container">
        <div class="row">
            <!-- Profile Section -->
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h2 class="section-title">
                            <i class="fas fa-user-circle me-2"></i>Profile
                        </h2>
                        <div class="text-center mb-4">
                            <i class="fas fa-user-circle fa-4x text-primary mb-3"></i>
                            <h3>${servicemanData.name}</h3>
                            <p class="text-muted">${servicemanData.email}</p>
                        </div>
                        <button class="btn edit-btn text-white w-100" onclick="editProfile()">
                            <i class="fas fa-edit me-2"></i>Edit Profile
                        </button>
                    </div>
                </div>

                <!-- Statistics Card -->
                <div class="card">
                    <div class="card-body">
                        <h2 class="section-title">Statistics</h2>
                        <div class="row">
                            <div class="col-6 stats-item">
                                <div class="stats-number">${job_history.size()}</div>
                                <div class="stats-label">Total Jobs</div>
                            </div>
                            <div class="col-6 stats-item">
                                <div class="stats-number">
                                    <c:set var="completedJobs" value="0" />
                                    <c:forEach items="${job_history}" var="job">
                                        <c:if test="${job.status == 'Completed'}">
                                            <c:set var="completedJobs" value="${completedJobs + 1}" />
                                        </c:if>
                                    </c:forEach>
                                    ${completedJobs}
                                </div>
                                <div class="stats-label">Completed</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Job History Section -->
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <h2 class="section-title">
                            <i class="fas fa-history me-2"></i>Job History
                        </h2>
                        
                        <div class="job-list">
                            <c:if test="${not empty job_history}">
                                <c:forEach items="${job_history}" var="job">
                                    <div class="job-item">
                                        <div class="row align-items-center">
                                            <div class="col-md-3">
                                                <strong><i class="fas fa-hashtag me-2"></i>Job ID:</strong>
                                                <div>${job.jobId}</div>
                                            </div>
                                            <div class="col-md-3">
                                                <strong><i class="fas fa-tasks me-2"></i>Service:</strong>
                                                <div>${job.serviceType}</div>
                                            </div>
                                            <div class="col-md-3">
                                                <strong><i class="fas fa-map-marker-alt me-2"></i>Location:</strong>
                                                <div>${job.location}</div>
                                            </div>
                                            <div class="col-md-3 text-end">
                                                <span class="status-badge ${job.status == 'Completed' ? 'status-completed' : 
                                                                          job.status == 'In Progress' ? 'status-in-progress' : 'status-pending'}">
                                                    <i class="fas fa-circle me-1"></i>${job.status}
                                                </span>
                                            </div>
                                        </div>
                                        <div class="row mt-2">
                                            <div class="col-12">
                                                <strong><i class="fas fa-info-circle me-2"></i>Description:</strong>
                                                <p class="mb-0">${job.description}</p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                            
                            <c:if test="${empty job_history}">
                                <div class="alert alert-info text-center" role="alert">
                                    <i class="fas fa-info-circle me-2"></i>No job history available
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</div>
</div>
</div>
</div>

<script>
    function logout() {
        window.location = "./Logout";
    }
    
    function editProfile() {
        alert('Edit profile functionality will be implemented soon!');
    }
</script>

<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>