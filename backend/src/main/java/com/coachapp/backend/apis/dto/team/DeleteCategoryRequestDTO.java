package com.coachapp.backend.apis.dto.team;

import lombok.Data;

@Data
public class DeleteCategoryRequestDTO {
    private String teamId;
    private String categoryId;
}
