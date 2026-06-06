package com.coachapp.backend.application.command.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegisterUserCommand {

    String email;
    String password;
    String firstName;
    String lastName;

}
