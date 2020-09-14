package com.grzzboot.service.pingpong.resource;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grzzboot.service.pingpong.resource.model.BucketResource;
import com.grzzboot.service.pingpong.resource.service.PingPongService;

@RestController
@RequestMapping(path = "ping/documents", produces = { "application/json" })
public class PingResourceController {

	private final PingPongService pingPongService;

	@Autowired
	public PingResourceController(PingPongService pingPongService) {
		this.pingPongService = pingPongService;
	}

	@GetMapping(path = "public/{name}")
	public ResponseEntity<BucketResource> getPublicDocumentUrl(@PathVariable String name) {
		Optional<String> result = pingPongService.getPublicDocumentUrl(name);
		return result.isPresent() ? ResponseEntity.ok(new BucketResource(result.get()))
				: ResponseEntity.notFound().build();
	}

	@PostMapping(path = "/public", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Void> createPublicDocument(@RequestParam("file") MultipartFile file) throws IOException {
		pingPongService.createPublicDocument(file);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(path = "private/{name}")
	public ResponseEntity<BucketResource> getPrivateDocumentUrl(@PathVariable String name) {
		Optional<String> result = pingPongService.getPrivateDocumentUrl(name);
		return result.isPresent() ? ResponseEntity.ok(new BucketResource(result.get()))
				: ResponseEntity.notFound().build();
	}
	
	@PostMapping(path = "/private", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Void> createPrivateDocument(@RequestParam("file") MultipartFile file) throws IOException {
		pingPongService.createPrivateDocument(file);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
