package com.coachapp.backend.infrastructure.mappers;

import com.coachapp.backend.domain.model.Category;
import com.coachapp.backend.infrastructure.documents.CategoryDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryPersistenceMapper {

    CategoryDocument toDocument(Category category);
    Category toDomain(CategoryDocument document);

}