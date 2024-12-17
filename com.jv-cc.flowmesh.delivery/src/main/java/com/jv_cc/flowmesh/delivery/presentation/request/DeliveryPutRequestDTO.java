package com.jv_cc.flowmesh.delivery.presentation.request;

import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEnum;
import lombok.Getter;

@Getter
public class DeliveryPutRequestDTO {

    private DeliveryEnum status;
    private Long deliveryId;
    private String slackId;

}
