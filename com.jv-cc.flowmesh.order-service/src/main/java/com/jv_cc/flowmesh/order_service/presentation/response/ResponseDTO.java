package com.jv_cc.flowmesh.order_service.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

    private Integer code;
    private String message;
    private T data;

}
