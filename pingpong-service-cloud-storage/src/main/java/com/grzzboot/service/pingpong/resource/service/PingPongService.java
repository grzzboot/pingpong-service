package com.grzzboot.service.pingpong.resource.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.grzzboot.service.pingpong.resource.service.repository.PingPongRepository;

@Service
public class PingPongService {

	private final PingPongRepository pingPongRepository;

	@Autowired
	public PingPongService(PingPongRepository pingPongRepository) {
		this.pingPongRepository = pingPongRepository;
	}

	public Optional<String> getPublicDocumentUrl(String name) {
		return pingPongRepository.getPublicDocumentUrl(name);
	}

	public void createPublicDocument(MultipartFile file) throws IOException {
		pingPongRepository.createPublicDocument(file);
	}

	public Optional<String> getPrivateDocumentUrl(String name) {
		return pingPongRepository.getPrivateDocumentUrl(name);
	}

	public void createPrivateDocument(MultipartFile file) throws IOException {
		pingPongRepository.createPrivateDocument(file);
	}

}
