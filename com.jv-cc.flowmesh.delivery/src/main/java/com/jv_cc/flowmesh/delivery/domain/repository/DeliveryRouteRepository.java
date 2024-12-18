package com.jv_cc.flowmesh.delivery.domain.repository;

import com.jv_cc.flowmesh.delivery.domain.model.DeliveryRouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRouteRepository extends JpaRepository<DeliveryRouteEntity, Long> {
    List<DeliveryRouteEntity> findAllByDeliveryId(Long deliveryId);
}
