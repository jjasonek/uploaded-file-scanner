package com.partnest.virusscan.apiclient;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "visrusscan.scanner")
public record ScannerProperties(String baseUrl, String scanUrl) {

}
