package com.coachapp.backend.infrastructure.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDocument {

    private String id;
    private String name;

}