package com.jv_cc.flowmesh.product.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResProductDTO {

    @JsonProperty(value = "product_id")
    private Long productId;

}
