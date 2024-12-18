package com.jv_cc.flowmesh.delivery.presentation.response;

import com.jv_cc.flowmesh.delivery.application.dto.DeliveryDTO;
import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEnum;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DeliveryPostResponseDTO {

    private Long deliveryId;
    private DeliveryEnum deliveryStatus;
    private LocalDateTime createdAt;

}
