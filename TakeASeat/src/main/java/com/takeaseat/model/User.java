package com.takeaseat.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
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
