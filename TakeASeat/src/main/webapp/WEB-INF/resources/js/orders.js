function showOrdersByStatus(status, element) {
    showOrders(status);
    changeButtonsAvailability(element);
}

function changeButtonsAvailability(buttonId) {
    let button = document.getElementById(buttonId);
    let buttons = document.getElementsByClassName('order-status-btn');
    for (const btn of buttons) {
        btn.disabled = false;
    }
    button.disabled = true;
}

function showApprovalButtonsForOrder(message, id) {
    let orderId = id.split('-').pop();
    let acceptBtn = document.getElementById('accept-' + orderId);
    let refuseBtn = document.getElementById('refuse-' + orderId);
    if (message.length > 0) {
        acceptBtn.disabled = false;
        refuseBtn.disabled = false;
    } else {
        acceptBtn.disabled = true;
        refuseBtn.disabled = true;
    }
}

function acceptOrder(id) {
    if (document.getElementById(id).disabled === true) {
        return false;
    }
    let orderId = id.split('-').pop();
    let message = document.getElementById('message-' + orderId).value;
    $.ajax({
        type: "POST",
        data: {orderId: orderId, message: message},
        url: '/order/reservations/accept',
    }).then(function (response) {
        $('#reservationsList').html(response);
    });
}

function refuseOrder(id) {
    if (document.getElementById(id).disabled === true) {
        return false;
    }
    let orderId = id.split('-').pop();
    let message = document.getElementById('message-' + orderId).value;
    $.ajax({
        type: "POST",
        data: {orderId: orderId, message: message},
        url: '/order/reservations/refuse',
    }).then(function (response) {
        $('#reservationsList').html(response);
    });
}

function showOrders(status) {
    $.ajax({
        type: "GET",
        data: {orderStatus: status},
        url: '/order/reservations/filter',
    }).then(function (response) {
        $('#reservationsList').html(response);
    });
}