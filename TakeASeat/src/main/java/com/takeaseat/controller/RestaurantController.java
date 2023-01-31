package com.takeaseat.controller;

import com.takeaseat.controller.dto.Cart;
import com.takeaseat.controller.form.CreateRestaurantForm;
import com.takeaseat.controller.validator.CreateRestaurantFormValidator;
import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;
import com.takeaseat.service.RestaurantService;
import com.takeaseat.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import static com.takeaseat.constants.EndpointsConstants.ALL;
import static com.takeaseat.constants.EndpointsConstants.CREATE_RESTAURANT_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.MANAGE_RESTAURANT_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.MENU_ITEM;
import static com.takeaseat.constants.EndpointsConstants.MENU_ITEM_AVAILABILITY;
import static com.takeaseat.constants.EndpointsConstants.MENU_ITEM_DELETE;
import static com.takeaseat.constants.EndpointsConstants.REDIRECT;
import static com.takeaseat.constants.EndpointsConstants.RESTAURANT_ENDPOINT;
import static com.takeaseat.constants.MessagePropertiesConstants.MENU_ITEM_ADDED_MESSAGE;
import static com.takeaseat.constants.MessagePropertiesConstants.MENU_ITEM_AVAILABILITY_MESSAGE;
import static com.takeaseat.constants.MessagePropertiesConstants.MENU_ITEM_DELETED_MESSAGE;
import static com.takeaseat.constants.MessagePropertiesConstants.RESTAURANT_ALREADY_CREATED_MESSAGE;
import static com.takeaseat.constants.MessagePropertiesConstants.RESTAURANT_NOT_CREATED_MESSAGE;
import static com.takeaseat.constants.StringConstants.CART;
import static com.takeaseat.constants.StringConstants.CREATE_RESTAURANT_FORM;
import static com.takeaseat.constants.StringConstants.CURRENT_RESTAURANT;
import static com.takeaseat.constants.StringConstants.CURRENT_USER;
import static com.takeaseat.constants.StringConstants.FLASH_MESSAGE;
import static com.takeaseat.constants.StringConstants.HAS_AVAILABLE_ITEMS;
import static com.takeaseat.constants.StringConstants.HAS_UNAVAILABLE_ITEMS;
import static com.takeaseat.constants.StringConstants.INGREDIENTS;
import static com.takeaseat.constants.StringConstants.MENU_ITEMS;
import static com.takeaseat.constants.StringConstants.MENU_ITEM_ADDED;
import static com.takeaseat.constants.StringConstants.MENU_ITEM_ID;
import static com.takeaseat.constants.StringConstants.NAME;
import static com.takeaseat.constants.StringConstants.PHOTO_LINK;
import static com.takeaseat.constants.StringConstants.PRICE;
import static com.takeaseat.constants.StringConstants.RESTAURANTS;
import static com.takeaseat.constants.StringConstants.SEARCHED_ITEM;
import static com.takeaseat.constants.StringConstants.SORT_OPTION;
import static com.takeaseat.constants.ViewsConstants.CREATE_RESTAURANT_PAGE;
import static com.takeaseat.constants.ViewsConstants.ERROR_404;
import static com.takeaseat.constants.ViewsConstants.MANAGE_RESTAURANT_PAGE;
import static com.takeaseat.constants.ViewsConstants.RESTAURANTS_LIST;
import static com.takeaseat.constants.ViewsConstants.RESTAURANTS_PAGE;
import static com.takeaseat.constants.ViewsConstants.RESTAURANT_MENU_ITEMS;
import static com.takeaseat.constants.ViewsConstants.RESTAURANT_PAGE;
import static com.takeaseat.helper.CreateEndpointHelper.createEndpoint;

@Controller
@RequestMapping(RESTAURANT_ENDPOINT)
@AllArgsConstructor
public class RestaurantController {

    private final UserService userService;
    private final CreateRestaurantFormValidator createRestaurantFormValidator;
    private final RestaurantService restaurantService;

    @InitBinder(CREATE_RESTAURANT_FORM)
    protected void initBinderRegister(WebDataBinder binder) {
        binder.setValidator(getCreateRestaurantFormValidator());
    }

