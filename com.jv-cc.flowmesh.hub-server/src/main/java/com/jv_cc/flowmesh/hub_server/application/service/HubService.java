package com.jv_cc.flowmesh.hub_server.application.service;

import com.jv_cc.flowmesh.hub_server.application.dto.HubDTO;
import com.jv_cc.flowmesh.hub_server.application.exception.DuplicateHubCoordinatesException;
import com.jv_cc.flowmesh.hub_server.application.exception.DuplicateHubNameException;
import com.jv_cc.flowmesh.hub_server.application.exception.MasterOnlyAccessException;
import com.jv_cc.flowmesh.hub_server.application.exception.NotFoundHubException;
import com.jv_cc.flowmesh.hub_server.domain.model.HubEntity;
import com.jv_cc.flowmesh.hub_server.domain.repository.HubRepository;
import com.jv_cc.flowmesh.hub_server.presentation.request.ReqHubPostDTO;
import com.jv_cc.flowmesh.hub_server.presentation.request.SearchReqDto;
import com.jv_cc.flowmesh.hub_server.presentation.response.ResHubGetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubRepository hubRepository;

    @Transactional
    public HubDTO createHub(Long userId, String role, ReqHubPostDTO dto) {

        checkMasterRole(role);

        if (checkDuplicateHubName(dto.getName())) {
            throw new DuplicateHubNameException();
        }

        if (checkDuplicateHubCoordinates(dto.getLatitude(), dto.getLongitude())) {
            throw new DuplicateHubCoordinatesException();
        }

        HubEntity hub = HubDTO.toEntity(userId, dto);

        return HubDTO.of(hubRepository.save(hub));
    }
    @Transactional(readOnly = true)
    public Page<ResHubGetDTO > searchHub(SearchReqDto dto) {
        Sort sort = Sort.by(dto.getOrder().getDirection(), dto.getSort().getLabel());
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), sort);
        Page<HubEntity> hubList = hubRepository.findAllByNameAndIsDeletedFalse(dto.getName(), pageable);

        return hubList.map(ResHubGetDTO::new);
    }

    @Transactional(readOnly = true)
    public HubDTO getHub(Long hubId) {
        HubEntity hubEntity = getHubEntity(hubId);

        return HubDTO.of(hubEntity);
    }

    @Transactional
    public HubDTO modifyHub(Long userId, String role, Long hubId, ReqHubPostDTO dto) {

        checkMasterRole(role);

        HubEntity hubEntity = getHubEntity(hubId);

        if (checkDuplicateHubName(dto.getName())) {
            throw new DuplicateHubNameException();
        }

        if (checkDuplicateHubCoordinates(dto.getLatitude(), dto.getLongitude())) {
            throw new DuplicateHubCoordinatesException();
        }

        hubEntity.update(dto.getName(), dto.getAddress(), dto.getLatitude(), dto.getLongitude(), userId);

        return HubDTO.of(hubEntity);
    }

    @Transactional
    public HubDTO deleteHub(Long userId, String role,Long hubId) {

        checkMasterRole(role);

        HubEntity hubEntity = getHubEntity(hubId);
        hubEntity.markAsDelete(userId);

        return HubDTO.of(hubEntity);
    }

    private void checkMasterRole(String role) {
        if(!role.equals("MASTER")) {
            throw new MasterOnlyAccessException();
        }
    }

    private HubEntity getHubEntity(Long hubId) {
        return hubRepository.findByIdAndIsDeletedFalse(hubId).orElseThrow(NotFoundHubException::new);
    }

    public boolean getHubIdBy(Long hubId) {
        return hubRepository.existsByIdAndIsDeletedFalse(hubId);
    }

    private boolean checkDuplicateHubName(String name) {
        return hubRepository.existsByNameAndIsDeletedFalse(name);
    }

    private boolean checkDuplicateHubCoordinates(double latitude, double longitude) {
        return hubRepository.existsByLatitudeAndLongitudeAndIsDeletedFalse(latitude, longitude);
    }
}
