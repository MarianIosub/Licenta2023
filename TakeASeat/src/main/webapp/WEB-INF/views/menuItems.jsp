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
            <div class="menu-item" id="menuItem${menuItem.id}">
                <div class="menu-item-photo">
                    <img src="data:image/jpeg;base64,${menuItem.photoLink}" alt="">
                </div>
                <div class="menu-item-info">
                    <p class="menu-item-name">${menuItem.name}</p>
                    <p class="menu-item-ingredients"><strong>Ingredients: </strong>${menuItem.ingredients}</p>
                    <div class="menu-item-footer">
                        <p class="menu-item-price"><strong>Price: </strong> ${menuItem.price} RON</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>