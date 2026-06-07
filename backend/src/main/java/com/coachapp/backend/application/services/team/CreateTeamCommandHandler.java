package com.coachapp.backend.application.services.team;

import com.coachapp.backend.application.command.team.CreateTeamCommand;
import com.coachapp.backend.application.shared.CommandHandler;
import com.coachapp.backend.application.validation.team.CreateTeamConstraintChecker;
import com.coachapp.backend.domain.mappers.TeamMapper;
import com.coachapp.backend.domain.model.Team;
import com.coachapp.backend.domain.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateTeamCommandHandler implements CommandHandler<CreateTeamCommand, Team> {

    private final TeamRepository teamRepository;
    private final CreateTeamConstraintChecker createTeamConstraintChecker;
    private final TeamMapper teamMapper;

    @Override
    public Mono<Team> execute(CreateTeamCommand command) {
        return createTeamConstraintChecker.check(command)
                .map(teamMapper::toDomain)
                .flatMap(teamRepository::save);
    }
}