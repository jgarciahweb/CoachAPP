package com.coachapp.backend.boot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(MinioProperties.class)
@Configuration
public class MinioConfig {
}