package com.jv_cc.flowmesh.delivery.application.dto;

import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEntity;
import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEnum;
import com.jv_cc.flowmesh.delivery.presentation.request.DeliveryPostRequestDTO;
import com.jv_cc.flowmesh.delivery.presentation.response.DeliveryPostResponseDTO;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeliveryCreateDTO {

    private final Long orderId;
    private final Long demandCompanyId;
    private final Long supplyCompanyId;
    private final Long productId;
    private final int count;
    private final String requestInfo;

    private final Long deliveryId;
    private final DeliveryEnum deliveryStatus;
    private final LocalDateTime createdAt;

    public DeliveryEntity toEntity(){
        return DeliveryEntity.builder()
                .orderId(this.orderId)

                .build();
    }

    public DeliveryPostResponseDTO toResponseDTO(){
        return DeliveryPostResponseDTO.builder()
                .deliveryId(this.deliveryId)
                .deliveryStatus(this.deliveryStatus)
                .createdAt(this.createdAt)
                .build();
    }

    public DeliveryCreateDTO(DeliveryEntity deliveryEntity){
        this.orderId = null;
        this.demandCompanyId = null;
        this.supplyCompanyId = null;
        this.productId = null;
        this.count = -1;
        this.requestInfo = null;

        this.deliveryId = deliveryEntity.getDeliveryId();
        this.deliveryStatus = deliveryEntity.getCurrentStatus();
        this.createdAt = deliveryEntity.getCreatedAt();
    }

    public DeliveryCreateDTO(DeliveryPostRequestDTO deliveryPostRequestDTO){
        this.orderId = deliveryPostRequestDTO.getOrderId();
        this.demandCompanyId = deliveryPostRequestDTO.getDemandCompanyId();
        this.supplyCompanyId = deliveryPostRequestDTO.getSupplyCompanyId();
        this.productId = deliveryPostRequestDTO.getProductId();
        this.count = deliveryPostRequestDTO.getCount();
        this.requestInfo = deliveryPostRequestDTO.getRequestInfo();

        this.deliveryId = null;
        this.deliveryStatus = null;
        this.createdAt = null;
    }


}
