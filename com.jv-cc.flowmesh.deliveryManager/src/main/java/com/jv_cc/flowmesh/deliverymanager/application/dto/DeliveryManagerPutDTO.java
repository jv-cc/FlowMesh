package com.jv_cc.flowmesh.deliverymanager.application.dto;

import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEntity;
import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEnum;
import com.jv_cc.flowmesh.deliverymanager.presentation.request.DeliveryManagerPutRequestDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.response.DeliveryManagerPutResponseDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.response.DeliveryManagerResponseDTO;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeliveryManagerPutDTO {

    private Long deliveryManagerId;
    private Long hubId;
    private DeliveryManagerEnum type;

    private LocalDateTime updatedAt;

    public DeliveryManagerPutResponseDTO toResponsePutDTO(){
        return DeliveryManagerPutResponseDTO.builder()
                .deliveryManagerId(this.deliveryManagerId)
                .updatedAt(this.updatedAt)
                .build();
    }

    public DeliveryManagerPutDTO(DeliveryManagerPutRequestDTO dto, Long deliveryManagerId) {
        this.deliveryManagerId = deliveryManagerId;
        this.hubId = dto.getHubId();
        this.type = dto.getType();

        this.updatedAt = null;
    }

    public DeliveryManagerPutDTO(DeliveryManagerEntity entity) {
        this.hubId = null;
        this.type = null;

        this.deliveryManagerId = entity.getDeliveryManagerId();
        this.updatedAt = entity.getUpdatedAt();
    }

}
