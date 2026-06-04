package com.coachapp.backend.infrastructure.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("players")
public class PlayerDocument {

    @Id
    private String id;
    private String categoryId;
    private String firstName;
    private String lastName;
    private Integer dorsal;
    private String position;

}