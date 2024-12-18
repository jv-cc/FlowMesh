package com.jv_cc.flowmesh.hub_server.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jv_cc.flowmesh.hub_server.domain.model.HubEntity;
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

    public ResHubGetDTO(HubEntity hubEntity) {
        this.mangerId = hubEntity.getId();
        this.hubId = hubEntity.getId();
        this.name = hubEntity.getName();
        this.address = hubEntity.getAddress();
        this.latitude = hubEntity.getLatitude();
        this.longitude = hubEntity.getLongitude();
    }
}
