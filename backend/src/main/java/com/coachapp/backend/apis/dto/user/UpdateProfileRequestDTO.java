package com.coachapp.backend.apis.dto.user;

import lombok.Data;

@Data
public class UpdateProfileRequestDTO {
    private String email;
    private String firstName;
    private String lastName;
}
