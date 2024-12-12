package com.jv_cc.flowmesh.company.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResCompanyDTO {

    @JsonProperty(value = "company_id")
    private Long companyId;

}
