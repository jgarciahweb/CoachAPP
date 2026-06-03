package com.coachapp.backend.apis.mapper;

import com.coachapp.backend.apis.dto.team.*;
import com.coachapp.backend.application.command.team.AddCategoryCommand;
import com.coachapp.backend.application.command.team.CreateTeamCommand;
import com.coachapp.backend.application.command.team.DeleteCategoryCommand;
import com.coachapp.backend.application.command.team.UpdateCategoryCommand;
import com.coachapp.backend.domain.model.Category;
import com.coachapp.backend.domain.model.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamApiMapper {

    CreateTeamCommand toCommand(CreateTeamRequestDTO request);
    CreateTeamResponseDTO toCreateResponse(Team team);
    TeamResponseDTO toResponse(Team team);
    AddCategoryCommand toCommand(AddCategoryRequestDTO request);
    CategoryResponseDTO toResponse(Category category);
    DeleteCategoryCommand toCommand(DeleteCategoryRequestDTO request);
    UpdateCategoryCommand toCommand(UpdateCategoryRequestDTO request);
}