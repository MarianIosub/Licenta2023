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
                <h1>You don't have any orders in this category!</h1>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach var="order" items="${orders}">
                <div class="container order-item">
                    <h1 class="order-title">Order #${order.id} - ${order.restaurant.name}</h1>
                    <div class="order-description">
                        <div>
                            <h1>When ?</h1>
                            <h1><strong>${order.date}</strong></h1>
                        </div>
                        <div>
                            <h1>Hours ?</h1>
                            <h1><strong>${fn:replace(order.startingHour, '.', ':')}0
                                <span>&#8594;</span> ${fn:replace(order.endingHour, '.', ':')}0</strong></h1>
                        </div>
                        <div>
                            <h1>Price ?</h1>
                            <h1><strong>${order.totalPrice} RON</strong></h1>
                        </div>
                        <div>
                            <h1>Placed on ?</h1>
                            <h1><strong>${fn:replace(order.creationDate, 'T', ' ')}</strong></h1>
                        </div>
                        <div>
                            <h1>Transaction Status ?</h1>
                            <h1><strong>${order.transactionStatus}</strong></h1>
                        </div>
                    </div>
                    <div class="order-entries">
                        <c:forEach var="entry" items="${order.orderEntries}">
                            <div class="order-entry">
                                <img src="data:image/jpeg;base64,${entry.photoLink}"/>
                                <div class="order-entry-infos">
                                    <h2>${entry.name}</h2>
                                    <h3>Quantity: <strong>${entry.quantity}</strong></h3>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <c:choose>
                        <c:when test="${!order.reviewed and (order.date.isBefore(today)) and order.approved}">
                            <div class="order-review">
                                <input id="review-message-${order.id}" class="review-message"
                                       placeholder="Let a review for ${order.restaurant.name}.."
                                       oninput="enableReviewButton(this.id)"/>
                                <select id="review-select-${order.id}" class="review-select select"
                                        onchange="enableReviewButton(this.id)">
                                    <option disabled selected value> Stars</option>
                                    <option value="5">&#9733; &#9733; &#9733; &#9733; &#9733;</option>
                                    <option value="4">&#9733; &#9733; &#9733; &#9733;</option>
                                    <option value="3">&#9733; &#9733; &#9733;</option>
                                    <option value="2">&#9733; &#9733;</option>
                                    <option value="1">&#9733;</option>
                                </select>
                                <button id="review-${order.id}" class="button is-primary" disabled
                                        onclick="placeReview(this.id)">
                                    Place review
                                </button>
                            </div>
                        </c:when>
                        <c:when test="${order.approved ne null}">
                            <div class="order-review">
                                <h1 class="approval-message">Restaurant's response: <strong>${order.message}</strong></h1>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</div>