package com.coachapp.backend.application.services.user;

import com.coachapp.backend.application.command.user.UploadAvatarCommand;
import com.coachapp.backend.application.ports.FileStorageService;
import com.coachapp.backend.application.shared.CommandHandler;
import com.coachapp.backend.application.validation.user.UploadAvatarConstraintChecker;
import com.coachapp.backend.domain.model.User;
import com.coachapp.backend.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UploadAvatarCommandHandler implements CommandHandler<UploadAvatarCommand, User> {

    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;
    private final UploadAvatarConstraintChecker checker;

    @Override
    public Mono<User> execute(UploadAvatarCommand command) {
        return checker.check(command)
                .flatMap(validCommand -> userRepository.findById(validCommand.getUserId()))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("User not found")))
                .flatMap(user -> fileStorageService
                                .uploadAvatar(user.getId(), command.getFile())
                                .map(user::withAvatarUrl))
                .flatMap(userRepository::save);
    }
}