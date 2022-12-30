<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="static com.takeaseat.constants.StringConstants.CART" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="reservation-component">
    <c:set var="cart" value="${sessionScope.cart}" scope="page"/>
    <sec:authorize access="isAnonymous()" var="isAnonymous"/>
    <div class="reservation-component-title">
        <h1>&darr; Your reservation &darr;</h1>
    </div>
    <div class="reservation-infos">
        <c:choose>
            <c:when test="${isAnonymous}">
                <div class="reservation-component-not-logged">
                    <h1>Please login to make reservations</h1>
                    <a class="button is-primary" href="<c:url value="/login"/>">
                        <spring:message code="Header.Login" htmlEscape="true"/>
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="reservation-component-logged">
                    <div class="reservation-component-time">
                        <div class="reservation-date">
                            <input type="date" class="input"
                                   min="<%=(LocalDate.now().plusDays(1)).format(DateTimeFormatter.ISO_DATE)%>"/>
                        </div>
                        <div class="reservation-start">
                            <input type="number" min="${cart.restaurant.openingHour}" max="${cart.restaurant.closingHour - 1.0}" step="0.5" onkeydown="return false" value="${cart.restaurant.openingHour}"/>
                        </div>
                        <div class="reservation-end">
                            <input type="number" min="${cart.restaurant.openingHour + 1.0}" max="${cart.restaurant.closingHour}" step="0.5" onkeydown="return false" value="${cart.restaurant.closingHour}"/>
                        </div>
                    </div>
                    <div class="reservation-component-menu-items">

                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="reservation-component-footer">
        <h1 class="reservation-total-price">
            Total price: ${cart.totalPrice} RON
        </h1>
        <a class="button is-info is-rounded" href="" id="place-order">
            Place order
        </a>
    </div>
</div>