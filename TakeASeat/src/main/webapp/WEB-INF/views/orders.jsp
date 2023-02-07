<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Orders | Take a sEAT</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/style.css">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>
    <script src="../resources/js/restaurant.js"></script>
    <script src="../resources/js/reservation.js"></script>
    <script src="../resources/js/orders.js"></script>


</head>
<body>
<st:header/>
<div class="reservations-header">
    <h1 class="reservations-restaurant-name">${currentUser.name} ${currentUser.surname}</h1>
    <h2 class="reservations-title"><spring:message code="Reservations.Title" htmlEscape="true"/></h2>
    <div class="reservations-filter-option">
        <button class="button is-primary order-status-btn" id="status-approved"
                onclick="showOrdersByStatus('Approved', this.id)"><spring:message
                code="Reservations.Approved.Filter" htmlEscape="true"/>
        </button>
        <button class="button is-primary order-status-btn" id="status-unapproved"
                onclick="showOrdersByStatus('Unapproved', this.id)"><spring:message
                code="Reservations.Unapproved.Filter" htmlEscape="true"/>
        </button>
        <button class="button is-primary order-status-btn" id="status-waiting" disabled
                onclick="showOrdersByStatus('Waiting', this.id)">
            <spring:message code="Reservations.Waiting.For.Approval.Filter" htmlEscape="true"/>
        </button>
        <button class="button is-primary order-status-btn" id="status-past"
                onclick="showOrdersByStatus('Past', this.id)"><spring:message code="Reservations.From.past.Filter"
                                                                              htmlEscape="true"/>
        </button>
        <button class="button is-primary order-status-btn" id="status-future"
                onclick="showOrdersByStatus('Future', this.id)"><spring:message code="Reservations.In.Future.Filter"
                                                                                htmlEscape="true"/>
        </button>
    </div>
</div>
<div class="container reservations-list" id="reservationsList">
    <%@include file="ordersList.jsp" %>
</div>
<st:footer/>
</body>
</html>
