package com.grzzboot.service.pingpong.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grzzboot.service.pingpong.resource.model.PingResource;
import com.grzzboot.service.pingpong.resource.service.PingPongService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(produces = { "application/json" })
@Slf4j
public class PingResourceController {

	private final PingPongService pingPongService;
	private final ApplicationContext applicationContext;

	@Autowired
	public PingResourceController(PingPongService pingPongService, final ApplicationContext applicationContext) {
		this.pingPongService = pingPongService;
		this.applicationContext = applicationContext;
	}

	@GetMapping(path = "/ping")
	public PingResource ping(@RequestParam(required = false) String name,
			@RequestParam(required = false, defaultValue = "1") int sleepSeconds) {
		long startTime = System.currentTimeMillis();
		PingResource pingResource = new PingResource(pingPongService.ping(name, sleepSeconds).getMessage());
		long duration = System.currentTimeMillis() - startTime;
		log.debug("Ping complete: {}, duration: {} ms.", pingResource, duration);
		return pingResource;
	}

	@GetMapping(path = "/unready")
	public void unready() {
		AvailabilityChangeEvent.publish(applicationContext, ReadinessState.REFUSING_TRAFFIC);
	}

	@GetMapping(path = "/ready")
	public void ready() {
		AvailabilityChangeEvent.publish(applicationContext, ReadinessState.ACCEPTING_TRAFFIC);
	}

}
