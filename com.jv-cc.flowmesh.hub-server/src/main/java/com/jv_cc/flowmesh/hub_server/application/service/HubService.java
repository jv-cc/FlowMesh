package com.jv_cc.flowmesh.hub_server.application.service;

import com.jv_cc.flowmesh.hub_server.domain.repository.HubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubService {

    private HubRepository hubRepository;

}
