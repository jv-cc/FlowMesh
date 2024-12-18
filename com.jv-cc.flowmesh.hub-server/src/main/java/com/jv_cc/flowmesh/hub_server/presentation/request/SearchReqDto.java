package com.jv_cc.flowmesh.hub_server.presentation.request;

import com.jv_cc.flowmesh.hub_server.infrastructure.constants.PageOrderType;
import com.jv_cc.flowmesh.hub_server.infrastructure.constants.PageSortType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchReqDto {

    private String name;
    private Integer page;
    private Integer limit;
    private PageOrderType order;
    private PageSortType sort;

}
