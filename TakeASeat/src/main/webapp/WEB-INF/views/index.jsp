<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home | Take a sEAT</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/register.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>

</head>
<body>
<st:header/>
<st:flashMessage/>
<sec:authorize var="isAdmin" access="hasRole('ROLE_ADMINISTRATOR')"/>
<div class="">
    <div class="home-header">
        <c:choose>
            <c:when test="${currentUser eq null}">
                <h1>Hi there! Welcome to <strong>Take a sEAT!</strong></h1>
                <h3>Reservations are much easier with us! Reserve, order and pay with some clicks!</h3>
            </c:when>
            <c:otherwise>
                <h1>Hi, <strong>${currentUser.name}</strong>! Welcome back to <strong>Take a sEAT!</strong></h1>
                <h3>Reservations are much easier with us! Reserve, order and pay with some clicks!</h3>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="home-content">
        <c:choose>
            <c:when test="${isAdmin}">
                <div class="home-manage">
                    <h1>Manage restaurant</h1>
                    <div class="home-text-content">
                        <div>
                            <p>✓ Create Restaurant</p>
                            <p>✓ Price for reservation</p>
                            <p>✓ Zero taxes</p>
                            <p>✓ Set program</p>
                        </div>
                        <div>
                            <p>✓ Create Menu Items</p>
                            <p>✓ Change availability</p>
                            <p>✓ Delete from menu</p>
                            <p>✓ Set prices for each</p>
                        </div>
                    </div>
                    <a class="button is-large is-warning is-centered"
                       href="${pageContext.request.contextPath}/restaurant/manage">Manage restaurant</a>
                </div>
                <div class="home-orders">
                    <h1>Manage orders</h1>
                    <div class="home-text-content">
                        <div>
                            <p>✓ Online orders</p>
                            <p>✓ Accept/Deny orders</p>
                            <p>✓ Message for client</p>
                            <p>✓ Instant emails</p>
                        </div>
                        <div>
                            <p>✓ Online pays</p>
                            <p>✓ Users' review</p>
                            <p>✓ No phone calls</p>
                            <p>✓ Easy to use</p>
                        </div>
                    </div>
                    <a class="button is-large is-warning is-centered"
                       href="${pageContext.request.contextPath}/order/reservations">Accept orders</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="home-ordered">
                    <h1>Most orders &darr;</h1>
                    <div class="small-items-list">
                        <c:forEach var="restaurant" items="${orderedRestaurants}">
                            <a class="small-item" href="/restaurant/${restaurant.id}">
                                <div>
                                    <img src="data:image/jpeg;base64,${restaurant.image}" alt="">
                                    <div>
                                        <h2>${restaurant.name}</h2>
                                        <h4><span
                                                class="fas fa-location-arrow"></span> ${restaurant.address} ${restaurant.city}
                                        </h4>
                                    </div>
                                </div>

                                <h6> ${restaurant.noOfReservations}</h6>
                            </a>
                        </c:forEach>
                    </div>
                </div>
                <div class="home-rating">
                    <h1>Best rated &darr;</h1>
                    <div class="big-items-list">
                        <c:forEach var="restaurant" items="${ratedRestaurants}">
                            <a class="big-item" href="/restaurant/${restaurant.id}">
                                <div>
                                    <img src="data:image/jpeg;base64,${restaurant.image}" alt="">
                                    <div>
                                        <h2>${restaurant.name}</h2>
                                        <h4><span
                                                class="fas fa-location-arrow"></span> ${restaurant.address} ${restaurant.city}
                                        </h4>
                                    </div>
                                </div>
                                <h6>&#9733;<fmt:formatNumber type="number" maxFractionDigits="1"
                                                             value="${restaurant.rating}"/></h6>
                            </a>
                        </c:forEach>
                    </div>
                </div>
                <div class="home-items">
                    <h1>Most delicious &darr;</h1>
                    <div class="small-items-list">
                        <c:forEach var="menuItem" items="${menuItems}">
                            <a class="small-item" href="/restaurant/${menuItem.value.id}">
                                <div>
                                    <img src="data:image/jpeg;base64,${menuItem.key.photoLink}" alt=""/>
                                    <div>
                                        <h2> ${menuItem.key.name}</h2>
                                        <h4> ${menuItem.key.price} RON</h4>
                                    </div>
                                </div>

                                <h6> ${menuItem.key.noOfOrders}</h6>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<st:footer/>
</body>
</html>
