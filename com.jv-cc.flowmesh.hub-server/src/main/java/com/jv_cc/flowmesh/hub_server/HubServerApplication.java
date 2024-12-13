package com.jv_cc.flowmesh.hub_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableFeignClients
@SpringBootApplication
public class HubServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HubServerApplication.class, args);
	}

}
