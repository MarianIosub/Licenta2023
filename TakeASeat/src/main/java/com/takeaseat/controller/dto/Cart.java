package com.takeaseat.controller.dto;

import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;
import com.takeaseat.model.User;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class Cart {
    private Date date;
    private Date today = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
    private Double startingHour;
    private Double endingHour;
    private User user;
    private Restaurant restaurant;
    private Map<MenuItem, Integer> menuItems = new HashMap<>();
    private Double totalPrice;
    private String error;
}
