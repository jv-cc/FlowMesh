package com.jv_cc.flowmesh.hub_server.application.service;

import com.jv_cc.flowmesh.hub_server.application.dto.HMIDTO;
import com.jv_cc.flowmesh.hub_server.application.exception.MasterOnlyAccessException;
import com.jv_cc.flowmesh.hub_server.application.exception.NotFoundHubException;
import com.jv_cc.flowmesh.hub_server.domain.model.HMIEntity;
import com.jv_cc.flowmesh.hub_server.domain.model.HubEntity;
import com.jv_cc.flowmesh.hub_server.domain.repository.HMIRepository;
import com.jv_cc.flowmesh.hub_server.domain.repository.HubRepository;
import com.jv_cc.flowmesh.hub_server.presentation.request.ReqHMIPostDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HMIService {

    private final HubRepository hubRepository;
    private final HMIRepository hmiRepository;

    public HMIDTO createHMI(String userId, String role, @Valid ReqHMIPostDTO dto) {
        if(!role.equals("MASTER")) {
            throw new MasterOnlyAccessException();
        }

        HubEntity startHub = hubRepository.findByIdAndIsDeletedFalse(dto.getStartHubId()).orElseThrow(NotFoundHubException::new);
        HubEntity endHub = hubRepository.findByIdAndIsDeletedFalse(dto.getEndHubId()).orElseThrow(NotFoundHubException::new);

        double startHubLatitude = startHub.getLatitude();
        double startHubLongitude = startHub.getLongitude();

        double endHubLatitude = endHub.getLatitude();
        double endHubLongitude = endHub.getLongitude();

        // 이동 거리
        double distance = hmiRepository.calculateDistanceBetweenHubs(startHubLatitude, startHubLongitude, endHubLatitude, endHubLongitude) / 1000;

        Long timeRequired = (long) ((distance / 60)  * 3600);

        HMIEntity hmiEntity = HMIDTO.toEntity(startHub, endHub, distance, timeRequired);

        return HMIDTO.of(hmiRepository.save(hmiEntity));
    }

}
