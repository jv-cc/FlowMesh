package com.jv_cc.flowmesh.delivery.domain.repository;


import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
}
