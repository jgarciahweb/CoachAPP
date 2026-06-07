package com.coachapp.backend.infrastructure.storage;

import com.coachapp.backend.application.ports.FileStorageService;
import com.coachapp.backend.boot.config.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioFileStorageService implements FileStorageService {

    private final MinioClient minioClient;
    private final MinioProperties properties;

    @Override
    public Mono<String> uploadAvatar(String userId, FilePart file) {

        return Mono.fromCallable(() -> {
            String extension = Optional.of(file.filename())
                    .filter(name -> name.contains("."))
                    .map(name -> name.substring(name.lastIndexOf(".")))
                    .orElse(".jpg");

            String objectName = userId + "-" + UUID.randomUUID() + extension;

            Path tempFile = Files.createTempFile("avatar", extension);

            file.transferTo(tempFile).block();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket("avatars")
                            .object(objectName)
                            .stream(Files.newInputStream(tempFile), Files.size(tempFile), -1)
                            .contentType(file.headers().getContentType().toString())
                            .build()
            );

            return objectName;

        });
    }
}