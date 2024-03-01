package com.example.s3Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class S3DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3DemoApplication.class, args);
	}

}
