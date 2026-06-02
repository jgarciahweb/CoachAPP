package com.coachapp.backend.application.shared;

import reactor.core.publisher.Mono;

public interface CommandHandler<C, R> {
    Mono<R> execute(C command);
}
