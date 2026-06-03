package com.coachapp.backend.apis.controller;

import com.coachapp.backend.apis.dto.team.*;
import com.coachapp.backend.apis.mapper.TeamApiMapper;
import com.coachapp.backend.application.queries.GetCategoriesQuery;
import com.coachapp.backend.application.queries.GetTeamsQuery;
import com.coachapp.backend.application.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TeamController {
    private final CreateTeamCommandHandler createTeamCommandHandler;
    private final AddCategoryCommandHandler addCategoryCommandHandler;
    private final GetTeamsQueryHandler getTeamsQueryHandler;
    private final GetCategoriesQueryHandler getCategoriesQueryHandler;
    private final DeleteCategoryCommandHandler deleteCategoryCommandHandler;
    private final UpdateCategoryCommandHandler updateCategoryCommandHandler;
    private final TeamApiMapper teamApiMapper;

    @PostMapping("/teams")
    public Mono<CreateTeamResponseDTO> create(@RequestBody CreateTeamRequestDTO request) {

        return Mono.just(request)
                .map(teamApiMapper::toCommand)
                .flatMap(createTeamCommandHandler::execute)
                .map(teamApiMapper::toCreateResponse);
    }

    @GetMapping("/teams")
    public Flux<TeamResponseDTO> getAll() {
        return getTeamsQueryHandler.execute(new GetTeamsQuery()).map(teamApiMapper::toResponse);
    }

    @PostMapping("/categories")
    public Mono<TeamResponseDTO> addCategory(@RequestBody AddCategoryRequestDTO request) {

        return Mono.just(request)
                .map(teamApiMapper::toCommand)
                .flatMap(addCategoryCommandHandler::execute)
                .map(teamApiMapper::toResponse);
    }

    @GetMapping("/categories/{teamId}")
    public Flux<CategoryResponseDTO> getCategories(@PathVariable String teamId) {
        return getCategoriesQueryHandler
                .execute(new GetCategoriesQuery(teamId))
                .map(teamApiMapper::toResponse);
    }

    @PutMapping("/categories")
    public Mono<TeamResponseDTO> updateCategory(@RequestBody UpdateCategoryRequestDTO request) {

        return Mono.just(request)
                .map(teamApiMapper::toCommand)
                .flatMap(updateCategoryCommandHandler::execute)
                .map(teamApiMapper::toResponse);
    }

    @DeleteMapping("/categories")
    public Mono<TeamResponseDTO> deleteCategory(@RequestBody DeleteCategoryRequestDTO request) {

        return Mono.just(request)
                .map(teamApiMapper::toCommand)
                .flatMap(deleteCategoryCommandHandler::execute)
                .map(teamApiMapper::toResponse);
    }
}
