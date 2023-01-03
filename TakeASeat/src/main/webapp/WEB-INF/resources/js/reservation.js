function addToCart(id) {
    let menuItemId = id.split('-').pop();
    $.ajax({
        type: "POST",
        data: {menuItemId: menuItemId},
        url: "/cart/add",
    }).then(function (response) {
        $('#reservation-component').html(response);
    });
}

function deleteFromCart(id) {
    let menuItemId = id.split('-').pop();
    $.ajax({
        type: "POST",
        data: {menuItemId: menuItemId},
        url: "/cart/delete",
    }).then(function (response) {
        $('#reservation-component').html(response);
    });
}

function increaseQuantity(id) {
    document.getElementById(id).disabled = true;

    let menuItemId = id.split('-').pop();
    $.ajax({
        type: "POST",
        data: {menuItemId: menuItemId},
        url: "/cart/increase",
    }).then(function (response) {
        $('#reservation-component').html(response);
    });
}

function decreaseQuantity(id) {
    document.getElementById(id).disabled = true;

    let menuItemId = id.split('-').pop();
    $.ajax({
        type: "POST",
        data: {menuItemId: menuItemId},
        url: "/cart/decrease",
    }).then(function (response) {
        $('#reservation-component').html(response);
    });
}