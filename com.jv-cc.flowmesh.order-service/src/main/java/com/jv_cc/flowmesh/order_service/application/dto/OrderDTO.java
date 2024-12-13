package com.jv_cc.flowmesh.order_service.application.dto;

import com.jv_cc.flowmesh.order_service.presentation.request.OrderRequestPostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public OrderDTO(OrderRequestPostDTO dto) {

    }
}
