package com.grzzboot.service.pingpong.resource.service.repository;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.grzzboot.service.pingpong.resource.service.domain.PingEntity;
import com.grzzboot.service.pingpong.resource.service.repository.config.RemoteConfig;
import com.grzzboot.service.pingpong.resource.service.repository.exception.PingPongErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class PingPongRepository {

	private final RestTemplate restTemplate;
	private final RemoteConfig remoteConfig;

	@Autowired
	public PingPongRepository(RestTemplateBuilder restTemplateBuilder, PingPongErrorHandler pingPongErrorHandler, RemoteConfig remoteConfig) {
		this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(1)).build();
		this.restTemplate.setErrorHandler(pingPongErrorHandler);
		this.remoteConfig = remoteConfig;
	}

	public PingEntity ping() {
		try {
			ResponseEntity<PingEntity> response = restTemplate.getForEntity(remoteConfig.getUrl(), PingEntity.class);
			return response != null ? response.getBody() : null;
		} catch (Exception e) {
			log.error("Failed to Ping service: {}", remoteConfig.getUrl());
			return null;
		}
	}

}
