package com.coachapp.backend.infrastructure.repositories;

import com.coachapp.backend.domain.model.Player;
import com.coachapp.backend.domain.repositories.PlayerRepository;
import com.coachapp.backend.infrastructure.mappers.PlayerPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PlayerRepositoryAdapter implements PlayerRepository {

    private final PlayerMongoRepository repository;
    private final PlayerPersistenceMapper mapper;

    @Override
    public Mono<Player> save(Player player) {

        return Mono.just(player)
                .map(mapper::toDocument)
                .flatMap(repository::save)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Player> findByCategoryIdAndDorsal(String categoryId, Integer dorsal) {
        return repository
                .findByCategoryIdAndDorsal(categoryId, dorsal)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Player> findById(String id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}