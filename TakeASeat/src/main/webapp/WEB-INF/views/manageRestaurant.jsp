<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
    <script src="../resources/js/menuItemsSearch.js"></script>
</head>
<body>
<c:set var="restaurant" value="${currentRestaurant}"/>
<st:header/>
<st:flashMessage/>
<section class="section manage-restaurant-header">
    <div class="restaurant-presentation">
        <h1 class="restaurant-name">${restaurant.name}</h1>
        <div class="restaurant-middle-section">
            <div class="restaurant-phone-number">
                <h1>Phone Number: </h1>
                <h1><strong>${restaurant.phoneNumber}</strong></h1>
            </div>
            <div class="restaurant-address">
                <h3>Address: </h3>
                <h3><strong>${restaurant.address}</strong></h3>
                <h3><strong>${restaurant.city}</strong></h3>
            </div>
        </div>
        <h5 class="restaurant-description">${restaurant.description}</h5>
    </div>
    <div class="menu-items-helpers">
        <div class="is-hidden add-menu-item-form" id="add-food__form">
            <h1 class="menu-item-form-title">Create menu item: </h1>
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
                </div>

                <div class="field is-horizontal">
                    <div class="field-label is-normal">
                        <label class="label">Photo Link</label>
                    </div>
                    <div class="field-body">
                        <div class="field">
                            <p class="control">
                                <input class="input" type="file" placeholder="Photo Link" name="photoLink" size="50"
                                       id="photoLink"
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
                </div>

                <div class="field is-horizontal">

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
        <div class="container menu-item-form__footer">
            <p class="is-danger is-hidden is-primary add-menu-item__error" id="menu-item-error"><spring:message
                    code="MenuItem.AllFieldRequired" htmlEscape="true"/></p>
            <a class="button is-primary add-menu-item__btn" id="add-food__btn" onclick="changeMenuItemFormVisibility()">Add
                menu item</a>
        </div>
    </div>
</section>
<section>
    <div class="container">
        <div class="menu-item-search">
            <h1 class="menu-item-search-label">Search for your menu items &darr;</h1>
            <label title="Menu Item name: ">
                <input class="input" type="text"
                       placeholder="Search.."
                       id="menu-items-search" onkeyup="searchForMenuItem()"/>
            </label>
        </div>
    </div>
</section>
<section id="menuItems" class="section">
    <jsp:include page="menuItems.jsp"/>
</section>
<st:footer/>
</body>
</html>
