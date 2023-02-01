<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login | Take a sEAT</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/register.css">
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>

</head>
<body class="login-body">
<st:header/>
<st:flashMessage/>
<spring:url value="/login" var="loginUrl"/>
<div id="login-label" class="login-form">
    <h1 class="login-form__h1"><spring:message code="Login.Title" htmlEscape="true"/> <strong>Take a sEAT!</strong>
    </h1>
    <form:form method="post" action="${loginUrl}" modelAttribute="loginForm">
        <form:label class="label is-hidden" path="mail"><spring:message code="Login.Mail"
                                                                        htmlEscape="true"/></form:label>
        <form:input class="form-field" type="text" placeholder="Email" required="true" path="mail"/>
        <form:errors path="mail" class="help is-danger"/>

        <form:label class="label is-hidden" path="password"><spring:message code="Login.Password"
                                                                            htmlEscape="true"/></form:label>
        <form:input class="form-field" type="password" placeholder="Password" required="true"
                    path="password"/>
        <form:errors path="password" class="help is-danger"/>
        <div class="login-form__checkers">
            <form:label class="checkbox" path="rememberMe">
                <form:checkbox path="rememberMe" cssClass="remember-me"/>
                <spring:message code="Login.RememberMe" htmlEscape="true"/>!
            </form:label>
            <form:errors path="rememberMe" class="is-danger"/>
            <c:if test="${loginError ne null}">
                <p class="form_error"><spring:message code="${loginError}"/></p>
            </c:if>
        </div>
        <button class="login-btn" type="submit"><spring:message code="Login.Button"
                                                                htmlEscape="true"/></button>
    </form:form>
    <a class="forget-password" href="/forgot-password">Forgot password? <span>&#8594;</span></a>
</div>
<st:footer/>
</body>
</html>
