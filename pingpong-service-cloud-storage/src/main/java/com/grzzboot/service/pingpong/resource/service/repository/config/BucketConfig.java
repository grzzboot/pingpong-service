package com.grzzboot.service.pingpong.resource.service.repository.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "buckets")
public class BucketConfig {

	private PublicStorage publicStorage;
	private PrivateStorage privateStorage;
	private String serviceAccountFile;

	@Getter
	@Setter
	public static class PublicStorage extends AbstractStorage {
	}

	@Getter
	@Setter
	public static class PrivateStorage extends AbstractStorage {
		private long defaultDuration;
	}

	@Getter
	@Setter
	private static class AbstractStorage {
		private String name;
	}

}
