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
    <title>Update Profile | Take a sEAT</title>
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
<st:flashMessage/>
<spring:url value="/update-profile" var="updateProfileUrl"/>
<div id="update_profile-label" class="update-profile-form">
    <h1 class="login-form__h1">Change your <strong>Take a sEAT!</strong> account:</h1>
    <form:form method="post" action="${updateProfileUrl}" modelAttribute="updateProfileForm">
        <spring:bind path="name">
            <form:label class="label is-hidden" path="name">Name</form:label>
            <form:input name="name" class="form-field" type="text" placeholder="Name" path="name"
                        required="required"/>

        </spring:bind>

        <spring:bind path="surname">
            <form:label class="label is-hidden" path="surname">Surname</form:label>
            <form:input class="form-field" type="text" placeholder="Surname" required="true" path="surname"/>
        </spring:bind>

        <spring:bind path="mail">
            <form:label class="label is-hidden" path="mail">Mail</form:label>
            <form:input class="form-field" type="email" placeholder="Mail" required="true" path="mail"/>
        </spring:bind>

        <spring:bind path="password">
            <form:label class="label is-hidden" path="password">Password</form:label>
            <form:input class="form-field" type="password" placeholder="Password" required="true"
                        path="password"/>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <form:label class="label is-hidden" path="confirmPassword">Confirm Password</form:label>
            <form:input class="form-field" type="password" placeholder="Confirm password" required="true"
                        path="confirmPassword"/>
        </spring:bind>
        <div class="update_profile_errors">
            <p class="form_error"><form:errors path="name"/></p>
            <p class="form_error"><form:errors path="surname"/></p>
            <p class="form_error"><form:errors path="mail"/></p>
            <p class="form_error"><form:errors path="password"/></p>
            <p class="form_error"><form:errors path="confirmPassword"/></p>
        </div>

        <button class="login-btn" type="submit">Change profile</button>
    </form:form>
</div>
<st:footer/>
</body>
</html>
