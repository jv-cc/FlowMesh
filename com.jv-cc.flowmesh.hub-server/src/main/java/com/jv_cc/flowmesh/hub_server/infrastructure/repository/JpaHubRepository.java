package com.jv_cc.flowmesh.hub_server.infrastructure.repository;

import com.jv_cc.flowmesh.hub_server.domain.model.HubEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaHubRepository extends JpaRepository<HubEntity, Long> {

    boolean existsByNameAndDeletedAtIsNull(String name);

    boolean existsByLatitudeAndLongitudeAndDeletedAtIsNull(Double latitude, Double longitude);

}
