package com.coachapp.backend.apis.dto.player;

import lombok.Data;

@Data
public class PlayerResponseDTO {
    private String id;
    private String categoryId;
    private String firstName;
    private String lastName;
    private Integer dorsal;
    private String position;
}