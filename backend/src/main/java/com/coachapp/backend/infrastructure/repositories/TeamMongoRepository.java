package com.coachapp.backend.infrastructure.repositories;

import com.coachapp.backend.infrastructure.documents.TeamDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TeamMongoRepository extends ReactiveMongoRepository<TeamDocument, String> {
    Mono<TeamDocument> findByNameIgnoreCase(String name);
}