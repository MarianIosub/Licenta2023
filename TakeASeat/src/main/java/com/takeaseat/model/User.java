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
import java.time.LocalDateTime;

import static com.takeaseat.constants.StringConstants.USERS;
import static com.takeaseat.constants.StringConstants.USER_ID_COLUMN_NAME;

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

    private LocalDateTime lastLoginDate;

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && this.getMail().equals(((User) obj).getMail());
    }
}
