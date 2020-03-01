package com.grzzboot.service.pingpong.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.grzzboot.service.pingpong.resource.service.domain.PingEntity;
import com.grzzboot.service.pingpong.resource.service.repository.PingPongRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@EnableScheduling
@Slf4j
@ConditionalOnProperty(prefix = "pingpong.remote", value = "enable")
public class PingPongScheduledService {

	private static final int PING_RATE = 5000;
	private static final int PING_INITIAL_DELAY = 5000;

	private PingPongRepository pingPongRepository;

	@Autowired
	public PingPongScheduledService(PingPongRepository pingPongRepository) {
		this.pingPongRepository = pingPongRepository;
	}

	@Scheduled(fixedRate = PING_RATE, initialDelay = PING_INITIAL_DELAY)
	public void ping() {
		PingEntity pingEntity = pingPongRepository.ping();
		if (pingEntity != null) {
			log.debug("Successfully Pinged service: {}", pingEntity.getMessage());
		}
	}

}
