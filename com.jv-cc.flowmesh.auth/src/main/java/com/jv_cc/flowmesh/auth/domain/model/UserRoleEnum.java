package com.jv_cc.flowmesh.auth.domain.model;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
    CUSTOMER(RoleSecurity.CUSTOMER),
    DRIVER(RoleSecurity.DRIVER),
    HUB_MANAGER(RoleSecurity.HUB_MANAGER),
    MASTER(RoleSecurity.MASTER),;

    private final String roleSecurity;

    UserRoleEnum(String roleSecurity) {
        this.roleSecurity = roleSecurity;
    }

    public static class RoleSecurity {
        public static final String CUSTOMER = "ROLE_CUSTOMER";
        public static final String DRIVER = "ROLE_DRIVER";
        public static final String HUB_MANAGER = "ROLE_HUB_MANAGER";
        public static final String MASTER = "ROLE_MASTER";
    }
}
