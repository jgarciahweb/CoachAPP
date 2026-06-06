package com.coachapp.backend.application.validation.auth;

import com.coachapp.backend.application.command.auth.LoginCommand;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LoginConstraintChecker {

    public Mono<LoginCommand> check(LoginCommand command) {

        if (command.getEmail() == null || command.getEmail().isBlank()) {
            return Mono.error(new IllegalArgumentException("Email is required"));
        }

        if (command.getPassword() == null || command.getPassword().isBlank()) {
            return Mono.error(new IllegalArgumentException("Password is required"));
        }

        return Mono.just(command);
    }
}