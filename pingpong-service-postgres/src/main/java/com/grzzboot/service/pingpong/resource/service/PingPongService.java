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

	@Autowired
	public PingPongService(MemesRepository memesRepository) {
		this.memesRepository = memesRepository;
	}

	public PingEntity ping(String name, boolean meme) {
		StringBuilder sb = new StringBuilder(MESSAGE_BASE);
		if (!StringUtils.isEmpty(name)) {
			sb.append(" " + name);
		}
		if (meme) {
			sb.append(" - '" + getRandomMeme(memesRepository.getMemes()).getMeme() + "'");
		}
		return new PingEntity(sb.toString());
	}

	private MemeEntity getRandomMeme(List<MemeEntity> memes) {
		Random random = new Random(System.currentTimeMillis());
		return memes.get(random.nextInt(memes.size()));
	}

}
