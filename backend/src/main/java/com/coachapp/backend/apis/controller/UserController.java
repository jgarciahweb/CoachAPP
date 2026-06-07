package com.coachapp.backend.apis.controller;

import com.coachapp.backend.apis.dto.user.UpdateProfileRequestDTO;
import com.coachapp.backend.apis.dto.user.UpdateProfileResponseDTO;
import com.coachapp.backend.apis.mapper.UserApiMapper;
import com.coachapp.backend.application.services.user.UpdateProfileCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserApiMapper userApiMapper;
    private final UpdateProfileCommandHandler updateProfileCommandHandler;

    @PutMapping("/users/profile")
    public Mono<UpdateProfileResponseDTO> updateProfile(@RequestBody UpdateProfileRequestDTO request, ServerWebExchange exchange) {
        return Mono.just(userApiMapper.toCommand(exchange.getAttribute("userId"), request))
                .flatMap(updateProfileCommandHandler::execute);
    }
}