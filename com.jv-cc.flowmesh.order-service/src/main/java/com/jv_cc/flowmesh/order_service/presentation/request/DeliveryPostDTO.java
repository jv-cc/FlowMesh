package com.jv_cc.flowmesh.order_service.presentation.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryPostDTO {
    private Long orderId;
    private Long demandCompanyId;
    private Long supplyCompanyId;
    private Long productId;
    private int count;
    private String requestInfo;
}
