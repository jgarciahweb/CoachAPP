package com.coachapp.backend.apis.mapper;

import com.coachapp.backend.apis.dto.user.RegisterUserRequestDTO;
import com.coachapp.backend.apis.dto.user.UserResponseDTO;
import com.coachapp.backend.application.command.user.RegisterUserCommand;
import com.coachapp.backend.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserApiMapper {

    RegisterUserCommand toCommand(RegisterUserRequestDTO request);
    UserResponseDTO toResponse(User user);

}