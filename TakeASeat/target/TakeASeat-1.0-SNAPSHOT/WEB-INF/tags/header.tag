<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="<c:url value="/"/>">
            <img src="../resources/assets/logo.png" width="40" height="40">
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
            <a class="navbar-item">
                Documentation
            </a>
            <div class="navbar-item has-dropdown is-hoverable">
                <a class="navbar-link">
                    More
                </a>
                <div class="navbar-dropdown">
                    <a class="navbar-item">
                        About
                    </a>
                    <a class="navbar-item">
                        Jobs
                    </a>
                    <a class="navbar-item">
                        Contact
                    </a>
                    <hr class="navbar-divider">
                    <a class="navbar-item">
                        Report an issue
                    </a>
                </div>
            </div>
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