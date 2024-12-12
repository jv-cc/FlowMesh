package com.jv_cc.flowmesh.auth.application.dto;

import com.jv_cc.flowmesh.auth.domain.model.Auth;
import com.jv_cc.flowmesh.auth.domain.model.UserRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AuthDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String slack_id;
    private UserRoleEnum role;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime createAt;

    public Auth toEntity() {
        return new Auth(
                this.username,
                this.password,
                this.email,
                this.nickname,
                this.slack_id
        );
    }
    public static AuthDto fromEntity(Auth auth, String accessToken) {
        return AuthDto.builder()
                .id(auth.getId())
                .username(auth.getUsername())
                .password(auth.getPassword())
                .nickname(auth.getNickname())
                .email(auth.getEmail())
                .slack_id(auth.getSlackId())
                .role(auth.getRole())
                .accessToken(accessToken)
                .refreshToken(auth.getRefresh_token())
                .createAt(auth.getCreatedAt())
                .build();
    }
}
