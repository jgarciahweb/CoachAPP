package com.coachapp.backend.application.services.player;

import com.coachapp.backend.application.command.player.DeletePlayerCommand;
import com.coachapp.backend.application.shared.CommandHandler;
import com.coachapp.backend.application.validation.player.DeletePlayerConstraintChecker;
import com.coachapp.backend.domain.model.Player;
import com.coachapp.backend.domain.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeletePlayerCommandHandler implements CommandHandler<DeletePlayerCommand, Player> {

    private final PlayerRepository playerRepository;
    private final DeletePlayerConstraintChecker checker;

    @Override
    public Mono<Player> execute(DeletePlayerCommand command) {

        return checker.check(command)
                .flatMap(validCommand -> playerRepository.findById(validCommand.getPlayerId()))
                .flatMap(player -> playerRepository.deleteById(player.getId()).thenReturn(player));
    }
}