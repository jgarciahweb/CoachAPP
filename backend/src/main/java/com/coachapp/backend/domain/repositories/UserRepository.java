package com.coachapp.backend.domain.repositories;

import com.coachapp.backend.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);
    Mono<User> findByEmail(String email);
    Mono<User> findById(String id);

}