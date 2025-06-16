package com.partnest.virusscan;

import com.partnest.virusscan.apiclient.ScannerProperties;
import com.partnest.virusscan.rabbit.RabbitMQConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({RabbitMQConfigProperties.class, ScannerProperties.class})
@SpringBootApplication
public class VirusscanApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirusscanApplication.class, args);
	}

}
