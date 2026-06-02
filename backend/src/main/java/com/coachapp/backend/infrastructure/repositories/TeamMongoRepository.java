package com.coachapp.backend.infrastructure.repositories;

import com.coachapp.backend.infrastructure.documents.TeamDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TeamMongoRepository extends ReactiveMongoRepository<TeamDocument, String> {
}