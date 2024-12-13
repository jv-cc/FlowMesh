package com.jv_cc.flowmesh.auth.application.dto;

import com.jv_cc.flowmesh.auth.domain.model.Auth;
import com.jv_cc.flowmesh.auth.domain.model.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserInfoDto {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String slackId;
    private UserRoleEnum role;

    public UserInfoDto(Auth auth) {
        this.id = auth.getId();
        this.username = auth.getUsername();
        this.nickname = auth.getNickname();
        this.email = auth.getEmail();
        this.slackId = auth.getSlackId();
        this.role = auth.getRole();
    }
}
