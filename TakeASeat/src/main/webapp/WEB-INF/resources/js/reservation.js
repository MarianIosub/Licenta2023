let paymentWindow;

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

function redirectToCheckout() {
    const dualScreenLeft = window.screenLeft !== undefined ? window.screenLeft : window.screenX;
    const dualScreenTop = window.screenTop !== undefined ? window.screenTop : window.screenY;

    const width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    const height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    const systemZoom = width / window.screen.availWidth;
    const left = (width - 600) / 2 / systemZoom + dualScreenLeft;
    const top = (height - 500) / 2 / systemZoom + dualScreenTop;
    let params = `scrollbars=no,resizable=no,status=no,location=no,toolbar=no,menubar=no,
                    width=600,height=500,right=${left},top=${top}`;
    window.open("/cart/checkout", "checkout", params);
}
