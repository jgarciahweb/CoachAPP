package com.coachapp.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {
    private String id;
    private String categoryId;
    private String firstName;
    private String lastName;
    private Integer dorsal;
    private PositionEnum position;
}
