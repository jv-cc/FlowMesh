package com.jv_cc.flowmesh.auth.presentation.request;

import com.jv_cc.flowmesh.auth.infrastructor.constants.PageOrderType;
import com.jv_cc.flowmesh.auth.infrastructor.constants.PageSortType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchReqDto {
    private String username;
    private Integer page;
    private Integer limit;
    private PageOrderType order;
    private PageSortType sort;
}
