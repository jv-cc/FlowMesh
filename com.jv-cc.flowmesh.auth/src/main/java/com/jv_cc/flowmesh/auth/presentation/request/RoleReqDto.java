package com.jv_cc.flowmesh.auth.presentation.request;

import com.jv_cc.flowmesh.auth.domain.model.UserRoleEnum;
import lombok.Getter;

@Getter
public class RoleReqDto {
    private UserRoleEnum role;
}