package com.jv_cc.flowmesh.auth.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUserDto {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String slack_id;
}
