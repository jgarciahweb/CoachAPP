package com.coachapp.backend.domain.mappers;

import com.coachapp.backend.application.command.user.RegisterUserCommand;
import com.coachapp.backend.domain.model.RoleEnum;
import com.coachapp.backend.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = RoleEnum.class)
public interface UserMapper {

    @Mapping(target = "id", expression = "java(generateUserId())")
    @Mapping(target = "role", expression = "java(RoleEnum.NORMAL_USER)")
    User toDomain(RegisterUserCommand command);

    default String generateUserId() {
        return "US-" + UUID.randomUUID().toString().substring(0, 8);
    }
}