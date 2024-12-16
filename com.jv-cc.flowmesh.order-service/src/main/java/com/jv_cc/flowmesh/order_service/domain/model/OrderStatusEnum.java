package com.jv_cc.flowmesh.order_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    ORDER_RECEIPT("주문 접수"),
    ORDER_CANCEL("주문 취소"),
    ORDER_FAILURE("주문 실패"),
    DELIVERYING("배송중"),
    DELIVERY_COMPLETE("배송 완료"),
    RETURN_RECEIPT("반품 접수"),
    RETURNING("반품 진행중"),
    RETURN_COMPLETE("반품 완료"),

    ;
    private final String label;

}
