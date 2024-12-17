package com.jv_cc.flowmesh.product.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReqProductPostDTO {

    @JsonProperty(value = "hub_id")
    @NotNull(message = "소속 허브 ID 를 입력해 주세요.")
    private Long hubId;

    @JsonProperty(value = "company_id")
    @NotNull(message = "소속 업체 ID 를 입력해 주세요.")
    private Long companyId;

    @NotBlank(message = "상품 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "상품 가격을 입력해주세요.")
    private int price;

    @NotNull(message = "상품 재고를 입력해주세요.")
    private int quantity;

}
