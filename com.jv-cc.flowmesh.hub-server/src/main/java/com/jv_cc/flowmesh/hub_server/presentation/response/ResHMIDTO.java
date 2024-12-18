package com.jv_cc.flowmesh.hub_server.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResHMIDTO {

    @JsonProperty(value = "hmi_id")
    private Long hmiId;

}
