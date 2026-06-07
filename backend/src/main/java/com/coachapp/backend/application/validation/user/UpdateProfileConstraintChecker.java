package com.coachapp.backend.application.validation.user;

import com.coachapp.backend.application.command.user.UpdateProfileCommand;
import com.coachapp.backend.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpdateProfileConstraintChecker {

    private final UserRepository userRepository;

    public Mono<UpdateProfileCommand> check(UpdateProfileCommand command) {

        if (command.getFirstName() == null || command.getFirstName().isBlank()) {
            return Mono.error(new IllegalArgumentException("First name is required")
            );
        }

        if (command.getLastName() == null || command.getLastName().isBlank()) {
            return Mono.error(new IllegalArgumentException("Last name is required"));
        }

        if (command.getEmail() == null || command.getEmail().isBlank()) {
            return Mono.error(new IllegalArgumentException("Email is required"));
        }

        return userRepository.findByEmail(command.getEmail())
                .filter(user -> !user.getId().equals(command.getUserId()))
                .hasElement()
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.error(new IllegalArgumentException("Email already exists"));
                    }

                    return Mono.just(command);
                });
    }
}