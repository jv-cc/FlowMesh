package com.jv_cc.flowmesh.delivery.application.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    INTERNAL_SERVER_ERROR(9999, "서버 오류입니다."),

    NOT_CREATED_DELIVERY(8101, "배송을 생성하지 못 했습니다.")

    ;

    private Integer code;
    private String message;
}
