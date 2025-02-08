<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Serviceman Home</title>
</head>
<body>
    <h1>Welcome to Serviceman Home Page</h1>
    <p>Name: ${servicemanData.name}</p> 
    <p>Email: ${servicemanData.email}</p>
    <p>The current date and time is: <%= new java.util.Date() %></p>

    <form action="/serviceman/logout" method="post" style="margin-top: 20px;">
        <button type="submit" class="btn btn-danger">Logout</button>
    </form>
</body>
</html>