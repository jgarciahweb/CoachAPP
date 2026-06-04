package com.coachapp.backend.application.validation.player;

import com.coachapp.backend.application.command.player.CreatePlayerCommand;
import com.coachapp.backend.domain.repositories.PlayerRepository;
import com.coachapp.backend.domain.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreatePlayerConstraintChecker {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public Mono<CreatePlayerCommand> check(CreatePlayerCommand command) {

        if (command.getFirstName() == null || command.getFirstName().isBlank()) {
            return Mono.error(new IllegalArgumentException("First name is required"));
        }

        if (command.getLastName() == null || command.getLastName().isBlank()) {
            return Mono.error(new IllegalArgumentException("Last name is required"));
        }

        return teamRepository.findByCategoryId(command.getCategoryId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Category not found")))
                .then(playerRepository.findByCategoryIdAndDorsal(command.getCategoryId(), command.getDorsal()))
                .flatMap(player ->
                        Mono.<CreatePlayerCommand>error(new IllegalArgumentException("Dorsal already exists")))
                .switchIfEmpty(Mono.just(command));
    }
}