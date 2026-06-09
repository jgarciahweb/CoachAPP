package com.coachapp.backend.apis.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadAvatarResponseDTO {
    private String avatarUrl;
    private String token;
}
