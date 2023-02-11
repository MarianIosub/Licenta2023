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
    <title>Create Restaurant | Take a sEAT</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../resources/css/style.css">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
    ></script>

</head>
<body>
<spring:url value="/restaurant/create" var="createRestaurantUrl"/>
<st:header/>
<st:flashMessage/>
<div id="create-restaurant-label"
     class="create-restaurant-form">
    <h1 class="login-form__h1"><spring:message code="CreateRestaurant.Title.FirstPart" htmlEscape="true"/> <strong>Take a
        sEAT!</strong> <spring:message code="CreateRestaurant.Title.SecondPart" htmlEscape="true"/>:</h1>
    <form:form method="POST" action="${createRestaurantUrl}" modelAttribute="createRestaurantForm"
               enctype="multipart/form-data">

        <spring:bind path="photo">
            <div id="file-js-example" class="file is-centered is-boxed is-success has-name">
                <form:label class="file-label my-photo-label" path="photo">
                    <form:input name="photo" class="file-input" type="file" placeholder="Photo" path="photo"
                                required="required"/>
                    <span class="file-cta">
                            <span class="file-icon">
                            <i class="fas fa-upload"></i>
                        </span>
                        <span class="file-label">
                            <spring:message code="CreateRestaurant.Photo"
                                            htmlEscape="true"/>
                        </span>
                    </span>
                    <span class="file-name my-file-name">
                        ...
                    </span>
                </form:label>
            </div>
        </spring:bind>

        <spring:bind path="name">
            <form:label class="is-hidden" path="name"><spring:message code="CreateRestaurant.Name"
                                                                      htmlEscape="true" var="name"/></form:label>
            <form:input name="name" class="form-field" type="text" placeholder="${name}" path="name"
                        required="required"/>
        </spring:bind>

        <spring:bind path="phoneNumber">
            <form:label class="is-hidden" path="phoneNumber"><spring:message code="CreateRestaurant.PhoneNumber"
                                                                             htmlEscape="true" var="phone"/></form:label>
            <form:input name="phoneNumber" class="form-field" type="number" placeholder="${phone}"
                        path="phoneNumber"
                        required="required"/>
        </spring:bind>

        <spring:bind path="description">
            <form:label class="is-hidden" path="description"><spring:message code="CreateRestaurant.Description"
                                                                             htmlEscape="true" var="description"/></form:label>
            <form:input name="description" class="form-field" type="text"
                        placeholder="${description}"
                        path="description"
                        required="required"/>
        </spring:bind>

        <spring:bind path="mail">
            <form:label class="is-hidden" path="mail">Email</form:label>
            <form:input name="mail" class="form-field" type="email" placeholder="Email" path="mail"
                        required="required"/>

        </spring:bind>

        <spring:bind path="city">
            <form:label class="is-hidden" path="city"><spring:message code="CreateRestaurant.City"
                                                                      htmlEscape="true" var="city"/></form:label>
            <form:input name="city" class="form-field" type="text" placeholder="${city}" path="city"
                        required="required"/>
        </spring:bind>

        <spring:bind path="address">
            <form:label class="is-hidden" path="address"><spring:message code="CreateRestaurant.Address"
                                                                         htmlEscape="true" var="address"/></form:label>
            <form:input name="phoneNumber" class="form-field" type="text" placeholder="${address}"
                        path="address" required="required"/>
        </spring:bind>


        <spring:bind path="openingHour">
            <form:label class="is-hidden" path="openingHour"><spring:message code="CreateRestaurant.Open"
                                                                             htmlEscape="true" var="open"/>:</form:label>
            <form:select name="openingHour" class="form-field" placeholder="${open}"
                         path="openingHour"
                         required="required">
                <form:option value="7.00">9:00</form:option>
                <form:option value="7.30">9:30</form:option>
                <form:option value="8.00">10:00</form:option>
                <form:option value="8.30">10:30</form:option>
                <form:option value="9.00">11:00</form:option>
                <form:option value="9.30">11:30</form:option>
            </form:select>
        </spring:bind>

        <spring:bind path="closingHour">
            <form:label class="is-hidden" path="closingHour"><spring:message code="CreateRestaurant.Close"
                                                                             htmlEscape="true" var="close"/>:</form:label>
            <form:select name="closingHour" class="form-field" placeholder="${close}"
                         path="closingHour" required="required">
                <form:option value="19.00">20:00</form:option>
                <form:option value="19.30">20:30</form:option>
                <form:option value="20.00">21:00</form:option>
                <form:option value="20.30">21:30</form:option>
                <form:option value="21.00">22:00</form:option>
                <form:option value="21.30">22:30</form:option>
            </form:select>
        </spring:bind>


        <spring:bind path="priceRequired">
            <form:label class="is-hidden" path="priceRequired"><spring:message code="CreateRestaurant.Price"
                                                                               htmlEscape="true" var="price"/></form:label>
            <form:input name="priceRequired" class="form-field" placeholder="${price}: 0.00"
                        path="priceRequired" required="required" type="number"/>
        </spring:bind>

        <div class="update_profile_errors">
            <p class="form_error"><form:errors path="name"/></p>
            <p class="form_error"><form:errors path="photo"/></p>
            <p class="form_error"><form:errors path="mail"/></p>
            <p class="form_error"><form:errors path="phoneNumber"/></p>
            <p class="form_error"><form:errors path="description"/></p>
            <p class="form_error"><form:errors path="city"/></p>
            <p class="form_error"><form:errors path="address"/></p>
            <p class="form_error"><form:errors path="openingHour"/></p>
            <p class="form_error"><form:errors path="closingHour"/></p>
            <p class="form_error"><form:errors path="priceRequired"/></p>
        </div>


        <button class="create-restaurant-btn" type="submit"><spring:message code="CreateRestaurant.Button"
                                                                            htmlEscape="true"/></button>

    </form:form>

</div>
<st:footer/>
</body>
<script>
    const fileInput = document.querySelector('#file-js-example input[type=file]');
    fileInput.onchange = () => {
        if (fileInput.files.length > 0) {
            const fileName = document.querySelector('#file-js-example .file-name');
            fileName.textContent = fileInput.files[0].name;
        }
    }
</script>
</html>
