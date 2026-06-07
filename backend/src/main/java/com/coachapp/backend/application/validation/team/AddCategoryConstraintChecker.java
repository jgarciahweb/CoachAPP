package com.coachapp.backend.application.validation.team;

import com.coachapp.backend.application.command.team.AddCategoryCommand;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AddCategoryConstraintChecker {
    public Mono<AddCategoryCommand> check(AddCategoryCommand command) {

        if (command.getTeamId() == null || command.getTeamId().isBlank()) {
            return Mono.error(new IllegalArgumentException("Team id is required"));
        }

        if (command.getName() == null || command.getName().isBlank()) {
            return Mono.error(new IllegalArgumentException("Category name is required"));
        }

        return Mono.just(command);
    }
}
