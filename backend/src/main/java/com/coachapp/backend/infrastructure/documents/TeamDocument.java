package com.coachapp.backend.infrastructure.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("teams")
public class TeamDocument {

    @Id
    private String id;
    private String name;

}