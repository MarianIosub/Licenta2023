function changeMenuItemFormVisibility() {
    let form = document.getElementById("add-food__form");
    if (form.classList.contains("is-hidden")) {
        displayForm(form);
    } else {
        hideForm(form);
    }
}

function hideForm(form) {
    createMenuItem();
    form.classList.add("is-hidden");
}

function displayForm(form) {
    form.classList.remove("is-hidden");
}

function createMenuItem() {
    let name = document.getElementById('name');
    let photoLink = document.getElementById('photoLink');
    let ingredients = document.getElementById('ingredients');
    let price = document.getElementById('price');
    if (name.value !== '' && photoLink.value !== '' && ingredients.value !== '' && price.value !== '') {
        postForm();
        name.value = '';
        photoLink.value = '';
        ingredients.value = '';
        price.value = '';
    }
}

function postForm() {
    let formData = $('#menu-item__form').serialize();
    $.ajax({
        type: "POST",
        data: formData,
        url: "/restaurant/menu-item",
    }).then(function (response) {
        $('#menuItems').html(response);
    });
}