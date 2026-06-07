package com.coachapp.backend.application.validation.user;

import com.coachapp.backend.application.command.user.UploadAvatarCommand;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UploadAvatarConstraintChecker {

    public Mono<UploadAvatarCommand> check(UploadAvatarCommand command) {

        if (command.getFile() == null) {
            return Mono.error(new IllegalArgumentException("Avatar file is required"));
        }

        return Mono.just(command);
    }
}