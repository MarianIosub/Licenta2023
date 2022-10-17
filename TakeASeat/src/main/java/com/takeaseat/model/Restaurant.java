package com.takeaseat.model;

import lombok.*;

import javax.persistence.*;

import static com.takeaseat.constants.StringConstants.RESTAURANTS;
import static com.takeaseat.constants.StringConstants.USER_ID_COLUMN_NAME;

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
    @Column(name = USER_ID_COLUMN_NAME)
    private Long id;

    @OneToOne
    private User administrator;

    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String email;
    @NonNull
    private String phoneNumber;
    @NonNull
    private Double openingHour;
    @NonNull
    private Double closingHour;
    @NonNull
    private Double priceRequired;
}
