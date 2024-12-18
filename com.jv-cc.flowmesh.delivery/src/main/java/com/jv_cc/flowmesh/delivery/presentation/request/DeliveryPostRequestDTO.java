package com.jv_cc.flowmesh.delivery.presentation.request;

import lombok.Getter;

@Getter
public class DeliveryPostRequestDTO {
    private Long orderId;
    private Long demandCompanyId;
    private Long supplyCompanyId;
    private Long productId;
    private int count;
    private String requestInfo;
}
