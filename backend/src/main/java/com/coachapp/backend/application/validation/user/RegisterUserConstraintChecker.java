package com.coachapp.backend.application.validation.user;

import com.coachapp.backend.application.command.user.RegisterUserCommand;
import com.coachapp.backend.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RegisterUserConstraintChecker {
    private final UserRepository userRepository;

    public Mono<RegisterUserCommand> check(RegisterUserCommand command) {

        if (command.getEmail() == null || command.getEmail().isBlank()) {
            return Mono.error(new IllegalArgumentException("Email is required"));
        }

        if (command.getPassword() == null || command.getPassword().isBlank()) {
            return Mono.error(new IllegalArgumentException("Password is required"));
        }

        if (command.getFirstName() == null || command.getFirstName().isBlank()) {
            return Mono.error(new IllegalArgumentException("First name is required"));
        }

        if (command.getLastName() == null || command.getLastName().isBlank()) {
            return Mono.error(new IllegalArgumentException("Last name is required"));
        }

        return userRepository.findByEmail(command.getEmail())
                .hasElement()
                .flatMap(exists -> {

                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.error(new IllegalArgumentException("User already exists"));
                    }

                    return Mono.just(command);
                });
    }
}
