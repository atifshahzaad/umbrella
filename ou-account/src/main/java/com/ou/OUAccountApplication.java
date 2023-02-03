package com.ou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OUAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(OUAccountApplication.class, args);

	}

}
