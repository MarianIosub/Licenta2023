<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home | Take a sEAT</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/style.css">
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
                <h1><spring:message code="Home.Welcome.Message.Anonymous"/></h1>
                <h3><spring:message code="Home.Subtitle.Message"/></h3>
            </c:when>
            <c:otherwise>
                <h1><spring:message code="Home.Welcome.Message.Authenticated" arguments="${currentUser.name}"/></h1>
                <h3><spring:message code="Home.Subtitle.Message"/></h3>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="home-content">
        <c:choose>
            <c:when test="${isAdmin}">
                <div class="home-manage">
                    <h1><spring:message code="Home.Manage.Restaurant"/></h1>
                    <div class="home-text-content">
                        <div>
                            <p>✓ <spring:message code="Home.Admin.Manage.First.Paragraph"/></p>
                            <p>✓ <spring:message code="Home.Admin.Manage.Second.Paragraph"/></p>
                            <p>✓ <spring:message code="Home.Admin.Manage.Third.Paragraph"/></p>
                            <p>✓ <spring:message code="Home.Admin.Manage.Fourth.Paragraph"/></p>
                        </div>
                        <div>
                            <p>✓ <spring:message code="Home.Admin.Manage.Fifth.Paragraph"/></p>
                            <p>✓ <spring:message code="Home.Admin.Manage.Sixth.Paragraph"/></p>
                            <p>✓ <spring:message code="Home.Admin.Manage.Seventh.Paragraph"/></p>
                            <p>✓ <spring:message code="Home.Admin.Manage.Eighth.Paragraph"/></p>
                        </div>
                    </div>
                    <a class="button is-large is-warning is-centered"
                       href="${pageContext.request.contextPath}/restaurant/manage"><spring:message
                            code="Home.Admin.Manage.Restaurant.Button"/></a>
                </div>
                <div class="home-orders">
                    <h1><spring:message code="Home.Admin.Orders"/></h1>
                    <div class="home-text-content">
                        <div>
                            <p>✓ <spring:message code="Home.Admin.Order.First.Paragraph"/></p>
                            <p>✓ <spring:message code="Home.Admin.Order.Second.Paragraph"/></p>
                            <p>✓ <spring:message code="Home.Admin.Order.Third.Paragraph"/></p>
                            <p>✓ <spring:message code="Home.Admin.Order.Fourth.Paragraph"/></p>
                        </div>
                        <div>
                            <p>✓ <spring:message code="Home.Admin.Order.Fifth.Paragraph"/></p>
                            <p>✓ <spring:message code="Home.Admin.Order.Sixth.Paragraph"/></p>
                            <p>✓ <spring:message code="Home.Admin.Order.Seventh.Paragraph"/></p>
                            <p>✓ <spring:message code="Home.Admin.Order.Eighth.Paragraph"/></p>
                        </div>
                    </div>
                    <a class="button is-large is-warning is-centered"
                       href="${pageContext.request.contextPath}/order/reservations"><spring:message
                            code="Home.Admin.Orders.Button"/></a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="home-ordered">
                    <h1><spring:message code="Home.Client.Most.Orders"/> &darr;</h1>
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
                    <h1><spring:message code="Home.Client.Best.Rated"/> &darr;</h1>
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
                    <h1><spring:message code="Home.Client.Most.Delicious"/> &darr;</h1>
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
