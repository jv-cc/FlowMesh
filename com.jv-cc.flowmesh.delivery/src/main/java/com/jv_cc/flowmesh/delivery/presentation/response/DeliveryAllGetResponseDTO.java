package com.jv_cc.flowmesh.delivery.presentation.response;

import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEntity;
import lombok.Getter;

@Getter
public class DeliveryAllGetResponseDTO {
    private Long orderId;
    private Long deliveryManagerId;
    private String address;
    private Long recipientId;

    public DeliveryAllGetResponseDTO(DeliveryEntity deliveryEntity) {
        this.orderId = deliveryEntity.getOrderId();
        this.deliveryManagerId = deliveryEntity.getDeliveryManagerId();
        this.address = deliveryEntity.getAddress();
        this.recipientId = deliveryEntity.getRecipientId();
    }
}
