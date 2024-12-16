package com.jv_cc.flowmesh.order_service.presentation.response;

import com.jv_cc.flowmesh.order_service.domain.model.OrderEntity;
import lombok.Getter;

@Getter
public class OrderGetResponseDTO {

    private Long orderId;
    private Long demandCompanyId;
    private Long supplyCompanyId;
    private Long productId;
    private int count;
    private String requestInfo;

    public OrderGetResponseDTO(OrderEntity entity) {
        this.orderId = entity.getOrderId();
        this.demandCompanyId = entity.getDemandId();
        this.supplyCompanyId = entity.getSupplyId();
        this.productId = entity.getProductId();
        this.count = entity.getCount();
        this.requestInfo = entity.getRequestInfo();
    }

}
