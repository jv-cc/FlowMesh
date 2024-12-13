package com.jv_cc.flowmesh.auth.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserReqDto {
    @NotNull
    @Email
    private String email;

    @NotBlank
    private String nickname;

    @JsonProperty(value = "slack_id")
    @NotBlank
    private String slackId;
}
