<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Serviceman Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Serviceman Registration</h2>
        
        <!-- Error/Success Messages -->
        <div id="messageArea">
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
            <c:if test="${not empty success}">
                <div class="alert alert-success">${success}</div>
            </c:if>
        </div>

        <form action="/serviceman/create" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="tel" class="form-control" id="phone" name="phone" required>
            </div>
            
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            
            <div class="mb-3">
                <label for="fieldOfWork" class="form-label">Field of Work</label>
                <input type="text" class="form-control" id="fieldOfWork" name="fieldOfWork" required>
            </div>
            
            <div class="mb-3">
                <label for="address" class="form-label">Address</label>
                <textarea class="form-control" id="address" name="address" rows="3" required></textarea>
            </div>
            
            <div class="mb-3">
                <label for="companyId" class="form-label">Company</label>
                <select class="form-control" id="companyId" name="companyId">
                    <option value="">Select a company</option>
                    <c:forEach items="${companies}" var="company">
                        <option value="${company.company_id}">${company.name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="thirdParty" name="thirdParty">
                <label class="form-check-label" for="thirdParty">Third Party</label>
            </div>
            
            <div class="mb-3 form-check" id="taxFormDiv" style="display:none;">
                <input type="checkbox" class="form-check-input" id="taxForm1099" name="taxForm1099">
                <label class="form-check-label" for="taxForm1099">Tax Form 1099</label>
            </div>
            
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>

    <script>
        document.getElementById('thirdParty').addEventListener('change', function() {
            const companySelect = document.getElementById('companyId');
            const taxFormDiv = document.getElementById('taxFormDiv');
            
            if (this.checked) {
                companySelect.value = '';
                companySelect.disabled = true;
                taxFormDiv.style.display = 'block';
            } else {
                companySelect.disabled = false;
                taxFormDiv.style.display = 'none';
            }
        });
    </script>
</body>
</html>
