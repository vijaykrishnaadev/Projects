package com.cloudscheduler.server;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CloudSchedulerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudSchedulerApplication.class, args);
	}

	@GetMapping("/")
	public Map<String, String> root() {
		return Map.of(
				"service", "cloud-task-scheduling",
				"status", "UP",
				"health", "/api/v1/health",
				"mobileClient", "/mobile.html",
				"tasks", "/api/v1/tasks",
				"vms", "/api/v1/vms",
				"phones", "/api/v1/phones");
	}
}
