package com.zorgundostu.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MpLocationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpLocationServiceApplication.class, args);
		System.out.println("http://localhost:8090/api/mp-location/v1/swagger-ui/index.html");
	}
}
