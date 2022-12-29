package com.takeaseat.controller.dto;

import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;
import com.takeaseat.model.User;

import java.util.Date;
import java.util.Map;

public class Cart {
    private Date date;
    private Double startingHour;
    private Double endingHour;
    private User user;
    private Restaurant restaurant;
    private Map<MenuItem, Integer> menuItems;
    private Double totalPrice;
    private String error;
}
