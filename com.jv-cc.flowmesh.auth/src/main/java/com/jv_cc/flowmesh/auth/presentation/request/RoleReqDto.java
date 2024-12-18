package com.jv_cc.flowmesh.auth.presentation.request;

import com.jv_cc.flowmesh.auth.domain.model.UserRoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class RoleReqDto {
    @Schema(example = "MASTER")
    private UserRoleEnum role;
}