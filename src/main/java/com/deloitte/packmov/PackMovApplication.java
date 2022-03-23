package com.deloitte.packmov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PackMovApplication {

	public static void main(String[] args) {
		SpringApplication.run(PackMovApplication.class, args);
	}
}
