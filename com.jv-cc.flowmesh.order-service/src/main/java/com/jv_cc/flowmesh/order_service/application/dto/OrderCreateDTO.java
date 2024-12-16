package com.jv_cc.flowmesh.order_service.application.dto;

import com.jv_cc.flowmesh.order_service.domain.model.OrderEntity;
import com.jv_cc.flowmesh.order_service.presentation.request.OrderRequestPostDTO;
import com.jv_cc.flowmesh.order_service.presentation.response.OrderPostResponseDTO;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderCreateDTO {

    private Long demandCompanyId;
    private Long supplyCompanyId;
    private Long productId;
    private int count;
    private String requestInfo;

    private Long orderId;
    private LocalDateTime createdAt;

    public OrderEntity toEntity(){
        return OrderEntity.builder()
                .demandId(this.demandCompanyId)
                .supplyId(this.supplyCompanyId)
                .productId(this.productId)
                .count(this.count)
                .requestInfo(this.requestInfo)
                .build();
    }

    public OrderPostResponseDTO toResponsePostDTO(){
        return OrderPostResponseDTO.builder()
                .orderId(this.orderId)
                .createdAt(this.createdAt)
                .build();
    }

    public OrderCreateDTO(OrderEntity entity){
        this.demandCompanyId = null;
        this.supplyCompanyId = null;
        this.productId = null;
        this.count = -1;
        this.requestInfo = null;

        this.orderId = entity.getOrderId();
        this.createdAt = entity.getCreatedAt();
    }

    public OrderCreateDTO(OrderRequestPostDTO dto){
        this.demandCompanyId = dto.getDemandCompanyId();
        this.supplyCompanyId = dto.getSupplyCompanyId();
        this.productId = dto.getProductId();
        this.count = dto.getCount();
        this.requestInfo = dto.getRequestInfo();

        this.orderId = null;
        this.createdAt = null;
    }

}
