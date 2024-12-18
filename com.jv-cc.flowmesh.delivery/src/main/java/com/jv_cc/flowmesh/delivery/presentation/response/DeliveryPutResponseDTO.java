package com.jv_cc.flowmesh.delivery.presentation.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeliveryPutResponseDTO {

    private Long deliveryId;
    private LocalDateTime updatedAt;

}