    @RequestMapping(value = ALL, method = RequestMethod.GET)
    public String getRestaurantPage(Model model) {
        model.addAttribute(RESTAURANTS, getRestaurantService().getAllRestaurants());

        return RESTAURANTS_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String searchForRestaurant(@RequestParam(value = SEARCHED_ITEM) String searchedItem,
                                      @RequestParam(value = SORT_OPTION, required = false) String sortOption,
                                      Model model) {
        final List<Restaurant> restaurants = getRestaurantService().searchForRestaurant(searchedItem, sortOption);
        model.addAttribute(RESTAURANTS, restaurants);

        return RESTAURANTS_LIST;
    }

    @RequestMapping(value = "/{restaurantId}", method = RequestMethod.GET)
    public String getRestaurantPage(@PathVariable String restaurantId, Model model, HttpSession session) {
        Restaurant restaurant = getRestaurantService().getRestaurantById(restaurantId);
        if (restaurant == null) {
            return ERROR_404;
        }
        setCartInSession(restaurant, session);
        model.addAttribute(CURRENT_RESTAURANT, restaurant);
        model.addAttribute(CURRENT_USER, getUserService().getCurrentUser());
        model.addAttribute(HAS_AVAILABLE_ITEMS, getRestaurantService().hasAvailableItems(restaurant.getMenuItems()));
        model.addAttribute(HAS_UNAVAILABLE_ITEMS, getRestaurantService().hasUnavailableItems(restaurant.getMenuItems()));
        return RESTAURANT_PAGE;
    }

    @RequestMapping(value = "/{restaurantId}/menuItems", method = RequestMethod.GET)
    public String getMenuItemsByRestaurant(@PathVariable String restaurantId,
                                           @RequestParam(value = SEARCHED_ITEM) String searchedItem,
                                           @RequestParam(value = SORT_OPTION, required = false) String sortOption,
                                           Model model) {

        Set<MenuItem> menuItems = getRestaurantService().getMenuItemsForRestaurant(restaurantId, searchedItem, sortOption);
        model.addAttribute(MENU_ITEMS, menuItems);
        model.addAttribute(HAS_AVAILABLE_ITEMS, getRestaurantService().hasAvailableItems(menuItems));
        model.addAttribute(HAS_UNAVAILABLE_ITEMS, getRestaurantService().hasUnavailableItems(menuItems));
        return RESTAURANT_MENU_ITEMS;
    }

    @RequestMapping(value = CREATE_RESTAURANT_ENDPOINT, method = RequestMethod.GET)
    public String getNewRestaurantForm(@ModelAttribute(CREATE_RESTAURANT_FORM) CreateRestaurantForm createRestaurantForm,
                                       Model model, RedirectAttributes redirectAttributes) {
        if (getRestaurantService().hasCurrentUserRestaurantCreated()) {
            redirectAttributes.addFlashAttribute(FLASH_MESSAGE, RESTAURANT_ALREADY_CREATED_MESSAGE);
            return createEndpoint(REDIRECT, RESTAURANT_ENDPOINT, MANAGE_RESTAURANT_ENDPOINT);
        }

        createRestaurantForm.setMail(getUserService().getCurrentUser().getMail());

        return CREATE_RESTAURANT_PAGE;
    }

    //TODO to validate the form
    @RequestMapping(value = CREATE_RESTAURANT_ENDPOINT, method = RequestMethod.POST)
    public String createRestaurant(@ModelAttribute(CREATE_RESTAURANT_FORM) @Validated CreateRestaurantForm createRestaurantForm,
                                   BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return CREATE_RESTAURANT_PAGE;
        }

        getRestaurantService().saveRestaurant(createRestaurantForm);

        return createEndpoint(REDIRECT, RESTAURANT_ENDPOINT, MANAGE_RESTAURANT_ENDPOINT);
    }

