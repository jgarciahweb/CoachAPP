package com.coachapp.backend.application.validation.player;

import com.coachapp.backend.application.command.player.DeletePlayerCommand;
import com.coachapp.backend.domain.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeletePlayerConstraintChecker {

    private final PlayerRepository playerRepository;

    public Mono<DeletePlayerCommand> check(DeletePlayerCommand command) {

        if (command.getPlayerId() == null || command.getPlayerId().isBlank()) {
            return Mono.error(new IllegalArgumentException("Player id is required"));
        }

        return playerRepository.findById(command.getPlayerId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Player not found")))
                .thenReturn(command);
    }
}