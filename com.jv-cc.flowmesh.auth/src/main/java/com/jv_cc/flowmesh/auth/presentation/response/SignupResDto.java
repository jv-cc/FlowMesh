package com.jv_cc.flowmesh.auth.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SignupResDto {
    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("created_at")
    private String createAt;
}
