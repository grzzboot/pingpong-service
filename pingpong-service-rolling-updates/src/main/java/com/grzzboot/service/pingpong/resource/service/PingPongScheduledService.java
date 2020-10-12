package com.grzzboot.service.pingpong.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.grzzboot.service.pingpong.resource.service.support.PingSleeper;

import lombok.extern.slf4j.Slf4j;

@Service
@EnableScheduling
@Slf4j
@ConditionalOnProperty(prefix = "pingpong.batch", value = "enable")
public class PingPongScheduledService {

	private static final int BATCH_RATE_MILLISECONDS = 60000;
	private static final int SLEEP_SECONDS = 60;

	private final PingSleeper pingSleeper;

	@Autowired
	public PingPongScheduledService(final PingSleeper pingSleeper) {
		this.pingSleeper = pingSleeper;
	}

	@Scheduled(fixedRate = BATCH_RATE_MILLISECONDS)
	public void ping() {
		log.info("Starting batch.");
		pingSleeper.sleep(SLEEP_SECONDS);
		log.info("Successfully ran batch for {} seconds.", SLEEP_SECONDS);
	}

}
