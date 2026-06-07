package com.coachapp.backend.domain.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
public class User {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private RoleEnum role;
    private String avatarUrl;
}
