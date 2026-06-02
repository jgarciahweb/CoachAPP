package com.coachapp.backend.apis.controller;

import com.coachapp.backend.apis.dto.team.CreateTeamRequestDTO;
import com.coachapp.backend.apis.dto.team.CreateTeamResponseDTO;
import com.coachapp.backend.apis.dto.team.TeamResponseDTO;
import com.coachapp.backend.apis.mapper.TeamApiMapper;
import com.coachapp.backend.application.queries.GetTeamsQuery;
import com.coachapp.backend.application.services.CreateTeamCommandHandler;
import com.coachapp.backend.application.services.GetTeamsQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TeamController {
    private final CreateTeamCommandHandler createTeamCommandHandler;
    private final GetTeamsQueryHandler getTeamsQueryHandler;
    private final TeamApiMapper teamApiMapper;

    @PostMapping("/teams")
    public Mono<CreateTeamResponseDTO> create(@RequestBody CreateTeamRequestDTO request) {

        return Mono.just(request)
                .map(teamApiMapper::toCommand)
                .flatMap(createTeamCommandHandler::execute)
                .map(teamApiMapper::toCreateResponse);
    }

    @GetMapping("/teams")
    public Flux<TeamResponseDTO> getAll() {
        return getTeamsQueryHandler.execute(new GetTeamsQuery()).map(teamApiMapper::toResponse);
    }
}
