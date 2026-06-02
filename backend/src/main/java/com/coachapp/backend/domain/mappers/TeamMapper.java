package com.coachapp.backend.domain.mappers;

import com.coachapp.backend.application.command.team.CreateTeamCommand;
import com.coachapp.backend.domain.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(target = "id", expression = "java(generateShortId())")
    Team toDomain(CreateTeamCommand command);

    default String generateShortId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}