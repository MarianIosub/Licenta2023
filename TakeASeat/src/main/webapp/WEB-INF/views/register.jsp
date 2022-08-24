<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
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
<div id="registration-label" class="registration-modal">
    <h3 class="title is-center">Register to <strong>Take a sEAT!</strong></h3>
    <form:form method="post" action="#" >
        <div class="field">
            <form:label class="label" path="name">Name</form:label>
            <div class="control">
                <form:input class="input" type="text" placeholder="Name" path="name" required="required"/>
            </div>
        </div>
        <div class="field">
            <form:label class="label" path="surname">Surname</form:label>
            <div class="control">
                <form:input class="input" type="text" placeholder="Surname" required="true" path="surname"/>
            </div>
        </div>

        <div class="field">
            <form:label class="label" path="email">Email</form:label>
            <div class="control has-icons-left has-icons-right">
                <form:input class="input" type="email" placeholder="Email input" required="true" path="email"/>
                <span class="icon is-small is-left">
      <i class="fas fa-envelope"></i>
    </span>
                <span class="icon is-small is-right">
      <i class="fas fa-exclamation-triangle"></i>
    </span>
            </div>
            <p class="help is-danger is-hidden">This email is invalid</p>
        </div>

        <div class="field">
            <form:label class="label" path="password">Password</form:label>
            <div class="control has-icons-left has-icons-right">
                <form:input class="input" type="password" placeholder="Password input" required="true" path="password"/>
                <span class="icon is-small is-left">
      <i class="fas fa-envelope"></i>
    </span>
                <span class="icon is-small is-right">
      <i class="fas fa-exclamation-triangle"></i>
    </span>
            </div>
            <p class="help is-danger is-hidden">This email is invalid</p>
        </div>

        <div class="field">
            <form:label class="label" path="title">You are a:</form:label>
            <div class="control">
                <div class="select">
                    <form:select required="required" path="title">
                        <option value="" selected disabled hidden>Click me!</option>
                        <option>Food lover</option>
                        <option>Administrator</option>
                    </form:select>
                </div>
            </div>
        </div>

        <div class="field">
            <div class="control">
                <form:label class="checkbox" path="terms">
                    <form:input type="checkbox" required="true" path="terms"/>
                    I agree to the <a href="#">terms and conditions</a>
                </form:label>
            </div>
        </div>

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
