package com.coachapp.backend.application.services;

import com.coachapp.backend.application.queries.GetHealthQuery;
import com.coachapp.backend.domain.model.Health;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetHealthQueryHandler {

    public Mono<Health> handle(GetHealthQuery query) {

        return Mono.just(
                new Health("UP")
        );
    }
}