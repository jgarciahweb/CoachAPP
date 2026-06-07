package com.coachapp.backend.application.command.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateProfileCommand {

    String userId;
    String email;
    String firstName;
    String lastName;

}
