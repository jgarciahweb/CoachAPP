package com.coachapp.backend.infrastructure.repositories;

import com.coachapp.backend.domain.model.Team;
import com.coachapp.backend.domain.repositories.TeamRepository;
import com.coachapp.backend.infrastructure.mappers.TeamPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TeamRepositoryAdapter implements TeamRepository {

    private final TeamMongoRepository repository;
    private final TeamPersistenceMapper mapper;

    @Override
    public Mono<Team> save(Team team) {

        return Mono.just(team)
                .map(mapper::toDocument)
                .flatMap(repository::save)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Team> findAll() {
        return repository.findAll()
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Team> findById(String id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Team> findByName(String name) {
        return repository.findByNameIgnoreCase(name)
                .map(mapper::toDomain);
    }
}