package com.takeaseat.model;

import lombok.*;

import javax.persistence.*;

import static com.takeaseat.constants.StringConstants.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = USERS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = USER_ID_COLUMN_NAME)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String mail;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String password;

    @NonNull
    private String role;
}
