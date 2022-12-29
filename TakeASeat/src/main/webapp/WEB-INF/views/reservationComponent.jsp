<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="reservation-component">
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

                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="reservation-component-footer">
        <h1 class="reservation-total-price">
            Total price: 0 RON
        </h1>
        <a class="button is-info is-rounded" href="" id="">
            Place order
        </a>
    </div>
</div>