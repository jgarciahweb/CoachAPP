package com.coachapp.backend.domain.repositories;

import com.coachapp.backend.domain.model.Team;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeamRepository {

    Mono<Team> save(Team team);
    Flux<Team> findAll();
    Mono<Team> findById(String id);
}