package com.aditya.serice_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SericeRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SericeRegistryApplication.class, args);
	}

}
