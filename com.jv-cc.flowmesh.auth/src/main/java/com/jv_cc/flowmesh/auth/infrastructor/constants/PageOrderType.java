package com.jv_cc.flowmesh.auth.infrastructor.constants;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public enum PageOrderType {
    ASC(Sort.Direction.ASC),
    DESC(Sort.Direction.DESC);

    private final Sort.Direction direction;

    PageOrderType(Sort.Direction direction) {
        this.direction = direction;
    }
}
