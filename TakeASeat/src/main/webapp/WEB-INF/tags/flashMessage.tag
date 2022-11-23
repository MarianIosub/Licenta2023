<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="flashMessage" value="${flashMessage}" scope="request"/>

<c:if test="${flashMessage ne null}">
    <h1 class="flash-message" id="flash-message">
        <spring:message code="${flashMessage}" htmlEscape="true"/>
    </h1>
</c:if>