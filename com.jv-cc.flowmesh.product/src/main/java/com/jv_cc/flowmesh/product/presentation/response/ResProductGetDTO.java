package com.jv_cc.flowmesh.product.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResProductGetDTO {

    @JsonProperty(value = "product_id")
    private Long productId;

    @JsonProperty(value = "hub_id")
    private Long hubId;

    @JsonProperty(value = "company_id")
    private Long companyId;

    private String name;

    private int price;

    private int quantity;

}
