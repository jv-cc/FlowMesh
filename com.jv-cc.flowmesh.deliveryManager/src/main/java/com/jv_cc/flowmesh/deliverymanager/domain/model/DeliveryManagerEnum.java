package com.jv_cc.flowmesh.deliverymanager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryManagerEnum {

    HUB_DELIVERY("허브 배송 담당자"),
    COMPANY_DELIVERY("업체 배송 담당자");

    private final String label;

}
