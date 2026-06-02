package com.coachapp.backend.apis.dto.team;

import lombok.Data;

@Data
public class AddCategoryRequestDTO {
    private String teamId;
    private String name;
}
