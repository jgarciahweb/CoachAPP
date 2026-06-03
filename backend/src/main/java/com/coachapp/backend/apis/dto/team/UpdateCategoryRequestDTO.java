package com.coachapp.backend.apis.dto.team;

import lombok.Data;

@Data
public class UpdateCategoryRequestDTO {
    private String teamId;
    private String categoryId;
    private String name;
}
