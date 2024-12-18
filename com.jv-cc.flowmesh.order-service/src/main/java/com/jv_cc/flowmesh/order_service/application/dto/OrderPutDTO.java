package com.jv_cc.flowmesh.order_service.application.dto;

import com.jv_cc.flowmesh.order_service.domain.model.OrderEntity;
import com.jv_cc.flowmesh.order_service.presentation.request.OrderRequestPutDTO;
import com.jv_cc.flowmesh.order_service.presentation.response.OrderPutResponseDTO;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderPutDTO {

    private Long productId;
    private int count;

    private Long orderId;
    private LocalDateTime updatedAt;

    public OrderPutResponseDTO toResponse() {
        return OrderPutResponseDTO.builder()
                .updatedAt(this.updatedAt)
                .orderId(this.orderId)
                .build();
    }

    public OrderPutDTO(OrderEntity orderEntity){
        this.productId = null;
        this.count = 0;

        this.orderId = orderEntity.getOrderId();
        this.updatedAt = orderEntity.getUpdatedAt();
    }

    public OrderPutDTO(OrderRequestPutDTO dto, Long orderId){
        this.productId = dto.getProductId();
        this.count = dto.getCount();
        this.orderId = orderId;

        this.updatedAt = null;
    }

}
