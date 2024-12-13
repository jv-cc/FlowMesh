package com.jv_cc.flowmesh.auth.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResDto {
    @JsonProperty(value = "user_id")
    String userId;
    String username;
    String nickname;
    String email;
    @JsonProperty(value = "slack_id")
    String slackId;
    String role;
}
