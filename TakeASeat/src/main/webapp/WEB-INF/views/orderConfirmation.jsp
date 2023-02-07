<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Order no. ${order.id} | Take a sEAT</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../../resources/css/style.css">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>
    <script src="../../resources/js/restaurant.js"></script>
    <script src="../../resources/js/reservation.js"></script>
    <base href="/">
</head>
<body>
<st:header/>
<div class="order-confirmation-component">
    <div class="back-to-orders">
        <a href="${pageContext.request.contextPath}/order/orders"><span>&#8592;</span></a>
    </div>
    <h1 class="order-confirmation-title">Hi ${currentUser.name} ${currentUser.surname}, </h1>
    <h1 class="order-confirmation-subtitle">Order <strong>#${order.id}</strong> at
        <strong>${order.restaurant.name}</strong> has been placed!</h1>
    <div class="order-details">
        <div class="restaurant-details">
            <img src="data:image/jpeg;base64,${order.restaurant.image}" alt="Restaurant image"/>
            <h1>${order.restaurant.name}</h1>
            <h2>On: <strong>${order.date}</strong></h2>
            <h2>At: <strong>${fn:replace(order.startingHour, '.', ':')}0
                <span>&#8594;</span> ${fn:replace(order.endingHour, '.', ':')}0</strong></h2>
        </div>
        <div class="transaction-details">
            <h1>Transaction details</h1>
            <div>
                <p><span>&#8594;</span> Price for reservation: <strong>${order.restaurant.priceRequired} RON</strong>
                </p>
                <p><span>&#8594;</span> Price for food: <strong>${order.totalPrice - order.restaurant.priceRequired}
                    RON</strong></p>
                <p><span>&#8594;</span> Order items: <strong>${order.orderEntries.size()}</strong></p>
                <p><span>&#8594;</span> Transaction status: <strong>${order.transactionStatus}</strong></p>
                <p><span>&#8594;</span> Bill date: <strong>${fn:replace(order.creationDate, 'T', ' at ')}</strong></p>
                <p><span>&#8594;</span> Card type: <strong>${order.cardPaymentNetwork}</strong></p>
                <p><span>&#8594;</span> Card number: <strong>**** ${order.cardLastDigits}</strong></p>
                <p><span>&#8594;</span> Card exp. date:
                    <strong>${order.cardExpirationMonth}/${order.cardExpirationYear}</strong></p>
            </div>
        </div>
    </div>
</div>
<st:footer/>
</body>
</html>
