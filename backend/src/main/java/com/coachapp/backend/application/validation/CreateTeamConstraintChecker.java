package com.coachapp.backend.application.validation;

import com.coachapp.backend.application.command.team.CreateTeamCommand;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateTeamConstraintChecker {
    public Mono<CreateTeamCommand> check(CreateTeamCommand command) {

        if (command.getName() == null || command.getName().isBlank()) {
            return Mono.error(new IllegalArgumentException("Team name is required"));
        }

        return Mono.just(command);
    }
}
