<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container orders">
    <c:choose>
        <c:when test="${orders.size() eq 0}">
            <div class="no-orders-displayed">
                <h1><spring:message code="Reservations.No.Orders.Message"
                                    htmlEscape="true"/></h1>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach var="order" items="${orders}">
                <div class="container order-item">
                    <h1 class="order-title"><spring:message code="Reservations.Order.Number"
                                                            htmlEscape="true"/> #${order.id}</h1>
                    <div class="order-description">
                        <div>
                            <h1><spring:message code="Reservations.When.Label"
                                                htmlEscape="true"/> ?</h1>
                            <h1><strong>${order.date}</strong></h1>
                        </div>
                        <div>
                            <h1><spring:message code="Reservations.Hours.Label"
                                                htmlEscape="true"/> ?</h1>
                            <h1><strong>${fn:replace(order.startingHour, '.', ':')}0
                                <span>&#8594;</span> ${fn:replace(order.endingHour, '.', ':')}0</strong></h1>
                        </div>
                        <div>
                            <h1><spring:message code="Reservations.Price.Label"
                                                htmlEscape="true"/> ?</h1>
                            <h1><strong>${order.totalPrice} RON</strong></h1>
                        </div>
                        <div>
                            <h1><spring:message code="Reservations.Placed.On.Label"
                                                htmlEscape="true"/> ?</h1>
                            <h1><strong>${fn:replace(order.creationDate, 'T', ' ')}</strong></h1>
                        </div>
                        <div>
                            <h1><spring:message code="Reservations.Transaction.Status.Label"
                                                htmlEscape="true"/> ?</h1>
                            <h1><strong>${order.transactionStatus}</strong></h1>
                        </div>
                    </div>
                    <div class="order-entries">
                        <c:forEach var="entry" items="${order.orderEntries}">
                            <div class="order-entry">
                                <img src="data:image/jpeg;base64,${entry.photoLink}"/>
                                <div class="order-entry-infos">
                                    <h2>${entry.name}</h2>
                                    <h3><spring:message code="Reservations.Quantity.Label"
                                                        htmlEscape="true"/>: <strong>${entry.quantity}</strong></h3>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <c:if test="${order.approved eq null}">
                        <div class="order-status">
                            <input id="message-${order.id}" class="order-message"
                                   placeholder="<spring:message code="Reservations.Let.A.Message"
                                                                                     htmlEscape="true"/>"
                                   oninput="showApprovalButtonsForOrder(this.value, this.id)"/>
                            <button id="accept-${order.id}" class="button is-primary" disabled
                                    onclick="acceptOrder(this.id)">
                                <spring:message code="Reservations.Accept.Button"
                                                htmlEscape="true"/>
                            </button>
                            <button id="refuse-${order.id}" class="button is-danger" disabled
                                    onclick="refuseOrder(this.id)">
                                <spring:message code="Reservations.Refuse.Order"
                                                htmlEscape="true"/>
                            </button>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</div>