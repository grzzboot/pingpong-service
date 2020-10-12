package com.grzzboot.service.pingpong.resource.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ConditionalOnProperty(prefix = "pingpong.rolling", value = "enable")
@Slf4j
public class RollingUpdatesConfig {

	private final ApplicationEventPublisher eventPublisher;

	@Autowired
	public RollingUpdatesConfig(final ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
		log.info("Configured for rolling updates.");
	}

	@EventListener
	public void onApplicationEvent(ContextClosedEvent event) throws InterruptedException {
		AvailabilityChangeEvent.publish(this.eventPublisher, event, ReadinessState.REFUSING_TRAFFIC);
		log.info("Got termination event {}", event);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			log.info("Termination forced? Exception = {}", e);
			throw e;
		}
		log.info("Terminating service.");
	}

}
