package com.jv_cc.flowmesh.auth.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SigninResDto {
    @JsonProperty("created_at")
    private String createAt;

    @JsonProperty("users_id")
    private String userId;

    private String role;
}
