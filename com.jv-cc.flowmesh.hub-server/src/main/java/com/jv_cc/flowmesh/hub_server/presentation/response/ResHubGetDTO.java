package com.jv_cc.flowmesh.hub_server.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResHubGetDTO {

    @JsonProperty(value = "manger_id")
    private Long mangerId;
    @JsonProperty(value = "hub_id")
    private Long hubId;
    private String name;
    private String address;
    private double latitude;
    private double longitude;

}
