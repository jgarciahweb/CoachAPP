package com.coachapp.backend.application.services.user;

import com.coachapp.backend.apis.dto.user.UpdateProfileResponseDTO;
import com.coachapp.backend.apis.mapper.UserApiMapper;
import com.coachapp.backend.application.command.user.UpdateProfileCommand;
import com.coachapp.backend.application.services.auth.JwtService;
import com.coachapp.backend.application.shared.CommandHandler;
import com.coachapp.backend.application.validation.user.UpdateProfileConstraintChecker;
import com.coachapp.backend.domain.model.User;
import com.coachapp.backend.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateProfileCommandHandler implements CommandHandler<UpdateProfileCommand, UpdateProfileResponseDTO> {

    private final UserRepository userRepository;
    private final UpdateProfileConstraintChecker checker;
    private final JwtService jwtService;
    private final UserApiMapper userApiMapper;

    @Override
    public Mono<UpdateProfileResponseDTO> execute(UpdateProfileCommand command) {

        return checker.check(command)
                .flatMap(validCommand -> userRepository.findById(validCommand.getUserId()))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("User not found")))
                .map(user -> User.builder()
                        .id(user.getId())
                        .email(command.getEmail())
                        .password(user.getPassword())
                        .role(user.getRole())
                        .firstName(command.getFirstName())
                        .lastName(command.getLastName())
                        .build())
                .flatMap(userRepository::save)
                .map(user -> UpdateProfileResponseDTO.builder()
                        .user(userApiMapper.toResponse(user))
                        .token(jwtService.generateToken(user))
                        .build());
    }
}