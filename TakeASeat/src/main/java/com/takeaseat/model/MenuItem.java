package com.takeaseat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.takeaseat.constants.StringConstants.MENU_ITEMS;
import static com.takeaseat.constants.StringConstants.MENU_ITEM_ID_COLUMN_NAME;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = MENU_ITEMS)
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = MENU_ITEM_ID_COLUMN_NAME)
    private Long id;

    @NonNull
    private String name;
    @NonNull
    @Column(columnDefinition = "TEXT")
    private String ingredients;
    @NonNull
    private Double price;
    @NonNull
    @Column(columnDefinition = "LONGBLOB")
    private String photoLink;
    private Integer noOfOrders = 0;
    private boolean available = true;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public Double getPrice() {
        return price;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public boolean isAvailable() {
        return available;
    }
}
