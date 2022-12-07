<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <div class="menu-item-search">
        <label title="Menu Item name: ">
            <input class="input" type="search" placeholder="Search.." id="menu-items-search" onchange="searchForMenuItem()"/>
        </label>
    </div>
    <c:if test="${menuItemAdded ne null}">
        <div class="menu-items__title">
            <h2><spring:message code="${menuItemAdded}"/></h2>
        </div>
    </c:if>
    <c:if test="${menuItems eq null}">
        <c:set var="menuItems" value="${currentRestaurant.menuItems}"/>
    </c:if>
    <c:forEach items="${menuItems}" var="menuItem">
        <div class="container">
            Name: ${menuItem.name}
        </div>
    </c:forEach>
</div>