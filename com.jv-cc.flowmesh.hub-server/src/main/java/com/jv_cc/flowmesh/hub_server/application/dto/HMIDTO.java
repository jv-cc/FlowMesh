package com.jv_cc.flowmesh.hub_server.application.dto;

import com.jv_cc.flowmesh.hub_server.domain.model.HMIEntity;
import com.jv_cc.flowmesh.hub_server.domain.model.HubEntity;
import com.jv_cc.flowmesh.hub_server.presentation.request.ReqHMIPostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HMIDTO {

    private Long hmiId;
    private HubEntity startHubId;
    private HubEntity endHubId;
    private Long timeRequired;
    private double distance;


    public static HMIEntity toEntity(HubEntity startHub, HubEntity endHub, double distance, Long timeRequired) {
        return HMIEntity.builder()
                .startHubId(startHub)
                .endHubId(endHub)
                .distance(distance)
                .timeRequired(timeRequired)
                .build();
    }

    public static HMIDTO of(HMIEntity hmi) {
        return HMIDTO.builder()
                .hmiId(hmi.getHmiId())
                .startHubId(hmi.getStartHubId())
                .endHubId(hmi.getEndHubId())
                .timeRequired(hmi.getTimeRequired())
                .distance(hmi.getDistance())
                .build();
    }
}
