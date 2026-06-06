package com.coachapp.backend.apis.controller;

import com.coachapp.backend.apis.dto.player.CreatePlayerRequestDTO;
import com.coachapp.backend.apis.dto.player.DeletePlayerRequestDTO;
import com.coachapp.backend.apis.dto.player.PlayerResponseDTO;
import com.coachapp.backend.apis.mapper.PlayerApiMapper;
import com.coachapp.backend.application.services.player.CreatePlayerCommandHandler;
import com.coachapp.backend.application.services.player.DeletePlayerCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlayerController {

    private final CreatePlayerCommandHandler createPlayerCommandHandler;
    private final DeletePlayerCommandHandler deletePlayerCommandHandler;
    private final PlayerApiMapper playerApiMapper;

    @PostMapping("/players")
    public Mono<PlayerResponseDTO> create(@RequestBody CreatePlayerRequestDTO request) {

        return Mono.just(request)
                .map(playerApiMapper::toCommand)
                .flatMap(createPlayerCommandHandler::execute)
                .map(playerApiMapper::toResponse);
    }

    @DeleteMapping("/players")
    public Mono<PlayerResponseDTO> delete(@RequestBody DeletePlayerRequestDTO request) {

        return Mono.just(request)
                .map(playerApiMapper::toCommand)
                .flatMap(deletePlayerCommandHandler::execute)
                .map(playerApiMapper::toResponse);
    }
}