    @RequestMapping(value = MANAGE_RESTAURANT_ENDPOINT, method = RequestMethod.GET)
    public String getManageRestaurantPage(Model model, RedirectAttributes redirectAttributes) {
        if (!getRestaurantService().hasCurrentUserRestaurantCreated()) {
            redirectAttributes.addFlashAttribute(FLASH_MESSAGE, RESTAURANT_NOT_CREATED_MESSAGE);
            return createEndpoint(REDIRECT, RESTAURANT_ENDPOINT, CREATE_RESTAURANT_ENDPOINT);
        }

        model.addAttribute(CURRENT_RESTAURANT, getRestaurantService().getCurrentUserRestaurant());

        return MANAGE_RESTAURANT_PAGE;
    }


    @RequestMapping(value = MENU_ITEM, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addMenuItemToRestaurant(@RequestParam(value = NAME) String name,
                                          @RequestParam(value = INGREDIENTS) String ingredients,
                                          @RequestParam(value = PRICE) String price,
                                          @RequestParam(value = PHOTO_LINK) MultipartFile photo,
                                          Model model) throws IOException {
        final MenuItem menuItem = createMenuItemFromRequest(name, ingredients, price, photo);

        final Set<MenuItem> menuItems = getRestaurantService().addMenuItemToRestaurant(restaurantService.getCurrentUserRestaurant(), menuItem);

        model.addAttribute(MENU_ITEMS, menuItems);

        model.addAttribute(MENU_ITEM_ADDED, MENU_ITEM_ADDED_MESSAGE);
        return MENU_ITEMS;
    }

    @RequestMapping(value = MENU_ITEM, method = RequestMethod.GET)
    public String searchForMenuItems(@RequestParam(value = SEARCHED_ITEM) String searchedItem, Model model) {
        final Set<MenuItem> menuItems = getRestaurantService().searchForMenuItems(searchedItem);
        model.addAttribute(MENU_ITEMS, menuItems);

        return MENU_ITEMS;
    }

    @RequestMapping(value = MENU_ITEM_AVAILABILITY, method = RequestMethod.POST)
    public String changeMenuItemAvailability(@PathVariable(value = MENU_ITEM_ID) String menuItem, Model model) {
        getRestaurantService().changeMenuItemAvailability(Long.parseLong(menuItem));

        model.addAttribute(MENU_ITEMS, getRestaurantService().getCurrentUserRestaurant().getMenuItems());

        model.addAttribute(MENU_ITEM_ADDED, MENU_ITEM_AVAILABILITY_MESSAGE);
        return MENU_ITEMS;
    }

    @RequestMapping(value = MENU_ITEM_DELETE, method = RequestMethod.POST)
    public String deleteMenuItem(@PathVariable(value = MENU_ITEM_ID) String menuItem, Model model) {
        getRestaurantService().deleteMenuItem(Long.parseLong(menuItem));

        model.addAttribute(MENU_ITEMS, getRestaurantService().getCurrentUserRestaurant().getMenuItems());
        model.addAttribute(MENU_ITEM_ADDED, MENU_ITEM_DELETED_MESSAGE);
        return MENU_ITEMS;
    }

    private MenuItem createMenuItemFromRequest(String name, String ingredients, String price, MultipartFile photo) throws IOException {
        byte[] encodeBase64 = Base64.getEncoder().encode(photo.getBytes());
        return new MenuItem(name, ingredients, Double.valueOf(price), new String(encodeBase64, StandardCharsets.UTF_8));
    }

    private void setCartInSession(Restaurant restaurant, HttpSession session) {
        if (session.getAttribute(CART) == null ||
                !((Cart) session.getAttribute(CART)).getRestaurant().getId().equals(restaurant.getId())) {
            Cart cart = new Cart();
            cart.setUser(getUserService().getCurrentUser());
            cart.setRestaurant(restaurant);
            cart.setTotalPrice(restaurant.getPriceRequired());
            session.setAttribute(CART, cart);
        }
    }

    protected UserService getUserService() {
        return userService;
    }

    protected CreateRestaurantFormValidator getCreateRestaurantFormValidator() {
        return createRestaurantFormValidator;
    }

    protected RestaurantService getRestaurantService() {
        return restaurantService;
    }
}
