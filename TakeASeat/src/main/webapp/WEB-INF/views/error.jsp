<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Internal Error | Take a sEAT</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.4.0/gsap.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/gsap/latest/TimelineLite.min.js"></script>
    <link rel="stylesheet" href="../resources/css/error.css">
    <script src="../resources/js/error.js"></script>
</head>
<body>
<div class="container">
    <h1 class="first-four">5</h1>
    <div class="cog-wheel1">
        <div class="cog1">
            <div class="top"></div>
            <div class="down"></div>
            <div class="left-top"></div>
            <div class="left-down"></div>
            <div class="right-top"></div>
            <div class="right-down"></div>
            <div class="left"></div>
            <div class="right"></div>
        </div>
    </div>

    <div class="cog-wheel2">
        <div class="cog2">
            <div class="top"></div>
            <div class="down"></div>
            <div class="left-top"></div>
            <div class="left-down"></div>
            <div class="right-top"></div>
            <div class="right-down"></div>
            <div class="left"></div>
            <div class="right"></div>
        </div>
    </div>
    <p class="wrong-para"><spring:message code="Error.500.Message" htmlEscape="true"/></p>
</div>
</body>
</html>
