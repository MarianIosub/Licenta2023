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
</head>
<body>
<st:header/>
<spring:url value="/register" var="registerUrl"/>
<div id="registration-label" class="registration-modal">
    <h3 class="title is-center">Register to <strong>Take a sEAT!</strong></h3>
    <form:form method="post" action="${registerUrl}" modelAttribute="registerForm">
        <spring:bind path="name">
            <div class="field">
                <form:label class="label" path="name">Name</form:label>
                <div class="control">
                    <form:input name="registerForm.name" class="input" type="text" placeholder="Name" path="name"
                                required="required"/>
                    <form:errors path="name" class="is-danger"/>
                </div>
            </div>
        </spring:bind>
        <spring:bind path="surname">
            <div class="field">
                <form:label class="label" path="surname">Surname</form:label>
                <div class="control">
                    <form:input class="input" type="text" placeholder="Surname" required="true" path="surname"/>
                    <form:errors path="surname" class="is-danger"/>
                </div>
            </div>
        </spring:bind>
        <spring:bind path="mail">
            <div class="field">
                <form:label class="label" path="mail">Mail</form:label>
                <div class="control">
                    <form:input class="input" type="email" placeholder="Email" required="true" path="mail"/>
                    <form:errors path="mail" class="is-danger"/>
                </div>
            </div>
        </spring:bind>
        <spring:bind path="password">
            <div class="field">
                <form:label class="label" path="password">Password</form:label>
                <div class="control">
                    <form:input class="input" type="password" placeholder="Password" required="true"
                                path="password"/>
                    <form:errors path="password" class="is-danger"/>
                </div>
            </div>
        </spring:bind>
        <spring:bind path="confirmPassword">
            <div class="field">
                <form:label class="label" path="confirmPassword">Confirm Password</form:label>
                <div class="control">
                    <form:input class="input" type="password" placeholder="Confirm Password" required="true"
                                path="confirmPassword"/>
                    <form:errors path="confirmPassword" class="is-danger"/>
                </div>
            </div>
        </spring:bind>
        <spring:bind path="role">
            <div class="field">
                <form:label class="label" path="role">You are a:</form:label>
                <div class="control">
                    <div class="select">
                        <form:select required="required" path="role">
                            <option value="" selected disabled hidden>Click me!</option>
                            <option>Food lover</option>
                            <option>Administrator</option>
                        </form:select>
                        <form:errors path="role" class="is-danger"/>
                    </div>
                </div>
            </div>
        </spring:bind>
        <spring:bind path="terms">
            <div class="field">
                <div class="control">
                    <form:label class="checkbox" path="terms">
                        <form:checkbox required="true" path="terms"/>
                        I agree to the <a href="#">terms and conditions</a>
                    </form:label>
                    <form:errors path="role" class="is-danger"/>
                </div>
            </div>
        </spring:bind>
        <div class="field is-grouped is-center is-10">
            <div class="control">
                <button class="button is-link" type="submit">Let's eat!</button>
            </div>
        </div>
    </form:form>
</div>
<st:footer/>
</body>
</html>
