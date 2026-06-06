package com.coachapp.backend.apis.mapper;

import com.coachapp.backend.apis.dto.auth.LoginRequestDTO;
import com.coachapp.backend.apis.dto.auth.LoginResponseDTO;
import com.coachapp.backend.apis.dto.user.RegisterUserRequestDTO;
import com.coachapp.backend.apis.dto.user.UserResponseDTO;
import com.coachapp.backend.application.command.auth.LoginCommand;
import com.coachapp.backend.application.command.user.RegisterUserCommand;
import com.coachapp.backend.domain.model.LoginResult;
import com.coachapp.backend.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserApiMapper {

    RegisterUserCommand toCommand(RegisterUserRequestDTO request);
    UserResponseDTO toResponse(User user);
    LoginCommand toCommand(LoginRequestDTO request);

    @Mapping(target = "token", source = "token")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "role", expression = "java(loginResult.getUser().getRole().name())")
    LoginResponseDTO toResponse(LoginResult loginResult);
}