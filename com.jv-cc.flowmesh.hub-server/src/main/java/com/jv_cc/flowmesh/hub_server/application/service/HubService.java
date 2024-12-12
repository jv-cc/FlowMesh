package com.jv_cc.flowmesh.hub_server.application.service;

import com.jv_cc.flowmesh.hub_server.application.dto.HubDTO;
import com.jv_cc.flowmesh.hub_server.application.exception.DuplicateHubCoordinatesException;
import com.jv_cc.flowmesh.hub_server.application.exception.DuplicateHubNameException;
import com.jv_cc.flowmesh.hub_server.application.exception.NotFoundHubException;
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

        if(checkDuplicateHubName(dto.getName())){
            throw new DuplicateHubNameException();
        }

        if(checkDuplicateHubCoordinates(dto.getLatitude(), dto.getLongitude())) {
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

    @Transactional
    public HubDTO modifyHub(Long hubId, ReqHubPostDTO dto) {

        HubEntity hubEntity = hubRepository.findByIdAndIsDeletedFalse(hubId).orElseThrow(NotFoundHubException::new);

        if(checkDuplicateHubName(dto.getName())){
            throw new DuplicateHubNameException();
        }

        if(checkDuplicateHubCoordinates(dto.getLatitude(), dto.getLongitude())) {
            throw new DuplicateHubCoordinatesException();
        }

        hubEntity.update(dto.getName(), dto.getAddress(), dto.getLatitude(), dto.getLongitude());

        return HubDTO.of(hubEntity) ;
    }

    private boolean checkDuplicateHubName(String name) {
        return hubRepository.existsByNameAndDeletedAtIsNull(name);
    }

    private boolean checkDuplicateHubCoordinates(double latitude, double longitude) {
        return hubRepository.existsByLatitudeAndLongitudeAndDeletedAtIsNull(latitude, longitude);
    }
}
