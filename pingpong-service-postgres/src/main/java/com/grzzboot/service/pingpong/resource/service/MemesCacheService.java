package com.grzzboot.service.pingpong.resource.service;

import static com.grzzboot.service.pingpong.resource.service.config.RedisConfig.CACHE_MANAGER;
import static com.grzzboot.service.pingpong.resource.service.config.RedisConfig.MEME_CACHE_NAME;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.grzzboot.service.pingpong.resource.service.domain.MemeEntity;
import com.grzzboot.service.pingpong.resource.service.repository.MemesRepository;

@Repository
public class MemesCacheService {

	private final MemesRepository memesRepository;

	@Autowired
	public MemesCacheService(MemesRepository memesRepository) {
		this.memesRepository = memesRepository;
	}

	@Cacheable(cacheManager = CACHE_MANAGER, value = MEME_CACHE_NAME)
	public MemeEntity getCachedRandomMeme() {
		return cacheRandomMeme();
	}

	@CachePut(cacheManager = CACHE_MANAGER, value = MEME_CACHE_NAME)
	public MemeEntity cacheRandomMeme() {
		Random random = new Random(System.currentTimeMillis());
		List<MemeEntity> memes = memesRepository.getMemes();
		return memes.get(random.nextInt(memes.size()));
	}

}
