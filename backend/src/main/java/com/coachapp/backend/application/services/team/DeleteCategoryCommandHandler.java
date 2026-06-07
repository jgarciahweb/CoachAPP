package com.coachapp.backend.application.services.team;

import com.coachapp.backend.application.command.team.DeleteCategoryCommand;
import com.coachapp.backend.application.shared.CommandHandler;
import com.coachapp.backend.application.validation.team.DeleteCategoryConstraintChecker;
import com.coachapp.backend.domain.model.Category;
import com.coachapp.backend.domain.model.Team;
import com.coachapp.backend.domain.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteCategoryCommandHandler implements CommandHandler<DeleteCategoryCommand, Team> {

    private final TeamRepository teamRepository;
    private final DeleteCategoryConstraintChecker checker;

    @Override
    public Mono<Team> execute(DeleteCategoryCommand command) {

        return checker.check(command)
                .flatMap(validCommand -> teamRepository.findById(validCommand.getTeamId()))
                .map(team -> {
                    List<Category> categories = team.getCategories()
                                    .stream()
                                    .filter(category -> !category.getId().equals(command.getCategoryId()))
                                    .toList();

                    return new Team(team.getId(), team.getName(), categories);
                })
                .flatMap(teamRepository::save);
    }
}
