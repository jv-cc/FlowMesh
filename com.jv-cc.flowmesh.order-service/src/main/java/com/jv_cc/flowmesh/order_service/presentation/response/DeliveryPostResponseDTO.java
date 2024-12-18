package com.jv_cc.flowmesh.order_service.presentation.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DeliveryPostResponseDTO {

    private Long deliveryId;
    private String deliveryStatus;
    private LocalDateTime createdAt;

}
