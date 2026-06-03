package com.coachapp.backend.application.validation;

import com.coachapp.backend.application.command.team.CreateTeamCommand;
import com.coachapp.backend.domain.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateTeamConstraintChecker {

    private final TeamRepository teamRepository;

    public Mono<CreateTeamCommand> check(CreateTeamCommand command) {

        if (command.getName() == null || command.getName().isBlank()) {
            return Mono.error(new IllegalArgumentException("Team name is required"));
        }

        return teamRepository.findByName(command.getName())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException("Team already exists"));
                    }

                    return Mono.just(command);
                });
    }
}
