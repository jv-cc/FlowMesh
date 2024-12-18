package com.jv_cc.flowmesh.hub_server.domain.repository;


import com.jv_cc.flowmesh.hub_server.domain.model.HubEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HubRepository extends JpaRepository<HubEntity, Long>{

    boolean existsByNameAndIsDeletedFalse(String name);

    boolean existsByLatitudeAndLongitudeAndIsDeletedFalse(Double latitude, Double longitude);

    Optional<HubEntity> findByIdAndIsDeletedFalse(Long id);

    boolean existsByIdAndIsDeletedFalse(Long id);

    Page<HubEntity> findAllByNameAndIsDeletedFalse(String name, Pageable pageable);
}
