package com.coachapp.backend.apis.controller;

import com.coachapp.backend.apis.dto.user.RegisterUserRequestDTO;
import com.coachapp.backend.apis.dto.user.UserResponseDTO;
import com.coachapp.backend.apis.mapper.UserApiMapper;
import com.coachapp.backend.application.services.user.RegisterUserCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserCommandHandler registerUserCommandHandler;
    private final UserApiMapper userApiMapper;

    @PostMapping("/register")
    public Mono<UserResponseDTO> register(@RequestBody RegisterUserRequestDTO request) {

        return Mono.just(request)
                .map(userApiMapper::toCommand)
                .flatMap(registerUserCommandHandler::execute)
                .map(userApiMapper::toResponse);
    }
}