package com.partnest.virusscan.apiclient;

import com.partnest.virusscan.dto.ScanResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.web.reactive.function.BodyInserters.fromMultipartData;

@Component
@RequiredArgsConstructor
public class VirusScanClient {

    private final WebClient webClient = WebClient.builder()
                                                 .baseUrl("http://localhost:1080")
                                                 .build();

    public String scanFile(byte[] fileData) {
        ScanResultDto scanResultDto = webClient.post()
                        .uri("/api/scan")
                        .bodyValue(fileData)
                        .retrieve()
                        .bodyToMono(ScanResultDto.class)
                        .block();

        return scanResultDto != null ? scanResultDto.getResult() : null;
    }
}
