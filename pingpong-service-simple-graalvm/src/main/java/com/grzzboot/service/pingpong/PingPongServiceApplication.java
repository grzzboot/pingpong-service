package com.grzzboot.service.pingpong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication(
        exclude = SpringDataWebAutoConfiguration.class,
        proxyBeanMethods = false
)
public class PingPongServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PingPongServiceApplication.class, args);
	}
	
	@Bean
	public WebClient webClient() {
		return WebClient.create();
	}

}
