package com.jv_cc.flowmesh.auth.domain.model;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
    CUSTOMER(RoleSecurity.CUSTOMER, RoleLabel.CUSTOMER),
    DRIVER(RoleSecurity.DRIVER, RoleLabel.DRIVER),
    HUB_MANAGER(RoleSecurity.HUB_MANAGER, RoleLabel.HUB_MANAGER),
    MASTER(RoleSecurity.MASTER, RoleLabel.MASTER),;

    private final String roleSecurity;
    private final String roleLabel;

    UserRoleEnum(String roleSecurity, String roleLabel) {
        this.roleSecurity = roleSecurity;
        this.roleLabel = roleLabel;
    }

    public static class RoleSecurity {
        public static final String CUSTOMER = "ROLE_CUSTOMER";
        public static final String DRIVER = "ROLE_DRIVER";
        public static final String HUB_MANAGER = "ROLE_HUB_MANAGER";
        public static final String MASTER = "ROLE_MASTER";
    }

    public static class RoleLabel {
        public static final String CUSTOMER = "업체 담당자";
        public static final String DRIVER = "배송 담당자";
        public static final String HUB_MANAGER = "허브 담당자";
        public static final String MASTER = "마스터 관리자";
    }
}
