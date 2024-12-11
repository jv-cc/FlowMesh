package com.jv_cc.flowmesh.hub_server.application.dto;

import com.jv_cc.flowmesh.hub_server.domain.model.HubEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HubDTO {

    private Long hubId;

    public static HubDTO of(HubEntity hubEntity) {
        return HubDTO.builder()
                .hubId(hubEntity.getId())
                .build();
    }

}
