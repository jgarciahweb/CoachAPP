package com.coachapp.backend.infrastructure.repositories;

import com.coachapp.backend.domain.model.User;
import com.coachapp.backend.domain.repositories.UserRepository;
import com.coachapp.backend.infrastructure.mappers.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserMongoRepository repository;
    private final UserPersistenceMapper mapper;

    @Override
    public Mono<User> save(User user) {

        return Mono.just(user)
                .map(mapper::toDocument)
                .flatMap(repository::save)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<User> findByEmail(String email) {

        return repository.findByEmailIgnoreCase(email)
                .map(mapper::toDomain);
    }
}