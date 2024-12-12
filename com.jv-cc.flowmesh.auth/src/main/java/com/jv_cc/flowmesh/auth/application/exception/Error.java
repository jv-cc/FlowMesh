package com.jv_cc.flowmesh.auth.application.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    INTERNAL_SERVER_ERROR(9999, "서버 오류입니다."),
    USER_EXIST_USERNAME(1101, "중복되는 유저네임 입니다."),
    USER_EXIST_NICKNAME(1102, "중복되는 닉네임 입니다."),
    USER_EXIST_EMAIL(1103, "중복되는 이메일 입니다."),
    USER_EXIST_SLACKID(1104, "중복되는 Slack_ID 입니다."),
    USER_NOT_EXIST(1105, "존재하지 않는 유저네임 입니다."),
    USER_PASSWORD_MISMATCH(1106, "일치하지 않은 비밀번호 입니다.")
    ;

    final Integer code;
    final String message;
}


