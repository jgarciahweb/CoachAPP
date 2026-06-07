package com.coachapp.backend.application.services.team;

import com.coachapp.backend.application.command.team.AddCategoryCommand;
import com.coachapp.backend.application.shared.CommandHandler;
import com.coachapp.backend.application.validation.team.AddCategoryConstraintChecker;
import com.coachapp.backend.domain.mappers.CategoryMapper;
import com.coachapp.backend.domain.model.Category;
import com.coachapp.backend.domain.model.Team;
import com.coachapp.backend.domain.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddCategoryCommandHandler implements CommandHandler<AddCategoryCommand, Team> {

    private final TeamRepository teamRepository;
    private final AddCategoryConstraintChecker checker;
    private final CategoryMapper categoryMapper;

    @Override
    public Mono<Team> execute(AddCategoryCommand command) {

        return checker.check(command)
                .flatMap(validCommand -> teamRepository.findById(validCommand.getTeamId()))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Team not found")))
                .map(team -> addCategory(team, categoryMapper.toDomain(command)))
                .flatMap(teamRepository::save);
    }

    private Team addCategory(Team team, Category category) {
        List<Category> categories = team.getCategories() == null
                ? new ArrayList<>()
                : new ArrayList<>(team.getCategories());

        categories.add(category);

        return new Team(team.getId(), team.getName(), categories);
    }
}