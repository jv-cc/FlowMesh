package com.jv_cc.flowmesh.deliverymanager.domain.repository;

import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
}
