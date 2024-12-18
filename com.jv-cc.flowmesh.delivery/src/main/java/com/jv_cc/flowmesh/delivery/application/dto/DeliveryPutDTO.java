package com.jv_cc.flowmesh.delivery.application.dto;


import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEnum;
import com.jv_cc.flowmesh.delivery.presentation.request.DeliveryPutRequestDTO;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeliveryPutDTO {
    private DeliveryEnum status;
    private Long deliveryId;
    private String slackId;

    private LocalDateTime updatedAt;


    public DeliveryPutDTO(DeliveryPutRequestDTO dto, Long deliveryId) {
        this.status = dto.getStatus();
        this.deliveryId = deliveryId;
        this.slackId = dto.getSlackId();

        this.updatedAt = null;
    }
}
