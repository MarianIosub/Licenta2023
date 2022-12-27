function searchRestaurantByInput(searchedItem) {
    $.ajax({
        type: "GET",
        data: {searchedItem: searchedItem},
        url: "/restaurant",
    }).then(function (response) {
        $('#restaurants-list').html(response);
    });
}

function getRestaurantsSorted(sortOption, searchedItem) {
    $.ajax({
        type: "GET",
        data: {searchedItem: searchedItem, sortOption: sortOption},
        url: "/restaurant",
    }).then(function (response) {
        $('#restaurants-list').html(response);
    });
}

function sortBy(sortOption) {
    let restaurantSearch = document.getElementById("restaurants-search");
    let searchedItem = restaurantSearch.value;
    getRestaurantsSorted(sortOption, searchedItem);
}

function showRedirectIcon(id) {
    let restaurant = document.getElementById(id);
    let icon = restaurant.getElementsByClassName('restaurant-list-redirect')[0];
    icon.classList.remove('is-hidden');
}

function hideRedirectIcon(id) {
    let restaurant = document.getElementById(id);
    let icon = restaurant.getElementsByClassName('restaurant-list-redirect')[0];
    icon.classList.add('is-hidden');
}

function searchForRestaurants() {
    let restaurantSearch = document.getElementById("restaurants-search");
    let searchedItem = restaurantSearch.value;
    searchRestaurantByInput(searchedItem);
}

function scrollForRestaurants() {
    let restaurantSearchLabel = document.getElementById("restaurants-search");
    restaurantSearchLabel.scrollIntoView({behavior: 'smooth', block: 'start', inline: 'nearest'});
}