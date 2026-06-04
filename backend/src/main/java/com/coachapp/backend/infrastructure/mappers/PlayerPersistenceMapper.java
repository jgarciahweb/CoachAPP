package com.coachapp.backend.infrastructure.mappers;


import com.coachapp.backend.domain.model.Player;
import com.coachapp.backend.infrastructure.documents.PlayerDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerPersistenceMapper {

    PlayerDocument toDocument(Player player);
    Player toDomain(PlayerDocument document);

}