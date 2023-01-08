package com.takeaseat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.takeaseat.constants.StringConstants.ORDERS;
import static com.takeaseat.constants.StringConstants.ORDER_ID_COLUMN_NAME;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = ORDERS)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ORDER_ID_COLUMN_NAME)
    private Long id;

    @OneToOne
    @NonNull
    private User user;

    @OneToOne
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderEntry> orderEntries = new HashSet<>();

    @NonNull
    private LocalDate date;
    @NonNull
    private Double startingHour;
    @NonNull
    private Double endingHour;
    @NonNull
    private Double totalPrice;
    @NonNull
    private String chargeId;
    @NonNull
    private String cardPaymentNetwork;
    @NonNull
    private Integer cardExpirationMonth;
    @NonNull
    private Integer cardExpirationYear;
    @NonNull
    private String cardLastDigits;
    @NonNull
    private String transactionStatus;
    @NonNull
    private LocalDateTime creationDate = LocalDateTime.now();

    private Boolean approved = null;
    private String message;
    private boolean reviewed = false;
}
