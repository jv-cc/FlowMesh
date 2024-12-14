package com.jv_cc.flowmesh.deliverymanager.domain.repository;


import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryManagerRepository extends JpaRepository<DeliveryManagerEntity, Long> {
}
