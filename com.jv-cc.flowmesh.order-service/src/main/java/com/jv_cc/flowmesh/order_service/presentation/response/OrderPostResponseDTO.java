package com.jv_cc.flowmesh.order_service.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrderPostResponseDTO {

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

}
