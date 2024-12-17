package com.jv_cc.flowmesh.delivery.application.dto;

import com.jv_cc.flowmesh.delivery.presentation.response.DeliveryDeleteResponseDTO;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeliveryDeleteDTO {

    private Long deliveryId;
    private LocalDateTime deletedAt;

    public DeliveryDeleteDTO(Long deliveryId, LocalDateTime deletedAt) {
        this.deliveryId = deliveryId;
        this.deletedAt = deletedAt;
    }

    public DeliveryDeleteResponseDTO toResponseDTO() {
        return DeliveryDeleteResponseDTO.builder()
                .deletedAt(deletedAt)
                .deliveryId(deliveryId)
                .build();
    }

}
