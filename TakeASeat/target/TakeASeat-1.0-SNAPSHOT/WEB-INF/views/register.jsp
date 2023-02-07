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
    <title>Register | Take a sEAT</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/style.css">
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>

</head>
<body>
<st:header/>
<st:flashMessage/>
<spring:url value="/register" var="registerUrl"/>
<div id="registration-label" class="register-form">
    <h1 class="register-form__h1"><spring:message code="Register.Title"/> <strong>Take a sEAT!</strong></h1>
    <form:form method="post" action="${registerUrl}" modelAttribute="registerForm">
        <div><spring:bind path="name">
            <form:label class="label is-hidden" path="name"><spring:message code="Register.Name"/></form:label>
            <form:input name="registerForm.name" class="form-field" type="text" placeholder="Name" path="name"
                        required="required"/>
        </spring:bind>
        </div>
        <div>
            <spring:bind path="surname">

                <form:label class="label is-hidden" path="surname"><spring:message
                        code="Register.Surname"/></form:label>
                <form:input class="form-field" type="text" placeholder="Surname" required="true" path="surname"/>

            </spring:bind>
        </div>
        <div>
            <spring:bind path="mail">
                <form:label class="label is-hidden" path="mail"><spring:message code="Register.Mail"/></form:label>
                <form:input class="form-field" type="email" placeholder="Email" required="true" path="mail"/>
            </spring:bind>
        </div>
        <div>
            <spring:bind path="password">
                <form:label class="label is-hidden" path="password"><spring:message
                        code="Register.Password"/></form:label>
                <form:input class="form-field" type="password" placeholder="Password" required="true"
                            path="password"/>
            </spring:bind>
        </div>
        <div>
            <spring:bind path="confirmPassword">
                <form:label class="label is-hidden" path="confirmPassword"><spring:message
                        code="Register.Confirm.Password"/></form:label>
                <form:input class="form-field" type="password" placeholder="Confirm Password" required="true"
                            path="confirmPassword"/>
            </spring:bind>
        </div>
        <div>
            <spring:bind path="role">
                <form:label class="label is-hidden" path="role"><spring:message
                        code="Register.Role.Label"/>:</form:label>
                <form:select required="required" path="role" cssClass="form-field">
                    <option value="" selected disabled hidden>Click me!</option>
                    <option value="ROLE_FOOD_LOVER"><spring:message code="Register.Food.Lover.Role"/></option>
                    <option value="ROLE_ADMINISTRATOR"><spring:message
                            code="Register.Administrator.Role"/></option>
                </form:select>

            </spring:bind>
        </div>
        <div class="login-form__checkers">
            <spring:bind path="terms">
                <form:label class="checkbox" path="terms">
                    <form:checkbox required="true" path="terms"/>
                    <spring:message code="Register.Terms.And.Conditions.First"/> <a href="#"><spring:message
                        code="Register.Terms.And.Conditions.Second"/></a>
                </form:label>
            </spring:bind>
            <p class="form_error"><form:errors path="role" class="is-danger"/></p>
            <p class="form_error"><form:errors path="name" class="is-danger"/></p>
            <p class="form_error"><form:errors path="surname" class="is-danger"/></p>
            <p class="form_error"><form:errors path="mail" class="is-danger"/></p>
            <p class="form_error"><form:errors path="password" class="is-danger"/></p>
            <p class="form_error"><form:errors path="confirmPassword" class="is-danger"/></p>
            <p class="form_error"><form:errors path="role" class="is-danger"/></p>
        </div>
        <button class="login-btn" type="submit"><spring:message code="Register.Button"/></button>
    </form:form>
</div>
<st:footer/>
</body>
</html>
