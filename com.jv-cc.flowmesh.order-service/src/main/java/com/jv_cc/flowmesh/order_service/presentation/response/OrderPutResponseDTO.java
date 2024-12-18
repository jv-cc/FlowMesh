package com.jv_cc.flowmesh.order_service.presentation.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class OrderPutResponseDTO {

    private Long orderId;
    private LocalDateTime updatedAt;
}
