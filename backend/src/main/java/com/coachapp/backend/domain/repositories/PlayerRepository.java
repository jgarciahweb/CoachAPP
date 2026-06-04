package com.coachapp.backend.domain.repositories;

import com.coachapp.backend.domain.model.Player;
import reactor.core.publisher.Mono;

public interface PlayerRepository {

    Mono<Player> save(Player player);
    Mono<Player> findByCategoryIdAndDorsal(String categoryId, Integer dorsal);

}