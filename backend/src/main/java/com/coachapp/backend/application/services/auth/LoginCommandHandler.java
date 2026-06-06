package com.coachapp.backend.application.services.auth;

import com.coachapp.backend.application.command.auth.LoginCommand;
import com.coachapp.backend.application.shared.CommandHandler;
import com.coachapp.backend.application.validation.auth.LoginConstraintChecker;
import com.coachapp.backend.domain.model.LoginResult;
import com.coachapp.backend.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LoginCommandHandler implements CommandHandler<LoginCommand, LoginResult> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginConstraintChecker checker;
    private final JwtService jwtService;

    @Override
    public Mono<LoginResult> execute(LoginCommand command) {

        return checker.check(command)
                .flatMap(validCommand -> userRepository.findByEmail(validCommand.getEmail()))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid credentials")))
                .flatMap(user -> {
                    boolean valid = passwordEncoder.matches(command.getPassword(), user.getPassword());

                    if (!valid) {
                        return Mono.error(new IllegalArgumentException("Invalid credentials"));
                    }

                    return Mono.just(LoginResult.builder()
                            .user(user)
                            .token(jwtService.generateToken(user))
                            .build());
                });
    }
}