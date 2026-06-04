package com.coachapp.backend.infrastructure.repositories;

import com.coachapp.backend.infrastructure.documents.PlayerDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PlayerMongoRepository extends ReactiveMongoRepository<PlayerDocument, String> {

    Mono<PlayerDocument> findByCategoryIdAndDorsal(String categoryId, Integer dorsal);
}