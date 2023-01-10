<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <c:forEach var="restaurant" items="${restaurants}">
        <div class="restaurant-list-display" id="restaurant-${restaurant.id}" onmouseenter="showRedirectIcon(this.id)"
             onmouseleave="hideRedirectIcon(this.id)">
            <div class="restaurant-list-image">
                <img src="data:image/jpeg;base64,${restaurant.image}" alt="">
            </div>
            <div class="restaurant-list-infos">
                <c:choose>
                    <c:when test="${restaurant.rating>0}">
                        <div class="restaurant-list-name">
                                ${restaurant.name} &#9733;<fmt:formatNumber type="number" maxFractionDigits="2"
                                                                            value="${restaurant.rating}"/>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="restaurant-list-name">
                                ${restaurant.name}
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="restaurant-list-address">
                    <span class="fas fa-location-arrow"></span> ${restaurant.address}, ${restaurant.city}
                </div>
                <div class="restaurant-list-infos_first">
                    <div class="restaurant-list-program">
                        <span class="fas fa-clock"></span> ${fn:replace(restaurant.openingHour, '.', ':')}0
                        -> ${fn:replace(restaurant.closingHour, '.', ':')}0
                    </div>
                    <div class="restaurant-list-contact">
                        <div>
                            <i class="fa fa-phone" aria-hidden="true"></i> ${restaurant.phoneNumber}
                        </div>
                        <div>
                            <i class="fa fa-envelope" aria-hidden="true"></i> ${restaurant.mail}
                        </div>
                    </div>
                </div>
                <div class="restaurant-list-price">
                    <spring:message code="Restaurants.Price" htmlEscape="true"/> : ${restaurant.priceRequired}
                    <spring:message code="Restaurants.Price.Currency" htmlEscape="true"/>
                </div>
            </div>
            <a class="restaurant-list-redirect is-hidden" href="/restaurant/${restaurant.id}">
                <div>&rarr;</div>
            </a>
        </div>
    </c:forEach>
</div>