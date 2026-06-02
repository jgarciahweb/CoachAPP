package com.coachapp.backend.domain.mappers;

import com.coachapp.backend.application.command.team.AddCategoryCommand;
import com.coachapp.backend.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", expression = "java(generateCategoryId())")
    Category toDomain(AddCategoryCommand command);

    default String generateCategoryId() {
        return "CAT-" + UUID.randomUUID().toString().substring(0, 8);
    }
}