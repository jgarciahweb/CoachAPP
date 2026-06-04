package com.coachapp.backend.application.services.player;

import com.coachapp.backend.application.command.player.CreatePlayerCommand;
import com.coachapp.backend.application.shared.CommandHandler;
import com.coachapp.backend.application.validation.player.CreatePlayerConstraintChecker;
import com.coachapp.backend.domain.mappers.PlayerMapper;
import com.coachapp.backend.domain.model.Player;
import com.coachapp.backend.domain.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreatePlayerCommandHandler implements CommandHandler<CreatePlayerCommand, Player> {

    private final CreatePlayerConstraintChecker checker;
    private final PlayerMapper playerMapper;
    private final PlayerRepository playerRepository;

    @Override
    public Mono<Player> execute(CreatePlayerCommand command) {

        return checker.check(command)
                .map(playerMapper::toDomain)
                .flatMap(playerRepository::save);
    }
}