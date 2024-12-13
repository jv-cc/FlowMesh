package com.jv_cc.flowmesh.company.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CompanyType {

    PRODUCER("생산 업체"),
    RECEIVER("수령 업체");

    private final String label;
}
