package com.jv_cc.flowmesh.order_service.presentation.response;

import lombok.Getter;

@Getter
public class ProductGetResponseDTO {

    private Long productId;
    private Long companyId;
    private Long hubId;
    private String productName;
    private int quantity;
    private int price;

}
