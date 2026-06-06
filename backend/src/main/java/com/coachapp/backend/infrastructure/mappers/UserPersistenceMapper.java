package com.coachapp.backend.infrastructure.mappers;

import com.coachapp.backend.domain.model.User;
import com.coachapp.backend.infrastructure.documents.UserDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    UserDocument toDocument(User user);
    User toDomain(UserDocument document);

}