package com.jv_cc.flowmesh.order_service.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    INTERNAL_SERVER_EROR(9999, "서버 오류입니다."),

    DELIVERY_FAILURE(7101, "배송 생성이 실패했습니다."),

    PRODUCT_FAILURE(7102, "상품 차감에 실패했습니다."),

    ORDER_NOT_CHANGED(7201, "주문 변경을 실패했습니다."),

    PRODUCT_NOT_FOUND(7202, "상품이 존재하지 않습니다.")
    ;

    private Integer code;
    private String message;

}
