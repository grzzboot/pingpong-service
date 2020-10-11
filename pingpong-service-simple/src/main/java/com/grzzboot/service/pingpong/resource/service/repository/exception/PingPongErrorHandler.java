package com.grzzboot.service.pingpong.resource.service.repository.exception;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.grzzboot.service.pingpong.resource.service.repository.config.RemoteConfig;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PingPongErrorHandler extends DefaultResponseErrorHandler {
	
	private final RemoteConfig remoteConfig;
	
	public PingPongErrorHandler(RemoteConfig remoteConfig) {
		this.remoteConfig = remoteConfig;
	}
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		log.error("Failed to Ping service: {}", remoteConfig.getUrl());
	}

}
