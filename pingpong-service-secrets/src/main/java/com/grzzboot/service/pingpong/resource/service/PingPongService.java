package com.grzzboot.service.pingpong.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grzzboot.service.pingpong.resource.service.config.SecretsConfig;
import com.grzzboot.service.pingpong.resource.service.domain.PingEntity;

@Service
public class PingPongService {

	private static final String MESSAGE_BASE = "Pong";

	private final SecretsConfig secretsConfig;

	@Autowired
	public PingPongService(final SecretsConfig secretsConfig) {
		this.secretsConfig = secretsConfig;
	}

	public PingEntity ping() {
		StringBuilder sb = new StringBuilder(MESSAGE_BASE);
		sb.append(", the secret is " + secretsConfig.getSecret());
		return new PingEntity(sb.toString());
	}

}
