package com.jv_cc.flowmesh.auth.infrastructor.constants;

import lombok.Getter;

@Getter
public enum PageSortType {
    USERNAME("username"),
    NICKNAME("nickname"),
    EMAIL("email"),
    ROLE("role");

    private final String label;

    PageSortType(String label) {
        this.label = label;
    }
}
