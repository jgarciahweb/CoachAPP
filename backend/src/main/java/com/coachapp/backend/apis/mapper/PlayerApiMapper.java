package com.coachapp.backend.apis.mapper;

import com.coachapp.backend.apis.dto.player.CreatePlayerRequestDTO;
import com.coachapp.backend.apis.dto.player.PlayerResponseDTO;
import com.coachapp.backend.application.command.player.CreatePlayerCommand;
import com.coachapp.backend.domain.model.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerApiMapper {

    CreatePlayerCommand toCommand(CreatePlayerRequestDTO createPlayerRequestDTO);
    PlayerResponseDTO toResponse(Player player);
}