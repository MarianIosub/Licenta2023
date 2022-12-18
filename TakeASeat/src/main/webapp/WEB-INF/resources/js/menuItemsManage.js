function displayManageButtons(id) {
    let menuItem = document.getElementById(id);
    let manageButtons = menuItem.getElementsByClassName("menu-item-manage")[0];
    manageButtons.classList.remove('is-hidden');
}

function hideManageButton(id) {
    let menuItem = document.getElementById(id);
    let manageButtons = menuItem.getElementsByClassName("menu-item-manage")[0];
    manageButtons.classList.add('is-hidden');
}

function changeMenuItemAvailability(id) {
    let menuItemId = id.split('-')[1];
    let menuItemSearch = document.getElementById("menu-items-search");

    $.ajax({
        type: "POST",
        processData: false,
        contentType: false,
        url: "/restaurant/menu-item/availability/" + menuItemId,
    }).then(function (response) {
        if (menuItemSearch.value !== '') {
            searchForMenuItem();
        } else {
            $('#menuItems').html(response);
        }
    });
}

function deleteMenuItem(id) {
    let menuItemId = id.split('-')[1];
    let menuItemSearch = document.getElementById("menu-items-search");
    setTimeout(sendDeleteCall(menuItemSearch, menuItemId), 2000);
}

function sendDeleteCall(menuItemSearch, menuItemId) {
    $.ajax({
        type: "POST",
        processData: false,
        contentType: false,
        url: "/restaurant/menu-item/delete/" + menuItemId,
    }).then(function (response) {
        if (menuItemSearch.value !== '') {
            searchForMenuItem();
        } else {
            $('#menuItems').html(response);
        }
    });
}