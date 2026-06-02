package com.coachapp.backend.apis.controller;

import com.coachapp.backend.application.queries.GetHealthQuery;
import com.coachapp.backend.application.services.GetHealthQueryHandler;
import com.coachapp.backend.domain.model.Health;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class HealthController {

    private final GetHealthQueryHandler handler;

    @GetMapping("/api/health")
    public Mono<Health> health() {

        return handler.handle(
                new GetHealthQuery()
        );
    }
}