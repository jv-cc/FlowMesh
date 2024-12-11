package com.jv_cc.flowmesh.hub_server.infrastructure.repository;

import com.jv_cc.flowmesh.hub_server.domain.model.HubEntity;
import com.jv_cc.flowmesh.hub_server.domain.repository.HubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HubRepositoryImpl implements HubRepository {

    private final JpaHubRepository jpaHubRepository;

    @Override
    public boolean existsByNameAndDeletedAtIsNull(String name) {
        return jpaHubRepository.existsByNameAndDeletedAtIsNull(name);
    }

    @Override
    public boolean existsByLatitudeAndLongitudeAndDeletedAtIsNull(double latitude, double longitude) {
        return jpaHubRepository.existsByLatitudeAndLongitudeAndDeletedAtIsNull(latitude, longitude);
    }

    @Override
    public HubEntity save(HubEntity hubEntity) {
        return jpaHubRepository.save(hubEntity);
    }


}
