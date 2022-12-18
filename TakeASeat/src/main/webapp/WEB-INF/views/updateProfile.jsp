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
<div id="update_profile-label" class="form-modal">
    <h3 class="title is-center">Change your <strong>Take a sEAT!</strong> account:</h3>
    <form:form method="post" action="${updateProfileUrl}" modelAttribute="updateProfileForm">
        <spring:bind path="name">
            <div class="field">
                <form:label class="label" path="name">Name</form:label>
                <div class="control">
                    <form:input name="name" class="input" type="text" placeholder="name" path="name"
                                required="required"/>
                    <form:errors path="name" class="help is-danger"/>
                </div>
            </div>
        </spring:bind>
        <spring:bind path="surname">
            <div class="field">
                <form:label class="label" path="surname">Surname</form:label>
                <div class="control">
                    <form:input class="input" type="text" placeholder="surname" required="true" path="surname"/>
                    <form:errors path="surname" class="help is-danger"/>
                </div>
            </div>
        </spring:bind>
        <spring:bind path="mail">
            <div class="field">
                <form:label class="label" path="mail">Mail</form:label>
                <div class="control">
                    <form:input class="input" type="email" placeholder="mail" required="true" path="mail"/>
                    <form:errors path="mail" class="help is-danger"/>
                </div>
            </div>
        </spring:bind>
        <spring:bind path="password">
            <div class="field">
                <form:label class="label" path="password">Password</form:label>
                <div class="control ">
                    <form:input class="input" type="password" placeholder="password" required="true"
                                path="password"/>
                    <form:errors path="password" class="help is-danger"/>
                </div>
            </div>
        </spring:bind>
        <spring:bind path="confirmPassword">
            <div class="field">
                <form:label class="label" path="confirmPassword">Confirm Password</form:label>
                <div class="control">
                    <form:input class="input" type="password" placeholder="password" required="true"
                                path="confirmPassword"/>
                    <form:errors path="confirmPassword" class="help is-danger"/>
                </div>
            </div>
        </spring:bind>
        <div class="field is-grouped is-center is-10">
            <div class="control">
                <button class="button is-link" type="submit">Change profile</button>
            </div>
        </div>
    </form:form>

</div>
<st:footer/>
</body>
</html>
