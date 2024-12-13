package com.jv_cc.flowmesh.hub_server.application.dto;

import com.jv_cc.flowmesh.hub_server.domain.model.HubEntity;
import com.jv_cc.flowmesh.hub_server.presentation.request.ReqHubPostDTO;
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
    private String name;
    private String address;
    private double latitude;
    private double longitude;

    public static HubEntity toEntity(ReqHubPostDTO dto) {
        return HubEntity.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }

    public static HubDTO of(HubEntity hubEntity) {
        return HubDTO.builder()
                .hubId(hubEntity.getId())
                .name(hubEntity.getName())
                .address(hubEntity.getAddress())
                .latitude(hubEntity.getLatitude())
                .longitude(hubEntity.getLongitude())
                .build();
    }

}
