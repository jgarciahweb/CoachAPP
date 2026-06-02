package com.coachapp.backend.infrastructure.documents;

import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document("teams")
public class TeamDocument {

    @Id
    private String id;
    private String name;

    @Singular
    private List<CategoryDocument> categories = new ArrayList<>();
}