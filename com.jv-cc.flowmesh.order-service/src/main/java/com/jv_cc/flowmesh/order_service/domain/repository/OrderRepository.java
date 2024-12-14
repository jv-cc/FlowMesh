package com.jv_cc.flowmesh.order_service.domain.repository;

import com.jv_cc.flowmesh.order_service.domain.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
