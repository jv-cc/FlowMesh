package com.jv_cc.flowmesh.order_service.presentation.response;

import com.jv_cc.flowmesh.order_service.domain.model.OrderEntity;
import lombok.Getter;

@Getter
public class OrderGetOneResponseDTO {

    private Long orderId;
    private Long demandCompanyId;
    private Long supplyCompanyId;
    private Long productId;
    private int count;
    private Long deliveryId;
    private String requestInfo;

    public OrderGetOneResponseDTO(OrderEntity entity, Long deliveryId) {
        this.orderId = entity.getOrderId();
        this.demandCompanyId = entity.getDemandId();
        this.supplyCompanyId = entity.getSupplyId();
        this.productId = entity.getProductId();
        this.count = entity.getCount();
        this.deliveryId = deliveryId;
        this.requestInfo = entity.getRequestInfo();
    }
}
