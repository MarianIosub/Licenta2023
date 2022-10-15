package com.takeaseat.service.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileDTO {
    private String mail;
    private String name;
    private String surname;
    private String password;
}
