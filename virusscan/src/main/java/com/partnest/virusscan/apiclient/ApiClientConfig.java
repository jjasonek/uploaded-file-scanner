package com.partnest.virusscan.apiclient;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class ApiClientConfig {

    private final ScannerProperties scannerProperties;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                        .baseUrl(scannerProperties.baseUrl())
                        .build();
    }
}
