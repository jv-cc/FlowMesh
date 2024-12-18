package com.jv_cc.flowmesh.deliverymanager.presentation.response;

import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEntity;
import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEnum;
import lombok.Getter;

@Getter
public class DeliveryManagerGetOneResponseDTO {

    private Long deliveryManagerId;
    private Long hubId;
    private Long sequence;
    private DeliveryManagerEnum type;

    public DeliveryManagerGetOneResponseDTO(DeliveryManagerEntity entity) {
        this.deliveryManagerId = entity.getDeliveryManagerId();
        this.hubId = entity.getHubId();
        this.sequence = entity.getSequence();
        this.type = entity.getType();
    }


}
