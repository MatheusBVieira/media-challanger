package com.example.mediachallanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MediaChallangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaChallangerApplication.class, args);
	}

}
