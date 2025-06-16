package com.partnest.virusscan.apiclient;

import com.partnest.virusscan.dto.ScanResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class VirusScanClient {

    @Autowired
    private WebClient webClient;

    private final ScannerProperties scannerProperties;

    public String scanFile(byte[] fileData) {
        ScanResultDto scanResultDto = webClient.post()
                        .uri(scannerProperties.scanUrl())
                        .bodyValue(fileData)
                        .retrieve()
                        .bodyToMono(ScanResultDto.class)
                        .block();

        return scanResultDto != null ? scanResultDto.getResult() : null;
    }
}
