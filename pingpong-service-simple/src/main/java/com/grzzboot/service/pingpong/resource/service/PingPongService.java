package com.grzzboot.service.pingpong.resource.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.grzzboot.service.pingpong.resource.service.domain.PingEntity;

@Service
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
		for (int i = 0; i < 50000000; i++) {
			Math.log(i);
		}
	}

}
