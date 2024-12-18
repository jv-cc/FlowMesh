package com.jv_cc.flowmesh.order_service.infrastructure.config;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {

    @Bean
    public Client client(){
        return new ApacheHttpClient();
    }

}
