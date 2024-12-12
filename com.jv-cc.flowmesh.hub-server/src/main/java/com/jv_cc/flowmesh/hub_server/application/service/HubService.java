package com.jv_cc.flowmesh.hub_server.application.service;

import com.jv_cc.flowmesh.hub_server.application.dto.HubDTO;
import com.jv_cc.flowmesh.hub_server.application.exception.DuplicateHubCoordinatesException;
import com.jv_cc.flowmesh.hub_server.application.exception.DuplicateHubNameException;
import com.jv_cc.flowmesh.hub_server.domain.model.HubEntity;
import com.jv_cc.flowmesh.hub_server.domain.repository.HubRepository;
import com.jv_cc.flowmesh.hub_server.presentation.request.ReqHubPostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubRepository hubRepository;

    @Transactional
    public HubDTO createHub(ReqHubPostDTO dto) {

        if(hubRepository.existsByNameAndIsDeletedFalse(dto.getName())){
            throw new DuplicateHubNameException();
        }

        if(hubRepository.existsByLatitudeAndLongitudeAndIsDeletedFalse(dto.getLatitude(), dto.getLongitude())) {
            throw new DuplicateHubCoordinatesException();
        }

        HubEntity hub = HubEntity.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();

        return HubDTO.of(hubRepository.save(hub));
    }
}
