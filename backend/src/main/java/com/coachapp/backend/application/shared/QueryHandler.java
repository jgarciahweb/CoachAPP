package com.coachapp.backend.application.shared;

import reactor.core.publisher.Flux;

public interface QueryHandler<Q, R> {

    Flux<R> execute(Q query);

}