function searchMenuItemByInput(searchedItem) {
    $.ajax({
        type: "GET",
        data: {searchedItem: searchedItem},
        url: "/restaurant/menu-item",
    }).then(function (response) {
        $('#menuItems').html(response);
    });
}

function searchForMenuItem() {
    let menuItemSearch = document.getElementById("menu-items-search");
    let searchedItem = menuItemSearch.value;
    searchMenuItemByInput(searchedItem);
}

function scrollForMenuItems() {
    let menuItemSearchLabel = document.getElementById("menu-item-search-label");
    menuItemSearchLabel.scrollIntoView({behavior: 'smooth', block: 'start', inline: 'nearest'});
}