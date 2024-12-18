package com.jv_cc.flowmesh.auth.presentation.request;

import com.jv_cc.flowmesh.auth.infrastructor.constants.PageOrderType;
import com.jv_cc.flowmesh.auth.infrastructor.constants.PageSortType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchReqDto {
    @Schema(example = "rtan1218")
    private String username;

    @Schema(example = "0")
    private Integer page;

    @Schema(example = "10")
    private Integer limit;

    @Schema(example = "ASC")
    private PageOrderType order;

    @Schema(example = "USERNAME")
    private PageSortType sort;
}
