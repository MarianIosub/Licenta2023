<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container">
    <c:if test="${menuItemAdded ne null}">
        <div class="menu-items__title">
            <h2><spring:message code="${menuItemAdded}"/></h2>
        </div>
    </c:if>
    <c:if test="${menuItems eq null}">
        <c:set var="menuItems" value="${currentRestaurant.menuItems}"/>
    </c:if>
    <div class="menu-items-list">
        <c:forEach items="${menuItems}" var="menuItem">
            <div class="menu-item" id="menuItem-${menuItem.id}" onmouseover="displayManageButtons(this.id)"
                 onmouseleave="hideManageButton(this.id)">
                <div class="menu-item-photo">
                    <img src="data:image/jpeg;base64,${menuItem.photoLink}" alt="">
                </div>
                <div class="menu-item-content">
                    <div class="menu-item-info">
                        <p class="menu-item-name">${menuItem.name}</p>
                        <p class="menu-item-ingredients"><strong><spring:message
                                code="ManageRestaurant.Items.Ingredients"
                                htmlEscape="true"/> </strong>${menuItem.ingredients}</p>
                        <div class="menu-item-footer">
                            <p class="menu-item-price"><strong><spring:message
                                    code="ManageRestaurant.Items.Price"
                                    htmlEscape="true"/> </strong> ${menuItem.price} RON</p>
                        </div>
                    </div>
                    <div class="menu-item-manage is-hidden">
                        <div class="menu-item-buttons">
                            <c:choose>
                                <c:when test="${not menuItem.available}">
                                    <button id="available-${menuItem.id}"
                                            class="button is-small is-responsive is-success"
                                            onclick="changeMenuItemAvailability(this.id)">
                                        <spring:message code="ManageRestaurant.Items.Available" htmlEscape="true"/>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button id="unavailable-${menuItem.id}"
                                            onclick="changeMenuItemAvailability(this.id)"
                                            class="button is-small is-responsive is-warning">
                                        <spring:message code="ManageRestaurant.Items.Unavailable" htmlEscape="true"/>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                            <button id="delete-${menuItem.id}" onclick="deleteMenuItem(this.id)"
                                    class="button is-small is-responsive is-danger">
                                <spring:message code="ManageRestaurant.Items.Delete" htmlEscape="true"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>