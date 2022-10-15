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
    <title>Take a sEAT - Register</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/register.css">
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>
</head>
<body>
<st:header/>
<spring:url value="/login" var="loginUrl"/>
<div id="registration-label" class="form-modal">
    <h3 class="title is-center">Login to <strong>Take a sEAT!</strong></h3>
    <form:form method="post" action="${loginUrl}" modelAttribute="loginForm">
        <div class="field">
            <form:label class="label" path="mail">Mail</form:label>
            <div class="control">
                <form:input class="input" type="email" placeholder="Email" required="true" path="mail"/>
                <form:errors path="mail" class="help is-danger"/>
            </div>
        </div>
        <div class="field">
            <form:label class="label" path="password">Password</form:label>
            <div class="control">
                <form:input class="input" type="password" placeholder="Password" required="true"
                            path="password"/>
                <form:errors path="password" class="help is-danger"/>
            </div>
        </div>
        <div class="field">
            <div class="control">
                <form:label class="checkbox" path="rememberMe">
                    <form:checkbox path="rememberMe"/>
                    Remember me!
                </form:label>
                <form:errors path="rememberMe" class="is-danger"/>
            </div>
        </div>
        <c:if test="${loginError ne null}">
            <p class="help is-danger is-large">${loginError}</p>
        </c:if>

        <div class="field is-grouped is-center is-10">
            <div class="control">
                <button class="button is-link" type="submit">Let me in!</button>
            </div>
        </div>
    </form:form>
</div>
<st:footer/>
</body>
</html>
