package com.jv_cc.flowmesh.delivery.application.dto;

import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEnum;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class deliveryRouteCreateDTO {

    private Long startHubId;
    private Long endHubId;
    private Long deliveryId;
    private Long deliveryManagerId;
    private Long deliverySequence;
    private DeliveryEnum currentStatus;
    private LocalDateTime estimateDuration;
    private double estimateDistance;

}
