package com.coachapp.backend.application.services.user;

import com.coachapp.backend.application.command.user.RegisterUserCommand;
import com.coachapp.backend.application.shared.CommandHandler;
import com.coachapp.backend.application.validation.user.RegisterUserConstraintChecker;
import com.coachapp.backend.domain.mappers.UserMapper;
import com.coachapp.backend.domain.model.User;
import com.coachapp.backend.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RegisterUserCommandHandler implements CommandHandler<RegisterUserCommand, User> {

    private final RegisterUserConstraintChecker checker;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<User> execute(RegisterUserCommand command) {

        return checker.check(command)
                .map(userMapper::toDomain)
                .map(this::encodePassword)
                .flatMap(userRepository::save);
    }

    private User encodePassword(User user) {
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build();
    }
}