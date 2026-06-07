package com.coachapp.backend.application.services.team;

import com.coachapp.backend.application.queries.GetCategoriesQuery;
import com.coachapp.backend.application.shared.QueryHandler;
import com.coachapp.backend.domain.model.Category;
import com.coachapp.backend.domain.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetCategoriesQueryHandler implements QueryHandler<GetCategoriesQuery, Category> {

    private final TeamRepository teamRepository;

    @Override
    public Flux<Category> execute(GetCategoriesQuery query) {

        return teamRepository.findById(query.getTeamId())
                .flatMapMany(team -> Flux.fromIterable(team.getCategories()));
    }
}