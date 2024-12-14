package com.jv_cc.flowmesh.product.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-service")
public interface HubClient {

    @GetMapping("/api/hub/{hubId}/client")
    boolean existsHubBy(@PathVariable Long hubId);

}
