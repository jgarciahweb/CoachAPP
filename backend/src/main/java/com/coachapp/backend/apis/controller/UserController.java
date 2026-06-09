package com.coachapp.backend.apis.controller;

import com.coachapp.backend.apis.dto.user.UpdateProfileRequestDTO;
import com.coachapp.backend.apis.dto.user.UpdateProfileResponseDTO;
import com.coachapp.backend.apis.dto.user.UploadAvatarResponseDTO;
import com.coachapp.backend.apis.mapper.UserApiMapper;
import com.coachapp.backend.application.command.user.UploadAvatarCommand;
import com.coachapp.backend.application.services.user.UpdateProfileCommandHandler;
import com.coachapp.backend.application.services.user.UploadAvatarCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserApiMapper userApiMapper;
    private final UpdateProfileCommandHandler updateProfileCommandHandler;
    private final UploadAvatarCommandHandler uploadAvatarCommandHandler;

    @PutMapping("/users/profile")
    public Mono<UpdateProfileResponseDTO> updateProfile(@RequestBody UpdateProfileRequestDTO request, ServerWebExchange exchange) {
        return Mono.just(userApiMapper.toCommand(exchange.getAttribute("userId"), request))
                .flatMap(updateProfileCommandHandler::execute);
    }

    @PostMapping(value = "/users/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<UploadAvatarResponseDTO> uploadAvatar(@RequestPart("file") FilePart file, ServerWebExchange exchange) {
        UploadAvatarCommand command = UploadAvatarCommand.builder()
                .userId(exchange.getAttribute("userId"))
                .file(file)
                .build();

        return uploadAvatarCommandHandler.execute(command);
    }
}