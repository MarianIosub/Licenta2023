function changeMenuItemFormVisibility() {
    let form = document.getElementById("add-food__form");
    if (form.classList.contains("is-hidden")) {
        window.open("http://localhost:8080/restaurant/manage", null, "height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
        displayForm(form);
    } else {
        createMenuItemOrHideForm(form);
    }
}

function displayForm(form) {
    form.classList.remove("is-hidden");
}

function hideForm(form) {
    form.classList.add("is-hidden");
}

function createMenuItemOrHideForm(form) {
    let name = document.getElementById('name');
    let photoLink = document.getElementById('photoLink');
    let ingredients = document.getElementById('ingredients');
    let price = document.getElementById('price');
    if (name.value !== '' && photoLink.value !== '' && ingredients.value !== '' && price.value !== '') {
        hideMenuItemError();
        postForm();
        emptyFormInputs(name, photoLink, ingredients, price);
        hideForm(form);
    } else if (name.value === '' && photoLink.value === '' && ingredients.value === '' && price.value === '') {
        hideMenuItemError();
        hideForm(form);
    } else {
        showMenuItemError();
    }
}

function showMenuItemError() {
    let menuItemErrorParagraph = document.getElementById("menu-item-error");
    menuItemErrorParagraph.classList.remove("is-hidden");
}

function hideMenuItemError() {
    let menuItemErrorParagraph = document.getElementById("menu-item-error");
    menuItemErrorParagraph.classList.add("is-hidden");
}

function postForm() {
    let formData = document.getElementById('menu-item__form');
    $.ajax({
        type: "POST",
        data: new FormData(formData),
        processData: false,
        contentType: false,
        url: "/restaurant/menu-item",
    }).then(function (response) {
        $('#menuItems').html(response);
    });
}

function emptyFormInputs(name, photoLink, ingredients, price) {
    name.value = '';
    photoLink.value = '';
    ingredients.value = '';
    price.value = '';
}