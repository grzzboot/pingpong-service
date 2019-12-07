package com.grzzboot.service.pingpong.resource.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.grzzboot.service.pingpong.resource.service.domain.MemeEntity;
import com.grzzboot.service.pingpong.resource.service.domain.PingEntity;
import com.grzzboot.service.pingpong.resource.service.repository.MemesRepository;

@Service
public class PingPongService {

	private static final String MESSAGE_BASE = "Pong";

	private final MemesRepository memesRepository;
	private final MemesCacheService memesCacheService;

	@Autowired
	public PingPongService(MemesRepository memesRepository, MemesCacheService memesCacheService) {
		this.memesRepository = memesRepository;
		this.memesCacheService = memesCacheService;
	}

	public PingEntity ping(String name, boolean expensive, boolean meme, boolean cache) {
		StringBuilder sb = new StringBuilder(MESSAGE_BASE);
		if (expensive) {
			doExpensiveCalculation();
		}
		if (!StringUtils.isEmpty(name)) {
			sb.append(" " + name);
		}
		if (meme) {
			if (cache) {
				sb.append(" - '" + memesCacheService.getCachedRandomMeme().getMeme() + "'");

			} else {
				sb.append(" - '" + getRandomMeme(memesRepository.getMemes()).getMeme() + "'");
			}

		}
		return new PingEntity(sb.toString());
	}

	private void doExpensiveCalculation() {
		for (int i = 0; i < 50000000; i++) {
			Math.log(i);
		}
	}

	private MemeEntity getRandomMeme(List<MemeEntity> memes) {
		Random random = new Random(System.currentTimeMillis());
		return memes.get(random.nextInt(memes.size()));
	}

}
