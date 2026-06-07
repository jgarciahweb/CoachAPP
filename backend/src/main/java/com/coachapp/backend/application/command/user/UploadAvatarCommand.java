package com.coachapp.backend.application.command.user;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.codec.multipart.FilePart;

@Value
@Builder
public class UploadAvatarCommand {

    String userId;
    FilePart file;

}
