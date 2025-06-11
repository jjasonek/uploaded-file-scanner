package com.partnest.virusscan.rabbit;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "visrusscan.rabbitmq.scanning")
public record RabbitMQConfigProperties(
    String exchange,
    String queue,
    RoutingKey routing
) {
    protected record RoutingKey(String key) { }
}
