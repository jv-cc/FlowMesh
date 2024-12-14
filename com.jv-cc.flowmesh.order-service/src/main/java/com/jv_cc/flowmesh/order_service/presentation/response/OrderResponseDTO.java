package com.jv_cc.flowmesh.order_service.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jv_cc.flowmesh.order_service.application.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponseDTO {

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public OrderResponseDTO(OrderDTO order) {

    }

}
