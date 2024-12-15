package com.jv_cc.flowmesh.delivery.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/*
 * TODO: api 수정
 * */
@FeignClient(name = "hub-service")
public interface HubClient {

    @GetMapping
    void getChangeHub();
}
