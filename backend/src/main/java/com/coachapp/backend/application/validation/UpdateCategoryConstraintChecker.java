package com.coachapp.backend.application.validation;

import com.coachapp.backend.application.command.team.UpdateCategoryCommand;
import com.coachapp.backend.domain.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpdateCategoryConstraintChecker {

    private final TeamRepository teamRepository;

    public Mono<UpdateCategoryCommand> check(UpdateCategoryCommand command) {

        if (command.getName() == null || command.getName().isBlank()) {
            return Mono.error(new IllegalArgumentException("Category name is required"));
        }

        return teamRepository.findById(command.getTeamId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Team not found")))
                .flatMap(team -> {
                    boolean categoryExists = team.getCategories().stream()
                                    .anyMatch(category -> category.getId().equals(command.getCategoryId()));

                    if (!categoryExists) {
                        return Mono.error(new IllegalArgumentException("Category not found"));
                    }

                    boolean duplicated = team.getCategories().stream()
                                    .anyMatch(category -> category.getName().equals(command.getName())
                                                    && team.getId().equals(command.getTeamId()));

                    if (duplicated) {
                        return Mono.error(new IllegalArgumentException("Category already exists"));
                    }

                    return Mono.just(command);
                });
    }
}