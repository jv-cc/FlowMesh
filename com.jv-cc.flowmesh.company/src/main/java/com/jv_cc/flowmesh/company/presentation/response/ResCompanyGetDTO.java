package com.jv_cc.flowmesh.company.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jv_cc.flowmesh.company.domain.model.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResCompanyGetDTO {

    @JsonProperty(value = "company_id")
    private Long companyId;

    @JsonProperty(value = "hub_id")
    private Long hubId;

    private String name;

    private CompanyType type;

    private String address;
}
