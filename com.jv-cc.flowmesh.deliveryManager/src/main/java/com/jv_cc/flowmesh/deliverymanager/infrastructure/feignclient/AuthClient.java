package com.jv_cc.flowmesh.deliverymanager.infrastructure.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth")
public interface AuthClient {

    /**
     * TODO : 매핑 주소 변경 및 반환값 변경
     */
    @GetMapping("/api/users/{users_id}")
    void checkRole(@PathVariable Long users_id) ;
}
