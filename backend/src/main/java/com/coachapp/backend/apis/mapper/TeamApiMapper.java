package com.coachapp.backend.apis.mapper;

import com.coachapp.backend.apis.dto.team.CreateTeamRequestDTO;
import com.coachapp.backend.apis.dto.team.CreateTeamResponseDTO;
import com.coachapp.backend.apis.dto.team.TeamResponseDTO;
import com.coachapp.backend.application.command.team.CreateTeamCommand;
import com.coachapp.backend.domain.model.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamApiMapper {

    CreateTeamCommand toCommand(CreateTeamRequestDTO request);
    CreateTeamResponseDTO toCreateResponse(Team team);
    TeamResponseDTO toResponse(Team team);

}