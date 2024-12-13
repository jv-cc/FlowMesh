package com.jv_cc.flowmesh.deliverymanager.domain.repository;

import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryRoutesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRouteRepository extends JpaRepository<DeliveryRoutesEntity, Long> {
}
