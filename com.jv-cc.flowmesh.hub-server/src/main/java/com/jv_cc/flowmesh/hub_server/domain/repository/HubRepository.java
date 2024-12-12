package com.jv_cc.flowmesh.hub_server.domain.repository;


import com.jv_cc.flowmesh.hub_server.domain.model.HubEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HubRepository extends JpaRepository<HubEntity, Long> {

    boolean existsByNameAndIsDeletedFalse(String name);

    boolean existsByLatitudeAndLongitudeAndIsDeletedFalse(Double latitude, Double longitude);
}