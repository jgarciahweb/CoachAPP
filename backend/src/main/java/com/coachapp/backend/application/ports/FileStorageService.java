package com.coachapp.backend.application.ports;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface FileStorageService {

    Mono<String> uploadAvatar(String userId, FilePart file);
}