package com.coachapp.backend.apis.dto.auth;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private String role;
    private String email;
    private String firstName;
}