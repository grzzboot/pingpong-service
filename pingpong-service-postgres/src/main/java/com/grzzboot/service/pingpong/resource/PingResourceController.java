package com.grzzboot.service.pingpong.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grzzboot.service.pingpong.resource.model.PingResource;
import com.grzzboot.service.pingpong.resource.service.PingPongService;

@RestController
@RequestMapping(produces = { "application/json" })
public class PingResourceController {

	private final PingPongService pingPongService;

	@Autowired
	public PingResourceController(PingPongService pingPongService) {
		this.pingPongService = pingPongService;
	}

	@GetMapping(path = "/ping")
	public PingResource ping(@RequestParam(required = false) String name,
			@RequestParam(required = false, defaultValue = "false") boolean meme) {
		return new PingResource(pingPongService.ping(name, meme).getMessage());
	}

}
