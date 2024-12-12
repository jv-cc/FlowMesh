package com.jv_cc.flowmesh.company.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jv_cc.flowmesh.company.domain.model.CompanyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReqCompanyPostDTO {

    @JsonProperty(value = "hub_id")
    @NotNull(message = "소속 허브 ID를 입력해주세요.")
    private Long hubId;

    @NotBlank(message = "업체 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "업체 타입을 입력해주세요.")
    private CompanyType type;

    @NotBlank(message = "업체 주소를 입력해주세요.")
    private String address;

}
