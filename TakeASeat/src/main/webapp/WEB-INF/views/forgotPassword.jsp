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
    <title>Forgot Password | Take a sEAT</title>
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
<spring:url value="/forgot-password" var="forgotPasswordUrl"/>
<div id="login-label" class="forgot-password-form">
    <h1 class="login-form__h1">Forgot <strong>Take a sEAT</strong> password?
    </h1>
    <form:form method="post" action="${forgotPasswordUrl}" modelAttribute="forgotPasswordForm">
        <form:label class="label is-hidden" path="mail"><spring:message code="Login.Mail"
                                                                        htmlEscape="true"/></form:label>
        <form:input class="form-field" type="text" placeholder="Email" required="true" path="mail"/>
        <div class="login-form__checkers">
            <p class="form_error"><form:errors path="mail"/></p>
        </div>
        <button class="forgot-password-btn" type="submit">Recover password</button>
    </form:form>
</div>
<st:footer/>
</body>
</html>
