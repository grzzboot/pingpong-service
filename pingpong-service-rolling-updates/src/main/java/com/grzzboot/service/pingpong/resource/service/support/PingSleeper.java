package com.grzzboot.service.pingpong.resource.service.support;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PingSleeper {

	public void sleep(int seconds) {
		long startTime = System.currentTimeMillis();
		long duration = 0;
		while ((duration = System.currentTimeMillis() - startTime) < (seconds * 1000)) {
			try {
				log.info("Still sleeping after {} ms.", duration);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				duration = System.currentTimeMillis() - startTime;
				log.error("Sleep was interrupted after {} ms.", duration);
				return;
			}
		}
		duration = System.currentTimeMillis() - startTime;
		log.info("Done sleeping after {} ms", duration);
	}

}
