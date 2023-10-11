package com.example.firstservice.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/first-service")
public class FirstServiceController {

	private final Environment env;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to the First Service.";
	}

	@GetMapping("/message")
	public String message(@RequestHeader("first-request") String header) {
		System.out.println(header);
		return "Hello World in First Service.";
	}

	@GetMapping("/check")
	public String check() {
			return "Hi, there. This is a message from First Service.";
	}

}