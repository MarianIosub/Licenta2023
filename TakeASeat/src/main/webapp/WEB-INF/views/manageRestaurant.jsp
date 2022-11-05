<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Take a sEAT - Manage Restaurant</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/register.css">
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>
</head>
<body>
<c:set var="restaurant" value="${currentRestaurant}"/>
<st:header/>
<st:flashMessage/>
<section class="section">
    <div class="container">
        <h1 class="title">
            Hello There!
        </h1>
        <p class="subtitle">
            Welcome to <strong>Take a sEAT!</strong>!
        </p>
        <p>Manage Restaurant Page</p>
        <p>name : ${restaurant.name}</p>
        <p>phoneNumber : ${restaurant.phoneNumber}</p>
        <p>description : ${restaurant.description}</p>
        <p>city : ${restaurant.city}</p>
        <p>address : ${restaurant.address}</p>

    </div>
</section>
<st:footer/>
</body>
</html>
