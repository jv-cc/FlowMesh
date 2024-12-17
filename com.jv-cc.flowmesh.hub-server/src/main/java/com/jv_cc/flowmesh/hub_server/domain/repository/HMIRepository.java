package com.jv_cc.flowmesh.hub_server.domain.repository;

import com.jv_cc.flowmesh.hub_server.domain.model.HMIEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HMIRepository extends JpaRepository<HMIEntity, Long> {

    @Query(value = "SELECT ST_DistanceSphere(" +
            "ST_MakePoint(:startHubLatitude, :startHubLongitude), " +
            "ST_MakePoint(:endHubLatitude, :endHubLongitude))", nativeQuery = true)
    double calculateDistanceBetweenHubs(@Param("startHubLatitude") double startHubLatitude,
                                        @Param("startHubLongitude") double startHubLongitude,
                                        @Param("endHubLatitude") double endHubLatitude,
                                        @Param("endHubLongitude") double endHubLongitude);
}
