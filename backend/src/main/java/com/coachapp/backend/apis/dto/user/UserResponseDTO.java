package com.coachapp.backend.apis.dto.user;

import lombok.Data;

@Data
public class UserResponseDTO {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String avatarUrl;
}
