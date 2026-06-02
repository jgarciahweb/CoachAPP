package com.coachapp.backend.infrastructure.mappers;

import com.coachapp.backend.domain.model.Team;
import com.coachapp.backend.infrastructure.documents.TeamDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamPersistenceMapper {

    TeamDocument toDocument(Team team);
    Team toDomain(TeamDocument document);

}
