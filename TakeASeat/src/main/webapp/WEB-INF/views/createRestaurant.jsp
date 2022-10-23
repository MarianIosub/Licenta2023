<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Take a sEAT - Create Restaurant</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/register.css">
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>
</head>
<body>
<spring:url value="/restaurant/create" var="createRestaurantUrl"/>
<st:header/>
<div id="create-restaurant-label"
     class="form-modal__create-restaurant is-flex is-flex-direction-column is-align-items-center is-align-content-space-between">
    <h3 class="title">Create your <strong>Take a sEAT!</strong> restaurant profile:</h3>
    <form:form method="POST" action="${createRestaurantUrl}" modelAttribute="createRestaurantForm"
               cssClass="create-restaurant__form">
        <div class="field is-horizontal">
            <div class="field-body">
                <spring:bind path="name">
                    <div class="field-label">
                        <form:label class="label" path="name">Name</form:label>
                    </div>
                    <div class="control">
                        <form:input name="name" class="input" type="text" placeholder="Name" path="name"
                                    required="required"/>
                    </div>
                </spring:bind>
            </div>
            <div class="field-body">
                <spring:bind path="phoneNumber">
                    <div class="field-label">
                        <form:label class="label" path="phoneNumber">Phone Number</form:label>
                    </div>
                    <div class="control">
                        <form:input name="phoneNumber" class="input" type="number" placeholder="Phone Number"
                                    path="phoneNumber"
                                    required="required"/>
                    </div>
                </spring:bind>
            </div>
            <spring:bind path="name">
                <form:errors path="name" class="help is-danger"/>
            </spring:bind>
            <spring:bind path="phoneNumber">
                <form:errors path="phoneNumber" class="help is-danger"/>
            </spring:bind>
        </div>
        <spring:bind path="description">
            <div class="field is-flex is-flex-direction-column restaurant__description">
                <div class="is-spaced">
                    <form:label class="label" path="description">Description</form:label>
                </div>
                <div class="is-expanded">
                    <div class="control ">
                        <form:textarea name="description" class="textarea is-expanded" type="text"
                                       placeholder="Description"
                                       path="description"
                                       required="required"/>
                    </div>
                    <form:errors path="description" class="help is-danger"/>
                </div>
            </div>
        </spring:bind>
        <div class="field is-horizontal">
            <div class="field-body">
                <spring:bind path="email">
                    <div class="field-label">
                        <form:label class="label" path="email">Email</form:label>
                    </div>
                    <div class="control">
                        <form:input name="name" class="input" type="email" placeholder="Email" path="email"
                                    required="required"/>
                    </div>
                </spring:bind>
            </div>
            <div class="field-body">
                <spring:bind path="address">
                    <div class="field-label">
                        <form:label class="label" path="address">Address</form:label>
                    </div>
                    <div class="control">
                        <form:input name="phoneNumber" class="input" type="text" placeholder="Address"
                                    path="address" required="required"/>
                    </div>
                </spring:bind>
            </div>
            <spring:bind path="email">
                <form:errors path="email" class="help is-danger"/>
            </spring:bind>
            <spring:bind path="address">
                <form:errors path="address" class="help is-danger"/>
            </spring:bind>
        </div>
        <div class="field is-horizontal is-align-content-space-between">
            <div class="field-body">
                <spring:bind path="openingHour">
                    <div class="field-label">
                        <form:label class="label" path="openingHour">Open:</form:label>
                    </div>
                    <div class="select">
                        <form:select name="name" class="select is-fullwidth" placeholder="Opening Hour"
                                     path="openingHour"
                                     required="required">
                            <form:option value="7.00">7:00</form:option>
                            <form:option value="7.30">7:00</form:option>
                            <form:option value="8.00">8:00</form:option>
                            <form:option value="8.30">8:30</form:option>
                            <form:option value="9.00">9:00</form:option>
                            <form:option value="9.30">9:30</form:option>
                        </form:select>
                    </div>
                </spring:bind>
            </div>
            <div class="field-body">
                <spring:bind path="closingHour">
                    <div class="field-label">
                        <form:label class="label" path="closingHour">Close:</form:label>
                    </div>
                    <div class="select ">
                        <form:select name="closingHour" class="select is-fullwidth" placeholder="Closing Hour"
                                     path="closingHour" required="required">
                            <form:option value="19.00">19:00</form:option>
                            <form:option value="19.30">19:30</form:option>
                            <form:option value="20.00">20:00</form:option>
                            <form:option value="20.30">20:30</form:option>
                            <form:option value="21.00">21:00</form:option>
                            <form:option value="21.30">21:30</form:option>
                        </form:select>
                    </div>
                </spring:bind>
            </div>
            <div class="field-body">
                <spring:bind path="priceRequired">
                    <div class="field-label">
                        <form:label class="label" path="priceRequired">Price</form:label>
                    </div>
                    <div class="control">
                        <form:input name="priceRequired" class="input" placeholder="0.00"
                                    path="priceRequired" required="required" type="number"/>
                    </div>
                    <p class="control">
                        <a class="button is-static">
                            RON
                        </a>
                    </p>
                </spring:bind>
            </div>
            <spring:bind path="openingHour">
                <form:errors path="openingHour" class="help is-danger"/>
            </spring:bind>
            <spring:bind path="closingHour">
                <form:errors path="closingHour" class="help is-danger"/>
            </spring:bind>
            <spring:bind path="priceRequired">
                <form:errors path="priceRequired" class="help is-danger"/>
            </spring:bind>
        </div>

        <div class="field is-grouped is-grouped-centered">
            <div class="control">
                <button class="button is-link" type="submit">Create Restaurant</button>
            </div>
        </div>
    </form:form>

</div>
<st:footer/>
</body>
</html>
