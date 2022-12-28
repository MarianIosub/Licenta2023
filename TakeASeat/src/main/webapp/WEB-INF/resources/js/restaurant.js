function searchForMenuItemByRestaurant(searchedItem, restaurantId) {
    $.ajax({
        type: "GET",
        data: {searchedItem: searchedItem},
        url: '/restaurant/' + restaurantId + '/menuItems',
    }).then(function (response) {
        $('#restaurant-menu-items').html(response);
    });
}

function sortMenuItemsByRestaurant(searchedItem, restaurantId, sortOption) {
    $.ajax({
        type: "GET",
        data: {searchedItem: searchedItem, sortOption: sortOption},
        url: '/restaurant/' + restaurantId + '/menuItems',
    }).then(function (response) {
        $('#restaurant-menu-items').html(response);
    });
}

function sortMenuItemsBy(sortOption) {
    let restaurantSearch = document.getElementById("restaurant-menu-items-search");
    let searchedItem = restaurantSearch.value;
    let restaurantId = window.location.href.split('/').pop();
    sortMenuItemsByRestaurant(searchedItem, restaurantId, sortOption);
}

function searchForRestaurantMenuItems() {
    let search = document.getElementById("restaurant-menu-items-search");
    let searchedItem = search.value;
    let restaurantId = window.location.href.split('/').pop();
    searchForMenuItemByRestaurant(searchedItem, restaurantId);
}

function scrollForRestaurantMenuItems() {
    let search = document.getElementById("restaurant-menu-items-search");
    search.scrollIntoView({behavior: 'smooth', block: 'start', inline: 'nearest'});
}