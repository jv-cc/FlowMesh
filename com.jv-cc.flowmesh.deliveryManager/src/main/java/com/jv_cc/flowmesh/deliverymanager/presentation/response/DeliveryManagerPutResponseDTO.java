package com.jv_cc.flowmesh.deliverymanager.presentation.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DeliveryManagerPutResponseDTO {

    private Long deliveryManagerId;
    private LocalDateTime updatedAt;

}
