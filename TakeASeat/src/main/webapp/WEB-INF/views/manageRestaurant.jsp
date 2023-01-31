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
    <title>Manage Restaurant | Take a sEAT</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/register.css">
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>
    <script src="../resources/js/menuItemForm.js"></script>
    <script src="../resources/js/menuItemsSearch.js"></script>
    <script src="../resources/js/menuItemsManage.js"></script>

</head>
<body>
<c:set var="restaurant" value="${currentRestaurant}"/>
<spring:message code="Search.Label" var="searchLabel"/>
<st:header/>
<st:flashMessage/>
<section class="section manage-restaurant-header">
    <div class="restaurant-presentation">
        <h1 class="restaurant-name">${restaurant.name}</h1>
        <div class="restaurant-middle-section">
            <div class="restaurant-phone-number">
                <h1><spring:message code="ManageRestaurant.PhoneNumber" htmlEscape="true"/></h1>
                <h1><strong>${restaurant.phoneNumber}</strong></h1>
            </div>
            <div class="restaurant-address">
                <h3><spring:message code="ManageRestaurant.Address" htmlEscape="true"/></h3>
                <h3><strong>${restaurant.address}</strong></h3>
                <h3><strong>${restaurant.city}</strong></h3>
            </div>
        </div>
        <h5 class="restaurant-description">${restaurant.description}</h5>
    </div>
    <div class="menu-items-helpers">
        <div class="is-hidden add-menu-item-form" id="add-food__form">
            <h1 class="menu-item-form-title"><spring:message code="ManageRestaurant.CreateItem.Title"
                                                             htmlEscape="true"/></h1>
            <form id="menu-item__form">

                <div class="field is-horizontal">

                    <div class="field-label is-normal">
                        <label class="label"><spring:message code="ManageRestaurant.CreateItem.Name"
                                                             htmlEscape="true"/></label>
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
                        <label class="label"><spring:message code="ManageRestaurant.CreateItem.PhotoLink"
                                                             htmlEscape="true"/></label>
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
                        <label class="label"><spring:message code="ManageRestaurant.CreateItem.Ingredients"
                                                             htmlEscape="true"/></label>
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
                        <label class="label"><spring:message code="ManageRestaurant.CreateItem.Price"
                                                             htmlEscape="true"/></label>
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
            <a class="button is-primary add-menu-item__btn" id="add-food__btn" onclick="changeMenuItemFormVisibility()">
                <spring:message code="Manage.Restaurant.Add.Menu.Item.Button" htmlEscape="true"/></a>
        </div>
    </div>
</section>
<section>
    <div class="container">
        <div class="menu-item-search">
            <h1 id="menu-item-search-label" class="menu-item-search-label"><spring:message
                    code="ManageRestaurant.SearchItem.Title" htmlEscape="true"/> &darr;</h1>
            <label title="Menu Item name: ">
                <input class="input" type="text"
                       placeholder="${searchLabel}.."
                       id="menu-items-search" onkeyup="searchForMenuItem()" onclick="scrollForMenuItems()"/>
            </label>
        </div>
    </div>
</section>
<section id="menuItems" class="section">
    <jsp:include page="menuItems.jsp"/>
</section>
<c:if test="${currentRestaurant.reviews.size()>0}">
    <div class="container">
        <div class="restaurant-reviews">
            <h1 class="restaurant-reviews-title"><spring:message
                    code="Manage.Restaurant.Review" htmlEscape="true"/> &darr;</h1>
            <c:forEach var="review" items="${currentRestaurant.reviews}">
                <div class="restaurant-review">
                    <div class="review-header">
                        <h1 class="review-title"><strong>${review.user}</strong> <spring:message
                                code="Manage.Restaurant.Visited.On" htmlEscape="true"/>
                            <strong>${review.localDate}</strong></h1>
                        <h1 class="review-rating">
                            <c:forEach begin="1" end="${review.grade}" step="1">
                                &#9733;
                            </c:forEach>
                        </h1>
                    </div>
                    <h1 class="review-comment">
                        <q>${review.comment}</q></h1>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>
<st:footer/>
</body>
</html>
