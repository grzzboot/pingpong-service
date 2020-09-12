package com.grzzboot.service.pingpong.resource.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import com.grzzboot.service.pingpong.resource.service.domain.PingEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PingPongService {

	private static final String MESSAGE_BASE = "Pong";
	
	private Random random;
	
	@Autowired
	public PingPongService(WebClient webClient) {
		random = new Random(System.currentTimeMillis());
	}

	public PingEntity ping(String name, boolean expensive) {
		StringBuilder sb = new StringBuilder(MESSAGE_BASE);
		if (expensive) {
			doExpensiveCalculation();
		}
		if (!StringUtils.isEmpty(name)) {
			sb.append(" " + name);
		}
		return new PingEntity(sb.toString());
	}

	private void doExpensiveCalculation() {
		long start = System.currentTimeMillis();
		long split = start;
		do {
			Math.log(random.nextLong());
			split = System.currentTimeMillis() - start;
		} while (split <= 50);
		log.debug("Aborting expensive calculation after approx {} ms.", split);
	}

}
