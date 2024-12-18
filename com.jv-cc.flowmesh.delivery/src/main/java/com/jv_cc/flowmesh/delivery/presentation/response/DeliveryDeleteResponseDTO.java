package com.jv_cc.flowmesh.delivery.presentation.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DeliveryDeleteResponseDTO {

    private Long deliveryId;
    private LocalDateTime deletedAt;

}
