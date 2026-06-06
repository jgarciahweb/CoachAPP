package com.coachapp.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private RoleEnum role;
}
