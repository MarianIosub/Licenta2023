<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${currentRestaurant.name} | Take a sEAT</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/register.css">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>
    <script src="../resources/js/restaurant.js"></script>
    <script src="../resources/js/reservation.js"></script>


</head>
<body>
<spring:message code="Search.Label" var="searchLabel"/>
<st:header/>
<div class="display-restaurant-page">
    <div class="display-restaurant-left">
        <div class="display-restaurant-presentation">
            <div class="back-to-restaurants">
                <a href="${pageContext.request.contextPath}/restaurant/all"><span>&#8592;</span></a>
            </div>
            <c:choose>
                <c:when test="${currentRestaurant.rating>0}">
                    <div class="restaurant-presentation-title">
                            ${currentRestaurant.name} &#9733;<fmt:formatNumber type="number" maxFractionDigits="2"
                                                                               value="${currentRestaurant.rating}"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="restaurant-presentation-title">
                            ${currentRestaurant.name}
                    </div>
                </c:otherwise>
            </c:choose>

            <div class="restaurant-presentation-description">
                ${currentRestaurant.description}
            </div>
            <div class="restaurant-presentation-infos">
                <img src="data:image/jpeg;base64,${currentRestaurant.image}" alt="restaurant-image"/>
                <div class="restaurant-presentation-details">
                    <div class="">
                        <spring:message code="Restaurant.Where.Label"/>?
                        <strong>
                            <span class="fas fa-location-arrow"></span> ${currentRestaurant.address}, ${currentRestaurant.city}
                        </strong>
                    </div>
                    <div class="">
                        <spring:message code="Restaurant.When.Label"/>?
                        <strong>
                            <span class="fas fa-clock"></span> ${fn:replace(currentRestaurant.openingHour, '.', ':')}0
                            -> ${fn:replace(currentRestaurant.closingHour, '.', ':')}0
                        </strong>
                    </div>
                    <div>
                        <spring:message code="Restaurant.Phone.Number.Label"/>?
                        <strong>
                            <i class="fa fa-phone" aria-hidden="true"></i> ${currentRestaurant.phoneNumber}
                        </strong>
                    </div>
                    <div>
                        <spring:message code="Restaurant.Mail.Address.Label"/>?
                        <strong>
                            <i class="fa fa-envelope" aria-hidden="true"></i> ${currentRestaurant.mail}
                        </strong>
                    </div>
                    <div>
                        <spring:message code="Restaurant.Price.Label"/> <strong>&dollar;</strong>?
                        <strong>
                            ${currentRestaurant.priceRequired} RON
                        </strong>
                    </div>
                </div>
            </div>
        </div>
        <div class="restaurant-menu-items">
            <div class="restaurant-menu-item-header">
                <label id="restaurant-menu-items-search-label" class="label restaurant-menu-items-search-label">
                    <input id="restaurant-menu-items-search" class="input restaurant-menu-items-search-input"
                           placeholder="${searchLabel}.." type="text"
                           onkeyup="searchForRestaurantMenuItems()" onclick="scrollForRestaurantMenuItems()">
                </label>
                <div class="dropdown is-hoverable is-pulled-right is-right">
                    <div class="dropdown-trigger">
                        <button class="button" aria-haspopup="true" aria-controls="dropdown-menu4">
                            <span><spring:message code="Restaurants.Order.Dropdown"/></span>
                            <span class="icon is-small">
                        &#10225;
                    </span>
                        </button>
                    </div>
                    <div class="dropdown-menu" id="dropdown-menu4" role="menu">
                        <div class="dropdown-content is-pulled-right">
                            <div class="dropdown-item">
                                <a onclick="sortMenuItemsBy('ALPHABETICAL')">A <span>&#8594;</span> Z</a>
                            </div>
                            <div class="dropdown-item">
                                <a onclick="sortMenuItemsBy('INVERSE')">Z <span>&#8594;</span> A</a>
                            </div>
                            <div class="dropdown-item">
                                <a onclick="sortMenuItemsBy('POPULAR')"><spring:message
                                        code="Restaurant.Order.Dropdown.Popular"/></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="restaurant-menu-items-list" id="restaurant-menu-items">
                <%@ include file="restaurantMenuItems.jsp" %>
            </div>
            <c:if test="${currentRestaurant.reviews.size()>0}">
                <div class="restaurant-reviews">
                    <h1 class="restaurant-reviews-title">Reviews &darr;</h1>
                    <c:forEach var="review" items="${currentRestaurant.reviews}">
                        <div class="restaurant-review">
                            <div class="review-header">
                                <h1 class="review-title"><strong>${review.user}</strong> was here on
                                    <strong>${review.localDate}</strong></h1>
                                <h1 class="review-rating">
                                    <c:forEach begin="1" end="${review.grade}" step="1">
                                        &#9733;
                                    </c:forEach>
                                </h1>
                            </div>
                            <h1 class="review-comment">
                                <q>${review.comment}</q></h1>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>

    </div>
    <div class="display-restaurant-order" id="reservation-component">
        <%@include file="reservationComponent.jsp" %>
    </div>
</div>
<st:footer/>
</body>
</html>
