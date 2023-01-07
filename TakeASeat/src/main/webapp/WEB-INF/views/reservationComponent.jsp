<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                            <c:choose>
                                <c:when test="${cart.date ne null}">
                                    <input type="date" class="input"
                                           min="<%=(LocalDate.now().plusDays(1)).format(DateTimeFormatter.ISO_DATE)%>"
                                           onchange="setReservationDate(this.value)" value="${cart.date}"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="date" class="input"
                                           min="<%=(LocalDate.now().plusDays(1)).format(DateTimeFormatter.ISO_DATE)%>"
                                           onchange="setReservationDate(this.value)"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="reservation-start">
                            <c:choose>
                                <c:when test="${(cart.startingHour eq null and cart.restaurant.openingHour < 10.0) || (cart.startingHour ne null and cart.startingHour <10.0)}">
                                    <input type="time" min="${fn:replace(cart.restaurant.openingHour, '.', ':')}0"
                                           max="${fn:replace(cart.endingHour eq null ? cart.restaurant.closingHour - 1.0 : cart.endingHour - 1.0, '.', ':')}0"
                                           step="1800" onkeydown="return false"
                                           value="0${fn:replace(cart.startingHour eq null ? cart.restaurant.openingHour : cart.startingHour, '.', ':')}0"
                                           onchange="setReservationStart(this.value)"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="time" min="${fn:replace(cart.restaurant.openingHour, '.', ':')}0"
                                           max="${fn:replace(cart.endingHour eq null ? cart.restaurant.closingHour - 1.0 : cart.endingHour - 1.0, '.', ':')}0"
                                           step="1800" onkeydown="return false"
                                           value="${fn:replace(cart.startingHour eq null ? cart.restaurant.openingHour : cart.startingHour, '.', ':')}0"
                                           onchange="setReservationStart(this.value)"/>
                                </c:otherwise>
                            </c:choose>

                        </div>
                        <div class="reservation-end">
                            <input type="time"
                                   min="${fn:replace(cart.startingHour eq null ? cart.restaurant.openingHour + 1.0 : cart.startingHour + 1.0, '.', ':')}0"
                                   max="${fn:replace(cart.restaurant.closingHour, '.', ':')}0" step="1800"
                                   onkeydown="return false"
                                   value="${fn:replace(cart.endingHour eq null ? cart.restaurant.closingHour : cart.endingHour, '.', ':')}0"
                                   onchange="setReservationEnd(this.value)"/>
                        </div>
                    </div>
                    <div class="reservation-component-menu-items">
                        <c:forEach var="menuItem" items="${cart.menuItems}">
                            <div class="cart-menu-item">
                                <div class="cart-menu-item-infos">
                                    <img src="data:image/jpeg;base64,${menuItem.key.photoLink}"
                                         class="cart-menu-item-photo"/>
                                    <div>
                                        <div class="cart-menu-item-name">
                                            <h1>${menuItem.key.name}</h1>
                                        </div>
                                        <div class="cart-menu-item-price">
                                            <h1><strong>${menuItem.key.price} RON</strong></h1>
                                        </div>
                                    </div>
                                </div>
                                <div class="cart-menu-item-qty">
                                    <button class="button is-normal is-warning" id="decrease-qty-${menuItem.key.id}"
                                            onclick="decreaseQuantity(this.id)">
                                        -
                                    </button>
                                    <h1> ${menuItem.value}</h1>
                                    <button class="button is-normal is-warning" id="increase-qty-${menuItem.key.id}"
                                            onclick="increaseQuantity(this.id)">
                                        +
                                    </button>
                                </div>

                                <div class="cart-menu-item-delete">
                                    <button class="button is-small is-danger" id="delete-${menuItem.key.id}"
                                            onclick="deleteFromCart(this.id)">
                                        X
                                    </button>
                                </div>
                            </div>

                        </c:forEach>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="reservation-component-footer">
        <h1 class="reservation-total-price">
            Total price: ${cart.totalPrice} RON
        </h1>
        <c:choose>
            <c:when test="${cart.canBePlaced and cart.user ne null}">
                <a class="button is-info is-rounded" id="place-order" onclick="redirectToCheckout()">
                    Place order
                </a>
            </c:when>
            <c:otherwise>
                <a class="button is-info is-rounded" disabled="disabled" id="place-order">
                    Place order
                </a>
            </c:otherwise>
        </c:choose>
    </div>
</div>