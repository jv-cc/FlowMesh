package com.jv_cc.flowmesh.delivery.presentation.response;

import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEntity;
import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEnum;
import com.jv_cc.flowmesh.delivery.domain.model.DeliveryRouteEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeliveryGetResponseDTO {

    private Long orderId;
    private Long deliveryManagerId; //업체 배송 담당자
    private String address;
    private Long recipientId;
    private List<DeliveryRouteGetResponseDTO> deliveryRouteInfos = new ArrayList<>();

    @Builder
    private static class DeliveryRouteGetResponseDTO {
        private Long startHubId;
        private Long endHubId;
        private Long deliverySequence;
        private DeliveryEnum currentStatus;
        private LocalDateTime actualDuration;
        private double actualDistance;
    }

    public DeliveryGetResponseDTO(DeliveryEntity deliveryEntity, List<DeliveryRouteEntity> deliveryRouteEntities) {
        this.orderId = deliveryEntity.getOrderId();
        this.deliveryManagerId = deliveryEntity.getDeliveryManagerId();
        this.address = deliveryEntity.getAddress();
        this.recipientId = deliveryEntity.getRecipientId();
        deliveryRouteEntities.forEach(deliveryRouteEntity
                -> {deliveryRouteInfos.add(DeliveryRouteGetResponseDTO.builder()
                        .startHubId(deliveryRouteEntity.getStartHubId())
                        .endHubId(deliveryRouteEntity.getEndHubId())
                        .deliverySequence(deliveryRouteEntity.getDeliverySequence())
                        .currentStatus(deliveryRouteEntity.getCurrentStatus())
                        .actualDistance(deliveryRouteEntity.getActualDistance())
                        .actualDuration(deliveryRouteEntity.getActualDuration())
                        .build());}
        );
    }

}
