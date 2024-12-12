package com.jv_cc.flowmesh.auth.application.dto;

import com.jv_cc.flowmesh.auth.domain.model.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String slackId;
    private UserRoleEnum role;
}