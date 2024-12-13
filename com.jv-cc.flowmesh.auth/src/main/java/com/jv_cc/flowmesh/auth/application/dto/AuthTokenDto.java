package com.jv_cc.flowmesh.auth.application.dto;

import com.jv_cc.flowmesh.auth.domain.model.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AuthTokenDto {
    private Long id;
    private UserRoleEnum role;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime createAt;
}
