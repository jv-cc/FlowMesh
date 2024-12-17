package com.jv_cc.flowmesh.deliverymanager.domain.repository;


import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEntity;
import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeliveryManagerRepository extends JpaRepository<DeliveryManagerEntity, Long> {
    int countByHubIdAndIsDeletedFalse(Long hubId);

    int countByDeliveryManagerIdAndIsDeletedFalse(DeliveryManagerEnum deliveryManagerEnum);

    @Query("SELECT COALESCE(MAX(d.sequence), 0) FROM DeliveryManagerEntity d WHERE d.hubId = :hubId AND d.type = :deliveryType")
    Long findMaxSequenceByHubIdAndType(Long hubId, DeliveryManagerEnum deliveryType);
}
