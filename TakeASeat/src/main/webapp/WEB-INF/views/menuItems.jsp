<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <c:if test="${menuItemAdded ne null}">
        <h3><spring:message code="${menuItemAdded}"/></h3>
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