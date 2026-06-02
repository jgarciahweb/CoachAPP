package com.coachapp.backend.application.services;

import com.coachapp.backend.application.queries.GetTeamsQuery;
import com.coachapp.backend.application.shared.QueryHandler;
import com.coachapp.backend.domain.model.Team;
import com.coachapp.backend.domain.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetTeamsQueryHandler implements QueryHandler<GetTeamsQuery, Team> {

    private final TeamRepository teamRepository;

    @Override
    public Flux<Team> execute(GetTeamsQuery query) {
        return teamRepository.findAll();
    }
}