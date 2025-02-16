<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Serviceman Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fc;
        }
        .container {
            padding-top: 2rem;
            padding-bottom: 2rem;
        }
        .card {
            border: none;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 24px;
        }
        .card-header {
            background-color: #4e73df;
            color: white;
            padding: 1rem 1.5rem;
            border-bottom: none;
        }
        .card-body {
            background-color: #ffffff;
            padding: 1.5rem;
        }
        .form-label {
            color: #5a5c69;
            font-weight: 500;
            margin-bottom: 0.5rem;
        }
        .form-control {
            border: 1px solid #d1d3e2;
            border-radius: 4px;
            padding: 0.8rem;
            font-size: 0.9rem;
        }
        .form-control:focus {
            border-color: #bac8f3;
            box-shadow: 0 0 0 0.2rem rgba(78, 115, 223, 0.25);
        }
        .btn-primary {
            background-color: #4e73df;
            border-color: #4e73df;
            padding: 0.75rem 1.5rem;
            font-weight: 500;
        }
        .btn-primary:hover {
            background-color: #2e59d9;
            border-color: #2653d4;
        }
        .alert {
            border-radius: 4px;
            margin-bottom: 1.5rem;
        }
        .alert-success {
            background-color: #1cc88a;
            border-color: #18b07c;
            color: white;
        }
        .alert-danger {
            background-color: #e74a3b;
            border-color: #e02d1b;
            color: white;
        }
        .form-check {
            padding: 1rem 0;
        }
        .form-check-input:checked {
            background-color: #4e73df;
            border-color: #4e73df;
        }
        select.form-control {
            padding: 0.8rem;
            height: auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-header">
                        <h4 class="mb-0">Serviceman Registration</h4>
                    </div>
                    <div class="card-body">
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
                            
                            <div class="mb-3">
                                <div class="form-check" style="padding-left: 2rem;">
                                    <input type="checkbox" class="form-check-input" id="thirdParty" name="thirdParty" style="margin-left: -2rem;">
                                    <label class="form-check-label" for="thirdParty">Third Party</label>
                                </div>
                                
                                <div class="form-check" id="taxFormDiv" style="display:none; padding-left: 2rem;">
                                    <input type="checkbox" class="form-check-input" id="taxForm1099" name="taxForm1099" style="margin-left: -2rem;">
                                    <label class="form-check-label" for="taxForm1099">Tax Form 1099</label>
                                </div>
                            </div>
                            
                            <div class="text-end">
                                <button type="submit" class="btn btn-primary">Register</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
