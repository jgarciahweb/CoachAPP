package com.coachapp.backend.apis.dto.user;

import lombok.Data;

@Data
public class RegisterUserRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
