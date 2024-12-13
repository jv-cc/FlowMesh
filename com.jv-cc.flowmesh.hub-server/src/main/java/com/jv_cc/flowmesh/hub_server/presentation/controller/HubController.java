package com.jv_cc.flowmesh.hub_server.presentation.controller;

import com.jv_cc.flowmesh.hub_server.application.service.HubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hub")
public class HubController{

    private final HubService hubService;

    @GetMapping
    public void test(){
        System.out.println("hubService = " + hubService);
    }

}
