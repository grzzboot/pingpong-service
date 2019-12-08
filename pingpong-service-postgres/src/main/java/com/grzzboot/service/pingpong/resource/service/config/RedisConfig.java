package com.grzzboot.service.pingpong.resource.service.config;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport implements CachingConfigurer {

	public static final String MEME_CACHE_NAME = "meme";
	public static final String CACHE_MANAGER = "cacheManager";

}
