<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="flashMessage" value="${flashMessage}" scope="request"/>
<!-- TODO flash attr -->
<div class="is-fixed-top is-align-self-center is-bordered">
    <h3><c:out value="${flashMessage}"/></h3>
</div>