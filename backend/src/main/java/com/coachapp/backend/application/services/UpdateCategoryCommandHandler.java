package com.coachapp.backend.application.services;

import com.coachapp.backend.application.command.team.UpdateCategoryCommand;
import com.coachapp.backend.application.shared.CommandHandler;
import com.coachapp.backend.application.validation.UpdateCategoryConstraintChecker;
import com.coachapp.backend.domain.model.Category;
import com.coachapp.backend.domain.model.Team;
import com.coachapp.backend.domain.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateCategoryCommandHandler implements CommandHandler<UpdateCategoryCommand, Team> {

    private final TeamRepository teamRepository;
    private final UpdateCategoryConstraintChecker checker;

    @Override
    public Mono<Team> execute(UpdateCategoryCommand command) {

        return checker.check(command)
                .flatMap(validCommand -> teamRepository.findById(validCommand.getTeamId()))
                .map(team -> {
                    List<Category> categories = team.getCategories().stream()
                                    .map(category -> {
                                        if (category.getId().equals(command.getCategoryId())) {
                                            return new Category(category.getId(), command.getName());
                                        }

                                        return category;
                                    })
                                    .toList();

                    return new Team(team.getId(), team.getName(), categories);
                })
                .flatMap(teamRepository::save);
    }
}
