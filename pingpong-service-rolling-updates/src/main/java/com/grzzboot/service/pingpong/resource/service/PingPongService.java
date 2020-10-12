package com.grzzboot.service.pingpong.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.grzzboot.service.pingpong.resource.service.domain.PingEntity;
import com.grzzboot.service.pingpong.resource.service.support.PingSleeper;

@Service
public class PingPongService {

	private static final String MESSAGE_BASE = "Pong";

	private final PingSleeper pingSleeper;

	@Autowired
	public PingPongService(final PingSleeper pingSleeper) {
		this.pingSleeper = pingSleeper;
	}

	public PingEntity ping(String name, int sleepSeconds) {
		StringBuilder sb = new StringBuilder(MESSAGE_BASE);
		if (!StringUtils.isEmpty(name)) {
			sb.append(" " + name);
		}
		if (sleepSeconds > 0) {
			pingSleeper.sleep(sleepSeconds);
			sb.append(" (delivered after " + sleepSeconds + " seconds)");
		}
		return new PingEntity(sb.toString());
	}

}
