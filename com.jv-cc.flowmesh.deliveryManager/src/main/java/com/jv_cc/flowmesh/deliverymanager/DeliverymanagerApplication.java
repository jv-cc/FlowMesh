package com.jv_cc.flowmesh.deliverymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DeliverymanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliverymanagerApplication.class, args);
	}

}
