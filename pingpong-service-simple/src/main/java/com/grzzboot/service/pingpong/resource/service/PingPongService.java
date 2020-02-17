package com.grzzboot.service.pingpong.resource.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.grzzboot.service.pingpong.resource.service.domain.PingEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PingPongService {

	private static final String MESSAGE_BASE = "Pong";

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
		for(int i = 0; i < 5000; i++) {
			for(int j = 0 ; j < 10000; j++) {
				Math.log(i*j);
			}
			long split = System.currentTimeMillis() - start;
			if(split > 500) {
				log.debug("Aborting expensive calculation after approx {} ms.", split);
				return;
			}
		}
	}

}
