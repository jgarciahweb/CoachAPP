package com.coachapp.backend.apis.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProfileResponseDTO {
    private UserResponseDTO user;
    private String token;
}
