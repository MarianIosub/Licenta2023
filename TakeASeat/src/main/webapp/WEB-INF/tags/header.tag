<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('ROLE_ADMINISTRATOR')" var="isAdmin"/>

<nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="<c:url value="/"/>">
            <img src="../resources/assets/logo.png" width="40" height="40" alt="Logo">
        </a>

        <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>

    <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item" href="<c:url value="/"/>">
                Home
            </a>
            <c:choose>
                <c:when test="${isAdmin}">
                    <a class="navbar-item" href="<c:url value="/restaurant/manage"/> ">
                        My Restaurant
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="navbar-item" href="<c:url value="/restaurant/all"/> ">
                        Restaurants
                    </a>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="navbar-end">
            <sec:authorize access="isAnonymous()">
                <div class="navbar-item">
                    <div class="buttons">
                        <a class="button is-primary " href="<c:url value="/register"/>" id="nav-bar__register">
                            <strong>Sign up</strong>
                        </a>
                        <a class="button is-light" href="<c:url value="/login"/>" id="nav-bar__login">
                            Log in
                        </a>
                    </div>
                </div>
            </sec:authorize>
            <sec:authorize access="!isAnonymous()">
                <div class="navbar-item">
                    <div class="buttons">
                        <a class="button is-primary" href="<c:url value="/update-profile"/>" id="nav-bar__my-account">
                            <strong>My account</strong>
                        </a>
                        <a class="button is-light" href="<c:url value="/logout"/>" id="nav-bar__logout">
                            Log out
                        </a>
                    </div>
                </div>
            </sec:authorize>
        </div>
    </div>
    <script src="../resources/js/navbar.js"></script>
</nav>