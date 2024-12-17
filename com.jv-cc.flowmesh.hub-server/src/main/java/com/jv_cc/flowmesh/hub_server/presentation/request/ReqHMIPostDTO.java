package com.jv_cc.flowmesh.hub_server.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReqHMIPostDTO {

    @JsonProperty(value = "start_hub_id")
    @NotNull(message = "출발 허브를 입력해주세요")
    private Long startHubId;

    @JsonProperty(value = "end_hub_id")
    @NotNull(message = "도착 허브를 입력해주세요")
    private Long endHubId;

}
