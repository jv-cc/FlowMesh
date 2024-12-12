package com.jv_cc.flowmesh.auth.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SigninResDto {
    @JsonProperty("created_at")
    private String createAt;
}
