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
    <title>Restaurants | Take a sEAT</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/register.css">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>
    <script src="../resources/js/restaurants.js"></script>


</head>
<body>
<st:header/>
<spring:message code="Search.Label" var="searchLabel"/>
<div class="restaurants-header">
    <h1 class="restaurants-title"><spring:message code="Restaurants.Title"/> &darr;</h1>
    <label id="restaurants-search-label" class="label restaurants-search-label">
        <input id="restaurants-search" class="input restaurants-search-input" placeholder="${searchLabel}.." type="text"
               onkeyup="searchForRestaurants()" onclick="scrollForRestaurants()">
    </label>
</div>
<div class="container restaurants-manager">
    <div class="dropdown is-hoverable is-pulled-right is-right">
        <div class="dropdown-trigger">
            <button class="button" aria-haspopup="true" aria-controls="dropdown-menu4">
                <span><spring:message code="Restaurant.Order.Dropdown.Label"/></span>
                <span class="icon is-small">
                        &#10225;
                    </span>
            </button>
        </div>
        <div class="dropdown-menu" id="dropdown-menu4" role="menu">
            <div class="dropdown-content is-pulled-right">
                <div class="dropdown-item">
                    <a onclick="sortBy('ALPHABETICAL')">A <span>&#8594;</span> Z</a>
                </div>
                <div class="dropdown-item">
                    <a onclick="sortBy('INVERSE')">Z <span>&#8594;</span> A</a>
                </div>
                <div class="dropdown-item">
                    <a onclick="sortBy('POPULAR')"><spring:message code="Restaurant.Order.Dropdown.Popular"/></a>
                </div>
                <div class="dropdown-item">
                    <a onclick="sortBy('RATING')">Rating</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container restaurants-list" id="restaurants-list">
    <jsp:include page="restaurantsList.jsp"/>
</div>
<st:footer/>
</body>
</html>
