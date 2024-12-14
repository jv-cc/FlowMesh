package com.jv_cc.flowmesh.product.application.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    NOT_FOUND_HUB(4000, "해당 허브가 존재하지 않습니다."),

    NOT_FOUND_COMPANY(5000, "해당 업체가 존재하지 않습니다."),

    DUPLICATE_PRODUCT_NAME(6100, "중복된 상품 이름이 존재합니다."),

    INTERNAL_SERVER_ERROR(9999, "서버 오류입니다.");

    final Integer code;

    final String message;
}


