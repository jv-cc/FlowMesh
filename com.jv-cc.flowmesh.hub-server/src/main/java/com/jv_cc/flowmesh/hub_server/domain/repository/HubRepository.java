package com.jv_cc.flowmesh.hub_server.domain.repository;


import com.jv_cc.flowmesh.hub_server.domain.model.HubEntity;

public interface HubRepository {

    boolean existsByNameAndDeletedAtIsNull(String name);

    boolean existsByLatitudeAndLongitudeAndDeletedAtIsNull(double latitude, double longitude);

    HubEntity save(HubEntity hubEntity);
}
