<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
    <c:set var="menuItems" value="${menuItems ne null ? menuItems : currentRestaurant.menuItems}"/>
    <c:if test="${hasAvailableItems}">
        <h1 class="menu-items-availability-groups"> Available products &darr; </h1>
        <div class="menu-items-list">
            <c:forEach items="${menuItems}" var="menuItem">
                <c:if test="${menuItem.available}">
                    <div class="menu-item" id="menuItem-${menuItem.id}" onmouseover="displayCartButton(this.id)"
                         onmouseleave="hideCartButton(this.id)">
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
                            <div class="menu-item-manage menu-item-cart-button is-hidden">
                                <div class="menu-item-buttons">
                                    <button class="button add-to-cart-button">
                                        <img src="../resources/assets/cart.png" width="20px" height="20px"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${hasUnavailableItems}">
        <h1 class="menu-items-availability-groups"> Unavailable products &darr; </h1>
        <div class="menu-items-list">
            <c:forEach items="${menuItems}" var="menuItem">
                <c:if test="${not menuItem.available}">
                    <div class="menu-item" id="menuItem-${menuItem.id}">
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
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:if>
</div>
