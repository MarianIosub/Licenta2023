package com.takeaseat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static com.takeaseat.constants.StringConstants.RESTAURANTS;
import static com.takeaseat.constants.StringConstants.RESTAURANT_ID_COLUMN_NAME;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = RESTAURANTS)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = RESTAURANT_ID_COLUMN_NAME)
    private Long id;

    @OneToOne
    private User administrator;

    @NonNull
    @Column(columnDefinition = "LONGBLOB")
    private String image;
    @NonNull
    private String name;
    @NonNull
    @Column(columnDefinition = "TEXT")
    private String description;
    @NonNull
    private String city;
    @NonNull
    private String address;
    @NonNull
    private String mail;
    @NonNull
    private String phoneNumber;
    @NonNull
    private Double openingHour;
    @NonNull
    private Double closingHour;
    @NonNull
    private Double priceRequired;

    private Integer noOfReservations = 0;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MenuItem> menuItems;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    private Double rating = 0.0;

    public void addReview(final Review review) {
        reviews.add(review);
    }
}
