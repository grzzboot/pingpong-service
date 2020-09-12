package com.grzzboot.service.pingpong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;

@SpringBootApplication(
        exclude = SpringDataWebAutoConfiguration.class,
        proxyBeanMethods = false
)
public class PingPongServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PingPongServiceApplication.class, args);
	}

}
