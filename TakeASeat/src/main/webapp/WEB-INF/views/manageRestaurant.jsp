<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Take a sEAT - Manage Restaurant</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/register.css">
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>
    <script src="../resources/js/menuItemForm.js"></script>
</head>
<body>
<c:set var="restaurant" value="${currentRestaurant}"/>
<st:header/>
<st:flashMessage/>
<section class="section">
    <div class="container">
        <h1 class="title">
            Hello There!
        </h1>
        <p class="subtitle">
            Welcome to <strong>Take a sEAT!</strong>!
        </p>
        <p>Manage Restaurant Page</p>
        <p>name : ${restaurant.name}</p>
        <p>phoneNumber : ${restaurant.phoneNumber}</p>
        <p>description : ${restaurant.description}</p>
        <p>city : ${restaurant.city}</p>
        <p>address : ${restaurant.address}</p>
    </div>
    <br>
    <div class="container is-hidden" id="add-food__form">
        <form id="menu-item__form">
            <div class="field is-horizontal">
                <div class="field-label is-normal">
                    <label class="label">Name</label>
                </div>
                <div class="field-body">
                    <div class="field">
                        <p class="control">
                            <input class="input" type="text" placeholder="Name" name="name" id="name" required/>
                        </p>
                    </div>
                </div>
                <div class="field-label is-normal">
                    <label class="label">Photo Link</label>
                </div>
                <div class="field-body">
                    <div class="field">
                        <p class="control">
                            <input class="input" type="text" placeholder="Photo Link" name="photoLink" id="photoLink"
                                   required/>
                        </p>
                    </div>
                </div>
            </div>
            <div class="field is-horizontal">
                <div class="field-label is-normal">
                    <label class="label">Ingredients</label>
                </div>
                <div class="field-body">
                    <div class="field">
                        <p class="control">
                            <input class="input" type="text" placeholder="water, flour, .." name="ingredients"
                                   id="ingredients" required/>
                        </p>
                    </div>
                </div>
                <div class="field-label is-normal">
                    <label class="label">Price</label>
                </div>
                <div class="field-body">
                    <div class="field">
                        <p class="control">
                            <input class="input" type="number" placeholder="0" name="price" id="price" required/>
                        </p>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <br>
    <div class="container">
        <a class="button is-primary add-menu-item__btn" id="add-food__btn" onclick="changeMenuItemFormVisibility()">Add
            menu item</a>
    </div>
</section>
<section id="menuItems" class="section">
    <jsp:include page="menuItems.jsp"/>
</section>
<st:footer/>
</body>
</html>
