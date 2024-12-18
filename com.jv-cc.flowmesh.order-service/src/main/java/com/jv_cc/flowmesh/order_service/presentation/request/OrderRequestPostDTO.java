package com.jv_cc.flowmesh.order_service.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class OrderRequestPostDTO {

    private Long demandCompanyId;
    private Long supplyCompanyId;
    private Long productId;
    private int count;
    private String requestInfo;

}
