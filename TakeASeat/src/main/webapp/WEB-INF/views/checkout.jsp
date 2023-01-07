<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Checkout | Take a sEAT</title>
    <script src="../resources/js/reservation.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/register.css">
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>
    <base href="/">
</head>
<body>
<c:set var="cart" value="${sessionScope.cart}"/>
<form action='${pageContext.request.contextPath}/cart/charge' method='POST' id='checkout-form' class="checkout-form">
    <input type='hidden' value='${cart.totalPrice}' name='amount'/>
    <div class="checkout-restaurant">
        <img src="data:image/jpeg;base64,${cart.restaurant.image}"/>
        <h1><strong>${cart.restaurant.name}</strong></h1>
    </div>
    <h1 class="checkout-price">Total price: <strong>${cart.totalPrice}</strong> RON</h1>
    <script src='https://checkout.stripe.com/checkout.js'
            class='stripe-button' data-key='${stripePublicKey}'
            data-amount='${cart.totalPrice*100}' data-currency='${currency}'
            data-name='${cart.restaurant.name}' data-description='Pay your reservation'
            data-image='https://img.freepik.com/free-vector/sticker-design-with-hamburger-isolated_1308-62485.jpg?w=1380&t=st=1672852651~exp=1672853251~hmac=23a129f25a6e099af42ed994f89b31ea6aed7517b3c6129ed78d5b07ee738d30'
            data-locale='auto' data-zip-code='false' data-email="${cart.user.mail}">
    </script>
</form>
</body>
</html>