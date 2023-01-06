<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Pay Confirmation | Take a sEAT</title>
    <script src="../resources/js/reservation.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/register.css">
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>
</head>
<body>
<div class="checkout-confirmation">
    <div class="checkout-confirmation-restaurant">
        <img src="data:image/jpeg;base64,${order.restaurant.image}"/>
        <h1><strong>${order.restaurant.name}</strong></h1>
    </div>
    <h1 class="checkout-confirmation-price">Total price: <strong>${order.totalPrice}</strong> RON</h1>
    <h1 class="checkout-confirmation-message">Transaction was successfully!</h1>
</div>
<script>
    setTimeout(function () {
        window.opener.location.href = '/restaurant/all';
        self.close();
    }, 2000);
</script>
</body>
</html>