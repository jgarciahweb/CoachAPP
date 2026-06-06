package com.coachapp.backend.infrastructure.repositories;

import com.coachapp.backend.infrastructure.documents.UserDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserMongoRepository extends ReactiveMongoRepository<UserDocument, String> {

    Mono<UserDocument> findByEmailIgnoreCase(String email);

}