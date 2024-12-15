package com.jv_cc.flowmesh.delivery.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ai")
public interface AiClient {

    @PostMapping("/api/delivery-limit")
    void createDeadline();
}
