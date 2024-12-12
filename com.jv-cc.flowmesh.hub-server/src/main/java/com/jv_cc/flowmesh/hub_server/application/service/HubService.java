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

        if (checkDuplicateHubName(dto.getName())) {
            throw new DuplicateHubNameException();
        }

        if (checkDuplicateHubCoordinates(dto.getLatitude(), dto.getLongitude())) {
            throw new DuplicateHubCoordinatesException();
        }

        HubEntity hub = HubDTO.toEntity(dto);

        return HubDTO.of(hubRepository.save(hub));
    }

    @Transactional(readOnly = true)
    public HubDTO getHub(Long hubId) {
        HubEntity hubEntity = getHubEntity(hubId);

        return HubDTO.of(hubEntity);
    }

    @Transactional
    public HubDTO modifyHub(Long hubId, ReqHubPostDTO dto) {

        HubEntity hubEntity = getHubEntity(hubId);

        if (checkDuplicateHubName(dto.getName())) {
            throw new DuplicateHubNameException();
        }

        if (checkDuplicateHubCoordinates(dto.getLatitude(), dto.getLongitude())) {
            throw new DuplicateHubCoordinatesException();
        }

        hubEntity.update(dto.getName(), dto.getAddress(), dto.getLatitude(), dto.getLongitude());

        return HubDTO.of(hubEntity);
    }

    @Transactional
    public HubDTO deleteHub(Long hubId) {

        HubEntity hubEntity = getHubEntity(hubId);
        hubEntity.markAsDelete();

        return HubDTO.of(hubEntity);
    }

    private HubEntity getHubEntity(Long hubId) {
        return hubRepository.findByIdAndIsDeletedFalse(hubId).orElseThrow(NotFoundHubException::new);
    }

    private boolean checkDuplicateHubName(String name) {
        return hubRepository.existsByNameAndIsDeletedFalse(name);
    }

    private boolean checkDuplicateHubCoordinates(double latitude, double longitude) {
        return hubRepository.existsByLatitudeAndLongitudeAndIsDeletedFalse(latitude, longitude);
    }
}
