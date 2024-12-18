package com.jv_cc.flowmesh.order_service.presentation.response;

import lombok.Getter;

@Getter
public class DeliveryGetResponseDTO {

    private Long userId;
    private Long hubId;
    private Long deliveryId;
    private String status;
    private Long deliveryNo;

}
