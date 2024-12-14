package com.jv_cc.flowmesh.delivery.application.dto;

import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class deliveryCreateDTO {

    private Long orderId;
    private Long deliveryManagerId; // 업체 배송 담당자
    private Long startHubId;
    private Long endHubId;
    private String address;
    private Long recipientId;
    private Long messageId;
    private DeliveryEnum currentStatus;

}
