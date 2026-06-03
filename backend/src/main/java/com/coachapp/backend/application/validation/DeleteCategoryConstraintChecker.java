package com.coachapp.backend.application.validation;

import com.coachapp.backend.application.command.team.DeleteCategoryCommand;
import com.coachapp.backend.domain.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeleteCategoryConstraintChecker {

    private final TeamRepository teamRepository;

    public Mono<DeleteCategoryCommand> check(DeleteCategoryCommand command) {

        return teamRepository.findById(command.getTeamId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Team not found")))
                .flatMap(team -> {
                    boolean exists = team.getCategories()
                                    .stream()
                                    .anyMatch(category -> category.getId().equals(command.getCategoryId()));

                    if (!exists) {
                        return Mono.error(new IllegalArgumentException("Category not found"));
                    }

                    return Mono.just(command);
                });
    }
}