package com.coachapp.backend.domain.mappers;

import com.coachapp.backend.application.command.player.CreatePlayerCommand;
import com.coachapp.backend.domain.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "id", expression = "java(generatePlayerId())")
    Player toDomain(CreatePlayerCommand command);

    default String generatePlayerId() {
        return "PYR-" + UUID.randomUUID().toString().substring(0, 8);
    }

}