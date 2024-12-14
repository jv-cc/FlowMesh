package com.jv_cc.flowmesh.delivery.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryEnum {

    ORDER_RECEPTION("주문 접수"),
    PREPARE_FOR_DELIVERY("배송 준비중"),
    DELIVERING("배송중"),
    COMPLETE_DELIVERY("배송완료"),
    ORDER_CANCEL("주문 취소"),

    ;
    private final String label;
}