package com.coachapp.backend.apis.controller;

import com.coachapp.backend.apis.dto.player.CreatePlayerRequestDTO;
import com.coachapp.backend.apis.dto.player.PlayerResponseDTO;
import com.coachapp.backend.apis.mapper.PlayerApiMapper;
import com.coachapp.backend.application.services.player.CreatePlayerCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlayerController {

    private final CreatePlayerCommandHandler createPlayerCommandHandler;
    private final PlayerApiMapper playerApiMapper;

    @PostMapping("/players")
    public Mono<PlayerResponseDTO> create(@RequestBody CreatePlayerRequestDTO request) {

        return Mono.just(request)
                .map(playerApiMapper::toCommand)
                .flatMap(createPlayerCommandHandler::execute)
                .map(playerApiMapper::toResponse);
    }
}