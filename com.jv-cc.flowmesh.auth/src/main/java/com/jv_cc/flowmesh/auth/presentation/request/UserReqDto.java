package com.jv_cc.flowmesh.auth.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserReqDto {
    @Schema(example = "rtan1218@email.com")
    @NotNull
    @Email
    private String email;

    @Schema(example = "르탄1218")
    @NotBlank
    private String nickname;

    @Schema(example = "U18RFDJRQEB")
    @JsonProperty(value = "slack_id")
    @NotBlank
    private String slackId;
}
