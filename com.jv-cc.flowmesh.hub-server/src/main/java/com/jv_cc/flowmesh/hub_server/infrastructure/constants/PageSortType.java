package com.jv_cc.flowmesh.hub_server.infrastructure.constants;

import lombok.Getter;

@Getter
public enum PageSortType {
    NAME("name");

    private final String label;

    PageSortType(String label) {
        this.label = label;
    }
}
