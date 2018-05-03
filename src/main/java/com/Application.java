package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args  );
	}
}
