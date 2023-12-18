package com.metlushko.strawberry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class StrawberryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrawberryApiApplication.class, args);

	}

}
